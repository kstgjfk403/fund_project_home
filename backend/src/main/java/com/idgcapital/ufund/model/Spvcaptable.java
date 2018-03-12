package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.util.Date;

public class Spvcaptable {
    @JsonView({AppView.SpvInvestorDetail.class})
    private Integer capid;

    @JsonView({AppView.SpvInvestorDetail.class})
    private String spvid;

    @JsonView({AppView.SpvInvestorDetail.class})
    private String stockholdername;

    @JsonView({AppView.SpvInvestorDetail.class})
    private String stockholdertype;

    @JsonView({AppView.SpvInvestorDetail.class})
    private String stockholdertypestr;

    @JsonView({AppView.SpvInvestorDetail.class})
    private String type;

    @JsonView({AppView.SpvInvestorDetail.class})
    private String stockholderid;

    @JsonView({AppView.SpvInvestorDetail.class})
    private String stockholdercode;

    @JsonView({AppView.SpvInvestorDetail.class})
    private String lpaamt;

    @JsonView({AppView.SpvInvestorDetail.class})
    private String lpaamtprop;

    @JsonView({AppView.SpvInvestorDetail.class})
    private Date lpaamtdate;

    private Date capdate;

    @JsonView({AppView.SpvInvestorDetail.class})
    private String capdatestr;

    private String paidamt;

    private Date paidamtdate;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    public Integer getCapid() {
        return capid;
    }

    public void setCapid(Integer capid) {
        this.capid = capid;
    }

    public String getSpvid() {
        return spvid;
    }

    public void setSpvid(String spvid) {
        this.spvid = spvid == null ? null : spvid.trim();
    }

    public String getStockholdername() {
        return stockholdername;
    }

    public void setStockholdername(String stockholdername) {
        this.stockholdername = stockholdername == null ? null : stockholdername.trim();
    }

    public String getStockholdertype() {
        return stockholdertype;
    }

    public void setStockholdertype(String stockholdertype) {
        this.stockholdertype = stockholdertype == null ? null : stockholdertype.trim();
    }

    public String getStockholderid() {
        return stockholderid;
    }

    public void setStockholderid(String stockholderid) {
        this.stockholderid = stockholderid == null ? null : stockholderid.trim();
    }

    public String getStockholdercode() {
        return stockholdercode;
    }

    public void setStockholdercode(String stockholdercode) {
        this.stockholdercode = stockholdercode == null ? null : stockholdercode.trim();
    }

    public String getLpaamt() {
        return lpaamt;
    }

    public void setLpaamt(String lpaamt) {
        this.lpaamt = lpaamt == null ? null : lpaamt.trim();
    }

    public Date getLpaamtdate() {
        return lpaamtdate;
    }

    public void setLpaamtdate(Date lpaamtdate) {
        this.lpaamtdate = lpaamtdate;
    }

    public String getPaidamt() {
        return paidamt;
    }

    public void setPaidamt(String paidamt) {
        this.paidamt = paidamt == null ? null : paidamt.trim();
    }

    public Date getPaidamtdate() {
        return paidamtdate;
    }

    public void setPaidamtdate(Date paidamtdate) {
        this.paidamtdate = paidamtdate;
    }

    public Date getCapdate() {
        return capdate;
    }

    public void setCapdate(Date capdate) {
        this.capdate = capdate;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCapdatestr() {
        return capdatestr;
    }

    public void setCapdatestr(String capdatestr) {
        this.capdatestr = capdatestr;
    }

    public String getLpaamtprop() {
        return lpaamtprop;
    }

    public void setLpaamtprop(String lpaamtprop) {
        this.lpaamtprop = lpaamtprop;
    }

    public String getStockholdertypestr() {
        return stockholdertypestr;
    }

    public void setStockholdertypestr(String stockholdertypestr) {
        this.stockholdertypestr = stockholdertypestr;
    }
}