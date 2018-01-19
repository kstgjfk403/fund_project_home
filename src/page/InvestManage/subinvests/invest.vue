<template>
<div class="invest loan" ref="Invest">
    <div class="loan-table-container">
        <h3 class="h3">Invest History</h3>
        <el-table :data="investData" border>
            <el-table-column fixed prop="eiid" label="EIID" width="90"></el-table-column>
            <el-table-column prop="fundname" label="Foud" width="150"></el-table-column>
            <el-table-column prop="investtype" label="Invest Type" width="150"></el-table-column>
            <el-table-column prop="convertamount" label="convert amount" width="150"></el-table-column>
            <el-table-column prop="closedate" label="Payment Date" width="150"></el-table-column>
            <el-table-column prop="round" label="Round" width="110"></el-table-column>
            <el-table-column prop="securitytypeidstr" label="Share Type" width="100"></el-table-column>
            <el-table-column prop="shareownedno" label="Shares Acquired" width="150"></el-table-column>
            <el-table-column prop="conversionratio" label="Rate" width="70"></el-table-column>
            <el-table-column prop="cost" label="Cost" width="100"></el-table-column>
            <el-table-column prop="otherfees" label="Fees" width="80"></el-table-column>
            <el-table-column prop="proceeds" label="Gross Proceeds" width="150"></el-table-column>
            <el-table-column prop="taxlotdate" label="Tax Lot Date" width="150"></el-table-column>
            <el-table-column prop="vouncher" label="vouncher" width="150"></el-table-column>
            <el-table-column label="操作" width="60" fixed='right'>
                <template slot-scope="scope">
                    <i class="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></i>
                    <i class="el-icon-delete" @click="handleDelet(scope.$index, scope.row)"></i>
                </template>
            </el-table-column>
        </el-table>
        <div class="table-foot">
            <i class="el-icon-circle-plus" @click="handleAdd"></i>
        </div>
    </div>
    <el-dialog title="Invest Edit" :visible.sync="investVisible">
    <div class="select-container">
        <el-form :model="investForm" :label-position='labelPosition' ref="investForm">
            <div class="select-fixed">
                <el-form-item label="Invest Type">
                    <el-select v-model="investForm.investtype" placeholder="请选择" @change="whichShow" :disabled="isDisable">
                        <el-option v-for="item in investTypeList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Payment Date">
                    <el-date-picker v-model="investForm.closedate" type="date" placeholder="选择日期" :disabled="isDisable">
                </el-date-picker>
                </el-form-item>
                <el-form-item label="Fund Family">
                    <el-select v-model="investForm.fundfamillyname" placeholder="请选择" @change="showTable" :disabled="isDisable" filterable>
                        <el-option v-for="item in fundFamilyList" :key="item.baseId"
                        :label="item.baseName" :value="item.baseName"></el-option>
                    </el-select>
                </el-form-item>
            </div>
            <div v-if="investType=='Loan To Equity'">
                <el-form-item label="Round">
                    <el-input v-model="investForm.round"></el-input>
                </el-form-item>
                <el-form-item label="Additional Cost" prop="type" style="margin-bottom:15px;">
                    <el-input v-model="investForm.value9"></el-input>
                </el-form-item>            
                <el-form-item label="Currency">
                    <el-select v-model="investForm.currency" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value"
                        :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>   
            </div>
            <div v-else-if="investType=='Equity Interest'">                
                <el-form-item label="Share Type">
                    <el-select v-model="investForm.securitytypeid" placeholder="请选择">
                        <el-option label='Equity Interest' value='Equity Interest' key='6'></el-option>
                        <el-option label='Capital Call' value='Capital Call' key='14'></el-option>
                        <el-option label='Distribution' value='Distribution' key='15'></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Round">
                    <el-input v-model="investForm.round"></el-input>
                </el-form-item>
                <el-form-item label="Registered Capital">
                    <el-input v-model="investForm.shareownedno"></el-input>
                </el-form-item>
                <el-form-item label="Cost">
                    <el-input v-model="investForm.cost"></el-input>
                </el-form-item>
                <el-form-item label="Other Fees">
                    <el-input v-model="investForm.otherfees"></el-input>
                </el-form-item>
                <el-form-item label="Currency">
                    <el-select v-model="investForm.currency" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value"
                        :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Remarks">
                    <el-input v-model="investForm.remarks"></el-input>
                </el-form-item>
                <el-form-item label="Tax Lot Date">
                    <el-date-picker v-model="investForm.taxlotdate" type="date" placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Voucher">
                    <el-input v-model="investForm.vouncher"></el-input>
                </el-form-item>                
            </div>
            <div v-else-if="investType=='Equity Investment'||investType=='Equity Investment&Loan To Equity'">              
                <el-form-item label="Share Type">
                    <el-select v-model="investForm.securitytypeid" placeholder="请选择">
                        <el-option label='Common' value='Common' key='2'></el-option>
                        <el-option label='Preferred' value='Preferred' key='3'></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Round">
                    <el-input v-model="investForm.round"></el-input>
                </el-form-item>
                <el-form-item label="Acquired Shares">
                    <el-input v-model="investForm.shareownedno"></el-input>
                </el-form-item>
                <el-form-item label="Cost">
                    <el-input v-model="investForm.cost"></el-input>
                </el-form-item>
                <el-form-item label="Other Fees">
                    <el-input v-model="investForm.otherfees"></el-input>
                </el-form-item>
                <el-form-item label="Currency">
                    <el-select v-model="investForm.currency" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value"
                        :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Remarks">
                    <el-input v-model="investForm.remarks"></el-input>
                </el-form-item>              
            </div>
            <div v-else-if="investType=='Convert To Equity Interest'">
                <el-form-item label="Share Type">
                    <el-select v-model="investForm.securitytypeid" placeholder="请选择">
                        <el-option label='Equity Interest' value='Equity Interest' key="6"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Converted Amount">
                    <el-input v-model="investForm.cost"></el-input>
                </el-form-item>
                <el-form-item label="Round">
                    <el-input v-model="investForm.round"></el-input>
                </el-form-item>
                <el-form-item label="Registered Capital">
                    <el-input v-model="investForm.shareownedno"></el-input>
                </el-form-item>
                <el-form-item label="Other Fees">
                    <el-input v-model="investForm.otherFees"></el-input>
                </el-form-item>
                <el-form-item label="Currency">
                    <el-select v-model="investForm.currency" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value"
                        :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Tax Lot Date">
                    <el-date-picker v-model="investForm.taxlotdate" type="date" placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Remarks">
                    <el-input v-model="investForm.remarks"></el-input>
                </el-form-item>
                <el-form-item label="Voucher">
                    <el-input v-model="investForm.vouncher"></el-input>
                </el-form-item>           
            </div>
            <div v-else-if="investType=='Convert To Equity Investment'">              
                <el-form-item label="Share Type">
                    <el-select v-model="investForm.securitytypeid" placeholder="请选择">
                        <el-option label='Common' value='Common' key="2"></el-option>
                        <el-option label='Preferred' value='Preferred' key="3"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Converted Amount">
                    <el-input v-model="investForm.cost"></el-input>
                </el-form-item>
                <el-form-item label="Round">
                    <el-input v-model="investForm.round"></el-input>
                </el-form-item>
                <el-form-item label="Acquired Shares">
                    <el-input v-model="investForm.shareownedno"></el-input>
                </el-form-item>
                <el-form-item label="Other Fees">
                    <el-input v-model="investForm.otherfees"></el-input>
                </el-form-item>
                <el-form-item label="Currency">
                    <el-select v-model="investForm.currency" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value"
                        :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Remarks">
                    <el-input v-model="investForm.remarks"></el-input>
                </el-form-item>
            </div>
            <div v-else-if="investType=='ShareReclassification'">
                <el-form-item label="Share Type">
                    <el-select v-model="investForm.securitytypeid" placeholder="请选择">
                        <el-option label='Common' value='Common' key="2"></el-option>
                        <el-option label='Preferred' value='Preferred' key="3"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Converted Shares">
                    <el-input v-model="investForm.value5"></el-input>
                </el-form-item>
                <el-form-item label="Round">
                    <el-input v-model="investForm.round"></el-input>
                </el-form-item>
                <el-form-item label="Remarks">
                    <el-input v-model="investForm.remarks"></el-input>
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
                            <th scope="col">Proceeds</th>
                            <th scope="col">Acquired Shares</th>
                            <th scope="col">Cost</th>
                            <th scope="col">anditionalCost</th>
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
                            <td><el-input v-model="item.cost" v-isedit class="inputnone"></el-input></td>
                            <td><el-input v-model="item.additionalcost" v-isedit class="inputnone"></el-input></td>
                            <td><el-select v-model="investForm.securitytypeid" placeholder="" v-isedit class="inputnone">
                                <el-option value="2" label="2"></el-option>
                                <el-option value="2" label="3"></el-option>
                                <el-option value="2" label="6"></el-option>
                                </el-select></td>
                            <td><el-input v-model="item.conversionratio" v-isedit class="inputnone"></el-input></td>
                        </tr>   
                    </tbody>
                </table>
            </div>
        </el-form>
    </div>
    <div slot="footer" class="dialog-footer">
        <el-button @click="investVisible = false" size='mini'>取 消</el-button>
        <el-button v-if="buttonShow=='add'" type="primary" size='mini' @click="submitForm('investForm','add')">创建</el-button>
        <el-button v-else type="primary" size='mini' @click="submitForm('investForm','update')">更新</el-button>
    </div>
    </el-dialog>
