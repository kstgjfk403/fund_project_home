package com.idgcapital.ufund.admin.proj.service;

import com.idgcapital.ufund.common.util.DateUtil;
import com.idgcapital.ufund.common.util.StringUtil;
import com.idgcapital.ufund.model.*;
import com.idgcapital.ufund.model.view.JsonResult;
import com.idgcapital.ufund.mybatis.dao.SpvcaptableMapper;
import com.idgcapital.ufund.mybatis.dao.SpvinfoMapper;
import com.idgcapital.ufund.mybatis.dao.SpvportfolioinfoMapper;
import com.idgcapital.ufund.security.model.LoginUser;
import com.idgcapital.ufund.sys.service.SysBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by li_zhao on 2017/12/15.
 */
@Service
public class SPVAdminService {
    @Autowired
    private SpvinfoMapper spvinfoMapper;

    @Autowired
    private SpvportfolioinfoMapper spvportfolioinfoMapper;

    @Autowired
    private SpvcaptableMapper spvcaptableMapper;

    @Autowired
    private SysBaseInfoService sysBaseInfoService;


    //spv basic
    public JsonResult querySPVEntityList(Spvinfo spvinfo) {

        JsonResult jsonResult = new JsonResult();

        int pageStart = (spvinfo.getPageIndex()-1)*spvinfo.getPageCount();
        int pageEnd = spvinfo.getPageIndex()*spvinfo.getPageCount();
        spvinfo.setPageEnd(pageEnd);
        spvinfo.setPageStart(pageStart);
        List<Spvinfo>  spvinfoList = spvinfoMapper.selectSPVEntityList(spvinfo);

        //翻译
        for(Spvinfo info:spvinfoList){
            spvinfoDDLFilter(info,"DDL_SpvType");
        }

        long count = spvinfoMapper.selectSPVEntityCount(spvinfo);

        jsonResult.setData(spvinfoList);
        jsonResult.setCount(count);

        return jsonResult;

    }


    public JsonResult querySPVEntityTreeList() {
        JsonResult jsonResult = new JsonResult();

        List<Spvinfo> spvinfoList = spvinfoMapper.selectBySPVParentId("-1");

        Tree treeNode = new Tree();
        Spvinfo root = spvinfoList.get(0);
        //生成树
        treeNode.setId(root.getSpvid());
        treeNode.setLabel(root.getSpvname());
        treeNode.setChildren(getChildren(root, spvinfoList));


        jsonResult.setData(treeNode);
        return jsonResult;
    }

    //生成树
    private List<Tree> getChildren(Spvinfo root, List<Spvinfo> spvinfoList) {
        List<Tree> treeList = new ArrayList<>();
        for (Spvinfo spvinfo : spvinfoList) {
            if (spvinfo.getParentspvid().equals(root.getSpvid())) {
                Tree node = new Tree();
                if (spvinfo.getIsleaf().equals("N")) { // 是否有孩子结点

                    List<Spvinfo> spvinfoListRecursion = spvinfoMapper.selectBySPVParentId(spvinfo.getSpvid());
                    node.setId(spvinfo.getSpvid());
                    node.setLabel(spvinfo.getSpvname());
                    node.setChildren(getChildren(spvinfo, spvinfoListRecursion));
                    treeList.add(node); // 添加内部结点
                } else {
                    node.setId(spvinfo.getSpvid());
                    node.setLabel(spvinfo.getSpvname());
                    treeList.add(node); // 添加叶子结点
                }
            }
        }
        return treeList;
    }

    public Spvinfo querySPVEntityById(String id) {
        Spvinfo spvinfo =  spvinfoMapper.selectByPrimaryKey(id);

        spvinfoDDLFilter(spvinfo,"DDL_SpvType");

        return spvinfo;

    }

