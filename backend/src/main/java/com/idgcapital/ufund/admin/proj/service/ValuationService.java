package com.idgcapital.ufund.admin.proj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idgcapital.ufund.common.ThymeCtx;
import com.idgcapital.ufund.model.*;
import com.idgcapital.ufund.model.enums.ValuationMethod;
import com.idgcapital.ufund.mybatis.dao.FundbasicinfoMapper;
import com.idgcapital.ufund.mybatis.dao.PortfoliobasicinfoMapper;
import com.idgcapital.ufund.mybatis.dao.PortfoliocomparableMapper;
import com.idgcapital.ufund.mybatis.dao.PortfoliovaluationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.idgcapital.ufund.model.enums.OpmOperation.*;
import static com.idgcapital.ufund.model.enums.ValuationMethod.*;
import static com.idgcapital.ufund.model.enums.ValuationType.QUARTERLY;
import static com.idgcapital.ufund.model.enums.ValuationType.WB;

/**
 * Created by yi_zhang on 2018/1/22.
 */
@Service
public class ValuationService {


    @Autowired
    private PortfoliovaluationMapper portfoliovaluationMapper;

    @Autowired
    private PortfoliobasicinfoMapper portfoliobasicinfoMapper;

    @Autowired
    private PortfoliocomparableMapper portfoliocomparableMapper;

    @Autowired
    private FundbasicinfoMapper fundbasicinfoMapper;

    @Autowired
    private InvestAdminService investAdminService;

    @Autowired
    private ProjFinService projFinService;

    @Autowired
    private ProjAdminService projAdminService;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private ThymeCtx thymeCtx;

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    private final String ABOVE = "above";
    private final String BELOW = "below";

    public int addComparableCompany(Portfoliocomparable portfoliocomparable) {
        return portfoliocomparableMapper.insert(portfoliocomparable);
    }


    public int updateComparable(Portfoliocomparable portfoliocomparable) {
        return portfoliocomparableMapper.updateByPrimaryKey(portfoliocomparable);
    }

    public int delComparableCompany(Integer cid) {
        return portfoliocomparableMapper.deleteByPrimaryKey(cid);
    }

    public Portfoliovaluation queryPortfolioValuationDetail(Integer id) {

        Portfoliovaluation portfoliovaluation = portfoliovaluationMapper.selectByPrimaryKey(id);

        if (isComparableMethod(portfoliovaluation)) {
            Portfoliocomparable search = new Portfoliocomparable();
            search.setPid(portfoliovaluation.getPortfolioid());
            search.setValueDate(portfoliovaluation.getValuationdate());
            List<Portfoliocomparable> list = queryPortfolioComparableList(search);
            portfoliovaluation.setPortfoliocomparables(list);
        }
        return portfoliovaluation;
    }


    public Map<String, Object> queryIndiValue(Portfoliovaluation search) {
        Map<String, Object> finData = queryLatestFinData(search);
        BigDecimal ownerShip = investAdminService.queryProperByFundId(search.getFundid(), search.getPortfolioid(), search.getValuationdate());
        finData.put("prop", ownerShip);
        return finData;
    }

    public BigDecimal queryBaseIndiValue(Portfoliovaluation search) {
        Portfoliofinancialinfo s = new Portfoliofinancialinfo();
        s.setPortfolioid(search.getPortfolioid());
        s.setEnddate(search.getValuationdate());
        Portfoliofinancialinfo portfoliofinancialinfo = projFinService.queryPortfoloFinInfoUsd(s);
        if (search.getValuationmethod().equals(PE.name())) {
            return portfoliofinancialinfo.getNetprofits();
        } else if (search.getValuationmethod().equals(PS.name())) {
            return portfoliofinancialinfo.getRevenue();
        } else if (search.getValuationmethod().equals(PB.name())) {
            BigDecimal totalAsset = portfoliofinancialinfo.getTotalassets();
            BigDecimal totalliabilities = portfoliofinancialinfo.getTotalliabilities() == null ? BigDecimal.ZERO : portfoliofinancialinfo.getTotalliabilities();
            BigDecimal netAsset = (totalAsset == null ? BigDecimal.ZERO : totalAsset).subtract(totalliabilities);
            return netAsset;
        }
        return BigDecimal.ZERO;
    }

