<template>
  <div class="add-project">
    <Header></Header>
    <!-- <h2>添加项目信息</h2> -->
    <el-form :label-position="labelposition" :model="formData" :rules="rules" ref="userform">
      <h3>Essential information </h3>
      <div class="big-base-container">
        <div class="base-info-container">
          <div class="baseinfo-left">

            <el-form-item label="Fund Full Name" prop="fundId">
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
            </el-form-item>
          </div>

          <div class="baseinfo-right">
            <el-form-item label="Called Date" prop="calledDate">
              <!-- <el-input v-model="formData.formationdate"></el-input> -->
              <el-date-picker v-model="formData.calleddate" type="date" placeholder="select date"></el-date-picker>
            </el-form-item>
            <el-form-item label="Due Date" prop="dueDate">
              <!-- <el-input v-model="formData.formationdate"></el-input> -->
              <el-date-picker v-model="formData.duedate" type="date" placeholder="select date"></el-date-picker>
            </el-form-item>
            <el-form-item label="Call Order" prop="callOrder">
              <el-input type="number" step="1" v-model="formData.callorder"></el-input>
            </el-form-item>

            <el-form-item label="Call Type(ERISA)" prop="erisaStatus">
              <el-select v-model="formData.erisastatus" placeholder="pls select type">
                <el-option :key="item.erisastatus"
                           :label="item.erisastatusstr"
                           :value="item.erisastatus"
                           v-for="item in erisaStatusList"></el-option>
              </el-select>
            </el-form-item>

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
          <router-link to="capitalcalllistpage" style="margin-left:10px"><el-button size="mini">返回</el-button></router-link>
        </div>
        <div v-else>
          <el-button type="primary" size="mini" @click="submitForm2('userform')">Update</el-button>
          <router-link to="capitalcalllistpage" style="margin-left:10px"><el-button size="mini">返回</el-button></router-link>
          <!--<el-button size="mini" type="primary" @click="linktodetail">返回</el-button>-->
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import Header from "../components/common/Header";
  import * as method from "../api/method";
  import axioss from '../api/axios';
  export default {
    name:"fundCapitalCallAdd",
    data:function(){
      return {
        labelposition:"right",
        //dictarraylist:'',
        fundarrayList:'',
        erisaStatusList: [{
          erisastatus: 'all',
          erisastatusstr: 'ALL',
        }, {
          erisastatus: 'erisaonly',
          erisastatusstr: 'ERISA Only',
        }, {
          erisastatus: 'excludeerisa',
          erisastatusstr: 'Exclude ERISA',
        }],
        //subsectorlist:[],
        //extraarr:[],
        opreationType:'creatuserform',
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
          fundid: [
            {required: true, message: 'required', trigger: 'blur' }
          ],
          calleddate: [
            {required: true, message: 'required', trigger: 'blur' }
          ],
          calledpecentage: [
            {required: true, message: 'required', trigger: 'blur' }
          ],
          duedate: [
            {required: true, message: 'required', trigger: 'blur' }
          ],
          callorder: [
            {required: true, message: 'required', trigger: 'blur' }
          ],

        }
      }
    },
    created(){
      var way=this.$route.query.path;
      var callid=this.$route.query.callid;
      this.classOperation(way,callid);
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
        this.$router.push({ name: "capitalcalllistpage"})
      },
      submitForm1(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var tranForm=this.formData;
            // tranForm.closedate=method.toMs(tranForm.closedate);
            // tranForm.formationdate=method.toMs(tranForm.formationdate);
            axioss.submitinfocapitalcall(tranForm).then(res=>{
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
            axioss.modifyinfocapitalcall(tranForm).then(res=>{
              var flag=res.data.code=="SUCCESS";
              if(flag){
                this.$message({
                  type:'success',
                  message:'Create success'
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
      classOperation(way,callid){
        console.log(way)
        if(way=="editor"){
          this.opreationType=way;
          axioss.reqdetailscapitalcall(callid).then((res)=>{

            var tranRes=res.data.data;

            this.formData=tranRes;
            //console.log(tranRes);
          }).catch((res)=>{
            console.log(res);
          })
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
