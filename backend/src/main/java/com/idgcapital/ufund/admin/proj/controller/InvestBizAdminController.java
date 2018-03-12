package com.idgcapital.ufund.admin.proj.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.admin.proj.service.InvestAdminService;
import com.idgcapital.ufund.common.controller.AppView;
import com.idgcapital.ufund.common.controller.BaseController;
import com.idgcapital.ufund.common.util.DateUtil;
import com.idgcapital.ufund.common.util.StringUtil;
import com.idgcapital.ufund.model.*;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.mybatis.dao.PortfoliowarrantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by li_zhao on 2018/1/11.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin")
public class InvestBizAdminController extends BaseController {
    @Autowired
    private InvestAdminService investAdminService;

    //    note
    @RequestMapping(value = "/proj/invest/note/list/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioNoteList.class)
    public ResponseEntity<JsonResult> queryPortfolioNoteListByPortfolioId(@PathVariable String id) {
        try {
            List<Portfolionote> portfolionoteList = investAdminService.queryPortfolioNoteListByPortfolioId(id);
            return wrapperJsonResult(portfolionoteList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/proj/invest/note/entity/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioNoteDetail.class)
    public ResponseEntity<JsonResult> queryPortfolioNoteEntityById(@PathVariable String id) {
        try {
            Portfolionote portfolionote = investAdminService.queryPortfolioNoteById(id);
            return wrapperJsonResult(portfolionote);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/invest/note/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addPortfolioNoteEntity(@RequestBody Portfolionote portfolionote) {
        try {
            investAdminService.addPortfolioNote(portfolionote, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/proj/invest/note/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updatePortfolioNoteEntity(@RequestBody Portfolionote portfolionote) {
        try {
            investAdminService.updatePortfolioNote(portfolionote, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/invest/note/delete/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deletePortfolioNoteEntityById(@PathVariable String id) {
        try {
            investAdminService.deletePortfolioNoteById(id);
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

//    invest

    @RequestMapping(value = "/proj/invest/equity/list/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioEquityInvestmentList.class)
    public ResponseEntity<JsonResult> queryPortfolioEquityListByPortfolioId(@PathVariable String id) {
        try {
            List<Portfolioequityinvestment> portfolioequityinvestmentList = investAdminService.queryPortfolioEquityListByPortfolioId(id);
            return wrapperJsonResult(portfolioequityinvestmentList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/invest/equity/entity/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioEquityInvestmentDetail.class)
    public ResponseEntity<JsonResult> queryPortfolioInvestmentEntityById(@PathVariable String id) {
        try {
            Portfolioequityinvestment portfolioequityinvestment = investAdminService.queryPortfolioInvestmentEntityById(id);
            return wrapperJsonResult(portfolioequityinvestment);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/invest/equity/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addPortfolioInvestmentEntity(@RequestBody Portfolioequityinvestment portfolioequityinvestment) {
        try {
            investAdminService.addPortfolioInvestmentEntity(portfolioequityinvestment, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/invest/equity/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updatePortfolioEquityEntity(@RequestBody Portfolioequityinvestment portfolioequityinvestment) {
        try {
            investAdminService.updatePortfolioEquityEntity(portfolioequityinvestment, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/invest/equity/delete/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deletePortfolioEquityEntityById(@PathVariable String id) {
        try {
            investAdminService.deletePortfolioEquityById(id,getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    //获取reclassification剩余可转
    @RequestMapping(value = "/proj/invest/reclassification/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioWarrantList.class)
    public ResponseEntity<JsonResult> queryReclassificationByFundFamilyName(@RequestBody Portfolioequityinvestment portfolioequityinvestment) {
        try {
            List<Map<String,Object>> portfolioequityinvestmentList = investAdminService.queryReclassificationByFundFamilyName(portfolioequityinvestment);
            return wrapperJsonResult(portfolioequityinvestmentList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    //获取剩余有效warrant
    @RequestMapping(value = "/proj/invest/warrantremain/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioWarrantList.class)
    public ResponseEntity<JsonResult> queryWarrantRemainByFundFamilyName(@RequestBody Portfoliowarrant portfoliowarrant) {
        try {
            List<Map<String,Object>> portfoliowarrantList = investAdminService.queryWarrantRemainByFundFamilyName(portfoliowarrant);
            return wrapperJsonResult(portfoliowarrantList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    //loan to equity
    @RequestMapping(value = "/proj/invest/n2i/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioLoanToEquityList.class)
    public ResponseEntity<JsonResult> queryPortfolioNoteByFundFamilyName(@RequestBody Portfolioloantoequity portfolioloantoequity) {
        try {
            List<Portfolioloantoequity> portfolioloantoequityList = investAdminService.queryPortfolioNoteByFundFamilyName(portfolioloantoequity);
            return wrapperJsonResult(portfolioloantoequityList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    //cap table新建股东
    @RequestMapping(value = "/proj/captable/investor/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addCapTableInvestor(@RequestBody Portfoliocaptablename portfoliocaptablename) {
        try {
            investAdminService.addCapTableInvestor(portfoliocaptablename, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson(e.getMessage());
        }
    }

    //cap table 获取detail列表
    @RequestMapping(value = "/proj/captable/investor/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioEquityInvestmentDetail.class)
    public ResponseEntity<JsonResult> queryCapTableInvestorList(@RequestBody Portfoliocaptablevaluedetail portfoliocaptablevaluedetail) {
        try {
            List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList = investAdminService.queryCapTableDetailInvestorList(portfoliocaptablevaluedetail);
            return wrapperJsonResult(portfoliocaptablevaluedetailList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    //cap table 汇总展示
    @RequestMapping(value = "/proj/captable/list/header/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioCapTableList.class)
    public ResponseEntity<JsonResult> queryCapTableHeaderList(@PathVariable String id) {
        try {
            List<Map<String, Object>> headerList = investAdminService.queryCapTableHeaderList(id);
            return wrapperJsonResult(headerList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/captable/list/content/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioCapTableList.class)
    public ResponseEntity<JsonResult> queryCapTableList(@PathVariable String id) {
        try {
            List<Map<String, Object>> portfoliocaptablevalueList = investAdminService.queryCapTableList(id);
            return wrapperJsonResult(portfoliocaptablevalueList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    //exit
    @RequestMapping(value = "/proj/exit/list/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioExitList.class)
    public ResponseEntity<JsonResult> queryPortfolioExitListByPortfolioId(@PathVariable String id) {
        try {
            List<Portfolioexit> portfolioexitList = investAdminService.queryPortfolioExitListByPortfolioId(id);
//            List<Portfolioexit> portfolioexitList = investAdminService.queryRemainSharesByFundId( "F0022","P00818", DateUtil.stringtoDate("2017-03-31",DateUtil.LONG_DATE_FORMAT));
//            BigDecimal portfolioexitList = investAdminService.queryUnrealizedCostByFundId( "F0022","P00818", DateUtil.stringtoDate("2017-03-31",DateUtil.LONG_DATE_FORMAT));
            return wrapperJsonResult(portfolioexitList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/exit/entity/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioExitDetail.class)
    public ResponseEntity<JsonResult> queryPortfolioExitListById(@PathVariable int id) {
        try {
            Portfolioexit portfolioexit = investAdminService.queryPortfolioExitListById(id);
            return wrapperJsonResult(portfolioexit);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/exit/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addPortfolioExitEntity(@RequestBody Portfolioexit portfolioexit) {
        try {
            investAdminService.addPortfolioExitEntity(portfolioexit, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/exit/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updatePortfolioExitEntity(@RequestBody Portfolioexit portfolioexit) {
        try {
            investAdminService.updatePortfolioExitEntity(portfolioexit, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/exit/delete/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deletePortfolioExitEntityById(@PathVariable int id) {
        try {
            investAdminService.deletePortfolioExitEntityById(id);
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/exit/share/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(AppView.PortfolioExitShare.class)
    public ResponseEntity<JsonResult> queryShareListByFund(@RequestBody PortfolioexitShare portfolioexitShare) {
        try {
            List<PortfolioexitShare> portfolioexitShareList = investAdminService.queryShareListByFund(portfolioexitShare);

            List<Map<String, Object>> returnMapList = new ArrayList<>();
            for (int i = 0; i < portfolioexitShareList.size(); i++) {
                Map<String, Object> returnMap = new HashMap<>();
                returnMap.put("baseId", i);
                returnMap.put("baseName", portfolioexitShareList.get(i));
                returnMapList.add(returnMap);
            }
            return wrapperJsonResult(returnMapList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    //warrant
    @RequestMapping(value = "/proj/invest/warrant/list/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioWarrantList.class)
    public ResponseEntity<JsonResult> queryPortfolioWarrantListByPortfolioId(@PathVariable String id) {
        try {
            List<Portfoliowarrant> portfoliowarrantList = investAdminService.queryPortfolioWarrantListByPortfolioId(id);
            return wrapperJsonResult(portfoliowarrantList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/invest/warrant/entity/{id}", method = RequestMethod.GET)
    @JsonView(AppView.PortfolioWarrantDetail.class)
    public ResponseEntity<JsonResult> queryPortfolioWarrantEntityById(@PathVariable String id) {
        try {
            Portfoliowarrant portfoliowarrant = investAdminService.queryPortfolioWarrantEntityById(id);
            return wrapperJsonResult(portfoliowarrant);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/invest/warrant/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addPortfolioWarrantEntity(@RequestBody Portfoliowarrant portfoliowarrant) {
        try {
            investAdminService.addPortfolioWarrantEntity(portfoliowarrant, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/proj/invest/warrant/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updatePortfolioWarrantEntity(@RequestBody Portfoliowarrant portfoliowarrant) {
        try {
            investAdminService.updatePortfolioWarrantEntity(portfoliowarrant, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/invest/warrant/delete/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deletePortfolioWarrantEntityById(@PathVariable String id) {
        try {
            investAdminService.deletePortfolioWarrantEntityById(id);
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    //dividend bonusShare
    @RequestMapping(value = "/proj/dividend/list/{id}", method = RequestMethod.GET)

    public ResponseEntity<JsonResult> queryPortfolioDividendListByPortfolioId(@PathVariable String id) {
        try {
            List<Portfoliodividentbonus> portfoliodividentbonusList = investAdminService.queryPortfolioDividendListByPortfolioId(id);
            return wrapperJsonResult(portfoliodividentbonusList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/dividend/entity/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryPortfolioDividendBonusEntityById(@PathVariable String id) {
        try {
            Portfoliodividentbonus portfoliodividentbonus = investAdminService.queryPortfolioDividendBonusEntityById(id);
            return wrapperJsonResult(portfoliodividentbonus);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/bonus/list/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryPortfolioBonusShareListByPortfolioId(@PathVariable String id) {
        try {
            List<Portfoliodividentbonus> portfoliodividentbonusList = investAdminService.queryPortfolioBonusShareListByPortfolioId(id);
            return wrapperJsonResult(portfoliodividentbonusList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }



    @RequestMapping(value = "/proj/dividendbonus/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addPortfolioDividendBonusEntity(@RequestBody Portfoliodividentbonus portfoliodividentbonus) {
        try {


            investAdminService.addPortfolioDividendBonusEntity(portfoliodividentbonus, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/proj/dividendbonus/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updatePortfolioDividendBonusEntity(@RequestBody Portfoliodividentbonus portfoliodividentbonus) {
        try {
            investAdminService.updatePortfolioDividendBonusEntity(portfoliodividentbonus, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/dividendbonus/delete/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deletePortfolioDividendBonusEntity(@PathVariable String id) {
        try {
            investAdminService.deletePortfolioDividendBonusEntityById(id);
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/proj/bov/list/{id}", method = RequestMethod.GET)

    public ResponseEntity<JsonResult> queryPortfolioBovListByPortfolioId(@PathVariable String id) {
        try {
            List<PortfolioBov> portfolioBovList = investAdminService.queryPortfolioBovListByPortfolioId(id);
            return wrapperJsonResult(portfolioBovList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/bov/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> addPortfolioBovEntity(@RequestBody PortfolioBov portfolioBov) {
        try {


            investAdminService.addPortfolioBovEntity(portfolioBov, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    @RequestMapping(value = "/proj/bov/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> updatePortfolioBovEntity(@RequestBody PortfolioBov portfolioBov) {
        try {
            investAdminService.updatePortfolioBovEntity(portfolioBov, getLoginUser());
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/bov/entity/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryPortfolioBovEntityById(@PathVariable int id) {
        try {
            PortfolioBov portfolioBov = investAdminService.queryPortfolioBovEntityById(id);
            return wrapperJsonResult(portfolioBov);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }

    @RequestMapping(value = "/proj/bov/delete/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResult> deletePortfolioBovEntity(@PathVariable int id) {
        try {
            investAdminService.deletePortfolioBovEntity(id);
            return returnSuccessJson("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


    //    note
    @RequestMapping(value = "/fund/invest/equity/list/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> queryPortfolioEquityListByFundId(@PathVariable String id) {
        try {
            List<Portfolioequityinvestment> fundLoanToEquityList = investAdminService.queryPortfolioEquityListByFundId(id);
            return wrapperJsonResult(fundLoanToEquityList);
        } catch (Exception e) {
            e.printStackTrace();
            return returnErrorJson("ERROR");
        }
    }


}
