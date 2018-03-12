<template>
<div class="bonus loan">
    <div class="loan-table-container">
      <h3 class="h3">Dividends</h3>
      <el-table :data="dividEndData" border style="float:left;width:auto;">
        <el-table-column prop="dbid" label="dbid" width="150"></el-table-column>
        <el-table-column prop="closedate" label="Payment Date" width="150" :formatter="formatDate"></el-table-column>
        <el-table-column prop="fundname" label="Fund" width="130"></el-table-column>
        <el-table-column prop="investtype" label="Invest Type" width="150"></el-table-column>
        <el-table-column prop="securitytypestr" label="Share Type" width="150"></el-table-column>
        <el-table-column prop="shareownedno" label="Shares Acquired" width="150" :formatter="numberFormat"></el-table-column>
        <el-table-column prop="costrelization" label="Realised Cost" width="110" :formatter="numberFormat"></el-table-column>
        <el-table-column prop="financialdate" label="Capital Call Date" width="150" :formatter="formatDate"></el-table-column>
        <el-table-column prop="remarks" label="Comment:" width="150"></el-table-column>
        <el-table-column prop="proceeds" label="Gross Proceed:" width="150"></el-table-column>
        <el-table-column prop="currency" label="Currency" width="150"></el-table-column>
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
    <el-dialog title="Dividends" :visible.sync="dividEndVisible">
    <div class="select-container">
      <el-form :model="dividEndForm" :label-position='labelPosition' ref="dividEndForm">
        <div class="select-fixed">
          <el-form-item label="Invest Type">
            <el-select v-model="dividEndForm.investtype" placeholder="  " disabled>
              <el-option v-for="item in investTypeList" :key="item.baseId"
              :label="item.baseName" :value="item.baseId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Payment Date">
            <el-date-picker v-model="dividEndForm.closedate" type="date" placeholder=" " :disabled="isDisable">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="Share Type">
            <el-select v-model="dividEndForm.securitytypeid" placeholder="  ">
              <el-option v-for="item in shareTypeList" :key="item.baseId"
              :label="item.baseName" :value="item.baseId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Fund">
            <el-select v-model="dividEndForm.fundid" placeholder="  " :disabled="isDisable" filterable>
              <el-option v-for="item in fundList" :key="item.baseId"
              :label="item.baseName" :value="item.baseId" @click.native='acqFundName'></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Fund Family">
            <el-select v-model="dividEndForm.fundfamillyname" placeholder="  " :disabled="isDisable" filterable>
              <el-option v-for="item in fundFamilyList" :key="item.baseId"
              :label="item.baseName" :value="item.baseId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="No of Shares">
            <el-input v-model="dividEndForm.shareownedno"></el-input>
          </el-form-item>
          <el-form-item label="Currency">
            <el-select v-model="dividEndForm.currency" placeholder="  ">
              <el-option v-for="item in fundCurrencyList" :key="item.baseId"
              :label="item.baseName" :value="item.baseId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Comment:">
            <el-input type="textarea" v-model="dividEndForm.remarks" autosize placeholder="  ">
            </el-input>
          </el-form-item>
          <el-form-item label="Realised Cost">
            <el-input v-model="dividEndForm.costrelization"></el-input>
          </el-form-item>
          <el-form-item label="Gross Proceed:">
            <el-input type="textarea" autosize placeholder="  " v-model="dividEndForm.proceeds">
            </el-input>
          </el-form-item>
          <el-form-item label="Capital Call Date">
            <el-date-picker v-model="dividEndForm.financialdate" type="date" placeholder=" ">
            </el-date-picker>
          </el-form-item>
        </div>
      </el-form>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dividEndVisible = false" size='mini'>Cancel</el-button>
      <el-button v-if="buttonShow=='add'" type="primary" size='mini' @click="submitForm('dividEndForm','add')">Create</el-button>
      <el-button v-else type="primary" size='mini' @click="submitForm('dividEndForm','update')">Update</el-button>
    </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from '@/api/axios';
