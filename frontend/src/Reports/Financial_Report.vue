<template>
<div class="idgaccelchinareport">
    <Header/>
    <div class="search" style="margin-top:10px;">
        <el-form :inline="true" :model="searchForm" ref="searchForm" style="margin-left:10px;">
          <el-form-item label="Fund">
            <el-select v-model="searchForm.fundid" placeholder="" filterable @change="reqPortfolioStatus_List">
              <el-option :label="item.baseName" :value="item.baseId" :key="item.baseId" v-for="item in fundList"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Portfolio Status" style="margin-left:20px;">
            <el-select v-model="searchForm.portfolioid" placeholder="" filterable>
            <el-option :label="item.fullName" :value="item.portfolioID" :key="item.baseId" v-for="item in DDL_PortfolioStatus_List"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="mini" @click="searchSubmit('searchForm')">查询</el-button>
            <!-- 导出excel -->
            <el-button type="primary" size="mini" id="but" @click="exportTab">导出Excel</el-button>
          </el-form-item>
        </el-form>
    </div>
    
    <!-- 需要导出表格的内容区域 -->
    <table class="mytable">
      <tr>
        <th colspan="11" style="text-align:center;font-size:18px;">PORTFOLIO FINANCIAL REPORT</th>        
      </tr>
      <tr>
        <th colspan="11">&nbsp;</th>        
      </tr>
      <tr>
        <th colspan="11">(Currency: US $)</th>        
      </tr>
      <tr>
        <th colspan="11">&nbsp;</th>        
      </tr>
      <tr>
        <th style="text-align:left;">NO.</th>
        <th style="text-align:left;">PROJECT NAME</th>
        <th>END DATE</th>
        <th>REVENUE</th>
        <th>NET PROFITS</th>
        <th>CASH ON HAND</th>
        <th>TOTAL ASSETS</th>
        <th>TOTAL LIABILITIES</th>
        <th>OPERATING PROFITS</th>
        <th>GROSS PROFITS</th>
        <th>BANK LOANS/DEBT</th>
      </tr>
      <tr v-for="(item,index) in searchData" :key="index">
        <td style="text-align:left;" :rowspan="index==0?String(searchData.length):'1'" v-if="index==0">{{index+1}}</td>
        <td style="text-align:left;" :rowspan="index==0?String(searchData.length):'1'" v-if="index==0">{{item.abbname}}</td>
        <td>{{ myformatTime(item.enddate)}} </td>
        <td>{{ mytoThousands(item.revenue)}} </td>
        <td>{{ mytoThousands(item.netprofits)}} </td>
        <td>{{ mytoThousands(item.cashassets)}} </td>
        <td>{{ mytoThousands(item.totalassets)}} </td>
        <td>{{ mytoThousands(item.totalliabilities)}} </td>
        <td>{{ mytoThousands(item.operatingprofits)}} </td>
        <td>{{ mytoThousands(item.grossprofit)}} </td>
        <td>{{ mytoThousands(item.cashlevel)}} </td>        
      </tr>      
    </table>    
</div>
</template>
<script>
import {toThousands,formatTime} from "@/api/method"
import Header from "@/components/common/Header";
import axioss from '@/api/axios';
import * as method from '@/api/method';
export default {
  name: "fina",
  data: function() {
    return {
        searchForm:{
            fundid:'F0022',
            portfolioid:'',            
        },
        fundList:[],
        DDL_PortfolioStatus_List:[],
        searchData:[],
    };
  },
  mounted:function(){
      this.requserlist();
      this.reqPortfolioStatus_List();
  },
  methods:{
        /* 下拉列表请求 */
        requserlist(){
          var enterobj={ dictArray:'FUND'}
          axioss.reqdroplist(enterobj).then(res=>{
            //console.log(res)            
            this.fundList=res.data.data[0].baseInfoList;
            //this.DDL_PortfolioStatus_List=res.data.data[1].baseInfoList;
            console.log(this.fundList,this.DDL_PortfolioStatus_List)            
          }).catch(res=>{
          })
       },
       reqPortfolioStatus_List(){
         let id = this.searchForm.fundid;
         axioss.reqfundproList(id).then(res => {
           if(res.data && res.data.code=="SUCCESS"){
             this.DDL_PortfolioStatus_List = res.data.data;
           }
           console.log("PortfolioStatus_List",res);
                     
        })
       },
       /* 搜索 */
       searchSubmit(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.reqSearchData(this.searchForm);                   
                }                
            });    
        },
        /* 请求表格数据 */
        reqSearchData(obj){             
            axioss.reqFinancial_Report(obj).then(res=>{
                if(res.data && res.data.code=="SUCCESS"){
                    let data = res.data.data;
                    this.searchData = data;
                    console.log("data",data); 
                }
            })
        },
        /* 千分处理数据过滤 */
        mytoThousands(num,mysymbol){
            num = Math.round(num);
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
                     return toThousands(num)[0]=="-"?'('+toThousands(num).slice(1)+')':toThousands(num);
                }
            }
        },
        myformatTime(value){
          return formatTime(new Date(Number(value)),"/")
        },
        exportTab(){
            //console.log(element_css)
            var oHtml = document.querySelector(".mytable").outerHTML;               
            //console.log(oHtml);            
            var excelHtml = `
            <html>
              <head>
                <meta charset='utf-8' />
                <style>
                  table{
                      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
                  }           
                  table tr td{
                      border:0.5px solid #ddd;
                      height:auto;
                      padding:10px;
                      color:#666;
                  }
                  table tr th{
                      border:0.5px solid #ddd;
                      text-align:center;
                      height:40px;
                      padding:10px;
                      color:#666; 
                      /* background:#5f87a0;
                      color:#FFF; */ 
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
  },
  components: {
      Header
  }
};
</script>

<style scoped lang="scss" >
  .mytable{
    margin-left:10px;
    margin-top:10px;
    margin-bottom:50px;                
    width:99%;
    border:1px solid #ebeef5;
    td,th{
      box-sizing: border-box;
      height:20px;
      line-height:20px;
      padding:5px;            
      border:1px solid #666;
      color:#666;
      vertical-align: middle; 
    }
    th{
      text-align:center;
    }
    td{
      text-align:right;
    }
    
  }
    
</style>
