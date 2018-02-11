<template>
<div class="financial loan" ref="Financial">
    <div class="title">Financial Information</div>
    <div class="loan-table-container">
        <h3>Financial Information</h3>
        <el-table :data="tableData" border style="width:100%;">
        <el-table-column prop="date" label="Start Date" width="140">
        </el-table-column>
        <el-table-column prop="name" label="End Date" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Currency" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Revenue" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Cash On Hand" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Total Assets" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Total Liabilities" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Operating Profits" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Gross profits" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Other Assets" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Owner Equal" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Bank Loan/Debt" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Currency" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Rate" width="140">
        </el-table-column>
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
    <el-dialog title="Financial Information" :visible.sync="financialVisible">
        <div class="select-container">
        <el-form :model="form" ref="financialForm">
            <div class="select-fixed">
            <el-form-item label="Start Date">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="End Date">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="Currency">
            <el-select v-model="form.region" placeholder="请选择活动区域">
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
            </el-select>
            </el-form-item>
            <el-form-item label="Revenue">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Gross profits">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Cash On Hand">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Total Assets">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
             <el-form-item label="Operating Profits">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Bank Loan/Debt">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Net Profits">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Total Liabilities">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Retianed Earnings">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="Share Captial">
                <el-input v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>
            </div>
        </el-form>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="financialVisible = false" size="mini">Cancel</el-button>
            <el-button type="primary" @click="submitFinancialForm" size="mini" v-if="buttonShow=='Add'">Add</el-button>
            <el-button type="primary" @click="submitFinancialForm" size="mini" v-else>Update</el-button>
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
            tableData: [{
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
                }, {
                date: '2016-05-04',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1517 弄'
                }, {
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1519 弄'
                }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄'
            }],
            form: {
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            }
        }
    },
    updated(){

    },
    mounted(){
        bus.$on('toScorll',(ace)=>{
           this.scrolltoview(ace,'Financial');
        });
    },
    methods:{
        submitFinancialForm(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        handleAdd(){
            this.financialVisible=true;
            this.buttonShow='Add';
        },
        handleEdit(index,data){
            this.financialVisible=true;
            this.buttonShow='Update';
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
    .loan-table-container .table-foot{
        background:#eee;
        padding:5px 0;
        padding-left:10px;
    }
    .select-container{
        width:100%;
    }
</style>
