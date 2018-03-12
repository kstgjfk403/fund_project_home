package com.idgcapital.ufund.admin.proj.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.admin.proj.service.ProjManageReportService;
import com.idgcapital.ufund.common.controller.AppView;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.model.ProjReportInput;
import com.idgcapital.ufund.model.view.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by li_zhao on 2018/1/31.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin")
public class ProjManageReportController extends BaseController {
    @Autowired
    private ProjManageReportService projManageReportService;

    @RequestMapping(value = "/proj/managereport/gainloss",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioList.class)
    private ResponseEntity<JsonResult> queryGainLossReport(@RequestBody ProjReportInput reportInput) {
        try{
            List<Map<String,Object>> reportResult = projManageReportService.queryGainLossReport(reportInput);
            return wrapperJsonResult(reportResult);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/managereport/gainlosssum",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioList.class)
    private ResponseEntity<JsonResult> queryGainLossSumReport(@RequestBody ProjReportInput reportInput) {
        try{
            List<Map<String,Object>> reportResult = projManageReportService.queryGainLossSumReport(reportInput);
            return wrapperJsonResult(reportResult);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/proj/managereport/capitalfund",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioList.class)
    private ResponseEntity<JsonResult> queryCapitalFundReport(@RequestBody ProjReportInput reportInput) {
        try{
            List<Map<String,Object>> reportResult = projManageReportService.queryCapitalFundReport(reportInput);
            return wrapperJsonResult(reportResult);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/proj/managereport/mainschedule",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioList.class)
    private ResponseEntity<JsonResult> queryMainScheduleReport(@RequestBody ProjReportInput reportInput) {
        try{
            List<Map<String,Object>> reportResult = projManageReportService.queryMainScheduleReport(reportInput);
            return wrapperJsonResult(reportResult);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/managereport/businesssummary",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioList.class)
    private ResponseEntity<JsonResult> queryBusinessSummary(@RequestBody Map<String,Object> search) {
        try{
            List<Map<String,Object>> reportResult = projManageReportService.queryBusinessSummary(search);
            return wrapperJsonResult(reportResult);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/proj/managereport/investdetail",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioList.class)
    private ResponseEntity<JsonResult> queryInvestDetailReport(@RequestBody ProjReportInput reportInput) {
        try{
            List<Map<String,Object>> reportResult = projManageReportService.queryInvestDetailReport(reportInput);
            return wrapperJsonResult(reportResult);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



}
