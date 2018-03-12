package com.idgcapital.ufund.admin.proj.service;

import com.idgcapital.ufund.common.util.DateUtil;
import com.idgcapital.ufund.model.DdlBaseInfo;
import com.idgcapital.ufund.model.Portfolioexit;
import com.idgcapital.ufund.model.ProjReportInput;
import com.idgcapital.ufund.model.ProjReportOutput;
import com.idgcapital.ufund.mybatis.dao.ProjManageReportMapper;
import javafx.scene.layout.BorderImage;
import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.font.BidiUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by li_zhao on 2018/2/1.
 */
@Service
public class ProjManageReportService {
    @Autowired
    private ProjManageReportMapper projManageReportMapper;
    @Autowired
    private InvestAdminService investAdminService;

    //input:fundId,date
    public List<Map<String, Object>> queryGainLossReport(ProjReportInput reportInput) {
        List<Map<String, Object>> resultMapList = new ArrayList<>();

        if(reportInput == null || reportInput.getClosedate() == null)
            return null;

        int year = DateUtil.getYear(reportInput.getClosedate());
        int quarter = DateUtil.getQuarter(reportInput.getClosedate());
        Date quarterTime = new Date();
        String quarterStr = "";
        if (quarter == 1) {
            quarterStr = year + "-" + "03" + "-" + "31";
            quarterTime = DateUtil.stringtoDate(year + "-" + "03" + "-" + "31", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 2) {
            quarterStr = year + "-" + "06" + "-" + "30";
            quarterTime = DateUtil.stringtoDate(year + "-" + "06" + "-" + "30", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 3) {
            quarterStr = year + "-" + "09" + "-" + "30";
            quarterTime = DateUtil.stringtoDate(year + "-" + "09" + "-" + "30", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 4) {
            quarterStr = year + "-" + "12" + "-" + "31";
            quarterTime = DateUtil.stringtoDate(year + "-" + "12" + "-" + "31", DateUtil.LONG_DATE_FORMAT);
        }

        String fundFullName = projManageReportMapper.queryFundFullNameByFundId(reportInput.getFundid());
        Map<String, Object> titleMap = new HashMap<>();
        titleMap.put("quarter", quarterStr);
        titleMap.put("fundFullName", fundFullName);
        resultMapList.add(titleMap);


        reportInput.setClosedate(quarterTime);
        BigDecimal defaultVal = new BigDecimal("0");

        List<String> gainLossPortfolioIdByFund = projManageReportMapper.queryGainLossPortfolioIdByFund(reportInput);
        if (gainLossPortfolioIdByFund != null && gainLossPortfolioIdByFund.size() > 0) {
            for (String portfolioId : gainLossPortfolioIdByFund) {
                reportInput.setPortfolioid(portfolioId);
                List<Map<String, Object>> gainLossDetailList = projManageReportMapper.queryGainLossReport(reportInput);
                if (gainLossDetailList != null && gainLossDetailList.size() > 0) {
                    BigDecimal multiple = new BigDecimal("1");
                    BigDecimal proceedsTotal = new BigDecimal("0");
                    BigDecimal costRelizationTotal = new BigDecimal("0");
                    BigDecimal gainLossTotal = new BigDecimal("0");
                    String Company = "";

                    for (Map<String, Object> detail : gainLossDetailList) {
                        if(detail.get("maxPurchaseDate") == null){
                            continue; // TODO: 2018/2/4
                        }
                        
                        String maxPurchaseDate = DateUtil.dateToString(DateUtil.stringtoDate(detail.get("maxPurchaseDate").toString(), DateUtil.LONG_DATE_FORMAT), DateUtil.REPORT_DATE_DIR_FORMAT);
                        String minPurchaseDate = DateUtil.dateToString(DateUtil.stringtoDate(detail.get("minPurchaseDate").toString(), DateUtil.LONG_DATE_FORMAT), DateUtil.REPORT_DATE_DIR_FORMAT);
                        String soldDate = DateUtil.dateToString(DateUtil.stringtoDate(detail.get("closeDate").toString(), DateUtil.LONG_DATE_FORMAT), DateUtil.REPORT_DATE_DIR_FORMAT);

                        BigDecimal proceeds = new BigDecimal(detail.get("proceeds").toString());
                        BigDecimal costRelization = new BigDecimal(detail.get("costRelization").toString());
                        BigDecimal gainLoss = new BigDecimal(detail.get("proceedsRelization").toString());

                        Map<String, Object> resultMap = new HashMap<>();

                        resultMap.put("Company", detail.get("abbName").toString());
                        resultMap.put("Shares", Integer.parseInt(detail.get("shareOwnedNo").toString()));
                        if (!maxPurchaseDate.equals(minPurchaseDate)) {
                            resultMap.put("PurchasedDate", maxPurchaseDate + "-" + minPurchaseDate);
                        } else {
                            resultMap.put("PurchasedDate", maxPurchaseDate);
                        }
                        resultMap.put("SoldDate", soldDate);
                        resultMap.put("ProceedsSales", proceeds);
                        resultMap.put("BasisCost", costRelization);

                        if (gainLoss.compareTo(defaultVal) == -1) {
                            resultMap.put("RealizedGainLoss", "(" + gainLoss.abs() + ")");
                        } else {
                            resultMap.put("RealizedGainLoss", gainLoss);
                        }
                        resultMap.put("exitMode", detail.get("exitMode").toString());
                        resultMapList.add(resultMap);

                        proceedsTotal = proceedsTotal.add(proceeds);
                        costRelizationTotal = costRelizationTotal.add(costRelization);
                        gainLossTotal = gainLossTotal.add(gainLoss);
                    }
                    multiple = proceedsTotal.divide(costRelizationTotal, 1, BigDecimal.ROUND_HALF_UP);

                    Map<String, Object> resultMapTotal = new HashMap<>();
                    resultMapTotal.put("Company", "");
                    resultMapTotal.put("Shares", "");
                    resultMapTotal.put("PurchasedDate", "multiple:");
                    resultMapTotal.put("SoldDate", multiple + "X");
                    resultMapTotal.put("ProceedsSales", proceedsTotal);
                    resultMapTotal.put("BasisCost", costRelizationTotal);
                    resultMapTotal.put("RealizedGain/(Loss)", gainLossTotal);

                    resultMapTotal.put("exitMode", "");
                    resultMapList.add(resultMapTotal);

                }
            }
        }
        return resultMapList;
    }

    public List<Map<String, Object>> queryGainLossSumReport(ProjReportInput reportInput) {
        List<Map<String, Object>> resultMapList = new ArrayList<>();

        BigDecimal defaultVal = new BigDecimal("0");

//        List<Integer> gainLossYear = projManageReportMapper.queryGainLossYearByFund(reportInput);
        BigDecimal proceedsTotal = new BigDecimal("0");
        BigDecimal costRelizationTotal = new BigDecimal("0");
        BigDecimal gainLossTotal = new BigDecimal("0");

        if(reportInput == null || reportInput.getClosedate() == null)
            return null;

        List<Map<String, Object>> gainLossDetailList = projManageReportMapper.queryGainLossReportSum(reportInput);
        if (gainLossDetailList != null && gainLossDetailList.size() > 0) {
            for (Map<String, Object> detail : gainLossDetailList) {

                BigDecimal proceeds = new BigDecimal(detail.get("proceeds").toString());
                BigDecimal costRelization = new BigDecimal(detail.get("costRelization").toString());
                BigDecimal gainLoss = new BigDecimal(detail.get("proceedsRelization").toString());

                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("SummaryByYear", detail.get("year").toString());
                resultMap.put("PriceSales", proceeds);
                resultMap.put("BasisCost", costRelization);

                if (gainLoss.compareTo(defaultVal) == -1) {
                    resultMap.put("RealizedGainLoss", "(" + gainLoss.abs() + ")");
                } else {
                    resultMap.put("RealizedGainLoss", gainLoss);
                }
                resultMapList.add(resultMap);

                proceedsTotal = proceedsTotal.add(proceeds);
                costRelizationTotal = costRelizationTotal.add(costRelization);
                gainLossTotal = gainLossTotal.add(gainLoss);
            }
        }

        Map<String, Object> resultMapTotal = new HashMap<>();
        resultMapTotal.put("ProceedsSales", proceedsTotal);
        resultMapTotal.put("BasisCost", costRelizationTotal);
        resultMapTotal.put("RealizedGainLoss", gainLossTotal);

        resultMapList.add(resultMapTotal);
        return resultMapList;
    }

    //input:fundId,closeDate
    public List<Map<String, Object>> queryCapitalFundReport(ProjReportInput reportInput) {
//        Date date = DateUtil.stringtoDate("2018-02-02",DateUtil.LONG_DATE_FORMAT);

        BigDecimal defaultVal = new BigDecimal("0");
        if(reportInput == null || reportInput.getClosedate() == null){
            return null;
        }

        List<String> capitalFundPortfolioIdByFund = projManageReportMapper.queryCapitalFundPortfolioIdByFund(reportInput);

        List<Map<String, Object>> privateList = new ArrayList<>();
        List<Map<String, Object>> publicList = new ArrayList<>();
        List<Map<String, Object>> totalList = new ArrayList<>();
        List<Map<String, Object>> resultList = new ArrayList<>();


        int year = DateUtil.getYear(reportInput.getClosedate());
        int quarter = DateUtil.getQuarter(reportInput.getClosedate());
        Date quarterTime = new Date();
        String quarterStr = "";
        if (quarter == 1) {
            quarterStr = year + "-" + "03" + "-" + "31";
            quarterTime = DateUtil.stringtoDate(year + "-" + "03" + "-" + "31", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 2) {
            quarterStr = year + "-" + "06" + "-" + "30";
            quarterTime = DateUtil.stringtoDate(year + "-" + "06" + "-" + "30", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 3) {
            quarterStr = year + "-" + "09" + "-" + "30";
            quarterTime = DateUtil.stringtoDate(year + "-" + "09" + "-" + "30", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 4) {
            quarterStr = year + "-" + "12" + "-" + "31";
            quarterTime = DateUtil.stringtoDate(year + "-" + "12" + "-" + "31", DateUtil.LONG_DATE_FORMAT);
        }

        String fundFullName = projManageReportMapper.queryFundFullNameByFundId(reportInput.getFundid());
        Map<String, Object> titleMap = new HashMap<>();
        titleMap.put("quarter", quarterStr);
        titleMap.put("fundFullName", fundFullName);
        resultList.add(titleMap);


        if (capitalFundPortfolioIdByFund != null && capitalFundPortfolioIdByFund.size() > 0) {
            BigDecimal costPrivate = new BigDecimal("0");
            BigDecimal marketValPrivate = new BigDecimal("0");
            BigDecimal capitalInvestedPrivate = new BigDecimal("0");
            BigDecimal reservePrivate = new BigDecimal("0");
            BigDecimal totalFundingPrivate = new BigDecimal("0");
            BigDecimal salesProceedsPrivate = new BigDecimal("0");
            BigDecimal dividendPrivate = new BigDecimal("0");
            BigDecimal totalGainLossPrivate = new BigDecimal("0");

            BigDecimal costPublic = new BigDecimal("0");
            BigDecimal marketValPublic = new BigDecimal("0");
            BigDecimal capitalInvestedPublic = new BigDecimal("0");
            BigDecimal reservePublic = new BigDecimal("0");
            BigDecimal totalFundingPublic = new BigDecimal("0");
            BigDecimal salesProceedsPublic = new BigDecimal("0");
            BigDecimal dividendPublic = new BigDecimal("0");
            BigDecimal totalGainLossPublic = new BigDecimal("0");


            for (String portfolioId : capitalFundPortfolioIdByFund) {

                reportInput.setPortfolioid(portfolioId);
                Map<String, Object> portfolio = projManageReportMapper.queryCapitalFundPortfolioById(reportInput);
                //project name
                String projName = portfolio.get("abbName").toString();

                //BizDesc
                String bizDesc = portfolio.get("oneLineDesc") == null ? "" : portfolio.get("oneLineDesc").toString();

                //private/public   portfolioStatus
                String privateOrpublic = portfolio.get("portfolioStatus") == null ? "" : portfolio.get("portfolioStatus").toString();

                //获取leader
                List<String> leaderList = projManageReportMapper.queryPortfolioLeaderList(portfolioId);
                String leaderName = "";
                if (leaderList != null && leaderList.size() > 0) {
                    for (String leader : leaderList) {
                        leaderName += leader + "/";
                    }
                    leaderName = leaderName.substring(0, leaderName.lastIndexOf("/"));
                }

                //board seat
                String boardSeat = "";
                if (portfolio.get("IDGOnBoard") != null) {
                    if (portfolio.get("IDGOnBoard").toString().equals("true")) {
                        boardSeat = "Y";
                    } else {
                        boardSeat = "N";
                    }
                } else {
                    boardSeat = "N";
                }

                //sector
                String sector = "";
                if (portfolio.get("sector") != null) {
                    if (portfolio.get("subSector") != null) {
                        sector = portfolio.get("sector").toString() + " " + "-" + " " + portfolio.get("subSector").toString();
                    }
                }

                //first funding
                String firstFunding = DateUtil.dateToString(DateUtil.stringtoDate(portfolio.get("firstFunding").toString(), DateUtil.LONG_DATE_FORMAT), DateUtil.LONG_DATE_FORMAT);

                //ownerShip
                String ownerShip = "-";
                if (portfolio.get("ownership") != null) {
                    ownerShip = new BigDecimal(portfolio.get("ownership").toString()).multiply(new BigDecimal("100")) + "%";
                }

                //cost @2017-09-30
                reportInput.setQuarterdate(quarterTime);
                BigDecimal costByQuarter = projManageReportMapper.queryPortfolioCostByQuarter(reportInput);
                String costByQuarterStr = bigDecimalFilter(costByQuarter);

                //market value @2017-09-30
                BigDecimal marketVal = projManageReportMapper.queryPortfolioMarketValueByQuarter(reportInput);
                if (marketVal == null) {
                    marketVal = new BigDecimal("0");
                }

                String marketValStr = bigDecimalFilter(marketVal);

                //capitalInvested
                BigDecimal capitalInvested = new BigDecimal(portfolio.get("capitalInvested") == null ? "0" : portfolio.get("capitalInvested").toString());
                String capitalInvestedStr = bigDecimalFilter(portfolio.get("capitalInvested"));
                //reserves
                BigDecimal reserve = new BigDecimal("0");
                String reserveStr = "-";
                //totalFunding
                BigDecimal totalFunding = new BigDecimal(portfolio.get("totalFunding") == null ? "0" : portfolio.get("totalFunding").toString());
                String totalFundingStr = bigDecimalFilter(portfolio.get("totalFunding"));
                //sales proceeds
                BigDecimal salesProceeds = new BigDecimal(portfolio.get("salesProceeds") == null ? "0" : portfolio.get("salesProceeds").toString());
                String salesProceedsStr = bigDecimalFilter(portfolio.get("salesProceeds"));
                //Dividends
                BigDecimal dividends = new BigDecimal("0");
                String dividendsStr = "-";// TODO: 2018/2/2
                //TotalGain/(Loss)
                BigDecimal proceedsRelization = new BigDecimal(portfolio.get("proceedsRelization") == null ? "0" : portfolio.get("proceedsRelization").toString());
                String proceedsRelizationStr = "";
                if (proceedsRelization.compareTo(defaultVal) == -1) {
                    proceedsRelizationStr = "(" + proceedsRelization.abs() + ")";
                } else {
                    proceedsRelizationStr = proceedsRelization + "";
                }

                //Financing
                String round = portfolio.get("round") == null ? "" : portfolio.get("round").toString();
                String security = portfolio.get("securityTypeID") == null ? "" : investAdminService.securityTypeFilter(Integer.parseInt(portfolio.get("securityTypeID").toString()));
                String financing = security + " " + round;
                //premoney postmoney
                String preMoney = "";
                String postMoney = "";
                if (portfolio.get("preMoney") != null) {
                    preMoney = portfolio.get("preMoney").toString();
                } else {
                    preMoney = "N/A";
                }

                if (portfolio.get("postMoney") != null) {
                    postMoney = portfolio.get("postMoney").toString();
                } else {
                    postMoney = "N/A";
                }
                //other investor
                String otherInvestor = "";
                List<String> otherInvestorList = projManageReportMapper.queryPortfolioOtherInvestorList(reportInput);
                if (otherInvestorList != null && otherInvestorList.size() > 0) {
                    for (String shareHolder : otherInvestorList) {
                        otherInvestor += shareHolder + ",";
                    }
                    otherInvestor = otherInvestor.substring(0, otherInvestor.lastIndexOf(","));
                }

                Map<String, Object> result = new HashMap<>();
                result.put("projName", projName);
                result.put("BusinessDescription", bizDesc);
                result.put("PrivatePublic", privateOrpublic);
                result.put("Lead", leaderName);
                result.put("BoardSeat", boardSeat);
                result.put("Sector", sector);
                result.put("FirstFunding", firstFunding);
                result.put("Ownership", ownerShip);
                result.put("cost", costByQuarterStr);
                result.put("MarketValue", marketValStr);
                result.put("CapitalInvestedToDate", capitalInvestedStr);
                result.put("Reserves", reserveStr);
                result.put("TotalFunding", totalFundingStr);
                result.put("SalesProceeds", salesProceedsStr);
                result.put("Dividends", dividendsStr);
                result.put("TotalGain", proceedsRelizationStr);
                result.put("Financing", financing);
                result.put("preMoney", preMoney);
                result.put("postMoney", postMoney);
                result.put("otherInvestor", otherInvestor);

                if (privateOrpublic.equals("Private")) {
                    costPrivate = costPrivate.add(costByQuarter);
                    marketValPrivate = marketValPrivate.add(marketVal);
                    capitalInvestedPrivate = capitalInvestedPrivate.add(capitalInvested);
                    reservePrivate = reservePrivate.add(reserve);
                    totalFundingPrivate = totalFundingPrivate.add(totalFunding);
                    salesProceedsPrivate = salesProceedsPrivate.add(salesProceeds);
                    dividendPrivate = dividendPrivate.add(dividends);
                    totalGainLossPrivate = totalGainLossPrivate.add(proceedsRelization);

                    privateList.add(result);
                } else if (privateOrpublic.equals("Public")) {
                    costPublic = costPublic.add(costByQuarter);
                    marketValPublic = marketValPublic.add(marketVal);
                    capitalInvestedPublic = capitalInvestedPublic.add(capitalInvested);
                    reservePublic = reservePublic.add(reserve);
                    totalFundingPublic = totalFundingPublic.add(totalFunding);
                    salesProceedsPublic = salesProceedsPublic.add(salesProceeds);
                    dividendPublic = dividendPublic.add(dividends);
                    totalGainLossPublic = totalGainLossPublic.add(proceedsRelization);

                    publicList.add(result);
                }
            }

            String totalGainLossPrivateStr = "";
            if (totalGainLossPrivate.compareTo(defaultVal) == -1) {
                totalGainLossPrivateStr = "(" + totalGainLossPrivate.abs() + ")";
            } else {
                totalGainLossPrivateStr = totalGainLossPrivate + "";
            }

            Map<String, Object> privateTotalMap = new HashMap<>();
            privateTotalMap.put("projName", "");
            privateTotalMap.put("BusinessDescription", "");
            privateTotalMap.put("PrivatePublic", "");
            privateTotalMap.put("Lead", "");
            privateTotalMap.put("BoardSeat", "");
            privateTotalMap.put("Sector", "");
            privateTotalMap.put("FirstFunding", "");
            privateTotalMap.put("Ownership", "");
            privateTotalMap.put("cost", costPrivate);
            privateTotalMap.put("MarketValue", marketValPrivate);
            privateTotalMap.put("CapitalInvestedToDate", capitalInvestedPrivate);
            privateTotalMap.put("Reserves", reservePrivate);
            privateTotalMap.put("TotalFunding", totalFundingPrivate);
            privateTotalMap.put("SalesProceeds", salesProceedsPrivate);
            privateTotalMap.put("Dividends", dividendPrivate);
            privateTotalMap.put("TotalGain", totalGainLossPrivateStr);
            privateTotalMap.put("Financing", "");
            privateTotalMap.put("preMoney", "");
            privateTotalMap.put("postMoney", "");
            privateTotalMap.put("otherInvestor", "");
            privateList.add(privateTotalMap);

            Map<String, Object> privateMap = new HashMap<>();
            privateMap.put("baseId", "Private");
            privateMap.put("baseName", privateList);


            String totalGainLossPublicStr = "";
            if (totalGainLossPublic.compareTo(defaultVal) == -1) {
                totalGainLossPublicStr = "(" + totalGainLossPublic + ")";
            } else {
                totalGainLossPublicStr = totalGainLossPublic + "";
            }


            Map<String, Object> publicTotalMap = new HashMap<>();
            publicTotalMap.put("projName", "");
            publicTotalMap.put("BusinessDescription", "");
            publicTotalMap.put("PrivatePublic", "");
            publicTotalMap.put("Lead", "");
            publicTotalMap.put("BoardSeat", "");
            publicTotalMap.put("Sector", "");
            publicTotalMap.put("FirstFunding", "");
            publicTotalMap.put("Ownership", "");
            publicTotalMap.put("cost", costPublic);
            publicTotalMap.put("MarketValue", marketValPublic);
            publicTotalMap.put("CapitalInvestedToDate", capitalInvestedPublic);
            publicTotalMap.put("Reserves", reservePublic);
            publicTotalMap.put("TotalFunding", totalFundingPublic);
            publicTotalMap.put("SalesProceeds", salesProceedsPublic);
            publicTotalMap.put("Dividends", dividendPublic);
            publicTotalMap.put("TotalGain", totalGainLossPublicStr);
            publicTotalMap.put("Financing", "");
            publicTotalMap.put("preMoney", "");
            publicTotalMap.put("postMoney", "");
            publicTotalMap.put("otherInvestor", "");
            publicList.add(publicTotalMap);

            Map<String, Object> publicMap = new HashMap<>();
            publicMap.put("baseId", "Public");
            publicMap.put("baseName", publicList);


            BigDecimal totalGainLoss = totalGainLossPrivate.add(totalGainLossPublic);
            String totalGainLossStr = "";
            if (totalGainLoss.compareTo(defaultVal) == -1) {
                totalGainLossStr = "(" + totalGainLoss.abs() + ")";
            } else {
                totalGainLossStr = totalGainLoss + "";
            }

            Map<String, Object> totalMap = new HashMap<>();
            totalMap.put("projName", "");
            totalMap.put("BusinessDescription", "");
            totalMap.put("PrivatePublic", "");
            totalMap.put("Lead", "");
            totalMap.put("BoardSeat", "");
            totalMap.put("Sector", "");
            totalMap.put("FirstFunding", "");
            totalMap.put("Ownership", "");
            totalMap.put("cost", costPrivate.add(costPublic));
            totalMap.put("MarketValue", marketValPrivate.add(marketValPublic));
            totalMap.put("CapitalInvestedToDate", capitalInvestedPrivate.add(capitalInvestedPublic));
            totalMap.put("Reserves", reservePrivate.add(reservePublic));
            totalMap.put("TotalFunding", totalFundingPrivate.add(totalFundingPublic));
            totalMap.put("SalesProceeds", salesProceedsPrivate.add(salesProceedsPublic));
            totalMap.put("Dividends", dividendPrivate.add(dividendPublic));
            totalMap.put("TotalGain", totalGainLossStr);
            totalMap.put("Financing", "");
            totalMap.put("preMoney", "");
            totalMap.put("postMoney", "");
            totalMap.put("otherInvestor", "");
            totalList.add(totalMap);

            Map<String, Object> totalReturnMap = new HashMap<>();
            totalReturnMap.put("baseId", "total");
            totalReturnMap.put("baseName", totalList);

            resultList.add(privateMap);
            resultList.add(publicMap);
            resultList.add(totalReturnMap);

        }


        return resultList;
    }

    public String bigDecimalFilter(Object input) {
        if (input != null && !input.equals("")) {
            BigDecimal inputD = new BigDecimal(input.toString());
            return inputD + "";
        } else {
            return "-";
        }
    }

    public List<Map<String, Object>> queryMainScheduleReport(ProjReportInput reportInput) {
        BigDecimal defaultVal = new BigDecimal("0");

        Date date = DateUtil.stringtoDate("2017-12-31", DateUtil.LONG_DATE_FORMAT);
        List<Map<String, Object>> resultMapList = new ArrayList<>();

        List<Map<String, Object>> privateList = new ArrayList<>();
        List<Map<String, Object>> publicList = new ArrayList<>();
        List<Map<String, Object>> totalList = new ArrayList<>();

        if(reportInput.getClosedate() == null){
            return null;
        }

        int year = DateUtil.getYear(reportInput.getClosedate());
        int quarter = DateUtil.getQuarter(reportInput.getClosedate());
        Date quarterTime = new Date();
        String quarterStr = "";
        if (quarter == 1) {
            quarterStr = year + "-" + "03" + "-" + "31";
            quarterTime = DateUtil.stringtoDate(year + "-" + "03" + "-" + "31", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 2) {
            quarterStr = year + "-" + "06" + "-" + "30";
            quarterTime = DateUtil.stringtoDate(year + "-" + "06" + "-" + "30", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 3) {
            quarterStr = year + "-" + "09" + "-" + "30";
            quarterTime = DateUtil.stringtoDate(year + "-" + "09" + "-" + "30", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 4) {
            quarterStr = year + "-" + "12" + "-" + "31";
            quarterTime = DateUtil.stringtoDate(year + "-" + "12" + "-" + "31", DateUtil.LONG_DATE_FORMAT);
        }

        reportInput.setClosedate(quarterTime);
        reportInput.setQuarterdate(quarterTime);

        String fundFullName = projManageReportMapper.queryFundFullNameByFundId(reportInput.getFundid());
        Map<String, Object> titleMap = new HashMap<>();
        titleMap.put("quarter", quarterStr);
        titleMap.put("fundFullName", fundFullName);
        resultMapList.add(titleMap);

        List<Map<String, Object>> mainSchedulePortfolioIdByFund = projManageReportMapper.queryMainSchedulePortfolioIdByFund(reportInput);
        if (mainSchedulePortfolioIdByFund != null && mainSchedulePortfolioIdByFund.size() > 0) {
            BigDecimal sumValuation = new BigDecimal("0");
            BigDecimal sumTotalCost = new BigDecimal("0");

            for (Map<String, Object> portfolioMap : mainSchedulePortfolioIdByFund) {

                String portfolioId = portfolioMap.get("portfolioID").toString();
                String fullName = portfolioMap.get("fullName") == null ? "" : portfolioMap.get("fullName").toString();

                reportInput.setPortfolioid(portfolioId);

                String privateOrPublic = projManageReportMapper.queryPrivateOrPublicByPortfolioId(reportInput);

                List<Map<String, Object>> noteList = projManageReportMapper.queryNoteMainScheduleByPortfolioId(reportInput);

                List<Map<String, Object>> investmentList = projManageReportMapper.queryInvestMainScheduleByPortfolioId(reportInput);

                BigDecimal proper = investAdminService.queryProperByFundId(reportInput.getFundid(), reportInput.getPortfolioid(), reportInput.getClosedate());
                String properStr = "";
                if (proper != null) {
                    properStr = proper.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP) + "%";
                }

                BigDecimal valuation = new BigDecimal("0");
                long totalShareOwned = 0;
                BigDecimal totalCost = new BigDecimal("0");

                BigDecimal fmvPerShare  = queryFMVPerShare(reportInput);//每股市值

                if (privateOrPublic != null) {
                    if (privateOrPublic.equals("Public")) {
                        for (Map<String, Object> note : noteList) {
                            Map<String, Object> noteResult = new HashMap<>();
                            noteResult.put("Company", fullName);
                            noteResult.put("purchaseDate", DateUtil.stringtoDate(note.get("closeDate").toString(), DateUtil.LONG_DATE_FORMAT));
                            noteResult.put("security", note.get("noteType"));
                            noteResult.put("sharesOwned", "-");
                            noteResult.put("FullyDiluted", "");
                            noteResult.put("Cost", note.get("noteNum").toString());
                            noteResult.put("FairValue", "");
                            noteResult.put("valuation", note.get("noteNum").toString());
                            publicList.add(noteResult);

                            totalCost = totalCost.add(new BigDecimal(note.get("noteNum").toString()));
                            valuation = valuation.add(new BigDecimal(note.get("noteNum").toString()));
                        }

                        for (Map<String, Object> invest : investmentList) {

                            Map<String, Object> investResult = new HashMap<>();

                            long shareOwned = Long.valueOf(invest.get("shareOwnedNo").toString());
                            BigDecimal cost = new BigDecimal(invest.get("cost").toString());

                            investResult.put("Company", fullName);
                            investResult.put("purchaseDate", DateUtil.stringtoDate(invest.get("closeDate").toString(), DateUtil.LONG_DATE_FORMAT));
                            investResult.put("security", invest.get("securityType"));
                            investResult.put("sharesOwned", shareOwned);
                            investResult.put("FullyDiluted", "");
                            investResult.put("Cost", cost);
                            investResult.put("FairValue", "");
                            investResult.put("valuation", fmvPerShare.multiply(new BigDecimal(shareOwned)));
                            publicList.add(investResult);


                            totalShareOwned = totalShareOwned + shareOwned;
                            totalCost = totalCost.add(cost);
                            valuation = valuation.add(fmvPerShare.multiply(new BigDecimal(shareOwned)));
                        }

                        Map<String, Object> totalMap = new HashMap<>();
                        totalMap.put("Company", "");
                        totalMap.put("purchaseDate", "");
                        totalMap.put("security", "");
                        totalMap.put("sharesOwned", totalShareOwned);
                        totalMap.put("FullyDiluted", properStr);
                        totalMap.put("Cost", totalCost);
                        totalMap.put("FairValue", valuation);
                        totalMap.put("valuation", defaultVal);
                        publicList.add(totalMap);

                        sumTotalCost = sumTotalCost.add(totalCost);
                        sumValuation = sumValuation.add(valuation);

                    } else if (privateOrPublic.equals("Private")) {
                        for (Map<String, Object> note : noteList) {
                            Map<String, Object> noteResult = new HashMap<>();

                            noteResult.put("Company", fullName);
                            noteResult.put("purchaseDate", DateUtil.stringtoDate(note.get("closeDate").toString(), DateUtil.LONG_DATE_FORMAT));
                            noteResult.put("security", note.get("noteType"));
                            noteResult.put("sharesOwned", "-");
                            noteResult.put("FullyDiluted", "");
                            noteResult.put("Cost", note.get("noteNum").toString());
                            noteResult.put("FairValue", "");
                            noteResult.put("valuation", note.get("noteNum").toString());
                            privateList.add(noteResult);

                            totalCost = totalCost.add(new BigDecimal(note.get("noteNum").toString()));
                            valuation = valuation.add(new BigDecimal(note.get("noteNum").toString()));
                        }

                        for (Map<String, Object> invest : investmentList) {
                            Map<String, Object> investResult = new HashMap<>();

                            long shareOwned = Long.parseLong(invest.get("shareOwnedNo").toString());
                            BigDecimal cost = new BigDecimal(invest.get("cost").toString());

                            investResult.put("Company", fullName);
                            investResult.put("purchaseDate", DateUtil.stringtoDate(invest.get("closeDate").toString(), DateUtil.LONG_DATE_FORMAT));
                            investResult.put("security", invest.get("securityType"));
                            investResult.put("sharesOwned", shareOwned);
                            investResult.put("FullyDiluted", "");
                            investResult.put("Cost", cost);
                            investResult.put("FairValue", "");
                            investResult.put("valuation", fmvPerShare.multiply(new BigDecimal(shareOwned)));
                            privateList.add(investResult);

                            totalShareOwned = totalShareOwned + shareOwned;
                            totalCost = totalCost.add(cost);
                            valuation = valuation.add(fmvPerShare.multiply(new BigDecimal(shareOwned)));
                        }

                        Map<String, Object> totalMap = new HashMap<>();
                        totalMap.put("Company", "");
                        totalMap.put("purchaseDate", "");
                        totalMap.put("security", "");
                        totalMap.put("sharesOwned", totalShareOwned);
                        totalMap.put("FullyDiluted", properStr);
                        totalMap.put("Cost", totalCost);
                        totalMap.put("FairValue", valuation);
                        totalMap.put("valuation", defaultVal);
                        privateList.add(totalMap);

                        sumTotalCost = sumTotalCost.add(totalCost);
                        sumValuation = sumValuation.add(valuation);
                    }
                }
            }
            Map<String, Object> sumMap = new HashMap<>();
            sumMap.put("Company", "");
            sumMap.put("purchaseDate", "");
            sumMap.put("security", "");
            sumMap.put("sharesOwned", "");
            sumMap.put("FullyDiluted", "");
            sumMap.put("Cost", sumTotalCost);
            sumMap.put("FairValue", sumValuation);
            sumMap.put("valuation", sumValuation);

            Map<String, Object> publicReturnMap = new HashMap<>();
            publicReturnMap.put("baseId", "Public");
            publicReturnMap.put("baseName", publicList);

            Map<String, Object> privateReturnMap = new HashMap<>();
            privateReturnMap.put("baseId", "Private");
            privateReturnMap.put("baseName", privateList);

            Map<String, Object> totalReturnMap = new HashMap<>();
            totalReturnMap.put("baseId", "total");
            totalReturnMap.put("baseName", sumMap);


            //security total
            totalList.addAll(publicList);
            totalList.addAll(privateList);

            Map<String, Object> securityMap = new HashMap<>();
            for (Map<String, Object> map : totalList) {
                if (map.get("security") != null && !map.get("security").toString().equals("")) {
                    ProjReportOutput projReportOutput = new ProjReportOutput();
                    projReportOutput.setShareOwnedNum(0);
                    projReportOutput.setCost(new BigDecimal("0"));
                    projReportOutput.setValuation(new BigDecimal("0"));
                    securityMap.put(map.get("security").toString(), projReportOutput);
                }
            }

            for (Map<String, Object> map : totalList) {
                for (String key : securityMap.keySet()) {
                    if (map.get("security").toString().equals(key)) {
                        ProjReportOutput projReportOutput = (ProjReportOutput) securityMap.get(key);
                        if (!map.get("valuation").toString().equals("")) {
                            BigDecimal  valuation = new BigDecimal(map.get("valuation").toString());
                            projReportOutput.setValuation(projReportOutput.getValuation().add(valuation));
                        }
                        if (!map.get("Cost").toString().equals("")) {
                            BigDecimal cost = new BigDecimal(map.get("Cost").toString());
                            projReportOutput.setCost(projReportOutput.getCost().add(cost));
                        }
                        if(!map.get("sharesOwned").toString().equals("-")){
                            long sharesOwned = Long.parseLong(map.get("sharesOwned").toString()) + projReportOutput.getShareOwnedNum();
                            projReportOutput.setShareOwnedNum(sharesOwned);
                        }
                    }
                }
            }

            List<Map<String,Object>> securityList = new ArrayList<>();
            for (String key : securityMap.keySet()) {
                Map<String,Object> map = new HashMap<>();
                ProjReportOutput projReportOutput = (ProjReportOutput) securityMap.get(key);
                map.put("security",key);
                map.put("sharesOwned",projReportOutput.getShareOwnedNum());
                map.put("Cost",projReportOutput.getCost());
                map.put("FairValue",projReportOutput.getValuation());
                securityList.add(map);
            }

            Map<String, Object> securityReturnMap = new HashMap<>();
            securityReturnMap.put("baseId", "securityTotal");
            securityReturnMap.put("baseName", securityList);

            resultMapList.add(publicReturnMap);
            resultMapList.add(privateReturnMap);
            resultMapList.add(totalReturnMap);

            resultMapList.add(securityReturnMap);

        }


        return resultMapList;
    }

    public List<Map<String, Object>> queryInvestDetailReport(ProjReportInput reportInput) {
        BigDecimal defaultVal = new BigDecimal("0");

        Date date = DateUtil.stringtoDate("2018-02-02", DateUtil.LONG_DATE_FORMAT);

        if(reportInput == null || reportInput.getClosedate() == null)
            return null;

        int year = DateUtil.getYear(reportInput.getClosedate());
        int quarter = DateUtil.getQuarter(reportInput.getClosedate());
        Date quarterTime = new Date();
        String quarterStr = "";
        if (quarter == 1) {
            quarterTime = DateUtil.stringtoDate(year + "-" + "03" + "-" + "31", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 2) {
            quarterTime = DateUtil.stringtoDate(year + "-" + "06" + "-" + "30", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 3) {
            quarterTime = DateUtil.stringtoDate(year + "-" + "09" + "-" + "30", DateUtil.LONG_DATE_FORMAT);
        } else if (quarter == 4) {
            quarterTime = DateUtil.stringtoDate(year + "-" + "12" + "-" + "31", DateUtil.LONG_DATE_FORMAT);
        }
        reportInput.setClosedate(quarterTime);
        reportInput.setQuarterdate(quarterTime);

        List<String> investDetailPortfolioIdByFund = projManageReportMapper.queryInvestDetailPortfolioIdByFund(reportInput);
        List<Map<String, Object>> resultMapList = new ArrayList<>();

        if (investDetailPortfolioIdByFund != null && investDetailPortfolioIdByFund.size() > 0) {
            String sumCompany = "";
            BigDecimal sumCost = new BigDecimal("0");
            BigDecimal sumValuation = new BigDecimal("0");
            BigDecimal sumUnrealizedGL = new BigDecimal("0");
            String sumUnrealizedGLStr = "";
            long sumIdgShare = 0;
            BigDecimal sumOwnership = new BigDecimal("0");

            for (String portfolioId : investDetailPortfolioIdByFund) {
                reportInput.setPortfolioid(portfolioId);
                List<Map<String, Object>> investDetailList = projManageReportMapper.queryInvestDetailById(reportInput);

                String totalCompany = "";
                BigDecimal totalCost = new BigDecimal("0");
                BigDecimal totalValuation = new BigDecimal("0");
                BigDecimal totalUnrealizedGL = new BigDecimal("0");
                String totalUnrealizedGLStr = "";
                long totalIdgShare = 0;
                BigDecimal totalOwnership = new BigDecimal("0");


                for (Map<String, Object> map : investDetailList) {
                    Map<String, Object> result = new HashMap<>();
                    //company
                    String company = map.get("fullName") == null ? "" : map.get("fullName").toString();
                    totalCompany = map.get("fullName") == null ? "" : map.get("fullName").toString() + " " + "Total";
                    //tax lot date
                    Date closeDate = DateUtil.stringtoDate(map.get("closeDate").toString(), DateUtil.LONG_DATE_FORMAT);
                    String closeDateStr = DateUtil.dateToString(closeDate, DateUtil.REPORT_DATE_DIR_FORMAT);
                    //security type
                    String security = map.get("securityTypeID") == null ? "" : investAdminService.securityTypeFilter(Integer.parseInt(map.get("securityTypeID").toString()));
                    int securityTypeID = map.get("securityTypeID") == null ? -1 : Integer.parseInt(map.get("securityTypeID").toString());
                    //round
                    String round = map.get("round") == null ? "" : map.get("round").toString();
                    //total cost
                    BigDecimal cost = new BigDecimal("0");
                    if (map.get("cost") != null) {
                        cost = new BigDecimal(map.get("cost").toString());
                    }

                    //idg share
                    long idgShare = 0;
                    if (map.get("shareOwnedNo") != null) {
                        idgShare = Long.valueOf(map.get("shareOwnedNo").toString());
                    }

                    //cost / share
                    BigDecimal costPerShare = new BigDecimal("0");
                    if (idgShare != 0) {
                        costPerShare = cost.divide(new BigDecimal(idgShare), 2, BigDecimal.ROUND_HALF_UP);
                    }

                    //FMV/share
                    BigDecimal fmvPerShare = queryFMVPerShare(reportInput);

                    //total mkt value
                    BigDecimal maketValuation = fmvPerShare.multiply(new BigDecimal(idgShare));


                    //unrealized G/L
                    BigDecimal unrealizedCost = new BigDecimal("0");
                    if (map.get("costUnRelization") != null) {
                        unrealizedCost = new BigDecimal(map.get("costUnRelization").toString());
                    }
                    BigDecimal unrealizedGL = maketValuation.subtract(unrealizedCost);
                    String unrealizedGLStr = "";
                    if (unrealizedGL.compareTo(defaultVal) == -1) {
                        unrealizedGLStr = "(" + unrealizedGL.abs() + ")";
                    }else{
                        unrealizedGLStr = unrealizedGL+"";
                    }

                    //public or private
                    String publicPrivate = map.get("portfolioStatus") == null ? "" : map.get("portfolioStatus").toString();

                    //总股份
                    ProjReportInput input = new ProjReportInput();
                    input.setPortfolioid(reportInput.getPortfolioid());
//                    input.setRound(round);
                    input.setSecurityTypeId(securityTypeID);
                    input.setClosedate(quarterTime);//截止目前的总股数

                    Long investorTotalSharesLong = projManageReportMapper.queryOtherInvestorTotalShares(input);
                    long investorTotalShares = 0;
                    if (investorTotalSharesLong != null) {
                        investorTotalShares = investorTotalSharesLong;
                    }
                    long idgAndOtherShare = investorTotalShares;

                    //idg占的股比
                    BigDecimal ownership = new BigDecimal("0");
                    if (idgAndOtherShare != 0) {
                        ownership = new BigDecimal(idgShare).divide(new BigDecimal(idgAndOtherShare), 4, BigDecimal.ROUND_HALF_UP);
                    }
                    String ownershipStr = ownership.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP) + "%";

                    result.put("Company", company);
                    result.put("TaxLotDate", closeDateStr);
                    result.put("SecurityType", security);
                    result.put("SeriesType", round);
                    result.put("TotalCost", cost);
                    result.put("TotalMktValue", maketValuation);
                    result.put("UnrealizedGL", unrealizedGLStr);
                    result.put("CostPerShare", costPerShare);
                    result.put("fmvPerShare", fmvPerShare);
                    result.put("publicPrivate", publicPrivate);
                    result.put("TotalShareOwned", idgShare);
                    result.put("TotalCSEOwned", idgShare);
                    result.put("TotalCSEOutstanding", idgAndOtherShare);
                    result.put("Ownership", ownershipStr);
                    result.put("PaidInCap", "");
                    result.put("oneLineDesc", map.get("oneLineDesc") == null ? "" : map.get("oneLineDesc").toString());
                    result.put("currency", map.get("currency") == null ? "" : map.get("currency").toString());

                    resultMapList.add(result);


                    totalCost = totalCost.add(cost);
                    totalValuation = totalValuation.add(maketValuation);
                    totalUnrealizedGL = totalUnrealizedGL.add(unrealizedGL);
                    totalIdgShare = totalIdgShare + idgShare;
                    totalOwnership = totalOwnership.add(ownership);
                }

                String totalOwnershipStr = totalOwnership.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP) + "%";
                if (totalUnrealizedGL.compareTo(defaultVal) == -1) {
                    totalUnrealizedGLStr = "(" + totalUnrealizedGL.abs() + ")";
                }

                Map<String, Object> totalResult = new HashMap<>();
                totalResult.put("Company", totalCompany);
                totalResult.put("TaxLotDate", "");
                totalResult.put("SecurityType", "");
                totalResult.put("SeriesType", "");
                totalResult.put("TotalCost", totalCost);
                totalResult.put("TotalMktValue", totalValuation);
                totalResult.put("UnrealizedGL", totalUnrealizedGLStr);
                totalResult.put("CostPerShare", "");
                totalResult.put("fmvPerShare", "");
                totalResult.put("publicPrivate", "");
                totalResult.put("TotalShareOwned", totalIdgShare);
                totalResult.put("TotalCSEOwned", totalIdgShare);
                totalResult.put("TotalCSEOutstanding", "");
                totalResult.put("Ownership", totalOwnershipStr);
                totalResult.put("PaidInCap", "");
                totalResult.put("oneLineDesc", "");
                totalResult.put("currency", "");

                resultMapList.add(totalResult);


                sumCost = sumCost.add(totalCost);
                sumValuation = sumValuation.add(totalValuation);
                sumUnrealizedGL = sumUnrealizedGL.add(totalUnrealizedGL);
                sumIdgShare = sumIdgShare + totalIdgShare;
                sumOwnership = sumOwnership.add(totalOwnership);
            }
            String sumOwnershipStr = sumOwnership.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP) + "%";

            if (sumUnrealizedGL.compareTo(defaultVal) == -1) {
                sumUnrealizedGLStr = "(" + sumUnrealizedGL.abs() + ")";
            }

            Map<String, Object> sumMap = new HashMap<>();
            sumMap.put("Company", "Grand Total");
            sumMap.put("TaxLotDate", "");
            sumMap.put("SecurityType", "");
            sumMap.put("SeriesType", "");
            sumMap.put("TotalCost", sumCost);
            sumMap.put("TotalMktValue", sumValuation);
            sumMap.put("UnrealizedGL", sumUnrealizedGLStr);
            sumMap.put("CostPerShare", "");
            sumMap.put("fmvPerShare", "");
            sumMap.put("publicPrivate", "");
            sumMap.put("TotalShareOwned", sumIdgShare);
            sumMap.put("TotalCSEOwned", sumIdgShare);
            sumMap.put("TotalCSEOutstanding", "");
            sumMap.put("Ownership", "");
            sumMap.put("PaidInCap", "");
            sumMap.put("oneLineDesc", "");
            sumMap.put("currency", "");

            resultMapList.add(sumMap);
        }

        return resultMapList;
    }

    //获取当前每股市值
    public BigDecimal queryFMVPerShare(ProjReportInput projReportInput){
        BigDecimal FMV = projManageReportMapper.queryPortfolioMarketValueByQuarter(projReportInput);
        Long remainShare = projManageReportMapper.queryPortfolioRemainByQuarter(projReportInput);
        BigDecimal FMVPerShare = new BigDecimal("0");
        if(FMV != null&&remainShare!=null&&remainShare!=0){
            FMVPerShare = FMV.divide(new BigDecimal(remainShare),2,BigDecimal.ROUND_HALF_UP);

        }
        return FMVPerShare;
    }


    public List<Map<String,Object>> queryBusinessSummary(Map<String,Object> param){

        if(param.get("isEquityOnly")!=null){

           String isEquityOnly =  (String) param.get("isEquityOnly");
           if(isEquityOnly.equals("Y")){
                return projManageReportMapper.selectPortfolioBusinessListOnlyEquity(param);
           }else{
                return projManageReportMapper.selectPortfolioBusinessList(param);
           }
        }
        return null;

    }

}
