package com.qi.common.cache.ehcache;

import com.qi.common.cache.ehcache.inf.CacheOperator;
import com.qi.common.model.model.CacheModel;
import com.qi.common.tool.Assert;
import com.qi.common.util.StringUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class CacheFactory
 *
 * @author 张麒 2016/5/31.
 * @version Description:
 */
public class CacheFactory {

    private static final String CACHE_TYPE_EH = "chcache";

    private static final Map<String, CacheOperator> CACHE_HANDLE = new HashMap<>();
    private final static CacheFactory cacheFactory = new CacheFactory();

    private CacheFactory() {
    }

    public static CacheOperator getInstance(final CacheModel cacheVO) throws IOException {
        Assert.notNull(cacheVO, "CacheVO对象为空");
        CacheOperator cache = CACHE_HANDLE.get(cacheVO.getConfigFile());
        if (cache == null) {
            synchronized (cacheFactory) {
                String cacheType = cacheVO.getCacheType();
                if (CACHE_TYPE_EH.equals(cacheType)) {
                    String ehCachePath = cacheVO.getEhcacheConfigPath();
                    if (StringUtil.isNotBlank(ehCachePath)) {
                        cache = new EHCacheOperator(ehCachePath);
                    } else {
                        cache = new EHCacheOperator();
                    }
                } else {
                    DefaultCache<Object, Object> defaultCache = new DefaultCache<>(cacheVO.getConfigFile(), cacheVO.getMaxCacheSize(), cacheVO.getMaxLifeTime());
                    cache = new DefaultCacheOperator(defaultCache);
                }
                CACHE_HANDLE.put(cacheVO.getConfigFile(), cache);
            }
        }
        return cache;
    }

    public static CacheOperator getEHCache(final String key) {
        CacheOperator cache = CACHE_HANDLE.get(key);
        if (cache == null) {
            synchronized (cacheFactory) {
                if (StringUtil.isNotBlank(key)) {
                    cache = new EHCacheOperator(key);
                } else {
                    cache = new EHCacheOperator();
                }
                CACHE_HANDLE.put(key, cache);
            }
        }
        return cache;
    }

    public static CacheOperator getDefaultCache(final String key, long maxCacheSize, long maxLifeTime) {
        CacheOperator cache = CACHE_HANDLE.get(key);
        if (cache == null) {
            synchronized (cacheFactory) {
                DefaultCache<Object, Object> defaultCache = new DefaultCache<>(key, maxCacheSize, (System.currentTimeMillis() + maxLifeTime));
                cache = new DefaultCacheOperator(defaultCache);
                CACHE_HANDLE.put(key, cache);
            }
        }
        return cache;
    }
}
