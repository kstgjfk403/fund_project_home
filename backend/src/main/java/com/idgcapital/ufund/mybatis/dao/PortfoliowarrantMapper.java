package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliowarrant;
import java.util.List;

public interface PortfoliowarrantMapper {
    int deleteByPrimaryKey(String warrantid);

    int insert(Portfoliowarrant record);

    Portfoliowarrant selectByPrimaryKey(String warrantid);

    List<Portfoliowarrant> selectAll();

    int updateByPrimaryKey(Portfoliowarrant record);

    List<Portfoliowarrant> queryPortfolioWarrantListByPortfolioId(String portfolioId);

    String selectMaxPortfolioWarrantId();

    List<Portfoliowarrant> queryWarrantRemainByFundFamilyName(Portfoliowarrant portfoliowarrant);
}