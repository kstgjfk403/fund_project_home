<template>
<div>
    <Header/>
    <div class="search">
        <el-form :inline="true" :model="searchForm" ref="searchForm">
            <el-form-item label="Fund">
                <el-select v-model="searchForm.fundid" placeholder=" ">
                <el-option :label="item.baseName" :value="item.baseId" :key="item.baseId" v-for="item in fundList"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Close Date">
                <el-date-picker v-model="searchForm.closedate" type="date" placeholder=" ">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size="mini" @click="searchSubmit('searchForm')">查询</el-button>
                <el-button type="primary" size="mini" id="but" @click="exportTab">导出Excel</el-button>
            </el-form-item>
        </el-form>
    </div>
    
    <!-- 需要导出的表格内容区域 -->
   
    <el-table
        border
        :data="tableData"
        style="width: 100%;"
        height="250"
        :key="searchForm.fundid+searchForm.closedate"
        class="mytable">
        <el-table-column
        fixed
        prop="projName"
        label="Project name"
        width="300">
        </el-table-column>
        <el-table-column
        prop="BusinessDescription"
        label="Business description"
        width="360">
        </el-table-column>
        <el-table-column
        prop="PrivatePublic"
        label="Private/Public"
        width="90">
        </el-table-column>
        <el-table-column
        prop="Lead"
        label="Lead"
        width="220">
        </el-table-column>
        <el-table-column
        prop="BoardSeat"
        label="Board Seat"
        width="100">
        </el-table-column>
        <el-table-column
        prop="Sector"
        label="Sector"
        width="400">
        </el-table-column>
        <el-table-column
        prop="FirstFunding"
        label="FirstFunding"
        width="150">
        </el-table-column>
        <el-table-column
        prop="Ownership"
        label="Ownership"
        width="120">
        </el-table-column>
        <el-table-column
        prop="cost"
        :label="'Cost @ '+tableInfoObj.quarter"
        align="right"
        header-align="center"
        width="120">
        </el-table-column>
        <el-table-column
        prop="MarketValue"
        :label="'Market Value @ '+tableInfoObj.quarter"
        align="right"
        header-align="center"
        width="120">
        </el-table-column>
        <el-table-column
        prop="CapitalInvestedToDate"
        label="Capital Invested to date"
        width="150">
        </el-table-column>
        <el-table-column
        prop="Reserves"
        label="Reserves"
        width="120">
        </el-table-column>
        <el-table-column
        prop="TotalFunding"
        label="Total Funding"
        width="130">
        </el-table-column>
        <el-table-column
        prop="SalesProceeds"
        label="Sales proceeds"
        width="150">
        </el-table-column>
        <el-table-column
        prop="Dividends"
        label="Dividends"
        width="120">
        </el-table-column>
        <el-table-column
        prop="TotalGain"
        label="TotalGain/(Loss)"
        width="150">
        </el-table-column><el-table-column
        prop="Financing"
        label="Financing"
        width="150">
        </el-table-column>
        <el-table-column
        prop="preMoney"
        label="Pre-money at Initial Investment"
        width="120">
        </el-table-column>
        <el-table-column
        prop="postMoney"
        label="Post-money Valuation of Last Round"
        width="120">
        </el-table-column>
        <el-table-column
        prop="otherInvestor"
        label="Other co-investor(s)"
        width="150">
        </el-table-column>
    </el-table>
    </div>
  
</template>
<script>

