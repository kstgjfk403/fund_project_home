<template>
<div class="add-project">
    <Header></Header>
    <div class="big-container" ref="BasicInfo">
    <div class="title">BasicInfo</div>
    <el-form :label-position="labelposition" :model="formData" :rules="rules" ref="userform">
        <h3>Essential information </h3>
        <div class="base-info-container">
            <div class="baseinfo-left">
                <el-form-item label="Project Name" prop="abbname">
                    <el-input v-model="formData.abbname"></el-input>
                </el-form-item>
                <el-form-item label="Company Legal Name" prop="fullname">
                    <el-input v-model="formData.fullname"></el-input>
                </el-form-item>
                <el-form-item label="Report Name" prop="reportname">
                    <el-input v-model="formData.reportname"></el-input>
                </el-form-item>
                <el-form-item label="Portfolio Status">
                    <el-select v-model="formData.portfoliostatus">
                    <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.DDL_PortfolioStatus"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="IDG Role(Lead/Co-invest)">
                    <el-select v-model="formData.idgroleid">
                        <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.DDL_IDGRole"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Initial Role" multiple>
                    <el-select v-model="formData.initialroleid">
                        <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.DDL_InitialRole"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Industry Sector" prop="sectorid">
                    <el-select v-model="formData.sectorid" filterable  @change="togetherChange">
                        <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.DDL_Sector"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="Anticipated Exit">
                    <el-select v-model="formData.antidilutionid">
                    <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.DDL_AntiDilution"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Incorprated In">
                    <el-select v-model="formData.locationid">
                    <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.DDL_IncorpLocation"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Company Register No." prop="registerno">
                    <el-input v-model="formData.registerno"></el-input>
                </el-form-item>

                <el-form-item label="Financial YTD">
                    <el-select v-model="formData.ytd" filterable>
                        <el-option :key="item.baseId"
                        :label="item.baseId"
                        :value="item.baseId"
                        v-for="item in dictarraylist.YTD"></el-option>
                    </el-select>
                </el-form-item>
            </div>
            <div class="baseinfo-right">
                <el-form-item label="项目名称" prop="abbnamechi">
                    <el-input v-model="formData.abbnamechi"></el-input>
                </el-form-item>
                <el-form-item label="公司名称" prop="fullnamechi">
                    <el-input v-model="formData.fullnamechi"></el-input>
                </el-form-item>
                <el-form-item label="CEO" prop="ceo">
                    <el-input v-model="formData.ceo"></el-input>
                </el-form-item>
                <el-form-item label="Portfolio Status Date">
                    <el-date-picker v-model="formData.portfoliostatusdate" type="date" placeholder="选择日期"></el-date-picker>
                </el-form-item>
                <el-form-item label="Initial Stage">
                    <el-select v-model="formData.stageid">
                    <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.DDL_Stage"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="IDG First Investment">
                    <el-select v-model="formData.firstinvestid">
                    <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.DDL_IDGFirstInvest"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Sub Sector" prop="subsectorid">
                    <el-select v-model="formData.subsectorid">
                    <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in subsectorlist"></el-option>
                    </el-select>
                </el-form-item>
                <!-- <el-form-item label="Total Board Representative No.">
                    <el-input v-model="formData.totalboardseatno"></el-input>
                </el-form-item> -->
                <el-form-item label="Fund of Fund" prop="fundoffund">
                    <el-checkbox-group v-model="formData.fundoffund">
                        <el-checkbox label="" value="true" name="type"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="Year of Incorpration">
                    <el-date-picker v-model="formData.foundeddate"
                        align="right"
                        type="year"
                        placeholder="选择年" format='yyyy'>
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Investment structure legal form">
                    <el-select v-model="formData.formid">
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
                    <el-select v-model="formData.projectmanager" multiple filterable>
                        <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.IDG_Staff"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Project Buddy" prop="projectbuddy">
                    <el-select v-model="formData.projectbuddy" multiple filterable>
                        <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.IDG_Staff"></el-option>
                    </el-select>
                </el-form-item>
                <!-- <el-form-item label="IDG Board Representative">
                    <el-select v-model="formData.idgboardrepresentative" multiple filterable>
                        <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.IDG_Staff"></el-option>
                    </el-select>
                </el-form-item> -->
            </div>
            <div class="baseinfo-right">
                <el-form-item label="IDG Inhouse Counsel">
                    <el-select v-model="formData.idginhousecounsel" multiple filterable>
                        <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.IDG_Staff"></el-option>
                    </el-select>
                </el-form-item>
                <!-- <el-form-item label="Observer" prop="observer">
                    <el-select v-model="formData.observer" multiple filterable>
                        <el-option :key="item.baseId"
                        :label="item.baseName"
                        :value="item.baseId"
                        v-for="item in dictarraylist.IDG_Staff"></el-option>
                    </el-select>
                </el-form-item> -->
                <el-form-item label="Comment(Memo)">
                    <el-input type="textarea" v-model="formData.memo"></el-input>
                </el-form-item>
            </div>
        </div>
        <h3>Contact information </h3>
        <div class="base-info-container">
            <div class="baseinfo-left">
                <el-form-item label="Company Website" prop="website">
                    <el-input v-model="formData.website"></el-input>
                </el-form-item>
                <el-form-item label="Fax" prop="fax">
                    <el-input v-model="formData.fax"></el-input>
                </el-form-item>
                <el-form-item label="Business Address">
                    <el-input type="textarea" v-model="formData.address" style="min-height:75px;"></el-input>
                </el-form-item>
                <el-form-item label="Primary Business">
                    <el-input type="textarea" v-model="formData.primarybusiness" style="min-height:75px;"></el-input>
                </el-form-item>
                <el-form-item label="Line2" prop="auditconfaddress2">
                    <el-input v-model="formData.auditconfaddress2"></el-input>
                </el-form-item>
                <el-form-item label="Line3">
                    <el-input type="textarea" v-model="formData.auditconfaddress3"></el-input>
                </el-form-item>
            </div>
            <div class="baseinfo-right">
                <el-form-item label="	Tel." prop="telephone">
                    <el-input v-model="formData.telephone"></el-input>
                </el-form-item>
                <el-form-item label="Product(White Book)" prop="product">
                    <el-input v-model="formData.product"></el-input>
                </el-form-item>
                <el-form-item label="Comment" prop="remarkes">
                    <el-input v-model="formData.remarkes"></el-input>
                </el-form-item>
                <el-form-item label="Company HeadCount" prop="headcount">
                    <el-input v-model="formData.headcount"></el-input>
                </el-form-item>
                <el-form-item label="Nominee Name" prop="nomineename">
                    <el-input v-model="formData.nomineename"></el-input>
                </el-form-item>
                <el-form-item label="Audit Conf Address" prop="auditconfaddress">
                    <el-input v-model="formData.auditconfaddress"></el-input>
                </el-form-item>
            </div>
            <el-form-item label="One line Description" class="oneline">
                <el-input type="textarea" v-model="formData.onelinedesc"></el-input>
            </el-form-item>
        </div>
        <el-form-item class="addpro-button">
            <div v-if="opreationType=='creatuserform'">
                <el-button type="primary" size="mini" @click="submitForm1('userform')">Submit</el-button>
                <router-link to="listpage" style="margin-left:10px"><el-button size="mini">Return</el-button></router-link>
            </div>
            <div v-else>
                <el-button type="primary" size="mini" @click="submitForm2('userform')">Submit</el-button>
                <el-button size="mini" type="primary" @click="linktodetail">Return</el-button>
            </div>
        </el-form-item>
    </el-form>
    </div>
    <Ivestment></Ivestment>
    <CapTable></CapTable>
    <FinancialInfo></FinancialInfo>
    <Director :totalboardseatno='formData.totalboardseatno'></Director>
    <MainBonus></MainBonus>
    <Business></Business>
    <Navlist v-on:toscorll="scrolltoview" :isactive='isActive'></Navlist>

