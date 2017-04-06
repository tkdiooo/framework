package com.qi.dict.web.dao;

import com.qi.common.dao.ibatis.inf.IBatisDao;
import com.qi.dict.web.model.example.DictionaryExample;
import com.qi.dict.web.model.model.Dictionary;

/**
 * Class DictionaryDAO
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
public interface DictionaryDAO extends IBatisDao<Dictionary, Long, DictionaryExample> {
}
