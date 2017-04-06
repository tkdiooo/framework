package com.qi.assistant.web.dao.impl;

import com.qi.assistant.web.dao.GasDetailsDAO;
import com.qi.assistant.web.model.example.GasDetailsExample;
import com.qi.assistant.web.model.model.GasDetails;
import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.dao.ibatis.abs.AbstractSqlDaoImpl;
import org.springframework.stereotype.Component;

/**
 * Class GasDetailsDAOImpl
 *
 * @author 张麒 2016/8/7.
 * @version Description:
 */
@Component("GasDetailsDAO")
@DaoSqlMapNamespace("t_family_gas_details")
public class GasDetailsDAOImpl extends AbstractSqlDaoImpl<GasDetails, Long, GasDetailsExample> implements GasDetailsDAO {

    public GasDetailsDAOImpl() {
        super(GasDetails.class);
    }

    @Override
    public GasDetailsExample getExample() {
        return new GasDetailsExample();
    }
}
