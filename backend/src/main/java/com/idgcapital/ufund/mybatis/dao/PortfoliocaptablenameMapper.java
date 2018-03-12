package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliocaptablename;
import java.util.List;

public interface PortfoliocaptablenameMapper {
    int deleteByPrimaryKey(Integer shareownerid);

    int insert(Portfoliocaptablename record);

    Portfoliocaptablename selectByPrimaryKey(Integer shareownerid);

    List<Portfoliocaptablename> selectAll();

    int updateByPrimaryKey(Portfoliocaptablename record);

    int selectMaxId();

    List<Portfoliocaptablename> queryShareHolderList(String portfolioId);

    Portfoliocaptablename queryDuplicateCaptableName(Portfoliocaptablename portfoliocaptablename);

    int deleteByPortfolioId(String portfolioId);
}