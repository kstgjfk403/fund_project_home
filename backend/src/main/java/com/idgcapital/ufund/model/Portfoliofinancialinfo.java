package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;

public class Portfoliofinancialinfo {

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private Integer portfoliofinanciallot;

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private String portfolioid;

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private Date startdate;

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private Date enddate;

    private String ytd;

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private BigDecimal revenue;


    //等于grossmargin ,新系统统一用 grossprofit字段
    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private BigDecimal grossmargin;

    private BigDecimal ebitda;

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private BigDecimal operatingprofits;

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private BigDecimal netprofits;

    private BigDecimal cashburnpermonth;

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private BigDecimal totalassets;

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private BigDecimal totalliabilities;

    private BigDecimal currentassets;

    private BigDecimal currentliabilities;

    private BigDecimal currentnetaL;


    // 对应 bank loan/debt
    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private BigDecimal cashlevel;

    private Boolean approved;

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private BigDecimal cashassets;

    private BigDecimal otherassets;

    private Long ownerequity;

    private BigDecimal operatingincome;

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private Double debt;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    @JsonView(AppView.FinList.class)
    private String currency;

    private String fsdate;

    private String yemth;

    private BigDecimal retainedearnings;

    @JsonView({AppView.FinList.class,AppView.FinReport.class})
    private BigDecimal grossprofit;

    private BigDecimal exchangerate;

    private BigDecimal sharecapital;

    private int pageIndex;

    private int pageCount;

    @JsonView({AppView.FinReport.class})
    private String abbname;

    public String getAbbname() {
        return abbname;
    }

    public void setAbbname(String abbname) {
        this.abbname = abbname;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPortfoliofinanciallot() {
        return portfoliofinanciallot;
    }


    public void setPortfoliofinanciallot(Integer portfoliofinanciallot) {
        this.portfoliofinanciallot = portfoliofinanciallot;
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getYtd() {
        return ytd;
    }

    public void setYtd(String ytd) {
        this.ytd = ytd == null ? null : ytd.trim();
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public BigDecimal getGrossmargin() {
        return grossmargin;
    }

    public void setGrossmargin(BigDecimal grossmargin) {
        this.grossmargin = grossmargin;
    }

    public BigDecimal getEbitda() {
        return ebitda;
    }

    public void setEbitda(BigDecimal ebitda) {
        this.ebitda = ebitda;
    }

    public BigDecimal getOperatingprofits() {
        return operatingprofits;
    }

    public void setOperatingprofits(BigDecimal operatingprofits) {
        this.operatingprofits = operatingprofits;
    }

    public BigDecimal getNetprofits() {
        return netprofits;
    }

    public void setNetprofits(BigDecimal netprofits) {
        this.netprofits = netprofits;
    }

    public BigDecimal getCashburnpermonth() {
        return cashburnpermonth;
    }

    public void setCashburnpermonth(BigDecimal cashburnpermonth) {
        this.cashburnpermonth = cashburnpermonth;
    }

    public BigDecimal getTotalassets() {
        return totalassets;
    }

    public void setTotalassets(BigDecimal totalassets) {
        this.totalassets = totalassets;
    }

    public BigDecimal getTotalliabilities() {
        return totalliabilities;
    }

    public void setTotalliabilities(BigDecimal totalliabilities) {
        this.totalliabilities = totalliabilities;
    }

    public BigDecimal getCurrentassets() {
        return currentassets;
    }

    public void setCurrentassets(BigDecimal currentassets) {
        this.currentassets = currentassets;
    }

    public BigDecimal getCurrentliabilities() {
        return currentliabilities;
    }

    public void setCurrentliabilities(BigDecimal currentliabilities) {
        this.currentliabilities = currentliabilities;
    }

    public BigDecimal getCurrentnetaL() {
        return currentnetaL;
    }

    public void setCurrentnetaL(BigDecimal currentnetaL) {
        this.currentnetaL = currentnetaL;
    }

    public BigDecimal getCashlevel() {
        return cashlevel;
    }

    public void setCashlevel(BigDecimal cashlevel) {
        this.cashlevel = cashlevel;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public BigDecimal getCashassets() {
        return cashassets;
    }

    public void setCashassets(BigDecimal cashassets) {
        this.cashassets = cashassets;
    }

    public BigDecimal getOtherassets() {
        return otherassets;
    }

    public void setOtherassets(BigDecimal otherassets) {
        this.otherassets = otherassets;
    }

    public Long getOwnerequity() {
        return ownerequity;
    }

    public void setOwnerequity(Long ownerequity) {
        this.ownerequity = ownerequity;
    }

    public BigDecimal getOperatingincome() {
        return operatingincome;
    }

    public void setOperatingincome(BigDecimal operatingincome) {
        this.operatingincome = operatingincome;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getFsdate() {
        return fsdate;
    }

    public void setFsdate(String fsdate) {
        this.fsdate = fsdate == null ? null : fsdate.trim();
    }

    public String getYemth() {
        return yemth;
    }

    public void setYemth(String yemth) {
        this.yemth = yemth == null ? null : yemth.trim();
    }

    public BigDecimal getRetainedearnings() {
        return retainedearnings;
    }

    public void setRetainedearnings(BigDecimal retainedearnings) {
        this.retainedearnings = retainedearnings;
    }

    public BigDecimal getGrossprofit() {
        return grossprofit;
    }

    public void setGrossprofit(BigDecimal grossprofit) {
        this.grossprofit = grossprofit;
    }

    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    public BigDecimal getSharecapital() {
        return sharecapital;
    }

    public void setSharecapital(BigDecimal sharecapital) {
        this.sharecapital = sharecapital;
    }
}