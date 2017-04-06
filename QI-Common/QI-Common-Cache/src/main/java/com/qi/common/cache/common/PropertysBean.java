package com.qi.common.cache.common;

/**
 * Class PropertysBean
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class PropertysBean {

    private String Defaultkey;
    //本地缓存元素的生命最长缓存时间
    private long maxLifeTime;
    //本地缓存最大大小
    private long maxCacheSize;

    public String getDefaultkey() {
        return Defaultkey;
    }

    public void setDefaultkey(String defaultkey) {
        Defaultkey = defaultkey;
    }

    public long getMaxLifeTime() {
        return maxLifeTime;
    }

    public void setMaxLifeTime(long maxLifeTime) {
        this.maxLifeTime = maxLifeTime;
    }

    public long getMaxCacheSize() {
        return maxCacheSize;
    }

    public void setMaxCacheSize(long maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }
}
