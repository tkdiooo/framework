package com.qi.common.database.datasource.dynamic;

import com.qi.common.consistentHash.Node;
import com.qi.common.consistentHash.RoundRobinWeight;
import com.qi.common.database.dao.DBType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class WriteOrReadDataSource
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class WriteOrReadDataSource extends AbstractDataSource implements InitializingBean {

    private Map<Object, Object> readDataSources;

    private Object writeDataSource;

    private boolean lenientFallback = true;

    private DataSourceLookup dataSourceLookup = new JndiDataSourceLookup();

    private Map<Object, DataSource> resolvedReadDataSources;

    private DataSource resolvedWriteDataSource;

//	private NodeStrategy routeStrategy = new RoundRobinWeight();

//	private static final int CONNECTION_VALID_TIME = 3;


//	public void setRouteStrategy(NodeStrategy routeStrategy) {
//		this.routeStrategy = routeStrategy;
//	}


    public void setWriteDataSource(Object writeDataSource) {
        this.writeDataSource = writeDataSource;
    }


    public void setReadDataSources(Map<Object, Object> readDataSources) {
        this.readDataSources = readDataSources;
        initReadNodes();
    }


    public void setLenientFallback(boolean lenientFallback) {
        this.lenientFallback = lenientFallback;
    }


    public void setDataSourceLookup(DataSourceLookup dataSourceLookup) {
        this.dataSourceLookup = (dataSourceLookup != null ? dataSourceLookup : new JndiDataSourceLookup());
    }


    public void afterPropertiesSet() {
        if (this.readDataSources == null) {
            throw new IllegalArgumentException("Property 'targetDataSources' is required");
        }
        this.resolvedReadDataSources = new HashMap<Object, DataSource>(this.readDataSources.size());
        for (Map.Entry entry : this.readDataSources.entrySet()) {
            Object lookupKey = resolveSpecifiedLookupKey(entry.getKey());
            DataSource dataSource = resolveSpecifiedDataSource(entry.getValue());
            this.resolvedReadDataSources.put(lookupKey, dataSource);
        }
        if (this.writeDataSource != null) {
            this.resolvedWriteDataSource = resolveSpecifiedDataSource(this.writeDataSource);
        }
    }

    /**
     * Resolve the specified data source object into a DataSource instance.
     * <p>The default implementation handles DataSource instances and data source
     * names (to be resolved via a {@link #setDataSourceLookup DataSourceLookup}).
     *
     * @param dataSource the data source value object as specified in the
     *                   {@link #setTargetDataSources targetDataSources} map
     * @return the resolved DataSource (never <code>null</code>)
     * @throws IllegalArgumentException in case of an unsupported value type
     */
    protected DataSource resolveSpecifiedDataSource(Object dataSource) throws IllegalArgumentException {
        if (dataSource instanceof DataSource) {
            return (DataSource) dataSource;
        } else if (dataSource instanceof String) {
            return this.dataSourceLookup.getDataSource((String) dataSource);
        } else {
            throw new IllegalArgumentException(
                    "Illegal data source value - only [javax.sql.DataSource] and String supported: " + dataSource);
        }
    }


    public Connection getConnection() throws SQLException {
        DBType dbtype = DbTypeHolder.getDbType();
        if (dbtype == DBType.READ) {
            Connection conn = getReadConnection();
            if (conn != null) {
                return conn;
            }
        }
        return getWriteDataSource().getConnection();

    }

    /**
     * @return
     * @throws SQLException
     */
    public Connection getReadConnection() throws SQLException {
        DataSourceWrap dsw = getReadDataSource();
        try {
            if (dsw != null) {
                return dsw.getDataSource().getConnection();
            }
        } catch (Exception e) {
            RoundRobinWeight.removeNode(new Node(dsw.getKey().toString()));
            new Thread(new RecoveryDataSourceThread(dsw)).start();
            return getReadConnection();

        }
        return null;

    }

    /**
     * @return
     * @throws SQLException
     */
    public Connection getReadConnection(String username, String password) throws SQLException {
        DataSourceWrap dsw = getReadDataSource();
        try {
            if (dsw != null) {
                return dsw.getDataSource().getConnection(username, password);
            }
        } catch (Exception e) {
            RoundRobinWeight.removeNode(new Node(dsw.getKey().toString()));
            new Thread(new RecoveryDataSourceThread(dsw)).start();
            return getReadConnection();

        }
        return null;

    }


    public Connection getConnection(String username, String password) throws SQLException {
        DBType dbtype = DbTypeHolder.getDbType();
        if (dbtype == DBType.READ) {
            Connection conn = getReadConnection(username, password);
            if (conn != null) {
                return conn;
            }
        }
        return getWriteDataSource().getConnection(username, password);
    }


    /**
     * 写数据源
     *
     * @return
     * @throws SQLException
     */
    private DataSource getWriteDataSource() {
        if (this.resolvedWriteDataSource == null) {
            throw new IllegalStateException("Cannot determine target Write DataSource ");
        }
        return this.resolvedWriteDataSource;

    }

    /**
     * 读数据源
     *
     * @return
     */
    private DataSourceWrap getReadDataSource() {
        Object lookupKey = determineReadLookupKey();
        if (lookupKey == null) {
            return null;
        }
        Assert.notNull(this.resolvedReadDataSources, "Read dataSource router not initialized");
        DataSource dataSource = this.resolvedReadDataSources.get(lookupKey);
        if (dataSource == null && (this.lenientFallback)) {
//            RoundRobinWeight.removeNode(new Node(lookupKey.toString()));
            return getReadDataSource();
        }
        return new DataSourceWrap(lookupKey, dataSource);


    }


    protected Object resolveSpecifiedLookupKey(Object lookupKey) {
        return lookupKey;
    }


    protected Object determineReadLookupKey() {

        Node n = RoundRobinWeight.nextNode();
        if (n != null) {
            return n.getName();
        }

        return null;

    }


    private void initReadNodes() {
        if (this.readDataSources == null) {
            throw new IllegalArgumentException("Property 'targetDataSources' is required");
        }

        for (Map.Entry entry : this.readDataSources.entrySet()) {
            String lookupKey = entry.getKey().toString();
            Node n = new Node(lookupKey.toString());
            RoundRobinWeight.addNode(n);
        }
    }


//	@Override
//	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
