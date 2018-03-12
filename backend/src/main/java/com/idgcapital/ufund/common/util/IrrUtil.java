package com.idgcapital.ufund.common.util;

import java.math.BigDecimal;
import java.util.*;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

public class IrrUtil {
    /**迭代次数*/
    public static int LOOPNUM=1000;
    /**最小差异*/
    public static final double MINDIF=0.00000001;

    public static final BigDecimal MINDIFDECIMAL= new BigDecimal("0.00000001");

    public static Date[] seasonArray = {
            DateUtil.stringtoDate("2001-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2001-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2001-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2001-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2002-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2002-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2002-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2002-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2003-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2003-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2003-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2003-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2004-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2004-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2004-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2004-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2005-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2005-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2005-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2005-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2006-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2006-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2006-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2006-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2007-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2007-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2007-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2007-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2008-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2008-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2008-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2008-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2009-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2009-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2009-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2009-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2010-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2010-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2010-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2010-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2011-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2011-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2011-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2011-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2012-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2012-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2012-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2012-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2013-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2013-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2013-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2013-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2014-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2014-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2014-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2014-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2015-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2015-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2015-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2015-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2016-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2016-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2016-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2016-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2017-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2017-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2017-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2017-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2018-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2018-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2018-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2018-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2019-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2019-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2019-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2019-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2020-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2020-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2020-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2020-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2021-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2021-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2021-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2021-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2022-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2022-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2022-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2022-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2023-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2023-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2023-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2023-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2024-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2024-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2024-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2024-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2025-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2025-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2025-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2025-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2026-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2026-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2026-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2026-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2027-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2027-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2027-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2027-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2028-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2028-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2028-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2028-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2029-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2029-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2029-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2029-12-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2030-03-31", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2030-06-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2030-09-30", DateUtil.LONG_DATE_FORMAT),
            DateUtil.stringtoDate("2030-12-31", DateUtil.LONG_DATE_FORMAT),
    };
    public static int getExcelDateValue(Date actualDate){
        for(int i = 0 ; i<seasonArray.length; i++){
            if(actualDate.compareTo(seasonArray[i])>0){

            }else if(actualDate.compareTo(seasonArray[i])<=0){
                return i;
            }
        }
        return -1;
    }

    public static long getExcelDateValueForPortfolio(Date startDate, Date endDate, Date actualDate){

        long diff = DateUtil.dayDiff(startDate,actualDate);
        return diff;
    }

    public static Date getPreQuarterDateByThisQuarterDate(Date endDate){
        for(int i = 0 ; i<seasonArray.length; i++){
            if(endDate.compareTo(seasonArray[i])==0){
                return seasonArray[i-1];
            }
        }
        return null;
    }

    public static List<Double> processFlowArray(List<Double> cashValues,  List<Integer> cashDates){
        List<Double>  flowTrueArr = new ArrayList<Double>();
        //int i = cashDates.get(0);
        for(int i = cashDates.get(0); i<= cashDates.get(cashDates.size()-1); i++){
            double totalAmt = 0.00;
            for(int index=0; index<cashDates.size(); index++){
                if(cashDates.get(index) == i){
                    totalAmt += cashValues.get(index);
                }
            }
            flowTrueArr.add(totalAmt);
        }
        return flowTrueArr;
    }

    public static List<BigDecimal> processFlowArrayForPortfolio(List<BigDecimal> cashValues,  List<Long> cashDates){
        List<BigDecimal>  flowTrueArr = new ArrayList<BigDecimal>();
        //int i = cashDates.get(0);
        for(long i = cashDates.get(0); i<= cashDates.get(cashDates.size()-1); i++){
            BigDecimal totalAmt = new BigDecimal(0.00);
            for(int index=0; index<cashDates.size(); index++){
                if(cashDates.get(index) == i){
                    //totalAmt += cashValues.get(index);
                    totalAmt = totalAmt.add(cashValues.get(index));
                }
            }
            flowTrueArr.add(totalAmt);
        }
        return flowTrueArr;
    }

    public static List<BigDecimal> processFlowArrayForPortfolioForAll(List<BigDecimal> cashValues,  List<Long> cashDates){
        long startDateIndex = 0;
        long endDateIndex = 0;
        for(Long tmp:cashDates){
            if(tmp<startDateIndex){
                startDateIndex = tmp;
            }
            if(tmp>endDateIndex){
                endDateIndex = tmp;
            }
        }

        List<BigDecimal>  flowTrueArr = new ArrayList<BigDecimal>();
        //int i = cashDates.get(0);
        for(long i = startDateIndex; i<= endDateIndex; i++){
            BigDecimal totalAmt = new BigDecimal(0.00);
            for(int index=0; index<cashDates.size(); index++){
                if(cashDates.get(index) == i){
                    //totalAmt += cashValues.get(index);
                    totalAmt = totalAmt.add(cashValues.get(index));
                }
            }
            flowTrueArr.add(totalAmt);
        }
        return flowTrueArr;
    }


    public static List<Map<String,Object>> processLpCashFlowInfoArray(List<Double> cashValues,  List<Integer> cashDates){
        List<Map<String,Object>>  lpCashFlowInfoArr = new ArrayList<Map<String,Object>>();
        //int i = cashDates.get(0);
        for(int i = cashDates.get(0); i<= cashDates.get(cashDates.size()-1); i++){
            double totalCallAmt = 0.00;
            double totalDisAmt = 0.00;
            double totalAmt = 0.00;
            for(int index=0; index<cashDates.size()-1; index++){//最后一个其实是fund NAV
                if(cashDates.get(index) == i){
                    totalAmt += cashValues.get(index);
                    if(cashValues.get(index)>0.00){
                        totalDisAmt += cashValues.get(index);
                    }else if(cashValues.get(index)<0.00){
                        totalCallAmt += cashValues.get(index);
                    }
                }
            }
            Map<String,Object> cashFlowInfo = new HashMap<String, Object>();
            cashFlowInfo.put("date", seasonArray[i]);
            cashFlowInfo.put("distribution", new BigDecimal(totalDisAmt));
            cashFlowInfo.put("capitalCalled", new BigDecimal(totalCallAmt));
            cashFlowInfo.put("capitalCalledStr", "("+new BigDecimal(totalCallAmt).abs().toString()+")");
            cashFlowInfo.put("netCashFlows", new BigDecimal(totalAmt));
            if(new BigDecimal(totalAmt).compareTo(new BigDecimal(0.00))==1 ||new BigDecimal(totalAmt).compareTo(new BigDecimal(0.00))==0 ){
                cashFlowInfo.put("netCashFlowsStr", new BigDecimal(totalAmt));
            }else{
                cashFlowInfo.put("netCashFlowsStr", "("+new BigDecimal(totalAmt).abs().toString()+")");
            }
            cashFlowInfo.put("description","");
            lpCashFlowInfoArr.add(cashFlowInfo);
        }
        if(lpCashFlowInfoArr!=null && lpCashFlowInfoArr.size()>0){
            lpCashFlowInfoArr.get(lpCashFlowInfoArr.size()-1).put("date", seasonArray[cashDates.get(cashDates.size()-1)]);
            lpCashFlowInfoArr.get(lpCashFlowInfoArr.size()-1).put("fundNav", new BigDecimal(cashValues.get(cashValues.size()-1)));
            lpCashFlowInfoArr.get(lpCashFlowInfoArr.size()-1).put("netCashFlows", new BigDecimal(cashValues.get(cashValues.size()-1)));
            lpCashFlowInfoArr.get(lpCashFlowInfoArr.size()-1).put("netCashFlowsStr", new BigDecimal(cashValues.get(cashValues.size()-1)));
        }

        return lpCashFlowInfoArr;
    }
    public static List<Map<String,Object>> processGrossCashFlowInfoArray(List<Double> cashValues,  List<Integer> cashDates){
        List<Map<String,Object>>  grossCashFlowInfoArr = new ArrayList<Map<String,Object>>();
        //int i = cashDates.get(0);
        for(int i = cashDates.get(0); i<= cashDates.get(cashDates.size()-1); i++){
            double totalCallAmt = 0.00;
            double totalDisAmt = 0.00;
            double totalAmt = 0.00;
            for(int index=0; index<cashDates.size()-1; index++){//最后一个其实是fund NAV
                if(cashDates.get(index) == i){
                    totalAmt += cashValues.get(index);
                    if(cashValues.get(index)>0.00){
                        totalDisAmt += cashValues.get(index);
                    }else if(cashValues.get(index)<0.00){
                        totalCallAmt += cashValues.get(index);
                    }
                }
            }
            Map<String,Object> cashFlowInfo = new HashMap<String, Object>();
            cashFlowInfo.put("date", seasonArray[i]);
            cashFlowInfo.put("distribution", new BigDecimal(totalDisAmt));
            cashFlowInfo.put("capitalCalled", new BigDecimal(totalCallAmt));
            cashFlowInfo.put("capitalCalledStr", "("+new BigDecimal(totalCallAmt).abs().toString()+")");
            cashFlowInfo.put("netCashFlows", new BigDecimal(totalAmt));
            if(new BigDecimal(totalAmt).compareTo(new BigDecimal(0.00))==1 ||new BigDecimal(totalAmt).compareTo(new BigDecimal(0.00))==0 ){
                cashFlowInfo.put("netCashFlowsStr", new BigDecimal(totalAmt));
            }else{
                cashFlowInfo.put("netCashFlowsStr", "("+new BigDecimal(totalAmt).abs().toString()+")");
            }
            cashFlowInfo.put("description","");
            grossCashFlowInfoArr.add(cashFlowInfo);
        }
        if(grossCashFlowInfoArr!=null && grossCashFlowInfoArr.size()>0){
            grossCashFlowInfoArr.get(grossCashFlowInfoArr.size()-1).put("date", seasonArray[cashDates.get(cashDates.size()-1)]);
            grossCashFlowInfoArr.get(grossCashFlowInfoArr.size()-1).put("fundNav", new BigDecimal(cashValues.get(cashValues.size()-1)));
            grossCashFlowInfoArr.get(grossCashFlowInfoArr.size()-1).put("netCashFlows", new BigDecimal(cashValues.get(cashValues.size()-1)));
            grossCashFlowInfoArr.get(grossCashFlowInfoArr.size()-1).put("netCashFlowsStr", new BigDecimal(cashValues.get(cashValues.size()-1)));
        }

        return grossCashFlowInfoArr;
    }



    /**
     * @desc 使用方法参考main方法
     * @param cashFlow  资金流
     * @return 收益率
     */
    public static double getIrr(List<Double> cashFlow){
        double flowOut=cashFlow.get(0);
        double minValue=0d;
        double maxValue=1d;
        double testValue=0d;
        LOOPNUM = 1000;
        while(LOOPNUM>0){
            testValue=(minValue+maxValue)/2;
            double npv=NPV(cashFlow,testValue);
            if(Math.abs(flowOut+npv)<MINDIF){
                break;
            }else if(Math.abs(flowOut)>npv){
                maxValue=testValue;
            }else{
                minValue=testValue;
            }
            LOOPNUM--;
        }
        return testValue;
    }

    public static BigDecimal getIrrForPortfolio(List<BigDecimal> cashFlow){
        BigDecimal flowOut=cashFlow.get(0);
        BigDecimal minValue= new BigDecimal(0.00);
        BigDecimal maxValue= new BigDecimal(1.00);
        BigDecimal testValue= new BigDecimal(0.00);
        LOOPNUM = 100;
        while(LOOPNUM>0){
            testValue=(minValue.add(maxValue)).divide(new BigDecimal(2.00));
            BigDecimal npv=NPVForBigDecimal(cashFlow,testValue);
           /* if(Math.abs(flowOut+npv)<MINDIF){
                break;
            }else if(Math.abs(flowOut)>npv){
                maxValue=testValue;
            }else{
                minValue=testValue;
            }*/

            if(flowOut.add(npv).abs().compareTo(MINDIFDECIMAL)<0){
                break;
            }else if(flowOut.abs().compareTo(npv)>0){
                maxValue=testValue;
            }else{
                minValue=testValue;
            }
            LOOPNUM--;
        }
        return testValue;
    }



    public static double NPV(List<Double> flowInArr,double rate){
        double npv=0;
        for(int i=1;i<flowInArr.size();i++){
            npv+=flowInArr.get(i)/Math.pow(1+rate, i);
        }
        return npv;
    }
    public static BigDecimal NPVForBigDecimal(List<BigDecimal> flowInArr,BigDecimal rate){
        BigDecimal npv= new BigDecimal(0.00);
        for(int i=1;i<flowInArr.size();i++){

            BigDecimal num1 = flowInArr.get(i);
            BigDecimal num2 = rate.add(new BigDecimal(1.00)).pow(i);
            BigDecimal num3 = num1.divide(num2, 10, ROUND_HALF_DOWN);
            npv = npv.add(num3);
        }
        return npv;
    }

    public static double irr(double[] income) {
        return irr(income, 0.0001D);
    }

    public static double irr(double[] values, double guess) {
        int maxIterationCount = 20;
        double absoluteAccuracy = 1.0E-007D;

        double x0 = guess;

        int i = 0;
        while (i < maxIterationCount) {
            double fValue = 0.0D;
            double fDerivative = 0.0D;
            for (int k = 0; k < values.length; k++) {
                fValue += values[k] / Math.pow(1.0D + x0, k);
                fDerivative += -k * values[k] / Math.pow(1.0D + x0, k + 1);
            }
            double x1 = x0 - fValue / fDerivative;
            if (Math.abs(x1 - x0) <= absoluteAccuracy) {
                return x1;
            }
            x0 = x1;
            i++;
        }
        return (0.0D / 0.0D);
    }


    public static void main(String[] args) {
        double flowOut=-2710000;
        List<Double> flowInArr=new ArrayList<Double>();
        flowInArr.add(-164100000d);   //DateUtil.stringtoDate("2010-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-164100000d);//DateUtil.stringtoDate("2010-12-31", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-136750000d);//DateUtil.stringtoDate("2011-03-31", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-629050000d);//DateUtil.stringtoDate("2011-06-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-7421000d);//DateUtil.stringtoDate("2011-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-324256000d);//DateUtil.stringtoDate("2011-12-31", DateUtil.LONG_DATE_FORMAT),

        flowInArr.add(-2710000d);//DateUtil.stringtoDate("2012-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(0d);//DateUtil.stringtoDate("2012-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(0d);//DateUtil.stringtoDate("2012-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(0d);//DateUtil.stringtoDate("2012-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-180250000d);//DateUtil.stringtoDate("2013-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-174986238d);//DateUtil.stringtoDate("2013-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-5263762d);//DateUtil.stringtoDate("2013-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-216300000d);//DateUtil.stringtoDate("2013-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(0d);//DateUtil.stringtoDate("2014-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-288400000d);//DateUtil.stringtoDate("2014-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(0d);//DateUtil.stringtoDate("2014-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-282788266d);//DateUtil.stringtoDate("2014-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-17882351d);//DateUtil.stringtoDate("2014-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-395024053d);//DateUtil.stringtoDate("2015-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-70822537d);//DateUtil.stringtoDate("2015-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(-297925871d);//DateUtil.stringtoDate("2015-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(0d);//DateUtil.stringtoDate("2015-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(960369425d);//DateUtil.stringtoDate("2016-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(110673362d);//DateUtil.stringtoDate("2016-09-30", DateUtil.LONG_DATE_FORMAT),
        flowInArr.add(8138265522d);//DateUtil.stringtoDate("2016-09-30", DateUtil.LONG_DATE_FORMAT),



        Double irr = IrrUtil.getIrr(flowInArr);
        System.out.println(irr);
        Double seasonIRR = 1+irr;
        System.out.println(seasonIRR);
        Double yearIRR = Math.pow(seasonIRR, 4) ;
        System.out.println(yearIRR);




        List<Double> flowValueInArr=new ArrayList<Double>();
        flowValueInArr.add(-164100000d);   //DateUtil.stringtoDate("2010-09-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-164100000d);//DateUtil.stringtoDate("2010-12-31", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-136750000d);//DateUtil.stringtoDate("2011-03-31", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-629050000d);//DateUtil.stringtoDate("2011-06-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-7421000d);//DateUtil.stringtoDate("2011-09-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-324256000d);//DateUtil.stringtoDate("2011-12-31", DateUtil.LONG_DATE_FORMAT),

        flowValueInArr.add(-2710000d);//DateUtil.stringtoDate("2012-03-31", DateUtil.LONG_DATE_FORMAT),

        flowValueInArr.add(-180250000d);//DateUtil.stringtoDate("2013-09-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-174986238d);//DateUtil.stringtoDate("2013-09-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-5263762d);//DateUtil.stringtoDate("2013-09-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-216300000d);//DateUtil.stringtoDate("2013-09-30", DateUtil.LONG_DATE_FORMAT),

        flowValueInArr.add(-288400000d);//DateUtil.stringtoDate("2014-09-30", DateUtil.LONG_DATE_FORMAT),

        flowValueInArr.add(-282788266d);//DateUtil.stringtoDate("2014-09-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-17882351d);//DateUtil.stringtoDate("2014-09-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-395024053d);//DateUtil.stringtoDate("2015-09-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-70822537d);//DateUtil.stringtoDate("2015-09-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(-297925871d);//DateUtil.stringtoDate("2015-09-30", DateUtil.LONG_DATE_FORMAT),

        flowValueInArr.add(960369425d);//DateUtil.stringtoDate("2016-09-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(110673362d);//DateUtil.stringtoDate("2016-09-30", DateUtil.LONG_DATE_FORMAT),
        flowValueInArr.add(8138265522d);//DateUtil.stringtoDate("2016-09-30", DateUtil.LONG_DATE_FORMAT),

        List<Date> flowDateInArr=new ArrayList<Date>();
        flowDateInArr.add(DateUtil.stringtoDate("2010-09-30", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2010-12-31", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2011-03-31", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2011-06-30", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2011-09-30", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2011-12-31", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2012-03-31", DateUtil.LONG_DATE_FORMAT));


        flowDateInArr.add(DateUtil.stringtoDate("2013-03-31", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2013-06-30", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2013-09-30", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2013-12-31", DateUtil.LONG_DATE_FORMAT));


        flowDateInArr.add(DateUtil.stringtoDate("2014-06-30", DateUtil.LONG_DATE_FORMAT));

        flowDateInArr.add(DateUtil.stringtoDate("2014-12-31", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2015-03-31", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2015-06-30", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2015-09-30", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2015-12-31", DateUtil.LONG_DATE_FORMAT));

        flowDateInArr.add(DateUtil.stringtoDate("2016-06-30", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2016-09-30", DateUtil.LONG_DATE_FORMAT));
        flowDateInArr.add(DateUtil.stringtoDate("2016-12-31", DateUtil.LONG_DATE_FORMAT));


        List<Double> cashValues = new ArrayList<Double>();
        List<Integer> cashDates = new ArrayList<Integer>();

        List<Map<String, Object>> cashList = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < flowDateInArr.size(); i++){
            Map<String, Object> map =new HashMap<String,Object>();
            map.put("cashValue", flowValueInArr.get(i));
            map.put("cashDate",flowDateInArr.get(i));
            cashList.add(map);
        }




        for(Map<String, Object> cashMap : cashList){

            if(cashMap.get("cashValue") != null){

                BigDecimal bigDecimal = new BigDecimal(String.valueOf(cashMap.get("cashValue")));
                String cashValueStr = bigDecimal.toString();
                Double.valueOf(cashValueStr);
                cashValues.add(Double.valueOf(cashValueStr));
                //String cashDateStr = String.valueOf(cashMap.get("cashDate"));
                String cashDateStr = DateUtil.dateToString(((Date)cashMap.get("cashDate")),DateUtil.LONG_DATE_FORMAT);
                int cashDate = IrrUtil.getExcelDateValue((Date)cashMap.get("cashDate"));
                cashDates.add(cashDate);
            }
        }
        List<Double> flowTrueArr = new ArrayList<Double>();


        flowTrueArr = IrrUtil.processFlowArray(cashValues, cashDates);

        Double currentirr = IrrUtil.getIrr(flowTrueArr);
        System.out.println(currentirr);
        Double currentseasonIRR = 1+currentirr;
        System.out.println(currentseasonIRR);
        Double currentyearIRR = Math.pow(currentseasonIRR, 4) ;

        System.out.println("系统现有" + currentyearIRR);

    }
}
