package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Exchangeratedetail;
import java.util.List;
import java.util.Map;

public interface ExchangeratedetailMapper {
    int deleteByPrimaryKey(Long eid);

    int insert(Exchangeratedetail record);

    Exchangeratedetail selectByPrimaryKey(Long eid);

    List<Exchangeratedetail> selectAll();

    int updateByPrimaryKey(Exchangeratedetail record);


    List<Exchangeratedetail> queryExchangeRateList(Map<String,Object> param);

    List<String> getAllCurrency();
}