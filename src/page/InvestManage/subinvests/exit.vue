<template>
<div class="exit loan">
    <div class="loan-table-container">
        <h3 class="h3">Exit</h3>
        <el-table ref="singleTable" :data="exitData" border>
            <el-table-column prop="costrelization" label="costRelization"></el-table-column>
            <el-table-column prop="round" label="round"></el-table-column>
            <el-table-column prop="shareownedno" label="shares"></el-table-column>
            <el-table-column prop="fundfamillyname" label="Fund Family"></el-table-column>
            <el-table-column prop="closedate" label="closeDate"></el-table-column>
            <el-table-column prop="vouncher" label="操作" v-if="isDetail">
                <template slot-scope="scope">
                    <i class="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></i>
                    <i class="el-icon-delete" @click="deleteExit(scope.$index, scope.row)"></i>
                </template>
            </el-table-column>
        </el-table>
        <div class="table-foot">
            <i class="el-icon-circle-plus" @click="handleAdd" v-if="isDetail"></i>
        </div>
    </div>
    <el-dialog title="Exit Edit" :visible.sync="ExitVisible">
        <div class="select-container">
            <el-form :model="exitForm" ref="exitForm" :label-position='labelPosition'>
                <div class="select-fixed">
                    <el-form-item label="Exit Type">
                        <el-select v-model="exitForm.exittype" placeholder="请选择" @change="togetherChange">
                            <el-option v-for="item in exitDropList.DDL_ExitType" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Exit Mode">
                        <el-select v-model="exitForm.exitmode" placeholder="请选择">
                            <el-option v-for="item in ExitModeDataList" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Payment Date">
                        <el-date-picker v-model="exitForm.closedate" type="date" placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="Fund Family">
                        <el-select v-model="exitForm.fundfamillyname" placeholder="请选择" @change="creatShareList" filterable>
                            <el-option v-for="item in exitDropList.FUNDFAMILY" :key="item.baseId"
                             :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Currency">
                        <el-select v-model="exitForm.currency" placeholder="请选择">
                            <el-option v-for="item in exitDropList.CURRENCY" :key="item.baseId"
                            :label="item.baseName" :value="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div v-if="exitForm.exittype=='Loan'">
                    <el-form-item label="costRelization ">
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
        <div slot="footer" class="dialog-footer">
            <el-button @click="ExitVisible = false" size='mini'>取 消</el-button>
            <el-button type="primary" size='mini' @click="submitAdd('exitForm','add')" v-if="buttonShow">新建</el-button>
            <el-button type="primary" size='mini' @click="submitAdd('exitForm','update')" v-else>更新</el-button>
        </div>
    </el-dialog>
</div>
</template>
<script>	
import axioss from '@/api/axios';
import * as method from "@/api/method";
export default {
    name:"Exit",
    data(){  
        return {
            exitId:'',
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
                currency:"USD",
                costrelization:'',
                proceeds:'',
                shareexit:'',
                shareremain:'',
                securitytypeid:'',
                round:""
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
                this.exitData=res.data.data;
            })
        },
        querySingal(id){
            axioss.querySingal(id).then(res=>{
                this.exitForm=res.data.data;
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
                                this.ExitVisible=false;
                                this.reqExitList(this.portfolioid);
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
                                this.ExitVisible=false;
                                this.reqExitList(this.portfolioid);
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
            this.ExitVisible=true;
            this.buttonShow=true;
        },
        handleEdit(index,data){
            this.ExitVisible=true;
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
            return this.$store.state.isDetail;
        }
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