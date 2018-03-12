package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfolioloantoequity;
import com.idgcapital.ufund.model.Portfolionote;

import java.math.BigDecimal;
import java.util.BitSet;
import java.util.List;

public interface PortfolioloantoequityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Portfolioloantoequity record);

    Portfolioloantoequity selectByPrimaryKey(Integer id);

    List<Portfolioloantoequity> selectAll();

    int updateByPrimaryKey(Portfolioloantoequity record);

    List<Portfolioloantoequity> selectLoanToEquityListByFundFamilyName(Portfolioloantoequity portfolioloantoequityInput);

    List<Portfolioloantoequity> selectLoanToEquityListByEiid(String eiid);

    Integer deleteByEiid(String eiid);

    BigDecimal selectByNoteId(String noteId);
}