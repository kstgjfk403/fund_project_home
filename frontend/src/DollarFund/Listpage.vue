
<template>
  <div class="listpage">
    <Header></Header>
    <div class="wrapper-container">
      <div class="button-container" style="margin:0 auto;margin-top:10px;">
        <el-button type="primary" @click="linkto('addproject')">New</el-button>
      </div>
      <div class="position-container">
        <div class="table-responsive">
          <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;">
            <thead>
              <tr>
              <th scope="col">Fund Type    
                  <el-input v-model="fundData.fundtypeid" @input.native="searchData"></el-input>
              </th>
              <th scope="col">Fund Family Name 
                <el-input class="search" v-model="fundData.fundfamillyname" @input.native="searchData"></el-input>
              </th>
              <th scope="col">Fund Abbr. Name
                <el-input class="search" v-model="fundData.fundname" @input.native="searchData"></el-input>
              </th>
              <th scope="col">Fund Full Name
                <el-input class="search" v-model="fundData.fundfullnameeng" @input.native="searchData"></el-input>
              </th>
              <th scope="col">基金全名
                <el-input class="search" v-model="fundData.fundfullnamechi" @input.native="searchData"></el-input>
              </th>
              <th scope="col" style="width:185px;">
                LP+GP Commitment
              </th>
              <th scope="col" style="width:240px;">Management Fee Structure
                <el-input class="search" v-model="fundData.managfeedesc" @input.native="searchData"></el-input>
              </th>                            
              <th scope="col" style="width:240px;">Capital Allocations Desc
                <el-input class="search" v-model="fundData.capitalallocationsdesc" @input.native="searchData"></el-input>                        
              </th> 
              <th scope="col">GP Name
                <el-input class="search" v-model="fundData.gpname" @input.native="searchData"></el-input>                        
              </th>
              <th scope="col">Opt</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in alllistData">
              <td scope="row">{{item.fundtypeid}}</td>
              <td>{{item.fundfamillyname}}</td>
              <td>{{item.fundname}}</td>
              <td>{{item.fundfullnameeng}}</td>
              <td>{{item.fundfullnamechi}}</td>                            
              <td>{{item.totalcommitmentnum}}</td>               
              <td>{{item.managfeedesc}}</td>
              <td>{{item.capitalallocationsdesc}}</td>
              <td>{{item.gpname}}</td>                           
              <td>
                <i class="el-icon-info" @click="linkto('details',item.fundid)"></i>                            
                <i class="el-icon-edit" @click="linkto('editor',item.fundid)"></i>
                <i class="el-icon-delete" @click="ifdeletproject(item.fundid)"></i>
              </td>
              </tr>   
            </tbody>
          </table>
          <div class="pagination-container">
            <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :current-page.sync="pageCurrent" @current-change="handleCurrentChange" style="position:absolute;left:50%;bottom:25px;transform: translate(-50%);"></el-pagination>
          </div>
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
  name: "fundlistpage",
  data: function() {
    return {
      portfolioid: "",
      pageCurrent: 1,
      pageCount: 10,
      allcount: 0,
      alllistData:'',
      fundData:{
        fundid:'',
        fundfamillyname:'',
        fundname:'',
        fundfullnameeng:'',
        fundfullnamechi:'',            
        totalcommitmentnum:'',            
        managfeedesc:'',
        capitalallocationsdesc:'',
        gpname:''
      },
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
      this.fundData.pageIndex=pageIndex;
      this.fundData.pageCount=pageCount;
      axioss.reqmemberlist2(obj).then(res => {
        var trans=res.data.data;
        for(var i=0;i<trans.length;i++){
          var num=trans[i].totalcommitmentnum;
          trans[i].totalcommitmentnum=method.toThousands(num);
        }
        that.alllistData = trans;
        this.allcount=res.data.count;
      })
      .catch(err => {
        console.log(err);
      });
    },
    handleCurrentChange() {
      this.submitpage(this.pageCurrent, this.pageCount);
    },
    linkto(path, id) {
      if (path == "details") {
        this.$router.push({ path: "funddetail", query: { fundid: id } });
      } else if (path == "editor") {
        this.$router.push({ path: "fundaddproject", query:{fundid:id,path:'editor'}})
      } else if (path == "addproject") {
        this.$router.push({ path: "fundaddproject",query:{path:'add'}});
      }
    },
    searchData() {
      var that = this;
      axioss.reqmemberlist2(this.fundData)
      .then(res => {
        that.alllistData = res.data.data;
        this.allcount=res.data.count;
      })
      .catch(err => {
        console.log(err);
      });
    },
    selectchange() {  
      var that = this;
      axioss.reqmemberlist2(this.fundData)
        .then((res) => {
          that.alllistData = res.data.data;
          this.allcount=res.data.count;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    ifdeletproject(fundid) {
        this.$confirm("此Opt将永久删除该文件, 是否继续?", "提示", {
          confirmButtonText: "OK",
          cancelButtonText: "Cancel",
          type: "warning"
        }).then(() => {
          axioss.reqdelet2(fundid).then(res => {
            var status = res.data.code;
            if (status.toLocaleLowerCase() == "success") {
              this.handleCurrentChange();//Update列表,页码
              this.$message({
                type: "success",
                message: " Delete success!"
              });
            } else {
              this.$message({
                type: "error",
                message: "Delete failure!"
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "Cancel deleting"
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
