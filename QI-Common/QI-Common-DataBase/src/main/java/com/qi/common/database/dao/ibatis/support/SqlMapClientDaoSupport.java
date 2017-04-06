package com.qi.common.database.dao.ibatis.support;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.qi.common.database.dao.ibatis.SqlMapClientTemplate;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

import javax.sql.DataSource;


/**
 * Convenient super class for iBATIS SqlMapClient data access objects.
 * Requires a SqlMapClient to be set, providing a SqlMapClientTemplate
 * based on it to subclasses.
 * <p>
 * <p>Instead of a plain SqlMapClient, you can also pass a preconfigured
 * SqlMapClientTemplate instance in. This allows you to share your
 * SqlMapClientTemplate configuration for all your DAOs, for example
 * a custom SQLExceptionTranslator to use.
 *
 * @author Juergen Hoeller
 * @see #setSqlMapClient
 * @see #setSqlMapClientTemplate
 * @see com.qi.common.database.dao.ibatis.SqlMapClientTemplate
 * @see com.qi.common.database.dao.ibatis.SqlMapClientTemplate#setExceptionTranslator
 * @since 24.02.2004
 */
public abstract class SqlMapClientDaoSupport extends DaoSupport {


    private SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate();

    private boolean externalTemplate = false;


    /**
     * Set the JDBC DataSource to be used by this DAO.
     * Not required: The SqlMapClient might carry a shared DataSource.
     *
     * @see #setSqlMapClient
     */
    public final void setDataSource(DataSource dataSource) {
        if (!this.externalTemplate) {
            this.sqlMapClientTemplate.setDataSource(dataSource);
        }
    }

    /**
     * Return the JDBC DataSource used by this DAO.
     */
    public final DataSource getDataSource() {
        return this.sqlMapClientTemplate.getDataSource();
    }

    /**
     * Set the iBATIS Database Layer SqlMapClient to work with.
     * Either this or a "sqlMapClientTemplate" is required.
     *
     * @see #setSqlMapClientTemplate
     */
    public final void setSqlMapClient(SqlMapClient sqlMapClient) {
        if (!this.externalTemplate) {
            this.sqlMapClientTemplate.setSqlMapClient(sqlMapClient);
        }
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
    public final void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
        Assert.notNull(sqlMapClientTemplate, "SqlMapClientTemplate must not be null");
        this.sqlMapClientTemplate = sqlMapClientTemplate;
        this.externalTemplate = true;
    }

    /**
     * Return the SqlMapClientTemplate for this DAO,
     * pre-initialized with the SqlMapClient or set explicitly.
     */
    public final SqlMapClientTemplate getSqlMapClientTemplate() {
        return this.sqlMapClientTemplate;
    }

    @Override
    protected final void checkDaoConfig() {
        if (!this.externalTemplate) {
            this.sqlMapClientTemplate.afterPropertiesSet();
        }
    }
}
