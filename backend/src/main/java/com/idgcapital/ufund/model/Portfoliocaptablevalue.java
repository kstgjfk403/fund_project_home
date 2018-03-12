package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;

public class Portfoliocaptablevalue {

    private Integer capid;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private String portfolioid;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private Integer shareownerid;

    private String round;

    private Date termsigndate;

    private Date maxtermsigndate;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private String sharetype;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private String shareowner;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private Integer securitytypeid;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private String securitytypeidstr;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private Long preshareownedno;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private Long shareownedno;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private Long addshareownedno;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private BigDecimal cost;

    private BigDecimal premoney;

    private BigDecimal postmoney;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private BigDecimal proper;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private BigDecimal properwithoutesop;

    private String investtype;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    private String fundid;

    public Integer getCapid() {
        return capid;
    }

    public void setCapid(Integer capid) {
        this.capid = capid;
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public Integer getShareownerid() {
        return shareownerid;
    }

    public void setShareownerid(Integer shareownerid) {
        this.shareownerid = shareownerid;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round == null ? null : round.trim();
    }

    public Date getTermsigndate() {
        return termsigndate;
    }

    public void setTermsigndate(Date termsigndate) {
        this.termsigndate = termsigndate;
    }

    public Integer getSecuritytypeid() {
        return securitytypeid;
    }

    public void setSecuritytypeid(Integer securitytypeid) {
        this.securitytypeid = securitytypeid;
    }

    public Long getPreshareownedno() {
        return preshareownedno;
    }

    public void setPreshareownedno(Long preshareownedno) {
        this.preshareownedno = preshareownedno;
    }

    public Long getShareownedno() {
        return shareownedno;
    }

    public void setShareownedno(Long shareownedno) {
        this.shareownedno = shareownedno;
    }

    public Long getAddshareownedno() {
        return addshareownedno;
    }

    public void setAddshareownedno(Long addshareownedno) {
        this.addshareownedno = addshareownedno;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPremoney() {
        return premoney;
    }

    public void setPremoney(BigDecimal premoney) {
        this.premoney = premoney;
    }

    public BigDecimal getPostmoney() {
        return postmoney;
    }

    public void setPostmoney(BigDecimal postmoney) {
        this.postmoney = postmoney;
    }

    public BigDecimal getProper() {
        return proper;
    }

    public void setProper(BigDecimal proper) {
        this.proper = proper;
    }

    public BigDecimal getProperwithoutesop() {
        return properwithoutesop;
    }

    public void setProperwithoutesop(BigDecimal properwithoutesop) {
        this.properwithoutesop = properwithoutesop;
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

    public Date getMaxtermsigndate() {
        return maxtermsigndate;
    }

    public void setMaxtermsigndate(Date maxtermsigndate) {
        this.maxtermsigndate = maxtermsigndate;
    }

    public String getSharetype() {
        return sharetype;
    }

    public void setSharetype(String sharetype) {
        this.sharetype = sharetype;
    }

    public String getShareowner() {
        return shareowner;
    }

    public void setShareowner(String shareowner) {
        this.shareowner = shareowner;
    }

    public String getSecuritytypeidstr() {
        return securitytypeidstr;
    }

    public void setSecuritytypeidstr(String securitytypeidstr) {
        this.securitytypeidstr = securitytypeidstr;
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid;
    }

    public String getInvesttype() {
        return investtype;
    }

    public void setInvesttype(String investtype) {
        this.investtype = investtype;
    }
}