<template>
<div class="warran loan">
    <div class="loan-table-container">
        <h3 class="h3">Warranting</h3>
        <el-table ref="singleTable" :data="warrantData" border style="float:left;width:auto;">
            <el-table-column prop="warrantid" label="WarrantID" width="130"></el-table-column>
            <el-table-column prop="ownertype" label="Type" width="130"></el-table-column>
            <el-table-column prop="warrantownername" label="Owner Name" width="130"></el-table-column>
            <el-table-column prop="fundid" label="Fund" width="130"></el-table-column>
            <el-table-column prop="issuedate" label="Issue Date" width="130" :formatter="formatDate"></el-table-column>
            <el-table-column prop="duedate" label="Due Date" width='130' :formatter="formatDate"></el-table-column>
            <el-table-column prop="sharesno" label="No. of Shares" width="130" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="warrantprice" label="Price" width="130"></el-table-column>
            <el-table-column prop="round" label="Round" width="130"></el-table-column>
            <el-table-column prop="securitytypeidstr" label="Share Type" width="130"></el-table-column>
            <el-table-column prop="currency" label="Cur." width="130"></el-table-column>
            <el-table-column prop="valid" label="Valid" width="130" :formatter="formatBoolean"></el-table-column>
            <el-table-column prop="vouncher" label="Opt" v-if="isDetail!='false'" fixed='right'>
                <template slot-scope="scope">
                    <i class="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></i>
                    <i class="el-icon-delete" @click="deleteWarrant(scope.$index, scope.row)"></i>
                </template>
            </el-table-column>
        </el-table>
        <div class="table-foot">
            <i class="el-icon-circle-plus" @click="handleAdd" v-if="isDetail!='false'"></i>
        </div>
    </div>
    <el-dialog title="Warrant Edit" :visible.sync="warrantVisible">
        <div class="select-container">
            <el-form :model="warrantForm" ref="warrantForm" :label-position='labelPosition'>
                <div class="select-fixed">
                    <el-form-item label="Portfolio Warrant:">
                        <el-select v-model="warrantForm.ownertype" placeholder="  " :disabled='!buttonShow'>
                            <el-option v-for="item in wrrantDropList.WARRANT_TYPE" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Warrant OwnerName:" v-if='warrantNameShow'>
                        <el-input v-model="warrantForm.warrantownername"></el-input>
                    </el-form-item>
                    <el-form-item label="Fund Family:" v-if="famillyShow">
                        <el-select v-model="warrantForm.fundfamillyname" placeholder="  " filterable>
                            <el-option v-for="item in wrrantDropList.FUNDFAMILY" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Fund:" v-if="fundShow">
                        <el-select v-model="warrantForm.fundid" placeholder="  " filterable>
                            <el-option v-for="item in wrrantDropList.FUND" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Issue Date:">
                        <el-date-picker v-model="warrantForm.issuedate" type="date" placeholder=" ">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="Due Date:">
                        <el-date-picker v-model="warrantForm.duedate" type="date" placeholder=" ">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="Cost:">
                        <el-input v-model="warrantForm.warrantamount"></el-input>
                    </el-form-item>
                    <el-form-item label="Currency">
                        <el-select v-model="warrantForm.currency" placeholder="  ">
                            <el-option v-for="item in wrrantDropList.CURRENCY" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Share Type:">
                        <el-select v-model="warrantForm.securitytypeid" placeholder="  ">
                            <el-option v-for="item in wrrantDropList.DDL_WarrantSecurityType" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Round:">
                        <el-input v-model="warrantForm.round"></el-input>
                    </el-form-item>
                    <el-form-item label="No. of Shares:">
                        <el-input v-model="warrantForm.sharesno"></el-input>
                    </el-form-item>
                    <el-form-item label="Comments:">
                        <el-input type="textarea" autosize placeholder="  " v-model="warrantForm.comment">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="Valid:">
                        <el-checkbox v-model="warrantForm.valid"></el-checkbox>
                    </el-form-item>
                </div>
            </el-form>
        </div>
        <subCapTable :dataObj='dataObj' :investForm='warrantForm' :buttonShow='buttonShow' v-show="subCapTableShow"></subCapTable>
        <div slot="footer" class="dialog-footer">
            <el-button @click="warrantVisible = false" size='mini'>Cancel</el-button>
            <el-button type="primary" size='mini' @click="submitForm('warrantForm','add')" v-if="buttonShow">Create</el-button>
            <el-button type="primary" size='mini' @click="submitForm('warrantForm','update')" v-else>Update</el-button>
        </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from '@/api/axios';
