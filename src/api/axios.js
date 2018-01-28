import axios from "axios";
import * as method from './method'
export default {
    //获取项目列表
    reqmemberlist(obj){
        return axios.post('/api/admin/proj/list',obj)
    },
    //删除
    reqdelet(id){
        return axios.post('/api/admin/proj/delete/'+id,{portfolioid:id})
    },
    //获取详情页信息
    reqdetails(id){
        return axios.get('/api/admin/proj/entity/'+id)
    },
    //获取字典下拉列表
    reqdroplist(obj){
        return axios.post('/api/sys/dict', obj)
    },
    //添加项目信息
    submitinfo(obj){
        var newobj=method.switchStr(obj)
        console.log(newobj);
        return axios.post("/api/admin/proj/add",newobj);
    },
    //修改项目信息
    modifyinfo(obj){
        var objj=method.switchStr(obj)
        return axios.post('/api/admin/proj/update',objj);
    },
    //获取项目列表2
    reqmemberlist2(obj){
        return axios.post('/api/admin/fund/list',obj)
    },
    //删除2
    reqdelet2(id){
        return axios.post('/api/admin/fund/delete/'+id,{portfolioid:id})
    },
    //获取详情页信息2
    reqdetails2(id){
        return axios.get('/api/admin/fund/entity/'+id)
    },
    //获取字典下拉列表2
    
    //添加项目信息2
    submitinfo2(obj){
        return axios.post("/api/admin/fund/add",obj);
    },
    //修改项目信息2
    modifyinfo2(obj){
        //var objj=method.switchStr(obj)
        return axios.post('/api/admin/fund/update',obj);
    },
    //获取spv树形列表;
    reqSpvList(){
        return axios.get('/api/admin/spv/tree');
    },
    //获取整个spv列表。
    reqSpvallList(obj){
        return axios.post("/api/admin/spv/list",obj);
    },
    //获取spv单个信息;
    querySpv(id){
        return axios.get('/api/admin/spv/entity/'+id);
    },
    //添加spv
    addSpv(obj){
        return axios.post('/api/admin/spv/add',obj);
    },
    //修改spv列表.
    updateSpv(obj){
        return axios.post('/api/admin/spv/update',obj);
    },
    //删除spv列表数据。
    deleteSpv(id){
        return axios.post('/api/admin/spv/delete/'+id,{spvid:id})
    },
    //获取股东信息列表
    reqShareList(id){
        return axios.get('/api/admin/spv/investor/list/'+id)
    },
    //更新股东认缴信息
    updateShare(obj){
        return axios.post('/api/admin/spv/investor/update',obj)
    },
    //按变更日期删除股东信息
    deleteShare(obj){
        return axios.post('/api/admin/spv/investor/delete',obj)
    },
    //shareholder下拉列表
    reqSharedrop(obj){
        return axios.post('/api/sys/dict', obj)
    },
    //查询股东信息
    queryShare(obj){
        return axios.post('/api/admin/spv/investor/sublist',obj)
    },
    //按portfolioId获取借款列表 GET
    reqLoanList(id){
        return axios.get('/api/admin/proj/invest/note/list/'+id)
    },
    //查询单个借款
    queryLoanSingal(id){
        return axios.get('/api/admin/proj/invest/note/entity/'+id)
    },
    //新建借款
    addLoan(obj){
        return axios.post('/api/admin/proj/invest/note/add/',obj)
    },
    //获取select的下拉列表
    reqSelectList(obj){
        return axios.post('/api/sys/dict', obj)
    },
    //update
    updateLoan(obj){
        return axios.post('/api/admin/proj/invest/note/update/',obj)
    },
    //删除贷款
    deletLoan(id){
        return axios.post("/api/admin/proj/invest/note/delete/"+id,{noteid:id})
    },
    //获取investType下拉列表
    invesDropList(obj){
        return axios.post('/api/sys/dict', obj)
    },
    //获取invest列表
    reqinvestList(id){
        return axios.get('/api/admin/proj/invest/equity/list/'+id)
    },
    //新建invest
    addInvest(obj){
        return axios.post('/api/admin/proj/invest/equity/add/',obj)
    },
    //update invest
    updateInvest(obj){
        return axios.post('/api/admin/proj/invest/equity/update/',obj)
    },
    //删除 invest
    deletInvest(id){
        return axios.post("/api/admin/proj/invest/equity/delete/"+id,{eiid:id})
    },
    //获取loantoequity列表
    reqLoanToEquity(obj){
        console.log(obj);
        return axios.post("/api/admin/proj/invest/n2i/list/",obj)
    },
    //按Eiid查询单条记录
    querySingalData(id){
        return axios.get('/api/admin/proj/invest/equity/entity/'+id)
    },
    //captable  
    //1.新增股东
    addShareHolder(obj){
        return axios.post('/api/admin/proj/captable/investor/add',obj)
    },
    reqCaptableList(obj){
        return axios.post('/api/admin/proj/captable/investor/list',obj)
    },
    //查询 captable 单个数据，会获取到一个列表。同上
    //Exit
    //获取下拉列表数据，同上。
    //Exit
    reqExitShareList(obj){
        return axios.post('/api/admin/proj/exit/share/list',obj)
    },
    //新建exit;
    addExit(obj){
        return axios.post('/api/admin/proj/exit/add',obj)
    },
    //获取exit列表数据
    reqExitList(id){
        return axios.get('/api/admin/proj/exit/list/'+id)
    },
    querySingal(id){
        return axios.get('/api/admin/proj/exit/entity/'+id)
    },
    updateExit(obj){
        return axios.post('/api/admin/proj/exit/update',obj)
    },
    deleteExit(id){
        return axios.post('/api/admin/proj/exit/delete/'+id,{id:id})
    },
    //valuation
    //获取valuation 下拉列表的数据
    reqselectlist(){
        return axios.get('/api/sys/fundfamilyList');
    },
    //请求fund
    reqFundFamilyName(id){
        return axios.get('/api/sys/fund/'+id);
    },
    //提交数据
    reqValList(obj){
        return axios.post('/api/admin/valuation/list',obj)
    }
    //
    
}