package com.qi.menu.web.service.read;

import com.qi.common.basic.service.BasicReadService;
import com.qi.common.model.vo.tree.RTreeVO;
import com.qi.common.model.vo.tree.ZTreeVO;
import com.qi.menu.model.domain.BasicMenu;
import com.qi.menu.web.model.vo.MenuVO;

import java.util.List;

/**
 * Class MenuReadService
 *
 * @author 张麒 2016/6/24.
 * @version Description:
 */
public interface MenuReadService extends BasicReadService<BasicMenu> {

    /**
     * 根据权限查询用户菜单
     *
     * @return List<MenuVO>
     */
    List<MenuVO> findMenuByAuthority(Long guid, String portalCode);

    /**
     * 根据ParentID获取RTree
     *
     * @param guid ParentID
     * @return List<RTreeVO>
     */
    List<RTreeVO> findMenuForRTree(Long guid);

    /**
     * 根据系统编号获取RTree
     *
     * @param code SystemCode
     * @return List<RTreeVO>
     */
    List<RTreeVO> findMenuByCodeForRTree(String code);

    /**
     * 根据ParentID、系统编号获取ZTree
     *
     * @param guid       ParentID
     * @param systemCode SystemCode
     * @return
     */
    List<ZTreeVO> findMenuForZTree(Long guid, String systemCode);

    /**
     * 根据ParentID获取菜单集合
     *
     * @param guid ParentID
     * @return List<BasicMenu>
     */
    List<BasicMenu> findMenuByParent(Long guid);
}