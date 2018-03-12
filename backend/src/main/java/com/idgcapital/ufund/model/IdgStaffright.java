package com.idgcapital.ufund.model;

import java.util.Date;

public class IdgStaffright {
    private Integer rightid;

    private String staffid;

    private String page;

    private Boolean viewright;

    private Boolean addright;

    private Boolean editright;

    private Boolean deleteright;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    public Integer getRightid() {
        return rightid;
    }

    public void setRightid(Integer rightid) {
        this.rightid = rightid;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page == null ? null : page.trim();
    }

    public Boolean getViewright() {
        return viewright;
    }

    public void setViewright(Boolean viewright) {
        this.viewright = viewright;
    }

    public Boolean getAddright() {
        return addright;
    }

    public void setAddright(Boolean addright) {
        this.addright = addright;
    }

    public Boolean getEditright() {
        return editright;
    }

    public void setEditright(Boolean editright) {
        this.editright = editright;
    }

    public Boolean getDeleteright() {
        return deleteright;
    }

    public void setDeleteright(Boolean deleteright) {
        this.deleteright = deleteright;
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
}