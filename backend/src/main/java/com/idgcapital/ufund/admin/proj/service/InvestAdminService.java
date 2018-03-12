package com.idgcapital.ufund.admin.proj.service;

import com.idgcapital.ufund.common.util.DateUtil;
import com.idgcapital.ufund.common.util.StringUtil;
import com.idgcapital.ufund.model.*;
import com.idgcapital.ufund.mybatis.dao.*;
import com.idgcapital.ufund.security.model.LoginUser;
import com.idgcapital.ufund.sys.service.SysBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by li_zhao on 2018/1/15.
 */
@Service
public class InvestAdminService {
    @Autowired
    private PortfolionoteMapper portfolionoteMapper;
    @Autowired
    private FundbasicinfoMapper fundbasicinfoMapper;
    @Autowired
    private PortfolioequityinvestmentMapper portfolioequityinvestmentMapper;
    @Autowired
    private PortfolioloantoequityMapper portfolioloantoequityMapper;
    @Autowired
    private SysBaseInfoService sysBaseInfoService;
    @Autowired
    private PortfoliosharestructureMapper portfoliosharestructureMapper;
    @Autowired
    private PortfolioexitMapper portfolioexitMapper;
    @Autowired
    private PortfoliocaptablenameMapper portfoliocaptablenameMapper;
    @Autowired
    private PortfoliocaptablevalueMapper portfoliocaptablevalueMapper;
    @Autowired
    private PortfoliocaptablevaluedetailMapper portfoliocaptablevaluedetailMapper;
    @Autowired
    private PortfolioexitdetailMapper portfolioexitdetailMapper;
    @Autowired
    private PortfoliowarrantMapper portfoliowarrantMapper;
    @Autowired
    private PortfoliodividentbonusMapper portfoliodividentbonusMapper;
    @Autowired
    private PortfolioBovMapper portfolioBovMapper;
    @Autowired
    private PortfolioWbstatusMapper portfolioWbstatusMapper;

    /*
       loan
    * */
    public List<Portfolionote> queryPortfolioNoteListByPortfolioId(String portfolioId) {
        List<Portfolionote> portfolionoteList = portfolionoteMapper.selectPortfolioNoteListByPortfolioId(portfolioId);
        return portfolionoteList;
    }
    public List<Portfolioequityinvestment> queryPortfolioEquityListByFundId(String fundId) {
        List<Portfolioequityinvestment> portfolioEquityList = portfolioequityinvestmentMapper.selectPortfolioEquityListByFundId(fundId);
        return portfolioEquityList;
    }


    public Portfolionote queryPortfolioNoteById(String noteId) {
        return portfolionoteMapper.selectByPrimaryKey(noteId);
    }

    //按照fundFamily 获取main与inv的比例
    public Map<String, Fundbasicinfo> getfundPropMap(String fundFamlilyName) {

        BigDecimal mainProp = new BigDecimal("1");
        BigDecimal invProp = new BigDecimal("1");

        Map<String, Fundbasicinfo> fundPropMap = new HashMap<>();

        //按比例拆分 noteNum  fee
        BigDecimal totalCommitment = new BigDecimal("0");
        List<Fundbasicinfo> fundbasicinfoList = fundbasicinfoMapper.selectFundByFamily(fundFamlilyName);
        if (fundbasicinfoList != null && fundbasicinfoList.size() > 0) {
            if (fundbasicinfoList.size() > 1) {
                for (Fundbasicinfo fundbasicinfo : fundbasicinfoList) {
                    totalCommitment = totalCommitment.add(fundbasicinfo.getTotalcommitmentnum());
                }
                for (Fundbasicinfo fundbasicinfo : fundbasicinfoList) {
                    if (fundbasicinfo.getFundname().toLowerCase().contains("main")) {
                        mainProp = fundbasicinfo.getTotalcommitmentnum().divide(totalCommitment, BigDecimal.ROUND_HALF_UP, 4);
                        fundbasicinfo.setFundProp(mainProp);
                        fundPropMap.put("mainProp", fundbasicinfo);
                    } else if (fundbasicinfo.getFundname().toLowerCase().contains("inv")) {
                        invProp = fundbasicinfo.getTotalcommitmentnum().divide(totalCommitment, BigDecimal.ROUND_HALF_UP, 4);
                        fundbasicinfo.setFundProp(invProp);
                        fundPropMap.put("invProp", fundbasicinfo);
                    }
                }
            } else {
                Fundbasicinfo fundbasicinfo = fundbasicinfoList.get(0);
                fundbasicinfo.setFundProp(mainProp);
                fundPropMap.put("mainProp", fundbasicinfo);
            }
        }
        return fundPropMap;
    }

    public int addPortfolioNote(Portfolionote portfolionote, LoginUser loginUser) {

        portfolionote.setNotetype("Bridging Loans");

        //按比例拆分 noteNum  fee
        Map<String, Fundbasicinfo> fundProp = getfundPropMap(portfolionote.getFundfamillyname());
        if (fundProp != null) {
            for (String key : fundProp.keySet()) {
                Fundbasicinfo fundbasicinfo = fundProp.get(key);
                Portfolionote noteClone = (Portfolionote) portfolionote.clone();
                noteClone.setFundid(fundbasicinfo.getFundid());

                String noteId = getPortfolioNoteId();
                noteClone.setNoteid(noteId);
                noteClone.setCreatedby(loginUser.getUserId());
                noteClone.setCreateddate(new Date());
                noteClone.setModifiedby(loginUser.getUserId());
                noteClone.setModifieddate(new Date());

                BigDecimal noteNum = noteClone.getNotenum();
                BigDecimal fee = noteClone.getNoteotherfee();
                noteClone.setNotenum(noteNum.multiply(fundbasicinfo.getFundProp()));
                noteClone.setNoteotherfee(fee.multiply(fundbasicinfo.getFundProp()));

                portfolionoteMapper.insert(noteClone);
            }
        }

        return 1;
    }


    public int updatePortfolioNote(Portfolionote portfolionote, LoginUser loginUser) {
        portfolionote.setModifieddate(new Date());
        portfolionote.setModifiedby(loginUser.getUserId());
        portfolionoteMapper.updateByPrimaryKey(portfolionote);
        return 1;
    }

    public int deletePortfolioNoteById(String noteId) {
        portfolionoteMapper.deleteByPrimaryKey(noteId);
        return 1;
    }

    public String getPortfolioNoteId() {
        String maxPortfolioNoteId = portfolionoteMapper.selectMaxPortfolioNoteId();
        String maxId = maxPortfolioNoteId.substring(maxPortfolioNoteId.indexOf("N") + 1);
        int id = Integer.parseInt(maxId) + 1;
        return "N" + StringUtil.autoCompleteZero(String.valueOf(id), 4);
    }


    /*
    * equity
    * */
    public List<Portfolioequityinvestment> queryPortfolioEquityListByPortfolioId(String portfolioId) {
        List<Portfolioequityinvestment> portfolioequityinvestmentList = portfolioequityinvestmentMapper.selectPortfolioEquityListByPortfolioId(portfolioId);
        for (Portfolioequityinvestment portfolioequityinvestment : portfolioequityinvestmentList) {
            String securityType = securityTypeFilter(portfolioequityinvestment.getSecuritytypeid());
            portfolioequityinvestment.setSecuritytypeidstr(securityType);
        }
        return portfolioequityinvestmentList;
    }

    public Portfolioequityinvestment queryPortfolioInvestmentEntityById(String eiid) {
        Portfolioequityinvestment portfolioequityinvestment = portfolioequityinvestmentMapper.selectByPrimaryKey(eiid);
        String securityType = securityTypeFilter(portfolioequityinvestment.getSecuritytypeid());
        portfolioequityinvestment.setSecuritytypeidstr(securityType);

        if (portfolioequityinvestment.getInvesttype().contains("Loan To Equity")) {
            List<Portfolioloantoequity> portfolioloantoequityList = portfolioloantoequityMapper.selectLoanToEquityListByEiid(portfolioequityinvestment.getEiid());
            for (Portfolioloantoequity portfolioloantoequity : portfolioloantoequityList) {
                String loanToEquitySecurityType = securityTypeFilter(portfolioequityinvestment.getSecuritytypeid());
                portfolioloantoequity.setSecuritytypeidstr(loanToEquitySecurityType);

                //计算detail里面的剩余可转债
                //获取单笔总债务
                Portfolionote portfolionote = portfolionoteMapper.selectByPrimaryKey(portfolioloantoequity.getNoteid());
                BigDecimal noteNum = portfolionote.getNotenum();
                //获取已转的总和
                BigDecimal loanToTotal = portfolioloantoequityMapper.selectByNoteId(portfolioloantoequity.getNoteid());

                portfolioloantoequity.setRemainderamount(noteNum.subtract(loanToTotal));
                portfolioloantoequity.setFundname(portfolioequityinvestment.getFundname());

            }
            portfolioequityinvestment.setPortfolioloantoequityList(portfolioloantoequityList);
        }

        //获取captable
        Date maxCloseDate = portfoliocaptablevaluedetailMapper.selectMaxCloseDate(portfolioequityinvestment.getPortfolioid());
        Date maxTermSignDate = portfoliocaptablevalueMapper.selectMaxTermSignDate(portfolioequityinvestment.getPortfolioid());
        portfolioequityinvestment.setMaxclosedate(maxCloseDate);


        Portfoliocaptablevaluedetail portfoliocaptablevaluedetail = new Portfoliocaptablevaluedetail();
        portfoliocaptablevaluedetail.setPortfolioid(portfolioequityinvestment.getPortfolioid());
        portfoliocaptablevaluedetail.setRound(portfolioequityinvestment.getRound());
        portfoliocaptablevaluedetail.setTermsigndate(portfolioequityinvestment.getTermsigndate());
        portfoliocaptablevaluedetail.setClosedate(portfolioequityinvestment.getClosedate());

//        List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailMapper.selectCapTableByRound(portfoliocaptablevaluedetail);
//        if (portfoliocaptablevaluedetailList == null || portfoliocaptablevaluedetailList.size() == 0) {
//            if (maxTermSignDate != null && portfolioequityinvestment.getTermsigndate().getTime() > maxTermSignDate.getTime()) {
//                portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailMapper.selectCapTableTotalByLastedDate(portfoliocaptablevaluedetail);
//            } else {
//                portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailMapper.selectCapTableByLastedDate(portfoliocaptablevaluedetail);
//            }
//        }

        List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList = queryCapTableDetailInvestorList(portfoliocaptablevaluedetail);

        BigDecimal defaultVal = new BigDecimal("0");
        for (Portfoliocaptablevaluedetail capTable : portfoliocaptablevaluedetailList) {
            capTable.setSecuritytypeidstr(securityTypeFilter(capTable.getSecuritytypeid()));
        }
        portfolioequityinvestment.setPortfoliocaptablevaluedetailList(portfoliocaptablevaluedetailList);

        return portfolioequityinvestment;
    }

