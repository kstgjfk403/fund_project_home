import axios from "axios";
import * as method from './method'
export default {
  //获取项目列表
  reqmemberlist(obj){
    return axios.post('/api/admin/proj/list', obj)
  },
  //删除
  reqdelet(id){
    return axios.post('/api/admin/proj/delete/' + id, {portfolioid: id})
  },
  //获取详情页信息
  reqdetails(id){
    return axios.get('/api/admin/proj/entity/' + id)
  },
  //获取字典下拉列表
  reqdroplist(obj){
    return axios.post('/api/sys/dict', obj)
  },
  //添加项目信息
  submitinfo(obj){
    var newobj = method.switchStr(obj)
    console.log(newobj);
    return axios.post("/api/admin/proj/add", newobj);
  },
  //修改项目信息
  modifyinfo(obj){
    var objj = method.switchStr(obj)
    return axios.post('/api/admin/proj/update', objj);
  },
  //获取项目列表2就是基金列表
  reqmemberlist2(obj){
    return axios.post('/api/admin/fund/list', obj);
  },
  //获取基金列表 不分页
  reqfundlistwithoutpage(){
    return axios.get('/api/admin/fund/listwithoutpage');
  },

  reqfundinvestportfolio(id){
    return axios.get('/api/admin/fund/fundinvestportfolios/' + id);
  },
  //删除2
  reqdelet2(id){
    return axios.post('/api/admin/fund/delete/' + id, {portfolioid: id})
  },
  //获取详情页信息2
  reqdetails2(id){
    return axios.get('/api/admin/fund/entity/' + id)
  },

  //获取字典下拉列表2

  //添加项目信息2
  submitinfo2(obj){
    return axios.post("/api/admin/fund/add", obj);
  },
  //修改项目信息2
  modifyinfo2(obj){
    //var objj=method.switchStr(obj)
    return axios.post('/api/admin/fund/update', obj);
  },

  //获取capitalcall列表2
  reqmemberlistcapitalcall(obj){
    return axios.post('/api/admin/fundcapitalcall/list', obj)
  },

  //删除capitalcall
  reqdeletcapitalcall(id){
    return axios.post('/api/admin/fundcapitalcall/delete/' + id, {portfolioid: id})
  },
  //获取capitalcall详情页信息
  reqdetailscapitalcall(id){
    return axios.get('/api/admin/fundcapitalcall/entity/' + id)
  },
  //添加capitalcall信息
  submitinfocapitalcall(obj){
    return axios.post("/api/admin/fundcapitalcall/add", obj);
  },
  //修改capitalcall信息
  modifyinfocapitalcall(obj){
    //var objj=method.switchStr(obj)
    return axios.post('/api/admin/fundcapitalcall/update', obj);
  },


  //获取capitalcalllp列表2
  reqmemberlistcapitalcalllp(obj){
    return axios.post('/api/admin/fundcapitalcalllp/list', obj)
  },

  //删除capitalcalllp
  reqdeletcapitalcalllp(id){
    return axios.post('/api/admin/fundcapitalcalllp/delete/' + id, {portfolioid: id})
  },
  //获取capitalcalllp详情页信息
  reqdetailscapitalcalllp(id){
    return axios.get('/api/admin/fundcapitalcalllp/entity/' + id)
  },
  //添加capitalcalllp信息
  submitinfocapitalcalllp(obj){
    return axios.post("/api/admin/fundcapitalcalllp/add", obj);
  },
  //修改capitalcalllp信息
  modifyinfocapitalcalllp(obj){
    //var objj=method.switchStr(obj)
    return axios.post('/api/admin/fundcapitalcalllp/update', obj);
  },

  //查询fundirrstatistics

    reqfundirrstatistics(obj){
      return axios.post('/api/admin/fundcapitalcall/irrstastic', obj);
    },
    //查询fundinvestmentoverview
    reqfundinvestmentoverview(obj){
      return axios.post('/api/admin/fundcapitalcall/investmentoverview' ,obj);
    },
    //查询reqfundtrackrecord
    reqfundtrackrecord(obj){
      return axios.post('/api/admin/fundcapitalcall/trackrecord',obj);
    },
    //查询reqfundtrackrecord
    reqfundgrosscfs(obj){
      return axios.post('/api/admin/fundcapitalcall/grosscfs',obj);
    },
  //查询reqfundtrackrecord
  reqfundtrackrecord(obj){
    return axios.post('/api/admin/fundcapitalcall/trackrecord', obj);
  },
  //查询reqfundtrackrecord
  reqfundgrosscfs(obj){
    return axios.post('/api/admin/fundcapitalcall/grosscfs', obj);
  },



  //查询reqfundfin
  reqmemberlistfundfin(obj){
    return axios.post('/api/admin/fundfin/list', obj);
  },
  //获取fundfin详情页信息
  reqdetailsfundfin(id){
    return axios.get('/api/admin/fundfin/entity/' + id)
  },

    //修改capitalcall信息
    modifyinfofundfin(obj){
      //var objj=method.switchStr(obj)
      return axios.post('/api/admin/fundfin/update',obj);
    },
    //添加capitalcalllp信息
    submitinfofundfin(obj){
      return axios.post("/api/admin/fundfin/add",obj);
    },

    //删除capitalcall
    reqdeletefundfin(id){
      return axios.post('/api/admin/fundfin/delete/'+id,{finid:id})
    },
    /**Appendix 2  capitalfund*/
    capitalfundAppendix_2(obj){
        return axios.post("/api/admin/proj/managereport/capitalfund",obj)
    },
    /* cc3 main_Schedule */
    reqMain_Schedule(obj){
        return axios.post("/api/admin/proj/managereport/mainschedule",obj)
    },



  //获取spv树形列表;
  reqSpvList(){
    return axios.get('/api/admin/spv/tree');
  },
  //获取整个spv列表。
  reqSpvallList(obj){
    return axios.post("/api/admin/spv/list", obj);
  },
  //获取spv单个信息;
  querySpv(id){
    return axios.get('/api/admin/spv/entity/' + id);
  },
  //添加spv
  addSpv(obj){
    return axios.post('/api/admin/spv/add', obj);
  },
  //修改spv列表.
  updateSpv(obj){
    return axios.post('/api/admin/spv/update', obj);
  },
  //删除spv列表数据。
  deleteSpv(id){
    return axios.post('/api/admin/spv/delete/' + id, {spvid: id})
  },
  //获取股东信息列表
  reqShareList(id){
    return axios.get('/api/admin/spv/investor/list/' + id)
  },
  //更新股东认缴信息
  updateShare(obj){
    return axios.post('/api/admin/spv/investor/update', obj)
  },
  //按变更日期删除股东信息
  deleteShare(obj){
    return axios.post('/api/admin/spv/investor/delete', obj)
  },
  //shareholder下拉列表
  reqSharedrop(obj){
    return axios.post('/api/sys/dict', obj)
  },
  //查询股东信息
  queryShare(obj){
    return axios.post('/api/admin/spv/investor/sublist', obj)
  },
  //按portfolioId获取借款列表 GET
  reqLoanList(id){
    return axios.get('/api/admin/proj/invest/note/list/' + id)
  },
  //查询单个借款
  queryLoanSingal(id){
    return axios.get('/api/admin/proj/invest/note/entity/' + id)
  },
  //新建借款
  addLoan(obj){
    return axios.post('/api/admin/proj/invest/note/add/', obj)
  },
  //获取select的下拉列表
  reqSelectList(obj){
    return axios.post('/api/sys/dict', obj)
  },
  //update
  updateLoan(obj){
    return axios.post('/api/admin/proj/invest/note/update/', obj)
  },
  //删除贷款
  deletLoan(id){
    return axios.post("/api/admin/proj/invest/note/delete/" + id, {noteid: id})
  },
  //获取investType下拉列表
  invesDropList(obj){
    return axios.post('/api/sys/dict', obj)
  },
  //获取invest列表
  reqinvestList(id){
    return axios.get('/api/admin/proj/invest/equity/list/' + id)
  },
  //新建invest
  addInvest(obj){
    return axios.post('/api/admin/proj/invest/equity/add/', obj)
  },
  //update invest
  updateInvest(obj){
    return axios.post('/api/admin/proj/invest/equity/update/', obj)
  },
  //删除 invest
  deletInvest(id){
    return axios.post("/api/admin/proj/invest/equity/delete/" + id, {eiid: id})
  },
  //获取loantoequity列表
  reqLoanToEquity(obj){
    console.log(obj);
    return axios.post("/api/admin/proj/invest/n2i/list/", obj)
  },
  //按Eiid查询单条记录
  querySingalData(id){
    return axios.get('/api/admin/proj/invest/equity/entity/' + id)
  },
  //captable
  //1.新增股东
  addShareHolder(obj){
    return axios.post('/api/admin/proj/captable/investor/add', obj)
  },
  reqCaptableList(obj){
    return axios.post('/api/admin/proj/captable/investor/list', obj)
  },
  //1.获取表头
  reqTableHead(portfolioid){
    return axios.get('/api/admin/proj/captable/list/header/' + portfolioid);
  },
  //api/admin/proj/captable/list/content/{portfolioid}
  reqTableContent(portfolioid){
    return axios.get('/api/admin/proj/captable/list/content/' + portfolioid);
  },
  //查询 captable 单个数据，会获取到一个列表。同上
  //Exit
  //获取下拉列表数据，同上。

  reqExitShareList(obj){
    return axios.post('/api/admin/proj/exit/share/list', obj)
  },
  //新建exit;
  addExit(obj){
    return axios.post('/api/admin/proj/exit/add', obj)
  },
  //获取exit列表数据
  reqExitList(id){
    return axios.get('/api/admin/proj/exit/list/' + id)
  },
  querySingal(id){
    return axios.get('/api/admin/proj/exit/entity/' + id)
  },
  updateExit(obj){
    return axios.post('/api/admin/proj/exit/update', obj)
  },
  deleteExit(id){
    return axios.post('/api/admin/proj/exit/delete/' + id, {id: id})
  },
  //valuation
  //获取valuation 下拉列表的数据
  reqselectlist(){
    return axios.get('/api/sys/fundfamilyList');
  },
  //请求fund
  reqFundFamilyName(id){
    return axios.get('/api/sys/fund/' + id);
  },
  //提交数据
  reqValList(obj){
    return axios.post('/api/admin/valuation/list', obj)
  },
  //获得基金下面的项目列表
  reqfundproList(id){
    return axios.get('/api/admin/fund/fundinvestportfolios/' + id)
  },
  //获得估值方法
  reqValueMethod(){
    return axios.get('/api/admin/valuation/method')
  },
  //获得基础指标和占比
  reqIndiction(obj){
    return axios.post('/api/admin/valuation/queryindivalue', obj)
  },
  //获得可比公司列表
  reqCompareList(obj){
    return axios.post('/api/admin/valuation/comparable', obj)
  },
  //最新一轮估值法
  ///api/admin/valuation/querylastround
  queryLastLRound(obj){
    return axios.post('/api/admin/valuation/querylastround', obj)
  },
  //增加可比公司
  addCompareCompany(obj){
    return axios.post('/api/admin/valuation/comparable/add', obj)
  },
  addvaluation(obj){
    return axios.post('/api/admin/valuation/add', obj)
  },

  updateValuation(obj){
    return axios.post('/api/admin/valuation/update', obj)
  },


  //估值报告接口
  valuationReport(valuationid){
    return axios.get('/api/admin/valuation/generatereport/' + valuationid)
  },
  //删除可比公司
  deleteComparable(cid){
    return axios.get('/api/admin/valuation/comparable/del/' + cid);
  },
  //删除valuation
  deleteValuation(valuationid){
    return axios.post('/api/admin/valuation/del', {valuationid: valuationid})
  },

  readValuation(valuationid){
    return axios.post('/api/admin/valuation/read', {valuationid: valuationid})
  },


  //captable报表展示
  reqCaptableGainloss(obj){
    return axios.post('/api/admin/proj/managereport/gainloss', obj)
  },
  reqCaptableGainlossSum(obj){
    return axios.post('/api/admin/proj/managereport/gainlosssum', obj)
  },
  reqCaptalInvestdetail(obj){
    return axios.post('/api/admin/proj/managereport/investdetail', obj)
  },
  /**Appendix 2  capitalfund*/
  capitalfundAppendix_2(obj){
    return axios.post("/api/admin/proj/managereport/capitalfund", obj)
  }

}

