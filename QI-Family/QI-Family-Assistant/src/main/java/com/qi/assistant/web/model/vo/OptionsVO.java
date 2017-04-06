package com.qi.assistant.web.model.vo;

import com.qi.assistant.web.model.model.*;
import com.qi.common.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class OptionsVO
 *
 * @author 张麒 2016/7/29.
 * @version Description:
 */
public class OptionsVO extends Options {

    private static final long serialVersionUID = -4282538542488294876L;

    private Long beforeid;

    private String billdate;

    private Date currentdate;

    private Date nextdate;

    private Integer useQuantity;

    private Integer useQuantityT;

    private Integer prevQuantity;

    private Integer prevQuantityT;

    private Integer useUp;

    private GasDetails gasDetails;

    private ElectricDetails electricDetails;

    private WaterDetails waterDetails;

    private BigDecimal beforebalance;

    private BigDecimal currentbalance;

    public Long getBeforeid() {
        return beforeid;
    }

    public void setBeforeid(Long beforeid) {
        this.beforeid = beforeid;
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public Date getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(Date currentdate) {
        this.currentdate = currentdate;
    }

    public Date getNextdate() {
        if (null != nextdate) {
            return nextdate;
        }
        // 本次抄表日期不为空，根据规则自动计算
        else if (null != currentdate) {
            switch (super.getIntervaltype()) {
                case 1: {
                    nextdate = DateUtil.addMonths(currentdate, super.getIntervaldate());
                    if (getOptionstype().equals(Water.class.getSimpleName())) {
                        nextdate = DateUtil.addDays(nextdate, 1);
                    }
                    break;
                }
                case 3: {
                    nextdate = DateUtil.addWeeks(currentdate, super.getIntervaldate());
                    break;
                }
            }
            return nextdate;
        }
        return null;
    }

    public void setNextdate(Date nextdate) {
        this.nextdate = nextdate;
    }

    public Integer getUseQuantity() {
        return useQuantity;
    }

    public void setUseQuantity(Integer useQuantity) {
        this.useQuantity = useQuantity;
    }

    public Integer getUseQuantityT() {
        return useQuantityT;
    }

    public void setUseQuantityT(Integer useQuantityT) {
        this.useQuantityT = useQuantityT;
    }

    public Integer getPrevQuantity() {
        return prevQuantity;
    }

    public void setPrevQuantity(Integer prevQuantity) {
        this.prevQuantity = prevQuantity;
    }

    public Integer getUseUp() {
        return useUp;
    }

    public void setUseUp(Integer useUp) {
        this.useUp = useUp;
    }

    public GasDetails getGasDetails() {
        return gasDetails;
    }

    public void setGasDetails(GasDetails gasDetails) {
        this.gasDetails = gasDetails;
    }

    public BigDecimal getCurrentbalance() {
        return currentbalance;
    }

    public void setCurrentbalance(BigDecimal currentbalance) {
        this.currentbalance = currentbalance;
    }

    public BigDecimal getBeforebalance() {
        return beforebalance;
    }

    public void setBeforebalance(BigDecimal beforebalance) {
        this.beforebalance = beforebalance;
    }

    public ElectricDetails getElectricDetails() {
        return electricDetails;
    }

    public void setElectricDetails(ElectricDetails electricDetails) {
        this.electricDetails = electricDetails;
    }

    public Integer getPrevQuantityT() {
        return prevQuantityT;
    }

    public void setPrevQuantityT(Integer prevQuantityT) {
        this.prevQuantityT = prevQuantityT;
    }

    public WaterDetails getWaterDetails() {
        return waterDetails;
    }

    public void setWaterDetails(WaterDetails waterDetails) {
        this.waterDetails = waterDetails;
    }
}
