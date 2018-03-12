package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.IdgStaffright;
import java.util.List;

public interface IdgStaffrightMapper {
    int deleteByPrimaryKey(Integer rightid);

    int insert(IdgStaffright record);

    IdgStaffright selectByPrimaryKey(Integer rightid);

    List<IdgStaffright> selectAll();

    int updateByPrimaryKey(IdgStaffright record);
}