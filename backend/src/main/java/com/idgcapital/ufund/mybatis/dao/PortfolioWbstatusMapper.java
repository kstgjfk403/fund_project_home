package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.PortfolioWbstatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PortfolioWbstatusMapper {
    int deleteByPrimaryKey(Integer wbid);

    int insert(PortfolioWbstatus record);

    PortfolioWbstatus selectByPrimaryKey(Integer wbid);

    List<PortfolioWbstatus> selectAll();

    List<PortfolioWbstatus> selectByPortfolioIdAndWbdate(PortfolioWbstatus record);

    int updateByPrimaryKey(PortfolioWbstatus record);
}