<template>
  <div class="add-project">
    <Header></Header>
    <!-- <h2>添加项目信息</h2> -->


    <el-form :label-position="labelposition" :model="formData" :rules="rules" ref="userform">

      <div style="margin-top:20px">
        <b style="font-size:18px">INVESTMENT OVERVIEW OF </b>
        <!--   <el-form-item label="NET CASHFLOW FOR" >-->
        <el-select v-model="searchData.fundid" placeholder="pls select fund name" style="width:400px" @change="changeFund()">
          <el-option :key="item.fundid"
                     :label="item.fundfullnameeng"
                     :value="item.fundid"
                     v-for="item in fundarrayList.data"></el-option>
        </el-select>
        <!-- </el-form-item>-->
      </div>


      <div style="margin-top:15px">
      </div>
      <div class="position-container">
        <div class="table-responsive">

          <el-table :data="portfoliolistData" border style="width: 100%" maxHeight="850">
            <el-table-column fixed prop="company" label="Company" width="150">
            </el-table-column>
            <el-table-column  prop="businessDescription" label="Business Description" width="400">
            </el-table-column>
            <el-table-column  prop="sectorStr" label="Sector" width="100">
            </el-table-column>
            <el-table-column  prop="portfolioStatusStr" label="Status" width="100">
            </el-table-column>
            <el-table-column  prop="privateOrPublic" label="Private/Public" width="120">
            </el-table-column>
            <el-table-column  prop="currentOwnership" label="Current Ownership" width="150">
            </el-table-column>
            <el-table-column  prop="investmentDate" label="Investment Date" width="150">
            </el-table-column>
            <el-table-column  prop="exitDate" label="Exit Date" width="150">
            </el-table-column>
            <el-table-column  prop="upround" label="Upround (Yes/No)" width="100">
            </el-table-column>
            <el-table-column  prop="boardSeatsStr" label="Broad Seats(Yes/No)" width="100">
            </el-table-column>
            <el-table-column  prop="lead" label="Lead" width="150">
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
        portfolioList:'',
        // opreationType:'creatuserform',
        portfoliolistData:[],
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
        axioss.reqfundinvestmentoverview(searchData.fundid)
          .then(res => {
            var trans=res.data;
            /*for(var i=0;i<trans.length;i++){
             var num=trans[i].totalcommitmentnum;
             trans[i].totalcommitmentnum=method.toThousands(num);
             }*/
            //console.log("临");
            //console.log(trans);
            this.portfoliolistData = trans.data;
            //this.lpandgplistData = trans.data.lpAndGpCashFlowList;
            //this.resultData = trans.data;

          })
          .catch(err => {
          });
      },
      changeFund:function() {
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
    width:88%;
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
