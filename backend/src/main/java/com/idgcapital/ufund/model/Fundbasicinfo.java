package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.math.BigDecimal;
import java.util.Date;

public class Fundbasicinfo {
    @JsonView({AppView.FundList.class,AppView.FundDetail.class,AppView.FundForQuery.class})
    private String fundid;

    @JsonView({AppView.FundList.class,AppView.FundDetail.class,AppView.FundForQuery.class})
    private String fundfamillyname;

    @JsonView({AppView.FundList.class,AppView.FundDetail.class,AppView.FundForQuery.class})
    private String fundname;

    @JsonView({AppView.FundList.class,AppView.FundDetail.class,AppView.FundForQuery.class})
    private String fundfullnameeng;

    @JsonView({AppView.FundList.class,AppView.FundDetail.class,AppView.FundForQuery.class})
    private String fundfullnamechi;

    @JsonView({AppView.FundList.class,AppView.FundDetail.class})
    private String fundtypeid;

    @JsonView({AppView.FundList.class,AppView.FundDetail.class})
    private String gpname;

    @JsonView({AppView.FundList.class,AppView.FundDetail.class})
    private String managfeedesc;

    @JsonView({AppView.FundList.class,AppView.FundDetail.class})
    private String capitalallocationsdesc;

    @JsonView({AppView.FundList.class,AppView.FundDetail.class})
    private BigDecimal totalcommitmentnum;

    private BigDecimal familytotalcommitmentnum;

    @JsonView(AppView.FundDetail.class)
    private String fundstatus;

    @JsonView(AppView.FundDetail.class)
    private Integer incorplocation;

    @JsonView(AppView.FundDetail.class)
    private String regaddress;

    @JsonView(AppView.FundDetail.class)
    private Date formationdate;

    @JsonView(AppView.FundDetail.class)
    private Date initiallpadate;

    @JsonView(AppView.FundDetail.class)
    private Date latestlpadate;

    @JsonView(AppView.FundDetail.class)
    private String mainindustry;

    @JsonView(AppView.FundDetail.class)
    private Date closedate;

    @JsonView(AppView.FundDetail.class)
    private String yearend;

    @JsonView(AppView.FundDetail.class)
    private String gaap;

    @JsonView(AppView.FundDetail.class)
    private String auditor;

    @JsonView(AppView.FundDetail.class)
    private String totalcommitmentcur;

    @JsonView(AppView.FundDetail.class)
    private String bankname;

    @JsonView(AppView.FundDetail.class)
    private String bankaccount;

    @JsonView(AppView.FundDetail.class)
    private String swiftcode;

    @JsonView(AppView.FundDetail.class)
    private String aba;

    @JsonView(AppView.FundDetail.class)
    private String attn;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    @JsonView(AppView.FundDetail.class)
    private String legaladvisor;

    @JsonView(AppView.FundDetail.class)
    private String taxrepresentative;


    @JsonView(AppView.FundDetail.class)
    private String magtcompany;

    @JsonView(AppView.FundDetail.class)
    private Integer magtcomincorporated;

    @JsonView(AppView.FundDetail.class)
    private Boolean fatca;

    @JsonView(AppView.FundDetail.class)
    private String fatcatype;

    @JsonView(AppView.FundDetail.class)
    private String fatcaofficer;

    @JsonView(AppView.FundDetail.class)
    private String giinNo;

    @JsonView(AppView.FundDetail.class)
    private String taxid;

    @JsonView(AppView.FundDetail.class)
    private BigDecimal totallpcommitnum;

    @JsonView(AppView.FundDetail.class)
    private BigDecimal totalgpcommitnum;

    private Boolean approved;

    @JsonView(AppView.FundDetail.class)
    private String relatefundid;

    @JsonView(AppView.FundDetail.class)
    private String fundwbname;

    @JsonView(AppView.FundDetail.class)
    private String fundmanager;

    @JsonView(AppView.FundDetail.class)
    private String financemanager;

    @JsonView(AppView.FundDetail.class)
    private String fundmanageremail;

    @JsonView(AppView.FundDetail.class)
    private String financemanageremail;

    @JsonView(AppView.FundDetail.class)
    private String bankaddress;

