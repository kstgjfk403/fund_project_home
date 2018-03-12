package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;

public class Portfolioloantoequity implements Cloneable{
    @JsonView({AppView.PortfolioLoanToEquityList.class,AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Date closedate;

    @JsonView({AppView.PortfolioLoanToEquityList.class,AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal remainderamount;

    @JsonView({AppView.PortfolioLoanToEquityList.class,AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal otherfees;

    @JsonView({AppView.PortfolioLoanToEquityList.class,AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal conversionratio;



    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Integer id;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String eiid;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String noteid;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String portfolioid;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String fundid;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal convertamount;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Integer securitytypeid;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String securitytypeidstr;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal costrelization;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal proceeds;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Long shareownedno;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal additionalcost;

    @JsonView({AppView.PortfolioLoanToEquityDetail.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String remarks;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    @JsonView({AppView.PortfolioLoanToEquityList.class,AppView.PortfolioLoanToEquityDetail.class})
    private String fundfamillyname;

    @JsonView(AppView.PortfolioLoanToEquityDetail.class)
    private String fundname;

    private String round;

    private String currency;

    private String investtype;


    @Override
    public Object clone() {
        Portfolioloantoequity to = null;
        try{
            to = (Portfolioloantoequity)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return to;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEiid() {
        return eiid;
    }

    public void setEiid(String eiid) {
        this.eiid = eiid == null ? null : eiid.trim();
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public String getNoteid() {
        return noteid;
    }

    public void setNoteid(String noteid) {
        this.noteid = noteid == null ? null : noteid.trim();
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

    public BigDecimal getConvertamount() {
        return convertamount;
    }

    public void setConvertamount(BigDecimal convertamount) {
        this.convertamount = convertamount;
    }

    public Integer getSecuritytypeid() {
        return securitytypeid;
    }

    public void setSecuritytypeid(Integer securitytypeid) {
        this.securitytypeid = securitytypeid;
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

    public BigDecimal getAdditionalcost() {
        return additionalcost;
    }

    public void setAdditionalcost(BigDecimal additionalcost) {
        this.additionalcost = additionalcost;
    }

    public BigDecimal getOtherfees() {
        return otherfees;
    }

    public void setOtherfees(BigDecimal otherfees) {
        this.otherfees = otherfees;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

    public String getFundfamillyname() {
        return fundfamillyname;
    }

    public void setFundfamillyname(String fundfamillyname) {
        this.fundfamillyname = fundfamillyname;
    }

    public BigDecimal getRemainderamount() {
        return remainderamount;
    }

    public void setRemainderamount(BigDecimal remainderamount) {
        this.remainderamount = remainderamount;
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }

    public String getSecuritytypeidstr() {
        return securitytypeidstr;
    }

    public void setSecuritytypeidstr(String securitytypeidstr) {
        this.securitytypeidstr = securitytypeidstr;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInvesttype() {
        return investtype;
    }

    public void setInvesttype(String investtype) {
        this.investtype = investtype;
    }

}