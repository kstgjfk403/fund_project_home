<template>
  <div class="add-project">
    <Header></Header>
    <!-- <h2>添加项目信息</h2> -->


    <el-form :label-position="labelposition" :model="formData" :rules="rules" ref="userform">

      <!--<div style="margin-top:20px;margin-bottom:20px">
          <b style="font-size:18px">NET CASHFLOW FOR </b>
          <el-select v-model="searchData.fundid" placeholder="pls select fund name" style="width:400px" @change="changeFund()">
            <el-option :key="item.fundid"
                       :label="item.fundfullnameeng"
                       :value="item.fundid"
                       v-for="item in fundarrayList.data"></el-option>
          </el-select>
          <p style="font-size:14px">as of {{resultData.dateStr}}; in US$ unless otherwise stated </p>
      </div>-->
      <div style="margin-top:20px;margin-bottom:20px">
        <b style="font-size:18px">NET CASHFLOW FOR </b>
        <!--   <el-form-item label="NET CASHFLOW FOR" >-->
        <el-select v-model="searchData.fundid" placeholder="pls select fund name" style="width:300px" @change="changeFund()">
          <el-option :key="item.fundid"
                     :label="item.fundfullnameeng"
                     :value="item.fundid"
                     v-for="item in fundarrayList.data"></el-option>
        </el-select>
        <p style="margin-top:10px"/>
        <b style="font-size:13px">as of </b>
        <el-select v-model="searchData.quarterid" placeholder="quarter" style="width:100px" @change="changeFund()">
          <el-option :key="item.quarterid"
                     :label="item.quarternameeng"
                     :value="item.quarterid"
                     v-for="item in quarterList"></el-option>
        </el-select>
        <el-select v-model="searchData.yearid" placeholder="year" style="width:90px" @change="changeFund()">
          <el-option :key="item.yearid"
                     :label="item.yearid"
                     :value="item.yearid"
                     v-for="item in yearList"></el-option>
        </el-select>
        <b style="font-size:13px">; in US$ unless otherwise stated </b>
        <!-- 导出excel -->
        <el-button type="primary" size="mini" id="but" @click="exportTab" style="margin-left:20px;">导出Excel</el-button>
        <!-- </el-form-item>-->
      </div>
      
      <!-- 需要导出的表格内容区域 -->
      <div id="excel">
        
        <el-table :data="resultTableData" border style="width: 70%" class="small_table">
          <el-table-column  prop="statisticsName" label=""  width="200" class-name="firstrow">
          </el-table-column>
          <el-table-column  prop="lpStatisticsValue" label="LP Statistics" width="298" class-name="secondrow">
          </el-table-column>
          <el-table-column  prop="lpGpStatisticsValue" label="LP&GP Statistics" width="298">
          </el-table-column>
          
        </el-table>


        
        <div style="margin-top:15px">
        </div>
        <div class="position-container">
          <div class="table-responsive">
            <h3 style="margin-left:-10px">LP Net Cashflow : </h3>
            <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;" title="LP Net Cashflow">
              <thead>

              <tr>
                <th scope="col" >Date
                </th>
                <th scope="col" >Capital Called
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
                <td>{{item.capitalCalledStr.toString()}}</td>
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
                <th scope="col" style="text-align:left">Date
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
        quarterList:[
          { quarterid:'01',
            quarternameeng:'Mar. 31',
          },
          { quarterid:'02',
            quarternameeng:'Jun. 30',
          },
          { quarterid:'03',
            quarternameeng:'Sep. 30',
          },
          { quarterid:'04',
            quarternameeng:'Dec. 31',
          },
        ],
        yearList:[
          { yearid:'2001'},{ yearid:'2002'},{ yearid:'2003'},{ yearid:'2004'},{ yearid:'2005'},{ yearid:'2006'},{ yearid:'2007'},{ yearid:'2008'},{ yearid:'2009'},{ yearid:'2010'},
          { yearid:'2011'},{ yearid:'2012'},{ yearid:'2013'},{ yearid:'2014'},{ yearid:'2015'},{ yearid:'2016'},{ yearid:'2017'},{ yearid:'2018'},{ yearid:'2019'},{ yearid:'2020'},
          { yearid:'2021'},{ yearid:'2022'},{ yearid:'2023'},{ yearid:'2024'},{ yearid:'2025'},{ yearid:'2026'},{ yearid:'2027'},{ yearid:'2028'},{ yearid:'2029'},{ yearid:'2030'},
        ],

       // opreationType:'creatuserform',
        lplistData:'',
        lpandgplistData:'',
        searchData: {
          fundid: 'F0001',
          quarterid: '',
          yearid:'',
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

      this.submitpage(this.searchData);
    },
    methods:{
      exportTab(){
        //var oHtml = document.getElementsByClassName('tableA')[0].outerHTML;
        var oHtml = document.getElementById('excel').outerHTML;
        //console.log(oHtml);        
        var excelHtml = `
          <html>
          <head>
          <meta charset='utf-8' />
          <style>          
          /* #excel .el-table__header-wrapper .el-table__header thead tr th{
            background:#5f87a0;
            color:#FFF;
            height:40px;
            text-align:right;
          } */
          h3{
            color:#2FA4E9;
          }
          table{
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
          }
          .small_table table{
            width:1000px;
          }
          table tr td,table tr th{
            border:0.5px solid #ddd;
            text-align:left;
            padding-left:3em;
          }
          table tr td{            
            height:30px;
            width:30%;
          }
          table tr .firstrow .cell{
            width:50px;
            color:red;
          }
          
          table tr th{
            height:40px; 
            background:#5f87a0;
            color:#FFF;
            height:40px;
          }         
          
          
          #excel .position-container .table-responsive h3{
            color:##2FA4E7;
          }
          #excel .el-table__header-wrapper .el-table__header thead tr th.gutter{
            border:0;
            background:#fff;
          }
          </style>    
          </head>
          <body>
          ${oHtml}
          </body>
          </html>
          `;

        var excelBlob = new Blob([excelHtml], {type: 'application/vnd.ms-excel'})
        // Create一个a标签
        var oA = document.createElement('a');
        // 利用URL.createObjectURL()方法为a元素生成blob URL
        oA.href = URL.createObjectURL(excelBlob);
        // 给文件命名
        oA.download = `FundIrrStasticPage${(new Date()).toLocaleString()}.xls`;
        // 模拟点击
        oA.click();
        // 移除
        oA.remove();
      },
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
          console.log(fundarrayList)
        }).catch(res=>{
        })
      },

      submitpage: function(searchData) {

        //var that = this;
        axioss.reqfundirrstatistics(searchData)
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

          console.log(this.searchData);
          if(this.searchData.fundid!='' && this.searchData.quarterid!='' && this.searchData.yearid!='') {
            this.resultTableData = [{
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
            }];
            this.lplistData = '';
            this.lpandgplistData = '';
            this.submitpage(this.searchData);
          }
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