    public Map<String, Object> queryLatestValue(Portfoliovaluation search) {
        BigDecimal postValue = investAdminService.queryPostValue(search.getPortfolioid(), search.getValuationdate());
        BigDecimal prop = investAdminService.queryProperByFundId(search.getFundid(), search.getPortfolioid(), search.getValuationdate());
        Map<String, Object> map = new HashMap<>();
        map.put("fairvalue", postValue);
        map.put("prop", prop);
        if (prop != null)
            map.put("valuation", postValue.multiply(prop));
        return map;
    }

    public Map<String, Object> queryOwnerShip(Portfoliovaluation search) {
        Map<String, Object> finData = new HashMap<>();
        BigDecimal ownerShip = projAdminService.queryPortfolioOwnerShip(search);
        finData.put("prop", ownerShip);
        return finData;
    }

    public Map<String, Object> queryLatestFinData(Portfoliovaluation search) {

        String method = search.getValuationmethod();
        Map<String, Object> result = new HashMap<>();
        Portfoliofinancialinfo s = new Portfoliofinancialinfo();
        s.setPortfolioid(search.getPortfolioid());
        s.setEnddate(search.getValuationdate());
        Portfoliofinancialinfo portfoliofinancialinfo = projFinService.queryPortfoloFinInfoUsd(s);

        if (portfoliofinancialinfo != null) {
            if (method.equals(EV.name())) {
                result.put("Revenue", portfoliofinancialinfo.getRevenue());
                result.put("debt", portfoliofinancialinfo.getCashlevel());
                result.put("cashassets", portfoliofinancialinfo.getCashassets());
            } else if (method.equals(PE.name())) {
                result.put("NetProfit", portfoliofinancialinfo.getNetprofits());
            } else if (method.equals(PS.name())) {
                result.put("Revenue", portfoliofinancialinfo.getRevenue());
            } else if (method.equals(PB.name())) {
                BigDecimal totalAsset = portfoliofinancialinfo.getTotalassets();
                BigDecimal totalliabilities = portfoliofinancialinfo.getTotalliabilities() == null ? BigDecimal.ZERO : portfoliofinancialinfo.getTotalliabilities();
                BigDecimal netAsset = (totalAsset == null ? BigDecimal.ZERO : totalAsset).subtract(totalliabilities);
                result.put("NetAsset", netAsset);
            }
        }
        return result;
    }

    public ValuationReport generateValuationReport(Integer valuationId) {
        Portfoliovaluation v = portfoliovaluationMapper.selectByPrimaryKey(valuationId);
        Portfoliobasicinfo portfoliobasicinfo = portfoliobasicinfoMapper.selectByPrimaryKey(v.getPortfolioid());
        ValuationReport valuationReport = new ValuationReport();
        Fundbasicinfo fund = fundbasicinfoMapper.selectByPrimaryKey(v.getFundid());
        switch (ValuationMethod.valueOf(v.getValuationmethod())) {
            case PE:
            case PS:
            case PB:
            case EV:
                setFinancialInfo(valuationReport, v);
                setComparable(valuationReport, v);
                setAdditonal(valuationReport, v);
                valuationReport.setPortfoliovaluation(v);
                valuationReport.setName(portfoliobasicinfo.getAbbname());
                valuationReport.setPortfolioFullName(portfoliobasicinfo.getFullname());
                valuationReport.setFundFamilyName(fund.getFundfamillyname());
                break;
            case OPM:
                valuationReport.setWebsite(portfoliobasicinfo.getWebsite());
                valuationReport.setFundFamilyName(fund.getFundfamillyname());
                valuationReport.setName(portfoliobasicinfo.getAbbname());
                setInvestRoundAndCost(valuationReport, fund, v);
                break;
            default:
                break;
        }
        return valuationReport;
    }

    public Portfoliovaluation queryValuationByFund(String fundId, String pid, Date valueDate) {
        Portfoliovaluation search = new Portfoliovaluation();
        search.setFundid(fundId);
        search.setPortfolioid(pid);
        search.setValuationdate(valueDate);
        return portfoliovaluationMapper.queryValuationByFund(search);
    }

