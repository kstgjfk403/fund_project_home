package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Fundbasicinfo;
import java.util.List;
import java.util.Map;

public interface FundbasicinfoMapper {
    int deleteByPrimaryKey(String fundid);

    int deleteFundById(String fundid);

    int insert(Fundbasicinfo record);

    Fundbasicinfo selectByPrimaryKey(String fundid);

    List<Fundbasicinfo> selectAll();

    int updateByPrimaryKey(Fundbasicinfo record);

    String selectMaxFundId();

    List<Fundbasicinfo> selectFundEntityList(Fundbasicinfo fundbasicinfo);

    List<Fundbasicinfo> selectFundEntityListWithoutPage();

    long selectFundEntityCount(Fundbasicinfo fundbasicinfo);

    List<Map<String,Object>> selectFundList();

    List<Map<String,Object>> selectFundFamilyList();

    List<Fundbasicinfo> selectFundByFamily(String family);
}