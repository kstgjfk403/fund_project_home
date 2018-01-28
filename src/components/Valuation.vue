<template>
<div class="valuation loan">
    <Header></Header>
    <div class="valuation-top">
        <el-form :model="valuationFormData">
            <el-form-item label="fund family:">
                <el-radio-group v-model="fundFamilyId" @change="reqFundFamilyName">
                    <el-radio :key="item.baseId" :label="item.baseId" v-for="item in fundfamilyList">{{item.baseName}}</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="fund name">
                <el-checkbox-group v-model="valuationFormData.fundIds" style="display:inline-block;">
                    <el-checkbox :label="item.fundid" :key="item.fundid" v-for="item in checkList" checked>{{item.fundname}}</el-checkbox>
                </el-checkbox-group>
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
            <el-table-column prop="fundname" label="Status"></el-table-column>
            <el-table-column prop="valuationdate" label="Valueation Date"></el-table-column>
            <el-table-column prop="valuationmethod" label="Method"></el-table-column>
            <el-table-column prop="valuation" label="Valueation"></el-table-column>
            <el-table-column prop="irr" label="IRR(%)"></el-table-column>
            <el-table-column prop="rating" label="Rating"></el-table-column>
            <el-table-column prop="valuationnote" label="Note"></el-table-column>
            <el-table-column label="操作" width="60">
                <template slot-scope="scope">
                    <i class="el-icon-edit"></i>
                    <i class="el-icon-delete"></i>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination-container">
            <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" @current-change="handleCurrentChange"></el-pagination>    
        </div>
    </div>
    <el-dialog title="valuation" :visible.sync="valuationVisible">
        <div class="select-container">
            <div class="select-fixed">
                <el-form :model="valuationForm" ref="valuationForm" :label-position='labelPosition'>
                    <el-form-item label="Fund Name">
                        <el-input v-model="valuationForm.fundFamilyId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="Invest Type">
                        <el-input v-model="valuationForm.fundFamilyId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="Fund Family">
                        <el-input v-model="valuationForm.fundFamilyId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="Invest Type">
                        <el-input v-model="valuationForm.fundFamilyId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="Payment Date">
                        
                    <el-input v-model="valuationForm.fundFamilyId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="Fund Family">
                        <el-input v-model="valuationForm.fundFamilyId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="Invest Type">
                        <el-input v-model="valuationForm.fundFamilyId" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="Payment Date">
                        <el-date-picker v-model="valuationForm.closedate" type="date" placeholder="选择日期">
                    </el-date-picker>
                    </el-form-item>
                    <el-form-item label="Fund Family">
                        <el-select v-model="valuationForm.fundfamillyname" placeholder="请选择" filterable>
                            <el-option v-for="item in fundfamilyList" :key="item.baseId"
                            :label="item.baseName" :value="item.baseName"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div style="text-align:right;padding:5px 0;">
                    <el-button type="primary" size="mini">新建</el-button>
                </div>
            </div>
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
                    <tr v-for="item in valuationTableData">
                        <td scope="row">{{item.closedate}}</td>
                        <td>{{item.fundfamillyname}}</td>
                        <td>{{item.remainderamount}}</td>
                        <td><el-input v-model="item.otherfees"></el-input></td>
                        <td><el-input v-model="item.convertamount"></el-input></td>
                        <td><el-input v-model="item.proceeds"></el-input></td>
                        <td><el-input v-model="item.shareownedno" ></el-input></td>
                        <td><el-input v-model="item.cost" ></el-input></td>
                        <td><el-input v-model="item.additionalcost"></el-input></td>
                        <td><el-select v-model="item.securitytypeid" placeholder="">
                            <el-option value="2" label="2"></el-option>
                            <el-option value="2" label="3"></el-option>
                            <el-option value="2" label="6"></el-option>
                            </el-select>
                        </td>
                        <td><el-input v-model="item.conversionratio"></el-input></td>
                    </tr>   
                </tbody>
            </table>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="valuationVisible = false" size='mini'>取 消</el-button>
            <el-button type="primary" size='mini'>新建</el-button>
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
            allcount:0,
            checkList:[],
            fundfamilyList:[],
            fundFamilyId:'',
            labelPosition:'right',
            valuationVisible:false,
            valuationData:[],
            valuationFormData:{
                fundIds:[],
                VALUEDATE:''
            },
            valuationForm:{

            },
            valuationTableData:{
                
            },
            radio2:''
        }
    },
    mounted(){
        this.reqselectlist();
    },
    methods:{
        reqselectlist(){
            axioss.reqselectlist().then(res=>{
                this.fundfamilyList=res.data.data;
            })
        },
        reqPaginationData(index,count){
            //用来做分页的请求方法。

        },
        reqFundFamilyName(id){
            this.checkList=[];
            var fundfamilyid=id||this.fundFamilyId;
            axioss.reqFundFamilyName(fundfamilyid).then(res=>{
                this.checkList=res.data.data;
            })
        },
        searchData(){
            var obj={},ids;
            ids=this.valuationFormData.fundIds.slice(-this.checkList.length)//解决引用类型数据追加问题,不能清空的问题。
            obj.fundIds=ids.join();
            obj.VALUEDATE=this.valuationFormData.VALUEDATE;
            axioss.reqValList(obj).then(res=>{
                this.valuationData=res.data.data;
            })
        },
        handleAdd(){
            this.valuationVisible=true;        
        },
        handleCurrentChange(currentPage){
            console.log(currentPage);
            this.reqPaginationData();
        }
    },
    computed:{
        portfolioid:function(){
            if(this.$store.state.portfolioid==''){
                this.$store.dispatch('updateData');
            }
            return this.$store.state.portfolioid
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