<template>
<div class="director loan" ref="Director">
    <div class="title">Director</div>
    <div class="loan-table-container">
        <h3>Director</h3>
        <div class="number">
            <label style="font-size:14px;margin-left:15px;">董事会成员总数:</label>
            <el-input v-model="form.name" placeholder="请输入数量" style="margin-left:15px;"></el-input>
            <el-button size="mini" type="primary" style="margin-left:15px;">Save</el-button>
        </div>
        <el-table :data="tableData" border style="width:auto;">
        <el-table-column prop="date" label="Type" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Director/Supervistor" width="220">
        </el-table-column>
        <el-table-column prop="name" label="Start Date" width="140">
        </el-table-column>
        <el-table-column prop="name" label="End Date" width="140">
        </el-table-column>
        <el-table-column label="操作" width="60" v-if="isDetail!='false'">
            <template slot-scope="scope">
                <i class="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></i>
                <i class="el-icon-delete" @click="handleDelet(scope.$index, scope.row)"></i>
            </template>
        </el-table-column>
        </el-table>
        <div class="table-foot" v-if="isDetail!='false'">
            <i class="el-icon-circle-plus" @click="handleAdd"></i>
        </div>
    </div>
    <el-dialog title="Financial Information" :visible.sync="financialVisible">
        <div class="select-container">
        <el-form :model="form" ref="financialForm">
            <div class="select-fixed">
                <el-form-item label="Currency">
                <el-select v-model="form.region" placeholder="请选择活动区域">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
                </el-form-item>
                <el-form-item label="Currency">
                <el-select v-model="form.region" placeholder="请选择活动区域">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
                </el-form-item>
                <el-form-item label="Start Date">
                    <el-input v-model="form.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="End Date">
                    <el-input v-model="form.name"></el-input>
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
    name:"director",
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
           this.scrolltoview(ace,'Director');
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
	.director{
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
    .title{
        padding:10px;
        box-sizing: border-box;
        font-size: 20px;
        color:white;
        font-weight:bold;
        background:#00a1e9;
    }
    .number{
        padding:10px 0;
    }
</style>
