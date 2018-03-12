<template>
  <div class="valuation loan">
    <Header></Header>
    <div class="valuation-top">
      <el-form :model="valuationFormData">
        <el-form-item label="Fund Family:">
          <el-radio-group v-model="fundFamilyId" @change="reqFundFamilyName">
            <el-radio :key="item.baseId" :label="item.baseId" v-for="item in fundfamilyList">{{item.baseName}}











            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="Fund Name">
          <el-radio-group v-model="valuationFormData.fundIds" style="display:inline-block;">
            <el-radio :label="item.fundid" :key="item.fundid" v-for="item in checkList">{{item.fundname}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="Time">
          <el-date-picker v-model="valuationFormData.VALUEDATE" type="date" placeholder="select date"></el-date-picker>
          <el-button size="mini" type="primary" @click="searchData">Search</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="valuation-bottom">
      <h3 class="h3">Valuation</h3>
      <div class="button-container">
        <el-button type="primary" size="mini" style="margin:5px 0;" @click="handleAdd">NEW</el-button>
      </div>
      <el-table :data="valuationData" border>
        <el-table-column prop="fundName" label="Foud"></el-table-column>
        <el-table-column prop="portfolioName" label="Portfolio"></el-table-column>
        <el-table-column prop="valuationdate" label="Valuation Date" :formatter="dateFormat"></el-table-column>
        <el-table-column prop="valuationmethod" label="Method"></el-table-column>
        <el-table-column prop="valuation" label="Valuation" :formatter="numberFormat"></el-table-column>
        <el-table-column prop="netvaluation" label="Net Valuation" :formatter="numberFormat"></el-table-column>
        <el-table-column prop="valuationnote" label="Note"></el-table-column>
        <el-table-column label="Opt" width="100">
          <template slot-scope="scope">
            <i class="el-icon-edit" @click="editValuation(scope.$index,scope.row)"></i>
            <i class="el-icon-delete" @click="deleteValuation(scope.$index,scope.row)"></i>
            <i class="el-icon-info" style="color:#00a1e9;" @click="linkto(scope.$index,scope.row)"></i>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination-container">
        <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount"
                       :current-page.sync="currentPage" @current-change="handleCurrentChange"></el-pagination>
      </div>
    </div>
    <el-dialog title="valuation" :visible.sync="valuationVisible">
      <div class="select-container">
        <div class="select-fixed" style="overflow:hidden">
          <el-form :model="valuationForm" ref="valuationForm" :label-position='labelPosition'>
            <el-form-item label="Fund">
              <el-input v-model="fundname" placeholder=" " disabled></el-input>
            </el-form-item>
            <el-form-item label="Protfolio">
              <el-select v-model="valuationForm.portfolioid" placeholder="  " filterable>
                <el-option v-for="item in fundprolist" :key="item.portfolioID"
                           :label="item.abbName" :value="item.portfolioID"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Valuation Date">
              <el-date-picker v-model="valuationForm.valuationdate" type="date" placeholder="select date">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="ValuationMethod">
              <el-select v-model="valuationForm.valuationmethod" placeholder="  " filterable @change="whichShow">
                <el-option v-for="(item,index) in valueMethod" :label="item" :value="item" :key="index"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="BaseValue" v-show="isShow">
              <el-input v-model="baseValue" placeholder=" "></el-input>
            </el-form-item>

            <el-form-item label="Multiple" v-show='isShow'>
              <el-input v-model="multiple" placeholder=" "></el-input>
            </el-form-item>


            <el-form-item label="Cash" v-show="isEvShow">
              <el-input v-model="valuationForm.cashassets" placeholder=" "></el-input>
            </el-form-item>
            <el-form-item label="Debt" v-show="isEvShow">
              <el-input v-model="valuationForm.debt" placeholder=" "></el-input>
            </el-form-item>
            
            <el-form-item label="Option Proceed" v-show="isEvShow">
              <el-input v-model="optionproceed" placeholder=" "></el-input>
            </el-form-item>
            <el-form-item label="Prefer Stock Preference" v-show="isEvShow">
              <el-input v-model="preferstockprefer" placeholder=" "></el-input>
            </el-form-item>


            <el-form-item label="FairValue">
              <el-input v-model="fairvalue" placeholder=" " :disabled="isShow"></el-input>
            </el-form-item>
            <el-form-item label="OwnerShip">
              <el-input v-model="valuationForm.prop" placeholder=" "></el-input>
            </el-form-item>

            <el-form-item label="Valuation">
              <el-input :value="isComp?valuation:valuationForm.valuation" :disabled="isComp"
                        @input.native="compValuation($event)" placeholder=" " ref="valuation"></el-input>
            </el-form-item>

            <el-form-item label="Additional">
              <el-input v-model="valuationForm.additional" placeholder=" "></el-input>
            </el-form-item>

            <el-form-item label="DLOM(%)">
              <el-input v-model="valuationForm.discount" placeholder=" "></el-input>
            </el-form-item>
            <el-form-item label="Fin48tax">
              <el-input v-model="valuationForm.fin48tax" placeholder=" "></el-input>
            </el-form-item>

            <el-form-item label="Loan">
              <el-input v-model="valuationForm.loan" placeholder=" "></el-input>
            </el-form-item>


            <el-form-item label="NetValuation">
              <el-input v-model="netvaluation" placeholder=" "></el-input>
            </el-form-item>
          </el-form>
        </div>
        <hr/>
        <div class="company-input select-fixed" style="overflow:hidden;position:relative" v-show="isShow">
          <el-form :model="valuationForm" ref="valuationForm" :label-position='labelPosition'>
            <el-form-item label="Company name">
              <el-input v-model="companyForm.comparableName"></el-input>
            </el-form-item>
            <el-form-item label="Multiple">
              <el-input v-model="companyForm.multiple"></el-input>
            </el-form-item>
            <el-form-item label="Weight">
              <el-input v-model="companyForm.weight"></el-input>
            </el-form-item>
          </el-form>
          <i class="el-icon-circle-plus" style="color:red;position:absolute;top:10px;right:10px;"
             @click="addCompareCompany"></i>
        </div>
        <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;width:70%;"
               v-show="isShow">
          <thead>
          <tr>
            <th scope="col">Company name</th>
            <th scope="col">Multiple</th>
            <th scope="col">Weight</th>
            <th scope="col">Opt</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="item in valuationTableData">
            <td>{{item.comparableName}}</td>
            <td>{{item.multiple}}</td>
            <td>{{item.weight}}</td>
            <td><i class="el-icon-delete" @click="deleteComparable(item.cid)"></i></td>
          </tr>
          </tbody>
        </table>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('valuationForm')" size='mini'>Cancel</el-button>
        <el-button type="primary" size='mini' @click="submitForm('valuationForm')">Create</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import axioss from '@/api/axios';
  import * as method from "@/api/method";
  import Header from "./common/Header";
  export default {
    name: "Valuation",
    data(){
      return {
        isEdit: false,
        isShow: false,
        isEvShow: false,
        currentPage: 1,
        allcount: 0,
        checkList: '',
        fundfamilyList: [],
        fundFamilyId: '',
        fundname: '',
        labelPosition: 'right',
        valuationVisible: false,
        valuationData: [],
        fundprolist: [],
        valueMethod: [],
        baseValue: '',
        fairvalue: '',
        multiple: '',
        optionproceed:'',
        preferstockprefer:'',
        valuationFormData: {
          fundIds: '',
          VALUEDATE: ''
        },
        companyForm: {
          multiple: '',
          weight: '',
          valueDate: '',
          comparableName: '',
          pid: ''
        },
        companyFormEmpty: {
          multiple: '',
          weight: '',
          valueDate: '',
          comparableName: '',
          pid: ''
        },

        valuationForm: {
          valuationid: '',
          fundid: '',
          portfolioid: "",
          valuationmethod: '',
          valuationdate: '',
          fairvalue: '',
          discount: '0',
          fin48tax: '0',
          prop: '',
          additional: '0',
          valuation: '',
          rating: '',
          multiple: '',
          netvaluation: '',
          loan: 0,
          debt: 0,
          cashassets: 0,
          optionproceed: 0,
          preferstockprefer: 0
        },
        valuationFormEmpty: {
          valuationid: '',
          fundid: '',
          portfolioid: "",
          valuationmethod: '',
          valuationdate: '',
          fairvalue: '',
          discount: '0',
          fin48tax: '0',
          prop: '',
          additional: '0',
          valuation: '',
          rating: '',
          multiple: '',
          netvaluation: '',
          loan: 0,
          debt: 0,
          cashassets: 0
        },
        valuationTableData: [],
        radio2: ''
      }
    },
    mounted(){
      this.reqselectlist();
      this.valuationForm.valuation = this.valuation;
    },
    methods: {
      cancel(){
        this.valuationVisible = false;
        this.empForm();
      },
      empForm(){
        this.valuationForm = JSON.parse(JSON.stringify(this.valuationFormEmpty));
        this.baseValue = "";
        this.multiple = "";
        this.fairvalue = "";
        this.companyForm = JSON.parse(JSON.stringify(this.companyFormEmpty));
        this.valuationTableData = [];
        this.isShow = false;
      },
      compValuation(event){
        this.valuationForm.valuation = event.target.value;
      },
      reqselectlist(){
        axioss.reqselectlist().then(res => {
          this.fundfamilyList = res.data.data;
        })
      },
      reqFundFamilyName(id){
        this.checkList = [];
        var fundfamilyid = id || this.fundFamilyId;
        axioss.reqFundFamilyName(fundfamilyid).then(res => {
          this.checkList = res.data.data;
        })
      },
      reqfundproList(){
        var id = this.valuationFormData.fundIds;
        axioss.reqfundproList(id).then(res => {
  
          this.fundprolist = res.data.data;
        })
      },

      dateFormat: function (row, column) {
        var date = row[column.property];
        if (date == undefined) {
          return "";
        }
        return method.toLocalString(date);
      },

      numberFormat: function (row, column) {
        var num = row[column.property];
        if (num == undefined) {
          return "";
        }
        return method.toThousands(num);
      },


      reqValueMethod(){
        axioss.reqValueMethod().then(res => {

          this.valueMethod = res.data.data;
        })
      },
      reqIndiction(){
        var obj = {};
        obj.portfolioid = this.valuationForm.portfolioid;
        obj.valuationdate = this.valuationForm.valuationdate;
        obj.valuationmethod = this.valuationForm.valuationmethod;
        obj.fundid = this.valuationForm.fundid;
        if (this.valuationForm.portfolioid && this.valuationForm.valuationdate) {
          axioss.reqIndiction(obj).then(res => {
            //如果是空的时候提示没有获得估值数据.//
            Object.assign(this.valuationForm, res.data.data);
            if (obj.valuationmethod == 'PE')
              this.baseValue = this.valuationForm.NetProfit;
            if (obj.valuationmethod == 'PS')
              this.baseValue = this.valuationForm.Revenue;
            if (obj.valuationmethod == 'PB')
              this.baseValue = this.valuationForm.NetAsset;
            if (obj.valuationmethod == 'EV') {
              this.baseValue = this.valuationForm.Revenue;
            }
          })
        }
        if (!this.valuationVisible) {
          this.baseValue = ""
        }
      },
      queryLastLRound(){
        var obj = {};
        obj.fundid = this.valuationFormData.fundIds;
        obj.portfolioid = this.valuationForm.portfolioid;
        obj.valuationdate = this.valuationForm.valuationdate;
        axioss.queryLastLRound(obj).then(res => {
          Object.assign(this.valuationForm, res.data.data);
          this.fairvalue = this.valuationForm.fairvalue;
        })
      },
      reqCompareList(obj){
        axioss.reqCompareList(obj).then(res => {
          this.valuationTableData = res.data.data;
          this.calculateMultiple();
        })
      },
      searchData(){
        var obj = {}, ids;
        //ids=this.valuationFormData.fundIds.slice(-this.checkList.length)//解决引用类型数据追加问题,不能清空的问题。
        //obj.fundIds=ids;
        obj.valuationdate = this.valuationFormData.VALUEDATE;
        var newobj = {
          pageIndex: this.currentPage,
          pageCount: 10
        };
        Object.assign(this.valuationFormData, newobj);
        axioss.reqValList(this.valuationFormData).then(res => {

          this.valuationData = res.data.data;
          this.allcount = res.data.count;
        })
      },
      addCompareCompany(){
        this.companyForm.pid = this.valuationForm.portfolioid;
        this.companyForm.valueDate = this.valuationForm.valuationdate;
        axioss.addCompareCompany(this.companyForm).then(res => {

          if (res.data.code == "SUCCESS") {
            var obj = {};
            obj.pid = this.valuationForm.portfolioid;
            obj.valueDate = this.valuationForm.valuationdate;
            this.reqCompareList(obj);
          }
        })
      },
      submitForm(formName){
        //this.valuationForm.valuation = this.valuation;

        this.$refs[formName].validate((validate) => {
          if (validate) {
            this.valuationForm.fundid = this.valuationFormData.fundIds;
            //this.valuationForm.valuation = this.valuation;
            this.valuationForm.netvaluation = this.netvaluation;
            this.valuationForm.fairvalue = this.fairvalue;
            this.valuationForm.multiple = this.multiple;
            this.valuationForm.optionproceed = this.optionproceed;
            this.valuationForm.multiple = this.multiple;
            this.valuationForm.preferstockprefer = this.preferstockprefer;

            if (this.isEdit) {

              axioss.updateValuation(this.valuationForm).then(res => {
                if (res.data.code == "SUCCESS") {
                  this.$refs[formName].resetFields();
                  this.$message({
                    type: 'success',
                    message: 'Save Success'
                  });
                  this.searchData();
                  Object.assign(this.valuationForm, this.valuationFormEmpty);
                  this.fairvalue = "";
                  this.multiple = "";
                  /* 重置数据 */
                  this.empForm();
                  this.valuationVisible = false;
                } else {
                  this.$message({
                    type: 'warning',
                    message: 'Save failure'
                  })
                }
              });
            } else {

              axioss.addvaluation(this.valuationForm).then(res => {
                if (res.data.code == "SUCCESS") {
                  this.$refs[formName].resetFields();
                  this.$message({
                    type: 'success',
                    message: 'Create success'
                  });
                  this.searchData();
                  Object.assign(this.valuationForm, this.valuationFormEmpty);
                  this.fairvalue = "";
                  this.multiple = "";
                  /* 重置数据 */
                  this.empForm();
                  this.valuationVisible = false;
                } else {
                  this.$message({
                    type: 'warning',
                    message: 'Create failure'
                  })
                }
              });
            }
          } else {
            console.log('err submit')
          }
        })
      },
      deleteComparable(cid){

        axioss.deleteComparable(cid).then(res => {

          if (res.data.code == "SUCCESS") {
            var obj = {};
            obj.pid = this.valuationForm.portfolioid;
            obj.valueDate = this.valuationForm.valuationdate;
            this.reqCompareList(obj);
          } else {
            this.$message({
              type: 'warning',
              message: 'Delete failure'
            })
          }
        })
      },
      deleteValuation(index, rowdata){

        axioss.deleteValuation(rowdata.valuationid).then(res => {
          if (res.data.code == "SUCCESS") {
            this.searchData()
          } else {
            this.$message({
              type: 'warning',
              message: 'Delete failure'
            })
          }
        })
      },

      editValuation(index, rowdata){

        this.fetchFundName();
        this.reqfundproList();
        this.reqValueMethod();

        axioss.readValuation(rowdata.valuationid).then(res => {
          if (res.data.code == "SUCCESS") {
            this.isEdit = true;
            Object.assign(this.valuationForm, res.data.data);
            this.fairvalue = this.valuationForm.fairvalue;
            this.netvaluation = this.valuationForm.netvaluation;
            this.multiple = this.valuationForm.multiple;


            if (res.data.data.valuationmethod == 'PE' || res.data.data.valuationmethod == 'PS' || res.data.data.valuationmethod == 'PB') {
              this.valuationTableData = res.data.data.portfoliocomparables;
              this.isShow = true;
              this.reqIndiction();
            } else if (res.data.data.valuationmethod == 'EV') {
              this.valuationTableData = res.data.data.portfoliocomparables;
              this.isShow = true;
              this.isEvShow = true;
              this.reqIndiction();
            }

            this.valuationVisible = true;


          } else {
            this.$message({
              type: 'warning',
              message: 'Read failure'
            })
          }
        })
      },


      handleAdd(){
        this.isEdit = false;
        this.valuationForm.fundid = this.valuationFormData.fundIds;
        this.valuationVisible = true;
        this.fetchFundName();
        this.reqfundproList();
        this.reqValueMethod();
      },
      handleCurrentChange(){
        this.searchData();
      },
      fetchFundName(){
        var len = this.checkList.length;
        for (var i = 0; i < len; i++) {
          if (this.checkList[i].fundid == this.valuationFormData.fundIds) {
            this.fundname = this.checkList[i].fundname;
          }
        }
      },
      whichShow(val){
        this.isShow = false;
        this.isEvShow = false;
        if (val == 'PE' || val == "PS" || val == "PB") {
          this.isShow = true;
          this.reqIndiction();
          this.reqCompareList(this.reqcompanyobj);
        } else if (val == "LASTROUND") {
          this.queryLastLRound();
        }
        if (val == 'EV') {
          this.isShow = true;
          this.isEvShow = true;
          this.reqIndiction();
          this.reqCompareList(this.reqcompanyobj);
        }
      },
      calculateMultiple(){
        var tem = this.valuationForm.valuationmethod;
        var data = this.valuationTableData, len = data.length, multiple = 0;
        for (var i = 0; i < len; i++) {
          multiple += data[i].multiple * data[i].weight;
        }
        this.multiple = multiple;
      },
      linkto(index, data){
        var method = data.valuationmethod;
        if (method == 'OPM') {
          window.open(window.location.origin + '/#/valuationreportopm?valuationid=' + data.valuationid)
          //this.$router.push({path: 'valuationreportopm', query: {valuationid: data.valuationid}});
        } else if (method == 'PS' || method == 'PE' || method == 'PB') {
          window.open(window.location.origin + '/#/valuationreport?valuationid=' + data.valuationid)
          //this.$router.push({path: 'valuationreport', query: {valuationid: data.valuationid}});
        } else if (method == 'EV') {
          window.open(window.location.origin + '/#/valuationreportev?valuationid=' + data.valuationid)
          //this.$router.push({path: 'valuationreport', query: {valuationid: data.valuationid}});
        } else {
          this.$message({
            showClose: true,
            message:'no corresponding report for the record',
            type: 'warning'
          });
        }
      }
    },
    watch: {
      multiple: function (newdata, olddata) {
        //this.multiple =  newdata.toFixed(2);
        var method = this.valuationForm.valuationmethod;
        if (method == 'EV') {
          this.fairvalue = (newdata * this.baseValue + this.valuationForm.cashassets * 1 - this.valuationForm.debt * 1 + this.optionproceed * 1 - this.preferstockprefer).toFixed(2);
        } else {
          this.fairvalue = (newdata * this.baseValue).toFixed(2);
        }
      },
      optionproceed: function (newdata, olddata) {
        //this.multiple =  newdata.toFixed(2);
        var method = this.valuationForm.valuationmethod;
        if (method == 'EV') {
          this.fairvalue = (this.multiple * this.baseValue + this.valuationForm.cashassets * 1 - this.valuationForm.debt * 1 + newdata * 1 - this.preferstockprefer * 1).toFixed(2);
        }
      },
      preferstockprefer: function (newdata, olddata) {
        //this.multiple =  newdata.toFixed(2);
        var method = this.valuationForm.valuationmethod;
        if (method == 'EV') {
          this.fairvalue = (this.multiple * this.baseValue + this.valuationForm.cashassets * 1 - this.valuationForm.debt * 1 + this.optionproceed * 1 - newdata * 1).toFixed(2);
        }
      },
      baseValue: function (newdata, olddata) {
        var method = this.valuationForm.valuationmethod;
        if (method == 'EV') {
          this.fairvalue = (newdata * this.multiple + this.valuationForm.cashassets * 1 - this.valuationForm.debt * 1 + this.optionproceed * 1 - this.preferstockprefer * 1).toFixed(2);
        } else {
          this.fairvalue = (newdata * this.multiple).toFixed(2);
        }
      },
      valuationVisible: function () {/* 有没有弹窗时都自动清空表单 */

      }
    },
    computed: {
      portfolioid: function () {
        if (this.$store.state.portfolioid == '') {
          this.$store.dispatch('updateData');
        }
        return this.$store.state.portfolioid
      },
      netvaluation: {
        get: function () {
          var tem = this.valuationForm.valuationmethod, val = '';
          var fairvalue = this.fairvalue, ownerShip = this.valuationForm.prop,
            discount = this.valuationForm.discount, additional = this.valuationForm.additional,
            fin48tax = this.valuationForm.fin48tax, multiple = this.valuationForm.multiple,
            loan = this.valuationForm.loan;
          val = (this.valuationForm.valuation * 1 + parseInt(additional) * 1 ) * (1 - discount / 100) - fin48tax * 1 + loan * 1;

          return val.toFixed(2);
        },
        set: function () {
        }
      },
      isComp(){
        var tem = this.valuationForm.valuationmethod;
        if (tem == 'PE' || tem == "PS" || tem == "PB" || tem == 'EV' || tem == 'LASTROUND' || tem == 'COST') {
          return true;
        } else {
          return false;
        }
      },

      valuation: {
        get: function () {
          let val = "0";
          var tem = this.valuationForm.valuationmethod;

          if (tem == 'PE' || tem == "PS" || tem == "PB" || tem == 'EV' || tem == 'LASTROUND' || tem == 'COST') {
            this.valuationForm.valuation = val = this.fairvalue * this.valuationForm.prop;
          }
          return parseInt(val);
        },
        set: function (newValue) {
          return newValue;
        }

      },
      reqcompanyobj(){
        var obj = {};
        obj.valueDate = this.valuationForm.valuationdate;
        obj.pid = this.valuationForm.portfolioid;
        return obj;
      }
    },
    components: {
      Header
    }
  }
</script>
<style scoped lang="scss">
  .valuation {
    width: 95%;
    margin: 0 auto;
  }

  .valuation-bottom {
    border-left: 1px solid #eee;
    border-right: 1px solid #eee;
  }

  .valuation .h3 {
    font-size: 16px;
    padding: 5px 0;
    padding-left: 10px;
    background: #eee;
    margin: 0;
    border-bottom: 1px solid #ebeef5;
  }

  .el-pagination {
    text-align: center;
  }
</style>
