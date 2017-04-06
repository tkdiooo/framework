package com.qi.sso.core.common.config;

import com.qi.common.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Class ToKenConfig
 *
 * @author 张麒 2016/6/6.
 * @version Description:
 */
public class ToKenConfig {

    private static String DEFAULT_PATH = "/token.properties";

    private final static Map<String, ToKenConfig> TOKEN_CONFIG_CACHE = new HashMap<>();

    public static ToKenConfig getInstance(String path) throws IOException {
        if (!TOKEN_CONFIG_CACHE.containsKey(path)) {
            synchronized (ToKenConfig.class) {
                if (!TOKEN_CONFIG_CACHE.containsKey(path)) TOKEN_CONFIG_CACHE.put(path, load(path));
            }
        }
        return TOKEN_CONFIG_CACHE.get(path);
    }

    public static ToKenConfig getInstance() throws IOException {
        return getInstance(DEFAULT_PATH);
    }

    private static ToKenConfig load(String path) throws IOException {
        InputStream in = ToKenConfig.class.getResourceAsStream(path);
        try {
            Properties prop = new Properties();
            prop.load(in);
            ToKenConfig config = new ToKenConfig();
            config.cacheIdKey = prop.getProperty("SSO.CacheIDKey");
            config.loginTimeout = Long.valueOf(prop.getProperty("SSO.LoginTimeout"));
            return config;
        } finally {
            FileUtil.close(in);
        }
    }

    // 加密CacheIDKey
    private String cacheIdKey;
    // session过期时间
    private Long loginTimeout;

    public String getCacheIdKey() {
        return cacheIdKey;
    }

    public Long getLoginTimeout() {
        return loginTimeout;
    }
}
