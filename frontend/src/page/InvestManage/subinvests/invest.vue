<template>
<div class="invest loan" ref="Invest">
  <div class="loan-table-container">
    <h3 class="h3">Invest History</h3>
    <el-table :data="investData" border style="float:left;width:auto;">
      <el-table-column fixed prop="eiid" label="EIID" width="70"></el-table-column>
      <el-table-column prop="termsigndate" label="Term Sign Date" width="130"></el-table-column>
      <el-table-column prop="fundname" label="Fund" width="130"></el-table-column>
      <el-table-column prop="investtype" label="Invest Type" width="150"></el-table-column>
      <el-table-column prop="securitytypeidstr" label="Share Type" width="150"></el-table-column>
      <el-table-column prop="closedate" label="Payment Date" width="150"></el-table-column>
      <el-table-column prop="round" label="Round" width="110"></el-table-column>
      <el-table-column prop="shareownedno" label="Shares Acquired" width="150" :formatter="numberFormat" ></el-table-column>
      <el-table-column prop="cost" label="Cost" width="100" :formatter="numberFormat"></el-table-column>
      <el-table-column prop="otherfees" label="Fees" width="80" :formatter="numberFormat"></el-table-column>
      <el-table-column prop="taxlotdate" label="Tax Lot Date" width="150"></el-table-column>
      <el-table-column prop="vouncher" label="vouncher" width="150"></el-table-column>
      <el-table-column prop="conversionratio" label="Rate" width="70"></el-table-column>
      <el-table-column prop="convertamount" label="convert amount" width="150" :formatter="numberFormat"></el-table-column>
      <el-table-column label="Opt" width="60" fixed='right' v-if="isDetail!='false'">
        <template slot-scope="scope">
          <i class="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></i>
          <i class="el-icon-delete" @click="handleDelet(scope.$index, scope.row)"></i>
        </template>
      </el-table-column>
    </el-table>
    <div class="table-foot"  v-if="isDetail!='false'">
      <i class="el-icon-circle-plus" @click="handleAdd"></i>
    </div>
  </div>
    <el-dialog title="Invest Edit" :visible.sync="investVisible" @close="resetForm('investForm')">
    <div class="select-container">
      <el-form :model="investForm" :label-position='labelPosition' ref="investForm" :rules="rules">
        <div class="select-fixed">
          <el-form-item label="Invest Type" prop='investtype'>
            <el-select v-model="investForm.investtype" placeholder="  " @change="whichShow" :disabled="isDisable">
              <el-option v-for="item in investTypeList" :key="item.baseId"
              :label="item.baseName" :value="item.baseId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Payment Date" prop='closedate'>
            <el-date-picker v-model="investForm.closedate" type="date" placeholder=" " :disabled="isDisable||isParticipatDisable">
          </el-date-picker>
          </el-form-item>
          <el-form-item label="Fund Family" v-if="buttonShow=='add'">
            <el-select v-model="investForm.fundfamillyname" placeholder="  " @change="showTable" :disabled="isDisable||isShareSplitDisable||isParticipatDisable" filterable>
              <el-option v-for="item in fundFamilyList" :key="item.baseId"
              :label="item.baseName" :value="item.baseName"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Fund" v-else>
            <el-select v-model="investForm.fundname" placeholder="  " @change="showTable" :disabled="isDisable||isParticipatDisable" filterable>
              <el-option v-for="item in fundList" :key="item.baseId"
              :label="item.baseName" :value="item.baseName"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Termsign Date" prop='termsigndate'>
            <el-date-picker v-model="investForm.termsigndate" type="date" placeholder=" " :disabled="isDisable">
            </el-date-picker>
          </el-form-item>
        </div>
        <div v-if="investType=='Loan To Equity'">
          <el-form-item label="Round">
            <el-input v-model="investForm.round"></el-input>
          </el-form-item>
          <el-form-item label="Currency">
            <el-select v-model="investForm.currency" placeholder="  ">
              <el-option v-for="item in fundCurrencyList" :key="item.baseId"
              :label="item.baseName" :value="item.baseId"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <div v-else-if="investType=='Equity Interest'">
          <el-form-item label="Share Type">
            <el-select v-model="investForm.securitytypeid" placeholder="  ">
              <el-option label='Equity Interest' value='Equity Interest' key='6'></el-option>
              <el-option label='Capital Call' value='Capital Call' key='14'></el-option>
              <el-option label='Distribution' value='Distribution' key='15'></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Round" prop='round'>
            <el-input v-model="investForm.round"></el-input>
          </el-form-item>
          <el-form-item label="Registered Capital">
            <el-input v-model="investForm.shareownedno"></el-input>
          </el-form-item>
          <el-form-item label="Cost">
            <el-input v-model="investForm.cost"></el-input>
          </el-form-item>
          <el-form-item label="Other Fees">
            <el-input v-model="investForm.otherfees"></el-input>
          </el-form-item>
          <el-form-item label="Currency">
            <el-select v-model="investForm.currency" placeholder="  ">
              <el-option v-for="item in fundCurrencyList" :key="item.baseId"
              :label="item.baseName" :value="item.baseId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Remarks">
            <el-input v-model="investForm.remarks"></el-input>
          </el-form-item>
          <el-form-item label="Tax Lot Date">
            <el-date-picker v-model="investForm.taxlotdate" type="date" placeholder=" ">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="Voucher">
            <el-input v-model="investForm.vouncher"></el-input>
          </el-form-item>
        </div>
        <div v-else-if="investType=='Equity Investment'||investType=='Equity Investment&Loan To Equity'||investType=='Bond'">
            <el-form-item label="Share Type">
                <el-select v-model="investForm.securitytypeid" placeholder="  ">
                    <el-option label='Common' value='Common' key='2'></el-option>
                    <el-option label='Preferred' value='Preferred' key='3'></el-option>
                    <el-option label='Bond' value='Bond' key='20'></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Round" prop='round'>
                <el-input v-model="investForm.round"></el-input>
            </el-form-item>
            <el-form-item label="Acquired Shares">
                <el-input v-model="investForm.shareownedno"></el-input>
            </el-form-item>
            <el-form-item label="Cost">
                <el-input v-model="investForm.cost"></el-input>
            </el-form-item>
            <el-form-item label="Other Fees">
                <el-input v-model="investForm.otherfees"></el-input>
            </el-form-item>
            <el-form-item label="Currency">
                <el-select v-model="investForm.currency" placeholder="  ">
                    <el-option v-for="item in fundCurrencyList" :key="item.baseId"
                    :label="item.baseName" :value="item.baseId"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Remarks">
                <el-input v-model="investForm.remarks"></el-input>
            </el-form-item>
        </div>
        <div v-else-if="investType=='Convert To Equity Interest'">
            <el-form-item label="Round" prop='round'>
                <el-input v-model="investForm.round"></el-input>
            </el-form-item>
        </div>
        <div v-else-if="investType=='Convert To Equity Investment'">

            <el-form-item label="Round" prop='round'>
                <el-input v-model="investForm.round"></el-input>
            </el-form-item>
        </div>
        <div v-else-if="investType=='Share Reclassification'">
            <el-form-item label="Share Type">
                <el-select v-model="investForm.securitytypeid" placeholder="  ">
                    <el-option label='Common' value='Common' key="2"></el-option>
                    <el-option label='Preferred' value='Preferred' key="3"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item  label="shares">
                <el-select v-model="investForm.fromeiid">
                    <el-option v-for="item in shareList" :key="item.baseId"
                    :label="item.baseName" :value="item.baseId"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Converted Shares">
                <el-input v-model="investForm.convertamount"></el-input>
            </el-form-item>
            <el-form-item label="Round" prop='round'>
                <el-input v-model="investForm.round"></el-input>
            </el-form-item>
            <el-form-item label="Remarks">
                <el-input v-model="investForm.remarks"></el-input>
            </el-form-item>
        </div>
        <div v-else-if="investType=='Shares Split'">
            <el-form-item label="Share Split Rate(1:x):">
                <el-input v-model="investForm.sharesplitrate"></el-input>
            </el-form-item>
            <el-form-item label="Remarks:">
                <el-input type="textarea" autosize placeholder="  " v-model="investForm.comment">
                </el-input>
            </el-form-item>
        </div>
        <div v-else-if="investType=='Equity Interest(Loan To Equity)'">
          <el-form-item label="Round" prop='round'>
            <el-input v-model="investForm.round"></el-input>
          </el-form-item>
          <el-form-item label="Currency">
            <el-select v-model="investForm.currency" placeholder="  ">
              <el-option v-for="item in fundCurrencyList" :key="item.baseId"
              :label="item.baseName" :value="item.baseId"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <div v-else-if="investType=='Equity Investment(Loan To Equity)'">
          <el-form-item label="Round" prop='round'>
            <el-input v-model="investForm.round"></el-input>
          </el-form-item>
          <el-form-item label="Currency">
            <el-select v-model="investForm.currency" placeholder="  ">
              <el-option v-for="item in fundCurrencyList" :key="item.baseId"
              :label="item.baseName" :value="item.baseId"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <div v-else-if="investType=='Warrant Exercise'">
            <el-form-item label="Warrant">
              <el-select v-model="investForm.convertfromwarrantid" placeholder="  ">
                <el-option v-for="item in wrrantSelectList" :key="item.baseId"
                :label="item.baseName" :value="item.baseId"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Share Type">
              <el-select v-model="investForm.securitytypeid" placeholder="  ">
                <el-option label='Equity Interest' value='Equity Interest' key='6'></el-option>
                <el-option label='Common' value='Common' key="2"></el-option>
                <el-option label='Preferred' value='Preferred' key="3"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Round" prop='round'>
              <el-input v-model="investForm.round"></el-input>
            </el-form-item>
            <el-form-item label="Acquired Shares">
              <el-input v-model="investForm.shareownedno"></el-input>
            </el-form-item>
            <el-form-item label="Cost">
              <el-input v-model="investForm.cost"></el-input>
            </el-form-item>
            <el-form-item label="Other Fees">
              <el-input v-model="investForm.otherfees"></el-input>
            </el-form-item>
            <el-form-item label="Conversion Rate">
              <el-input v-model="investForm.conversionratio"></el-input>
            </el-form-item>
        </div>
        <div v-else-if="investType=='None Participated In'">
          <el-form-item label="Round" prop='round'>
              <el-input v-model="investForm.round"></el-input>
          </el-form-item>
        </div>
        <div v-show="isTableShow=='LoanToEquity1'">
          <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;">
            <thead>
              <tr>
                <th scope="col">Time</th>
                <th scope="col">Fund Name</th>
                <th scope="col">loanAmount</th>
                <th scope="col">other Fees</th>
                <th scope="col">Additional cost</th>
                <th scope="col">convertAmount</th>
                <th scope="col">Capitalized Interest</th>
                <th scope="col">Acquired Shares</th>
                <th scope="col">Share Type</th>
                <th scope="col">convert Rate</th>
              </tr>
            </thead>
              <tbody>
                <tr v-for="item in loanToEquityData">
                  <td scope="row">{{item.closedate}}</td>
                  <td>{{item.fundname}}</td>
                  <td>{{item.remainderamount | transformNum}}</td>
                  <td>{{item.otherfees}}</td>
                  <td><el-input v-model="item.additionalcost" v-isedit class="inputnone"></el-input></td>
                  <td><el-input v-model="item.convertamount" v-isedit class="inputnone"></el-input></td>
                  <td><el-input v-model="item.proceeds" v-isedit class="inputnone"></el-input></td>
                  <td><el-input v-model="item.shareownedno" v-isedit class="inputnone"></el-input></td>
                  <td><el-select v-model="item.securitytypeid" placeholder="" v-isedit class="inputnone">
                    <el-option value="Common" label="Common" key="1"></el-option>
                    <el-option value="Preferred" label="Preferred" key="2"></el-option>
                    <el-option value="Equity Interest" label="Equity Interest" key="3"></el-option>
                    </el-select></td>
                  <td><el-input v-model="item.conversionratio" v-isedit class="inputnone"></el-input></td>
                </tr>
              </tbody>
          </table>
        </div>
      </el-form>
    </div>
    <subCapTable :dataObj='dataObj' :investForm='investForm' :buttonShow='buttonShow' :capTableData='captelDetailData' v-show="subCapTableShow"></subCapTable :key="new Date()">
    <div slot="footer" class="dialog-footer">
      <el-button @click="investVisible = false" size='mini'>Cancel</el-button>
      <el-button v-if="buttonShow=='add'" type="primary" size='mini' @click="submitForm('investForm','add')">Create</el-button>
      <el-button v-else type="primary" size='mini' @click="submitForm('investForm','update')">Update</el-button>
    </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from "@/api/axios";
