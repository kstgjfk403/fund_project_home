package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

import java.util.List;
import java.util.Map;

public class DdlBaseInfo {

    @JsonView({AppView.DictBaseInfo.class})
    private String baseType;

    private String dictArray;

    @JsonView({AppView.DictBaseInfo.class})
    private List<Map<String,Object>> baseInfoList;


    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    public List<Map<String, Object>> getBaseInfoList() {
        return baseInfoList;
    }

    public void setBaseInfoList(List<Map<String, Object>> baseInfoList) {
        this.baseInfoList = baseInfoList;
    }

    public String getDictArray() {
        return dictArray;
    }

    public void setDictArray(String dictArray) {
        this.dictArray = dictArray;
    }
}