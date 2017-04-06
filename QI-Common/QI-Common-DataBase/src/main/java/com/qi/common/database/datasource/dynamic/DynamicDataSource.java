package com.qi.common.database.datasource.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Class DynamicDataSource
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

//	@Override
//	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
//		// TODO Auto-generated method stub
//		return null;
//	}

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
