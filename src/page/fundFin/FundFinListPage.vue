
<template>
  <div class="listpage">
    <Header></Header>
    <div class="wrapper-container">
      <div style="margin-top:20px;margin-left:50px">
        <b style="font-size:18px">FUND NAME </b>
        <!--   <el-form-item label="NET CASHFLOW FOR" >-->
        <el-select v-model="fundFinSearchData.fundid" placeholder="pls select fund name" style="width:300px" @change="changeFund()">
          <el-option :key="item.fundid"
                     :label="item.fundfullnameeng"
                     :value="item.fundid"
                     v-for="item in fundarrayList.data"></el-option>
        </el-select>

        <b style="font-size:18px;margin-left:50px">FINANCIAL DATE </b>
        <el-select v-model="fundFinSearchData.quarterid" placeholder="pls select quarter" style="width:100px" @change="changeFund()">
          <el-option :key="item.quarterid"
                     :label="item.quarternameeng"
                     :value="item.quarterid"
                     v-for="item in quarterList"></el-option>
        </el-select>
        <el-select v-model="fundFinSearchData.yearid" placeholder="pls select year" style="width:90px" @change="changeFund()">
          <el-option :key="item.yearid"
                     :label="item.yearid"
                     :value="item.yearid"
                     v-for="item in yearList"></el-option>
        </el-select>

        <!-- </el-form-item>-->
      </div>
      <div class="button-container" style="margin:0 auto;margin-top:10px;">
        <el-button type="primary" @click="linkto('addFundFin')">New</el-button>
      </div>
      <div class="position-container">
        <div class="table-responsive">
          <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;">
            <thead>
            <tr>
              <th scope="col" style="width:85px">operation</th>
              <th scope="col" style="display:none">Fin ID
                <el-input v-model="fundFinData.finId" @input.native="searchData"></el-input>
              </th>
              <th scope="col" style="display:none">Fund ID
                <el-input class="search" v-model="fundFinData.fundId" @input.native="searchData"></el-input>
              </th>
              <th scope="col" >Fund Name
                <el-input class="search" v-model="fundFinData.fundName" @input.native="searchData"></el-input>
              </th>
              <th scope="col" >Fund Full Name Eng
                <el-input class="search" v-model="fundFinData.fundFullNameEng" @input.native="searchData"></el-input>
              </th>
              <th scope="col" >Financial Date
              </th>

              <th scope="col">Portfolio Fair Value</th>
              <th scope="col">Fund Net Assets</th>
              <th scope="col">Current Income</th>
              <th scope="col">Current Profit</th>
              <th scope="col">Cash Assets</th>
              <th scope="col">Other Assets</th>
              <th scope="col">GP Net Assets</th>
              <th scope="col">LP Net Assets</th>
              <th scope="col">Total Debt Value</th>
              <th scope="col">GP Distribution</th>
              <th scope="col">Net Profit</th>
              <th scope="col">Distributable Profit</th>
              <th scope="col"  style="display:none">Net Value Fund Asset</th>
              <th scope="col">Call Assets</th>
              <th scope="col">LP Net IRR</th>
              <th scope="col">GP Net IRR</th>
              <th scope="col">LP Net Multiple</th>
              <th scope="col">GP Net Multiple</th>
              <th scope="col"  style="display:none">Is Modified</th>
              <th scope="col"  style="display:none">Valid Flag</th>
              <th scope="col" >Remarks</th>
              <th scope="col"  style="display:none">Fund Work Report</th>
              <th scope="col"  style="display:none">Fund Work Report Code</th>


            <!--  <th scope="col" style="width:185px;">LP+GP Commitment
                &lt;!&ndash; <el-input class="search" placeholder=" Programe Name" v-model="capitalCallData.totalcommitmentnum" @input.native="searchData"></el-input>&ndash;&gt;
              </th>

              <th scope="col" style="width:240px;">Management Fee Structure
                <el-input class="search" placeholder=" Management Fee Structure" v-model="capitalCallData.managfeedesc" @input.native="searchData"></el-input>
              </th>
              <th scope="col" style="width:240px;">Capital Allocations Desc
                <el-input class="search" placeholder=" Management Fee Structure" v-model="capitalCallData.capitalallocationsdesc" @input.native="searchData"></el-input>
              </th>

              <th scope="col">GP Name
                <el-input class="search" placeholder=" Management Fee Structure" v-model="capitalCallData.gpname" @input.native="searchData"></el-input>
              </th>-->

            </tr>
            </thead>
            <tbody>
            <tr v-for="item in alllistData">
              <td>
                <!--<i class="el-icon-info" ></i>-->
                <i class="el-icon-edit" @click="linkto('editor',item.finId)"></i>
                <i class="el-icon-delete" @click="ifdeletefundfin(item.finId)"></i>
              </td>
              <td scope="row" style="display:none">{{item.finId}}</td>
              <td style="display:none">{{item.fundId}}</td>
              <td>{{item.fundName}}</td>
              <td>{{item.fundFullNameEng}}</td>
              <td>{{item.finDate}}</td>
              <td>{{item.projFairValue}}</td>
              <td>{{item.netAssets}}</td>
              <td>{{item.curIncome}}</td>
              <td>{{item.curProfit}}</td>
              <td>{{item.cashAssets}}</td>
              <td>{{item.otherAssets}}</td>
              <td>{{item.gpNetAssets}}</td>
              <td>{{item.lpNetAssets}}</td>
              <td>{{item.debtValue}}</td>
              <td>{{item.gpDistribution}}</td>
              <td>{{item.netProfit}}</td>
              <td>{{item.distributableProfit}}</td>
              <td>{{item.netValueFundAsset}}</td>
              <td>{{item.callAsset}}</td>
              <td>{{item.netIrrLp}}</td>
              <td>{{item.netIrrGp}}</td>
              <td>{{item.netMultipleLp}}</td>
              <td>{{item.netMultipleGp}}</td>
              <td>{{item.isModified}}</td>
              <td>{{item.validFlag}}</td>
              <td>{{item.remarks}}</td>
              <td>{{item.fundWorkReport}}</td>
              <td>{{item.fundWorkReportCode}}</td>

              <!--<td>{{item.totalcommitmentnum}}</td>
              <td>{{item.managfeedesc}}</td>
              <td>{{item.capitalallocationsdesc}}</td>
              <td>{{item.gpname}}</td>-->

            </tr>
            </tbody>
          </table>
          <!--<div class="pagination-container">
            <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :current-page.sync="pageCurrent" @current-change="handleCurrentChange" style="position:absolute;left:50%;bottom:25px;transform: translate(-50%);"></el-pagination>
          </div>-->
        </div>

      </div>
    </div>
  </div>
