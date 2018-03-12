package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfolioexitdetail;
import java.util.List;

public interface PortfolioexitdetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Portfolioexitdetail record);

    Portfolioexitdetail selectByPrimaryKey(Integer id);

    List<Portfolioexitdetail> selectAll();

    int updateByPrimaryKey(Portfolioexitdetail record);
}