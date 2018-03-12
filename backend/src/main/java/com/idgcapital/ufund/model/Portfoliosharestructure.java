package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;

public class Portfoliosharestructure {
    @JsonView({AppView.PortfolioCapTableList.class})
    private Integer sharecaptialid;

    @JsonView({AppView.PortfolioCapTableList.class})
    private String sharetype;

    @JsonView({AppView.PortfolioCapTableList.class})
    private String shareowner;

    @JsonView({AppView.PortfolioCapTableList.class})
    private Long shareownedno;

    @JsonView({AppView.PortfolioCapTableList.class})
    private BigDecimal cost;

    @JsonView({AppView.PortfolioCapTableList.class})
    private Long totalshareownedno;

    @JsonView({AppView.PortfolioCapTableList.class})
    private BigDecimal proper;

    private BigDecimal properwithoutesop;

    @JsonView({AppView.PortfolioCapTableList.class})
    private BigDecimal totalcost;

    private String portfolioid;

    private String fundid;

    private String eiid;

    private String abbname;

    private String round;

    private Date closedate;

    private String liquidationpreference;

    private String conversionratio;

    private Integer securitytypeid;

    private BigDecimal otherfees;

    private BigDecimal value;

    private Date lastupdatedate;

    private String remarks;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private BigDecimal premoney;

    private BigDecimal currentround;

    private BigDecimal postmoney;

    private String currency;

    private Double usdRmbRate;

    private Boolean approved;

    private BigDecimal investmentamount;

    private BigDecimal amounttofund;

    private String comments;

    private Long rid;

    private Integer id;

    private String investtype;

    private BigDecimal pic;

    private String warrantid;

    private BigDecimal costrealization;

    public Integer getSharecaptialid() {
        return sharecaptialid;
    }

    public void setSharecaptialid(Integer sharecaptialid) {
        this.sharecaptialid = sharecaptialid;
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

    public String getEiid() {
        return eiid;
    }

    public void setEiid(String eiid) {
        this.eiid = eiid == null ? null : eiid.trim();
    }

    public String getSharetype() {
        return sharetype;
    }

    public void setSharetype(String sharetype) {
        this.sharetype = sharetype == null ? null : sharetype.trim();
    }

    public String getShareowner() {
        return shareowner;
    }

    public void setShareowner(String shareowner) {
        this.shareowner = shareowner == null ? null : shareowner.trim();
    }

    public String getAbbname() {
        return abbname;
    }

    public void setAbbname(String abbname) {
        this.abbname = abbname == null ? null : abbname.trim();
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round == null ? null : round.trim();
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public String getLiquidationpreference() {
        return liquidationpreference;
    }

    public void setLiquidationpreference(String liquidationpreference) {
        this.liquidationpreference = liquidationpreference == null ? null : liquidationpreference.trim();
    }

    public String getConversionratio() {
        return conversionratio;
    }

    public void setConversionratio(String conversionratio) {
        this.conversionratio = conversionratio == null ? null : conversionratio.trim();
    }

    public Integer getSecuritytypeid() {
        return securitytypeid;
    }

    public void setSecuritytypeid(Integer securitytypeid) {
        this.securitytypeid = securitytypeid;
    }

    public Long getShareownedno() {
        return shareownedno;
    }

    public void setShareownedno(Long shareownedno) {
        this.shareownedno = shareownedno;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getOtherfees() {
        return otherfees;
    }

    public void setOtherfees(BigDecimal otherfees) {
        this.otherfees = otherfees;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
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

    public BigDecimal getPremoney() {
        return premoney;
    }

    public void setPremoney(BigDecimal premoney) {
        this.premoney = premoney;
    }

    public BigDecimal getCurrentround() {
        return currentround;
    }

    public void setCurrentround(BigDecimal currentround) {
        this.currentround = currentround;
    }

    public BigDecimal getPostmoney() {
        return postmoney;
    }

    public void setPostmoney(BigDecimal postmoney) {
        this.postmoney = postmoney;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Double getUsdRmbRate() {
        return usdRmbRate;
    }

    public void setUsdRmbRate(Double usdRmbRate) {
        this.usdRmbRate = usdRmbRate;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public BigDecimal getInvestmentamount() {
        return investmentamount;
    }

    public void setInvestmentamount(BigDecimal investmentamount) {
        this.investmentamount = investmentamount;
    }

    public BigDecimal getAmounttofund() {
        return amounttofund;
    }

    public void setAmounttofund(BigDecimal amounttofund) {
        this.amounttofund = amounttofund;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvesttype() {
        return investtype;
    }

    public void setInvesttype(String investtype) {
        this.investtype = investtype == null ? null : investtype.trim();
    }

    public BigDecimal getPic() {
        return pic;
    }

    public void setPic(BigDecimal pic) {
        this.pic = pic;
    }

    public String getWarrantid() {
        return warrantid;
    }

    public void setWarrantid(String warrantid) {
        this.warrantid = warrantid == null ? null : warrantid.trim();
    }

    public BigDecimal getCostrealization() {
        return costrealization;
    }

    public void setCostrealization(BigDecimal costrealization) {
        this.costrealization = costrealization;
    }

    public Long getTotalshareownedno() {
        return totalshareownedno;
    }

    public void setTotalshareownedno(Long totalshareownedno) {
        this.totalshareownedno = totalshareownedno;
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

    public BigDecimal getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(BigDecimal totalcost) {
        this.totalcost = totalcost;
    }
}