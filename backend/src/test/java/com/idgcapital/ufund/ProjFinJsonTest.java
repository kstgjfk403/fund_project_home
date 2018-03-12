package com.idgcapital.ufund;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idgcapital.ufund.admin.proj.service.ValuationService;
import com.idgcapital.ufund.model.Portfoliofinancialinfo;
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
import org.thymeleaf.spring4.expression.Mvc;

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by yi_zhang on 2018/2/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjFinJsonTest {


    private MockMvc mvc;


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ValuationService valuationService;


    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void queryCurrency() throws Exception {

        MvcResult result = this.mvc.perform(get("/api/admin/currencies")).andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void queryFinList() throws Exception {

        Portfoliofinancialinfo s = new Portfoliofinancialinfo();
        s.setPortfolioid("P00818");
        s.setPageIndex(1);
        s.setPageCount(3);

        MvcResult result = this.mvc.perform(post("/api/admin/proj/fin/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(s))).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }


    @Test
    public void queryFin() throws Exception {

        MvcResult result = this.mvc.perform(get("/api/admin/proj/fin/query/12027")).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void addFin() throws Exception {

        Portfoliofinancialinfo s = new Portfoliofinancialinfo();
        s.setPortfolioid("P00026");
        s.setStartdate(new Date());
        s.setEnddate(new Date());
        s.setNetprofits(new BigDecimal("1234"));
        s.setRevenue(new BigDecimal("12334"));
        s.setCurrency("USD");
        s.setApproved(false);
        MvcResult result = this.mvc.perform(post("/api/admin/proj/fin/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(s))).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void updateFin() throws Exception {

        Portfoliofinancialinfo s = new Portfoliofinancialinfo();
        s.setPortfoliofinanciallot(12021);
        s.setPortfolioid("P00026");
        s.setStartdate(new Date());
        s.setEnddate(new Date());
        s.setNetprofits(new BigDecimal("1234"));
        s.setRevenue(new BigDecimal("12334"));
        s.setCurrency("USD");
        s.setApproved(false);
        MvcResult result = this.mvc.perform(post("/api/admin/proj/fin/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(s))).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }


    @Test
    public void searchProjFin() throws Exception {

        Portfoliofinancialinfo s = new Portfoliofinancialinfo();

        s.setPortfolioid("P00820");

        MvcResult result = this.mvc.perform(post("/api/admin/proj/fin/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(s))).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }




    @Test
    public void delFin() throws Exception {

        Portfoliofinancialinfo s = new Portfoliofinancialinfo();
        s.setPortfoliofinanciallot(12020);
        MvcResult result = this.mvc.perform(post("/api/admin/proj/fin/del")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(s))).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }


}
