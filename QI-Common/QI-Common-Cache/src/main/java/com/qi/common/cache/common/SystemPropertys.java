package com.qi.common.cache.common;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * Class SystemPropertys
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class SystemPropertys extends PropertyPlaceholderConfigurer {

    private static PropertysBean propbean = new PropertysBean();

    public static void resolvePlaceholder(Properties props) {
        StringBuilder buff = new StringBuilder();
        if (props != null) {
            MemcacheUtil.setConfigFilePath(props.getProperty("memcache.config.file"));
            buff.append(props.getProperty("memcache.project.name"));
            buff.append(props.getProperty("memcache.project.code"));
            if (buff.length() > 1) {
                propbean.setDefaultkey(buff.toString());
            } else {
                propbean.setDefaultkey("default0");
            }
            propbean.setMaxLifeTime(Long.valueOf(props.getProperty("memcache.MaxCacheSize")));
            propbean.setMaxCacheSize(Long.valueOf(props.getProperty("memcache.MaxLifeTime")));
            MemcacheUtil.setPropbean(propbean);
        }
    }
}
