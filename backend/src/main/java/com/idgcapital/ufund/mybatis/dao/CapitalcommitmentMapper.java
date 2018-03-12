package com.idgcapital.ufund.mybatis.dao;

import com.idgcapital.ufund.model.Capitalcommitment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CapitalcommitmentMapper {
    int deleteByPrimaryKey(Integer commitid);

    int insert(Capitalcommitment record);

    Capitalcommitment selectByPrimaryKey(Integer commitid);

    List<Capitalcommitment> selectAll();

    int updateByPrimaryKey(Capitalcommitment record);

    int updateByPrimaryKeySelective(Capitalcommitment record);


    List<Capitalcommitment> selectCapitalCommitmentEntityListByPara(Capitalcommitment record);

    Integer selectMaxCommitId();

    List<Map<String,Object>> selectCapitalCommitmentEntityListForTransfer(Capitalcommitment record);

}