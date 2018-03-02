<template>
<div class="financial loan lpinfo" style="margin-top:0;">
    <div class="title">LP Information</div>
    <div class="loan-table-container">
      <h3>LP Information</h3>
      <div class="table-responsive">
      <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;">
        <thead>
          <tr>
          <th scope="col">LP ID
            <el-input placeholder="请输入Programe Name" @input.native="searchData('abbname',abbnameval)"></el-input>
          </th>
          <th scope="col">LP Group
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('abbnamechi',abbnamechival)"></el-input>
          </th>
          <th scope="col">Investor Legal Name
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullname',fullnameval)"></el-input>
          </th>
          <th scope="col">Investor Type
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">investorTypeID
            <el-select v-model="LPInfoFrom.currency" placeholder="请选择" clearable @change="searchData('portfoliostatus', projectmanagerval)">
              <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
                :value="item.baseId"></el-option>
            </el-select>
          </th>
          <th scope="col">Partner Type
            <el-select v-model="LPInfoFrom.currency" placeholder="请选择" clearable @change="searchData('portfoliostatus', projectmanagerval)">
              <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
                :value="item.baseId"></el-option>
            </el-select>
          </th>
          <th scope="col">Quit
            <el-select v-model="LPInfoFrom.currency" placeholder="请选择" clearable @change="searchData('portfoliostatus', projectmanagerval)">
              <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
                :value="item.baseId"></el-option>
            </el-select>
          </th>
          <th scope="col">Quit Date
            <el-select v-model="LPInfoFrom.currency" placeholder="请选择" clearable @change="searchData('portfoliostatus', projectmanagerval)">
              <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
                :value="item.baseId"></el-option>
            </el-select>
          </th>
          <th scope="col">Investor Geo
            <el-select v-model="LPInfoFrom.currency" placeholder="请选择" clearable @change="searchData('portfoliostatus', projectmanagerval)">
              <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
            </el-select>
          </th>
          <th scope="col">geoID
            <el-select v-model="LPInfoFrom.currency" placeholder="请选择" clearable @change="searchData('portfoliostatus', projectmanagerval)">
              <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
                :value="item.baseId"></el-option>
            </el-select>
          </th>
          <th scope="col">ERISA
            <el-select v-model="LPInfoFrom.currency" placeholder="请选择" clearable @change="searchData('portfoliostatus', projectmanagerval)">
              <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
                :value="item.baseId"></el-option>
            </el-select>
          </th>
          <th scope="col">Incorporated In
          </th>
          <th scope="col">incorpLocationID
          </th>
          <th scope="col">State of Domicile
          </th>
          <th scope="col">Website 
          </th>
          <th scope="col">Business Address
          </th>
          <th scope="col">Bank Name
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Swift Code
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Account Name
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Account No.
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">ABA
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">IBAN
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Bank Region
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Bank Address
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">For Credit To
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col" style="width:200px;">For Credit Account No.
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Coresp. Bank Name
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Coresp. Swift Code
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Coresp. ABA
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Coresp. IBAN
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Further Credit To
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Further Account No.
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Further Bank Name
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Further Swift Code
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Further Credit ABA
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Further Credit IBAN
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">Notes
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col" style='width:200px'>Corresponence Address
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th scope="col">approved
            <el-input class="search" placeholder="请输入Programe Name" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
          </th>
          <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in LPinfoList">
            <td scope="row">{{item.abbname}}</td>
            <td>{{item.abbnamechi}}</td>
            <td>{{item.fullname}}</td>
            <td>{{item.fullnamechi}}</td>
            <td>{{item.projectmanagerstr}}</td>
            <td>{{item.portfoliostatusstr}}</td>
            <td>{{item.idgonboardflag}}</td>
            <td>{{item.heldbyspvflag}}</td>
            <td scope="row">{{item.abbname}}</td>
            <td>{{item.abbnamechi}}</td>
            <td>{{item.fullname}}</td>
            <td>{{item.fullnamechi}}</td>
            <td>{{item.projectmanagerstr}}</td>
            <td>{{item.portfoliostatusstr}}</td>
            <td>{{item.idgonboardflag}}</td>
            <td>{{item.heldbyspvflag}}</td>
            <td scope="row">{{item.abbname}}</td>
            <td>{{item.abbnamechi}}</td>
            <td>{{item.fullname}}</td>
            <td>{{item.fullnamechi}}</td>
            <td>{{item.projectmanagerstr}}</td>
            <td>{{item.portfoliostatusstr}}</td>
            <td>{{item.idgonboardflag}}</td>
            <td>{{item.heldbyspvflag}}</td>
            <td scope="row">{{item.abbname}}</td>
            <td>{{item.abbnamechi}}</td>
            <td>{{item.fullname}}</td>
            <td>{{item.fullnamechi}}</td>
            <td>{{item.projectmanagerstr}}</td>
            <td>{{item.portfoliostatusstr}}</td>
            <td>{{item.idgonboardflag}}</td>
            <td>{{item.heldbyspvflag}}</td>
            <td scope="row">{{item.abbname}}</td>
            <td>{{item.abbnamechi}}</td>
            <td>{{item.fullname}}</td>
            <td>{{item.fullnamechi}}</td>
            <td>{{item.projectmanagerstr}}</td>
            <td>{{item.portfoliostatusstr}}</td>
            <td>{{item.idgonboardflag}}</td>
            <td>
              <i class="el-icon-edit" @click="handleEdit()"></i>
              <i class="el-icon-delete" @click="handleDelet()"></i>
            </td>
          </tr>
        </tbody>
      </table>
      </div>
      <div class="pagination-container">
        <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :page-size='5' :current-page.sync="pageIndex" @current-change="handleCurrentChange" style="position:absolute;left:50%;transform: translate(-50%);"></el-pagination>    
      </div>
      <div class="table-foot">
        <i class="el-icon-circle-plus" @click="handleAdd" v-if="isDetail!='false'"></i>
      </div>
    </div>
    <el-dialog title="LP Information" :visible.sync="LPInfoVisible">
      <div class="select-container">
      <el-form :model="LPInfoFrom" ref="LPInfoFrom">
        <div class="select-fixed">
        <el-form-item label="Start Date">
          <el-date-picker v-model="LPInfoFrom.startdate" type="date" placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="End Date">
          <el-date-picker v-model="LPInfoFrom.enddate" type="date" placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="Currency">
        <el-select v-model="LPInfoFrom.currency" placeholder="请选择活动区域">
          <el-option v-for='item in currencyList' :label="item" :value="item" :key='item'></el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="Revenue">
          <el-input v-model="LPInfoFrom.revenue" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Gross profits">
          <el-input v-model="LPInfoFrom.grossprofits" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Cash On Hand">
          <el-input v-model="LPInfoFrom.cashassets" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Total Assets">
          <el-input v-model="LPInfoFrom.totalassets" auto-complete="off"></el-input>
        </el-form-item>
          <el-form-item label="Operating Profits">
          <el-input v-model="LPInfoFrom.operatingprofits" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Bank Loan/Debt">
          <el-input v-model="LPInfoFrom.debt" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Net Profits">
          <el-input v-model="LPInfoFrom.netprofits" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Total Liabilities">
          <el-input v-model="LPInfoFrom.totalliabilities" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Retailed Earning ">
          <el-input v-model="LPInfoFrom.retainedearnings" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Share Captial">
          <el-input v-model="LPInfoFrom.sharecapital" auto-complete="off"></el-input>
        </el-form-item>
        </div>
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="LPInfoVisible = false" size="mini">Cancel</el-button>
        <el-button type="primary" @click="submitLPInfoFrom('LPInfoFrom','add')" size="mini" v-if="buttonShow=='Add'">Add</el-button>
        <el-button type="primary" @click="submitLPInfoFrom('LPInfoFrom','update')" size="mini" v-else>Update</el-button>
      </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from '@/api/axios';
