package com.qi.common.model.vo.echarts.pie.series;

/**
 * Class DataPie
 *
 * @author 张麒 2016/9/9.
 * @version Description:
 */
public class DataPie {

    private String name;
    private Object value;

    public DataPie(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
