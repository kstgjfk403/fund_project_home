package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Distributionsource;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface DistributionsourceMapper {
    int deleteByPrimaryKey(@Param("lpdistributionid") String lpdistributionid, @Param("sourcetype") String sourcetype, @Param("sourceid") String sourceid);

    int insert(Distributionsource record);

    List<Distributionsource> selectAll();

    int deleteByDistributionId(String distributionId);
}