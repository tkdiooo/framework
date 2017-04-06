package com.qi.common.model.vo.echarts.pie;

import com.qi.common.model.vo.echarts.common.Legend;
import com.qi.common.model.vo.echarts.common.Title;
import com.qi.common.model.vo.echarts.common.Tooltip;

import java.util.ArrayList;
import java.util.List;

/**
 * Class OptionPie
 *
 * @author 张麒 2016/9/9.
 * @version Description:
 */
public class OptionPie {

    private Title title;

    private Tooltip tooltip;

    private Legend legend;

    private List<SeriesPie> series = new ArrayList<>();

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

    public List<SeriesPie> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesPie> series) {
        this.series = series;
    }
}
