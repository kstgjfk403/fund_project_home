<template>
  <div class="add-project">
    <Header></Header>
    <!-- <h2>添加项目信息</h2> -->
    <el-form :label-position="labelposition" :model="formData" :rules="rules" ref="userform">
      <h3>Finantial information </h3>
      <div class="big-base-container">
        <div class="base-info-container">
          <div class="baseinfo-left">
            <el-form-item label="Financial Date" prop="finDate">
              <!-- <el-input v-model="formData.formationdate"></el-input> -->
              <el-date-picker v-model="formData.findate" type="date" placeholder="select date"></el-date-picker>
            </el-form-item>
            <el-form-item label="Fund Net Assets" prop="netAssets">
              <el-input type="number" step="1" v-model="formData.netassets"></el-input>
            </el-form-item>
            <el-form-item label="Current Profit" prop="curProfit">
              <el-input type="number" step="1" v-model="formData.curprofit"></el-input>
            </el-form-item>
            <el-form-item label="Other Assets" prop="otherAssets">
              <el-input type="number" step="1" v-model="formData.otherassets"></el-input>
            </el-form-item>
            <el-form-item label="LP Net Assets" prop="lpNetAssets">
              <el-input type="number" step="1" v-model="formData.lpnetassets"></el-input>
            </el-form-item>
            <el-form-item label="GP Distribution" prop="gpDistribution">
              <el-input type="number" step="1" v-model="formData.gpdistribution"></el-input>
            </el-form-item>
            <el-form-item label="Distributable Profit" prop="distributableProfit">
              <el-input type="number" step="1" v-model="formData.distributableprofit"></el-input>
            </el-form-item>
            <el-form-item label="LP Net IRR(%)" prop="netIrrLp">
              <el-input type="number" step="0.01" v-model="formData.netirrlp"></el-input>
            </el-form-item>
            <el-form-item label="LP Net Multiple" prop="netMultipleLp">
              <el-input type="number" step="0.01" v-model="formData.netmultiplelp"></el-input>
            </el-form-item>
            <el-form-item label="Remarks" >
              <el-input type="textarea"  v-model="formData.remarks" placeholder=""></el-input>
            </el-form-item>
            <!--<el-form-item label="Fund Full Name" prop="fundId">
              <el-select v-model="formData.fundid" placeholder="pls select fund name">
                <el-option :key="item.fundid"
                           :label="item.fundfullnameeng"
                           :value="item.fundid"
                           v-for="item in fundarrayList.data"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Called Percentage(%)" prop="calledPercentage">
              <el-input type="number" step="0.01" v-model="formData.calledpecentage"></el-input>
            </el-form-item>

            <el-form-item label="Total Call Amount" prop="totalCallAmount">
              <el-input type="number" step="1" v-model="formData.totalcallamount"></el-input>
            </el-form-item>

            <el-form-item label="Called Purpose" >
              <el-input type="textarea"  v-model="formData.calledpurpose" placeholder="pls enter call purpose"></el-input>
            </el-form-item>-->
          </div>

          <div class="baseinfo-right">
            <el-form-item label="Portfolio Fair Value" prop="projFairValue">
              <el-input type="number" step="1" v-model="formData.projfairvalue"></el-input>
            </el-form-item>
            <el-form-item label="Current Income" prop="curIncome">
              <el-input type="number" step="1" v-model="formData.curincome"></el-input>
            </el-form-item>
            <el-form-item label="Cash Assets" prop="cashAssets">
              <el-input type="number" step="1" v-model="formData.cashassets"></el-input>
            </el-form-item>
            <el-form-item label="GP Net Assets" prop="gpNetAssets">
              <el-input type="number" step="1" v-model="formData.gpnetassets"></el-input>
            </el-form-item>
            <el-form-item label="Total Debt Value" prop="debtValue">
              <el-input type="number" step="1" v-model="formData.debtvalue"></el-input>
            </el-form-item>
            <el-form-item label="Net Profit" prop="netProfit">
              <el-input type="number" step="1" v-model="formData.netprofit"></el-input>
            </el-form-item>
            <el-form-item label="Call Assets" prop="callAsset">
              <el-input type="number" step="1" v-model="formData.callasset"></el-input>
            </el-form-item>
            <el-form-item label="GP Net IRR(%)" prop="netIrrGp">
              <el-input type="number" step="0.01" v-model="formData.netirrgp"></el-input>
            </el-form-item>
            <el-form-item label="GP Net Multiple" prop="netMultipleGp">
              <el-input type="number" step="0.01" v-model="formData.netmultiplegp"></el-input>
            </el-form-item>
            <!--<el-form-item label="Called Date" prop="calledDate">
              &lt;!&ndash; <el-input v-model="formData.formationdate"></el-input> &ndash;&gt;
              <el-date-picker v-model="formData.calleddate" type="date" placeholder="select date"></el-date-picker>
            </el-form-item>
            <el-form-item label="Due Date" prop="dueDate">
              &lt;!&ndash; <el-input v-model="formData.formationdate"></el-input> &ndash;&gt;
              <el-date-picker v-model="formData.duedate" type="date" placeholder="select date"></el-date-picker>
            </el-form-item>
            <el-form-item label="Call Order" prop="callOrder">
              <el-input type="number" step="1" v-model="formData.callorder"></el-input>
            </el-form-item>-->

          </div>
        </div>
        <!--<div class="singal-base-container">
          <el-form-item label="Industry focus">
            <el-input type="textarea" v-model="formData.mainindustry"></el-input>
          </el-form-item>
          <el-form-item label="Management Fee Structure">
            <el-input type="textarea" v-model="formData.managfeedesc" placeholder="pls company register no"></el-input>
          </el-form-item>
        </div>-->
      </div>
      <el-form-item style="margin-top:15px;">
        <div v-if="opreationType=='creatuserform'">
          <el-button type="primary" size="mini" @click="submitForm1('userform')">Create</el-button>
          <router-link to="FundFinListPage" style="margin-left:10px"><el-button size="mini">返回</el-button></router-link>
        </div>
        <div v-else>
          <el-button type="primary" size="mini" @click="submitForm2('userform')">Update</el-button>
          <router-link to="FundFinListPage" style="margin-left:10px"><el-button size="mini">返回</el-button></router-link>
          <!--<el-button size="mini" type="primary" @click="linktodetail">返回</el-button>-->
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import Header from "@/components/common/Header";
  import * as method from "@/api/method";
  import axioss from '@/api/axios';
  export default {
    name:"fundCapitalCallAdd",
    data:function(){
      return {
        labelposition:"right",
        //dictarraylist:'',
        fundarrayList:'',
        //subsectorlist:[],
        //extraarr:[],
        opreationType:'creatuserform',
        formData: {
          fundid:'',
          findate: '',
          projfairvalue : '',
          netassets : '',
          curincome : '',
          curprofit : '',
          cashassets : '',
          otherassets : '',
          gpnetassets : '',
          lpnetassets : '',
          debtvalue : '',
          gpdistribution : '',
          netprofit : '',
          distributableprofit : '',
          netvaluefundasset : '',
          callasset : '',
          netirrlp : '',
          netirrgp : '',
          netmultiplelp : '',
          netmultiplegp : '',
          ismodified : '',
          validflag : '',
          remarks : '',
          fundworkreport : '',
          fundworkreportcode : '',

          /*callid:'',
          fundid: '',
          calleddate: '',
          calledpecentage:'',
          totalcallamount:'',
          calledpurpose:'',
          duedate:'',
          callorder:'',
          createby:'',
          createdate:'',
          approved:'',*/
        },
        rules: {
          fundid: [
            {required: true, message: 'required', trigger: 'blur' }
          ],
          findate: [
            {required: true, message: 'required', trigger: 'blur' }
          ],


        }
      }
    },
    created(){
      var way=this.$route.query.path;
      var finId=this.$route.query.finId;
      this.classOperation(way,finId);
    },
    mounted:function(){
      //this.requserlist();//获取列表数据
      this.reqfundlist();
    },
    methods:{
      /*requserlist(){
       var enterobj={
       dictArray:'DDL_IncorpLocation,YTD,CURRENCY'
       }
       axioss.reqdroplist(enterobj).then(res=>{
       this.dictarraylist=method.translateFormat(res.data.data);
       console.log(this.dictarraylist)
       }).catch(res=>{
       console.log(res);
       })
       },*/
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
          console.log(this.fundarrayList);
        }).catch(res=>{
          console.log(res);
        })
      },

      linktodetail(){
        var id=this.formData.fundid
        this.$router.push({ path: "funddetail", query: { fundid: id } })
      },
      linktolist(){
        this.$router.push({ name: "fundFinListPage",query: { fundId: this.formData.fundid } })
      },
      submitForm1(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var tranForm=this.formData;
            // tranForm.closedate=method.toMs(tranForm.closedate);
            // tranForm.formationdate=method.toMs(tranForm.formationdate);
            axioss.submitinfofundfin(tranForm).then(res=>{
              console.log(res)
              var flag=res.data.code=="SUCCESS";
              if(flag){
                this.$message({
                  type:'success',
                  message:'Create success'
                })
                this.linktolist()
              }else{
                this.$message({
                  type:'warning',
                  message:'Create failure'
                })
              }
            }).catch(res => {
              console.log(res)
            })
          } else {
            this.$message({
              type:"warning",
              message:"The content format is not correct"
            })
            return false;
          }
        });
      },
      submitForm2(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var tranForm=this.formData;
            /* console.log("临兵斗者");
             console.log(tranForm);*/
            axioss.modifyinfofundfin(tranForm).then(res=>{
              var flag=res.data.code=="SUCCESS";
              if(flag){
                this.$message({
                  type:'success',
                  message:'Update success'
                })
                this.linktolist()
              }
            }).catch(res => {
              console.log(res)
            })
          } else {
            this.$message({
              type:"warning",
              message:"输入内容格式不正确"
            })
            return false;
          }
        });
      },
      /*  togetherChange(id){
       var empty=[];
       var len=this.extraarr.length;
       for(var i=0;i<len;i++){
       if(this.extraarr[i].parentId==id){
       empty.push(this.extraarr[i])
       }
       }
       this.subsectorlist=empty;
       },*/
      classOperation(way,finId){
        console.log(way)
        if(way=="editor"){
          this.opreationType=way;
          axioss.reqdetailsfundfin(finId).then((res)=>{

            var tranRes=res.data.data;

            this.formData=tranRes;
            //console.log(tranRes);
          }).catch((res)=>{
            console.log(res);
          })
        }else if(way=="add"){
          this.formData.fundid=finId; //此时的finId 其实就是fundId
        }
      }
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