    public Portfoliovaluation queryLatestQuarterValuation(String fundId, String pid, Date valueDate) {
        Portfoliovaluation search = new Portfoliovaluation();
        search.setFundid(fundId);
        search.setPortfolioid(pid);
        search.setValuationdate(valueDate);
        return portfoliovaluationMapper.queryLastQuarterValuation(search);

    }


    private void setInvestRoundAndCost(ValuationReport valuationReport, Fundbasicinfo fund, Portfoliovaluation v) {
        List<Fundbasicinfo> fundList = fundbasicinfoMapper.selectFundByFamily(fund.getFundfamillyname());
        List<String> roundNames = new ArrayList<>();
        int i = 0;
        Map<String, Object> mainFund = new HashMap<>();
        Map<String, Object> sideFund = new HashMap<>();

        BigDecimal totalCarryValue = BigDecimal.ZERO;
        BigDecimal totalOpm = BigDecimal.ZERO;
        List<Long> totalShare = new ArrayList<>();
        for (Fundbasicinfo fundbasicinfo : fundList) {
            List<Portfolioexit> portfolioexits = investAdminService.queryRemainSharesByFundId(fundbasicinfo.getFundid(), v.getPortfolioid(), v.getValuationdate());
            List<Long> shares = new ArrayList<>();
            if (i == 0) {
                int j = 0;
                for (Portfolioexit portfolioexit : portfolioexits) {

                    if (portfolioexit.getShareremain() != 0 && portfolioexit.getShareremain() > 0) {
                        String s = portfolioexit.getRound();
                        roundNames.add(s + "-" + sdf.format(portfolioexit.getClosedate()));
                        shares.add(portfolioexit.getShareremain());

                    }
                    j++;
                }
            }

            Portfoliovaluation valuation = queryValuationByFund(fundbasicinfo.getFundid(), v.getPortfolioid(), v.getValuationdate());
            Portfoliovaluation lastValuation = queryLatestQuarterValuation(fundbasicinfo.getFundid(), v.getPortfolioid(), v.getValuationdate());
            //MainFund SiteFund
            if (!fundbasicinfo.getFundname().contains("side")) {
                setProperty(mainFund, valuation, lastValuation, fundbasicinfo, shares);
                valuationReport.setMainFund(mainFund);
            } else if (fundbasicinfo.getFundname().contains("side")) {
                setProperty(sideFund, valuation, lastValuation, fundbasicinfo, shares);
                valuationReport.setSideFund(sideFund);
            }
            totalCarryValue = totalCarryValue.add(lastValuation.getNetvaluation());
            totalOpm = totalOpm.add(valuation.getNetvaluation());
            i++;
        }

        BigDecimal variation = totalOpm.subtract(totalCarryValue).divide(totalCarryValue, 7, BigDecimal.ROUND_HALF_UP);
        String description = mergeTemplateContent(variation, totalOpm, totalCarryValue);
        Map<String, Object> m = valuationReport.getMainFund();
        Map<String, Object> s = valuationReport.getSideFund();
        if (m != null && m.get("shares") != null) {
            List<Long> ms = (List<Long>) m.get("shares");
            if (s != null && s.get("shares") != null) {
                List<Long> ss = (List<Long>) s.get("shares");
                for (int num = 0; num < ms.size(); num++) {
                    long ts = ms.get(num) + ss.get(num);
                    totalShare.add(ts);
                }
            } else {
                totalShare.addAll(ms);
            }
        }
        valuationReport.setDescription(description);
        valuationReport.setRoundName(roundNames);
        valuationReport.setTotalShare(totalShare);
    }

