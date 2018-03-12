<template>
  <div class="director loan">
    <div class="loan-table-container">
      <h3>Capital lpTransfer of the Selected Investor</h3>
      <el-table :data="lpTransferData" border style="display:inline-block;width:auto;margin-bottom:-5px;">
        <el-table-column prop="fundName" label="Fund" width="110">
        </el-table-column>
        <el-table-column prop="commitmentNum" label="commitment Num" width="150" :formatter='numberFormat'>
        </el-table-column>
        <el-table-column prop="commitmentDate" label="commitment Date" width="150">
        </el-table-column>
        <el-table-column prop="fundFamillyName" label="fundFamillyName" width="160">
        </el-table-column>
        <el-table-column label="Transfer" width="80">
          <template slot-scope="scope">
            <i class="el-icon-edit" @click="handleAdd(scope.$index, scope.row)"></i>
          </template>
        </el-table-column>
      </el-table>
      <div class="table-foot">
        
      </div>
    </div>
    <el-dialog title="LP Information" :visible.sync="lpTransferVisible">
      <div class="select-container">
      <el-form :model="lpTransferForm" ref="lpTransferForm" :rules='rules'>
        <div class="select-fixed">
          <el-form-item label="Transfer Type:">
          <el-select v-model="lpTransferForm.transfertype" @change='judgeRquest'>
            <el-option v-for="item in investorType" :key="item.baseId"
            :label="item.baseName" :value="item.baseId"></el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="Transfer To:" v-if="transferType=='toExist'">
          <el-select v-model="lpTransferForm.transfertolpid">
            <el-option v-for="item in lpTransferDropList" :key="item.lpid"
            :label="item.lplegalfullname" :value="item.lpid"></el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="Transfer To:" v-else>
            <el-input v-model="lpTransferForm.transfertolpname"></el-input>
          </el-form-item>
          <el-form-item label="Transfer Date:">
            <el-date-picker v-model="lpTransferForm.commitmentdate" type="date">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="Transfer Amount:">
            <el-input v-model="lpTransferForm.commitmentnum"></el-input>
          </el-form-item>
        </div>
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm" size="mini">Cancel</el-button>
        <el-button type="primary" @click="submitForm('lpTransferForm')" size="mini">Add</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import axioss from '@/api/axios';
import bus from '@/api/eventbus';
import mix from "@/api/mixin";
import * as method from "@/api/method";
export default {
  name:"selinvestor",
  mixins:[mix],
  props:['lpid'],
  data(){
    var compareDate=(rules, value, callback)=>{
      if(value==''){
        callback();
      }else if(value<this.lpTransferForm.directorstartdate){
        callback(new Error('不能小于Start Date'))
      }else{
        callback();
      }
    }
    return {
      fundID:'F0001',
      lpTransferVisible:false,
      lpTransferData: [],
      pageIndex:1,
      allcount:0,
      transferType:'toExist',
      searchForm:{
        lplegalfullname:'',
        lpTransferdate:'',
      },
      lpTransferForm: {
        transfertype:'',
        transfertolpid:'',
        commitmentnum:'',
        commitmentdate:'',
        transfertolpname:''
      },
      lpTransferFormEmpty: {
        transfertype:'',
        transfertolpid:'',
        commitmentnum:'',
        commitmentdate:'',
        transfertolpname:''
      },
      lpTransferDropList:[],
      rules:{
        directorenddate:[
          {validator:compareDate,trigger:'change'}
        ]
      },
      investorType:[
        {baseName:"Internal Transfer: Transfer toExist LP",baseId:'toExist'},
        {baseName:"Internal Transfer: Transfer to New LP",baseId:'toNew'}
      ]
    }
  },
  updated(){
    
  },
  mounted(){
    bus.$on('toScorll',(ace)=>{
      this.scrolltoview(ace,'lpTransfer');
    });
    this.reqCommitSelectList();
  },
  methods:{
    reqCommitSelectList(){
      axioss.reqCommitSelectList().then(res=>{
        this.lpTransferDropList=res.data.data
      })
    },
    
    reqListTransfer(lpid){
      var obj={
        lpid:lpid
      }
      console.log(obj)
      axioss.reqListTransfer(obj).then(res=>{
        console.log(res)
        this.lpTransferData=res.data.data;
      })
    },
    submitForm(formName,type){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var obj=this.lpTransferForm;
          obj.fundID=this.fundID;
          console.log(obj)
          axioss.addLpTransfer(obj).then(res=>{
            let status=res.data.code,succMes='Create success',failMes='Create failure';
            let stateCode=this.showToast(status,succMes,failMes);
            if(stateCode){
              this.lpTransferVisible=false;
              Object.assign(this.lpTransferForm,this.lpTransferFormEmpty);
              this.reqListTransfer(this.lpid);
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(){
      this.lpTransferVisible=false;
      Object.assign(this.lpTransferForm,this.lpTransferFormEmpty)
    },
    handleAdd(index,data){
      this.lpTransferVisible=true;
      this.fundID=data.fundID;
    },
    judgeRquest(val){
      this.transferType=val;
      //初始化;
      this.lpTransferForm.transfertolpid='';
      this.lpTransferForm.transfertolpname='';
    },
    handleCurrentChange(){
      this.reqListTransfer(this.$route.query.fundid,this.pageIndex)
    }
  },
  watch:{
    lpid(newdata,olddata){
      this.reqListTransfer(newdata)
    }
  },
  computed:{
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
	.director{
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
  .loan-table-container .table-foot{
    background:#eee;
    padding:5px 0;
    padding-left:10px;
    clear:both;
  }
  .loan-table-container{
    position:relative;
  }
  .select-container{
    width:100%;
  }
  .number{
    padding:10px 0;
  }
  .pagination-container{
        width:100%;
        height:35px;
    }
  .loan{
    margin-bottom:0;
  }
</style>