import * as method from "@/api/method";
import subCapTable from "../../capTable/subCapTable";
import mix from "@/api/mixin";
import bus from "@/api/eventbus";
export default {
  name: "invest",
  mixins: [mix],
  data() {
    var paymentDateValidate = function(rule, value, callback) {
      if (this.isDisable || this.isParticipatDisable) {
        callback();
      } else if (!value) {
        callback(new Error("require"));
      } else {
        callback();
      }
    };
    return {
      subCapTableShow: true,
      isDisable: false,
      isShareSplitDisable: false,
      isParticipatDisable: false,
      investVisible: false,
      isReqLoanToEquity: false,
      investType: "",
      isTableShow: "",
      labelPosition: "right",
      buttonShow: "add",
      fundFamily: "",
      maxclosedate: "",
      dataObj: {
        eiid: "",
        portfolioid: "",
        fundid: "",
        investtype: "",
        closedate: "",
        maxclosedate: "",
        round: "",
        securitytypeid: ""
      },
      investData: [],
      loanToEquityData: [],
      translateObj: {
        Common: 2,
        Preferred: 3,
        "Equity Interest": 6,
        Distribution: 15,
        "Capital Call": 14,
        Bond: 20
      },
      shareList: [],
      investTypeList: "",
      fundFamilyList: "",
      fundCurrencyList: [],
      wrrantSelectList: [],
      investForm: {
        investtype: "",
        closedate: "",
        fundfamillyname: "",
        cost: "",
        interestproceeds: "",
        securitytypeid: "",
        round: "",
        shareownedno: "",
        conversionratio: "",
        otherfees: "",
        currency: "USD",
        remarks: "",
        taxlotdate: "",
        vouncher: "",
        otherproceeds: "",
        proceeds: "",
        costrelization: 0,
        termsigndate: "",
        fundname: "",
        convertfromwarrantid: "",
        sharesplitrate: "",
        convertamount: "",
        fromeiid: ""
      },
      emptyInvestForm: {
        investtype: "",
        closedate: "",
        fundfamillyname: "",
        cost: "",
        interestproceeds: "",
        securitytypeid: "",
        round: "",
        shareownedno: "",
        conversionratio: "",
        otherfees: "",
        currency: "USD",
        remarks: "",
        taxlotdate: "",
        vouncher: "",
        otherproceeds: "",
        additionalcost: "",
        proceeds: "",
        costrelization: 0,
        termsigndate: "",
        convertfromwarrantid: "",
        sharesplitrate: "",
        convertamount: "",
        fromeiid: ""
      },
      capTabelList: [],
      captelDetailData: [],
      options: [
        {
          value: "USD",
          label: "USD"
        }
      ],
      rules: {
        investtype: [
          { required: true, message: "required", trigger: "change" }
        ],
        closedate: [{ validator: paymentDateValidate, trigger: "change" }],
        termsigndate: [
          { required: true, message: "required", trigger: "change" }
        ],
        round: [{ required: true, message: "required", trigger: "blur" }]
      }
    };
  },
  updated() {},
  mounted() {
    this.invesDropList();
    this.reqinvestList();
  },
  methods: {
    invesDropList() {
      var obj = {
        dictArray: "DDL_InvestType,FUNDFAMILY,DDL_SecurityType,CURRENCY,FUND"
      };
      axioss.invesDropList(obj).then(res => {
        this.investTypeList = res.data.data[0].baseInfoList;
        this.fundFamilyList = res.data.data[1].baseInfoList;
        this.fundCurrencyList = res.data.data[3].baseInfoList;
        this.fundList = res.data.data[4].baseInfoList;
      });
    },
    reqWrrantSelectList() {
      axioss.reqWrrantSelectList(this.portfolioid).then(res => {
        this.wrrantSelectList = res.data.data;
      });
    },
    numberFormat: function(row, column) {
      var num = row[column.property];
      if (num == undefined) {
        return "";
      }
      return method.toThousands(num);
    },
    reqinvestList(id) {
      var portfolioid = id || this.portfolioid;
      axioss.reqinvestList(portfolioid).then(res => {
        this.investData = this.formatTime(res.data.data);
      });
    },
    reqLoanToEquity(fundfamillyname, portfolioid) {
      var obj = { fundfamillyname: fundfamillyname, portfolioid: portfolioid };
      axioss.reqLoanToEquity(obj).then(res => {
        this.loanToEquityData = this.formatTime(res.data.data);
      });
    },
    reqReclassList(fundfamillyname) {
      var obj = {
        portfolioid: this.portfolioid,
        fundfamillyname: fundfamillyname
      };
      axioss.reqReclassList(obj).then(res => {
        this.shareList = res.data.data;
      });
    },
    querySingalData(id) {
      axioss.querySingalData(id).then(res => {
        var newdata = JSON.stringify(res.data.data);
        var data = JSON.parse(newdata);
        this.dataObj.portfolioid = data.portfolioid;
        this.dataObj.termsigndate = data.termsigndate;
        this.dataObj.closedate = data.closedate;
        this.dataObj.round = data.round;
        this.dataObj.securitytypeid = data.securitytypeid;
        this.dataObj.bizid = data.eiid;
        this.dataObj.maxclosedate = data.maxclosedate;
        this.dataObj.investtype = data.investtype;
        this.maxclosedate = data.maxclosedate;
        this.investForm = this.translateEdit(data, this.translateObj);
        this.loanToEquityData = this.formatTime(
          res.data.data.portfolioloantoequityList
        );
        this.loanToEquityData = this.translateShareType(
          this.loanToEquityData,
          this.translateObj
        );
        this.captelDetailData = data.portfoliocaptablevaluedetailList;
        this.$store.dispatch(
          "saveCapTabel",
          data.portfoliocaptablevaluedetailList
        );
        this.whichShow(data.investtype);
      });
    },
    submitForm(formName, type) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          console.log(obj);
          var obj = this.translateSubmit(this.investForm, this.translateObj);
          console.log(obj);
          obj.portfoliocaptablevaluedetailList = this.capFormList;
          console.log(obj);
          if (this.isTableShow == "LoanToEquity1") {
            obj.portfolioloantoequityList = this.toMs(this.loanToEquityData);
            obj.portfolioloantoequityList = this.translateShareTypeSubmit(
              obj.portfolioloantoequityList,
              this.translateObj
            );
          }
          console.log(obj);
          if (type == "add") {
            obj.portfolioid = this.portfolioid;
            axioss.addInvest(obj).then(res => {
              if (res.data.code == "SUCCESS") {
                this.$message({
                  type: "success",
                  message: "Create success"
                });
                this.investVisible = false;
                this.reqinvestList(this.portfolioid);
                Object.assign(this.investForm, this.emptyInvestForm);
                bus.$emit("updateCaptable");
              } else {
                this.$message({
                  type: "warning",
                  message: "Create failure"
                });
              }
            });
          } else {
            axioss.updateInvest(obj).then(res => {
              if (res.data.code == "SUCCESS") {
                this.$message({
                  type: "success",
                  message: "Update success"
                });
                this.investVisible = false;
                this.reqinvestList(this.portfolioid); //请求invest数据
                Object.assign(this.investForm, this.emptyInvestForm);
                bus.$emit("updateCaptable");
              } else {
                this.$message({
                  type: "warning",
                  message: "Update failure"
                });
              }
            });
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleAdd() {
      this.subCapTableShow = false;
      this.isReqLoanToEquity = true;
      this.isDisable = false;
      this.investForm = { currency: "USD" };
      this.investVisible = true;
      this.buttonShow = "add";
      this.investType = "";
      this.whichShow("");
    },
    handleEdit(index, data) {
      this.subCapTableShow = true;
      this.isReqLoanToEquity = false;
      this.isDisable = true;
      this.investVisible = true;
      this.buttonShow = "eidt";
      this.investForm.investtype = data.investtype;
      this.querySingalData(data.eiid);
      bus.$emit("clearSubCaptable");
    },
    handleDelet(index, data) {
      var id = data.eiid;
      this.$confirm("This Opt will permanently delete the file, whether it will continue?", "Notice", {
        confirmButtonText: "OK",
        cancelButtonText: "Cancel",
        type: "warning"
      }).then(() => {
        axioss.deletInvest(id).then(res => {
          var status = res.data.code;
          if (status.toLocaleLowerCase() == "success") {
            this.reqinvestList();
            this.$message({
              type: "success",
              message: "Delete success!"
            });
            bus.$emit("updateCaptable");
          } else {
            this.$message({
              type: "error",
              message: "Delete failure!"
            });
          }
        });
      });
    },
    whichShow(val) {
      this.investType = val;
      if (val == "Warrant Exercise") {
        this.reqWrrantSelectList();
      }
      if (val == "Bond") {
        this.investForm.securitytypeid = "Bond";
      }
      this.isShareSplitDisable = val == "Shares Split" ? true : false;
      this.isParticipatDisable = val == "None Participated In" ? true : false;
      if (
        val != "Equity Interest(Loan To Equity)" &&
        val != "Equity Investment(Loan To Equity)" &&
        val != "Loan To Equity"
      ) {
        this.isTableShow = "";
      } else {
        if (this.investForm.fundfamillyname) {
          this.isTableShow = "LoanToEquity1";
          if (this.isReqLoanToEquity) {
            this.reqLoanToEquity(
              this.investForm.fundfamillyname,
              this.portfolioid
            );
          }
        }
      }
    },
    showTable(val) {
      if (val) {
        if (
          this.investType == "Equity Interest(Loan To Equity)" ||
          this.investType == "Equity Investment(Loan To Equity)" ||
          this.investType == "Loan To Equity"
        ) {
          this.isTableShow = "LoanToEquity1";
          if (this.isReqLoanToEquity) {
            this.reqLoanToEquity(val, this.portfolioid);
          }
        }
        if (this.investType == "Share Reclassification") {
          this.reqReclassList(val);
        }
      } else {
        this.isTableShow = "";
      }
    },
    translateSubmit(data, obj) {
      var val = data.securitytypeid;
      if (!val) {
        return data;
      } else {
        data.securitytypeid = obj[val];
        return data;
      }
    },
    translateEdit(data, obj) {
      var val = data.securitytypeid;
      if (!val) {
        return data;
      } else {
        for (var i in obj) {
          if (obj[i] == val) {
            data.securitytypeid = i;
          }
        }
      }
      return data;
    },
    translateShareType(data, obj) {
      if (data && data != null && data != []) {
        for (var i = 0; i < data.length; i++) {
          data[i] = this.translateEdit(data[i], obj);
        }
      }
      return data;
    },
    translateShareTypeSubmit(data, obj) {
      for (var i = 0; i < data.length; i++) {
        data[i] = this.translateSubmit(data[i], obj);
      }
      return data;
    },
    formatTime(data) {
      if (data && data != null && data != []) {
        for (var i = 0; i < data.length; i++) {
          data[i].closedate = method.toLocalString(data[i].closedate);
          data[i].termsigndate = method.toLocalString(data[i].termsigndate);
          data[i].taxlotdate = method.toLocalString(data[i].taxlotdate);
        }
      }
      return data;
    },
    toMs(data) {
      if (data.constructor == Array && data != []) {
        for (var i = 0; i < data.length; i++) {
          data[i].closedate = method.toMs(data[i].closedate);
        }
      }
      return data;
    }
  },
  computed: {
    portfolioid() {
      if (this.$store.state.portfolioid == "") {
        this.$store.dispatch("updateData");
      }
      return this.$store.state.portfolioid;
    },
    capFormList() {
      return this.$store.state.capTabelData;
    },
    isDetail() {
      if (this.$store.state.portfolioid == "") {
        this.$store.dispatch("updateIsDetail");
      }
      return this.$store.state.isDetail;
    }
  },
  components: {
    subCapTable
  },
  directives: {
    isedit: {
      inserted: function(el) {
        var inp = el.children[0];
        inp.onfocus = function() {
          this.style.borderColor = "#ccc";
        };
        inp.onblur = function() {
          this.style.borderColor = "transparent";
        };
      }
    }
  }
};
</script>
<style scoped lang="scss">
.invest {
  .loan-table-container h3 {
    font-size: 16px;
    padding: 5px 0;
    padding-left: 10px;
    background: #eee;
    margin: 0;
    border-bottom: 1px solid #ebeef5;
  }
}
.loan-table-container .table-foot {
  background: #eee;
  padding: 5px 0;
  padding-left: 10px;
}
.select-container {
  width: 100%;
}
</style>
