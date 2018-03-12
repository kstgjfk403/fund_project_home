package com.idgcapital.ufund.model;

public class Distributionsource {
    private String lpdistributionid;

    private String sourcetype;

    private String sourceid;

    public String getLpdistributionid() {
        return lpdistributionid;
    }

    public void setLpdistributionid(String lpdistributionid) {
        this.lpdistributionid = lpdistributionid == null ? null : lpdistributionid.trim();
    }

    public String getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype == null ? null : sourcetype.trim();
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid == null ? null : sourceid.trim();
    }
}