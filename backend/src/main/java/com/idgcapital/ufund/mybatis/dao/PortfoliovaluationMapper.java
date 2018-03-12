package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliovaluation;
import java.util.List;

public interface PortfoliovaluationMapper {
    int deleteByPrimaryKey(Integer valuationid);

    int insert(Portfoliovaluation record);

    Portfoliovaluation selectByPrimaryKey(Integer valuationid);

    List<Portfoliovaluation> selectAll();

    int updateByPrimaryKey(Portfoliovaluation record);

    int updateBySelective(Portfoliovaluation record);

    List<Portfoliovaluation> queryPortfolioValuationList(Portfoliovaluation search);

    Portfoliovaluation queryLastQuarterValuation(Portfoliovaluation search);

    Portfoliovaluation queryValuationByFund(Portfoliovaluation search);



}