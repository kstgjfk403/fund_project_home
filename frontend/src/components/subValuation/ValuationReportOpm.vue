<template>
  <div class="ValuationReport">
    <div class="header-top font14">{{fundFamilyName}}</div>
    <div class="logo"></div>
    <div class="info">
      <ul>
        <li><span>Company:</span><span>{{name}}</span></li>
        <li><span>Website:</span><span>{{website}}</span></li>
      </ul>

    </div>
    <div class="header-top font14" style="margin-bottom:10px;">{{fundFamilyName}}</div>
    <div class="app-table-container clearfloat">

      <div class="left floatleft width15">
        <table class="table font12 table-condensed">
          <caption style="opacity:0;" class="font14 fontweight">No. of shares</caption>
          <thead>
          <tr style="opacity:0;">
            <th style="border:none;"># Series B-3</th>
            <th style="border:none;">Cost</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td scope="row" style="opacity:0;border:none;">held</td>
            <td style="opacity:0;border:none;">{{shareCost | formatNumber}}</td>
          </tr>

          <tr>
            <td scope="row" style="border:none;">IDG</td>
            <td style="border:none;"></td>
          </tr>
          <tr>
            <td scope="row" style="border:none;">{{mainFund.fundName}}</td>
            <td style="border:none;"></td>
          </tr>
          <tr>
            <td scope="row" style="border:none;">{{sideFund.fundName}}</td>
            <td style="border:none;"></td>
          </tr>
          <tr style="opacity:0;">
            <td scope="row" style="border:none;">46,157,733</td>
            <td style="border:none;">$25,000,000</td>
          </tr>
          </tbody>
        </table>
      </div>

      <div class="floatleft" style="min-width:400px;">
        <table class="table font12 table-condensed">
          <caption class="font14 fontweight">No. of shares</caption>
          <thead>
          <tr>
            <th v-for="item in roundName">{{item}}</th>
            <th>Cost</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td v-for="item in roundName">held</td>
            <td>US{{shareCost}}</td>
          </tr>
          <tr>
            <td scope="row" style="border:none"></td>
            <td style="border:none"></td>
          </tr>
          <tr>
            <td scope="row" v-for="share in mainFund.shares">{{share}}</td>
            <td>{{mainFund.cost  | formatNumber}}</td>
          </tr>
          <tr>
            <td scope="row" v-for="share in sideFund.shares">{{share}}</td>
            <td>{{sideFund.cost  | formatNumber}}</td>
          </tr>
          <tr>
            <td v-for="share in totalShares">{{share}}</td>
            <td>{{totalCost  | formatNumber}}</td>
          </tr>

          </tbody>
        </table>
      </div>

      <div class="right floatleft" style="width:40%;">
        <table class="table font12 table-condensed">
          <caption class="font14 fontweight">Valuation</caption>
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
          <tr>
            <td scope="row"></td>
            <td></td>
            <td>after FIN48</td>
            <td>Value</td>
            <td></td>
          </tr>
          <tr>
            <td scope="row" style="border:none"></td>
            <td style="border:none"></td>
            <td style="border:none"></td>
            <td style="border:none"></td>
            <td style="border:none"></td>
          </tr>
          <tr>
            <td scope="row">${{mainFund.valuation  | formatNumber}}</td>
            <td>{{mainFund.fin48tax  | formatNumber}}</td>
            <td>{{mainFund.netvaluation  | formatNumber}}</td>
            <td>{{mainFund.carryValue  | formatNumber}}</td>
            <td>{{mainFund.variation  | formatNumber}}</td>
          </tr>
          <tr>
            <td scope="row">${{sideFund.valuation}}</td>
            <td>{{sideFund.fin48tax}}</td>
            <td>{{sideFund.netvaluation}}</td>
            <td>{{sideFund.carryValue}}</td>
            <td>{{sideFund.variation}}</td>
          </tr>
          <tr>
            <td scope="row">{{totalOpm  | formatNumber}}</td>
            <td>{{totalTax  | formatNumber}}</td>
            <td>{{totalAfterTax  | formatNumber }}</td>
            <td>{{totalCarryValue | formatNumber}}</td>
            <td>{{totalVariation | formatNumber}}</td>
          </tr>
          <tr style="border-bottom:1px solid #ddd;">
            <td scope="row"></td>
            <td></td>
            <td></td>
            <td></td>
            <td>{{variationPercent}}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="valuation" style="padding-left:5px;overflow:hidden;">
      <div class="width15 floatleft">
        Valuation

      </div>
      <div class="floatleft" style="border:1px solid #ddd;min-height:100px;width:62%;">
        {{description}}

      </div>
    </div>
  </div>
