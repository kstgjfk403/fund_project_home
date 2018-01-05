<template>
<div class="spv-member-list">
    <Header></Header>
    <div class="spv-list-container">
        <div class="spv-left">
            <el-card class="box-card">
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
                <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>基本信息</span>
                </div>
                <el-table
                    :data="tableData"
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
            </el-card>
            </div>
            <div class="spv-right-bottom">
                <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <div class="form-container">
                        <el-form :model="ruleForm" :rules="rules" label-width="40px" ref="ruleForm">
                            <el-form-item label="Time">
                                <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1" style="width: 100%;"></el-date-picker>
                            </el-form-item>
                        </el-form>
                    </div>
                    <el-button style="float: left; margin:2px 0 0 10px" size="medium" type="primary">Add</el-button>
                </div>
                <el-table
                    :data="tableData"
                    border
                    stripe
                    style="width: 100%;">
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
            </el-card>
            </div>
        </div>
    </div>
</div>
</template>
<script>
import Header from "../../components/common/Header";	
import axioss from '../../api/axios';
export default {
    name:"spvlist",
    data:function(){  
        return {
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
            tableData: [{
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }],
            ruleForm:{
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
    mounted:function(){
       
       
    },
    methods:{
        handleNodeClick(a,b,c){  
            console.log(a)
            console.log(b)
            console.log(c)
            console.log(this.$refs.spvTree.getCheckedNodes());
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
        padding:0 10px;
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
        }
        .spv-right{
            float:left;
            width:85%;
            .spv-right-top{
                margin-bottom:30px;
            }
        }
    }
    
</style>