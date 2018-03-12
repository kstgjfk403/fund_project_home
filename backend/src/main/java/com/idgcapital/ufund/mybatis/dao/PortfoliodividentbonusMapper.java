package com.idgcapital.ufund.mybatis.dao;


import com.idgcapital.ufund.model.Portfoliodividentbonus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PortfoliodividentbonusMapper {
    int deleteByPrimaryKey(String dbid);

    int insert(Portfoliodividentbonus record);

    Portfoliodividentbonus selectByPrimaryKey(String dbid);

    List<Portfoliodividentbonus> selectAll();

    int updateByPrimaryKey(Portfoliodividentbonus record);

    List<Portfoliodividentbonus> queryPortfolioDividendListByPortfolioId(String portfolioId);

    List<Portfoliodividentbonus> queryPortfolioBonusShareListByPortfolioId(String portfolioId);

    String selectMaxDbId(Portfoliodividentbonus record);
}