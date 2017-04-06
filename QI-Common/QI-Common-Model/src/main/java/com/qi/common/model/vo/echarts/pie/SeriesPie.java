package com.qi.common.model.vo.echarts.pie;

import com.qi.common.constants.EchartsConstants.Type;
import com.qi.common.model.vo.echarts.pie.series.DataPie;
import com.qi.common.model.vo.echarts.common.label.Label;

import java.util.ArrayList;
import java.util.List;

/**
 * Class SeriesBar
 *
 * @author 张麒 2016/9/9.
 * @version Description:
 */
public class SeriesPie {

    private Type type;

    private String name;

    /**
     * 选中模式，表示是否支持多个选中，默认关闭，支持布尔值
     */
    private Boolean selectedMode;

    private String[] center;

    private String[] radius;

    private List<DataPie> data = new ArrayList<>();

    private Label label;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelectedMode() {
        return selectedMode;
    }

    public void setSelectedMode(Boolean selectedMode) {
        this.selectedMode = selectedMode;
    }

    public String[] getCenter() {
        return center;
    }

    public void setCenter(String[] center) {
        this.center = center;
    }

    public String[] getRadius() {
        return radius;
    }

    public void setRadius(String[] radius) {
        this.radius = radius;
    }

    public List<DataPie> getData() {
        return data;
    }

    public void setData(List<DataPie> data) {
        this.data = data;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
