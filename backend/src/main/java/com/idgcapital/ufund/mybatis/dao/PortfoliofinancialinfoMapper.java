package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliofinancialinfo;
import java.util.List;

public interface PortfoliofinancialinfoMapper {
    int deleteByPrimaryKey(Integer portfoliofinanciallot);

    int insert(Portfoliofinancialinfo record);

    Portfoliofinancialinfo selectByPrimaryKey(Integer portfoliofinanciallot);

    List<Portfoliofinancialinfo> selectAll();

    int updateByPrimaryKey(Portfoliofinancialinfo record);

    int updateByPrimaryKeySelective(Portfoliofinancialinfo record);

    Portfoliofinancialinfo queryFinancialData(Portfoliofinancialinfo search);

    List<Portfoliofinancialinfo> queryFinancialDataList(Portfoliofinancialinfo s);


}