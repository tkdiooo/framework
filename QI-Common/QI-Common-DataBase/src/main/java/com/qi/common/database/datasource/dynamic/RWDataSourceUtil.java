package com.qi.common.database.datasource.dynamic;

import com.qi.common.database.dao.DBType;

/**
 * Class RWDataSourceUtil
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class RWDataSourceUtil {

    public static void setReadDataSource(){
        DbTypeHolder.setDbType(DBType.READ);
    }

    public static void setWriteDataSource(){
        DbTypeHolder.setDbType(DBType.WRITE);
    }
}