import * as method from "@/api/method";
import bus from '@/api/eventbus'
import mix from "@/api/mixin"
export default {
    name:"bonus",
    mixins:[mix],
    data(){
        return {
            subCapTableShow:true,
            isDisable:false,
            dividEndVisible:false,
            labelPosition:'right',
            buttonShow:'add',
            fundFamily:'',
            maxclosedate:'',
            dataObj:{
                eiid:"",
                portfolioid:"",
                fundid:"",
                investtype:"",
                closedate: '',
                maxclosedate: '',
                round: "",
                securitytypeid:''
            },
            dividEndData: [
            ],
            translateObj:{
                Common:2,
                Preferred:3,
                'Equity Interest':6,
                 Distribution:15,
                'Capital Call':14
            },
            investTypeList:[{baseId:'Dividends',baseName:'Dividends'}],
            fundFamilyList:'',
            fundCurrencyList:[],
            fundList:[],
            dividEndForm:{
                investtype:'Dividends',
                closedate:'',
                fundfamillyname:'',
                securitytypeid:'',
                shareownedno:'',
                currency:'USD',
                remarks:'',
                proceeds:'',
                costrelization: '',
                fundname:'',
                fundid:'',
                financialdate:''
            },
            emptydividEndForm:{
                investtype:'Dividends',closedate:'',fundfamillyname:'',
                securitytypeid:'',shareownedno:'',currency:'USD',
                remarks:'',proceeds:'',costrelization: '',
                fundname:'',fundid:'',financialdate:''
            },
            shareTypeList:[
                {baseId:'2',baseName:'Common'},
                {baseId:'3',baseName:'Preferred'},
                {baseId:'6',baseName:'Equity Interest'},
                {baseId:'9',baseName:'ESOP'},
                {baseId:'10',baseName:'Dividends'},
            ]
        }
    },
    updated(){

    },
    mounted(){
        this.invesDropList();
        this.reqDividEndList();
    },
    methods:{
        invesDropList(){
            var obj={dictArray:"FUNDFAMILY,DDL_SecurityType,CURRENCY,FUND"};
            axioss.invesDropList(obj).then(res=>{
                //console.log(res)
                this.fundFamilyList=res.data.data[0].baseInfoList;
                this.fundCurrencyList=res.data.data[2].baseInfoList;
                this.fundList=res.data.data[3].baseInfoList;
            })
        },
        reqDividEndList(id){
            var portfolioid=id||this.portfolioid;
            axioss.reqDividEndList(portfolioid).then(res=>{
                console.log(res)
                this.dividEndData=res.data.data;
            })
        },
        querySingalBonusData(id){
            axioss.querySingalBonusData(id).then(res=>{
                console.log(res)
                this.dividEndForm=res.data.data;
            })
        },
        submitForm(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var obj=this.dividEndForm;
                    if(type=="add"){
                        obj.portfolioid=this.portfolioid;
                        console.log(obj)
                        axioss.addBonus(obj).then(res=>{
                            console.log(res)
                            let status=res.data.code,succMes='Create success',failMes='Create failure';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.dividEndVisible=false;
                                Object.assign(this.dividEndForm,this.emptydividEndForm);
                                this.reqDividEndList();
                            }
                        })
                    }else{
                        axioss.updateBonus(obj).then(res=>{
                            let status=res.data.code,succMes='Update success',failMes='Update failure';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.dividEndVisible=false;
                                Object.assign(this.dividEndForm,this.emptydividEndForm);
                                this.reqDividEndList();
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
            this.subCapTableShow=false;
            this.isReqLoanToEquity=true;
            this.isDisable=false;
            this.dividEndForm={currency:'USD',investtype:'Dividends'};
            this.dividEndVisible=true;
            this.buttonShow='add';
        },
        handleEdit(index,data){
            this.subCapTableShow=true;
            this.isReqLoanToEquity=false;
            this.isDisable=true;
            this.dividEndVisible=true;
            this.buttonShow='eidt';
            this.dividEndForm.investtype=data.investtype;
            this.querySingalBonusData(data.dbid);
        },
        acqFundName(e){
            this.dividEndForm.fundname=e.target.textContent
        },
        handleDelet(index,data){
            var id=data.eiid;
            this.$confirm("This Opt will permanently delete the file, whether it will continue?","Notice", {
                confirmButtonText: "OK",
                cancelButtonText: "Cancel",
                type: "warning"
            }).then(() => {
                axioss.deletInvest(id).then(res => {
                    var status = res.data.code;
                    if (status.toLocaleLowerCase() == "success") {
                    this.reqinvestList();
                    this.$message({
                        type: "success",
                        message: "Delete success!"
                    });
                    } else {
                    this.$message({
                        type: "error",
                        message: "Delete failure!"
                    });
                    }
                });
            })
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
	.bonus{
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
