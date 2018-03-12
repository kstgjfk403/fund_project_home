package com.idgcapital.ufund.model;

import java.math.BigDecimal;
import java.util.Date;

public class Lpdistributiondetail {
    private Integer distributiondetailid;

    private String lpdistributionid;

    private Boolean emailsend;

    private Date emailsenddatetime;

    private String emailto;

    private String emailbc;

    private String emailcc;

    private String subject;

    private String emailcontent;

    private String attachment;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean emailreviewed;

    private BigDecimal distributionamount;

    private String lpid;

    private Boolean approved;

    private String fundid;

    private BigDecimal gpcarryamount;

    public Integer getDistributiondetailid() {
        return distributiondetailid;
    }

    public void setDistributiondetailid(Integer distributiondetailid) {
        this.distributiondetailid = distributiondetailid;
    }

    public String getLpdistributionid() {
        return lpdistributionid;
    }

    public void setLpdistributionid(String lpdistributionid) {
        this.lpdistributionid = lpdistributionid == null ? null : lpdistributionid.trim();
    }

    public Boolean getEmailsend() {
        return emailsend;
    }

    public void setEmailsend(Boolean emailsend) {
        this.emailsend = emailsend;
    }

    public Date getEmailsenddatetime() {
        return emailsenddatetime;
    }

    public void setEmailsenddatetime(Date emailsenddatetime) {
        this.emailsenddatetime = emailsenddatetime;
    }

    public String getEmailto() {
        return emailto;
    }

    public void setEmailto(String emailto) {
        this.emailto = emailto == null ? null : emailto.trim();
    }

    public String getEmailbc() {
        return emailbc;
    }

    public void setEmailbc(String emailbc) {
        this.emailbc = emailbc == null ? null : emailbc.trim();
    }

    public String getEmailcc() {
        return emailcc;
    }

    public void setEmailcc(String emailcc) {
        this.emailcc = emailcc == null ? null : emailcc.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getEmailcontent() {
        return emailcontent;
    }

    public void setEmailcontent(String emailcontent) {
        this.emailcontent = emailcontent == null ? null : emailcontent.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
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

    public Boolean getEmailreviewed() {
        return emailreviewed;
    }

    public void setEmailreviewed(Boolean emailreviewed) {
        this.emailreviewed = emailreviewed;
    }

    public BigDecimal getDistributionamount() {
        return distributionamount;
    }

    public void setDistributionamount(BigDecimal distributionamount) {
        this.distributionamount = distributionamount;
    }

    public String getLpid() {
        return lpid;
    }

    public void setLpid(String lpid) {
        this.lpid = lpid == null ? null : lpid.trim();
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid == null ? null : fundid.trim();
    }

    public BigDecimal getGpcarryamount() {
        return gpcarryamount;
    }

    public void setGpcarryamount(BigDecimal gpcarryamount) {
        this.gpcarryamount = gpcarryamount;
    }
}