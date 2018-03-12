package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;

public class Lpcashdistribution {
    private String lpdistributionid;

    private String fundid;

    private Date issuedate;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    private BigDecimal totaldistributionamount;

    private String distributiondes;

    private Integer distributionorder;

    private BigDecimal multiple;


    private int pageIndex;

    private int pageCount;

    private int pageEnd;

    private int pageStart;
    //查询条件
    private String fundfamillyname;
    private String fundname;
    private String fundfullnameeng;
    private String fundfullnamechi;

    private Date issuedateStart; //作为查询条件

    private Date issuedateEnd;//作为查询条件

    private String sourceIdListStr; //portfolioExit ID 或者 dividend id

    private String sourceTypeListStr; //"exit,dividend,exit,dividend"


    public String getLpdistributionid() {
        return lpdistributionid;
    }

    public void setLpdistributionid(String lpdistributionid) {
        this.lpdistributionid = lpdistributionid == null ? null : lpdistributionid.trim();
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

    public BigDecimal getTotaldistributionamount() {
        return totaldistributionamount;
    }

    public void setTotaldistributionamount(BigDecimal totaldistributionamount) {
        this.totaldistributionamount = totaldistributionamount;
    }

    public String getDistributiondes() {
        return distributiondes;
    }

    public void setDistributiondes(String distributiondes) {
        this.distributiondes = distributiondes == null ? null : distributiondes.trim();
    }

    public Integer getDistributionorder() {
        return distributionorder;
    }

    public void setDistributionorder(Integer distributionorder) {
        this.distributionorder = distributionorder;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }

    public void setMultiple(BigDecimal multiple) {
        this.multiple = multiple;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public String getFundfamillyname() {
        return fundfamillyname;
    }

    public void setFundfamillyname(String fundfamillyname) {
        this.fundfamillyname = fundfamillyname;
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }

    public String getFundfullnameeng() {
        return fundfullnameeng;
    }

    public void setFundfullnameeng(String fundfullnameeng) {
        this.fundfullnameeng = fundfullnameeng;
    }

    public String getFundfullnamechi() {
        return fundfullnamechi;
    }

    public void setFundfullnamechi(String fundfullnamechi) {
        this.fundfullnamechi = fundfullnamechi;
    }

    public Date getIssuedateStart() {
        return issuedateStart;
    }

    public void setIssuedateStart(Date issuedateStart) {
        this.issuedateStart = issuedateStart;
    }

    public Date getIssuedateEnd() {
        return issuedateEnd;
    }

    public void setIssuedateEnd(Date issuedateEnd) {
        this.issuedateEnd = issuedateEnd;
    }

    public String getSourceIdListStr() {
        return sourceIdListStr;
    }

    public void setSourceIdListStr(String sourceIdListStr) {
        this.sourceIdListStr = sourceIdListStr;
    }

    public String getSourceTypeListStr() {
        return sourceTypeListStr;
    }

    public void setSourceTypeListStr(String sourceTypeListStr) {
        this.sourceTypeListStr = sourceTypeListStr;
    }
}