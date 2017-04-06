package com.qi.dict.web.controller;

import com.qi.common.basic.controller.BasicController;
import com.qi.common.basic.service.BasicReadService;
import com.qi.common.model.vo.tree.ZTreeVO;
import com.qi.common.tool.Logger;
import com.qi.common.util.ResponseUtil;
import com.qi.common.util.StringUtil;
import com.qi.common.web.http.ResponseResult;
import com.qi.dict.web.config.ApplicationConfig;
import com.qi.dict.web.model.model.Dictionary;
import com.qi.dict.web.service.read.DictReadService;
import com.qi.dict.web.service.transactional.DictTransactionalService;
import com.qi.dict.web.service.write.DictWriteService;
import com.qi.dict.web.tool.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class DictController
 *
 * @author 张麒 2016/7/25.
 * @version Description:
 */
@Controller("/")
public class DictController extends BasicController<Dictionary> {

    private static final Logger logger = new Logger(DictController.class);

    @Autowired
    private DictReadService dictReadService;

    @Autowired
    private DictWriteService dictWriteService;

    @Autowired
    private DictTransactionalService dictService;

    @Override
    protected BasicReadService<Dictionary> getReadService() {
        return dictReadService;
    }

    @RequestMapping(value = "index.html")
    public String index(ModelMap model, String parentCode) {
        model.put("header", Header.getInstance().buildHeader(0));
        if (StringUtil.isBlank(parentCode)) {
            parentCode = ApplicationConfig.ROOT_CODE;
        }
        model.put("parentCode", parentCode);
        return "dict/index";
    }

    @RequestMapping(value = "added.html")
    public String added(ModelMap model, String parentCode) {
        if (parentCode.equals(ApplicationConfig.ROOT_CODE)) {
            model.put("parentName", ApplicationConfig.ROOT_NODE);
        } else {
            Dictionary pDict = dictReadService.getDictionaryByCode(parentCode);
            model.put("parentName", null != pDict ? pDict.getDictname() : "");
        }
        model.put("parentCode", parentCode);
        return "dict/edit";
    }

    @RequestMapping(value = "edited.html")
    public String edited(ModelMap model, Long guid) {
        Dictionary dict = dictReadService.getModelByGuid(guid);
        if (dict.getParentcode().equals(ApplicationConfig.ROOT_CODE)) {
            model.put("parentName", ApplicationConfig.ROOT_NODE);
        } else {
            Dictionary pDict = dictReadService.getDictionaryByCode(dict.getParentcode());
            model.put("parentName", null != pDict ? pDict.getDictname() : "");
        }
        model.put("parentCode", dict.getParentcode());
        model.put("model", dictReadService.getModelByGuid(guid));
        return "dict/edit";
    }

    @RequestMapping(value = "ordering.html")
    public String ordering(ModelMap model) {
        model.put("layout", "layout/empty.vm");
        return "dict/ordering";
    }

    @RequestMapping(value = "sortable.html")
    public String sortable(ModelMap model, String code) {
        model.put("layout", "layout/empty.vm");
        model.put("result", dictReadService.findDictByParent(code));
        return "dict/sortable";
    }

    @RequestMapping(value = "save.ajax")
    public ModelAndView save(Dictionary condition) {
        dictService.saveDict(condition);
        return ResponseResult.getInstance().getJsonModelView();
    }
    
    @RequestMapping(value = "statusChanges.ajax")
    public ModelAndView statusChanges(String guids, boolean status) {
        dictWriteService.statusChanges(guids, status);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "delete.ajax")
    public ModelAndView delete(Long guid) {
        dictWriteService.delete(guid);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "batchDelete.ajax")
    public ModelAndView batchDelete(String guids) {
        dictWriteService.batchDelete(guids);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "findDictForZTree.ajax")
    public void findDictForZTree(String id, HttpServletResponse response) throws IOException {
        List<ZTreeVO> tree = dictReadService.findDictForZTree(id);
        ResponseUtil.writeJson(tree, response);
    }

    @RequestMapping(value = "sortable.ajax")
    public ModelAndView sortable(String sortable) throws IOException {
        dictWriteService.sortable(sortable);
        return ResponseResult.getInstance().getJsonModelView();
    }
}
