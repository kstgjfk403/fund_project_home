package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliodirector;
import java.util.List;
import java.util.Map;

public interface PortfoliodirectorMapper {
    int deleteByPrimaryKey(Integer directorid);

    int insert(Portfoliodirector record);

    Portfoliodirector selectByPrimaryKey(Integer directorid);

    List<Portfoliodirector> selectAll();

    int updateByPrimaryKey(Portfoliodirector record);

    List<Portfoliodirector> queryPortfolioDirectorListByPortfolioId(String portfolioId);

    List<Portfoliodirector> queryIDGPortfolioDirectorList(Map<String,Object> inputMap);
}