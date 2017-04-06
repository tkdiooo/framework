package com.qi.common.model.vo.echarts.bar.series;

/**
 * Class DataMP
 *
 * @author 张麒 2016/9/13.
 * @version Description:
 */
public class DataMP {

    public enum Type {
        min, max, average
    }

    public enum Symbol {
        circle, rect, roundRect, triangle, diamond, pin, arrow
    }

    private Type type;

    private String name;

    private Symbol symbol;

    private Integer symbolSize;

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

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Integer getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(Integer symbolSize) {
        this.symbolSize = symbolSize;
    }

}
