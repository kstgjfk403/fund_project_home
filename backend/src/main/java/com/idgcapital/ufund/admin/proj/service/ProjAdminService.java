package com.idgcapital.ufund.admin.proj.service;

import com.idgcapital.ufund.common.constant.PortfolioMemberConstant;
import com.idgcapital.ufund.common.util.DateUtil;
import com.idgcapital.ufund.common.util.StringUtil;
import com.idgcapital.ufund.model.*;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.mybatis.dao.*;
import com.idgcapital.ufund.security.model.LoginUser;
import com.idgcapital.ufund.sys.service.SysBaseInfoService;
import com.idgcapital.ufund.sys.service.SysStaffService;
import org.codehaus.groovy.antlr.treewalker.PreOrderTraversal;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yi_zhang on 2017/12/8.
 */
@Service
public class ProjAdminService {
    @Autowired
    private PortfoliobasicinfoMapper portfoliobasicinfoMapper;
    @Autowired
    private PortfoliomemberMapper portfoliomemberMapper;

    @Autowired
    private SysStaffService sysStaffService;

    @Autowired
    private SysBaseInfoService sysBaseInfoService;

    @Autowired
    private PortfoliostatushistoryMapper portfoliostatushistoryMapper;

    @Autowired
    private PortfoliodirectorMapper portfoliodirectorMapper;


    public JsonResult queryProjInfos(Portfoliobasicinfo portfoliobasicinfo) {
        JsonResult jsonResult = new JsonResult();

        int pageStart = (portfoliobasicinfo.getPageIndex() - 1) * portfoliobasicinfo.getPageCount();
        int pageEnd = portfoliobasicinfo.getPageIndex() * portfoliobasicinfo.getPageCount();
        portfoliobasicinfo.setPageEnd(pageEnd);
        portfoliobasicinfo.setPageStart(pageStart);
        List<Portfoliobasicinfo> portfoliobasicinfoList = portfoliobasicinfoMapper.selectPortfolioList(portfoliobasicinfo);

        //获取董事席位，观察员席位
        for (Portfoliobasicinfo portfolio : portfoliobasicinfoList) {
            List<Portfoliodirector> portfoliodirectorList = queryIDGPortfolioDirectorList(portfolio.getPortfolioid(),new Date(),"director");
            if (portfoliodirectorList != null && portfoliodirectorList.size() > 0) {
                portfolio.setIdgonboardflag(true);
            }
        }


        //IDG_STAFF
        Map<String, Object> staff = sysStaffService.queryIdgStaffMap();

        //翻译
        for (Portfoliobasicinfo info : portfoliobasicinfoList) {
            //
            portfolioMemberFilter(info, PortfolioMemberConstant.PM, staff);
            //
            portfolioDDLFilter(info, "DDL_PortfolioStatus");
        }

        long count = portfoliobasicinfoMapper.selectPortfolioCount(portfoliobasicinfo);

        jsonResult.setData(portfoliobasicinfoList);
        jsonResult.setCount(count);

        return jsonResult;
    }

    public List<Portfoliobasicinfo> queryPortfolioByFundId(String id) {
        return portfoliobasicinfoMapper.queryPortfolioByFundId(id);
    }

    public BigDecimal queryPortfolioOwnerShip(Portfoliovaluation search) {
        BigDecimal totalShare = portfoliobasicinfoMapper.queryTotalShare(search);
        BigDecimal idgShare = portfoliobasicinfoMapper.queryIdgShare(search);
        return idgShare.divide(totalShare, 4, BigDecimal.ROUND_HALF_UP);
    }

