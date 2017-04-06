package com.qi.menu.web.service.transactional.impl;

import com.qi.common.constants.StatusConstants;
import com.qi.common.tool.Logger;
import com.qi.common.util.BeanUtil;
import com.qi.menu.model.domain.BasicMenu;
import com.qi.menu.web.dao.BasicMenuDAO;
import com.qi.menu.web.model.example.BasicMenuExample;
import com.qi.menu.web.service.transactional.MenuTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class MenuTransactionalServiceImpl
 *
 * @author 张麒 2016/7/15.
 * @version Description:
 */
@Transactional
@Service("MenuTransactionalService")
public class MenuTransactionalServiceImpl implements MenuTransactionalService {

    private static final Logger logger = new Logger(MenuTransactionalServiceImpl.class);

    @Autowired
    private BasicMenuDAO menuDAO;

    @Override
    public void saveMenu(BasicMenu condition) {
        // 父节点是0
        if (null == condition.getParentid() || condition.getParentid() == 0) {
            condition.setParentid(0L);
            condition.setParentname("ROOT NODE");
            condition.setRootid(condition.getParentid());
            condition.setLevel(1);
        } else {
            BasicMenu parentMenu = menuDAO.selectByPrimaryKey(condition.getParentid());
            // 如果父节点是一级节点，子节点的rootId为父节点ID
            if (parentMenu.getLevel() == 1) condition.setRootid(parentMenu.getGuid());
            else condition.setRootid(parentMenu.getRootid());
            condition.setParentname(parentMenu.getMenuname());
            condition.setLevel(parentMenu.getLevel() + 1);
            if (!parentMenu.getIsleaf().equals(StatusConstants.YesNo.No.getKey())) {
                parentMenu.setIsleaf(StatusConstants.YesNo.No.getKey());
                menuDAO.updateByPrimaryKeySelective(parentMenu);
            }
        }
//        if (!condition.getDomain().startsWith("http://")) {
//            condition.setDomain("http://" + condition.getDomain());
//        }
        // 编辑
        if (null != condition.getGuid()) {
            BasicMenu oldMenu = menuDAO.selectByPrimaryKey(condition.getGuid());
            // 编辑操作，并且旧数据中父节点不为0，计算旧数据父节点包含的子节点数，如果为0修改父节点isleaf
            if (oldMenu.getParentid() != 0) {
                BasicMenuExample example = menuDAO.getExample();
                example.createCriteria().andParentidEqualTo(oldMenu.getParentid());
                List<BasicMenu> list = menuDAO.selectByExample(example);
                if (menuDAO.selectByExample(example).size() == 1) {
                    BasicMenu parentMenu = menuDAO.selectByPrimaryKey(oldMenu.getParentid());
                    parentMenu.setIsleaf(StatusConstants.YesNo.Yes.getKey());
                    menuDAO.updateByPrimaryKeySelective(parentMenu);
                }
            }
            BeanUtil.copyPropertiesNotEmpty(oldMenu, condition);
            menuDAO.updateByPrimaryKeySelective(oldMenu);
            // TODO 记录日志
        } else {
            condition.setSort(0);
            condition.setIsleaf(StatusConstants.YesNo.Yes.getKey());
            condition.setStatus(StatusConstants.YesNo.Yes.getKey());
            Long pk = menuDAO.insertGetPrimaryKey(condition);
            System.out.println(pk);
            // TODO 记录日志
        }
    }
}