    public int addPortfolioInvestmentEntity(Portfolioequityinvestment portfolioequityinvestment, LoginUser loginUser) {
        portfolioequityinvestment.setCreatedby(loginUser.getUserId());
        portfolioequityinvestment.setCreateddate(new Date());
        portfolioequityinvestment.setModifiedby(loginUser.getUserId());
        portfolioequityinvestment.setModifieddate(new Date());

        BigDecimal defaultVal = new BigDecimal("0");
        if (portfolioequityinvestment.getInvesttype().equals("Warrant Exercise")) {
            Fundbasicinfo fundbasicinfo = fundbasicinfoMapper.selectByPrimaryKey(portfolioequityinvestment.getFundid());

            Portfolioequityinvestment equityClone = (Portfolioequityinvestment) portfolioequityinvestment.clone();
            equityClone.setFundid(fundbasicinfo.getFundid());
            equityClone.setFundname(fundbasicinfo.getFundname());

            String equityId = getPortfolioEquityId();
            equityClone.setEiid(equityId);

            long shareOwnedNo = 0;
            shareOwnedNo = equityClone.getShareownedno();
            BigDecimal fee = equityClone.getOtherfees();
            BigDecimal cost = equityClone.getCost();

            equityClone.setShareownedno(shareOwnedNo);
            equityClone.setOtherfees(fee);
            equityClone.setCost(cost);

            portfolioequityinvestmentMapper.insert(equityClone);

            //插入cap table
            addCapTable4Investment(equityClone, loginUser);
        }
        if (portfolioequityinvestment.getInvesttype().equals("Convert To Equity Interest") ||
                portfolioequityinvestment.getInvesttype().equals("Convert To Equity Investment")) {

            String equityId = getPortfolioEquityId();
            portfolioequityinvestment.setEiid(equityId);
            portfolioequityinvestment.setRound(portfolioequityinvestment.getInvesttype());

            portfolioequityinvestmentMapper.insert(portfolioequityinvestment);
        }

        if (portfolioequityinvestment.getInvesttype().equals("Shares Split")) {

            String equityId = getPortfolioEquityId();
            portfolioequityinvestment.setEiid(equityId);
            portfolioequityinvestment.setRound(portfolioequityinvestment.getInvesttype());

            portfolioequityinvestmentMapper.insert(portfolioequityinvestment);

            addCapTable4ShareSplit(portfolioequityinvestment, loginUser);
        }

        if (portfolioequityinvestment.getInvesttype().equals("None Participated In")) {

            String equityId = getPortfolioEquityId();
            portfolioequityinvestment.setEiid(equityId);
            portfolioequityinvestment.setClosedate(portfolioequityinvestment.getTermsigndate());

            portfolioequityinvestmentMapper.insert(portfolioequityinvestment);

        }


        //按比例拆分 noteNum  fee
        Map<String, Fundbasicinfo> fundProp = getfundPropMap(portfolioequityinvestment.getFundfamillyname());
        if (portfolioequityinvestment.getInvesttype().equals("Equity Interest") ||
                portfolioequityinvestment.getInvesttype().equals("Equity Investment") ||
                portfolioequityinvestment.getInvesttype().equals("Bond")) {
            if (fundProp != null) {
                for (String key : fundProp.keySet()) {
                    Fundbasicinfo fundbasicinfo = fundProp.get(key);
                    Portfolioequityinvestment equityClone = (Portfolioequityinvestment) portfolioequityinvestment.clone();
                    equityClone.setFundid(fundbasicinfo.getFundid());
                    equityClone.setFundname(fundbasicinfo.getFundname());

                    String equityId = getPortfolioEquityId();
                    equityClone.setEiid(equityId);

                    long shareOwnedNo = 0;
                    shareOwnedNo = equityClone.getShareownedno();
                    BigDecimal fee = equityClone.getOtherfees();
                    BigDecimal cost = equityClone.getCost();
                    BigDecimal prop = fundbasicinfo.getFundProp();

                    equityClone.setShareownedno(prop.multiply(new BigDecimal(shareOwnedNo)).longValue());
                    equityClone.setOtherfees(fee == null ? defaultVal : fee.multiply(prop));
                    equityClone.setCost(cost == null ? defaultVal : cost.multiply(prop));

                    portfolioequityinvestmentMapper.insert(equityClone);

                    //插入cap table
                    if (portfolioequityinvestment.getInvesttype().equals("Equity Interest") ||
                            portfolioequityinvestment.getInvesttype().equals("Equity Investment")) {
                        addCapTable4Investment(equityClone, loginUser);
                    }
                }
            }
        } else if (portfolioequityinvestment.getInvesttype().equals("Other Asset")) {
            if (fundProp != null) {
                for (String key : fundProp.keySet()) {
                    Fundbasicinfo fundbasicinfo = fundProp.get(key);
                    Portfolioequityinvestment equityClone = (Portfolioequityinvestment) portfolioequityinvestment.clone();
                    equityClone.setFundid(fundbasicinfo.getFundid());
                    equityClone.setFundname(fundbasicinfo.getFundname());

                    String equityId = getPortfolioEquityId();
                    equityClone.setEiid(equityId);

                    BigDecimal fee = equityClone.getOtherfees();
                    BigDecimal prop = fundbasicinfo.getFundProp();

                    equityClone.setOtherfees(fee == null ? defaultVal : fee.multiply(prop));

                    portfolioequityinvestmentMapper.insert(equityClone);

                }
            }
        } else if (portfolioequityinvestment.getInvesttype().equals("Share Reclassification")) {
            if (fundProp != null) {
                for (String key : fundProp.keySet()) {
                    Fundbasicinfo fundbasicinfo = fundProp.get(key);
                    Portfolioequityinvestment equityClone = (Portfolioequityinvestment) portfolioequityinvestment.clone();
                    equityClone.setFundid(fundbasicinfo.getFundid());
                    equityClone.setFundname(fundbasicinfo.getFundname());

                    String equityId = getPortfolioEquityId();
                    equityClone.setEiid(equityId);

                    Portfolioequityinvestment investment = portfolioequityinvestmentMapper.selectByPrimaryKey(equityClone.getFromeiid());
                    if (investment.getFundid().equals(fundbasicinfo.getFundid())) {
                        portfolioequityinvestmentMapper.insert(equityClone);
                    }
                }
            }
        } else if (portfolioequityinvestment.getInvesttype().equals("Loan To Equity")) {
            if (fundProp != null) {
                for (String key : fundProp.keySet()) {
                    Fundbasicinfo fundbasicinfo = fundProp.get(key);
                    Portfolioequityinvestment equityClone = (Portfolioequityinvestment) portfolioequityinvestment.clone();
                    equityClone.setFundid(fundbasicinfo.getFundid());
                    equityClone.setFundname(fundbasicinfo.getFundname());

                    String equityId = getPortfolioEquityId();
                    equityClone.setEiid(equityId);

                    BigDecimal cost = equityClone.getCost();
                    BigDecimal prop = fundbasicinfo.getFundProp();

                    equityClone.setCost(cost == null ? defaultVal : cost.multiply(prop));

//                    portfolioequityinvestmentMapper.insert(equityClone);

                    //新建债转股
                    addLoanToEuqity(equityId, equityClone, fundbasicinfo, loginUser);

                }
            }
        } else if (portfolioequityinvestment.getInvesttype().equals("Equity Investment&Loan To Equity")) {
            if (fundProp != null) {
                for (String key : fundProp.keySet()) {
                    Fundbasicinfo fundbasicinfo = fundProp.get(key);
                    Portfolioequityinvestment equityClone = (Portfolioequityinvestment) portfolioequityinvestment.clone();
                    equityClone.setFundid(fundbasicinfo.getFundid());
                    equityClone.setFundname(fundbasicinfo.getFundname());

                    String equityId = getPortfolioEquityId();
                    equityClone.setEiid(equityId);

                    long shareOwnedNo = 0;
                    shareOwnedNo = equityClone.getShareownedno();
                    BigDecimal fee = equityClone.getOtherfees();
                    BigDecimal cost = equityClone.getCost();
                    BigDecimal prop = fundbasicinfo.getFundProp();

                    equityClone.setShareownedno(prop.multiply(new BigDecimal(shareOwnedNo)).longValue());
                    equityClone.setOtherfees(fee == null ? defaultVal : fee.multiply(prop));
                    equityClone.setCost(cost == null ? defaultVal : cost.multiply(prop));

                    portfolioequityinvestmentMapper.insert(equityClone);

                    //插入cap table
//                    addCapTable4Investment(equityClone);

                    //新建债转股
                    addLoanToEuqity(equityId, equityClone, fundbasicinfo, loginUser);

                }
            }
        }
        return 1;
    }

    public void addCapTable4Investment(Portfolioequityinvestment portfolioequityinvestment, LoginUser loginUser) {
        //判断有没有当前股东，如果没有则插入，如果有的话，则拿最新captable记录累加
        //查询重复股东
        Portfoliocaptablename portfoliocaptablename = new Portfoliocaptablename();
        portfoliocaptablename.setPortfolioid(portfolioequityinvestment.getPortfolioid());
        portfoliocaptablename.setSecuritytypeid(portfolioequityinvestment.getSecuritytypeid());
        portfoliocaptablename.setShareowner(portfolioequityinvestment.getFundid());
        portfoliocaptablename.setTermsigndate(portfolioequityinvestment.getTermsigndate());
        portfoliocaptablename.setClosedate(portfolioequityinvestment.getClosedate());
        portfoliocaptablename.setRound(portfolioequityinvestment.getRound());
        portfoliocaptablename.setBizid(portfolioequityinvestment.getEiid());
        portfoliocaptablename.setInvesttype(portfolioequityinvestment.getInvesttype());

        portfoliocaptablename.setSharetype("IDG");
        Portfoliocaptablename duplicateCaptableName = portfoliocaptablenameMapper.queryDuplicateCaptableName(portfoliocaptablename);
        if (duplicateCaptableName == null) {
            portfoliocaptablename.setPreshareownedno(Long.valueOf(0));
            portfoliocaptablename.setShareownedno(portfolioequityinvestment.getShareownedno());
            portfoliocaptablename.setAddshareownedno(portfolioequityinvestment.getShareownedno());
            portfoliocaptablename.setCost(portfolioequityinvestment.getCost());
            insertCapTableShareHolder(portfoliocaptablename, loginUser);
        } else {
            portfoliocaptablename.setShareownerid(duplicateCaptableName.getShareownerid());

            Portfoliocaptablevaluedetail portfoliocaptablevaluedetail = new Portfoliocaptablevaluedetail();
            portfoliocaptablevaluedetail.setPortfolioid(portfolioequityinvestment.getPortfolioid());
            portfoliocaptablevaluedetail.setClosedate(portfolioequityinvestment.getClosedate());
            portfoliocaptablevaluedetail.setSharetype("IDG");
            portfoliocaptablevaluedetail.setSecuritytypeid(portfolioequityinvestment.getSecuritytypeid());
            List<Portfoliocaptablevaluedetail> capTableInvestorBeforeList = portfoliocaptablevaluedetailMapper.queryCapTableInvestorBeforeList(portfoliocaptablevaluedetail);

            if (capTableInvestorBeforeList != null && capTableInvestorBeforeList.size() > 0) {
                Portfoliocaptablevaluedetail captable = capTableInvestorBeforeList.get(0);
                long preShare = captable.getShareownedno();
                long share = 0;
                if (portfolioequityinvestment.getShareownedno() != null) {
                    share = portfolioequityinvestment.getShareownedno() + captable.getShareownedno();
                }
                long addShare = portfolioequityinvestment.getShareownedno();

                portfoliocaptablename.setTermsigndate(portfolioequityinvestment.getTermsigndate());
                portfoliocaptablename.setClosedate(portfolioequityinvestment.getClosedate());
                portfoliocaptablename.setRound(portfolioequityinvestment.getRound());
                portfoliocaptablename.setPreshareownedno(preShare);
                portfoliocaptablename.setShareownedno(share);
                portfoliocaptablename.setAddshareownedno(addShare);
                portfoliocaptablename.setCost(portfolioequityinvestment.getCost());

                //插入cap table detail
                addInvestor2CapTableDetail(portfoliocaptablename, loginUser);
                //插入cap table
                addInvestor2CapTable(portfoliocaptablename, loginUser);
            }
        }
        return;
    }

    public void addCapTableInvestmentForBonusShare(Portfoliodividentbonus portfoliodividentbonus, LoginUser loginUser) {
        //判断有没有当前股东，如果没有则插入，如果有的话，则拿最新captable记录累加
        //查询重复股东
        Portfoliocaptablename portfoliocaptablename = new Portfoliocaptablename();
        portfoliocaptablename.setPortfolioid(portfoliodividentbonus.getPortfolioid());
        portfoliocaptablename.setSecuritytypeid(portfoliodividentbonus.getSecuritytypeid());
        portfoliocaptablename.setShareowner(portfoliodividentbonus.getFundid());
        portfoliocaptablename.setTermsigndate(portfoliodividentbonus.getClosedate());
        portfoliocaptablename.setClosedate(portfoliodividentbonus.getClosedate());
        portfoliocaptablename.setRound(portfoliodividentbonus.getRound());
        portfoliocaptablename.setSharetype("IDG");
        Portfoliocaptablename duplicateCaptableName = portfoliocaptablenameMapper.queryDuplicateCaptableName(portfoliocaptablename);
        if (duplicateCaptableName == null) {
            portfoliocaptablename.setPreshareownedno(Long.valueOf(0));
            portfoliocaptablename.setShareownedno(portfoliodividentbonus.getShareownedno());
            portfoliocaptablename.setAddshareownedno(portfoliodividentbonus.getShareownedno());
            portfoliocaptablename.setCost(new BigDecimal(0.00));
            insertCapTableShareHolder(portfoliocaptablename, loginUser);
        } else {
            portfoliocaptablename.setShareownerid(duplicateCaptableName.getShareownerid());

            Portfoliocaptablevaluedetail portfoliocaptablevaluedetail = new Portfoliocaptablevaluedetail();
            portfoliocaptablevaluedetail.setPortfolioid(portfoliodividentbonus.getPortfolioid());
            portfoliocaptablevaluedetail.setClosedate(portfoliodividentbonus.getClosedate());
            portfoliocaptablevaluedetail.setSharetype("IDG");
            portfoliocaptablevaluedetail.setSecuritytypeid(portfoliodividentbonus.getSecuritytypeid());
            List<Portfoliocaptablevaluedetail> capTableInvestorBeforeList = portfoliocaptablevaluedetailMapper.queryCapTableInvestorBeforeList(portfoliocaptablevaluedetail);

            if (capTableInvestorBeforeList != null && capTableInvestorBeforeList.size() > 0) {
                Portfoliocaptablevaluedetail captable = capTableInvestorBeforeList.get(0);
                long preShare = captable.getShareownedno();
                long share = 0;
                if (portfoliodividentbonus.getShareownedno() != null) {
                    share = portfoliodividentbonus.getShareownedno() + captable.getShareownedno();
                }
                long addShare = portfoliodividentbonus.getShareownedno();

                portfoliocaptablename.setTermsigndate(portfoliodividentbonus.getClosedate());
                portfoliocaptablename.setClosedate(portfoliodividentbonus.getClosedate());
                portfoliocaptablename.setRound(portfoliodividentbonus.getRound());
                portfoliocaptablename.setPreshareownedno(preShare);
                portfoliocaptablename.setShareownedno(share);
                portfoliocaptablename.setAddshareownedno(addShare);
                portfoliocaptablename.setCost(new BigDecimal(0.00));

                //插入cap table detail
                addInvestor2CapTableDetail(portfoliocaptablename, loginUser);
                //插入cap table
                addInvestor2CapTable(portfoliocaptablename, loginUser);
            }
        }
        return;
    }


