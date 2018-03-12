package com.idgcapital.ufund.proj.service;

import com.idgcapital.ufund.model.Portfoliobasicinfo;
import com.idgcapital.ufund.mybatis.dao.PortfoliobasicinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yi_zhang on 2017/12/8.
 */
@Service
public class ProjService {



      @Autowired
      private PortfoliobasicinfoMapper portfoliobasicinfoMapper;



      public List<Portfoliobasicinfo> queryProjInfos(){

          return portfoliobasicinfoMapper.selectAll();
      }

}
