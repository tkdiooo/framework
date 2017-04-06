package com.qi.common.model.vo.echarts.common.label;

import com.qi.common.model.vo.echarts.common.label.Label.Position;
import com.qi.common.model.vo.echarts.common.TextStyle;

/**
 * Class Normal
 *
 * @author 张麒 2016/9/12.
 * @version Description:
 */
public class Normal {

    private Boolean show;

    private Position position;

    private String formatter;

    private TextStyle textStyle;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public TextStyle getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
    }
}
