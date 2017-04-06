package com.qi.common.dao.jdbc;

import com.qi.common.model.model.DBConfigModel;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Class JdbcOperations
 *
 * @author 张麒 2016/9/29.
 * @version Description:
 */
public abstract class JdbcOperations {

    private static Map<String, JdbcTemplate> dataSourceMap = new HashMap<>();

    protected JdbcTemplate getJdbcTemplate(DBConfigModel configModel) {
        if (!dataSourceMap.containsKey(configModel.getJdbcUrl())) {
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName(configModel.getDriver().get());
            ds.setUrl(configModel.getJdbcUrl());
            ds.setUsername(configModel.getDbuser());
            ds.setPassword(configModel.getDbpassword());
            ds.setInitialSize(5);
            ds.setMaxActive(10);
            dataSourceMap.put(configModel.getJdbcUrl(), new JdbcTemplate(ds));
        }
        return dataSourceMap.get(configModel.getJdbcUrl());
    }
}
