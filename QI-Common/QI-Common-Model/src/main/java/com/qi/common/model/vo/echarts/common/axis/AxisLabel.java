package com.qi.common.model.vo.echarts.common.axis;

/**
 * Class AxisLabel
 *
 * @author 张麒 2016/9/20.
 * @version Description:
 */
public class AxisLabel {

    private String formatter;

    public AxisLabel(String formatter) {
        this.formatter = formatter;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
