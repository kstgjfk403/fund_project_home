<template>
<div class="idgaccelchinareport">
    <Header/>
    <div class="search">
        <el-form :inline="true" :model="searchForm" ref="searchForm">
            <el-form-item label="Fund">
                <el-select v-model="searchForm.fundid" placeholder="" filterable>
                <el-option :label="item.baseName" :value="item.baseId" :key="item.baseId" v-for="item in fundList"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Close Date">
                <el-date-picker v-model="searchForm.closedate" type="date" placeholder="选择日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size="mini" @click="searchSubmit('searchForm')">查询</el-button>
            </el-form-item>
        </el-form>
    </div>
    <!-- 导出excel -->
    <button id="but" @click="exportTab" style="margin-top:10px;margin-bottom:10px;">导出Excel</button>
    <!-- 需要导出的表格内容区域 -->
    <el-table :data="InvestdetailCaptal"  border style="width: 100%" id="excel">
        <el-table-column fixed prop="Company" label="Company" width="140"></el-table-column>
        <el-table-column fixed prop="TaxLotDate" label="Tax Lot Date" width="120"></el-table-column>
        <el-table-column prop="SecurityType" label="Security Type" width="150"></el-table-column>
        <el-table-column prop="SeriesType" label="Series Type" width="120"></el-table-column>
        <el-table-column prop="TotalCost" label="Total Cost" width="120" :formatter='transformNum'></el-table-column>
        <el-table-column prop="TotalMktValue" label="Total Mkt Value" width="150" :formatter='transformNum'></el-table-column>
        <el-table-column prop="UnrealizedGL" label="Unrealized G/L" width="120" :formatter='transformNum'></el-table-column>
        <el-table-column prop="CostPerShare" label="Cost/Share" width="120"></el-table-column>
        <el-table-column prop="fmvPerShare" label="FMV/Share" width="150"></el-table-column>
        <el-table-column prop="publicPrivate" label="Public/Private" width="120"></el-table-column>
        <el-table-column prop="TotalShareOwned" label="Total Shares Owned" width="140" :formatter='transformNum'></el-table-column>
        <el-table-column prop="TotalCSEOwned" label="Total CSE Owned" width="150" :formatter='transformNum'></el-table-column>
        <el-table-column prop="TotalCSEOutstanding" label="Total CSEs Outstanding" width="160" :formatter='transformNum'></el-table-column>
        <el-table-column prop="Ownership" label="% Ownership" width="120"></el-table-column>
        <el-table-column prop="PaidInCap" label="Paid In Cap" width="150"></el-table-column>
        <el-table-column prop="oneLineDesc" label="1 Line Desc" width="300"></el-table-column>
        <el-table-column prop="currency" label="Currency" width="120"></el-table-column>
    </el-table>
</div>
</template>
<script>
import Header from "@/components/common/Header";
import axioss from '@/api/axios';
import * as method from '@/api/method';
export default {
  name: "idgaccelchinareport",
  data: function() {
    return {
        searchForm:{
            fundid:'F0022',
            closedate:''
        },
        fundList:[],
        InvestdetailCaptal:[]
    };
  },
  mounted:function(){
      this.reportDropList();
  },
  methods:{
        exportTab(){
            
            var oHtml1 = document.querySelector("#excel .el-table__fixed-header-wrapper").outerHTML;
            var oHtml2 = document.querySelector("#excel .el-table__fixed-body-wrapper").outerHTML;
            var oHtml = oHtml1+oHtml2;    
            //console.log(oHtml);
            // 这里我是用了es6的字符串语法``,对es6不熟悉的同学可以去查一下。
            // 有没有发现我这里的样式就是和HTML上的style复制下来的，
            var excelHtml = `
            <html>
            <head>
            <meta charset='utf-8' />
            <style>          
            /* #excel .el-table__header-wrapper .el-table__header thead tr th{
                background:#5f87a0;
                color:#FFF;
                height:40px;
                text-align：right;
            } */
            h3{
                color:#2FA4E9;
            }
            table{
                cellspacing:0px; cellpadding:0px;
            }
            table tr .firstrow{
                text-align:left;
            }
            
            table tr td{
                border:0.5px solid #ddd;
                height:30px;
            }
            
            table tr th{
                border:0.5px solid #ddd;
                text-align:right;
                height:40px; 
                background:#5f87a0;
                color:#FFF;
                height:40px;            
            }
            
            
            
            #excel .position-container .table-responsive h3{
                color:##2FA4E7;
            }
            #excel .el-table__header-wrapper .el-table__header thead tr th.gutter{
                border:0;
                background:#fff;
            }
            </style>    
            </head>
            <body>
            ${oHtml}
            </body>
            </html>
            `;

            var excelBlob = new Blob([excelHtml], {type: 'application/vnd.ms-excel'})
            // 创建一个a标签
            var oA = document.createElement('a');
            // 利用URL.createObjectURL()方法为a元素生成blob URL
            oA.href = URL.createObjectURL(excelBlob);
            // 给文件命名
            oA.download = `InvestDetail${(new Date()).toLocaleString()}.xls`;
            // 模拟点击
            oA.click();
            // 移除
            oA.remove();
        },
        reportDropList(){
            var obj={dictArray:"FUND"};
            axioss.reqdroplist(obj).then(res=>{
                this.fundList=res.data.data[0].baseInfoList;
            })
        },
        reqCaptalInvestdetail(obj){
            axioss.reqCaptalInvestdetail(obj).then(res=>{
                console.log(res)
                this.InvestdetailCaptal=res.data.data;
            })
        },
        searchSubmit(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.reqCaptalInvestdetail(this.searchForm);
                }
            })
        },
        transformNum(r,c,cellvalue){
           return method.toThousands(cellvalue);
        }
  },
  components: {
      Header
  }
};
</script>

<style scoped>

    .table{
        display: inline-block;
        width:auto;
    }
    .table-container{
        overflow-x:scroll;
        white-space: nowrap;
    }
    .idgaccelchinareport{
        padding:10px;
        box-sizing: border-box;
    }
</style>
