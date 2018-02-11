<template>
<div class="financial loan" ref="Business">
    <div class="title">Business Update</div>
    <div class="loan-table-container">
        <h3>Bov</h3>
        <el-table :data="businessTableData" border style="width:100%;">
        <el-table-column prop="date" label="Update Date" width="140">
        </el-table-column>
        <el-table-column prop="name" label="Head Count" width="140">
        </el-table-column>
        <el-table-column prop="address" label="WBStatus" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Company Background" width="190">
        </el-table-column>
        <el-table-column prop="address" label="Development" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Valuation" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Management" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Disposal" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Comparables" width="140">
        </el-table-column>
        <el-table-column prop="address" label="Update Business" width="140">
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
    <el-dialog title="Business Information" :visible.sync="businessVisible">
        <div class="select-container">
        <el-form :model="form" ref="businessForm">
            <div class="select-fixed">
            <el-form-item label="Head Count:">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="Update Date:">
                <el-date-picker v-model="form.closedate" type="date" placeholder="选择日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="White Book Status:">
            <el-select v-model="form.region" placeholder="请选择活动区域">
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
            </el-select>
            </el-form-item>
            <el-form-item label="Company BG:">
                <el-input v-model="form.name" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Development">
                <el-input v-model="form.name" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Valuation">
                <el-input v-model="form.name" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Management:">
                <el-input v-model="form.name" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Disposal">
                <el-input v-model="form.name" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Comparables">
                <el-input v-model="form.name" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Update Business">
                <el-input v-model="form.name" autosize type="textarea"></el-input>
            </el-form-item>
            </div>
        </el-form>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="businessVisible = false" size="mini">Cancel</el-button>
            <el-button type="primary" @click="submitbusinessForm" size="mini" v-if="buttonShow=='Add'">Add</el-button>
            <el-button type="primary" @click="submitbusinessForm" size="mini" v-else>Update</el-button>
        </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from '@/api/axios';
import bus from '@/api/eventbus'
import mix from "@/api/mixin"
export default {
    name:"business",
    mixins:[mix],
    data(){
        return {
            heightObj:'',
            businessVisible:false,
            buttonShow:'Add',
            businessTableData: [{
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金'
                }, {
                date: '2016-05-04',
                name: '王小虎',
                address: '上海市普陀区金'
                }, {
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金'
                }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金'
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
           this.scrolltoview(ace,'Business');
        });
    },
    methods:{
        submitbusinessForm(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        handleAdd(){
            this.businessVisible=true;
            this.buttonShow='Add';
        },
        handleEdit(index,data){
            this.businessVisible=true;
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
