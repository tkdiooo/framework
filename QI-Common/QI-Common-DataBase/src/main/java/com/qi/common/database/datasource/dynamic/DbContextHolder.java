package com.qi.common.database.datasource.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class DbContextHolder
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public class DbContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    private static final Logger logger = LoggerFactory.getLogger(DbContextHolder.class);

    protected static void setDbType(String dbType) {

        logger.debug("dbtype=" + dbType);

        contextHolder.set(dbType);

    }

    protected static String getDbType() {

        return (String) contextHolder.get();

    }

    public static void clearDbType() {

        contextHolder.remove();

    }
}
