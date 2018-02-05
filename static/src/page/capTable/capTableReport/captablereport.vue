<template>
<div class="captableReport">
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
    <div class="table-container2" style="width:80%;">
        <table class="table font14 table-condensed">
            <thead>
            <tr class="textcenter"><td scope="row">{{title.fundFullName}}</td></tr>
            <tr class="textcenter"><td scope="row">History of Realized Gains and Losses by Portfolio Company</td></tr>
            <tr class="textcenter"><td scope="row">{{title.quarter}}</td></tr>
            </thead>
        </table>
        <table class="table font12 table-condensed">
            <thead>
                <tr>
                    <td scope="row"></td><td></td>
                    <td>Date</td><td>Date</td>
                    <td>Sales</td><td>Cost</td>
                    <td>Realized</td>
                </tr>
            </thead>
            <tbody>
                <tr><td scope="row">Company</td><td scope="row">Shares</td>
                <td>Purchased</td><td>Sold</td><td>Proceeds</td>
                <td>Basis</td><td>Gain/(Loss)</td>
                </tr>
                <tr class="empty">
                    <td scope="row"></td><td></td><td></td><td></td><td></td>
                    <td></td><td></td><td></td>
                </tr>
                <template v-for="item in captableContent">
                    <tr><td scope="row">{{item.Company}}</td><td>{{item.Shares}}</td>
                        <td>{{item.PurchasedDate}}</td><td>{{item.SoldDate}}</td><td>{{item.ProceedsSales}}</td><td>{{item.BasisCost}}</td>
                        <td>{{item.RealizedGain}}</td><td class="bordertop" v-if="item.PurchasedDate=='multiple:'">return of capital</td>
                    </tr>
                    <tr class="empty" v-if="item.PurchasedDate=='multiple:'">
                        <td scope="row"></td><td></td><td></td><td></td><td></td>
                        <td></td><td></td><td></td>
                    </tr>
                </template>
                <tr>
                    <td scope="row"></td><td></td>
                    <td></td><td></td><td>Sales</td>
                    <td>Cost</td><td>Realized</td>
                </tr>
                <tr>
                    <td scope="row"></td><td></td>
                    <td></td><td>Summary by Year:</td><td>Price</td>
                    <td>Basis</td><td>Gain/(Loss)</td>
                </tr>
                <tr v-for="item in prevSum">
                    <td scope="row"></td><td></td>
                    <td></td><td>{{item.SummaryByYear}}</td><td>{{item.PriceSales}}</td>
                    <td>{{item.BasisCost}}</td><td>{{item.RealizedGainLoss}}</td>
                </tr>
                <tr v-for="item in lastSum">
                    <td scope="row"></td><td></td>
                    <td></td><td></td><td>{{item.ProceedsSales}}</td>
                    <td>{{item.BasisCost}}</td><td>{{item.RealizedGainLoss}}</td>
                </tr>
            </tbody>
        </table>
    </div>

</div>
</template>
<script>
import axioss from '@/api/axios';
import Header from "@/components/common/Header";
export default {
  name: "captablereport",
  data: function() {
    return {
        searchForm:{
            fundid:'F0022',
            closedate:'',
        },
        fundList:[],
        content:[],
        captableContent:[],
        captableSum:[],
        lastSum:[],
        prevSum:[],
        title:[]
    };
  },
  mounted:function(){
      this.reportDropList();
  },
  methods:{
        reportDropList(){
            var obj={dictArray:"FUND"};
            axioss.reqdroplist(obj).then(res=>{
                this.fundList=res.data.data[0].baseInfoList;
            })
        },
        reqCaptableGainloss(obj){
            axioss.reqCaptableGainloss(obj).then(res=>{
                this.title=res.data.data.slice(0,1)[0];
                this.captableContent=res.data.data.slice(1)
            })
        },
        reqCaptableGainlossSum(obj){
            axioss.reqCaptableGainlossSum(obj).then(res=>{
                var len=res.data.data.length;
                this.prevSum=res.data.data.slice(0,len-1);
                this.lastSum=res.data.data.slice(-1);
            })
        },
        searchSubmit(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.reqCaptableGainloss(this.searchForm)
                    this.reqCaptableGainlossSum(this.searchForm)
                }
            })    
        }
  },
  components: {
      Header
  }
};
</script>

<style scoped>
    .empty td{
        height:25px;
        border:none;
    }
   .captableReport .table-container2 .table td,.table th{
        height:25px;
    }
    .borderbottom{ 
        border-bottom:1px solid black;
    }
    .bordertop{
        border-top:none;
    }
    .textcenter{
        text-align:center;
        color:#666;
    }
    .search{
        margin-top:5px;
    }
</style>