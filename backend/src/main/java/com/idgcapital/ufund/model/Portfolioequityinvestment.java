package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Portfolioequityinvestment implements Cloneable {
    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String eiid;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String portfolioid;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String fundid;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String investtype;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Date closedate;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Date termsigndate;

//    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Date maxtermsigndate;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Date maxclosedate;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String round;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Integer securitytypeid;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String securitytypeidstr;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Long shareownedno;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal conversionratio;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal cost;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal otherfees;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String remarks;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal costrelization;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal proceeds;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String currency;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Date taxlotdate;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String vouncher;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal convertamount;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal additionalcost;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private BigDecimal otherproceeds;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String fundname;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String fundfamillyname;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Date convertdate;

    private String fromeiid;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private String convertfromwarrantid;

    private BigDecimal value;

    private String comments;

    private String convertremark;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private Double sharesplitrate;

    private BigDecimal pic;

    private String convertfromnoteid;

    private String converttonoteid;

    private String warrantid;

    private String liquidationpreference;

    private String redemption;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private Boolean approved;

    private BigDecimal einterestpercentage;

    private BigDecimal investmentamount;

    private BigDecimal amounttofund;

    private Integer trans;

    private BigDecimal premoney;

    private BigDecimal currentround;

    private BigDecimal postmoney;

    private Double usdRmbRate;

    private Date lastupdatedate;

    private Integer rid;

    private Integer id;

    private Long convertequityamount;

    private String convertfundid;

    private Date financialdate;

    private BigDecimal tax;

    private BigDecimal futureproceeds;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private List<Portfolioloantoequity> portfolioloantoequityList;

//    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private List<Portfoliosharestructure> portfoliosharestructureList;

    @JsonView({AppView.PortfolioEquityInvestmentList.class,AppView.PortfolioEquityInvestmentDetail.class})
    private List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList;


    @Override
    public Object clone() {
        Portfolioequityinvestment investment = null;
        try{
            investment = (Portfolioequityinvestment)super.clone();
            investment.portfolioloantoequityList = new ArrayList<>();
            if(this.portfolioloantoequityList != null){
                for(Portfolioloantoequity loanToEquity:this.portfolioloantoequityList){
                    investment.portfolioloantoequityList.add((Portfolioloantoequity) loanToEquity.clone());
                }
            }
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return investment;
    }


    public String getEiid() {
        return eiid;
    }

    public void setEiid(String eiid) {
        this.eiid = eiid == null ? null : eiid.trim();
    }

    public String getFromeiid() {
        return fromeiid;
    }

    public void setFromeiid(String fromeiid) {
        this.fromeiid = fromeiid == null ? null : fromeiid.trim();
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid == null ? null : fundid.trim();
    }

    public String getInvesttype() {
        return investtype;
    }

    public void setInvesttype(String investtype) {
        this.investtype = investtype == null ? null : investtype.trim();
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round == null ? null : round.trim();
    }

    public Integer getSecuritytypeid() {
        return securitytypeid;
    }

    public void setSecuritytypeid(Integer securitytypeid) {
        this.securitytypeid = securitytypeid;
    }

    public Long getShareownedno() {
        return shareownedno;
    }

    public void setShareownedno(Long shareownedno) {
        this.shareownedno = shareownedno;
    }

    public BigDecimal getConversionratio() {
        return conversionratio;
    }

    public void setConversionratio(BigDecimal conversionratio) {
        this.conversionratio = conversionratio;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getOtherfees() {
        return otherfees;
    }

    public void setOtherfees(BigDecimal otherfees) {
        this.otherfees = otherfees;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getConvertremark() {
        return convertremark;
    }

    public void setConvertremark(String convertremark) {
        this.convertremark = convertremark == null ? null : convertremark.trim();
    }

    public BigDecimal getCostrelization() {
        return costrelization;
    }

    public void setCostrelization(BigDecimal costrelization) {
        this.costrelization = costrelization;
    }

    public BigDecimal getProceeds() {
        return proceeds;
    }

    public void setProceeds(BigDecimal proceeds) {
        this.proceeds = proceeds;
    }

    public BigDecimal getOtherproceeds() {
        return otherproceeds;
    }

    public void setOtherproceeds(BigDecimal otherproceeds) {
        this.otherproceeds = otherproceeds;
    }

    public Double getSharesplitrate() {
        return sharesplitrate;
    }

    public void setSharesplitrate(Double sharesplitrate) {
        this.sharesplitrate = sharesplitrate;
    }

    public BigDecimal getPic() {
        return pic;
    }

    public void setPic(BigDecimal pic) {
        this.pic = pic;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getConvertfromnoteid() {
        return convertfromnoteid;
    }

    public void setConvertfromnoteid(String convertfromnoteid) {
        this.convertfromnoteid = convertfromnoteid == null ? null : convertfromnoteid.trim();
    }

    public BigDecimal getConvertamount() {
        return convertamount;
    }

    public void setConvertamount(BigDecimal convertamount) {
        this.convertamount = convertamount;
    }

    public String getConverttonoteid() {
        return converttonoteid;
    }

    public void setConverttonoteid(String converttonoteid) {
        this.converttonoteid = converttonoteid == null ? null : converttonoteid.trim();
    }

    public String getWarrantid() {
        return warrantid;
    }

    public void setWarrantid(String warrantid) {
        this.warrantid = warrantid == null ? null : warrantid.trim();
    }

    public String getLiquidationpreference() {
        return liquidationpreference;
    }

    public void setLiquidationpreference(String liquidationpreference) {
        this.liquidationpreference = liquidationpreference == null ? null : liquidationpreference.trim();
    }

    public String getRedemption() {
        return redemption;
    }

    public void setRedemption(String redemption) {
        this.redemption = redemption == null ? null : redemption.trim();
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

    public BigDecimal getEinterestpercentage() {
        return einterestpercentage;
    }

    public void setEinterestpercentage(BigDecimal einterestpercentage) {
        this.einterestpercentage = einterestpercentage;
    }

    public BigDecimal getInvestmentamount() {
        return investmentamount;
    }

    public void setInvestmentamount(BigDecimal investmentamount) {
        this.investmentamount = investmentamount;
    }

    public BigDecimal getAmounttofund() {
        return amounttofund;
    }

    public void setAmounttofund(BigDecimal amounttofund) {
        this.amounttofund = amounttofund;
    }

    public Date getTaxlotdate() {
        return taxlotdate;
    }

    public void setTaxlotdate(Date taxlotdate) {
        this.taxlotdate = taxlotdate;
    }

    public String getVouncher() {
        return vouncher;
    }

    public void setVouncher(String vouncher) {
        this.vouncher = vouncher == null ? null : vouncher.trim();
    }

    public Integer getTrans() {
        return trans;
    }

    public void setTrans(Integer trans) {
        this.trans = trans;
    }

    public BigDecimal getPremoney() {
        return premoney;
    }

    public void setPremoney(BigDecimal premoney) {
        this.premoney = premoney;
    }

    public BigDecimal getCurrentround() {
        return currentround;
    }

    public void setCurrentround(BigDecimal currentround) {
        this.currentround = currentround;
    }

    public BigDecimal getPostmoney() {
        return postmoney;
    }

    public void setPostmoney(BigDecimal postmoney) {
        this.postmoney = postmoney;
    }

    public Double getUsdRmbRate() {
        return usdRmbRate;
    }

    public void setUsdRmbRate(Double usdRmbRate) {
        this.usdRmbRate = usdRmbRate;
    }

    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getConvertequityamount() {
        return convertequityamount;
    }

    public void setConvertequityamount(Long convertequityamount) {
        this.convertequityamount = convertequityamount;
    }

    public String getConvertfundid() {
        return convertfundid;
    }

    public void setConvertfundid(String convertfundid) {
        this.convertfundid = convertfundid == null ? null : convertfundid.trim();
    }

    public Date getFinancialdate() {
        return financialdate;
    }

    public void setFinancialdate(Date financialdate) {
        this.financialdate = financialdate;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getFutureproceeds() {
        return futureproceeds;
    }

    public void setFutureproceeds(BigDecimal futureproceeds) {
        this.futureproceeds = futureproceeds;
    }

    public BigDecimal getAdditionalcost() {
        return additionalcost;
    }

    public void setAdditionalcost(BigDecimal additionalcost) {
        this.additionalcost = additionalcost;
    }

    public String getSecuritytypeidstr() {
        return securitytypeidstr;
    }

    public void setSecuritytypeidstr(String securitytypeidstr) {
        this.securitytypeidstr = securitytypeidstr;
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }

    public String getFundfamillyname() {
        return fundfamillyname;
    }

    public void setFundfamillyname(String fundfamillyname) {
        this.fundfamillyname = fundfamillyname;
    }

    public List<Portfolioloantoequity> getPortfolioloantoequityList() {
        return portfolioloantoequityList;
    }

    public void setPortfolioloantoequityList(List<Portfolioloantoequity> portfolioloantoequityList) {
        this.portfolioloantoequityList = portfolioloantoequityList;
    }

    public List<Portfoliosharestructure> getPortfoliosharestructureList() {
        return portfoliosharestructureList;
    }

    public void setPortfoliosharestructureList(List<Portfoliosharestructure> portfoliosharestructureList) {
        this.portfoliosharestructureList = portfoliosharestructureList;
    }

    public Date getTermsigndate() {
        return termsigndate;
    }

    public void setTermsigndate(Date termsigndate) {
        this.termsigndate = termsigndate;
    }

    public List<Portfoliocaptablevaluedetail> getPortfoliocaptablevaluedetailList() {
        return portfoliocaptablevaluedetailList;
    }

    public void setPortfoliocaptablevaluedetailList(List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList) {
        this.portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailList;
    }

    public Date getMaxtermsigndate() {
        return maxtermsigndate;
    }

    public void setMaxtermsigndate(Date maxtermsigndate) {
        this.maxtermsigndate = maxtermsigndate;
    }

    public Date getMaxclosedate() {
        return maxclosedate;
    }

    public void setMaxclosedate(Date maxclosedate) {
        this.maxclosedate = maxclosedate;
    }

    public Date getConvertdate() {
        return convertdate;
    }

    public void setConvertdate(Date convertdate) {
        this.convertdate = convertdate;
    }

    public String getConvertfromwarrantid() {
        return convertfromwarrantid;
    }

    public void setConvertfromwarrantid(String convertfromwarrantid) {
        this.convertfromwarrantid = convertfromwarrantid;
    }

}