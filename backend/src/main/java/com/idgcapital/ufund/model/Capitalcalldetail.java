package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;

public class Capitalcalldetail {
    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private Integer calldetailid;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private String callid;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private String lpid;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private String fundid;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private BigDecimal callednum;

    @JsonView({AppView.capitalCallLpDetail.class})
    private String createdby;

    @JsonView({AppView.capitalCallLpDetail.class})
    private Date createddate;

    @JsonView({AppView.capitalCallLpDetail.class})
    private String modifiedby;

    @JsonView({AppView.capitalCallLpDetail.class})
    private Date modifieddate;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private Boolean emailsend;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private Date emailsenddatetime;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private Boolean moneyreceived;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private Date receivedate;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private Boolean erisa;

    @JsonView({AppView.capitalCallLpDetail.class})
    private String emailto;

    @JsonView({AppView.capitalCallLpDetail.class})
    private String emailbc;

    @JsonView({AppView.capitalCallLpDetail.class})
    private String emailcc;

    @JsonView({AppView.capitalCallLpDetail.class})
    private String subject;

    @JsonView({AppView.capitalCallLpDetail.class})
    private String emailcontent;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private String attachment;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private Boolean emailreviewed;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private BigDecimal receivenum;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private BigDecimal totalcommentnum;

    private int pageIndex;

    private int pageCount;

    private int pageEnd;

    private int pageStart;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private String lpFamily;

    @JsonView({AppView.capitalCallLpList.class,AppView.capitalCallLpDetail.class})
    private String lpLegalFullName;

    public Integer getCalldetailid() {
        return calldetailid;
    }

    public void setCalldetailid(Integer calldetailid) {
        this.calldetailid = calldetailid;
    }

    public String getCallid() {
        return callid;
    }

    public void setCallid(String callid) {
        this.callid = callid == null ? null : callid.trim();
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

    public BigDecimal getCallednum() {
        return callednum;
    }

    public void setCallednum(BigDecimal callednum) {
        this.callednum = callednum;
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

    public Boolean getMoneyreceived() {
        return moneyreceived;
    }

    public void setMoneyreceived(Boolean moneyreceived) {
        this.moneyreceived = moneyreceived;
    }

    public Date getReceivedate() {
        return receivedate;
    }

    public void setReceivedate(Date receivedate) {
        this.receivedate = receivedate;
    }

    public Boolean getErisa() {
        return erisa;
    }

    public void setErisa(Boolean erisa) {
        this.erisa = erisa;
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

    public Boolean getEmailreviewed() {
        return emailreviewed;
    }

    public void setEmailreviewed(Boolean emailreviewed) {
        this.emailreviewed = emailreviewed;
    }

    public BigDecimal getReceivenum() {
        return receivenum;
    }

    public void setReceivenum(BigDecimal receivenum) {
        this.receivenum = receivenum;
    }

    public BigDecimal getTotalcommentnum() {
        return totalcommentnum;
    }

    public void setTotalcommentnum(BigDecimal totalcommentnum) {
        this.totalcommentnum = totalcommentnum;
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

    public String getLpLegalFullName() {
        return lpLegalFullName;
    }

    public void setLpLegalFullName(String lpLegalFullName) {
        this.lpLegalFullName = lpLegalFullName;
    }

    public String getLpFamily() {
        return lpFamily;
    }

    public void setLpFamily(String lpFamily) {
        this.lpFamily = lpFamily;
    }
}