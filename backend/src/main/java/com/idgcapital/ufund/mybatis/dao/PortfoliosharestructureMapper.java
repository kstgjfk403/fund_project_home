package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliosharestructure;
import java.util.List;

public interface PortfoliosharestructureMapper {
    int deleteByPrimaryKey(Integer sharecaptialid);

    int insert(Portfoliosharestructure record);

    Portfoliosharestructure selectByPrimaryKey(Integer sharecaptialid);

    List<Portfoliosharestructure> selectAll();

    int updateByPrimaryKey(Portfoliosharestructure record);

    List<Portfoliosharestructure> selectCapTableByRound(Portfoliosharestructure portfoliosharestructure);
}