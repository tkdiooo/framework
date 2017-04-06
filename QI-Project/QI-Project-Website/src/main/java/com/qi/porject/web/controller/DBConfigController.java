package com.qi.porject.web.controller;

import com.qi.common.basic.controller.BasicController;
import com.qi.common.basic.service.BasicReadService;
import com.qi.common.web.annotation.BreadcrumbNavigation;
import com.qi.porject.web.service.DBConfigService;
import com.qi.porject.web.tool.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class BasicController
 *
 * @author 张麒 2017/1/23.
 * @version Description:
 */
@Controller
@RequestMapping(value = "/dbconfig")
public class DBConfigController extends BasicController {

    @Autowired
    private DBConfigService dbConfigService;

    @Override
    protected BasicReadService getReadService() {
        return dbConfigService;
    }

    @RequestMapping(value = "/index")
    @BreadcrumbNavigation(root = "项目工具", value = "DB配置管理")
    public String index(ModelMap model) {
        model.put("header", Header.getInstance().buildHeader(0));
        return "dbconfig/index";
    }

    @RequestMapping(value = "added")
    public String added(ModelMap model) {
//        List<BasicSystem> dataSet = portalReadService.findNormalPortal();
//        List<RTreeVO> list = ListUtil.getInstance();
//        dataSet.forEach((portal) -> list.add(TreeUtil.convertRTree(portal, "code", "namecn", null)));
//        model.put("systemTree", list);
        return "dbconfig/edit";
    }

}
