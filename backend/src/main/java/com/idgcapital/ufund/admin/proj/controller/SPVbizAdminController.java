package com.idgcapital.ufund.admin.proj.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.admin.proj.service.SPVAdminService;
import com.idgcapital.ufund.common.controller.AppView;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.model.Spvcaptable;
import com.idgcapital.ufund.model.Spvinfo;
import com.idgcapital.ufund.model.Spvportfolioinfo;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.model.view.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by li_zhao on 2017/12/15.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin")
public class SPVbizAdminController extends BaseController{
    @Autowired
    private SPVAdminService spvAdminService;

    /*
    * SPV基本信息
    * */
    @RequestMapping(value = "/spv/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.SpvEntityList.class)
    private ResponseEntity<JsonResult> querySPVEntityList(@RequestBody Spvinfo spvinfo) {
        try{
            JsonResult jsonResult = spvAdminService.querySPVEntityList(spvinfo);

            jsonResult.setCode(ReturnCode.SUCCESS);
            jsonResult.setReturnMessage("SUCCESS");

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/spv/tree", method = RequestMethod.GET)
    private ResponseEntity<JsonResult> querySPVEntityTreeList() {
        try{
            JsonResult jsonResult = spvAdminService.querySPVEntityTreeList();

            jsonResult.setCode(ReturnCode.SUCCESS);
            jsonResult.setReturnMessage("SUCCESS");

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/spv/entity/{id}", method = RequestMethod.GET)
    @JsonView(AppView.SpvDetail.class)
    public ResponseEntity<JsonResult> querySPVEntityById(@PathVariable String id) {
        try{
            Spvinfo spvinfo = spvAdminService.querySPVEntityById(id);
            return wrapperJsonResult(spvinfo);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/spv/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addSPVEntity(@RequestBody Spvinfo spvinfo) {
        try{
            spvAdminService.addSPVEntity(spvinfo,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/spv/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateSPVEntity(@RequestBody Spvinfo spvinfo) {
        try{
            spvAdminService.updateSPVEntity(spvinfo,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/spv/delete/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteSPVEntityById(@PathVariable String id) {
        try{
            spvAdminService.deleteSPVEntityById(id);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    /*
    * SPV股东信息
    * */
    @RequestMapping(value = "/spv/investor/list/{id}", method = RequestMethod.GET)
    @JsonView(AppView.SpvInvestorDetail.class)
    private ResponseEntity<JsonResult> queryInvestorListBySPVId(@PathVariable String id) {
        try{
            Spvcaptable spvcaptable = new Spvcaptable();
            spvcaptable.setSpvid(id);
            JsonResult jsonResult = spvAdminService.queryInvestorListBySPVId(spvcaptable);

            jsonResult.setCode(ReturnCode.SUCCESS);
            jsonResult.setReturnMessage("SUCCESS");

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/spv/investor/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateSPVInvestorList(@RequestBody List<Spvcaptable> spvcaptableList) {
        try{
            spvAdminService.updateSPVInvestorList(spvcaptableList,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/spv/investor/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteSPVPortfolioById(@RequestBody Spvcaptable spvcaptable) {
        try{
            spvAdminService.deleteSPVInvestors(spvcaptable);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/spv/investor/sublist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.SpvInvestorDetail.class)
    public ResponseEntity<JsonResult> queryInvestorListByDate(@RequestBody Spvcaptable spvcaptable) {
        try{
            JsonResult jsonResult = spvAdminService.queryInvestorListBySPVId(spvcaptable);

            jsonResult.setCode(ReturnCode.SUCCESS);
            jsonResult.setReturnMessage("SUCCESS");

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    /*
    * SPV投资信息
    * */
    @RequestMapping(value = "/proj/spvportfolio/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.SpvPortfolioList.class)
    private ResponseEntity<JsonResult> querySPVPortfolioList(@RequestBody Spvportfolioinfo spvportfolioinfo) {
        try{
            List<Spvportfolioinfo> spvportfolioinfoList = spvAdminService.querySPVPortfolioList(spvportfolioinfo);
            return wrapperJsonResult(spvportfolioinfoList);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/spvportfolio/entity/{id}", method = RequestMethod.GET)
    @JsonView(AppView.SpvPortfolioList.class)
    public ResponseEntity<JsonResult> querySPVPortfolioById(@PathVariable Integer id) {
        try{
            Spvportfolioinfo spvportfolioinfo = spvAdminService.querySPVPortfolioById(id);
            return wrapperJsonResult(spvportfolioinfo);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/spvportfolio/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addSPVPortfolio(@RequestBody Spvportfolioinfo spvportfolioinfo) {
        try{
            spvAdminService.addSPVPortfolio(spvportfolioinfo,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/spvportfolio/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateSPVPortfolio(@RequestBody Spvportfolioinfo spvportfolioinfo) {
        try{
            spvAdminService.updateSPVPortfolio(spvportfolioinfo,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/spvportfolio/delete/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteSPVPortfolioById(@PathVariable Integer id) {
        try{
            spvAdminService.deleteSPVPortfolioById(id);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

}
