package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

/**
 * Created by li_zhao on 2018/1/24.
 */
public class PortfolioexitShare {

    @JsonView({AppView.PortfolioExitShare.class})
    private String fundfamillyname;

    @JsonView({AppView.PortfolioExitShare.class})
    private String fundid;

    @JsonView({AppView.PortfolioExitShare.class})
    private String fundname;

    @JsonView({AppView.PortfolioExitShare.class})
    private String securitytype;

    @JsonView({AppView.PortfolioExitShare.class})
    private String portfolioid;

    @JsonView({AppView.PortfolioExitShare.class})
    private Integer securitytypeid;

    @JsonView({AppView.PortfolioExitShare.class})
    private String round;

    @JsonView({AppView.PortfolioExitShare.class})
    private Long shareremain;

    public String getFundfamillyname() {
        return fundfamillyname;
    }

    public void setFundfamillyname(String fundfamillyname) {
        this.fundfamillyname = fundfamillyname;
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid;
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }

    public String getSecuritytype() {
        return securitytype;
    }

    public void setSecuritytype(String securitytype) {
        this.securitytype = securitytype;
    }

    public Integer getSecuritytypeid() {
        return securitytypeid;
    }

    public void setSecuritytypeid(Integer securitytypeid) {
        this.securitytypeid = securitytypeid;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Long getShareremain() {
        return shareremain;
    }

    public void setShareremain(Long shareremain) {
        this.shareremain = shareremain;
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid;
    }
}
