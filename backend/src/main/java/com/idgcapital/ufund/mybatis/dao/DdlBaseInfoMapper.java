package com.idgcapital.ufund.mybatis.dao;

import java.util.List;
import java.util.Map;

public interface DdlBaseInfoMapper {
    List<Map<String,Object>> selectIDGRoleList();
    List<Map<String,Object>> selectStageList();
    List<Map<String,Object>> selectInitialRoleList();
    List<Map<String,Object>> selectIDGFirstInvestList();
    List<Map<String,Object>> selectSectorList();
    List<Map<String,Object>> selectSubSectorList();
    List<Map<String,Object>> selectAntiDilutionList();
    List<Map<String,Object>> selectIncorpLocationList();
    List<Map<String,Object>> selectCompanyLegalFormList();
    List<Map<String,Object>> selectStockExchangeList();
    List<Map<String,Object>> selectPortfolioStatusList();
    List<Map<String,Object>> selectInvestorTypeList();
    List<Map<String,Object>> selectSpvTypeList();
    List<Map<String,Object>> selectInvestTypeList();
    List<Map<String,Object>> selectSecurityTypeList();
    List<Map<String,Object>> selectShareTypeList();
    List<Map<String,Object>> selectShareHolderTypeList();
    List<Map<String,Object>> selectExitTypeList();
    List<Map<String,Object>> selectExitModeTypeList();
    List<Map<String,Object>> selectSecurityType4WarrantList();
}