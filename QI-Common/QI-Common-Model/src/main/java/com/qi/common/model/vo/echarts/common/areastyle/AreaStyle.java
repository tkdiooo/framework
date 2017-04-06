package com.qi.common.model.vo.echarts.common.areastyle;

/**
 * Class AreaStyle
 *
 * @author 张麒 2016/9/14.
 * @version Description:
 */
public class AreaStyle {

    private Normal normal;

    public AreaStyle(){
        this.normal = new Normal();
    }

    public Normal getNormal() {
        return normal;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }
}
