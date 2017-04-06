package com.qi.assistant.web.dao;

import com.qi.assistant.web.model.example.OptionsExample;
import com.qi.assistant.web.model.model.Options;
import com.qi.common.dao.ibatis.inf.IBatisDao;

/**
 * Class OptionsDAO
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
public interface OptionsDAO extends IBatisDao<Options, Long, OptionsExample> {
}
