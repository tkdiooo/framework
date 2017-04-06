package com.qi.common.model.vo.echarts.common.areastyle;

import com.qi.common.constants.EchartsConstants;

/**
 * Class Normal
 *
 * @author 张麒 2016/9/14.
 * @version Description:
 */
public class Normal {

    private Boolean show;

    public Normal() {
        this.show = EchartsConstants.Boolean.TRUE.getValue();
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}
