package com.qi.common.database.datasource.dynamic;

import com.qi.common.database.dao.DBType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class DbTypeHolder
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public class DbTypeHolder {

    private static final ThreadLocal<DBType> contextHolder = new ThreadLocal<DBType>();

    private static final Logger logger = LoggerFactory.getLogger(DbTypeHolder.class);

    public static void setDbType(DBType dbType) {

        logger.debug("dbtype=" + dbType);

        contextHolder.set(dbType);

    }

    public static DBType getDbType() {

        return (DBType) contextHolder.get();

    }

    public static void clearDbType() {

        contextHolder.remove();

    }
}
