<template>
<div class="spv-member-list">
    <Header></Header>
    <div class="spv-list-container">
        <div class="spv-left" >
            <el-card class="box-card" style="height:464px;">
                <div slot="header" class="clearfix">
                    <span>SPV</span>
                    <el-button style="float: right; padding: 3px 0" type="text">Add</el-button>
                </div>
                <el-tree :data="treedata"
                    highlight-current 
                    ref="spvTree" 
                    :props="defaultProps" 
                    @node-click="handleNodeClick">
                </el-tree>
            </el-card>
        </div>
        <div class="spv-right">
            <div class="spv-right-top">    
                <div class="clearfix">
                    <span>基本信息</span>
                </div>
                <el-table
                    :data="tableData1"
                    border
                    stripe
                    style="width: 100%">
                    <el-table-column
                    prop="date"
                    label="日期"
                    width="180">
                    </el-table-column>
                    <el-table-column
                    prop="name"
                    label="姓名"
                    width="180">
                    </el-table-column>
                    <el-table-column
                    prop="address"
                    label="地址">
                    </el-table-column>
                </el-table>
            </div>
            <div class="spv-right-bottom">    
                <div class="clearfix">
                    <div class="form-container">
                        <el-form :model="ruleForm" :rules="rules" label-width="40px" ref="ruleForm1">
                            <el-form-item label="Time">
                                <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1" style="width: 100%;"></el-date-picker>
                            </el-form-item>
                        </el-form>
                    </div>
                    <el-button style="float: left; margin:3px 0 0 10px" size="medium" type="primary" @click="dialogTableVisible=true">Add</el-button>
                </div>
                <el-table :data="tableData" border stripe :span-method="objectSpanMethod">
                    <el-table-column prop="date" label="日期" width="180" sortable></el-table-column>
                    <el-table-column prop="name" label="股东名称" width="180"></el-table-column>
                    <el-table-column prop="address" label="股东类别"></el-table-column>
                    <el-table-column prop="address" label="认缴金额(元)" sortable></el-table-column>
                    <el-table-column prop="address" label="占比(%)" sortable></el-table-column>
                </el-table>
            </div>
        </div>
    </div>
    <el-dialog title="ADD" :visible.sync="dialogTableVisible">
        <div class="edit-input">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm2" :inline="true">
                <el-form-item label="股东类别">
                    <el-select v-model="ruleForm.region" placeholder="请选择活动区域">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="股东名称">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="证件凭证">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="金额(万元)">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
            </el-form>
        </div>
        <el-table bordered :data="tableData">
            <el-table-column prop="date" label="日期" width="180" sortable></el-table-column>
            <el-table-column prop="name" label="股东名称" width="180"></el-table-column>
            <el-table-column prop="address" label="股东类别"></el-table-column>
            <el-table-column prop="address" label="认缴金额(元)" sortable></el-table-column>
            <el-table-column prop="address" label="占比(%)" sortable></el-table-column>
            <el-table-column prop="address" label="操作"></el-table-column>
        </el-table>
    </el-dialog>
</div>
</template>
<script>
import Header from "../../components/common/Header";	
import axioss from '../../api/axios';
export default {
    name:"spvlist",
    data:function(){  
        return {
            dialogTableVisible:false,
            labelposition:"right",
            treedata: [{
                id:0,
                label:'SPV',
                children:[{
                id: 1,
                label: '一级 1',
                children: [{
                    id: 4,
                    label: '二级 1-1',
                    children: [{
                    id: 9,
                    label: '三级 1-1-1'
                    }, {
                    id: 10,
                    label: '三级 1-1-2'
                    }]
                }]
                }, {
                id: 2,
                label: '一级 2',
                children: [{
                    id: 5,
                    label: '二级 2-1'
                }, {
                    id: 6,
                    label: '二级 2-2'
                }]
                }, {
                id: 3,
                label: '一级 3',
                children: [{
                    id: 7,
                    label: '二级 3-1'
                }, {
                    id: 8,
                    label: '二级 3-2'
                }]
                }]
            }],
            defaultProps: {
            children: 'children',
                label: 'label'
            },
            tableData1: [{
                date: '2016-06-02',
                name: '王小虎',
                address: '上海市普陀区金'
            }],
            tableData: [{
                date: '2016-08-02',
                name: '王小虎',
                address: '上海市普陀区金'
            },
            {
                date: '2016-05-05',
                name: '王小虎',
                address: '上海市普陀区金'
            },
            {
                date: '2016-05-06',
                name: '王小虎',
                address: '上海市普陀区金'
            },
            {
                date: '2016-05-07',
                name: '王小虎',
                address: '上海市普陀区金'
            }
            ],
            ruleForm:{
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
            rules:{
                region:[
                    { required: true, message: '请选择活动区域', trigger: 'change' }
                ]
            }
        }   
    },
    mounted:function(){
       
       
    },
    methods:{
        handleNodeClick(a,b,c){  
            console.log(a)
            console.log(b)
            console.log(c)
            console.log(this.$refs.spvTree.getCheckedNodes());
        },
        objectSpanMethod({ row, column, rowIndex, columnIndex }) {
        if (columnIndex === 0) {
          if (rowIndex % 2 === 0) {
            return {
              rowspan: 2,
              colspan: 1
            };
          } else {
            return {
              rowspan: 0,
              colspan: 0
            };
          }
        }
      }
    },
    components:{
        Header
    } 	
}
</script>
<style scoped lang="scss">
	.spv-member-list{
        width:100%;
        padding:15px 10px;
        box-sizing: border-box;
    }
    .spv-list-container {
        .form-container{
            width:20%;
            float:left;
            .el-form-item{
                margin-bottom: 0;
            }
        }
    }
    .spv-list-container{
        .spv-left{
            float:left;
            width:15%;
            height:600px;
        }
        .spv-right{
            float:left;
            width:80%;
            margin-left:2%;
            .spv-right-top{
                margin-bottom:30px;
                border-radius:4px;
                overflow:hidden;
            }
            .spv-right-bottom{
                border-radius:4px;
                overflow:hidden;
            }
        }
    }
    .spv-right .clearfix{
        background:#eee;
        padding:5px 15px;
        box-sizing:border-box;
        span{
            color:#606266;
        }
    }
    .spv-list-container::after{
        content:'';
        height: 0;
        clear: both;
        display:block;
        visibility: hidden;
    }
</style>