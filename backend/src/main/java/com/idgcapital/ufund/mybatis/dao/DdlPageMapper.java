package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.DdlPage;
import java.util.List;

public interface DdlPageMapper {
    int deleteByPrimaryKey(Integer pageid);

    int insert(DdlPage record);

    DdlPage selectByPrimaryKey(Integer pageid);

    List<DdlPage> selectAll();

    int updateByPrimaryKey(DdlPage record);

    List<DdlPage> selectDDLPage(DdlPage ddlPage);
}