    public void addCapTable4ShareSplit(Portfolioequityinvestment portfolioequityinvestment, LoginUser loginUser) {
        Portfoliocaptablevaluedetail portfoliocaptablevaluedetail = new Portfoliocaptablevaluedetail();
        portfoliocaptablevaluedetail.setPortfolioid(portfolioequityinvestment.getPortfolioid());
        portfoliocaptablevaluedetail.setClosedate(portfolioequityinvestment.getClosedate());
        List<Portfoliocaptablevaluedetail> capTableInvestorBeforeList = portfoliocaptablevaluedetailMapper.queryCapTableInvestorBeforeList(portfoliocaptablevaluedetail);

        for (Portfoliocaptablevaluedetail detail : capTableInvestorBeforeList) {
            Double addShare = detail.getAddshareownedno() * portfolioequityinvestment.getSharesplitrate();
            Double share = detail.getShareownedno() * portfolioequityinvestment.getSharesplitrate();
            Double preShare = detail.getPreshareownedno() * portfolioequityinvestment.getSharesplitrate();
            detail.setAddshareownedno(addShare.longValue());
            detail.setShareownedno(share.longValue());
            detail.setPreshareownedno(preShare.longValue());
            detail.setClosedate(portfolioequityinvestment.getClosedate());
            detail.setTermsigndate(portfolioequityinvestment.getTermsigndate());
            detail.setRound(portfolioequityinvestment.getInvesttype());
            detail.setCreatedby(loginUser.getUserId());
            detail.setCreateddate(new Date());
            detail.setModifiedby(loginUser.getUserId());
            detail.setModifieddate(new Date());

            portfoliocaptablevaluedetailMapper.insert(detail);
        }

        Portfoliocaptablevalue portfoliocaptablevalue = new Portfoliocaptablevalue();
        portfoliocaptablevalue.setPortfolioid(portfolioequityinvestment.getPortfolioid());
        portfoliocaptablevalue.setTermsigndate(portfolioequityinvestment.getTermsigndate());
        portfoliocaptablevalue.setRound(portfolioequityinvestment.getInvesttype());

        portfoliocaptablevalueMapper.deleteTotalCapTable(portfoliocaptablevalue);

        List<Portfoliocaptablevalue> portfoliocaptablevalueList = portfoliocaptablevalueMapper.queryTotalCapTableByDetail(portfoliocaptablevalue);
        for (Portfoliocaptablevalue captable : portfoliocaptablevalueList) {
            captable.setAddshareownedno(captable.getAddshareownedno());
            captable.setShareownedno(captable.getShareownedno());
            captable.setPreshareownedno(captable.getPreshareownedno());
            captable.setTermsigndate(portfolioequityinvestment.getTermsigndate());
            captable.setRound(portfolioequityinvestment.getInvesttype());
            captable.setCreatedby(loginUser.getUserId());
            captable.setCreateddate(new Date());
            captable.setModifiedby(loginUser.getUserId());
            captable.setModifieddate(new Date());
            portfoliocaptablevalueMapper.insert(captable);
        }

        return;
    }


    public int updatePortfolioEquityEntity(Portfolioequityinvestment portfolioequityinvestment, LoginUser loginUser) {
        portfolioequityinvestment.setModifieddate(new Date());
        portfolioequityinvestment.setModifiedby(loginUser.getUserId());

        if (portfolioequityinvestment.getInvesttype().contains("Loan To Equity")) {
            for (Portfolioloantoequity portfolioloantoequity : portfolioequityinvestment.getPortfolioloantoequityList()) {
                portfolioloantoequity.setModifieddate(new Date());
                portfolioloantoequity.setModifiedby(loginUser.getUserId());

                portfolioloantoequityMapper.updateByPrimaryKey(portfolioloantoequity);
            }
        }
        portfolioequityinvestmentMapper.updateByPrimaryKey(portfolioequityinvestment);

        Map<String, Object> inputPara = new HashMap<>();
        inputPara.put("termSignDate", DateUtil.dateToString(portfolioequityinvestment.getTermsigndate(), DateUtil.LONG_DATE_FORMAT));
        inputPara.put("closeDate", DateUtil.dateToString(portfolioequityinvestment.getClosedate(), DateUtil.LONG_DATE_FORMAT));
        inputPara.put("round", portfolioequityinvestment.getRound());
        inputPara.put("portfolioId", portfolioequityinvestment.getPortfolioid());


        //级联更改captable
        modifyAllCaptable4Update(inputPara, portfolioequityinvestment, loginUser);

        return 1;
    }

    public void modifyAllCaptable4Update(Map<String, Object> inputPara, Portfolioequityinvestment portfolioequityinvestment, LoginUser loginUser) {
        //插入cap table detail
        addCapTableDetailValue(inputPara, portfolioequityinvestment.getPortfoliocaptablevaluedetailList(), loginUser, portfolioequityinvestment.getEiid(), portfolioequityinvestment.getInvesttype());
        //插入cap table
        addCapTableValue(inputPara, loginUser, portfolioequityinvestment.getInvesttype());

        List<Portfoliocaptablevaluedetail> newDetailList = portfolioequityinvestment.getPortfoliocaptablevaluedetailList();


        //级联更改历史的captable
        List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailMapper.queryCloseDateHistoryList(portfolioequityinvestment);
        if (portfoliocaptablevaluedetailList != null && portfoliocaptablevaluedetailList.size() > 0) {
            for (Portfoliocaptablevaluedetail portfoliocaptablevaluedetail : portfoliocaptablevaluedetailList) {

                portfoliocaptablevaluedetail.setPortfolioid(portfolioequityinvestment.getPortfolioid());

                List<Portfoliocaptablevaluedetail> detailHistory = queryCapTableDetailInvestorList(portfoliocaptablevaluedetail);

                for (Portfoliocaptablevaluedetail newDetail : newDetailList) {
                    for (Portfoliocaptablevaluedetail oldDetail : detailHistory) {
                        if (newDetail.getShareownerid().intValue() == oldDetail.getShareownerid().intValue()) {
                            if (oldDetail.getPreshareownedno() == 0) {
                                if (newDetail.getPreshareownedno() == oldDetail.getShareownedno()) {
                                    oldDetail.setPreshareownedno(newDetail.getPreshareownedno());
                                    oldDetail.setShareownedno(newDetail.getShareownedno());
                                    oldDetail.setAddshareownedno(newDetail.getAddshareownedno());
                                } else if (newDetail.getPreshareownedno() == 0) {
                                    oldDetail.setPreshareownedno(oldDetail.getShareownedno());
                                    oldDetail.setShareownedno(newDetail.getShareownedno() + oldDetail.getShareownedno());
                                    oldDetail.setAddshareownedno(oldDetail.getShareownedno() - oldDetail.getPreshareownedno());
                                }

                            } else {
                                long shareNoNew = newDetail.getShareownedno();
                                long shareNoOld = (shareNoNew - oldDetail.getPreshareownedno()) + oldDetail.getShareownedno();
                                oldDetail.setPreshareownedno(newDetail.getShareownedno());
                                oldDetail.setShareownedno(shareNoOld);
                            }
                        }
                    }
                }

                Map<String, Object> inputParaHis = new HashMap<>();
                inputParaHis.put("termSignDate", DateUtil.dateToString(portfoliocaptablevaluedetail.getTermsigndate(), DateUtil.LONG_DATE_FORMAT));
                inputParaHis.put("closeDate", DateUtil.dateToString(portfoliocaptablevaluedetail.getClosedate(), DateUtil.LONG_DATE_FORMAT));
                inputParaHis.put("round", portfoliocaptablevaluedetail.getRound());
                inputParaHis.put("portfolioId", portfolioequityinvestment.getPortfolioid());
                //插入cap table detail
                addCapTableDetailValue(inputParaHis, detailHistory, loginUser, detailHistory.get(0).getBizid(), detailHistory.get(0).getInvesttype());
                //插入cap table
                addCapTableValue(inputParaHis, loginUser, detailHistory.get(0).getInvesttype());

                newDetailList = detailHistory;
            }
        }
    }

    //
    public void modifyAllCaptable4Delete(String eiid, LoginUser loginUser) {

        List<Portfoliocaptablevaluedetail> newDetailList = portfoliocaptablevaluedetailMapper.queryCaptableListByEiid(eiid);
        Portfolioequityinvestment portfolioequityinvestment = portfolioequityinvestmentMapper.selectByPrimaryKey(eiid);

        //级联更改历史的captable
        List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailMapper.queryCloseDateHistoryList(portfolioequityinvestment);

        if (portfoliocaptablevaluedetailList != null && portfoliocaptablevaluedetailList.size() > 0) {
            for (Portfoliocaptablevaluedetail portfoliocaptablevaluedetail : portfoliocaptablevaluedetailList) {

                portfoliocaptablevaluedetail.setPortfolioid(portfolioequityinvestment.getPortfolioid());

                List<Portfoliocaptablevaluedetail> detailHistory = queryCapTableDetailInvestorList(portfoliocaptablevaluedetail);

                for (Portfoliocaptablevaluedetail newDetail : newDetailList) {
                    for (Portfoliocaptablevaluedetail oldDetail : detailHistory) {
                        if (newDetail.getShareownerid().intValue() == oldDetail.getShareownerid().intValue()) {
                             oldDetail.setShareownedno(oldDetail.getShareownedno() - newDetail.getShareownedno());
                             oldDetail.setAddshareownedno(oldDetail.getShareownedno() - oldDetail.getPreshareownedno());
                        }
                    }
                }

                Map<String, Object> inputParaHis = new HashMap<>();
                inputParaHis.put("termSignDate", DateUtil.dateToString(portfoliocaptablevaluedetail.getTermsigndate(), DateUtil.LONG_DATE_FORMAT));
                inputParaHis.put("closeDate", DateUtil.dateToString(portfoliocaptablevaluedetail.getClosedate(), DateUtil.LONG_DATE_FORMAT));
                inputParaHis.put("round", portfoliocaptablevaluedetail.getRound());
                inputParaHis.put("portfolioId", portfolioequityinvestment.getPortfolioid());
                //插入cap table detail
                addCapTableDetailValue(inputParaHis, detailHistory, loginUser, detailHistory.get(0).getBizid(), detailHistory.get(0).getInvesttype());
                //插入cap table
                addCapTableValue(inputParaHis, loginUser, detailHistory.get(0).getInvesttype());
            }
        }
        portfoliocaptablevaluedetailMapper.deleteByEiid(eiid);

        List<Portfoliocaptablevaluedetail> detailListByRound = portfoliocaptablevaluedetailMapper.queryDetailListByRound(portfolioequityinvestment);
        if(detailListByRound ==null||detailListByRound.size()==0){
            Portfoliocaptablevalue portfoliocaptablevalue = new Portfoliocaptablevalue();

            portfoliocaptablevalue.setPortfolioid(portfolioequityinvestment.getPortfolioid());
            portfoliocaptablevalue.setTermsigndate(portfolioequityinvestment.getTermsigndate());
            portfoliocaptablevalue.setRound(portfolioequityinvestment.getRound());

            portfoliocaptablevalueMapper.deleteTotalCapTable(portfoliocaptablevalue);
        }

        List<Portfoliocaptablevalue> portfoliocaptablevalueList = portfoliocaptablevaluedetailMapper.queryCaptableListByPortfolioId(portfolioequityinvestment.getPortfolioid());
        if(portfoliocaptablevalueList == null||portfoliocaptablevalueList.size()==0){
            portfoliocaptablenameMapper.deleteByPortfolioId(portfolioequityinvestment.getPortfolioid());
        }
    }


