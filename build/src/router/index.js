 import Vue from 'vue'
 import Router from 'vue-router'
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
 import ValuationReport from "@/components/subValuation/ValuationReport"
 import CaptableReport from "@/page/capTable/capTableReport/captablereport"

 /* 20180202 添加新表单 */
 import Appendix_2 from "@/page/capTable/capTableReport/appendix_2"
 import Main_Schedule from "@/page/capTable/capTableReport/main_Schedule"

 import InvestDetail from "@/page/capTable/capTableReport/InvestDetail"
 export default new Router({
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




    //invest
    {path:'/captable',name:'captable',component:CapTable},

    {path:"/spvlist",name:"spvlist",component:SpvList},//spv
    {path:'/investmanage',name:'investmanage',component:InvestManage},//invest
    {path:'/financialinfo',name:'financialinfo',component:FinancialInfo},
    {path:'/valuation',name:'valuation',component:Valuation},//valuation
    {path:'/valuationreportopm',name:'valuationreportopm',component:ValuationReportOpm},
    {path:'/valuationreport',name:'valuationreport',component:ValuationReport},

    {path:'/captablereport',name:'captablereport',component:CaptableReport},
    {path:'/appendix_2',name:'appendix_2',component:Appendix_2},/* Appendix II */
    {path:'/main_Schedule',name:'main_Schedule',component:Main_Schedule},/* main_Schedule */
    {path:'/InvestDetail',name:'InvestDetail',component:InvestDetail}
   ]
 })
