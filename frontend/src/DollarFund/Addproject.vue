<template>
<div class="add-project">
    <Header></Header>
    <div class="big-container" ref="BasicInfo">
    <div class="title">BasicInfo</div>
    <el-form :label-position="labelposition" :model="formData" :rules="rules" ref="userform">
        <h3>Essential information </h3>
        <div class="big-base-container">
          <div class="base-info-container">
            <div class="baseinfo-left">
              <el-form-item label="Fund Type(Abbr.)" prop="fundtypeid">
                  <el-input v-model="formData.fundtypeid" placeholder="pls enter company name"></el-input>
              </el-form-item>
              <el-form-item label="Fund Abbr. Name" prop="fundname">
                  <el-input v-model="formData.fundname" placeholder="pls enter company name"></el-input>
              </el-form-item>
              <el-form-item label="基金全名" prop="fundfullnamechi">
                  <el-input v-model="formData.fundfullnamechi" placeholder="pls enter report name"></el-input>
              </el-form-item>
              
              <el-form-item label="Financial Year End">
                <el-select v-model="formData.yearend" placeholder="">
                <el-option :key="item.baseId" 
                  :label="item.baseName" 
                  :value="item.baseId" 
                  v-for="item in dictarraylist.YTD"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="Fund Closing Date" multiple>
                <el-date-picker v-model="formData.closedate" type="date" placeholder=""></el-date-picker>
              </el-form-item>
              <el-form-item label="Total GP Commitment">
                <el-input v-model="formData.totalgpcommitnum" placeholder="" filterable  @change="togetherChange"></el-input>
              </el-form-item>
              
              <el-form-item label="Fund Currency">
                <el-select v-model="formData.totalcommitmentcur" placeholder="">
                <el-option :key="item.baseId" 
                  :label="item.baseName" 
                  :value="item.baseId" 
                  v-for="item in dictarraylist.CURRENCY"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="Incorprated In">
                <el-select v-model="formData.incorplocation" placeholder="">
                <el-option :key="item.baseId" 
                  :label="item.baseName" 
                  :value="item.baseId" 
                  v-for="item in dictarraylist.DDL_IncorpLocation"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="ABA"> 
                <el-input v-model="formData.aba"></el-input>
              </el-form-item>
              <el-form-item label="Attn"> 
                <el-input v-model="formData.attn"></el-input>
              </el-form-item>
              <el-form-item label="Auditor">
                <el-input v-model="formData.auditor"></el-input>
              </el-form-item>
              <el-form-item label="Tax Representative">
                <el-input v-model="formData.taxrepresentative"></el-input>
              </el-form-item>
              <el-form-item label="FATCA Type">
                <el-input v-model="formData.fatcatype"></el-input>
              </el-form-item>
              <el-form-item label="GP Name">
                <el-input v-model="formData.gpname"></el-input>
              </el-form-item>
              <el-form-item label=" Mgmt Co. Incorporated In">
                <el-select v-model="formData.magtcomincorporated" placeholder="">
                <el-option :key="item.baseId" 
                  :label="item.baseName" 
                  :value="item.baseId" 
                  v-for="item in dictarraylist.DDL_AntiDilution"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="Tax ID">
                <el-input v-model="formData.taxid" placeholder="pls company register no"></el-input>
              </el-form-item>
              <el-form-item label="Capital Allocations Desc">
                <el-input v-model="formData.capitalallocationsdesc" placeholder="pls company register no"></el-input>
              </el-form-item>
              <el-form-item label="Finance Manager(Email)">
                <el-input type="textarea" v-model="formData.financemanageremail"></el-input>
              </el-form-item>
              <el-form-item label="Bank Address" prop="registerno">
                <el-input type="textarea" v-model="formData.bankaddress" placeholder="pls company register no"></el-input>
              </el-form-item>
            </div>
            <div class="baseinfo-right">
              <el-form-item label="Fund Family Name" prop="fundfamillyname">
                <el-input v-model="formData.fundfamillyname" placeholder=" 项目名称"></el-input>
              </el-form-item>
              <el-form-item label="Fund Full Name" prop="fundfullnameeng">
                <el-input v-model="formData.fundfullnameeng"></el-input>
              </el-form-item>
              <el-form-item label="Registered Address">
                <el-input v-model="formData.regaddress"></el-input>
              </el-form-item>
              <el-form-item label="Formation Date" prop="formationdate">
                <el-date-picker v-model="formData.formationdate" type="date" placeholder=" "></el-date-picker>
              </el-form-item>
              <el-form-item label="Total LP Commitment">
                <el-input v-model="formData.totallpcommitnum" placeholder="  "></el-input>
              </el-form-item>
              <el-form-item label="LP+GP Commitment">
                <el-input v-model="formData.totalcommitmentnum" placeholder="  "></el-input>
              </el-form-item>
              <el-form-item label="Bank Name">
                <el-input v-model="formData.bankname" placeholder="pls enter total border representative"></el-input>
              </el-form-item>
              <el-form-item label="Bank Account No">
                <el-input v-model="formData.bankaccount"></el-input>
              </el-form-item>
              <el-form-item label="Swift Code">                    
                <el-input v-model="formData.swiftcode"></el-input>    
              </el-form-item>
              <el-form-item label="GAAP">
                <el-input v-model="formData.gaap"></el-input>
              </el-form-item>
              <el-form-item label="Legal Advisor">
                <el-input v-model="formData.legaladvisor" placeholder="pls company register no"></el-input>
              </el-form-item>
              <el-form-item label="FATCA">
                  <el-checkbox v-model="formData.fatca" placeholder="pls company register no"></el-checkbox>
              </el-form-item>
              <el-form-item label="FATCA Officer">
                <el-input v-model="formData.fatcaofficer" placeholder="pls company register no"></el-input>
              </el-form-item>
              <el-form-item label="Management Company">
                <el-input v-model="formData.magtcompany" placeholder="pls company register no"></el-input>
              </el-form-item>
              <el-form-item label="GIIN No">
                <el-input v-model="formData.giin_no" placeholder="pls company register no"></el-input>
              </el-form-item>
              <el-form-item label="Fund Manager Name">
                <el-input v-model="formData.fundmanager" placeholder="pls company register no"></el-input>
              </el-form-item>
              <el-form-item label="Finance Manager Name">
                <el-input v-model="formData.financemanager" placeholder="pls company register no"></el-input>
              </el-form-item>
              <el-form-item label="Fund Manager(Email)">
                <el-input type="textarea" v-model="formData.fundmanageremail"></el-input>
              </el-form-item>
            </div>
          </div>
          <div class="singal-base-container">
            <el-form-item label="Industry focus">
              <el-input type="textarea" v-model="formData.mainindustry"></el-input>
            </el-form-item>
            <el-form-item label="Management Fee Structure">
              <el-input type="textarea" v-model="formData.managfeedesc" placeholder="pls company register no"></el-input>
            </el-form-item>
          </div>
        </div>
        <el-form-item class='addpro-button' style="border-top:1px solid #ddd;">
          <div v-if="opreationType=='creatuserform'">
            <el-button type="primary" size="mini" @click="submitForm1('userform')">Create</el-button>
            <router-link to="fundlistpage" style="margin-left:10px"><el-button size="mini">返回</el-button></router-link>
          </div>
          <div v-else>
            <el-button type="primary" size="mini" @click="submitForm2('userform')">Update</el-button>
            <el-button size="mini" type="primary" @click="linktodetail">返回</el-button>
          </div>
        </el-form-item> 
    </el-form>
    </div>
    <Commit></Commit>
    <Navlist v-on:toscorll="scrolltoview" :isactive='isActive'></Navlist>
