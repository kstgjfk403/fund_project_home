package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Fundfininfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface FundfininfoMapper {
    int deleteByPrimaryKey(String finid);

    int insert(Fundfininfo record);

    Fundfininfo selectByPrimaryKey(String finid);

    List<Fundfininfo> selectAll();

    int updateByPrimaryKey(Fundfininfo record);

    List<Fundfininfo> queryFundFinInfoListByFundIdAndFinDate(Fundfininfo record);

    List<Map<String,Object>> queryFundFinEntityList(Map<String, Object> map);

    List<Map<String, Object>> queryLpCashListByFundId(Map<String, Object> map);

    List<Map<String, Object>> queryGrossCashListByFundId(Map<String, Object> map);

    List<Map<String, Object>> queryPortfolioCashList(Map<String, Object> map);//测试用

    List<Map<String, Object>> queryPortfolioCashListTrue(Map<String, Object> map);

    Map<String, Object> queryGpCarryByMap(Map<String, Object> map);

    List<Map<String, Object>> queryFundInvestmentOverview(Map<String, Object> map);

    List<Map<String, Object>> queryPortfolioTrackRecordList(Map<String, Object> map);

    List<Map<String, Object>> queryPortfolioExitList(Map<String, Object> map);

    List<Map<String, Object>> queryPortfolioValuationList(Map<String, Object> map);

    List<Map<String, Object>> queryPortfolioValuationListOrderByDateDesc(Map<String, Object> map);

    List<Map<String, Object>> queryPortfolioInvestmentList(Map<String, Object> map);

}