package com.idgcapital.ufund.admin.proj.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.PageInfo;
import com.idgcapital.ufund.admin.proj.service.ValuationService;
import com.idgcapital.ufund.common.controller.AppView;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.model.Portfoliocomparable;
import com.idgcapital.ufund.model.Portfoliovaluation;
import com.idgcapital.ufund.model.ValuationReport;
import com.idgcapital.ufund.model.enums.ValuationMethod;
import com.idgcapital.ufund.model.view.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yi_zhang on 2018/1/25.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin/valuation")
public class ValuationAdminController extends BaseController{


    @Autowired
    private ValuationService valuationService;


    @RequestMapping(value = "/method", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> queryValuationMethod() {
        try {
            List<String> queryNames = new ArrayList<>();
            for (ValuationMethod valuationMethod : ValuationMethod.values()) {
                 queryNames.add(valuationMethod.name());
            }
            return wrapperJsonResult(queryNames);

        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/queryindivalue", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> queryIndiValue(@RequestBody Portfoliovaluation portfoliovaluation) {
        try {
            Map<String,Object> names = valuationService.queryIndiValue(portfoliovaluation);
            return wrapperJsonResult(names);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/queryowner", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> queryOwnerShip(@RequestBody Portfoliovaluation portfoliovaluation) {
        try {
            Map<String,Object> names = valuationService.queryOwnerShip(portfoliovaluation);
            return wrapperJsonResult(names);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/querylastround", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> queryLatestRound(@RequestBody Portfoliovaluation portfoliovaluation) {
        try {
            Map<String,Object> names = valuationService.queryLatestValue(portfoliovaluation);
            return wrapperJsonResult(names);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/generatereport/{valuationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> generateReport(@PathVariable Integer valuationId) {
        try {
            ValuationReport report = valuationService.generateValuationReport(valuationId);
            return wrapperJsonResult(report);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.ValuationList.class)
    private ResponseEntity<JsonResult> queryValuationList(@RequestBody Portfoliovaluation portfoliovaluation) {
        try {
            PageInfo<Portfoliovaluation> pageInfo = valuationService.queryPortfolioValuationList(portfoliovaluation);
            return wrapperPageResult(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.ValuationDetail.class)
    private ResponseEntity<JsonResult> queryPortfolioValuationDetail(@PathVariable Integer id) {
        try {
            Portfoliovaluation valuation = valuationService.queryPortfolioValuationDetail(id);
            return wrapperJsonResult(valuation);

        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> addPortfolioValuation(@RequestBody Portfoliovaluation portfoliovaluation) {
        try {

            Integer result = valuationService.addPortfolioValuation(portfoliovaluation);
            if(result >= 0)
               return returnSuccessJson("SUCCESS");
            else
               return returnErrorJson("ERROR");

        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> updatePortfolioValuation(@RequestBody Portfoliovaluation portfoliovaluation) {
        try {
            int result = valuationService.updatePortfolioValuation(portfoliovaluation);
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/read", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> readValuation(@RequestBody Portfoliovaluation portfoliovaluation) {
        try {
            Portfoliovaluation valuation = valuationService.readValuation(portfoliovaluation);
            return wrapperJsonResult(valuation);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/del", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> delPortfolioValuation(@RequestBody Portfoliovaluation portfoliovaluation) {
        try {

            valuationService.delPortfolioValuation(portfoliovaluation);
            return returnSuccessJson("SUCCESS");

        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/comparable", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> queryComparableCompany(@RequestBody Portfoliocomparable search) {
        try {

            List<Portfoliocomparable> valuationList = valuationService.queryPortfolioComparableList(search);
            return wrapperJsonResult(valuationList);

        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/comparable/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> addPortfolioComparable(@RequestBody Portfoliocomparable portfoliocomparable) {
        try {

            valuationService.addComparableCompany(portfoliocomparable);
            return returnSuccessJson("SUCCESS");

        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/comparable/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> updatePortfolioComparable(@RequestBody Portfoliocomparable portfoliocomparable) {
        try {

            Integer result = valuationService.updateComparable(portfoliocomparable);
            return returnSuccessJson("SUCCESS");

        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/comparable/del/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> delPortfolioComparable(@PathVariable Integer id) {
        try {
            Integer result = valuationService.delComparableCompany(id);
            return returnSuccessJson("SUCCESS");

        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

}
