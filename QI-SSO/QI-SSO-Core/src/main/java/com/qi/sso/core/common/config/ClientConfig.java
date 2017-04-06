package com.qi.sso.core.common.config;

import com.qi.common.constants.CommonConstants;
import com.qi.common.model.model.CacheModel;
import com.qi.common.util.FileUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 */
public class ClientConfig {

    public static final String DEFAULT_PATH = "client.properties";

    private final static Map<String, ClientConfig> CLIENT_CONFIG_CACHE = new HashMap<>();

    public static ClientConfig getInstance(String propFile) throws IOException {
        if (!CLIENT_CONFIG_CACHE.containsKey(propFile)) {
            synchronized (ClientConfig.class) {
                if (!CLIENT_CONFIG_CACHE.containsKey(propFile)) CLIENT_CONFIG_CACHE.put(propFile, load(propFile));
            }
        }
        return CLIENT_CONFIG_CACHE.get(propFile);
    }

    public static ClientConfig getInstance() throws IOException {
        return getInstance(DEFAULT_PATH);
    }

    private static ClientConfig load(String propFile) throws IOException {
        String path = System.getProperty(CommonConstants.RESOURCES_CONFIG_PATH) == null ? System.getenv(CommonConstants.RESOURCES_CONFIG_PATH) : System.getProperty(CommonConstants.RESOURCES_CONFIG_PATH);
        InputStream in = new BufferedInputStream(new FileInputStream(path.concat("QI-SSO/" + propFile)));

        try {
            Properties prop = new Properties();
            prop.load(in);
            ClientConfig config = new ClientConfig();
            config.connectType = prop.getProperty("SSO.ConnectType");
            config.connectUrl = prop.getProperty("SSO.ConnectUrl");
            config.readTimeout = Long.valueOf(prop.getProperty("SSO.ConnectTimeout"));
            config.connectTimeout = Long.valueOf(prop.getProperty("SSO.ReadTimeout"));
            config.isCache = Boolean.parseBoolean(prop.getProperty("SSO.IsCache"));
            if (config.isCache) {
                config.cacheVO = new CacheModel();
                config.cacheVO.setConfigFile(path);
                config.cacheVO.setCacheType(prop.getProperty("SSO.Cache.Type"));
                config.cacheVO.setEhcacheConfigPath(prop.getProperty("SSO.ehcache.config.path"));
                config.cacheVO.setMaxLifeTime(Long.valueOf(prop.getProperty("SSO.MaxLifeTime")));
                config.cacheVO.setMaxCacheSize(Long.valueOf(prop.getProperty("SSO.MaxCacheSize")));
            }
            return config;
        } finally {
            FileUtil.close(in);
        }
    }

    private ClientConfig() {

    }

    // 与服务端通信通道类型
    private String connectType;
    // 与服务端通信地址
    private String connectUrl;
    // 是否启用本地缓存
    private boolean isCache;
    //建立连接超时时间
    private long connectTimeout = 5000;
    //读操作超时时间
    private long readTimeout = 5000;

    private CacheModel cacheVO;

    public long getReadTimeout() {
        return readTimeout;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public boolean isCache() {
        return isCache;
    }

    public String getConnectUrl() {
        return connectUrl;
    }

    public String getConnectType() {
        return connectType;
    }

    public CacheModel getCacheVO() {
        return cacheVO;
    }
}
