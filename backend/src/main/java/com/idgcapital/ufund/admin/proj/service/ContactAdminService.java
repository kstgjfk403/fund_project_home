package com.idgcapital.ufund.admin.proj.service;

import com.idgcapital.ufund.model.Portfoliocontactorinfo;
import com.idgcapital.ufund.mybatis.dao.PortfoliocontactorinfoMapper;
import com.idgcapital.ufund.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by li_zhao on 2017/12/15.
 */
@Service
public class ContactAdminService {
    @Autowired
    private PortfoliocontactorinfoMapper portfoliocontactorinfoMapper;

    public List<Portfoliocontactorinfo> queryProjContactEntityListByProjId(String portfolioID){
        return portfoliocontactorinfoMapper.selectContactListByProjId(portfolioID);
    }

    public Portfoliocontactorinfo queryProjContactById(Integer id){
        return portfoliocontactorinfoMapper.selectByPrimaryKey(id);
    }

    public int addProjContactInfo(Portfoliocontactorinfo portfoliocontactorinfo, LoginUser loginUser){
        portfoliocontactorinfo.setCreatedby(loginUser.getUserId());
        portfoliocontactorinfo.setCreateddate(new Date());
        portfoliocontactorinfo.setModifiedby(loginUser.getUserId());
        portfoliocontactorinfo.setModifieddate(new Date());
        return portfoliocontactorinfoMapper.insert(portfoliocontactorinfo);
    }

    public int updateProjContactInfo(Portfoliocontactorinfo portfoliocontactorinfo,LoginUser loginUser){
        portfoliocontactorinfo.setModifiedby(loginUser.getUserId());
        portfoliocontactorinfo.setModifieddate(new Date());
        return portfoliocontactorinfoMapper.updateByPrimaryKey(portfoliocontactorinfo);
    }

    public int deleteProjContactEntityById(Integer id){
        return portfoliocontactorinfoMapper.deleteByPrimaryKey(id);
    }
}
