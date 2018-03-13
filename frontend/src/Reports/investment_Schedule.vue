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
                <!-- <el-button type="primary" size="mini" @click="myGetPdf()" title="导出pdf时:目标打印机选择另存为pdf,并点击保存">打印或导出PDF</el-button> -->
            </el-form-item>
        </el-form>
    </div>
    <table class="mytable">
        <tr>
            <th colspan="9" class="redline textRight">IDG CHINA CAPITAL FUND III L.P.</th>
        </tr>
         <tr>
            <th colspan="9" class="space20 textRight">SCHEDULE OF INVESTMENTS</th>
        </tr>
        <tr>
            <th colspan="9" class="textLeft borderbtm" style="padding-top:20px;">AS OF DECEMBER 31, 2017</th>
        </tr>
        <tr>
            <th>Purchase</th>            
            <th></th>
            <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
            <th>Shares</th>
            <th>Fully Diluted</th>
            <th rowspan="2"></th>
            <th></th>
            <!-- <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th> -->
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
            <th colspan="9" class="textLeft borderbtm" style="padding-top:30px">PUBLIC COMPANIES:</th>
        </tr>
        <template v-for="n in 5">
        <tr>
            <td colspan="9">Olympique Lyonnais Groupe</td>
        </tr>
        <tr>
          <td>12/22/2016</td>
          <td>bond</td>
          <td></td>
          <td class="textRight">55,174</td>
          <td></td>
          <td class="textRight "></td>
          <td class="textRight positionRelative symbol">134,454,455</td>
          <td class="textRight"></td>  
          <td class="textRight positionRelative symbol" >233.432,78</td>
        </tr>
        <tr>
          <td>12/22/2016</td>
          <td>common</td>
          <td></td>
          <td class="textRight">55,174</td>
          <td></td>
          <td class="textRight"></td>
          <td class="textRight positionRelative symbol">134,454,455</td>
          <td class="textRight"></td>  
          <td class="textRight positionRelative symbol" >233.432,78</td>
        </tr>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td class="textRight doubleline">55,174</td>
          <td>12.3%</td>
          <td class="textRight" ></td>
          <td class="textRight doubleline positionRelative symbol">134,454,455</td>
          <td class="textRight"></td>  
          <td class="textRight doubleline positionRelative symbol" >233.432,78</td>
        </tr>
        </template>
        <tr>
            <td colspan="9" style="text-align:center; color: rgb(185, 58, 58);border-top:1px solid #000; padding-top:20px; font-weight:600;">IDG Capital</td>
        </tr>
         <tr>
            <td colspan="9" style="text-align:center; color:rgb(180, 184, 187);">Confidential & Proprietary</td>
        </tr>
    </table>
    
  
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
        myGetPdf(){
            window.print();
        },
                
    }
    
  }
</script>
<style scoped lang="scss">
    .search{
        margin-top:5px;        
    }
    .mytable{
        margin:auto;                
        width:80%;
        /* border:1px solid #ebeef5; */
        
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
    
</style>