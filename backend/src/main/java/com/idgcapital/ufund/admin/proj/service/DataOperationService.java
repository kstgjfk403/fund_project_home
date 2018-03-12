package com.idgcapital.ufund.admin.proj.service;

import com.idgcapital.ufund.model.DdlBaseInfo;
import com.idgcapital.ufund.model.Portfoliobasicinfo;
import com.idgcapital.ufund.model.Portfoliostatushistory;
import com.idgcapital.ufund.mybatis.dao.PortfoliobasicinfoMapper;
import com.idgcapital.ufund.mybatis.dao.PortfoliostatushistoryMapper;
import com.idgcapital.ufund.sys.service.SysBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by li_zhao on 2018/3/8.
 */
@Service
public class DataOperationService {
    @Autowired
    private PortfoliobasicinfoMapper portfoliobasicinfoMapper;

    @Autowired
    private PortfoliostatushistoryMapper portfoliostatushistoryMapper;

    @Autowired
    private SysBaseInfoService sysBaseInfoService;

    //处理项目的历史状态数据
    public void dealWithPortfolioStatusHistory(){
        List<Portfoliobasicinfo> portfoliobasicinfoList = portfoliobasicinfoMapper.selectAll();
        for(Portfoliobasicinfo portfoliobasicinfo:portfoliobasicinfoList){
            Integer statusId = portfolioStatusFilter(portfoliobasicinfo.getPrivateorpublic());
            portfoliobasicinfo.setPortfoliostatus(statusId);
            portfoliobasicinfoMapper.updateByPrimaryKey(portfoliobasicinfo);

            Portfoliostatushistory portfoliostatushistory = new Portfoliostatushistory();
            portfoliostatushistory.setPortfolioid(portfoliobasicinfo.getPortfolioid());
            portfoliostatushistory.setPortfoliostatus(statusId);
            portfoliostatushistory.setStatuseffectivedate(portfoliobasicinfo.getPortfoliostatusdate());
            portfoliostatushistoryMapper.insert(portfoliostatushistory);

            if(portfoliobasicinfo.getLastportfoliostatus() != null){
                Integer lastStatusId = portfolioStatusFilter(portfoliobasicinfo.getLastportfoliostatus());
                Portfoliostatushistory lastHistory = new Portfoliostatushistory();
                lastHistory.setPortfolioid(portfoliobasicinfo.getPortfolioid());
                lastHistory.setPortfoliostatus(lastStatusId);
                lastHistory.setStatuseffectivedate(portfoliobasicinfo.getLastportfoliostatusdate());
                portfoliostatushistoryMapper.insert(lastHistory);
            }
        }
    }

    public Integer portfolioStatusFilter(String baseName) {
        DdlBaseInfo ddlBaseInfo = new DdlBaseInfo();
        ddlBaseInfo.setDictArray("DDL_PortfolioStatus");
        List<DdlBaseInfo> ddlBaseInfoList = sysBaseInfoService.querySysDictList(ddlBaseInfo);
        List<Map<String, Object>> ddlMapList = ddlBaseInfoList.get(0).getBaseInfoList();
        for (Map<String, Object> map : ddlMapList) {
            if (baseName != null) {
                if (map.get("baseName").toString().equals(baseName)) {
                    return Integer.parseInt(map.get("baseId").toString());
                }
            }
        }
        return -1;
    }



}
