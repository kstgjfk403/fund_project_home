package com.idgcapital.ufund.common.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by tiantian_yu on 2018/1/25.
 */
public class FundIrrStasticInfo {

    private String fundID;

    private String fundfamillyname;

    private String fundname;

    private String fundfullnameeng;

    private String fundfullnamechi;

    private String dateStr;

    private BigDecimal irrLp;

    private BigDecimal irrLpAndGp;

    private String irrLpStr;

    private String irrLpAndGpStr;

    private BigDecimal mocLp;

    private BigDecimal mocLpAndGp;

    private String mocLpStr;

    private String mocLpAndGpStr;

    private BigDecimal dpiLp;

    private BigDecimal dpiLpAndGp;

    private String dpiLpStr;

    private String dpiLpAndGpStr;

    private BigDecimal totalCapitalCallsLp;

    private BigDecimal totalCapitalCallsLpAndGp;

    private String totalCapitalCallsLpStr;

    private String totalCapitalCallsLpAndGpStr;

    private BigDecimal totalDistributionsLp;

    private BigDecimal totalDistributionsLpAndGp;

    private BigDecimal lpNetAssets;

    private BigDecimal gpNetAssets;

    private BigDecimal netAssets; //LP+GP

    private BigDecimal totalGpCarry;

    private List<Map<String,Object>> lpCashFlowList;

    private List<Map<String,Object>> lpAndGpCashFlowList;

    public String getFundID() {
        return fundID;
    }

    public void setFundID(String fundID) {
        this.fundID = fundID;
    }

    public String getFundfamillyname() {
        return fundfamillyname;
    }

    public void setFundfamillyname(String fundfamillyname) {
        this.fundfamillyname = fundfamillyname;
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }

    public String getFundfullnameeng() {
        return fundfullnameeng;
    }

    public void setFundfullnameeng(String fundfullnameeng) {
        this.fundfullnameeng = fundfullnameeng;
    }

    public String getFundfullnamechi() {
        return fundfullnamechi;
    }

