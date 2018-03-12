package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliomember;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PortfoliomemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Portfoliomember record);

    Portfoliomember selectByPrimaryKey(Integer id);

    List<Portfoliomember> selectAll();

    int updateByPrimaryKey(Portfoliomember record);

    int deleteByProjId(Portfoliomember portfoliomember);
}