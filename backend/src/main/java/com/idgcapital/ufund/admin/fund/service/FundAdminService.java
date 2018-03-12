package com.idgcapital.ufund.admin.fund.service;

import com.idgcapital.ufund.common.util.StringUtil;
import com.idgcapital.ufund.model.Fundbasicinfo;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.mybatis.dao.FundbasicinfoMapper;
import com.idgcapital.ufund.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by li_zhao on 2017/12/13.
 */
@Service
public class FundAdminService {
    @Autowired
    private FundbasicinfoMapper fundbasicinfoMapper;

    public JsonResult queryFundEntityList(Fundbasicinfo fundbasicinfo){
        JsonResult jsonResult = new JsonResult();

        int pageStart = (fundbasicinfo.getPageIndex()-1)*fundbasicinfo.getPageCount();
        int pageEnd = fundbasicinfo.getPageIndex()*fundbasicinfo.getPageCount();
        fundbasicinfo.setPageStart(pageStart);
        fundbasicinfo.setPageEnd(pageEnd);

        List<Fundbasicinfo> fundbasicinfoList = fundbasicinfoMapper.selectFundEntityList(fundbasicinfo);

        long count = fundbasicinfoMapper.selectFundEntityCount(fundbasicinfo);

        jsonResult.setData(fundbasicinfoList);
        jsonResult.setCount(count);

        return jsonResult;
    }

    public List<Fundbasicinfo> queryFundEntityListWithoutPage(){

        List<Fundbasicinfo> fundbasicinfoList = fundbasicinfoMapper.selectFundEntityListWithoutPage();
        return fundbasicinfoList;
    }

    public Fundbasicinfo queryFundEntityById(String id){
        return fundbasicinfoMapper.selectByPrimaryKey(id);
    }

    public int addFundEntity(Fundbasicinfo fundbasicinfo, LoginUser loginUser){
        String fundId = getFundId();

        fundbasicinfo.setFundid(fundId);
        fundbasicinfo.setValidflag("1");
        fundbasicinfo.setCreatedby(loginUser.getUserId());
        fundbasicinfo.setCreateddate(new Date());
        fundbasicinfo.setModifiedby(loginUser.getUserId());
        fundbasicinfo.setModifieddate(new Date());

        fundbasicinfoMapper.insert(fundbasicinfo);

        return 1;
    }

    public int updateFundEntity(Fundbasicinfo fundbasicinfo,LoginUser loginUser){
        fundbasicinfo.setModifieddate(new Date());
        fundbasicinfo.setModifiedby(loginUser.getUserId());
        fundbasicinfoMapper.updateByPrimaryKey(fundbasicinfo);
        return 1;
    }

    public int deleteFundEntityById(String id){
        return fundbasicinfoMapper.deleteFundById(id);
    }

    public String getFundId(){
        String maxFundId = fundbasicinfoMapper.selectMaxFundId();
        String maxId = maxFundId.substring(maxFundId.indexOf("F")+1);
        int id = Integer.parseInt(maxId)+1;
        return "F"+ StringUtil.autoCompleteZero(String.valueOf(id),4);
    }

}
