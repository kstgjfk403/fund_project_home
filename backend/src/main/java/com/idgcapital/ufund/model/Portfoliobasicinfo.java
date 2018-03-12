package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.util.Date;

public class Portfoliobasicinfo {
    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String portfolioid;

    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String abbname;

    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String fullname;

    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String abbnamechi;

    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String fullnamechi;

    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String reportname;

    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String projectmanager;

//    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String privateorpublic;

    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private Boolean heldbyspvflag;

    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private Boolean idgonboardflag;

    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private Integer portfoliostatus;

    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String portfoliostatusstr;

    @JsonView(AppView.PortfolioDetail.class)
    private String heldbyspv;

    @JsonView(AppView.PortfolioDetail.class)
    private String idgboardrepresentative;

    @JsonView( AppView.PortfolioDetail.class  )
    private String idgboardrepresentativestr;

    @JsonView(AppView.PortfolioDetail.class)
    private String logo;

    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String projectmanagerstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private String idginhousecounsel;

    @JsonView( AppView.PortfolioDetail.class  )
    private String idginhousecounselstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private String projectbuddy;

    @JsonView( AppView.PortfolioDetail.class  )
    private String projectbuddystr;

    @JsonView( AppView.PortfolioDetail.class  )
    private Integer idgroleid;

    @JsonView( AppView.PortfolioDetail.class  )
    private String idgroleidstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private Integer stageid;

    @JsonView( AppView.PortfolioDetail.class  )
    private String stageidstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private Integer initialroleid;

    @JsonView( AppView.PortfolioDetail.class  )
    private String initialroleidstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private Integer firstinvestid;

    @JsonView( AppView.PortfolioDetail.class  )
    private String firstinvestidstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private Integer sectorid;

    @JsonView( AppView.PortfolioDetail.class  )
    private String sectoridstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private Integer subsectorid;

    @JsonView( AppView.PortfolioDetail.class  )
    private String subsectoridstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private Integer antidilutionid;

    @JsonView( AppView.PortfolioDetail.class  )
    private String antidilutionidstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private Integer locationid;

    @JsonView( AppView.PortfolioDetail.class  )
    private String locationidstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private Integer formid;

    @JsonView( AppView.PortfolioDetail.class  )
    private String formidstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private Integer stockexchangeid;

    @JsonView( AppView.PortfolioDetail.class  )
    private String stockexchangeidstr;

    @JsonView( AppView.PortfolioDetail.class  )
    private String onelinedesc;

    @JsonView( AppView.PortfolioDetail.class  )
    private String onelinedeschi;

    @JsonView( AppView.PortfolioDetail.class  )
    private String website;

    @JsonView( AppView.PortfolioDetail.class  )
    private String headcount;

    @JsonView( AppView.PortfolioDetail.class  )
    private String foundeddate;

    @JsonView( AppView.PortfolioDetail.class  )
    private String registerno;

    @JsonView( AppView.PortfolioDetail.class  )
    private String liquidationpreference;

    @JsonView( AppView.PortfolioDetail.class  )
    private String telephone;

    @JsonView( AppView.PortfolioDetail.class  )
    private String fax;

    @JsonView( AppView.PortfolioDetail.class  )
    private String address;

    @JsonView( AppView.PortfolioDetail.class  )
    private String addresschi;

    @JsonView( AppView.PortfolioDetail.class  )
    private String adrratio;

    @JsonView( AppView.PortfolioDetail.class  )
    private String lockupperiod;

    @JsonView( AppView.PortfolioDetail.class  )
    private Date lockupexpireddate;

    @JsonView( AppView.PortfolioDetail.class  )
    private String stockcode;

    @JsonView( AppView.PortfolioDetail.class  )
    private String spvjurisdition;

//    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String createdby;

//    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private Date createddate;

//    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private String modifiedby;

//    @JsonView({AppView.PortfolioList.class,AppView.PortfolioDetail.class})
    private Date modifieddate;

    @JsonView(AppView.PortfolioDetail.class)
    private String ceo;

    @JsonView(AppView.PortfolioDetail.class)
    private String product;

    @JsonView(AppView.PortfolioDetail.class)
    private String primarybusiness;

    @JsonView(AppView.PortfolioDetail.class)
    private String ytd;

    @JsonView(AppView.PortfolioDetail.class)
    private Boolean approved;

//    @JsonView(AppView.PortfolioDetail.class)
    private String directors;

    @JsonView(AppView.PortfolioDetail.class)
    private Integer idgboardseatno;

    @JsonView(AppView.PortfolioDetail.class)
    private Integer totalboardseatno;

