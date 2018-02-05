<template>
<div>
    <Header/>
    <div class="search">
        <el-form :inline="true" :model="searchForm" ref="searchForm">
            <el-form-item label="Fund">
                <el-select v-model="searchForm.fundid" placeholder="活动区域">
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
  
  <el-table
    border
    :data="tableData"
    style="width: 100%;"
    height="450"
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
      width="300">
    </el-table-column>
    <el-table-column
      prop="Sector"
      label="Sector"
      width="500">
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
      width="140">
    </el-table-column>
    <el-table-column
      prop="MarketValue"
      :label="'Market Value @ '+tableInfoObj.quarter"
      width="140">
    </el-table-column>
    <el-table-column
      prop="CapitalInvestedToDate"
      label="Capital Invested to date"
      width="300">
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