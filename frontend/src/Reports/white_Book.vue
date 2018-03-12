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
    <p style="color:#000;border-bottom:1px solid #000;margin:50px 0 20px 0;width:70.4%;margin:auto;">http://www.17huo.com</p>
    <div id="pdfDom">
        
        <div class="tablecontent" style="padding-top:100px;">
            
            <table class="section1 mytable tableheader" style="width:80%;margin:auto">            
                <tr>
                    <th >17HUO INC.</th>
                </tr>
                <tr>
                    <th>(17HUO)</th>
                </tr>
                <tr>
                    <th>INVESTMENT REPORT</th>
                </tr>
                <tr>
                    <th>31 JULY 2017</th>
                </tr>
            </table>
            <div class="section2 clearFix">
                <div class="tableleft">
                    <p class="tabletitle">General Information</p>
                    <table class="mytable">
                        <tr>
                            <td>Industry</td>
                            <td>Consumer & O2O</td>                    
                        </tr>
                        <tr>
                            <td>Product</td>
                            <td style="height:36px;">B2B platform that connects clothing stores and wholesalers</td>                            
                        </tr>
                        <tr>
                            <td>Year founded</td>
                            <td>2015</td>
                        </tr>
                        <tr>
                            <td>Place of incorporation</td>
                            <td>Cayman Islands</td>
                        </tr>
                        <tr>
                            <td>CEO</td>
                            <td>Zhen Wang</td>
                        </tr>
                        <tr>
                            <td>No of employees</td>
                            <td>15</td>
                        </tr>
                        <tr>
                            <td>IDG ownership</td>
                            <td>15.00 %</td>
                        </tr>
                        <tr>
                            <td>IDG board seat</td>
                            <td>Yukun Liu</td>
                        </tr>
                        <tr>
                            <td>Other investors</td>
                            <td>--</td>
                        </tr>
                        <tr>
                            <td>IDG Role</td>
                            <td>Lead Investor</td>
                        </tr>
                    </table>            
                </div>
                <div class="tableRight">
                    <p class="tabletitle">Investment Information</p>
                    <table class="mytable">
                        <tr>
                            <td>Equity</td>
                            <td>US$ 1,380,600</td>                    
                        </tr>
                        <tr>
                            <td>Loan</td>
                            <td class="limitHeight">--</td>
                            
                        </tr>
                        <tr>
                            <td>Total investment</td>
                            <td>US$ 1,380,600</td>
                        </tr>
                        <tr>
                            <td>Gain/losses</td>
                            <td class="limitHeight">--</td>
                        </tr>
                        <tr>
                            <td>Current valuation</td>
                            <td class="limitHeight">US$ 1,380,600</td>
                        </tr>
                        <tr>
                            <td>Anticipated exit</td>
                            <td>Too early to tell</td>
                        </tr>
                        <tr>
                            <td>Warrants/options</td>
                            <td>Nil</td>
                        </tr>
                        <tr>
                            <td>Expected IRR</td>
                            <td>--</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="history">
                <p class="tabletitle">Investment History</p>
                <table class="mytable">
                    <tr>
                        <td></td>
                        <td></td>
                        <td>No. of</td>
                        <td>Amount</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Date</td>
                        <td>Fund-Description</td>
                        <td>shares</td>
                        <td>US$</td>
                        <td>Comment</td>
                    </tr>
                    <tr>
                        <td>03/2016</td>
                        <td>F5-Preferred A 4,772,728</td>
                        <td>4,772,728</td>
                        <td>1,380,600</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>Total</td>
                        <td>4,772,728</td>
                        <td>1,380,600</td>
                        <td></td>
                    </tr>
                </table>
            </div>
            <div class="change">
                <p class="tabletitle">Rating Changes</p>
                <table class="mytable">                    
                    <tr>
                        <td>Date</td>
                        <td>Rating</td>
                        <td>Date</td>
                        <td>Rating</td>
                        <td>Date</td>
                        <td>Rating</td>
                    </tr>
                    <tr>
                        <td>07/2016</td>
                        <td>B</td>
                        <td>01/2017</td>
                        <td>B</td>
                        <td>07/2017</td>
                        <td>B</td>
                    </tr>
                </table>
            </div>
            <div class="summary clearFix">
                <p class="tabletitle">Financial Summary(Note)</p>
                <div class="tableleft">
                    <table class="mytable">
                        <tr>
                            <td>
                                <span>Income statement</span>
                                <span>US$'000</span>
                            </td>
                            <td>
                                <span>2016 YTD</span>
                                <span>31/12</span>
                            </td>
                            <td>
                                 <span>2016 YTD</span>
                                <span>31/12</span>
                            </td>
                        </tr>                        
                        <tr>
                            <td>Revenue</td>
                            <td>772</td>
                            <td>791</td>
                        </tr>
                        <tr>
                            <td>Profit/(Loss)</td>
                            <td>(919)</td>
                            <td>(189)</td>
                        </tr>
                    </table>
                </div>
                <div class="tableRight">
                    <table class="mytable">
                       <tr>
                            <td>
                                <span>Balance sheet</span>
                                <span>US$'000</span>
                            </td>
                            <td>
                                <span>As at&nbsp;&nbsp;&nbsp;</span>
                                <span>30/06/17</span>
                            </td>                            
                        </tr>
                        <tr>
                            <td>Cash</td>
                            <td>772</td>
                        </tr>
                        <tr>
                            <td>Other assets</td>
                            <td class="borderbtm">772</td>
                        </tr>
                        <tr>
                            <td>Total assets</td>
                            <td>772</td>
                        </tr>
                        <tr>
                            <td>Liabilities</td>
                            <td class="borderbtm">772</td>
                        </tr>
                        <tr>
                            <td>Shareholders' equity</td>
                            <td>772</td>
                        </tr>
                    </table>
                </div>
                
            </div>
            <p style="color:#000">(Note: Based on management accounts)</p>
            <p style="color:#000;border-top:1px solid #000;margin:300px 0 100px 0">http://www.17huo.com</p>
            <div class="background">
                <p class="tabletitle">Investment History</p>
                <p>17huo operates an online B2B platform that connects small-to-middle-sized clothing stores and wholesalers to improve their operational efficiency. By grouping individual orders together, 17huo has been able to gain stronger bargaining power which allows store owners to enjoy up to 7% discounts compared with normal wholesale prices.</p>
            </div>
            <div class="development">
                <p class="tabletitle">Operations review and business development</p>
                <p>By the end of 2016, 17Huo has recorded over 82K wholesalers and store owners onboard its platform with a one-month repurchasing rate of 73% and a six-month retention rate of 50%, and users make 8.6 orders per month on average. With more wholesalers onboard, the company has expanded its SKU pool from 336K to 1M including women’s and men’s wear as well as shoes and bags over the past 12 months. In addition, the company updated its platform to allow wholesalers to add short videos in the description of their products. 17Huo recorded revenue of US$791K in the first half of 2017, similar to the 2016 annual revenue, and is currently operating at a burn rate of US$30K per month.</p>            
            </div>
             <div class="valuation">
                <p class="tabletitle">Valuation</p>
                <p>The investment is carried at cost.</p>
            </div>
        </div>
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
        htmlTitle:'white_Book',                
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
       margin:auto;
       font-family: 'STSong';
       p{
           color:#000;
       }
    }
    .mytable{
        width:100%;
        border:1px solid #000;        
        td,th{
            box-sizing: border-box;
            color:#000;             
        }
        td{
            font-size:16px;
            line-height:16px;
            padding:5px;

        }        
        th{            
            height:20px;
            line-height:20px;
            font-weight:600; 
            text-align:center;
            font-size:18px;
            line-height:18px;
            padding:5px;            
        }
        .borderbtm{
            border-bottom:1px solid #000;
        }
        
        .textRight{
            text-align:right;
        }
        .textLeft{
            text-align:left;
        }
        .textCenter{
            text-align:center;
        }
        .limitHeight{
            height:43px;
            border-bottom:1px solid #000;
        }
       
    }
    .tableheader{
        border:1px solid black;
    }
    .tablecontent{                
        width:88%;
        margin:auto;
    }
    .tableleft, .tableRight{
       
        display:inline-block;
        td:nth-of-type(1){
            border-right:1px solid black;
            font-weight:600;
            width:40%;
        }
    }
    
    .tableleft{
        width:55%;
        float:left;
    }
    .tableRight{
        width:40%;
        float:right;
    }
    .tabletitle{
        color:#222;
        font-size:18px;
        font-weight:600;
        text-decoration: underline;
        line-height:50px;
        padding-top:20px;
    }
    .section2 .tableRight{
        td{
            line-height:19.5px;
        }
        td:nth-of-type(2){
            text-align:right;
        }
        tr:last-child td{
            padding-bottom:5px;
        }
    }
    .history .mytable{
        td{
            border:1px solid #000;
        }
        tr:nth-of-type(1) {
            td{
                border-bottom:0;
            }            
        }
        tr:nth-of-type(1),tr:nth-of-type(2){
            td:nth-of-type(3),td:nth-of-type(4){
                text-align:center;
            }
        }
        tr:nth-of-type(2) td{
            border-top:0;
        }
        td:nth-of-type(3),td:nth-of-type(4){
            text-align:right;
        }       
    }
    .change .mytable{
        td{
            border:1px solid #000;
            span{
                display:inline-block;
                height:100%;
            }
        }
    }
    .summary .mytable{
        td{
            border-left:1px solid #000;
            font-weight:normal;            
            span{
                display:inline-block;
                width:100%;
                height:20px;
                line-height:20px;
            }
        } 
    }
    .summary .tableleft .mytable{
        tr:nth-of-type(1){
            border-bottom:1px solid #000;
        }
        tr{            
            td:nth-of-type(2),td:nth-of-type(3){
                text-align:right;
                padding-right:20px;
            }            
        }
        tr:nth-of-type(2),tr:nth-of-type(3){
            td{
                line-height:58px;
            }
        }
              
    }
    .summary .tableRight .mytable{        
        tr{
            
            td:nth-of-type(2){
                text-align:right;
                padding-right:80px;
            }            
        }
        tr:nth-of-type(1){
            border-bottom:1px solid #000;
            
            td{
                padding-bottom:5px;
                padding-top:5px;
                &:nth-of-type(1){
                    width:50%;
                }
            }
            
        }
        tr:last-child{
            td{
                padding-bottom:10px;
            }
        }
    }
    
</style>