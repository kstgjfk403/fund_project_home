package com.idgcapital.ufund.sys.controller;

import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.model.DdlPage;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by li_zhao on 2017/12/19.
 */
@RestController
@RequestMapping(value = "/sys")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/menu/list",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<JsonResult> queryMenuList(@RequestBody DdlPage ddlPage) {
        try{
            List<DdlPage> ddlPageList = sysMenuService.queryMenuList(ddlPage);
            return wrapperJsonResult(ddlPageList);
        }
        catch (Exception e){
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



}
