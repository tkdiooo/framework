package com.qi.assistant.web.dao.impl;

import com.qi.assistant.web.dao.WaterDAO;
import com.qi.assistant.web.model.example.WaterExample;
import com.qi.assistant.web.model.model.Water;
import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.dao.ibatis.abs.AbstractSqlDaoImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class WaterDAOImpl
 *
 * @author 张麒 2016/8/30.
 * @version Description:
 */
@Component("WaterDAO")
@DaoSqlMapNamespace("t_family_water")
public class WaterDAOImpl extends AbstractSqlDaoImpl<Water, Long, WaterExample> implements WaterDAO {

    public WaterDAOImpl() {
        super(Water.class);
    }

    @Override
    public WaterExample getExample() {
        return new WaterExample();
    }

    @Override
    public Integer countByQuantity(Long guid) {
        return queryForObject("countByQuantity", guid, Integer.class);
    }

    @Override
    public Long getLatestReset() {
        return queryForObject("getLatestReset", null, Long.class);
    }

    @Override
    public Water getLatestModel() {
        return queryForObject("getLatestModel", null);
    }

    @Override
    public List<String> findYear() {
        return queryForList("findYear", null, String.class);
    }
}
