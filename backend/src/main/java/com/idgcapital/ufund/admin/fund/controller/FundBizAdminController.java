package com.idgcapital.ufund.admin.fund.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.admin.fund.service.FundAdminService;
import com.idgcapital.ufund.admin.fund.service.FundCapitalCallService;
import com.idgcapital.ufund.admin.fund.service.FundDistributionService;
import com.idgcapital.ufund.admin.proj.service.ProjAdminService;
import com.idgcapital.ufund.common.controller.AppView;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.common.model.FundIrrStasticInfo;
import com.idgcapital.ufund.common.util.DateUtil;
import com.idgcapital.ufund.model.*;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.model.view.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by li_zhao on 2017/12/13.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin")
public class FundBizAdminController extends BaseController{
    @Autowired
    private FundAdminService fundAdminService;
    @Autowired
    private FundCapitalCallService fundCapitalCallService;
    @Autowired
    private FundDistributionService fundDistributionService;
    @Autowired
    private ProjAdminService projAdminService;

    @RequestMapping(value = "/fund/list",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.FundList.class)
    public ResponseEntity<JsonResult> queryFundEntityList(@RequestBody Fundbasicinfo fundbasicinfo) {
        try{
            JsonResult jsonResult = fundAdminService.queryFundEntityList(fundbasicinfo);

            jsonResult.setCode(ReturnCode.SUCCESS);
            jsonResult.setReturnMessage("SUCCESS");

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }
    @RequestMapping(value = "/fund/listwithoutpage",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryFundEntityListWithoutPage() {
        try{
            List<Fundbasicinfo> fundEntityListWithoutPage = fundAdminService.queryFundEntityListWithoutPage();
            return wrapperJsonResult(fundEntityListWithoutPage);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fund/entity/{id}", method = RequestMethod.GET)
    @JsonView(AppView.FundDetail.class)
    public ResponseEntity<JsonResult> queryFundEntityById(@PathVariable String id) {
        try{
            Fundbasicinfo fundbasicinfo = fundAdminService.queryFundEntityById(id);
            return wrapperJsonResult(fundbasicinfo);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fund/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addFundEntity(@RequestBody Fundbasicinfo fundbasicinfo) {
        try{
            fundAdminService.addFundEntity(fundbasicinfo,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fund/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateFundEntity(@RequestBody Fundbasicinfo fundbasicinfo) {
        try{
            fundAdminService.updateFundEntity(fundbasicinfo, getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fund/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteFundEntityById(@PathVariable String id) {
        try{
            fundAdminService.deleteFundEntityById(id);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/fundcapitalcall/list",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.capitalCallList.class)
    public ResponseEntity<JsonResult> queryCapitalCallEntityList(@RequestBody Capitalcall capitalcall) {
        try{
            JsonResult jsonResult = fundCapitalCallService.queryCapitalCallEntityList(capitalcall);

            jsonResult.setCode(ReturnCode.SUCCESS);
            jsonResult.setReturnMessage("SUCCESS");

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/fundcapitalcall/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addFundCapitalCall(@RequestBody Capitalcall capitalcall) {
        try{
            fundCapitalCallService.addFundCapitalCall(capitalcall,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fundcapitalcall/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateFundCapitalCall(@RequestBody Capitalcall capitalcall) {
        try{
            fundCapitalCallService.updateFundCapitalCall(capitalcall, getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fundcapitalcall/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteFundCapitalCallById(@PathVariable String id) {
        try{
            fundCapitalCallService.deleteFundCapitalCallById(id);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/fundcapitalcall/entity/{id}", method = RequestMethod.GET)

    public ResponseEntity<JsonResult> queryFundCapitalCallEntityById(@PathVariable String id) {
        try{
            Capitalcall capitalcallinfo = fundCapitalCallService.queryFundCapitalCallEntityById(id);
            return wrapperJsonResult(capitalcallinfo);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fundcapitalcalllp/entity/{id}", method = RequestMethod.GET)

    public ResponseEntity<JsonResult> queryFundCapitalCallLpEntityById(@PathVariable Integer id) {
        try{
            Capitalcalldetail capitalcalldetailinfo = fundCapitalCallService.queryCapitalCallLpEntityById(id);
            return wrapperJsonResult(capitalcalldetailinfo);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/fundcapitalcalllp/list",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.capitalCallLpList.class)
    public ResponseEntity<JsonResult> queryCapitalCallLpEntityList(@RequestBody Capitalcalldetail capitalcalldetail) {
        try{
            JsonResult jsonResult = fundCapitalCallService.queryCapitalCallLpEntityList(capitalcalldetail);

            jsonResult.setCode(ReturnCode.SUCCESS);
            jsonResult.setReturnMessage("SUCCESS");

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fundcapitalcalllp/update", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> updateFundCapitalCallLpDetail(@RequestBody Capitalcalldetail capitalcalldetail) {
        try{
            fundCapitalCallService.updateFundCapitalCallLpDetail(capitalcalldetail, getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }
    @RequestMapping(value = "/fundcapitalcall/irrstastic", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryFundIrrStastic(@RequestBody Map<String,Object> paraMap) {
        try{
            if(paraMap!=null){
                String dateStr = "";
                if(paraMap.get("yearid")!=null && !"".equals(paraMap.get("yearid").toString())){
                    dateStr += paraMap.get("yearid").toString() + "-";
                }else{
                    dateStr += DateUtil.getYear(new Date()) + "-";
                }
                if(paraMap.get("quarterid")!=null && !"".equals(paraMap.get("quarterid").toString())){
                    if("01".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "03-31";
                    }else if("02".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "06-30";
                    }else if("03".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "09-30";
                    }else if("04".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "12-31";
                    }else{
                        dateStr += "12-31";
                    }
                }
                Date date = DateUtil.stringtoDate(dateStr,DateUtil.LONG_DATE_FORMAT);
                paraMap.put("date",date);
            }
            FundIrrStasticInfo resultFundIrrStastic = fundCapitalCallService.queryFundIrrStastic(paraMap);
            return wrapperJsonResult(resultFundIrrStastic);
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fundcapitalcall/investmentoverview", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryFundInvestmentOverview(@RequestBody Map<String,Object> paraMap) {
        try{
            if(paraMap!=null){
                String dateStr = "";
                if(paraMap.get("yearid")!=null && !"".equals(paraMap.get("yearid").toString())){
                    dateStr += paraMap.get("yearid").toString() + "-";
                }else{
                    dateStr += DateUtil.getYear(new Date()) + "-";
                }
                if(paraMap.get("quarterid")!=null && !"".equals(paraMap.get("quarterid").toString())){
                    if("01".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "03-31";
                    }else if("02".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "06-30";
                    }else if("03".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "09-30";
                    }else if("04".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "12-31";
                    }else{
                        dateStr += "12-31";
                    }
                }
                Date date = DateUtil.stringtoDate(dateStr,DateUtil.LONG_DATE_FORMAT);
                paraMap.put("date",date);
            }
            List<Map<String,Object>> resultFundInvestmentOverview = fundCapitalCallService.queryFundInvestmentOverview(paraMap);
            return wrapperJsonResult(resultFundInvestmentOverview);
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fundcapitalcall/trackrecord", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryFundTrackRecord(@RequestBody Map<String,Object> paraMap) {
        try{
            if(paraMap!=null){
                String dateStr = "";
                if(paraMap.get("yearid")!=null && !"".equals(paraMap.get("yearid").toString())){
                    dateStr += paraMap.get("yearid").toString() + "-";
                }else{
                    dateStr += DateUtil.getYear(new Date()) + "-";
                }
                if(paraMap.get("quarterid")!=null && !"".equals(paraMap.get("quarterid").toString())){
                    if("01".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "03-31";
                    }else if("02".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "06-30";
                    }else if("03".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "09-30";
                    }else if("04".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "12-31";
                    }else{
                        dateStr += "12-31";
                    }
                }
                Date date = DateUtil.stringtoDate(dateStr,DateUtil.LONG_DATE_FORMAT);
                paraMap.put("date",date);
            }
            Map<String,Object> resultFundTrackRecord = fundCapitalCallService.queryFundTrackRecord(paraMap);
            return wrapperJsonResult(resultFundTrackRecord);
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fundcapitalcall/grosscfs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryFundGrossFfs(@RequestBody Map<String,Object> paraMap) {
        try{
            if(paraMap!=null){
                String dateStr = "";
                if(paraMap.get("yearid")!=null && !"".equals(paraMap.get("yearid").toString())){
                    dateStr += paraMap.get("yearid").toString() + "-";
                }else{
                    dateStr += DateUtil.getYear(new Date()) + "-";
                }
                if(paraMap.get("quarterid")!=null && !"".equals(paraMap.get("quarterid").toString())){
                    if("01".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "03-31";
                    }else if("02".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "06-30";
                    }else if("03".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "09-30";
                    }else if("04".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "12-31";
                    }else{
                        dateStr += "12-31";
                    }
                }
                Date date = DateUtil.stringtoDate(dateStr,DateUtil.LONG_DATE_FORMAT);
                paraMap.put("date",date);
            }
            Map<String,Object> resultFundGrossFfs = fundCapitalCallService.queryFundGrossFfs(paraMap);
            return wrapperJsonResult(resultFundGrossFfs);
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }
    @RequestMapping(value = "/fundfin/list",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryFundFinEntityList(@RequestBody Map<String,Object> paraMap) {
        try{
            if(paraMap!=null){
                String dateStr = "";
                if(paraMap.get("yearid")!=null && !"".equals(paraMap.get("yearid").toString())){
                    dateStr += paraMap.get("yearid").toString() + "-";
                }else{

                }
                if(paraMap.get("quarterid")!=null && !"".equals(paraMap.get("quarterid").toString())){
                    if("01".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "03-31";
                    }else if("02".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "06-30";
                    }else if("03".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "09-30";
                    }else if("04".equals(paraMap.get("quarterid").toString().trim())){
                        dateStr += "12-31";
                    }else{
                        dateStr += "12-31";
                    }
                }
                if(dateStr!=null && !"".equals(dateStr)){
                    Date date = DateUtil.stringtoDate(dateStr,DateUtil.LONG_DATE_FORMAT);
                    paraMap.put("date",date);
                }

            }
            List<Map<String,Object>> resultFundFinEntityList = fundCapitalCallService.queryFundFinEntityList(paraMap);
            return wrapperJsonResult(resultFundFinEntityList);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/fund/fundinvestportfolios/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryFundInvestPortfolios(@PathVariable String id) {
        try{

            JsonResult jsonResult = projAdminService.queryFundInvestPortfoliosByFundId(id);

            jsonResult.setCode(ReturnCode.SUCCESS);
            jsonResult.setReturnMessage("SUCCESS");

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fundfin/entity/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryFundFinEntityById(@PathVariable String id) {
        try{
            Fundfininfo fundFinInfo = fundCapitalCallService.queryFundFinEntityById(id);
            return wrapperJsonResult(fundFinInfo);
            /*Capitalcall capitalcallinfo = fundCapitalCallService.queryFundCapitalCallEntityById(id);
            return wrapperJsonResult(capitalcallinfo);*/
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }
    @RequestMapping(value = "/fundfin/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addFundFin(@RequestBody Fundfininfo fundFinInfo) {
        try{
            fundCapitalCallService.addFundFinInfo(fundFinInfo,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/fundfin/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateFundFin(@RequestBody Fundfininfo fundFinInfo) {
        try{
            fundCapitalCallService.updateFundFinInfo(fundFinInfo, getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fundfin/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteFundFinById(@PathVariable String id) {
        try{
            fundCapitalCallService.deleteFundFinById(id);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/distributable/list",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryDistributableList(@RequestBody Map<String,Object> paraMap) {
        try{
            List<Map<String,Object>> resultDistributableList =  fundDistributionService.queryDistributableList(paraMap);
            return wrapperJsonResult(resultDistributableList);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/funddistribution/list",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryDistributionEntityList(@RequestBody Lpcashdistribution lpcashdistribution) {
        try{
            JsonResult jsonResult = fundDistributionService.queryDistributionEntityList(lpcashdistribution);

            jsonResult.setCode(ReturnCode.SUCCESS);
            jsonResult.setReturnMessage("SUCCESS");

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }
    @RequestMapping(value = "/funddistribution/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addFundDistribution(@RequestBody Lpcashdistribution lpcashdistribution) {
        try{
            fundDistributionService.addFundDistribution(lpcashdistribution,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/funddistribution/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateFundDistribution(@RequestBody Lpcashdistribution lpcashdistribution) {
        try{
            fundDistributionService.updateFundDistribution(lpcashdistribution, getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/funddistribution/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteFundDistributionById(@PathVariable String id) {
        try{
            fundDistributionService.deleteFundDistributionById(id);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/lpdistributiondetail/list",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryLpDistributionDetailEntityList(@RequestBody Lpdistributiondetail lpdistributiondetail) {
        try{
            JsonResult jsonResult = new JsonResult();
            //jsonResult = fundDistributionService.queryLpDistributionDetailEntityList(lpdistributiondetail);

            jsonResult.setCode(ReturnCode.SUCCESS);
            jsonResult.setReturnMessage("SUCCESS");

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/lpdistributiondetail/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateLpDistributionDetail(@RequestBody Lpdistributiondetail lpdistributiondetail) {
        try{
            fundDistributionService.updateLpDistributionDetail(lpdistributiondetail, getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }
    @RequestMapping(value = "/lpdistributiondetail/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteLpDistributionDetailById(@PathVariable Integer id) {
        try{
            fundDistributionService.deleteLpDistributionDetailById(id);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }
}
