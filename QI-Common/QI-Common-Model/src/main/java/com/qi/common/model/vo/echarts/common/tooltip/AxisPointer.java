package com.qi.common.model.vo.echarts.common.tooltip;

/**
 * Class AxisPointer
 *
 * @author 张麒 2016/9/13.
 * @version Description:
 */
public class AxisPointer {

    public enum Type {
        line, cross, shadow
    }

    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
