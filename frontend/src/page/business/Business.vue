<template>
<div class="financial loan business1" ref="Business">
    <div class="title">Business Update</div>
    <div class="loan-table-container business">
        <h3>Bov</h3>
        <el-table :data="businessTableData" border style="float:left;width:auto;">
          <el-table-column prop="updatedate" label="Update Date" width="140" :formatter='formatDate'>
          </el-table-column>
          <el-table-column prop="headcount" label="Head Count" width="140">
          </el-table-column>
          <el-table-column prop="wbstatus" label="WBStatus" width="140">
          </el-table-column>
          <el-table-column prop="lastwbstatus" label="LastWBStatus" width="140" v-if="columnHidden">
          </el-table-column>
          <el-table-column prop="companybackground" label="Company Background" width="190">
          </el-table-column>
          <el-table-column prop="operationandbusinessdevelopment" label="Development" width="140">
          </el-table-column>
          <el-table-column prop="valuation" label="Valuation" width="140">
          </el-table-column>
          <el-table-column prop="management" label="Management" width="140">
          </el-table-column>
          <el-table-column prop="disposal" label="Disposal" width="140">
          </el-table-column>
          <el-table-column prop="comparables" label="Comparables" width="140">
          </el-table-column>
          <el-table-column prop="updatebusiness" label="Update Business" width="140">
          </el-table-column>
          <el-table-column label="Opt" width="60" fixed='right' v-if="isDetail!='false'">
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
    <el-dialog title="Business Information" :visible.sync="businessVisible">
        <div class="select-container">
        <el-form :model="businessFormData" ref="businessForm">
            <div class="select-fixed">
            <el-form-item label="Porfolio Id:" hidden="true">
              <el-input v-model="businessFormData.portfolioid" type="hidden"></el-input>
            </el-form-item>
            <el-form-item label="Head Count:">
                <el-input v-model="businessFormData.headcount"></el-input>
            </el-form-item>

            <el-form-item label="Update Date:">
                <el-date-picker v-model="businessFormData.updatedate" type="date" placeholder=" ">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="White Book Status:">
            <el-select v-model="businessFormData.wbstatus" placeholder="">
                <el-option label="Actively involved investments" value="Actively involved investments"></el-option>
                <el-option label="Sell & Liquidated" value="Sell & Liquidated"></el-option>
                <el-option label="The End" value="The End"></el-option>
                <el-option label="Listed portfolio" value="Listed portfolio"></el-option>
                <el-option label="Other Investments" value="Other Investments"></el-option>
            </el-select>
            </el-form-item>

            
            <el-form-item label="Valuation">
                <el-input v-model="businessFormData.valuation" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Management:">
                <el-input v-model="businessFormData.management" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Disposal">
                <el-input v-model="businessFormData.disposal" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Comparables">
                <el-input v-model="businessFormData.comparables" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Update Business">
                <el-input v-model="businessFormData.updatebusiness" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Background" style="width:100%;" class='businessfull'>
                <el-input v-model="businessFormData.companybackground" autosize type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="Development" style="width:100%;" class='businessfull'>
                <el-input v-model="businessFormData.operationandbusinessdevelopment" autosize type="textarea"></el-input>
            </el-form-item>
            </div>
        </el-form>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="businessVisible = false" size="mini">Cancel</el-button>
            <el-button type="primary" @click="submitbusinessForm('businessForm','add')" size="mini" v-if="buttonShow=='Add'">Add</el-button>
            <el-button type="primary" @click="submitbusinessForm('businessForm','update')" size="mini" v-else>Update</el-button>
        </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from '@/api/axios';
