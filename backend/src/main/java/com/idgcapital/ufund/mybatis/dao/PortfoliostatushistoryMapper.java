package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliostatushistory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PortfoliostatushistoryMapper {
    int deleteByPrimaryKey(Integer logid);

    int insert(Portfoliostatushistory record);

    Portfoliostatushistory selectByPrimaryKey(Integer logid);

    List<Portfoliostatushistory> selectAll();

    int updateByPrimaryKey(Portfoliostatushistory record);

    Portfoliostatushistory selectByPortfolioStatus(Portfoliostatushistory portfoliostatushistory);
}