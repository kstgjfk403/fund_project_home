package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.PortfolioBov;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PortfolioBovMapper {
    int deleteByPrimaryKey(Integer bovid);

    int insert(PortfolioBov record);

    PortfolioBov selectByPrimaryKey(Integer bovid);

    List<PortfolioBov> selectAll();

    int updateByPrimaryKey(PortfolioBov record);

    List<PortfolioBov> queryPortfolioBovListByPortfolioId(String portfolioId);
}