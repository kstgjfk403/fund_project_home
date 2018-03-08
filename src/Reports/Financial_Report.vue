<template>
<div class="idgaccelchinareport">
    <Header/>
    <div class="search" style="margin-top:10px;">
        <el-form :inline="true" :model="searchForm" ref="searchForm" style="margin-left:10px;">
          <el-form-item label="Fund">
            <el-select v-model="searchForm.fundid" placeholder="" filterable >
            <el-option :label="item.baseName" :value="item.baseId" :key="item.baseId" v-for="item in fundList"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Portfolio Status" style="margin-left:20px;">
            <el-select v-model="searchForm.portfolioid" placeholder="" filterable>
            <el-option :label="item.baseName" :value="item.baseId" :key="item.baseId" v-for="item in DDL_PortfolioStatus_List"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="mini" @click="searchSubmit('searchForm')">查询</el-button>
            <!-- 导出excel -->
            <el-button type="primary" size="mini" id="but" @click="exportTab">导出Excel</el-button>
          </el-form-item>
        </el-form>
    </div>
    
    <!-- 需要导出的表格内容区域 -->
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
      <tr v-for="n in 10">
        <td style="text-align:left;" :rowspan="n==1?'10':'1'" v-if="n==1">111</td>
        <td style="text-align:left;" :rowspan="n==1?'10':'1'" v-if="n==1">222</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>        
      </tr>      
    </table>    
</div>
</template>
<script>
import Header from "@/components/common/Header";
import axioss from '@/api/axios';
import * as method from '@/api/method';
export default {
  name: "fina",
  data: function() {
    return {
        searchForm:{
            fundid:'F0022',
            portfolioid:1,            
        },
        fundList:[],
        DDL_PortfolioStatus_List:[],
    };
  },
  mounted:function(){
      this.requserlist()
  },
  methods:{
        /* 下拉列表请求 */
        requserlist(){
          var enterobj={ dictArray:'FUND,DDL_PortfolioStatus'}
          axioss.reqdroplist(enterobj).then(res=>{
            //console.log(res)            
            this.fundList=res.data.data[0].baseInfoList;
            this.DDL_PortfolioStatus_List=res.data.data[1].baseInfoList;
            console.log(this.fundList,this.DDL_PortfolioStatus_List)            
          }).catch(res=>{
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
                if(res.data){
                    let data = res.data.data;
                    console.log("data",data) 
                }
            })
        },
        transformNum(cellvalue){
           return method.toThousands(cellvalue);
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
