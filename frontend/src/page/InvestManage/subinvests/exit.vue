<template>
<div class="exit loan">
    <div class="loan-table-container">
        <h3 class="h3">Exit</h3>
        <el-table ref="singleTable" :data="exitData" border style="width:100%;">
            <el-table-column prop="exittype" label="exitType"></el-table-column>
            <el-table-column prop="exitmode" label="exitMode"></el-table-column>
            <el-table-column prop="costrelization" label="costRelization" width="130" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="round" label="round"></el-table-column>
            <el-table-column prop="shareexit" label="shares" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="fundfamillyname" label="Fund Family" width='110'></el-table-column>
            <el-table-column prop="closedate" label="closeDate" width='130' :formatter="formatDate"></el-table-column>
            <el-table-column prop="termsigndate" label="termsigndate" width='130' :formatter="formatDate"></el-table-column>
            <el-table-column prop="currency" label="currency"></el-table-column>
            <el-table-column prop="shareremain" label="shareremain" :formatter="numberFormat" width='110'></el-table-column>
            <el-table-column prop="vouncher" label="Opt" v-if="isDetail!='false'">
                <template slot-scope="scope">
                    <i class="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></i>
                    <i class="el-icon-delete" @click="deleteExit(scope.$index, scope.row)"></i>
                </template>
            </el-table-column>
        </el-table>
        <div class="table-foot">
            <i class="el-icon-circle-plus" @click="handleAdd" v-if="isDetail!='false'"></i>
        </div>
    </div>
    <el-dialog title="Exit Edit" :visible.sync="ExitVisible">
        <div class="select-container">
            <el-form :model="exitForm" ref="exitForm" :label-position='labelPosition'>
                <div class="select-fixed">
                    <el-form-item label="Exit Type">
                        <el-select v-model="exitForm.exittype" placeholder="  " @change="togetherChange">
                            <el-option v-for="item in exitDropList.DDL_ExitType" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Exit Mode">
                        <el-select v-model="exitForm.exitmode" placeholder="  ">
                            <el-option v-for="item in ExitModeDataList" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Payment Date">
                        <el-date-picker v-model="exitForm.closedate" type="date" placeholder=" ">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="Termsign Date">
                        <el-date-picker v-model="exitForm.termsigndate" type="date" placeholder=" ">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="Fund Family">
                        <el-select v-model="exitForm.fundfamillyname" placeholder="  " @change="creatShareList" filterable :disabled="isDisabled">
                            <el-option v-for="item in exitDropList.FUNDFAMILY" :key="item.baseId"
                             :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Currency">
                        <el-select v-model="exitForm.currency" placeholder="  ">
                            <el-option v-for="item in exitDropList.CURRENCY" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div v-if="exitForm.exittype=='Loan'">
                    <el-form-item label="costRelization">
                        <el-input v-model="exitForm.shareexit"></el-input>
                    </el-form-item>
                    <el-form-item label="proceeds">
                        <el-input v-model="exitForm.proceeds"></el-input>
                    </el-form-item>
                </div>
                <div v-else>
                    <el-form-item label="proceeds">
                        <el-input v-model="exitForm.proceeds"></el-input>
                    </el-form-item>
                    <el-form-item  label="shares">
                        <el-select v-model="exitId" @change="fetchData">
                            <el-option v-for="item in shareList" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="No. of Shares">
                        <el-input v-model="exitForm.shareexit"></el-input>
                    </el-form-item>
                </div>
            </el-form>
        </div>
        <subCapTable :dataObj='dataObj' :investForm='exitForm' :buttonShow='buttonShow' v-show="subCapTableShow"></subCapTable>
        <div slot="footer" class="dialog-footer">
            <el-button @click="ExitVisible = false" size='mini'>Cancel</el-button>
            <el-button type="primary" size='mini' @click="submitAdd('exitForm','add')" v-if="buttonShow">Create</el-button>
            <el-button type="primary" size='mini' @click="submitAdd('exitForm','update')" v-else>Update</el-button>
        </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from '@/api/axios';
