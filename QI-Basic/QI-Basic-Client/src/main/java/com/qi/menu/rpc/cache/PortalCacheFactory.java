package com.qi.menu.rpc.cache;

import com.qi.common.cache.util.CacheUtil;
import com.qi.common.util.ListUtil;
import com.qi.menu.model.dto.SystemDto;
import com.qi.menu.rpc.constants.CacheConstants;

import java.util.List;

/**
 * Class PortalCacheFactory
 *
 * @author 张麒 2016/12/2.
 * @version Description:
 */
public class PortalCacheFactory {

    public static List<SystemDto> getBasicSystemCache() {
        return CacheUtil.getList(CacheConstants.PORTAL_MEMCACHED_KEY);
    }

    public static SystemDto getBasicSystemCacheByCode(String code) {
        List<SystemDto> dataSet = CacheUtil.getList(CacheConstants.PORTAL_MEMCACHED_KEY);
        if (null != dataSet) {
            return ListUtil.getBeanByList("code", code, dataSet);
        } else {
            return null;
        }
    }
}