    private void spvinfoDDLFilter(Spvinfo spvinfo,String filterType){
        DdlBaseInfo ddlBaseInfo = new DdlBaseInfo();
        ddlBaseInfo.setDictArray(filterType);
        List<DdlBaseInfo> ddlBaseInfoList = sysBaseInfoService.querySysDictList(ddlBaseInfo);
        for(DdlBaseInfo ddl:ddlBaseInfoList){
            List<Map<String,Object>> ddlMapList = ddl.getBaseInfoList();
            switch (ddl.getBaseType()){
                case "DDL_SpvType":
                    if(spvinfo.getSpvtype()!=null){
                        for(Map<String,Object> map:ddlMapList){
                            if(Integer.parseInt(map.get("baseId").toString()) == spvinfo.getSpvtype()){
                                spvinfo.setSpvtypestr(map.get("baseName").toString());
                                break;
                            }
                        }
                    }
                    break;
                default:break;
            }
        }
    }

    private void spvinfoCaptableDDLFilter(Spvcaptable spvcaptable,String filterType){
        DdlBaseInfo ddlBaseInfo = new DdlBaseInfo();
        ddlBaseInfo.setDictArray(filterType);
        List<DdlBaseInfo> ddlBaseInfoList = sysBaseInfoService.querySysDictList(ddlBaseInfo);
        for(DdlBaseInfo ddl:ddlBaseInfoList){
            List<Map<String,Object>> ddlMapList = ddl.getBaseInfoList();
            switch (ddl.getBaseType()){
                case "DDL_SpvInvestorType":
                    if(spvcaptable.getStockholdertype()!=null){
                        for(Map<String,Object> map:ddlMapList){
                            if(map.get("baseId").toString().equals(spvcaptable.getStockholdertype())){
                                spvcaptable.setStockholdertypestr(map.get("baseName").toString());
                                break;
                            }
                        }
                    }
                    break;
                default:break;
            }
        }
    }


    public int addSPVEntity(Spvinfo spvbasicinfo, LoginUser loginUser) {
        String spvId = getSPVid();

        //对父节点进行操作 修改是否为叶子结点
        Spvinfo parentSpvInfo = querySPVEntityById(spvbasicinfo.getParentspvid());
        if(parentSpvInfo.getIsleaf().equals("Y")){
            parentSpvInfo.setIsleaf("N");
            updateSPVEntity(parentSpvInfo,loginUser);
        }

        spvbasicinfo.setSpvid(spvId);
        spvbasicinfo.setCreatedby(loginUser.getUserId());
        spvbasicinfo.setModifiedby(loginUser.getUserId());
        spvbasicinfo.setCreateddate(new Date());
        spvbasicinfo.setModifieddate(new Date());
        spvinfoMapper.insert(spvbasicinfo);

        return 1;
    }

    public int updateSPVEntity(Spvinfo spvbasicinfo, LoginUser loginUser) {
        spvbasicinfo.setModifieddate(new Date());
        spvbasicinfo.setModifiedby(loginUser.getUserId());
        spvinfoMapper.updateByPrimaryKey(spvbasicinfo);

        return 1;

    }

    public int deleteSPVEntityById(String id) {
        //删除节点
        spvinfoMapper.deleteByPrimaryKey(id);

        //删除子节点
        List<Spvinfo> spvinfoList = spvinfoMapper.selectBySPVParentId(id);
        for (Spvinfo s : spvinfoList) {
            spvinfoMapper.deleteByPrimaryKey(s.getSpvid());
        }

        return 1;
    }

