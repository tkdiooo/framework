package com.qi.common.cache.ehcache;

import com.qi.common.cache.ehcache.inf.CacheOperator;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author chen.z
 */
public class EHCacheOperator implements CacheOperator {

    private static final String CAHCE_NAME = "uamsCache";

    private static CacheManager cm;
    private static net.sf.ehcache.Cache cache;

    public EHCacheOperator(String ehConfigFile) {
        if (cm == null) {

            InputStream is = null;
            try {
                is = new FileInputStream(ehConfigFile);
                cm = CacheManager.newInstance(is);
            } catch (CacheException e1) {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                e1.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (cache == null) {
            cache = cm.getCache(CAHCE_NAME);
        }
    }

    public EHCacheOperator() {
        if (cm == null) {

            InputStream is = null;
            try {
                is = EHCacheOperator.class.getResourceAsStream("/ehcache.xml");
                cm = CacheManager.newInstance(is);
            } catch (CacheException e1) {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                e1.printStackTrace();
            }
        }
        if (cache == null) {
            cache = cm.getCache(CAHCE_NAME);
        }
    }

    public EHCacheOperator(net.sf.ehcache.Cache cache) {
        this.cache = cache;
    }

    @Override
    public Object putObjectToCache(Object key, Object value) {
        Element e = new Element(key, value);
        cache.put(e);
        return null;
    }

    @Override
    public void clear() {
        cache.removeAll();

    }

    @Override
    public Object removeObjectOfCache(Object key) {
        return cache.remove(key);
    }

    @Override
    public Object getObjectOfCache(Object key) {
        return cache.get(key).getObjectValue();
    }

    @Override
    public Object getCacheName() {
        return cache.getName();
    }


    @Override
    public int getCacheSize() {
        return cache.getSize();
    }

    public List getAllKey() {
        return cache.getKeys();
    }


}
