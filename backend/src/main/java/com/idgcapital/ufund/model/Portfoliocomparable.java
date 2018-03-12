package com.idgcapital.ufund.model;

import java.math.BigDecimal;
import java.util.Date;

public class Portfoliocomparable {
    private Integer cid;

    private String pid;

    private String comparableName;

    private Date valueDate;

    private BigDecimal multiple;

    private BigDecimal weight;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getComparableName() {
        return comparableName;
    }

    public void setComparableName(String comparableName) {
        this.comparableName = comparableName == null ? null : comparableName.trim();
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }

    public void setMultiple(BigDecimal multiple) {
        this.multiple = multiple;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}