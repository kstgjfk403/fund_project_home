<template>
  <div class="add-project">
    <Header></Header>
    <!-- <h2>添加项目信息</h2> -->


    <el-form :label-position="labelposition"  :rules="rules" ref="userform">

      <div style="margin-top:20px">
        <b style="font-size:18px">TRACK RECORD OF </b>
        <!--   <el-form-item label="NET CASHFLOW FOR" >-->
        <el-select v-model="searchData.fundid" placeholder="pls select fund name" style="width:300px" @change="changeFund()">
          <el-option :key="item.fundid"
                     :label="item.fundfullnameeng"
                     :value="item.fundid"
                     v-for="item in fundarrayList.data"></el-option>
        </el-select>
        <p style="margin-top:10px"/>
        <b style="font-size:13px">as of </b>
        <el-select v-model="searchData.quarterid" placeholder="pls select quarter" style="width:100px" @change="changeFund()">
          <el-option :key="item.quarterid"
                     :label="item.quarternameeng"
                     :value="item.quarterid"
                     v-for="item in quarterList"></el-option>
        </el-select>
        <el-select v-model="searchData.yearid" placeholder="pls select year" style="width:90px" @change="changeFund()">
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


      <div style="margin-top:15px">
      </div>
      
      <!-- 需要导出的表格内容区域 --> 
      <div id="excel">   
      <div class="position-container">
        <div class="table-responsive excel1">
          <el-table :data="summaryListDate" border style="width: 80%" maxHeight="850" >
            <el-table-column fixed prop="overview" label="Overview of Total Investments" width="200">
            </el-table-column>
            <el-table-column  prop="total" label="All" width="190">
            </el-table-column>
            <el-table-column  prop="unrealised" label="Unrealised" width="130">
            </el-table-column>
            <el-table-column  prop="partiallyRealised" label="Partially Realised" width="130">
            </el-table-column>
            <el-table-column  prop="realised" label="Realised" width="130">
            </el-table-column>
            <el-table-column  prop="writtenOff" label="Written Off" width="130">
            </el-table-column>

          </el-table>

        </div>
      </div>
      <div style="margin-top:15px;" class="space">&nbsp;&nbsp;&nbsp;
      </div>
      <div class="position-container">
        <div class="table-responsive excel2">

          <el-table :data="portfoliolistData" border style="width: 100%" maxHeight="850">
            <el-table-column fixed prop="company" label="Company" width="150">
            </el-table-column>
            <el-table-column  prop="status" label="Status" width="150">
            </el-table-column>
            <el-table-column  prop="investedCost" label="Invested Cost" width="100">
            </el-table-column>
            <el-table-column  prop="realisedProceeds" label="Realised Proceeds" width="100">
            </el-table-column>
            <el-table-column  prop="unRealisedProceeds" label="Unrealised Proceeds" width="120">
            </el-table-column>
            <el-table-column  prop="totalValue" label="Total Value" width="150">
            </el-table-column>
            <el-table-column  prop="moc" label="Moc" width="150">
            </el-table-column>
            <el-table-column  prop="irr" label="IRR" width="150">
            </el-table-column>
          </el-table>

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
        portfolioList:'',
        // opreationType:'creatuserform',
        portfoliolistData:[],
        summaryListDate:[],
        lpandgplistData:'',
        searchData: {
          fundid: 'F0001',
          quarterid: '03',
          yearid:'2017',
        },
        resultData: {
          totalInvestedCost :'',
          totalInvestedCostUnrealised :'',
          totalInvestedCostPartRealised :'',
          totalInvestedCostRealised :'',
          totalInvestedCostWrittenOff :'',

          totalDistributions :'',
          totalDistributionsUnrealised :'',
          totalDistributionsPartRealised :'',
          totalDistributionsRealised :'',
          totalDistributionsWrittenOff :'',

          totalUnrealisedDistributions :'',
          totalUnrealisedDistributionsUnrealised :'',
          totalUnrealisedDistributionsPartRealised :'',
          totalUnrealisedDistributionsRealised :'',
          totalUnrealisedDistributionsWrittenOff :'',

          totalValue :'',
          totalValueUnrealised :'',
          totalValuePartRealised :'',
          totalValueRealised :'',
          totalValueWrittenOff :'',
        },

        rules: {


        }
      }
    },
    created(){
      var way=this.$route.query.path;
      var callid=this.$route.query.callid;
    },
    mounted:function(){
      //this.requserlist();//获取列表数据
      this.reqfundlist();

      this.submitpage(this.searchData);
    },
    methods:{
      exportTab(){
        //var oHtml = document.getElementsByClassName('tableA')[0].outerHTML;
        var oHtml1 = document.querySelector(".excel1 .el-table__fixed-header-wrapper").outerHTML;
        var oHtml2 = document.querySelector(".excel1 .el-table__fixed-body-wrapper").outerHTML;
        var oHtmlspace = document.querySelector(".space").outerHTML;
        console.log(oHtmlspace)
        var oHtml3 = document.querySelector(".excel2 .el-table__fixed-header-wrapper").outerHTML;
        var oHtml4 = document.querySelector(".excel2 .el-table__fixed-body-wrapper").outerHTML;
        var oHtml = oHtml1+oHtml2+oHtmlspace+oHtml3+oHtml4;
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
          .space{
            font-size:30px;
          }
          table{
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
          }
          table tr:nth-child(odd){
            background:red;
          }
          table tr .firstrow{
            text-align:left;
          }
         
          table tr td{
            border:0.5px solid #ddd;
            height:auto;
            padding:3em;
          }
         
          table tr th{
            border:0.5px solid #ddd;
            text-align:left;
            padding:3em;
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
        oA.download = `FundTrackRecord${(new Date()).toLocaleString()}.xls`;
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
        }).catch(res=>{
        })
      },

      submitpage: function(searchData) {

        //var that = this;
        axioss.reqfundtrackrecord(searchData)
          .then(res => {
            var trans=res.data;

            this.resultData = trans.data;
            console.log(this.resultData);
            this.portfoliolistData = trans.data.portfolioTrackRecordList;

            this.summaryListDate =[
              {
                overview: 'Total Invested Cost',
                total: this.resultData.totalInvestedCost,
                unrealised: this.resultData.totalInvestedCostUnrealised,
                partiallyRealised: this.resultData.totalInvestedCostPartRealised,
                realised: this.resultData.totalInvestedCostRealised,
                writtenOff: this.resultData.totalInvestedCostWrittenOff,
              }, {
                overview: 'Total Distributions',
                total: this.resultData.totalDistributions,
                unrealised: this.resultData.totalDistributionsUnrealised,
                partiallyRealised: this.resultData.totalDistributionsPartRealised,
                realised: this.resultData.totalDistributionsRealised,
                writtenOff: this.resultData.totalDistributionsWrittenOff,
              }, {
                overview: 'Total Fair Value',
                total: this.resultData.totalUnrealisedDistributions,
                unrealised: this.resultData.totalUnrealisedDistributionsUnrealised,
                partiallyRealised: this.resultData.totalUnrealisedDistributionsPartRealised,
                realised: this.resultData.totalUnrealisedDistributionsRealised,
                writtenOff: this.resultData.totalUnrealisedDistributionsWrittenOff,
              }
            ]
            console.log(this.summaryListDate);
          })
          .catch(err => {
          });
      },
      changeFund:function() {
        // console.log("临");
        // console.log("临");
        this.portfoliolistData = [];
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

</style>
