package com.idgcapital.ufund;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idgcapital.ufund.admin.proj.service.ValuationService;
import com.idgcapital.ufund.model.Portfoliocomparable;
import com.idgcapital.ufund.model.Portfolioequityinvestment;
import com.idgcapital.ufund.model.Portfoliovaluation;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by yi_zhang on 2018/1/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValuationJsonTest {


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
    public void searchValuationList() throws Exception {
        Portfoliovaluation search = new Portfoliovaluation();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        search.setFundIds("F0025");
        search.setValuationdate(sdf.parse("2016-01-31"));
        search.setPageIndex(1);
        search.setPageCount(5);

        this.mvc.perform(post("/api/admin/valuation/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andExpect(status().is2xxSuccessful());
    }

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


        this.mvc.perform(post("api/admin/valuation/comparable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andExpect(status().is2xxSuccessful());

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
    public void queryPortfolioEquityInvestment() throws Exception {

        Portfoliovaluation search = new Portfoliovaluation();

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        search.setPortfolioid("P00818");
        search.setFundid("F0022");
        search.setValuationdate(sdf.parse("2017-01-31"));*/


        MvcResult result = this.mvc.perform(get("/api/admin/fund/invest/equity/list/"+"F0025")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(search)))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        JSONObject json = new JSONObject(result.getResponse().getContentAsString());
        JSONArray portfolioEquityInvestmentList = (JSONArray)json.get("data");
        for(int i=0 ;i<portfolioEquityInvestmentList.length(); i++){
            JSONObject portfolioEquityInvestment =  new JSONObject(portfolioEquityInvestmentList.get(i).toString());
            Portfolioequityinvestment origInvestment = new Portfolioequityinvestment();
            origInvestment = new ObjectMapper().readValue(portfolioEquityInvestmentList.get(i).toString(), Portfolioequityinvestment.class);
            //String investType = portfolioEquityInvestment.get("investType").toString();
            System.out.println(origInvestment);
            if("Convert To Equity Interest".equals(origInvestment.getInvesttype())){
                /*Portfolioequityinvestment portfolioequityinvestment = new Portfolioequityinvestment();
                portfolioequityinvestment.setPortfolioid(portfolioEquityInvestment.get("portfolioID").toString());
                portfolioequityinvestment.setFundid(portfolioEquityInvestment.get("fundID").toString());
                portfolioequityinvestment.setInvesttype(portfolioEquityInvestment.get("investType").toString());
                portfolioequityinvestment.setClosedate(portfolioEquityInvestment.);*/


            }

        }

    }



}