import bus from '@/api/eventbus'
import mix from "@/api/mixin"
export default {
    name:"business",
    mixins:[mix],
    data(){
        return {
            heightObj:'',
            columnHidden:false,
            businessVisible:false,
            buttonShow:'Add',
            businessTableData: [
            ],
            emptybusinessFormData:{
              bovid:'',
              portfolioid: '',
              updatedate: '',
              companybackground: '',
              operationandbusinessdevelopment: '',
              valuation: '',
              createdby: '',
              modifiedby: '',
              modifieddate: '',
              approved: '',
              management: '',
              disposal: '',
              comparables: '',
              updatebusiness: '',
              headcount: '',
              wbstatus: ''
            },
            businessFormData: {
              bovid:'',
              portfolioid: '',
              updatedate: '',
              companybackground: '',
              operationandbusinessdevelopment: '',
              valuation: '',
              createdby: '',
              modifiedby: '',
              modifieddate: '',
              approved: '',
              management: '',
              disposal: '',
              comparables: '',
              updatebusiness: '',
              headcount: '',
              wbstatus: ''
            },
            fundFamilyList:[],
            fundCurrencyList:[],
            fundList:[],
            wbStatusList:[
              {statusName:'Actively involved investments'},
              {statusName:'Sell & Liquidated'},
              {statusName:'The End'},
              {statusName:'Listed portfolio'},
              {statusName:'Other Investments'},
            ]
        }
    },
    updated(){

    },
    mounted(){
        bus.$on('toScorll',(ace)=>{
           this.scrolltoview(ace,'Business');
        });
      this.invesDropList();
      this.reqPortfolioBovList();
    },
    methods:{
      invesDropList(){
        var obj={dictArray:"FUNDFAMILY,DDL_SecurityType,CURRENCY,FUND"};
        axioss.invesDropList(obj).then(res=>{
          //console.log(res)
          this.fundFamilyList=res.data.data[0].baseInfoList;
          this.fundCurrencyList=res.data.data[2].baseInfoList;
          this.fundList=res.data.data[3].baseInfoList;
          this.wbStatusList = [
            {statusName:'Actively involved investments'},
            {statusName:'Sell & Liquidated'},
            {statusName:'The End'},
            {statusName:'Listed portfolio'},
            {statusName:'Other Investments'},
          ]
        })
      },
      reqPortfolioBovList(id){
        var portfolioid=id||this.portfolioid;
        axioss.reqPortfolioBovList(portfolioid).then(res=>{
          this.businessTableData=res.data.data;
        })
      },
      queryPortfolioBovData(id){
        var portfolioid=id||this.portfolioid;
        axioss.queryPortfolioBovsData(portfolioid).then(res=>{
          console.log(res);
          this.businessFormData=res.data.data;
        })
      },
      submitbusinessForm(formName,type){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var obj=this.businessFormData;
            if(type=="add"){
              obj.portfolioid=this.portfolioid;
              console.log(obj);
              axioss.addPortfolioBov(obj).then(res=>{
                console.log(res)
                let status=res.data.code,succMes='Create success',failMes='Create failure';
                let stateCode=this.showToast(status,succMes,failMes);
                if(stateCode){
                  this.businessVisible=false;
                  Object.assign(this.businessFormData,this.emptybusinessFormData);
                  this.reqPortfolioBovList();
                }
              })
            }else{
              axioss.updatePortfolioBov(obj).then(res=>{
                let status=res.data.code,succMes='Update success',failMes='Update failure';
                let stateCode=this.showToast(status,succMes,failMes);
                if(stateCode){
                  this.businessVisible=false;
                  Object.assign(this.businessFormData,this.emptybusinessFormData);
                  this.reqPortfolioBovList();
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
            this.businessVisible=true;
            this.buttonShow='Add';
            Object.assign(this.businessFormData,this.emptybusinessFormData);
        },
        handleEdit(index,data){
            this.businessVisible=true;
            this.buttonShow='Update';
          this.queryPortfolioBovData(data.bovid);
        },
        handleDelet(index,data){
            var id=data.bovid;
            this.$confirm("This Opt will permanently delete the file, whether it will continue?", "Notice", {
                confirmButtonText: "OK",
                cancelButtonText: "Cancel",
                type: "warning"
            }).then(() => {
                axioss.deletPortfolioBov(id).then(res => {
                    var status = res.data.code;
                    if (status.toLocaleLowerCase() == "success") {
                      this.$message({
                          type: "success",
                          message: "Delete success!"
                      });
                      this.reqPortfolioBovList();
                    } else {
                    this.$message({
                        type: "error",
                        message: "Delete failure!"
                    });
                    }
                });
            })
        },

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
    .loan-table-container .table-foot{
        background:#eee;
        padding:5px 0;
        padding-left:10px;
    }
    .select-container{
        width:100%;
    }

</style>
