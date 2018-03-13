<template>
<div>
    <Header/>
    <div class="search">
        <el-form :inline="true" :model="searchForm" ref="searchForm" style="margin-top:10px;">
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
                <el-button type="primary" size="mini" @click="myGetPdf()" title="导出pdf时:目标打印机选择另存为pdf,并点击保存">打印或导出PDF</el-button>
            
            </el-form-item>
        </el-form>
    </div>
    
    <!-- 需要导出的表格内容区域 -->
    <div id="excel">
        <!-- public -->
        <table class="mytable">
            <tr>
                <th class="redline textRight" colspan="9">{{tableInfoObj.fundFullName}}</th>                        
            </tr>
            <tr>
                <th class="space20 textRight" colspan="9">SCHEDULE OF INVESTMENTS</th>                        
            </tr>            
            <tr>
                <th style="padding-top:20px;" class="textLeft borderbtm" colspan="9">{{tableInfoObj.quarter}}</th>                        
            </tr>
            <tr>
                <th>Purchase</th>
                <th></th>
                <th rowspan="2" style="border-bottom:4px solid #666;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                
                <th>Shares</th>
                <th>Fully Diluted</th>
                <th rowspan="2" style="border-bottom:4px solid #666;"></th>
                <th></th>
                <!-- <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th> -->
                <th rowspan="2" style="border-bottom:4px solid #666;"></th>
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
                <th colspan="9" v-if="searchData[0]">&nbsp;</th>                        
            </tr>
            <tr>
                <th colspan="9" v-if="searchData[0]" style="text-align:left;">PUBLIC COMPANIES:</th>                        
            </tr>
            <tr>
                <th colspan="9" v-if="searchData[0]">&nbsp;</th>                        
            </tr>
            <template v-for="(items,index) in searchData">
                <tr v-if="isDifCompany(searchData,index)">
                    <th colspan="10" style="text-align:left;">{{items.Company}}</th>                        
                </tr>                
                <tr>
                    <td>{{transTime(items.purchaseDate)}}</td>
                    <td>{{items.security}}</td>
                    <td>&nbsp;</td>
                    <td style="text-align:right" :class="{doubleline:items.purchaseDate==''}">{{mytoThousands(items.sharesOwned)}}</td>
                    <td>{{items.FullyDiluted}}</td>
                    <td></td>
                    <td style="text-align:right" class="textRight positionRelative symbol" :class="{doubleline:items.purchaseDate==''}">{{mytoThousands(items.Cost)}}</td>            
                    <td></td>                    
                    <td style="text-align:right" class="textRight positionRelative symbol" :class="{doubleline:items.purchaseDate==''}">{{mytoThousands(items.FairValue)}}</td>
                </tr>
            </template>
            <tr>
            <td colspan="9" style="text-align:center; color: rgb(185, 58, 58);border-top:1px solid #000; font-weight:600;">IDG Capital</td>
            </tr>
            <tr>
                <td colspan="9" style="text-align:center; color:rgb(180, 184, 187);">Confidential & Proprietary</td>
            </tr>
        </table>
        <!-- private + total-->
        <!-- <table class="mytable">
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
        </table> -->
        <!-- securityTotal -->
        <!-- <table class="mytable">
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
        </table> -->
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
        },
        myGetPdf(){
            window.print();            
        },
    }
    
  }
</script>
<style scoped lang="scss">
    .mytable{
        margin:auto;                
        width:80%;
        /* border:1px solid #ebeef5; */
        tr:hover td, tr:hover th{
            background-color:#f5f7fa;
        }
        td,th{
            box-sizing: border-box;                      
            padding:5px;
            color:#666;           
             
        }
        td{
            font-size:14px;
            line-height:14px;
        }
        th{            
            height:20px;
            line-height:20px;
            font-weight:600; 
            text-align:center;
        }
        tr:nth-child(5) th{
            border-bottom:4px solid #666
        }
        td:nth-of-type(5){
            text-align:center;
        }
        .redline{
            color: rgb(185, 58, 58); 
            font-size:18px; 
            text-align:right;            
            border-bottom:2px solid rgb(185, 58, 58);
        }
        .space20{
            padding-bottom:20px;
        }
        .textRight{
            text-align:right;
        }
        .textLeft{
            text-align:left;
        }
        .borderbtm{
            border-bottom:1px solid #666;
        }
        .doubleline{
            border-top:1px solid #666;
            border-bottom:1px solid #666;

        }
        .symbol:before{
            content:"$";
            display:inline-block;
            position:absolute;
            left:0px;
        }
        .positionRelative{
            position: relative;
        }
    }
    /* 打印样式 */
    @media print {
        .mysearch,#app>div>div:nth-of-type(1){
            display:none!important;
        }
        .mytable{
            page-break-after: always;
            /* margin-top:30px; */
            width:700px!important;
            margin-left:10px;
        }
        form{
            display:none!important;
        }
        .mytable td, .mytable th{
            font-family: 'Times New Roman', Times, serif;
        }     
        #pdfDom{
            
            font-family: 'Times New Roman'!important;
        }
        .pageSpace{
            border-top:0px!important;
        }
        @page { 
            size: A4 portrait;             
        }
        @page { 
            margin-right:20px;             
        }
    
    }
    
</style>