import * as method from "@/api/method";
import subCapTable from "../../capTable/subCapTable"
import mix from "@/api/mixin"
import bus from "@/api/eventbus";
export default {
  name:"Exit",
  mixins:[mix],
  data(){
    return {
      dataObj:{},
      subCapTableShow:true,
      exitId:'',
      isDisabled:false,
      shareList:[],
      ExitVisible:false,
      buttonShow:true,
      exitData:[],
      labelPosition:'right',
      ExitModeDataList:[],
      sharelistData:[],
      exitType:'',
      exitMode:'',
      exitDropList:'',
      selectlist:{},
      loanData: [],
      exitForm:{
        exitmode:'',
        exittype:'',
        fundfamillyname:"", 
        portfolioid:"",
        closedate:'',
        termsigndate:'',
        currency:"USD",
        costrelization:'',
        proceeds:'',
        shareexit:'',
        shareremain:'',
        securitytypeid:'',
        round:"",
        portfoliocaptablevaluedetailList:[]
      },
      exitFormEmpty:{
        exitmode:'',exittype:'',fundfamillyname:"",
        portfolioid:"",closedate:'',currency:"USD",
        costrelization:'',proceeds:'',shareexit:'',
        shareremain:'',securitytypeid:'',round:"",
        portfoliocaptablevaluedetailList:[],termsigndate:''
      }
    }
  },
  mounted(){
    this.reqSelectList();
    this.reqExitList(this.portfolioid);
  },
  methods:{
    reqSelectList(){
      var obj={dictArray:"DDL_ExitType,DDL_ExitModeType,FUNDFAMILY,CURRENCY"};
      axioss.reqSelectList(obj).then(res=>{
        this.exitDropList=method.translateFormat(res.data.data);
      })
    },
    numberFormat: function (row, column) {
      var num = row[column.property];
      if (num == undefined) {
        return "";
      }
      return method.toThousands(num);
    },
    reqExitShareList(obj){
      axioss.reqExitShareList(obj).then(res=>{
        this.sharelistData=res.data.data;
        var data=res.data.data;
        var tem=JSON.stringify(data);
        var newdata=JSON.parse(tem);
        this.shareList=method.ConcatStr(newdata)
      })
    },
    reqExitList(id){
      var portfolioid=id||this.portfolioid;
      axioss.reqExitList(portfolioid).then(res=>{
        this.exitData=this.formatTime(res.data.data);//gai
      })
    },
    querySingal(id){
      axioss.querySingal(id).then(res=>{
        this.exitForm=res.data.data;
        this.$store.dispatch('saveCapTabel',res.data.data.portfoliocaptablevaluedetailList);
        this.dataObj.termsigndate=res.data.data.termsigndate;
        this.dataObj.portfolioid=this.portfolioid;
        this.dataObj.closedate=this.exitForm.closedate;
        this.dataObj.round='exit';
      })
    },
    submitAdd(formName,type){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var obj=this.exitForm;
          if(type=='add'){
            obj.portfolioid=this.portfolioid;
            axioss.addExit(obj).then(res=>{
              if(res.data.code=="SUCCESS"){
                this.$message({
                  type:'success',
                  message: 'Create success'
                })
                this.ExitVisible=false;
                this.reqExitList(this.portfolioid);
                Object.assign(this.exitForm,this.exitFormEmpty);
                bus.$emit("updateCaptable");
              }else{
                this.$message({
                  type:'warning',
                  message: 'Create failure'
                })
              }
            })
          }else{
            obj.portfoliocaptablevaluedetailList=this.capFormList;
            axioss.updateExit(obj).then(res=>{
              if(res.data.code=="SUCCESS"){
                this.$message({
                  type:'success',
                  message: 'Update success'
                })
                this.ExitVisible=false;
                this.reqExitList(this.portfolioid);
                Object.assign(this.exitForm,this.exitFormEmpty)
                bus.$emit("updateCaptable");
              }else{
                this.$message({
                  type:'warning',
                  message: 'Update failure'
                })
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
      Object.assign(this.exitForm,this.exitFormEmpty)
      this.subCapTableShow=false;
      this.isDisabled=false;
      this.ExitVisible=true;
      this.buttonShow=true;
    },
    handleEdit(index,data){
      this.subCapTableShow=true;
      this.isDisabled=true;
      this.ExitVisible=true;
      this.buttonShow=false;
      this.querySingal(data.id);
    },
    deleteExit(index,data){
      this.$confirm("This Opt will permanently delete the file, whether it will continue?", "Notice", {
        confirmButtonText: "OK",
        cancelButtonText: "Cancel",
        type: "warning"
      }).then(() => {
        axioss.deleteExit(data.id).then(res=>{
          var status = res.data.code;
          if (status.toLocaleLowerCase() == "success") {
            this.reqExitList(this.portfolioid);
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
        })
      })
    },
    creatShareList(val){
      var obj={fundfamillyname:val,portfolioid:this.portfolioid}
      this.reqExitShareList(obj);
    },
    fetchData(val){
      var len=this.sharelistData.length;
      var data=this.sharelistData;
      for(var i=0;i<len;i++){
        if(val==data[i].baseId){
          this.exitForm.securitytypeid=data[i].baseName.securitytypeid;
          this.exitForm.round=data[i].baseName.round;
          this.exitForm.shareremain=data[i].baseName.shareremain;
          this.exitForm.fundfamillyname=data[i].baseName.fundfamillyname;
        }
      }
    },
    togetherChange(val){
      this.exitForm.exitmode='';
      var ExitMode=this.exitDropList.DDL_ExitModeType;
      var empty=[];
      for(var i=0;i<ExitMode.length;i++){
        if(ExitMode[i].bizType==val){
          empty.push(ExitMode[i])
        }
      }
      this.ExitModeDataList=empty;
    },
    formatTime(data){
      if(data&&data!=null&&data!=[]){
        for(var i=0;i<data.length;i++){
          data[i].closedate=method.toLocalString(data[i].closedate);
        }
      }
      return data
    }
  },
  computed:{
    portfolioid:function(){
      if(this.$store.state.portfolioid==''){
        this.$store.dispatch('updateData');
      }
      return this.$store.state.portfolioid;
    },
    capFormList(){
      return this.$store.state.capTabelData;
    },
    isDetail(){
      if(this.$store.state.isDetail==''){
        this.$store.dispatch('updateIsDetail');
      }
      return this.$store.state.isDetail;
    }
  },
  components:{
    subCapTable
  }
}
</script>
<style scoped lang="scss">
	.loan{
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
    }
    .select-container{
        width:100%;
    }
</style>
