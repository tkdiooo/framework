package com.qi.common.cache.ehcache.inf;


public interface CacheOperator {

    Object putObjectToCache(Object key, Object value);

    void clear();

    Object removeObjectOfCache(Object key);

    Object getObjectOfCache(Object key);

    Object getCacheName();

    int getCacheSize();
}
