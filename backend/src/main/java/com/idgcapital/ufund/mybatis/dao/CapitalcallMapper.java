package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Capitalcall;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CapitalcallMapper {
    int deleteByPrimaryKey(String callid);

    int insert(Capitalcall record);

    Capitalcall selectByPrimaryKey(String callid);

    List<Capitalcall> selectAll();

    int updateByPrimaryKey(Capitalcall record);

    List<Map<String,Object>> selectCapitalCallEntityList(Capitalcall record);

    long selectCapitalCallEntityListCount(Capitalcall record);

    String selectMaxCallId(Capitalcall record);


    List<Map<String,Object>> selectLpCommitmentInfoList(Capitalcall record);
}