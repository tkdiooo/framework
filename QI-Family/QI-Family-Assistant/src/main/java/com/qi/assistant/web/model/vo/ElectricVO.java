package com.qi.assistant.web.model.vo;

import com.qi.assistant.web.model.model.Electric;
import com.qi.assistant.web.model.model.ElectricDetails;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class ElectricVO
 *
 * @author 张麒 2016/8/11.
 * @version Description:
 */
public class ElectricVO extends Electric {

    private static final long serialVersionUID = -1672148552739456517L;

    private List<ElectricDetails> children;

    private Integer grade;

    private BigDecimal peakprice;

    private Integer peaktotal;

    private BigDecimal flatprice;

    private Integer flattotal;

    public Integer getFlattotal() {
        return flattotal;
    }

    public void setFlattotal(Integer flattotal) {
        this.flattotal = flattotal;
    }

    public BigDecimal getFlatprice() {
        return flatprice;
    }

    public void setFlatprice(BigDecimal flatprice) {
        this.flatprice = flatprice;
    }

    public Integer getPeaktotal() {
        return peaktotal;
    }

    public void setPeaktotal(Integer peaktotal) {
        this.peaktotal = peaktotal;
    }

    public BigDecimal getPeakprice() {
        return peakprice;
    }

    public void setPeakprice(BigDecimal peakprice) {
        this.peakprice = peakprice;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public List<ElectricDetails> getChildren() {
        return children;
    }

    public void setChildren(List<ElectricDetails> children) {
        this.children = children;
    }
}
