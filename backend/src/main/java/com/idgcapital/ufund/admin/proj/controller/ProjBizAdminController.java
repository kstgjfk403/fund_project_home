package com.idgcapital.ufund.admin.proj.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.admin.proj.service.ProjAdminService;
import com.idgcapital.ufund.common.controller.AppView;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.model.Portfoliobasicinfo;
import com.idgcapital.ufund.model.Portfoliodirector;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.model.view.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by li_zhao on 2017/12/13.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin")
public class ProjBizAdminController extends BaseController {
    @Autowired
    private ProjAdminService projAdminService;

    @RequestMapping(value = "/proj/list",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioList.class)
    private ResponseEntity<JsonResult> queryProjEntityList(@RequestBody Portfoliobasicinfo portfoliobasicinfo) {
        try{
            JsonResult jsonResult = projAdminService.queryProjInfos(portfoliobasicinfo);

            jsonResult.setCode(ReturnCode.SUCCESS);
            jsonResult.setReturnMessage("SUCCESS");

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/proj/entity/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioDetail.class)
    public ResponseEntity<JsonResult> queryProjEntityById(@PathVariable String id) {
        try{
            Portfoliobasicinfo portfoliobasicinfo = projAdminService.queryProjInfoById(id);
            return wrapperJsonResult(portfoliobasicinfo);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addProjEntity(@RequestBody Portfoliobasicinfo portfoliobasicinfo) {
        try{
            projAdminService.addProjInfo(portfoliobasicinfo,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateProjEntityById(@RequestBody Portfoliobasicinfo portfoliobasicinfo) {
        try{
            projAdminService.updateProjInfo(portfoliobasicinfo, getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/delete/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteProjEntityById(@PathVariable String id) {
        try{
            projAdminService.deleteProjInfoById(id);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    //董事监事维护
    @RequestMapping(value = "/proj/director/num/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateProjDirectorById(@RequestBody Portfoliobasicinfo portfoliobasicinfo) {
        try{
            projAdminService.updateProjDirectorById(portfoliobasicinfo, getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/director/list/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioDirectorList.class)
    public ResponseEntity<JsonResult> queryPortfolioDirectorListByPortfolioId(@PathVariable String id) {
        try{
            List<Portfoliodirector> portfoliodirectorList = projAdminService.queryPortfolioDirectorListByPortfolioId(id);
            return wrapperJsonResult(portfoliodirectorList);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/proj/director/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addPortfolioDirector(@RequestBody Portfoliodirector portfoliodirector) {
        try{
            projAdminService.addPortfolioDirector(portfoliodirector,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/director/entity/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioDirectorDetail.class)
    public ResponseEntity<JsonResult> queryPortfolioDirectorById(@PathVariable Integer id) {
        try{
            Portfoliodirector portfoliodirector = projAdminService.queryPortfolioDirectorById(id);
            return wrapperJsonResult(portfoliodirector);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/director/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updatePortfolioDirectorEntity(@RequestBody Portfoliodirector portfoliodirector) {
        try{
            projAdminService.updatePortfolioDirectorEntity(portfoliodirector, getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/director/delete/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deletePortfolioDirectorEntityById(@PathVariable Integer id) {
        try{
            projAdminService.deletePortfolioDirectorEntityById(id);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



}