    //captable 维护
    public int addCapTableDetailValue(Map<String, Object> inputPara, List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList, LoginUser loginUser, String bizId, String investType) {
        Date termSignDate = DateUtil.stringtoDate(inputPara.get("termSignDate").toString(), DateUtil.LONG_DATE_FORMAT);
        Date closeDate = DateUtil.stringtoDate(inputPara.get("closeDate").toString(), DateUtil.LONG_DATE_FORMAT);
        String round = inputPara.get("round").toString();
        String portfolioId = inputPara.get("portfolioId").toString();

        if (portfoliocaptablevaluedetailList != null && portfoliocaptablevaluedetailList.size() > 0) {
            //总股数
            long totalShare = 0;
            long totalShareWithOutESOP = 0;
            for (Portfoliocaptablevaluedetail portfoliocaptablevaluedetail : portfoliocaptablevaluedetailList) {
                totalShare += portfoliocaptablevaluedetail.getShareownedno();
                if (!portfoliocaptablevaluedetail.getSharetype().equals("ESOP")) {
                    totalShareWithOutESOP += portfoliocaptablevaluedetail.getShareownedno();
                }
            }
            for (Portfoliocaptablevaluedetail portfoliocaptablevaluedetail : portfoliocaptablevaluedetailList) {
                //新增
                portfoliocaptablevaluedetail.setTermsigndate(termSignDate);
                portfoliocaptablevaluedetail.setClosedate(closeDate);
                portfoliocaptablevaluedetail.setRound(round);

                portfoliocaptablevaluedetail.setBizid(bizId);
                portfoliocaptablevaluedetail.setInvesttype(investType);

                long preShare = 0;
                long addShareNew = 0;
                long shareNoOrigin = portfoliocaptablevaluedetail.getPreshareownedno() + portfoliocaptablevaluedetail.getAddshareownedno();
                if (shareNoOrigin != portfoliocaptablevaluedetail.getShareownedno()) {  //判断share是否有变化
                    addShareNew = portfoliocaptablevaluedetail.getShareownedno() - shareNoOrigin;
                    preShare = shareNoOrigin;
                } else {
                    preShare = portfoliocaptablevaluedetail.getPreshareownedno();
                    addShareNew = portfoliocaptablevaluedetail.getAddshareownedno();
                }


                BigDecimal proper = new BigDecimal("0");
                BigDecimal properWithoutESOP = new BigDecimal("0");
                if (totalShare != 0) {
                    proper = new BigDecimal(portfoliocaptablevaluedetail.getShareownedno()).divide(new BigDecimal(totalShare), 7, BigDecimal.ROUND_HALF_UP);
                }
                if (totalShareWithOutESOP != 0) {
                    properWithoutESOP = new BigDecimal(portfoliocaptablevaluedetail.getShareownedno()).divide(new BigDecimal(totalShareWithOutESOP), 7, BigDecimal.ROUND_HALF_UP);
                }

                portfoliocaptablevaluedetail.setProper(proper);
                portfoliocaptablevaluedetail.setProperwithoutesop(properWithoutESOP);
                portfoliocaptablevaluedetail.setPreshareownedno(preShare);
                portfoliocaptablevaluedetail.setAddshareownedno(addShareNew);

                Portfoliocaptablevaluedetail insertCapTable = portfoliocaptablevaluedetailMapper.selectByPrimaryKey(portfoliocaptablevaluedetail.getDetailcapid());
                if (insertCapTable != null && insertCapTable.getClosedate().equals(closeDate)) {
//                if (portfoliocaptablevaluedetail.getDetailcapid() != null) {
                    portfoliocaptablevaluedetail.setModifieddate(new Date());
                    portfoliocaptablevaluedetail.setModifiedby(loginUser.getUserId());
                    portfoliocaptablevaluedetailMapper.updateByPrimaryKey(portfoliocaptablevaluedetail);
                } else {
                    portfoliocaptablevaluedetail.setModifieddate(new Date());
                    portfoliocaptablevaluedetail.setModifiedby(loginUser.getUserId());
                    portfoliocaptablevaluedetail.setModifiedby(loginUser.getUserId());
                    portfoliocaptablevaluedetail.setModifieddate(new Date());
                    portfoliocaptablevaluedetailMapper.insert(portfoliocaptablevaluedetail);
                }
            }
        }
        return 1;
    }

    //captable 维护
    public int addCapTableValue(Map<String, Object> inputPara, LoginUser loginUser, String investType) {
        BigDecimal defaultVal = new BigDecimal("0");
        Date termSignDate = DateUtil.stringtoDate(inputPara.get("termSignDate").toString(), DateUtil.LONG_DATE_FORMAT);
        Date closeDate = DateUtil.stringtoDate(inputPara.get("closeDate").toString(), DateUtil.LONG_DATE_FORMAT);
        String round = inputPara.get("round").toString();
        String portfolioId = inputPara.get("portfolioId").toString();

        Portfoliocaptablevalue portfoliocaptablevalue = new Portfoliocaptablevalue();

        portfoliocaptablevalue.setPortfolioid(portfolioId);
        portfoliocaptablevalue.setTermsigndate(termSignDate);
        portfoliocaptablevalue.setRound(round);

        portfoliocaptablevalueMapper.deleteTotalCapTable(portfoliocaptablevalue);

        List<Portfoliocaptablevalue> portfoliocaptablevalueList = portfoliocaptablevalueMapper.queryTotalCapTableByDetail(portfoliocaptablevalue);
        BigDecimal totalCost = new BigDecimal("0");
        BigDecimal postMoney = new BigDecimal("0");
        BigDecimal preMoney = new BigDecimal("0");
        long totalShare = 0;
        for (Portfoliocaptablevalue capTable : portfoliocaptablevalueList) {
            totalCost = totalCost.add(capTable.getCost());
            totalShare += capTable.getShareownedno();
        }

        for (Portfoliocaptablevalue capTable : portfoliocaptablevalueList) {
            if (capTable.getSharetype().equals("IDG")) {
                if (totalShare != 0) {
                    BigDecimal proper = new BigDecimal(capTable.getAddshareownedno()).divide(new BigDecimal(totalShare), 7, BigDecimal.ROUND_HALF_UP);
                    if (proper.compareTo(new BigDecimal("0")) != 0) {
                        postMoney = capTable.getCost().divide(proper, 4, BigDecimal.ROUND_HALF_UP);
                    }
                }
            }
        }

        preMoney = postMoney.subtract(totalCost);
        if (preMoney.compareTo(defaultVal) == -1) {
            preMoney = defaultVal;
        }

        for (Portfoliocaptablevalue capTable : portfoliocaptablevalueList) {
            if (capTable.getSharetype().equals("IDG")) {
                capTable.setPremoney(preMoney);
                capTable.setPostmoney(postMoney);
            }
            capTable.setCreateddate(new Date());
            capTable.setModifieddate(new Date());
            capTable.setModifiedby(loginUser.getUserId());
            capTable.setCreatedby(loginUser.getUserId());
            capTable.setInvesttype(investType);
            portfoliocaptablevalueMapper.insert(capTable);
        }

        return 1;
    }


    public String getPortfolioEquityId() {
        String maxPortfolioEquityId = portfolioequityinvestmentMapper.selectMaxPortfolioEquityId();
        String maxId = maxPortfolioEquityId.substring(maxPortfolioEquityId.indexOf("I") + 1);
        int id = Integer.parseInt(maxId) + 1;
        return "EI" + StringUtil.autoCompleteZero(String.valueOf(id), 4);
    }

    public int deletePortfolioEquityById(String equityId, LoginUser loginUser) {
        //删除债转股
        portfolioloantoequityMapper.deleteByEiid(equityId);
        //删除captable
        modifyAllCaptable4Delete(equityId, loginUser);

        portfolioequityinvestmentMapper.deleteByPrimaryKey(equityId);
        return 1;
    }


    public String securityTypeFilter(Integer securityTypeID) {
        DdlBaseInfo ddlBaseInfo = new DdlBaseInfo();
        ddlBaseInfo.setDictArray("DDL_SecurityType");
        List<DdlBaseInfo> ddlBaseInfoList = sysBaseInfoService.querySysDictList(ddlBaseInfo);
        List<Map<String, Object>> ddlMapList = ddlBaseInfoList.get(0).getBaseInfoList();
        for (Map<String, Object> map : ddlMapList) {
            if (securityTypeID != null) {
                if (Integer.parseInt(map.get("baseId").toString()) == securityTypeID) {
                    return map.get("baseName").toString();
                }
            }
        }
        return "";
    }

    /*
    * loan to equity
    * */
    public List<Portfolioloantoequity> queryPortfolioNoteByFundFamilyName(Portfolioloantoequity portfolioloantoequityInput) {
        //获取总贷款
        List<Portfolionote> portfolionoteList = portfolionoteMapper.selectPortfolioNoteListByFundFamilyName(portfolioloantoequityInput);
        //获取已转的贷款
        List<Portfolioloantoequity> portfolioloantoequityList = portfolioloantoequityMapper.selectLoanToEquityListByFundFamilyName(portfolioloantoequityInput);
        //返回
        List<Portfolioloantoequity> returnValList = new ArrayList<>();
        for (Portfolionote portfolionote : portfolionoteList) {
            Portfolioloantoequity returnVal = new Portfolioloantoequity();
            returnVal.setFundfamillyname(portfolioloantoequityInput.getFundfamillyname());
            returnVal.setClosedate(portfolionote.getClosedate());
            for (Portfolioloantoequity portfolioloantoequity : portfolioloantoequityList) {
                if (portfolionote.getClosedate().compareTo(portfolioloantoequity.getClosedate()) == 0) {
                    portfolionote.setReminderamount(portfolionote.getNotenum().subtract(portfolioloantoequity.getConvertamount()));
                    portfolionote.setNoteotherfee(portfolionote.getNoteotherfee().subtract(portfolioloantoequity.getOtherfees()));
                }
            }
            if (portfolionote.getReminderamount() == null) {
                returnVal.setRemainderamount(portfolionote.getNotenum());
            } else {
                returnVal.setRemainderamount(portfolionote.getReminderamount());
            }
            returnVal.setOtherfees(portfolionote.getNoteotherfee());
            returnVal.setConversionratio(new BigDecimal("1"));

            if (returnVal.getRemainderamount().compareTo(new BigDecimal(0)) == 1) {
                returnValList.add(returnVal);
            }
        }
        return returnValList;
    }

    public int addLoanToEuqity(String equityId, Portfolioequityinvestment portfolioequityinvestment, Fundbasicinfo fundbasicinfo, LoginUser loginUser) {
        BigDecimal defaultVal = new BigDecimal("0");
        List<Portfolioloantoequity> portfolioloantoequityList = portfolioequityinvestment.getPortfolioloantoequityList();
        for (Portfolioloantoequity portfolioloantoequity : portfolioloantoequityList) {
            if (portfolioloantoequity.getShareownedno() != null) {
                portfolioloantoequity.setCreatedby(loginUser.getUserId());
                portfolioloantoequity.setModifiedby(loginUser.getUserId());
                portfolioloantoequity.setCreateddate(new Date());
                portfolioloantoequity.setModifieddate(new Date());

                portfolioloantoequity.setEiid(equityId);
                portfolioloantoequity.setFundid(fundbasicinfo.getFundid());
                portfolioloantoequity.setPortfolioid(portfolioequityinvestment.getPortfolioid());
                portfolioloantoequity.setFundname(portfolioequityinvestment.getFundname());

                BigDecimal prop = fundbasicinfo.getFundProp();

                BigDecimal proceeds = portfolioloantoequity.getProceeds() == null ? defaultVal : portfolioloantoequity.getProceeds().multiply(prop);
                long shareOwnedNo = 0;
                shareOwnedNo = prop.multiply(new BigDecimal(portfolioloantoequity.getShareownedno())).longValue();
                BigDecimal additionalCost = portfolioloantoequity.getAdditionalcost() == null ? defaultVal : portfolioloantoequity.getAdditionalcost().multiply(prop);
                BigDecimal totalOtherFees = portfolioloantoequity.getOtherfees() == null ? defaultVal : portfolioloantoequity.getOtherfees().multiply(prop);
                BigDecimal convertAmount = portfolioloantoequity.getConvertamount() == null ? defaultVal : portfolioloantoequity.getConvertamount().multiply(prop);

                Portfolionote convertFromNote = queryPortfolioNoteId4Convert(portfolioloantoequity);

                BigDecimal otherFees = (convertAmount.divide(convertFromNote.getNotenum(), 7, BigDecimal.ROUND_HALF_UP)).multiply(totalOtherFees);


                portfolioloantoequity.setConvertamount(convertAmount);
                portfolioloantoequity.setProceeds(proceeds);
                portfolioloantoequity.setShareownedno(shareOwnedNo);
                portfolioloantoequity.setAdditionalcost(additionalCost);
                portfolioloantoequity.setOtherfees(otherFees);
                portfolioloantoequity.setNoteid(convertFromNote.getNoteid());

                portfolioloantoequityMapper.insert(portfolioloantoequity);

                Portfolioequityinvestment investment = new Portfolioequityinvestment();
                String eiid = getPortfolioEquityId();

                //债转股，每转一笔需要插入一笔投资
                if (portfolioloantoequity.getSecuritytypeid() == 6) {
                    investment.setInvesttype("Equity Interest(Loan To Equity)");
                } else {
                    investment.setInvesttype("Equity Investment(Loan To Equity)");
                }
                investment.setEiid(eiid);
                investment.setPortfolioid(convertFromNote.getPortfolioid());
                investment.setFundid(convertFromNote.getFundid());
                investment.setRound(portfolioequityinvestment.getRound());
                investment.setConvertfromnoteid(convertFromNote.getNoteid());
                investment.setConvertamount(convertAmount);
                investment.setShareownedno(shareOwnedNo);
                investment.setConversionratio(portfolioloantoequity.getConversionratio());
                investment.setShareownedno(shareOwnedNo);
//                investment.setAdditionalcost(portfolioloantoequity.getAdditionalcost());
                investment.setCost(additionalCost.add(otherFees));
                investment.setSecuritytypeid(portfolioloantoequity.getSecuritytypeid());
                investment.setTermsigndate(portfolioequityinvestment.getTermsigndate());
                investment.setClosedate(portfolioequityinvestment.getClosedate());
                investment.setOtherfees(defaultVal);
                investment.setConvertdate(portfolioloantoequity.getClosedate());

                portfolioequityinvestmentMapper.insert(investment);

                //新建captable
                addCapTable4Investment(investment, loginUser);

            }

        }
        return 1;
    }


