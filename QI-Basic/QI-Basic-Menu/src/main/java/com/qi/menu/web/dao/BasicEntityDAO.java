package com.qi.menu.web.dao;

import com.qi.common.model.model.DBConfigModel;

import java.util.List;

/**
 * Class BasicEntityDAO
 *
 * @author 张麒 2016/9/28.
 * @version Description:
 */
public interface BasicEntityDAO{

    List<String> findTableNameByDBName(DBConfigModel configModel);
}
