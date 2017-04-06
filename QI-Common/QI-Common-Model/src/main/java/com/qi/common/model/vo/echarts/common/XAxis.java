package com.qi.common.model.vo.echarts.common;

import com.qi.common.constants.EchartsConstants;

/**
 * Class XAxis
 *
 * @author 张麒 2016/9/13.
 * @version Description:
 */
public class XAxis {

    public enum Type {
        value, category, time, log
    }

    private Type type;
    private Boolean boundaryGap;
    private String[] data;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public Boolean getBoundaryGap() {
        return boundaryGap;
    }

    public void setBoundaryGap(Boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
    }
}
