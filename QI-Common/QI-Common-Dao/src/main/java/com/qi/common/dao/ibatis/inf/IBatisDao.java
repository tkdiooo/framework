package com.qi.common.dao.ibatis.inf;

import com.qi.common.database.dto.IBaseDto;
import com.qi.common.model.model.PagingModel;

import java.io.Serializable;
import java.util.List;

/**
 * Class IBatisDao
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public interface IBatisDao<T extends IBaseDto, PK extends Serializable, Example> {

    /**
     * 根据条件统计数据量
     *
     * @param example Example
     * @return Integer
     */
    Integer countByExample(Example example);

    /**
     * 根据条件删除数据
     *
     * @param example Example
     * @return primary key
     */
    PK deleteByExample(Example example);

    /**
     * 根据主键删除数据
     *
     * @param key primary key
     * @return primary key
     */
    PK deleteByPrimaryKey(PK key);

    /**
     * 插入一条数据(全量)
     *
     * @param record 数据实体
     */
    void insert(T record);

    /**
     * 插入一条数据(非空)
     *
     * @param record 数据实体
     */
    void insertSelective(T record);

    /**
     * 插入一条数据，返回主键
     *
     * @param record 数据实体
     * @return primary key
     */
    PK insertGetPrimaryKey(T record);

    /**
     * 根据条件查询数据集
     *
     * @param example Example
     * @return List<T>
     */
    List<T> selectByExample(Example example);

    /**
     * 根据条件查询数据对象
     *
     * @param example Example
     * @return List<T>
     */
    T getByExample(Example example);

    /**
     * 根据主键查询单条数据
     *
     * @param key primary key
     * @return 数据实体
     */
    T selectByPrimaryKey(PK key);

    /**
     * 根据主键更新实体中不为空的属性对应列
     *
     * @param record 数据实体
     */
    void updateByPrimaryKeySelective(T record);

    /**
     * 分页查询
     *
     * @param parameters Example
     * @param page       PagingModel
     * @return PagingModel
     */
    PagingModel<T> queryPagination(Example parameters, PagingModel<T> page);

    /**
     * 分页查询
     *
     * @param pageInfo PagingModel
     */
    void queryPagination(PagingModel<T> pageInfo);

    /**
     * 获取Example
     *
     * @return Example
     */
    Example getExample();

    /**
     * 唯一校验
     *
     * @param example   Example
     * @param fieldName 排除条件字段
     * @param condition 排除条件值
     * @return boolean
     */
    boolean checkUnique(Example example, String fieldName, Object condition);
}
