package com.idgcapital.ufund.model;

import java.math.BigDecimal;
import java.util.Date;

public class Capitalcommitment {
    private Integer commitid;

    private String lpid;

    private String lpfamily;

    private String lplegalfullname;

    private String fundid;

    private BigDecimal commitmentnum;

    private String commitmentcur;

    private Date commitmentdate;

    private String percentage;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    private String transferfromlpid;

    private Date transferdate;

    private String transfertolpid;

    private BigDecimal transferamount;

    private String comment;

    private String transfertype;

    private String transfertolpname;

    private int pageIndex;

    private int pageCount;

    private int pageEnd;

    private int pageStart;

    public Integer getCommitid() {
        return commitid;
    }

    public void setCommitid(Integer commitid) {
        this.commitid = commitid;
    }

    public String getLpid() {
        return lpid;
    }

    public void setLpid(String lpid) {
        this.lpid = lpid == null ? null : lpid.trim();
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid == null ? null : fundid.trim();
    }

    public BigDecimal getCommitmentnum() {
        return commitmentnum;
    }

    public void setCommitmentnum(BigDecimal commitmentnum) {
        this.commitmentnum = commitmentnum;
    }

    public String getCommitmentcur() {
        return commitmentcur;
    }

    public void setCommitmentcur(String commitmentcur) {
        this.commitmentcur = commitmentcur == null ? null : commitmentcur.trim();
    }

    public Date getCommitmentdate() {
        return commitmentdate;
    }

    public void setCommitmentdate(Date commitmentdate) {
        this.commitmentdate = commitmentdate;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage == null ? null : percentage.trim();
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

    public String getTransferfromlpid() {
        return transferfromlpid;
    }

    public void setTransferfromlpid(String transferfromlpid) {
        this.transferfromlpid = transferfromlpid == null ? null : transferfromlpid.trim();
    }

    public Date getTransferdate() {
        return transferdate;
    }

    public void setTransferdate(Date transferdate) {
        this.transferdate = transferdate;
    }

    public String getTransfertolpid() {
        return transfertolpid;
    }

    public void setTransfertolpid(String transfertolpid) {
        this.transfertolpid = transfertolpid == null ? null : transfertolpid.trim();
    }

    public BigDecimal getTransferamount() {
        return transferamount;
    }

    public void setTransferamount(BigDecimal transferamount) {
        this.transferamount = transferamount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getLpfamily() {
        return lpfamily;
    }

    public void setLpfamily(String lpfamily) {
        this.lpfamily = lpfamily;
    }

    public String getLplegalfullname() {
        return lplegalfullname;
    }

    public void setLplegalfullname(String lplegalfullname) {
        this.lplegalfullname = lplegalfullname;
    }

    public String getTransfertype() {
        return transfertype;
    }

    public void setTransfertype(String transfertype) {
        this.transfertype = transfertype;
    }

    public String getTransfertolpname() {
        return transfertolpname;
    }

    public void setTransfertolpname(String transfertolpname) {
        this.transfertolpname = transfertolpname;
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
}