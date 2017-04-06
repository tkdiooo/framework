package com.qi.common.database.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.qi.common.cache.inf.ICacheControllerService;
import com.qi.common.database.dao.DBType;
import com.qi.common.database.datasource.dynamic.DynamicDataSourceCommonUtil;
import org.springframework.dao.support.DaoSupport;

import javax.sql.DataSource;

/**
 * Class SqlMapClientDaoSupportMonitor
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public abstract class SqlMapClientDaoSupportMonitor extends DaoSupport {

    private SqlMapClientTemplateCacheMonitor sqlMapClientTemplate;

    private boolean externalTemplate = false;

    private DBType dbtype;

    public void setDbType(DBType dbtype) {
        this.dbtype = dbtype;
    }


    /**
     * Set the JDBC DataSource to be used by this DAO.
     * Not required: The SqlMapClient might carry a shared DataSource.
     *
     * @see #setSqlMapClient
     */
    public final void setDataSource(DataSource dataSource) {
        this.sqlMapClientTemplate.setDataSource(dataSource);
    }

    /**
     * Return the JDBC DataSource used by this DAO.
     */
    public final DataSource getDataSource() {
        return (this.sqlMapClientTemplate != null ? this.sqlMapClientTemplate.getDataSource() : null);
    }

    /**
     * Set the iBATIS Database Layer SqlMapClient to work with.
     * Either this or a "sqlMapClientTemplate" is required.
     *
     * @see #setSqlMapClientTemplate
     */
    public final void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClientTemplate.setSqlMapClient(sqlMapClient);
    }

    /**
     * Return the iBATIS Database Layer SqlMapClient that this template works with.
     */
    public final SqlMapClient getSqlMapClient() {

        return this.sqlMapClientTemplate.getSqlMapClient();
    }

    /**
     * Set the SqlMapClientTemplate for this DAO explicitly,
     * as an alternative to specifying a SqlMapClient.
     *
     * @see #setSqlMapClient
     */
    public final void setSqlMapClientTemplate(SqlMapClientTemplateCacheMonitor sqlMapClientTemplateMonitor) {
        if (sqlMapClientTemplate == null) {
            throw new IllegalArgumentException("Cannot set sqlMapClientTemplate to null");
        }
        this.sqlMapClientTemplate = sqlMapClientTemplateMonitor;
        this.externalTemplate = true;
    }

    /**
     * Return the SqlMapClientTemplate for this DAO,
     * pre-initialized with the SqlMapClient or set explicitly.
     */
    public final SqlMapClientTemplateCacheMonitor getSqlMapClientTemplate() {
        return getSqlMapClientTemplate(this.dbtype);
    }


    /**
     * Return the SqlMapClientTemplate for this DAO,
     * pre-initialized with the SqlMapClient or set explicitly.
     */
    public final SqlMapClientTemplateCacheMonitor getSqlMapClientTemplate(DBType dbtype) {
        sqlMapClientTemplate.setDataSource(dbtype);
        return sqlMapClientTemplate;
    }

    protected final void checkDaoConfig() {
        if (sqlMapClientTemplate == null) {
            sqlMapClientTemplate = new SqlMapClientTemplateCacheMonitor();
        }
        this.setCacheClient(initCacheClient());
        this.setSqlMapClient(initSqlMapClient());
        if (!this.externalTemplate) {
            this.sqlMapClientTemplate.afterPropertiesSet();
        }
    }

    public abstract ICacheControllerService initCacheClient();

    public abstract SqlMapClient initSqlMapClient();

    /**
     * Set the iBATIS Database Layer SqlMapClient to work with.
     * Either this or a "sqlMapClientTemplate" is required.
     *
     * @see #setSqlMapClientTemplate
     */
    protected final void setCacheClient(ICacheControllerService cacheClient) {
        this.sqlMapClientTemplate.setCacheClient(cacheClient);
    }

    protected final void setDynamicDataSourceCommonUtil(DynamicDataSourceCommonUtil dynamicDataSourceCommonUtil) {
        this.sqlMapClientTemplate.setDynamicDataSourceCommonUtil(dynamicDataSourceCommonUtil);
    }

}
