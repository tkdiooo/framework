package com.qi.sso.common.config;


import com.qi.common.tool.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取Cookie配置
 */
public class CookiesConfig {

    private static final Logger logger = new Logger(CookiesConfig.class);

    private Properties prop = null;

    private static final Object ob = new Object();

    private static CookiesConfig config = null;

    public static CookiesConfig getInstance() {
        if (config == null) {
            synchronized (ob) {
                if (config == null) {
                    config = new CookiesConfig();
                }
            }
        }
        return config;
    }

    public static CookiesConfig getInstance(Properties prop) {
        if (config == null) {
            synchronized (ob) {
                if (config == null) {
                    config = new CookiesConfig(prop);
                }
            }
        }
        return config;
    }

    public static CookiesConfig getInstance(String pathname) {
        if (config == null) {
            synchronized (ob) {
                if (config == null) {
                    config = new CookiesConfig(pathname);
                }
            }
        }
        return config;
    }


    private CookiesConfig() {
        try {
            prop = new Properties();
            InputStream in = CookiesConfig.class.getResourceAsStream("/cookies.properties");
            prop.load(in);
        } catch (Exception e) {
            logger.error("Cookies 文件加载失败", e);
            e.printStackTrace();
        }
    }

    private CookiesConfig(Properties config) {
        this.prop = config;
    }

    private CookiesConfig(String pathname) {
        try {
            prop = new Properties();
            InputStream in = new FileInputStream(pathname);
            prop.load(in);
        } catch (Exception e) {
            logger.error("Cookies 文件加载失败", e);
            e.printStackTrace();
        }
    }

    public String getDomain() {
        return prop.getProperty("cookie.domain");
    }

    public int getLoginTimeOut() {
        return Integer.parseInt(prop.getProperty("cookie.loginTimeOut"));
    }

}