    public Portfoliobasicinfo queryProjInfoById(String id) {
        //IDG_STAFF
        Map<String, Object> staff = sysStaffService.queryIdgStaffMap();

        Portfoliobasicinfo portfoliobasicinfo = portfoliobasicinfoMapper.selectByPrimaryKey(id);

        if (!StringUtil.isBlankOrNull(portfoliobasicinfo.getHeldbyspv())) {
            portfoliobasicinfo.setHeldbyspvflag(true);
        } else {
            portfoliobasicinfo.setHeldbyspvflag(false);
        }

        //获取董事席位，观察员席位
        String directorStr = "";
        List<Portfoliodirector> portfoliodirectorList = queryIDGPortfolioDirectorList(portfoliobasicinfo.getPortfolioid(),new Date(),"director");
        if (portfoliodirectorList != null && portfoliodirectorList.size() > 0) {
            portfoliobasicinfo.setIdgonboardflag(true);
            for(Portfoliodirector director:portfoliodirectorList){
                directorStr += director.getStaffid()+",";
            }
            directorStr = directorStr.substring(0,directorStr.lastIndexOf(","));
        }
        portfoliobasicinfo.setIdgboardrepresentative(directorStr);

        String observerStr = "";
        List<Portfoliodirector> portfolioObserverList = queryIDGPortfolioDirectorList(portfoliobasicinfo.getPortfolioid(),new Date(),"observer");
        if (portfolioObserverList != null && portfolioObserverList.size() > 0) {
            for(Portfoliodirector observer:portfolioObserverList){
                observerStr += observer.getStaffid() + ",";
            }
            observerStr = observerStr.substring(0,observerStr.lastIndexOf(","));
        }
        portfoliobasicinfo.setObserver(observerStr);

        //ddl filter
        String ddlStr = "DDL_IDGRole,DDL_Stage,DDL_InitialRole,DDL_IDGFirstInvest,DDL_Sector,DDL_SubSector,DDL_AntiDilution,DDL_IncorpLocation,DDL_CompanyLegalForm,DDL_StockExchange,DDL_PortfolioStatus";
        portfolioDDLFilter(portfoliobasicinfo, ddlStr);

        //member filter
        String memberStr = PortfolioMemberConstant.BOARDSEAT + "," + PortfolioMemberConstant.COUNSEL + "," + PortfolioMemberConstant.OBSERVER + "," + PortfolioMemberConstant.PM + "," + PortfolioMemberConstant.TEAMMEBER;
        portfolioMemberFilter(portfoliobasicinfo, memberStr, staff);

        if(portfoliobasicinfo.getLockupexpireddate() != null){
            String dateStr = DateUtil.dateToString(portfoliobasicinfo.getLockupexpireddate(),DateUtil.LONG_DATE_FORMAT);
            if(dateStr.equals("1900-01-01")){
                portfoliobasicinfo.setLockupexpireddate(null);
            }
        }




        return portfoliobasicinfo;
    }

    public int addProjInfo(Portfoliobasicinfo portfoliobasicinfo, LoginUser loginUser) {
        String portfolioId = getPortfolioId();
        portfoliobasicinfo.setPortfolioid(portfolioId);
        portfoliobasicinfo.setValidFlag("1");
        portfoliobasicinfo.setCreatedby(loginUser.getUserId());
        portfoliobasicinfo.setCreateddate(new Date());
        portfoliobasicinfo.setModifiedby(loginUser.getUserId());
        portfoliobasicinfo.setModifieddate(new Date());

//        if (!StringUtil.isBlankOrNull(portfoliobasicinfo.getIdgboardrepresentative())) {
//            portfoliobasicinfo.setIdgonboardflag(true);
//        } else {
//            portfoliobasicinfo.setIdgonboardflag(false);
//        }

        String foundDate = portfoliobasicinfo.getFoundeddate();
        if (foundDate != null && !"".equals(foundDate)) {
            int year = DateUtil.getYear(DateUtil.stringtoDate(foundDate, DateUtil.LONG_DATE_FORMAT));
            portfoliobasicinfo.setFoundeddate(String.valueOf(year));
        }

        //处理项目成员
        dealWithProjMember(portfoliobasicinfo, loginUser);

        //记录项目状态历史
        if(getPortfolioStatusHistory(portfoliobasicinfo) == null){
            insertPortfolioStatusHistory(portfoliobasicinfo, loginUser);
        }

        return portfoliobasicinfoMapper.insert(portfoliobasicinfo);
    }

    private void insertPortfolioStatusHistory(Portfoliobasicinfo portfoliobasicinfo, LoginUser loginUser) {
        Portfoliostatushistory portfoliostatushistory = new Portfoliostatushistory();
        portfoliostatushistory.setPortfolioid(portfoliobasicinfo.getPortfolioid());
        portfoliostatushistory.setStatuseffectivedate(portfoliobasicinfo.getPortfoliostatusdate());
        portfoliostatushistory.setPortfoliostatus(portfoliobasicinfo.getPortfoliostatus());
        portfoliostatushistory.setModifiedby(loginUser.getUserId());
        portfoliostatushistory.setModifieddate(new Date());

        portfoliostatushistoryMapper.insert(portfoliostatushistory);
    }

