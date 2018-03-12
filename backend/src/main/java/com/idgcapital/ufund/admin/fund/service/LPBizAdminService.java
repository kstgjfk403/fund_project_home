package com.idgcapital.ufund.admin.fund.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idgcapital.ufund.common.util.StringUtil;
import com.idgcapital.ufund.model.Capitalcommitment;
import com.idgcapital.ufund.model.Fundbasicinfo;
import com.idgcapital.ufund.model.Lpbasicinfo;
import com.idgcapital.ufund.model.Portfoliofinancialinfo;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.mybatis.dao.CapitalcommitmentMapper;
import com.idgcapital.ufund.mybatis.dao.LpbasicinfoMapper;
import com.idgcapital.ufund.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by li_zhao on 2017/12/19.
 */
@Service
public class LPBizAdminService {
    @Autowired
    private LpbasicinfoMapper lpbasicinfoMapper;
    @Autowired
    private CapitalcommitmentMapper capitalcommitmentMapper;

  /*  public List<Lpbasicinfo> queryLPEntityList(Lpbasicinfo lpbasicinfo){
        return lpbasicinfoMapper.selectAll();
    }*/
    public PageInfo queryLPEntityList(Lpbasicinfo lpbasicinfo){
       /* JsonResult jsonResult = new JsonResult();

        int pageStart = (lpbasicinfo.getPageIndex()-1)*lpbasicinfo.getPageCount();
        int pageEnd = lpbasicinfo.getPageIndex()*lpbasicinfo.getPageCount();
        lpbasicinfo.setPageStart(pageStart);
        lpbasicinfo.setPageEnd(pageEnd);

        List<Lpbasicinfo> fundbasicinfoList = lpbasicinfoMapper.queryLPEntityList(lpbasicinfo);

        long count = lpbasicinfoMapper.queryLPEntityListCount(lpbasicinfo);

        jsonResult.setData(fundbasicinfoList);
        jsonResult.setCount(count);

        return jsonResult;*/
        PageHelper.startPage(lpbasicinfo.getPageIndex(), lpbasicinfo.getPageCount());

        List<Lpbasicinfo> list = lpbasicinfoMapper.selectLPEntityList(lpbasicinfo);
        PageInfo<Lpbasicinfo> pageInfo = new PageInfo<>(list);
        return pageInfo;

    }



    public List<Lpbasicinfo> queryLpEntityListWithoutPage(){
        return lpbasicinfoMapper.selectAll();
    }

    public Lpbasicinfo queryLPEntityById(String id){
        return lpbasicinfoMapper.selectByPrimaryKey(id);
    }

    public int addLPEntity(Lpbasicinfo lpbasicinfo, LoginUser loginUser){
        String lpId = getLPId();

        lpbasicinfo.setLpid(lpId);
        lpbasicinfo.setCreatedby(loginUser.getUserId());
        lpbasicinfo.setCreateddate(new Date());
        lpbasicinfo.setModifiedby(loginUser.getUserId());
        lpbasicinfo.setModifieddate(new Date());

        lpbasicinfoMapper.insert(lpbasicinfo);

        return 1;
    }

    public int updateLPEntityById(Lpbasicinfo lpbasicinfo,LoginUser loginUser){
        lpbasicinfo.setModifieddate(new Date());
        lpbasicinfo.setModifiedby(loginUser.getUserId());

        lpbasicinfoMapper.updateByPrimaryKey(lpbasicinfo);
        return 1;
    }

    public int deleteLPById(String id){
        return lpbasicinfoMapper.deleteByPrimaryKey(id);
    }


    public String getLPId(){
        String maxLPId = lpbasicinfoMapper.selectMaxLPId();
        String maxId = maxLPId.substring(maxLPId.indexOf("I")+1);
        int id = Integer.parseInt(maxId)+1;
        return "I"+ StringUtil.autoCompleteZero(String.valueOf(id),5);
    }



    public int addCapitalCommitment(Capitalcommitment capitalcommitment){
        return capitalcommitmentMapper.insert(capitalcommitment);
    }