    public Portfolionote queryPortfolioNoteId4Convert(Portfolioloantoequity portfolioloantoequity) {
        return portfolionoteMapper.queryPortfolioNoteId4Convert(portfolioloantoequity);
    }

    public int addCapTableInvestor(Portfoliocaptablename portfoliocaptablename, LoginUser loginUser) throws Exception {
        //查询重复股东
        Portfoliocaptablename duplicateCaptableName = portfoliocaptablenameMapper.queryDuplicateCaptableName(portfoliocaptablename);
        if (duplicateCaptableName != null) {
            throw new Exception("股东名字重复！");
        }

        portfoliocaptablename.setPreshareownedno(Long.valueOf(0));
        portfoliocaptablename.setShareownedno(Long.valueOf(0));
        portfoliocaptablename.setAddshareownedno(Long.valueOf(0));
        portfoliocaptablename.setCost(new BigDecimal("0"));
        insertCapTableShareHolder(portfoliocaptablename, loginUser);

        return 1;
    }

    private void insertCapTableShareHolder(Portfoliocaptablename portfoliocaptablename, LoginUser loginUser) {
        portfoliocaptablename.setCreateddate(new Date());
        portfoliocaptablename.setCreatedby(loginUser.getUserId());
        portfoliocaptablename.setModifieddate(new Date());
        portfoliocaptablename.setModifiedby(loginUser.getUserId());

        //插入股东表
        portfoliocaptablenameMapper.insert(portfoliocaptablename);
        int shareOwnerId = portfoliocaptablenameMapper.selectMaxId();
        portfoliocaptablename.setShareownerid(shareOwnerId);
        portfoliocaptablename.setBizid(portfoliocaptablename.getBizid());
        portfoliocaptablename.setInvesttype(portfoliocaptablename.getInvesttype());

        //插入cap table detail
        addInvestor2CapTableDetail(portfoliocaptablename, loginUser);
        //插入cap table
        addInvestor2CapTable(portfoliocaptablename, loginUser);
    }

    public int addInvestor2CapTableDetail(Portfoliocaptablename portfoliocaptablename, LoginUser loginUser) {
        //为历史的cap table新建股东
        List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailMapper.selectALLCapTableByRound(portfoliocaptablename.getPortfolioid());
        Date maxCloseDate = portfoliocaptablevaluedetailMapper.selectMaxCloseDate(portfoliocaptablename.getPortfolioid());
        if (portfoliocaptablevaluedetailList != null && portfoliocaptablevaluedetailList.size() > 0) {
            Portfoliocaptablename duplicateShareHolder = portfoliocaptablenameMapper.selectByPrimaryKey(portfoliocaptablename.getShareownerid());
            if (duplicateShareHolder == null) {
                for (Portfoliocaptablevaluedetail portfoliocaptablevaluedetail : portfoliocaptablevaluedetailList) {
                    portfoliocaptablevaluedetail.setCreatedby(loginUser.getUserId());
                    portfoliocaptablevaluedetail.setCreateddate(new Date());
                    portfoliocaptablevaluedetail.setModifiedby(loginUser.getUserId());
                    portfoliocaptablevaluedetail.setModifieddate(new Date());

                    portfoliocaptablevaluedetail.setPreshareownedno(Long.valueOf(0));
                    portfoliocaptablevaluedetail.setShareownedno(Long.valueOf(0));
                    portfoliocaptablevaluedetail.setAddshareownedno(Long.valueOf(0));
                    portfoliocaptablevaluedetail.setCost(new BigDecimal("0"));

                    portfoliocaptablevaluedetail.setSecuritytypeid(portfoliocaptablename.getSecuritytypeid());
                    portfoliocaptablevaluedetail.setShareownerid(portfoliocaptablename.getShareownerid());

                    portfoliocaptablevaluedetailMapper.insert(portfoliocaptablevaluedetail);
                }
            }

        }
        if (maxCloseDate == null || maxCloseDate.getTime() <= portfoliocaptablename.getClosedate().getTime()) {
            //插入新股东到cap table表
            Portfoliocaptablevaluedetail portfoliocaptablevaluedetail = new Portfoliocaptablevaluedetail();
            portfoliocaptablevaluedetail.setPortfolioid(portfoliocaptablename.getPortfolioid());
            portfoliocaptablevaluedetail.setShareownerid(portfoliocaptablename.getShareownerid());
            portfoliocaptablevaluedetail.setRound(portfoliocaptablename.getRound());
            portfoliocaptablevaluedetail.setTermsigndate(portfoliocaptablename.getTermsigndate());

            portfoliocaptablevaluedetail.setPreshareownedno(portfoliocaptablename.getPreshareownedno());
            portfoliocaptablevaluedetail.setShareownedno(portfoliocaptablename.getShareownedno());
            portfoliocaptablevaluedetail.setAddshareownedno(portfoliocaptablename.getAddshareownedno());
            portfoliocaptablevaluedetail.setCost(portfoliocaptablename.getCost());

            portfoliocaptablevaluedetail.setSecuritytypeid(portfoliocaptablename.getSecuritytypeid());
            portfoliocaptablevaluedetail.setCreatedby(loginUser.getUserId());
            portfoliocaptablevaluedetail.setCreateddate(new Date());
            portfoliocaptablevaluedetail.setModifiedby(loginUser.getUserId());
            portfoliocaptablevaluedetail.setModifieddate(new Date());
            portfoliocaptablevaluedetail.setClosedate(portfoliocaptablename.getClosedate());
            portfoliocaptablevaluedetail.setBizid(portfoliocaptablename.getBizid());
            portfoliocaptablevaluedetail.setInvesttype(portfoliocaptablename.getInvesttype());
            portfoliocaptablevaluedetailMapper.insert(portfoliocaptablevaluedetail);
        }
        return 1;
    }

    public int addInvestor2CapTable(Portfoliocaptablename portfoliocaptablename, LoginUser loginUser) {
        //为历史的cap table新建股东
        List<Portfoliocaptablevalue> portfoliocaptablevalueList = portfoliocaptablevalueMapper.selectALLCapTableByRound(portfoliocaptablename.getPortfolioid());
        Date maxTermSignDate = portfoliocaptablevalueMapper.selectMaxTermSignDate(portfoliocaptablename.getPortfolioid());
        if (portfoliocaptablevalueList != null && portfoliocaptablevalueList.size() > 0) {
            Portfoliocaptablename duplicateShareHolder = portfoliocaptablenameMapper.selectByPrimaryKey(portfoliocaptablename.getShareownerid());
            if (duplicateShareHolder == null) {
                for (Portfoliocaptablevalue portfoliocaptablevalue : portfoliocaptablevalueList) {
                    portfoliocaptablevalue.setCreatedby(loginUser.getUserId());
                    portfoliocaptablevalue.setCreateddate(new Date());
                    portfoliocaptablevalue.setModifiedby(loginUser.getUserId());
                    portfoliocaptablevalue.setModifieddate(new Date());

                    portfoliocaptablevalue.setPreshareownedno(Long.valueOf(0));
                    portfoliocaptablevalue.setShareownedno(Long.valueOf(0));
                    portfoliocaptablevalue.setAddshareownedno(Long.valueOf(0));
                    portfoliocaptablevalue.setCost(new BigDecimal("0"));

                    portfoliocaptablevalue.setSecuritytypeid(portfoliocaptablename.getSecuritytypeid());
                    portfoliocaptablevalue.setPortfolioid(portfoliocaptablename.getPortfolioid());
                    portfoliocaptablevalue.setShareownerid(portfoliocaptablename.getShareownerid());
                    portfoliocaptablevalueMapper.insert(portfoliocaptablevalue);
                }
            }
        }
        if (maxTermSignDate == null || maxTermSignDate.getTime() <= portfoliocaptablename.getTermsigndate().getTime()) {
            //插入新股东到cap table表
            Portfoliocaptablevalue portfoliocaptablevalue = new Portfoliocaptablevalue();
            portfoliocaptablevalue.setPortfolioid(portfoliocaptablename.getPortfolioid());
            portfoliocaptablevalue.setShareownerid(portfoliocaptablename.getShareownerid());
            portfoliocaptablevalue.setRound(portfoliocaptablename.getRound());
            portfoliocaptablevalue.setTermsigndate(portfoliocaptablename.getTermsigndate());

            portfoliocaptablevalue.setPreshareownedno(portfoliocaptablename.getPreshareownedno());
            portfoliocaptablevalue.setShareownedno(portfoliocaptablename.getShareownedno());
            portfoliocaptablevalue.setAddshareownedno(portfoliocaptablename.getAddshareownedno());
            portfoliocaptablevalue.setCost(portfoliocaptablename.getCost());

            portfoliocaptablevalue.setSecuritytypeid(portfoliocaptablename.getSecuritytypeid());
            portfoliocaptablevalue.setCreatedby(loginUser.getUserId());
            portfoliocaptablevalue.setCreateddate(new Date());
            portfoliocaptablevalue.setModifiedby(loginUser.getUserId());
            portfoliocaptablevalue.setModifieddate(new Date());
            portfoliocaptablevalue.setInvesttype(portfoliocaptablename.getInvesttype());
            portfoliocaptablevalueMapper.insert(portfoliocaptablevalue);
        }
        return 1;
    }

    //获取captable列表
    public List<Portfoliocaptablevaluedetail> queryCapTableDetailInvestorList(Portfoliocaptablevaluedetail portfoliocaptablevaluedetail) {
        List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList = new ArrayList<>();
        List<Portfoliocaptablevaluedetail> capTableInvestorBeforeList = portfoliocaptablevaluedetailMapper.queryCapTableInvestorBeforeList(portfoliocaptablevaluedetail);
        List<Portfoliocaptablevaluedetail> capTableInvestorList = portfoliocaptablevaluedetailMapper.selectCapTableByRound(portfoliocaptablevaluedetail);

        if (capTableInvestorList != null && capTableInvestorList.size() > 0) {
            if (capTableInvestorBeforeList != null && capTableInvestorBeforeList.size() > 0) {
                for (Portfoliocaptablevaluedetail now : capTableInvestorList) {

                    for (Portfoliocaptablevaluedetail pre : capTableInvestorBeforeList) {
                        if (now.getShareownerid().intValue() == pre.getShareownerid().intValue() && now.getDetailcapid() == null) {
                            now.setPreshareownedno(pre.getPreshareownedno());
                            now.setShareownedno(pre.getShareownedno());
                            now.setAddshareownedno(pre.getAddshareownedno());
                            now.setCost(pre.getCost());
                            now.setProper(pre.getProper());
                            now.setProperwithoutesop(pre.getProperwithoutesop());
                        }
                    }

                    portfoliocaptablevaluedetailList.add(now);

                }
            } else {
                portfoliocaptablevaluedetailList = capTableInvestorList;
            }
        } else {
            portfoliocaptablevaluedetailList = capTableInvestorBeforeList;
        }

        for (Portfoliocaptablevaluedetail detail : portfoliocaptablevaluedetailList) {
            String securitTypeStr = securityTypeFilter(detail.getSecuritytypeid());
            detail.setSecuritytypeidstr(securitTypeStr);
        }


//        if ((portfoliocaptablevaluedetail.getMaxclosedate() != null &&
//                portfoliocaptablevaluedetail.getMaxclosedate().getTime() < portfoliocaptablevaluedetail.getClosedate().getTime())) {
//            portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailMapper.queryCapTableInvestorBeforeList(portfoliocaptablevaluedetail);
//        } else {
//            portfoliocaptablevaluedetailList = portfoliocaptablevaluedetailMapper.selectCapTableByRound(portfoliocaptablevaluedetail);
//        }
        return portfoliocaptablevaluedetailList;
    }

