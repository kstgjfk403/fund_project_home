package com.idgcapital.ufund.sys.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.model.DdlBaseInfo;
import com.idgcapital.ufund.model.Fundbasicinfo;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.mybatis.dao.FundbasicinfoMapper;
import com.idgcapital.ufund.sys.service.SysBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by li_zhao on 2017/12/18.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class SysBaseInfoController extends BaseController{

    @Autowired
    private SysBaseInfoService sysBaseInfoService;

    @Autowired
    private FundbasicinfoMapper fundbasicinfoMapper;

//    入参的type为字典表名的逗号分隔
    @RequestMapping(value = "/sys/dict",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.DictBaseInfo.class)
    public ResponseEntity<JsonResult> querySysDictList(@RequestBody DdlBaseInfo ddlBaseInfo) {
        try{
            List<DdlBaseInfo> ddlBaseInfoList = sysBaseInfoService.querySysDictList(ddlBaseInfo);
            return wrapperJsonResult(ddlBaseInfoList);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/sys/fundfamilyList",method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> queryFundFamilyList() {
        try{
            List<Map<String,Object>> fundList = fundbasicinfoMapper.selectFundFamilyList();
            return wrapperJsonResult(fundList);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/sys/fund/{fundfamily}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AppView.FundForQuery.class})
    public ResponseEntity<JsonResult> queryFundsByFamily(@PathVariable String fundfamily) {
        try{
            List<Fundbasicinfo> fundList = fundbasicinfoMapper.selectFundByFamily(fundfamily);
            return wrapperJsonResult(fundList);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }

    }

}
