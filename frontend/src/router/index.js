 import Vue from 'vue'
 import Router from 'vue-router'
 import store from '@/vuex/store'
 Vue.use(Router)
 import Listpage from '../components/Listpage'
 import Detail from '../components/Detail'
 import Addproject from '../components/Addproject'
 import FundListpage from '../DollarFund/Listpage'
 import FundDetail from '../DollarFund/Detail'
 import FundAddproject from '../DollarFund/Addproject'

 import FundCapitalCallListPage from '../FundCapitalCall/CapitalCallListPage'
 import FundCapitalCallAddPage from '../FundCapitalCall/CapitalCallAddPage'
 import FundCapitalCallEditPage from '../FundCapitalCall/CapitalCallEditPage'
 import FundCapitalCallDetailPage from '../FundCapitalCall/CapitalCallDetailPage'
 import FundFinListPage from '@/page/fundFin/FundFinListPage'
 import FundFinAddPage from '@/page/fundFin/FundFinAddPage'
 import FundDistribution from '@/page/fundDistribution/FundDistribution'
 import LPinformation from '@/page/lpinformation/LPinformation'

 import FundIrrStasticPage from '../FundCapitalCall/FundIrrStasticPage'
 import FundInvestmentOverview from '../FundCapitalCall/FundInvestmentOverview'
 import FundTrackRecord from '../FundCapitalCall/FundTrackRecord'
 import FundGrossCfs from '../FundCapitalCall/FundGrossCfs'


 import SpvList from "../page/spv/spvMember"

 import InvestManage from "@/page/InvestManage/investmanager"
 import CapTable from "@/page/capTable/capTable"
 import FinancialInfo from "@/page/financial/FinancialInformation"

 import Valuation from "@/components/Valuation"
 import ValuationReportOpm from "@/components/subValuation/ValuationReportOpm"
 import ValuationReportEV from "@/components/subValuation/ValuationReportEV"
 import ValuationReport from "@/components/subValuation/ValuationReport"
 import RealizedGainsLosses from "@/Reports/RealizedGainsLosses"

 /* 20180202 添加新表单 */
 import Appendix_2 from "@/Reports/appendix_2"
 import Main_Schedule from "@/Reports/main_Schedule"
 import Main_Schedule_Report from "@/Reports/main_Schedule_Report"
 /* 20180226 添加表单 */
 import Main_Business from "@/Reports/main_Business"
 import Investment_Schedule from "@/Reports/investment_Schedule"
 import Valuation_Committee_Report from "@/Reports/valuation_Committee_Report"
 import White_Book from "@/Reports/white_Book"
 
 /* 2018/3/8 添加表单 */
 import Financial_Report from "@/Reports/Financial_Report"

 import InvestDetail from "@/Reports/InvestDetail"
  var router = new Router({
   routes: [
    {path: '/',redirect:"/listpage"},//portfolio
    {path: '*',redirect:"/listpage"},
    {path: '/listpage',name: 'listpage',component: Listpage},
    {path:'/detail',name:'detail',component: Detail},
    {path:'/addproject',name:'addproject',component: Addproject},
    {path: '/fundlistpage',name: 'fundlistpage',component: FundListpage},//fund
    {path:'/funddetail',name:'funddetail',component: FundDetail},
    {path:'/fundaddproject',name:'fundaddproject',component: FundAddproject},
    {path:'/capitalcalllistpage',name:'capitalcalllistpage',component: FundCapitalCallListPage},//capitalcall
    {path:'/capitalcalladdpage',name:'capitalcalladdpage',component: FundCapitalCallAddPage},
    {path:'/capitalcalleditpage',name:'capitalcalleditpage',component: FundCapitalCallEditPage},
    {path:'/capitalcalldetailpage',name:'capitalcalldetailpage',component: FundCapitalCallDetailPage},
    {path:'/fundFinListPage',name:'fundFinListPage',component: FundFinListPage},//capitalcall
    {path:'/fundFinAddpage',name:'fundFinAddpage',component: FundFinAddPage},

    //fundirr
    {path:'/fundirrstasticpage',name:'fundirrstasticpage',component: FundIrrStasticPage},//    FundCapitalCallListPage
    {path:'/fundinvestoverviewpage',name:'fundinvestoverviewpage',component: FundInvestmentOverview},//
    {path:'/fundtrackrecordpage',name:'fundtrackrecordpage',component: FundTrackRecord},//
    {path:'/fundgrosscfspage',name:'fundgrosscfspage',component: FundGrossCfs},
    {path:'/funddistribution',name:'funddistribution',component:FundDistribution},
    {path:'/lpinformation',name:'lpinformation',component:LPinformation},


    //invest
    {path:'/captable',name:'captable',component:CapTable},

    {path:"/spvlist",name:"spvlist",component:SpvList},//spv
    {path:'/investmanage',name:'investmanage',component:InvestManage},//invest
    {path:'/financialinfo',name:'financialinfo',component:FinancialInfo},
    {path:'/valuation',name:'valuation',component:Valuation},//valuation
    {path:'/valuationreportopm',name:'valuationreportopm',component:ValuationReportOpm},
    {path:'/valuationreportev',name:'valuationreportev',component:ValuationReportEV},
    {path:'/valuationreport',name:'valuationreport',component:ValuationReport},

    {path:'/RealizedGainsLosses',name:'captablereport',component:RealizedGainsLosses},
    {path:'/appendix_2',name:'appendix_2',component:Appendix_2},/* Appendix II */
    {path:'/main_Schedule',name:'main_Schedule',component:Main_Schedule},/* main_Schedule */
    {path:'/main_Schedule_Report',name:'main_Schedule_Report',component:Main_Schedule_Report},/* main_Schedule_Report */
    {path:'/main_Business',name:'main_Business',component:Main_Business},/* main_Business */
    {path:'/investment_Schedule',name:'investment_Schedule',component:Investment_Schedule},/* investment_Schedule */
    {path:'/valuation_Committee_Report',name:'valuation_Committee_Report',component:Valuation_Committee_Report},/* valuation_Committee_Report */
    {path:'/white_Book',name:'white_Book',component:White_Book},/* white_Book */
    {path:'/financial_Report',name:'financial_Report',component:Financial_Report},/* Financial_Report */
    


    {path:'/InvestDetail',name:'InvestDetail',component:InvestDetail},
   ]
 })

export default router