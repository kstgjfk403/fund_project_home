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
      width="180">
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
      :label="'Cost@'+tableInfoObj.quarter"
      width="120">
    </el-table-column>
    <el-table-column
      prop="MarketValue"
      :label="'Market Value'+tableInfoObj.quarter"
      width="120">
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
      width="120">
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
      width="120">
    </el-table-column>
  </el-table>
  </div>
</template>
<script>
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
            "TotalGain": " ",
            "Dividends": " ",
            "Ownership": " ",
            "postMoney": " ",
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
                    //console.log("合并的数据",mydata);
                    //console.log("原始的数据",data);
                    this.tableData = mydata;
                    console.log("绑定的数据",res)
                }
            })
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
                    this.$nextTick(()=>{
                        let domArr = document.getElementsByClassName("cell");
                        let len = domArr.length;
                        for(var i=0;i<len;i++){
                            //console.log(domArr[i].innerHTML)
                            if(domArr[i].innerHTML=="Private companies"){
                                console.log("3333333333333333333")
                            }
                        }
                    })
                }
                
            });    
        }
    }
  }
</script>
<style>    
    .mytable.el-table td, .mytable.el-table th.is-leaf{
        border-bottom:1px solid #ebeef5;
        border-right:1px solid #ebeef5;
    }
    .mytable.el-table th.is-leaf{
        text-align: center;
    }
    .mytable.el-table {
         border:1px solid #ebeef5;
         height:650px;       
    }
    .mytable.el-table td, .mytable.el-table th{
        box-sizing: border-box;
        padding:0 5px 0 5px;
    }
    .mytable.el-table .cell{
        line-height:25px;         
    }
    
</style>
<style scoped>
    .search{
        margin-top:5px;
    }
</style>