    //获取captable内容
    public List<Map<String, Object>> queryCapTableList(String portfolioId) {

        List<Portfoliocaptablevalue> hearderList = portfoliocaptablevalueMapper.queryCapTableHeaderList(portfolioId);

        List<Portfoliocaptablename> shareHolderList = portfoliocaptablenameMapper.queryShareHolderList(portfolioId);

        List<Map<String, Object>> capTableContentList = new ArrayList<>();

//        if (shareHolderList != null && shareHolderList.size() > 0) {
//            for(int i=0;i<shareHolderList.size();i++){
//                Portfoliocaptablename portfoliocaptablename = shareHolderList.get(i);
//                Map<String, Object> content = new HashMap<>();
//                content.put("shareownerid",portfoliocaptablename.getShareownerid());
//                content.put("shareowner",portfoliocaptablename.getShareowner());
//                content.put("sharetype",portfoliocaptablename.getSharetype());
//                content.put("securitytypeid",portfoliocaptablename.getSecuritytypeid());
//
//                String securityTypeStr = securityTypeFilter(portfoliocaptablename.getSecuritytypeid());
//                content.put("securitytypeidstr", securityTypeStr);
//
//                capTableContentList.add(content);
//            }
//        }


//        if (hearderList != null && hearderList.size() > 0) {
//            for (int i = 0; i < hearderList.size(); i++) {
//                Portfoliocaptablevalue search = new Portfoliocaptablevalue();
//                search.setPortfolioid(portfolioId);
//                search.setRound(hearderList.get(i).getRound());
//                search.setTermsigndate(hearderList.get(i).getTermsigndate());
//                List<Portfoliocaptablevalue> portfoliocaptablevalueList = portfoliocaptablevalueMapper.queryCapTableList(search);
//
//                if (portfoliocaptablevalueList != null && portfoliocaptablevalueList.size() > 0) {
//                    for (int j = 0; j < portfoliocaptablevalueList.size(); j++) {
//                        Portfoliocaptablevalue portfoliocaptablevalue = portfoliocaptablevalueList.get(j);
//
//                        for (int k = 0; k < capTableContentList.size(); k++) {
//                            Map<String, Object> contentMap = capTableContentList.get(k);
//                            if (Integer.parseInt(contentMap.get("shareownerid").toString()) == portfoliocaptablevalue.getShareownerid()) {
//                                String securityTypeStr = securityTypeFilter(portfoliocaptablevalue.getSecuritytypeid());
//                                contentMap.put("securitytypeidstr", securityTypeStr);
//
//                                contentMap.put("shareownerid",portfoliocaptablevalue.getShareownerid());
//                                contentMap.put("shareowner",portfoliocaptablevalue.getShareowner());
//                                contentMap.put("sharetype",portfoliocaptablevalue.getSharetype());
//                                contentMap.put("securitytypeid",portfoliocaptablevalue.getSecuritytypeid());
//
//                                contentMap.put("shareownedno" + i, portfoliocaptablevalue.getShareownedno());
//                                contentMap.put("cost" + i, portfoliocaptablevalue.getCost());
//                                contentMap.put("proper" + i, portfoliocaptablevalue.getProper());
//                                contentMap.put("properwithoutesop"+i, portfoliocaptablevalue.getProperwithoutesop());
//                                contentMap.put("round"+i, portfoliocaptablevalue.getRound());
//                                contentMap.put("termsigndate"+i, DateUtil.dateToString(portfoliocaptablevalue.getTermsigndate(), DateUtil.LONG_DATE_FORMAT));
//                            }
//                        }
//                    }
//                }
//            }
//        }

        if (hearderList != null && hearderList.size() > 0) {
            for (int i = 0; i < hearderList.size(); i++) {
                Portfoliocaptablevalue search = new Portfoliocaptablevalue();
                search.setPortfolioid(portfolioId);
                search.setRound(hearderList.get(i).getRound());
                search.setTermsigndate(hearderList.get(i).getTermsigndate());
                List<Portfoliocaptablevalue> portfoliocaptablevalueList = portfoliocaptablevalueMapper.queryCapTableList(search);

                Map<String, Object> contentMap = new HashMap<>();
                List<Map<String, Object>> capTableContentDetailList = new ArrayList<>();
                String investType = "";

                if (portfoliocaptablevalueList != null && portfoliocaptablevalueList.size() > 0) {
                    for (int j = 0; j < portfoliocaptablevalueList.size(); j++) {
                        Portfoliocaptablevalue portfoliocaptablevalue = portfoliocaptablevalueList.get(j);

                        Map<String, Object> contentDetailMap = new HashMap<>();

                        String securityTypeStr = securityTypeFilter(portfoliocaptablevalue.getSecuritytypeid());
                        contentDetailMap.put("securitytypeidstr", securityTypeStr);


                        contentDetailMap.put("shareownerid", portfoliocaptablevalue.getShareownerid());
                        contentDetailMap.put("shareowner", portfoliocaptablevalue.getShareowner());
                        contentDetailMap.put("sharetype", portfoliocaptablevalue.getSharetype());
                        contentDetailMap.put("securitytypeid", portfoliocaptablevalue.getSecuritytypeid());

                        contentDetailMap.put("shareownedno", portfoliocaptablevalue.getShareownedno());
                        contentDetailMap.put("cost", portfoliocaptablevalue.getCost());
                        contentDetailMap.put("proper", portfoliocaptablevalue.getProper());
                        contentDetailMap.put("properwithoutesop", portfoliocaptablevalue.getProperwithoutesop());
                        contentDetailMap.put("round", portfoliocaptablevalue.getRound());
                        contentDetailMap.put("termsigndate", DateUtil.dateToString(portfoliocaptablevalue.getTermsigndate(), DateUtil.LONG_DATE_FORMAT));

                        if (portfoliocaptablevalue.getInvesttype() != null && !portfoliocaptablevalue.getInvesttype().equals("")) {
                            investType = portfoliocaptablevalue.getInvesttype();
                        }

                        capTableContentDetailList.add(contentDetailMap);
                    }
                    contentMap.put("baseId", hearderList.get(i).getRound() + " " + DateUtil.dateToString(hearderList.get(i).getTermsigndate(), DateUtil.LONG_DATE_FORMAT));
                    contentMap.put("baseName", capTableContentDetailList);
                    contentMap.put("investType", investType);
                    capTableContentList.add(contentMap);
                }
            }
        }

        return capTableContentList;
    }

    //获取captable header
    public List<Map<String, Object>> queryCapTableHeaderList(String portfolioId) {
        List<Portfoliocaptablevalue> portfoliocaptablevalueList = portfoliocaptablevalueMapper.queryCapTableHeaderList(portfolioId);
        List<Map<String, Object>> queryCapTableHeaderList = new ArrayList<>();
        if (portfoliocaptablevalueList != null) {
            for (int i = 0; i < portfoliocaptablevalueList.size(); i++) {
                Portfoliocaptablevalue portfoliocaptablevalue = portfoliocaptablevalueList.get(i);
                Map<String, Object> header = new HashMap<>();
                header.put("header" + i, portfoliocaptablevalue.getRound() + " " + DateUtil.dateToString(portfoliocaptablevalue.getTermsigndate(), DateUtil.LONG_DATE_FORMAT));
                header.put("round", portfoliocaptablevalue.getRound());
                header.put("termsigndate", DateUtil.dateToString(portfoliocaptablevalue.getTermsigndate(), DateUtil.LONG_DATE_FORMAT));
                queryCapTableHeaderList.add(header);
            }
        }
        return queryCapTableHeaderList;
    }

    //exit----------------------------------
    public List<Portfolioexit> queryPortfolioExitListByPortfolioId(String portfolioId) {
        List<Portfolioexit> portfolioexitList = portfolioexitMapper.selectPortfolioExitListByPortfolioId(portfolioId);
        for (Portfolioexit portfolioexit : portfolioexitList) {
            portfolioexit.setSecuritytypestr(securityTypeFilter(portfolioexit.getSecuritytypeid()));
        }
        return portfolioexitList;
    }

    public Portfolioexit queryPortfolioExitListById(int id) {
        Portfolioexit portfolioexit = portfolioexitMapper.selectByPrimaryKey(id);
        portfolioexit.setSecuritytypestr(securityTypeFilter(portfolioexit.getSecuritytypeid()));

        Portfoliocaptablevaluedetail portfoliocaptablevaluedetail = new Portfoliocaptablevaluedetail();
        portfoliocaptablevaluedetail.setPortfolioid(portfolioexit.getPortfolioid());
        portfoliocaptablevaluedetail.setTermsigndate(portfolioexit.getTermsigndate());
        portfoliocaptablevaluedetail.setClosedate(portfolioexit.getClosedate());
        portfoliocaptablevaluedetail.setRound("Exit");

        List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList = queryCapTableDetailInvestorList(portfoliocaptablevaluedetail);

        for (Portfoliocaptablevaluedetail capTable : portfoliocaptablevaluedetailList) {
            capTable.setSecuritytypeidstr(securityTypeFilter(capTable.getSecuritytypeid()));
        }
        portfolioexit.setPortfoliocaptablevaluedetailList(portfoliocaptablevaluedetailList);

        return portfolioexit;
    }