    private void setProperty(Map<String, Object> fund, Portfoliovaluation valuation, Portfoliovaluation lastValuation, Fundbasicinfo fundbasicinfo, List<Long> shares) {

        BigDecimal cost = investAdminService.queryUnrealizedCostByFundId(fundbasicinfo.getFundid(), valuation.getPortfolioid(), valuation.getValuationdate());
        BigDecimal postValue = BigDecimal.ZERO;

        BigDecimal variation = null;
        if (lastValuation != null && lastValuation.getNetvaluation() != null) {
            postValue = lastValuation.getNetvaluation();
            variation = valuation.getNetvaluation().subtract(lastValuation.getNetvaluation());
        }
        fund.put("fundName", fundbasicinfo.getFundfullnameeng());
        fund.put("cost", cost);
        fund.put("shares", shares);
        fund.put("valuation", valuation.getValuation());
        fund.put("fin48tax", valuation.getFin48tax());
        fund.put("netvaluation", valuation.getNetvaluation());
        fund.put("variation", variation);
        fund.put("carryValue", postValue);
    }


    private void setAdditonal(ValuationReport valuationReport, Portfoliovaluation v) {
        BigDecimal carryCost = investAdminService.queryUnrealizedCostByFundId(v.getFundid(), v.getPortfolioid(), v.getValuationdate());
        valuationReport.setCarryCost(carryCost);
        Portfoliovaluation lastValuation = queryLatestQuarterValuation(v.getFundid(), v.getPortfolioid(), v.getValuationdate());
        if (lastValuation != null) {
            BigDecimal lastNetV = lastValuation.getNetvaluation();
            valuationReport.setCarryValuation(lastNetV);
            if (lastNetV != null)
                valuationReport.setVariation(v.getNetvaluation().subtract(lastNetV));
        }
    }

    private void setComparable(ValuationReport valuationReport, Portfoliovaluation portfoliovaluation) {
        Portfoliocomparable com = new Portfoliocomparable();
        com.setPid(portfoliovaluation.getPortfolioid());
        com.setValueDate(portfoliovaluation.getValuationdate());
        List<Portfoliocomparable> portfoliocomparables = portfoliocomparableMapper.queryPortfolioComparableList(com);
        BigDecimal multiple = BigDecimal.ZERO;
        BigDecimal high = BigDecimal.ZERO;
        BigDecimal low = new BigDecimal(20000); //用于初始化倍数
        for (Portfoliocomparable c : portfoliocomparables) {
            multiple = multiple.add(c.getMultiple().multiply(c.getWeight()));
            if (c.getMultiple().compareTo(high) > 0) {
                high = c.getMultiple();
            }
            if (c.getMultiple().compareTo(low) < 0) {
                low = c.getMultiple();
            }
        }
        BigDecimal proper = investAdminService.queryProperByFundId(portfoliovaluation.getFundid(), portfoliovaluation.getPortfolioid(), portfoliovaluation.getValuationdate());
        BigDecimal base = queryBaseIndiValue(portfoliovaluation);
        BigDecimal highValuation = high.multiply(base).multiply(proper).setScale(0, BigDecimal.ROUND_HALF_UP);
        BigDecimal lowValuation = low.multiply(base).multiply(proper).setScale(0, BigDecimal.ROUND_HALF_UP);
        valuationReport.setHighValuation(highValuation);
        valuationReport.setLowValuation(lowValuation);
        valuationReport.setMultiple(multiple);
        valuationReport.setPortfoliocomparables(portfoliocomparables);
        valuationReport.setOwnerShip(proper);
    }


