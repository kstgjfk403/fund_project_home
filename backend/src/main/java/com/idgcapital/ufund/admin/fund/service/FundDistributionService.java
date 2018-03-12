package com.idgcapital.ufund.admin.fund.service;

import com.idgcapital.ufund.common.util.DateUtil;
import com.idgcapital.ufund.common.util.StringUtil;
import com.idgcapital.ufund.model.*;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.mybatis.dao.CapitalcallMapper;
import com.idgcapital.ufund.mybatis.dao.DistributionsourceMapper;
import com.idgcapital.ufund.mybatis.dao.LpcashdistributionMapper;
import com.idgcapital.ufund.mybatis.dao.LpdistributiondetailMapper;
import com.idgcapital.ufund.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tiantian_yu on 2018/1/19.
 */
@Service
public class FundDistributionService {
    @Autowired
    private LpcashdistributionMapper lpcashdistributionMapper;

    @Autowired
    private LpdistributiondetailMapper lpdistributiondetailMapper;

    @Autowired
    private DistributionsourceMapper distributionsourceMapper;

    @Autowired
    private CapitalcallMapper capitalCallMapper;
/*    @Autowired
    private FundfininfoMapper fundFinInfoMapper;*/


    //DistributableList
    public List<Map<String,Object>> queryDistributableList(Map<String, Object> paraMap){

        List<Map<String,Object>> distributableList = new ArrayList<Map<String,Object>>();

        distributableList = lpcashdistributionMapper.queryDistritableList(paraMap);


        return distributableList;
    }

    public JsonResult queryDistributionEntityList(Lpcashdistribution lpcashdistribution){
        JsonResult jsonResult = new JsonResult();

        int pageStart = (lpcashdistribution.getPageIndex()-1)*lpcashdistribution.getPageCount();
        int pageEnd = lpcashdistribution.getPageIndex()*lpcashdistribution.getPageCount();
        lpcashdistribution.setPageStart(pageStart);
        lpcashdistribution.setPageEnd(pageEnd);

        //List<Map<String,Object>> capitalCallInfoList = capitalCallMapper.selectFundEntityList(fundbasicinfo);
        List<Map<String,Object>> distibutionInfoList = lpcashdistributionMapper.selectDistributionEntityList(lpcashdistribution);

        long count = lpcashdistributionMapper.selectDistributionEntityListCount(lpcashdistribution);

        jsonResult.setData(distibutionInfoList);
        jsonResult.setCount(count);

        return jsonResult;
    }

