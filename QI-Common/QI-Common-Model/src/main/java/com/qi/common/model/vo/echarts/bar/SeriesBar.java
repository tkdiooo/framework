package com.qi.common.model.vo.echarts.bar;

import com.qi.common.constants.EchartsConstants.Type;
import com.qi.common.model.vo.echarts.bar.series.MarkPoint;
import com.qi.common.model.vo.echarts.bar.series.ItemStyle;
import com.qi.common.model.vo.echarts.common.label.Label;

/**
 * Class SeriesBar
 *
 * @author 张麒 2016/9/13.
 * @version Description:
 */
public class SeriesBar {


    private Type type;

    private String name;

    private Label label;

    private String[] data;

    private MarkPoint markPoint;

    private ItemStyle itemStyle;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public MarkPoint getMarkPoint() {
        return markPoint;
    }

    public void setMarkPoint(MarkPoint markPoint) {
        this.markPoint = markPoint;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }
}
