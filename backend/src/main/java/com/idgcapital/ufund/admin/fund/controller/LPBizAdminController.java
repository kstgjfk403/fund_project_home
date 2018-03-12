package com.idgcapital.ufund.admin.fund.controller;

import com.github.pagehelper.PageInfo;
import com.idgcapital.ufund.admin.fund.service.LPBizAdminService;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.model.Capitalcommitment;
import com.idgcapital.ufund.model.Lpbasicinfo;
import com.idgcapital.ufund.model.Portfoliofinancialinfo;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.model.view.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by li_zhao on 2017/12/19.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin")
public class LPBizAdminController extends BaseController{
    @Autowired
    private LPBizAdminService lpBizAdminService;


    @RequestMapping(value = "/fund/lp/list",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryLPEntityList(@RequestBody Lpbasicinfo lpbasicinfo) {
        try{
            PageInfo<Lpbasicinfo> pageInfo = lpBizAdminService.queryLPEntityList(lpbasicinfo);
            return wrapperPageResult(pageInfo);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fund/lp/entity/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryLPEntityById(@PathVariable String id) {
        try{
            Lpbasicinfo lpbasicinfo = lpBizAdminService.queryLPEntityById(id);
            return wrapperJsonResult(lpbasicinfo);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fund/lp/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addLPEntity(@RequestBody Lpbasicinfo lpbasicinfo) {
        try{
            lpBizAdminService.addLPEntity(lpbasicinfo,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fund/lp/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateLPEntityById(@RequestBody Lpbasicinfo lpbasicinfo) {
        try{
            lpBizAdminService.updateLPEntityById(lpbasicinfo, getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fund/lp/delete/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteLPEntityById(@PathVariable String id) {
        try{
            lpBizAdminService.deleteLPById(id);
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/fund/capitalcommitment/list",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryCapitalCommitmentEntityList(@RequestBody Capitalcommitment capitalCommitment) {
        try{

            PageInfo<Lpbasicinfo> pageInfo = lpBizAdminService.queryCapitalCommitmentEntityList(capitalCommitment);
            return wrapperPageResult(pageInfo);

        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/fund/capitalcommitment/entity/{id}",method = RequestMethod.GET )
    public ResponseEntity<JsonResult> queryCommitment(@PathVariable Integer id) {
        try{
            Capitalcommitment capitalcommitment = lpBizAdminService.queryCapitalCommitmentDetail(id);
            return wrapperJsonResult(capitalcommitment);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/fund/lp/listwithoutpage",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryLpEntityListWithoutPage() {
        try{
            List<Lpbasicinfo> lpbasicinfoList = lpBizAdminService.queryLpEntityListWithoutPage();
            return wrapperJsonResult(lpbasicinfoList);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }
    @RequestMapping(value = "/fund/capitalcommitment/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addCapitalCommitment(@RequestBody Capitalcommitment capitalCommitment) {
        try{
            lpBizAdminService.addCapitalCommitment(capitalCommitment,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }
    @RequestMapping(value = "/fund/capitalcommitment/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updateCapitalCommitmentById(@RequestBody Capitalcommitment capitalCommitment) {
        try{
            lpBizAdminService.updateCapitalCommitmentById(capitalCommitment, getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/fund/capitalcommitment/delete/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deleteCommitment(@PathVariable Integer id) {
        try{
            int result = lpBizAdminService.deleteCapitalCommitment(id);
            return returnSuccessJson("SUCCESS");
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }




    @RequestMapping(value = "/fund/capitalcommitment/listfortransfer",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryCapitalCommitmentEntityListForTransfer(@RequestBody Capitalcommitment capitalCommitment) {
        try{
            List<Map<String,Object>> capitalCommitmentListForTransfer = lpBizAdminService.queryCapitalCommitmentEntityListForTransfer(capitalCommitment);
            return wrapperJsonResult(capitalCommitmentListForTransfer);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }
    @RequestMapping(value = "/fund/capitalcommitment/addtransfer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addCapitalCommitmentTransfer(@RequestBody Capitalcommitment capitalCommitment) {
        try{
            lpBizAdminService.addCapitalCommitmentTransfer(capitalCommitment,getLoginUser());
            return returnSuccessJson("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

}
