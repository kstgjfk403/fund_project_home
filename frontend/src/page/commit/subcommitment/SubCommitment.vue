<template>
  <div class="director loan">
    <div class="loan-table-container">
      <h3>Capital Commitment of the Selected Investor</h3>
      <el-table :data="commitmentData" border>
        <el-table-column prop="lpid" label="Investor" width="110">
        </el-table-column>
        <el-table-column prop="lplegalfullname" label="Investor Full Name" width="150">
        </el-table-column>
        <el-table-column prop="commitmentnum" label="Commitment" width="120" :formatter='numberFormat'>
        </el-table-column>
        <el-table-column prop="commitmentdate" label="Commitment Date" width="160" :formatter='formatDate'>
        </el-table-column>
        <el-table-column label="Opt" width="60">
          <template slot-scope="scope">
            <i class="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></i>
            <i class="el-icon-delete" @click="handleDelet(scope.$index, scope.row)"></i>
          </template>
        </el-table-column>
      </el-table>
      <div class="table-foot">
        <i class="el-icon-circle-plus" @click="handleAdd" v-if="isDetail!='false'"></i>
      </div>
    </div>
    <el-dialog title="Director Information" :visible.sync="commitmentVisible">
      <div class="select-container">
      <el-form :model="commitmentForm" ref="commitmentForm" :rules='rules'>
        <div class="select-fixed">
          <el-form-item label="*Transfer Type:">
          <el-select v-model="commitmentForm.lpid" placeholder="  ">
            <el-option v-for="item in commitmentDropList" :key="item.lpid"
            :label="item.lplegalfullname" :value="item.lpid"></el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="*Transfer Type">
          <el-select v-model="commitmentForm.lpid" placeholder="  ">
            <el-option v-for="item in commitmentDropList" :key="item.lpid"
            :label="item.lplegalfullname" :value="item.lpid"></el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="*Transfer Date:">
            <el-date-picker v-model="commitmentForm.commitmentdate" type="date" placeholder=" ">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="*Transfer Amount:">
            <el-input v-model="commitmentForm.commitmentnum"></el-input>
          </el-form-item>
        </div>
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="commitmentVisible = false" size="mini">Cancel</el-button>
        <el-button type="primary" @click="submitForm('commitmentForm','add')" size="mini" v-if="buttonShow=='Add'">Add</el-button>
        <el-button type="primary" @click="submitForm('commitmentForm','update')" size="mini" v-else>Update</el-button>
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
      }else if(value<this.commitmentForm.directorstartdate){
        callback(new Error('不能小于Start Date'))
      }else{
        callback();
      }
    }
    return {
      totalboard1:'',
      heightObj:'',
      commitmentVisible:false,
      buttonShow:'Add',
      commitmentData: [],
      pageIndex:1,
      allcount:0,
      searchForm:{
        lplegalfullname:'',
        commitmentdate:'',
      },
      commitmentForm: {
        lpid:'',
        commitmentnum:'',
        commitmentdate:''
      },
      commitmentFormEmpty: {
        lpid:'',
        commitmentnum:'',
        commitmentdate:''
      },
      commitmentDropList:[],
      rules:{
        directorenddate:[
          {validator:compareDate,trigger:'change'}
        ]
      }
    }
  },
  updated(){
    
  },
  mounted(){
    bus.$on('toScorll',(ace)=>{
      this.scrolltoview(ace,'Commitment');
    });
    this.reqCommitSelectList();
    this.reqCommitmentList(this.$route.query.fundid,this.pageIndex);
  },
  methods:{
    reqCommitSelectList(){
      axioss.reqCommitSelectList().then(res=>{
        this.commitmentDropList=res.data.data
      })
    },
    reqCommitmentList(fundid,pageIndex,search){
      var obj={
        fundid:fundid,
        pageIndex:pageIndex,
        pageCount:10
      }
      if(search){
        Object.assign(obj,search)
      }
      axioss.reqCommitmentList(obj).then(res=>{
        this.commitmentData=res.data.data;
        this.allcount=res.data.count;
      })
    },
    queryCommitment(id){
      axioss.queryCommitment(id).then(res=>{
        this.commitmentForm=res.data.data;
      })
    },
    submitForm(formName,type){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var obj=this.commitmentForm;
          if(type=='update'){
            obj.fundid=this.$route.query.fundid;
            axioss.updateCommitment(obj).then(res=>{
              let status=res.data.code,succMes='Update success',failMes='Update failure';
              let stateCode=this.showToast(status,succMes,failMes);
              if(stateCode){
                this.commitmentVisible=false;
                Object.assign(this.commitmentForm, this.commitmentFormEmpty);
                this.reqCommitmentList();
              }
            })
          }else{
            obj.fundid=this.$route.query.fundid;
            axioss.addCommitment(obj).then(res=>{
              let status=res.data.code,succMes='Create success',failMes='Create failure';
              let stateCode=this.showToast(status,succMes,failMes);
              if(stateCode){
                this.commitmentVisible=false;
                Object.assign(this.commitmentForm,this.commitmentFormEmpty);
                this.reqCommitmentList();
              }
            })
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    handleAdd(){
      this.commitmentVisible=true;
      this.buttonShow='Add';
    },
    handleEdit(index,data){
      this.commitmentVisible=true;
      this.buttonShow='Update';
      this.queryCommitment(data.commitID)
    },
    handleCurrentChange(){
      this.reqCommitmentList(this.$route.query.fundid,this.pageIndex)
    },
    handleDelet(index,data){
      var id=data.commitID;
      this.$confirm("This Opt will permanently delete the file, whether it will continue?", "Notice", {
        confirmButtonText: "OK",
        cancelButtonText: "Cancel",
        type: "warning"
      }).then(() => {
        axioss.deleteCommitment(id).then(res => {
          let status=res.data.code, succMes='Delete success', failMes='Delete failure';
          let stateCode=this.showToast(status,succMes,failMes);
          if(stateCode){
            this.reqCommitmentList(this.$route.query.fundid);
          }
        });
      })
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
</style>
