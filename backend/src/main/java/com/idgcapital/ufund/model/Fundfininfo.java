package com.idgcapital.ufund.model;

import java.math.BigDecimal;
import java.util.Date;

public class Fundfininfo {
    private String finid;

    private String fundid;

    private Date findate;

    private BigDecimal projfairvalue;

    private BigDecimal netassets;

    private BigDecimal curincome;

    private BigDecimal curprofit;

    private BigDecimal cashassets;

    private BigDecimal otherassets;

    private BigDecimal gpnetassets;

    private BigDecimal lpnetassets;

    private BigDecimal debtvalue;

    private BigDecimal gpdistribution;

    private BigDecimal netprofit;

    private BigDecimal distributableprofit;

    private BigDecimal netvaluefundasset;

    private BigDecimal callasset;

    private BigDecimal netirrlp;

    private BigDecimal netirrgp;

    private BigDecimal netmultiplelp;

    private BigDecimal netmultiplegp;

    private String ismodified;

    private String validflag;

    private String remarks;

    private String fundworkreport;

    private String fundworkreportcode;

    private String createby;

    private Date createdate;

    private String editby;

    private Date editdate;

    public String getFinid() {
        return finid;
    }

    public void setFinid(String finid) {
        this.finid = finid == null ? null : finid.trim();
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid == null ? null : fundid.trim();
    }

    public Date getFindate() {
        return findate;
    }

    public void setFindate(Date findate) {
        this.findate = findate;
    }

    public BigDecimal getProjfairvalue() {
        return projfairvalue;
    }

    public void setProjfairvalue(BigDecimal projfairvalue) {
        this.projfairvalue = projfairvalue;
    }

    public BigDecimal getNetassets() {
        return netassets;
    }

    public void setNetassets(BigDecimal netassets) {
        this.netassets = netassets;
    }

    public BigDecimal getCurincome() {
        return curincome;
    }

    public void setCurincome(BigDecimal curincome) {
        this.curincome = curincome;
    }

    public BigDecimal getCurprofit() {
        return curprofit;
    }

    public void setCurprofit(BigDecimal curprofit) {
        this.curprofit = curprofit;
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

    public BigDecimal getGpnetassets() {
        return gpnetassets;
    }

    public void setGpnetassets(BigDecimal gpnetassets) {
        this.gpnetassets = gpnetassets;
    }

    public BigDecimal getLpnetassets() {
        return lpnetassets;
    }

    public void setLpnetassets(BigDecimal lpnetassets) {
        this.lpnetassets = lpnetassets;
    }

    public BigDecimal getDebtvalue() {
        return debtvalue;
    }

    public void setDebtvalue(BigDecimal debtvalue) {
        this.debtvalue = debtvalue;
    }

    public BigDecimal getGpdistribution() {
        return gpdistribution;
    }

    public void setGpdistribution(BigDecimal gpdistribution) {
        this.gpdistribution = gpdistribution;
    }

    public BigDecimal getNetprofit() {
        return netprofit;
    }

    public void setNetprofit(BigDecimal netprofit) {
        this.netprofit = netprofit;
    }

    public BigDecimal getDistributableprofit() {
        return distributableprofit;
    }

    public void setDistributableprofit(BigDecimal distributableprofit) {
        this.distributableprofit = distributableprofit;
    }

    public BigDecimal getNetvaluefundasset() {
        return netvaluefundasset;
    }

    public void setNetvaluefundasset(BigDecimal netvaluefundasset) {
        this.netvaluefundasset = netvaluefundasset;
    }

    public BigDecimal getCallasset() {
        return callasset;
    }

    public void setCallasset(BigDecimal callasset) {
        this.callasset = callasset;
    }

    public BigDecimal getNetirrlp() {
        return netirrlp;
    }

    public void setNetirrlp(BigDecimal netirrlp) {
        this.netirrlp = netirrlp;
    }

    public BigDecimal getNetirrgp() {
        return netirrgp;
    }

    public void setNetirrgp(BigDecimal netirrgp) {
        this.netirrgp = netirrgp;
    }

    public BigDecimal getNetmultiplelp() {
        return netmultiplelp;
    }

    public void setNetmultiplelp(BigDecimal netmultiplelp) {
        this.netmultiplelp = netmultiplelp;
    }

    public BigDecimal getNetmultiplegp() {
        return netmultiplegp;
    }

    public void setNetmultiplegp(BigDecimal netmultiplegp) {
        this.netmultiplegp = netmultiplegp;
    }

    public String getIsmodified() {
        return ismodified;
    }

    public void setIsmodified(String ismodified) {
        this.ismodified = ismodified == null ? null : ismodified.trim();
    }

    public String getValidflag() {
        return validflag;
    }

    public void setValidflag(String validflag) {
        this.validflag = validflag == null ? null : validflag.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getFundworkreport() {
        return fundworkreport;
    }

    public void setFundworkreport(String fundworkreport) {
        this.fundworkreport = fundworkreport == null ? null : fundworkreport.trim();
    }

    public String getFundworkreportcode() {
        return fundworkreportcode;
    }

    public void setFundworkreportcode(String fundworkreportcode) {
        this.fundworkreportcode = fundworkreportcode == null ? null : fundworkreportcode.trim();
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getEditby() {
        return editby;
    }

    public void setEditby(String editby) {
        this.editby = editby == null ? null : editby.trim();
    }

    public Date getEditdate() {
        return editdate;
    }

    public void setEditdate(Date editdate) {
        this.editdate = editdate;
    }
}