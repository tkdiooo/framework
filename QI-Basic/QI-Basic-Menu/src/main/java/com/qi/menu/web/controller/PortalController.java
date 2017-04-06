package com.qi.menu.web.controller;

import com.qi.common.basic.controller.BasicController;
import com.qi.common.basic.service.BasicReadService;
import com.qi.menu.model.domain.BasicMenu;
import com.qi.menu.model.domain.BasicSystem;
import com.qi.menu.web.cache.CacheFactory;
import com.qi.menu.web.tool.Header;
import com.qi.common.tool.Logger;
import com.qi.common.web.http.ResponseResult;
import com.qi.menu.web.service.read.SystemReadService;
import com.qi.menu.web.service.transactional.PortalTransactionalService;
import com.qi.menu.web.service.write.SystemWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class RegistryController
 *
 * @author 张麒 2016/9/2.
 * @version Description:
 */
@Controller
@RequestMapping("/portal")
public class PortalController extends BasicController<BasicSystem> {

    private static final Logger logger = new Logger(PortalController.class);

    @Autowired
    private SystemReadService readService;

    @Autowired
    private SystemWriteService writeService;

    @Autowired
    private PortalTransactionalService transactionalService;

    @Override
    protected BasicReadService<BasicSystem> getReadService() {
        return readService;
    }

    @RequestMapping(value = "index.html")
    public String index(ModelMap model) {
        CacheFactory.refreshBasicSystemCache();
        model.put("header", Header.getInstance().buildHeader(1));
        return "portal/index";
    }

    @RequestMapping(value = "added.html")
    public String added(ModelMap model) {
//        List<RTreeVO> menuTree = readService.findMenuForRTree(0L);
//        model.put("menuTree", menuTree);
        return "portal/edit";
    }

    @RequestMapping(value = "edited.html")
    public String edited(ModelMap model, Long guid) {
//        model.put("menu", readService.getModelByGuid(guid));
//        model.put("menuTree", writeService.findMenuForRTree(0L));
        return "portal/edit";
    }

    @RequestMapping(value = "save.ajax")
    public ModelAndView save(BasicMenu condition) {
//        menuService.saveMenu(condition);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "statusChanges.ajax")
    public ModelAndView statusChanges(String guids, boolean status) {
//        menuWriteService.statusChanges(guids, status);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "delete.ajax")
    public ModelAndView delete(Long guid) {
//        menuWriteService.delete(guid);
        return ResponseResult.getInstance().getJsonModelView();
    }
}
