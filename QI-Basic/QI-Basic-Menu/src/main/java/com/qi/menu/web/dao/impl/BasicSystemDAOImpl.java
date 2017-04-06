package com.qi.menu.web.dao.impl;

import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.dao.ibatis.abs.AbstractSqlDaoImpl;
import com.qi.menu.model.domain.BasicSystem;
import com.qi.menu.web.dao.BasicSystemDAO;
import com.qi.menu.web.model.example.BasicSystemExample;
import org.springframework.stereotype.Component;

/**
 * Class BasicPortalDAOImpl
 *
 * @author 张麒 2016/9/1.
 * @version Description:
 */
@Component("BasicPortalDAO")
@DaoSqlMapNamespace("t_basic_system_registry")
public class BasicSystemDAOImpl extends AbstractSqlDaoImpl<BasicSystem, Long, BasicSystemExample> implements BasicSystemDAO {

    public BasicSystemDAOImpl() {
        super(BasicSystem.class);
    }

    @Override
    public BasicSystemExample getExample() {
        return new BasicSystemExample();
    }

}
