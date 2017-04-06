package com.qi.common.model.vo.echarts.common;

import com.qi.common.model.vo.echarts.common.tooltip.AxisPointer;

/**
 * Class Tooltip
 * 图表字体显示特殊处理
 *
 * @author 张麒 2016/9/9.
 * @version Description:
 */
public class Tooltip {

    public enum Trigger {
        item, axis
    }

    private Boolean show;
    private Trigger trigger;
    private String formatter;
    private TextStyle textStyle;
    private AxisPointer axisPointer;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public TextStyle getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
    }

    public AxisPointer getAxisPointer() {
        return axisPointer;
    }

    public void setAxisPointer(AxisPointer axisPointer) {
        this.axisPointer = axisPointer;
    }
}
