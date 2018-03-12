package com.idgcapital.ufund.mybatis.dao;


import com.idgcapital.ufund.model.ProjReportInput;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProjManageReportMapper {
    //
    List<String> queryGainLossPortfolioIdByFund(ProjReportInput reportInput);
    List<Map<String,Object>> queryGainLossReport(ProjReportInput reportInput);

    List<Integer> queryGainLossYearByFund(ProjReportInput projReportInput);
    List<Map<String,Object>> queryGainLossReportSum(ProjReportInput reportInput);

    //
    List<String> queryCapitalFundPortfolioIdByFund(ProjReportInput reportInput);
    Map<String,Object> queryCapitalFundPortfolioById(ProjReportInput reportInput);
    List<String> queryPortfolioLeaderList(String portfolioId);
    BigDecimal queryPortfolioCostByQuarter(ProjReportInput projReportInput);
    BigDecimal queryPortfolioMarketValueByQuarter(ProjReportInput projReportInput);
    Long queryPortfolioRemainByQuarter(ProjReportInput projReportInput);
    List<String> queryPortfolioOtherInvestorList(ProjReportInput projReportInput);


    //invest detail
    List<String> queryInvestDetailPortfolioIdByFund(ProjReportInput projReportInput);
    List<Map<String,Object>> queryInvestDetailById (ProjReportInput projReportInput);
    long queryRemainSharesByFundId(ProjReportInput projReportInput);
    String queryFundFullNameByFundId(String fundId);
    Long queryOtherInvestorTotalShares(ProjReportInput projReportInput);

    //main schedule
    List<Map<String,Object>> queryMainSchedulePortfolioIdByFund(ProjReportInput projReportInput);
    List<Map<String,Object>> queryNoteMainScheduleByPortfolioId(ProjReportInput projReportInput);
    List<Map<String,Object>> queryInvestMainScheduleByPortfolioId(ProjReportInput projReportInput);
    String queryPrivateOrPublicByPortfolioId(ProjReportInput projReportInput);


    List<Map<String,Object>> selectPortfolioBusinessListOnlyEquity(Map<String,Object> params);

    List<Map<String,Object>> selectPortfolioBusinessList(Map<String,Object> params);




}