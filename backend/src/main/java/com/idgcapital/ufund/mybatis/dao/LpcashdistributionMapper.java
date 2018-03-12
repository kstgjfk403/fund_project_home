package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Capitalcall;
import com.idgcapital.ufund.model.Lpcashdistribution;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LpcashdistributionMapper {
    int deleteByPrimaryKey(String lpdistributionid);

    int insert(Lpcashdistribution record);

    Lpcashdistribution selectByPrimaryKey(String lpdistributionid);

    List<Lpcashdistribution> selectAll();

    int updateByPrimaryKey(Lpcashdistribution record);


   /* List<Map<String, Object>> queryPortfolioTrackRecordList(Map<String, Object> map);*/

    List<Map<String,Object>> queryDistritableList(Map<String, Object> map);

    List<Map<String,Object>> selectDistributionEntityList(Lpcashdistribution record);

    long selectDistributionEntityListCount(Lpcashdistribution record);

    String selectMaxDistributionId(Lpcashdistribution record);


}