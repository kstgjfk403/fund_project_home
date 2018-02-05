<template>
  <div class="add-project">
    <Header></Header>
    <!-- <h2>添加项目信息</h2> -->


    <el-form :label-position="labelposition" :model="formData" :rules="rules" ref="userform">

      <div style="margin-top:20px;margin-bottom:20px">
          <b style="font-size:18px">NET CASHFLOW FOR </b>
     <!--   <el-form-item label="NET CASHFLOW FOR" >-->
          <el-select v-model="searchData.fundid" placeholder="pls select fund name" style="width:400px" @change="changeFund()">
            <el-option :key="item.fundid"
                       :label="item.fundfullnameeng"
                       :value="item.fundid"
                       v-for="item in fundarrayList.data"></el-option>
          </el-select>
          <p style="font-size:14px">as of {{resultData.dateStr}}; in US$ unless otherwise stated </p>
       <!-- </el-form-item>-->
      </div>

      <el-table :data="resultTableData" border style="width: 70%" > <!--style="width: 80%"-->
        <el-table-column  prop="statisticsName" label=""  width="200" >
        </el-table-column>
        <el-table-column  prop="lpStatisticsValue" label="LP Statistics" width="298">
        </el-table-column>
        <el-table-column  prop="lpGpStatisticsValue" label="LP&GP Statistics" width="298">
        </el-table-column>

      </el-table>


      <!--<div class="big-base-container">
        <div class="base-info-container">
          <div class="baseinfo-left">
            <h3 style="margin-left:100px">LP Statistics </h3>
            <el-form-item label="IRR" >
              <el-input type="text" step="0.01" v-model="resultData.irrLpStr"></el-input>
            </el-form-item>
            <el-form-item label="MOC" >
              <el-input type="number" step="0.01" v-model="resultData.mocLpStr"></el-input>
            </el-form-item>

            <el-form-item label="DPI" >
              <el-input type="number" step="1" v-model="resultData.dpiLpStr"></el-input>
            </el-form-item>

            <el-form-item label="Total Capital Calls" >
              <el-input type="number" step="1" v-model="resultData.totalCapitalCallsLpStr"></el-input>
            </el-form-item>

            <el-form-item label="Total Distributions" >
              <el-input type="number" step="1" v-model="resultData.totalDistributionsLp"></el-input>
            </el-form-item>

            <el-form-item label="Fund NAV" >
              <el-input type="number" step="1" v-model="resultData.lpNetAssets"></el-input>
            </el-form-item>

            <el-form-item label="GP Carry" >
              <el-input type="number" step="1" v-model="resultData.totalGpCarry"></el-input>
            </el-form-item>
          </div>

          <div class="baseinfo-right">
            <h3 style="margin-left:120px">LP&GP Statistics </h3>
            <el-form-item label="IRR" >
              <el-input type="text" step="0.01" v-model="resultData.irrLpAndGpStr"></el-input>
            </el-form-item>
            <el-form-item label="MOC" >
              <el-input type="number" step="0.01" v-model="resultData.mocLpAndGpStr"></el-input>
            </el-form-item>

            <el-form-item label="DPI" >
              <el-input type="number" step="1" v-model="resultData.dpiLpAndGpStr"></el-input>
            </el-form-item>

            <el-form-item label="Total Capital Calls" >
              <el-input type="number" step="1" v-model="resultData.totalCapitalCallsLpAndGpStr"></el-input>
            </el-form-item>

            <el-form-item label="Total Distributions" >
              <el-input type="number" step="1" v-model="resultData.totalDistributionsLpAndGp"></el-input>
            </el-form-item>

            <el-form-item label="Fund NAV" >
              <el-input type="number" step="1" v-model="resultData.netAssets"></el-input>
            </el-form-item>
          </div>
        </div>
      </div>-->
      <div style="margin-top:15px">
      </div>
      <div class="position-container">
        <div class="table-responsive">
          <h3 style="margin-left:-10px">LP Net Cashflow : </h3>
          <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;" title="LP Net Cashflow">
            <thead>

            <tr>
              <th scope="col">Date
              </th>
              <th scope="col">Capital Called
              </th>
              <th scope="col">Distribution
              </th>
              <th scope="col">GP Carry
              </th>
              <th scope="col">Fund NAV
              </th>
              <th scope="col">Net Cashflows
              </th>
              <th scope="col">Description</th>

            </tr>
            </thead>
            <tbody>
            <tr v-for="item in lplistData">
              <td scope="row" >{{item.date}}</td>
              <td>{{item.capitalCalledStr}}</td>
              <td>{{item.distribution}}</td>
              <td>{{item.gpCarry}}</td>
              <td>{{item.fundNav}}</td>
              <td>{{item.netCashFlowsStr}}</td>

              <td>{{item.description}}</td>
            </tr>
            </tbody>
          </table>
          <!--<div class="pagination-container">
            <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :current-page.sync="pageCurrent" @current-change="handleCurrentChange" style="position:absolute;left:50%;bottom:25px;transform: translate(-50%);"></el-pagination>
          </div>-->
        </div>
      </div>

      <div class="position-container">
        <div class="table-responsive">
          <h3 style="margin-left:-10px">LP&GP Net Cashflow : </h3>
          <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;">
            <thead>
            <tr>
              <th scope="col">Date
              </th>
              <th scope="col">Capital Called
              </th>
              <th scope="col">Distribution
              </th>
              <th scope="col">GP Carry
              </th>
              <th scope="col">Fund NAV
              </th>
              <th scope="col">Net Cashflows
              </th>
              <th scope="col">Description</th>

            </tr>
            </thead>
            <tbody>
            <tr v-for="item in lpandgplistData">
              <td scope="row" >{{item.date}}</td>
              <td>{{item.capitalCalledStr}}</td>
              <td>{{item.distribution}}</td>
              <td>{{item.gpCarry}}</td>
              <td>{{item.fundNav}}</td>
              <td>{{item.netCashFlowsStr}}</td>

              <td>{{item.description}}</td>
            </tr>
            </tbody>
          </table>
          <!--<div class="pagination-container">
            <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :current-page.sync="pageCurrent" @current-change="handleCurrentChange" style="position:absolute;left:50%;bottom:25px;transform: translate(-50%);"></el-pagination>
          </div>-->
        </div>
      </div>

    </el-form>
  </div>
