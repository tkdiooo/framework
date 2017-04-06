package com.qi.menu.web.dao.impl;

import com.qi.common.constants.StatusConstants;
import com.qi.common.dao.annotation.DaoSqlMapNamespace;
import com.qi.common.dao.ibatis.abs.AbstractSqlDaoImpl;
import com.qi.common.util.MapUtil;
import com.qi.menu.model.domain.BasicMenu;
import com.qi.menu.web.dao.BasicMenuDAO;
import com.qi.menu.web.model.example.BasicMenuExample;
import com.qi.menu.web.model.vo.MenuVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Class BasicMenuDAOImpl
 *
 * @author 张麒 2016/7/6.
 * @version Description:
 */
@Component("BasicMenuDAO")
@DaoSqlMapNamespace("t_basic_menu")
public class BasicMenuDAOImpl extends AbstractSqlDaoImpl<BasicMenu, Long, BasicMenuExample> implements BasicMenuDAO {

    public BasicMenuDAOImpl() {
        super(BasicMenu.class);
    }

    @Override
    public BasicMenuExample getExample() {
        return new BasicMenuExample();
    }

    @Override
    public List<MenuVO> findBasicMenuByAuthority(Long guid, String systemCode) {
        Map<String, Object> params = MapUtil.getInstance();
        params.put("ParentID", guid);
        params.put("SystemCode", systemCode);
        params.put("status", StatusConstants.Status.VALID.getKey());
        return queryForList("findBasicMenuByAuthority", params, MenuVO.class);
    }
}
