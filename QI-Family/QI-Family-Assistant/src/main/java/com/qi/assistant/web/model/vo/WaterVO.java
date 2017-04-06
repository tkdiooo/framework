package com.qi.assistant.web.model.vo;

import com.qi.assistant.web.model.model.Water;
import com.qi.assistant.web.model.model.WaterDetails;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class WaterVO
 *
 * @author 张麒 2016/8/30.
 * @version Description:
 */
public class WaterVO extends Water {

    private static final long serialVersionUID = -5070001692124742782L;

    private List<WaterDetails> children;

    private Integer grade;

    private BigDecimal wateramount;

    private BigDecimal drainageamount;

    private Integer watertotal;

    public List<WaterDetails> getChildren() {
        return children;
    }

    public void setChildren(List<WaterDetails> children) {
        this.children = children;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public BigDecimal getWateramount() {
        return wateramount;
    }

    public void setWateramount(BigDecimal wateramount) {
        this.wateramount = wateramount;
    }

    public BigDecimal getDrainageamount() {
        return drainageamount;
    }

    public void setDrainageamount(BigDecimal drainageamount) {
        this.drainageamount = drainageamount;
    }

    public Integer getWatertotal() {
        return watertotal;
    }

    public void setWatertotal(Integer watertotal) {
        this.watertotal = watertotal;
    }
}
