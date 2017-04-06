package com.qi.common.model.vo.echarts.line.series;

/**
 * Class DataMA
 *
 * @author 张麒 2016/9/20.
 * @version Description:
 */
public class DataMA {

    private String name;
    private String xAxis;

    public DataMA(String name, String xAxis) {
        this.name = name;
        this.xAxis = xAxis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }
}
