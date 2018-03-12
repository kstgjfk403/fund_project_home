package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Capitalcalldetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CapitalcalldetailMapper {
    int deleteByPrimaryKey(Integer calldetailid);

    int insert(Capitalcalldetail record);

    Capitalcalldetail selectByPrimaryKey(Integer calldetailid);

    List<Capitalcalldetail> selectAll();

    int updateByPrimaryKey(Capitalcalldetail record);

    List<Map<String,Object>> selectCapitalCallDetailEntityList(Capitalcalldetail record);

    long selectCapitalCallDetailEntityListCount(Capitalcalldetail record);

    Integer selectMaxCallDetailId();

    List<Capitalcalldetail> selectCapitalCallDetailListByFundIdAndCallIdAndLpId(Capitalcalldetail record);

    int deleteByCallId(String callid);
}