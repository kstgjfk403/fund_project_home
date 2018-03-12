package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.util.Date;

public class Spvinfo {
    @JsonView({AppView.SpvDetail.class,AppView.SpvEntityList.class})
    private String spvid;

    @JsonView({AppView.SpvDetail.class,AppView.SpvEntityList.class})
    private String spvname;

    @JsonView({AppView.SpvDetail.class,AppView.SpvEntityList.class})
    private String spvnamechi;

    @JsonView({AppView.SpvDetail.class,AppView.SpvEntityList.class})
    private String parentspvid;

    @JsonView({AppView.SpvDetail.class,AppView.SpvEntityList.class})
    private Integer spvlevel;

    @JsonView({AppView.SpvDetail.class,AppView.SpvEntityList.class})
    private String regaddress;

    @JsonView({AppView.SpvDetail.class,AppView.SpvEntityList.class})
    private String remarks;

    @JsonView({AppView.SpvDetail.class,AppView.SpvEntityList.class})
    private String isleaf;

    @JsonView({AppView.SpvDetail.class,AppView.SpvEntityList.class})
    private Integer spvtype;

    @JsonView({AppView.SpvDetail.class,AppView.SpvEntityList.class})
    private String spvtypestr;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private int pageIndex;

    private int pageCount;

    private int pageEnd;

    private int pageStart;

    public String getSpvid() {
        return spvid;
    }

    public void setSpvid(String spvid) {
        this.spvid = spvid == null ? null : spvid.trim();
    }

    public String getSpvname() {
        return spvname;
    }

    public void setSpvname(String spvname) {
        this.spvname = spvname == null ? null : spvname.trim();
    }

    public String getSpvnamechi() {
        return spvnamechi;
    }

    public void setSpvnamechi(String spvnamechi) {
        this.spvnamechi = spvnamechi == null ? null : spvnamechi.trim();
    }

    public String getParentspvid() {
        return parentspvid;
    }

    public void setParentspvid(String parentspvid) {
        this.parentspvid = parentspvid == null ? null : parentspvid.trim();
    }

    public Integer getSpvlevel() {
        return spvlevel;
    }

    public void setSpvlevel(Integer spvlevel) {
        this.spvlevel = spvlevel;
    }

    public String getRegaddress() {
        return regaddress;
    }

    public void setRegaddress(String regaddress) {
        this.regaddress = regaddress == null ? null : regaddress.trim();
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

    public String getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(String isleaf) {
        this.isleaf = isleaf;
    }

    public Integer getSpvtype() {
        return spvtype;
    }

    public void setSpvtype(Integer spvtype) {
        this.spvtype = spvtype;
    }

    public String getSpvtypestr() {
        return spvtypestr;
    }

    public void setSpvtypestr(String spvtypestr) {
        this.spvtypestr = spvtypestr;
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