import mix from "@/api/mixin"
export default {
  name:"LPinformation",
  mixins:[mix],
  data(){
    return {
      heightObj:'',
      LPInfoVisible:false,
      buttonShow:'Add',
      LPinfoList: [],
      currencyList:[],
      pageIndex:1,
      pageCount:5,
      allcount:0,
      LPInfoFrom: {
        startdate:'',
        enddate:'',
        currency:'',
        revenue:'',
        grossprofits:'',
        cashassets:'',
        totalassets:'',
        operatingprofits:'',
        debt:'',
        netprofits:'',
        totalliabilities:'',
        retainedearnings:'',
        sharecapital:''
      },
      LPInfoFromEmpty: {
        startdate:'',enddate:'',currency:'',
        revenue:'',grossprofits:'',cashassets:'',
        totalassets:'',operatingprofits:'',debt:'',
        netprofits:'',totalliabilities:'',
        retainedearnings:'',sharecapital:''
      }
    }
  },
  mounted(){
    this.reqFinList(this.pageIndex,this.pageCount);
    this.reqCurrencyList();
  },
  methods:{
    reqFinList(pageIndex,pageCount){
      var obj={
        portfolioid:this.portfolioid,
        pageIndex:pageIndex,
        pageCount:pageCount
      }
      axioss.reqFinList(obj).then(res=>{
        this.LPinfoList=res.data.data;
        this.allcount=res.data.count;
      })
    },
    reqCurrencyList(){
      axioss.reqCurrencyList().then(res=>{
        this.currencyList=res.data.data;
      })
    },
    queryFin(id){
      axioss.queryFin(id).then(res=>{
        this.LPInfoFrom=res.data.data;
      })            
    },
    submitLPInfoFrom(formName,type){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var obj=this.LPInfoFrom;
          obj.portfolioid=this.portfolioid;
          if(type=='add'){   
            axioss.addFin(obj).then(res=>{
              let status=res.data.code,succMes='创建成功',failMes='创建失败';
              let stateCode=this.showToast(status,succMes,failMes);
              if(stateCode){
                this.LPInfoVisible=false;
                Object.assign(this.LPInfoFrom,this.LPInfoFromEmpty);
                this.reqFinList(this.pageIndex,this.pageCount);
              }
            })
          }else{
            axioss.updateFin(obj).then(res=>{
              let status=res.data.code,succMes='更新成功',failMes='更新失败';
              let stateCode=this.showToast(status,succMes,failMes);
              if(stateCode){
                this.LPInfoVisible=false;
                Object.assign(this.LPInfoFrom,this.LPInfoFromEmpty);
                this.reqFinList(this.pageIndex,this.pageCount);
              }
            })
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    handleCurrentChange(){
      this.reqFinList(this.pageIndex,this.pageCount);
    },
    handleAdd(){
      this.LPInfoVisible=true;
      this.buttonShow='Add';
    },
    handleEdit(index,data){
      this.LPInfoVisible=true;
      this.buttonShow='Update';
      this.queryFin(data.portfoliofinanciallot);
    },
    handleDelet(index,data){
      var id=data.portfoliofinanciallot;
      console.log(id)
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        axioss.deleteFin(id).then(res => {
          console.log(res)   
          let status=res.data.code,succMes='删除成功!',failMes='删除失败!';
          let stateCode=this.showToast(status,succMes,failMes);
          if(stateCode){
            this.reqFinList(this.pageIndex,this.pageCount);
          }
        });
      })
    },
    searchData(){

    }
  },
  computed:{
    portfolioid(){
      if(this.$store.state.portfolioid==''){
        this.$store.dispatch('updateData');
      }
      return this.$store.state.portfolioid;
    },
    isDetail(){
      if(this.$store.state.isDetail==''){
        this.$store.dispatch('updateIsDetail');
      }
      return this.$store.state.isDetail;
    }
  }
}
</script>
<style scoped lang="scss">
	.financial{
        margin-top:20px;
        border:1px solid #ddd;
        .loan-table-container h3{
            font-size:16px;
            padding:5px 0;
            padding-left:10px;
            background:#eee;
            margin: 0;
            border-bottom: 1px solid #ebeef5;
        }
    }
    
    .loan-table-container{
        position:relative;
        
    }
    .loan-table-container .table-foot{
        background:#eee;
        padding:5px 0;
        padding-left:10px;
    }
    .select-container{
        width:100%;
    }
    .pagination-container{
        width:100%;
        height:35px;
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
</style>