    private Portfoliostatushistory getPortfolioStatusHistory(Portfoliobasicinfo portfoliobasicinfo) {
        Portfoliostatushistory portfoliostatushistory = new Portfoliostatushistory();
        portfoliostatushistory.setPortfolioid(portfoliobasicinfo.getPortfolioid());
        portfoliostatushistory.setStatuseffectivedate(portfoliobasicinfo.getPortfoliostatusdate());
        portfoliostatushistory.setPortfoliostatus(portfoliobasicinfo.getPortfoliostatus());
        Portfoliostatushistory result = portfoliostatushistoryMapper.selectByPortfolioStatus(portfoliostatushistory);
        return result;
    }

    public int deleteProjInfoById(String id) {
        return portfoliobasicinfoMapper.deleteByPrimaryKey(id);
    }

    public int updateProjInfo(Portfoliobasicinfo portfoliobasicinfo, LoginUser loginUser) {
        Portfoliobasicinfo origin = portfoliobasicinfoMapper.selectByPrimaryKey(portfoliobasicinfo.getPortfolioid());
        portfoliobasicinfo.setModifiedby("");
        portfoliobasicinfo.setModifieddate(new Date());
        if (origin != null) {
            dealWithProjMember(portfoliobasicinfo, loginUser);

//            if (!StringUtil.isBlankOrNull(portfoliobasicinfo.getIdgboardrepresentative())) {
//                origin.setIdgonboardflag(true);
//            } else {
//                origin.setIdgonboardflag(false);
//            }
            //项目状态历史
            if(getPortfolioStatusHistory(portfoliobasicinfo) == null){
                insertPortfolioStatusHistory(portfoliobasicinfo, loginUser);
            }

            origin.setAbbname(portfoliobasicinfo.getAbbname());
            origin.setAbbnamechi(portfoliobasicinfo.getAbbnamechi());
            origin.setFullname(portfoliobasicinfo.getFullname());
            origin.setFullnamechi(portfoliobasicinfo.getFullnamechi());
            origin.setReportname(portfoliobasicinfo.getReportname());
            origin.setLogo(portfoliobasicinfo.getLogo());
            origin.setCeo(portfoliobasicinfo.getCeo());
            origin.setIdgroleid(portfoliobasicinfo.getIdgroleid());
            origin.setStageid(portfoliobasicinfo.getStageid());
            origin.setInitialroleid(portfoliobasicinfo.getInitialroleid());
            origin.setFirstinvestid(portfoliobasicinfo.getFirstinvestid());
            origin.setSectorid(portfoliobasicinfo.getSectorid());
            origin.setSubsectorid(portfoliobasicinfo.getSubsectorid());
            origin.setIdgboardseatno(portfoliobasicinfo.getIdgboardseatno());
            origin.setTotalboardseatno(portfoliobasicinfo.getTotalboardseatno());
            origin.setAntidilutionid(portfoliobasicinfo.getAntidilutionid());
            origin.setFundoffund(portfoliobasicinfo.getFundoffund());
            origin.setLocationid(portfoliobasicinfo.getLocationid());
            origin.setFoundeddate(portfoliobasicinfo.getFoundeddate());
            origin.setRegisterno(portfoliobasicinfo.getRegisterno());
            origin.setFormid(portfoliobasicinfo.getFormid());
            origin.setWebsite(portfoliobasicinfo.getWebsite());
            origin.setHeadcount(portfoliobasicinfo.getHeadcount());
            origin.setFax(portfoliobasicinfo.getFax());
            origin.setTelephone(portfoliobasicinfo.getTelephone());
            origin.setAddress(portfoliobasicinfo.getAddress());
            origin.setAddresschi(portfoliobasicinfo.getAddresschi());
            origin.setPrimarybusiness(portfoliobasicinfo.getPrimarybusiness());
            origin.setProduct(portfoliobasicinfo.getProduct());
            origin.setOnelinedesc(portfoliobasicinfo.getOnelinedesc());
            origin.setOnelinedeschi(portfoliobasicinfo.getOnelinedeschi());
            origin.setRemarkes(portfoliobasicinfo.getRemarkes());
//            origin.setPrivateorpublic(portfoliobasicinfo.getPrivateorpublic());
            origin.setYtd(portfoliobasicinfo.getYtd());
            origin.setNomineename(portfoliobasicinfo.getNomineename());
            origin.setAuditconfaddress(portfoliobasicinfo.getAuditconfaddress());
            origin.setAuditconfaddress2(portfoliobasicinfo.getAuditconfaddress2());
            origin.setAuditconfaddress3(portfoliobasicinfo.getAuditconfaddress3());
            origin.setLiquidationpreference(portfoliobasicinfo.getLiquidationpreference());
            origin.setPortfoliostatusdate(portfoliobasicinfo.getPortfoliostatusdate());
            origin.setStockexchangeid(portfoliobasicinfo.getStockexchangeid());
            origin.setStockcode(portfoliobasicinfo.getStockcode());
            origin.setLockupperiod(portfoliobasicinfo.getLockupperiod());
            origin.setLockupexpireddate(portfoliobasicinfo.getLockupexpireddate());
            origin.setAdrratio(portfoliobasicinfo.getAdrratio());
            origin.setSpvjurisdition(portfoliobasicinfo.getSpvjurisdition());
            origin.setModifiedby(loginUser.getUserId());
            origin.setModifieddate(new Date());
            origin.setPortfoliostatus(portfoliobasicinfo.getPortfoliostatus());

            origin.setObserver(portfoliobasicinfo.getObserver());
            origin.setProjectmanager(portfoliobasicinfo.getProjectmanager());
            origin.setProjectbuddy(portfoliobasicinfo.getProjectbuddy());
            origin.setIdginhousecounsel(portfoliobasicinfo.getIdginhousecounsel());
            origin.setIdgboardrepresentative(portfoliobasicinfo.getIdgboardrepresentative());
        }

        return portfoliobasicinfoMapper.updateByPrimaryKey(origin);
    }

