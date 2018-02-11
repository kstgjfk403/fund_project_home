<template>
<div class="bonus loan">
    <div class="loan-table-container">
        <h3 class="h3">Bonus Shares</h3>
        <el-table :data="BonusData" border>
            <el-table-column fixed prop="eiid" label="EIID" width="70"></el-table-column>
            <el-table-column prop="termsigndate" label="Term Sign Date" width="130"></el-table-column>
            <el-table-column prop="fundname" label="Fund" width="130"></el-table-column>
            <el-table-column prop="investtype" label="Invest Type" width="150"></el-table-column>
            <el-table-column prop="securitytypeidstr" label="Share Type" width="150"></el-table-column>
            <el-table-column prop="closedate" label="Payment Date" width="150"></el-table-column>
            <el-table-column prop="round" label="Round" width="110"></el-table-column>
            <el-table-column prop="shareownedno" label="Shares Acquired" width="150" :formatter="numberFormat" ></el-table-column>
            <el-table-column prop="cost" label="Cost" width="100" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="otherfees" label="Fees" width="80" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="taxlotdate" label="Tax Lot Date" width="150"></el-table-column>
            <el-table-column prop="vouncher" label="vouncher" width="150"></el-table-column>
          <el-table-column prop="conversionratio" label="Rate" width="70"></el-table-column>
          <el-table-column prop="convertamount" label="convert amount" width="150"></el-table-column>
            <el-table-column label="操作" width="60" fixed='right' v-if="isDetail!='false'">
                <template slot-scope="scope">
                    <i class="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></i>
                    <i class="el-icon-delete" @click="handleDelet(scope.$index, scope.row)"></i>
                </template>
            </el-table-column>
        </el-table>
        <div class="table-foot">
            <i class="el-icon-circle-plus" @click="handleAdd" v-if="isDetail!='false'"></i>
        </div>
    </div>
    <el-dialog title="Bonus Shares" :visible.sync="BonusVisible">
    <div class="select-container">
        <el-form :model="BonusForm" :label-position='labelPosition' ref="BonusForm">
            <div class="select-fixed">
                <el-form-item label="Bonus Shares">
                    <el-select v-model="BonusForm.investtype" placeholder="请选择" disabled>
                        <el-option v-for="item in investTypeList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Payment Date">
                    <el-date-picker v-model="BonusForm.closedate" type="date" placeholder="选择日期" :disabled="isDisable">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Share Type">
                    <el-select v-model="BonusForm.securitytypeid" placeholder="请选择">
                        <el-option v-for="item in shareTypeList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Fund">
                    <el-select v-model="BonusForm.fundname" placeholder="请选择" :disabled="isDisable" filterable>
                        <el-option v-for="item in fundList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId" @click.native='acqFundName'></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Fund Family">
                    <el-select v-model="BonusForm.fundfamillyname" placeholder="请选择" :disabled="isDisable" filterable>
                        <el-option v-for="item in fundFamilyList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseName"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="No of Shares">
                    <el-input v-model="BonusForm.shareownedno"></el-input>
                </el-form-item>
                <el-form-item label="Round">
                    <el-input v-model="BonusForm.round"></el-input>
                </el-form-item>
                <el-form-item label="Currency">
                    <el-select v-model="BonusForm.currency" placeholder="请选择">
                        <el-option v-for="item in fundCurrencyList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Conversion Rate">
                    <el-input v-model="BonusForm.conversionratio"></el-input>
                </el-form-item>
                <el-form-item label="Comment:">
                    <el-input type="textarea" v-model="BonusForm.remarks" autosize placeholder="请输入内容">
                    </el-input>
                </el-form-item>
                <el-form-item label="Capital Call Date">
                    <el-date-picker v-model="BonusForm.taxlotdate" type="date" placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
            </div>
        </el-form>
    </div>
    <subCapTable :dataObj='dataObj' :buttonShow='buttonShow' v-show="subCapTableShow"></subCapTable>
    <div slot="footer" class="dialog-footer">
        <el-button @click="BonusVisible = false" size='mini'>取消</el-button>
        <el-button v-if="buttonShow=='add'" type="primary" size='mini' @click="submitForm('BonusForm','add')">创建</el-button>
        <el-button v-else type="primary" size='mini' @click="submitForm('BonusForm','update')">更新</el-button>
    </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from '@/api/axios';
