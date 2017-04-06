package com.qi.menu.web.dao.impl;

import com.qi.common.dao.jdbc.JdbcOperations;
import com.qi.common.model.model.DBConfigModel;
import com.qi.menu.web.dao.BasicEntityDAO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class BasicEntityDAOImpl
 *
 * @author 张麒 2016/9/28.
 * @version Description:
 */
@Component("BasicEntityDAO")
public class BasicEntityDAOImpl extends JdbcOperations implements BasicEntityDAO {

    @Override
    public List<String> findTableNameByDBName(DBConfigModel configModel) {
        try {
            String sql = String.format("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '%s'", configModel.getDbname());
            return getJdbcTemplate(configModel).queryForList(sql, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
