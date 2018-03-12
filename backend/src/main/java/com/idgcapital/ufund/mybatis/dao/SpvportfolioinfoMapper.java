package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Spvportfolioinfo;
import java.util.List;

public interface SpvportfolioinfoMapper {
    int deleteByPrimaryKey(Integer spvportfolioid);

    int insert(Spvportfolioinfo record);

    Spvportfolioinfo selectByPrimaryKey(Integer spvportfolioid);

    List<Spvportfolioinfo> selectAll();

    int updateByPrimaryKey(Spvportfolioinfo record);

    List<Spvportfolioinfo> selectSpvByPortfolio(Spvportfolioinfo spvportfolioinfo);

    int deleteSPVByPortfolioId(String portfolioid);

}