package com.qi.common.model.vo.echarts.line;

import com.qi.common.constants.EchartsConstants.Type;
import com.qi.common.model.vo.echarts.common.areastyle.AreaStyle;
import com.qi.common.model.vo.echarts.common.label.Label;
import com.qi.common.model.vo.echarts.line.series.MarkArea;

/**
 * Class SeriesBar
 *
 * @author 张麒 2016/9/13.
 * @version Description:
 */
public class SeriesLine {

    private Type type;

    private String name;

    private String stack;

    private Label label;

    private AreaStyle areaStyle;

    private String[] data;

    private Boolean smooth;

    private MarkArea markArea;

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

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public AreaStyle getAreaStyle() {
        return areaStyle;
    }

    public void setAreaStyle(AreaStyle areaStyle) {
        this.areaStyle = areaStyle;
    }

    public Boolean getSmooth() {
        return smooth;
    }

    public void setSmooth(Boolean smooth) {
        this.smooth = smooth;
    }

    public MarkArea getMarkArea() {
        return markArea;
    }

    public void setMarkArea(MarkArea markArea) {
        this.markArea = markArea;
    }
}