    public void setFundfullnamechi(String fundfullnamechi) {
        this.fundfullnamechi = fundfullnamechi;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public BigDecimal getMocLp() {
        return mocLp;
    }

    public void setMocLp(BigDecimal mocLp) {
        this.mocLp = mocLp;
    }

    public BigDecimal getMocLpAndGp() {
        return mocLpAndGp;
    }

    public void setMocLpAndGp(BigDecimal mocLpAndGp) {
        this.mocLpAndGp = mocLpAndGp;
    }

    public BigDecimal getDpiLp() {
        return dpiLp;
    }

    public void setDpiLp(BigDecimal dpiLp) {
        this.dpiLp = dpiLp;
    }

    public BigDecimal getDpiLpAndGp() {
        return dpiLpAndGp;
    }

    public void setDpiLpAndGp(BigDecimal dpiLpAndGp) {
        this.dpiLpAndGp = dpiLpAndGp;
    }

    public BigDecimal getTotalCapitalCallsLp() {
        return totalCapitalCallsLp;
    }

    public void setTotalCapitalCallsLp(BigDecimal totalCapitalCallsLp) {
        this.totalCapitalCallsLp = totalCapitalCallsLp;
    }

    public BigDecimal getTotalCapitalCallsLpAndGp() {
        return totalCapitalCallsLpAndGp;
    }

    public void setTotalCapitalCallsLpAndGp(BigDecimal totalCapitalCallsLpAndGp) {
        this.totalCapitalCallsLpAndGp = totalCapitalCallsLpAndGp;
    }

    public BigDecimal getTotalDistributionsLp() {
        return totalDistributionsLp;
    }

    public void setTotalDistributionsLp(BigDecimal totalDistributionsLp) {
        this.totalDistributionsLp = totalDistributionsLp;
    }

    public BigDecimal getTotalDistributionsLpAndGp() {
        return totalDistributionsLpAndGp;
    }

    public void setTotalDistributionsLpAndGp(BigDecimal totalDistributionsLpAndGp) {
        this.totalDistributionsLpAndGp = totalDistributionsLpAndGp;
    }

    public BigDecimal getLpNetAssets() {
        return lpNetAssets;
    }

    public void setLpNetAssets(BigDecimal lpNetAssets) {
        this.lpNetAssets = lpNetAssets;
    }

    public BigDecimal getGpNetAssets() {
        return gpNetAssets;
    }

    public void setGpNetAssets(BigDecimal gpNetAssets) {
        this.gpNetAssets = gpNetAssets;
    }

    public BigDecimal getNetAssets() {
        return netAssets;
    }

    public void setNetAssets(BigDecimal netAssets) {
        this.netAssets = netAssets;
    }

    public BigDecimal getTotalGpCarry() {
        return totalGpCarry;
    }

    public void setTotalGpCarry(BigDecimal totalGpCarry) {
        this.totalGpCarry = totalGpCarry;
    }

    public List<Map<String, Object>> getLpCashFlowList() {
        return lpCashFlowList;
    }

    public void setLpCashFlowList(List<Map<String, Object>> lpCashFlowList) {
        this.lpCashFlowList = lpCashFlowList;
    }

    public List<Map<String, Object>> getLpAndGpCashFlowList() {
        return lpAndGpCashFlowList;
    }

    public void setLpAndGpCashFlowList(List<Map<String, Object>> lpAndGpCashFlowList) {
        this.lpAndGpCashFlowList = lpAndGpCashFlowList;
    }

    public BigDecimal getIrrLp() {
        return irrLp;
    }

    public void setIrrLp(BigDecimal irrLp) {
        this.irrLp = irrLp;
    }

    public BigDecimal getIrrLpAndGp() {
        return irrLpAndGp;
    }

    public void setIrrLpAndGp(BigDecimal irrLpAndGp) {
        this.irrLpAndGp = irrLpAndGp;
    }

    public String getTotalCapitalCallsLpAndGpStr() {
        return totalCapitalCallsLpAndGpStr;
    }

    public void setTotalCapitalCallsLpAndGpStr(String totalCapitalCallsLpAndGpStr) {
        this.totalCapitalCallsLpAndGpStr = totalCapitalCallsLpAndGpStr;
    }

    public String getTotalCapitalCallsLpStr() {
        return totalCapitalCallsLpStr;
    }

    public void setTotalCapitalCallsLpStr(String totalCapitalCallsLpStr) {
        this.totalCapitalCallsLpStr = totalCapitalCallsLpStr;
    }
    public String getMocLpStr() {
        return mocLpStr;
    }

    public void setMocLpStr(String mocLpStr) {
        this.mocLpStr = mocLpStr;
    }

    public String getMocLpAndGpStr() {
        return mocLpAndGpStr;
    }

    public void setMocLpAndGpStr(String mocLpAndGpStr) {
        this.mocLpAndGpStr = mocLpAndGpStr;
    }

    public String getDpiLpStr() {
        return dpiLpStr;
    }

    public void setDpiLpStr(String dpiLpStr) {
        this.dpiLpStr = dpiLpStr;
    }

    public String getDpiLpAndGpStr() {
        return dpiLpAndGpStr;
    }

    public void setDpiLpAndGpStr(String dpiLpAndGpStr) {
        this.dpiLpAndGpStr = dpiLpAndGpStr;
    }

    public String getIrrLpStr() {
        return irrLpStr;
    }

    public void setIrrLpStr(String irrLpStr) {
        this.irrLpStr = irrLpStr;
    }

    public String getIrrLpAndGpStr() {
        return irrLpAndGpStr;
    }

    public void setIrrLpAndGpStr(String irrLpAndGpStr) {
        this.irrLpAndGpStr = irrLpAndGpStr;
    }
}