    private void setFinancialInfo(ValuationReport report, Portfoliovaluation portfoliovaluation) {
        Portfoliofinancialinfo search = new Portfoliofinancialinfo();
        Date date = portfoliovaluation.getValuationdate();
        search.setEnddate(date);
        search.setPortfolioid(portfoliovaluation.getPortfolioid());
        Portfoliofinancialinfo financialData = projFinService.queryPortfoloFinInfoUsd(search);
        report.setPortfoliofinancialinfo(financialData);

        //获得上一年财务数据
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -1);
        Date last = c.getTime();
        search.setEnddate(last);
        Portfoliofinancialinfo lastfinancialData = projFinService.queryPortfoloFinInfoUsd(search);
        report.setLastfinancialinfo(lastfinancialData);
    }


    private String mergeTemplateContent(BigDecimal variation, BigDecimal opmValuation, BigDecimal carryValue) {
        String method = null;
        String lowOrAbove = null;
        if (variation.abs().compareTo(new BigDecimal(0.1)) <= 0) {
            method = MAINTAIN.name();
        } else if (variation.abs().compareTo(new BigDecimal(0.1)) > 0 && variation.abs().compareTo(new BigDecimal(0.2)) < 0) {
            if (variation.compareTo(BigDecimal.ZERO) > 0) {
                lowOrAbove = ABOVE;
            } else {
                lowOrAbove = BELOW;
            }
            method = ADJUST.name();
        } else if (variation.compareTo(new BigDecimal(0.2)) > 0) {
            method = WRITEUP.name();
        } else if (variation.compareTo(new BigDecimal(-0.2)) < 0) {
            method = WRITEDOWN.name();
        }
        Map<String, Object> params = new HashMap<>();
        String percent = variation.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "%";
        params.put("opmValue", opmValuation);
        params.put("carryValue", carryValue);
        params.put("percent", percent);
        params.put("method", method);
        params.put("lowOrAbove", lowOrAbove);
        String content = templateEngine.process("content/opm", thymeCtx.getContext(params));
        content = content.replaceAll("<span>", "");
        content = content.replaceAll("</span>", "");
        content = content.replaceAll("\n", "");
        return content;
    }

    private boolean isComparableMethod(Portfoliovaluation portfoliovaluation) {
        String method = portfoliovaluation.getValuationmethod();
        if (method.equals(PS.name()) || method.equals(PE.name()) || method.equals(PB.name())) {
            return true;
        }
        return false;
    }


    public List<Portfoliocomparable> queryPortfolioComparableList(Portfoliocomparable search) {
        return portfoliocomparableMapper.queryPortfolioComparableList(search);
    }

    public PageInfo<Portfoliovaluation> queryPortfolioValuationList(Portfoliovaluation search) {

        setQuery(search);
        PageHelper.startPage(search.getPageIndex(), search.getPageCount());
        List<Portfoliovaluation> list = portfoliovaluationMapper.queryPortfolioValuationList(search);
        //4，取得分页结果
        PageInfo<Portfoliovaluation> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    private void setQuery(Portfoliovaluation search) {
        if (search.getFundIds() != null) {
            Map<String, List> query = new HashMap<>();
            List<String> list = Arrays.asList(search.getFundIds().split(","));
            query.put("fundIds", list);
            search.setCheckQuery(query);
        }
    }


    public Integer addPortfolioValuation(Portfoliovaluation record) {
        record.setModifieddate(new Date());
        record.setCreateddate(new Date());
        setValuationType(record);
        return portfoliovaluationMapper.insert(record);
    }

    public Portfoliovaluation readValuation(Portfoliovaluation record) {

        Portfoliovaluation portfoliovaluation = portfoliovaluationMapper.selectByPrimaryKey(record.getValuationid());
        if (portfoliovaluation != null) {
            switch (ValuationMethod.valueOf(portfoliovaluation.getValuationmethod())) {
                case PB:
                case PE:
                case PS:
                case EV:
                    Portfoliocomparable search = new Portfoliocomparable();
                    search.setPid(portfoliovaluation.getPortfolioid());
                    search.setValueDate(portfoliovaluation.getValuationdate());
                    List<Portfoliocomparable> portfoliocomparables = portfoliocomparableMapper.queryPortfolioComparableList(search);
                    portfoliovaluation.setPortfoliocomparables(portfoliocomparables);
                default:
                    break;
            }

        }
        return portfoliovaluation;
    }


    private void setValuationType(Portfoliovaluation record) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(record.getValuationdate());
        if (date.indexOf("01-31") > 0 || date.indexOf("07-31") > 0) {
            record.setValuationtype(WB.name());
        } else {
            record.setValuationtype(QUARTERLY.name());
        }
    }

    public Integer updatePortfolioValuation(Portfoliovaluation record) {
        record.setModifieddate(new Date());
        return portfoliovaluationMapper.updateBySelective(record);
    }

    public Integer delPortfolioValuation(Portfoliovaluation record) {
        return portfoliovaluationMapper.deleteByPrimaryKey(record.getValuationid());
    }


}
