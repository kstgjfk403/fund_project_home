package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Lpdistributiondetail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LpdistributiondetailMapper {
    int deleteByPrimaryKey(Integer distributiondetailid);

    int insert(Lpdistributiondetail record);

    Lpdistributiondetail selectByPrimaryKey(Integer distributiondetailid);

    List<Lpdistributiondetail> selectAll();

    int updateByPrimaryKey(Lpdistributiondetail record);

    Integer selectMaxDistribtuionDetailId();

    int deleteByDistributionId(String distributionId);
}