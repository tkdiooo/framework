package com.qi.assistant.web.controller;

import com.qi.assistant.web.model.model.Electric;
import com.qi.assistant.web.model.model.ElectricDetails;
import com.qi.assistant.web.model.vo.ElectricVO;
import com.qi.assistant.web.model.vo.OptionsVO;
import com.qi.assistant.web.service.read.ElectricReadService;
import com.qi.assistant.web.service.read.OptionsReadService;
import com.qi.assistant.web.service.transactional.ElectricTransactionalService;
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
 * Class ElectricController
 *
 * @author 张麒 2016/8/11.
 * @version Description:
 */
@Controller()
@RequestMapping("/electric")
public class ElectricController extends BasicController<Electric> {

    @Autowired
    private OptionsReadService optionsReadService;

    @Autowired
    private ElectricReadService electricReadService;

    @Autowired
    private ElectricTransactionalService transactionalService;

    @Override
    protected BasicReadService<Electric> getReadService() {
        return electricReadService;
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @RequestMapping(value = "index.html")
    public String index(ModelMap model) {
        model.put("years", electricReadService.findYear());
        model.put("header", Header.getInstance().buildHeader(Electric.class.getSimpleName()));
        return "assistant/electric/index";
    }


    @RequestMapping(value = "added.html")
    public String added(ModelMap model) {
        List<OptionsVO> options = optionsReadService.findOptionsByType(Electric.class);
        OptionsVO VO = electricReadService.getOptions(options.get(0), null);
        model.put("options", options);
        model.put("VO", VO);
        return "assistant/electric/edit";
    }

    @RequestMapping(value = "edited.html")
    public String edited(ModelMap model, Long guid) {
        Electric electric = electricReadService.getModelByGuid(guid);
        model.put("model", electric);
        List<OptionsVO> options = optionsReadService.findOptionsByType(Electric.class);
        List<ElectricDetails> list = electricReadService.findElectricDetailsByFKGuid(electric.getGuid());
        for (ElectricDetails details : list) {
            // 优惠
            if (details.getGrade() < 0) {
                model.put("favorable", Math.abs(details.getPeakquantity()));
            } else {
                options.stream().filter(VO -> VO.getGrade().equals(details.getGrade())).forEach(VO -> VO.setElectricDetails(details));
            }
        }
        model.put("options", options);
        OptionsVO VO = electricReadService.getOptions(options.get(0), electric);
        model.put("VO", VO);
        return "assistant/electric/edit";
    }

    @RequestMapping(value = "save.ajax")
    public ModelAndView save(ElectricVO condition) {
        transactionalService.save(condition);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "delete.ajax")
    public ModelAndView delete(Long guid) {
        transactionalService.delete(guid);
        return ResponseResult.getInstance().getJsonModelView();
    }
}
