package com.qi.menu.web.controller;

import com.qi.common.constants.JdbcConstants;
import com.qi.common.model.model.DBConfigModel;
import com.qi.common.tool.Logger;
import com.qi.common.util.ResponseUtil;
import com.qi.menu.web.service.read.EntityReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class EntityController
 *
 * @author 张麒 2016/9/27.
 * @version Description:
 */
@Controller
@RequestMapping("/entity")
public class EntityController {

    private static final Logger logger = new Logger(EntityController.class);

    @Autowired
    private EntityReadService readService;

    @RequestMapping(value = "index.html")
    public String index(ModelMap model) {
        return "entity/index";
    }

    @RequestMapping(value = "findTableForZTree.ajax")
    public void findMenuForZTree(DBConfigModel configModel, HttpServletResponse response) throws IOException {
        configModel.setDriver(JdbcConstants.Driver.MySQL);
        ResponseUtil.writeJson(readService.findTableNameByDBName(configModel), response);
    }
}
