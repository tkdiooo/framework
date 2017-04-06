package com.qi.common.basic.service;

import com.qi.common.model.model.PagingModel;
import com.qi.common.model.vo.paging.PagingVO;

/**
 * Class BasicReadService
 *
 * @author 张麒 2016/7/27.
 * @version Description:
 */
public interface BasicReadService<T> {

    /**
     * 分页查询
     *
     * @param pageInfo PagingModel
     * @return PagingVO
     */
    PagingVO queryPagination(PagingModel<T> pageInfo);

    /**
     * 通过Guid获取Model
     *
     * @param guid Guid
     * @return Model
     */
    T getModelByGuid(Long guid);

    /**
     * 唯一验证
     *
     * @param model Model
     * @return boolean
     */
    boolean checkUnique(T model);
}
