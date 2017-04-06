package com.qi.common.model.vo.echarts.common.visualmap;

import java.util.List;

/**
 * Class VisualMap
 *
 * @author 张麒 2016/9/20.
 * @version Description:
 */
public class VisualMap {

    private Boolean show;
    private Integer dimension;
    private List<Pieces> pieces;

    public VisualMap(Boolean show, Integer dimension, List<Pieces> pieces) {
        this.show = show;
        this.dimension = dimension;
        this.pieces = pieces;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public List<Pieces> getPieces() {
        return pieces;
    }

    public void setPieces(List<Pieces> pieces) {
        this.pieces = pieces;
    }
}
