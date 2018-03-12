package com.idgcapital.ufund.admin.proj.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.PageInfo;
import com.idgcapital.ufund.admin.proj.service.ProjFinService;
import com.idgcapital.ufund.common.controller.AppView;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.model.Portfoliofinancialinfo;
import com.idgcapital.ufund.model.Portfoliovaluation;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yi_zhang on 2018/2/12.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin/")
public class ProjFinAdminController extends BaseController{



    @Autowired
    private ProjFinService projFinService;

    @RequestMapping(value = "/proj/fin/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.FinList.class)
    private ResponseEntity<JsonResult> queryFinancialList(@RequestBody Portfoliofinancialinfo portfoliovaluation) {
        try {
            PageInfo<Portfoliofinancialinfo> pageInfo = projFinService.queryPortfoliofiancialInfo(portfoliovaluation);
            return wrapperPageResult(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/proj/fin/report", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.FinReport.class)
    private ResponseEntity<JsonResult> queryFinancialReportList(@RequestBody Portfoliofinancialinfo portfoliovaluation) {
        try {
            List<Portfoliofinancialinfo> pageInfo = projFinService.queryPortfolioFinInfoListByUsd(portfoliovaluation,getLoginUser());
            return wrapperJsonResult(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/currencies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.ValuationList.class)
    private ResponseEntity<JsonResult> getAllCurrencies() {
        try {
            List<String> currencies = projFinService.getAllCurrencies();
            return wrapperJsonResult(currencies);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/fin/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.ValuationList.class)
    private ResponseEntity<JsonResult> addFinInfo(@RequestBody Portfoliofinancialinfo portfoliovaluation) {
        try {

            int result = projFinService.addProjFinInfo(portfoliovaluation,getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/proj/fin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.ValuationList.class)
    private ResponseEntity<JsonResult> updateFinInfo(@RequestBody Portfoliofinancialinfo portfoliovaluation) {
        try {
            int result = projFinService.updateProjFinInfo(portfoliovaluation, getLoginUser());
            return returnSuccessJson("SUCCESS");

        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/fin/query/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> queryFinInfo(@PathVariable Integer id) {
        try {
            Portfoliofinancialinfo  result = projFinService.queryPortfolioFinInfo(id);
            return wrapperJsonResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/proj/fin/del", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.ValuationList.class)
    private ResponseEntity<JsonResult> delFinInfo(@RequestBody Portfoliofinancialinfo portfoliovaluation) {
        try {
            int result = projFinService.delProjFinInfo(portfoliovaluation,getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

}