    public int addFundDistribution(Lpcashdistribution lpcashdistribution, LoginUser loginUser){

        String lpDistributionID = getDistributionId(lpcashdistribution);
        // String fundId = getFundId();

        lpcashdistribution.setLpdistributionid(lpDistributionID);
        lpcashdistribution.setApproved(true);
        lpcashdistribution.setCreatedby(loginUser.getUserId());
        lpcashdistribution.setCreateddate(new Date());
        lpcashdistribution.setModifiedby(loginUser.getUserId());
        lpcashdistribution.setModifieddate(new Date());
        lpcashdistributionMapper.insert(lpcashdistribution);

        Capitalcall capitalcall = new Capitalcall();
        capitalcall.setFundid(lpcashdistribution.getFundid());

        List<Map<String,Object>> LpCommitmentInfoList = capitalCallMapper.selectLpCommitmentInfoList(capitalcall);
        for(Map<String,Object> tmpMap:LpCommitmentInfoList){
            Lpdistributiondetail lpDistributionDetail = new Lpdistributiondetail();
            Integer distributionDetailId = getDistributionDetailId();
            lpDistributionDetail.setDistributiondetailid(distributionDetailId);
            lpDistributionDetail.setLpdistributionid(lpcashdistribution.getLpdistributionid());
            lpDistributionDetail.setLpid(tmpMap.get("LPID").toString());
            lpDistributionDetail.setFundid(lpcashdistribution.getFundid());

            BigDecimal distributionAmount =
                    ((BigDecimal)tmpMap.get("LPCommitmentNum")).divide((BigDecimal)tmpMap.get("totalCommitmentNum")).multiply((BigDecimal)lpcashdistribution.getTotaldistributionamount());
            lpDistributionDetail.setDistributionamount(distributionAmount);
            lpDistributionDetail.setEmailsend(false);
            lpDistributionDetail.setEmailsenddatetime(null);
            lpDistributionDetail.setEmailto("");
            lpDistributionDetail.setEmailbc("");
            lpDistributionDetail.setEmailcc("");
            Date currentDate = new Date();
            lpDistributionDetail.setSubject("Cash Distribution Notice for " + tmpMap.get("fundFullNameEng").toString() + "("
                                           + DateUtil.getWeedDay(currentDate) + ", " + DateUtil.dateToEnStringLongFormat(currentDate) );

            lpDistributionDetail.setEmailcontent("");
            //lpDistributionDetail.setAttachment(null);
            lpDistributionDetail.setCreatedby(loginUser.getUserId());
            lpDistributionDetail.setCreateddate(new Date());
            lpDistributionDetail.setEmailreviewed(false);
            lpDistributionDetail.setApproved(true);

            lpdistributiondetailMapper.insert(lpDistributionDetail);

        }
        //插入分配来源表
        String sourceIdListStr = lpcashdistribution.getSourceIdListStr();
        String sourceTypeListStr = lpcashdistribution.getSourceTypeListStr();
        String[] sourceIdArray ;
        String[] sourceTypeArray ;
        if(sourceIdListStr!=null && !"".equals(sourceIdListStr)){
            sourceIdArray = sourceIdListStr.split(",");
        }else{
            sourceIdArray = new String[0];
        }
        if(sourceTypeListStr!=null && !"".equals(sourceTypeListStr)){
            sourceTypeArray = sourceTypeListStr.split(",");
        }else{
            sourceTypeArray = new String[0];
        }
        for(int i=0; i<sourceIdArray.length; i++){
            Distributionsource distributionsource = new Distributionsource();
            distributionsource.setLpdistributionid(lpcashdistribution.getLpdistributionid());
            distributionsource.setSourceid(sourceIdArray[i]);
            distributionsource.setSourcetype(sourceTypeArray[i]);
            distributionsourceMapper.insert(distributionsource);
        }



        return 1;
    }

    public int updateFundDistribution(Lpcashdistribution lpcashdistribution, LoginUser loginUser){

        lpcashdistribution.setModifiedby(loginUser.getUserId());
        lpcashdistribution.setModifieddate(new Date());
        int result = lpcashdistributionMapper.updateByPrimaryKey(lpcashdistribution);

        /* 不再对下面的capitalCallLp进行重新分配
        List<Map<String,Object>> LpCommitmentInfoList = capitalCallMapper.selectLpCommitmentInfoList(capitalcall);
        for(Map<String,Object> tmpMap:LpCommitmentInfoList){
            Capitalcalldetail paramCapitalCallDetail = new Capitalcalldetail();
            paramCapitalCallDetail.setFundid(capitalcall.getFundid());
            paramCapitalCallDetail.setCallid(capitalcall.getCallid());
            paramCapitalCallDetail.setLpid(tmpMap.get("LPID").toString());
            List<Capitalcalldetail> capitalcalldetailList = capitalCalldetailMapper.selectCapitalCallDetailListByFundIdAndCallIdAndLpId(paramCapitalCallDetail);
            Capitalcalldetail capitalcalldetail = new Capitalcalldetail();
            if(capitalcalldetailList!=null && capitalcalldetailList.size()>0){
                capitalcalldetail= capitalcalldetailList.get(0);
            }


            BigDecimal calledNum = ((BigDecimal)tmpMap.get("LPCommitmentNum")).multiply((BigDecimal)capitalcall.getCalledpecentage().multiply((BigDecimal)tmpMap.get("totalCommitmentNum")))
                    .divide((BigDecimal)tmpMap.get("totalCommitmentNum"));
            capitalcalldetail.setCallednum(calledNum.multiply(new BigDecimal("0.01")));
            capitalcalldetail.setModifiedby(loginUser.getUserId());
            capitalcalldetail.setModifieddate(new Date());

            capitalCalldetailMapper.updateByPrimaryKey(capitalcalldetail);
        }
        */
        return  result;
    }
    public int deleteFundDistributionById(String id){
        lpdistributiondetailMapper.deleteByDistributionId(id);
        distributionsourceMapper.deleteByDistributionId(id);

        return lpcashdistributionMapper.deleteByPrimaryKey(id);
    }

