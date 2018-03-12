package com.idgcapital.ufund.model;

import java.util.Date;

public class PortfolioBov {
    private Integer bovid;

    private String portfolioid;

    private Date updatedate;

    private String companybackground;

    private String operationandbusinessdevelopment;

    private String valuation;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    private String management;

    private String disposal;

    private String comparables;

    private String updatebusiness;

    private Integer headcount;

    private String wbstatus;

    private String lastwbstatus;

    public Integer getBovid() {
        return bovid;
    }

    public void setBovid(Integer bovid) {
        this.bovid = bovid;
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getCompanybackground() {
        return companybackground;
    }

    public void setCompanybackground(String companybackground) {
        this.companybackground = companybackground == null ? null : companybackground.trim();
    }

    public String getOperationandbusinessdevelopment() {
        return operationandbusinessdevelopment;
    }

    public void setOperationandbusinessdevelopment(String operationandbusinessdevelopment) {
        this.operationandbusinessdevelopment = operationandbusinessdevelopment == null ? null : operationandbusinessdevelopment.trim();
    }

    public String getValuation() {
        return valuation;
    }

    public void setValuation(String valuation) {
        this.valuation = valuation == null ? null : valuation.trim();
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

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management == null ? null : management.trim();
    }

    public String getDisposal() {
        return disposal;
    }

    public void setDisposal(String disposal) {
        this.disposal = disposal == null ? null : disposal.trim();
    }

    public String getComparables() {
        return comparables;
    }

    public void setComparables(String comparables) {
        this.comparables = comparables == null ? null : comparables.trim();
    }

    public String getUpdatebusiness() {
        return updatebusiness;
    }

    public void setUpdatebusiness(String updatebusiness) {
        this.updatebusiness = updatebusiness == null ? null : updatebusiness.trim();
    }

    public Integer getHeadcount() {
        return headcount;
    }

    public void setHeadcount(Integer headcount) {
        this.headcount = headcount;
    }

    public String getWbstatus() {
        return wbstatus;
    }

    public void setWbstatus(String wbstatus) {
        this.wbstatus = wbstatus;
    }

    public String getLastwbstatus() {
        return lastwbstatus;
    }

    public void setLastwbstatus(String lastwbstatus) {
        this.lastwbstatus = lastwbstatus;
    }
}