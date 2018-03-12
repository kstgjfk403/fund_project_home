<template>
  <div class="ValuationReport2">
    <h3 class="title">{{fundFamilyName}} EV</h3>
    <div class="table-container1">
      <div class="header-top font14">{{name}} ({{portfolioFullName}})</div>
      <table class="table font12 table-condensed">
        <caption class="font14 fontweight" style="caption-side:top;">Financials</caption>
        <thead>
        <tr>
          <th>Financial year ended July 31</th>
          <th>{{lastfinancialinfo.enddate | formatDate}}<br/>(US$)</th>
          <th>{{portfoliofinancialinfo.enddate | formatDate}}<br/>(US$)</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <th scope="row">Revenue</th>
          <td>{{lastfinancialinfo.revenue | formatNumber }}</td>
          <td>{{portfoliofinancialinfo.revenue | formatNumber }}</td>
        </tr>

        <tr class="empty">
          <th scope="row"></th>
          <td></td>
          <td></td>
        </tr>

        <tr>
          <th scope="row">Gross Profit<br/>% Margin</th>
          <td>{{lastfinancialinfo.grossprofit | formatNumber }}<br/>{{marginLG}}</td>
          <td>{{portfoliofinancialinfo.grossprofit | formatNumber }}<br/>{{marginG}}</td>
        </tr>

        <tr class="empty">
          <th scope="row"></th>
          <td></td>
          <td></td>
        </tr>

        <tr>
          <th scope="row">Operating Income<br/>% Margin</th>
          <td>{{lastfinancialinfo.operatingincome | formatNumber }}<br/>{{marginLO}}</td>
          <td>{{portfoliofinancialinfo.operatingincome | formatNumber }}<br/>{{marginO}}</td>
        </tr>

        <tr class="empty">
          <th scope="row"></th>
          <td></td>
          <td></td>
        </tr>

        <tr>
          <th scope="row">Net Income<br/>% Margin</th>
          <td>{{lastfinancialinfo.netprofits | formatNumber }}<br/>{{marginLN}}</td>
          <td>{{portfoliofinancialinfo.netprofits | formatNumber }}<br/>{{marginN}}</td>
        </tr>

        <tr class="empty">
          <th scope="row"></th>
          <td></td>
          <td></td>
        </tr>

        <tr>
          <th scope="row">Cash Marketable securities</th>
          <td>{{lastfinancialinfo.cashassets | formatNumber }}</td>
          <td>{{portfoliofinancialinfo.cashassets | formatNumber }}</td>
        </tr>

        <tr class="empty">
          <th scope="row"></th>
          <td></td>
          <td></td>
        </tr>

        <tr>
          <th scope="row">Debt</th>
          <td>{{lastfinancialinfo.cashlevel | formatNumber }}</td>
          <td>{{portfoliofinancialinfo.cashlevel | formatNumber }}</td>
        </tr>

        <tr class="empty">
          <th scope="row"></th>
          <td></td>
          <td></td>
        </tr>

        <tr style="border-bottom:1px solid #ddd;">
          <th scope="row">IDG ownership%</th>
          <td></td>
          <td>{{ownerShip}}%</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="table-container2" style="width:100%;">
      <table class="table font12 table-condensed floatleft" style="width:70%;">
        <caption class="font14 fontweight" style="caption-side:top;">{{portfoliovaluation.valuationmethod}} Multiple (Run Rate: Current Fiscal Year)



        </caption>
        <!-- <thead>
            <tr><th></th><th></th></tr>
        </thead> -->
        <tbody>
        <tr>
          <td scope="row">Enterprise Value</td>
          <td align="right">{{ ev | formatNumber }}</td>
        </tr>
        <tr>
          <td scope="row">+ Cash</td>
          <td align="right">{{portfoliofinancialinfo.cashassets | formatNumber }}</td>
        </tr>
        <tr>
          <td scope="row">- Debt</td>
          <td align="right">{{portfoliofinancialinfo.cashlevel | formatNumber }}</td>
        </tr>
        <tr>
          <td scope="row">+ Option Proceeds</td>
          <td align="right" style="border-bottom:1px solid black">{{portfoliovaluation.optionproceed | formatNumber}}</td>
        </tr>
        <tr>
          <td scope="row">Equity Value</td>
          <td align="right">{{ eqv | formatNumber }}</td>
        </tr>
        <tr>
          <td scope="row">- Preferred Stock Preference</td>
          <td align="right" style="border-bottom:1px solid black">{{portfoliovaluation.preferstockprefer | formatNumber}}</td>
        </tr>
        <tr>
          <td scope="row">Common Equity Value</td>
          <td align="right">{{portfoliovaluation.fairvalue | formatNumber }}</td>
        </tr>
        </tbody>
      </table>
      <table class="table table-hover table-bordered table-condensed floatleft font14"
             style="table-layout:fixed;width:30%">
        <caption class="font14 fontweight" style="caption-side:top;">comparable</caption>
        <thead>
        <tr>
          <td scope="col">Comparable</td>
          <td scope="col">PE multiple</td>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in comparables">
          <td>{{item.comparableName}}</td>
          <td>{{item.multiple}}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="table-container3">
      <table class="table font12 table-condensed">
        <caption class="font14 fontweight" style="caption-side:top;">Fair Value</caption>
        <thead>
        <tr>
          <th></th>
          <th>High</th>
          <th>Low</th>
          <th align="right">Weighted average</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td scope="row">{{portfoliovaluation.valuationmethod}} Mutiple</td>
          <td>{{highValuation | formatNumber }}</td>
          <td>{{lowValuation | formatNumber }}</td>
          <td align="right">{{portfoliovaluation.valuation | formatNumber }}</td>
        </tr>
        <tr>
          <td scope="row" style="border:none"></td>
          <td style="border:none"></td>
          <td style="border:none"></td>
          <td style="border:none"></td>
        </tr>
        <tr>
          <td scope="row">Valuation based on market comparables</td>
          <td></td>
          <td></td>
          <td align="right">{{portfoliovaluation.valuation | formatNumber }}</td>
        </tr>
        <tr>
          <td scope="row">Add: Control premium</td>
          <td></td>
          <td></td>
          <td align="right">{{portfoliovaluation.additional | formatNumber }}</td>
        </tr>

        <tr class="emptyfair">
          <td scope="row"></td>
          <td></td>
          <td></td>
          <td style="border-top:1px solid black;" align="right">{{portfoliovaluation.valuation | formatNumber }}</td>
        </tr>
        <tr>
          <td scope="row">Less: DLOM</td>
          <td></td>
          <td>{{portfoliovaluation.discount }}%</td>
          <td align="right">{{discountValue | formatNumber }}</td>
        </tr>

        <tr class="emptyfair">
          <td scope="row"></td>
          <td></td>
          <td></td>
          <td style="border-top:1px solid black;" align="right"></td>
        </tr>

        <tr>
          <td scope="row">Valuation based on market comparables</td>
          <td></td>
          <td></td>
          <td align="right">{{beforeTax | formatNumber }}</td>
        </tr>
        <tr>
          <td scope="row">Carrying cost</td>
          <td></td>
          <td></td>
          <td align="right">{{carryCost | formatNumber }}</td>
        </tr>
        <tr>
          <td scope="row">FIN48 provision</td>
          <td></td>
          <td></td>
          <td style="border-top:1px solid black;" align="right">({{portfoliovaluation.fin48tax | formatNumber }})</td>
        </tr>
        <tr>
          <td scope="row">Net valuation</td>
          <td></td>
          <td></td>
          <td style="border-top:1px solid black;" align="right">{{portfoliovaluation.netvaluation | formatNumber }}</td>
        </tr>
        <tr>
          <td scope="row">Current valuation</td>
          <td></td>
          <td></td>
          <td align="right">{{carryValuation | formatNumber }}</td>
        </tr>

        <tr class="emptyfair">
          <td scope="row"></td>
          <td></td>
          <td></td>
          <td style="border-bottom:1px solid black;" align="right"></td>
        </tr>

        <tr>
          <td scope="row">Variance</td>
          <td></td>
          <td></td>
          <td align="right">{{variation | formatNumber }}</td>
        </tr>

        <tr class="emptyfair">
          <td scope="row"></td>
          <td></td>
          <td></td>
          <td style="border-top:1px solid black;" align="right">{{variationPercent}}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
  import axioss from "../../api/axios";
  import  {formatDate} from "@/api/date"
  import  {toThousands} from "@/api/method"
  export default {
    name: "valuationreport",
    data: function () {
      return {
        portfoliofinancialinfo: {},
        comparables: {},
        lastfinancialinfo: {
          enddate: "",
          revenue: "",
          grossprofit: "",
          netprofit: "",
          totalliabilities: "",
          cashassets: ""
        },
        portfoliovaluation: {},
        discount: "",
        highValuation: "",
        lowValuation: "",
        carryCost: "",
        carryValuation: "",
        variation: "",
        variationPercent: "",
        discountValue: "",
        beforeTax: "",
        ownerShip: "",
        fundFamilyName: "",
        name: "",
        portfolioFullName: "",
        marginLG: "",
        marginG: "",
        marginLO: "",
        marginO: "",
        marginLN: "",
        marginN: "",
        ev: "",
        eqv: ""


      };
    },
    filters: {
      formatDate(time){
        if (time == null || time == '')
          return '';
        let date = new Date(time);
        return formatDate(date, 'yyyy/MM/dd');
      },
      formatNumber(num){
        if (num == '')
          return '';
        return toThousands(num);
      }

    },
    mounted: function () {
      this.valuationReport();
    },
    methods: {
      valuationReport(){
        var obj = {};
        var valuationid = this.$route.query.valuationid;

        axioss.valuationReport(valuationid).then(res => {

          this.portfoliofinancialinfo = res.data.data.portfoliofinancialinfo;
          if (res.data.data.lastfinancialinfo)
            Object.assign(this.lastfinancialinfo, res.data.data.lastfinancialinfo);
          //this.lastfinancialinfo = res.data.data.lastfinancialinfo;
          if (res.data.data.portfoliovaluation)
            Object.assign(this.portfoliovaluation, res.data.data.portfoliovaluation);


          this.ev = res.data.data.multiple * this.portfoliofinancialinfo.revenue;
          this.eqv = this.ev + this.portfoliofinancialinfo.cashassets * 1 - this.portfoliofinancialinfo.cashlevel * 1 + this.portfoliovaluation.optionproceed*1;


          this.comparables = res.data.data.portfoliocomparables;

          this.highValuation = res.data.data.highValuation;
          this.lowValuation = res.data.data.lowValuation;
          this.ownerShip = res.data.data.ownerShip * 100;
          this.carryValuation = res.data.data.carryValuation;
          this.carryCost = res.data.data.carryCost;
          this.variation = res.data.data.variation;
          this.discountValue = ((this.portfoliovaluation.valuation + this.portfoliovaluation.additional * 1) * this.portfoliovaluation.discount / 100).toFixed(2);
          this.beforeTax = this.portfoliovaluation.valuation - this.discountValue * 1;
          this.fundFamilyName = res.data.data.fundFamilyName;
          this.name = res.data.data.name;
          this.portfolioFullName = res.data.data.portfolioFullName;

          if (this.lastfinancialinfo.grossprofit != '')
            this.marginLG = this.lastfinancialinfo.grossprofit / this.lastfinancialinfo.revenue * 100 + '%';


          this.marginG = this.portfoliofinancialinfo.grossprofit / this.portfoliofinancialinfo.revenue * 100 + '%';


          if (this.lastfinancialinfo.operatingincome != '' && this.lastfinancialinfo.operatingincome != undefined)
            this.marginLO = (this.lastfinancialinfo.operatingincome / this.lastfinancialinfo.revenue * 100).toFixed(2) + '%';

          this.marginO = (this.portfoliofinancialinfo.operatingincome / this.portfoliofinancialinfo.revenue * 100).toFixed(2) + '%';

          if (this.lastfinancialinfo.netprofits != '' && this.lastfinancialinfo.netprofits != undefined)
            this.marginLN = (this.lastfinancialinfo.netprofits / this.lastfinancialinfo.revenue * 100).toFixed(2) + '%';
          this.marginN = (this.portfoliofinancialinfo.netprofits / this.portfoliofinancialinfo.revenue * 100).toFixed(2) + '%';

          if (this.carryValuation != '' && this.carryValuation != undefined)
            this.variationPercent = (this.variation / this.carryValuation * 100).toFixed(0) + '%';

        })
      }
    },
    components: {}
  };
</script>

<style scoped>
  .ValuationReport2 {
    padding: 0 6px;
  }

  h3.title {
    font-size: 16px;
    padding-left: 5px;
    box-sizing: border-box;
  }

  .header-top {
    color: black;
    font-weight: bold;
    padding-left: 5px;
    box-sizing: border-box;
    background: yellow;
  }

  .table-container1, .table-container2, .table-container3 {
    margin-top: 10px;
    width: 70%;
    border-left: 1px solid #ebeef5;
    border-right: 1px solid #ebeef5;
  }

  .table-container3 caption, .table-container2 caption {
    background: yellow;
    color: black;
  }

  .table .empty td, .table .empty th {
    height: 5px;
    border: none;
  }

  .table td, .table th {
    border: none;
  }

  .border {
    border-top: 1px solid black;
  }
</style>