    public int updateLpDistributionDetail(Lpdistributiondetail lpdistributiondetail, LoginUser loginUser){

        lpdistributiondetail.setModifiedby(loginUser.getUserId());
        lpdistributiondetail.setModifieddate(new Date());
        int result = lpdistributiondetailMapper.updateByPrimaryKey(lpdistributiondetail);

        /* 不再对下面的capitalCallLp进行重新分配
        List<Map<String,Object>> LpCommitmentInfoList = capitalCallMapper.selectLpCommitmentInfoList(capitalcall);
        for(Map<String,Object> tmpMap:LpCommitmentInfoList){
            Capitalcalldetail paramCapitalCallDetail = new Capitalcalldetail();
            paramCapitalCallDetail.setFundid(capitalcall.getFundid());
            paramCapitalCallDetail.setCallid(capitalcall.getCallid());
            paramCapitalCallDetail.setLpid(tmpMap.get("LPID").toString());
            List<Capitalcalldetail> capitalcalldetailList = capitalCalldetailMapper.selectCapitalCallDetailListByFundIdAndCallIdAndLpId(paramCapitalCallDetail);
            Capitalcalldetail capitalcalldetail = new Capitalcalldetail();
            if(capitalcalldetailList!=null && capitalcalldetailList.size()>0){
                capitalcalldetail= capitalcalldetailList.get(0);
            }


            BigDecimal calledNum = ((BigDecimal)tmpMap.get("LPCommitmentNum")).multiply((BigDecimal)capitalcall.getCalledpecentage().multiply((BigDecimal)tmpMap.get("totalCommitmentNum")))
                    .divide((BigDecimal)tmpMap.get("totalCommitmentNum"));
            capitalcalldetail.setCallednum(calledNum.multiply(new BigDecimal("0.01")));
            capitalcalldetail.setModifiedby(loginUser.getUserId());
            capitalcalldetail.setModifieddate(new Date());

            capitalCalldetailMapper.updateByPrimaryKey(capitalcalldetail);
        }
        */
        return  result;
    }
    public int deleteLpDistributionDetailById(Integer id){

        return lpdistributiondetailMapper.deleteByPrimaryKey(id);
    }


    public String getDistributionId(Lpcashdistribution lpcashdistribution){
        Lpcashdistribution paramLpcashdistribution = new Lpcashdistribution();
        paramLpcashdistribution.setFundid(lpcashdistribution.getFundid());
        String maxDistributionId = lpcashdistributionMapper.selectMaxDistributionId(lpcashdistribution);
        String maxId = "";
        if(maxDistributionId == null || "".equals(maxDistributionId)){
            maxId = "0";
        }else{
            maxId = maxDistributionId.substring(maxDistributionId.indexOf("D")+1);
        }

        int id = Integer.parseInt(maxId)+1;
        return lpcashdistribution.getFundid() + "_D"+ StringUtil.autoCompleteZero(String.valueOf(id),2);
    }

    public Integer getDistributionDetailId(){
        Integer maxDistributionDetailId = lpdistributiondetailMapper.selectMaxDistribtuionDetailId();
        int id = maxDistributionDetailId.intValue()+1;
        return id;
    }

}
