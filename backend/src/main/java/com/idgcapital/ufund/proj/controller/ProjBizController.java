package com.idgcapital.ufund.proj.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.model.Portfoliobasicinfo;
import com.idgcapital.ufund.model.view.DatatableResult;
import com.idgcapital.ufund.proj.service.ProjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yi_zhang on 2017/12/8.
 */
@RestController
public class ProjBizController extends BaseController {


    @Autowired
    private ProjService projService;


    @RequestMapping(value = "/proj", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DatatableResult queryPortfolios() {

        List<Portfoliobasicinfo> infos  = projService.queryProjInfos();
        List<String[]> list = new ArrayList<>();
        for(Portfoliobasicinfo p:infos){
             String[] columns = new String[]{p.getAbbname(),p.getDirectors(),p.getAbbnamechi()};
             list.add(columns);
        }


        DatatableResult result = new DatatableResult();
        result.setData(list);

        return result;
    }

}