    @JsonView(AppView.PortfolioDetail.class)
    private Boolean fundoffund;

    @JsonView(AppView.PortfolioDetail.class)
    private String remarkes;

    @JsonView(AppView.PortfolioDetail.class)
    private Date portfoliostatusdate;

    @JsonView(AppView.PortfolioDetail.class)
    private String auditconfaddress;

    @JsonView(AppView.PortfolioDetail.class)
    private String auditconfaddress2;

    @JsonView(AppView.PortfolioDetail.class)
    private String auditconfaddress3;

    @JsonView(AppView.PortfolioDetail.class)
    private String nomineename;

    @JsonView(AppView.PortfolioDetail.class)
    private String iceforceid;

    @JsonView(AppView.PortfolioDetail.class)
    private String observer;

    @JsonView(AppView.PortfolioDetail.class)
    private String observerstr;

    @JsonView(AppView.PortfolioDetail.class)
    private String memo;

    private String validFlag;

    private int pageIndex;

    private int pageCount;

    private int pageEnd;

    private int pageStart;

    private String lastportfoliostatus;

    private Date lastportfoliostatusdate;

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getPortfolioid() {
        return portfolioid;
    }

    public void setPortfolioid(String portfolioid) {
        this.portfolioid = portfolioid == null ? null : portfolioid.trim();
    }

    public String getAbbname() {
        return abbname;
    }

