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

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by yi_zhang on 2018/3/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportJsonTest {





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
    public void queryBusiness() throws Exception {

        Map<String,Object> param  = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        param.put("isEquityOnly","N");
        param.put("closeDate","2017-12-31");
        param.put("fundId","F0025");


        MvcResult result = this.mvc.perform(post("/api/admin/proj/managereport/businesssummary")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(param))).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
