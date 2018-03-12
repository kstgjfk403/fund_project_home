package com.idgcapital.ufund.model;

public class DdlGrid {
    private Integer gridid;

    private String gridname;

    private Integer pageid;

    private String description;

    private Boolean isenabled;

    public Integer getGridid() {
        return gridid;
    }

    public void setGridid(Integer gridid) {
        this.gridid = gridid;
    }

    public String getGridname() {
        return gridname;
    }

    public void setGridname(String gridname) {
        this.gridname = gridname == null ? null : gridname.trim();
    }

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getIsenabled() {
        return isenabled;
    }

    public void setIsenabled(Boolean isenabled) {
        this.isenabled = isenabled;
    }
}