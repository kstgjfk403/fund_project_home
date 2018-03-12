package com.idgcapital.ufund.model;

import java.math.BigDecimal;

/**
 * Created by li_zhao on 2018/2/3.
 */
public class ProjReportOutput {
    private BigDecimal cost;
    private BigDecimal valuation;
    private long shareOwnedNum;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getValuation() {
        return valuation;
    }

    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    public long getShareOwnedNum() {
        return shareOwnedNum;
    }

    public void setShareOwnedNum(long shareOwnedNum) {
        this.shareOwnedNum = shareOwnedNum;
    }
}
