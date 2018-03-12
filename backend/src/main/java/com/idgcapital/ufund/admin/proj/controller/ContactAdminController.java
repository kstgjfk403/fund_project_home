package com.idgcapital.ufund.admin.proj.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.admin.proj.service.ContactAdminService;
import com.idgcapital.ufund.common.controller.AppView;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.model.Portfoliocontactorinfo;
import com.idgcapital.ufund.model.view.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ContactAdminController extends BaseController {
    @Autowired
    private ContactAdminService contactAdminService;

    @RequestMapping(value = "/proj/contact/list/{portfolioID}",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryProjContactEntityListByPorjId(@PathVariable String portfolioID) {
        try{
            List<Portfoliocontactorinfo> portfoliocontactorinfos = contactAdminService.queryProjContactEntityListByProjId(portfolioID);
            return wrapperJsonResult(portfoliocontactorinfos);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/contact/entity/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryProjEntityById(@PathVariable Integer id) {
        try{
            Portfoliocontactorinfo portfoliocontactorinfo = contactAdminService.queryProjContactById(id);
            return wrapperJsonResult(portfoliocontactorinfo);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/contact/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addProjContactEntity(@RequestBody Portfoliocontactorinfo portfoliocontactorinfo) {
        try{
            contactAdminService.addProjContactInfo(portfoliocontactorinfo,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/contact/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateProjContactEntityById(@RequestBody Portfoliocontactorinfo portfoliocontactorinfo) {
        try{
            contactAdminService.updateProjContactInfo(portfoliocontactorinfo,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/proj/contact/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteProjContactEntityById(@PathVariable Integer id) {
        try{
            contactAdminService.deleteProjContactEntityById(id);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

}
