package com.qi.common.model.vo.echarts.common.visualmap;

/**
 * Class Pieces
 *
 * @author 张麒 2016/9/20.
 * @version Description:
 */
public class Pieces {

    public enum Color {
        red, green
    }

    private Integer gt;
    private Integer lte;
    private Color color;

    public Pieces(Integer gt) {
        this.gt = gt;
        this.color = Color.green;
    }

    public Pieces(Integer lte, Color color) {
        this.gt = gt;
        this.lte = lte;
        this.color = color;
    }


    public Pieces(Integer gt, Integer lte, Color color) {
        this.gt = gt;
        this.lte = lte;
        this.color = color;
    }


    public Integer getGt() {
        return gt;
    }

    public void setGt(Integer gt) {
        this.gt = gt;
    }

    public Integer getLte() {
        return lte;
    }

    public void setLte(Integer lte) {
        this.lte = lte;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
