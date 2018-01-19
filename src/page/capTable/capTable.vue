<template>
<div class="capTable">
    <div class="title" ref="CapTable">CapTable History</div>
    <div class="loan-table-container">
        <h3 class="h3">CapTable History</h3>
        <el-table :data="tableData3" style="width: 100%">
            <el-table-column prop="date" label="股东信息" width="150">
                <el-table-column prop="name" label="股东类型" width="130"></el-table-column>
                <el-table-column prop="name" label="股东" width="130"></el-table-column>
            </el-table-column>
            <el-table-column label="第一轮 2014-05-06">
                <el-table-column prop="name" label="认缴注册资本" width="130"></el-table-column>
                <el-table-column prop="province" label="认缴投资额" width="130"></el-table-column>
                <el-table-column prop="address" label="股比" width="130"></el-table-column>
                <el-table-column prop="address" label="股比" width="130"></el-table-column>
            </el-table-column>
            <el-table-column label="第二轮 2014-05-06">
                <el-table-column prop="name" label="认缴注册资本" width="130"></el-table-column>
                <el-table-column prop="province" label="认缴投资额" width="130"></el-table-column>
                <el-table-column prop="address" label="股比" width="130"></el-table-column>
                <el-table-column prop="address" label="股比" width="130"></el-table-column>
            </el-table-column>
            <el-table-column label="合计">
                <el-table-column prop="province" label="累计投资额" width="130"></el-table-column>
                <el-table-column prop="address" label="股比" width="130"></el-table-column>
            </el-table-column>
        </el-table>
        <div class="table-foot">
            <i class="el-icon-circle-plus" @click="handleAdd"></i>
        </div>
    </div>
    <el-dialog title="capTable Edit" :visible.sync="capVisible">
        <div class="select-container">
            <div class="select-fixed">
                <el-form :model="capForm" ref="capForm">
                    <el-form-item label="Invest Type">
                        <el-select placeholder="请选择" v-model="capForm.a">
                            <el-option label="dsf" value="dfddsf" key="w"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Payment Date">
                        <el-date-picker type="date" placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item>
                        <el-select v-model="capForm.b">
                            <el-option label="dsf" value="dfdsf" key="d"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <div class="content">
            <el-table :data="tableData6" border 
                :summary-method="getSummaries" show-summary style="width: 100%;">
                <el-table-column prop="id" label="ID" width="180"></el-table-column>
                <el-table-column prop="name" label="姓名"></el-table-column>
                <el-table-column prop="amount1" label="数值 1（元）"> </el-table-column>
                <el-table-column prop="amount2" label="数值 2（元）"></el-table-column>
                <el-table-column prop="amount3" label="数值 3（元）"></el-table-column>
            </el-table>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="capVisible = false" size='mini'>取 消</el-button>
        </div>
    </el-dialog>
</div>
</template>
<script>	
import axioss from '@/api/axios';
import * as method from "@/api/method";
import bus from "@/api/eventbus";
import mix from "@/api/mixin";
export default {
    name:"CapTable",
    mixins:[mix],
    data(){  
        return {
            capVisible:false,
            capForm:{
            },
            tableData3: [{
                date: '2016-05-03',
                name: '王小虎',
                province: '上海',
                city: '普陀区',
                address: '上海市普陀区金',
                zip: 200333
                }, {
                date: '2016-05-02',
                name: '王小虎',
                province: '上海',
                city: '普陀区',
                address: '上海市普陀区金沙',
                zip: 200333
                }, {
                date: '2016-05-04',
                name: '王小虎',
                province: '上海',
                city: '普陀区',
                address: '上海市普陀区金沙',
                zip: 200333
                }],
            tableData6: [{
                id: '12987122',
                name: '王小虎',
                amount1: '234',
                amount2: '3.2',
                amount3: 10
                }, {
                id: '12987123',
                name: '王小虎',
                amount1: '165',
                amount2: '4.43',
                amount3: 12
                }, {
                id: '12987124',
                name: '王小虎',
                amount1: '324',
                amount2: '1.9',
                amount3: 9
                }, {
                id: '12987125',
                name: '王小虎',
                amount1: '621',
                amount2: '2.2',
                amount3: 17
                }, {
                id: '12987126',
                name: '王小虎',
                amount1: '539',
                amount2: '4.1',
                amount3: 15
            }]
        }
    },
    updated(){
    },
    mounted(){
        bus.$on('toscorll',(ace)=>{
           this.scrolltoview(ace);
       });
    },
    methods:{
        handleAdd(){
            this.capVisible=true;
        },
        getSummaries(param){
            const { columns, data } = param;
            const sums = [];
            console.log(columns)
            columns.forEach((column, index) => {
            if (index === 0) {
                sums[index] = '总价';
                return;
            }
            const values = data.map(item => Number(item[column.property]));
            if (!values.every(value => isNaN(value))) {
                sums[index] = values.reduce((prev, curr) => {
                const value = Number(curr);
                if (!isNaN(value)) {
                    return prev + curr;
                } else {
                    return prev;
                }
                }, 0);
                sums[index] += '元';
            } else {
                sums[index] = 'N/A';
            }
            });
            return sums;
        }
    },
    computed:{

    }
}
</script>
<style scoped lang="scss">
	
        .loan-table-container h3{
            font-size:16px;
            padding:5px 0;
            padding-left:10px;
            background:#eee;
            margin: 0;
            border-bottom: 1px solid #ebeef5;
        }
    
    .loan-table-container .table-foot{
        background:#eee;
        padding:5px 0;
        padding-left:10px;
    }
    .select-container{
        width:100%;
    }
    .capTable{
        margin-top:20px;
        border:1px solid #ddd;
    }
    .title{
        padding:10px;
        box-sizing: border-box;
        font-size: 20px;
        color:white;
        font-weight:bold;
        background:#00a1e9;
    }
</style>