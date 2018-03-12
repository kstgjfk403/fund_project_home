package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Portfoliowarrant implements Cloneable{
    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private String warrantid;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private String portfolioid;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private String fundid;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private Date issuedate;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private Date duedate;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private BigDecimal warrantamount;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private Double warrantprice;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private String round;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private Integer sharesno;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private Integer securitytypeid;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private String securitytypeidstr;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private String currency;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private String ownertype;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private String warrantownername;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private Boolean valid;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private String comment;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private String fundfamillyname;

    @JsonView({AppView.PortfolioWarrantList.class,AppView.PortfolioWarrantDetail.class})
    private List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList;

    private String fundname;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    private BigDecimal ownership;

    private Boolean exercise;

    private Date termSignDate;

    @Override
    public Object clone() {
        Portfoliowarrant portfoliowarrant = null;
        try{
            portfoliowarrant = (Portfoliowarrant)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return portfoliowarrant;
    }



    public String getWarrantid() {
        return warrantid;
    }

    public void setWarrantid(String warrantid) {
        this.warrantid = warrantid == null ? null : warrantid.trim();
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

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public BigDecimal getWarrantamount() {
        return warrantamount;
    }

    public void setWarrantamount(BigDecimal warrantamount) {
        this.warrantamount = warrantamount;
    }

    public Double getWarrantprice() {
        return warrantprice;
    }

    public void setWarrantprice(Double warrantprice) {
        this.warrantprice = warrantprice;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round == null ? null : round.trim();
    }

    public Integer getSharesno() {
        return sharesno;
    }

    public void setSharesno(Integer sharesno) {
        this.sharesno = sharesno;
    }

    public Integer getSecuritytypeid() {
        return securitytypeid;
    }

    public void setSecuritytypeid(Integer securitytypeid) {
        this.securitytypeid = securitytypeid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public BigDecimal getOwnership() {
        return ownership;
    }

    public void setOwnership(BigDecimal ownership) {
        this.ownership = ownership;
    }

    public Boolean getExercise() {
        return exercise;
    }

    public void setExercise(Boolean exercise) {
        this.exercise = exercise;
    }

    public String getOwnertype() {
        return ownertype;
    }

    public void setOwnertype(String ownertype) {
        this.ownertype = ownertype == null ? null : ownertype.trim();
    }

    public String getWarrantownername() {
        return warrantownername;
    }

    public void setWarrantownername(String warrantownername) {
        this.warrantownername = warrantownername == null ? null : warrantownername.trim();
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getFundfamillyname() {
        return fundfamillyname;
    }

    public void setFundfamillyname(String fundfamillyname) {
        this.fundfamillyname = fundfamillyname;
    }

    public String getSecuritytypeidstr() {
        return securitytypeidstr;
    }

    public void setSecuritytypeidstr(String securitytypeidstr) {
        this.securitytypeidstr = securitytypeidstr;
    }

    public List<Portfoliocaptablevaluedetail> getPortfoliocaptablevaluedetailList() {
        return portfoliocaptablevaluedetailList;
    }

    public void setPortfoliocaptablevaluedetailList(List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList) {
        this.portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailList;
    }

    public Date getTermSignDate() {
        return termSignDate;
    }

    public void setTermSignDate(Date termSignDate) {
        this.termSignDate = termSignDate;
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }
}