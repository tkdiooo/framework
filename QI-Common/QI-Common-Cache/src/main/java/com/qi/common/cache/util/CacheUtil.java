package com.qi.common.cache.util;

import com.qi.common.cache.memcached.MemcachedController;
import com.qi.common.util.SpringContextUtil;

import java.util.List;
import java.util.Map;

/**
 * Class CacheUtil
 *
 * @author 张麒 2016/9/2.
 * @version Description:
 */
public class CacheUtil {

    private static MemcachedController memcacheClient = (MemcachedController) SpringContextUtil.getBean("memcachedClient");

    public static MemcachedController getMemcacheClient() {
        return memcacheClient;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getList(String key) {
        return (List<T>) memcacheClient.getCacheClient().get(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getObject(String key) {
        return (T) memcacheClient.getCacheClient().get(key);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> getMap(String key) {
        return (Map<K, V>) memcacheClient.getCacheClient().get(key);
    }
}
