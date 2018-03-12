package com.idgcapital.ufund.model;

import java.util.Date;

public class Portfoliostatushistory {
    private Integer logid;

    private String portfolioid;

    private Integer portfoliostatus;

    private Date statuseffectivedate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public Integer getPortfoliostatus() {
        return portfoliostatus;
    }

    public void setPortfoliostatus(Integer portfoliostatus) {
        this.portfoliostatus = portfoliostatus;
    }

    public Date getStatuseffectivedate() {
        return statuseffectivedate;
    }

    public void setStatuseffectivedate(Date statuseffectivedate) {
        this.statuseffectivedate = statuseffectivedate;
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
}