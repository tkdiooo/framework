package com.qi.common.model.vo.echarts.bar.series;

import com.qi.common.model.vo.echarts.common.TextStyle;

/**
 * Class LabelBar
 *
 * @author 张麒 2016/9/13.
 * @version Description:
 */
public class LabelBar {

    private Boolean show;
    private TextStyle textStyle;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public TextStyle getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
    }
}
