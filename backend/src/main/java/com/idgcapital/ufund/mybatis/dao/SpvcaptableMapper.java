package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Spvcaptable;
import java.util.List;

public interface SpvcaptableMapper {
    int deleteByPrimaryKey(Integer capid);

    int insert(Spvcaptable record);

    Spvcaptable selectByPrimaryKey(Integer capid);

    List<Spvcaptable> selectAll();

    int updateByPrimaryKey(Spvcaptable record);

    List<Spvcaptable> selectInvestorListBySPVId(Spvcaptable spvcaptable);

    int deleteInvestorListBySPVId(Spvcaptable spvcaptable);
}