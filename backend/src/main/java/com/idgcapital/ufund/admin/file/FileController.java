package com.idgcapital.ufund.admin.file;

import com.idgcapital.ufund.admin.proj.service.ProjAdminService;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.model.Portfoliobasicinfo;
import com.idgcapital.ufund.model.view.JsonResult;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by yi_zhang on 2018/3/9.
 */
@Controller
@CrossOrigin
public class FileController extends BaseController {


    @Autowired
    private ProjAdminService projAdminService;

    @RequestMapping(value = "/api/admin/logo/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> upload(@RequestParam("upload") MultipartFile upload, @RequestParam("portfolioId") String portfolioId) {

        try {

            Portfoliobasicinfo portfoliobasicinfo = projAdminService.queryProjInfoById(portfolioId);
            String realPath = request.getSession().getServletContext().getRealPath("/docs/logos");
            FileUtils.copyInputStreamToFile(upload.getInputStream(), new File(realPath, upload.getOriginalFilename()));
            String logoPath = "/docs/logos/" + upload.getOriginalFilename();
            portfoliobasicinfo.setLogo(logoPath);
            projAdminService.updateProjInfo(portfoliobasicinfo, getLoginUser());
            return returnSuccessJson("SUCCESS");

        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

}
