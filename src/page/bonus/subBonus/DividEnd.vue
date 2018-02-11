<template>
<div class="bonus loan">
    <div class="loan-table-container">
        <h3 class="h3">Dividends</h3>
        <el-table :data="dividEndData" border>
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
    <el-dialog title="Dividends" :visible.sync="dividEndVisible">
    <div class="select-container">
        <el-form :model="dividEndForm" :label-position='labelPosition' ref="dividEndForm">
            <div class="select-fixed">
                <el-form-item label="Dividends">
                    <el-select v-model="dividEndForm.investtype" placeholder="请选择" disabled>
                        <el-option v-for="item in investTypeList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Payment Date">
                    <el-date-picker v-model="dividEndForm.closedate" type="date" placeholder="选择日期" :disabled="isDisable">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Share Type">
                    <el-select v-model="dividEndForm.securitytypeid" placeholder="请选择">
                        <el-option v-for="item in shareTypeList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Fund">
                    <el-select v-model="dividEndForm.fundid" placeholder="请选择" :disabled="isDisable" filterable>
                        <el-option v-for="item in fundList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId" @click.native='acqFundName'></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Fund Family">
                    <el-select v-model="dividEndForm.fundfamillyname" placeholder="请选择" :disabled="isDisable" filterable>
                        <el-option v-for="item in fundFamilyList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="No of Shares">
                    <el-input v-model="dividEndForm.shareownedno"></el-input>
                </el-form-item>
                <el-form-item label="Currency">
                    <el-select v-model="dividEndForm.currency" placeholder="请选择">
                        <el-option v-for="item in fundCurrencyList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Comment:">
                    <el-input type="textarea" v-model="dividEndForm.remarks" autosize placeholder="请输入内容">
                    </el-input>
                </el-form-item>
                <el-form-item label="Realised Cost">
                    <el-input v-model="dividEndForm.costrelization"></el-input>
                </el-form-item>
                <el-form-item label="Gross Proceed:">
                    <el-input type="textarea" autosize placeholder="请输入内容" v-model="dividEndForm.proceeds">
                    </el-input>
                </el-form-item>
                <el-form-item label="Capital Call Date">
                    <el-date-picker v-model="dividEndForm.financialdate" type="date" placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
            </div>
        </el-form>
    </div>
    <div slot="footer" class="dialog-footer">
        <el-button @click="dividEndVisible = false" size='mini'>取消</el-button>
        <el-button v-if="buttonShow=='add'" type="primary" size='mini' @click="submitForm('dividEndForm','add')">创建</el-button>
        <el-button v-else type="primary" size='mini' @click="submitForm('dividEndForm','update')">更新</el-button>
    </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from '@/api/axios';
import * as method from "@/api/method";
import bus from '@/api/eventbus'
import mix from "@/api/mixin"
export default {
    name:"bonus",
    mixins:[mix],
    data(){
        return {
            subCapTableShow:true,
            isDisable:false,
            dividEndVisible:false,
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
            dividEndData: [
            ],
            translateObj:{
                Common:2,
                Preferred:3,
                'Equity Interest':6,
                 Distribution:15,
                'Capital Call':14
            },
            investTypeList:[{baseId:'Dividends',baseName:'Dividends'}],
            fundFamilyList:'',
            fundCurrencyList:[],
            fundList:[],
            dividEndForm:{
                investtype:'Dividends',
                closedate:'',
                fundfamillyname:'',
                securitytypeid:'',
                shareownedno:'',
                currency:'USD',
                remarks:'',
                proceeds:'',
                costrelization: '',
                fundname:'',
                fundid:'',
                financialdate:''
            },
            emptydividEndForm:{
                investtype:'Dividends',closedate:'',fundfamillyname:'',
                securitytypeid:'',shareownedno:'',currency:'USD',
                remarks:'',proceeds:'',costrelization: '',
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
        this.reqDividEndList()
    },
    methods:{
        invesDropList(){
            var obj={dictArray:"FUNDFAMILY,DDL_SecurityType,CURRENCY,FUND"};
            axioss.invesDropList(obj).then(res=>{
                console.log(res)
                this.fundFamilyList=res.data.data[0].baseInfoList;
                this.fundCurrencyList=res.data.data[2].baseInfoList;
                this.fundList=res.data.data[3].baseInfoList;
            })
        },
        reqDividEndList(id){
            var portfolioid=id||this.portfolioid;
            axioss.reqDividEndList(portfolioid).then(res=>{
                this.dividEndData=res.data.data;
            })
        },
        querySingalData(id){
            axioss.querySingalData(id).then(res=>{
                var newdata=JSON.stringify(res.data.data);
                var data=JSON.parse(newdata);
                this.dataObj.portfolioid=data.portfolioid;
                this.dataObj.termsigndate=data.termsigndate;
                this.dataObj.closedate=data.closedate;
                this.dataObj.round=data.round;
                this.dataObj.securitytypeid=data.securitytypeid;
                this.dataObj.eiid=data.eiid;
                this.dataObj.maxclosedate=data.maxclosedate;
                this.dataObj.investtype=data.investtype;
                this.maxclosedate=data.maxclosedate;
                this.dividEndForm=this.translateEdit(data,this.translateObj);
                this.loanToEquityData=this.formatTime(res.data.data.portfolioloantoequityList);
                this.loanToEquityData=this.translateShareType(this.loanToEquityData,this.translateObj);
                this.$store.dispatch('saveCapTabel',data.portfoliocaptablevaluedetailList);
            })
        },
        submitForm(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var obj=this.dividEndForm;
                    if(type=="add"){
                        console.log(obj)
                        axioss.addBonus(obj).then(res=>{
                            console.log(res)
                            let status=res.data.code,succMes='创建成功',failMes='创建失败';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.dividEndVisible=false;
                                Object.assign(this.dividEndForm,this.emptydividEndForm);
                                this.reqDividEndList();
                            }
                        })
                    }else{
                        axioss.updateInvest(obj).then(res=>{
                            if(res.data.code=="SUCCESS"){
                                this.$message({
                                    type:'success',
                                    message: '更新成功'
                                })
                                this.dividEndVisible=false;
                                this.reqinvestList(this.portfolioid);//请求invest数据
                                Object.assign(this.dividEndForm,this.emptydividEndForm);
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
            this.isReqLoanToEquity=true;
            this.isDisable=false;
            this.dividEndForm={currency:'USD',investtype:'Dividends'};
            this.dividEndVisible=true;
            this.buttonShow='add';
        },
        handleEdit(index,data){
            this.subCapTableShow=true;
            this.isReqLoanToEquity=false;
            this.isDisable=true;
            this.dividEndVisible=true;
            this.buttonShow='eidt';
            this.dividEndForm.investtype=data.investtype;
            this.querySingalData(data.eiid);
        },
        acqFundName(e){
            this.dividEndForm.fundname=e.target.textContent
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
        }
    },
    computed:{
        portfolioid(){
            if(this.$store.state.portfolioid==''){
                this.$store.dispatch('updateData');
            }
            return this.$store.state.portfolioid;
        },
        isDetail(){
            if(this.$store.state.isDetail==''){
                this.$store.dispatch('updateIsDetail');
            }
            return this.$store.state.isDetail;
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
