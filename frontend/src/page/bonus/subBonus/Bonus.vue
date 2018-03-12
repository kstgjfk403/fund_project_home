<template>
<div class="bonus loan">
    <div class="loan-table-container">
        <h3 class="h3">Bonus Shares</h3>
        <el-table :data="BonusData" border style="float:left;width:auto;">
            <el-table-column fixed prop="dbid" label="DBID" width="70" v-if="false"></el-table-column>
            <el-table-column prop="investtype" label="Invest Type" width="150"></el-table-column>
            <el-table-column prop="fundname" label="Fund" width="130"></el-table-column>
            <el-table-column prop="fundfamillyname" label="Fund Family" width="130"></el-table-column>
            <el-table-column prop="securitytypeidstr" label="Share Type" width="150"></el-table-column>
            <el-table-column prop="closedate" label="Payment Date" width="150" :formatter="formatDate"></el-table-column>
            <el-table-column prop="round" label="Round" width="110"></el-table-column>
            <el-table-column prop="shareownedno" label="Shares Acquired" width="150" :formatter="numberFormat" ></el-table-column>
            <el-table-column prop="cost" label="Cost" width="100" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="conversionratio" label="Rate" width="70"></el-table-column>
            <el-table-column prop="convertamount" label="convert amount" width="150" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="taxlotdate" label="Capital Call Date" width="150" :formatter="formatDate"></el-table-column>
            <el-table-column label="Opt" width="60" fixed='right' v-if="isDetail!='false'">
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
                <el-form-item label="Invest Type">
                    <el-select v-model="BonusForm.investtype" placeholder="  " disabled>
                        <el-option v-for="item in investTypeList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Payment Date">
                    <el-date-picker v-model="BonusForm.closedate" type="date" placeholder=" " :disabled="isDisable">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Share Type">
                    <el-select v-model="BonusForm.securitytypeid" placeholder="  ">
                        <el-option v-for="item in shareTypeList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Fund">
                    <el-select v-model="BonusForm.fundid" placeholder="  " :disabled="isDisable" filterable>
                        <el-option v-for="item in fundList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId" @click.native='acqFundName'></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Fund Family">
                    <el-select v-model="BonusForm.fundfamillyname" placeholder="  " :disabled="isDisable" filterable>
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
                    <el-select v-model="BonusForm.currency" placeholder="  ">
                        <el-option v-for="item in fundCurrencyList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Conversion Rate(x:1):">
                    <el-input v-model="BonusForm.conversionratio"></el-input>
                </el-form-item>
                <el-form-item label="Comment:">
                    <el-input type="textarea" v-model="BonusForm.remarks" autosize placeholder="  ">
                    </el-input>
                </el-form-item>
                <el-form-item label="Capital Call Date">
                    <el-date-picker v-model="BonusForm.taxlotdate" type="date" placeholder=" ">
                    </el-date-picker>
                </el-form-item>
            </div>
        </el-form>
    </div>
    <subCapTable :dataObj='dataObj' :buttonShow='buttonShow' v-show="subCapTableShow"></subCapTable>
    <div slot="footer" class="dialog-footer">
        <el-button @click="BonusVisible = false" size='mini'>Cancel</el-button>
        <el-button v-if="buttonShow=='add'" type="primary" size='mini' @click="submitForm('BonusForm','add')">Create</el-button>
        <el-button v-else type="primary" size='mini' @click="submitForm('BonusForm','update')">Update</el-button>
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
                {baseId:2,baseName:'Common'},
                {baseId:3,baseName:'Preferred'},
                {baseId:6,baseName:'Equity Interest'},
                {baseId:9,baseName:'ESOP'},
                {baseId:10,baseName:'Dividends'},	
            ]
        }
    },
    updated(){

    },
    mounted(){
        this.invesDropList();
        this.reqBonusList();
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
        querySingalBonusData(id){
            axioss.querySingalBonusData(id).then(res=>{
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
                        obj.portfolioid=this.portfolioid;
                        console.log(obj)
                        axioss.addBonus(obj).then(res=>{
                            console.log(res)
                            let status=res.data.code,succMes='Create success',failMes='Create failure';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.BonusVisible=false;
                                Object.assign(this.BonusForm,this.emptyBonusForm);
                                this.reqBonusList();
                            }
                        })
                    }else{
                        obj.portfoliocaptablevaluedetailList=this.capFormList;
                        axioss.updateBonus(obj).then(res=>{
                            let status=res.data.code,succMes='Update success',failMes='Update failure';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.BonusVisible=false;
                                Object.assign(this.BonusForm,this.emptyBonusForm);
                                this.reqBonusList();
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
            console.log(data.dbid);
            this.querySingalBonusData(data.dbid);
        },
        acqFundName(e){
            this.BonusForm.fundname=e.target.textContent
        },
        handleDelet(index,data){
            var id=data.eiid;
            this.$confirm("This Opt will permanently delete the file, whether it will continue?","Notice", {
                confirmButtonText: "OK",
                cancelButtonText: "Cancel",
                type: "warning"
            }).then(() => {
                axioss.deletInvest(id).then(res => {
                    var status = res.data.code;
                    if (status.toLocaleLowerCase() == "success") {
                    this.reqinvestList();
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
