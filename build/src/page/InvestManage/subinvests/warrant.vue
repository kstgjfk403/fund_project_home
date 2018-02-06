<template>
<div class="warran loan">
    <div class="loan-table-container">
        <h3 class="h3">Warraning</h3>
        <el-table ref="singleTable" :data="warrantData" border style="width:100%;">
            <el-table-column prop="exittype" label="WarrantID"></el-table-column>
            <el-table-column prop="exitmode" label="Type"></el-table-column>
            <el-table-column prop="costrelization" label="Owner Name" width="130" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="round" label="Fund Family"></el-table-column>
            <el-table-column prop="shareexit" label="Fund" :formatter="numberFormat" ></el-table-column>
            <el-table-column prop="fundfamillyname" label="Issue Date"></el-table-column>
            <el-table-column prop="closedate" label="Due Date" width='130'></el-table-column>
            <el-table-column prop="currency" label="No. of Shares"></el-table-column>
            <el-table-column prop="shareremain" label="Cost" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="shareremain" label="Price" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="shareremain" label="Round" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="shareremain" label="Share Type" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="shareremain" label="Cur." :formatter="numberFormat"></el-table-column>
            <el-table-column prop="shareremain" label="Valid" :formatter="numberFormat"></el-table-column>
            <el-table-column prop="vouncher" label="操作" v-if="isDetail!='false'" fixed='right'>
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
    <el-dialog title="Warrant Edit" :visible.sync="warrantVisible">
        <div class="select-container">
            <el-form :model="warrantForm" ref="exitForm" :label-position='labelPosition'>
                <div class="select-fixed">
                    <el-form-item label="Warrant Owner Type:">
                        <el-select v-model="warrantForm.exittype" placeholder="请选择" @change="togetherChange">
                            <el-option v-for="item in exitDropList.DDL_ExitType" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Warrant OwnerName:">
                        <el-input v-model="warrantForm.name" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="Fund Family:">
                        <el-select v-model="warrantForm.exitmode" placeholder="请选择">
                            <el-option v-for="item in ExitModeDataList" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Fund:">
                        <el-select v-model="warrantForm.exitmode" placeholder="请选择">
                            <el-option v-for="item in ExitModeDataList" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Issue Date:">
                        <el-date-picker v-model="warrantForm.closedate" type="date" placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="Due Date:">
                        <el-date-picker v-model="warrantForm.closedate" type="date" placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="Cost:">
                        <el-input v-model="warrantForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="Currency">
                        <el-select v-model="warrantForm.currency" placeholder="请选择">
                            <el-option v-for="item in exitDropList.CURRENCY" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Share Type:">
                        <el-select v-model="warrantForm.currency" placeholder="请选择">
                            <el-option v-for="item in exitDropList.CURRENCY" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Round:">
                        <el-input v-model="warrantForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="No. of Shares:">
                        <el-input v-model="warrantForm.name"></el-input>
                    </el-form-item>
                    <!-- <el-form-item label="Valid:">
                        <el-input v-model="warrantForm.name"></el-input>
                    </el-form-item> -->
                    <el-form-item label="Comments:">
                        <el-input type="textarea" autosize placeholder="请输入内容" v-model="warrantForm.name">
                        </el-input>
                    </el-form-item>
                </div>
            </el-form>
        </div>
        <subCapTable :dataObj='dataObj' :investForm='warrantForm' :buttonShow='buttonShow' v-show="subCapTableShow"></subCapTable>
        <div slot="footer" class="dialog-footer">
            <el-button @click="warrantVisible = false" size='mini'>取 消</el-button>
            <el-button type="primary" size='mini' @click="submitAdd('exitForm','add')" v-if="buttonShow">新建</el-button>
            <el-button type="primary" size='mini' @click="submitAdd('exitForm','update')" v-else>更新</el-button>
        </div>
    </el-dialog>
</div>
</template>
<script>
import axioss from '@/api/axios';
import * as method from "@/api/method";
import subCapTable from "../../capTable/subCapTable"
export default {
    name:"warraning",
    data(){
        return {
            dataObj:'',
            subCapTableShow:true,
            exitId:'',
            isDisabled:false,
            shareList:[],
            warrantVisible:false,
            buttonShow:true,
            warrantData:[],
            labelPosition:'right',
            ExitModeDataList:[],
            sharelistData:[],
            exitType:'',
            exitMode:'',
            exitDropList:'',
            selectlist:{},
            loanData: [],
            warrantForm:{
                exitmode:'',
                exittype:'',
                fundfamillyname:"",
                portfolioid:"",
                closedate:'',
                currency:"USD",
                costrelization:'',
                proceeds:'',
                shareexit:'',
                shareremain:'',
                securitytypeid:'',
                round:""
            },
            warrantFormEmpty:{
                exitmode:'',exittype:'',fundfamillyname:"",
                portfolioid:"",closedate:'',currency:"USD",
                costrelization:'',proceeds:'',shareexit:'',
                shareremain:'',securitytypeid:'',round:""
            }
        }
    },
    updated(){

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
                console.log(res);
                this.warrantData=this.formatTime(res.data.data);//gai
            })
        },
        querySingal(id){
            axioss.querySingal(id).then(res=>{
                this.warrantForm=res.data.data;
            })
        },
        submitAdd(formName,type){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var obj=this.exitForm;
                    if(type=='add'){
                        obj.portfolioid=this.portfolioid;
                        axioss.addExit(obj).then(res=>{
                            console.log(res);
                            if(res.data.code=="SUCCESS"){
                                this.$message({
                                    type:'success',
                                    message: '创建成功'
                                })
                                this.warrantVisible=false;
                                this.reqExitList(this.portfolioid);
                                Object.assign(this.warrantForm,this.warrantFormEmpty)
                            }else{
                                this.$message({
                                    type:'warning',
                                    message: '创建失败'
                                })
                            }
                        })
                    }else{
                        axioss.updateExit(obj).then(res=>{
                            console.log(res);
                            if(res.data.code=="SUCCESS"){
                                this.$message({
                                    type:'success',
                                    message: '更新成功'
                                })
                                this.warrantVisible=false;
                                this.reqExitList(this.portfolioid);
                                Object.assign(this.warrantForm,this.warrantFormEmpty)
                            }else{
                                this.$message({
                                    type:'warning',
                                    message: '更新失败'
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
            this.isDisabled=false;
            this.warrantVisible=true;
            this.buttonShow=true;
        },
        handleEdit(index,data){
            this.isDisabled=true;
            this.warrantVisible=true;
            this.buttonShow=false;
            this.querySingal(data.id);
        },
        deleteExit(index,data){
            this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                axioss.deleteExit(data.id).then(res=>{
                    console.log(res);
                    var status = res.data.code;
                    if (status.toLocaleLowerCase() == "success") {
                    this.reqExitList(this.portfolioid);
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
                    this.warrantForm.securitytypeid=data[i].baseName.securitytypeid;
                    this.warrantForm.round=data[i].baseName.round;
                    this.warrantForm.shareremain=data[i].baseName.shareremain;
                    this.warrantForm.fundfamillyname=data[i].baseName.fundfamillyname;
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