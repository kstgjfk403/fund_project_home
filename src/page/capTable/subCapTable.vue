<template>
    <div class="capTable loan">
        <div class="select-container">
            <div class="select-fixed">
                <el-form :model="capInputForm" ref="capInputForm">
                    <el-form-item label="股东类型">
                        <el-select placeholder="请选择" v-model="capInputForm.sharetype" @change="hanleShareChange">
                            <el-option v-for="item in capSelectList" :label="item.baseName" :value="item.baseId" :key="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="基金" v-if="idg=='IDG'">
                        <el-select placeholder="请选择" v-model="capInputForm.shareowner">
                            <el-option v-for="item in fundList" :label="item.baseName" :value="item.baseId" :key="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="股东名称" v-else>
                        <el-input placeholder="" v-model="capInputForm.shareowner"></el-input>
                    </el-form-item>
                    <el-form-item label="Share Type">
                        <el-select placeholder="请选择" v-model="capInputForm.securitytype">
                            <el-option v-for="item in shareTypeList" :label="item.baseName" :value="item.baseId" :key="item.baseId"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitInputForm('capInputForm','add')">创建股东</el-button>
                        <!-- <el-button type="primary" @click="submitInputForm('capInputForm','update')">更新股东</el-button> -->
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <div class="content">
            <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;">
                <thead>
                    <tr>
                        <th scope="col">股东类型</th>
                        <th scope="col">股东</th>
                        <th scope="col">新增认缴注册资本</th>
                        <th scope="col">新增认缴投资额</th>
                        <th scope="col">当前总认缴资本</th>
                        <th scope="col">当前总投资额</th>
                        <th scope="col">股比</th>
                        <th scope="col">不含ESOP的股比</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in capTableData">
                        <td scope="row" >{{item.sharetype}}</td>
                        <td>{{item.shareowner}}</td>
                        <td>{{item.addshareownedno}}</td>
                        <td>{{item.addcost}}</td>
                        <td><el-input  v-isedit class="inputnone" v-model="item.shareownedno"></el-input></td>
                        <td><el-input  v-isedit class="inputnone" v-model="item.cost"></el-input></td>
                        <td>{{item.proper}}</td>
                        <td>{{item.properwithoutesop}}</td>
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
    props:['dataObj'],
    data(){  
        return {
            heightObj:'',
            idg:'',
            capVisible:false,
            capSelectList:[],
            shareTypeList:[],
            fundList:[],
            capInputForm:{
                sharetype:"",
                shareowner:""
            },
            capTableData:[]
        }
    },
    updated(){
    },
    mounted(){
        this.reqdroplist();
    },
    methods:{
        reqdroplist(){
            var obj={dictArray:"DDL_ShareHolderType,DDL_ShareType,FUND"}
            axioss.reqdroplist(obj).then(res=>{
                console.log(res)
                this.capSelectList=res.data.data[0].baseInfoList;
                this.shareTypeList=res.data.data[1].baseInfoList;
                this.fundList=res.data.data[2].baseInfoList;
            })
        },
        querySingalData(id){
            axioss.querySingalData(id).then(res=>{
                console.log(res)
                this.capTableData=res.data.data.portfoliocaptablevalueList;
                this.$store.dispatch('saveCapTabel',this.capTableData);
            })
        },
        submitInputForm(fromName){
            this.$refs[fromName].validate((validate)=>{
                if(validate){
                    this.dataObj.termsigndate=this.dataObj.termsigndate?this.dataObj.termsigndate:1484150400000;
                    Object.assign(this.dataObj,this.capInputForm);
                    console.log(this.dataObj)
                    axioss.addShareHolder(this.dataObj).then(res=>{
                        console.log(res);
                        if(res.data.code=="SUCCESS"){
                            this.$message({
                                type:'success',
                                message: '创建成功'
                            })
                            //this.capTableData.push(res.data.data);
                            this.querySingalData(this.dataObj.eiid);
                        }else{
                            this.$message({
                                type:'warning',
                                message: '创建失败'
                            })
                        }
                    })
                }else{
                    console.log('err submit')
                }
            })
        },
        saveData(){

        },
        hanleShareChange(val){
            this.idg=val;
        }    
    },
    computed:{
        
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