import * as method from "@/api/method";
import subCapTable from "../../capTable/subCapTable";
import mix from "@/api/mixin" 
export default {
    name:"warraning",
    mixins:[mix],
    data(){
        return {
            dataObj:{},
            subCapTableShow:true,
            famillyShow:'',
            fundShow:'',
            shareList:[],
            warrantVisible:false,
            buttonShow:true,
            warrantData:[],
            labelPosition:'right',
            wrrantDropList:'',
            warrantForm:{
                warrantid: '',
                portfolioid: '',
                fundid: '',
                issuedate: '',
                duedate: '',
                warrantamount:'',
                warrantprice:'',
                round: '',
                sharesno:'',
                securitytypeid: '',
                securitytypeidstr: '',
                currency: "USD",
                ownertype: '',
                warrantownername: '',
                valid: '',
                comment: '',
                fundfamillyname: ''
            },
            warrantFormEmpty:{
                warrantid: '',portfolioid: '',fundid: '',
                issuedate: '',duedate: '', warrantamount:'',
                warrantprice:'',round: '',sharesno:'',
                securitytypeid: '',securitytypeidstr: '',currency: "USD",
                ownertype: '',warrantownername: '',valid: '',
                comment: '',fundfamillyname: ''
            }
        }
    },
    updated(){

    },
    mounted(){
        this.reqSelectList();
        this.reqWrrantList(this.portfolioid);
    },
    methods:{
        reqSelectList(){
            var obj={dictArray:"DDL_WarrantSecurityType,WARRANT_TYPE,FUNDFAMILY,CURRENCY,FUND"};
            axioss.reqSelectList(obj).then(res=>{
                this.wrrantDropList=method.translateFormat(res.data.data);
            })
        },
        reqWrrantList(id){
            var portfolioid=id||this.portfolioid;
            axioss.reqWrrantList(portfolioid).then(res=>{
                this.warrantData=res.data.data;
            })
        },
        querySingalWarrant(id){
            axioss.querySingalWarrant(id).then(res=>{
                this.warrantForm=res.data.data;
                this.$store.dispatch('saveCapTabel',this.warrantForm.portfoliocaptablevaluedetailList);
                this.dataObj.termsigndate=res.data.data.termsigndate;
                this.dataObj.portfolioid=this.portfolioid;
                this.dataObj.closedate=this.warrantForm.closedate;
                this.dataObj.round=this.warrantForm.round;
                this.dataObj.bizid=this.warrantForm.eiid;
                this.dataObj.investtype=res.data.data.investtype;
            })
        },
        submitForm(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var obj=this.warrantForm;
                    if(type=='add'){
                        obj.portfolioid=this.portfolioid;
                        axioss.addWarrant(obj).then(res=>{
                            let status=res.data.code,succMes='Create success',failMes='Create failure';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.warrantVisible=false;
                                Object.assign(this.warrantForm,this.warrantFormEmpty);
                                this.reqWrrantList();
                            }
                        })
                    }else{
                        obj.portfoliocaptablevaluedetailList=this.capFormList;
                        axioss.updateWarrant(obj).then(res=>{
                            let status=res.data.code,succMes='Update success',failMes='Update failure';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.warrantVisible=false;
                                Object.assign(this.warrantForm,this.warrantFormEmpty);
                                this.reqWrrantList();
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
            Object.assign(this.warrantForm,this.warrantFormEmpty);
            this.subCapTableShow=false;
            this.famillyShow=true;
            this.fundShow=false;
            this.warrantVisible=true;
            this.buttonShow=true;
        },
        handleEdit(index,data){
            this.subCapTableShow=true;
            this.famillyShow=false;
            this.fundShow=true;
            this.warrantVisible=true;
            this.buttonShow=false;
            this.querySingalWarrant(data.warrantid);
        },
        deleteWarrant(index,data){
            this.$confirm("This Opt will permanently delete the file, whether it will continue?", "Notice", {
                confirmButtonText: "OK",
                cancelButtonText: "Cancel",
                type: "warning"
            }).then(() => {
                axioss.deleteWarrant(data.warrantid).then(res=>{
                    let status=res.data.code,succMes='Delete success',failMes='Delete failure';
                    let stateCode=this.showToast(status,succMes,failMes);
                    if(stateCode){
                        this.reqWrrantList();
                    }
                })
            })
        },
        formatBoolean(row,cloumn,cellvalue){
            return cellvalue+''
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
        },
        capFormList(){
            return this.$store.state.capTabelData;
        },
        warrantNameShow(){
            var isShow;
            if(this.buttonShow){
                if(this.warrantForm.ownertype=='Investor(except IDG)'){
                    isShow=true;
                    this.famillyShow=false;
                }else{
                    isShow=false;
                    this.famillyShow=true;
                }
                return isShow;
            } 
        }
    },
    components:{
        subCapTable
    }
}
</script>
<style scoped lang="scss">
	.loan{
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