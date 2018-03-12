package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfoliocaptablevalue;
import com.idgcapital.ufund.model.Portfoliocaptablevaluedetail;
import com.idgcapital.ufund.model.Portfolioequityinvestment;

import java.util.Date;
import java.util.List;

public interface PortfoliocaptablevaluedetailMapper {
    int deleteByPrimaryKey(Integer detailcapid);

    int insert(Portfoliocaptablevaluedetail record);

    Portfoliocaptablevaluedetail selectByPrimaryKey(Integer detailcapid);

    List<Portfoliocaptablevaluedetail> selectAll();

    int updateByPrimaryKey(Portfoliocaptablevaluedetail record);

//    List<Portfoliocaptablevaluedetail> selectALLCapTableByRound();

    List<Portfoliocaptablevaluedetail> selectALLCapTableByRound(String portfolioId);

    Date selectMaxCloseDate(String portfolioId);

    List<Portfoliocaptablevaluedetail> selectCapTableByRound(Portfoliocaptablevaluedetail portfoliocaptablevaluedetail);

    List<Portfoliocaptablevaluedetail> selectCapTableByLastedDate(Portfoliocaptablevaluedetail portfoliocaptablevaluedetail);

    List<Portfoliocaptablevaluedetail> queryCapTableInvestorBeforeList(Portfoliocaptablevaluedetail portfoliocaptablevaluedetail);

    List<Portfoliocaptablevaluedetail> selectCapTableTotalByLastedDate(Portfoliocaptablevaluedetail portfoliocaptablevaluedetail);

    int deleteByEiid(String eiid);

    List<Portfoliocaptablevaluedetail> queryCloseDateHistoryList(Portfolioequityinvestment portfolioequityinvestment);

    List<Portfoliocaptablevaluedetail> queryCaptableListByEiid(String eiid);

    List<Portfoliocaptablevaluedetail> queryDetailListByRound(Portfolioequityinvestment portfolioequityinvestment);

    List<Portfoliocaptablevalue> queryCaptableListByPortfolioId(String portfoloId);

}