    public void setAbbname(String abbname) {
        this.abbname = abbname == null ? null : abbname.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getAbbnamechi() {
        return abbnamechi;
    }

    public void setAbbnamechi(String abbnamechi) {
        this.abbnamechi = abbnamechi == null ? null : abbnamechi.trim();
    }

    public String getFullnamechi() {
        return fullnamechi;
    }

    public void setFullnamechi(String fullnamechi) {
        this.fullnamechi = fullnamechi == null ? null : fullnamechi.trim();
    }

    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname == null ? null : reportname.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getProjectmanager() {
        return projectmanager;
    }

    public void setProjectmanager(String projectmanager) {
        this.projectmanager = projectmanager == null ? null : projectmanager.trim();
    }

    public String getProjectbuddy() {
        return projectbuddy;
    }

    public void setProjectbuddy(String projectbuddy) {
        this.projectbuddy = projectbuddy == null ? null : projectbuddy.trim();
    }

    public Integer getIdgroleid() {
        return idgroleid;
    }

    public void setIdgroleid(Integer idgroleid) {
        this.idgroleid = idgroleid;
    }

    public Integer getStageid() {
        return stageid;
    }

    public void setStageid(Integer stageid) {
        this.stageid = stageid;
    }

    public Integer getInitialroleid() {
        return initialroleid;
    }

    public void setInitialroleid(Integer initialroleid) {
        this.initialroleid = initialroleid;
    }

    public Integer getFirstinvestid() {
        return firstinvestid;
    }

    public void setFirstinvestid(Integer firstinvestid) {
        this.firstinvestid = firstinvestid;
    }

    public Integer getSectorid() {
        return sectorid;
    }

    public void setSectorid(Integer sectorid) {
        this.sectorid = sectorid;
    }

    public Integer getSubsectorid() {
        return subsectorid;
    }

    public void setSubsectorid(Integer subsectorid) {
        this.subsectorid = subsectorid;
    }

    public String getOnelinedesc() {
        return onelinedesc;
    }

    public void setOnelinedesc(String onelinedesc) {
        this.onelinedesc = onelinedesc == null ? null : onelinedesc.trim();
    }

    public String getOnelinedeschi() {
        return onelinedeschi;
    }

    public void setOnelinedeschi(String onelinedeschi) {
        this.onelinedeschi = onelinedeschi == null ? null : onelinedeschi.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getHeadcount() {
        return headcount;
    }

    public void setHeadcount(String headcount) {
        this.headcount = headcount == null ? null : headcount.trim();
    }

    public Integer getAntidilutionid() {
        return antidilutionid;
    }

    public void setAntidilutionid(Integer antidilutionid) {
        this.antidilutionid = antidilutionid;
    }

    public String getIdginhousecounsel() {
        return idginhousecounsel;
    }

    public void setIdginhousecounsel(String idginhousecounsel) {
        this.idginhousecounsel = idginhousecounsel == null ? null : idginhousecounsel.trim();
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public String getFoundeddate() {
        return foundeddate;
    }

    public void setFoundeddate(String foundeddate) {
        this.foundeddate = foundeddate == null ? null : foundeddate.trim();
    }

    public String getRegisterno() {
        return registerno;
    }

    public void setRegisterno(String registerno) {
        this.registerno = registerno == null ? null : registerno.trim();
    }

    public Integer getFormid() {
        return formid;
    }

    public void setFormid(Integer formid) {
        this.formid = formid;
    }

    public String getIdgboardrepresentative() {
        return idgboardrepresentative;
    }

    public void setIdgboardrepresentative(String idgboardrepresentative) {
        this.idgboardrepresentative = idgboardrepresentative == null ? null : idgboardrepresentative.trim();
    }

    public String getLiquidationpreference() {
        return liquidationpreference;
    }

    public void setLiquidationpreference(String liquidationpreference) {
        this.liquidationpreference = liquidationpreference == null ? null : liquidationpreference.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAddresschi() {
        return addresschi;
    }

    public void setAddresschi(String addresschi) {
        this.addresschi = addresschi == null ? null : addresschi.trim();
    }

    public String getPrivateorpublic() {
        return privateorpublic;
    }

    public void setPrivateorpublic(String privateorpublic) {
        this.privateorpublic = privateorpublic == null ? null : privateorpublic.trim();
    }

    public String getAdrratio() {
        return adrratio;
    }

    public void setAdrratio(String adrratio) {
        this.adrratio = adrratio == null ? null : adrratio.trim();
    }

    public String getLockupperiod() {
        return lockupperiod;
    }

    public void setLockupperiod(String lockupperiod) {
        this.lockupperiod = lockupperiod == null ? null : lockupperiod.trim();
    }

    public Date getLockupexpireddate() {
        return lockupexpireddate;
    }

    public void setLockupexpireddate(Date lockupexpireddate) {
        this.lockupexpireddate = lockupexpireddate;
    }

    public Integer getStockexchangeid() {
        return stockexchangeid;
    }

    public void setStockexchangeid(Integer stockexchangeid) {
        this.stockexchangeid = stockexchangeid;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode == null ? null : stockcode.trim();
    }

    public String getSpvjurisdition() {
        return spvjurisdition;
    }

    public void setSpvjurisdition(String spvjurisdition) {
        this.spvjurisdition = spvjurisdition == null ? null : spvjurisdition.trim();
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

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo == null ? null : ceo.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getPrimarybusiness() {
        return primarybusiness;
    }

    public void setPrimarybusiness(String primarybusiness) {
        this.primarybusiness = primarybusiness == null ? null : primarybusiness.trim();
    }

    public String getYtd() {
        return ytd;
    }

    public void setYtd(String ytd) {
        this.ytd = ytd == null ? null : ytd.trim();
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors == null ? null : directors.trim();
    }

    public Integer getIdgboardseatno() {
        return idgboardseatno;
    }

    public void setIdgboardseatno(Integer idgboardseatno) {
        this.idgboardseatno = idgboardseatno;
    }

    public Integer getTotalboardseatno() {
        return totalboardseatno;
    }

    public void setTotalboardseatno(Integer totalboardseatno) {
        this.totalboardseatno = totalboardseatno;
    }

    public Boolean getFundoffund() {
        return fundoffund;
    }

    public void setFundoffund(Boolean fundoffund) {
        this.fundoffund = fundoffund;
    }

    public String getRemarkes() {
        return remarkes;
    }

    public void setRemarkes(String remarkes) {
        this.remarkes = remarkes == null ? null : remarkes.trim();
    }

    public Date getPortfoliostatusdate() {
        return portfoliostatusdate;
    }

    public void setPortfoliostatusdate(Date portfoliostatusdate) {
        this.portfoliostatusdate = portfoliostatusdate;
    }

    public String getAuditconfaddress() {
        return auditconfaddress;
    }

    public void setAuditconfaddress(String auditconfaddress) {
        this.auditconfaddress = auditconfaddress == null ? null : auditconfaddress.trim();
    }

    public String getAuditconfaddress2() {
        return auditconfaddress2;
    }

    public void setAuditconfaddress2(String auditconfaddress2) {
        this.auditconfaddress2 = auditconfaddress2 == null ? null : auditconfaddress2.trim();
    }

    public String getAuditconfaddress3() {
        return auditconfaddress3;
    }

    public void setAuditconfaddress3(String auditconfaddress3) {
        this.auditconfaddress3 = auditconfaddress3 == null ? null : auditconfaddress3.trim();
    }

    public String getNomineename() {
        return nomineename;
    }

    public void setNomineename(String nomineename) {
        this.nomineename = nomineename == null ? null : nomineename.trim();
    }

    public String getIceforceid() {
        return iceforceid;
    }

    public void setIceforceid(String iceforceid) {
        this.iceforceid = iceforceid == null ? null : iceforceid.trim();
    }

    public String getProjectmanagerstr() {
        return projectmanagerstr;
    }

    public void setProjectmanagerstr(String projectmanagerstr) {
        this.projectmanagerstr = projectmanagerstr;
    }

    public String getProjectbuddystr() {
        return projectbuddystr;
    }

    public void setProjectbuddystr(String projectbuddystr) {
        this.projectbuddystr = projectbuddystr;
    }

    public String getIdginhousecounselstr() {
        return idginhousecounselstr;
    }

    public void setIdginhousecounselstr(String idginhousecounselstr) {
        this.idginhousecounselstr = idginhousecounselstr;
    }

    public String getIdgboardrepresentativestr() {
        return idgboardrepresentativestr;
    }

    public void setIdgboardrepresentativestr(String idgboardrepresentativestr) {
        this.idgboardrepresentativestr = idgboardrepresentativestr;
    }

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public String getHeldbyspv() {
        return heldbyspv;
    }

    public void setHeldbyspv(String heldbyspv) {
        this.heldbyspv = heldbyspv;
    }

    public Boolean getHeldbyspvflag() {
        return heldbyspvflag;
    }

    public void setHeldbyspvflag(Boolean heldbyspvflag) {
        this.heldbyspvflag = heldbyspvflag;
    }

    public Boolean getIdgonboardflag() {
        return idgonboardflag;
    }

    public void setIdgonboardflag(Boolean idgonboardflag) {
        this.idgonboardflag = idgonboardflag;
    }

    public Integer getPortfoliostatus() {
        return portfoliostatus;
    }

    public void setPortfoliostatus(Integer portfoliostatus) {
        this.portfoliostatus = portfoliostatus;
    }

    public String getPortfoliostatusstr() {
        return portfoliostatusstr;
    }

    public void setPortfoliostatusstr(String portfoliostatusstr) {
        this.portfoliostatusstr = portfoliostatusstr;
    }

    public String getIdgroleidstr() {
        return idgroleidstr;
    }

    public void setIdgroleidstr(String idgroleidstr) {
        this.idgroleidstr = idgroleidstr;
    }

    public String getStageidstr() {
        return stageidstr;
    }

    public void setStageidstr(String stageidstr) {
        this.stageidstr = stageidstr;
    }

    public String getInitialroleidstr() {
        return initialroleidstr;
    }

    public void setInitialroleidstr(String initialroleidstr) {
        this.initialroleidstr = initialroleidstr;
    }

    public String getFirstinvestidstr() {
        return firstinvestidstr;
    }

    public void setFirstinvestidstr(String firstinvestidstr) {
        this.firstinvestidstr = firstinvestidstr;
    }

    public String getSectoridstr() {
        return sectoridstr;
    }

    public void setSectoridstr(String sectoridstr) {
        this.sectoridstr = sectoridstr;
    }

    public String getSubsectoridstr() {
        return subsectoridstr;
    }

    public void setSubsectoridstr(String subsectoridstr) {
        this.subsectoridstr = subsectoridstr;
    }

    public String getAntidilutionidstr() {
        return antidilutionidstr;
    }

    public void setAntidilutionidstr(String antidilutionidstr) {
        this.antidilutionidstr = antidilutionidstr;
    }

    public String getLocationidstr() {
        return locationidstr;
    }

    public void setLocationidstr(String locationidstr) {
        this.locationidstr = locationidstr;
    }

    public String getFormidstr() {
        return formidstr;
    }

    public void setFormidstr(String formidstr) {
        this.formidstr = formidstr;
    }

    public String getStockexchangeidstr() {
        return stockexchangeidstr;
    }

    public void setStockexchangeidstr(String stockexchangeidstr) {
        this.stockexchangeidstr = stockexchangeidstr;
    }

    public String getObserverstr() {
        return observerstr;
    }

    public void setObserverstr(String observerstr) {
        this.observerstr = observerstr;
    }

    public String getLastportfoliostatus() {
        return lastportfoliostatus;
    }

    public void setLastportfoliostatus(String lastportfoliostatus) {
        this.lastportfoliostatus = lastportfoliostatus;
    }

    public Date getLastportfoliostatusdate() {
        return lastportfoliostatusdate;
    }

    public void setLastportfoliostatusdate(Date lastportfoliostatusdate) {
        this.lastportfoliostatusdate = lastportfoliostatusdate;
    }
}