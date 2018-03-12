package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Spvinfo;
import java.util.List;
import java.util.Map;

public interface SpvinfoMapper {
    int deleteByPrimaryKey(String spvid);

    int insert(Spvinfo record);

    Spvinfo selectByPrimaryKey(String spvid);

    List<Spvinfo> selectAll();

    int updateByPrimaryKey(Spvinfo record);

    String selectMaxSPVId();

    List<Spvinfo> selectSPVEntityList(Spvinfo spvinfo);

    int selectSPVEntityCount(Spvinfo spvinfo);

    List<Map<String,Object>> selectSPVList();

    List<Spvinfo> selectBySPVParentId(String parentId);

}