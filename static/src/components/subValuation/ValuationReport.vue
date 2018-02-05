<template>
<div class="ValuationReport2">
    <h3 class="title">IDGCC</h3>
    <div class="table-container1">
        <div class="header-top font14">Jing Ying Education (China Elite Education Media Group Ltd.)</div>
        <table class="table font12 table-condensed">
            <caption class="font14 fontweight" style="caption-side:top;">Financials</caption>
            <thead>
                <tr><th>Financial year ended July 31</th><th>2016A<br/>(US$)</th>
                <th>2017A<br/>(US$)</th></tr>
            </thead>
            <tbody>
                <tr><th scope="row">Revenue</th>
                <td>{{portfoliofinancialinfo.revenue}}</td><td>Value</td></tr>
                
                <tr class="empty"><th scope="row"></th><td></td><td></td></tr>

                <tr><th scope="row">Cross Profit<br/>% Margin</th>
                <td>{{portfoliofinancialinfo.grossprofit}}<br/>% 13</td>
                <td>Value<br/>% 13</td></tr>

                <tr class="empty"><th scope="row"></th><td></td><td></td></tr>

                <tr><th scope="row">Operating Income<br/>% Margin</th>
                <td>{{portfoliofinancialinfo.revenue}}<br/>% 13</td>
                <td>Value<br/>% 13</td></tr>

                <tr class="empty"><th scope="row"></th><td></td><td></td></tr>

                <tr><th scope="row">Net Income<br/>% Margin</th>
                <td>{{portfoliofinancialinfo.revenue}}<br/>% 13</td>
                <td>Value<br/>% 13</td></tr>

                <tr class="empty"><th scope="row"></th><td></td><td></td></tr>

                <tr><th scope="row">Cash Marketable securities</th>
                <td>{{portfoliofinancialinfo.revenue}}</td>
                <td>Value</td></tr>

                <tr class="empty"><th scope="row"></th><td></td><td></td></tr>

                <tr><th scope="row">Debt</th>
                <td>{{portfoliofinancialinfo.revenue}}</td>
                <td>Value</td></tr>

                <tr class="empty"><th scope="row"></th><td></td><td></td></tr>

                <tr style="border-bottom:1px solid #ddd;"><th scope="row">IDG ownership%</th>
                <td></td>
                <td>Value</td></tr>
            </tbody>
        </table>
    </div>
    <div class="table-container2" style="width:100%;">
        <table class="table font12 table-condensed floatleft" style="width:50%;">
            <caption class="font14 fontweight" style="caption-side:top;">PE Multiple (Run Rate: Current Fiscal Year)</caption>
            <!-- <thead>
                <tr><th></th><th></th></tr>
            </thead> -->
            <tbody>
                <tr><td scope="row">Enterprise Value</td><td> 345,234,577</td></tr>
                <tr><td scope="row">+ Cash</td><td>US$0.54</td></tr>
                <tr><td scope="row">- Debt</td><td>US$0.54</td></tr>
                <tr><td scope="row">+ Option Proceeds</td><td style="border-bottom:1px solid black">$2,837,500</td></tr>
                <tr><td scope="row">Equity Value</td><td>$25,000,000</td></tr>
                <tr><td scope="row">- Preferred Stock Preference</td><td style="border-bottom:1px solid black">$25,000,000</td></tr>
                <tr><td scope="row">Common Equity Value</td><td>$25,000,000</td></tr>
            </tbody>
        </table>
        <table class="table table-hover table-bordered table-condensed floatleft font14" style="table-layout:fixed;width:20%">
                <caption class="font14 fontweight" style="caption-side:top;">comparable</caption>
                <thead>
                    <tr>
                        <td scope="col">Comparable</td>
                        <td scope="col">PE multiple</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>2</td>
                    </tr>   
                </tbody>
            </table>
    </div>
    <div class="table-container3">
        <table class="table font12 table-condensed">
            <caption class="font14 fontweight" style="caption-side:top;">Fair Value</caption>
            <thead>
                <tr>
                    <th>Carrying</th>
                    <th>High</th>
                    <th>Low</th>
                    <th>Weighted average</th>
                </tr>
            </thead>
            <tbody>
                <tr><td scope="row">Valuation based on market comparables</td><td></td>
                    <td>after FIN48</td><td>Value</td>   
                </tr>
                <tr><td scope="row" style="border:none"></td><td style="border:none"></td>
                    <td style="border:none"></td><td style="border:none"></td>   
                </tr>
                <tr><td scope="row">Valuation based on market comparables</td><td></td>
                    <td></td><td>US$0.54</td></tr>
                <tr><td scope="row">Add: Control premium</td>
                    <td></td><td></td><td>US$0.54</td></tr>

                <tr class="emptyfair"><td scope="row"></td><td></td><td></td><td style="border-top:1px solid black;">108,886,986</td></tr>
                <tr><td scope="row">Less: DLOM</td><td></td><td>25%</td><td> (27,221,746)</td></tr>
                
                <tr class="emptyfair"><td scope="row"></td><td></td><td></td><td style="border-top:1px solid black;"></td></tr>
                
                <tr><td scope="row">Valuation based on market comparables</td><td></td>
                    <td></td><td>US$0.54</td></tr>
                <tr><td scope="row">Carrying cost</td><td></td>
                    <td></td><td>234455</td></tr>
                <tr>
                    <td scope="row">FIN48 provision</td><td></td>
                    <td></td><td style="border-top:1px solid black;">US$0.54</td>
                </tr>
                 <tr>
                    <td scope="row">Net valuation</td><td></td>
                    <td></td><td style="border-top:1px solid black;">75,792,875</td>
                </tr>
                <tr>
                    <td scope="row">Current valuation</td><td></td>
                    <td></td><td>$25,000,000</td>
                </tr>

                <tr class="emptyfair"><td scope="row"></td><td></td><td></td><td style="border-bottom:1px solid black;"></td></tr>
                
                <tr>
                    <td scope="row">Variance</td><td></td>
                    <td></td><td>$25,000,000</td>
                </tr>

                <tr class="emptyfair"><td scope="row"></td><td></td><td></td><td style="border-top:1px solid black;">100%</td></tr>
            </tbody>
        </table>
    </div>
</div>
</template>
<script>
import axioss from "../../api/axios";
export default {
  name: "valuationreport",
  data: function() {
    return {
        portfoliofinancialinfo:{

        }
        
    };
  },
  mounted:function(){
      this.valuationReport();
  },
  methods:{
    valuationReport(){
        var obj={};
        var valuationid=this.$route.query.valuationid;
        console.log(valuationid)
        axioss.valuationReport(valuationid).then(res=>{
            console.log(res);
            this.portfoliofinancialinfo=res.data.data.portfoliofinancialinfo;
        })
    }
  },
  components: {
     
  }
};
</script>

<style scoped>
.ValuationReport2{
    padding:0 6px;
}
h3.title{
    font-size: 16px;
    padding-left:5px;
    box-sizing: border-box;
}
.header-top{
    color:black;
    font-weight: bold;
    padding-left:5px;
    box-sizing: border-box;
    background:yellow;
}
.table-container1,.table-container2,.table-container3{
    margin-top:15px;
    width:50%; 
    border-left:1px solid #ebeef5;
    border-right:1px solid #ebeef5;
}
.table-container3 caption,.table-container2 caption{
    background:yellow;
    color:black; 
}
.table .empty td,.table .empty th{
    height:15px;
    border:none;
}
.table td,.table th{
    border:none;
}
.border{
    border-top:1px solid black;
}
</style>