</template>
<script>
  import axioss from "../../api/axios";
  import Header from "../common/Header";
  import  {toThousands} from "@/api/method"
  export default {
    name: "valuationreport",
    data: function () {
      return {
        portfoliovaluation: {},
        sideFund: {},
        mainFund: {},
        name: "",
        fundFamilyName: "",
        website: "",
        description: "",
        totalOpm: "",
        totalTax: "",
        totalAfterTax: "",
        totalCarryValue: "",
        totalVariation: "",
        roundName: [],
        variationPercent: "",
        totalCost: "",
        shareCost:"",
        totalShares:""


      };
    },
    filters: {
      formatNumber(num){
        return toThousands(num);
      }
    },
    mounted: function () {
      this.valuationReport();
    },
    components: {
      Header
    },
    methods: {
      valuationReport(){
        var obj = {};
        var valuationid = this.$route.query.valuationid;

        axioss.valuationReport(valuationid).then(res => {


          this.portfoliovaluation = res.data.data.portfoliovaluation;
          this.mainFund = res.data.data.mainFund;
          this.siteFund = res.data.data.sideFund;
          this.name = res.data.data.name;
          this.fundFamilyName = res.data.data.fundFamilyName;
          this.website = res.data.data.website;
          this.description = res.data.data.description;
          this.roundName = res.data.data.roundName;
          this.totalShares = res.data.data.totalShare;

          let totalS = 0;
          for (let i = 0; i < this.totalShares.length; i++) {
                 totalS = totalS + this.totalShares[i]*1;
          }

          if (this.mainFund && this.mainFund.opm)
            this.totalOpm = this.mainFund.opm;
          if (this.sideFund && this.sideFund.opm)
            this.totalOpm = this.totalOpm + this.sideFund.opm;

          if (this.mainFund && this.mainFund.fin48tax)
            this.totalTax = this.mainFund.fin48tax;
          if (this.sideFund && this.sideFund.fin48tax)
            this.totalTax = this.totalTax + this.sideFund.fin48tax;

          if (this.mainFund && this.mainFund.netvaluation)
            this.totalAfterTax = this.mainFund.netvaluation;
          if (this.sideFund && this.sideFund.netvaluation)
            this.totalAfterTax = this.totalAfterTax + this.sideFund.netvaluation;

          if (this.mainFund && this.mainFund.carryValue)
            this.totalCarryValue = this.mainFund.carryValue;
          if (this.sideFund && this.sideFund.carryValue)
            this.totalCarryValue = this.totalCarryValue + this.sideFund.carryValue;

          if (this.mainFund && this.mainFund.variation)
            this.totalVariation = this.mainFund.variation;
          if (this.sideFund && this.sideFund.variation)
            this.totalVariation = this.totalVariation + this.sideFund.variation;


          if (this.totalCarryValue != '' && this.totalCarryValue != '')
            this.variationPercent = (this.totalVariation / this.totalCarryValue * 100).toFixed(2) + "%";

          if (this.mainFund && this.mainFund.variation && this.mainFund.variation < 0) {
            this.mainFund.variation = '(' + this.mainFund.variation * -1 + ')';
          }

          if (this.totalVariation && this.totalVariation < 0) {
            this.totalVariation = '(' + this.totalVariation * -1 + ')';
          }

          if (this.sideFund && this.sideFund.variation && this.sideFund.variation < 0) {
            this.sideFund.variation = '(' + this.sideFund.variation * -1 + ')';
          }

          if (this.mainFund && this.mainFund.cost) {
            this.totalCost = this.mainFund.cost;
          }

          if (this.sideFund && this.sideFund.cost) {
            this.totalCost = this.totalCost + this.sideFund.cost;
          }

          this.shareCost = (this.totalCost / totalS).toFixed(2);

        })
      }
    }
  };
</script>

<style scoped>
  .header-top {
    color: black;
    font-weight: bold;
    margin-top: 15px;
    padding-left: 5px;
    box-sizing: border-box;
  }

  .info ul li span {
    display: inline-block;
    width: 15%;
  }

  .app-table-container .floatleft caption {
    text-align: center;
    background: yellow;
    caption-side: top;
    overflow: hidden;
  }

  .app-table-container .floatleft {
    padding: 5px;
    box-sizing: border-box;
  }

  .app-table-container .floatleft ul li {
    margin-top: 6px;
  }

  .clearfloat:after {
    display: block;
    clear: both;
    content: "";
    visibility: hidden;
    height: 0
  }

  .clearfloat {
    zoom: 1
  }

  .info {
    padding-left: 5px;
    box-sizing: border-box;
  }

  .table td {
    height: 30px;
  }
</style>
