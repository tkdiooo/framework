package com.qi.common.model.model;

/**
 * Class CacheModel
 *
 * @author 张麒 2016/5/31.
 * @version Description:
 */
public class CacheModel {

    // 缓存配置文件
    private String configFile;
    // 缓存类型
    private String cacheType;
    // 缓存文件路径
    private String ehcacheConfigPath;
    //本地缓存元素的生命最长缓存时间
    private long maxLifeTime;
    //本地缓存最大大小
    private long maxCacheSize;

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public String getEhcacheConfigPath() {
        return ehcacheConfigPath;
    }

    public void setEhcacheConfigPath(String ehcacheConfigPath) {
        this.ehcacheConfigPath = ehcacheConfigPath;
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
