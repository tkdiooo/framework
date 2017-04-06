package com.qi.common.model.vo.echarts.line.series;

import java.util.List;

/**
 * Class MarkArea
 *
 * @author 张麒 2016/9/20.
 * @version Description:
 */
public class MarkArea {

    private List<List<DataMA>> data;

    public MarkArea(List<List<DataMA>> data) {
        this.data = data;
    }

    public List<List<DataMA>> getData() {
        return data;
    }

    public void setData(List<List<DataMA>> data) {
        this.data = data;
    }
}
