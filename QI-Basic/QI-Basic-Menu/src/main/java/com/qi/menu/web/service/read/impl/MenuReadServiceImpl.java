package com.qi.menu.web.service.read.impl;

import com.qi.common.constants.StatusConstants;
import com.qi.common.database.dao.DBType;
import com.qi.common.database.datasource.dynamic.ReadWriteDataSource;
import com.qi.common.model.model.PagingModel;
import com.qi.common.model.vo.paging.OperateVO;
import com.qi.common.model.vo.paging.PagingVO;
import com.qi.common.model.vo.paging.TitleVO;
import com.qi.common.model.vo.tree.RTreeVO;
import com.qi.common.model.vo.tree.ZTreeVO;
import com.qi.common.tool.Logger;
import com.qi.common.util.ListUtil;
import com.qi.common.util.MapUtil;
import com.qi.common.util.TreeUtil;
import com.qi.menu.model.domain.BasicMenu;
import com.qi.menu.web.config.ApplicationConfig;
import com.qi.menu.web.dao.BasicMenuDAO;
import com.qi.menu.web.model.example.BasicMenuExample;
import com.qi.menu.web.model.vo.MenuVO;
import com.qi.menu.web.service.read.MenuReadService;
import com.qi.menu.web.tool.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class MenuReadServiceImpl
 *
 * @author 张麒 2016/6/24.
 * @version Description:
 */
@ReadWriteDataSource(DBType.READ)
@Service("MenuReadService")
public class MenuReadServiceImpl implements MenuReadService {

    private static final Logger logger = new Logger(MenuReadServiceImpl.class);

    @Autowired
    private BasicMenuDAO dao;

    @Override
    public List<MenuVO> findMenuByAuthority(Long guid, String portalCode) {
        List<MenuVO> dataSet = dao.findBasicMenuByAuthority(guid, portalCode);
        if (dataSet.size() > 0) {
            // 默认第一个显示
            dataSet.get(0).setHide(false);
        }
        return dataSet;
    }

    @Override
    public boolean checkUnique(BasicMenu model) {
        BasicMenuExample example = dao.getExample();
        example.createCriteria().andMenucodeEqualTo(model.getMenucode());
        return dao.checkUnique(example, "guid", model.getGuid());
    }

    @Override
    public List<RTreeVO> findMenuForRTree(Long guid) {
        List<RTreeVO> dataSet = new ArrayList<>();
        // 读取菜单数据
        List<BasicMenu> list = this.findMenuByParent(guid);
        list.forEach((menu) -> dataSet.add(TreeUtil.convertRTree(menu, "guid", "menuname", "isleaf")));
        return dataSet;
    }

    @Override
    public PagingVO queryPagination(PagingModel<BasicMenu> pageInfo) {
        // 分页查询
        dao.queryPagination(pageInfo);
        // List集合转换为map集合
        pageInfo.setMapResult(new ArrayList<>());
        pageInfo.getResult().forEach((VO) -> {
            Map<String, Object> map = MapUtil.toMap(VO);
            map.put("状态", StatusConstants.Status.getValueByKey(VO.getStatus()));
            if (VO.getIsleaf()) {
                map.put(TitleVO.fieldType.operate.name(), new OperateVO(OperateVO.operateType.edited, OperateVO.operateType.delete, OperateVO.operateType.operate));
            }
            pageInfo.getMapResult().add(map);
        });
        pageInfo.getResult().clear();
        PagingVO<BasicMenu> VO = new PagingVO<>();
        VO.setPagingModel(pageInfo);
        VO.setHeader(Header.getInstance().buildHeader(0));
        return VO;
    }

    @Override
    public BasicMenu getModelByGuid(Long guid) {
        return dao.selectByPrimaryKey(guid);
    }

    @Override
    public List<ZTreeVO> findMenuForZTree(Long guid, String systemCode) {
        List<ZTreeVO> dataSet = new ArrayList<>();
        if (null == guid) {
            ZTreeVO VO = new ZTreeVO();
            VO.setId(ApplicationConfig.CONSTANT_0.toString());
            VO.setName("ROOT NODE");
            VO.setPId("-1");
            VO.setIsParent(true);
            dataSet.add(VO);
        } else {
            BasicMenuExample example = dao.getExample();
            BasicMenuExample.Criteria c = example.createCriteria();
            c.andParentidEqualTo(guid);
            c.andSystemcodeEqualTo(systemCode);
            example.setOrderByClause("sort ASC");
            // 读取菜单数据
            List<BasicMenu> list = dao.selectByExample(example);
            list.forEach((menu) -> dataSet.add(TreeUtil.convertZTree(menu, "guid", "parentid", "menuname", "isleaf")));
        }
        return dataSet;
    }

    @Override
    public List<BasicMenu> findMenuByParent(Long guid) {
        BasicMenuExample example = dao.getExample();
        example.createCriteria().andParentidEqualTo(guid);
        example.setOrderByClause("sort ASC");
        return dao.selectByExample(example);
    }

    @Override
    public List<RTreeVO> findMenuByCodeForRTree(String code) {
        List<RTreeVO> dataSet = new ArrayList<>();
        BasicMenuExample example = dao.getExample();
        BasicMenuExample.Criteria c = example.createCriteria();
        c.andSystemcodeEqualTo(code);
        c.andParentidEqualTo(0L);
        List<BasicMenu> list = dao.selectByExample(example);
        list.forEach((menu) -> dataSet.add(TreeUtil.convertRTree(menu, "guid", "menuname", "isleaf")));
        return dataSet;
    }
}
