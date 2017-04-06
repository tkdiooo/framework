package com.qi.common.database.dao.impl;

import com.qi.common.database.dao.DBType;

/**
 * Class AbstractReadWriteMySqlDao
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public abstract class AbstractReadWriteMySqlDao<T extends DBType> extends BaseMySqlDaoImpl {

    public AbstractReadWriteMySqlDao(T dbtype) {
        super.setDbType(dbtype);
    }
}
