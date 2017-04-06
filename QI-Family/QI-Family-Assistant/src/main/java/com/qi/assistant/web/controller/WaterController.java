package com.qi.assistant.web.controller;

import com.qi.assistant.web.model.model.Water;
import com.qi.assistant.web.model.model.WaterDetails;
import com.qi.assistant.web.model.vo.OptionsVO;
import com.qi.assistant.web.model.vo.WaterVO;
import com.qi.assistant.web.service.read.OptionsReadService;
import com.qi.assistant.web.service.read.WaterReadService;
import com.qi.assistant.web.service.transactional.WaterTransactionalService;
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
 * Class WaterController
 *
 * @author 张麒 2016/8/11.
 * @version Description:
 */
@Controller()
@RequestMapping("/water")
public class WaterController extends BasicController<Water> {

    @Autowired
    private OptionsReadService optionsReadService;

    @Autowired
    private WaterReadService waterReadService;

    @Autowired
    private WaterTransactionalService transactionalService;


    @Override
    protected BasicReadService<Water> getReadService() {
        return waterReadService;
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @RequestMapping(value = "index.html")
    public String index(ModelMap model) {
        model.put("years", waterReadService.findYear());
        model.put("header", Header.getInstance().buildHeader(Water.class.getSimpleName()));
        return "assistant/water/index";
    }


    @RequestMapping(value = "added.html")
    public String added(ModelMap model) {
        List<OptionsVO> options = optionsReadService.findOptionsByType(Water.class);
        OptionsVO VO = waterReadService.getOptions(options.get(0), null);
        model.put("options", options);
        model.put("VO", VO);
        return "assistant/water/edit";
    }

    @RequestMapping(value = "edited.html")
    public String edited(ModelMap model, Long guid) {
        Water water = waterReadService.getModelByGuid(guid);
        model.put("model", water);
        List<OptionsVO> options = optionsReadService.findOptionsByType(Water.class);
        List<WaterDetails> list = waterReadService.findWaterDetailsByFKGuid(water.getGuid());
        for (WaterDetails details : list) {
            options.stream().filter(VO -> VO.getGrade().equals(details.getGrade())).forEach(VO -> VO.setWaterDetails(details));
        }
        model.put("options", options);
        OptionsVO VO = waterReadService.getOptions(options.get(0), water);
        model.put("VO", VO);
        return "assistant/water/edit";
    }

    @RequestMapping(value = "save.ajax")
    public ModelAndView save(WaterVO condition) {
        transactionalService.save(condition);
        return ResponseResult.getInstance().getJsonModelView();
    }

    @RequestMapping(value = "delete.ajax")
    public ModelAndView delete(Long guid) {
        transactionalService.delete(guid);
        return ResponseResult.getInstance().getJsonModelView();
    }
}
