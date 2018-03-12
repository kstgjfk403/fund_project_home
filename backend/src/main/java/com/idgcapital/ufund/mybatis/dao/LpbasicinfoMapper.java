package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Capitalcommitment;
import com.idgcapital.ufund.model.Lpbasicinfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LpbasicinfoMapper {
    int deleteByPrimaryKey(String lpid);

    int insert(Lpbasicinfo record);

    Lpbasicinfo selectByPrimaryKey(String lpid);

    List<Lpbasicinfo> selectAll();

    int updateByPrimaryKey(Lpbasicinfo record);

    String selectMaxLPId();

    List<Lpbasicinfo> selectLPEntityList(Lpbasicinfo lpbasicinfo);

}