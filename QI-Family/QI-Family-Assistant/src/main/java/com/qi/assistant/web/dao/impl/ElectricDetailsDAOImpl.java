package com.qi.assistant.web.dao.impl;

import com.qi.assistant.web.dao.ElectricDetailsDAO;
import com.qi.assistant.web.model.example.ElectricDetailsExample;
import com.qi.assistant.web.model.model.ElectricDetails;
import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.dao.ibatis.abs.AbstractSqlDaoImpl;
import org.springframework.stereotype.Component;

/**
 * Class ElectricDetailsDAOImpl
 *
 * @author 张麒 2016/8/11.
 * @version Description:
 */
@Component("ElectricDetailsDAO")
@DaoSqlMapNamespace("t_family_electric_details")
public class ElectricDetailsDAOImpl extends AbstractSqlDaoImpl<ElectricDetails, Long, ElectricDetailsExample> implements ElectricDetailsDAO {

    public ElectricDetailsDAOImpl() {
        super(ElectricDetails.class);
    }

    @Override
    public ElectricDetailsExample getExample() {
        return new ElectricDetailsExample();
    }
}
