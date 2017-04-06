package com.qi.assistant.web.dao.impl;

import com.qi.assistant.web.dao.GasDAO;
import com.qi.assistant.web.model.example.GasExample;
import com.qi.assistant.web.model.model.Gas;
import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.dao.ibatis.abs.AbstractSqlDaoImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class GasDAOImpl
 *
 * @author 张麒 2016/7/28.
 * @version Description:
 */
@Component("GasDAO")
@DaoSqlMapNamespace("t_family_gas")
public class GasDAOImpl extends AbstractSqlDaoImpl<Gas, Long, GasExample> implements GasDAO {

    public GasDAOImpl() {
        super(Gas.class);
    }

    @Override
    public GasExample getExample() {
        return new GasExample();
    }

    @Override
    public Integer countByGasQuantity(Long guid) {
        return queryForObject("countByGasQuantity", guid, Integer.class);
    }

    @Override
    public Long getLatestReset() {
        return queryForObject("getLatestReset", null, Long.class);
    }

    @Override
    public Gas getLatestModel() {
        return queryForObject("getLatestModel", null);
    }

    @Override
    public List<String> findYear() {
        return queryForList("findYear", null, String.class);
    }
}
