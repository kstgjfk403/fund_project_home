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
    <div id="pdfDom">
    <table class="mytable" v-for="m in 3">
        <tr>
            <th colspan="2" :class="{pageSpace:m>1,firstPage:true}" style="color: rgb(185, 58, 58);font-size:18px;">IDG CHINA CAPITAL FUND III L.P.</th>
        </tr>
        <tr>
            <th colspan="2" style="color:#000;">PORTFOLIO SUMMARY</th>
        </tr>
        <tr>
            <th colspan="2" style="color:rgb(180, 184, 187);padding-bottom:30px;">December 31, 2017</th>
        </tr>
        <template v-for="n in 4">
        <tr  class="borderTop">
            <td style="padding-top:20px;"><img src="../../static/images/cjia.jpg"></td>
            <td rowspan="8" style="vertical-align: middle;">Cheng Jia operates serviced apartments and business centers, targeting the post-80s generation.</td>
        </tr>
        <tr>
            <td >Chengjia (Shanghai) Apartment Management Co., Ltd</td>
        </tr>
        <tr>
            <td>http://www.cjia.com/</td>
        </tr>
        <tr>
            <td>Sector | Sub-Sector: TMT | Consumer & O2O</td>
        </tr>
        <tr>
            <td>IDG Initial Role: Active | Lead Investor</td>
        </tr>
        <tr>
            <td>Initial Stage: Early</td>
        </tr>
        <tr>
            <td>Board Seat: Yes</td>
        </tr>
        <tr>
            <td style="padding-bottom:20px;">Status: Private</td>
        </tr>
        </template>
        <tr>
            <td colspan="2" style="text-align:center; color: rgb(185, 58, 58);border-top:1px solid #000; padding-top:20px; font-weight:800;">IDG Capital</td>
        </tr>
         <tr>
            <td colspan="2" style="text-align:center; color:rgb(180, 184, 187);">Confidential & Proprietary</td>
        </tr>

        
    </table>
    </div>
    <button type="button" class="btn btn-primary" @click="getPdf()">导出PDF</button>
  
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
        htmlTitle:"main_business"                
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
    #pdfDom{
       width:80%;
       margin:auto
    }
    .mytable{                        
        width:92%;
        margin:auto;
        
        
        td,th{
            box-sizing: border-box;           
            padding:5px;
            color:#000;
            font-family: STSong;            
            /*border:1px solid #ebeef5;*/ 
        }
        td{
            font-size:16px;
            line-height:16px;
        }
        th{
            text-align:right;
            height:20px;
            line-height:20px;
        }
        td:nth-of-type(5){
            text-align:center;
        }
        th:nth-of-type(1),td:nth-of-type(1){
            width:50%;
        }
        
        .borderTop{
            border-top:1px solid #000;
        }
        .firstPage{
            padding-top:30px;
        }
        .pageSpace{
            padding-top:130px;
        }
    }
    
</style>