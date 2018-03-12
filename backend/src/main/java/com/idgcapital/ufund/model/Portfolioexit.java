package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Portfolioexit implements Cloneable{
    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private Integer id;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private String exittype;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private String exitmode;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private String portfolioid;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private String fundfamillyname;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private String fundid;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private String fundname;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private Date closedate;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private Date termsigndate;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private String currency;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private BigDecimal proceeds;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private Long shareexit;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private String round;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private String securitytypestr;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private Integer securitytypeid;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private BigDecimal costrelization;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private Long shareremain;

    private BigDecimal otherfeesrelization;

    private BigDecimal costunrelization;

    private BigDecimal proceedsrelization;

    private BigDecimal proceedsunrelization;

    private BigDecimal lossrelization;

    private String noteid;

    private String eiid;

    private String remarks;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    @JsonView({AppView.PortfolioExitList.class,AppView.PortfolioExitDetail.class})
    private List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList;


    @Override
    public Object clone() {
        Portfolioexit exit = null;
        try{
            exit = (Portfolioexit)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return exit;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExittype() {
        return exittype;
    }

    public void setExittype(String exittype) {
        this.exittype = exittype == null ? null : exittype.trim();
    }

    public String getExitmode() {
        return exitmode;
    }

    public void setExitmode(String exitmode) {
        this.exitmode = exitmode == null ? null : exitmode.trim();
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public String getFundfamillyname() {
        return fundfamillyname;
    }

    public void setFundfamillyname(String fundfamillyname) {
        this.fundfamillyname = fundfamillyname == null ? null : fundfamillyname.trim();
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public BigDecimal getProceeds() {
        return proceeds;
    }

    public void setProceeds(BigDecimal proceeds) {
        this.proceeds = proceeds;
    }

    public Long getShareexit() {
        return shareexit;
    }

    public void setShareexit(Long shareexit) {
        this.shareexit = shareexit;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round == null ? null : round.trim();
    }

    public Integer getSecuritytypeid() {
        return securitytypeid;
    }

    public void setSecuritytypeid(Integer securitytypeid) {
        this.securitytypeid = securitytypeid;
    }

    public BigDecimal getOtherfeesrelization() {
        return otherfeesrelization;
    }

    public void setOtherfeesrelization(BigDecimal otherfeesrelization) {
        this.otherfeesrelization = otherfeesrelization;
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

    public String getNoteid() {
        return noteid;
    }

    public void setNoteid(String noteid) {
        this.noteid = noteid == null ? null : noteid.trim();
    }

    public String getEiid() {
        return eiid;
    }

    public void setEiid(String eiid) {
        this.eiid = eiid == null ? null : eiid.trim();
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

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }

    public String getSecuritytypestr() {
        return securitytypestr;
    }

    public void setSecuritytypestr(String securitytypestr) {
        this.securitytypestr = securitytypestr;
    }

    public Long getShareremain() {
        return shareremain;
    }

    public void setShareremain(Long shareremain) {
        this.shareremain = shareremain;
    }

    public List<Portfoliocaptablevaluedetail> getPortfoliocaptablevaluedetailList() {
        return portfoliocaptablevaluedetailList;
    }

    public void setPortfoliocaptablevaluedetailList(List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList) {
        this.portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailList;
    }

    public Date getTermsigndate() {
        return termsigndate;
    }

    public void setTermsigndate(Date termsigndate) {
        this.termsigndate = termsigndate;
    }
}