package com.idgcapital.ufund.admin.fund.service;

import com.idgcapital.ufund.common.model.FundIrrStasticInfo;
import com.idgcapital.ufund.common.util.DateUtil;
import com.idgcapital.ufund.common.util.IrrUtil;
import com.idgcapital.ufund.common.util.StringUtil;
import com.idgcapital.ufund.model.Capitalcall;
import com.idgcapital.ufund.model.Capitalcalldetail;
import com.idgcapital.ufund.model.Fundbasicinfo;
import com.idgcapital.ufund.model.Fundfininfo;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.mybatis.dao.CapitalcallMapper;
import com.idgcapital.ufund.mybatis.dao.CapitalcalldetailMapper;
import com.idgcapital.ufund.mybatis.dao.FundfininfoMapper;
import com.idgcapital.ufund.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static java.math.BigDecimal.ROUND_HALF_DOWN;
import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Created by tiantian_yu on 2018/1/19.
 */
@Service
public class FundCapitalCallService {
    @Autowired
    private CapitalcallMapper capitalCallMapper;

    @Autowired
    private CapitalcalldetailMapper capitalCalldetailMapper;

    @Autowired
    private FundfininfoMapper fundFinInfoMapper;

    public JsonResult queryCapitalCallEntityList(Capitalcall capitalcall){
        JsonResult jsonResult = new JsonResult();

        int pageStart = (capitalcall.getPageIndex()-1)*capitalcall.getPageCount();
        int pageEnd = capitalcall.getPageIndex()*capitalcall.getPageCount();
        capitalcall.setPageStart(pageStart);
        capitalcall.setPageEnd(pageEnd);

        //List<Map<String,Object>> capitalCallInfoList = capitalCallMapper.selectFundEntityList(fundbasicinfo);
        List<Map<String,Object>> capitalCallInfoList = capitalCallMapper.selectCapitalCallEntityList(capitalcall);

        long count = capitalCallMapper.selectCapitalCallEntityListCount(capitalcall);

        jsonResult.setData(capitalCallInfoList);
        jsonResult.setCount(count);

        return jsonResult;
    }

