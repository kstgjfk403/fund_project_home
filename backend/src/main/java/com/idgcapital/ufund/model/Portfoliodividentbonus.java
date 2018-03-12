package com.idgcapital.ufund.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Portfoliodividentbonus {
    private String dbid;

    private String portfolioid;

    private String fundid;

    private String fundname;

    private String fundfamillyname;

    private String investtype;

    private Date closedate;

    private String round;

    private Integer securitytypeid;

    private String securitytypestr;

    private Long shareownedno;

    private BigDecimal conversionratio;

    private String remarks;

    private BigDecimal costrelization;

    private BigDecimal proceeds;

    private String currency;

    private String redemption;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    private Date financialdate;

    private List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList;

    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid == null ? null : dbid.trim();
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid == null ? null : fundid.trim();
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname == null ? null : fundname.trim();
    }

    public String getFundfamillyname() {
        return fundfamillyname;
    }

    public void setFundfamillyname(String fundfamillyname) {
        this.fundfamillyname = fundfamillyname == null ? null : fundfamillyname.trim();
    }

    public String getInvesttype() {
        return investtype;
    }

    public void setInvesttype(String investtype) {
        this.investtype = investtype == null ? null : investtype.trim();
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round == null ? null : round.trim();
    }

    public Integer getSecuritytypeid() {
        return securitytypeid;
    }

    public void setSecuritytypeid(Integer securitytypeid) {
        this.securitytypeid = securitytypeid;
    }


    public String getSecuritytypestr() {
        return securitytypestr;
    }

    public void setSecuritytypestr(String securitytypestr) {
        this.securitytypestr = securitytypestr;
    }

    public Long getShareownedno() {
        return shareownedno;
    }

    public void setShareownedno(Long shareownedno) {
        this.shareownedno = shareownedno;
    }

    public BigDecimal getConversionratio() {
        return conversionratio;
    }

    public void setConversionratio(BigDecimal conversionratio) {
        this.conversionratio = conversionratio;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public BigDecimal getCostrelization() {
        return costrelization;
    }

    public void setCostrelization(BigDecimal costrelization) {
        this.costrelization = costrelization;
    }

    public BigDecimal getProceeds() {
        return proceeds;
    }

    public void setProceeds(BigDecimal proceeds) {
        this.proceeds = proceeds;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getRedemption() {
        return redemption;
    }

    public void setRedemption(String redemption) {
        this.redemption = redemption == null ? null : redemption.trim();
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby == null ? null : createdby.trim();
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby == null ? null : modifiedby.trim();
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Date getFinancialdate() {
        return financialdate;
    }

    public void setFinancialdate(Date financialdate) {
        this.financialdate = financialdate;
    }

    public List<Portfoliocaptablevaluedetail> getPortfoliocaptablevaluedetailList() {
        return portfoliocaptablevaluedetailList;
    }

    public void setPortfoliocaptablevaluedetailList(List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList) {
        this.portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailList;
    }
}