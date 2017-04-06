package com.qi.assistant.web.dao.impl;

import com.qi.assistant.web.dao.OptionsDAO;
import com.qi.assistant.web.model.example.OptionsExample;
import com.qi.assistant.web.model.model.Options;
import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.dao.ibatis.abs.AbstractSqlDaoImpl;
import org.springframework.stereotype.Component;

/**
 * Class OptionsDAOImpl
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
@Component("OptionsDAO")
@DaoSqlMapNamespace("t_family_options")
public class OptionsDAOImpl extends AbstractSqlDaoImpl<Options, Long, OptionsExample> implements OptionsDAO {

    public OptionsDAOImpl() {
        super(Options.class);
    }

    @Override
    public OptionsExample getExample() {
        return new OptionsExample();
    }
}
