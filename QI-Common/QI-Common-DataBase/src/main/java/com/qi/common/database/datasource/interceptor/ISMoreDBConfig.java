package com.qi.common.database.datasource.interceptor;

/**
 * Class ISMoreDBConfig
 * 保存数据源以DAO为准的线程标识
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public class ISMoreDBConfig {


    private static final ThreadLocal<String> isconfig = new ThreadLocal<>();

    protected static void setDBConfig(String dbType) {

        isconfig.set(dbType);

    }

    public static String getDBConfig() {

        return isconfig.get();

    }

    protected static void clearDBConfig() {

        isconfig.remove();

    }
}
