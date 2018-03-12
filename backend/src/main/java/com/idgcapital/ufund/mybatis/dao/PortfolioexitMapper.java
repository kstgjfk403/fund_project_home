package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfolioexit;
import com.idgcapital.ufund.model.PortfolioexitShare;

import java.math.BigDecimal;
import java.util.BitSet;
import java.util.List;

public interface PortfolioexitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Portfolioexit record);

    Portfolioexit selectByPrimaryKey(Integer id);

    List<Portfolioexit> selectAll();

    int updateByPrimaryKey(Portfolioexit record);

    List<Portfolioexit> selectPortfolioExitListByPortfolioId(String portfolioId);

    List<PortfolioexitShare> selectShareListByFundFamily(PortfolioexitShare portfolioexitShare);

    List<PortfolioexitShare> selectShareListByFundId(PortfolioexitShare portfolioexitShare);

    List<Portfolioexit> queryRemainSharesByFundId(Portfolioexit portfolioexit);

    BigDecimal queryUnrealizedCostByFundId(Portfolioexit portfolioexit);

    int selectMaxExitId();
}