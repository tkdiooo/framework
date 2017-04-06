package com.qi.assistant.web.model.model;

import com.qi.common.dao.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class Electric extends BaseModel {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.Guid
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private Long guid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.BeforeID
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private Long beforeid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.BatchNumber
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private String batchnumber;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.BillDate
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private String billdate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.CurrentDate
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private Date currentdate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.NextDate
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private Date nextdate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.Employe
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private String employe;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.PeakQuantity
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private Integer peakquantity;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.FlatQuantity
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private Integer flatquantity;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.BeforeBalance
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private BigDecimal beforebalance;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.CurrentBalance
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private BigDecimal currentbalance;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.Deposited
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private BigDecimal deposited;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.Additional
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private BigDecimal additional;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.TotalShould
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private BigDecimal totalshould;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.TotalActual
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private BigDecimal totalactual;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.IsValid
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private Boolean isvalid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_family_electric.IsReset
     *
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    private Boolean isreset;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.Guid
     *
     * @return the value of t_family_electric.Guid
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public Long getGuid() {
        return guid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.Guid
     *
     * @param guid the value for t_family_electric.Guid
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setGuid(Long guid) {
        this.guid = guid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.BeforeID
     *
     * @return the value of t_family_electric.BeforeID
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public Long getBeforeid() {
        return beforeid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.BeforeID
     *
     * @param beforeid the value for t_family_electric.BeforeID
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setBeforeid(Long beforeid) {
        this.beforeid = beforeid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.BatchNumber
     *
     * @return the value of t_family_electric.BatchNumber
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public String getBatchnumber() {
        return batchnumber;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.BatchNumber
     *
     * @param batchnumber the value for t_family_electric.BatchNumber
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setBatchnumber(String batchnumber) {
        this.batchnumber = batchnumber;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.BillDate
     *
     * @return the value of t_family_electric.BillDate
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public String getBilldate() {
        return billdate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.BillDate
     *
     * @param billdate the value for t_family_electric.BillDate
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.CurrentDate
     *
     * @return the value of t_family_electric.CurrentDate
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public Date getCurrentdate() {
        return currentdate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.CurrentDate
     *
     * @param currentdate the value for t_family_electric.CurrentDate
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setCurrentdate(Date currentdate) {
        this.currentdate = currentdate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.NextDate
     *
     * @return the value of t_family_electric.NextDate
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public Date getNextdate() {
        return nextdate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.NextDate
     *
     * @param nextdate the value for t_family_electric.NextDate
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setNextdate(Date nextdate) {
        this.nextdate = nextdate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.Employe
     *
     * @return the value of t_family_electric.Employe
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public String getEmploye() {
        return employe;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.Employe
     *
     * @param employe the value for t_family_electric.Employe
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setEmploye(String employe) {
        this.employe = employe;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.PeakQuantity
     *
     * @return the value of t_family_electric.PeakQuantity
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public Integer getPeakquantity() {
        return peakquantity;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.PeakQuantity
     *
     * @param peakquantity the value for t_family_electric.PeakQuantity
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setPeakquantity(Integer peakquantity) {
        this.peakquantity = peakquantity;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.FlatQuantity
     *
     * @return the value of t_family_electric.FlatQuantity
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public Integer getFlatquantity() {
        return flatquantity;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.FlatQuantity
     *
     * @param flatquantity the value for t_family_electric.FlatQuantity
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setFlatquantity(Integer flatquantity) {
        this.flatquantity = flatquantity;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.BeforeBalance
     *
     * @return the value of t_family_electric.BeforeBalance
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public BigDecimal getBeforebalance() {
        return beforebalance;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.BeforeBalance
     *
     * @param beforebalance the value for t_family_electric.BeforeBalance
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setBeforebalance(BigDecimal beforebalance) {
        this.beforebalance = beforebalance;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.CurrentBalance
     *
     * @return the value of t_family_electric.CurrentBalance
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public BigDecimal getCurrentbalance() {
        return currentbalance;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.CurrentBalance
     *
     * @param currentbalance the value for t_family_electric.CurrentBalance
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setCurrentbalance(BigDecimal currentbalance) {
        this.currentbalance = currentbalance;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.Deposited
     *
     * @return the value of t_family_electric.Deposited
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public BigDecimal getDeposited() {
        return deposited;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.Deposited
     *
     * @param deposited the value for t_family_electric.Deposited
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setDeposited(BigDecimal deposited) {
        this.deposited = deposited;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.Additional
     *
     * @return the value of t_family_electric.Additional
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public BigDecimal getAdditional() {
        return additional;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.Additional
     *
     * @param additional the value for t_family_electric.Additional
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setAdditional(BigDecimal additional) {
        this.additional = additional;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.TotalShould
     *
     * @return the value of t_family_electric.TotalShould
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public BigDecimal getTotalshould() {
        return totalshould;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.TotalShould
     *
     * @param totalshould the value for t_family_electric.TotalShould
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setTotalshould(BigDecimal totalshould) {
        this.totalshould = totalshould;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.TotalActual
     *
     * @return the value of t_family_electric.TotalActual
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public BigDecimal getTotalactual() {
        return totalactual;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.TotalActual
     *
     * @param totalactual the value for t_family_electric.TotalActual
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setTotalactual(BigDecimal totalactual) {
        this.totalactual = totalactual;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.IsValid
     *
     * @return the value of t_family_electric.IsValid
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public Boolean getIsvalid() {
        return isvalid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.IsValid
     *
     * @param isvalid the value for t_family_electric.IsValid
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setIsvalid(Boolean isvalid) {
        this.isvalid = isvalid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_family_electric.IsReset
     *
     * @return the value of t_family_electric.IsReset
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public Boolean getIsreset() {
        return isreset;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_family_electric.IsReset
     *
     * @param isreset the value for t_family_electric.IsReset
     * @ibatorgenerated Thu Aug 11 11:48:11 GMT+08:00 2016
     */
    public void setIsreset(Boolean isreset) {
        this.isreset = isreset;
    }
}