</template>
<script>
  import axioss from '@/api/axios';
  import Header from "@/components/common/Header";
  import * as method from "@/api/method";

  export default {
    name: "FundFinListPage",
    data: function() {
      return {
        showColumn:false,
        showPartColumn:true,
        portfolioid: "",
        pageCurrent: 1,
        pageCount: 10,
        allcount: 0,
        alllistData:'',
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

        fundFinData:{
          finId:'',
          fundId:'',
          fundName:'',
          fundFullNameEng:'',

          fundfamillyname:'',
          fundname:'',
          fundfullnameeng:'',
          fundfullnamechi:'',
          calleddatestart:'',
          calleddateend:'',

        },
        fundFinSearchData:{
          fundid: 'F0001',
          quarterid: '',
          yearid:'',
          fundName:'',
          fundFullNameEng:'',

        },


        allDetailData:[],

        options: [
          {
            value: true,
            label: "yes"
          },
          {
            value: false,
            label: "no"
          }
        ]
      }
    },
    mounted: function() {
      var fundId = this.$route.query.fundId;
      if(fundId!=null && fundId!= undefined){
        this.fundFinSearchData.fundid = fundId;
      }
      this.reqfundlist();
      this.submitpage(this.fundFinSearchData);
    },
    components: {
      Header
    },
    methods: {

      reqfundlist(){
        /*var enterobj={
         dictArray:'DDL_IncorpLocation,YTD,CURRENCY'
         }
         axioss.reqdroplist(enterobj).then(res=>{
         this.dictarraylist=method.translateFormat(res.data.data);
         }).catch(res=>{
         })*/
        axioss.reqfundlistwithoutpage().then(res=>{
          this.fundarrayList=res.data;
        }).catch(res=>{
        })
      },

      submitpage: function(fundFinSearchData) {
        var that = this;
        /*var obj={pageIndex:pageIndex,pageCount:pageCount}
        this.capitalCallData.pageIndex=pageIndex;
        this.capitalCallData.pageCount=pageCount;*/
        //axioss.reqmemberlistcapitalcall(this.capitalCallData)
        axioss.reqmemberlistfundfin(this.fundFinSearchData)
          .then(res => {
            var trans=res.data.data;
            for(var i=0;i<trans.length;i++){
              var num=trans[i].totalcommitmentnum;
              trans[i].totalcommitmentnum=method.toThousands(num);
            }
            that.alllistData = trans;
            this.allcount=res.data.count;


          })
          .catch(err => {
          });
      },
      changeFund(){
        this.submitpage(this.fundFinSearchData);
      },
      handleCurrentChange() {
        this.submitpage(this.fundFinSearchData);
      },
      linkto(path, id) {
        if (path == "details") {
          this.$router.push({ path: "fundFinDetail", query: { finId: id } });
        } else if (path == "editor") {
          this.$router.push({ path: "fundFinaddpage", query:{finId:id,path:'editor'}})
        } else if (path == "addFundFin") {
          this.$router.push({ path: "fundFinaddpage",query:{finId:this.fundFinSearchData.fundid,path:'add'}});
        }
      },
      showdetail(id){
        this.capitalCallLpSearchData.callid = id;
        axioss.reqmemberlistcapitalcalllp(this.capitalCallLpSearchData)
          .then(res => {
            var trans=res.data.data;

            this.allDetailData = trans;
          })
          .catch(err => {
          });
      },
      searchData() {
        var that = this;
        axioss.reqmemberlistcapitalcall(this.capitalCallData)
          .then(res => {

            that.alllistData = res.data.data;
            this.allcount=res.data.count;
          })
          .catch(err => {
          });
      },
      selectchange() {
        var that = this;
        axioss.reqmemberlistcapitalcall(this.capitalCallData)
          .then((res) => {
            that.alllistData = res.data.data;
            this.allcount=res.data.count;
          })
          .catch((err) => {
          });
      },
      // fetchperStatus() {
      //     var that=this;
      //     axioss.reqdroplist({dictArray:"IDG_Staff,DDL_PortfolioStatus"})
      //     .then((res)=>{
      //         this.staffs=res.data.data[0].baseInfoList;
      //         this.portfoliostatus=res.data.data[1].baseInfoList;
      //     })
      //     .catch(function(err) {
      //     });
      // },
      ifdeletefundfin(finid) {
        this.$confirm("Confirm to delete this Fund Finance Info, continue?", "Warning", {
          confirmButtonText: "confirm",
          cancelButtonText: "cancel",
          type: "warning"
        }).then(() => {
          axioss.reqdeletefundfin(finid).then(res => {
            var status = res.data.code;
            if (status.toLocaleLowerCase() == "success") {
              this.handleCurrentChange();//Update列表,页码
              this.$message({
                type: "success",
                message: "Delete success!"
              });
            } else {
              this.$message({
                type: "error",
                message: "Delete failure!"
              });
            }
          });
        })
          .catch(() => {
            this.$message({
              type: "info",
              message: "Cancel Delete"
            });
          });
      }


    }
  };
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .top {
    margin-top: 50px;
  }
  .el-pagination {
    text-align: center;
    padding: 10px 0;
  }
  .table tr th {
    vertical-align: top;
    width:166px;
    text-align:center;
  }
  .table tr th:last-child {
    width:130px;
  }
  .table tr td {
    width: 166px;
    white-space: wrap;
    overflow: hidden;
    color:#666;
  }
  .table tr td:last-child{
    text-align: center;
  }
  .table tr th,.table tr td{
    border-color:#ddd;
  }
  .pagination-container{
    width:100%;
    height:50px;
  }
  .position-container{
    width:95%;margin:0 auto;
    position:relative;
  }
  .el-button--primary {
    margin: 10px 0;
    background-image: linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);
  }

  .el-button--mini {
    width: 48px;
    margin-left: 0;
    margin-right: 5px;
    padding: 7px 12px;
  }
  .button-container{
    /* border:1px solid #ddd; */
    border-bottom:none;
    width:95%;
  }
  [class^=el-icon-]{
    margin-left:8px;
  }
</style>
