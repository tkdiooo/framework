package com.qi.assistant.web.controller;

import com.qi.assistant.web.service.read.ReportReadService;
import com.qi.assistant.web.tool.Echarts;
import com.qi.common.tool.Logger;
import com.qi.common.util.DateUtil;
import com.qi.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class AssistantController
 *
 * @author 张麒 2016/7/21.
 * @version Description:
 */
@Controller("/")
public class AssistantController {

    private static final Logger logger = new Logger(AssistantController.class);

    @Autowired
    private ReportReadService readService;

    /**
     * 首页（图表）
     */
    @RequestMapping(value = "index.html")
    public String index(ModelMap model) {
        model.put("year", readService.findYear());
        model.put("currentYear", DateUtil.getYears(DateUtil.getCurrentDate()));
        return "assistant/index";
    }

    /*---------------------------------------------------------------------------------------*/

    @RequestMapping(value = "getSpendingByPie.ajax")
    public void getSpendingByPie(String year, HttpServletResponse response) throws IOException {
        ResponseUtil.writeJson(Echarts.buildPieEcharts(readService.findSpendingByPie(year), year), response);
    }

    @RequestMapping(value = "getBeforeYearByBar.ajax")
    public void getBeforeYearByBar(String year, HttpServletResponse response) throws IOException {
        ResponseUtil.writeJson(Echarts.buildBarEcharts(readService.findBeforeYearByBar(year), year), response);
    }

    @RequestMapping(value = "getFullYearByLine.ajax")
    public void getFullYearByLine(String year, HttpServletResponse response) throws IOException {
        ResponseUtil.writeJson(Echarts.buildLineEcharts(readService.findFullYearByLine(year), year), response);
    }

    @RequestMapping(value = "getFullYearScatterGram.ajax")
    public void getFullYearScatterGram(String year, Integer type, HttpServletResponse response) throws IOException {
        ResponseUtil.writeJson(Echarts.buildLineScatterGram(readService.findFullYearByLine(year, type), year, type), response);
    }
}
