package com.qi.common.database.dao.impl;

import com.qi.common.database.dao.DBType;
import com.qi.common.database.dao.ibatis.SqlMapClientTemplate;
import com.qi.common.database.datasource.ReadWritExcepiton;
import com.qi.common.database.datasource.dynamic.DbTypeHolder;
import com.qi.common.database.datasource.dynamic.DynamicDataSourceCommonUtil;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

/**
 * Class SqlMapClientTemplateMonitor
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public class SqlMapClientTemplateMonitor extends SqlMapClientTemplate {


//    private static final Logger logger = Logger.getLogger(SqlMapClientTemplateMonitor.class);

    private DynamicDataSourceCommonUtil dynamicDataSourceCommonUtil;


    @Override
    public List queryForList(String statementName, Object parameterObject, int skipResults, int maxResults) throws ReadWritExcepiton {

//		setDataSource(parameterObject);

        try {
            return super.queryForList(statementName, parameterObject, skipResults,
                    maxResults);

        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }

    }

    @Override
    public List queryForList(String statementName, int skipResults, int maxResults) throws ReadWritExcepiton {
        try {
            return super.queryForList(statementName, skipResults, maxResults);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }

    }

    @Override
    public List queryForList(String statementName) throws ReadWritExcepiton {
        try {

            return super.queryForList(statementName);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }

    }

    @Override
    public List queryForList(String statementName, Object parameterObject) throws ReadWritExcepiton {

//		setDataSource(parameterObject);
        try {
            return super.queryForList(statementName, parameterObject);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }

    }


    @Override
    public Map queryForMap(String statementName, Object parameterObject, String keyProperty, String valueProperty) throws ReadWritExcepiton {

//		setDataSource(parameterObject);
        try {
            return super.queryForMap(statementName, parameterObject, keyProperty,
                    valueProperty);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }

    }

    @Override
    public Map queryForMap(String statementName, Object parameterObject, String keyProperty) throws ReadWritExcepiton {

//		setDataSource(parameterObject);
        try {

            return super.queryForMap(statementName, parameterObject, keyProperty);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }

    }

    @Override
    public Object queryForObject(String statementName, Object parameterObject, Object resultObject) throws ReadWritExcepiton {

//		setDataSource(parameterObject);
        try {
            return super.queryForObject(statementName, parameterObject,
                    resultObject);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }
    }

    @Override
    public Object queryForObject(String statementName, Object parameterObject) throws ReadWritExcepiton {

//		setDataSource(parameterObject);
        try {

            return super.queryForObject(statementName, parameterObject);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }

    }

    @Override
    public Object queryForObject(String statementName) throws ReadWritExcepiton {
        try {
            return super.queryForObject(statementName);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }

    }

    @Override
    public Object insert(String statementName) throws ReadWritExcepiton {
        try {
            return super.insert(statementName);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }
    }

    @Override
    public Object insert(String statementName, Object parameterObject) throws ReadWritExcepiton {

//		setDataSource(parameterObject);
        try {
            return super.insert(statementName, parameterObject);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }
    }

    @Override
    public void update(String statementName, Object parameterObject, int requiredRowsAffected) throws ReadWritExcepiton {

//		setDataSource(parameterObject);
        try {
            super.update(statementName, parameterObject, requiredRowsAffected);

        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }
    }

    @Override
    public int update(String statementName, Object parameterObject) throws ReadWritExcepiton {

//		setDataSource(parameterObject);
        try {

            return super.update(statementName, parameterObject);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }
    }

    @Override
    public int update(String statementName) throws ReadWritExcepiton {
        try {

            return super.update(statementName);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }
    }

    @Override
    public int delete(String statementName) throws ReadWritExcepiton {
        try {

            return super.delete(statementName);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }
    }

    @Override
    public int delete(String statementName, Object parameterObject) throws ReadWritExcepiton {

//		setDataSource(parameterObject);
        try {

            return super.delete(statementName, parameterObject);
        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }
    }

    @Override
    public void delete(String statementName, Object parameterObject, int requiredRowsAffected) throws ReadWritExcepiton {

//		setDataSource(parameterObject);
        try {
            super.delete(statementName, parameterObject, requiredRowsAffected);

        } catch (DataAccessException e) {
            throw new ReadWritExcepiton("", e);
        }
    }

    private void setDataSource(Object parameterObject) {

        try {

            if (parameterObject != null) {

                dynamicDataSourceCommonUtil.setDataSource(parameterObject);

            }

        } catch (Exception e) {

//            logger.error(e);

        }
    }


    protected void setDataSource(DBType dbtype) {
        if (dbtype != null) {
            DbTypeHolder.setDbType(dbtype);
        }
    }


    protected DynamicDataSourceCommonUtil getDynamicDataSourceCommonUtil() {
        return dynamicDataSourceCommonUtil;
    }

    protected void setDynamicDataSourceCommonUtil(
            DynamicDataSourceCommonUtil dynamicDataSourceCommonUtil) {
        this.dynamicDataSourceCommonUtil = dynamicDataSourceCommonUtil;
    }
}