</div>
</template>
<script>
import Ivestment from '@/page/InvestManage/investmanager';
import Header from "./common/Header";
import CapTable from "@/page/capTable/capTable";
import FinancialInfo from "@/page/financial/FinancialInformation";
import Director from "@/page/directorandsuper/Director";
import MainBonus from "@/page/bonus/MainBonus";
import Business from "@/page/business/Business";
import * as method from "../api/method";
import axioss from '../api/axios';
import Navlist from '@/components/common/Nav';
import store from '@/vuex/store';
export default {
    name:"AddProject",
    data:function(){
      return {
        totalboardseatno:'',
        labelposition:"right",
        dictarraylist:'',
        subsectorlist:[],
        extraarr:[],
        foundeddatee:"",
        opreationType:'creatuserform',
        heightObj:'',
        isActive:{
            BasicInfo:true,
            Invest:false,
            CapTable:false,
            Financial:false,
            Director:false,
            Bonus:false,
            Business:false,
        },
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
          fullname:[
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
            {message: 'required', trigger: 'blur' }
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
        var portfolioid=this.$route.query.portfolioid;
        var way=this.$route.query.path;
        this.classOperation(way,portfolioid);
        this.$store.dispatch('storedata',portfolioid);

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
              })
       },
       linktodetail(){
           var id=this.formData.portfolioid;
           this.$router.push({ path: "detail", query: { portfolioid: id } })
       },
       linktolist(){
           this.$router.push({name:'listpage'});
       },
       submitForm1(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
              axioss.submitinfo(this.formData).then(res=>{
                  var flag=res.data.code=="SUCCESS";
                  if(flag){
                      this.$message({
                          type:'success',
                          message:'创建成功'
                      })
                      this.linktolist();
                  }else{
                      this.$message({
                          type:'warning',
                          message:'创建失败'
                      })
                  }
              }).catch(res => {
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
              axioss.modifyinfo(this.formData).then(res=>{
                  this.linktodetail();
              }).catch(res => {
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
      togetherChange(id){
          this.formData.subsectorid='';
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
                var newobjdetail=method.deleteattr(detailobj);
                var obj=method.nulltostr(newobjdetail);
                obj.projectmanager=obj.projectmanager==''?[]:obj.projectmanager.split(",");
                obj.projectbuddy=obj.projectbuddy==''?[]:obj.projectbuddy.split(",");
                obj.idgboardrepresentative=obj.idgboardrepresentative==''?[]:obj.idgboardrepresentative.split(",");
                obj.idginhousecounsel=obj.idginhousecounsel==''?[]:obj.idginhousecounsel.split(",")
                obj.observer=obj.observer==''?[]:obj.observer.split(",");
                obj.privateorpublic="Realized";
                delete obj.heldbyspvflag;
                delete obj.heldbyspv;
                this.formData=obj;
                this.$store.dispatch('storetotalboardseatno',obj.totalboardseatno)
            }).catch((res)=>{
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
        Ivestment,
        Navlist,
        CapTable,
        FinancialInfo,
        Director,
        MainBonus,
        Business
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
    .add-project .big-container{
        border:1px solid #ddd;
    }
    .add-project .base-info-container{
        border:1px solid #ddd;
        box-shadow:0 0 2px #eee;
        border-radius:3px;
        border-left:none;
        border-right:none;
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
        font-size:16px;
        padding:5px 0;
        background:#eee;
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