import * as method from "@/api/method";
import bus from '@/api/eventbus'
import mix from "@/api/mixin"
import subCapTable from '@/page/capTable/subCapTable'
export default {
    name:"bonus",
    mixins:[mix],
    data(){
        return {
            subCapTableShow:true,
            isDisable:false,
            BonusVisible:false,
            investType:'',
            labelPosition:'right',
            buttonShow:'add',
            fundFamily:'',
            maxclosedate:'',
            dataObj:{
                eiid:"",
                portfolioid:"",
                fundid:"",
                investtype:"",
                closedate: '',
                maxclosedate: '',
                round: "",
                securitytypeid:''
            },
            BonusData: [
            ],
            investTypeList:[{baseId:'Bonus Shares',baseName:'Bonus Shares'}],
            fundFamilyList:'',
            fundCurrencyList:[],
            fundList:[],
            BonusForm:{
                investtype:'Bonus Shares',
                closedate:'',
                fundfamillyname:'',
                securitytypeid:'',
                shareownedno:'',
                currency:'USD',
                remarks:'',
                round:'',
                conversionratio:'',
                fundname:'',
                fundid:'',
                financialdate:''
            },
            emptyBonusForm:{
                investtype:'Bonus Shares',closedate:'',fundfamillyname:'',
                securitytypeid:'',shareownedno:'',currency:'USD',
                remarks:'',round:'',conversionratio:'',
                fundname:'',fundid:'',financialdate:''
            },
            shareTypeList:[
                {baseId:'2',baseName:'Common'},
                {baseId:'3',baseName:'Preferred'},
                {baseId:'6',baseName:'Equity Interest'}
            ]
        }
    },
    updated(){

    },
    mounted(){
        this.invesDropList();
        this.reqBonusList()
    },
    methods:{
        invesDropList(){
            var obj={dictArray:"FUNDFAMILY,DDL_SecurityType,CURRENCY,FUND"};
            axioss.invesDropList(obj).then(res=>{
                this.fundFamilyList=res.data.data[0].baseInfoList;
                this.fundCurrencyList=res.data.data[2].baseInfoList;
                this.fundList=res.data.data[3].baseInfoList;
            })
        },
        reqBonusList(id){
            var portfolioid=id||this.portfolioid;
            axioss.reqBonusList(portfolioid).then(res=>{
                console.log(res)
                this.BonusData=res.data.data;
            })
        },
        reqLoanToEquity(fundfamillyname,portfolioid){
            var obj={fundfamillyname:fundfamillyname,portfolioid:portfolioid}
            axioss.reqLoanToEquity(obj).then(res=>{
                this.loanToEquityData=this.formatTime(res.data.data);
            })
        },
        querySingalData(id){
            axioss.querySingalData(id).then(res=>{
                this.BonusForm=res.data.data;
                this.$store.dispatch('saveCapTabel',res.data.data.portfoliocaptablevaluedetailList);
                this.dataObj.termsigndate=res.data.data.termsigndate;
                this.dataObj.portfolioid=this.portfolioid;
                this.dataObj.closedate=this.BonusForm.closedate;
                this.dataObj.round=this.BonusForm.round;
            })
        },
        submitForm(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var obj=this.BonusForm;
                    if(type=="add"){
                        console.log(obj)
                        axioss.addBonus(obj).then(res=>{
                            console.log(res)
                            let status=res.data.code,succMes='创建成功',failMes='创建失败';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.BonusVisible=false;
                                Object.assign(this.BonusForm,this.emptyBonusForm);
                                this.reqBonusList();
                            }
                        })
                    }else{
                        obj.portfoliocaptablevaluedetailList=this.capFormList;
                        axioss.updateInvest(obj).then(res=>{
                            if(res.data.code=="SUCCESS"){
                                this.$message({
                                    type:'success',
                                    message: '更新成功'
                                })
                                this.BonusVisible=false;
                                this.reqBonusList(this.portfolioid);//请求invest数据
                                Object.assign(this.BonusForm,this.emptyBonusForm);
                            }else{
                                this.$message({
                                    type:'warning',
                                    message: '更新失败'
                                })
                            }
                        })
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        handleAdd(){
            this.subCapTableShow=false;
            this.isDisable=false;
            this.BonusForm={currency:'USD',investtype:'Bonus Shares'}
            this.BonusVisible=true;
            this.buttonShow='add';
        },
        handleEdit(index,data){
            this.subCapTableShow=true;
            this.isDisable=true;
            this.BonusVisible=true;
            this.buttonShow='eidt';
            this.querySingalData(data.eiid);
        },
        acqFundName(e){
            this.BonusForm.fundname=e.target.textContent
        },
        handleDelet(index,data){
            var id=data.eiid;
            this.$confirm("此操作将永久删除该文件, 是否继续?","提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                axioss.deletInvest(id).then(res => {
                    var status = res.data.code;
                    if (status.toLocaleLowerCase() == "success") {
                    this.reqinvestList();
                    this.$message({
                        type: "success",
                        message: "删除成功!"
                    });
                    } else {
                    this.$message({
                        type: "error",
                        message: "删除失败!"
                    });
                    }
                });
            })
        },
        translateSubmit(data,obj){
            var val=data.securitytypeid;
            if(!val){
                return data;
            }else{
                data.securitytypeid=obj[val]
                return data
            }
        },
        translateEdit(data,obj){
            var val=data.securitytypeid;
            if(!val){
                return data;
            }else{
                for(var i in obj){
                    if(obj[i]==val){
                        data.securitytypeid=i
                    }
                }
            }
            return data;
        },
        translateShareType(data,obj){
            if(data&&data!=null&&data!=[]){
                for(var i=0;i<data.length;i++){
                    data[i]=this.translateEdit(data[i],obj);
                }
            }
            return data;
        },
        translateShareTypeSubmit(data,obj){
            for(var i=0;i<data.length;i++){
                data[i]=this.translateSubmit(data[i],obj)
            }
            return data;
        },
        formatTime(data){
            if(data&&data!=null&&data!=[]){
                for(var i=0;i<data.length;i++){
                    data[i].closedate = method.toLocalString(data[i].closedate);
                    data[i].termsigndate = method.toLocalString(data[i].termsigndate)
                    data[i].taxlotdate = method.toLocalString(data[i].taxlotdate)
                }
            }
            return data
        },
        toMs(data){
            if(data.constructor==Array&&data!=[]){
                for(var i=0;i<data.length;i++){
                    data[i].closedate=method.toMs(data[i].closedate)
                }
            }
            return data
        }
    },
    computed:{
        portfolioid(){
            if(this.$store.state.portfolioid==''){
                this.$store.dispatch('updateData');
            }
            return this.$store.state.portfolioid;
        },
        capFormList(){
            return this.$store.state.capTabelData;
        },
        isDetail(){
            if(this.$store.state.isDetail==''){
                this.$store.dispatch('updateIsDetail');
            }
            return this.$store.state.isDetail;
        }
    },
    components:{
        subCapTable
    },
    directives:{
        isedit:{
            inserted:function(el){
                var inp=el.children[0];
                inp.onfocus=function(){
                    this.style.borderColor="#ccc";
                };
                inp.onblur=function(){
                    this.style.borderColor="transparent";
                }
            }
        }
    }
}
</script>
<style scoped lang="scss">
	.bonus{
        .loan-table-container h3{
            font-size:16px;
            padding:5px 0;
            padding-left:10px;
            background:#eee;
            margin: 0;
            border-bottom: 1px solid #ebeef5;
        }
    }
    .loan-table-container .table-foot{
        background:#eee;
        padding:5px 0;
        padding-left:10px;
    }
    .select-container{
        width:100%;
    }
    
</style>
