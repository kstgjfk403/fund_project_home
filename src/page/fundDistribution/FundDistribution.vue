
<template>
    <div class="funddistribution">
        <Header></Header>
        <div class="search">
            <el-form :inline="true" :model="searchForm" ref="searchForm">
                <el-form-item label="Fund">
                    <el-select v-model="searchForm.fundid" placeholder="" filterable>
                    <el-option :label="item.baseName" :value="item.baseId" :key="item.baseId" v-for="item in fundList"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="mini" @click="searchSubmit('searchForm')">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <div class="position-container">
                <h3 class="h3">Distributable</h3>
                <el-table :data="distriList" tooltip-effect="dark" border style="width: 100%;" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="60">
                    </el-table-column>
                    <el-table-column prop="sourceid" label="sourceid" width="180">
                    </el-table-column>
                    <el-table-column prop="sourcetype" label="sourcetype" width="180">
                    </el-table-column>
                    <el-table-column prop="concretesourcetype" label="concretesourcetype" width="180">
                    </el-table-column>
                    <el-table-column prop="paymentdate" label="paymentdate" width="180">
                    </el-table-column>
                    <el-table-column prop="payment" label="payment" width="180">
                    </el-table-column>
                    <el-table-column prop="currency" label="currency" width="180">
                    </el-table-column>
                    <el-table-column prop="portfolioname" label="portfolioname" width="180">
                    </el-table-column>
                    
                </el-table>
            </div>
            <div class="position-container">
                <h3 class="h3">Distribution</h3>
                <el-table :data="fundDistriList" border style="width: 100%;">
                    <el-table-column prop="fundFamillyname" label="fundFamillyname" width="180">
                    </el-table-column>
                    <el-table-column prop="fundname" label="fundname" width="180">
                    </el-table-column>
                    <el-table-column prop="fundfullnameeng" label="fundfullnameeng" width="180">
                    </el-table-column>
                    <el-table-column prop="fundfullnamechi" label="fundfullnamechi" width="180">
                    </el-table-column>
                    <el-table-column prop="issuedate" label="issuedate" width="180">
                    </el-table-column>
                    <el-table-column prop="distributiondes" label="distributiondes" width="180">
                    </el-table-column>
                    <el-table-column prop="multiple" label="multiple" width="180">
                    </el-table-column>
                    <el-table-column prop="distributionorder" label="distributionorder" width="180">
                    </el-table-column>
                    <el-table-column prop="totalcallamount" label="totalcallamount" width="180">
                    </el-table-column>
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
                <div class="pagination-container">
                    <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :page-size='5' :current-page.sync="pageCurrent" @current-change="handleCurrentChange" style="position:absolute;left:50%;transform: translate(-50%);"></el-pagination>    
                </div>
            </div>
            <div class="position-container">
                <h3 class="h3">Distribution Detail</h3>
                <el-table :data="fundDistriList" border style="width: 100%;">
                    <el-table-column prop="fundid" label="fundid" width="180">
                    </el-table-column>
                    <el-table-column prop="emailsenddatetime" label="emailsenddatetime" width="180">
                    </el-table-column>
                    <el-table-column prop="emailto" label="emailto" width="180">
                    </el-table-column>
                    <el-table-column prop="emailbc" label="emailbc" width="180">
                    </el-table-column>
                    <el-table-column prop="emailcc" label="emailcc" width="180">
                    </el-table-column>
                    <el-table-column prop="subject" label="subject" width="180">
                    </el-table-column>
                    <el-table-column prop="emailcontent" label="emailcontent" width="180">
                    </el-table-column>
                    <el-table-column prop="attachment" label="attachment" width="180">
                    </el-table-column>
                    <el-table-column prop="emailreviewed" label="emailreviewed" width="180">
                    </el-table-column>
                    <el-table-column prop="distributionamount" label="distributionamount" width="180">
                    </el-table-column>
                    <el-table-column prop="lpid" label="lpid" width="180">
                    </el-table-column>
                    <el-table-column prop="approved" label="approved" width="180">
                    </el-table-column>
                    <el-table-column prop="gpcarryamount" label="gpcarryamount" width="180">
                    </el-table-column>
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
                <!--<div class="pagination-container">
                    <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :page-size='5' style="position:absolute;left:50%;transform: translate(-50%);"></el-pagination>    
                </div>-->
            </div>
        </div>
        <el-dialog title="Distribution" :visible.sync="distributionVisible">
            <div class="select-container">
                <el-form :model="distributionForm" ref="distributionForm" :label-position='labelPosition'>
                    <div class="select-fixed">
                        <el-form-item label="Fund">
                            <el-select v-model="distributionForm.fundid" placeholder="" filterable disabled>
                            <el-option :label="item.baseName" :value="item.baseId" :key="item.baseId" v-for="item in fundList"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="issueDate">
                            <el-date-picker v-model="distributionForm.issuedate" type="date" placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="multiple">
                            <el-input v-model="distributionForm.multiple"></el-input>
                        </el-form-item>
                        <el-form-item label="distributionOrder">
                            <el-input v-model="distributionForm.distributionorder"></el-input>
                        </el-form-item>
                        <el-form-item label="totalCallAmount">
                            <el-input v-model="distributionForm.totalcallamount"></el-input>
                        </el-form-item>
                        <el-form-item label="distributionDes:">
                            <el-input type="textarea" autosize placeholder="请输入内容" v-model="distributionForm.distributiondes">
                            </el-input>
                        </el-form-item>
                    </div>
                </el-form>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="distributionVisible = false" size='mini'>取 消</el-button>
                <el-button type="primary" size='mini' @click="submitAdd('distributionForm','add')" v-if="buttonShow">新建</el-button>
                <el-button type="primary" size='mini' @click="submitAdd('distributionForm','update')" v-else>更新</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import axioss from "@/api/axios";
