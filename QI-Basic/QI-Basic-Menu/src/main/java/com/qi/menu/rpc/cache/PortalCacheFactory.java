package com.qi.menu.rpc.cache;

import com.qi.common.util.BeanUtil;
import com.qi.common.util.ListUtil;
import com.qi.common.util.MapUtil;
import com.qi.menu.model.dto.SystemDto;
import com.qi.menu.model.domain.BasicSystem;
import com.qi.menu.web.cache.CacheFactory;

import java.util.List;
import java.util.Map;

/**
 * Class PortalCacheFactory
 *
 * @author 张麒 2016/9/5.
 * @version Description:
 */
public class PortalCacheFactory {

    public static Map<String, SystemDto> getPortalDtoCache() {
        List<SystemDto> dataSet = CacheFactory.getBasicSystemCache();
        if (ListUtil.isContentNull(dataSet)) {
            dataSet = CacheFactory.refreshBasicSystemCache();
        }
        return MapUtil.toMap(dataSet, "code");
    }

    public static SystemDto getPortalDtoCacheByCode(String code) {
        BasicSystem result = CacheFactory.getBasicSystemCacheByCode(code);
        return BeanUtil.copyPropertiesNotEmpty(SystemDto.class, result);
    }

    public static void refreshPortalDtoCache() {
        CacheFactory.refreshBasicSystemCache();
    }
}
