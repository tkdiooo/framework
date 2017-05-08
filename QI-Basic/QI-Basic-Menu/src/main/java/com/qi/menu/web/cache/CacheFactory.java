package com.qi.menu.web.cache;

import com.qi.common.cache.util.CacheUtil;
import com.qi.common.util.BeanUtil;
import com.qi.common.util.ListUtil;
import com.qi.common.util.SpringContextUtil;
import com.qi.menu.model.dto.SystemDto;
import com.qi.menu.model.domain.BasicSystem;
import com.qi.menu.rpc.constants.CacheConstants;
import com.qi.menu.web.service.read.SystemReadService;

import java.util.ArrayList;
import java.util.List;

/**
 * Class CacheFactory
 *
 * @author 张麒 2016/9/23.
 * @version Description:
 */
public class CacheFactory {

    private static SystemReadService readService = (SystemReadService) SpringContextUtil.getBean("PortalReadService");


    public static List<SystemDto> getBasicSystemCache() {
        List<SystemDto> dataSet = CacheUtil.getList(CacheConstants.PORTAL_MEMCACHED_KEY);
        if (dataSet == null) {
            dataSet = refreshBasicSystemCache();
        }
        return dataSet;
    }

    public static SystemDto getBasicSystemCacheByCode(String code) {
        List<SystemDto> dataSet = CacheUtil.getList(CacheConstants.PORTAL_MEMCACHED_KEY);
        if (null != dataSet) {
            SystemDto result = ListUtil.getBeanByList("code", code, dataSet);
            if (null != result) {
                return result;
            } else {
                return getBasicSystemByCode(dataSet, code);
            }
        } else {
            return getBasicSystemByCode(new ArrayList<>(), code);
        }
    }

    public static List<SystemDto> refreshBasicSystemCache() {
        List<BasicSystem> dataSet = readService.findNormalPortal();
        List<SystemDto> list = ListUtil.copyConvert(SystemDto.class, dataSet);
        CacheUtil.getMemcacheClient().getCacheClient().putTimeOut(CacheConstants.PORTAL_MEMCACHED_KEY, list, 24 * 60 * 60 * 1000);
        return list;
    }

    private static SystemDto getBasicSystemByCode(List<SystemDto> dataSet, String code) {
        BasicSystem result = readService.getBasicSystemByCode(code);
        SystemDto dto = null;
        if (null != result) {
            dto = BeanUtil.copyPropertiesNotEmpty(SystemDto.class, result);
            dataSet.add(dto);
            CacheUtil.getMemcacheClient().getCacheClient().add(CacheConstants.PORTAL_MEMCACHED_KEY, dataSet);
        }
        return dto;
    }
}
