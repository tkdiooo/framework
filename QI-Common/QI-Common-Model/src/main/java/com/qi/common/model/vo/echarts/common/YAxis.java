package com.qi.common.model.vo.echarts.common;

import com.qi.common.model.vo.echarts.common.XAxis.Type;
import com.qi.common.model.vo.echarts.common.axis.AxisLabel;

/**
 * Class YAxis
 *
 * @author 张麒 2016/9/13.
 * @version Description:
 */
public class YAxis {

    private Type type;

    private AxisLabel axisLabel;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public AxisLabel getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(AxisLabel axisLabel) {
        this.axisLabel = axisLabel;
    }
}