    public int addFundCapitalCall(Capitalcall capitalcall, LoginUser loginUser){

        String callId = getCapitalCallId(capitalcall);
       // String fundId = getFundId();

        capitalcall.setCallid(callId);
        capitalcall.setApproved(true);
        capitalcall.setCreatedby(loginUser.getUserId());
        capitalcall.setCreateddate(new Date());
        capitalcall.setModifiedby(loginUser.getUserId());
        capitalcall.setModifieddate(new Date());
        capitalCallMapper.insert(capitalcall);

        List<Map<String,Object>> LpCommitmentInfoList = capitalCallMapper.selectLpCommitmentInfoList(capitalcall);
        for(Map<String,Object> tmpMap:LpCommitmentInfoList){
            Capitalcalldetail capitalcalldetail = new Capitalcalldetail();
            Integer callDetailId = getCapitalCallDetailId();
            capitalcalldetail.setCalldetailid(callDetailId);
            capitalcalldetail.setCallid(capitalcall.getCallid());
            capitalcalldetail.setLpid(tmpMap.get("LPID").toString());
            capitalcalldetail.setFundid(capitalcall.getFundid());

            BigDecimal calledNum = ((BigDecimal)tmpMap.get("LPCommitmentNum")).multiply((BigDecimal)capitalcall.getCalledpecentage().multiply((BigDecimal)tmpMap.get("totalCommitmentNum")))
                                                                              .divide((BigDecimal)tmpMap.get("totalCommitmentNum"));
            capitalcalldetail.setCallednum(calledNum.multiply(new BigDecimal("0.01")));
            capitalcalldetail.setCreatedby(loginUser.getUserId());
            capitalcalldetail.setCreateddate(new Date());
            capitalcalldetail.setModifiedby(loginUser.getUserId());
            capitalcalldetail.setModifieddate(new Date());
            capitalcalldetail.setEmailsend(false);
            capitalcalldetail.setEmailsenddatetime(null);
            capitalcalldetail.setMoneyreceived(false);
            capitalcalldetail.setReceivedate(null);
            capitalcalldetail.setErisa(false);
            capitalcalldetail.setEmailreviewed(false);
            capitalcalldetail.setReceivenum(new BigDecimal(0.00));
            capitalCalldetailMapper.insert(capitalcalldetail);
        }
        return 1;
    }
    public int updateFundCapitalCall(Capitalcall capitalcall, LoginUser loginUser){

        capitalcall.setModifiedby(loginUser.getUserId());
        capitalcall.setModifieddate(new Date());
        int result = capitalCallMapper.updateByPrimaryKey(capitalcall);

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
    public int deleteFundCapitalCallById(String id){
        capitalCalldetailMapper.deleteByCallId(id);

        return capitalCallMapper.deleteByPrimaryKey(id);
    }


    public Capitalcall queryFundCapitalCallEntityById(String id){
        return capitalCallMapper.selectByPrimaryKey(id);
    }

    public Capitalcalldetail queryCapitalCallLpEntityById(Integer id){
        return capitalCalldetailMapper.selectByPrimaryKey(id);
    }


    public String getCapitalCallId(Capitalcall capitalcall){
        Capitalcall paramCapitalCall = new Capitalcall();
        paramCapitalCall.setFundid(capitalcall.getFundid());
        String maxCallId = capitalCallMapper.selectMaxCallId(paramCapitalCall);
        int id = 0;
        if(maxCallId==null){
            id = 1;
        }else{
            String maxId = maxCallId.substring(maxCallId.indexOf("C")+1);
            id = Integer.parseInt(maxId)+1;
        }

        return capitalcall.getFundid() + "_C"+ StringUtil.autoCompleteZero(String.valueOf(id),2);
    }


    public Integer getCapitalCallDetailId(){
        Integer maxCallDetailId = capitalCalldetailMapper.selectMaxCallDetailId();
        int id = maxCallDetailId.intValue()+1;
        return id;
    }


    public JsonResult queryCapitalCallLpEntityList(Capitalcalldetail capitalcalldetail){
        JsonResult jsonResult = new JsonResult();

        int pageStart = (capitalcalldetail.getPageIndex()-1)*capitalcalldetail.getPageCount();
        int pageEnd = capitalcalldetail.getPageIndex()*capitalcalldetail.getPageCount();
        capitalcalldetail.setPageStart(pageStart);
        capitalcalldetail.setPageEnd(pageEnd);

        //List<Map<String,Object>> capitalCallInfoList = capitalCallMapper.selectFundEntityList(fundbasicinfo);
        List<Map<String,Object>> capitalCallDetailInfoList = capitalCalldetailMapper.selectCapitalCallDetailEntityList(capitalcalldetail);

        long count = capitalCalldetailMapper.selectCapitalCallDetailEntityListCount(capitalcalldetail);

        jsonResult.setData(capitalCallDetailInfoList);
        jsonResult.setCount(count);

        return jsonResult;
    }

    public int updateFundCapitalCallLpDetail(Capitalcalldetail capitalcalldetail, LoginUser loginUser){

        capitalcalldetail.setModifiedby(loginUser.getUserId());
        capitalcalldetail.setModifieddate(new Date());
        return capitalCalldetailMapper.updateByPrimaryKey(capitalcalldetail);
    }

    public FundIrrStasticInfo queryFundIrrStastic(Map<String,Object> paraMap){
        FundIrrStasticInfo resultFundIrrStasticInfo = new FundIrrStasticInfo();

        Date curDate = new Date();
        //Date finDate = getSignDateFromCurDate(curDate);
        Date finDate = (Date)paraMap.get("date");
        String fundId = paraMap.get("fundid").toString();
        Fundfininfo fundFinInfo = new Fundfininfo();
        fundFinInfo.setFundid(fundId);
        List<Fundfininfo> allQuarterFundFinInfoList = fundFinInfoMapper.queryFundFinInfoListByFundIdAndFinDate(fundFinInfo);//某个基金所有的季度末报表
        fundFinInfo.setFindate(finDate);
        List<Fundfininfo> resultFundFinInfoList = fundFinInfoMapper.queryFundFinInfoListByFundIdAndFinDate(fundFinInfo);
        Fundfininfo lastFundFinInfo = new Fundfininfo();
        if(resultFundFinInfoList!=null && resultFundFinInfoList.size()>0){
            lastFundFinInfo = resultFundFinInfoList.get(0);
        }
        /*暂时不做
        else if(allQuarterFundFinInfoList!=null && allQuarterFundFinInfoList.size()>0){
            lastFundFinInfo = allQuarterFundFinInfoList.get(0);
        }
        */

        else{
            return resultFundIrrStasticInfo;//没有基金季度报表信息
        }
        resultFundIrrStasticInfo.setDateStr(DateUtil.dateToEnStringLongFormat(lastFundFinInfo.getFindate()));//以最近一次存储的fundFinDate作为截止日期
        resultFundIrrStasticInfo.setLpNetAssets(lastFundFinInfo.getLpnetassets());
        resultFundIrrStasticInfo.setNetAssets(lastFundFinInfo.getNetassets());

        List<Double> cashValues = new ArrayList<Double>();
        List<Integer> cashDates = new ArrayList<Integer>();
        List<Double> grossCashValues = new ArrayList<Double>();
        List<Integer> grossCashDates = new ArrayList<Integer>();

        //这是lpNetIRR
        Map<String, Object> lpNetAssetsValueMap = new HashMap<String, Object>();
        lpNetAssetsValueMap.put("cashDate", lastFundFinInfo.getFindate());
        lpNetAssetsValueMap.put("cashValue", lastFundFinInfo.getLpnetassets());
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("fundid",fundId);
        paramMap.put("findate", lastFundFinInfo.getFindate());
        List<Map<String, Object>> cashList = fundFinInfoMapper.queryLpCashListByFundId(paramMap);
        cashList.add(lpNetAssetsValueMap);
        for(Map<String, Object> cashMap : cashList){

            if(cashMap.get("cashValue") != null){

                BigDecimal bigDecimal = (BigDecimal) cashMap.get("cashValue");
                String cashValueStr = bigDecimal.toString();

                cashValues.add(Double.valueOf(cashValueStr));
                int cashDate = IrrUtil.getExcelDateValue((Date)cashMap.get("cashDate"));
                cashDates.add(cashDate);
            }
        }
        List<Map<String,Object>>  lpCashFlowInfoArr = new ArrayList<Map<String,Object>>();
        if(cashValues != null && cashValues.size() > 0 && cashDates != null && cashDates.size() > 0){

            List<Double> flowTrueArr = new ArrayList<Double>();
            flowTrueArr = IrrUtil.processFlowArray(cashValues, cashDates);

            Double currentirr = IrrUtil.getIrr(flowTrueArr);
            System.out.println(currentirr);
            Double currentseasonIRR = 1+currentirr;
            System.out.println(currentseasonIRR);
            Double currentyearIRR = Math.pow(currentseasonIRR, 4) ;
            currentyearIRR = currentyearIRR-1;
            resultFundIrrStasticInfo.setIrrLp(new BigDecimal(currentyearIRR).setScale(4,ROUND_HALF_UP));
            resultFundIrrStasticInfo.setIrrLpStr(new BigDecimal(currentyearIRR).setScale(4,ROUND_HALF_UP).multiply(new BigDecimal(100.00)).setScale(2,ROUND_HALF_UP).toString()+"%");

            lpCashFlowInfoArr = IrrUtil.processLpCashFlowInfoArray(cashValues, cashDates);
            for(Map<String,Object> tmpMap:lpCashFlowInfoArr){
                Date startDate = IrrUtil.getPreQuarterDateByThisQuarterDate((Date)tmpMap.get("date"));
                tmpMap.put("startDate",startDate);
                tmpMap.put("fundid", fundId);
                Map<String,Object> gpCarryMap = fundFinInfoMapper.queryGpCarryByMap(tmpMap);
                if(gpCarryMap!=null){
                    tmpMap.put("gpCarry", (BigDecimal)gpCarryMap.get("cashValue"));
                }
                tmpMap.put("date",DateUtil.dateToEnStringLongFormat((Date)tmpMap.get("date")));
            }

        }
        /*
        <td>{{item.capitalCalledStr}}</td>
              <td>{{item.distribution}}</td>
              <td>{{item.gpCarry}}</td>
              <td>{{item.fundNav}}</td>
              <td>{{item.netCashFlowsStr}}</td>
         */
        BigDecimal totalCapitalCall = new BigDecimal(0.00);
        BigDecimal totalDistribution = new BigDecimal(0.00);
        BigDecimal totalGpCarry = new BigDecimal(0.00);
        BigDecimal totalFundNav = new BigDecimal(0.00);
        BigDecimal totalNetCashFlow = new BigDecimal(0.00);
        for(Map<String,Object> lpCashFlowInfo:lpCashFlowInfoArr){
            totalCapitalCall = totalCapitalCall.add(lpCashFlowInfo.get("capitalCalled")==null?new BigDecimal(0.00):(BigDecimal)lpCashFlowInfo.get("capitalCalled"));
            totalDistribution = totalDistribution.add(lpCashFlowInfo.get("distribution")==null?new BigDecimal(0.00):(BigDecimal)lpCashFlowInfo.get("distribution"));
            totalGpCarry = totalGpCarry.add(lpCashFlowInfo.get("gpCarry")==null?new BigDecimal(0.00):(BigDecimal)lpCashFlowInfo.get("gpCarry"));
            totalFundNav = totalFundNav.add(lpCashFlowInfo.get("fundNav")==null?new BigDecimal(0.00):(BigDecimal)lpCashFlowInfo.get("fundNav"));
            totalNetCashFlow = totalNetCashFlow.add(lpCashFlowInfo.get("netCashFlows")==null?new BigDecimal(0.00):(BigDecimal)lpCashFlowInfo.get("netCashFlows"));
        }
        Map<String,Object> totalMap = new HashMap<String,Object>();
        totalMap.put("capitalCalledStr",totalCapitalCall);
        totalMap.put("distribution",totalDistribution);
        totalMap.put("gpCarry",totalGpCarry);
        totalMap.put("fundNav",totalFundNav);
        totalMap.put("netCashFlowsStr",totalNetCashFlow);
        lpCashFlowInfoArr.add(totalMap);
        resultFundIrrStasticInfo.setLpCashFlowList(lpCashFlowInfoArr);
        //下面是grossIRR
        Map<String, Object> netAssetsValueMap = new HashMap<String, Object>();
        netAssetsValueMap.put("cashDate", lastFundFinInfo.getFindate());
        netAssetsValueMap.put("cashValue", lastFundFinInfo.getNetassets());

        List<Map<String, Object>> grossCashList = fundFinInfoMapper.queryGrossCashListByFundId(paramMap);
        grossCashList.add(netAssetsValueMap);
        for(Map<String, Object> cashMap : grossCashList){

            if(cashMap.get("cashValue") != null){

                BigDecimal bigDecimal = (BigDecimal) cashMap.get("cashValue");
                String cashValueStr = bigDecimal.toString();

                grossCashValues.add(Double.valueOf(cashValueStr));
                int cashDate = IrrUtil.getExcelDateValue((Date)cashMap.get("cashDate"));
                grossCashDates.add(cashDate);
            }
        }
        List<Map<String,Object>>  grossCashFlowInfoArr = new ArrayList<Map<String,Object>>();
        if(grossCashValues != null && grossCashValues.size() > 0 && grossCashDates != null && grossCashDates.size() > 0){

            List<Double> flowTrueArr = new ArrayList<Double>();
            flowTrueArr = IrrUtil.processFlowArray(grossCashValues, grossCashDates);

            Double currentirr = IrrUtil.getIrr(flowTrueArr);
            System.out.println(currentirr);
            Double currentseasonIRR = 1+currentirr;
            System.out.println(currentseasonIRR);
            Double currentyearIRR = Math.pow(currentseasonIRR, 4) ;
            currentyearIRR = currentyearIRR-1;
            resultFundIrrStasticInfo.setIrrLpAndGp(new BigDecimal(currentyearIRR).setScale(4,ROUND_HALF_UP));
            resultFundIrrStasticInfo.setIrrLpAndGpStr(new BigDecimal(currentyearIRR).setScale(4,ROUND_HALF_UP).multiply(new BigDecimal(100.00)).setScale(2,ROUND_HALF_UP).toString()+"%");

            grossCashFlowInfoArr = IrrUtil.processGrossCashFlowInfoArray(grossCashValues, grossCashDates);

        }

        totalCapitalCall = new BigDecimal(0.00);
        totalDistribution = new BigDecimal(0.00);
        totalGpCarry = new BigDecimal(0.00);
        totalFundNav = new BigDecimal(0.00);
        totalNetCashFlow = new BigDecimal(0.00);
        for(Map<String,Object> cashFlowInfo:grossCashFlowInfoArr){
            cashFlowInfo.put("date",DateUtil.dateToEnStringLongFormat((Date)cashFlowInfo.get("date")));
            totalCapitalCall = totalCapitalCall.add(cashFlowInfo.get("capitalCalled")==null?new BigDecimal(0.00):(BigDecimal)cashFlowInfo.get("capitalCalled"));
            totalDistribution = totalDistribution.add(cashFlowInfo.get("distribution")==null?new BigDecimal(0.00):(BigDecimal)cashFlowInfo.get("distribution"));
            totalGpCarry = totalGpCarry.add(cashFlowInfo.get("gpCarry")==null?new BigDecimal(0.00):(BigDecimal)cashFlowInfo.get("gpCarry"));
            totalFundNav = totalFundNav.add(cashFlowInfo.get("fundNav")==null?new BigDecimal(0.00):(BigDecimal)cashFlowInfo.get("fundNav"));
            totalNetCashFlow = totalNetCashFlow.add(cashFlowInfo.get("netCashFlows")==null?new BigDecimal(0.00):(BigDecimal)cashFlowInfo.get("netCashFlows"));
        }
        Map<String,Object> grossTotalMap = new HashMap<String,Object>();
        grossTotalMap.put("capitalCalledStr",totalCapitalCall);
        grossTotalMap.put("distribution",totalDistribution);
        grossTotalMap.put("gpCarry",totalGpCarry);
        grossTotalMap.put("fundNav",totalFundNav);
        grossTotalMap.put("netCashFlowsStr",totalNetCashFlow);
        grossCashFlowInfoArr.add(grossTotalMap);
        resultFundIrrStasticInfo.setLpAndGpCashFlowList(grossCashFlowInfoArr);

        calculateSummerInfo(resultFundIrrStasticInfo);

        return resultFundIrrStasticInfo;
    }
    public void calculateSummerInfo(FundIrrStasticInfo fundIrrStasticInfo){
        List<Map<String,Object>> lpCashFlowList = fundIrrStasticInfo.getLpCashFlowList();
        List<Map<String,Object>> lpAndGpCashFlowList = fundIrrStasticInfo.getLpAndGpCashFlowList();
        /*fundIrrStasticInfo.setTotalCapitalCallsLp();
        fundIrrStasticInfo.setTotalDistributionsLp();
        fundIrrStasticInfo.setTotalCapitalCallsLpAndGp();
        fundIrrStasticInfo.setTotalDistributionsLpAndGp();
        fundIrrStasticInfo.setTotalGpCarry();
        fundIrrStasticInfo.setMocLp();
        fundIrrStasticInfo.setMocLpAndGp();
        fundIrrStasticInfo.setDpiLp();
        fundIrrStasticInfo.setDpiLpAndGp();
        */

        BigDecimal totalCapitalCallsLp = new BigDecimal(0.00);
        BigDecimal totalDistributionsLp = new BigDecimal(0.00);
        BigDecimal totalCapitalCallsLpAndGp = new BigDecimal(0.00);
        BigDecimal totalDistributionsLpAndGp = new BigDecimal(0.00);
        BigDecimal totalGpCarry = new BigDecimal(0.00);

        BigDecimal mocLp = new BigDecimal(0.00);
        BigDecimal mocLpAndGp = new BigDecimal(0.00);
        BigDecimal dpiLp = new BigDecimal(0.00);
        BigDecimal dpiLpAndGp = new BigDecimal(0.00);

        for(Map<String,Object> lpCashFlow : lpCashFlowList){
            if(lpCashFlow.get("date")!=null){
                totalCapitalCallsLp = totalCapitalCallsLp.add((lpCashFlow.get("capitalCalled")==null?new BigDecimal(0.00):(BigDecimal)lpCashFlow.get("capitalCalled")));
                totalDistributionsLp = totalDistributionsLp.add((lpCashFlow.get("distribution")==null?new BigDecimal(0.00):(BigDecimal)lpCashFlow.get("distribution")));
                totalGpCarry = totalGpCarry.add((lpCashFlow.get("gpCarry")==null?new BigDecimal(0.00):(BigDecimal)lpCashFlow.get("gpCarry")));

            }
        }
        for(Map<String,Object> lpAndGpCashFlow : lpAndGpCashFlowList) {
            if (lpAndGpCashFlow.get("date") != null) {
                totalCapitalCallsLpAndGp = totalCapitalCallsLpAndGp.add((lpAndGpCashFlow.get("capitalCalled") == null ? new BigDecimal(0.00) : (BigDecimal) lpAndGpCashFlow.get("capitalCalled")));
                totalDistributionsLpAndGp = totalDistributionsLpAndGp.add((lpAndGpCashFlow.get("distribution") == null ? new BigDecimal(0.00) : (BigDecimal) lpAndGpCashFlow.get("distribution")));
            }
        }
        mocLp = totalDistributionsLp.add(fundIrrStasticInfo.getLpNetAssets()==null?new BigDecimal(0.00):fundIrrStasticInfo.getLpNetAssets())
                .divide(totalCapitalCallsLp,10, ROUND_HALF_DOWN);
        mocLpAndGp = totalDistributionsLpAndGp.add(fundIrrStasticInfo.getNetAssets()==null?new BigDecimal(0.00):fundIrrStasticInfo.getNetAssets())
                .divide(totalCapitalCallsLpAndGp,10, ROUND_HALF_DOWN);
        dpiLp = totalDistributionsLp.divide(totalCapitalCallsLp,10, ROUND_HALF_DOWN);
        dpiLpAndGp = totalDistributionsLpAndGp.divide(totalCapitalCallsLpAndGp,10, ROUND_HALF_DOWN);
        fundIrrStasticInfo.setTotalCapitalCallsLp(totalCapitalCallsLp.abs().setScale(2,ROUND_HALF_UP));
        fundIrrStasticInfo.setTotalCapitalCallsLpStr(totalCapitalCallsLp.abs().setScale(2,ROUND_HALF_UP).toString());
        fundIrrStasticInfo.setTotalDistributionsLp(totalDistributionsLp.abs().setScale(2,ROUND_HALF_UP));
        fundIrrStasticInfo.setTotalCapitalCallsLpAndGp(totalCapitalCallsLpAndGp.abs().setScale(2,ROUND_HALF_UP));
        fundIrrStasticInfo.setTotalCapitalCallsLpAndGpStr(totalCapitalCallsLpAndGp.abs().setScale(2,ROUND_HALF_UP).toString());
        fundIrrStasticInfo.setTotalDistributionsLpAndGp(totalDistributionsLpAndGp.abs().setScale(2,ROUND_HALF_UP));
        fundIrrStasticInfo.setTotalGpCarry(totalGpCarry.abs().setScale(2,ROUND_HALF_UP));
        fundIrrStasticInfo.setMocLp(mocLp.abs().setScale(2,ROUND_HALF_UP));
        fundIrrStasticInfo.setMocLpStr(mocLp.abs().setScale(2,ROUND_HALF_UP).toString());
        fundIrrStasticInfo.setMocLpAndGp(mocLpAndGp.abs().setScale(2,ROUND_HALF_UP));
        fundIrrStasticInfo.setMocLpAndGpStr(mocLpAndGp.abs().setScale(2,ROUND_HALF_UP).toString());
        fundIrrStasticInfo.setDpiLp(dpiLp.abs().setScale(2,ROUND_HALF_UP));
        fundIrrStasticInfo.setDpiLpStr(dpiLp.abs().setScale(2,ROUND_HALF_UP).toString());
        fundIrrStasticInfo.setDpiLpAndGp(dpiLpAndGp.abs().setScale(2,ROUND_HALF_UP));
        fundIrrStasticInfo.setDpiLpAndGpStr(dpiLpAndGp.abs().setScale(2,ROUND_HALF_UP).toString());

    }

    public Date getSignDateFromCurDate(Date curDate){
        Date signDate = new Date();
        if(DateUtil.getQuarter(curDate)==1){
            signDate =DateUtil.stringtoDate(DateUtil.getYear(curDate)-1 + "-12-31", DateUtil.LONG_DATE_FORMAT) ;
        }else if(DateUtil.getQuarter(curDate)==2){
            signDate =DateUtil.stringtoDate(DateUtil.getYear(curDate) + "-03-31", DateUtil.LONG_DATE_FORMAT) ;
        }else if(DateUtil.getQuarter(curDate)==3){
            signDate =DateUtil.stringtoDate(DateUtil.getYear(curDate) + "-06-30", DateUtil.LONG_DATE_FORMAT) ;
        }else if(DateUtil.getQuarter(curDate)==4){
            signDate =DateUtil.stringtoDate(DateUtil.getYear(curDate) + "-09-30", DateUtil.LONG_DATE_FORMAT) ;
        }
        return signDate;
    }
    /*public Fundbasicinfo queryFundEntityById(String id){
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
    }*/

    public List<Map<String,Object>> queryFundInvestmentOverview(Map<String, Object> origParaMap){
        List<Map<String,Object>> fundInvestmentOverviewList = new ArrayList<Map<String,Object>>();
        Map<String,Object> paraMap = new HashMap<String, Object>();
        String fundId = origParaMap.get("fundid").toString();
        Date date = (Date)origParaMap.get("date");
        paraMap.put("fundId",fundId);
        paraMap.put("date",date);
        fundInvestmentOverviewList = fundFinInfoMapper.queryFundInvestmentOverview(paraMap);
        for(Map<String,Object> tmpMap:fundInvestmentOverviewList){
          /*if((Boolean)tmpMap.get("IDGOnBoard")){
              tmpMap.put("boardSeatsStr", "是");
          }else{
              tmpMap.put("boardSeatsStr", "否");
          }*/
            Map<String,Object> tmpParaMap = new HashMap<String,Object>();
            tmpParaMap.put("portfolioId", tmpMap.get("portfolioID").toString());
            tmpParaMap.put("fundId", fundId);
            tmpParaMap.put("date", date);
            List<Map<String,Object>> portfolioExitList = fundFinInfoMapper.queryPortfolioExitList(tmpParaMap);
            if(portfolioExitList==null || portfolioExitList.size()==0){
                tmpMap.put("status","Unrealised");
                tmpMap.put("portfolioStatusStr","Unrealised");
            }else{
                if(portfolioExitList.get(0).get("exitType").toString().equals("Write Off")){
                    tmpMap.put("status","Written Off");
                    tmpMap.put("portfolioStatusStr","Written Off");
                }else if(((BigDecimal)(portfolioExitList.get(0).get("shareRemain"))).equals(new BigDecimal(0.00))){
                    tmpMap.put("status","Realised");
                    tmpMap.put("portfolioStatusStr","Realised");
                }else{
                    tmpMap.put("status","Partially Realised");
                    tmpMap.put("portfolioStatusStr","Partially Realised");
                }
            }
        }

        return fundInvestmentOverviewList;
    }

    public Map<String,Object> queryFundTrackRecord(Map<String, Object> paraMap){
        Map<String,Object> resultFundTrackRecord = new HashMap<String,Object>();
        List<Map<String,Object>> portfolioTrackRecordList = new ArrayList<Map<String,Object>>();

        portfolioTrackRecordList = fundFinInfoMapper.queryPortfolioTrackRecordList(paraMap);

        for(Map<String,Object> tmpMap:portfolioTrackRecordList){
            Map<String,Object> tmpParaMap = new HashMap<String,Object>();
            tmpParaMap.put("portfolioId", tmpMap.get("portfolioID").toString());
            tmpParaMap.put("fundId", paraMap.get("fundid").toString());
            tmpParaMap.put("date", (Date)paraMap.get("date"));
            List<Map<String,Object>> portfolioExitList = fundFinInfoMapper.queryPortfolioExitList(tmpParaMap);
            if(portfolioExitList==null || portfolioExitList.size()==0){
                tmpMap.put("status","Unrealised");
            }else{
                if(portfolioExitList.get(0).get("exitType").toString().equals("Write Off")){
                    tmpMap.put("status","Written Off");
                }else if(((BigDecimal)(portfolioExitList.get(0).get("shareRemain"))).equals(new BigDecimal(0.00))){
                    tmpMap.put("status","Realised");
                }else{
                    tmpMap.put("status","Partially Realised");
                }
            }
            List<Map<String,Object>> portfolioValuationList = fundFinInfoMapper.queryPortfolioValuationList(tmpParaMap);
            if(portfolioValuationList==null || portfolioValuationList.size()==0){
                tmpMap.put("unRealisedProceeds",new BigDecimal(0.00));
            }else{
                tmpMap.put("unRealisedProceeds",(BigDecimal)(portfolioValuationList.get(0).get("Valuation")));
            }
            BigDecimal irr = calculatePortfolioIrr(tmpParaMap);
            tmpMap.put("irr",irr);
            tmpMap.put("totalValue",((BigDecimal)tmpMap.get("realisedProceeds")).add((BigDecimal)tmpMap.get("unRealisedProceeds")));
            tmpMap.put("moc",((BigDecimal)tmpMap.get("totalValue")).divide((BigDecimal)tmpMap.get("investedCost"),2,ROUND_HALF_DOWN));
        }
        List<Map<String,Object>> summationPortfolioTrackRecordList = processSummation(resultFundTrackRecord, portfolioTrackRecordList);//做汇总
        portfolioTrackRecordList.addAll(summationPortfolioTrackRecordList);
        resultFundTrackRecord.put("portfolioTrackRecordList",portfolioTrackRecordList);
        return resultFundTrackRecord;
    }
    public BigDecimal calculatePortfolioIrr(Map<String, Object> paraMap){
        List<Map<String,Object>> portfolioValuationList = fundFinInfoMapper.queryPortfolioValuationListOrderByDateDesc(paraMap);
        if(portfolioValuationList==null || portfolioValuationList.size()==0){
            return new BigDecimal(0.00);
        }else{
            if(portfolioValuationList.get(0).get("valuationDate")!=null){
                paraMap.put("endDate", (Date)portfolioValuationList.get(0).get("valuationDate"));
            }else{
                paraMap.put("endDate", paraMap.get("date"));
            }
        }

        List<Map<String,Object>> cashList = fundFinInfoMapper.queryPortfolioCashList(paraMap);
        //计算项目IRR
        if(cashList!=null && cashList.size()>0){
            List<BigDecimal> portfolioCashValues = new ArrayList<BigDecimal>();
            List<Long> portfolioCashDates = new ArrayList<Long>();
            Date startDate = (Date)cashList.get(0).get("cashDate");
            Date endDate = (Date)cashList.get(cashList.size()-1).get("cashDate");
            for(Map<String, Object> cashMap : cashList){

                if(cashMap.get("cashValue") != null){

                    BigDecimal cashValue = (BigDecimal) cashMap.get("cashValue");


                    portfolioCashValues.add(cashValue);
                    long cashDateIndex = IrrUtil.getExcelDateValueForPortfolio(startDate, endDate,(Date)cashMap.get("cashDate"));
                    portfolioCashDates.add(cashDateIndex);
                }
            }
            List<Map<String,Object>>  grossCashFlowInfoArr = new ArrayList<Map<String,Object>>();
            if(portfolioCashValues != null && portfolioCashValues.size() > 0 && portfolioCashDates != null && portfolioCashDates.size() > 0){

                List<BigDecimal> flowTrueArr = new ArrayList<BigDecimal>();
                //flowTrueArr = IrrUtil.processFlowArray(portfolioCashValues, portfolioCashDates);
                flowTrueArr = IrrUtil.processFlowArrayForPortfolio(portfolioCashValues, portfolioCashDates);
                double[] flowDoubleArr = new double[flowTrueArr.size()];
                List<Double> flowTrueList = new ArrayList<Double>();
                for(int i =0 ; i<flowTrueArr.size(); i++){
                    flowDoubleArr[i] =flowTrueArr.get(i).doubleValue();
                    flowTrueList.add(flowTrueArr.get(i).doubleValue());

                }
                //double currentirr = IrrUtil.getIrr(flowTrueList); 效率稍慢
                double currentirr = IrrUtil.irr(flowDoubleArr);
                System.out.println(currentirr);
                double currentDayIRR = currentirr+1.00;
                System.out.println(currentDayIRR);
                double currentyearIRR = Math.pow(currentDayIRR,365);
                return new BigDecimal(currentyearIRR);


            }else{
                return new BigDecimal(0.00);
            }
        }else{
            return new BigDecimal(0.00);
        }
    }


    public List<Map<String,Object>> processSummation(Map<String, Object> resultFundTrackRecord, List<Map<String,Object>> portfolioTrackRecordList){
        List<Map<String,Object>> summationPortfolioTrackRecordList = new ArrayList<Map<String,Object>>();

        BigDecimal totalInvestedCost = new BigDecimal(0.00);
        BigDecimal totalInvestedCostUnrealised = new BigDecimal(0.00);
        BigDecimal totalInvestedCostPartRealised = new BigDecimal(0.00);
        BigDecimal totalInvestedCostRealised = new BigDecimal(0.00);
        BigDecimal totalInvestedCostWrittenOff = new BigDecimal(0.00);

        BigDecimal totalDistributions = new BigDecimal(0.00);
        BigDecimal totalDistributionsUnrealised = new BigDecimal(0.00);
        BigDecimal totalDistributionsPartRealised = new BigDecimal(0.00);
        BigDecimal totalDistributionsRealised = new BigDecimal(0.00);
        BigDecimal totalDistributionsWrittenOff = new BigDecimal(0.00);

        BigDecimal totalUnrealisedDistributions = new BigDecimal(0.00);
        BigDecimal totalUnrealisedDistributionsUnrealised = new BigDecimal(0.00);
        BigDecimal totalUnrealisedDistributionsPartRealised = new BigDecimal(0.00);
        BigDecimal totalUnrealisedDistributionsRealised = new BigDecimal(0.00);
        BigDecimal totalUnrealisedDistributionsWrittenOff = new BigDecimal(0.00);

        BigDecimal totalValue = new BigDecimal(0.00);
        BigDecimal totalValueUnrealised = new BigDecimal(0.00);
        BigDecimal totalValuePartRealised = new BigDecimal(0.00);
        BigDecimal totalValueRealised = new BigDecimal(0.00);
        BigDecimal totalValueWrittenOff = new BigDecimal(0.00);


        for(Map<String,Object> tmpMap:portfolioTrackRecordList){
            totalInvestedCost = totalInvestedCost.add((BigDecimal)(tmpMap.get("investedCost")==null?new BigDecimal(0.00):tmpMap.get("investedCost")));
            totalDistributions = totalDistributions.add((BigDecimal)(tmpMap.get("realisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("realisedProceeds")));
            totalUnrealisedDistributions = totalUnrealisedDistributions.add((BigDecimal)(tmpMap.get("unRealisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("unRealisedProceeds")));
            totalValue = totalValue.add((BigDecimal)(tmpMap.get("totalValue")==null?new BigDecimal(0.00):tmpMap.get("totalValue")));
            if("Unrealised".equals(tmpMap.get("status").toString())){
                totalInvestedCostUnrealised = totalInvestedCostUnrealised.add((BigDecimal)(tmpMap.get("investedCost")==null?new BigDecimal(0.00):tmpMap.get("investedCost")));
                totalDistributionsUnrealised = totalDistributionsUnrealised.add((BigDecimal)(tmpMap.get("realisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("realisedProceeds")));
                totalUnrealisedDistributionsUnrealised = totalUnrealisedDistributionsUnrealised.add((BigDecimal)(tmpMap.get("unRealisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("unRealisedProceeds")));
                totalValueUnrealised = totalValueUnrealised.add((BigDecimal)(tmpMap.get("totalValue")==null?new BigDecimal(0.00):tmpMap.get("totalValue")));
            }else if("Partially Realised".equals(tmpMap.get("status").toString())){
                totalInvestedCostPartRealised = totalInvestedCostPartRealised.add((BigDecimal)(tmpMap.get("investedCost")==null?new BigDecimal(0.00):tmpMap.get("investedCost")));
                totalDistributionsPartRealised = totalDistributionsPartRealised.add((BigDecimal)(tmpMap.get("realisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("realisedProceeds")));
                totalUnrealisedDistributionsPartRealised = totalUnrealisedDistributionsPartRealised.add((BigDecimal)(tmpMap.get("unRealisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("unRealisedProceeds")));
                totalValuePartRealised = totalValuePartRealised.add((BigDecimal)(tmpMap.get("totalValue")==null?new BigDecimal(0.00):tmpMap.get("totalValue")));
            }else if("Realised".equals(tmpMap.get("status").toString())){
                totalInvestedCostRealised = totalInvestedCostRealised.add((BigDecimal)(tmpMap.get("investedCost")==null?new BigDecimal(0.00):tmpMap.get("investedCost")));
                totalDistributionsRealised = totalDistributionsRealised.add((BigDecimal)(tmpMap.get("realisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("realisedProceeds")));
                totalUnrealisedDistributionsRealised = totalUnrealisedDistributionsRealised.add((BigDecimal)(tmpMap.get("unRealisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("unRealisedProceeds")));
                totalValueRealised = totalValueRealised.add((BigDecimal)(tmpMap.get("totalValue")==null?new BigDecimal(0.00):tmpMap.get("totalValue")));
            }else if("Written Off".equals(tmpMap.get("status").toString())){
                totalInvestedCostWrittenOff = totalInvestedCostWrittenOff.add((BigDecimal)(tmpMap.get("investedCost")==null?new BigDecimal(0.00):tmpMap.get("investedCost")));
                totalDistributionsWrittenOff = totalDistributionsWrittenOff.add((BigDecimal)(tmpMap.get("realisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("realisedProceeds")));
                totalUnrealisedDistributionsWrittenOff = totalUnrealisedDistributionsWrittenOff.add((BigDecimal)(tmpMap.get("unRealisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("unRealisedProceeds")));
                totalValueWrittenOff = totalValueWrittenOff.add((BigDecimal)(tmpMap.get("totalValue")==null?new BigDecimal(0.00):tmpMap.get("totalValue")));
            }
        }
        resultFundTrackRecord.put("totalInvestedCost",totalInvestedCost);
        resultFundTrackRecord.put("totalInvestedCostUnrealised",totalInvestedCostUnrealised);
        resultFundTrackRecord.put("totalInvestedCostPartRealised",totalInvestedCostPartRealised);
        resultFundTrackRecord.put("totalInvestedCostRealised",totalInvestedCostRealised);
        resultFundTrackRecord.put("totalInvestedCostWrittenOff",totalInvestedCostWrittenOff);

        resultFundTrackRecord.put("totalDistributions",totalDistributions);
        resultFundTrackRecord.put("totalDistributionsUnrealised",totalDistributionsUnrealised);
        resultFundTrackRecord.put("totalDistributionsPartRealised",totalDistributionsPartRealised);
        resultFundTrackRecord.put("totalDistributionsRealised",totalDistributionsRealised);
        resultFundTrackRecord.put("totalDistributionsWrittenOff",totalDistributionsWrittenOff);

        resultFundTrackRecord.put("totalUnrealisedDistributions",totalUnrealisedDistributions);
        resultFundTrackRecord.put("totalUnrealisedDistributionsUnrealised",totalUnrealisedDistributionsUnrealised);
        resultFundTrackRecord.put("totalUnrealisedDistributionsPartRealised",totalUnrealisedDistributionsPartRealised);
        resultFundTrackRecord.put("totalUnrealisedDistributionsRealised",totalUnrealisedDistributionsRealised);
        resultFundTrackRecord.put("totalUnrealisedDistributionsWrittenOff",totalUnrealisedDistributionsWrittenOff);

        resultFundTrackRecord.put("totalValue",totalValue);
        resultFundTrackRecord.put("totalValueUnrealised",totalValueUnrealised);
        resultFundTrackRecord.put("totalValuePartRealised",totalValuePartRealised);
        resultFundTrackRecord.put("totalValueRealised",totalValueRealised);
        resultFundTrackRecord.put("totalValueWrittenOff",totalValueWrittenOff);

        int portfolioNum = 0;
        if(portfolioTrackRecordList!=null){
            portfolioNum = portfolioTrackRecordList.size();
        }
        Map<String,Object> allMap = new HashMap<String, Object>();
        allMap.put("company","All "+portfolioNum);
        allMap.put("status","");
        allMap.put("investedCost",totalInvestedCost);
        allMap.put("realisedProceeds",totalDistributions);
        allMap.put("unRealisedProceeds",totalUnrealisedDistributions);
        allMap.put("totalValue",totalValue);
        if(((BigDecimal)(allMap.get("investedCost"))).equals(new BigDecimal(0.00))){
            allMap.put("mocStr","");
        }else{
            allMap.put("moc",((BigDecimal)allMap.get("totalValue")).divide((BigDecimal)allMap.get("investedCost"),4,ROUND_HALF_DOWN));
            allMap.put("mocStr",((BigDecimal)allMap.get("totalValue")).divide((BigDecimal)allMap.get("investedCost"),4,ROUND_HALF_DOWN).toString()+"x");
        }
        summationPortfolioTrackRecordList.add(allMap);

        Map<String,Object> unRealisedMap = new HashMap<String, Object>();
        unRealisedMap.put("company","");
        unRealisedMap.put("status","Unrealised");
        unRealisedMap.put("investedCost",totalInvestedCostUnrealised);
        unRealisedMap.put("realisedProceeds",totalDistributionsUnrealised);
        unRealisedMap.put("unRealisedProceeds",totalUnrealisedDistributionsUnrealised);
        unRealisedMap.put("totalValue",totalValueUnrealised);
        if(((BigDecimal)(unRealisedMap.get("investedCost"))).equals(new BigDecimal(0.00))){
            unRealisedMap.put("mocStr","");
        }else{
            unRealisedMap.put("moc",((BigDecimal)unRealisedMap.get("totalValue")).divide((BigDecimal)unRealisedMap.get("investedCost"),4,ROUND_HALF_DOWN));
            unRealisedMap.put("mocStr",((BigDecimal)unRealisedMap.get("totalValue")).divide((BigDecimal)unRealisedMap.get("investedCost"),4,ROUND_HALF_DOWN).toString()+"x");
        }
        //unRealisedMap.put("moc",((BigDecimal)unRealisedMap.get("totalValue")).divide((BigDecimal)unRealisedMap.get("investedCost")));
        summationPortfolioTrackRecordList.add(unRealisedMap);

        Map<String,Object> partRealisedMap = new HashMap<String, Object>();
        partRealisedMap.put("company","");
        partRealisedMap.put("status","Partially Realised");
        partRealisedMap.put("investedCost",totalInvestedCostPartRealised);
        partRealisedMap.put("realisedProceeds",totalDistributionsPartRealised);
        partRealisedMap.put("unRealisedProceeds",totalUnrealisedDistributionsPartRealised);
        partRealisedMap.put("totalValue",totalValuePartRealised);
        if(((BigDecimal)(partRealisedMap.get("investedCost"))).equals(new BigDecimal(0.00))){
            partRealisedMap.put("mocStr","");
        }else{
            partRealisedMap.put("moc",((BigDecimal)partRealisedMap.get("totalValue")).divide((BigDecimal)partRealisedMap.get("investedCost"),4,ROUND_HALF_DOWN));
            partRealisedMap.put("mocStr",((BigDecimal)partRealisedMap.get("totalValue")).divide((BigDecimal)partRealisedMap.get("investedCost"),4,ROUND_HALF_DOWN).toString()+"x");
        }
        //partRealisedMap.put("moc",((BigDecimal)partRealisedMap.get("totalValue")).divide((BigDecimal)partRealisedMap.get("investedCost")));
        summationPortfolioTrackRecordList.add(partRealisedMap);

        Map<String,Object> realisedMap = new HashMap<String, Object>();
        realisedMap.put("company","");
        realisedMap.put("status","Realised");
        realisedMap.put("investedCost",totalInvestedCostRealised);
        realisedMap.put("realisedProceeds",totalDistributionsRealised);
        realisedMap.put("unRealisedProceeds",totalUnrealisedDistributionsRealised);
        realisedMap.put("totalValue",totalValueRealised);
        if(((BigDecimal)(realisedMap.get("investedCost"))).equals(new BigDecimal(0.00))){
            realisedMap.put("mocStr","");
        }else{
            realisedMap.put("moc",((BigDecimal)realisedMap.get("totalValue")).divide((BigDecimal)realisedMap.get("investedCost"),4,ROUND_HALF_DOWN));
            realisedMap.put("mocStr",((BigDecimal)realisedMap.get("totalValue")).divide((BigDecimal)realisedMap.get("investedCost"),4,ROUND_HALF_DOWN).toString()+"x");
        }
        //realisedMap.put("moc",((BigDecimal)realisedMap.get("totalValue")).divide((BigDecimal)realisedMap.get("investedCost")));
        summationPortfolioTrackRecordList.add(realisedMap);

        Map<String,Object> writtenOffMap = new HashMap<String, Object>();
        writtenOffMap.put("company","");
        writtenOffMap.put("status","Written Off");
        writtenOffMap.put("investedCost",totalInvestedCostWrittenOff);
        writtenOffMap.put("realisedProceeds",totalDistributionsWrittenOff);
        writtenOffMap.put("unRealisedProceeds",totalUnrealisedDistributionsWrittenOff);
        writtenOffMap.put("totalValue",totalValueWrittenOff);
        if(((BigDecimal)(writtenOffMap.get("investedCost"))).equals(new BigDecimal(0.00))){
            writtenOffMap.put("mocStr","");
        }else{
            writtenOffMap.put("moc",((BigDecimal)writtenOffMap.get("totalValue")).divide((BigDecimal)writtenOffMap.get("investedCost"),4,ROUND_HALF_DOWN));
            writtenOffMap.put("mocStr",((BigDecimal)writtenOffMap.get("totalValue")).divide((BigDecimal)writtenOffMap.get("investedCost"),4,ROUND_HALF_DOWN).toString()+"x");
        }
        //writtenOffMap.put("moc",((BigDecimal)writtenOffMap.get("totalValue")).divide((BigDecimal)writtenOffMap.get("investedCost")));
        summationPortfolioTrackRecordList.add(writtenOffMap);
        return summationPortfolioTrackRecordList;
    }
    public Map<String,Object> queryFundGrossFfs(Map<String, Object> paraMap){
        Map<String,Object> resultFundGrossFfs = new HashMap<String,Object>();
        List<Map<String,Object>> portfolioTrackRecordList = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> portfolioInvestmentList = new ArrayList<Map<String,Object>>();


        portfolioTrackRecordList = fundFinInfoMapper.queryPortfolioTrackRecordList(paraMap);
        for(Map<String,Object> tmpMap:portfolioTrackRecordList){
            Map<String,Object> tmpParaMap = new HashMap<String,Object>();
            tmpParaMap.put("portfolioId", tmpMap.get("portfolioID").toString());
            tmpParaMap.put("fundId", paraMap.get("fundid").toString());
            tmpParaMap.put("date", (Date)paraMap.get("date"));
            List<Map<String,Object>> portfolioExitList = fundFinInfoMapper.queryPortfolioExitList(tmpParaMap);
            if(portfolioExitList==null || portfolioExitList.size()==0){
                tmpMap.put("status","Unrealised");
            }else{
                if(portfolioExitList.get(0).get("exitType").toString().equals("Write Off")){
                    tmpMap.put("status","Written Off");
                }else if(((BigDecimal)(portfolioExitList.get(0).get("shareRemain"))).equals(new BigDecimal(0.00))){
                    tmpMap.put("status","Realised");
                }else{
                    tmpMap.put("status","Partially Realised");
                }
            }
            List<Map<String,Object>> portfolioValuationList = fundFinInfoMapper.queryPortfolioValuationList(tmpParaMap);
            if(portfolioValuationList==null || portfolioValuationList.size()==0){
                tmpMap.put("unRealisedProceeds",new BigDecimal(0.00));
            }else{
                tmpMap.put("unRealisedProceeds",(BigDecimal)(portfolioValuationList.get(0).get("Valuation")));
            }
            BigDecimal irr = calculatePortfolioIrr(tmpParaMap);
            tmpMap.put("irr",irr);
            tmpMap.put("totalValue",((BigDecimal)tmpMap.get("realisedProceeds")).add((BigDecimal)tmpMap.get("unRealisedProceeds")));
            tmpMap.put("moc",((BigDecimal)tmpMap.get("totalValue")).divide((BigDecimal)tmpMap.get("investedCost"),2,ROUND_HALF_DOWN));
        }
        int unrealisedPortfolioNum = 0;
        int partiallyRealisedPortfolioNum = 0;
        int realisedPortfolioNum = 0;
        int writtenOffNum = 0;
        for(Map<String,Object> portfolioTrackRecord:portfolioTrackRecordList){
            if("Unrealised".equals(portfolioTrackRecord.get("status").toString())){
                unrealisedPortfolioNum ++;
            }else if("Partially Realised".equals(portfolioTrackRecord.get("status").toString())){
                partiallyRealisedPortfolioNum ++;
            }else if("Realised".equals(portfolioTrackRecord.get("status").toString())){
                realisedPortfolioNum ++;
            }else if("Written Off".equals(portfolioTrackRecord.get("status").toString())){
                writtenOffNum ++;
            }
        }
        resultFundGrossFfs.put("totalPortfolioNum",portfolioTrackRecordList.size());
        resultFundGrossFfs.put("unrealisedPortfolioNum",unrealisedPortfolioNum);
        resultFundGrossFfs.put("partiallyRealisedPortfolioNum",partiallyRealisedPortfolioNum);
        resultFundGrossFfs.put("realisedPortfolioNum",realisedPortfolioNum);
        resultFundGrossFfs.put("writtenOffNum",writtenOffNum);



        portfolioInvestmentList = fundFinInfoMapper.queryPortfolioInvestmentList(paraMap);
        for(Map<String,Object> portfolioInvestmentMap:portfolioInvestmentList){
            if(!"".equals(portfolioInvestmentMap.get("investedCost").toString()) && ((BigDecimal)portfolioInvestmentMap.get("investedCost")).compareTo(new BigDecimal(0.00))<0){
                portfolioInvestmentMap.put("distribution",((BigDecimal)portfolioInvestmentMap.get("investedCost")).abs());
                portfolioInvestmentMap.put("investedCost",new BigDecimal(0.00));
            }
            BigDecimal investedCost = new BigDecimal(0.00).subtract((BigDecimal)portfolioInvestmentMap.get("investedCost"));
            BigDecimal distribution = (BigDecimal)portfolioInvestmentMap.get("distribution");
            BigDecimal fairValue = (BigDecimal)portfolioInvestmentMap.get("fairValue");
            portfolioInvestmentMap.put("grossCashflows",investedCost.add(distribution).add(fairValue));
            portfolioInvestmentMap.put("grossCashflowsAbs",investedCost.add(distribution).add(fairValue).abs());
            if(investedCost.add(distribution).add(fairValue).compareTo(new BigDecimal(0.00))>=0){
                portfolioInvestmentMap.put("grossCashflowsStr",investedCost.add(distribution).add(fairValue).abs().toString());
            }else{
                portfolioInvestmentMap.put("grossCashflowsStr","("+investedCost.add(distribution).add(fairValue).abs().toString()+")");
            }
        }


        /*BigDecimal grossIrrForAll = calculatePortfolioIrrForAll(portfolioInvestmentList);
        resultFundGrossFfs.put("grossIrrForAll",grossIrrForAll);
        resultFundGrossFfs.put("grossIrr",grossIrrForAll);*/

        List<Map<String,Object>> summationPortfolioTrackRecordList = processSummationGrossFfs(resultFundGrossFfs, portfolioTrackRecordList, portfolioInvestmentList);//做汇总
        portfolioTrackRecordList.addAll(summationPortfolioTrackRecordList);
        resultFundGrossFfs.put("portfolioTrackRecordList",portfolioTrackRecordList);


        //合并两个表
        /*
        <el-table-column  prop="status" label="Status" width="150">
            </el-table-column>
            <el-table-column  prop="investedCost" label="Invested Cost" width="100">
            </el-table-column>
            <el-table-column  prop="realisedProceeds" label="Realised Proceeds" width="100">
            </el-table-column>
            <el-table-column  prop="unRealisedProceeds" label="Unrealised Proceeds" width="120">
            </el-table-column>
            <el-table-column  prop="totalValue" label="Total Value" width="150">
            </el-table-column>
            <el-table-column  prop="moc" label="Moc" width="150">
            </el-table-column>
            <el-table-column  prop="irr" label="IRR" width="150">


            <el-table-column  prop="status" label="Status" width="150">
            </el-table-column>
            <el-table-column  prop="investedCost" label="Invested Cost" width="100">
            </el-table-column>
            <el-table-column  prop="realisedProceeds" label="Realised Proceeds" width="100">
            </el-table-column>
            <el-table-column  prop="unRealisedProceeds" label="Unrealised Proceeds" width="120">
            </el-table-column>
            <el-table-column  prop="totalValue" label="Total Value" width="150">
            </el-table-column>
            <el-table-column  prop="moc" label="Moc" width="150">
            </el-table-column>
            <el-table-column  prop="irr" label="IRR" width="150">
            </el-table-column>
         */
        for(Map<String,Object> portfolioInvestment : portfolioInvestmentList){
            for(Map<String,Object> portfolioTrackRecord :portfolioTrackRecordList)
            if(portfolioInvestment.get("portfolioID")!=null && portfolioTrackRecord.get("portfolioID")!=null
                    && portfolioInvestment.get("portfolioID").toString().equals(portfolioTrackRecord.get("portfolioID").toString())){
                portfolioInvestment.put("status", portfolioTrackRecord.get("status"));
                portfolioInvestment.put("totalInvestedCost", portfolioTrackRecord.get("investedCost"));
                portfolioInvestment.put("realisedProceeds", portfolioTrackRecord.get("realisedProceeds"));
                portfolioInvestment.put("unRealisedProceeds", portfolioTrackRecord.get("unRealisedProceeds"));
                portfolioInvestment.put("totalValue", portfolioTrackRecord.get("totalValue"));
                portfolioInvestment.put("moc", portfolioTrackRecord.get("moc"));
                portfolioInvestment.put("irr", portfolioTrackRecord.get("irr"));
            }
        }

        resultFundGrossFfs.put("portfolioInvestmentList",portfolioInvestmentList);

        return resultFundGrossFfs;
    }

    public BigDecimal calculatePortfolioIrrForAll(List<Map<String, Object>> portfolioInvestmentList){
        //计算项目IRR
        if(portfolioInvestmentList!=null && portfolioInvestmentList.size()>0){
            List<BigDecimal> portfolioCashValues = new ArrayList<BigDecimal>();
            List<Long> portfolioCashDates = new ArrayList<Long>();
            Date startDate = (Date)portfolioInvestmentList.get(0).get("cashDate");
            Date endDate = (Date)portfolioInvestmentList.get(0).get("cashDate");
            for(Map<String, Object> tmpMap:portfolioInvestmentList){
                if(((Date)tmpMap.get("cashDate")).compareTo(endDate)>0){
                    endDate = (Date)tmpMap.get("cashDate");
                }
                if(((Date)tmpMap.get("cashDate")).compareTo(startDate)<0){
                    startDate = (Date)tmpMap.get("cashDate");
                }
            }

            for(Map<String, Object> cashMap : portfolioInvestmentList){

                if(cashMap.get("grossCashflows") != null){

                    BigDecimal cashValue = (BigDecimal) cashMap.get("grossCashflows");


                    portfolioCashValues.add(cashValue);
                    long cashDateIndex = IrrUtil.getExcelDateValueForPortfolio(startDate, endDate,(Date)cashMap.get("cashDate"));
                    portfolioCashDates.add(cashDateIndex);
                }
            }
            List<Map<String,Object>>  grossCashFlowInfoArr = new ArrayList<Map<String,Object>>();
            if(portfolioCashValues != null && portfolioCashValues.size() > 0 && portfolioCashDates != null && portfolioCashDates.size() > 0){

                List<BigDecimal> flowTrueArr = new ArrayList<BigDecimal>();
                //flowTrueArr = IrrUtil.processFlowArray(portfolioCashValues, portfolioCashDates);
                flowTrueArr = IrrUtil.processFlowArrayForPortfolioForAll(portfolioCashValues, portfolioCashDates);
                double[] flowDoubleArr = new double[flowTrueArr.size()];
                List<Double> flowTrueList = new ArrayList<Double>();
                for(int i =0 ; i<flowTrueArr.size(); i++){
                    flowDoubleArr[i] =flowTrueArr.get(i).doubleValue();
                    flowTrueList.add(flowTrueArr.get(i).doubleValue());

                }
                //double currentirr = IrrUtil.getIrr(flowTrueList); 效率稍慢
                double currentirr = IrrUtil.irr(flowDoubleArr);
                System.out.println(currentirr);
                double currentDayIRR = currentirr+1.00;
                System.out.println(currentDayIRR);
                double currentyearIRR = Math.pow(currentDayIRR,365);
                return new BigDecimal(currentyearIRR);


                /*BigDecimal currentirr = IrrUtil.getIrrForPortfolio(flowTrueArr);
                System.out.println(currentirr);
                BigDecimal currentseasonIRR = currentirr.add(new BigDecimal(1.00));
                System.out.println(currentseasonIRR);
                BigDecimal currentyearIRR = currentseasonIRR.pow(365);
                currentyearIRR = currentyearIRR.subtract(new BigDecimal(1.00));
                return currentyearIRR;*/
                /*resultFundIrrStasticInfo.setIrrLpAndGp(currentyearIRR.setScale(4,ROUND_HALF_UP));
                resultFundIrrStasticInfo.setIrrLpAndGpStr(currentyearIRR.setScale(4,ROUND_HALF_UP).multiply(new BigDecimal(100.00)).setScale(2,ROUND_HALF_UP).toString()+"%");
                */
            }else{
                return new BigDecimal(0.00);
            }
        }else{
            return new BigDecimal(0.00);
        }
    }

    /*processSummationGrossFfs*/
    public List<Map<String,Object>> processSummationGrossFfs(Map<String, Object> resultFundGrossFfs, List<Map<String,Object>> portfolioTrackRecordList,List<Map<String,Object>> portfolioInvestmentList){
        List<Map<String,Object>> summationPortfolioTrackRecordList = new ArrayList<Map<String,Object>>();

        BigDecimal totalInvestedCost = new BigDecimal(0.00);
        BigDecimal totalInvestedCostUnrealised = new BigDecimal(0.00);
        BigDecimal totalInvestedCostPartRealised = new BigDecimal(0.00);
        BigDecimal totalInvestedCostRealised = new BigDecimal(0.00);
        BigDecimal totalInvestedCostWrittenOff = new BigDecimal(0.00);

        BigDecimal totalDistributions = new BigDecimal(0.00);
        BigDecimal totalDistributionsUnrealised = new BigDecimal(0.00);
        BigDecimal totalDistributionsPartRealised = new BigDecimal(0.00);
        BigDecimal totalDistributionsRealised = new BigDecimal(0.00);
        BigDecimal totalDistributionsWrittenOff = new BigDecimal(0.00);

        BigDecimal totalUnrealisedDistributions = new BigDecimal(0.00);
        BigDecimal totalUnrealisedDistributionsUnrealised = new BigDecimal(0.00);
        BigDecimal totalUnrealisedDistributionsPartRealised = new BigDecimal(0.00);
        BigDecimal totalUnrealisedDistributionsRealised = new BigDecimal(0.00);
        BigDecimal totalUnrealisedDistributionsWrittenOff = new BigDecimal(0.00);

        BigDecimal totalValue = new BigDecimal(0.00);
        BigDecimal totalValueUnrealised = new BigDecimal(0.00);
        BigDecimal totalValuePartRealised = new BigDecimal(0.00);
        BigDecimal totalValueRealised = new BigDecimal(0.00);
        BigDecimal totalValueWrittenOff = new BigDecimal(0.00);


        for(Map<String,Object> tmpMap:portfolioTrackRecordList){
            totalInvestedCost = totalInvestedCost.add((BigDecimal)(tmpMap.get("investedCost")==null?new BigDecimal(0.00):tmpMap.get("investedCost")));
            totalDistributions = totalDistributions.add((BigDecimal)(tmpMap.get("realisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("realisedProceeds")));
            totalUnrealisedDistributions = totalUnrealisedDistributions.add((BigDecimal)(tmpMap.get("unRealisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("unRealisedProceeds")));
            totalValue = totalValue.add((BigDecimal)(tmpMap.get("totalValue")==null?new BigDecimal(0.00):tmpMap.get("totalValue")));
            if("Unrealised".equals(tmpMap.get("status").toString())){
                totalInvestedCostUnrealised = totalInvestedCostUnrealised.add((BigDecimal)(tmpMap.get("investedCost")==null?new BigDecimal(0.00):tmpMap.get("investedCost")));
                totalDistributionsUnrealised = totalDistributionsUnrealised.add((BigDecimal)(tmpMap.get("realisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("realisedProceeds")));
                totalUnrealisedDistributionsUnrealised = totalUnrealisedDistributionsUnrealised.add((BigDecimal)(tmpMap.get("unRealisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("unRealisedProceeds")));
                totalValueUnrealised = totalValueUnrealised.add((BigDecimal)(tmpMap.get("totalValue")==null?new BigDecimal(0.00):tmpMap.get("totalValue")));
            }else if("Partially Realised".equals(tmpMap.get("status").toString())){
                totalInvestedCostPartRealised = totalInvestedCostPartRealised.add((BigDecimal)(tmpMap.get("investedCost")==null?new BigDecimal(0.00):tmpMap.get("investedCost")));
                totalDistributionsPartRealised = totalDistributionsPartRealised.add((BigDecimal)(tmpMap.get("realisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("realisedProceeds")));
                totalUnrealisedDistributionsPartRealised = totalUnrealisedDistributionsPartRealised.add((BigDecimal)(tmpMap.get("unRealisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("unRealisedProceeds")));
                totalValuePartRealised = totalValuePartRealised.add((BigDecimal)(tmpMap.get("totalValue")==null?new BigDecimal(0.00):tmpMap.get("totalValue")));
            }else if("Realised".equals(tmpMap.get("status").toString())){
                totalInvestedCostRealised = totalInvestedCostRealised.add((BigDecimal)(tmpMap.get("investedCost")==null?new BigDecimal(0.00):tmpMap.get("investedCost")));
                totalDistributionsRealised = totalDistributionsRealised.add((BigDecimal)(tmpMap.get("realisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("realisedProceeds")));
                totalUnrealisedDistributionsRealised = totalUnrealisedDistributionsRealised.add((BigDecimal)(tmpMap.get("unRealisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("unRealisedProceeds")));
                totalValueRealised = totalValueRealised.add((BigDecimal)(tmpMap.get("totalValue")==null?new BigDecimal(0.00):tmpMap.get("totalValue")));
            }else if("Written Off".equals(tmpMap.get("status").toString())){
                totalInvestedCostWrittenOff = totalInvestedCostWrittenOff.add((BigDecimal)(tmpMap.get("investedCost")==null?new BigDecimal(0.00):tmpMap.get("investedCost")));
                totalDistributionsWrittenOff = totalDistributionsWrittenOff.add((BigDecimal)(tmpMap.get("realisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("realisedProceeds")));
                totalUnrealisedDistributionsWrittenOff = totalUnrealisedDistributionsWrittenOff.add((BigDecimal)(tmpMap.get("unRealisedProceeds")==null?new BigDecimal(0.00):tmpMap.get("unRealisedProceeds")));
                totalValueWrittenOff = totalValueWrittenOff.add((BigDecimal)(tmpMap.get("totalValue")==null?new BigDecimal(0.00):tmpMap.get("totalValue")));
            }
        }
        resultFundGrossFfs.put("totalInvestedCost",totalInvestedCost);
        resultFundGrossFfs.put("totalInvestedCostUnrealised",totalInvestedCostUnrealised);
        resultFundGrossFfs.put("totalInvestedCostPartRealised",totalInvestedCostPartRealised);
        resultFundGrossFfs.put("totalInvestedCostRealised",totalInvestedCostRealised);
        resultFundGrossFfs.put("totalInvestedCostWrittenOff",totalInvestedCostWrittenOff);

        resultFundGrossFfs.put("totalDistributions",totalDistributions);
        resultFundGrossFfs.put("totalDistributionsUnrealised",totalDistributionsUnrealised);
        resultFundGrossFfs.put("totalDistributionsPartRealised",totalDistributionsPartRealised);
        resultFundGrossFfs.put("totalDistributionsRealised",totalDistributionsRealised);
        resultFundGrossFfs.put("totalDistributionsWrittenOff",totalDistributionsWrittenOff);

        resultFundGrossFfs.put("totalUnrealisedDistributions",totalUnrealisedDistributions);
        resultFundGrossFfs.put("totalUnrealisedDistributionsUnrealised",totalUnrealisedDistributionsUnrealised);
        resultFundGrossFfs.put("totalUnrealisedDistributionsPartRealised",totalUnrealisedDistributionsPartRealised);
        resultFundGrossFfs.put("totalUnrealisedDistributionsRealised",totalUnrealisedDistributionsRealised);
        resultFundGrossFfs.put("totalUnrealisedDistributionsWrittenOff",totalUnrealisedDistributionsWrittenOff);

        resultFundGrossFfs.put("totalValue",totalValue);
        resultFundGrossFfs.put("totalValueUnrealised",totalValueUnrealised);
        resultFundGrossFfs.put("totalValuePartRealised",totalValuePartRealised);
        resultFundGrossFfs.put("totalValueRealised",totalValueRealised);
        resultFundGrossFfs.put("totalValueWrittenOff",totalValueWrittenOff);

        int portfolioNum = 0;
        if(portfolioTrackRecordList!=null){
            portfolioNum = portfolioTrackRecordList.size();
        }
        Map<String,Object> allMap = new HashMap<String, Object>();
        allMap.put("company","All "+portfolioNum);
        allMap.put("status","");
        allMap.put("investedCost",totalInvestedCost);
        allMap.put("realisedProceeds",totalDistributions);
        allMap.put("unRealisedProceeds",totalUnrealisedDistributions);
        allMap.put("totalValue",totalValue);
        if(((BigDecimal)(allMap.get("investedCost"))).equals(new BigDecimal(0.00))){
            allMap.put("mocStr","");
        }else{
            allMap.put("moc",((BigDecimal)allMap.get("totalValue")).divide((BigDecimal)allMap.get("investedCost"),4,ROUND_HALF_DOWN));
            allMap.put("mocStr",((BigDecimal)allMap.get("totalValue")).divide((BigDecimal)allMap.get("investedCost"),4,ROUND_HALF_DOWN).toString()+"x");
        }
        summationPortfolioTrackRecordList.add(allMap);

        Map<String,Object> unRealisedMap = new HashMap<String, Object>();
        unRealisedMap.put("company","");
        unRealisedMap.put("status","Unrealised");
        unRealisedMap.put("investedCost",totalInvestedCostUnrealised);
        unRealisedMap.put("realisedProceeds",totalDistributionsUnrealised);
        unRealisedMap.put("unRealisedProceeds",totalUnrealisedDistributionsUnrealised);
        unRealisedMap.put("totalValue",totalValueUnrealised);
        if(((BigDecimal)(unRealisedMap.get("investedCost"))).equals(new BigDecimal(0.00))){
            unRealisedMap.put("mocStr","");
        }else{
            unRealisedMap.put("moc",((BigDecimal)unRealisedMap.get("totalValue")).divide((BigDecimal)unRealisedMap.get("investedCost"),4,ROUND_HALF_DOWN));
            unRealisedMap.put("mocStr",((BigDecimal)unRealisedMap.get("totalValue")).divide((BigDecimal)unRealisedMap.get("investedCost"),4,ROUND_HALF_DOWN).toString()+"x");
        }
        //unRealisedMap.put("moc",((BigDecimal)unRealisedMap.get("totalValue")).divide((BigDecimal)unRealisedMap.get("investedCost")));
        summationPortfolioTrackRecordList.add(unRealisedMap);

        Map<String,Object> partRealisedMap = new HashMap<String, Object>();
        partRealisedMap.put("company","");
        partRealisedMap.put("status","Partially Realised");
        partRealisedMap.put("investedCost",totalInvestedCostPartRealised);
        partRealisedMap.put("realisedProceeds",totalDistributionsPartRealised);
        partRealisedMap.put("unRealisedProceeds",totalUnrealisedDistributionsPartRealised);
        partRealisedMap.put("totalValue",totalValuePartRealised);
        if(((BigDecimal)(partRealisedMap.get("investedCost"))).equals(new BigDecimal(0.00))){
            partRealisedMap.put("mocStr","");
        }else{
            partRealisedMap.put("moc",((BigDecimal)partRealisedMap.get("totalValue")).divide((BigDecimal)partRealisedMap.get("investedCost"),4,ROUND_HALF_DOWN));
            partRealisedMap.put("mocStr",((BigDecimal)partRealisedMap.get("totalValue")).divide((BigDecimal)partRealisedMap.get("investedCost"),4,ROUND_HALF_DOWN).toString()+"x");
        }
        //partRealisedMap.put("moc",((BigDecimal)partRealisedMap.get("totalValue")).divide((BigDecimal)partRealisedMap.get("investedCost")));
        summationPortfolioTrackRecordList.add(partRealisedMap);

        Map<String,Object> realisedMap = new HashMap<String, Object>();
        realisedMap.put("company","");
        realisedMap.put("status","Realised");
        realisedMap.put("investedCost",totalInvestedCostRealised);
        realisedMap.put("realisedProceeds",totalDistributionsRealised);
        realisedMap.put("unRealisedProceeds",totalUnrealisedDistributionsRealised);
        realisedMap.put("totalValue",totalValueRealised);
        if(((BigDecimal)(realisedMap.get("investedCost"))).equals(new BigDecimal(0.00))){
            realisedMap.put("mocStr","");
        }else{
            realisedMap.put("moc",((BigDecimal)realisedMap.get("totalValue")).divide((BigDecimal)realisedMap.get("investedCost"),4,ROUND_HALF_DOWN));
            realisedMap.put("mocStr",((BigDecimal)realisedMap.get("totalValue")).divide((BigDecimal)realisedMap.get("investedCost"),4,ROUND_HALF_DOWN).toString()+"x");
        }
        //realisedMap.put("moc",((BigDecimal)realisedMap.get("totalValue")).divide((BigDecimal)realisedMap.get("investedCost")));
        summationPortfolioTrackRecordList.add(realisedMap);

        Map<String,Object> writtenOffMap = new HashMap<String, Object>();
        writtenOffMap.put("company","");
        writtenOffMap.put("status","Written Off");
        writtenOffMap.put("investedCost",totalInvestedCostWrittenOff);
        writtenOffMap.put("realisedProceeds",totalDistributionsWrittenOff);
        writtenOffMap.put("unRealisedProceeds",totalUnrealisedDistributionsWrittenOff);
        writtenOffMap.put("totalValue",totalValueWrittenOff);
        if(((BigDecimal)(writtenOffMap.get("investedCost"))).equals(new BigDecimal(0.00))){
            writtenOffMap.put("mocStr","");
        }else{
            writtenOffMap.put("moc",((BigDecimal)writtenOffMap.get("totalValue")).divide((BigDecimal)writtenOffMap.get("investedCost"),4,ROUND_HALF_DOWN));
            writtenOffMap.put("mocStr",((BigDecimal)writtenOffMap.get("totalValue")).divide((BigDecimal)writtenOffMap.get("investedCost"),4,ROUND_HALF_DOWN).toString()+"x");
        }
        //writtenOffMap.put("moc",((BigDecimal)writtenOffMap.get("totalValue")).divide((BigDecimal)writtenOffMap.get("investedCost")));
        summationPortfolioTrackRecordList.add(writtenOffMap);
        return summationPortfolioTrackRecordList;
    }



    //以下是fundFin相关
    public List<Map<String,Object>> queryFundFinEntityList(Map<String, Object> paraMap){

        List<Map<String,Object>> fundFinEntityList = new ArrayList<Map<String,Object>>();

        fundFinEntityList = fundFinInfoMapper.queryFundFinEntityList(paraMap);


        return fundFinEntityList;
    }

    public Fundfininfo queryFundFinEntityById(String id){
        return fundFinInfoMapper.selectByPrimaryKey(id);
    }

    public int addFundFinInfo(Fundfininfo fundFinInfo, LoginUser loginUser){

        String finId = fundFinInfo.getFundid() + "_"+DateUtil.dateToString(fundFinInfo.getFindate(),DateUtil.FILE_DATE_DIR_FORMAT);
        // String fundId = getFundId();

        fundFinInfo.setFinid(finId);
        fundFinInfo.setIsmodified("1");
        fundFinInfo.setValidflag("1");

        fundFinInfo.setCreateby(loginUser.getUserId());
        fundFinInfo.setCreatedate(new Date());
        fundFinInfo.setEditby(loginUser.getUserId());
        fundFinInfo.setEditdate(new Date());
        return fundFinInfoMapper.insert(fundFinInfo);
    }

    public int updateFundFinInfo(Fundfininfo fundFinInfo, LoginUser loginUser){
        fundFinInfo.setEditby(loginUser.getUserId());
        fundFinInfo.setEditdate(new Date());
        return fundFinInfoMapper.updateByPrimaryKey(fundFinInfo);
    }

    public int deleteFundFinById(String id){
        return fundFinInfoMapper.deleteByPrimaryKey(id);
    }
}
