package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliocaptablevalue;
import com.idgcapital.ufund.model.Portfolioexit;

import java.math.BigDecimal;
import java.util.BitSet;
import java.util.Date;
import java.util.List;

public interface PortfoliocaptablevalueMapper {
    int deleteByPrimaryKey(Integer capid);

    int insert(Portfoliocaptablevalue record);

    Portfoliocaptablevalue selectByPrimaryKey(Integer capid);

    List<Portfoliocaptablevalue> selectAll();

    int updateByPrimaryKey(Portfoliocaptablevalue record);

//    List<Portfoliocaptablevalue> selectALLCapTableByRound();

    List<Portfoliocaptablevalue> selectALLCapTableByRound(String portfolioId);

    List<Portfoliocaptablevalue> selectCapTableByRound(Portfoliocaptablevalue portfoliocaptablevalue);

    List<Portfoliocaptablevalue> selectCapTableByLastedDate(Portfoliocaptablevalue portfoliocaptablevalue);

    Portfoliocaptablevalue selectByPrimaryKeyAndTermDate(Portfoliocaptablevalue portfoliocaptablevalue);

    Portfoliocaptablevalue selectByMaxId();

    List<Portfoliocaptablevalue> queryCapTableInvestorList(Portfoliocaptablevalue portfoliocaptablevalue);

    Date selectMaxTermSignDate(String portfolioId);

    List<Portfoliocaptablevalue> queryCapTableInvestorBeforeList(Portfoliocaptablevalue portfoliocaptablevalue);


    List<Portfoliocaptablevalue> queryTotalCapTableByDetail(Portfoliocaptablevalue portfoliocaptablevalue);

    int deleteTotalCapTable(Portfoliocaptablevalue portfoliocaptablevalue);

    BigDecimal queryPostValue(Portfoliocaptablevalue portfoliocaptablevalue);

    BigDecimal queryProperByFundId(Portfoliocaptablevalue portfoliocaptablevalue);

    List<Portfoliocaptablevalue> queryCapTableList(Portfoliocaptablevalue portfoliocaptablevalue);

    List<Portfoliocaptablevalue> queryCapTableHeaderList(String portfolioId);


}