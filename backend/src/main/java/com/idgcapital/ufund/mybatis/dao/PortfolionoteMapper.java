package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Portfolioequityinvestment;
import com.idgcapital.ufund.model.Portfolioloantoequity;
import com.idgcapital.ufund.model.Portfolionote;
import org.springframework.security.access.method.P;

import java.util.List;
import java.util.Map;

public interface PortfolionoteMapper {
    int deleteByPrimaryKey(String noteid);

    int insert(Portfolionote record);

    Portfolionote selectByPrimaryKey(String noteid);

    List<Portfolionote> selectAll();

    int updateByPrimaryKey(Portfolionote record);

    List<Portfolionote> selectPortfolioNoteListByPortfolioId(String portfolioid);



    String selectMaxPortfolioNoteId();

    List<Portfolionote> selectPortfolioNoteListByFundFamilyName(Portfolioloantoequity portfolioloantoequityInput);

    Portfolionote queryPortfolioNoteId4Convert(Portfolioloantoequity portfolioloantoequityInput);

}