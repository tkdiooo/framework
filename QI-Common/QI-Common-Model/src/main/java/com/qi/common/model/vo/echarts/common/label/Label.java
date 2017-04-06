package com.qi.common.model.vo.echarts.common.label;

/**
 * Class Label
 *
 * @author 张麒 2016/9/9.
 * @version Description:
 */
public class Label {

    public enum Position {
        inner,
        outside,
        center,
        inside,
        top
    }

    public Label(Normal normal){
        this.normal = normal;
    }

    private Normal normal;

    private Emphasis emphasis;

    public Normal getNormal() {
        return normal;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }

    public Emphasis getEmphasis() {
        return emphasis;
    }

    public void setEmphasis(Emphasis emphasis) {
        this.emphasis = emphasis;
    }
}