</div>
</template>
<script>	
import axioss from '@/api/axios';
import mix from "@/api/mixin";
export default {
    name:"invest",
    mixins:[mix],
    data(){  
        return {
            isDisable:false,
            investVisible:false,
            investType:'',
            isTableShow:'',
            labelPosition:'right',
            buttonShow:'add',
            fundFamily:'',
            investData: [
            ],
            loanToEquityData:[
            ],
            loanToEquityForm:{
                portfolioloantoequityList:[]
            },
            translateObj:{
                Common:2,
                Preferred:3,
                'Equity Interest':6,
                 Distribution:15,
                'Capital Call':14
            },
            investTypeList:'',
            fundFamilyList:'',
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
                additionalcost:'',
                proceeds:'',
                costrelization: 0
            },
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
        this.invesDropList();
        this.reqinvestList();
    },
    methods:{ 
        invesDropList(){
            var obj={dictArray:"DDL_InvestType,FUNDFAMILY,DDL_SecurityType"};
            axioss.invesDropList(obj).then(res=>{
                //console.log(res)
                this.investTypeList=res.data.data[0].baseInfoList;
                this.fundFamilyList=res.data.data[1].baseInfoList;
            })
        },
        reqinvestList(id){
            var portfolioid=id||this.portfolioid;
            axioss.reqinvestList(portfolioid).then(res=>{
                this.investData=res.data.data;
            })
        },
        reqLoanToEquity(fundfamillyname,portfolioid){
            var obj={fundfamillyname:fundfamillyname,portfolioid:portfolioid}
            axioss.reqLoanToEquity(obj).then(res=>{
                this.loanToEquityData=res.data.data;
            })
        },
        submitForm(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    console.log(this.investForm);
                    var obj=this.translateSubmit(this.investForm,this.translateObj)
                    if(this.isTableShow=='LoanToEquity1'){
                        obj.portfolioloantoequityList=loanToEquityData;
                    }
                    console.log(obj)
                    if(type=="add"){
                        obj.portfolioid=this.portfolioid;
                        axioss.addInvest(obj).then(res=>{
                            console.log(res);
                            if(res.data.code=="SUCCESS"){
                                this.$message({
                                    type:'success',
                                    message: '创建成功'
                                })
                                this.investVisible=false;
                                this.reqinvestList(this.portfolioid);
                                this.investForm={};
                            }else{
                                this.$message({
                                    type:'warning',
                                    message: '创建失败'
                                })
                            }
                        })
                    }else{
                        axioss.updateInvest(obj).then(res=>{
                            //console.log(res);
                            if(res.data.code=="SUCCESS"){
                                this.$message({
                                    type:'success',
                                    message: '更新成功'
                                })
                                this.investVisible=false;
                                this.reqinvestList(this.portfolioid);
                                this.investForm={};
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
            this.isDisable=false;
            this.investForm={currency:'USD'};
            this.investVisible=true;
            this.buttonShow='add';
            this.investType='';
            this.whichShow('');
        },
        handleEdit(index,data){
            this.isDisable=true;
            this.investVisible=true;
            this.buttonShow='eidt';
            this.investForm.investtype=data.investtype;
            this.whichShow(data.investtype);
            this.investForm=this.translateEdit(data,this.translateObj)
        },
        handleDelet(index,data){ 
            var id=data.eiid;
            this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
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
        whichShow(val){
            this.investType=val;
            if(val!='Equity Investment&Loan To Equity'&&val!='Loan To Equity'){
                this.isTableShow=''
            }else{
                if(this.investForm.fundfamillyname){
                    this.isTableShow='LoanToEquity1'
                    this.reqLoanToEquity(this.investForm.fundfamillyname,this.portfolioid)
                }
            }
        },
        showTable(val){
            if(val){
                if(this.investType=='Equity Investment&Loan To Equity'||this.investType=='Loan To Equity')
                this.isTableShow='LoanToEquity1'
                this.reqLoanToEquity(val,this.portfolioid)
            }else{
                this.isTableShow=''
            }
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
        }
    },
    computed:{
        portfolioid(){
            if(this.$store.state.portfolioid==''){
                this.$store.dispatch('updateData');
            }
            return this.$store.state.portfolioid
        }
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