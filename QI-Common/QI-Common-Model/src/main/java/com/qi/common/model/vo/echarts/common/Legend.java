package com.qi.common.model.vo.echarts.common;

import com.qi.common.constants.EchartsConstants.HorizAlign;

/**
 * Class Legend
 *
 * @author 张麒 2016/9/9.
 * @version Description:
 */
public class Legend {

    public enum Orient {
        vertical, horizontal
    }

    private Boolean show;
    private Orient orient;
    private HorizAlign x;
    private String[] data;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Orient getOrient() {
        return orient;
    }

    public void setOrient(Orient orient) {
        this.orient = orient;
    }

    public HorizAlign getX() {
        return x;
    }

    public void setX(HorizAlign x) {
        this.x = x;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
