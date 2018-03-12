package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfolioequityinvestment;
import java.util.List;

public interface PortfolioequityinvestmentMapper {
    int deleteByPrimaryKey(String eiid);

    int insert(Portfolioequityinvestment record);

    Portfolioequityinvestment selectByPrimaryKey(String eiid);

    List<Portfolioequityinvestment> selectAll();

    int updateByPrimaryKey(Portfolioequityinvestment record);

    List<Portfolioequityinvestment> selectPortfolioEquityListByPortfolioId(String portfolioId);

    String selectMaxPortfolioEquityId();

    List<Portfolioequityinvestment> queryInvestmentForExit(Portfolioequityinvestment portfolioequityinvestment);

    List<Portfolioequityinvestment> queryReclassificationByFundFamilyName(Portfolioequityinvestment portfolioequityinvestment);



    List<Portfolioequityinvestment> selectPortfolioEquityListByFundId(String fundId);
}