package com.idgcapital.ufund.sys.service;

import com.idgcapital.ufund.common.util.StringUtil;
import com.idgcapital.ufund.model.IdgStaff;
import com.idgcapital.ufund.mybatis.dao.IdgStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by li_zhao on 2017/12/20.
 */
@Service
public class SysStaffService {
    @Autowired
    private IdgStaffMapper idgStaffMapper;

    public Map<String,Object> queryIdgStaffMap (){
        List<IdgStaff> idgStaffList = idgStaffMapper.selectStaffList();
        Map<String,Object> result = new HashMap<>();
        for(IdgStaff idgStaff:idgStaffList){
            result.put(idgStaff.getStaffid(),idgStaff);
        }
        return result;
    }
}
