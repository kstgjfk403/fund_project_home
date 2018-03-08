<template>
<div class="financial loan lpinfo">
    <Header></Header>
    <div class="title" style="margin:0 20px;margin-top:20px;">LP Information</div>
    <div class="loan-table-container"  style="margin:0 20px;border:1px solid #ddd;">
      <h3>LP Information</h3>
      <div class="table-responsive">
        <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;">
          <thead>
            <tr>
            <th scope="col">LP ID
              <el-input v-model='LPinfoListParams.lpid' @input.native="searchData('abbname',abbnameval)"></el-input>
            </th>
            <th scope="col">LP Group
              <el-input class="search" v-model='LPinfoListParams.lpfamily' @input.native="searchData('abbnamechi',abbnamechival)"></el-input>
            </th>
            <th scope="col">Investor Legal Name
              <el-input class="search" v-model='LPinfoListParams.lplegalfullname' @input.native="searchData('fullname',fullnameval)"></el-input>
            </th>
            <th scope="col">Investor Type
              <el-input class="search" v-model='LPinfoListParams.investortype' @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Partner Type
              <el-select v-model="LPinfoListParams.partnertype" clearable @change="searchData('portfoliostatus', projectmanagerval)">
                <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
                  :value="item.baseId"></el-option>
              </el-select>
            </th>
            <th scope="col">Quit
              <el-select v-model="LPinfoListParams.deactive" clearable @change="searchData('portfoliostatus', projectmanagerval)">
                <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
                  :value="item.baseId"></el-option>
              </el-select>
            </th>
            <th scope="col">Quit Date
              <el-select v-model="LPinfoListParams.deactivedate" clearable @change="searchData('portfoliostatus', projectmanagerval)">
                <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
                  :value="item.baseId"></el-option>
              </el-select>
            </th>
            <th scope="col">Investor Geo
              <el-select v-model="LPinfoListParams.geo" clearable @change="searchData('portfoliostatus', projectmanagerval)">
                <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
              </el-select>
            </th>
            <th scope="col">ERISA
              <el-select v-model="LPinfoListParams.erisa" clearable @change="searchData('portfoliostatus', projectmanagerval)">
                <el-option v-for="item in 6" :key="item.baseId" :label="item.baseName"
                  :value="item.baseId"></el-option>
              </el-select>
            </th>
            <th scope="col">Incorporated In
            </th>
            <th scope="col">State of Domicile
            </th>
            <th scope="col">Website
            </th>
            <th scope="col">Business Address
            </th>
            <th scope="col">Bank Name
              <el-input class="search" v-model="LPinfoListParams.bankname" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Swift Code
              <el-input class="search" v-model="LPinfoListParams.swiftcode" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Account Name
              <el-input class="search" v-model="LPinfoListParams.bankaccountname" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Account No.
              <el-input class="search" v-model="LPinfoListParams.bankaccount" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">ABA
              <el-input class="search" v-model="LPinfoListParams.aba" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">IBAN
              <el-input class="search" v-model="LPinfoListParams.iban" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Bank Region
              <el-input class="search" v-model="LPinfoListParams.bankregion" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Bank Address
              <el-input class="search" v-model="LPinfoListParams.bankaddress" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">For Credit To
              <el-input class="search" v-model="LPinfoListParams.forcreditto" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col" style="width:200px;">For Credit Account No.
              <el-input class="search" v-model="LPinfoListParams.forcreditaccount" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Coresp. Bank Name
              <el-input class="search" v-model="LPinfoListParams.currency" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Coresp. Swift Code
              <el-input class="search" v-model="LPinfoListParams.currency" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Coresp. ABA
              <el-input class="search" v-model="LPinfoListParams.currency" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Coresp. IBAN
              <el-input class="search" v-model="LPinfoListParams.currency" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Further Credit To
              <el-input class="search" v-model="LPinfoListParams.furthercreditto" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Further Account No.
              <el-input class="search" v-model="LPinfoListParams.furtheraccountno" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Further Bank Name
              <el-input class="search" placeholder=" Programe Name" v-model="LPinfoListParams.furthercreditbank" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Further Swift Code
              <el-input class="search" v-model="LPinfoListParams.furthercreditswiftcode" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Further Credit ABA
              <el-input class="search" v-model="LPinfoListParams.furthercreditaba" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Further Credit IBAN
              <el-input class="search" v-model="LPinfoListParams.furthercreditiban" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">Notes
              <el-input class="search" v-model="LPinfoListParams.notes" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col" style='width:200px'>Corresponence Address
              <el-input class="search" v-model="LPinfoListParams.coraddress" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <th scope="col">approved
              <el-input class="search" v-model="LPinfoListParams.approved" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>
            </th>
            <!-- <th>Opt</th> -->
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in LPinfoList" @click='transFerLpid($event,item.lpid)'>
              <td scope="row">{{item.lpid}}</td>
              <td>{{item.lpfamily}}</td>
              <td><p>{{item.lplegalfullname}}</p></td>
              <td>{{item.investortype}}</td>
              <td>{{item.partnertype}}</td>
              <td>{{item.deactive}}</td>
              <td>{{item.deactivedate}}</td>
              <td>{{item.geo}}</td>
              <td scope="row">{{item.erisa}}</td>
              <td scope="row">{{item.incorplocation}}</td>
              <td>{{item.registerplace}}</td>
              <td>{{item.website}}</td>
              <td>{{item.businessplace}}</td>
              <td>{{item.bankname}}</td>
              <td>{{item.swiftcode}}</td>
              <td><p>{{item.bankaccountname}}</p></td>
              <td>{{item.bankaccount}}</td>
              <td>{{item.aba}}</td>
              <td>{{item.iban}}</td>
              <td>{{item.bankregion}}</td>
              <td scope="row"><p>{{item.bankaddress}}</p></td>
              <td>{{item.forcreditto}}</td>
              <td>{{item.forcreditaccount}}</td>
              <td>{{item.forcreditbank}}</td>
              <td>{{item.forcreditswiftcode}}</td>
              <td>{{item.forcreditaba}}</td>
              <td>{{item.forcreditiban}}</td>
              <td>{{item.forcreditto}}</td>
              <td scope="row">{{item.furtheraccountno}}</td>
              <td>{{item.furthercreditbank}}</td>
              <td>{{item.furthercreditswiftcode}}</td>
              <td>{{item.furthercreditaba}}</td>
              <td>{{item.furthercreditiban}}</td>
              <td><p>{{item.notes}}</p></td>
              <td><p>{{item.coraddress}}</p></td>
              <td>{{item.approved}}</td>
              <!-- <td>
                <i class="el-icon-edit" @click="handleEdit"></i>
                <i class="el-icon-delete" @click="handleDelet"></i>
              </td> -->
            </tr>
          </tbody>
        </table>
        <!--<div class='fixed-right'>
        </div>-->
      </div>
      <div class="pagination-container">
        <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :page-size='5' :current-page.sync="pageIndex" @current-change="handleCurrentChange" style="position:absolute;left:50%;transform: translate(-50%);"></el-pagination>
      </div>
      <div class="table-foot">
        <i class="el-icon-circle-plus" @click="handleAdd" v-if="isDetail!='false'"></i>
      </div>

      <SelInvestor :lpid='lpid'></SelInvestor>

    </div>
    <el-dialog title="LP Information" :visible.sync="LPInfoVisible">
      <div class="select-container">
      <el-form :model="LPInfoFrom" ref="LPInfoFrom">
        <div class="select-fixed">
        <el-form-item label="LP Group:">
          <el-input v-model="LPInfoFrom.lpfamily" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Investor Legal Name:">
          <el-input v-model="LPInfoFrom.lplegalfullname" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Investor Type:">
          <el-select v-model="LPInfoFrom.investortype" placeholder="  ">
            <el-option v-for='item in currencyList' :label="item" :value="item" :key='item'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Partner Type:">
          <el-select v-model="LPInfoFrom.partnertype" placeholder="  ">
            <el-option v-for='item in currencyList' :label="item" :value="item" :key='item'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Investor Geo:">
          <el-select v-model="LPInfoFrom.geo" placeholder="  ">
            <el-option v-for='item in currencyList' :label="item" :value="item" :key='item'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Co. Location:">
          <el-select v-model="LPInfoFrom.incorplocation" placeholder="  ">
            <el-option v-for='item in incorpLocationList' :label="item.baseName" :value="item.baseId" :key='item.baseId'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="State of Domicile:">
          <el-input v-model="LPInfoFrom.registerplace" auto-complete="off"></el-input>
        </el-form-item>
          <el-form-item label="Business Address:">
          <el-input v-model="LPInfoFrom.businessplace" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Website:">
          <el-input v-model="LPInfoFrom.website" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Bank Name:">
          <el-input v-model="LPInfoFrom.bankname" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Swift Code:">
          <el-input v-model="LPInfoFrom.swiftcode" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Bank Account Name:">
          <el-input v-model="LPInfoFrom.bankaccountname" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Bank Account No.:">
          <el-input v-model="LPInfoFrom.bankaccount" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="ABA:">
          <el-input v-model="LPInfoFrom.aba" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="IBAN:">
          <el-input v-model="LPInfoFrom.iban" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Bank Region:">
          <el-input v-model="LPInfoFrom.bankregion" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item label="For Credit To:">
          <el-input v-model="LPInfoFrom.forcreditto" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="For Credit Account:">
          <el-input v-model="LPInfoFrom.forcreditaccount" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Correspondent Bank:">
          <el-input v-model="LPInfoFrom.forcreditbank" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Correspondent Swift:">
          <el-input v-model="LPInfoFrom.forcreditswiftcode" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Correspondent ABA:">
          <el-input v-model="LPInfoFrom.forcreditaba" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Correspondent IBAN:">
          <el-input v-model="LPInfoFrom.forcreditiban" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Further Credit To:">
          <el-input v-model="LPInfoFrom.furthercreditto" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Further Account:">
          <el-input v-model="LPInfoFrom.furtheraccountno" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Further Bank:">
          <el-input v-model="LPInfoFrom.furthercreditbank" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Further Swift:">
          <el-input v-model="LPInfoFrom.furthercreditswiftcode" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Further Credit ABA:">
          <el-input v-model="LPInfoFrom.furthercreditaba" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Further Credit IBAN:">
          <el-input v-model="LPInfoFrom.furthercreditiban" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Quit Date:">
          <el-date-picker v-model="LPInfoFrom.deactivedate" type="date" placeholder="select Date">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="Connect Address:">
          <el-input type="textarea" autosize
             v-model="LPInfoFrom.coraddress">
          </el-input>
        </el-form-item>
        <el-form-item label="Notes:">
          <el-input type="textarea" autosize
             v-model="LPInfoFrom.notes">
          </el-input>
        </el-form-item>
        <el-form-item label="Bank Address:">
          <el-input type="textarea" autosize
             v-model="LPInfoFrom.bankaddress">
          </el-input>
        </el-form-item>
        <el-form-item label="Quit:">
          <el-checkbox v-model="LPInfoFrom.deactive"></el-checkbox>
        </el-form-item>
        <el-form-item label="ERISA:">
          <el-checkbox v-model="LPInfoFrom.erisa"></el-checkbox>
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
import SelInvestor from './subLP/SubLp';
import axioss from '@/api/axios';
import mix from "@/api/mixin";
import Header from '@/components/common/Header';
export default {
  name:"LPinformation",
  mixins:[mix],
  data(){
    return {
      lpid:'',
      heightObj:'',
      LPInfoVisible:false,
      buttonShow:'Add',
      LPinfoList: [],
      currencyList:[],
      incorpLocationList:[],
      LPinfoListParams:{
        aba:"", approved:'',
        auditor:"", bankaccount:"",
        bankaccountname:"", bankaddress:"",
        bankname:"", bankregion:"",
        businessplace:"", coraddress:"",
        createdby:'', createddate:'',
        deactive:'', deactivedate:'',
        eabmembers:'', erisa:'',
        forcreditaba:"", forcreditaccount:"",
        forcreditbank:"", forcreditiban:"",
        forcreditswiftcode:"", forcreditto:"",
        furcreditacc:'', furcreditaccname:'',
        furtheraccountno:"", furthercreditaba:"",
        furthercreditbank:"", furthercreditiban:"",
        furthercreditswiftcode:"", furthercreditto:"",
        geo:'', governmententity:'',
        iban:"", incorplocation:"",
        investortype:'', lpfamily:"",
        lpid:"", lplegalfullname:"",
        modifiedb:"", modifieddate:'',
        nature:'', notes:"",
        organizationtype:'', partnertype:"",
        registerplace:"", source:"",
        ssnortin:'', swiftcode:"",
        taxid:"", website:"", yearend:""
      },
      pageIndex:1,
      pageCount:5,
      allcount:0,
      LPInfoFrom: {
        aba:"",
        approved:'',
        auditor:"",
        bankaccount:"",
        bankaccountname:"",
        bankaddress:"",
        bankname:"",
        bankregion:"",
        businessplace:"",
        coraddress:"",
        createdby:'',
        createddate:'',
        deactive:'',
        deactivedate:'',
        eabmembers:'',
        erisa:'',
        forcreditaba:"",
        forcreditaccount:"",
        forcreditbank:"",
        forcreditiban:"",
        forcreditswiftcode:"",
        forcreditto:"",
        furcreditacc:'',
        furcreditaccname:'',
        furtheraccountno:"",
        furthercreditaba:"",
        furthercreditbank:"",
        furthercreditiban:"",
        furthercreditswiftcode:"",
        furthercreditto:"",
        geo:'',
        governmententity:'',
        iban:"",
        incorplocation:"",
        investortype:'',
        lpfamily:"",
        lpid:"",
        lplegalfullname:"",
        modifiedb:"",
        modifieddate:'',
        nature:'',
        notes:"",
        organizationtype:'',
        partnertype:"",
        registerplace:"",
        source:"",
        ssnortin:'',
        swiftcode:"",
        taxid:"",
        website:"",
        yearend:""
      },
      LPInfoFromEmpty: {
        aba:"", approved:'',auditor:"", bankaccount:"",
        bankaccountname:"", bankaddress:"",bankname:"", bankregion:"",
        businessplace:"", coraddress:"", createdby:'', createddate:'',
        deactive:'', deactivedate:'', eabmembers:'', erisa:'',
        forcreditaba:"", forcreditaccount:"", forcreditbank:"", forcreditiban:"",
        forcreditswiftcode:"", forcreditto:"", furcreditacc:'', furcreditaccname:'',
        furtheraccountno:"", furthercreditaba:"", furthercreditbank:"", furthercreditiban:"",
        furthercreditswiftcode:"", furthercreditto:"",
        geo:'', governmententity:'', iban:"", incorplocation:"",
        investortype:'', lpfamily:"", lpid:"", lplegalfullname:"",
        modifiedb:"", modifieddate:'', nature:'', notes:"",
        organizationtype:'', partnertype:"", registerplace:"", source:"",
        ssnortin:'', swiftcode:"", taxid:"", website:"", yearend:""
      }
    }
  },
  mounted(){
    this.reqLPinfomationList(this.pageIndex,this.pageCount);
    this.reqdroplist();
  },
  methods:{
    reqdroplist(){
      var obj = {
        dictArray: "DDL_IncorpLocation"
      };
      axioss.reqdroplist(obj).then(res=>{
        this.incorpLocationList=(res.data.data)[0].baseInfoList;
      })
    },
    reqLPinfomationList(pageIndex,pageCount){
      this.LPinfoListParams.pageIndex=pageIndex;
      this.LPinfoListParams.pageCount=pageCount;
      axioss.reqLPinfomationList(this.LPinfoListParams).then(res=>{
          console.log(res.data);
        this.LPinfoList=res.data.data;
        this.allcount=res.data.count;
      })
    },
    //queryLPinfo
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
            axioss.addFin(obj).then(res => {
              let status=res.data.code,succMes='Create success',failMes='Create failure';
              let stateCode=this.showToast(status,succMes,failMes);
              if(stateCode) {
                this.LPInfoVisible=false;
                Object.assign(this.LPInfoFrom,this.LPInfoFromEmpty);
                this.reqLPinfomationList(this.pageIndex,this.pageCount);
              }
            })
          }else{
            axioss.updateFin(obj).then(res=>{
              let status=res.data.code,succMes='Update success',failMes='Update failure';
              let stateCode=this.showToast(status,succMes,failMes);
              if(stateCode){
                this.LPInfoVisible=false;
                Object.assign(this.LPInfoFrom,this.LPInfoFromEmpty);
                this.reqLPinfomationList(this.pageIndex,this.pageCount);
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
      this.reqLPinfomationList(this.pageIndex,this.pageCount);
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
      this.$confirm("This Opt will permanently delete the file, whether it will continue?", "Notice", {
        confirmButtonText: "OK",
        cancelButtonText: "Cancel",
        type: "warning"
      }).then(() => {
        axioss.deleteFin(id).then(res => {
          let status=res.data.code,succMes='Delete success!',failMes='Delete failure!';
          let stateCode=this.showToast(status,succMes,failMes);
          if(stateCode){
            this.reqLPinfomationList(this.pageIndex,this.pageCount);
          }
        });
      })
    },
    transFerLpid(e,lpid){
      this.lpid = lpid;
      var ele = e.target;
      if(ele.tagName == 'P'){
        var parentnodes = ele.parentNode.parentNode.parentNode;
        var trnodes = parentnodes.children;
        this.clearStyle(trnodes);
        ele.parentNode.parentNode.style.background = 'rgba(0, 0, 0, 0.075)'
      }else if(ele.tagName == 'TD'){
        var parentnodes = ele.parentNode.parentNode;
        var trnodes = parentnodes.children;
        this.clearStyle(trnodes);
        ele.parentNode.style.background = 'rgba(0, 0, 0, 0.075)'
      }else{
        return false
      }
    },
    clearStyle(nodes){
      for(var i=0;i<=nodes.length;i++){
        if(nodes[i]){
          nodes[i].style = "white";
        }
      }
    }
  },
  components:{
    Header,
    SelInvestor
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
  
  .table tr td p{position:relative;line-height:20px;max-height:60px;overflow:hidden;}
  
  .table tr td:last-child{
    text-align: center;
  }
  .table tr th,.table tr td{
    border-color:#ddd;
  }
  .fixed-right{
    display: flex;
    justify-content: flex-end;
    align-items: top;
    width:132px;
    overflow:hidden;
    position: absolute;
    top:30px;
    left:auto;
    right: 0;
    background:white;
  }
  .white{
    background:white;
  }
</style>
