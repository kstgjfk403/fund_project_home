package com.idgcapital.ufund;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idgcapital.ufund.admin.proj.service.DataOperationService;
import com.idgcapital.ufund.admin.proj.service.InvestAdminService;
import com.idgcapital.ufund.admin.proj.service.ValuationService;
import com.idgcapital.ufund.model.Portfoliocomparable;
import com.idgcapital.ufund.model.Portfoliovaluation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.idgcapital.ufund.model.enums.ValuationMethod.OPM;
import static com.idgcapital.ufund.model.enums.ValuationMethod.PE;
import static com.idgcapital.ufund.model.enums.ValuationType.QUARTERLY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by yi_zhang on 2018/1/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessJsonTest {


    private MockMvc mvc;


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ValuationService valuationService;

    @Autowired
    private InvestAdminService investAdminService;

    @Autowired
    private DataOperationService dataOperationService;


    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void searchValuationList() throws Exception {

        Portfoliovaluation search = new Portfoliovaluation();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        search.setFundIds("F0025");
        search.setValuationdate(sdf.parse("2016-01-31"));
        search.setPageIndex(1);
        search.setPageCount(5);

        MvcResult result = this.mvc.perform(post("/api/admin/valuation/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

/*    @Test
    public void queryPortfolioBovListByPortfolioId() throws Exception {

        *//*PortfolioBov search = new PortfolioBov();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        search.setPortfolioid("P00818");*//*
        //search.setValuationdate(sdf.parse("2016-01-31"));
        String id = "P00818";

        MvcResult result = this.mvc.perform(get(new URI("/api/admin/valuation/list"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }*/


    @Test
    public void addPortfolioComparable() throws Exception {

        Portfoliocomparable search = new Portfoliocomparable();
        search.setPid("F0017");
        search.setComparableName("testcompany3");
        search.setMultiple(new BigDecimal(10));
        search.setValueDate(new Date());
        search.setWeight(new BigDecimal("0.4"));

        MvcResult result = this.mvc.perform(post("/api/admin/valuation/comparable/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }


    @Test
    public void queryPortfolioDetail() throws Exception {

        Portfoliovaluation search = new Portfoliovaluation();
        search.setValuationid(3924);

        MvcResult result = this.mvc.perform(post("/api/admin/valuation/detail/3924")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }


    @Test
    public void updatePortfolioDetail() throws Exception {

        Portfoliovaluation search = new Portfoliovaluation();
        search.setValuationid(3924);

        Portfoliovaluation portfoliovaluation = valuationService.queryPortfolioValuationDetail(3924);

        portfoliovaluation.setFairvalue(new BigDecimal("233232111"));
        portfoliovaluation.setModifieddate(new Date());
        portfoliovaluation.setValuation(new BigDecimal("999999"));
        portfoliovaluation.setAdditional(new BigDecimal("322111"));
        MvcResult result = this.mvc.perform(post("/api/admin/valuation/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(portfoliovaluation)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }


    @Test
    public void addPortfolioValuation() throws Exception {

        Portfoliovaluation search = new Portfoliovaluation();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        search.setPortfolioid("P00818");

        search.setFundid("F0022");
        search.setValuationmethod(OPM.name());
        search.setValuationdate(sdf.parse("2018-01-31"));
        search.setFairvalue(new BigDecimal(6000000));
        search.setValuation(new BigDecimal("138000"));
        search.setFin48tax(new BigDecimal("0"));
        search.setNetvaluation(new BigDecimal("322323"));
        search.setValuationtype(QUARTERLY.name());
        search.setDiscount(new BigDecimal(1));
        search.setAdditional(new BigDecimal("0"));
        search.setProp(new BigDecimal("0.3"));
        MvcResult result = this.mvc.perform(post("/api/admin/valuation/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }


    @Test
    public void generateReport() throws Exception {

        Portfoliovaluation search = new Portfoliovaluation();
        search.setValuationid(3957);


        MvcResult result = this.mvc.perform(post("/api/admin/valuation/generatereport")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void getPortfolioComparableList() throws Exception {
        Portfoliocomparable search = new Portfoliocomparable();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        search.setPid("F0017");

        MvcResult result = this.mvc.perform(post("/api/admin/valuation/comparable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void queryIndiValue() throws Exception {

        Portfoliovaluation search = new Portfoliovaluation();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        search.setPortfolioid("P00958");
        search.setValuationmethod(PE.name());
        search.setValuationdate(sdf.parse("2018-01-31"));

        MvcResult result = this.mvc.perform(post("/api/admin/valuation/queryindivalue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void queryLatest() throws Exception {

        Portfoliovaluation search = new Portfoliovaluation();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        search.setPortfolioid("P00818");
        search.setFundid("F0022");
        search.setValuationdate(sdf.parse("2017-01-31"));

        MvcResult result = this.mvc.perform(post("/api/admin/valuation/querylastround")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void dataOperation() throws Exception {
//        dataOperationService.dealWithPortfolioStatusHistory();
    }
}
