<template>
<div class="loan">
    <div class="loan-table-container">
        <h3 class="h3">Loan</h3>
        <el-table ref="singleTable" :data="loanData" border style="float:left;width:auto;">
            <el-table-column fixed prop="fundfamillyname" label="Family" width="110"></el-table-column>
            <el-table-column prop="fundname" label="Fund" width="110"></el-table-column>
            <el-table-column prop="notetype" Loans label="Note Type" width="120"></el-table-column>
            <el-table-column prop="closedate" label="Payment Date" width="120"></el-table-column>
            <el-table-column prop="duedate" label="Due Date" width="90"></el-table-column>
            <el-table-column prop="feedate" label="Fee Date" width="90"></el-table-column>
            <el-table-column prop="notenum" label="Loan Amount" width="150"></el-table-column>
            <el-table-column prop="noteotherfee" label="Other Fees" width="100"></el-table-column>
            <el-table-column prop="currency" label="Currency" width="100"></el-table-column>
            <el-table-column prop="interestrate" label="Rate(%)" width="100"></el-table-column>
            <el-table-column prop="interestdesc" label="Interest Desc." width="150"></el-table-column>
            <el-table-column prop="extension" label="Extension" width="150"></el-table-column>
            <el-table-column prop="extensiondate" label="Extension Date" width="130"></el-table-column>
            <el-table-column prop="taxlotdate" label="Tax Lot Date" width="130"></el-table-column>
            <el-table-column prop="vouncher" label="Vouncher" width="90"></el-table-column>
            <el-table-column prop="vouncher" label="Opt" width="60" fixed='right' v-if="isDetail!='false'">
                <template slot-scope="scope">
                    <i class="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></i>
                    <i class="el-icon-delete" @click="deletLoan(scope.$index, scope.row)"></i>
                </template>
            </el-table-column>
        </el-table>
        <div class="table-foot">
            <i class="el-icon-circle-plus" @click="handleAdd" v-if="isDetail!='false'"></i>
        </div>
    </div>
    <el-dialog title="Loan Edit" :visible.sync="loanVisible" @close="resetForm('loanForm')">
    <div class="select-container">
        <el-form :model="loanForm" ref="loanForm" :label-position='labelPosition' :rules="rules">
            <div class="select-fixed">
                <el-form-item label="Loan Type" prop="notetype">
                    <el-select v-model="loanForm.notetype" placeholder="  " :disabled="disable">
                        <el-option v-for="item in selectlist.DDL_NoteType" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Payment Date" prop="closedate">
                    <el-date-picker v-model="loanForm.closedate" type="date" placeholder=" ">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Fund Family" prop="fundfamillyname">
                    <el-select v-model="loanForm.fundfamillyname" placeholder="  " :disabled='disable' filterable>
                        <el-option v-for="item in selectlist.FUNDFAMILY" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Currency">
                    <el-select v-model="loanForm.currency" placeholder="  ">
                        <el-option v-for="item in selectlist.CURRENCY" :key="item.baseId"
                        :label="item.baseName" :value="item.baseId"></el-option>
                    </el-select>
                </el-form-item>
            </div>
            <div v-if="loanType=='brigeLoans'">
                <el-form-item label="Loan Amount" prop="notenum">
                    <el-input v-model="loanForm.notenum"></el-input>
                </el-form-item>
              <el-form-item label="Due Date">
                <el-date-picker v-model="loanForm.duedate" type="date" placeholder=" ">
                </el-date-picker>
              </el-form-item>
                <el-form-item label="Fees Date">
                    <!--<el-input v-model="loanForm.feedate"></el-input>-->
                  <el-date-picker v-model="loanForm.feedate" type="date" placeholder=" ">
                  </el-date-picker>
                </el-form-item>
                <el-form-item label="Other Fees" prop="noteotherfee">
                    <el-input v-model="loanForm.noteotherfee"></el-input>
                </el-form-item>
                <el-form-item label="Interest Rate(%)">
                    <el-input v-model="loanForm.interestrate"></el-input>
                </el-form-item>
                <el-form-item label="Interest Description">
                    <el-input v-model="loanForm.interestdesc"></el-input>
                </el-form-item>
                <el-form-item label="Extension" style="margin-bottom:15px;">
                    <el-checkbox label="Extension" name="type" v-model="loanForm.extension"></el-checkbox>
                </el-form-item>
                <el-form-item label="Extension Date">
                    <el-date-picker v-model="loanForm.extensiondate" type="date" placeholder=" ">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="TaxLot Date">
                    <el-date-picker v-model="loanForm.taxLotdate" type="date" placeholder=" ">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Voucher">
                    <el-input v-model="loanForm.vouncher"></el-input>
                </el-form-item>
            </div>
        </el-form>
    </div>
    <div slot="footer" class="dialog-footer">
        <el-button @click="loanVisible = false" size='mini'>Cancel</el-button>
        <el-button type="primary" size='mini' @click="submitAdd('loanForm','add')" v-if="submitButton">Create</el-button>
        <el-button type="primary" size='mini' @click="submitAdd('loanForm','update')" v-else>Update</el-button>
    </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from '@/api/axios';