    //spv investor
    public JsonResult queryInvestorListBySPVId(Spvcaptable spvcaptableSearch) {
        JsonResult jsonResult = new JsonResult();

        if (!StringUtil.isBlankOrNull(spvcaptableSearch.getCapdatestr())) {
            spvcaptableSearch.setCapdate(DateUtil.stringtoDate(spvcaptableSearch.getCapdatestr(), DateUtil.LONG_DATE_FORMAT));
        }
        List<Spvcaptable> spvcaptableList = spvcaptableMapper.selectInvestorListBySPVId(spvcaptableSearch);

        Double total = 0.0;
        for (Spvcaptable spvcaptable : spvcaptableList) {
            spvcaptable.setCapdatestr(DateUtil.dateToString(spvcaptable.getCapdate(), DateUtil.LONG_DATE_FORMAT));
            if (!StringUtil.isBlankOrNull(spvcaptable.getLpaamt())) {
                total += Double.parseDouble(spvcaptable.getLpaamt());
            }
        }

        DecimalFormat df = new DecimalFormat("######0.00");
        for (Spvcaptable spvcaptable : spvcaptableList) {
            if (!StringUtil.isBlankOrNull(spvcaptable.getLpaamt())) {
                BigDecimal b = new BigDecimal(Double.parseDouble(spvcaptable.getLpaamt()) / total);
                double lpaProp = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                spvcaptable.setLpaamtprop(df.format(lpaProp * 100));
            }
            spvinfoCaptableDDLFilter(spvcaptable,"DDL_SpvInvestorType");
        }

        jsonResult.setData(spvcaptableList);
        return jsonResult;
    }

    public int updateSPVInvestorList(List<Spvcaptable> spvcaptableList, LoginUser loginUser) {
        if (spvcaptableList != null && spvcaptableList.size() > 0) {
            Spvcaptable deleteSpvCap = new Spvcaptable();
            deleteSpvCap.setSpvid(spvcaptableList.get(0).getSpvid());
            deleteSpvCap.setCapdate(DateUtil.stringtoDate(spvcaptableList.get(0).getCapdatestr(), DateUtil.LONG_DATE_FORMAT));
            spvcaptableMapper.deleteInvestorListBySPVId(deleteSpvCap);

            for (Spvcaptable spvcaptable : spvcaptableList) {
                spvcaptable.setCapdate(DateUtil.stringtoDate(spvcaptable.getCapdatestr(), DateUtil.LONG_DATE_FORMAT));
                spvcaptable.setModifiedby(loginUser.getUserId());
                spvcaptable.setModifieddate(new Date());
                spvcaptableMapper.insert(spvcaptable);
            }
        }

        return 1;
    }

    public int deleteSPVInvestors(Spvcaptable spvcaptable) {
        if (!StringUtil.isBlankOrNull(spvcaptable.getCapdatestr())) {
            spvcaptable.setCapdate(DateUtil.stringtoDate(spvcaptable.getCapdatestr(), DateUtil.LONG_DATE_FORMAT));
        }
        return spvcaptableMapper.deleteInvestorListBySPVId(spvcaptable);
    }


    //spv portfolio
    public List<Spvportfolioinfo> querySPVPortfolioList(Spvportfolioinfo spvportfolioinfo) {
        return spvportfolioinfoMapper.selectSpvByPortfolio(spvportfolioinfo);
    }

    public Spvportfolioinfo querySPVPortfolioById(Integer id) {
        return spvportfolioinfoMapper.selectByPrimaryKey(id);
    }


    public int addSPVPortfolio(Spvportfolioinfo spvportfolioinfo, LoginUser loginUser) {
        //spv portfolio info
        spvportfolioinfo.setCreatedby(loginUser.getUserId());
        spvportfolioinfo.setCreateddate(new Date());
        spvportfolioinfo.setModifiedby(loginUser.getUserId());
        spvportfolioinfo.setModifieddate(new Date());
        spvportfolioinfoMapper.insert(spvportfolioinfo);

        return 1;
    }

    public int updateSPVPortfolio(Spvportfolioinfo spvportfolioinfo, LoginUser loginUser) {

        spvportfolioinfo.setModifieddate(new Date());
        spvportfolioinfo.setModifiedby(loginUser.getUserId());
        spvportfolioinfoMapper.updateByPrimaryKey(spvportfolioinfo);

        return 1;

    }

    public int deleteSPVPortfolioById(Integer id) {
        return spvportfolioinfoMapper.deleteByPrimaryKey(id);
    }

    public String getSPVid() {
        String maxSPVId = spvinfoMapper.selectMaxSPVId();
        String maxId = maxSPVId.substring(maxSPVId.indexOf("S") + 1);
        int id = Integer.parseInt(maxId) + 1;
        return "S" + StringUtil.autoCompleteZero(String.valueOf(id), 5);
    }
}
