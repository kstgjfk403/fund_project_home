package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.IdgStaff;
import java.util.List;
import java.util.Map;

public interface IdgStaffMapper {
    int deleteByPrimaryKey(String staffid);

    int insert(IdgStaff record);

    IdgStaff selectByPrimaryKey(String staffid);

    List<IdgStaff> selectAll();

    int updateByPrimaryKey(IdgStaff record);

    IdgStaff selectByUsername(String username);

    List<IdgStaff> selectStaffList();
}