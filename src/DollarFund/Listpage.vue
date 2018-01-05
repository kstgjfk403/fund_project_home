
<template>
    <div class="listpage">
        <Header></Header>
        <div class="wrapper-container">
            <div class="button-container"  style="margin:0 auto;margin-top:10px;">
                <el-button type="primary" @click="linkto('addproject')">New</el-button>
            </div>
            <div class="position-container">
                <div class="table-responsive">
                    <table class="table table-hover table-bordered" style="table-layout:fixed;">
                        <thead>
                            <tr>
                            <th scope="col">Fund Type    
                                <el-input placeholder="请输入Fund Type" v-model="fundData.fundtypeid"></el-input>
                            </th>
                            <th scope="col">Fund Family Name
                                
                                <el-input class="search" placeholder="请输入Fund Family Name" v-model="fundData.fundfamillyname" @input.native="searchData"></el-input>
                            </th>
                            <th scope="col">Fund Abbr. Name
                                <el-input class="search" placeholder="请输入Fund Abbr. Name" v-model="fundData.fundname" @input.native="searchData"></el-input>
                                <!-- <input @input="searchData"> -->
                            </th>
                            <th scope="col">Fund Full Name
                                <el-input class="search" placeholder="请输入Fund Full Name" v-model="fundData.fundfullnameeng" @input.native="searchData"></el-input>
                                <!-- <input @input="searchData"> -->
                            </th>
                            <th scope="col">基金全名
                                <el-input class="search" placeholder="请输入基金全名" v-model="fundData.fundfullnamechi" @input.native="searchData"></el-input>
                                
                            </th>
                            <!-- <th scope="col">Registered Address
                                <el-input class="search" placeholder="请输入Registered Address" v-model="fundData.regaddress" @input.native="searchData"></el-input>
                            </th> -->
                            <!-- <th scope="col">Industry focus
                                <el-input class="search" placeholder="请输入Industry focus" v-model="fundData.mainindustry" @input.native="searchData"></el-input>                               
                            </th> -->
                            
                            <!-- <th scope="col">Incorporated In                        
                                <el-select placeholder="请选择" v-model="fundData.incorplocation"  clearable filterable @change="selectchange">
                                <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                </el-option>    
                                </el-select>
                            </th> -->
                            <!-- <th scope="col">Financial Year End
                                <el-input class="search" placeholder="请输入Financial Year End" v-model="fundData.yearend" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>                               
                            </th> -->
                            
                            <!-- <th scope="col">Total LP Commitment
                                <el-input class="search" placeholder="请输入Total LP Commitment" v-model="fundData.totallpcommitnum" @input.native="searchData"></el-input>                        
                            </th> -->
                            <!-- <th scope="col">Total GP Commitment
                                <el-input class="search" placeholder="请输入Total GP Commitment" v-model="fundData.totalgpcommitnum" @input.native="searchData"></el-input>  
                            </th> -->
                            <th scope="col" style="width:185px;">LP+GP Commitment
                                <el-input class="search" placeholder="请输入Programe Name" v-model="fundData.totalcommitmentnum" @input.native="searchData"></el-input>                        
                            </th>
                            <!--<th scope="col">Formation Date
                                <el-input class="search" placeholder="请输入Programe Name" v-model="fundData.formationdate" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>  
                            </th>
                            <th scope="col">Fund Closing Date
                                <el-input class="search" placeholder="请输入Programe Name" v-model="fundData.closedate" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
                            </th> -->
                            <th scope="col" style="width:240px;">Management Fee Structure
                                <el-input class="search" placeholder="请输入Management Fee Structure" v-model="fundData.managfeedesc" @input.native="searchData"></el-input>                        
                            </th>                            
                            <th scope="col" style="width:240px;">Capital Allocations Desc
                                <el-input class="search" placeholder="请输入Management Fee Structure" v-model="fundData.capitalallocationsdesc" @input.native="searchData"></el-input>                        
                            </th>
                            
                            <th scope="col">GP Name
                                <el-input class="search" placeholder="请输入Management Fee Structure" v-model="fundData.gpname" @input.native="searchData"></el-input>                        
                            </th>
                            <!-- <th scope="col">Fund Currency
                                <el-select placeholder="请选择" v-model='fundData.totalcommitmentcur' clearable @change="selectchange">
                                <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                </el-option>    
                                </el-select>
                            </th> -->
                            <th scope="col">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="item in alllistData">
                            <td scope="row">{{item.fundtypeid}}</td>
                            <td>{{item.fundfamillyname}}</td>
                            <td>{{item.fundname}}</td>
                            <td>{{item.fundfullnameeng}}</td>
                            <td>{{item.fundfullnamechi}}</td>
                            <!-- <td>{{item.regaddress}}</td> -->
                            <!-- <td>{{item.mainindustry}}</td> -->
                            <!-- <td>{{item.incorplocation}}</td> -->
                            <!-- <td>{{item.yearend}}</td> -->
                            <!-- <td>{{item.totallpcommitnum}}</td> -->
                            <!-- <td>{{item.totalgpcommitnum}}</td> -->
                            <td>{{item.totalcommitmentnum}}</td>
                            <!-- <td>{{item.formationdate}}</td>
                            <td>{{item.closedate}}</td> -->
                            <td>{{item.managfeedesc}}</td>
                            <td>{{item.capitalallocationsdesc}}</td>
                            <td>{{item.gpname}}</td>
                            <!-- <td>{{item.totalcommitmentcur}}</td> -->
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
export default {
  name: "fundlistpage",
  data: function() {
    return {
        //   bankname:'',
        //   bankaddress:'',
        //   bankaccount:'',
        //   swiftcode:'',
        //   auditor:'',
        //   legaladvisor:'',
        //   taxrepresentative:'',
        //   magtcompany:'',
        //   magtcomincorporated:'',
        //   taxid:''
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
            //regaddress:'',
            //mainindustry:'',
            //incorplocation:'',
            //yearend:'',
            //totallpcommitnum:'',
            //totalgpcommitnum:'',
            totalcommitmentnum:'',
            //totalcommitmentcur:'',
            managfeedesc:'',
            //formationdate:'',
            //closedate:'',
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
    //this.fetchperStatus();
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
      //console.log(this.fundData);
      axioss.reqmemberlist2(obj)
        .then(res => {
          //console.log(res);
          that.alllistData = res.data.data;
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
        console.log(this.fundData)
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
    ifdeletproject(fundid) {
        this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
        }).then(() => {
          axioss.reqdelet2(fundid).then(res => {
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
  width:175px;
  text-align:center;
}
.table tr th:last-child {
  width:130px;
}
.table tr td {
  width: 175px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
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
    width:85%;margin:0 auto;
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
    width:85%;
}
[class^=el-icon-]{
    margin-left:8px;
}
</style>