</div>
</template>
<script>
import Header from "../components/common/Header";
import Navlist from '@/components/common/Nav';
import Commit from '@/page/commit/Commit';

import * as method from "../api/method";	
import axioss from '../api/axios';

export default {
    name:"FundAddproject",
    data:function(){  
      return {
        labelposition:"right",
        dictarraylist:'',
        subsectorlist:[],
        extraarr:[],
        opreationType:'creatuserform',
        heightObj:'',
        isActive:{
            BasicInfo:true,
            Commitment:false,
        },
        formData: {
          fundtypeid: '',
          fundfamillyname: '',
          fundfullnameeng: '',
          fundfullnamechi: '',
          mainindustry: '',
          yearend:'',
          closedate:'',
          totalgpcommitnum:'',
          totalcommitmentcur:'',
          incorplocation:'',
          bankaddress:'',
          aba:'',
          attn:'',
          auditor:'',
          taxrepresentative:'',
          fatcatype:'',
          gpname:'',
          magtcomincorporated:'',
          taxid:'',
          capitalallocationsdesc:'',
          financemanageremail:'',
          regaddress:'',
          formationdate:'',
          totallpcommitnum:'',
          totalcommitmentnum:'',
          bankname:'',
          bankaccount:'',
          swiftcode:'',
          gaap:'',
          legaladvisor:'',
          fatca:'',
          fatcaofficer:'',
          magtcompany:'',
          giin_no:'',
          managfeedesc:'',
          fundmanager:'',
          financemanager:'',
          fundmanageremail:''
        },
        rules: {
          fundtypeid: [
            {required: true, message: 'required', trigger: 'blur' }
          ],
          fundfamillyname: [
            {required: true, message: 'required', trigger: 'blur' }
          ],
          
          fundfullnameeng:[
            {required: true, message: 'required', trigger: 'blur' }
          ],
          formationdate:[
            {required: true, message: 'required', trigger: 'blur' }
          ],
          fundname:[
            {required: true, message: 'required', trigger: 'change' }
          ],
          fundfullnamechi:[
              { required: true, message: 'required', trigger: 'change'}
          ],
          subsectorid:[
              { type:"number", message: 'required', trigger: 'change'}
          ],
           reportname: [
            { message: 'required', trigger: 'blur' }
          ],
          ceo: [
            { message: 'required', trigger: 'blur' }
          ],
          registerno: [
            {  message: 'required', trigger: 'blur' }   
          ],
          headcount: [
            { message: 'required', trigger: 'blur' } 
          ],
          website: [
            { message: 'required', trigger: 'blur' }
          ],
          fax: [
            { message: 'required', trigger: 'blur' }  
          ],
          telephone: [
            { message: 'required', trigger: 'blur' }
          ],
          product:[
            { message: 'required', trigger: 'blur' }
          ],
          remarkes:[
            { message: 'required', trigger: 'blur' }
          ],
          nomineename:[
            { message: 'required', trigger: 'blur' }
          ],
          auditconfaddress:[
            { message: 'required', trigger: 'blur' }
          ],
          auditconfaddress2:[
            { message: 'required', trigger: 'blur' }
          ],
          idgboard: [
            { message: 'required', trigger: 'blur' }
          ]
        }  
      }
    },
    created(){
      var way=this.$route.query.path;
      var fundid=this.$route.query.fundid;
      this.classOperation(way,fundid);
    },
    mounted:function(){
      this.requserlist();//获取列表数据
    },
    methods:{
      requserlist(){
        var enterobj={
          dictArray:'DDL_IncorpLocation,YTD,CURRENCY'
        }
        axioss.reqdroplist(enterobj).then(res=>{
          this.dictarraylist=method.translateFormat(res.data.data);
        }).catch(res=>{
          console.log(res);
        })
      },
      linktodetail(){
        var id=this.formData.fundid
        this.$router.push({ path: "funddetail", query: { fundid: id } })
      },
      linktolist(){
        this.$router.push({ name: "fundlistpage"})
      },
      submitForm1(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var tranForm=this.formData;
          axioss.submitinfo2(tranForm).then(res=>{
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
          axioss.modifyinfo2(tranForm).then(res=>{
            this.linktodetail(); 
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
    togetherChange(id){
      var empty=[];
      var len=this.extraarr.length;
      for(var i=0;i<len;i++){
        if(this.extraarr[i].parentId==id){
          empty.push(this.extraarr[i])
        }
      }
      this.subsectorlist=empty;
    },
    classOperation(way,fundtypeid){
      if(way=="editor"){
        this.opreationType=way;
        axioss.reqdetails2(fundtypeid).then((res)=>{
          var tranRes=res.data.data;
          var closedate=tranRes.closedate;
          var formationdate=tranRes.formationdate;
          this.formData=tranRes;
        }).catch((res)=>{
          console.log(res);
        })
      }
    },
    scrolltoview(eletoview,arr){
      var obj=this.$refs[eletoview];
      if(!this.heightObj){
        this.heightObj=this.$refs["BasicInfo"].offsetHeight
      }
      if(!obj){
        this.$refs["BasicInfo"].style.height=43+"px";
        this.$refs["BasicInfo"].style.overflow='hidden';
      }
      if(obj){
        var scrolly=window.scrollY
        var _top=obj.getBoundingClientRect().top;
        var top=_top+scrolly-130;
        document.documentElement.scrollTop=top;
        obj.style.height=this.heightObj+"px"
      }
    }
    },
    components:{
      Header,
      Navlist,
      Commit
    } 	
}
</script>
<style scoped>
	.add-project{
        width:85%;
        margin-left:156px;
        float:left;
        margin-top:20px;
    }
    .add-project .big-base-container{
        /* border:1px solid #ddd; */
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
    .add-project .big-container{
        border:1px solid #ddd;
    }
    .add-project h3{
        font-size:16px;
        padding:5px 0;
        background:#eee;
    }
    .add-project .baseinfo-left,.add-project .baseinfo-right{
        width:50%;
        padding:10px;
        border:none;
    }
    .add-project h3,.add-project h2{
        padding-left:10px; 
    }
    .add-project .el-form-item label,.add-project .el-form-item .el-form-item__content{
        width:50%;
    }
</style>