    public Capitalcommitment queryCapitalCommitmentDetail(Integer id){
        return capitalcommitmentMapper.selectByPrimaryKey(id);
    }

    public Integer updateCapitalCommitment(Capitalcommitment capitalcommitment){
        return capitalcommitmentMapper.updateByPrimaryKeySelective(capitalcommitment);
    }


    public int deleteCapitalCommitment(Integer id){
        return capitalcommitmentMapper.deleteByPrimaryKey(id);
    }


    public PageInfo queryCapitalCommitmentEntityList(Capitalcommitment capitalcommitment){

        PageHelper.startPage(capitalcommitment.getPageIndex(), capitalcommitment.getPageCount());

        List<Capitalcommitment> list = capitalcommitmentMapper.selectCapitalCommitmentEntityListByPara(capitalcommitment);
        PageInfo<Capitalcommitment> pageInfo = new PageInfo<>(list);
        return pageInfo;

        //return capitalcommitmentMapper.selectCapitalCommitmentEntityListByPara(capitalcommitment);
    }

    public Capitalcommitment queryCapitalCommitmentEntityById(Integer id){
        return capitalcommitmentMapper.selectByPrimaryKey(id);
    }

    //addCapitalCommitment

    public int addCapitalCommitment(Capitalcommitment capitalcommitment, LoginUser loginUser){
        Integer commitId = getCommitId();

        capitalcommitment.setCommitid(commitId);
        capitalcommitment.setCreatedby(loginUser.getUserId());
        capitalcommitment.setCreateddate(new Date());
        capitalcommitment.setModifiedby(loginUser.getUserId());
        capitalcommitment.setModifieddate(new Date());

        capitalcommitmentMapper.insert(capitalcommitment);

        return 1;
    }

