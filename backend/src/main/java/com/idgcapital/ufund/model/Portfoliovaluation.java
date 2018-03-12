package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Portfoliovaluation {

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private Integer valuationid;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private Date valuationdate;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private String fundid;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private String portfolioid;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private BigDecimal valuation;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private BigDecimal netvaluation;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private String rating;

    private String fundFamily;

    private String status;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private String valuationnote;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private String valuationmethod;

    private int pageIndex;

    private int pageCount;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    private BigDecimal revaluation;

    private BigDecimal gainslosses;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private String fundName;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private String portfolioName;

    private Integer cid;

    private Date taxotdate;

    @JsonView(AppView.ValuationList.class)
    private BigDecimal irr;

    private Integer referenceshare;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private String valuationtype;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private BigDecimal fairvalue;

    @JsonView({AppView.ValuationList.class, AppView.ValuationDetail.class})
    private BigDecimal prop;

    @JsonView({AppView.ValuationDetail.class})
    private BigDecimal discount;

    @JsonView({AppView.ValuationDetail.class})
    private BigDecimal fin48tax;

    @JsonView({AppView.ValuationDetail.class})
    private BigDecimal additional;

    @JsonView({AppView.ValuationDetail.class})
    private BigDecimal multiple;

    private BigDecimal optionproceed;

    private BigDecimal preferstockprefer;


    public BigDecimal getOptionproceed() {
        return optionproceed;
    }

    public void setOptionproceed(BigDecimal optionproceed) {
        this.optionproceed = optionproceed;
    }

    public BigDecimal getPreferstockprefer() {
        return preferstockprefer;
    }

    public void setPreferstockprefer(BigDecimal preferstockprefer) {
        this.preferstockprefer = preferstockprefer;
    }

    public BigDecimal getNetvaluation() {
        return netvaluation;
    }

    public void setNetvaluation(BigDecimal netvaluation) {
        this.netvaluation = netvaluation;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }

    public void setMultiple(BigDecimal multiple) {
        this.multiple = multiple;
    }

    private String fundIds;

    public String getFundIds() {
        return fundIds;
    }

    public void setFundIds(String fundIds) {
        this.fundIds = fundIds;
    }

    public BigDecimal getAdditional() {
        return additional;
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

    public String getFundFamily() {
        return fundFamily;
    }

    public void setFundFamily(String fundFamily) {
        this.fundFamily = fundFamily;
    }

    public void setAdditional(BigDecimal additional) {
        this.additional = additional;
    }

    private List<Portfoliocomparable> portfoliocomparables;

    private Map<String, List> checkQuery;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public Map<String, List> getCheckQuery() {
        return checkQuery;
    }

    public void setCheckQuery(Map<String, List> checkQuery) {
        this.checkQuery = checkQuery;
    }

    public List<Portfoliocomparable> getPortfoliocomparables() {
        return portfoliocomparables;
    }

    public void setPortfoliocomparables(List<Portfoliocomparable> portfoliocomparables) {
        this.portfoliocomparables = portfoliocomparables;
    }

    public Integer getValuationid() {
        return valuationid;
    }

    public void setValuationid(Integer valuationid) {
        this.valuationid = valuationid;
    }

    public Date getValuationdate() {
        return valuationdate;
    }

    public void setValuationdate(Date valuationdate) {
        this.valuationdate = valuationdate;
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid == null ? null : fundid.trim();
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public BigDecimal getValuation() {
        return valuation;
    }

    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getValuationnote() {
        return valuationnote;
    }

    public void setValuationnote(String valuationnote) {
        this.valuationnote = valuationnote == null ? null : valuationnote.trim();
    }

    public String getValuationmethod() {
        return valuationmethod;
    }

    public void setValuationmethod(String valuationmethod) {
        this.valuationmethod = valuationmethod == null ? null : valuationmethod.trim();
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

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public BigDecimal getRevaluation() {
        return revaluation;
    }

    public void setRevaluation(BigDecimal revaluation) {
        this.revaluation = revaluation;
    }

    public BigDecimal getGainslosses() {
        return gainslosses;
    }

    public void setGainslosses(BigDecimal gainslosses) {
        this.gainslosses = gainslosses;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getTaxotdate() {
        return taxotdate;
    }

    public void setTaxotdate(Date taxotdate) {
        this.taxotdate = taxotdate;
    }

    public BigDecimal getIrr() {
        return irr;
    }

    public void setIrr(BigDecimal irr) {
        this.irr = irr;
    }

    public Integer getReferenceshare() {
        return referenceshare;
    }

    public void setReferenceshare(Integer referenceshare) {
        this.referenceshare = referenceshare;
    }

    public String getValuationtype() {
        return valuationtype;
    }

    public void setValuationtype(String valuationtype) {
        this.valuationtype = valuationtype == null ? null : valuationtype.trim();
    }

    public BigDecimal getFairvalue() {
        return fairvalue;
    }

    public void setFairvalue(BigDecimal fairvalue) {
        this.fairvalue = fairvalue;
    }

    public BigDecimal getProp() {
        return prop;
    }

    public void setProp(BigDecimal prop) {
        this.prop = prop;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getFin48tax() {
        return fin48tax;
    }

    public void setFin48tax(BigDecimal fin48tax) {
        this.fin48tax = fin48tax;
    }
}