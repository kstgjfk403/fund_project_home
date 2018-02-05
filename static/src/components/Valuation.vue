<template>
<div class="valuation loan">
    <Header></Header>
    <div class="valuation-top">
        <el-form :model="valuationFormData">
            <el-form-item label="Fund Family:">
                <el-radio-group v-model="fundFamilyId" @change="reqFundFamilyName">
                    <el-radio :key="item.baseId" :label="item.baseId" v-for="item in fundfamilyList">{{item.baseName}}</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="Fund Name">
                <el-radio-group v-model="valuationFormData.fundIds" style="display:inline-block;">
                    <el-radio :label="item.fundid" :key="item.fundid" v-for="item in checkList">{{item.fundname}}</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="Time">
                <el-date-picker v-model="valuationFormData.VALUEDATE" type="date" placeholder="选择日期"></el-date-picker>
                <el-button size="mini" type="primary" @click="searchData">Search</el-button>
            </el-form-item>
        </el-form>
    </div>
    <div class="valuation-bottom">
        <h3 class="h3">Valuation</h3>
        <div class="button-container">
            <el-button type="primary" size="mini" style="margin:5px 0;" @click="handleAdd">NEW</el-button>
        </div>
        <el-table :data="valuationData" border>
            <el-table-column prop="fundname" label="Foud"></el-table-column>
            <el-table-column prop="portfolioname" label="Portfolio"></el-table-column>
            <el-table-column prop="valuationdate" label="Valueation Date"></el-table-column>
            <el-table-column prop="valuationmethod" label="Method"></el-table-column>
            <el-table-column prop="valuation" label="Valueation"></el-table-column>
            <el-table-column prop="rating" label="Rating"></el-table-column>
            <el-table-column prop="valuationnote" label="Note"></el-table-column>
            <el-table-column label="操作" width="100">
                <template slot-scope="scope">
                    <i class="el-icon-edit"></i>
                    <i class="el-icon-delete" @click="deleteValuation(scope.$index,scope.row)"></i>
                    <i class="el-icon-info" style="color:#00a1e9;" @click="linkto(scope.$index,scope.row)"></i>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination-container">
            <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :current-page.sync="currentPage" @current-change="handleCurrentChange"></el-pagination>    
        </div>
    </div>
    <el-dialog title="valuation" :visible.sync="valuationVisible">
        <div class="select-container">
            <div class="select-fixed" style="overflow:hidden">
                <el-form :model="valuationForm" ref="valuationForm" :label-position='labelPosition'>
                    <el-form-item label="Fund">
                        <el-input v-model="fundname" placeholder="请输入内容" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="Protfolio">
                        <el-select v-model="valuationForm.portfolioid" placeholder="请选择" filterable>
                            <el-option v-for="item in fundprolist" :key="item.portfolioID"
                            :label="item.abbName" :value="item.portfolioID"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Valuation Date">
                        <el-date-picker v-model="valuationForm.valuationdate" type="date" placeholder="选择日期">
                    </el-date-picker>
                    </el-form-item>
                    <el-form-item label="ValuationMethod">
                        <el-select v-model="valuationForm.valuationmethod" placeholder="请选择" filterable @change="whichShow">
                            <el-option v-for="(item,index) in valueMethod" :label="item" :value="item" :key="index"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="BbaseValue" v-show="isShow">
                        <el-input v-model="baseValue" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="Multiple" v-show='isShow'>
                        <el-input v-model="multiple" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="fair value">
                        <el-input v-model="fairvalue" placeholder="请输入内容" :disabled="isShow"></el-input>
                    </el-form-item>
                    <el-form-item label="prop">
                        <el-input v-model="valuationForm.ownerShip" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="Valuation">
                        <el-input v-model="valuation" placeholder="请输入内容" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="折扣%">
                        <el-input v-model="valuationForm.discount" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="fin48tax">
                        <el-input v-model="valuationForm.fin48tax" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="Additional">
                        <el-input v-model="valuationForm.additional" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="NetValuation">
                        <el-input v-model="netvaluation" placeholder="请输入内容"></el-input>
                    </el-form-item>
                </el-form>
            </div>
            <hr/>
            <div class="company-input select-fixed" style="overflow:hidden;position:relative" v-show="isShow">
                <el-form :model="valuationForm" ref="valuationForm" :label-position='labelPosition'>
                    <el-form-item label="Company name">
                        <el-input v-model="companyForm.comparableName"></el-input>
                    </el-form-item>
                    <el-form-item label="Multiple">
                        <el-input v-model="companyForm.multiple"></el-input>
                    </el-form-item>
                    <el-form-item label="Weight">
                       <el-input v-model="companyForm.weight"></el-input>
                    </el-form-item>
                </el-form>
                 <i class="el-icon-circle-plus" style="color:red;position:absolute;top:10px;right:10px;" @click="addCompareCompany"></i>
            </div>        
            <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;width:70%;margin-left:20px;" v-show="isShow">
                <thead>
                    <tr>
                        <th scope="col">Company name</th>
                        <th scope="col">Multiple</th>
                        <th scope="col">Weight</th>
                        <th scope="col">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in valuationTableData">
                        <td>{{item.comparableName}}</td>
                        <td>{{item.multiple}}</td>
                        <td>{{item.weight}}</td>
                        <td><i class="el-icon-delete"  @click="deleteComparable(item.cid)"></i></td>
                    </tr>   
                </tbody>
            </table>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="valuationVisible = false" size='mini'>取 消</el-button>
            <el-button type="primary" size='mini' @click="submitForm('valuationForm')">新建</el-button>
        </div>
    </el-dialog>
