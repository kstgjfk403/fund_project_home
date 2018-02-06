<template>
<div class="invest loan" ref="Bonus">
    <div class="title">Bonus</div>
    <div class="loan-table-container">
        <h3 class="h3">Bonus History</h3>
        <el-table :data="investData" border>
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
    <el-dialog title="Invest Edit" :visible.sync="investVisible">
    <div class="select-container">
        <el-form :model="investForm" :label-position='labelPosition' ref="investForm">
            <div class="select-fixed">
                <el-form-item label="Invest Type">
                    <el-select v-model="investForm.investtype" placeholder="请选择" :disabled="isDisable">
                        <el-option v-for="item in investTypeList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Payment Date">
                    <el-date-picker v-model="investForm.closedate" type="date" placeholder="选择日期" :disabled="isDisable">
                </el-date-picker>
                </el-form-item>
                <el-form-item label="Share Type">
                    <el-select v-model="investForm.securitytypeid" placeholder="请选择">
                        <el-option label='Equity Interest' value='Equity Interest' key='6'></el-option>
                        <el-option label='Capital Call' value='Capital Call' key='14'></el-option>
                        <el-option label='Distribution' value='Distribution' key='15'></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Fund">
                    <el-select v-model="investForm.fundname" placeholder="请选择" :disabled="isDisable" filterable>
                        <el-option v-for="item in fundList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseName"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Fund Family">
                    <el-select v-model="investForm.fundfamillyname" placeholder="请选择" :disabled="isDisable" filterable>
                        <el-option v-for="item in fundFamilyList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseName"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Round">
                    <el-input v-model="investForm.round"></el-input>
                </el-form-item>
                <el-form-item label="Currency">
                    <el-select v-model="investForm.currency" placeholder="请选择">
                        <el-option v-for="item in fundCurrencyList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Capital Call Date">
                    <el-date-picker v-model="investForm.taxlotdate" type="date" placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
            </div>
            <div v-show="isTableShow=='LoanToEquity1'">
                <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;">
                    <thead>
                        <tr>
                            <th scope="col">Time</th>
                            <th scope="col">Fund Family</th>
                            <th scope="col">loanAmount</th>
                            <th scope="col">other Fees</th>
                            <th scope="col">convertAmount</th>
                            <th scope="col">Capitalized Interest</th>
                            <th scope="col">Acquired Shares</th>
                            <th scope="col">Share Type</th>
                            <th scope="col">convert Rate</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="item in loanToEquityData">
                            <td scope="row">{{item.closedate}}</td>
                            <td>{{item.fundfamillyname}}</td>
                            <td>{{item.remainderamount}}</td>
                            <td><el-input v-model="item.otherfees" v-isedit class="inputnone"></el-input></td>
                            <td><el-input v-model="item.convertamount" v-isedit class="inputnone"></el-input></td>
                            <td><el-input v-model="item.proceeds" v-isedit class="inputnone"></el-input></td>
                            <td><el-input v-model="item.shareownedno" v-isedit class="inputnone"></el-input></td>
                            <td><el-select v-model="item.securitytypeid" placeholder="" v-isedit class="inputnone">
                                <el-option value="Common" label="Common" key="1"></el-option>
                                <el-option value="Preferred" label="Preferred" key="2"></el-option>
                                <el-option value="Equity Interest" label="Equity Interest" key="3"></el-option>
                                </el-select></td>
                            <td><el-input v-model="item.conversionratio" v-isedit class="inputnone"></el-input></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </el-form>
    </div>
    <div slot="footer" class="dialog-footer">
        <el-button @click="investVisible = false" size='mini'>取消</el-button>
        <el-button v-if="buttonShow=='add'" type="primary" size='mini' @click="submitForm('investForm','add')">创建</el-button>
        <el-button v-else type="primary" size='mini' @click="submitForm('investForm','update')">更新</el-button>
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
            investVisible:false,
            isReqLoanToEquity:false,
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
            investData: [
            ],
            loanToEquityData:[
            ],
            translateObj:{
                Common:2,
                Preferred:3,
                'Equity Interest':6,
                 Distribution:15,
                'Capital Call':14
            },
            investTypeList:'',
            fundFamilyList:'',
            fundCurrencyList:[],
            fundList:[],
            investForm:{
                investtype:'',
                closedate:'',
                fundfamillyname:'',
                cost:'',
                interestproceeds:'',
                securitytypeid:'',
                round:'',
                shareownedno:'',
                conversionratio:'',
                otherfees:'',
                currency:'USD',
                remarks:'',
                taxlotdate:'',
                vouncher:'',
                otherproceeds:'',
                proceeds:'',
                costrelization: 0,
                termsigndate:'',
                fundname:''
            },
            emptyInvestForm:{
                investtype:'',closedate:'',fundfamillyname:'',
                cost:'',interestproceeds:'',securitytypeid:'',
                round:'',shareownedno:'',conversionratio:'',
                otherfees:'',currency:'USD',remarks:'',
                taxlotdate:'',vouncher:'',otherproceeds:'',
                additionalcost:'',proceeds:'',costrelization: 0,
                termsigndate:''
            },
            capTabelList:[],
            captelDetailData:[],
            options:[
               {
                value: 'USD',
                label: 'USD'
                }
            ]
        }
    },
    updated(){

    },
    mounted(){
        bus.$on('toScorll',(ace)=>{
           this.scrolltoview(ace,'Bonus');
        });
        this.invesDropList();
        this.reqinvestList();
        var isDetail=localStorage.isDetail
    },
    methods:{
        invesDropList(){
            var obj={dictArray:"DDL_InvestType,FUNDFAMILY,DDL_SecurityType,CURRENCY,FUND"};
            axioss.invesDropList(obj).then(res=>{
                this.investTypeList=res.data.data[0].baseInfoList;
                this.fundFamilyList=res.data.data[1].baseInfoList;
                this.fundCurrencyList=res.data.data[3].baseInfoList;
                this.fundList=res.data.data[4].baseInfoList;
            })
        },
      numberFormat: function (row, column) {
        var num = row[column.property];
        if (num == undefined) {
          return "";
        }
        return method.toThousands(num);
      },
        reqinvestList(id){
            var portfolioid=id||this.portfolioid;
            axioss.reqinvestList(portfolioid).then(res=>{
                this.investData=this.formatTime(res.data.data);
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
                this.investForm=this.translateEdit(data,this.translateObj);
                this.loanToEquityData=this.formatTime(res.data.data.portfolioloantoequityList);
                this.loanToEquityData=this.translateShareType(this.loanToEquityData,this.translateObj);
                this.$store.dispatch('saveCapTabel',data.portfoliocaptablevaluedetailList);
            })
        },
        submitForm(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var obj=this.translateSubmit(this.investForm,this.translateObj)
                    obj.portfoliocaptablevaluedetailList=this.capFormList;
                    if(this.isTableShow=='LoanToEquity1'){
                        obj.portfolioloantoequityList=this.toMs(this.loanToEquityData);
                        obj.portfolioloantoequityList=this.translateShareTypeSubmit(obj.portfolioloantoequityList,this.translateObj);
                    }
                    if(type=="add"){
                        obj.portfolioid=this.portfolioid;
                        axioss.addInvest(obj).then(res=>{
                            if(res.data.code=="SUCCESS"){
                                this.$message({
                                    type:'success',
                                    message: '创建成功'
                                })
                                this.investVisible=false;
                                this.reqinvestList(this.portfolioid);
                                Object.assign(this.investForm,this.emptyInvestForm);
                            }else{
                                this.$message({
                                    type:'warning',
                                    message: '创建失败'
                                })
                            }
                        })
                    }else{
                        axioss.updateInvest(obj).then(res=>{
                            if(res.data.code=="SUCCESS"){
                                this.$message({
                                    type:'success',
                                    message: '更新成功'
                                })
                                this.investVisible=false;
                                this.reqinvestList(this.portfolioid);//请求invest数据
                                Object.assign(this.investForm,this.emptyInvestForm);
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
            this.investForm={currency:'USD'};
            this.investVisible=true;
            this.buttonShow='add';
            this.investType='';
            this.whichShow('');
        },
        handleEdit(index,data){
            this.subCapTableShow=true;
            this.isReqLoanToEquity=false;
            this.isDisable=true;
            this.investVisible=true;
            this.buttonShow='eidt';
            this.investForm.investtype=data.investtype;
            this.querySingalData(data.eiid);
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
                    data[i].closedate=method.toLocalString(data[i].closedate);
                    data[i].termsigndate=method.toLocalString(data[i].termsigndate)
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
        },
        isTableShow(){
            let investtype=this.investForm.investtype;
            let fundfamilly=this.investForm.fundfamillyname;
            if((investtype=='Equity Investment&Loan To Equity'||investtype=='Loan To Equity')&&fundfamilly){
                if(this.isReqLoanToEquity){
                    this.reqLoanToEquity(fundfamilly,this.portfolioid)
                }
                return 'LoanToEquity1'
            }else{
                return ''
            }
        }
    },
    components:{
        
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
	.invest{
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
