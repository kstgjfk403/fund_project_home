package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliocontactorinfo;
import java.util.List;

public interface PortfoliocontactorinfoMapper {
    int deleteByPrimaryKey(Integer contactid);

    int insert(Portfoliocontactorinfo record);

    Portfoliocontactorinfo selectByPrimaryKey(Integer contactid);

    List<Portfoliocontactorinfo> selectAll();

    List<Portfoliocontactorinfo> selectContactListByProjId(String id);

    int updateByPrimaryKey(Portfoliocontactorinfo record);
}