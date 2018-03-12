package com.idgcapital.ufund.admin.proj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idgcapital.ufund.model.Exchangeratedetail;
import com.idgcapital.ufund.model.Portfoliofinancialinfo;
import com.idgcapital.ufund.mybatis.dao.ExchangeratedetailMapper;
import com.idgcapital.ufund.mybatis.dao.PortfoliofinancialinfoMapper;
import com.idgcapital.ufund.security.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by yi_zhang on 2018/2/5.
 */
@Service
public class ProjFinService {


    @Autowired
    private PortfoliofinancialinfoMapper portfoliofinancialinfoMapper;

    @Autowired
    private ExchangeratedetailMapper exchangeratedetailMapper;

    private final Integer monthOfYear = 12;


    public int addProjFinInfo(Portfoliofinancialinfo f, LoginUser loginUser) {
        f.setCreateddate(new Date());
        f.setCreatedby(loginUser.getUserId());
        f.setApproved(false);
        return portfoliofinancialinfoMapper.insert(f);
    }

    public int updateProjFinInfo(Portfoliofinancialinfo f, LoginUser loginUser) {
        f.setApproved(false);
        f.setModifieddate(new Date());
        f.setModifiedby(loginUser.getUserId());
        return portfoliofinancialinfoMapper.updateByPrimaryKey(f);
    }

    public int delProjFinInfo(Portfoliofinancialinfo f, LoginUser loginUser) {
        return portfoliofinancialinfoMapper.deleteByPrimaryKey(f.getPortfoliofinanciallot());
    }

    public Portfoliofinancialinfo queryPortfolioFinInfo(Integer id) {

        return portfoliofinancialinfoMapper.selectByPrimaryKey(id);
    }

    public Portfoliofinancialinfo queryPortfoloFinInfoUsd(Portfoliofinancialinfo s) {
        Portfoliofinancialinfo info = portfoliofinancialinfoMapper.queryFinancialData(s);

        Date startDate = info.getStartdate();
        Date endDate = info.getEnddate();
        Map<String, Object> param = new HashMap<>();
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("source", info.getCurrency());
        List<Exchangeratedetail> rates = exchangeratedetailMapper.queryExchangeRateList(param);

        Calendar sc = Calendar.getInstance();
        sc.setTime(startDate);
        Calendar nc = Calendar.getInstance();
        nc.setTime(endDate);
        int timePeriod = nc.get(Calendar.MONTH) + 1 - (sc.get(Calendar.MONTH) + 1) + 1;

        setFinInfoToUsd(info, rates, timePeriod, true);
        return info;
    }


    public List<Portfoliofinancialinfo> queryPortfolioFinInfoListByUsd(Portfoliofinancialinfo search, LoginUser loginUser) {

        List<Portfoliofinancialinfo> infos = portfoliofinancialinfoMapper.queryFinancialDataList(search);
        for (Portfoliofinancialinfo f : infos) {
            Map<String, Object> param = new HashMap<>();
            param.put("startDate", f.getStartdate());
            param.put("endDate", f.getEnddate());
            param.put("source", f.getCurrency());
            List<Exchangeratedetail> rates = exchangeratedetailMapper.queryExchangeRateList(param);
            Calendar sc = Calendar.getInstance();
            sc.setTime(f.getStartdate());
            Calendar nc = Calendar.getInstance();
            nc.setTime(f.getEnddate());
            int timePeriod = nc.get(Calendar.MONTH) + 1 - (sc.get(Calendar.MONTH) + 1) + 1;
            setFinInfoToUsd(f, rates, timePeriod, false);
        }

        return infos;
    }


    public List<String> getAllCurrencies() {
        return exchangeratedetailMapper.getAllCurrency();
    }

    private void setFinInfoToUsd(Portfoliofinancialinfo f, List<Exchangeratedetail> rates, int timePeriod, Boolean needToYear) {

        BigDecimal rate = null;
        if (f.getCurrency().equals("USD")) {
            rate = BigDecimal.ONE;
        } else {
            Exchangeratedetail latest = rates.get(rates.size() - 1);
            rate = latest.getRate();
        }
        if (timePeriod == 0) {
            timePeriod = 12;
        }

        BigDecimal toYearNumber = BigDecimal.ONE;
        if (needToYear)
            toYearNumber = new BigDecimal(monthOfYear).divide(new BigDecimal(timePeriod), 5, BigDecimal.ROUND_HALF_UP);
        //当前日汇率
        f = setByEndOfDateRate(f, rate, toYearNumber);

        BigDecimal average = null;
        if (f.getCurrency().equals("USD")) {
            average = BigDecimal.ONE;
        } else {
            int size = rates.size();
            BigDecimal total = BigDecimal.ZERO;
            for (Exchangeratedetail exchangeratedetail : rates) {
                total = total.add(exchangeratedetail.getRate());
            }
            average = total.divide(new BigDecimal(size), 5, BigDecimal.ROUND_HALF_UP);
        }
        //平均汇率
        setByAverageRate(f, toYearNumber, average);
    }


    private Portfoliofinancialinfo setByEndOfDateRate(Portfoliofinancialinfo f, BigDecimal rate, BigDecimal toYearNumber) {
        BigDecimal cashAssets = f.getCashassets();
        BigDecimal totalassets = f.getTotalassets();
        BigDecimal totalLiabilities = f.getTotalliabilities();
        BigDecimal cashLevel = f.getCashlevel();
        BigDecimal otherAssets = f.getOtherassets();
        if (cashAssets != null) {
            f.setCashassets(cashAssets.multiply(rate).multiply(toYearNumber));
        }
        if (totalassets != null) {
            f.setTotalassets(totalassets.multiply(rate).multiply(toYearNumber));
        }
        if (totalLiabilities != null) {
            f.setTotalliabilities(totalLiabilities.multiply(rate).multiply(toYearNumber));
        }
        if (cashLevel != null) {
            f.setCashlevel(cashLevel.multiply(rate).multiply(toYearNumber));
        }
        if (otherAssets != null) {
            f.setOtherassets(otherAssets.multiply(rate).multiply(toYearNumber));
        }
        return f;
    }


    private void setByAverageRate(Portfoliofinancialinfo f, BigDecimal cal, BigDecimal average) {

        BigDecimal revenue = f.getRevenue();
        BigDecimal grossProfit = f.getGrossprofit();
        BigDecimal operationProfits = f.getOperatingprofits();
        BigDecimal operationIncome = f.getOperatingincome();
        BigDecimal netProfit = f.getNetprofits();
        if (revenue != null) {
            f.setRevenue(revenue.multiply(average));
        }
        if (grossProfit != null) {
            f.setGrossprofit(grossProfit.multiply(average).multiply(cal));
        }
        if (operationProfits != null) {
            f.setOperatingprofits(operationProfits.multiply(average).multiply(cal));
        }
        if (operationIncome != null) {
            f.setOperatingincome(operationIncome.multiply(average).multiply(cal));
        }
        if (netProfit != null) {
            f.setNetprofits(netProfit.multiply(average).multiply(cal));
        }
    }


    public PageInfo<Portfoliofinancialinfo> queryPortfoliofiancialInfo(Portfoliofinancialinfo s) {

        PageHelper.startPage(s.getPageIndex(), s.getPageCount());

        List<Portfoliofinancialinfo> list = portfoliofinancialinfoMapper.queryFinancialDataList(s);
        PageInfo<Portfoliofinancialinfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


}
