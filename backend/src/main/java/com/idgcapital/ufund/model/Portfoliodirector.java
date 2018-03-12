package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.util.Date;

public class Portfoliodirector {
    @JsonView({AppView.PortfolioDirectorList.class,AppView.PortfolioDirectorDetail.class})
    private Integer directorid;

    @JsonView({AppView.PortfolioDirectorList.class,AppView.PortfolioDirectorDetail.class})
    private String staffid;

    @JsonView({AppView.PortfolioDirectorList.class,AppView.PortfolioDirectorDetail.class})
    private String staffidstr;

    @JsonView({AppView.PortfolioDirectorList.class,AppView.PortfolioDirectorDetail.class})
    private String portfolioid;

    @JsonView({AppView.PortfolioDirectorList.class,AppView.PortfolioDirectorDetail.class})
    private String directortype;

    @JsonView({AppView.PortfolioDirectorList.class,AppView.PortfolioDirectorDetail.class})
    private Date directorstartdate;

    @JsonView({AppView.PortfolioDirectorList.class,AppView.PortfolioDirectorDetail.class})
    private Date directorenddate;

    private Boolean approved;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    public Integer getDirectorid() {
        return directorid;
    }

    public void setDirectorid(Integer directorid) {
        this.directorid = directorid;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public String getDirectortype() {
        return directortype;
    }

    public void setDirectortype(String directortype) {
        this.directortype = directortype == null ? null : directortype.trim();
    }

    public Date getDirectorstartdate() {
        return directorstartdate;
    }

    public void setDirectorstartdate(Date directorstartdate) {
        this.directorstartdate = directorstartdate;
    }

    public Date getDirectorenddate() {
        return directorenddate;
    }

    public void setDirectorenddate(Date directorenddate) {
        this.directorenddate = directorenddate;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
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

    public String getStaffidstr() {
        return staffidstr;
    }

    public void setStaffidstr(String staffidstr) {
        this.staffidstr = staffidstr;
    }
}