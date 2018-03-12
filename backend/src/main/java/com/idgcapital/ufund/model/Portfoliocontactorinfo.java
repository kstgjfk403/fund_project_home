package com.idgcapital.ufund.model;

import java.util.Date;

public class Portfoliocontactorinfo {
    private Integer contactid;

    private String portfolioid;

    private String firstnameeng;

    private String lastnameeng;

    private String firstnamechi;

    private String lastnamechi;

    private String title;

    private String email;

    private String officeno;

    private String mobileno;

    private String faxno;

    private String address;

    private String nickname;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private String emailcontactortype;

    private Boolean approved;

    private String status;

    private String preferredlanguage;

    private String financialcollection;

    private String businessupdate;

    private String auditconfirmation;

    private Boolean ccpm;

    private String quarterlyemail;

    public Integer getContactid() {
        return contactid;
    }

    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public String getFirstnameeng() {
        return firstnameeng;
    }

    public void setFirstnameeng(String firstnameeng) {
        this.firstnameeng = firstnameeng == null ? null : firstnameeng.trim();
    }

    public String getLastnameeng() {
        return lastnameeng;
    }

    public void setLastnameeng(String lastnameeng) {
        this.lastnameeng = lastnameeng == null ? null : lastnameeng.trim();
    }

    public String getFirstnamechi() {
        return firstnamechi;
    }

    public void setFirstnamechi(String firstnamechi) {
        this.firstnamechi = firstnamechi == null ? null : firstnamechi.trim();
    }

    public String getLastnamechi() {
        return lastnamechi;
    }

    public void setLastnamechi(String lastnamechi) {
        this.lastnamechi = lastnamechi == null ? null : lastnamechi.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOfficeno() {
        return officeno;
    }

    public void setOfficeno(String officeno) {
        this.officeno = officeno == null ? null : officeno.trim();
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno == null ? null : mobileno.trim();
    }

    public String getFaxno() {
        return faxno;
    }

    public void setFaxno(String faxno) {
        this.faxno = faxno == null ? null : faxno.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
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

    public String getEmailcontactortype() {
        return emailcontactortype;
    }

    public void setEmailcontactortype(String emailcontactortype) {
        this.emailcontactortype = emailcontactortype == null ? null : emailcontactortype.trim();
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPreferredlanguage() {
        return preferredlanguage;
    }

    public void setPreferredlanguage(String preferredlanguage) {
        this.preferredlanguage = preferredlanguage == null ? null : preferredlanguage.trim();
    }

    public String getFinancialcollection() {
        return financialcollection;
    }

    public void setFinancialcollection(String financialcollection) {
        this.financialcollection = financialcollection == null ? null : financialcollection.trim();
    }

    public String getBusinessupdate() {
        return businessupdate;
    }

    public void setBusinessupdate(String businessupdate) {
        this.businessupdate = businessupdate == null ? null : businessupdate.trim();
    }

    public String getAuditconfirmation() {
        return auditconfirmation;
    }

    public void setAuditconfirmation(String auditconfirmation) {
        this.auditconfirmation = auditconfirmation == null ? null : auditconfirmation.trim();
    }

    public Boolean getCcpm() {
        return ccpm;
    }

    public void setCcpm(Boolean ccpm) {
        this.ccpm = ccpm;
    }

    public String getQuarterlyemail() {
        return quarterlyemail;
    }

    public void setQuarterlyemail(String quarterlyemail) {
        this.quarterlyemail = quarterlyemail == null ? null : quarterlyemail.trim();
    }
}