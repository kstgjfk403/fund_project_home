<template>
<div class="add-project">
    <Header></Header>
    <!-- <h2>添加项目信息</h2> -->
    <el-form :label-position="labelposition" :model="formData" :rules="rules" ref="userform">
        <h3>Essential information </h3>
        <div class="base-info-container">
            <div class="baseinfo-left">
                <el-form-item label="Project Name" prop="abbname">
                    <el-input v-model="formData.abbname" placeholder="pls enter projectname"></el-input>
                </el-form-item>
                <el-form-item label="Compony Lagel Name" prop="companyname">
                    <el-input v-model="formData.companyname" placeholder="pls enter company name"></el-input>
                </el-form-item>
                <el-form-item label="Report Name" prop="reportname">
                    <el-input v-model="formData.reportname" placeholder="pls enter report name"></el-input>
                </el-form-item>
                <el-form-item label="Portflio Status">
                    <el-select v-model="formData.portfoliostatus" placeholder="请选择活动区域">
                    <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.DDL_PortfolioStatus"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="IDG Role">
                    <el-select v-model="formData.idgroleid" placeholder="请选择活动区域">
                        <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.DDL_IDGRole"></el-option>   
                    </el-select>
                </el-form-item>
                <el-form-item label="Initial Role" multiple>
                    <el-select v-model="formData.initialroleid" placeholder="请选择活动区域">
                        <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.DDL_InitialRole"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Industry Sector" prop="sectorid">
                    <el-select v-model="formData.sectorid" placeholder="多选+搜索select框" filterable  @change="togetherChange">
                        <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.DDL_Sector"></el-option>
                    </el-select>
                </el-form-item>
                
                <el-form-item label="Anticipated Eixt">
                    <el-select v-model="formData.antidilutionid" placeholder="请选择活动区域">
                    <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.DDL_AntiDilution"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Incorprated In">
                    <el-select v-model="formData.locationid" placeholder="请选择活动区域">
                    <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.DDL_IncorpLocation"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Company register No" prop="registerno">
                    <el-input v-model="formData.registerno" placeholder="pls company register no"></el-input>
                </el-form-item>
                
                <el-form-item label="Financial YTD"> 
                    <el-select v-model="formData.ytd" placeholder="select框" filterable>
                        <el-option :key="item.baseId" 
                        :label="item.baseId" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.YTD"></el-option>
                    </el-select>
                </el-form-item>
            </div>
            <div class="baseinfo-right">
                <el-form-item label="项目名称" prop="abbnamechi">
                    <el-input v-model="formData.abbnamechi" placeholder="请输入项目名称"></el-input>
                </el-form-item>
                <el-form-item label="公司名称" prop="fullnamechi">
                    <el-input v-model="formData.fullnamechi" placeholder="请输入公司名称"></el-input>
                </el-form-item>
                <el-form-item label="CEO" prop="ceo">
                    <el-input v-model="formData.ceo" placeholder="pls enter ceo"></el-input>
                </el-form-item>
                <el-form-item label="Portfolio Status Date">
                    <el-date-picker v-model="formData.portfoliostatusdate" type="date" placeholder="选择日期"></el-date-picker>
                </el-form-item>
                <el-form-item label="Inistial Stage">
                    <el-select v-model="formData.stageid" placeholder="请选择活动区域">
                    <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.DDL_Stage"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="IDG First Investment">
                    <el-select v-model="formData.firstinvestid" placeholder="请选择活动区域">
                    <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.DDL_IDGFirstInvest"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Sub Sector" prop="subsectorid">
                    <el-select v-model="formData.subsectorid" placeholder="请选择活动区域">
                    <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in subsectorlist"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Total Border Representative" prop="totalboardseatno">
                    <el-input v-model="formData.totalboardseatno" placeholder="pls enter total border representative"></el-input>
                </el-form-item>
                <el-form-item label="Fund of Fund" prop="fundoffund">
                    <el-checkbox-group v-model="formData.fundoffund">
                        <el-checkbox label="基金" value="true" name="type"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="Year of Incorpration">                    
                    <el-date-picker v-model="formData.foundeddate"
                        align="right"
                        type="year"
                        placeholder="选择年" format='yyyy'>
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Ivestment Structure legal form">
                    <el-select v-model="formData.formid" placeholder="请选择活动区域">
                    <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.DDL_CompanyLegalForm"></el-option>
                    </el-select>
                </el-form-item>
            </div>
        </div>
        <h3>Personnel information</h3>
        <div class="base-info-container">
            <div class="baseinfo-left">
                <el-form-item label="Project Manager" prop="projectmanager">
                    <el-select v-model="formData.projectmanager" placeholder="多选+搜索select框" multiple filterable>
                        <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.IDG_Staff"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Project Buddy" prop="projectbuddy">
                    <el-select v-model="formData.projectbuddy" placeholder="多选+搜索select框" multiple filterable>
                        <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.IDG_Staff"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="IDG board representative">
                    <el-select v-model="formData.idgboardrepresentative" placeholder="多选+搜索select框" multiple filterable>
                        <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.IDG_Staff"></el-option>
                    </el-select>
                </el-form-item>             
            </div>
            <div class="baseinfo-right">
                <el-form-item label="IDG Inhouse Counsel">
                    <el-select v-model="formData.idginhousecounsel" placeholder="多选+搜索select框" multiple filterable>
                        <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.IDG_Staff"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Observer" prop="observer">
                    <el-select v-model="formData.observer" placeholder="多选+搜索select框" multiple filterable>
                        <el-option :key="item.baseId" 
                        :label="item.baseName" 
                        :value="item.baseId" 
                        v-for="item in dictarraylist.IDG_Staff"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Comment(Memo)">
                    <el-input type="textarea" v-model="formData.memo"></el-input>
                </el-form-item>
            </div>
        </div>
        <h3>Contact information </h3>
        <div class="base-info-container">
            <div class="baseinfo-left">
                <el-form-item label="Company Website" prop="website">
                    <el-input v-model="formData.website" placeholder="pls enter company website"></el-input>
                </el-form-item>
                <el-form-item label="Fax" prop="fax">
                    <el-input v-model="formData.fax" placeholder="pls enter fax"></el-input>
                </el-form-item>               
                <el-form-item label="Business Adress">
                    <el-input type="textarea" v-model="formData.address" style="min-height:75px;"></el-input>
                </el-form-item>
                <el-form-item label="Primary Business">
                    <el-input type="textarea" v-model="formData.primarybusiness" style="min-height:75px;"></el-input>
                </el-form-item>               
                <el-form-item label="Line2" prop="auditconfaddress2">
                    <el-input v-model="formData.auditconfaddress2" placeholder="pls enter line"></el-input>
                </el-form-item>
                <el-form-item label="Line3">
                    <el-input type="textarea" v-model="formData.auditconfaddress3"></el-input>
                </el-form-item>                                       
            </div>
            <div class="baseinfo-right">
                <el-form-item label="Tel" prop="telephone">
                    <el-input v-model="formData.telephone" placeholder="pls enter tel"></el-input>
                </el-form-item>
                <el-form-item label="Product(White Book)" prop="product">
                    <el-input v-model="formData.product" placeholder="pls enter product"></el-input>
                </el-form-item>
                <el-form-item label="Comment" prop="remarkes">
                    <el-input v-model="formData.remarkes" placeholder="pls enter comment"></el-input>
                </el-form-item>               
                <el-form-item label="Company HeadCount" prop="headcount">
                    <el-input v-model="formData.headcount" placeholder="pls enter company head count"></el-input>
                </el-form-item>
                <el-form-item label="Nominee Name" prop="nomineename">
                    <el-input v-model="formData.nomineename" placeholder="pls enter nominee name"></el-input>
                </el-form-item>
                <el-form-item label="Audit Conf Adress" prop="auditconfaddress">
                    <el-input v-model="formData.auditconfaddress" placeholder="pls enter audit conf adress"></el-input>
                </el-form-item>
                   
            </div>
            <el-form-item label="One line Discription" class="oneline">
                <el-input type="textarea" v-model="formData.onelinedesc"></el-input>
            </el-form-item>
        </div>
        <el-form-item style="margin-top:15px;">
            <el-button type="primary" size="mini" @click="submitForm1('userform')" v-if="opreationType=='creatuserform'">创建</el-button>
            <el-button type="primary" size="mini" @click="submitForm2('userform')" v-else>更新</el-button>
            <el-button size="mini" @click="resetForm('userform')">重置</el-button>
            <router-link to="listpage" style="margin-left:10px"><el-button size="mini">返回</el-button></router-link>
        </el-form-item> 
    </el-form>
