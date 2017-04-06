package com.qi.menu.web.controller;

import com.qi.common.basic.controller.BasicController;
import com.qi.common.basic.service.BasicReadService;
import com.qi.common.model.vo.select.SelectVO;
import com.qi.common.model.vo.tree.RTreeVO;
import com.qi.common.model.vo.tree.ZTreeVO;
import com.qi.menu.model.domain.BasicMenu;
import com.qi.menu.model.domain.BasicSystem;
import com.qi.menu.web.tool.Header;
import com.qi.common.tool.Logger;
import com.qi.common.util.ListUtil;
import com.qi.common.util.ResponseUtil;
import com.qi.common.util.TreeUtil;
import com.qi.common.web.http.ResponseBody;
import com.qi.common.web.http.ResponseResult;
import com.qi.menu.web.model.vo.MenuVO;
import com.qi.menu.web.service.read.MenuReadService;
import com.qi.menu.web.service.read.SystemReadService;
import com.qi.menu.web.service.transactional.MenuTransactionalService;
import com.qi.menu.web.service.write.MenuWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class MenuController
 *
 * @author 张麒 2016/6/14.
 * @version Description:
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BasicController<BasicMenu> {

    private static final Logger logger = new Logger(MenuController.class);

    @Autowired
    private MenuReadService menuReadService;

    @Autowired
    private MenuWriteService menuWriteService;

    @Autowired
    private MenuTransactionalService menuService;

    @Autowired
    private SystemReadService portalReadService;

    @Override
    protected BasicReadService<BasicMenu> getReadService() {
        return menuReadService;
    }

    @RequestMapping(value = "index.html")
    public String index(ModelMap model) {
        model.put("header", Header.getInstance().buildHeader(0));
        List<BasicSystem> dataSet = portalReadService.findNormalPortal();
        List<SelectVO> list = ListUtil.getInstance();
        dataSet.forEach((VO) -> list.add(new SelectVO(VO.getCode(), VO.getNamecn())));
        model.put("portalList", list);
        return "menu/index";
    }

    @RequestMapping(value = "added.html")
    public String added(ModelMap model) {
        List<BasicSystem> dataSet = portalReadService.findNormalPortal();
        List<RTreeVO> list = ListUtil.getInstance();
        dataSet.forEach((portal) -> list.add(TreeUtil.convertRTree(portal, "code", "namecn", null)));
        model.put("systemTree", list);
        return "menu/edit";
    }

    @RequestMapping(value = "edited.html")
    public String edited(ModelMap model, Long guid) {
        BasicMenu menu = menuReadService.getModelByGuid(guid);
        model.put("menu", menu);
        List<BasicSystem> dataSet = portalReadService.findNormalPortal();
        model.put("system", ListUtil.getBeanByList("code", menu.getSystemcode(), dataSet));
        List<RTreeVO> list = ListUtil.getInstance();
        dataSet.forEach((portal) -> list.add(TreeUtil.convertRTree(portal, "code", "namecn", null)));
        model.put("systemTree", list);
        return "menu/edit";
    }

    @RequestMapping(value = "ordering.html")
    public String ordering(ModelMap model) {
        model.put("layout", "layout/empty.vm");
        List<BasicSystem> dataSet = portalReadService.findNormalPortal();
        List<SelectVO> list = ListUtil.getInstance();
        dataSet.forEach((VO) -> list.add(new SelectVO(VO.getCode(), VO.getNamecn())));
        model.put("portalList", list);
        return "menu/ordering";
    }

    @RequestMapping(value = "sortable.html")
    public String sortable(ModelMap model, Long guid) {
        model.put("layout", "layout/empty.vm");
        model.put("menus", menuReadService.findMenuByParent(guid));
        return "menu/sortable";
    }

    @RequestMapping(value = "save.ajax")
    public ModelAndView save(BasicMenu condition) {
        menuService.saveMenu(condition);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "findMenuForRTree.ajax")
    public void findMenuForRTree(Long guid, HttpServletResponse response) throws IOException {
        List<RTreeVO> menuTree = menuReadService.findMenuForRTree(guid);
        ResponseBody responseBody = ResponseBody.getInstance(response);
        responseBody.setSuccess(true).set("result", menuTree).write();
    }

    @RequestMapping(value = "statusChanges.ajax")
    public ModelAndView statusChanges(String guids, boolean status) {
        menuWriteService.statusChanges(guids, status);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "delete.ajax")
    public ModelAndView delete(Long guid) {
        menuWriteService.delete(guid);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "batchDelete.ajax")
    public ModelAndView batchDelete(String guids) {
        menuWriteService.batchDelete(guids);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "findMenuForZTree.ajax")
    public void findMenuForZTree(Long id, String systemCode, HttpServletResponse response) throws IOException {
        List<ZTreeVO> menuTree = menuReadService.findMenuForZTree(id, systemCode);
        ResponseUtil.writeJson(menuTree, response);
    }

    @RequestMapping(value = "sortable.ajax")
    public ModelAndView sortable(String sortable) throws IOException {
        menuWriteService.sortable(sortable);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "findMenuByAuthority.ajax")
    public void findMenuByAuthority(Long guid, String portalCode, HttpServletResponse response) throws IOException {
        List<MenuVO> list = menuReadService.findMenuByAuthority(guid, portalCode);
        ResponseBody responseBody = ResponseBody.getInstance(response);
        responseBody.setSuccess(true).set("result", list).write();
    }

    @RequestMapping(value = "findMenuByCodeForZTree.ajax")
    public void findMenuByCodeForZTree(String guid, HttpServletResponse response) throws IOException {
        List<RTreeVO> menuTree = menuReadService.findMenuByCodeForRTree(guid);
        ResponseUtil.writeJson(menuTree, response);
    }
}
