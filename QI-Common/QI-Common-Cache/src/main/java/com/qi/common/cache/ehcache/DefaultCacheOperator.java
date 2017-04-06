package com.qi.common.cache.ehcache;

import com.qi.common.cache.ehcache.inf.CacheOperator;

public class DefaultCacheOperator implements CacheOperator {

    private DefaultCache<Object, Object> cache = null;

    public DefaultCacheOperator(DefaultCache<Object, Object> cache) {
        this.cache = cache;
    }

    public Object putObjectToCache(Object key, Object value) {
        return cache.put(key, value);
    }

    public void clear() {
        cache.clear();
    }

    public Object removeObjectOfCache(Object key) {
        return cache.remove(key);
    }

    public Object getObjectOfCache(Object key) {
        return cache.get(key);
    }

    public Object getCacheName() {
        return cache.getName();
    }

    public int getCacheSize() {
        return cache.size();
    }
}
