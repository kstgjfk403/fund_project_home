 import Vue from 'vue'
 import Router from 'vue-router'
  Vue.use(Router)
 import Listpage from '../components/Listpage'
 import Detail from '../components/Detail'
 import Addproject from '../components/Addproject'
 import FundListpage from '../DollarFund/Listpage'
 import FundDetail from '../DollarFund/Detail'
 import FundAddproject from '../DollarFund/Addproject'
 import SpvList from "../page/spv/spvMember"
 import InvestManage from "@/page/investManage/investmanager"
 export default new Router({
   routes: [
    //portfolio
    {path: '/',redirect:"/listpage"},
    {path: '*',redirect:"/listpage"},
    {path: '/listpage',name: 'listpage',component: Listpage},
    {path:'/detail',name:'detail',component: Detail},
    {path:'/addproject',name:'addproject',component: Addproject},
    //fund
    {path: '/fundlistpage',name: 'fundlistpage',component: FundListpage},
    {path:'/funddetail',name:'funddetail',component: FundDetail},
    {path:'/fundaddproject',name:'fundaddproject',component: FundAddproject},
    //spv
    {path:"/spvlist",name:"spvlist",component:SpvList},
    //invest
    {path:'/investmanage',name:'investmanage',component:InvestManage}
   ]
 })