    public String getPortfolioId() {
        String maxPortfolioId = portfoliobasicinfoMapper.selectMaxPortfolioId();
        String maxId = maxPortfolioId.substring(maxPortfolioId.indexOf("P") + 1);
        int id = Integer.parseInt(maxId) + 1;
        return "P" + StringUtil.autoCompleteZero(String.valueOf(id), 5);
    }


    private void dealWithProjMember(Portfoliobasicinfo portfoliobasicinfo, LoginUser loginUser) {
        Portfoliomember deleteEntity = new Portfoliomember();
        deleteEntity.setPortfolioid(portfoliobasicinfo.getPortfolioid());

        //项目经理
        deleteEntity.setMembertype(PortfolioMemberConstant.PM);
        portfoliomemberMapper.deleteByProjId(deleteEntity);
        String pmStr = portfoliobasicinfo.getProjectmanager();
        if (!StringUtil.isBlankOrNull(pmStr)) {

            String[] pmStrArray = pmStr.split(",");
            for (int i = 0; i < pmStrArray.length; i++) {
                Portfoliomember portfoliomember = new Portfoliomember();
                portfoliomember.setStaffid(pmStrArray[i]);
                portfoliomember.setPortfolioid(portfoliobasicinfo.getPortfolioid());
                portfoliomember.setMembertype(PortfolioMemberConstant.PM);
                portfoliomember.setMemberorder(i);
                portfoliomember.setCreatedby(loginUser.getUserId());
                portfoliomember.setCreateddate(new Date());
                portfoliomember.setModifiedby(loginUser.getUserId());
                portfoliomember.setModifieddate(new Date());

                portfoliomemberMapper.insert(portfoliomember);
            }
        }
        //小组成员
        deleteEntity.setMembertype(PortfolioMemberConstant.TEAMMEBER);
        portfoliomemberMapper.deleteByProjId(deleteEntity);
        String teamStr = portfoliobasicinfo.getProjectbuddy();
        if (!StringUtil.isBlankOrNull(teamStr)) {

            String[] teamStrArray = teamStr.split(",");
            for (int i = 0; i < teamStrArray.length; i++) {
                Portfoliomember portfoliomember = new Portfoliomember();
                portfoliomember.setStaffid(teamStrArray[i]);
                portfoliomember.setPortfolioid(portfoliobasicinfo.getPortfolioid());
                portfoliomember.setMembertype(PortfolioMemberConstant.TEAMMEBER);
                portfoliomember.setMemberorder(i);
                portfoliomember.setCreatedby(loginUser.getUserId());
                portfoliomember.setCreateddate(new Date());
                portfoliomember.setModifiedby(loginUser.getUserId());
                portfoliomember.setModifieddate(new Date());

                portfoliomemberMapper.insert(portfoliomember);
            }
        }
//        //董事
//        deleteEntity.setMembertype(PortfolioMemberConstant.BOARDSEAT);
//        portfoliomemberMapper.deleteByProjId(deleteEntity);
//        String boardStr = portfoliobasicinfo.getIdgboardrepresentative();
//        if (!StringUtil.isBlankOrNull(boardStr)) {
//
//            String[] boardStrArray = boardStr.split(",");
//            for (int i = 0; i < boardStrArray.length; i++) {
//                Portfoliomember portfoliomember = new Portfoliomember();
//                portfoliomember.setStaffid(boardStrArray[i]);
//                portfoliomember.setPortfolioid(portfoliobasicinfo.getPortfolioid());
//                portfoliomember.setMembertype(PortfolioMemberConstant.BOARDSEAT);
//                portfoliomember.setMemberorder(i);
//                portfoliomember.setCreatedby(loginUser.getUserId());
//                portfoliomember.setCreateddate(new Date());
//                portfoliomember.setModifiedby(loginUser.getUserId());
//                portfoliomember.setModifieddate(new Date());
//
//                portfoliomemberMapper.insert(portfoliomember);
//            }
//        }
        //项目律师
        deleteEntity.setMembertype(PortfolioMemberConstant.COUNSEL);
        portfoliomemberMapper.deleteByProjId(deleteEntity);
        String counselStr = portfoliobasicinfo.getIdginhousecounsel();
        if (!StringUtil.isBlankOrNull(counselStr)) {

            String[] counselStrArray = counselStr.split(",");
            for (int i = 0; i < counselStrArray.length; i++) {
                Portfoliomember portfoliomember = new Portfoliomember();
                portfoliomember.setStaffid(counselStrArray[i]);
                portfoliomember.setPortfolioid(portfoliobasicinfo.getPortfolioid());
                portfoliomember.setMembertype(PortfolioMemberConstant.COUNSEL);
                portfoliomember.setMemberorder(i);
                portfoliomember.setCreatedby(loginUser.getUserId());
                portfoliomember.setCreateddate(new Date());
                portfoliomember.setModifiedby(loginUser.getUserId());
                portfoliomember.setModifieddate(new Date());

                portfoliomemberMapper.insert(portfoliomember);
            }
        }
//        //观察员
//        deleteEntity.setMembertype(PortfolioMemberConstant.OBSERVER);
//        portfoliomemberMapper.deleteByProjId(deleteEntity);
//        String observerStr = portfoliobasicinfo.getObserver();
//        if (!StringUtil.isBlankOrNull(observerStr)) {
//
//            String[] observerStrArray = observerStr.split(",");
//            for (int i = 0; i < observerStrArray.length; i++) {
//                Portfoliomember portfoliomember = new Portfoliomember();
//                portfoliomember.setStaffid(observerStrArray[i]);
//                portfoliomember.setPortfolioid(portfoliobasicinfo.getPortfolioid());
//                portfoliomember.setMembertype(PortfolioMemberConstant.OBSERVER);
//                portfoliomember.setMemberorder(i);
//                portfoliomember.setCreatedby(loginUser.getUserId());
//                portfoliomember.setCreateddate(new Date());
//                portfoliomember.setModifiedby(loginUser.getUserId());
//                portfoliomember.setModifieddate(new Date());
//
//                portfoliomemberMapper.insert(portfoliomember);
//            }
//        }
    }


