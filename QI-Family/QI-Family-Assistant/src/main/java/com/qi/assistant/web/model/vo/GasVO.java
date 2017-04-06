package com.qi.assistant.web.model.vo;

import com.qi.assistant.web.model.model.Gas;
import com.qi.assistant.web.model.model.GasDetails;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class GasVO
 *
 * @author 张麒 2016/8/7.
 * @version Description:
 */
public class GasVO extends Gas {

    private static final long serialVersionUID = -2159633462178271275L;

    private List<GasDetails> children;

    private Integer grade;

    private BigDecimal gasprice;

    private Integer gastotal;

    public List<GasDetails> getChildren() {
        return children;
    }

    public void setChildren(List<GasDetails> children) {
        this.children = children;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public BigDecimal getGasprice() {
        return gasprice;
    }

    public void setGasprice(BigDecimal gasprice) {
        this.gasprice = gasprice;
    }

    public Integer getGastotal() {
        return gastotal;
    }

    public void setGastotal(Integer gastotal) {
        this.gastotal = gastotal;
    }
}
