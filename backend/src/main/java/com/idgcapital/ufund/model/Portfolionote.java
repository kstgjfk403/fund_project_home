package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;

public class Portfolionote implements Cloneable{
    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private String noteid;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private String portfolioid;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private String fundid;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private String fundname;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private String fundfamillyname;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private Date closedate;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private String notetype;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private BigDecimal notenum;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private String currency;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private Date feedate;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private BigDecimal noteotherfee;

    private BigDecimal noteotherfeemain;

    private BigDecimal noteotherfeeinv;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private Date duedate;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private String interestrate;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private String interestdesc;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private Boolean extension;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private Date extensiondate;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private Date taxlotdate;

    @JsonView({AppView.PortfolioNoteList.class,AppView.PortfolioNoteDetail.class})
    private String vouncher;

    private BigDecimal reminderamount;

    private BigDecimal notevalue;

    private String loanterm;

    private BigDecimal proceeds;

    private BigDecimal costrelization;

    private Boolean convertible;

    private String convertfromnoteid;

    private String convertremark;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    private String transactiondocument;

    private Date valuatedate;

    private Double usdRmbRate;

    private BigDecimal investamount;

    private Double amounttofund;

    private String trans;

    private Date financialdate;

    private Long bondnum;

    private String interestfromnoteid;

    @Override
    public Object clone() {
        Portfolionote note = null;
        try{
            note = (Portfolionote)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return note;
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

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public String getNotetype() {
        return notetype;
    }

    public void setNotetype(String notetype) {
        this.notetype = notetype == null ? null : notetype.trim();
    }

    public BigDecimal getNotenum() {
        return notenum;
    }

    public void setNotenum(BigDecimal notenum) {
        this.notenum = notenum;
    }

    public BigDecimal getNotevalue() {
        return notevalue;
    }

    public void setNotevalue(BigDecimal notevalue) {
        this.notevalue = notevalue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getLoanterm() {
        return loanterm;
    }

    public void setLoanterm(String loanterm) {
        this.loanterm = loanterm == null ? null : loanterm.trim();
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public String getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(String interestrate) {
        this.interestrate = interestrate == null ? null : interestrate.trim();
    }

    public String getInterestdesc() {
        return interestdesc;
    }

    public void setInterestdesc(String interestdesc) {
        this.interestdesc = interestdesc == null ? null : interestdesc.trim();
    }

    public BigDecimal getProceeds() {
        return proceeds;
    }

    public void setProceeds(BigDecimal proceeds) {
        this.proceeds = proceeds;
    }

    public BigDecimal getCostrelization() {
        return costrelization;
    }

    public void setCostrelization(BigDecimal costrelization) {
        this.costrelization = costrelization;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public String getConvertfromnoteid() {
        return convertfromnoteid;
    }

    public void setConvertfromnoteid(String convertfromnoteid) {
        this.convertfromnoteid = convertfromnoteid == null ? null : convertfromnoteid.trim();
    }

    public String getConvertremark() {
        return convertremark;
    }

    public void setConvertremark(String convertremark) {
        this.convertremark = convertremark == null ? null : convertremark.trim();
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

    public Boolean getExtension() {
        return extension;
    }

    public void setExtension(Boolean extension) {
        this.extension = extension;
    }

    public Date getExtensiondate() {
        return extensiondate;
    }

    public void setExtensiondate(Date extensiondate) {
        this.extensiondate = extensiondate;
    }

    public String getTransactiondocument() {
        return transactiondocument;
    }

    public void setTransactiondocument(String transactiondocument) {
        this.transactiondocument = transactiondocument == null ? null : transactiondocument.trim();
    }

    public Date getValuatedate() {
        return valuatedate;
    }

    public void setValuatedate(Date valuatedate) {
        this.valuatedate = valuatedate;
    }

    public Double getUsdRmbRate() {
        return usdRmbRate;
    }

    public void setUsdRmbRate(Double usdRmbRate) {
        this.usdRmbRate = usdRmbRate;
    }

    public BigDecimal getInvestamount() {
        return investamount;
    }

    public void setInvestamount(BigDecimal investamount) {
        this.investamount = investamount;
    }

    public Double getAmounttofund() {
        return amounttofund;
    }

    public void setAmounttofund(Double amounttofund) {
        this.amounttofund = amounttofund;
    }

    public Date getTaxlotdate() {
        return taxlotdate;
    }

    public void setTaxlotdate(Date taxlotdate) {
        this.taxlotdate = taxlotdate;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans == null ? null : trans.trim();
    }

    public String getVouncher() {
        return vouncher;
    }

    public void setVouncher(String vouncher) {
        this.vouncher = vouncher == null ? null : vouncher.trim();
    }

    public Date getFinancialdate() {
        return financialdate;
    }

    public void setFinancialdate(Date financialdate) {
        this.financialdate = financialdate;
    }

    public BigDecimal getNoteotherfee() {
        return noteotherfee;
    }

    public void setNoteotherfee(BigDecimal noteotherfee) {
        this.noteotherfee = noteotherfee;
    }

    public Long getBondnum() {
        return bondnum;
    }

    public void setBondnum(Long bondnum) {
        this.bondnum = bondnum;
    }

    public String getInterestfromnoteid() {
        return interestfromnoteid;
    }

    public void setInterestfromnoteid(String interestfromnoteid) {
        this.interestfromnoteid = interestfromnoteid == null ? null : interestfromnoteid.trim();
    }

    public Date getFeedate() {
        return feedate;
    }

    public void setFeedate(Date feedate) {
        this.feedate = feedate;
    }

    public String getFundfamillyname() {
        return fundfamillyname;
    }

    public void setFundfamillyname(String fundfamillyname) {
        this.fundfamillyname = fundfamillyname;
    }

    public BigDecimal getNoteotherfeemain() {
        return noteotherfeemain;
    }

    public void setNoteotherfeemain(BigDecimal noteotherfeemain) {
        this.noteotherfeemain = noteotherfeemain;
    }

    public BigDecimal getNoteotherfeeinv() {
        return noteotherfeeinv;
    }

    public void setNoteotherfeeinv(BigDecimal noteotherfeeinv) {
        this.noteotherfeeinv = noteotherfeeinv;
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }

    public BigDecimal getReminderamount() {
        return reminderamount;
    }

    public void setReminderamount(BigDecimal reminderamount) {
        this.reminderamount = reminderamount;
    }
}