package com.qi.menu.web.dao;

import com.qi.common.dao.ibatis.inf.IBatisDao;
import com.qi.menu.model.domain.BasicMenu;
import com.qi.menu.web.model.example.BasicMenuExample;
import com.qi.menu.web.model.vo.MenuVO;

import java.util.List;

/**
 * Class BasicMenuDAO
 *
 * @author 张麒 2016/6/24.
 * @version Description:
 */
public interface BasicMenuDAO extends IBatisDao<BasicMenu, Long, BasicMenuExample> {

    List<MenuVO> findBasicMenuByAuthority(Long guid, String systemCode);
}
