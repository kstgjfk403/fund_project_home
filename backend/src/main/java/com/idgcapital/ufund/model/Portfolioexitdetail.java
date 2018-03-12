package com.idgcapital.ufund.model;

import java.math.BigDecimal;

public class Portfolioexitdetail {
    private Integer id;

    private Integer exitid;

    private String eiid;

    private Long shareexit;

    private BigDecimal costrelization;

    private BigDecimal costunrelization;

    private BigDecimal proceedsrelization;

    private BigDecimal proceedsunrelization;

    private BigDecimal lossrelization;

    private String remarks;

    private Boolean approved;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExitid() {
        return exitid;
    }

    public void setExitid(Integer exitid) {
        this.exitid = exitid;
    }

    public String getEiid() {
        return eiid;
    }

    public void setEiid(String eiid) {
        this.eiid = eiid == null ? null : eiid.trim();
    }

    public Long getShareexit() {
        return shareexit;
    }

    public void setShareexit(Long shareexit) {
        this.shareexit = shareexit;
    }

    public BigDecimal getCostrelization() {
        return costrelization;
    }

    public void setCostrelization(BigDecimal costrelization) {
        this.costrelization = costrelization;
    }

    public BigDecimal getCostunrelization() {
        return costunrelization;
    }

    public void setCostunrelization(BigDecimal costunrelization) {
        this.costunrelization = costunrelization;
    }

    public BigDecimal getProceedsrelization() {
        return proceedsrelization;
    }

    public void setProceedsrelization(BigDecimal proceedsrelization) {
        this.proceedsrelization = proceedsrelization;
    }

    public BigDecimal getProceedsunrelization() {
        return proceedsunrelization;
    }

    public void setProceedsunrelization(BigDecimal proceedsunrelization) {
        this.proceedsunrelization = proceedsunrelization;
    }

    public BigDecimal getLossrelization() {
        return lossrelization;
    }

    public void setLossrelization(BigDecimal lossrelization) {
        this.lossrelization = lossrelization;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}