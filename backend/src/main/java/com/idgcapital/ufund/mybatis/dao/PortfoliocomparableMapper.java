package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliocomparable;
import java.util.List;

public interface PortfoliocomparableMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Portfoliocomparable record);

    Portfoliocomparable selectByPrimaryKey(Integer cid);

    List<Portfoliocomparable> selectAll();

    int updateByPrimaryKey(Portfoliocomparable record);

    List<Portfoliocomparable> queryPortfolioComparableList(Portfoliocomparable search);
}