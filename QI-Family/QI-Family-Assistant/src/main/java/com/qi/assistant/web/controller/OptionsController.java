package com.qi.assistant.web.controller;

import com.qi.assistant.web.config.ApplicationConfig;
import com.qi.assistant.web.model.model.Gas;
import com.qi.assistant.web.model.model.Options;
import com.qi.assistant.web.service.read.OptionsReadService;
import com.qi.assistant.web.service.transactional.OptionsTransactionalService;
import com.qi.assistant.web.tool.Header;
import com.qi.common.basic.controller.BasicController;
import com.qi.common.basic.service.BasicReadService;
import com.qi.common.constants.DateConstants;
import com.qi.common.tool.Logger;
import com.qi.common.util.StringUtil;
import com.qi.common.web.http.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class OptionsController
 *
 * @author 张麒 2016/7/22.
 * @version Description:
 */
@Controller()
@RequestMapping("/options")
public class OptionsController extends BasicController<Options> {

    private static final Logger logger = new Logger(OptionsController.class);

    @Autowired
    private OptionsReadService readService;

    @Autowired
    private OptionsTransactionalService transactionalService;

    @Override
    protected BasicReadService<Options> getReadService() {
        return readService;
    }

    @RequestMapping(value = "index.html")
    public String index(ModelMap model, String optionstype) {
        if (StringUtil.isBlank(optionstype)) {
            optionstype = Gas.class.getSimpleName();
        }
        model.put("breadcrumbs", ApplicationConfig.FamilyOptions.getValueByKey(optionstype));
        model.put("FamilyOptions", ApplicationConfig.FamilyOptions.values());
        model.put("optionstype", optionstype);
        return "options/index";
    }

    @RequestMapping(value = "toPaging.html")
    public String toPaging(ModelMap model, String optionstype) {
        model.put("layout", "layout/empty.vm");
        model.put("header", Header.getInstance().buildOptions(optionstype));
        return "options/paging";
    }


    @RequestMapping(value = "added.html")
    public String added(ModelMap model, String optionstype) {
        model.put("dateType", DateConstants.DateType.values());
        model.put("breadcrumbs", ApplicationConfig.FamilyOptions.getValueByKey(optionstype));
        model.put("optionstype", optionstype);
        if (optionstype.equals("Water")) {
            model.put("priceone", "水费单价");
            model.put("pricetwo", "污水单价");
        } else if (optionstype.equals(Gas.class.getSimpleName())) {
            model.put("priceone", "燃气费单价");
        } else {
            model.put("priceone", "电费单价(峰)");
            model.put("pricetwo", "电费单价(谷)");
        }
        return "options/edit";
    }

    @RequestMapping(value = "edited.html")
    public String edited(ModelMap model, Long guid) {
        Options options = readService.getModelByGuid(guid);
        model.put("model", options);
        model.put("dateType", DateConstants.DateType.values());
        model.put("interval", DateConstants.DateType.getValueByKey(options.getIntervaltype()));
        model.put("breadcrumbs", ApplicationConfig.FamilyOptions.getValueByKey(options.getOptionstype()));
        model.put("optionstype", options.getOptionstype());
        if (options.getOptionstype().equals("Water")) {
            model.put("priceone", "水费单价");
            model.put("pricetwo", "污水单价");
        } else if (options.getOptionstype().equals(Gas.class.getSimpleName())) {
            model.put("priceone", "燃气费单价");
        } else {
            model.put("priceone", "电费单价(峰)");
            model.put("pricetwo", "电费单价(谷)");
        }
        return "options/edit";
    }

    @RequestMapping(value = "save.ajax")
    public ModelAndView save(Options condition) {
        transactionalService.save(condition);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "delete.ajax")
    public ModelAndView delete(Long guid) {
//        menuWriteService.delete(guid);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "batchDelete.ajax")
    public ModelAndView batchDelete(String guids) {
//        menuWriteService.batchDelete(guids);
        return ResponseResult.getInstance().getJsonModelView();
    }

}
