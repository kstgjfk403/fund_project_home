package com.idgcapital.ufund.model;

import java.util.Date;

/**
 * Created by li_zhao on 2018/2/1.
 */
public class ProjReportInput {
    private String fundid;

    private Date closedate;

    private String portfolioid;

    private Integer year;

    private Date quarterdate;

    private String round;

    private Integer securityTypeId;

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid;
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getQuarterdate() {
        return quarterdate;
    }

    public void setQuarterdate(Date quarterdate) {
        this.quarterdate = quarterdate;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Integer getSecurityTypeId() {
        return securityTypeId;
    }

    public void setSecurityTypeId(Integer securityTypeId) {
        this.securityTypeId = securityTypeId;
    }
}
