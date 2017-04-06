package com.qi.assistant.web.controller;

import com.qi.assistant.web.model.model.Gas;
import com.qi.assistant.web.model.model.GasDetails;
import com.qi.assistant.web.model.vo.GasVO;
import com.qi.assistant.web.model.vo.OptionsVO;
import com.qi.assistant.web.service.read.GasReadService;
import com.qi.assistant.web.service.read.OptionsReadService;
import com.qi.assistant.web.service.transactional.GasTransactionalService;
import com.qi.assistant.web.tool.Header;
import com.qi.common.basic.controller.BasicController;
import com.qi.common.basic.service.BasicReadService;
import com.qi.common.tool.DateEditor;
import com.qi.common.web.http.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Class GasController
 *
 * @author 张麒 2016/8/3.
 * @version Description:
 */
@Controller()
@RequestMapping("/gas")
public class GasController extends BasicController<Gas> {

    @Autowired
    private OptionsReadService optionsReadService;

    @Autowired
    private GasReadService gasReadService;

    @Autowired
    private GasTransactionalService transactionalService;

    @Override
    protected BasicReadService<Gas> getReadService() {
        return gasReadService;
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @RequestMapping(value = "index.html")
    public String index(ModelMap model) {
        model.put("years", gasReadService.findYear());
        model.put("header", Header.getInstance().buildHeader(Gas.class.getSimpleName()));
        return "assistant/gas/index";
    }

    @RequestMapping(value = "added.html")
    public String added(ModelMap model) {
        List<OptionsVO> options = optionsReadService.findOptionsByType(Gas.class);
        OptionsVO VO = gasReadService.getOptions(options.get(0), null);
        model.put("options", options);
        model.put("VO", VO);
        return "assistant/gas/edit";
    }

    @RequestMapping(value = "edited.html")
    public String edited(ModelMap model, Long guid) {
        Gas gas = gasReadService.getModelByGuid(guid);
        model.put("model", gas);
        List<OptionsVO> options = optionsReadService.findOptionsByType(Gas.class);
        List<GasDetails> list = gasReadService.findGasDetailsByGasGuid(gas.getGuid());
        for (OptionsVO VO : options) {
            list.stream().filter(details -> VO.getGrade().equals(details.getGrade())).forEach(VO::setGasDetails);
        }
        model.put("options", options);
        OptionsVO VO = gasReadService.getOptions(options.get(0), gas);
        model.put("VO", VO);
        return "assistant/gas/edit";
    }

    @RequestMapping(value = "save.ajax")
    public ModelAndView save(GasVO condition) {
        transactionalService.save(condition);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "delete.ajax")
    public ModelAndView delete(Long guid) {
        transactionalService.delete(guid);
        return ResponseResult.getInstance().getJsonModelView();
    }
}
