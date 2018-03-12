package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.util.Date;

public class Portfoliocaptablevaluedetail extends Portfoliocaptablevalue {

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private Integer detailcapid;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private Date closedate;

    @JsonView({AppView.PortfolioEquityInvestmentDetail.class,AppView.PortfolioExitDetail.class,AppView.PortfolioWarrantDetail.class,AppView.PortfolioDividendBonusDetail.class})
    private Date maxclosedate;

    private String bizid;


    public Integer getDetailcapid() {
        return detailcapid;
    }

    public void setDetailcapid(Integer detailcapid) {
        this.detailcapid = detailcapid;
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public Date getMaxclosedate() {
        return maxclosedate;
    }

    public void setMaxclosedate(Date maxclosedate) {
        this.maxclosedate = maxclosedate;
    }

    public String getBizid() {
        return bizid;
    }

    public void setBizid(String bizid) {
        this.bizid = bizid;
    }

}