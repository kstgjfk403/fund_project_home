<template>
  <div class="capTable loan">
    <div class="select-container">
      <div class="select-fixed">
        <el-form :model="capInputForm" ref="capInputForm" style="overflow:hidden;width:90%;display:inline-block;vertical-align:middle;" :rules="rules">
          <el-form-item label="ShareholderType" prop="sharetype">
            <el-select v-model="capInputForm.sharetype" @change="hanleShareChange">
              <el-option v-for="item in capSelectList" :label="item.baseName" :value="item.baseId" :key="item.baseId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Fund" v-if="idg=='IDG'" prop="shareowner">
            <el-select v-model="capInputForm.shareowner" filterable>
              <el-option v-for="item in fundList" :label="item.baseName" :value="item.baseId" :key="item.baseId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="ShareholderName" v-else prop="shareowner">
            <el-input v-model="capInputForm.shareowner"></el-input>
          </el-form-item>
          <el-form-item label="Share Type" prop="securitytypeid">
            <el-select v-model="capInputForm.securitytypeid">
              <el-option v-for="item in shareTypeList" :label="item.baseName" :value="item.baseId" :key="item.baseId"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div style="text-align:right;display:inline-block;margin-bottom: 13px;vertical-align:middle;">   
          <el-button type="primary" size="mini" @click="submitInputForm('capInputForm','add')">Create</el-button>
        </div>
      </div>
    </div>
    <div class="content">
      <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;" id="capTable">
        <thead>
          <tr>
            <th scope="col">Stockholder Type</th>
            <th scope="col">Stockholder</th>
            <th scope="col">Share Type</th>
            <th scope="col">{{mycellname}}</th>
            <th scope="col">New investment</th>
            <th scope="col">Share ratio</th> 
            <th scope="col">No ESOP</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in captableList">
            <td scope="row" >{{item.sharetype}}</td>
            <td>{{item.shareowner}}</td>
            <td>{{item.securitytypeidstr}}</td>
            <td><el-input  v-isedit class="inputnone" v-model="item.shareownedno"></el-input></td>
            <td><el-input  v-isedit class="inputnone" v-model="item.cost"></el-input></td>
            <td>{{properFormat(item.proper)}}</td>
            <td>{{properFormat(item.properwithoutesop)}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
import axioss from '@/api/axios';
import * as method from "@/api/method";
import bus from "@/api/eventbus";
export default {
  name:"subCapTable",
  props:['dataObj','investForm','buttonShow'],
  timer:null,
  data(){
    return {
      heightObj:'',
      idg:'',
      cellName:'',
      capVisible:false,
      capSelectList:[],
      shareTypeList:[],
      fundList:[],
      capInputForm:{
        sharetype:"",
        shareowner:"",
        securitytypeid:''
      },
      capInputFormEmpty:{
        sharetype:"",
        shareowner:"",
        securitytypeid:''
      },
      translateObj:{
        Common:2,
        Preferred:3,
        'Equity Interest':6,
          Distribution:15,
        'Capital Call':14
      },
      rules: {
        sharetype: [
          { required: true, message: 'required', trigger: 'change' }
        ],
        shareowner: [
          { required: true, message: 'required', trigger: 'blur' }  
        ],
        securitytypeid: [
          { required: true, message: 'required', trigger: 'change' }  
        ]
      }
    }
  },
  updated(){
    this.$nextTick(function(){
      method.mc('capTable',0,0,0);
    })
  },
  mounted(){
    this.reqdroplist();
    bus.$on('clearSubCaptable',()=>{
      Object.assign(this.capInputForm,this.capInputFormEmpty);
    })
  },
  methods:{
      reqdroplist(){
        var obj={dictArray:"DDL_ShareHolderType,DDL_ShareType,FUND"}
        axioss.reqdroplist(obj).then(res=>{
          this.capSelectList=res.data.data[0].baseInfoList;
          this.shareTypeList=res.data.data[1].baseInfoList;
          this.fundList=res.data.data[2].baseInfoList;
        })
      },
      reqCaptableList(obj){
        axioss.reqCaptableList(obj).then(res=>{
          this.$store.dispatch('saveCapTabel',res.data.data);
        })
      },
      submitInputForm(fromName){
        this.$refs[fromName].validate((validate)=>{
          if(validate){
            if(this.buttonShow=='add'){
              this.dataObj.termsigndate=this.investForm.termsigndate;
              this.dataObj.portfolioid=this.portfolioid;
              this.dataObj.round=this.investForm.round;
              this.dataObj.closedate=this.investForm.closedate;
              this.dataObj.maxclosedate=this.investForm.closedate;
              Object.assign(this.dataObj,this.capInputForm);
            }else{
              this.dataObj.termsigndate=this.dataObj.termsigndate?this.dataObj.termsigndate:1484150400000;
              Object.assign(this.dataObj,this.capInputForm);
            }
            axioss.addShareHolder(this.dataObj).then(res=>{
              if(res.data.code=="SUCCESS"){
                this.$message({
                  type:'success',
                  message: 'Create success'
                })
                this.reqCaptableList(this.dataObj);
              }else{
                this.$message({
                  type:'warning',
                  message: 'Create failure'
                })
              }
            })
          }else{
            console.log('err submit')
          }
        })
      },
      hanleShareChange(val){
        this.idg=val;
        this.capInputForm.shareowner='';
      },
      translateSubmit(data,obj){
        var val=data.securitytypeid;
        if(!val){
          return data;
        }else{
          data.securitytypeid=obj[val]
          return data;
        }
      },
      properFormat: function (num) {
        if (num == undefined) {
          return "";
        }
        return (num*100).toFixed(2);
      },
  },
  computed:{
    portfolioid(){
      if(this.$store.state.portfolioid==''){
        this.$store.dispatch('updateData');
      }
      return this.$store.state.portfolioid;
    },
    captableList(){
      return this.$store.state.capTabelData;
    },
    mycellname(){
      //let investType = this.dataObj.investtype.trim();
      let investType = this.dataObj.investtype;
      if(investType == 'Equity Interest'||investType == 'Convert To Equity Interest'){
        this.cellName = 'Subscribed investment'
      }else {
        this.cellName = 'Shares Number'
      }
      return this.cellName;
    }
  },
  directives:{
    isedit:{
      inserted:function(el){
        var inp=el.children[0];
        inp.onfocus=function(){
            this.style.borderColor="#ccc";
        };
        inp.onblur=function(){
            this.style.borderColor="transparent";
        }
      }
    }
  }
}
</script>
<style scoped lang="scss">
    .select-fixed{
        margin-top:10px;
    }
    .select-container{
        width:100%;
    }
    .capTable{
        margin-top:20px;
        border-top:2px solid #ddd;
    }
</style>
