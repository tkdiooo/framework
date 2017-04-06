package com.qi.common.model.vo.echarts.bar;

import com.qi.common.model.vo.echarts.common.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class OptionPie
 *
 * @author 张麒 2016/9/9.
 * @version Description:
 */
public class OptionBar {

    private Title title;

    private Tooltip tooltip;

    private Legend legend;

    private List<SeriesBar> series = new ArrayList<>();

    private Boolean calculable;

    private XAxis xAxis;

    private YAxis yAxis;

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

    public List<SeriesBar> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesBar> series) {
        this.series = series;
    }

    public Boolean getCalculable() {
        return calculable;
    }

    public void setCalculable(Boolean calculable) {
        this.calculable = calculable;
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
}
