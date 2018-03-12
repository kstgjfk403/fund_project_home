<template>
<div class="financial loan" ref="Financial">
    <div class="title">Financial Information</div>
    <div class="loan-table-container">
        <h3>Financial Information</h3>
        <el-table :data="finList" border style="width:auto;display:inline-block;">
            <el-table-column prop="portfoliofinanciallot" label="portfoliofinanciallot" width="140" v-if='false'>
            </el-table-column>    
            <el-table-column prop="startdate" label="Start Date" width="140" :formatter="formatDate">
            </el-table-column>
            <el-table-column prop="enddate" label="End Date" width="140" :formatter="formatDate">
            </el-table-column>
            <el-table-column prop="revenue" label="Revenue" width="140">
            </el-table-column>
            <el-table-column prop="cashassets" label="Cash On Hand" width="140">
            </el-table-column>
            <el-table-column prop="totalassets" label="Total Assets" width="140">
            </el-table-column>
            <el-table-column prop="totalliabilities" label="Total Liabilities" width="140">
            </el-table-column>
            <el-table-column prop="operatingprofits" label="Operating Profits" width="140">
            </el-table-column>
            <el-table-column prop="grossprofits" label="Gross profits" width="140">
            </el-table-column>
            <el-table-column prop="debt" label="Bank Loan/Debt" width="140" :formatter="numberFormat">
            </el-table-column>
            <el-table-column prop="currency" label="Currency" width="140">
            </el-table-column>
            <el-table-column label="Opt" width="60" fixed='right' v-if="isDetail!='false'">
                <template slot-scope="scope">
                    <i class="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></i>
                    <i class="el-icon-delete" @click="handleDelet(scope.$index, scope.row)"></i>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination-container">
            <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :page-size='10' :current-page.sync="pageIndex" @current-change="handleCurrentChange" style="position:absolute;left:50%;transform: translate(-50%);"></el-pagination>    
        </div>
        <div class="table-foot">
            <i class="el-icon-circle-plus" @click="handleAdd" v-if="isDetail!='false'"></i>
        </div>
    </div>
    <el-dialog title="Financial Information" :visible.sync="financialVisible">
        <div class="select-container">
        <el-form :model="finForm" ref="financialForm">
            <div class="select-fixed">
            <el-form-item label="Start Date">
                <el-date-picker v-model="finForm.startdate" type="date" placeholder=" ">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="End Date">
                <el-date-picker v-model="finForm.enddate" type="date" placeholder=" ">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="Currency">
            <el-select v-model="finForm.currency" placeholder="  ">
                <el-option v-for='item in currencyList' :label="item" :value="item" :key='item'></el-option>
            </el-select>
            </el-form-item>
            <el-form-item label="Revenue">
                <el-input v-model="finForm.revenue" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Gross profits">
                <el-input v-model="finForm.grossprofits" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Cash On Hand">
                <el-input v-model="finForm.cashassets" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Total Assets">
                <el-input v-model="finForm.totalassets" auto-complete="off"></el-input>
            </el-form-item>
             <el-form-item label="Operating Profits">
                <el-input v-model="finForm.operatingprofits" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Bank Loan/Debt">
                <el-input v-model="finForm.debt" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Net Profits">
                <el-input v-model="finForm.netprofits" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Total Liabilities">
                <el-input v-model="finForm.totalliabilities" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Retailed Earning ">
                <el-input v-model="finForm.retainedearnings" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Share Captial">
                <el-input v-model="finForm.sharecapital" auto-complete="off"></el-input>
            </el-form-item>
            </div>
        </el-form>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="financialVisible = false" size="mini">Cancel</el-button>
            <el-button type="primary" @click="submitFinForm('financialForm','add')" size="mini" v-if="buttonShow=='Add'">Add</el-button>
            <el-button type="primary" @click="submitFinForm('financialForm','update')" size="mini" v-else>Update</el-button>
        </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from '@/api/axios';
