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
            <el-date-picker v-model="searchForm.closedate" type="date" placeholder=" ">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="mini" @click="searchSubmit('searchForm')">查询</el-button>
            <!-- 导出excel -->
            <el-button type="primary" size="mini" id="but" @click="exportTab">导出Excel</el-button>
          </el-form-item>
        </el-form>
    </div>
    
    <!-- 需要导出的表格内容区域 -->
    <div class="table-container2" style="width:80%;" id="excel">
      <table class="table font14 table-condensed">
        <thead>
          <tr class="textcenter"><td scope="row" colspan="8">{{title.fundFullName}}</td></tr>
          <tr class="textcenter"><td scope="row" colspan="8">History of Realized Gains and Losses by Portfolio Company</td></tr>
          <tr class="textcenter"><td scope="row" colspan="8">{{title.quarter}}</td></tr>
        </thead>
      </table>
      <table class="table font12 table-condensed">
        <thead>
          <tr>
            <td scope="row"></td><td></td>
            <td>Date</td><td>Date</td>
            <td>Sales</td><td>Cost</td>
            <td>Realized</td>   <td></td>
          </tr>
        </thead>
        <tbody>
          <tr><td scope="row">Company</td><td scope="row">Shares</td>
          <td>Purchased</td><td>Sold</td><td>Proceeds</td>
          <td>Basis</td><td>Gain/(Loss)</td>  <td></td>
          </tr>
          <tr class="empty">
            <td scope="row"></td><td></td><td></td><td></td><td></td>
            <td></td><td></td>   <td></td>
          </tr>
          <template v-for="item in captableContent">
            <tr><td scope="row">{{item.Company}}</td><td>{{transformNum(item.Shares)}}</td>
                <td>{{item.PurchasedDate}}</td><td>{{item.SoldDate}}</td><td>{{transformNum(item.ProceedsSales)}}</td><td>{{transformNum(item.BasisCost)}}</td>
                <td>{{item.RealizedGainLoss | transformNum}}</td><td>{{item.PurchasedDate=='multiple:'?'return of capital':''}}</td>
            </tr>
            <tr class="empty" v-if="item.PurchasedDate=='multiple:'">
              <td scope="row"></td><td></td><td></td><td></td><td></td>
              <td></td><td></td><td></td>
            </tr>
          </template>
          <tr>
            <td scope="row"></td><td></td>
            <td></td><td></td><td>Sales</td>
            <td>Cost</td><td>Realized</td>  <td></td>
          </tr>
          <tr>
            <td scope="row"></td><td></td>
            <td></td><td>Summary by Year:</td><td>Price</td>
            <td>Basis</td><td>Gain/(Loss)</td>   <td></td>
          </tr>
          <tr v-for="item in prevSum">
            <td scope="row"></td><td></td>
            <td></td><td>{{item.SummaryByYear}}</td><td>{{transformNum(item.PriceSales)}}</td>
            <td>{{transformNum(item.BasisCost)}}</td><td>{{transformNum(item.RealizedGainLoss)}}</td>  <td></td>
          </tr>
          <tr v-for="item in lastSum">
            <td scope="row"></td><td></td>
            <td></td><td></td><td>{{transformNum(item.ProceedsSales)}}</td>
            <td>{{transformNum(item.BasisCost)}}</td><td>{{transformNum(item.RealizedGainLoss)}}</td>   <td></td>
          </tr>
        </tbody>
      </table>
    </div>

</div>
</template>
<script>
import axioss from '@/api/axios';
import Header from "@/components/common/Header";
import * as method from '@/api/method'
import mix from '@/api/mixin'
export default {
  name: "captablereport",
  mixins:[mix],
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
        exportTab(){
            //var oHtml = document.getElementsByClassName('tableA')[0].outerHTML;
            var oHtml = document.getElementById('excel').outerHTML;
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
            table tr .firstrow{
                text-align:left;
            }
            
            table tr td{
                border:0.5px solid #ddd;
                height:30px;
                paddiing:3em;
            }
            
            table tr th{
                border:0.5px solid #ddd;
                text-align:center;
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
            // Create一个a标签
            var oA = document.createElement('a');
            // 利用URL.createObjectURL()方法为a元素生成blob URL
            oA.href = URL.createObjectURL(excelBlob);
            // 给文件命名
            oA.download = `RealizedGainsLosses${(new Date()).toLocaleString()}.xls`;
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
        reqCaptableGainloss(obj){
            axioss.reqCaptableGainloss(obj).then(res=>{
              console.log(res.data.data)
              if(res.data.data){
                this.title=res.data.data.slice(0,1)[0];
                this.captableContent=res.data.data.slice(1);
              }
            })
        },
        reqCaptableGainlossSum(obj){
            axioss.reqCaptableGainlossSum(obj).then(res=>{
                if(res.data.data){
                  var len=res.data.data.length;
                  this.prevSum=res.data.data.slice(0,len-1);
                  this.lastSum=res.data.data.slice(-1);
                }
            })
        },
        searchSubmit(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.reqCaptableGainloss(this.searchForm)
                    this.reqCaptableGainlossSum(this.searchForm)
                }
            })
        },
        transformNum(num){
            var newNum=method.toThousands(num);
            return newNum;
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
    .table td{
        border:1px solid #ddd;

    }
</style>
