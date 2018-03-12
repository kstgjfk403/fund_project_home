package com.idgcapital.ufund.proj.controller;

import com.idgcapital.ufund.model.Portfoliobasicinfo;
import com.idgcapital.ufund.proj.service.ProjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by yi_zhang on 2017/12/8.
 */
@Controller
public class ProjBizJspController {


    @Autowired
    private ProjService projService;


    @RequestMapping(value = "/projlist",method = RequestMethod.GET)
    public String hello(Model model) {
        List<Portfoliobasicinfo> portfoliobasicinfos = projService.queryProjInfos();
        model.addAttribute("infos", portfoliobasicinfos);
        return "proj";
    }


}
