package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;

public class Capitalcall {
    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private String callid;

    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private String fundid;

    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private Date calleddate;

    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private BigDecimal calledpecentage;

    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private Date duedate;

    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private String calledpurpose;

    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private String status;

    @JsonView({AppView.capitalCallDetail.class})
    private String createdby;

    @JsonView({AppView.capitalCallDetail.class})
    private Date createddate;

    @JsonView({AppView.capitalCallDetail.class})
    private String modifiedby;

    @JsonView({AppView.capitalCallDetail.class})
    private Date modifieddate;

    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private Integer callorder;

    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private Boolean approved;

    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private BigDecimal totalcallamount;

    private int pageIndex;

    private int pageCount;

    private int pageEnd;

    private int pageStart;
    //查询条件
    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private String fundfamillyname;
    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private String fundname;
    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private String fundfullnameeng;
    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private String fundfullnamechi;

    private Date calleddateStart; //作为查询条件

    private Date calleddateEnd;//作为查询条件

    @JsonView({AppView.capitalCallList.class,AppView.capitalCallDetail.class})
    private String erisastatus;


    public String getCallid() {
        return callid;
    }

    public void setCallid(String callid) {
        this.callid = callid == null ? null : callid.trim();
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid == null ? null : fundid.trim();
    }

    public Date getCalleddate() {
        return calleddate;
    }

    public void setCalleddate(Date calleddate) {
        this.calleddate = calleddate;
    }

    public BigDecimal getCalledpecentage() {
        return calledpecentage;
    }

    public void setCalledpecentage(BigDecimal calledpecentage) {
        this.calledpecentage = calledpecentage;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public String getCalledpurpose() {
        return calledpurpose;
    }

    public void setCalledpurpose(String calledpurpose) {
        this.calledpurpose = calledpurpose == null ? null : calledpurpose.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public Integer getCallorder() {
        return callorder;
    }

    public void setCallorder(Integer callorder) {
        this.callorder = callorder;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public BigDecimal getTotalcallamount() {
        return totalcallamount;
    }

    public void setTotalcallamount(BigDecimal totalcallamount) {
        this.totalcallamount = totalcallamount;
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

    public Date getCalleddateStart() {
        return calleddateStart;
    }

    public void setCalleddateStart(Date calleddateStart) {
        this.calleddateStart = calleddateStart;
    }

    public Date getCalleddateEnd() {
        return calleddateEnd;
    }

    public void setCalleddateEnd(Date calleddateEnd) {
        this.calleddateEnd = calleddateEnd;
    }

    public String getErisastatus() {
        return erisastatus;
    }

    public void setErisastatus(String erisastatus) {
        this.erisastatus = erisastatus;
    }
}