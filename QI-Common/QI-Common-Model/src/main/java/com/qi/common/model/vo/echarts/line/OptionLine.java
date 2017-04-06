package com.qi.common.model.vo.echarts.line;

import com.qi.common.model.vo.echarts.common.*;
import com.qi.common.model.vo.echarts.common.visualmap.VisualMap;
import com.qi.common.model.vo.echarts.pie.SeriesPie;

import java.util.ArrayList;
import java.util.List;

/**
 * Class OptionPie
 *
 * @author 张麒 2016/9/9.
 * @version Description:
 */
public class OptionLine {

    private Title title;

    private Tooltip tooltip;

    private Legend legend;

    private List<SeriesLine> series = new ArrayList<>();

    private XAxis xAxis;

    private YAxis yAxis;

    private VisualMap visualMap;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public List<SeriesLine> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesLine> series) {
        this.series = series;
    }

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public YAxis getyAxis() {
        return yAxis;
    }

    public void setyAxis(YAxis yAxis) {
        this.yAxis = yAxis;
    }

    public VisualMap getVisualMap() {
        return visualMap;
    }

    public void setVisualMap(VisualMap visualMap) {
        this.visualMap = visualMap;
    }
}
