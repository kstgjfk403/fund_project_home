package com.idgcapital.ufund.model;

import java.math.BigDecimal;
import java.util.Date;

public class Portfoliocaptablename {

    private Integer shareownerid;

    private String portfolioid;

    private String sharetype;

    private String shareowner;

    private Integer securitytypeid;

    private String round;

    private Date termsigndate;

    private Date closedate;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    private Long preshareownedno;

    private Long shareownedno;

    private Long addshareownedno;

    private BigDecimal cost;

    private String bizid;

    private String investtype;

    public Integer getShareownerid() {
        return shareownerid;
    }

    public void setShareownerid(Integer shareownerid) {
        this.shareownerid = shareownerid;
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
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

    public Integer getSecuritytypeid() {
        return securitytypeid;
    }

    public void setSecuritytypeid(Integer securitytypeid) {
        this.securitytypeid = securitytypeid;
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

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Date getTermsigndate() {
        return termsigndate;
    }

    public void setTermsigndate(Date termsigndate) {
        this.termsigndate = termsigndate;
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
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

    public String getBizid() {
        return bizid;
    }

    public void setBizid(String bizid) {
        this.bizid = bizid;
    }

    public String getInvesttype() {
        return investtype;
    }

    public void setInvesttype(String investtype) {
        this.investtype = investtype;
    }
}