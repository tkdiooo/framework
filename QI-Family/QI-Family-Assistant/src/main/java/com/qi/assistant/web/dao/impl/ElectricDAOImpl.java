package com.qi.assistant.web.dao.impl;

import com.qi.assistant.web.dao.ElectricDAO;
import com.qi.assistant.web.model.example.ElectricExample;
import com.qi.assistant.web.model.model.Electric;
import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.dao.ibatis.abs.AbstractSqlDaoImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class ElectricDAOImpl
 *
 * @author 张麒 2016/8/11.
 * @version Description:
 */
@Component("ElectricDAO")
@DaoSqlMapNamespace("t_family_electric")
public class ElectricDAOImpl extends AbstractSqlDaoImpl<Electric, Long, ElectricExample> implements ElectricDAO {

    public ElectricDAOImpl() {
        super(Electric.class);
    }

    @Override
    public ElectricExample getExample() {
        return new ElectricExample();
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
    public Electric getLatestModel() {
        return queryForObject("getLatestModel", null);
    }

    @Override
    public List<String> findYear() {
        return queryForList("findYear", null, String.class);
    }
}