</template>
<script>
  import Header from "../components/common/Header";
  import * as method from "../api/method";
  import axioss from '../api/axios';
  export default {
    name:"fundIrrStastic",
    data:function(){
      return {
        labelposition:"right",
        fundarrayList:'',
        portfolioList:'',
       // opreationType:'creatuserform',
        lplistData:'',
        lpandgplistData:'',
        searchData: {
          fundid: 'F0001',
        },
        resultData: {
          irrLp:'',
          irrLpAndGp: '',
          irrLpStr:'',
          irrLpAndGpStr: '',
          mocLp: '',
          mocLpAndGp:'',
          mocLpStr: '',
          mocLpAndGpStr:'',
          dpiLp:'',
          dpiLpAndGp:'',
          dpiLpStr:'',
          dpiLpAndGpStr:'',
          totalCapitalCallsLp:'',
          totalCapitalCallsLpAndGp:'',
          totalCapitalCallsLpStr:'',
          totalCapitalCallsLpAndGpStr:'',
          totalDistributionsLp:'',
          totalDistributionsLpAndGp:'',
          lpNetAssets:'',
          netAssets:'',
          totalGpCarry:'',
          dateStr:'September 30, 2017'
        },
        resultTableData:[{
          statisticsName: 'IRR',
          lpStatisticsValue: '',
          lpGpStatisticsValue: '',
        }, {
          statisticsName: 'MOC',
          lpStatisticsValue: '',
          lpGpStatisticsValue: '',
        }, {
          statisticsName: 'DPI',
          lpStatisticsValue: '',
          lpGpStatisticsValue: '',
        }, {
          statisticsName: 'Total Capital Calls',
          lpStatisticsValue: '',
          lpGpStatisticsValue: '',
        }, {
          statisticsName: 'Total Distributions',
          lpStatisticsValue: '',
          lpGpStatisticsValue: '',
        }, {
          statisticsName: 'Fund NAV',
          lpStatisticsValue: '',
          lpGpStatisticsValue: '',
        }, {
          statisticsName: 'GP Carry',
          lpStatisticsValue: '',
          lpGpStatisticsValue: '',
        }],
        formData: {
          callid:'',
          fundid: '',
          calleddate: '',
          calledpecentage:'',
          totalcallamount:'',
          calledpurpose:'',
          duedate:'',
          callorder:'',
          createby:'',
          createdate:'',
          approved:'',
        },
        rules: {


        }
      }
    },
    created(){
      var way=this.$route.query.path;
      var callid=this.$route.query.callid;
      //this.classOperation(way,callid);
    },
    mounted:function(){
      //this.requserlist();//获取列表数据
      this.reqfundlist();
      this.reqportfoliolist();
      this.submitpage(this.searchData);
    },
    methods:{

      reqfundlist(){
        /*var enterobj={
         dictArray:'DDL_IncorpLocation,YTD,CURRENCY'
         }
         axioss.reqdroplist(enterobj).then(res=>{
         this.dictarraylist=method.translateFormat(res.data.data);
         console.log(this.dictarraylist)
         }).catch(res=>{
         console.log(res);
         })*/
        axioss.reqfundlistwithoutpage().then(res=>{
          this.fundarrayList=res.data;
        }).catch(res=>{
        })
      },
      reqportfoliolist(){
        /*var enterobj={
         dictArray:'DDL_IncorpLocation,YTD,CURRENCY'
         }
         axioss.reqdroplist(enterobj).then(res=>{
         this.dictarraylist=method.translateFormat(res.data.data);
         console.log(this.dictarraylist)
         }).catch(res=>{
         console.log(res);
         })*/
        axioss.reqfundinvestportfolio(this.searchData.fundid).then(res=>{
          //console.log(123);
          //console.log(res.data);
          this.portfolioList=res.data.data;
          //console.log(this.portfolioList);
        }).catch(res=>{
        })
      },
      submitpage: function(searchData) {

        //var that = this;
        axioss.reqfundirrstatistics(searchData.fundid)
          .then(res => {
            var trans=res.data;
            /*for(var i=0;i<trans.length;i++){
              var num=trans[i].totalcommitmentnum;
              trans[i].totalcommitmentnum=method.toThousands(num);
            }*/
            this.lplistData = trans.data.lpCashFlowList;
            this.lpandgplistData = trans.data.lpAndGpCashFlowList;
            this.resultData = trans.data;
            this.resultTableData[0].lpStatisticsValue = this.resultData.irrLpStr;
            this.resultTableData[0].lpGpStatisticsValue = this.resultData.irrLpAndGpStr;
            this.resultTableData[1].lpStatisticsValue = this.resultData.mocLpStr;
            this.resultTableData[1].lpGpStatisticsValue = this.resultData.mocLpAndGpStr;
            this.resultTableData[2].lpStatisticsValue = this.resultData.dpiLpStr;
            this.resultTableData[2].lpGpStatisticsValue = this.resultData.dpiLpAndGpStr;
            this.resultTableData[3].lpStatisticsValue = this.resultData.totalCapitalCallsLpStr;
            this.resultTableData[3].lpGpStatisticsValue = this.resultData.totalCapitalCallsLpAndGpStr;
            this.resultTableData[4].lpStatisticsValue = this.resultData.totalDistributionsLp;
            this.resultTableData[4].lpGpStatisticsValue = this.resultData.totalDistributionsLpAndGp;
            this.resultTableData[5].lpStatisticsValue = this.resultData.lpNetAssets;
            this.resultTableData[5].lpGpStatisticsValue = this.resultData.netAssets;
            this.resultTableData[6].lpStatisticsValue = this.resultData.totalGpCarry;
            //this.resultTableData[6].lpGpStatisticsValue = this.resultData.totalGpCarry;
          })
          .catch(err => {
          });
      },
      changeFund:function() {
         // console.log("临");
          this.resultData = '';
          this.lplistData = '';
          this.lpandgplistData = '';
          this.submitpage(this.searchData);
      },



    },
    components:{
      Header
    }
  }
</script>
<style scoped>
  .add-project{
    width:1140px;
    margin:0 auto;
  }
  .add-project .big-base-container{
    border:1px solid #ddd;
    box-shadow:0 0 2px #eee;
    border-radius:3px;
  }
  .add-project .el-form-item{
    font-family:'droid';
  }
  .add-project .base-info-container{
    display: flex;
    align-items:flex-start;
    justify-content:space-between;
  }
  .add-project h3{
    font-size:20px;
    margin:15px 0;
  }
  .add-project .baseinfo-left,.add-project .baseinfo-right{
    width:50%;
    padding:10px;
  }
  .add-project h3,.add-project h2{
    padding-left:10px;
  }
  .add-project .el-form-item label,.add-project .el-form-item .el-form-item__content{
    width:50%;
  }
  .table-column{
    width:20%;
  }
  .table-column-long{
    width:40%;
  }
</style>