    private int pageIndex;

    private int pageCount;

    private int pageEnd;

    private int pageStart;

    private String validflag;

    private BigDecimal fundProp;

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid == null ? null : fundid.trim();
    }

    public String getFundfamillyname() {
        return fundfamillyname;
    }

    public void setFundfamillyname(String fundfamillyname) {
        this.fundfamillyname = fundfamillyname == null ? null : fundfamillyname.trim();
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname == null ? null : fundname.trim();
    }

    public String getFundfullnameeng() {
        return fundfullnameeng;
    }

    public void setFundfullnameeng(String fundfullnameeng) {
        this.fundfullnameeng = fundfullnameeng == null ? null : fundfullnameeng.trim();
    }

    public String getFundfullnamechi() {
        return fundfullnamechi;
    }

    public void setFundfullnamechi(String fundfullnamechi) {
        this.fundfullnamechi = fundfullnamechi == null ? null : fundfullnamechi.trim();
    }

    public String getFundtypeid() {
        return fundtypeid;
    }

    public void setFundtypeid(String fundtypeid) {
        this.fundtypeid = fundtypeid == null ? null : fundtypeid.trim();
    }

    public String getFundstatus() {
        return fundstatus;
    }

    public void setFundstatus(String fundstatus) {
        this.fundstatus = fundstatus == null ? null : fundstatus.trim();
    }

    public Integer getIncorplocation() {
        return incorplocation;
    }

    public void setIncorplocation(Integer incorplocation) {
        this.incorplocation = incorplocation;
    }

    public String getRegaddress() {
        return regaddress;
    }

    public void setRegaddress(String regaddress) {
        this.regaddress = regaddress == null ? null : regaddress.trim();
    }

    public Date getFormationdate() {
        return formationdate;
    }

    public void setFormationdate(Date formationdate) {
        this.formationdate = formationdate;
    }

    public Date getInitiallpadate() {
        return initiallpadate;
    }

    public void setInitiallpadate(Date initiallpadate) {
        this.initiallpadate = initiallpadate;
    }

    public Date getLatestlpadate() {
        return latestlpadate;
    }

    public void setLatestlpadate(Date latestlpadate) {
        this.latestlpadate = latestlpadate;
    }

    public String getMainindustry() {
        return mainindustry;
    }

    public void setMainindustry(String mainindustry) {
        this.mainindustry = mainindustry == null ? null : mainindustry.trim();
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public String getYearend() {
        return yearend;
    }

    public void setYearend(String yearend) {
        this.yearend = yearend == null ? null : yearend.trim();
    }

    public String getGaap() {
        return gaap;
    }

    public void setGaap(String gaap) {
        this.gaap = gaap == null ? null : gaap.trim();
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public BigDecimal getTotalcommitmentnum() {
        return totalcommitmentnum;
    }

    public void setTotalcommitmentnum(BigDecimal totalcommitmentnum) {
        this.totalcommitmentnum = totalcommitmentnum;
    }

    public String getTotalcommitmentcur() {
        return totalcommitmentcur;
    }

    public void setTotalcommitmentcur(String totalcommitmentcur) {
        this.totalcommitmentcur = totalcommitmentcur == null ? null : totalcommitmentcur.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount == null ? null : bankaccount.trim();
    }

    public String getSwiftcode() {
        return swiftcode;
    }

    public void setSwiftcode(String swiftcode) {
        this.swiftcode = swiftcode == null ? null : swiftcode.trim();
    }

    public String getAba() {
        return aba;
    }

    public void setAba(String aba) {
        this.aba = aba == null ? null : aba.trim();
    }

    public String getAttn() {
        return attn;
    }

    public void setAttn(String attn) {
        this.attn = attn == null ? null : attn.trim();
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

    public String getLegaladvisor() {
        return legaladvisor;
    }

    public void setLegaladvisor(String legaladvisor) {
        this.legaladvisor = legaladvisor == null ? null : legaladvisor.trim();
    }

    public String getTaxrepresentative() {
        return taxrepresentative;
    }

    public void setTaxrepresentative(String taxrepresentative) {
        this.taxrepresentative = taxrepresentative == null ? null : taxrepresentative.trim();
    }

    public String getGpname() {
        return gpname;
    }

    public void setGpname(String gpname) {
        this.gpname = gpname == null ? null : gpname.trim();
    }

    public String getMagtcompany() {
        return magtcompany;
    }

    public void setMagtcompany(String magtcompany) {
        this.magtcompany = magtcompany == null ? null : magtcompany.trim();
    }

    public Integer getMagtcomincorporated() {
        return magtcomincorporated;
    }

    public void setMagtcomincorporated(Integer magtcomincorporated) {
        this.magtcomincorporated = magtcomincorporated;
    }

    public Boolean getFatca() {
        return fatca;
    }

    public void setFatca(Boolean fatca) {
        this.fatca = fatca;
    }

    public String getFatcatype() {
        return fatcatype;
    }

    public void setFatcatype(String fatcatype) {
        this.fatcatype = fatcatype == null ? null : fatcatype.trim();
    }

    public String getFatcaofficer() {
        return fatcaofficer;
    }

    public void setFatcaofficer(String fatcaofficer) {
        this.fatcaofficer = fatcaofficer == null ? null : fatcaofficer.trim();
    }

    public String getGiinNo() {
        return giinNo;
    }

    public void setGiinNo(String giinNo) {
        this.giinNo = giinNo == null ? null : giinNo.trim();
    }

    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid == null ? null : taxid.trim();
    }

    public BigDecimal getTotallpcommitnum() {
        return totallpcommitnum;
    }

    public void setTotallpcommitnum(BigDecimal totallpcommitnum) {
        this.totallpcommitnum = totallpcommitnum;
    }

    public BigDecimal getTotalgpcommitnum() {
        return totalgpcommitnum;
    }

    public void setTotalgpcommitnum(BigDecimal totalgpcommitnum) {
        this.totalgpcommitnum = totalgpcommitnum;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getRelatefundid() {
        return relatefundid;
    }

    public void setRelatefundid(String relatefundid) {
        this.relatefundid = relatefundid == null ? null : relatefundid.trim();
    }

    public String getFundwbname() {
        return fundwbname;
    }

    public void setFundwbname(String fundwbname) {
        this.fundwbname = fundwbname == null ? null : fundwbname.trim();
    }

    public String getFundmanager() {
        return fundmanager;
    }

    public void setFundmanager(String fundmanager) {
        this.fundmanager = fundmanager == null ? null : fundmanager.trim();
    }

    public String getFinancemanager() {
        return financemanager;
    }

    public void setFinancemanager(String financemanager) {
        this.financemanager = financemanager == null ? null : financemanager.trim();
    }

    public String getFundmanageremail() {
        return fundmanageremail;
    }

    public void setFundmanageremail(String fundmanageremail) {
        this.fundmanageremail = fundmanageremail == null ? null : fundmanageremail.trim();
    }

    public String getFinancemanageremail() {
        return financemanageremail;
    }

    public void setFinancemanageremail(String financemanageremail) {
        this.financemanageremail = financemanageremail == null ? null : financemanageremail.trim();
    }

    public String getManagfeedesc() {
        return managfeedesc;
    }

    public void setManagfeedesc(String managfeedesc) {
        this.managfeedesc = managfeedesc == null ? null : managfeedesc.trim();
    }

    public String getCapitalallocationsdesc() {
        return capitalallocationsdesc;
    }

    public void setCapitalallocationsdesc(String capitalallocationsdesc) {
        this.capitalallocationsdesc = capitalallocationsdesc == null ? null : capitalallocationsdesc.trim();
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress == null ? null : bankaddress.trim();
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

    public String getValidflag() {
        return validflag;
    }

    public void setValidflag(String validflag) {
        this.validflag = validflag;
    }

    public BigDecimal getFamilytotalcommitmentnum() {
        return familytotalcommitmentnum;
    }

    public void setFamilytotalcommitmentnum(BigDecimal familytotalcommitmentnum) {
        this.familytotalcommitmentnum = familytotalcommitmentnum;
    }

    public BigDecimal getFundProp() {
        return fundProp;
    }

    public void setFundProp(BigDecimal fundProp) {
        this.fundProp = fundProp;
    }
}