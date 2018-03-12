package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.DdlGrid;
import java.util.List;

public interface DdlGridMapper {
    int insert(DdlGrid record);

    List<DdlGrid> selectAll();
}