    private void portfolioDDLFilter(Portfoliobasicinfo portfoliobasicinfo, String filterType) {
        DdlBaseInfo ddlBaseInfo = new DdlBaseInfo();
        ddlBaseInfo.setDictArray(filterType);
        List<DdlBaseInfo> ddlBaseInfoList = sysBaseInfoService.querySysDictList(ddlBaseInfo);
        for (DdlBaseInfo ddl : ddlBaseInfoList) {
            List<Map<String, Object>> ddlMapList = ddl.getBaseInfoList();
            switch (ddl.getBaseType()) {
                case "DDL_IDGRole":
                    if (portfoliobasicinfo.getIdgroleid() != null) {
                        for (Map<String, Object> map : ddlMapList) {
                            if (portfoliobasicinfo.getIdgroleid() != null) {
                                if (Integer.parseInt(map.get("baseId").toString()) == portfoliobasicinfo.getIdgroleid()) {
                                    portfoliobasicinfo.setIdgroleidstr(map.get("baseName").toString());
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case "DDL_Stage":
                    for (Map<String, Object> map : ddlMapList) {
                        if (portfoliobasicinfo.getStageid() != null) {
                            if (Integer.parseInt(map.get("baseId").toString()) == portfoliobasicinfo.getStageid()) {
                                portfoliobasicinfo.setStageidstr(map.get("baseName").toString());
                                break;
                            }
                        }
                    }
                    break;
                case "DDL_InitialRole":
                    for (Map<String, Object> map : ddlMapList) {
                        if (portfoliobasicinfo.getInitialroleid() != null) {
                            if (Integer.parseInt(map.get("baseId").toString()) == portfoliobasicinfo.getInitialroleid()) {
                                portfoliobasicinfo.setInitialroleidstr(map.get("baseName").toString());
                                break;
                            }
                        }
                    }
                    break;
                case "DDL_IDGFirstInvest":
                    for (Map<String, Object> map : ddlMapList) {
                        if (portfoliobasicinfo.getFirstinvestid() != null) {
                            if (Integer.parseInt(map.get("baseId").toString()) == portfoliobasicinfo.getFirstinvestid()) {
                                portfoliobasicinfo.setFirstinvestidstr(map.get("baseName").toString());
                                break;
                            }
                        }

                    }
                    break;
                case "DDL_Sector":
                    for (Map<String, Object> map : ddlMapList) {
                        if (portfoliobasicinfo.getSectorid() != null) {
                            if (Integer.parseInt(map.get("baseId").toString()) == portfoliobasicinfo.getSectorid()) {
                                portfoliobasicinfo.setSectoridstr(map.get("baseName").toString());
                                break;
                            }
                        }
                    }
                    break;
                case "DDL_SubSector":
                    for (Map<String, Object> map : ddlMapList) {
                        if (portfoliobasicinfo.getSubsectorid() != null) {
                            if (Integer.parseInt(map.get("baseId").toString()) == portfoliobasicinfo.getSubsectorid()) {
                                portfoliobasicinfo.setSubsectoridstr(map.get("baseName").toString());
                                break;
                            }
                        }

                    }
                    break;
                case "DDL_AntiDilution":
                    for (Map<String, Object> map : ddlMapList) {
                        if (portfoliobasicinfo.getAntidilutionid() != null) {
                            if (Integer.parseInt(map.get("baseId").toString()) == portfoliobasicinfo.getAntidilutionid()) {
                                portfoliobasicinfo.setAntidilutionidstr(map.get("baseName").toString());
                                break;
                            }
                        }
                    }
                    break;
                case "DDL_IncorpLocation":
                    for (Map<String, Object> map : ddlMapList) {
                        if (portfoliobasicinfo.getLocationid() != null) {
                            if (Integer.parseInt(map.get("baseId").toString()) == portfoliobasicinfo.getLocationid()) {
                                portfoliobasicinfo.setLocationidstr(map.get("baseName").toString());
                                break;
                            }
                        }
                    }
                    break;
                case "DDL_CompanyLegalForm":
                    for (Map<String, Object> map : ddlMapList) {
                        if (portfoliobasicinfo.getFormid() != null) {
                            if (Integer.parseInt(map.get("baseId").toString()) == portfoliobasicinfo.getFormid()) {
                                portfoliobasicinfo.setFormidstr(map.get("baseName").toString());
                                break;
                            }
                        }

                    }
                    break;
                case "DDL_StockExchange":
                    for (Map<String, Object> map : ddlMapList) {
                        if (portfoliobasicinfo.getStockexchangeid() != null) {
                            if (Integer.parseInt(map.get("baseId").toString()) == portfoliobasicinfo.getStockexchangeid()) {
                                portfoliobasicinfo.setStockexchangeidstr(map.get("baseName").toString());
                                break;
                            }
                        }
                    }
                    break;
                case "DDL_PortfolioStatus":
                    if (portfoliobasicinfo.getPortfoliostatus() != null) {
                        for (Map<String, Object> map : ddlMapList) {
                            if (portfoliobasicinfo.getPortfoliostatus() != null) {
                                if (Integer.parseInt(map.get("baseId").toString()) == portfoliobasicinfo.getPortfoliostatus()) {
                                    portfoliobasicinfo.setPortfoliostatusstr(map.get("baseName").toString());
                                    break;
                                }
                            }

                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void portfolioMemberFilter(Portfoliobasicinfo portfoliobasicinfo, String filterType, Map<String, Object> staff) {
        String[] filter = filterType.split(",");
        for (int i = 0; i < filter.length; i++) {
            switch (filter[i]) {
                case PortfolioMemberConstant.PM:
                    String pmStr = "";
                    if (!StringUtil.isBlankOrNull(portfoliobasicinfo.getProjectmanager())) {
                        String[] pm = portfoliobasicinfo.getProjectmanager().split(",");
                        for (int j = 0; j < pm.length; j++) {
                            String baseName = "";
                            if (staff.get(pm[j]) != null) {
                                IdgStaff idgStaff = (IdgStaff) staff.get(pm[j]);
                                if (!StringUtil.isBlankOrNull(idgStaff.getFirstnamechi()) && !StringUtil.isBlankOrNull(idgStaff.getLastnamechi())) {
                                    baseName = idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng() + "(" + idgStaff.getLastnamechi() + idgStaff.getFirstnamechi() + ")";
                                    pmStr += baseName + ",";
                                } else {
                                    baseName = idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng();
                                    pmStr += baseName + ",";
                                }
                            }
                        }
                        if (!StringUtil.isBlankOrNull(pmStr)) {
                            pmStr = pmStr.substring(0, pmStr.lastIndexOf(","));
                        }
                        portfoliobasicinfo.setProjectmanagerstr(pmStr);
                    }
                    break;
                case PortfolioMemberConstant.TEAMMEBER:
                    String teamStr = "";
                    if (!StringUtil.isBlankOrNull(portfoliobasicinfo.getProjectbuddy())) {
                        String[] team = portfoliobasicinfo.getProjectbuddy().split(",");
                        for (int j = 0; j < team.length; j++) {
                            String baseName = "";
                            if (staff.get(team[j]) != null) {
                                IdgStaff idgStaff = (IdgStaff) staff.get(team[j]);
                                if (!StringUtil.isBlankOrNull(idgStaff.getFirstnamechi()) && !StringUtil.isBlankOrNull(idgStaff.getLastnamechi())) {
                                    baseName = idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng() + "(" + idgStaff.getLastnamechi() + idgStaff.getFirstnamechi() + ")";
                                    teamStr += baseName + ",";
                                } else {
                                    baseName = idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng();
                                    teamStr += baseName + ",";
                                }
                            }
                        }
                        if (!StringUtil.isBlankOrNull(teamStr)) {
                            teamStr = teamStr.substring(0, teamStr.lastIndexOf(","));
                        }
                        portfoliobasicinfo.setProjectbuddystr(teamStr);
                    }
                    break;

                case PortfolioMemberConstant.BOARDSEAT:
                    String boardStr = "";
                    if (!StringUtil.isBlankOrNull(portfoliobasicinfo.getIdgboardrepresentative())) {
                        String[] board = portfoliobasicinfo.getIdgboardrepresentative().split(",");
                        for (int j = 0; j < board.length; j++) {
                            String baseName = "";
                            if (staff.get(board[j]) != null) {
                                IdgStaff idgStaff = (IdgStaff) staff.get(board[j]);
                                if (!StringUtil.isBlankOrNull(idgStaff.getFirstnamechi()) && !StringUtil.isBlankOrNull(idgStaff.getLastnamechi())) {
                                    baseName = idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng() + "(" + idgStaff.getLastnamechi() + idgStaff.getFirstnamechi() + ")";
                                    boardStr += baseName + ",";
                                } else {
                                    baseName = idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng();
                                    boardStr += baseName + ",";
                                }
                            }
                        }
                        if (!StringUtil.isBlankOrNull(boardStr)) {
                            boardStr = boardStr.substring(0, boardStr.lastIndexOf(","));
                        }
                        portfoliobasicinfo.setIdgboardrepresentativestr(boardStr);
                    }
                    break;

                case PortfolioMemberConstant.COUNSEL:
                    String counselStr = "";
                    if (!StringUtil.isBlankOrNull(portfoliobasicinfo.getIdginhousecounsel())) {
                        String[] counsel = portfoliobasicinfo.getIdginhousecounsel().split(",");
                        for (int j = 0; j < counsel.length; j++) {
                            String baseName = "";
                            if (staff.get(counsel[j]) != null) {
                                IdgStaff idgStaff = (IdgStaff) staff.get(counsel[j]);
                                if (!StringUtil.isBlankOrNull(idgStaff.getFirstnamechi()) && !StringUtil.isBlankOrNull(idgStaff.getLastnamechi())) {
                                    baseName = idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng() + "(" + idgStaff.getLastnamechi() + idgStaff.getFirstnamechi() + ")";
                                    counselStr += baseName + ",";
                                } else {
                                    baseName = idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng();
                                    counselStr += baseName + ",";
                                }
                            }
                        }
                        if (!StringUtil.isBlankOrNull(counselStr)) {
                            counselStr = counselStr.substring(0, counselStr.lastIndexOf(","));
                        }
                        portfoliobasicinfo.setIdginhousecounselstr(counselStr);
                    }
                    break;

                case PortfolioMemberConstant.OBSERVER:
                    String observerStr = "";
                    if (!StringUtil.isBlankOrNull(portfoliobasicinfo.getObserver())) {
                        String[] observer = portfoliobasicinfo.getObserver().split(",");
                        for (int j = 0; j < observer.length; j++) {
                            String baseName = "";
                            if (staff.get(observer[j]) != null) {
                                IdgStaff idgStaff = (IdgStaff) staff.get(observer[j]);
                                if (!StringUtil.isBlankOrNull(idgStaff.getFirstnamechi()) && !StringUtil.isBlankOrNull(idgStaff.getLastnamechi())) {
                                    baseName = idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng() + "(" + idgStaff.getLastnamechi() + idgStaff.getFirstnamechi() + ")";
                                    observerStr += baseName + ",";
                                } else {
                                    baseName = idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng();
                                    observerStr += baseName + ",";
                                }
                            }
                        }
                        if (!StringUtil.isBlankOrNull(observerStr)) {
                            observerStr = observerStr.substring(0, observerStr.lastIndexOf(","));
                        }
                        portfoliobasicinfo.setObserverstr(observerStr);
                    }
                    break;

                default:
                    break;
            }
        }
    }


    public JsonResult queryFundInvestPortfoliosByFundId(String fundId) {
        JsonResult jsonResult = new JsonResult();


        //List<Map<String,Object>> capitalCallInfoList = capitalCallMapper.selectFundEntityList(fundbasicinfo);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("fundId", fundId);
        List<Map<String, Object>> fundInvestPortfolios = portfoliobasicinfoMapper.selectFundInvestPortfoliosByParam(paramMap);

        jsonResult.setData(fundInvestPortfolios);

        return jsonResult;
    }

    //director---------------------------

    public int updateProjDirectorById(Portfoliobasicinfo portfoliobasicinfo, LoginUser loginUser) {
        portfoliobasicinfo.setModifieddate(new Date());
        portfoliobasicinfo.setModifiedby(loginUser.getUserId());

        portfoliobasicinfoMapper.updateProjDirectorById(portfoliobasicinfo);
        return 1;
    }

    public int addPortfolioDirector(Portfoliodirector portfoliodirector, LoginUser loginUser) {
        portfoliodirector.setCreatedby(loginUser.getUserId());
        portfoliodirector.setCreateddate(new Date());
        portfoliodirector.setModifiedby(loginUser.getUserId());
        portfoliodirector.setModifieddate(new Date());
        portfoliodirectorMapper.insert(portfoliodirector);
        return 1;
    }

    public Portfoliodirector queryPortfolioDirectorById(Integer directorId) {
        Portfoliodirector portfoliodirector = portfoliodirectorMapper.selectByPrimaryKey(directorId);
        return portfoliodirector;
    }

    public List<Portfoliodirector> queryPortfolioDirectorListByPortfolioId(String portfolioId) {
        List<Portfoliodirector> portfoliodirectorList = portfoliodirectorMapper.queryPortfolioDirectorListByPortfolioId(portfolioId);
        return portfoliodirectorList;
    }

    public int updatePortfolioDirectorEntity(Portfoliodirector portfoliodirector, LoginUser loginUser) {
        portfoliodirector.setModifieddate(new Date());
        portfoliodirector.setModifiedby(loginUser.getUserId());
        portfoliodirectorMapper.updateByPrimaryKey(portfoliodirector);
        return 1;
    }

    public int deletePortfolioDirectorEntityById(Integer id) {
        return portfoliodirectorMapper.deleteByPrimaryKey(id);
    }

    //获取idg到截止时间的董监事列表
    public List<Portfoliodirector> queryIDGPortfolioDirectorList(String portfolioId,Date toDate,String directorType){
        Map<String,Object> inputMap = new HashMap<>();
        inputMap.put("portfolioid",portfolioId);
        inputMap.put("directortype",directorType);
        inputMap.put("todate",toDate);
        List<Portfoliodirector> portfoliodirectorList = portfoliodirectorMapper.queryIDGPortfolioDirectorList(inputMap);
        return portfoliodirectorList;
    }


}
