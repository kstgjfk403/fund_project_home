package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.util.Date;

public class Spvportfolioinfo {
    @JsonView({AppView.SpvPortfolioList.class})
    private Integer spvportfolioid;

    @JsonView({AppView.SpvPortfolioList.class})
    private String spvid;

    @JsonView({AppView.SpvPortfolioList.class})
    private String spvName;

    @JsonView({AppView.SpvPortfolioList.class})
    private String portfolioid;

    @JsonView({AppView.SpvPortfolioList.class})
    private String remarks;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    @JsonView({AppView.SpvPortfolioList.class})
    private String fundid;

    @JsonView({AppView.SpvPortfolioList.class})
    private String fundfamilyname;

    public Integer getSpvportfolioid() {
        return spvportfolioid;
    }

    public void setSpvportfolioid(Integer spvportfolioid) {
        this.spvportfolioid = spvportfolioid;
    }

    public String getSpvid() {
        return spvid;
    }

    public void setSpvid(String spvid) {
        this.spvid = spvid == null ? null : spvid.trim();
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
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

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid == null ? null : fundid.trim();
    }

    public String getFundfamilyname() {
        return fundfamilyname;
    }

    public void setFundfamilyname(String fundfamilyname) {
        this.fundfamilyname = fundfamilyname == null ? null : fundfamilyname.trim();
    }

    public String getSpvName() {
        return spvName;
    }

    public void setSpvName(String spvName) {
        this.spvName = spvName;
    }
}