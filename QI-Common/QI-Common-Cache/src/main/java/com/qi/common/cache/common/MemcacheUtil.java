package com.qi.common.cache.common;

/**
 * Class MemcacheUtil
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class MemcacheUtil {

    private static PropertysBean propbean;

    private static String configFilePath;

    public static String getConfigFilePath() {
        return configFilePath;
    }

    public static void setConfigFilePath(String configFilePath) {
        MemcacheUtil.configFilePath = configFilePath;
    }

    public static PropertysBean getPropbean() {
        return propbean;
    }

    public static void setPropbean(PropertysBean propbean) {
        MemcacheUtil.propbean = propbean;
    }
}
