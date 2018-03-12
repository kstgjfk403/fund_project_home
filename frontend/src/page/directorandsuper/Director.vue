<template>
<div class="director loan" ref="Director">
    <div class="title">Director</div>
    <div class="loan-table-container">
      <h3>Director</h3>
      <div class="number">
        <label style="font-size:14px;margin-left:15px;">Total Board Representative No.:</label>
        <el-input v-model="totalboard" placeholder="  " style="margin-left:15px;"></el-input>
        <el-button size="mini" type="primary" style="margin-left:15px;" @click="submitNumForm">Save</el-button>
      </div>
      <el-table :data="directorData" border style="float:left;width:auto;">
      <el-table-column prop="directortype" label="Type" width="140">
      </el-table-column>
      <el-table-column prop="staffidstr" label="Director/Observor" width="220">
      </el-table-column>
      <el-table-column prop="directorstartdate" label="Start Date" width="140" :formatter='formatDate'>
      </el-table-column>
      <el-table-column prop="directorenddate" label="End Date" width="140" :formatter='formatDate'>
      </el-table-column>
      <el-table-column label="Opt" width="60" v-if="isDetail!='false'" fixed="right">
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
    <el-dialog title="Director Information" :visible.sync="directorVisible">
      <div class="select-container">
      <el-form :model="directorForm" ref="directorForm" :rules='rules'>
        <div class="select-fixed">
          <el-form-item label="Type">
          <el-select v-model="directorForm.directortype" placeholder="  ">
            <el-option v-for="item in directorDropList.DIRECTOR_TYPE" :key="item.baseId"
            :label="item.baseName" :value="item.baseId"></el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="Staff">
            <el-select v-model="directorForm.staffid" placeholder="  " filterable>
              <el-option v-for="item in directorDropList.IDG_Staff" :key="item.baseId"
              :label="item.baseName" :value="item.baseId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Start Date">
            <el-date-picker v-model="directorForm.directorstartdate" type="date" placeholder=" ">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="End Date" prop='directorenddate'>
            <el-date-picker v-model="directorForm.directorenddate" type="date" placeholder=" ">
            </el-date-picker>
          </el-form-item>
        </div>
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="directorVisible = false" size="mini">Cancel</el-button>
        <el-button type="primary" @click="submitForm('directorForm','add')" size="mini" v-if="buttonShow=='Add'">Add</el-button>
        <el-button type="primary" @click="submitForm('directorForm','update')" size="mini" v-else>Update</el-button>
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
    name:"director",
    mixins:[mix],
    data(){
        var compareDate=(rules, value, callback)=>{
          if(!value){
            callback();
          }else if(value<this.directorForm.directorstartdate){
            callback(new Error('Greater than Start Date'))
          }else{
            callback();
          }
        }
        return {
            totalboard1:'',
            heightObj:'',
            directorVisible:false,
            buttonShow:'Add',
            directorData: [],
            directorForm: {
                directorid: '',
                staffid: "",
                staffidstr: "",
                portfolioid: "",
                directortype: "",
                directorstartdate: '',
                directorenddate: ''
            },
            directorFormEmpty: {
                directorid: '',
                staffid: "",
                staffidstr: "",
                portfolioid: "",
                directortype: "",
                directorstartdate: '',
                directorenddate: ''
            },
            directorDropList:{},
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
           this.scrolltoview(ace,'Director');
        });
        this.reqSelectList();
        this.reqDirectorList();
    },
    methods:{
        reqSelectList(){
            var obj={dictArray:"DIRECTOR_TYPE,IDG_Staff"};
            axioss.reqSelectList(obj).then(res=>{
                this.directorDropList=method.translateFormat(res.data.data);
            })
        },
        reqDirectorList(){
            axioss.reqDirectorList(this.portfolioid).then(res=>{
                console.log(res)
                this.directorData=res.data.data;
            })
        },
        querySingalDirector(id){
            axioss.querySingalDirector(id).then(res=>{
                this.directorForm=res.data.data;
            })
        },
        submitNumForm(){
            var obj={portfolioid:this.portfolioid,totalboardseatno:this.totalboard1}
            axioss.modifyDirectorNum(obj).then(res=>{
                let status=res.data.code,succMes='Save Success',failMes='Save failure';
                let stateCode=this.showToast(status,succMes,failMes);
            })
        },
        submitForm(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var obj=this.directorForm;
                    if(type=='update'){
                        axioss.updateDirector(obj).then(res=>{
                            console.log(res);
                            let status=res.data.code,succMes='Update success',failMes='Update failure';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.directorVisible=false;
                                Object.assign(this.directorForm,this.directorFormEmpty);
                                this.reqDirectorList();
                            }
                        })
                    }else{
                        obj.portfolioid=this.portfolioid;
                        axioss.addDirector(obj).then(res=>{
                            console.log(res);
                            let status=res.data.code,succMes='Create success',failMes='Create failure';
                            let stateCode=this.showToast(status,succMes,failMes);
                            if(stateCode){
                                this.directorVisible=false;
                                Object.assign(this.directorForm,this.directorFormEmpty);
                                this.reqDirectorList();
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
            this.directorVisible=true;
            this.buttonShow='Add';
        },
        handleEdit(index,data){
            this.directorVisible=true;
            this.buttonShow='Update';
            this.querySingalDirector(data.directorid)
        },
        handleDelet(index,data){
            var id=data.directorid;
            this.$confirm("This Opt will permanently delete the file, whether it will continue?", "Notice", {
                confirmButtonText: "OK",
                cancelButtonText: "Cancel",
                type: "warning"
            }).then(() => {
                axioss.deleteDirector(id).then(res => {
                    console.log(res)
                    let status=res.data.code,succMes='Delete success',failMes='Delete failure';
                    let stateCode=this.showToast(status,succMes,failMes);
                    if(stateCode){
                        this.reqDirectorList();
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
        },
        totalboard:{
            get: function () {
                return this.$store.state.storetotalboardseatno
            },
            set: function (newValue) {
                this.totalboard1=newValue;
            }
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
    .select-container{
        width:100%;
    }
    .number{
        padding:10px 0;
    }
</style>