import {toThousands} from "@/api/method"
import axioss from '@/api/axios';
import Header from "@/components/common/Header";
  export default {
    data() {
      return {
        tableData: [],
        fundList:[],
        searchForm:{
            fundid:'F0022',
            closedate:'',
        },
        searchData:{},
        tableInfoObj:{},
        str:"&nbsp;",
        dataEmp:[{
            "Reserves": "",
            "TotalGain": "",
            "Dividends": "",
            "Ownership": "",
            "postMoney": "",
            "cost": "",
            "projName": "--",
            "Sector": "",
            "otherInvestor": "",
            "preMoney": "",
            "CapitalInvestedToDate": "",
            "PrivatePublic": "",
            "BusinessDescription": "",
            "MarketValue": "",
            "Financing": "",
            "Lead": "",
            "TotalFunding": "",
            "FirstFunding": "",
            "BoardSeat": "",
            "SalesProceeds": ""
        }],
      }
    },
    components: {Header},
    mounted(){
        this.reportDropList();
    },
    updated(){
        this.$nextTick(()=>{/* 将 Private companies和Public companies加粗*/
            let domArr = document.getElementsByClassName("cell");
            let len = domArr.length;
            for(var i=0;i<len;i++){
                if(domArr[i].innerHTML=="Private companies" || domArr[i].innerHTML=="Public companies"){
                    domArr[i].style.fontWeight="800";
                    domArr[i].style.color="#909399"
                }
            }
        })
    },
    methods:{
        exportTab(){
            //console.log(element_css)
            var oHtml1 = document.querySelector(".el-table__header-wrapper").outerHTML;
            var oHtml2 = document.querySelector(".el-table__body-wrapper").outerHTML;
            //console.log(oHtml1,oHtml2)
            var oHtml = oHtml1+oHtml2;    
            //console.log(oHtml);            
            var excelHtml = `
            <html>
            <head>
            <meta charset='utf-8' />
            <style>          
            h3{
                color:#2FA4E9;
            }
            table{
                font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
            }
            .el-table__body .el-table__row .is-right .cell{
               
                text-align: right;
            } 
            
            table tr td{
                border:0.5px solid #ddd;
                height:auto;
                padding:3em;
            }           
            
            table tr th{
                border:0.5px solid #ddd;
                text-align:center;
                height:40px; 
                background:#5f87a0;
                color:#FFF; 
            }
            /*去除多余th的颜色和边框*/
            table tr .gutter{
                background:#fff;
                border:0;
            }
           
            
            </style>    
            </head>
            <body>
            ${oHtml}
            </body>
            </html>
            `;
            //console.log(excelHtml)
            var excelBlob = new Blob([excelHtml], {type: 'application/vnd.ms-excel'})
            // Create一个a标签
            var oA = document.createElement('a');
            // 利用URL.createObjectURL()方法为a元素生成blob URL
            oA.href = URL.createObjectURL(excelBlob);
            // 给文件命名
            oA.download = `appendix_2 ${(new Date()).toLocaleString()}.xls`;
            // 模拟点击
            oA.click();
            // 移除
            oA.remove();
        },
        reqfundlist(){
            /*var enterobj={
            dictArray:'DDL_IncorpLocation,YTD,CURRENCY'
            }
            axioss.reqdroplist(enterobj).then(res=>{
            this.dictarraylist=method.translateFormat(res.data.data);
            console.log(this.dictarraylist)
            }).catch(res=>{
            console.log(res);
            })*/
            axioss.reqfundlistwithoutpage().then(res=>{
            this.fundarrayList=res.data;
            console.log(fundarrayList)
            }).catch(res=>{
            })
        },
        reqSearchData(obj){
            axioss.capitalfundAppendix_2(obj).then(res=>{
                //console.log(res)
                if(res.data && res.data.code=="SUCCESS"){
                    //console.log("searchDate",res.data);
                    let data = res.data.data;
                    this.tableInfoObj = data[0];
                    let mydata = [];
                    let dataTemp1 = JSON.parse(JSON.stringify(this.dataEmp));
                    dataTemp1[0].projName="Private companies";
                    let dataTemp2 = JSON.parse(JSON.stringify(this.dataEmp));
                    dataTemp2[0].projName="Public companies";

                    /* 数据处理 */
                    data.forEach((item,index) => {
                        if(index==1){
                            mydata= mydata.concat(dataTemp1,item.baseName,this.dataEmp);
                        }else if(index==2){
                             mydata= mydata.concat(dataTemp2,item.baseName,this.dataEmp);
                        }else if(index==3){
                            mydata= mydata.concat(item.baseName);
                        }

                    });
                    /* 千分位处理需要处理的字段 */
                    let handleArr = ["cost","MarketValue","CapitalInvestedToDate","TotalFunding","SalesProceeds","TotalGain","preMoney","postMoney"]
                    mydata.forEach((item,index)=>{
                        handleArr.forEach((itemchild)=>{
                             mydata[index][itemchild]=this.mytoThousands(item[itemchild],"$");
                        });
                    })
                    this.tableData = mydata;
                    console.log(this.tableData,"tabledata")
                }
            })
        },
        /* 千分处理数据过滤 */
        mytoThousands(num,mysymbol){
            if(num=="N/A"){
                return "N/A"
            }
            if(num=="-"){
                return "-"
            }else{
                if(num===""){
                    return ""
                }
                num = parseInt(num);
                if(mysymbol){
                    return "$"+toThousands(num);
                }else{
                     return toThousands(num);
                }
            }
        },
        reportDropList(){
            var obj={dictArray:"FUND"};
            axioss.reqdroplist(obj).then(res=>{
                this.fundList=res.data.data[0].baseInfoList;
            })
        },
        searchSubmit(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.reqSearchData(this.searchForm);

                }

            });
        }
    }
  }

</script>
<style>
.mytable.el-table td:nth-of-type(9),
.mytable.el-table td:nth-of-type(10),
.mytable.el-table td:nth-of-type(11),
.mytable.el-table td:nth-of-type(12),
.mytable.el-table td:nth-of-type(13),
.mytable.el-table td:nth-of-type(14),
.mytable.el-table td:nth-of-type(15),
.mytable.el-table td:nth-of-type(16),
.mytable.el-table td:nth-of-type(18),
.mytable.el-table td:nth-of-type(19){
    text-align:right;
}
</style>
<style scoped>
    .search{
        margin-top:5px;
    }
</style>
