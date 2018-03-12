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
                <!-- 导出excel -->
                <el-button type="primary" size="mini" id="but" @click="exportTab">导出Excel</el-button>
            </el-form-item>
        </el-form>
    </div>
    
    <!-- 需要导出的表格内容区域 -->
    <div id="excel">
        <!-- public -->
        <table class="mytable">
            <tr>
                <th style="text-align:right;color:#b93a3a;" colspan="10">{{tableInfoObj.fundFullName}}</th>                        
            </tr>
            <tr>
                <th style="text-align:right;" colspan="10">SCHEDULE OF INVESTMENTS</th>                        
            </tr>
            <tr>
                <th style="text-align:right;" colspan="10">&nbsp;</th>                        
            </tr>
            <tr>
                <th style="text-align:left;" colspan="10">{{tableInfoObj.quarter}}</th>                        
            </tr>
            <tr>
                <th>Purchase</th>
                <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th></th>
                <th>Shares</th>
                <th>Fully Diluted</th>
                <th rowspan="2"></th>
                <th></th>
                <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th rowspan="2"></th>
                <th>Estimated</th>                       
            </tr>
            <tr>            
                <th>Date</th>
                <th style="text-align:left;">Security</th>
                <th>Owned</th>
                <th>Ownership</th>
                <th>Cost</th>
                <th>Fair Value</th>                      
            </tr>
            <tr>
                <th colspan="10" v-if="searchData[0]">&nbsp;</th>                        
            </tr>
            <tr>
                <th colspan="10" v-if="searchData[0]" style="text-align:left;">PUBLIC COMPANIES:</th>                        
            </tr>
            <tr>
                <th colspan="10" v-if="searchData[0]">&nbsp;</th>                        
            </tr>
            <template v-for="(items,index) in searchData">
                <tr v-if="isDifCompany(searchData,index)">
                    <th colspan="10" style="text-align:left;">{{items.Company}}</th>                        
                </tr>                
                <tr>
                    <td>{{transTime(items.purchaseDate)}}</td>
                    <td></td>
                    <td>{{items.security}}</td>
                    <td style="text-align:right">{{mytoThousands(items.sharesOwned)}}</td>
                    <td>{{items.FullyDiluted}}</td>
                    <td>$</td>
                    <td style="text-align:right">{{mytoThousands(items.Cost)}}</td>            
                    <td></td>
                    <td>{{(items.Company=="")?"$":""}}</td>
                    <td style="text-align:right">{{mytoThousands(items.FairValue)}}</td>
                </tr>
            </template>
        </table>
        <!-- private + total-->
        <table class="mytable">
            <tr>
                <th style="text-align:right;color:#b93a3a;" colspan="10">{{tableInfoObj.fundFullName}}</th>                        
            </tr>
            <tr>
                <th style="text-align:right;" colspan="10">SCHEDULE OF INVESTMENTS</th>                        
            </tr>
            <tr>
                <th style="text-align:right;" colspan="10">&nbsp;</th>                        
            </tr>
            <tr>
                <th style="text-align:left;" colspan="10">{{tableInfoObj.quarter}}</th>                        
            </tr>
            
            <tr>
                <th>Purchase</th>
                <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th></th>
                <th>Shares</th>
                <th>Fully Diluted</th>
                <th rowspan="2"></th>
                <th></th>
                <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th rowspan="2"></th>
                <th>Estimated</th>                       
            </tr>
            <tr>            
                <th>Date</th>
                <th style="text-align:left;">Security</th>
                <th>Owned</th>
                <th>Ownership</th>
                <th>Cost</th>
                <th>Fair Value</th>                      
            </tr>
            <tr>
                <th colspan="10" v-if="searchData[0]">&nbsp;</th>                        
            </tr>
            <tr>
                <th colspan="10" v-if="searchData[0]" style="text-align:left;">PRIVATE COMPANIES:</th>                        
            </tr>
            <tr>
                <th colspan="10" v-if="searchData[0]">&nbsp;</th>                        
            </tr>
            <template v-for="(items,index1) in searchData2">
                <tr v-if="isDifCompany(searchData2,index1)">
                    <th colspan="10" style="text-align:left;">{{items.Company}}</th>                        
                </tr>                
                <tr>
                    <td>{{transTime(items.purchaseDate)}}</td>
                    <td></td>
                    <td>{{items.security}}</td>
                    <td style="text-align:right;">{{mytoThousands(items.sharesOwned)}}</td>
                    <td>{{items.FullyDiluted}}</td>
                    <td>$</td>
                    <td style="text-align:right;">{{mytoThousands(items.Cost)}}</td>            
                    <td></td>
                    <td>{{(items.Company=="")?"$":""}}</td>
                    <td style="text-align:right;">{{mytoThousands(items.FairValue)}}</td>
                </tr>
            </template>
            <tr>
                <th colspan="10" >&nbsp;</th>                        
            </tr>
            <tr >
                <td colspan="4" style="text-align:right;">TOTAL INVESTMENTS</td>            
                <td>{{searchData3.FullyDiluted}}</td>
                <td>$</td>
                <td style="text-align:right;">{{mytoThousands(searchData3.Cost)}}</td>            
                <td></td>
                <td>$</td>
                <td style="text-align:right;">{{mytoThousands(searchData3.FairValue)}}</td>
            </tr>
            <tr>
                <th colspan="10" >&nbsp;</th>                        
            </tr>
        </table>
        <!-- securityTotal -->
        <table class="mytable">
            <tr>
                <th style="text-align:right;color:#b93a3a;" colspan="10">{{tableInfoObj.fundFullName}}</th>                        
            </tr>
            <tr>
                <th style="text-align:right;" colspan="10">SCHEDULE OF INVESTMENTS</th>                        
            </tr>
            <tr>
                <th style="text-align:right;" colspan="10">&nbsp;</th>                        
            </tr>
            <tr>
                <th style="text-align:left;" colspan="10">{{tableInfoObj.quarter}}</th>                        
            </tr>
            
            <tr>
                <th>Purchase</th>
                <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th></th>
                <th>Shares</th>
                <th>Fully Diluted</th>
                <th rowspan="2"></th>
                <th></th>
                <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th rowspan="2"></th>
                <th>Estimated</th>                       
            </tr>
            <tr>            
                <th>Date</th>
                <th style="text-align:left;">Security</th>
                <th>Owned</th>
                <th>Ownership</th>
                <th>Cost</th>
                <th>Fair Value</th>                      
            </tr>
            <tr>
                <th colspan="10" >&nbsp;</th>                        
            </tr>
            <tr>
                <th colspan="10" style="text-align:left;">SUMMARY OF SECURITIES</th>                        
            </tr>                
            <tr v-for="(items,index4) in searchData4">
                <td>{{transTime(items.purchaseDate)}}</td>
                <td></td>
                <td>{{items.security}}</td>
                <td style="text-align:right;">{{mytoThousands(items.sharesOwned)}}</td>
                <td>{{items.FullyDiluted}}</td>
                <td>$</td>
                <td style="text-align:right;">{{mytoThousands(items.Cost)}}</td>            
                <td></td>
                <td>$</td>
                <td style="text-align:right;">{{mytoThousands(items.FairValue)}}</td>
            </tr>
            <tr>
                <th colspan="10" >&nbsp;</th>                        
            </tr>
            <tr >
                <td colspan="4" style="text-align:right;">TOTAL INVESTMENTS</td>            
                <td>{{searchData3.FullyDiluted}}</td>
                <td>$</td>
                <td style="text-align:right;">{{mytoThousands(searchData3.Cost)}}</td>            
                <td></td>
                <td>$</td>
                <td style="text-align:right;">{{mytoThousands(searchData3.FairValue)}}</td>
            </tr>
            <tr>
                <th colspan="10" >&nbsp;</th>                        
            </tr>
        </table>
    </div>
    
  
  </div>