import Header from "@/components/common/Header";
import mix from "@/api/mixin";
export default {
    name: "funddistribution",
    mixins:[mix],
    data: function() {
        return {
            labelPosition:'right',
            pageCurrent:1,
            allcount:0,
            distributionVisible:false,
            buttonShow:true,
            fundList:[],
            distriList:[],
            fundDistriList:[],
            searchForm:{
                fundid:'F0017',
                pageIndex: 1,
                pageCount: 2
            },
            distributionForm:{
                fundid:'F0017',
                issuedate: '',
                distributiondes: '',
                multiple : '',
                distributionorder:'',
                totalcallamount:''
            },
            distributionFormEmpty:{
                fundid: 'F0017',
                issuedate: '',
                distributiondes: '',
                multiple : '',
                distributionorder:'',
                totalcallamount:''
            }
        };
    },
    mounted: function() {
        this.reqdFundList();
        this.reqDistriList(this.searchForm)
        this.reqFundDistriList(this.searchForm)
    },
    methods: {
        reqdFundList(){
            var obj={dictArray:"FUND"};
            axioss.reqdroplist(obj).then(res=>{
                this.fundList=res.data.data[0].baseInfoList;
            })
        }, 
        reqDistriList(obj){
            axioss.reqDistriList(obj).then(res=>{
                console.log(res);
                this.distriList=res.data.data
            })
        },
        reqFundDistriList(obj){
            axioss.reqFundDistriList(obj).then(res=>{
                console.log(res);
                this.fundDistriList=res.data.data;
                this.allcount=res.data.count;
            })
        },
        searchSubmit(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.reqDistriList(this.searchForm);
                    this.reqFundDistriList(this.searchForm);
                }
            })
        },
        submitAdd(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var obj=this.distributionForm;
                    if(type=='add'){
                        axioss.addFundDistri(obj).then(res=>{  
                            let status=res.data.code,succMes='创建成功',failMes='创建失败';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.distributionVisible=false;
                                this.reqFundDistriList(this.searchForm);
                                Object.assign(this.distributionForm,this.distributionFormEmpty)
                            }
                        })
                    }else{
                        obj.portfoliocaptablevaluedetailList=this.capFormList;
                        axioss.updateExit(obj).then(res=>{
                            let status=res.data.code,succMes='更新成功',failMes='更新失败';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.distributionVisible=false;
                                this.reqFundDistriList(this.distributionForm.fundid);
                                Object.assign(this.distributionForm,this.distributionFormEmpty)
                            }
                        })
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        handleSelectionChange(){

        },
        handleCurrentChange(){
            this.searchForm.pageIndex=this.pageCurrent;
            this.reqFundDistriList(this.searchFrom);
            console.log(this.searchForm)
        },
        handleAdd(){
            this.distributionVisible=true;
            this.buttonShow=true;
            this.distributionForm.fundid=this.searchForm.fundid;
        },
        handleEdit(index,data){
            this.distributionVisible=true;
            this.buttonShow=false;
        },
        handleDelet(){

        }
    },
    components: {
        Header
    }
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang='scss'>
    .search{
        padding:10px 8.3%;
    }
    .position-container{
        width:95%;margin:0 auto;
        position:relative;
        margin-bottom:15px;
    }
    .pagination-container{
        width:100%;
        height:50px;
    }
    .funddistribution{
        .position-container .h3{
            font-size:16px;
            padding:5px 0;
            padding-left:10px;
            background:#00a1e9;
            margin: 0;
            border-bottom: 1px solid #ebeef5;
            color:white;
        }
    }
    .position-container .table-foot{
        background:#eee;
        padding:5px 0;
        padding-left:10px;
    }
</style>
