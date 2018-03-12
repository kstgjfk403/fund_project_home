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
            </el-form-item>
        </el-form>
    </div>
    <table class="mytable">
        <tr>
            <th>&nbsp;</th>
            <th colspan="15" class="font18 myThRed">VALUATION COMMITTEE REPORT</th>
        </tr>
        <tr>
            <th>&nbsp;</th>
            <th colspan="15" class="font16 myThRed">IDG CHINA CAPITAL FUND III L.P.</th>
        </tr>
        <tr>
            <th class="borderbtm">&nbsp;</th>
            <th  colspan="15" class="font16 myThRed borderbtm">Summary of Private Valuation Changes in 2017 ($ In Thousands)</th>
        </tr>
        <tr class="borderbtm4">
            <th>&nbsp;</th>
            <th class="textRight">Company</th>
            <th class="textRight">Value @ 12/13/16</th>
            <th class="textRight">cost @ 12/31/16</th>            
            <th class="textRight">Unrealized G/L</th>
            <th class="textRight">%Val to Cost</th>
            <th class="textRight">Implied Market Cap @ 12/31/16</th>
            <th class="textRight">Value 12/31/17</th>
            <th class="textRight">Cost 12/31/17</th>
            <th class="textRight">Unrealized G/L</th>            
            <th class="textRight">%Val to Cost</th>
            <th class="textRight">Implied Market Cap @ 12/31/17</th>
            <th class="textRight">PIC @ 12/31/17</th>
            <th class="textRight">% Own</th>
            <th class="textRight">Change in Unrealized G/L from  12/31/16 to 12/31/17</th>
            <th class="textRight">Notes</th>            
        </tr>

        <!-- 表格内容 -->
        <tr v-for="n in 4" :key="n">
            <td>{{n}}</td>
            <td>Chengjia (Shanghai) Apartment Management Co., Ltd</td>
            <td class="textRight">$13,729</td>
            <td class="textRight">$13,729</td>
            <td class="textRight">$13,729</td>
            <td class="textRight">$13,729</td>
            <td class="textRight">$13,729</td>
            <td class="textRight">$13,729</td>
            <td class="textRight">$13,729</td>
            <td class="textRight">$13,729</td>
            <td class="textRight">$13,729</td>
            <td class="textRight">$13,729</td>
            <td class="textRight">$13,729</td>
            <td class="textRight">34.65%</td>
            <td class="textRight">$13,729</td>
            <td>Valuation based on $29.3M Joint Venture Equity Interest financing at an enterprise value of $318.4M. Capitalized cost written off.</td>
        </tr>
        <tr>
            <td colspan="16" style="text-align:center; color: rgb(185, 58, 58);border-top:1px solid rgb(185, 58, 58); padding-top:20px; font-weight:600;">IDG Capital</td>
        </tr>
         <tr>
            <td colspan="16" style="text-align:center;">Confidential & Proprietary</td>
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
                
    }
    
  }
</script>
<style scoped lang="scss">
    .search{
        margin-top:5px;        
    }
    .mytable{
        margin:auto;                
        width:98%;
        /* border:1px solid #ebeef5; */        
        td,th{
            box-sizing: border-box;                      
            padding:5px;
            color:#666; 
            font-size:14px;
            line-height:14px;          
            vertical-align: middle; 
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
        .font16{
            font-size:16px;
        }
        .font18{
            font-size:18px;            
        }
        .myThRed{
           color: rgb(185, 58, 58);
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
        .borderbtm4{
            border-bottom:4px solid #666;
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