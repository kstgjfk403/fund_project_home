package com.idgcapital.ufund.sys.service;

import com.idgcapital.ufund.model.DdlPage;
import com.idgcapital.ufund.mybatis.dao.DdlGridMapper;
import com.idgcapital.ufund.mybatis.dao.DdlPageMapper;
import com.idgcapital.ufund.mybatis.dao.IdgStaffrightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by li_zhao on 2017/12/19.
 */
@Service
public class SysMenuService {
    @Autowired
    private DdlPageMapper ddlPageMapper;

    @Autowired
    private DdlGridMapper ddlGridMapper;

    @Autowired
    private IdgStaffrightMapper idgStaffrightMapper;

    public List<DdlPage> queryMenuList(DdlPage ddlPage){
        return ddlPageMapper.selectDDLPage(ddlPage);
    }








}
