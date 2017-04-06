package com.qi.common.database.service;

import com.qi.common.database.dto.IBaseDto;

import java.io.Serializable;

/**
 * interface IGenericService
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public interface IGenericService<T extends IBaseDto, PK extends Serializable> extends IBaseService {
}
