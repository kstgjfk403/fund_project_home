<template>
<div class="ValuationReport">
    <div class="header-top font14">IDG China Venture Capital Fund IV L.P. ("IDG CVC Fund IV")</div>
    <div class="logo"> </div>
    <div class="info">
        <ul>
            <li><span>Company:</span><span>Ppzuche (Sharing Corporation)</span></li>
            <li><span>Website:</span><span>https://www.ppzuche.com</span></li>
        </ul>

    </div>
    <div class="header-top font14" style="margin-bottom:10px;">IDG China Venture Capital Fund IV L.P. ("IDG CVC Fund IV")</div>
    <div class="app-table-container">
        <div class="left floatleft width25">
            <table class="table font12 table-condensed">
                <caption style="opacity:0;" class="font14 fontweight">No. of shares</caption>
                <thead>
                    <tr style="opacity:0;">
                        <th style="border:none;"># Series B-3</th>
                        <th style="border:none;">Cost</th>
                    </tr>
                </thead>
                <tbody>
                    <tr><td scope="row" style="opacity:0;border:none;">held</td><td style="opacity:0;border:none;">US$0.54</td></tr>
                    <tr style="opacity:0;"><td scope="row" style="border:none"></td><td style="border:none"></td></tr>
                    <tr><td scope="row" style="border:none;">IDG</td><td style="border:none;"></td></tr>
                    <tr><td scope="row" style="border:none;">IDG China Venture Capital Fund IV L.P.</td><td style="border:none;"></td></tr>
                    <tr><td scope="row" style="border:none;">IDG China IV Investors L.P.</td><td style="border:none;"></td></tr>
                    <tr style="opacity:0;"><td scope="row" style="border:none;">46,157,733</td><td style="border:none;">$25,000,000</td></tr>
                </tbody>
            </table>
        </div>
        <div class="floatleft width25">
            <table class="table font12 table-condensed">
                <caption class="font14 fontweight">No. of shares</caption>
                <thead>
                    <tr>
                        <th># Series B-3</th>
                        <th>Cost</th>
                    </tr>
                </thead>
                <tbody>
                    <tr><td scope="row">held</td><td>US$0.54</td></tr>
                    <tr><td scope="row" style="border:none"></td><td style="border:none"></td></tr>
                    <tr><td scope="row">40,918,831</td><td>$22,162,500</td></tr>
                    <tr><td scope="row">1</td><td>Mark</td></tr>
                    <tr><td scope="row">5,238,902</td><td>$2,837,500</td></tr>
                    <tr><td scope="row">46,157,733</td><td>$25,000,000</td></tr>
                </tbody>
            </table>
        </div>
        <div class="right floatleft" style="width:50%;">
            <table class="table font12 table-condensed">
                <caption class="font14 fontweight">No. of shares</caption>
                <thead>
                    <tr>
                        <th>OPM</th>
                        <th>10% FIN48</th>
                        <th>OPM</th>
                        <th>Carrying</th>
                        <th>Variance</th>    
                    </tr>
                </thead>
                <tbody>
                    <tr><td scope="row"></td><td></td>
                        <td>after FIN48</td><td>Value</td>
                        <td></td>
                    </tr>
                    <tr><td scope="row" style="border:none"></td><td style="border:none"></td>
                        <td style="border:none"></td><td style="border:none"></td>
                        <td style="border:none"></td>
                    </tr>
                    <tr><td scope="row">40,918,831</td><td>$22,162,500</td>
                        <td>US$0.54</td><td>US$0.54</td><td>US$0.54</td>
                    </tr>
                    <tr><td scope="row">1</td><td>Mark</td>
                        <td>US$0.54</td><td>US$0.54</td><td>US$0.54</td>
                    </tr>
                    <tr><td scope="row">5,238,902</td><td>$2,837,500</td>
                        <td>US$0.54</td><td>US$0.54</td><td>US$0.54</td>
                    </tr>
                    <tr>
                        <td scope="row">46,157,733</td><td>$25,000,000</td>
                        <td>US$0.54</td><td>US$0.54</td><td>US$0.54</td>
                    </tr>
                   <tr style="border-bottom:1px solid #ddd;">
                        <td scope="row"></td><td></td>
                        <td></td><td></td><td>US$0.54</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="valuation" style="padding-left:5px;overflow:hidden;">
        <div class="width25 floatleft">
            Valuation
        </div>
        <div class="floatleft" style="border:1px solid #ddd;min-height:100px;width:75%;">
            kdsflsdkjflkdsjflkj
        </div>
    </div>
</div>
</template>
<script>
import axioss from "../../api/axios";
import Header from "../common/Header";
export default {
    name: "valuationreport",
    data: function() {
        return {
            
        };
    },
    mounted:function() {
        this.valuationReport();
    },
    components: {
        Header
    },
    methods:{
        valuationReport(){
            var obj={};
            var valuationid=this.$route.query.valuationid;
            console.log(valuationid)
            axioss.valuationReport(valuationid).then(res=>{
                console.log(res);
            })
        }
    }
};
</script>

<style scoped>
.header-top{
    color:black;
    font-weight: bold;
    margin-top:15px;
    padding-left:5px;
    box-sizing: border-box;
}
.info ul li span{
    display: inline-block;
    width:20%;
}
.app-table-container .floatleft caption{
    text-align: center;
    background:yellow;
    caption-side: top;
}
.app-table-container .floatleft{
    padding:5px;
    box-sizing:border-box;
}
.app-table-container .floatleft ul li{
    margin-top:6px; 
}
.info{
    padding-left:5px;
    box-sizing: border-box;
}
.table td{
    height:30px;
}
</style>