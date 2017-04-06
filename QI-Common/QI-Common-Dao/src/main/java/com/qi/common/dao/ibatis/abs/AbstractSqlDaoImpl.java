package com.qi.common.dao.ibatis.abs;

import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.model.model.PagingModel;
import com.qi.common.database.dto.IBaseDto;
import com.qi.common.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Dao基类，封装了常用操作 <br>
 * SqlMap的namespace应当为子类DaoSqlMapNamespace标注名称。<br>
 * 注：分页查询已经封装并生成了count查询，无需在SqlMap中在定义count查询语句。<br>
 *
 * @author :  yeq
 * @version :  2014年10月29日
 * @Filename :  AbstractSqlDaoImpl.java
 * @Package :  com.egolden.model.dao.impl
 * @Description :
 */
public class AbstractSqlDaoImpl<T extends IBaseDto, PK extends Serializable, Example> extends CommonMySqlDaoImpl<T, PK> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractSqlDaoImpl.class);

    private String sqlMapNamespace;
    private Class<T> persistentClass;

    public AbstractSqlDaoImpl(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @PostConstruct
    public void initClient() {
        if (StringUtils.isEmpty(sqlMapNamespace)) {
            DaoSqlMapNamespace annotation = getClass().getAnnotation(DaoSqlMapNamespace.class);
            if (annotation != null) {
                sqlMapNamespace = annotation.value();
            }
        }
    }


    /**
     * 获取配置文件中的statementName
     *
     * @param name
     * @return
     */
    protected String getStatName(String name) {
        return String.format("%s.%s", getSqlMapNamespace(), name);
    }

    /**
     * 获取SqlMapNamespace，默认为Annotation名称
     *
     * @return
     */
    protected String getSqlMapNamespace() {
        return sqlMapNamespace;
    }

    @SuppressWarnings("unchecked")
    protected List<T> queryForList(String name, Object parameterObject) {
        return getSqlMapClientTemplate().queryForList(getStatName(name), parameterObject);
    }

    @SuppressWarnings("unchecked")
    protected <C> List<C> queryForList(String name, Object parameterObject, Class<C> cls) {
        return getSqlMapClientTemplate().queryForList(getStatName(name), parameterObject);
    }

    @SuppressWarnings("unchecked")
    protected T queryForObject(String name, Object parameterObject) {
        return (T) getSqlMapClientTemplate().queryForObject(getStatName(name), parameterObject);
    }

    @SuppressWarnings("unchecked")
    protected <C> C queryForObject(String name, Object parameterObject, Class<C> cls) {
        return (C) getSqlMapClientTemplate().queryForObject(getStatName(name), parameterObject);
    }


    public Integer countByExample(Example example) {
        return (Integer) getSqlMapClientTemplate().queryForObject(getStatName("ibatorgenerated_countByExample"), example);
    }

    @SuppressWarnings("unchecked")
    public PK deleteByExample(Example example) {
        Integer rows = getSqlMapClientTemplate().delete(getStatName("ibatorgenerated_deleteByExample"), example);
        return (PK) rows;
    }

    public PK deleteByPrimaryKey(PK key) {
        Object pk = wrapPrimaryKeyObj(key);
        getSqlMapClientTemplate().delete(getStatName("ibatorgenerated_deleteByPrimaryKey"), pk);
        return key;
    }

    public void insert(T record) {
        getSqlMapClientTemplate().insert(getStatName("ibatorgenerated_insert"), record);
    }

    public void insertSelective(T record) {
        getSqlMapClientTemplate().insert(getStatName("ibatorgenerated_insertSelective"), record);
    }

    @SuppressWarnings("unchecked")
    public PK insertGetPrimaryKey(T record) {
        return (PK) getSqlMapClientTemplate().insert(getStatName("ibatorgenerated_insertSelective"), record);
    }

    @SuppressWarnings("unchecked")
    public List<T> selectByExample(Example example) {
        return getSqlMapClientTemplate().queryForList(getStatName("ibatorgenerated_selectByExample"), example);
    }

    @SuppressWarnings("unchecked")
    public T getByExample(Example example) {
        return (T) getSqlMapClientTemplate().queryForObject(getStatName("ibatorgenerated_selectByExample"), example);
    }

    @SuppressWarnings("unchecked")
    public T selectByPrimaryKey(PK key) {
        Object pk = wrapPrimaryKeyObj(key);
        return (T) getSqlMapClientTemplate().queryForObject(getStatName("ibatorgenerated_selectByPrimaryKey"), pk);
    }

    @SuppressWarnings("unchecked")
    public void updateByPrimaryKeySelective(T record) {
        getSqlMapClientTemplate().update(getStatName("ibatorgenerated_updateByPrimaryKeySelective"), record);
    }

    @SuppressWarnings("unchecked")
    public PagingModel<T> queryPagination(Example parameters, PagingModel<T> page) {
        int count = countByExample(parameters);
        List<T> list = getSqlMapClientTemplate().queryForList(getStatName("ibatorgenerated_selectByExample"), parameters, page.getStartIndex(), page.getPageSize());
        page.setResult(list);
        page.setTotalCount(count);
        return page;
    }

    @SuppressWarnings("unchecked")
    public void queryPagination(PagingModel<T> pageInfo) {
        Integer count = queryForObject("countByPagination", pageInfo, Integer.class);
        List list = queryForList("queryByPagination", pageInfo);
        pageInfo.setResult(list);
        pageInfo.setTotalCount(count);
    }

    /**
     * 包装成xxxInfo以适应ibator生成的sqlmap
     *
     * @param key
     * @return
     */
    private Object wrapPrimaryKeyObj(PK key) {
        Object pk = null;
        //本项目单列主键名约定为guid，复合主键要求T extends PK
        //也可通过参数注解或参数名等指定主键属性名...以后再说
        if (BeanUtils.isSimpleValueType(key.getClass())) {
            T obj = BeanUtils.instantiate(persistentClass);
            Method m = null;
            final Class keyType = key.getClass();
            if (ClassUtils.hasMethod(persistentClass, "setId", keyType)) {
                m = ClassUtils.getMethod(persistentClass, "setId", keyType);
            } else if (ClassUtils.hasMethod(persistentClass, "setGuid", keyType)) {
                m = ClassUtils.getMethod(persistentClass, "setGuid", keyType);
            }
            try {
                m.invoke(obj, key);
                pk = obj;
            } catch (Exception e) {
                logger.error("pk transform error", e);
                throw new RuntimeException(e);
            }
        } else {
            //复合主键
            if (!persistentClass.getClass().isAssignableFrom(key.getClass())) {
                throw new RuntimeException("复合主键要求T extends PK");
            }
            pk = key;
        }
        return pk;
    }

    public boolean checkUnique(Example example, String fieldName, Object condition) {
        List<T> list = selectByExample(example);
        return list.size() == 0 || null != condition && BeanUtil.getPropertyValue(list.get(0), fieldName).equals(condition);
    }
}
