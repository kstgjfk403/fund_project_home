package com.idgcapital.ufund.model;

import java.util.Date;

public class PortfolioWbstatus {
    private Integer wbid;

    private Date wbdate;

    private String portfolioid;

    private String wbstatus;

    private Boolean generate;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    private Boolean valuationready;

    private Boolean finicialready;

    private Boolean bovupdate;

    private String fundtype;

    private String fundid;

    private Integer startpageno;

    private String html;

    public Integer getWbid() {
        return wbid;
    }

    public void setWbid(Integer wbid) {
        this.wbid = wbid;
    }

    public Date getWbdate() {
        return wbdate;
    }

    public void setWbdate(Date wbdate) {
        this.wbdate = wbdate;
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public String getWbstatus() {
        return wbstatus;
    }

    public void setWbstatus(String wbstatus) {
        this.wbstatus = wbstatus == null ? null : wbstatus.trim();
    }

    public Boolean getGenerate() {
        return generate;
    }

    public void setGenerate(Boolean generate) {
        this.generate = generate;
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

    public Boolean getValuationready() {
        return valuationready;
    }

    public void setValuationready(Boolean valuationready) {
        this.valuationready = valuationready;
    }

    public Boolean getFinicialready() {
        return finicialready;
    }

    public void setFinicialready(Boolean finicialready) {
        this.finicialready = finicialready;
    }

    public Boolean getBovupdate() {
        return bovupdate;
    }

    public void setBovupdate(Boolean bovupdate) {
        this.bovupdate = bovupdate;
    }

    public String getFundtype() {
        return fundtype;
    }

    public void setFundtype(String fundtype) {
        this.fundtype = fundtype == null ? null : fundtype.trim();
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid == null ? null : fundid.trim();
    }

    public Integer getStartpageno() {
        return startpageno;
    }

    public void setStartpageno(Integer startpageno) {
        this.startpageno = startpageno;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html == null ? null : html.trim();
    }
}