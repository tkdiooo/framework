package com.qi.common.database.dao.impl;

import com.qi.common.database.dao.IDefaultColumnManage;

/**
 * Class DefaultColumnImpl
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public abstract class DefaultColumnImpl implements IDefaultColumnManage {

//    private final Logger logger = Logger.getLogger(this.getClass());

    public abstract void setCreateUserID(Object ob) throws Exception;

    public abstract void setUpdateUserID(Object ob) throws Exception;
}