    public int addCapitalCommitmentTransfer(Capitalcommitment capitalcommitment, LoginUser loginUser){


        Integer commitId = getCommitId();

        capitalcommitment.setCommitid(commitId);
        capitalcommitment.setCreatedby(loginUser.getUserId());
        capitalcommitment.setCreateddate(new Date());
        capitalcommitment.setModifiedby(loginUser.getUserId());
        capitalcommitment.setModifieddate(new Date());

        Capitalcommitment tagetCapitalcommitment = new Capitalcommitment();


        if(capitalcommitment!=null && capitalcommitment.getTransfertype()!=null && "toExist".equals(capitalcommitment.getTransfertype())){

            Integer targetCommitId = getCommitId();
            tagetCapitalcommitment = new Capitalcommitment();
            tagetCapitalcommitment.setCommitid(targetCommitId);
            tagetCapitalcommitment.setLpid(capitalcommitment.getTransfertolpid());
            tagetCapitalcommitment.setFundid(capitalcommitment.getFundid());
            tagetCapitalcommitment.setCommitmentnum(capitalcommitment.getTransferamount());
            tagetCapitalcommitment.setCommitmentdate(capitalcommitment.getTransferdate());
            tagetCapitalcommitment.setTransferfromlpid(capitalcommitment.getLpid());
            tagetCapitalcommitment.setTransfertolpid(capitalcommitment.getTransfertolpid());
            tagetCapitalcommitment.setTransferdate(capitalcommitment.getTransferdate());
            tagetCapitalcommitment.setTransferamount(capitalcommitment.getTransferamount());
            tagetCapitalcommitment.setComment("transfer" +capitalcommitment.getTransferamount().toString()+" From " + capitalcommitment.getLpid());

            tagetCapitalcommitment.setCreatedby(loginUser.getUserId());
            tagetCapitalcommitment.setCreateddate(new Date());
            tagetCapitalcommitment.setModifiedby(loginUser.getUserId());
            tagetCapitalcommitment.setModifieddate(new Date());
            tagetCapitalcommitment.setApproved(true);



            capitalcommitment.setCommitmentnum(new BigDecimal("0.00").subtract(capitalcommitment.getTransferamount()));
            capitalcommitment.setCommitmentdate(capitalcommitment.getTransferdate());
            capitalcommitment.setTransferfromlpid(capitalcommitment.getLpid());
            capitalcommitment.setComment("transfer" +capitalcommitment.getTransferamount().toString()+" To " + capitalcommitment.getTransfertolpid());

        }else if(capitalcommitment!=null && capitalcommitment.getTransfertype()!=null && "toNew".equals(capitalcommitment.getTransfertype())){
            //先插入一个LPBasicinfo类型变量
            Lpbasicinfo lpbasicinfo = new Lpbasicinfo();
            String lpId = getLPId();

            lpbasicinfo.setLpid(lpId);
            lpbasicinfo.setLplegalfullname(capitalcommitment.getTransfertolpname());
            lpbasicinfo.setCreatedby(loginUser.getUserId());
            lpbasicinfo.setCreateddate(new Date());
            lpbasicinfo.setModifiedby(loginUser.getUserId());
            lpbasicinfo.setModifieddate(new Date());

            lpbasicinfoMapper.insert(lpbasicinfo);


            Integer targetCommitId = getCommitId();
            tagetCapitalcommitment = new Capitalcommitment();
            tagetCapitalcommitment.setCommitid(targetCommitId);
            tagetCapitalcommitment.setLpid(lpbasicinfo.getLpid());
            tagetCapitalcommitment.setFundid(capitalcommitment.getFundid());
            tagetCapitalcommitment.setCommitmentnum(capitalcommitment.getTransferamount());
            tagetCapitalcommitment.setCommitmentdate(capitalcommitment.getTransferdate());
            tagetCapitalcommitment.setTransferfromlpid(capitalcommitment.getLpid());
            tagetCapitalcommitment.setTransfertolpid(lpbasicinfo.getLpid());
            tagetCapitalcommitment.setTransferdate(capitalcommitment.getTransferdate());
            tagetCapitalcommitment.setTransferamount(capitalcommitment.getTransferamount());
            tagetCapitalcommitment.setComment("transfer" +capitalcommitment.getTransferamount().toString()+" From " + capitalcommitment.getLpid());

            tagetCapitalcommitment.setCreatedby(loginUser.getUserId());
            tagetCapitalcommitment.setCreateddate(new Date());
            tagetCapitalcommitment.setModifiedby(loginUser.getUserId());
            tagetCapitalcommitment.setModifieddate(new Date());
            tagetCapitalcommitment.setApproved(true);


            capitalcommitment.setTransfertolpid(lpbasicinfo.getLpid());
            capitalcommitment.setCommitmentnum(new BigDecimal("0.00").subtract(capitalcommitment.getTransferamount()));
            capitalcommitment.setCommitmentdate(capitalcommitment.getTransferdate());
            capitalcommitment.setTransferfromlpid(capitalcommitment.getLpid());
            capitalcommitment.setComment("transfer" +capitalcommitment.getTransferamount().toString()+" To " + capitalcommitment.getTransfertolpid());

        }


        capitalcommitmentMapper.insert(capitalcommitment);
        capitalcommitmentMapper.insert(tagetCapitalcommitment);

        return 1;
    }


    public int updateCapitalCommitmentById(Capitalcommitment capitalcommitment,LoginUser loginUser){
        capitalcommitment.setModifieddate(new Date());
        capitalcommitment.setModifiedby(loginUser.getUserId());

        capitalcommitmentMapper.updateByPrimaryKey(capitalcommitment);
        return 1;
    }

    public int deleteCapitalCommitmentById(Integer id){
        return capitalcommitmentMapper.deleteByPrimaryKey(id);
    }


    public Integer getCommitId(){
        Integer maxCommitId = capitalcommitmentMapper.selectMaxCommitId();
        int id = maxCommitId.intValue()+1;
        return id;
    }

    public List<Map<String,Object>> queryCapitalCommitmentEntityListForTransfer(Capitalcommitment capitalcommitment){
        return capitalcommitmentMapper.selectCapitalCommitmentEntityListForTransfer(capitalcommitment);
    }
}
