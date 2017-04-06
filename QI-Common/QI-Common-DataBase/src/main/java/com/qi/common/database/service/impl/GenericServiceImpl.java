package com.qi.common.database.service.impl;

import com.qi.common.database.dto.IBaseDto;
import com.qi.common.database.service.IGenericService;

import java.io.Serializable;

/**
 * Class GenericServiceImpl
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class GenericServiceImpl<T extends IBaseDto, PK extends Serializable> extends BaseServiceImpl implements IGenericService<T, PK> {

//    private final Logger     logger = Logger.getLogger(this.getClass());

//    private BaseDao<T, PK> baseDao;
//
//    public GenericServiceImpl() {
//
//    }
//
//    public GenericServiceImpl(BaseDao<T, PK> baseDao) {
//        this.baseDao = baseDao;
//    }
//
//    public abstract BaseDao<T, PK> getBaseDao() ;
}
