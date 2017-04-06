package com.qi.common.database.datasource.dynamic;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class RecoveryDataSource
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class RecoveryDataSource {

    //读
    public static final Map<Object, DataSource> READ_RECOVERY = new ConcurrentHashMap<>();
    //写
    public static final Map<Object, DataSource> WRITE_RECOVERY = new ConcurrentHashMap<>();
}