    public int addPortfolioExitEntity(Portfolioexit portfolioexit, LoginUser loginUser) {
        portfolioexit.setCreatedby(loginUser.getUserId());
        portfolioexit.setCreateddate(new Date());
        portfolioexit.setModifiedby(loginUser.getUserId());
        portfolioexit.setModifieddate(new Date());
        if (portfolioexit.getExittype().equals("Loan")) {
            Map<String, Fundbasicinfo> fundProp = getfundPropMap(portfolioexit.getFundfamillyname());
            if (fundProp != null) {
                for (String key : fundProp.keySet()) {
                    Fundbasicinfo fundbasicinfo = fundProp.get(key);
                    Portfolioexit exitClone = (Portfolioexit) portfolioexit.clone();
                    exitClone.setFundid(fundbasicinfo.getFundid());
                    exitClone.setFundname(fundbasicinfo.getFundname());

                    BigDecimal defaultVal = new BigDecimal("0");
                    BigDecimal shareExit = exitClone.getCostrelization();
                    BigDecimal costRelization = new BigDecimal("0");
                    if (exitClone.getShareexit() != null) {
                        costRelization = new BigDecimal(exitClone.getShareexit());
                    }

                    BigDecimal proceeds = exitClone.getProceeds();
                    BigDecimal prop = fundbasicinfo.getFundProp();

//                    exitClone.setShareexit(shareExit == null?defaultVal:shareExit.multiply(prop));
                    exitClone.setCostrelization(costRelization == null ? defaultVal : costRelization.multiply(prop));
                    exitClone.setProceeds(proceeds == null ? defaultVal : proceeds.multiply(prop));

                    portfolioexitMapper.insert(exitClone);
                }
            }
        } else if (portfolioexit.getExittype().equals("Equity")) {
            Map<String, Fundbasicinfo> fundProp = getfundPropMap(portfolioexit.getFundfamillyname());
            if (fundProp != null) {
                for (String key : fundProp.keySet()) {
                    Fundbasicinfo fundbasicinfo = fundProp.get(key);
                    Portfolioexit exitClone = (Portfolioexit) portfolioexit.clone();
                    exitClone.setFundid(fundbasicinfo.getFundid());
                    exitClone.setFundname(fundbasicinfo.getFundname());

                    BigDecimal defaultVal = new BigDecimal("0");
                    long shareExit = 0;
                    shareExit = exitClone.getShareexit();
                    long shareRemain = 0;
                    shareRemain = exitClone.getShareremain() - shareExit;
                    BigDecimal proceeds = exitClone.getProceeds();
                    BigDecimal prop = fundbasicinfo.getFundProp();

                    BigDecimal proceeds4Fund = proceeds == null ? defaultVal : proceeds.multiply(prop);
                    long shareExit4Fund = prop.multiply(new BigDecimal(shareExit)).longValue();
                    long shareRemain4Fund = prop.multiply(new BigDecimal(shareRemain)).longValue();

                    exitClone.setProceeds(proceeds4Fund);
                    exitClone.setShareexit(shareExit4Fund);
                    exitClone.setShareremain(shareRemain4Fund);

                    //detail
                    List<Portfolioexitdetail> portfolioexitdetailList = new ArrayList<>();
                    //当前价
                    BigDecimal currentPrice = proceeds.divide(new BigDecimal(shareExit), 4, BigDecimal.ROUND_HALF_UP);
                    long share4Exit = 0;
                    share4Exit = exitClone.getShareexit();
                    long shareRemainAfterExit = 0;

                    //计算未实现成本
                    BigDecimal totalCost = new BigDecimal("0");
                    BigDecimal unRealizedCost = new BigDecimal("0");
                    BigDecimal realizedCost = new BigDecimal("0");
                    BigDecimal proceedsRelization = new BigDecimal("0");

                    Portfolioequityinvestment portfolioequityinvestment = new Portfolioequityinvestment();
                    portfolioequityinvestment.setFundid(fundbasicinfo.getFundid());
                    portfolioequityinvestment.setSecuritytypeid(portfolioexit.getSecuritytypeid());
                    portfolioequityinvestment.setPortfolioid(portfolioexit.getPortfolioid());
                    portfolioequityinvestment.setRound(portfolioexit.getRound());
                    List<Portfolioequityinvestment> portfolioequityinvestmentList = portfolioequityinvestmentMapper.queryInvestmentForExit(portfolioequityinvestment);
                    for (Portfolioequityinvestment investment : portfolioequityinvestmentList) {
                        totalCost = totalCost.add(investment.getCost());
                    }

                    for (Portfolioequityinvestment investment : portfolioequityinvestmentList) {
                        //每笔的单价
                        BigDecimal originPrice = investment.getCost().divide(new BigDecimal(investment.getShareownedno()), 4, BigDecimal.ROUND_HALF_UP);

                        Portfolioexitdetail portfolioexitdetail = new Portfolioexitdetail();
                        portfolioexitdetail.setEiid(investment.getEiid());

                        if (investment.getShareownedno() >= share4Exit) {
                            if (share4Exit == 0) {
                                portfolioexitdetail.setShareexit(Long.valueOf(0));
                                portfolioexitdetail.setCostrelization(new BigDecimal("0"));
                                portfolioexitdetail.setCostunrelization(investment.getCost());
                                portfolioexitdetail.setProceedsrelization(new BigDecimal("0"));

                                portfolioexitdetailList.add(portfolioexitdetail);

                            } else {
                                BigDecimal realizedCostDetail = originPrice.multiply(new BigDecimal(share4Exit));

                                BigDecimal proceedsDetail = currentPrice.multiply(new BigDecimal(share4Exit)).
                                        subtract(originPrice.multiply(new BigDecimal(share4Exit)));

                                portfolioexitdetail.setShareexit(share4Exit);
                                portfolioexitdetail.setCostrelization(realizedCostDetail);
                                portfolioexitdetail.setCostunrelization(investment.getCost().subtract(realizedCostDetail));
                                portfolioexitdetail.setProceedsrelization(proceedsDetail);//已实现收益或损失

                                portfolioexitdetailList.add(portfolioexitdetail);

                                share4Exit = 0;
                            }

                        } else {
                            share4Exit = shareExit - investment.getShareownedno();

                            portfolioexitdetail.setShareexit(investment.getShareownedno());
                            portfolioexitdetail.setCostrelization(investment.getCost());
                            portfolioexitdetail.setCostunrelization(new BigDecimal("0"));

                            BigDecimal proceedsDetail = currentPrice.multiply(new BigDecimal(investment.getShareownedno())).
                                    subtract(originPrice.multiply(new BigDecimal(investment.getShareownedno())));
                            portfolioexitdetail.setProceedsrelization(proceedsDetail);

                            portfolioexitdetailList.add(portfolioexitdetail);
                        }
                    }


                    //汇总
                    for (Portfolioexitdetail portfolioexitdetail : portfolioexitdetailList) {
                        realizedCost = realizedCost.add(portfolioexitdetail.getCostrelization());
                        proceedsRelization = proceedsRelization.add(portfolioexitdetail.getProceedsrelization());
                        unRealizedCost = unRealizedCost.add(portfolioexitdetail.getCostunrelization());
                    }

                    exitClone.setCostrelization(realizedCost);
                    exitClone.setCostunrelization(unRealizedCost);
                    exitClone.setProceedsrelization(proceedsRelization);

                    portfolioexitMapper.insert(exitClone);

                    int exitId = portfolioexitMapper.selectMaxExitId();

                    for (Portfolioexitdetail portfolioexitdetail : portfolioexitdetailList) {
                        portfolioexitdetail.setExitid(exitId);
                        portfolioexitdetailMapper.insert(portfolioexitdetail);
                    }

                }
            }
        }
        return 1;
    }

    public int updatePortfolioExitEntity(Portfolioexit portfolioexit, LoginUser loginUser) {
        portfolioexit.setModifieddate(new Date());
        portfolioexit.setModifiedby(loginUser.getUserId());

        Map<String, Object> inputPara = new HashMap<>();
        inputPara.put("termSignDate", DateUtil.dateToString(portfolioexit.getTermsigndate(), DateUtil.LONG_DATE_FORMAT));
        inputPara.put("closeDate", DateUtil.dateToString(portfolioexit.getClosedate(), DateUtil.LONG_DATE_FORMAT));
        inputPara.put("round", "Exit");
        inputPara.put("portfolioId", portfolioexit.getPortfolioid());


        //插入cap table detail
        addCapTableDetailValue(inputPara, portfolioexit.getPortfoliocaptablevaluedetailList(), loginUser, portfolioexit.getId().toString(), "exit");

        //插入cap table
        addCapTableValue(inputPara, loginUser, "exit");

        return portfolioexitMapper.updateByPrimaryKey(portfolioexit);
    }

    public int deletePortfolioExitEntityById(int id) {
        return portfolioexitMapper.deleteByPrimaryKey(id);
    }

    //计算剩余股数
    public List<PortfolioexitShare> queryShareListByFund(PortfolioexitShare portfolioexitShare) {
        List<PortfolioexitShare> portfolioexitShareList = new ArrayList<>();
        if (portfolioexitShare.getFundfamillyname() != null) {
            portfolioexitShareList = portfolioexitMapper.selectShareListByFundFamily(portfolioexitShare);
        }
        if (portfolioexitShare.getFundid() != null) {
            portfolioexitShareList = portfolioexitMapper.selectShareListByFundId(portfolioexitShare);
        }

        for (PortfolioexitShare share : portfolioexitShareList) {
            share.setSecuritytype(securityTypeFilter(share.getSecuritytypeid()));
        }

        return portfolioexitShareList;
    }

    //获取idg postValue   (idgshare/totalshare)*postValue  ,  <=termSignDate
    public BigDecimal queryPostValue(String portfolioId, Date termSignDate) {
        Portfoliocaptablevalue portfoliocaptablevalue = new Portfoliocaptablevalue();
        portfoliocaptablevalue.setPortfolioid(portfolioId);
        portfoliocaptablevalue.setTermsigndate(termSignDate);
        BigDecimal postValue = portfoliocaptablevalueMapper.queryPostValue(portfoliocaptablevalue);
        return postValue;
    }

    //获取 fund 下的idg 所占股比     ,  <=termSignDate
    public BigDecimal queryProperByFundId(String fundId, String portfolioId, Date termSignDate) {
        Portfoliocaptablevalue portfoliocaptablevalue = new Portfoliocaptablevalue();
        portfoliocaptablevalue.setPortfolioid(portfolioId);
        portfoliocaptablevalue.setFundid(fundId);
        portfoliocaptablevalue.setTermsigndate(termSignDate);
        BigDecimal proper = portfoliocaptablevalueMapper.queryProperByFundId(portfoliocaptablevalue);
        return proper;
    }

    //按照fundId,portfolioId获取剩余股数,截止目前为止
    public List<Portfolioexit> queryRemainSharesByFundId(String fundId, String portfolioId, Date currentDate) {
        Portfolioexit portfolioexit = new Portfolioexit();
        portfolioexit.setFundid(fundId);
        portfolioexit.setPortfolioid(portfolioId);
        portfolioexit.setClosedate(currentDate);
        List<Portfolioexit> portfolioexitList = portfolioexitMapper.queryRemainSharesByFundId(portfolioexit);
        return portfolioexitList;
    }

    //按照fundId,portfolioId获取未实现成本,截止目前为止
    public BigDecimal queryUnrealizedCostByFundId(String fundId, String portfolioId, Date currentDate) {
        Portfolioexit portfolioexit = new Portfolioexit();
        portfolioexit.setFundid(fundId);
        portfolioexit.setPortfolioid(portfolioId);
        portfolioexit.setClosedate(currentDate);
        BigDecimal unrealizedCost = portfolioexitMapper.queryUnrealizedCostByFundId(portfolioexit);
        return unrealizedCost;
    }


    //warrant
    public List<Portfoliowarrant> queryPortfolioWarrantListByPortfolioId(String portfolioId) {
        List<Portfoliowarrant> portfoliowarrantList = portfoliowarrantMapper.queryPortfolioWarrantListByPortfolioId(portfolioId);
        for (Portfoliowarrant portfoliowarrant : portfoliowarrantList) {
            String securityType = securityTypeFilter(portfoliowarrant.getSecuritytypeid());
            portfoliowarrant.setSecuritytypeidstr(securityType);
        }
        return portfoliowarrantList;
    }

    public Portfoliowarrant queryPortfolioWarrantEntityById(String warrantId) {
        Portfoliowarrant portfoliowarrant = portfoliowarrantMapper.selectByPrimaryKey(warrantId);
        String securityType = securityTypeFilter(portfoliowarrant.getSecuritytypeid());
        portfoliowarrant.setSecuritytypeidstr(securityType);


        Portfoliocaptablevaluedetail portfoliocaptablevaluedetail = new Portfoliocaptablevaluedetail();
        portfoliocaptablevaluedetail.setPortfolioid(portfoliowarrant.getPortfolioid());
        portfoliocaptablevaluedetail.setTermsigndate(portfoliowarrant.getTermSignDate());
        portfoliocaptablevaluedetail.setClosedate(portfoliowarrant.getIssuedate());

        List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList = queryCapTableDetailInvestorList(portfoliocaptablevaluedetail);

        for (Portfoliocaptablevaluedetail capTable : portfoliocaptablevaluedetailList) {
            capTable.setSecuritytypeidstr(securityTypeFilter(capTable.getSecuritytypeid()));
        }
        portfoliowarrant.setPortfoliocaptablevaluedetailList(portfoliocaptablevaluedetailList);

        return portfoliowarrant;
    }

    public int addPortfolioWarrantEntity(Portfoliowarrant portfoliowarrant, LoginUser loginUser) {
        portfoliowarrant.setCreatedby(loginUser.getUserId());
        portfoliowarrant.setCreateddate(new Date());
        portfoliowarrant.setModifiedby(loginUser.getUserId());
        portfoliowarrant.setModifieddate(new Date());

        //按比例拆分 warrant:amount,shareno
        Map<String, Fundbasicinfo> fundProp = getfundPropMap(portfoliowarrant.getFundfamillyname());
        if (fundProp != null) {
            for (String key : fundProp.keySet()) {
                Fundbasicinfo fundbasicinfo = fundProp.get(key);
                Portfoliowarrant warrantClone = (Portfoliowarrant) portfoliowarrant.clone();
                warrantClone.setFundid(fundbasicinfo.getFundid());

                String warrantId = getPortfolioWarrantId();
                warrantClone.setWarrantid(warrantId);

                warrantClone.setCreatedby(loginUser.getUserId());
                warrantClone.setCreateddate(new Date());
                warrantClone.setModifiedby(loginUser.getUserId());
                warrantClone.setModifieddate(new Date());

                BigDecimal warrantamount = warrantClone.getWarrantamount();
                long shareNo = 0;
                shareNo = warrantClone.getSharesno();
                BigDecimal proper = fundbasicinfo.getFundProp();
                BigDecimal warrantPrice = new BigDecimal("0");

                warrantClone.setWarrantamount(warrantamount.multiply(proper));
                warrantClone.setSharesno(proper.multiply(new BigDecimal(shareNo)).intValue());
                if (shareNo != 0) {
                    warrantPrice = warrantamount.divide(new BigDecimal(shareNo), 7, BigDecimal.ROUND_HALF_UP);
                }
                warrantClone.setWarrantprice(warrantPrice.doubleValue());
                warrantClone.setWarrantownername(fundbasicinfo.getFundname());

                portfoliowarrantMapper.insert(warrantClone);
            }
        }
        return 1;
    }

    public int updatePortfolioWarrantEntity(Portfoliowarrant portfoliowarrant, LoginUser loginUser) {
        portfoliowarrant.setModifiedby(loginUser.getUserId());
        portfoliowarrant.setModifieddate(new Date());
        portfoliowarrantMapper.updateByPrimaryKey(portfoliowarrant);

        Map<String, Object> inputPara = new HashMap<>();
        inputPara.put("termSignDate", DateUtil.dateToString(portfoliowarrant.getTermSignDate(), DateUtil.LONG_DATE_FORMAT));
        inputPara.put("closeDate", DateUtil.dateToString(portfoliowarrant.getIssuedate(), DateUtil.LONG_DATE_FORMAT));
        inputPara.put("round", portfoliowarrant.getRound());
        inputPara.put("portfolioId", portfoliowarrant.getPortfolioid());


        //插入cap table detail
        addCapTableDetailValue(inputPara, portfoliowarrant.getPortfoliocaptablevaluedetailList(), loginUser, portfoliowarrant.getWarrantid(), "warrant");

        //插入cap table
        addCapTableValue(inputPara, loginUser, "warrant");
        return 1;
    }