import * as method from "@/api/method";
import mix from "@/api/mixin";
export default {
    name:"Loan",
    mixins:[mix],
    data(){
        return {
            loanVisible:false,
            loanType:'brigeLoans',
            labelPosition:'right',
            loanData: [],
            enableEidt:false,
            enableDelet:false,
            submitButton:true,
            noteid:'',
            selectlist:'',
            disable:false,
            currentRow:'',
            loanForm:{
                fundfamillyname: "",
                closedate:'',
                notetype: "Bridging Loans",
                notenum:'',
                currency: "USD",
                feedate: '',
                noteotherfee:'',
                duedate:'',
                interestrate:"",
                interestdesc:"",
                extension:'',
                extensiondate:'',
                taxlotdate:'',
                vouncher: ""
            },
            loanFormEmpty:{
                fundfamillyname: "",
                closedate:'',
                notetype: "Bridging Loans",
                notenum:'',
                currency: "USD",
                feedate: '',
                noteotherfee:'',
                duedate:'',
                interestrate:null,
                interestdesc:"",
                extension:'',
                extensiondate:'',
                taxlotdate:null,
                vouncher: ""
            },
            rules: {
                notenum: [
                    { required: true, message: 'The input box is required', trigger: 'blur' },
                ],
                notetype: [
                    { required: true, message: 'The input box is required', trigger: 'change' }
                ],
                fundfamillyname: [
                    { required: true, message: 'The input box is required', trigger: 'change' }
                ],
                closedate: [
                    {required: true, message: 'The input box is required', trigger: 'change' }
                ],
                noteotherfee: [
                    {required: true, message: 'The input box is required', trigger: 'change' }
                ]
            }
        }
    },
    updated(){
    },
    mounted(){
        this.reqloanlist(this.portfolioid);
        this.reqSelectList();
    },
    methods:{
        reqloanlist(id){
            axioss.reqLoanList(id).then(res=>{
                var obj=res.data.data;
                var newdata=this.handleData(obj)
                this.loanData=newdata;
            })
        },
        reqSelectList(){
            var obj={dictArray:"DDL_NoteType,FUNDFAMILY,CURRENCY"};
            axioss.reqSelectList(obj).then(res=>{
                var data=res.data.data,newdata;
                newdata=method.translateFormat(data);
                this.selectlist=newdata;
            })
        },
        queryLoanSingal(id){
            axioss.queryLoanSingal(id).then(res=>{
                var newdata=res.data.data;
                this.loanForm=newdata;
            })
        },
        submitAdd(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    console.log(this.loanForm);
                    var obj=this.loanForm;
                    if(type=="add"){
                        obj.portfolioid=this.portfolioid;
                        axioss.addLoan(obj).then(res=>{
                            console.log(res);
                            if(res.data.code=="SUCCESS"){
                                this.$message({
                                    type:'success',
                                    message: 'Create success'
                                })
                                this.loanVisible=false;
                                this.reqloanlist(this.portfolioid);
                            }else{
                                this.$message({
                                    type:'warning',
                                    message: 'Create failure'
                                })
                            }
                        })
                    }else{
                        axioss.updateLoan(obj).then(res=>{
                            console.log(res);
                            if(res.data.code=="SUCCESS"){
                                this.$message({
                                    type:'success',
                                    message: 'Update success'
                                })
                                this.loanVisible=false;
                                this.reqloanlist(this.portfolioid);
                                Object.assign(this.loanForm,this.loanFormEmpty);
                            }else{
                                this.$message({
                                    type:'warning',
                                    message: 'Update failure'
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
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        deletLoan(index,data){
            var id=data.noteid;
            this.$confirm("This Opt will permanently delete the file, whether it will continue?", "Notice", {
                confirmButtonText: "OK",
                cancelButtonText: "Cancel",
                type: "warning"
            }).then(()=>{
                axioss.deletLoan(id).then(res=>{
                    let status=res.data.code,succMes='Delete success',failMes='Delete failure';
                    let stateCode=this.showToast(status,succMes,failMes);
                    if(stateCode){
                        this.reqloanlist(this.portfolioid);
                    }
                })
            })
            
        },
        
        formatBoolean(row,cloumn,cellvalue){
            return cellvalue+''
        },
        handleEdit(index,data){
            var id=data.noteid;
            this.submitButton=false;
            this.loanVisible=true;
            this.disable=true;
            this.queryLoanSingal(id)
        },
        handleAdd(){
            Object.assign(this.loanForm,this.loanFormEmpty);
            this.loanVisible=true;
            this.submitButton=true;
            this.disable=false;
        },
        handleData(data){
            for(var i=0;i<data.length;i++){
                data[i].closedate=method.toLocalString(data[i].closedate,"/");
                data[i].feedate=method.toLocalString(data[i].feedate,"/");
                data[i].duedate=method.toLocalString(data[i].duedate,"/");
                data[i].extensiondate=method.toLocalString(data[i].extensiondate,"/");
                data[i].taxlotdate=method.toLocalString(data[i].taxlotdate,"/");
                data[i].notenum=method.toThousands(data[i].notenum);
            }
            return data;
        }
    },
    computed:{
        portfolioid:function(){
            if(this.$store.state.portfolioid==''){
                this.$store.dispatch('updateData');
            }
            return this.$store.state.portfolioid
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
    .displaynone{
        display: none;
    }
</style>
