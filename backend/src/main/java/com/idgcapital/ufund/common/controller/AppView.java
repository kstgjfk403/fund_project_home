package com.idgcapital.ufund.common.controller;

/**
 * Created by yi_zhang on 2017/11/14.
 */
public class AppView {

    public interface BaseResult {}
    public interface Page {}

    //admin
    //portfolio
    public interface PortfolioList extends BaseResult{}
    public interface PortfolioDetail extends  BaseResult{}
    //invest loan
    public interface PortfolioNoteList extends BaseResult{}
    public interface PortfolioNoteDetail extends BaseResult{}

    //investment
    public interface PortfolioEquityInvestmentList extends BaseResult{}
    public interface PortfolioEquityInvestmentDetail extends BaseResult{}

    //loan to equity
    public interface PortfolioLoanToEquityList extends BaseResult{}
    public interface PortfolioLoanToEquityDetail extends BaseResult{}

    //exit
    public interface PortfolioExitList extends BaseResult{}
    public interface PortfolioExitDetail extends BaseResult{}
    public interface PortfolioExitShare extends BaseResult{}

    //captable
    public interface PortfolioCapTableList extends BaseResult{}
    public interface PortfolioCapTableDetail extends BaseResult{}

    //warrant
    public interface PortfolioWarrantList extends BaseResult{}
    public interface PortfolioWarrantDetail extends BaseResult{}

    public interface PortfolioDividendBonusList extends BaseResult{}
    public interface PortfolioDividendBonusDetail extends BaseResult{}


    //portfolio director
    public interface PortfolioDirectorList extends BaseResult{}
    public interface PortfolioDirectorDetail extends BaseResult{}


    //spv
    public interface SpvPortfolioList extends  BaseResult{}
    public interface SpvEntityList extends  BaseResult{}

    public interface SpvDetail extends  BaseResult{}
    public interface SpvInvestorDetail extends  BaseResult{}
    //fund

 //   public interface fundList extends  BaseResult{}
 //   public interface fundDetail extends  BaseResult{}
    //capitalCallList
    public interface capitalCallList extends  BaseResult{}
    public interface capitalCallDetail extends  BaseResult{}
    //capitalCallList
    public interface capitalCallLpList extends  BaseResult{}
    public interface capitalCallLpDetail extends  BaseResult{}




    public interface FundForQuery extends  BaseResult{}

    public interface FundList extends  BaseResult{}
    public interface FundDetail extends  BaseResult{}
    //sys
    public interface DictBaseInfo extends BaseResult{}
    public interface MenuList extends BaseResult{}


    //portal
    public interface ProjList extends  BaseResult, Page {}


    //valuation
    public interface ValuationList extends BaseResult{}

    public interface ValuationDetail extends BaseResult{}


    public interface FinList extends  BaseResult, Page {}

    public interface FinReport extends BaseResult{}

}
