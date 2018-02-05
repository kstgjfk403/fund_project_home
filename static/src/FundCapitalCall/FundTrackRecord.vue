<template>
  <div class="add-project">
    <Header></Header>
    <!-- <h2>添加项目信息</h2> -->


    <el-form :label-position="labelposition"  :rules="rules" ref="userform">

      <div style="margin-top:20px">
        <b style="font-size:18px">INVESTMENT OVERVIEW OF </b>
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
        <!-- </el-form-item>-->
      </div>


      <div style="margin-top:15px">
      </div>
      <div class="position-container">
        <div class="table-responsive">




          <el-table :data="summaryListDate" border style="width: 80%" maxHeight="850">
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
      <div style="margin-top:15px">
      </div>
      <div class="position-container">
        <div class="table-responsive">

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
