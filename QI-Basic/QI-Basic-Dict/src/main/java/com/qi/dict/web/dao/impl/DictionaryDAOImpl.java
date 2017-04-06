package com.qi.dict.web.dao.impl;

import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.dao.ibatis.abs.AbstractSqlDaoImpl;
import com.qi.dict.web.dao.DictionaryDAO;
import com.qi.dict.web.model.example.DictionaryExample;
import com.qi.dict.web.model.model.Dictionary;
import org.springframework.stereotype.Component;

/**
 * Class DictionaryDAOImpl
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
@Component("DictionaryDAO")
@DaoSqlMapNamespace("t_basic_dictionary")
public class DictionaryDAOImpl extends AbstractSqlDaoImpl<Dictionary, Long, DictionaryExample> implements DictionaryDAO {

    public DictionaryDAOImpl() {
        super(Dictionary.class);
    }

    @Override
    public DictionaryExample getExample() {
        return new DictionaryExample();
    }
}
