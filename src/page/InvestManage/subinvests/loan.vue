<template>
<div class="loan">
    <div class="loan-table-container">
        <h3 class="h3">Loan</h3>
        <el-table :data="tableData" border style="overflow:hidden;">
            <el-table-column prop="date" label="日期"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
        </el-table>
        <div class="table-foot">
            <i class="el-icon-edit"></i>
            <i class="el-icon-delete"></i>
        </div>
    </div>

    <div class="select-container">
        <el-form :model="loanForm" ref="loanForm" :label-position='labelPosition'>
            <div class="select-fixed">
                <el-form-item label="Loan Type">
                    <el-select v-model="loanForm.value1" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value"
                        :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Payment Date">
                    <el-date-picker v-model="loanForm.value2" type="date" placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Fund Family">
                    <el-select v-model="loanForm.value3" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value"
                        :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Currency">
                    <el-select v-model="loanForm.value4" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value"
                        :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
            </div>
            <div v-if="loanType=='brigeLoans'">
                <el-form-item label="Loan Amount">
                    <el-input v-model="loanForm.value5"></el-input>
                </el-form-item>
                <el-form-item label="Legal Fees">
                    <el-input v-model="loanForm.value55"></el-input>
                </el-form-item>
                <el-form-item label="Other Fees">
                    <el-input v-model="loanForm.value6"></el-input>
                </el-form-item>
                <el-form-item label="Due Date">
                    <el-date-picker v-model="loanForm.value7" type="date" placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Interest Rate">
                    <el-input v-model="loanForm.value8"></el-input>
                </el-form-item>
                <el-form-item label="Interest Description">
                    <el-input v-model="loanForm.value9"></el-input>
                </el-form-item>
                <el-form-item label="Extension" prop="type" style="margin-bottom:15px;">
                    <el-checkbox-group v-model="loanForm.value10">
                    <el-checkbox label="美食" name="type"></el-checkbox>
                    <el-checkbox label="地推" name="type"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="Extension Date">
                    <el-date-picker v-model="loanForm.value11" type="date" placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="TaxLot Date">
                    <el-date-picker v-model="loanForm.value12" type="date" placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="Voucher">
                    <el-input v-model="loanForm.value13"></el-input>
                </el-form-item>
            </div>
        </el-form>
    </div>
    <!-- <div v-else-if="loanType=='Calpatal Interest'">
        <el-form-item label="Interest From">
            <el-select v-model="loanForm.value14" placeholder="请选择">
                <el-option v-for="item in options" :key="item.value"
                :label="item.label" :value="item.value"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="Proceeds">
            <el-input v-model="loanForm.value15"></el-input>
        </el-form-item>
    </div>
    <div v-else>
        <el-form-item label="Fee For">
            <el-select v-model="loanForm.value14" placeholder="请选择">
                <el-option v-for="item in options" :key="item.value"
                :label="item.label" :value="item.value"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="Other Fees">
            <el-input v-model="loanForm.value15"></el-input>
        </el-form-item>
    </div> -->
</div>
</template>
<script>	
import axioss from '@/api/axios';
import * as method from "@/api/method";
export default {
    name:"loan",
    data(){  
        return {
            loanType:'brigeLoans',
            labelPosition:'right',
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
            loanForm:{
                value1:'',value2:'',value3:'',value4:'',value5:'',value55:'',
                value6:'',value7:'',value8:'',value9:'',value10:'',
                value11:'',value12:'',value13:'',value14:'',value15:''
            },
            options:[
               {
                value: '选项1',
                label: '黄金糕'
                }, {
                value: '选项2',
                label: '双皮奶'
                }, {
                value: '选项3',
                label: '蚵仔煎'
                } 
            ]
        }   
    },
    updated(){  
        
    },
    mounted(){
       
    },
    methods:{ 
        
    },
    watch:{
        'addShareShow':{
            handler(newValue,oldValue){
                   
            },
            deep:true
        }
    },
    directives:{
        isedit:{
            inserted:function(el){
                
            }
        }
    }	
}
</script>
<style scoped lang="scss">
	.loan{
        .loan-table-container h3{
            font-size:20px;
            padding:5px 0;
            background:#eee;
            margin: 0;
            border-bottom: 1px solid #ebeef5;
        }
    }
    .loan-table-container .table-foot{
        background:#eee;
        padding:5px 0;
    }
    .select-container{
        width:80%;
    }
</style>