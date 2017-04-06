package com.qi.common.model.vo.echarts.bar.series;

import java.util.ArrayList;
import java.util.List;

/**
 * Class MarkPoint
 *
 * @author 张麒 2016/9/13.
 * @version Description:
 */
public class MarkPoint {

    private List<DataMP> data = new ArrayList<>();

    public List<DataMP> getData() {
        return data;
    }

    public void setData(List<DataMP> data) {
        this.data = data;
    }
}