    //获取warrant list
    public List<Map<String, Object>> queryWarrantRemainByFundFamilyName(Portfoliowarrant portfoliowarrant) {
        List<Portfoliowarrant> portfoliowarrantList = portfoliowarrantMapper.queryWarrantRemainByFundFamilyName(portfoliowarrant);
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        for (Portfoliowarrant warrant : portfoliowarrantList) {
            Map<String, Object> result = new HashMap<>();
            result.put("baseId", warrant.getWarrantid());
            result.put("baseName", warrant.getFundname() + "_" + DateUtil.dateToString(warrant.getIssuedate(), DateUtil.REPORT_DATE_DIR_FORMAT) + "_" + warrant.getRound() + "_" + warrant.getSharesno());
            resultMapList.add(result);
        }
        return resultMapList;
    }

    //获取reclassification列表
    public List<Map<String, Object>> queryReclassificationByFundFamilyName(Portfolioequityinvestment portfolioequityinvestment) {
        List<Portfolioequityinvestment> portfolioequityinvestmentList = portfolioequityinvestmentMapper.queryReclassificationByFundFamilyName(portfolioequityinvestment);
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        for (Portfolioequityinvestment investment : portfolioequityinvestmentList) {
            String securityStr = securityTypeFilter(investment.getSecuritytypeid());

            Map<String, Object> result = new HashMap<>();
            result.put("baseId", investment.getEiid());
            result.put("baseName", investment.getFundname() + "_" + investment.getShareownedno() + "_" + securityStr + "_" + investment.getRound());
            resultMapList.add(result);
        }
        return resultMapList;
    }


    public int deletePortfolioWarrantEntityById(String warrantId) {
        return portfoliowarrantMapper.deleteByPrimaryKey(warrantId);
    }

    public String getPortfolioWarrantId() {
        String maxPortfolioWarrantId = portfoliowarrantMapper.selectMaxPortfolioWarrantId();
        String maxId = maxPortfolioWarrantId.substring(maxPortfolioWarrantId.indexOf("W") + 1);
        int id = Integer.parseInt(maxId) + 1;
        return "W" + StringUtil.autoCompleteZero(String.valueOf(id), 3);
    }


    //dividend  bonusShare
    public List<Portfoliodividentbonus> queryPortfolioDividendListByPortfolioId(String portfolioId) {
        List<Portfoliodividentbonus> portfoliodividentbonusList = portfoliodividentbonusMapper.queryPortfolioDividendListByPortfolioId(portfolioId);
        for (Portfoliodividentbonus portfoliodividentbonus : portfoliodividentbonusList) {
            String securityType = securityTypeFilter(portfoliodividentbonus.getSecuritytypeid());
            portfoliodividentbonus.setSecuritytypestr(securityType);
        }
        return portfoliodividentbonusList;
    }

    //dividend  bonusShare
    public List<Portfoliodividentbonus> queryPortfolioBonusShareListByPortfolioId(String portfolioId) {
        List<Portfoliodividentbonus> portfoliodividentbonusList = portfoliodividentbonusMapper.queryPortfolioBonusShareListByPortfolioId(portfolioId);
        for (Portfoliodividentbonus portfoliodividentbonus : portfoliodividentbonusList) {
            String securityType = securityTypeFilter(portfoliodividentbonus.getSecuritytypeid());
            portfoliodividentbonus.setSecuritytypestr(securityType);
        }
        return portfoliodividentbonusList;
    }

    public List<Portfoliodividentbonus> queryPortfolioBonusListByPortfolioId(String portfolioId) {
        List<Portfoliodividentbonus> portfoliodividentbonusList = portfoliodividentbonusMapper.queryPortfolioBonusShareListByPortfolioId(portfolioId);
        for (Portfoliodividentbonus portfoliodividentbonus : portfoliodividentbonusList) {
            String securityType = securityTypeFilter(portfoliodividentbonus.getSecuritytypeid());
            portfoliodividentbonus.setSecuritytypestr(securityType);
        }
        return portfoliodividentbonusList;
    }

    public Portfoliodividentbonus queryPortfolioDividendBonusEntityById(String dbid) {
        Portfoliodividentbonus portfoliodividentbonus = portfoliodividentbonusMapper.selectByPrimaryKey(dbid);
        String securityType = securityTypeFilter(portfoliodividentbonus.getSecuritytypeid());
        portfoliodividentbonus.setSecuritytypestr(securityType);

        Portfoliocaptablevaluedetail portfoliocaptablevaluedetail = new Portfoliocaptablevaluedetail();
        portfoliocaptablevaluedetail.setPortfolioid(portfoliodividentbonus.getPortfolioid());
        portfoliocaptablevaluedetail.setRound(portfoliodividentbonus.getRound());
        portfoliocaptablevaluedetail.setTermsigndate(portfoliodividentbonus.getClosedate());
        portfoliocaptablevaluedetail.setClosedate(portfoliodividentbonus.getClosedate());

        List<Portfoliocaptablevaluedetail> portfoliocaptablevaluedetailList = queryCapTableDetailInvestorList(portfoliocaptablevaluedetail);

        BigDecimal defaultVal = new BigDecimal("0");
        for (Portfoliocaptablevaluedetail capTable : portfoliocaptablevaluedetailList) {
            capTable.setSecuritytypeidstr(securityTypeFilter(capTable.getSecuritytypeid()));
        }
        portfoliodividentbonus.setPortfoliocaptablevaluedetailList(portfoliocaptablevaluedetailList);


        return portfoliodividentbonus;
    }

    public int addPortfolioDividendBonusEntity(Portfoliodividentbonus portfoliodividentbonus, LoginUser loginUser) {
        String dbId = getPortfolioDividendBonusDbId(portfoliodividentbonus);
        portfoliodividentbonus.setDbid(dbId);
        portfoliodividentbonus.setCreatedby(loginUser.getUserId());
        portfoliodividentbonus.setCreateddate(new Date());
        portfoliodividentbonus.setModifiedby(loginUser.getUserId());
        portfoliodividentbonus.setModifieddate(new Date());
        portfoliodividentbonus.setApproved(true);

        if ("Bonus Shares".equals(portfoliodividentbonus.getInvesttype())) {
            addCapTableInvestmentForBonusShare(portfoliodividentbonus, loginUser);
        }

        return portfoliodividentbonusMapper.insert(portfoliodividentbonus);
    }

    public int updatePortfolioDividendBonusEntity(Portfoliodividentbonus portfoliodividentbonus, LoginUser loginUser) {
        portfoliodividentbonus.setModifiedby(loginUser.getUserId());
        portfoliodividentbonus.setModifieddate(new Date());
        Integer result = portfoliodividentbonusMapper.updateByPrimaryKey(portfoliodividentbonus);

        Map<String, Object> inputPara = new HashMap<>();
        inputPara.put("termSignDate", DateUtil.dateToString(portfoliodividentbonus.getClosedate(), DateUtil.LONG_DATE_FORMAT));
        inputPara.put("closeDate", DateUtil.dateToString(portfoliodividentbonus.getClosedate(), DateUtil.LONG_DATE_FORMAT));
        inputPara.put("round", portfoliodividentbonus.getRound());
        inputPara.put("portfolioId", portfoliodividentbonus.getPortfolioid());
        //插入cap table detail
        addCapTableDetailValue(inputPara, portfoliodividentbonus.getPortfoliocaptablevaluedetailList(), loginUser, portfoliodividentbonus.getDbid(), "bonus");

        //插入cap table
        addCapTableValue(inputPara, loginUser, "bonus");
        return result;
    }

    public int deletePortfolioDividendBonusEntityById(String dbid) {
        return portfoliodividentbonusMapper.deleteByPrimaryKey(dbid);
    }

    public String getPortfolioDividendBonusDbId(Portfoliodividentbonus portfoliodividentbonus) {
        Portfoliodividentbonus paramPortfoliodividentbonus = new Portfoliodividentbonus();
        paramPortfoliodividentbonus.setPortfolioid(portfoliodividentbonus.getPortfolioid());
        String maxCallId = portfoliodividentbonusMapper.selectMaxDbId(paramPortfoliodividentbonus);
        String maxId = "";
        if (maxCallId == null) {
            maxId = "0";
        } else {
            maxId = maxCallId.substring(maxCallId.indexOf("D") + 1);
        }

        int id = Integer.parseInt(maxId) + 1;
        return portfoliodividentbonus.getPortfolioid() + "_D" + StringUtil.autoCompleteZero(String.valueOf(id), 4);
    }

    //Portfolio Bov
    public List<PortfolioBov> queryPortfolioBovListByPortfolioId(String portfolioId) {
        List<PortfolioBov> portfolioBovList = portfolioBovMapper.queryPortfolioBovListByPortfolioId(portfolioId);
        for (PortfolioBov portfolioBov : portfolioBovList) {
            PortfolioWbstatus portfolioWbstatus = new PortfolioWbstatus();
            portfolioWbstatus.setPortfolioid(portfolioBov.getPortfolioid());
            portfolioWbstatus.setWbdate(portfolioBov.getUpdatedate());
            List<PortfolioWbstatus> portfolioWbstatusList = portfolioWbstatusMapper.selectByPortfolioIdAndWbdate(portfolioWbstatus);
            if (portfolioWbstatusList != null && portfolioWbstatusList.size() > 0) {
                portfolioBov.setLastwbstatus(portfolioWbstatusList.get(0).getWbstatus());
                portfolioBov.setWbstatus(portfolioWbstatusList.get(0).getWbstatus());
            }
        }
        return portfolioBovList;
    }

    public int addPortfolioBovEntity(PortfolioBov portfolioBov, LoginUser loginUser) {

        portfolioBov.setCreatedby(loginUser.getUserId());
        portfolioBov.setCreateddate(new Date());
        //portfolioBov.setModifiedby(loginUser.getUserId());
        //portfolioBov.setModifieddate(new Date());
        portfolioBov.setApproved(true);
        Integer result = portfolioBovMapper.insert(portfolioBov);

        PortfolioWbstatus portfolioWbstatus = new PortfolioWbstatus();
        portfolioWbstatus.setApproved(true);
        portfolioWbstatus.setCreatedby(loginUser.getUserId());
        portfolioWbstatus.setCreateddate(new Date());
        //portfolioWbstatus.setModifiedby(loginUser.getUserId());
        //portfolioWbstatus.setModifieddate(new Date());
        portfolioWbstatus.setWbdate(new Date());
        portfolioWbstatus.setPortfolioid(portfolioBov.getPortfolioid());
        portfolioWbstatus.setWbstatus(portfolioBov.getWbstatus());
        portfolioWbstatusMapper.insert(portfolioWbstatus);


        return result;
    }

    public PortfolioBov queryPortfolioBovEntityById(int bovid) {
        PortfolioBov portfolioBov = portfolioBovMapper.selectByPrimaryKey(bovid);
        PortfolioWbstatus portfolioWbstatus = new PortfolioWbstatus();
        portfolioWbstatus.setPortfolioid(portfolioBov.getPortfolioid());
        portfolioWbstatus.setWbdate(portfolioBov.getUpdatedate());
        List<PortfolioWbstatus> portfolioWbstatusList = portfolioWbstatusMapper.selectByPortfolioIdAndWbdate(portfolioWbstatus);
        if (portfolioWbstatusList != null && portfolioWbstatusList.size() > 0) {
            portfolioBov.setLastwbstatus(portfolioWbstatusList.get(0).getWbstatus());
            portfolioBov.setWbstatus(portfolioWbstatusList.get(0).getWbstatus());
        }
        return portfolioBov;
    }

    public int updatePortfolioBovEntity(PortfolioBov portfolioBov, LoginUser loginUser) {
        portfolioBov.setModifiedby(loginUser.getUserId());
        portfolioBov.setModifieddate(new Date());
        Integer result = portfolioBovMapper.updateByPrimaryKey(portfolioBov);
        if ((portfolioBov.getLastwbstatus() == null && portfolioBov.getWbstatus() != null) || !portfolioBov.getLastwbstatus().equals(portfolioBov.getWbstatus())) {
            PortfolioWbstatus portfolioWbstatus = new PortfolioWbstatus();
            portfolioWbstatus.setApproved(true);
            portfolioWbstatus.setCreatedby(loginUser.getUserId());
            portfolioWbstatus.setCreateddate(new Date());
            //portfolioWbstatus.setModifiedby(loginUser.getUserId());
            //portfolioWbstatus.setModifieddate(new Date());
            portfolioWbstatus.setWbdate(portfolioBov.getUpdatedate());
            portfolioWbstatus.setPortfolioid(portfolioBov.getPortfolioid());
            portfolioWbstatus.setWbstatus(portfolioBov.getWbstatus());
            portfolioWbstatusMapper.insert(portfolioWbstatus);
        }

        return result;
    }

    public int deletePortfolioBovEntity(int wbid) {
        return portfolioBovMapper.deleteByPrimaryKey(wbid);
    }


}
