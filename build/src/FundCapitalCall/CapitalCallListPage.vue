
<template>
  <div class="listpage">
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
                <!-- <input @input="searchData"> -->
              </th>
              <th scope="col">Fund Full Name
                <el-input class="search" placeholder="请输入Fund Full Name" v-model="capitalCallData.fundfullnameeng" @input.native="searchData"></el-input>
                <!-- <input @input="searchData"> -->
              </th>
              <th scope="col">基金全名
                <el-input class="search" placeholder="请输入基金全名" v-model="capitalCallData.fundfullnamechi" @input.native="searchData"></el-input>
              </th>
              <th scope="col">Capital Call ID</th>
              <th scope="col">Call Order</th>
              <th scope="col">Called Date</th>
              <th scope="col">Called Pecentage</th>
              <th scope="col">Called Purpose</th>
              <th scope="col">Due Date</th>

            <!--  <th scope="col" style="width:185px;">LP+GP Commitment
                &lt;!&ndash; <el-input class="search" placeholder="请输入Programe Name" v-model="capitalCallData.totalcommitmentnum" @input.native="searchData"></el-input>&ndash;&gt;
              </th>

              <th scope="col" style="width:240px;">Management Fee Structure
                <el-input class="search" placeholder="请输入Management Fee Structure" v-model="capitalCallData.managfeedesc" @input.native="searchData"></el-input>
              </th>
              <th scope="col" style="width:240px;">Capital Allocations Desc
                <el-input class="search" placeholder="请输入Management Fee Structure" v-model="capitalCallData.capitalallocationsdesc" @input.native="searchData"></el-input>
              </th>

              <th scope="col">GP Name
                <el-input class="search" placeholder="请输入Management Fee Structure" v-model="capitalCallData.gpname" @input.native="searchData"></el-input>
              </th>-->
              <th scope="col">操作</th>
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
              <!--<td>{{item.totalcommitmentnum}}</td>
              <td>{{item.managfeedesc}}</td>
              <td>{{item.capitalallocationsdesc}}</td>
              <td>{{item.gpname}}</td>-->
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
          </el-table>


        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import axioss from "../api/axios";
  import Header from "../components/common/Header";
  import * as method from '@/api/method';
  export default {
    name: "capitalcalllistpage",
    data: function() {
      return {
        showColumn:false,
        showPartColumn:true,
        portfolioid: "",
        pageCurrent: 1,
        pageCount: 10,
        allcount: 0,
        alllistData:'',
        capitalCallData:{
          fundfamillyname:'',
          fundname:'',
          fundfullnameeng:'',
          fundfullnamechi:'',
          calleddatestart:'',
          calleddateend:'',

        },
        capitalCallLpSearchData:{
          callid:'',


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
            console.log("临");
            console.log(res.data);
            this.allDetailData = trans;
            console.log(trans);
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
      // fetchperStatus() {
      //     var that=this;
      //     axioss.reqdroplist({dictArray:"IDG_Staff,DDL_PortfolioStatus"})
      //     .then((res)=>{
      //         this.staffs=res.data.data[0].baseInfoList;
      //         this.portfoliostatus=res.data.data[1].baseInfoList;
      //     })
      //     .catch(function(err) {
      //         console.log(err);
      //     });
      // },
      ifdeletecapitalcall(callid) {
        this.$confirm("Confirm to delete this Call, continue?", "Warning", {
          confirmButtonText: "confirm",
          cancelButtonText: "cancel",
          type: "warning"
        }).then(() => {
          axioss.reqdeletcapitalcall(callid).then(res => {
            var status = res.data.code;
            if (status.toLocaleLowerCase() == "success") {
              this.handleCurrentChange();//更新列表,页码
              this.$message({
                type: "success",
                message: "删除成功!"
              });
            } else {
              this.$message({
                type: "error",
                message: "删除失败!"
              });
            }
          });
        })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });
      }
    }
  };
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
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

  .el-button--mini {
    width: 48px;
    margin-left: 0;
    margin-right: 5px;
    padding: 7px 12px;
  }
  .button-container{
    /* border:1px solid #ddd; */
    border-bottom:none;
    width:95%;
  }
  [class^=el-icon-]{
    margin-left:8px;
  }
</style>
