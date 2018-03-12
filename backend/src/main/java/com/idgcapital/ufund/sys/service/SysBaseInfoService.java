package com.idgcapital.ufund.sys.service;

import com.idgcapital.ufund.common.util.StringUtil;
import com.idgcapital.ufund.model.DdlBaseInfo;
import com.idgcapital.ufund.model.IdgStaff;
import com.idgcapital.ufund.mybatis.dao.DdlBaseInfoMapper;
import com.idgcapital.ufund.mybatis.dao.FundbasicinfoMapper;
import com.idgcapital.ufund.mybatis.dao.IdgStaffMapper;
import com.idgcapital.ufund.mybatis.dao.SpvinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by li_zhao on 2017/12/18.
 */
@Service
public class SysBaseInfoService {
    @Autowired
    private DdlBaseInfoMapper ddlBaseInfoMapper;
    @Autowired
    private SpvinfoMapper spvinfoMapper;
    @Autowired
    private IdgStaffMapper idgStaffMapper;
    @Autowired
    private FundbasicinfoMapper fundbasicinfoMapper;

    public List<DdlBaseInfo> querySysDictList(DdlBaseInfo ddlBaseInfo){
        String [] ddlTypeArray = ddlBaseInfo.getDictArray().split(",");
        List<DdlBaseInfo> returnVal = new ArrayList<>();
        if(ddlTypeArray.length>0){
            for(int i=0;i<ddlTypeArray.length;i++){
                DdlBaseInfo ddl = new DdlBaseInfo();
                switch (ddlTypeArray[i]){
                    case "DDL_IDGRole":
                        ddl.setBaseType("DDL_IDGRole");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectIDGRoleList());
                        break;
                    case "DDL_Stage":
                        ddl.setBaseType("DDL_Stage");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectStageList());
                        break;
                    case "DDL_InitialRole":
                        ddl.setBaseType("DDL_InitialRole");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectInitialRoleList());
                        break;
                    case "DDL_IDGFirstInvest":
                        ddl.setBaseType("DDL_IDGFirstInvest");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectIDGFirstInvestList());
                        break;
                    case "DDL_Sector":
                        ddl.setBaseType("DDL_Sector");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectSectorList());
                        break;
                    case "DDL_SubSector":
                        ddl.setBaseType("DDL_SubSector");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectSubSectorList());
                        break;
                    case "DDL_AntiDilution":
                        ddl.setBaseType("DDL_AntiDilution");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectAntiDilutionList());
                        break;
                    case "DDL_IncorpLocation":
                        ddl.setBaseType("DDL_IncorpLocation");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectIncorpLocationList());
                        break;
                    case "DDL_CompanyLegalForm":
                        ddl.setBaseType("DDL_CompanyLegalForm");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectCompanyLegalFormList());
                        break;
                    case "DDL_StockExchange":
                        ddl.setBaseType("DDL_StockExchange");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectStockExchangeList());
                        break;
                    case "DDL_PortfolioStatus":
                        ddl.setBaseType("DDL_PortfolioStatus");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectPortfolioStatusList());
                        break;
                    case "DDL_SpvInvestorType":
                        ddl.setBaseType("DDL_SpvInvestorType");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectInvestorTypeList());
                        break;
                    case "DDL_SpvType":
                        ddl.setBaseType("DDL_SpvType");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectSpvTypeList());
                        break;
                    case "DDL_InvestType":
                        ddl.setBaseType("DDL_InvestType");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectInvestTypeList());
                        break;
                    case "DDL_SecurityType":
                        ddl.setBaseType("DDL_SecurityType");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectSecurityTypeList());
                        break;
                    case "DDL_ShareType":
                        ddl.setBaseType("DDL_ShareType");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectShareTypeList());
                        break;
                    case "DDL_ShareHolderType":
                        ddl.setBaseType("DDL_ShareHolderType");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectShareHolderTypeList());
                        break;
                    case "DDL_ExitType":
                        ddl.setBaseType("DDL_ExitType");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectExitTypeList());
                        break;
                    case "DDL_ExitModeType":
                        ddl.setBaseType("DDL_ExitModeType");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectExitModeTypeList());
                        break;
                    case "DDL_WarrantSecurityType":
                        ddl.setBaseType("DDL_WarrantSecurityType");
                        ddl.setBaseInfoList(ddlBaseInfoMapper.selectSecurityType4WarrantList());
                        break;
                    case "SPV":
                        ddl.setBaseType("SPV");
                        ddl.setBaseInfoList(spvinfoMapper.selectSPVList());
                        break;
                    case "FUND":
                        ddl.setBaseType("FUND");
                        ddl.setBaseInfoList(fundbasicinfoMapper.selectFundList());
                        break;
                    case "FUNDFAMILY":
                        ddl.setBaseType("FUNDFAMILY");
                        ddl.setBaseInfoList(fundbasicinfoMapper.selectFundFamilyList());
                        break;
                    case "IDG_Staff":
                        ddl.setBaseType("IDG_Staff");
                        List<IdgStaff> idgStaffList = idgStaffMapper.selectStaffList();
                        List<Map<String,Object>> result = new ArrayList<>();
                        for(IdgStaff idgStaff:idgStaffList){
                            Map<String,Object> map = new HashMap<>();
                            if(!StringUtil.isBlankOrNull(idgStaff.getFirstnamechi())&&!StringUtil.isBlankOrNull(idgStaff.getLastnamechi())){
                                String baseName = idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng() + "(" +idgStaff.getLastnamechi()+idgStaff.getFirstnamechi()+")";
                                map.put("baseName",baseName);
                            }else {
                                map.put("baseName", idgStaff.getFirstnameeng() + " " + idgStaff.getLastnameeng());
                            }
                            map.put("baseId",idgStaff.getStaffid());
                            result.add(map);
                        }
                        ddl.setBaseInfoList(result);
                        break;
                    case "YTD":
                        ddl.setBaseType("YTD");
                        ddl.setBaseInfoList(generateYTDList());
                        break;
                    case "CURRENCY":
                        ddl.setBaseType("CURRENCY");
                        ddl.setBaseInfoList(generateCurrList());
                        break;
                    case "DIRECTOR_TYPE":
                        ddl.setBaseType("DIRECTOR_TYPE");
                        ddl.setBaseInfoList(generateDirectorTypeList());
                        break;
                    case "WARRANT_TYPE":
                        ddl.setBaseType("WARRANT_TYPE");
                        ddl.setBaseInfoList(generateWarrantTypeList());
                        break;
                    default:break;
                }
                returnVal.add(ddl);
            }
        }
        return returnVal;
    }

    private List<Map<String,Object>> generateYTDList(){
        List<Map<String,Object>> YTDlist = new ArrayList<>();
        String [] ytdStr = {"March 31","June 30","September 30","December 31"};
        for(int i=0;i<ytdStr.length;i++){
            Map<String,Object> YTD = new HashMap<>();
            YTD.put("baseId",ytdStr[i]);
            YTD.put("baseName",ytdStr[i]);
            YTDlist.add(YTD);
        }
        return YTDlist;
    }

    private List<Map<String,Object>> generateCurrList(){
        List<Map<String,Object>> currList = new ArrayList<>();
        String [] currStr = {"USD","CNY","HKD"};
        for(int i=0;i<currStr.length;i++){
            Map<String,Object> curr = new HashMap<>();
            curr.put("baseId",currStr[i]);
            curr.put("baseName",currStr[i]);
            currList.add(curr);
        }
        return currList;
    }

    private List<Map<String,Object>> generateDirectorTypeList(){
        List<Map<String,Object>> directorTypeList = new ArrayList<>();
        String [] directorTypeStr = {"director","observer"};
        for(int i=0;i<directorTypeStr.length;i++){
            Map<String,Object> directorType = new HashMap<>();
            directorType.put("baseId",directorTypeStr[i]);
            directorType.put("baseName",directorTypeStr[i]);
            directorTypeList.add(directorType);
        }
        return directorTypeList;
    }

    private List<Map<String,Object>> generateWarrantTypeList(){
        List<Map<String,Object>> warrantTypeList = new ArrayList<>();
        String [] warrantTypeStr = {"IDG","Investor(except IDG)"};
        for(int i=0;i<warrantTypeStr.length;i++){
            Map<String,Object> warrantType = new HashMap<>();
            warrantType.put("baseId",warrantTypeStr[i]);
            warrantType.put("baseName",warrantTypeStr[i]);
            warrantTypeList.add(warrantType);
        }
        return warrantTypeList;
    }
}
