package com.qi.common.database.datasource.interceptor;

import com.qi.common.database.datasource.dynamic.DbContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class MoreDBConfigClass
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class MoreDBConfigClass {

    private static final Logger logger = LoggerFactory.getLogger(MoreDBConfigClass.class);

    public void addMoreDBConfig() {

        logger.debug("addMoreDBConfig is run");

        ISMoreDBConfig.setDBConfig("true");

    }

    public void clearMoreDBConfig() {

        logger.debug("clearMoreDBConfig is run");

        ISMoreDBConfig.clearDBConfig();

        DbContextHolder.clearDbType();

    }
}
