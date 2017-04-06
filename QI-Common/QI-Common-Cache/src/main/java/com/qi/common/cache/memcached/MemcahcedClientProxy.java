package com.qi.common.cache.memcached;

import com.danga.MemCached.MemCachedClient;
import com.qi.common.cache.common.MemcacheUtil;
import com.qi.common.cache.ehcache.CacheFactory;
import com.qi.common.cache.memcached.inf.IMemcachedService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Class MemcahcedClientProxy
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class MemcahcedClientProxy implements IMemcachedService {

    private static final Logger logger = LoggerFactory.getLogger(MemcachedController.class);

    private static MemCachedClient mc = null;

    private static MemcahcedClientProxy instance = null;

    private static final Object ob = new Object();

    private static boolean success = true;

    /**
     * <pre>
     * MEMCACHE的单例
     * </pre>
     *
     * @return MemcahcedClientProxy
     */
    protected static MemcahcedClientProxy getInstance() {
        if (instance == null) {
            synchronized (ob) {
                if (instance == null) {
                    try {
                        instance = new MemcahcedClientProxy();
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        }
        return instance;
    }

    private MemcahcedClientProxy() {

    }

    private Object getCache(String key) {
        if (success) {
            return mc.get(addPrefix(key));
        } else {
            return CacheFactory.getDefaultCache(addPrefix(key), MemcacheUtil.getPropbean().getMaxCacheSize(), MemcacheUtil.getPropbean().getMaxLifeTime()).getObjectOfCache(addPrefix(key));
        }
    }

    private Boolean addCache(String key, Object value) {
        if (success) {
            return mc.add(addPrefix(key), value);
        } else {
            CacheFactory.getDefaultCache(addPrefix(key), MemcacheUtil.getPropbean().getMaxCacheSize(), MemcacheUtil.getPropbean().getMaxLifeTime()).putObjectToCache(addPrefix(key), value);
            return true;

        }
    }

    private Boolean addCache(String key, Object value, Date expiry) {
        if (success) {
            return mc.add(addPrefix(key), value, expiry);
        } else {
            CacheFactory.getDefaultCache(addPrefix(key), MemcacheUtil.getPropbean().getMaxCacheSize(), MemcacheUtil.getPropbean().getMaxLifeTime()).putObjectToCache(addPrefix(key), value);
            return true;
        }
    }

    private Boolean putCache(String key, Object value) {
        if (success) {
            return mc.set(addPrefix(key), value);
        } else {
            CacheFactory.getDefaultCache(addPrefix(key), MemcacheUtil.getPropbean().getMaxCacheSize(), MemcacheUtil.getPropbean().getMaxLifeTime()).putObjectToCache(addPrefix(key), value);
            return true;
        }
    }

    private Boolean putCache(String key, Object value, Date expiry) {
        if (success) {
            return mc.set(addPrefix(key), value, expiry);
        } else {
            CacheFactory.getDefaultCache(addPrefix(key), MemcacheUtil.getPropbean().getMaxCacheSize(), MemcacheUtil.getPropbean().getMaxLifeTime()).putObjectToCache(addPrefix(key), value);
            return true;
        }
    }

    public Object get(String key) {
        if (StringUtils.isBlank(key)) {
            logger.warn("param key can not null");
            return null;
        }
        logger.info("memcached key = " + addPrefix(key));
        return getCache(key);
    }

    public Boolean put(String key, Object value) {
        if (StringUtils.isBlank(key) || value == null || "".equals(value)) {
            logger.warn("param key,value can not null");
            return false;
        }
        logger.info("memcached key = " + addPrefix(key));
        return putCache(key, value);
    }

    public Boolean add(String key, Object value) {
        if (StringUtils.isBlank(key) || value == null || "".equals(value)) {
            logger.warn("param key,value can not null");
            return false;
        }
        logger.info("memcached key = " + addPrefix(key));
        return addCache(key, value);
    }

    public Boolean putTimeOut(String key, Object value, int timeout) {
        if (StringUtils.isBlank(key) || value == null || "".equals(value) || timeout < 1) {
            logger.warn("param key,value can not null");
            return false;
        }
        logger.info("memcached key = " + addPrefix(key));
        return putCache(key, value, new Date(timeout));
    }

    public Boolean put(String key, Object value, Date expiry) {
        if (StringUtils.isBlank(key) || value == null || "".equals(value) || expiry == null) {
            logger.warn("param key,value,expiry can not null");
            return false;
        }
        logger.info("memcached key = " + addPrefix(key));
        return putCache(key, value, expiry);
    }

    public Boolean add(String key, Object value, Date expiry) {
        if (StringUtils.isBlank(key) || value == null || "".equals(value) || expiry == null) {
            logger.warn("param key,value,expiry can not null");
            return false;
        }
        logger.info("memcached key = " + addPrefix(key));
        return addCache(key, value, expiry);
    }

    public Boolean remove(String key) {
        if (StringUtils.isBlank(key)) {
            logger.warn("param key can not null");
            return false;
        }
        logger.info("memcached key = " + addPrefix(key));
        return mc.delete(addPrefix(key));
    }

    public Boolean replace(String key, Object value) {
        if (StringUtils.isBlank(key) || value == null || "".equals(value)) {
            logger.warn("param key,value can not null");
            return false;
        }
        logger.info("memcached key = " + addPrefix(key));
        return mc.replace(addPrefix(key), value);
    }

    public boolean flushAll() {
        return mc.flushAll();
    }

    public Object put(String key, Object value, Integer TTL) {
        if (StringUtils.isBlank(key) || value == null || "".equals(value) || TTL == null
                || TTL < 1) {
            logger.warn("param key,value,expiry can not null");
            return false;
        }
        logger.info("memcached key = " + addPrefix(key));
        return mc.set(addPrefix(key), value, TTL);
    }

    public Object add(String key, Object value, Integer TTL) {
        if (StringUtils.isBlank(key) || value == null || "".equals(value) || TTL == null || TTL < 1) {
            logger.warn("param key,value,expiry can not null");
            return false;
        }
        logger.info("memcached key = " + addPrefix(key));
        return mc.add(addPrefix(key), value, TTL);
    }

    public Object addTimeOut(String key, Object value, int timeout) {
        if (StringUtils.isBlank(key) || value == null || "".equals(value)) {
            logger.info("param key,value,expiry can not null");
            return false;
        }
        logger.info("memcached key = " + addPrefix(key));
        return mc.add(addPrefix(key), value, new Date(timeout * 1000));
    }

    private String addPrefix(String key) {
        return (MemcacheUtil.getPropbean().getDefaultkey() + key).trim();
    }

    protected static MemCachedClient getMc() {
        return mc;
    }

    protected static void setMc(MemCachedClient mc) {
        MemcahcedClientProxy.mc = mc;
    }

    protected static void setSuccess(boolean success) {
        MemcahcedClientProxy.success = success;
    }

}