import bus from '@/api/eventbus'
import mix from "@/api/mixin"
export default {
    name:"financialinformation",
    mixins:[mix],
    
    data(){
        return {
            heightObj:'',
            financialVisible:false,
            buttonShow:'Add',
            finList: [],
            currencyList:[],
            pageIndex:1,
            pageCount:5,
            allcount:0,
            finForm: {
                startdate:'',
                enddate:'',
                currency:'',
                revenue:'',
                grossprofits:'',
                cashassets:'',
                totalassets:'',
                operatingprofits:'',
                debt:'',
                netprofits:'',
                totalliabilities:'',
                retainedearnings:'',
                sharecapital:''
            },
            finFormEmpty: {
                startdate:'',enddate:'',currency:'',
                revenue:'',grossprofits:'',cashassets:'',
                totalassets:'',operatingprofits:'',debt:'',
                netprofits:'',totalliabilities:'',
                retainedearnings:'',sharecapital:''
            }
        }
    },
    updated(){

    },
    mounted(){
        bus.$on('toScorll',(ace)=>{
           this.scrolltoview(ace,'Financial');
        });
        this.reqFinList(this.pageIndex,this.pageCount);
        this.reqCurrencyList();
    },
    methods:{
        reqFinList(pageIndex,pageCount){
            var obj={
                portfolioid:this.portfolioid,
                pageIndex:pageIndex,
                pageCount:pageCount
            }
            axioss.reqFinList(obj).then(res=>{
                this.finList=res.data.data;
                this.allcount=res.data.count;
            })
        },
        reqCurrencyList(){
            axioss.reqCurrencyList().then(res=>{
                this.currencyList=res.data.data;
            })
        },
        queryFin(id){
            axioss.queryFin(id).then(res=>{
                this.finForm=res.data.data;
            })            
        },
        submitFinForm(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var obj=this.finForm;
                    obj.portfolioid=this.portfolioid;
                    if(type=='add'){   
                        axioss.addFin(obj).then(res=>{
                            let status=res.data.code,succMes='Create success',failMes='Create failure';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.financialVisible=false;
                                Object.assign(this.finForm,this.finFormEmpty);
                                this.reqFinList(this.pageIndex,this.pageCount);
                            }
                        })
                    }else{
                        axioss.updateFin(obj).then(res=>{

                            let status=res.data.code,succMes='Update success',failMes='Update failure';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.financialVisible=false;
                                Object.assign(this.finForm,this.finFormEmpty);
                                this.reqFinList(this.pageIndex,this.pageCount);
                            }
                        })

                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        handleCurrentChange(){
            this.reqFinList(this.pageIndex,this.pageCount);
        },
        handleAdd(){
            this.financialVisible=true;
            this.buttonShow='Add';
        },
        handleEdit(index,data){
            this.financialVisible=true;
            this.buttonShow='Update';
            this.queryFin(data.portfoliofinanciallot);
        },
        handleDelet(index,data){
            var id=data.portfoliofinanciallot;
            console.log(id)
            this.$confirm("This Opt will permanently delete the file, whether it will continue?", "Notice", {
                confirmButtonText: "OK",
                cancelButtonText: "Cancel",
                type: "warning"
            }).then(() => {
                axioss.deleteFin(id).then(res => {
                    console.log(res)   
                    let status=res.data.code,succMes='Delete success!',failMes='Delete failure!';
                    let stateCode=this.showToast(status,succMes,failMes);
                    if(stateCode){
                        this.reqFinList(this.pageIndex,this.pageCount);
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
	.financial{
        margin-top:20px;
        border:1px solid #ddd;
        .loan-table-container h3{
            font-size:16px;
            padding:5px 0;
            padding-left:10px;
            background:#eee;
            margin: 0;
            border-bottom: 1px solid #ebeef5;
        }
    }
    
    .loan-table-container{
        position:relative;
    }
    .loan-table-container .table-foot{
        background:#eee;
        padding:5px 0;
        padding-left:10px;
    }
    .select-container{
        width:100%;
    }
    .pagination-container{
        width:100%;
        height:35px;
    }
</style>