</template>
<script>
import {toThousands} from "@/api/method"
import axioss from '@/api/axios';
import Header from "@/components/common/Header";
  export default {
    data() {
      return {
        fundList:[],
        searchForm:{
            fundid:'F0022',
            closedate:'',
        },
        searchData:[],
        searchData2:[],
        searchData3:{},
        searchData4:[],
        tableInfoObj:{},        
      }
    },
    components: {Header},
    mounted(){
        this.reportDropList();
    },
    methods:{
       exportTab(){
            //console.log(element_css)
            var oHtml = document.querySelector("#excel").outerHTML;               
            //console.log(oHtml);            
            var excelHtml = `
            <html>
              <head>
                <meta charset='utf-8' />
                <style>
                  table{
                      width:1000px;
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
        /* 千分位 */
        mytoThousands(num,mysymbol){
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
        /* 数据搜索 start */
        reqSearchData(obj){             
            axioss.reqMain_Schedule(obj).then(res=>{
                if(res.data && res.data.code=="SUCCESS"){
                    let data = res.data.data;
                    this.tableInfoObj=data[0];
                    this.searchData=data[1].baseName;
                    this.searchData2=data[2].baseName;
                    this.searchData3=data[3].baseName;
                    this.searchData4=data[4].baseName; 
                }
            })
        },
        reportDropList(){/* 获取下拉列表 */
            var obj={dictArray:"FUND"};
            axioss.reqdroplist(obj).then(res=>{
                this.fundList=res.data.data[0].baseInfoList;
            })
        },
        searchSubmit(formName){/* 搜索事件函数 */
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.reqSearchData(this.searchForm);                   
                }
                
            });    
        },
        /* 数据搜索 end */
        transTime(value){
            if(value){
                return new Date(value).toLocaleString().split(" ")[0];
            }else{
                return "";
            }
            
        },
        isDifCompany(arr,index){            
            if(index!=(arr.length-1)){
                if(index==0){
                    return true;
                }else{
                    if(arr[index].Company!=""&&arr[index].Company!=arr[index-1].Company){
                        return true;
                    }else{
                        return false;
                    }
                }               
            }else{
                if(arr[index].Company==""){
                    return false;
                }else{
                    return true;
                }
                
            }
        }
    }
    
  }
</script>
<style scoped lang="scss">
    .search{
        margin-top:5px;        
    }
    .mytable{
        margin-left:10px;
        margin-bottom:50px;                
        width:90%;
        border:1px solid #ebeef5;
        td,th{
            box-sizing: border-box;
            height:20px;
            line-height:20px;
            padding:5px;            
            border:1px solid #ebeef5; 
        }
        th{
            text-align:center;
        }
        td:nth-of-type(5){
            text-align:center;
        }
    }
    
</style>