</div>
</template>
<script>
import Header from "./common/Header";
import * as method from "../api/method";	
import axioss from '../api/axios';
export default {
    name:"AddProject",
    data:function(){  
      return {
        labelposition:"right",
        dictarraylist:'',
        subsectorlist:[],
        extraarr:[],
        foundeddatee:"",
        opreationType:'creatuserform',
        formData: {
          abbname: '',
          abbnamechi: '',
          fullname: '',
          fullnamechi: '',
          reportname: '',
          ceo: '',
          registerno:"",
          totalboardseatno:'',
          headcount:'',
          website:'',
          fax:'',
          telephone:'',
          product:'',
          remarkes:'',
          nomineename:'',
          auditconfaddress:'',
          auditconfaddress2:'',
          idgboard:'',
          address:'',
          primarybusiness:'',
          onelinedesc:'',
          auditconfaddress3:'',
          ytd:'',
          locationid:'',
          antidilutionid:'',
          sectorid:'',
          subsectorid:'',
          initialroleid:'',
          idgroleid:[],
          formid:'',
          foundeddate:'',
          fundoffund:'',
          //idgfirstinvestment:[],//有问题，没有对应项;
          stageid:'',
          portfoliostatus:'',
          portfoliostatusdate:'',
          projectmanager:[],
          idginhousecounsel:[],
          observer:[],
          idgboardrepresentative:[],
          memo:'',
          projectbuddy:'',
          firstinvestid:'',
          logo: "../docs/logos/qiutan(qiuba).jpg",//从这里开始新增字段，前端界面没有对应的显示；
          onelinedeschi: null,
          addresschi: "广州市天河区科韵路24-26号测绘大厦809室",
          privateorpublic: "Realized",//不确定是否增加，后期做了修改；
          adrratio: "",
          lockupperiod: "",
          lockupexpireddate: -2209017600000,
          stockexchangeid: 0,
          stockcode: "",
          spvjurisdition: "",
          approved: false,
          idgboardseatno: 1,
          iceforceid: null,
          idgonboardflag: true,
        },
        
        rules: {
          abbname: [
            { message: 'required', trigger: 'blur' }
          ],
          abbnamechi: [
            {message: '必填', trigger: 'blur' }
          ],
          companyname:[
            { message: 'required', trigger: 'blur' }      
          ],
          fullnamechi:[
            { message: '必填', trigger: 'blur' }
          ],
          projectmanager:[
            {type:'array',message: 'required', trigger: 'change' }
          ],
          projectbuddy:[
            {type:'array', message: 'required', trigger: 'change' }
          ],
          sectorid:[
              { type:'number', message: 'required', trigger: 'change'}
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
          totalboardseatno: [
            {type:"number", message: 'required', trigger: 'blur' }
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
    mounted:function(){
        this.requserlist();
        var way=this.$route.query.path;
        var portfolioid=this.$route.query.portfolioid;
        this.classOperation(way,portfolioid);
    },
    methods:{
       requserlist(){
           var enterobj={
               dictArray:'DDL_IDGRole,DDL_Stage,DDL_InitialRole,DDL_IDGFirstInvest,DDL_Sector,DDL_SubSector,DDL_AntiDilution,DDL_IncorpLocation,DDL_CompanyLegalForm,DDL_PortfolioStatus,DDL_StockExchange,IDG_Staff,YTD'
           }
           axioss.reqdroplist(enterobj).then(res=>{
                this.dictarraylist=method.translateFormat(res.data.data);
                this.subsectorlist=this.dictarraylist.DDL_SubSector;
                this.extraarr=this.dictarraylist.DDL_SubSector.slice();  
              }).catch(res=>{
                console.log(res);
              })
       },
       linktodetail(id){
           this.$router.push({ path: "detail", query: { portfolioid: id } })
       },
       submitForm1(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
              axioss.submitinfo(this.formData).then(res=>{
                  //console.log(res)
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
      submitForm2(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
              //console.log(this.formData)
              axioss.modifyinfo(this.formData).then(res=>{
                  //console.log(res)
                  this.linktodetail(this.formData.portfolioid); 
              }).catch(res => {
                  //console.log(res)
              })
          } else {
            this.$message({
                type:"warning",
                message:"您输入的内容格式不正确"
            })
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
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
      classOperation(way,portfolioid){
          if(way=="editor"){
            this.opreationType=way;
            axioss.reqdetails(portfolioid).then((res)=>{
                var detailobj=res.data.data;
                //console.log(detailobj);
                var newobjdetail=method.deleteattr(detailobj);
                var obj=method.nulltostr(newobjdetail);
                //console.log(obj)
                obj.projectmanager=obj.projectmanager.split(","); 
                obj.projectbuddy=obj.projectbuddy.split(",");
                obj.idgboardrepresentative=obj.idgboardrepresentative.split(",");
                obj.idginhousecounsel=obj.idginhousecounsel.split(",")
                obj.observer=obj.observer.split(",");
                obj.privateorpublic="Realized";
                delete obj.heldbyspvflag;
                delete obj.heldbyspv;
                this.formData=obj;
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
    .add-project .base-info-container{
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
        flex-wrap:wrap;
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