</div>
</template>
<script>	
import axioss from '@/api/axios';
import * as method from "@/api/method";
import Header from "./common/Header";
export default {
    name:"Valuation",
    data(){  
        return {
            isShow:false,
            currentPage:1,
            allcount:0,
            checkList:'',
            fundfamilyList:[],
            fundFamilyId:'',
            fundname:'',
            labelPosition:'right',
            valuationVisible:false,
            valuationData:[],
            fundprolist:[],
            valueMethod:[],
            baseValue:'',
            fairvalue:'',
            multiple:'',
            valuationFormData:{
                fundIds:'',
                VALUEDATE:''
            },
            companyForm:{
                multiple:'',
                weight:'',
                valueDate:'',
                comparableName:'',
                pid:''
            },
            valuationForm:{
                fundid:'',
                portfolioid:"",
                valuationmethod:'',
                valuationdate:'',
                fairvalue:'',
                discount:'0',
                fin48tax:'0',
                ownerShip:'',
                additional:'0',
                valuation:'',
                rating:'',
                multiple:'',
                netvaluation:''
            },
            valuationFormEmpty:{
                fundid:'',portfolioid:"",valuationmethod:'',
                valuationdate:'',fairvalue:'',discount:'1',
                fin48tax:'0',ownerShip:'',additional:'0',
                valuation:'',rating:'',multiple:''
            },
            valuationTableData:[],
            radio2:''
        }
    },
    mounted(){
        this.reqselectlist();
        this.valuationForm.valuation=this.valuation;
    },
    methods:{
        reqselectlist(){
            axioss.reqselectlist().then(res=>{
                this.fundfamilyList=res.data.data;
            })
        },
        reqFundFamilyName(id){
            this.checkList=[];
            var fundfamilyid=id||this.fundFamilyId;
            axioss.reqFundFamilyName(fundfamilyid).then(res=>{
                this.checkList=res.data.data;
            })
        },
        reqfundproList(){
            var id=this.valuationFormData.fundIds;
            axioss.reqfundproList(id).then(res=>{
                console.log(res);
                this.fundprolist=res.data.data;
            })
        },
        reqValueMethod(){
            axioss.reqValueMethod().then(res=>{
                console.log(res)
                this.valueMethod=res.data.data;
            })
        },
        reqIndiction(){
            var obj={};
            obj.portfolioid=this.valuationForm.portfolioid;
            obj.valuationdate=this.valuationForm.valuationdate;
            obj.valuationmethod=this.valuationForm.valuationmethod;
            console.log(obj)
            if(this.valuationForm.portfolioid&&this.valuationForm.valuationdate){
                axioss.reqIndiction(obj).then(res=>{
                    //如果是空的时候提示没有获得估值数据.//
                    console.log(res)
                    Object.assign(this.valuationForm,res.data.data)
                })
            }
        },
        queryLastLRound(){
            var obj={};
            obj.fundid=this.valuationFormData.fundIds;
            obj.portfolioid=this.valuationForm.portfolioid;
            obj.valuationdate=this.valuationForm.valuationdate;
            axioss.queryLastLRound(obj).then(res=>{
                Object.assign(this.valuationForm,res.data.data);
                this.fairvalue=this.valuationForm.fairvalue;
            })
        },
        reqCompareList(obj){
            axioss.reqCompareList(obj).then(res=>{
                console.log(res)
                this.valuationTableData=res.data.data;
                this.calculateMultiple();
            })
        },
        searchData(){
            var obj={},ids;
            //ids=this.valuationFormData.fundIds.slice(-this.checkList.length)//解决引用类型数据追加问题,不能清空的问题。
            //obj.fundIds=ids;
            obj.valuationdate=this.valuationFormData.VALUEDATE;
            var newobj={
                pageIndex:this.currentPage,
                pageCount:10
            }
            Object.assign(this.valuationFormData,newobj);
            axioss.reqValList(this.valuationFormData).then(res=>{
                console.log(res)
                this.valuationData=res.data.data;
                this.allcount=res.data.count;
            })
        },
        addCompareCompany(){
            this.companyForm.pid=this.valuationForm.portfolioid;
            this.companyForm.valuationdate=this.valuationForm.valuationdate;
            console.log(this.companyForm);
            axioss.addCompareCompany(this.companyForm).then(res=>{
                console.log(res);
                if(res.data.code=="SUCCESS"){
                    var obj={};
                    obj.pid=this.valuationForm.portfolioid;
                    obj.valuationdate=this.valuationForm.valuationdate;
                    this.reqCompareList(obj);
                }
            })
        },
        submitForm(formName){
            this.$refs[formName].validate((validate)=>{
                if(validate){
                    this.valuationForm.fundid=this.valuationFormData.fundIds;
                    this.valuationForm.valuation=this.valuation;
                    this.valuationForm.netvaluation=this.netvaluation;
                    this.valuationForm.fairvalue=this.fairvalue;
                    this.valuationForm.multiple=this.multiple;
                    console.log(this.valuationForm)
                    axioss.addvaluation(this.valuationForm).then(res=>{
                        if(res.data.code=="SUCCESS"){
                            this.$message({
                                type:'success',
                                message: '创建成功'
                            })
                        this.searchData();
                        Object.assign(this.valuationForm,this.valuationFormEmpty);
                        this.fairvalue="";
                        this.multiple="";
                        this.valuationVisible=false;
                        }else{
                            this.$message({
                                type:'warning',
                                message: '创建失败'
                            })
                        }
                    })
                }else{
                    console.log('err submit')
                }
            })
        },
        deleteComparable(cid){
            console.log(cid)
            axioss.deleteComparable(cid).then(res=>{
                console.log(res);
                if(res.data.code=="SUCCESS"){
                    var obj={};
                    obj.pid=this.valuationForm.portfolioid;
                    obj.valuationdate=this.valuationForm.valuationdate;
                    this.reqCompareList(obj);
                }else{
                    this.$message({
                        type:'warning',
                        message: '删除失败'
                    })
                }
            })
        },
        deleteValuation(index,rowdata){
            console.log(rowdata.valuationid)
            axioss.deleteValuation(rowdata.valuationid).then(res=>{
                if(res.data.code=="SUCCESS"){
                    this.searchData()
                }else{
                    this.$message({
                        type:'warning',
                        message: '删除失败'
                    })
                }
            })
        },
        handleAdd(){
            this.valuationVisible=true;
            this.fetchFundName();
            this.reqfundproList();
            this.reqValueMethod()
        },
        handleCurrentChange(){
            this.searchData();
        },
        fetchFundName(){
            var len=this.checkList.length;
            for(var i=0;i<len;i++){
                if(this.checkList[i].fundid==this.valuationFormData.fundIds){
                    this.fundname=this.checkList[i].fundname;
                }
            }
        },
        whichShow(val){
            this.isShow=false;
            if(val=='PE'||val=="PS"||val=="PB"){
                this.isShow=true;
                this.reqIndiction();
                this.reqCompareList(this.reqcompanyobj);    
            }else if(val="LASTROUND"){
                this.queryLastLRound()
            }
        },
        calculateMultiple(){
            var tem=this.valuationForm.valuationmethod;
            var data=this.valuationTableData,len=data.length,multiple=0;
            for(var i=0;i<len;i++){
                multiple+=data[i].multiple*data[i].weight;
            }
            this.multiple=multiple;
        },
        linkto(index,data){
            var method=data.valuationmethod;
            if(method=='OPM'){
                window.open(window.location.origin+"/"+"#/"+"valuationreportopm/"+"?valuationid="+data.valuationid)
                //this.$router.push({path:'valuationreportopm',query:{valuationid:data.valuationid}});
            }else if(method=='PS'||method=='PE'||method=='PB'){
                window.open(window.location.origin+"/"+"#/"+"valuationreport/"+"?valuationid="+data.valuationid)
                //this.$router.push({path:'valuationreport',query:{valuationid:data.valuationid}});
            }else{
                this.$message({
                    showClose: true,
                    message: '这条记录没有对应的报表',
                    type: 'warning'
                });
            }
        }
    },
    watch:{
        multiple:function(newdata,olddata){
            this.multiple=Math.floor(newdata);
            this.fairvalue=(newdata*this.baseValue).toFixed(2);
        },
        baseValue:function(newdata,olddata){
            this.fairvalue=(this.multiple*newdata).toFixed(2);
        }
    },
    computed:{
        portfolioid:function(){
            if(this.$store.state.portfolioid==''){
                this.$store.dispatch('updateData');
            }
            return this.$store.state.portfolioid
        },
        netvaluation:{
            get:function(){
                var tem=this.valuationForm.valuationmethod,val='';
                var fairvalue=this.fairvalue,ownerShip=this.valuationForm.ownerShip,
                discount=this.valuationForm.discount,additional=this.valuationForm.additional,
                fin48tax=this.valuationForm.fin48tax,multiple=this.valuationForm.multiple;
                if(tem=='PE'||tem=="PS"||tem=="PB"){
                    fairvalue=this.fairvalue;
                }else{
                    fairvalue=this.valuationForm.fairvalue?this.valuationForm.fairvalue:fairvalue;
                }
                val=fairvalue*ownerShip*(1-discount/100)+parseInt(additional)-fin48tax;
                return val.toFixed(2);
            },
            set:function(){}
        },
        valuation(){
            var val=this.fairvalue*this.valuationForm.ownerShip
            return parseInt(val);
        },
        reqcompanyobj(){
            var obj={};
            obj.valuationdate=this.valuationForm.valuationdate;
            obj.pid=this.valuationForm.portfolioid;
            return obj;
        }
    },
    components:{
        Header
    }
}
</script>
<style scoped lang="scss">
    .valuation{
        width:95%;
        margin:0 auto;
    }
    .valuation-bottom{
        border-left:1px solid #eee;
        border-right:1px solid #eee;
    }
	.valuation .h3{
        font-size:16px;
        padding:5px 0;
        padding-left:10px;
        background:#eee;
        margin: 0;
        border-bottom: 1px solid #ebeef5;
    }
    .el-pagination{
        text-align:center;
    }
</style>