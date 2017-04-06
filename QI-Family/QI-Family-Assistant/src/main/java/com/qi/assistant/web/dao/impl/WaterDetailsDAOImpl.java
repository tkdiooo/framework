package com.qi.assistant.web.dao.impl;

import com.qi.assistant.web.dao.WaterDetailsDAO;
import com.qi.assistant.web.model.example.WaterDetailsExample;
import com.qi.assistant.web.model.model.WaterDetails;
import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.dao.ibatis.abs.AbstractSqlDaoImpl;
import org.springframework.stereotype.Component;

/**
 * Class WaterDetailsDAOImpl
 *
 * @author 张麒 2016/8/30.
 * @version Description:
 */
@Component("WaterDetailsDAO")
@DaoSqlMapNamespace("t_family_water_details")
public class WaterDetailsDAOImpl extends AbstractSqlDaoImpl<WaterDetails, Long, WaterDetailsExample> implements WaterDetailsDAO {

    public WaterDetailsDAOImpl() {
        super(WaterDetails.class);
    }

    @Override
    public WaterDetailsExample getExample() {
        return new WaterDetailsExample();
    }
}
