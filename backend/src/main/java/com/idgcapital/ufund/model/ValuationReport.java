package com.idgcapital.ufund.model;

import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by yi_zhang on 2018/1/28.
 */
public class ValuationReport {


    private String name;
    private String portfolioFullName;
    private String website;
    private String fundName;
    private String fundFamilyName;
    private String img;

    private BigDecimal mainCost;
    private BigDecimal sideCost;
    private String description;

    private BigDecimal multiple;
    private BigDecimal highValuation;
    private BigDecimal lowValuation;

    private BigDecimal ownerShip;

    private BigDecimal carryCost;
    private BigDecimal carryValuation;
    private BigDecimal variation;

    private List<String> roundName;
    private List<Long> totalShare;
    private Map<String, Object> mainFund;
    private Map<String, Object> sideFund;

    private Portfoliovaluation portfoliovaluation;
    private List<Portfoliocomparable> portfoliocomparables;
    private Portfoliofinancialinfo portfoliofinancialinfo;
    private Portfoliofinancialinfo lastfinancialinfo;

    public String getPortfolioFullName() {
        return portfolioFullName;
    }

    public void setPortfolioFullName(String portfolioFullName) {
        this.portfolioFullName = portfolioFullName;
    }

    public List<Long> getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(List<Long> totalShare) {
        this.totalShare = totalShare;
    }

    public BigDecimal getOwnerShip() {
        return ownerShip;
    }

    public void setOwnerShip(BigDecimal ownerShip) {
        this.ownerShip = ownerShip;
    }

    public Map<String, Object> getMainFund() {
        return mainFund;
    }

    public void setMainFund(Map<String, Object> mainFund) {
        this.mainFund = mainFund;
    }

    public Map<String, Object> getSideFund() {
        return sideFund;
    }

    public void setSideFund(Map<String, Object> sideFund) {
        this.sideFund = sideFund;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }

    public BigDecimal getCarryCost() {
        return carryCost;
    }

    public void setCarryCost(BigDecimal carryCost) {
        this.carryCost = carryCost;
    }

    public BigDecimal getCarryValuation() {
        return carryValuation;
    }

    public void setCarryValuation(BigDecimal carryValuation) {
        this.carryValuation = carryValuation;
    }

    public BigDecimal getVariation() {
        return variation;
    }

    public void setVariation(BigDecimal variation) {
        this.variation = variation;
    }

    public void setMultiple(BigDecimal multiple) {
        this.multiple = multiple;
    }

    public BigDecimal getHighValuation() {
        return highValuation;
    }

    public void setHighValuation(BigDecimal highValuation) {
        this.highValuation = highValuation;
    }

    public BigDecimal getLowValuation() {
        return lowValuation;
    }

    public void setLowValuation(BigDecimal lowValuation) {
        this.lowValuation = lowValuation;
    }

    public Portfoliofinancialinfo getLastfinancialinfo() {
        return lastfinancialinfo;
    }

    public void setLastfinancialinfo(Portfoliofinancialinfo lastfinancialinfo) {
        this.lastfinancialinfo = lastfinancialinfo;
    }

    public List<String> getRoundName() {
        return roundName;
    }

    public void setRoundName(List<String> roundName) {
        this.roundName = roundName;
    }

    public String getFundFamilyName() {
        return fundFamilyName;
    }

    public void setFundFamilyName(String fundFamilyName) {
        this.fundFamilyName = fundFamilyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Portfoliovaluation getPortfoliovaluation() {
        return portfoliovaluation;
    }

    public void setPortfoliovaluation(Portfoliovaluation portfoliovaluation) {
        this.portfoliovaluation = portfoliovaluation;
    }

    public List<Portfoliocomparable> getPortfoliocomparables() {
        return portfoliocomparables;
    }

    public void setPortfoliocomparables(List<Portfoliocomparable> portfoliocomparables) {
        this.portfoliocomparables = portfoliocomparables;
    }

    public Portfoliofinancialinfo getPortfoliofinancialinfo() {
        return portfoliofinancialinfo;
    }

    public void setPortfoliofinancialinfo(Portfoliofinancialinfo portfoliofinancialinfo) {
        this.portfoliofinancialinfo = portfoliofinancialinfo;
    }

    public BigDecimal getMainCost() {
        return mainCost;
    }

    public void setMainCost(BigDecimal mainCost) {
        this.mainCost = mainCost;
    }

    public BigDecimal getSideCost() {
        return sideCost;
    }

    public void setSideCost(BigDecimal sideCost) {
        this.sideCost = sideCost;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
