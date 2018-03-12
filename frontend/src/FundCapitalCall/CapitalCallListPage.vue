
<template>
  <div class="loan capitalcall listpage">
    <Header></Header>
    <div class="wrapper-container">
      <div class="button-container" style="margin:0 auto;margin-top:10px;">
        <el-button type="primary" @click="linkto('addCapitalCall')">New</el-button>
      </div>
      <div class="position-container">
        <div class="table-responsive">
          <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;">
            <thead>
            <tr>
              <th scope="col" style="display:none">Fund Type
                <el-input v-model="capitalCallData.fundtypeid" @input.native="searchData"></el-input>
              </th>
              <th scope="col">Fund Family Name
                <el-input class="search" v-model="capitalCallData.fundfamillyname" @input.native="searchData"></el-input>
              </th>
              <th scope="col">Fund Abbr. Name
                <el-input class="search" v-model="capitalCallData.fundname" @input.native="searchData"></el-input>
              </th>
              <th scope="col">Fund Full Name
                <el-input class="search" placeholder=" Fund Full Name" v-model="capitalCallData.fundfullnameeng" @input.native="searchData"></el-input>
              </th>
              <th scope="col">基金全名
                <el-input class="search" placeholder="  " v-model="capitalCallData.fundfullnamechi" @input.native="searchData"></el-input>
              </th>
              <th scope="col">Capital Call ID</th>
              <th scope="col">Call Order</th>
              <th scope="col">Called Date</th>
              <th scope="col">Called Pecentage</th>
              <th scope="col">Called Purpose</th>
              <th scope="col">Due Date</th>
              <th scope="col">Edit</th>
            </tr>
            </thead>
            <tbody>
              <tr v-for="item in alllistData">
                <td scope="row" style="display:none">{{item.fundtypeid}}</td>
                <td>{{item.fundFamillyName}}</td>
                <td>{{item.fundName}}</td>
                <td>{{item.fundFullNameEng}}</td>
                <td>{{item.fundFullNameChi}}</td>
                <td>{{item.callID}}</td>
                <td>{{item.callOrder}}</td>
                <td>{{item.calledDate}}</td>
                <td>{{item.calledPecentage}}</td>
                <td>{{item.calledPurpose}}</td>
                <td>{{item.dueDate}}</td>
                <td>
                  <i class="el-icon-info" @click="showdetail(item.callID)"></i>
                  <i class="el-icon-edit" @click="linkto('editor',item.callID)"></i>
                  <i class="el-icon-delete" @click="ifdeletecapitalcall(item.callID)"></i>
                </td>
              </tr>
            </tbody>
          </table>
          <div class="pagination-container">
            <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :current-page.sync="pageCurrent" @current-change="handleCurrentChange" style="position:absolute;left:50%;bottom:25px;transform: translate(-50%);"></el-pagination>
          </div>
        </div>
      </div>
      <div class="position-container">
        <div class="table-responsive">
          <el-table :data="allDetailData" border style="width: 100%" maxHeight="850">
            <el-table-column  prop="LPLegalFullName" label="LP(GP) LegalFullName" width="180" >
            </el-table-column>
            <el-table-column  prop="callDetailId" label="Call Detail ID" width="400"  v-if="showColumn">
            </el-table-column>
            <el-table-column  prop="calledNum" label="Called Num" width="100">
            </el-table-column>
            <el-table-column  prop="receiveNum" label="Receive Num" width="120">
            </el-table-column>
            <el-table-column  prop="emailSendStr" label="Email Send" width="100">
            </el-table-column>
            <el-table-column  prop="emailSendDateTimeStr" label="Email Send Date Time" width="180">
            </el-table-column>
            <el-table-column  prop="receiveDate" label="Receive Date" width="120">
            </el-table-column>
            <el-table-column  prop="erisaStr" label="ERISA" width="60">
            </el-table-column>
            <el-table-column  prop="emailTo" label="Email To" width="150">
            </el-table-column>
            <el-table-column  prop="emailBC" label="Email BC" width="120">
            </el-table-column>
            <el-table-column  prop="emailCC" label="Email CC" width="220" show-overflow-tooltip>
            </el-table-column>
            <el-table-column  prop="subject" label="Subject" width="150">
            </el-table-column>
            <el-table-column  prop="emailContent" label="Email Content" width="150" v-if="showColumn">
            </el-table-column>
            <el-table-column  prop="attachment" label="ttachment" width="150" v-if="showColumn">
            </el-table-column>
            <el-table-column  prop="emailReviewedStr" label="Email Reviewed" width="120">
            </el-table-column>
            <el-table-column  prop="totalCommentNum" label="Total Comment Num" width="166">
            </el-table-column>
            <el-table-column label="Edit" width="70" fixed='right'>
              <template slot-scope="scope">
                <i class="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></i>
                <i class="el-icon-delete" @click="handleDelet(scope.$index, scope.row)"></i>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </div>
    <el-dialog title="capitalCall Information" :visible.sync="capitalCallVisible">
      <div class="select-container">
      <el-form :model="capitalCallForm" ref="capitalCallForm">
        <div class="select-fixed">
          <!-- <el-form-item label="Investor:">
          <el-select v-model="capitalCallForm.directortype" placeholder="  ">
            <el-option v-for="item in 5" :key="item.baseId"
            :label="item.baseName" :value="item.baseId"></el-option>
          </el-select>
          </el-form-item> -->
          <el-form-item label="LegalFullName:">
            <el-input v-model="capitalCallForm.lpLegalFullName"></el-input>
          </el-form-item>
          <el-form-item label="Called Num:">
            <el-input v-model="capitalCallForm.callednum"></el-input>
          </el-form-item>
          <el-form-item label="Receive Amount:">
            <el-input v-model="capitalCallForm.receivenum"></el-input>
          </el-form-item>
          <el-form-item label="Email Send:">
            <el-select v-model="capitalCallForm.emailsend" placeholder="  ">
              <el-option v-for="item in options" :key="item.value"
              :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="emailto:">
            <el-input v-model="capitalCallForm.emailto"></el-input>
          </el-form-item>
          <el-form-item label="emailbc:">
            <el-input v-model="capitalCallForm.emailbc"></el-input>
          </el-form-item>
          <el-form-item label="emailcc:">
            <el-input v-model="capitalCallForm.emailcc"></el-input>
          </el-form-item>
          <el-form-item label="emailcontent:">
            <el-input v-model="capitalCallForm.emailcontent"></el-input>
          </el-form-item>
          <el-form-item label="emailreviewed:">
            <el-select v-model="capitalCallForm.emailreviewed" placeholder="  ">
              <el-option v-for="item in options" :key="item.value"
              :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Email Send Date:">
            <el-date-picker v-model="capitalCallForm.emailsenddatetime" type="date" placeholder="select Date">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="ERISA:">
            <el-select v-model="capitalCallForm.erisa" placeholder="  ">
              <el-option v-for="item in options" :key="item.value"
              :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="moneyreceived:">
            <el-select v-model="capitalCallForm.moneyreceived" placeholder="  ">
              <el-option v-for="item in options" :key="item.value"
              :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Subject:">
            <el-input v-model="capitalCallForm.subject"></el-input>
          </el-form-item>
          <el-form-item label="Receive Date:">
            <el-date-picker v-model="capitalCallForm.receivedate" type="date" placeholder="select Date">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="Total Comment:">
            <el-input v-model="capitalCallForm.totalcommentnum"></el-input>
          </el-form-item>
        </div>
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="capitalCallVisible = false" size="mini">Cancel</el-button>
        <el-button type="primary" @click="submitForm('capitalCallForm','add')" size="mini" v-if="buttonShow=='Add'">Add</el-button>
        <el-button type="primary" @click="submitForm('capitalCallForm','update')" size="mini" v-else>Update</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import axioss from "../api/axios";
  import Header from "../components/common/Header";
  import * as method from '@/api/method';
  import mix from '@/api/mixin';
  export default {
    name: "capitalcalllistpage",
    mixins:[mix],
    data: function() {
      return {
        buttonShow:'',
        showColumn:false,
        showPartColumn:true,
        portfolioid: "",
        pageCurrent: 1,
        pageCount: 10,
        allcount: 0,
        alllistData:'',
        capitalCallVisible:false,
        capitalCallForm:{
          lpLegalFullName:'',
          callednum:'',
          emailbc:'',
          emailcc:'',
          emailcontent:'',
          emailreviewed:'',
          emailsend:'',
          emailsenddatetime:'',
          emailto:'',
          erisa:'',
          moneyreceived:'',
          receivedate:'',
          receivenum:'',
          subject:'',
          totalcommentnum:''
        },
        capitalCallFormEmpty:{
          
        },
        capitalCallData:{
          lpLegalFullName:'',
          callednum:'',
          emailbc:'',
          emailcc:'',
          emailcontent:'',
          emailreviewed:'',
          emailsend:'',
          emailsenddatetime:'',
          emailto:'',
          erisa:'',
          moneyreceived:'',
          receivedate:'',
          receivenum:'',
          subject:'',
          totalcommentnum:''
        },
        capitalCallLpSearchData:{
          callid:'',
          callDetailId:'',
        },

        allDetailData:[],

        options: [
          {
            value: true,
            label: "yes"
          },
          {
            value: false,
            label: "no"
          }
        ]
      }
    },
    mounted: function() {
      this.submitpage(this.pageCurrent, this.pageCount);
    },
    components: {
      Header
    },
    methods: {
      submitpage: function(pageIndex, pageCount) {
        var that = this;
        var obj={pageIndex:pageIndex,pageCount:pageCount}
        this.capitalCallData.pageIndex=pageIndex;
        this.capitalCallData.pageCount=pageCount;
        axioss.reqmemberlistcapitalcall(this.capitalCallData)
          .then(res => {
            var trans=res.data.data;
            for(var i=0;i<trans.length;i++){
              var num=trans[i].totalcommitmentnum;
              trans[i].totalcommitmentnum=method.toThousands(num);
            }
            that.alllistData = trans;
            this.allcount=res.data.count;

          })
          .catch(err => {
          });
      },
      handleCurrentChange() {
        this.submitpage(this.pageCurrent, this.pageCount);
      },
      linkto(path, id) {
        if (path == "details") {
          this.$router.push({ path: "capitalcalldetail", query: { callid: id } });
        } else if (path == "editor") {
          this.$router.push({ path: "capitalcalladdpage", query:{callid:id,path:'editor'}})
        } else if (path == "addCapitalCall") {
          this.$router.push({ path: "capitalcalladdpage",query:{path:'add'}});
        }
      },
      showdetail(id){
        this.capitalCallLpSearchData.callid = id;
        axioss.reqmemberlistcapitalcalllp(this.capitalCallLpSearchData)
          .then(res => {
            var trans=res.data.data;
            this.allDetailData = trans;
          })
          .catch(err => {
          });
      },
      searchData() {
        var that = this;
        axioss.reqmemberlistcapitalcall(this.capitalCallData)
          .then(res => {

            that.alllistData = res.data.data;
            this.allcount=res.data.count;
          })
          .catch(err => {
          });
      },
      selectchange() {
        var that = this;
        axioss.reqmemberlistcapitalcall(this.capitalCallData)
          .then((res) => {
            that.alllistData = res.data.data;
            this.allcount=res.data.count;
          })
          .catch((err) => {
          });
      },
      ifdeletecapitalcall(callid) {
        this.$confirm("Confirm to delete this Call, continue?", "Warning", {
          confirmButtonText: "confirm",
          cancelButtonText: "cancel",
          type: "warning"
        }).then(() => {
          axioss.reqdeletcapitalcall(callid).then(res => {
            var status = res.data.code;
            if (status.toLocaleLowerCase() == "success") {
              this.handleCurrentChange();//Update列表,页码
              this.$message({
                type: "success",
                message: "Delete success!"
              });
            } else {
              this.$message({
                type: "error",
                message: "Delete Failure!"
              });
            }
          });
          }).catch(() => {
          this.$message({
            type: "info",
            message: "Cancel Delete"
          });
        });
      },
      submitForm(formName,type){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var obj=this.capitalCallForm;
            if(type=='update'){
              axioss.updateCapitalCallLp(obj).then(res=>{
                let status=res.data.code,succMes='Update success',failMes='Update failure';
                let stateCode=this.showToast(status,succMes,failMes);
                if(stateCode){
                  this.capitalCallVisible=false;
                  Object.assign(this.capitalCallForm,this.capitalCallFormEmpty);
                  this.showdetail(this.capitalCallLpSearchData.callid);
                }
              })
            }else{
              obj.portfolioid=this.portfolioid;
              axioss.addDirector(obj).then(res=>{
                let status=res.data.code,succMes='Create success',failMes='Create failure';
                let stateCode=this.showToast(status,succMes,failMes);
                if(stateCode){
                  this.capitalCallVisible=false;
                  Object.assign(this.capitalCallForm,this.capitalCallFormEmpty);
                  
                }
              })
            }
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      handleEdit(index, row){

        this.capitalCallVisible=true;
        this.buttonShow='';
        //this.capitalCallLpSearchData.callDetailId = row.callDetailId;
        console.log(row);
        axioss.reqdetailscapitalcalllp(row.callDetailID)
          .then(res => {
            var trans=res.data.data;
            console.log(res.data);
            this.capitalCallForm = trans;
            console.log(trans);
          })
          .catch(err => {
          });
      },
      handleDelet(){

      }
    }
  };
</script>
<style scoped>
  .top {
    margin-top: 50px;
  }
  .el-pagination {
    text-align: center;
    padding: 10px 0;
  }
  .table tr th {
    vertical-align: top;
    width:166px;
    text-align:center;
  }
  .table tr th:last-child {
    width:130px;
  }
  .table tr td {
    width: 166px;
    white-space: wrap;
    overflow: hidden;
    color:#666;
  }
  .table tr td:last-child{
    text-align: center;
  }
  .table tr th,.table tr td{
    border-color:#ddd;
  }
  .pagination-container{
    width:100%;
    height:50px;
  }
  .position-container{
    width:95%;margin:0 auto;
    position:relative;
  }
  .el-button--primary {
    margin: 10px 0;
    background-image: linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);
  }

  /* .el-button--mini {
    width: 48px;
    margin-left: 0;
    margin-right: 5px;
    padding: 7px 12px;
  } */
  .button-container{
    /* border:1px solid #ddd; */
    border-bottom:none;
    width:95%;
  }
  [class^=el-icon-]{
    margin-left:8px;
  }
</style>
