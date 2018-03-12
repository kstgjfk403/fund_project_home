package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliobasicinfo;

import org.springframework.stereotype.Service;

import com.idgcapital.ufund.model.Portfoliovaluation;

import java.math.BigDecimal;
import java.util.List;


import java.util.List;
import java.util.Map;

public interface PortfoliobasicinfoMapper {
    int deleteByPrimaryKey(String portfolioid);

    int insert(Portfoliobasicinfo record);

    Portfoliobasicinfo selectByPrimaryKey(String portfolioid);

    List<Portfoliobasicinfo> selectAll();

    int updateByPrimaryKey(Portfoliobasicinfo record);

    String selectMaxPortfolioId();

    List<Portfoliobasicinfo> selectPortfolioList(Portfoliobasicinfo portfoliobasicinfo);

    long selectPortfolioCount(Portfoliobasicinfo portfoliobasicinfo);

    List<Map<String,Object>> selectFundInvestPortfoliosByParam(Map<String,Object> paraMap);

    List<Portfoliobasicinfo> queryPortfolioByFundId(String fundID);

    BigDecimal queryTotalShare(Portfoliovaluation s);

    BigDecimal queryIdgShare(Portfoliovaluation s);

    int updateProjDirectorById(Portfoliobasicinfo portfoliobasicinfo);

}