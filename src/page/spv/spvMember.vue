<template>
<div class="spv-member-list">
    <Header></Header>
    <div class="spv-list-container">
        <div class="spv-left">
            <el-card class="box-card" style="height:560px;">
                <div slot="header" class="clearfix">
                    <span>SPV</span>
                    <el-button @click="changeShow('add')" style="float: right; padding: 3px 0" type="text">Add</el-button>
                </div>
                <el-tree :data="spvTreeData"
                    highlight-current 
                    ref="spvTree" 
                    :props="defaultProps"
                    @node-click="handleNodeClick"
                    node-key="id"
                    :default-expanded-keys="['S00000']"
                    default-expand-all>
                </el-tree>
            </el-card>
        </div>
        <div class="spv-right" v-if="allListShow">
            <div class="spv-right-top">    
                <div class="clearfix">
                    <span>Base Information</span>
                </div>
                <el-table :data="spvData" border stripe style="width: 100%">
                    <el-table-column prop="spvtypestr" label="Type"></el-table-column>
                    <el-table-column prop="spvname" label="SPV Name"></el-table-column>
                    <el-table-column prop="spvnamechi" label="spv中文名"></el-table-column>
                    <el-table-column prop="regaddress" label="Registor Address"></el-table-column>
                    <el-table-column prop="remarks" label="Remarks"></el-table-column>
                    <el-table-column label="Operation">
                        <template slot-scope="scope">
                            <i class="el-icon-delete" @click="deleteSpv"></i>
                            <i class="el-icon-edit" @click="changeShow('update')"></i>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            
            <div class="spv-right-bottom">    
                <div class="clearfix">
                    <div class="form-container">
                        <el-form>
                            <el-form-item label="Date">                                                   
                                <el-date-picker type="date" placeholder="选择日期" v-model="addShareData.capdatestr" value-format="yyyy-MM-dd"></el-date-picker>
                                <!-- <el-date-picker type="date" placeholder="选择日期" v-model="datetime" v-show="false" value-format="yyyy-MM-dd"></el-date-picker>-->
                                <!-- v-model="addShareData.capdate"-->
                            </el-form-item>
                        </el-form>
                    </div>
                    <el-button style="float: left; margin:3px 0 0 10px" size="medium" type="primary" @click="isShareShow">Add</el-button>
                </div>
                <table class="table table-hover table-bordered table-condensed" id="table1" style="table-layout:fixed;">
                    <thead>
                        <tr>
                            <th scope="col">日期</th>
                            <th scope="col">股东名称</th>
                            <th scope="col">类别</th>
                            <th scope="col">股东类别</th>
                            <th scope="col">认缴金额 </th>
                            <th scope="col">占比</th>
                            <th scope="col">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="item in shareListData">
                            <td scope="row">{{item.capdatestr}}</td>
                            <td>{{item.stockholdername}}</td>
                            <td>{{item.type}}</td>
                            <td>{{item.stockholdertypestr}}</td>
                            <td>{{item.lpaamt}}</td>
                            <td>{{item.lpaamtprop}}</td>
                            <td>                           
                                <i class="el-icon-delete" @click="deleteShare(item.capdatestr)"></i>
                                <i class="el-icon-search" @click="queryShare(item.spvid,item.capdatestr)"></i>
                            </td>
                        </tr>   
                    </tbody>
                </table>
            </div>
        </div>
        <div class="position-container spv-right" v-else>
            <!-- <div style="position:relative;margin-top:5px;overflow-x:auto;width:82%;margin:0 auto;"> -->
                <div class="table-responsive">
                <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;">
                    <thead>
                        <tr>
                        <th scope="col">Type    
                            <!-- <el-input placeholder="请输入Programe Name" v-model="abbnameval" @input.native="searchData('abbname',abbnameval)"></el-input> -->
                        </th>
                        <th scope="col">SPV Name    
                            <!-- <el-input class="search" placeholder="请输入Programe Name" v-model="abbnamechival" @input.native="searchData('abbnamechi',abbnamechival)"></el-input> -->
                        </th>
                        <th scope="col">spv中文名
                            <!-- <el-input class="search" placeholder="请输入Programe Name" v-model="fullnameval" @input.native="searchData('fullname',fullnameval)"></el-input> -->
                        </th>
                        <th scope="col">Registor Address
                            <!-- <el-input class="search" placeholder="请输入Programe Name" v-model="fullnamechival" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>-->
                        </th>
                        <th scope="col">Remarks
                            <!-- <el-input class="search" placeholder="请输入Programe Name" v-model="fullnamechival" @input.native="searchData('fullnamechi',fullnamechival)"></el-input>-->
                        </th>
                        <th scope="col">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="item in allSpvList">
                            <td>{{item.spvtypestr}}</td>
                            <td @click="querySpvAll(item.spvid)" style="color:#00a1e9;cursor:pointer;">{{item.spvname}}</td>
                            <td>{{item.spvnamechi}}</td>
                            <td>{{item.regaddress}}</td>
                            <td>{{item.remarks}}</td>
                            <td>                           
                                <i class="el-icon-delete" @click="deleteSpvAll(item.spvid)"></i>
                                <i class="el-icon-edit" @click="changeShow('update',item.spvid)"></i>
                            </td>
                        </tr>   
                    </tbody>
                </table>
                <div class="pagination-container">
                    <el-pagination background layout="prev, pager, next,jumper,total" :total="allcount" :page-size='10' :current-page.sync="pageCurrent" @current-change="handleCurrentChange" style="position:absolute;left:50%;bottom:25px;transform: translate(-50%);"></el-pagination>    
                </div>
            </div>
            </div>
    </div>
    <el-dialog title="ADD Shareholder" :visible.sync="shareView">
        <div class="edit-input">
            <el-form :model="addShareData" ref="shareForm" :inline="true">
                <div class="top">
                    <el-form-item label="日期">
                        <el-input v-model="addShareData.capdatestr" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="投资类别">
                        <el-select v-model="addShareData.stockholdertype" placeholder="请选择活动区域" @change="togetherChange">
                            <el-option :key='item.baseId'
                                :label="item.baseName" 
                                :value="item.baseId"
                                v-for="item in dropType"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="金额(万元)">
                        <el-input v-model="addShareData.lpaamt"></el-input>
                    </el-form-item>     
                </div>
                <div class="bottom">
                    <el-form-item label="基金/spv">
                        <el-select v-model="addShareData.stockholderid" placeholder="请选择活动区域" :disabled="isDisabled2">
                            <el-option v-for='item in fundspvData'
                            :key='item.baseId' :label='item.baseName' :value="item.baseId" @click.native="reqValue($event)"></el-option> 
                        </el-select>
                    </el-form-item>
                    <el-form-item label="股东名称">
                        <el-input v-model="addShareData.stockholdername" :disabled='isDisabled'></el-input>
                    </el-form-item>
                    <el-form-item label="CODE">
                        <el-input v-model="addShareData.stockholdercode" :disabled="isDisabled"></el-input>
                    </el-form-item>    
                </div>
                <div class="bottom">
                    <el-form-item label="股东Type">
                        <el-select v-model="addShareData.type" v-if="whichTypeShow">
                            <el-option value='GP'></el-option>
                            <el-option value='LP'></el-option> 
                        </el-select>
                        <el-input v-model="addShareData.type" disabled v-else></el-input>
                    </el-form-item>
                </div>
                <el-form-item style="float:right;text-align:right;">
                    <el-button type="primary" size="mini" @click="localAdd">add</el-button>
                </el-form-item>
            </el-form>
        </div>
        <table class="table table-hover table-bordered table-condensed" style="table-layout:fixed;">
            <thead>
                <tr>
                    <th scope="col">日期</th>
                    <th scope="col">股东名称</th>
                    <th scope="col">股东类别</th>
                    <th scope="col">投资类别</th>
                    <th scope="col">认缴金额 </th>
                    <th scope="col">占比</th>
                    <th scope="col">操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in addShareShow">
                    <td scope="row">{{item.capdatestr}}</td>
                    <td>{{item.stockholdername}}</td>
                    <td>{{item.type}}</td>
                    <td>{{item.stockholdertypestr}}</td>
                    <td><el-input v-model="item.lpaamt" v-isedit class="inputnone"></el-input></td>
                    <td>{{item.lpaamtprop}}</td>
                    <td>                           
                        <i class="el-icon-delete" @click="addDelete(item.capid)"></i>
                    </td>
                </tr>   
            </tbody>
        </table>
        <el-button type="primary" size="mini" @click="submitShare('shareForm')" style="float:right;">创建</el-button>
    </el-dialog>
    <el-dialog title="ADD Spv" :visible.sync="spvView">
        <div class="edit-input">
            <el-form :model="addSpvData" ref="addSpvForm" :inline="true">
                <el-form-item label="SPV Name">
                    <el-input v-model="addSpvData.spvname"></el-input>
                </el-form-item>
                <el-form-item label="spv中文名">
                    <el-input v-model="addSpvData.spvnamechi"></el-input>
                </el-form-item>
                <el-form-item label="Address">
                    <el-input v-model="addSpvData.regaddress"></el-input>
                </el-form-item>
                <el-form-item label="Remarks">
                    <el-input v-model="addSpvData.remarks"></el-input>
                </el-form-item>
                <el-form-item label="Type">
                    <el-select v-model="addSpvData.spvtype" placeholder="请选Type">
                        <el-option v-for='item in dropSpvType'
                        :key='item.baseId' :label='item.baseName' :value="item.baseId"></el-option> 
                    </el-select>
                </el-form-item>
            </el-form>
        </div>
        <div class="spv-build" style="text-align:center;">
            <el-button size='mini' style="background:#eee;color:#666;" @click="submitSpv('addSpvForm','add')" v-if="isShow">Build</el-button>
            <el-button size='mini' style="background:#eee;color:#666;" @click="submitSpv('addSpvForm','update')" v-else>Update</el-button>
        </div> 
    </el-dialog>
</div>
</template>
<script>
import Header from "../../components/common/Header";	
import axioss from '../../api/axios';
import * as method from "../../api/method";
export default {
    name:"spvlist",
    data:function(){  
        return {
            pageCurrent: 1,
            pageCount: 10,
            allcount: 0,
            pagesize:2,
            isDisabled2:false,
            isDisabled:false,
            canAdd:false,
            canShareAdd:false,
            spvId:'S00001',
            formDate:'',
            isShow:true,
            spvView:false,
            shareView:false,
            whichTypeShow:true,
            allListShow:false,
            allSpvList:[],
            labelposition:"right",
            parentid:'',
            spvTreeData: [],
            defaultProps: {
                children: 'children',
                label: 'label'
            },
            spvData: [
                {
                    spvname:'no data',
                    spvnamechi:'no data',
                    regaddress:'no data',
                    remarks:'no data',
                    spvtype:''
                }
            ],
            shareListData: [],
            resetaddSpvData:{
                spvname:'',
                spvnamechi:'',
                regaddress:'',
                remarks:'',
                spvlevel:'',
                parentspvid: '',
                isleaf:'',
                spvtype:''
            },
            addSpvData:{
                spvname:'',
                spvnamechi:'',
                regaddress:'',
                remarks:'',
                spvlevel:'',
                parentspvid: '',
                isleaf:'',
                spvtype:''
            },
            resetaddShareData:{
                capid:0,                
                capdatestr:"",
                spvid: "",
                stockholdername:"",
                stockholdertype:"",
                stockholderid:"",
                stockholdercode:"",
                lpaamt:"",
                lpaamtdate:"",
                lpaamtprop:'',
                type:''                  
            },
            addShareData:{
                capid:0,                
                capdatestr:"",
                spvid: "",
                stockholdername:"",
                stockholdertype:"",
                stockholderid:"",
                stockholdercode:"",
                lpaamt:"",
                lpaamtdate:"",
                lpaamtprop:'',
                type:''                  
            },
            addShareShow:[],
            addShareShow2:[],
            fundspvData:'',
            dropFundData:'',
            dropSpvData:'',
            dropType:'',
            dropSpvType:[
                {baseID:'',baseName:''},
                {baseID:'',baseName:''},
                {baseID:'',baseName:''}
            ],
            rules:{
                region:[
                    { required: true, message: '请选择活动区域', trigger: 'change' }
                ]
            }
        }   
    },
    updated(){
        this.$nextTick(function(){
            method.mc('table1',0,0,0);
        })
    },
    mounted:function(){
        //console.log("mounted")
       var obj={pageIndex:this.pageCurrent,pageCount:2,spvname:'SPV'}
        this.reqSpvallList(obj);
        this.reqSpvList();
        this.reqSharedrop();
        this.reqShareList();
        //this.querySpv(this.spvId);
        this.reqSpvallList();  
    },
    methods:{
        querySpvAll(id){
            this.spvId=id;
            this.querySpv(id);
            this.reqShareList(id);
            this.allListShow=true;
        },
        handleCurrentChange(){
            var obj={pageIndex:this.pageCurrent,pageCount:2,spvname:'SPV'}
            this.reqSpvallList(obj);
        }, 
        reqSpvList(){
            axioss.reqSpvList().then(res=>{
                console.log(res)
                this.spvTreeData.splice(0,1,res.data.data)
            })
        },
        reqSpvallList(obj){
            axioss.reqSpvallList(obj).then(res=>{
                this.allSpvList=res.data.data;
                this.allcount=res.data.count;
            })
        },
        deleteSpvAll(id){
            axioss.deleteSpv(id).then(res=>{
                this.reqSpvList();
                var obj={pageIndex:this.pageCurrent,pageCount:2,spvname:'SPV'}
                this.reqSpvallList(obj)
            })
        },
        querySpv(spvId){
            axioss.querySpv(spvId).then(res=>{
                this.whichType(res.data.data.spvtype);
                this.spvData.splice(0,1,res.data.data);
                //this.allListShow=true;
                //console.log(res)
            })
        },
        queryShare(spvid,capdatestr){
            var obj={spvid:spvid,capdatestr:capdatestr}
            //console.log(obj)
            this.addShareData.capdatestr=capdatestr;
            axioss.queryShare(obj).then(res=>{
                //console.log(res);
                this.addShareShow=res.data.data;
                this.shareView=true;
            })
        },
        handleNodeClick(dataNode,node,treeCom){
            //console.log(node)
            this.canAdd=true;
            this.spvId=node.data.id;
            this.addSpvData.isleaf=node.isLeaf;
            this.addSpvData.spvlevel=node.level;
            this.addSpvData.parentspvid=node.data.id;
            this.parentid=node.parent.data.id;
            this.reqShareList();
            this.querySpv(this.spvId);
            if(node.data.id=='S00000'){
                this.allListShow=!this.allListShow;
            }else{
                this.allListShow=true;
            }
        },
        submitSpv(formName,whichOper){
            if(whichOper=='add'){
                this.$refs[formName].validate((valid) => {
                    if (valid) {      
                        if(this.addSpvData.hasOwnProperty('spvid')){
                            delete this.addSpvData.spvid;
                        }
                        this.addSpvData.spvlevel++;
                        this.addSpvData.isleaf="Y";
                        //console.log(this.addSpvData)
                        axioss.addSpv(this.addSpvData).then(res=>{
                            //console.log(res)
                            if(res.data.code=='SUCCESS'){
                                this.reqSpvList();
                                Object.assign(this.addSpvData,this.resetaddSpvData);//清空对象的值。
                                this.$message({
                                    type:'success',
                                    message:"Succeed to add spv!!!"
                                })
                            }else{
                                this.$message({
                                    type:'warning',
                                    message:"Failed to add spv!!!"
                                })
                            }
                            this.spvView=false;
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }else{
                this.$refs[formName].validate((valid) => {
                    //console.log(this.addSpvData);
                    if (valid) {
                        //console.log(this.addSpvData)   
                        axioss.updateSpv(this.addSpvData).then(res=>{
                            if(res.data.code=='SUCCESS'){
                                this.reqSpvList();
                                this.querySpv(this.spvId);
                                this.reqShareList(this.spvId);
                                this.allListShow=true;
                                this.$message({
                                    type:'success',
                                    message:"Succeed to update spv!!!"
                                })
                            }else{
                                this.$message({
                                    type:'warning',
                                    message:"Failed to update spv!!!"
                                })
                            }
                            this.spvView=false;
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }    
        },
        submitShare(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    axioss.updateShare(this.addShareShow).then(res=>{
                        this.reqShareList();
                        this.addShareShow=[];
                        this.shareView=false;
                        //Object.assign(this.addShareData,this.resetaddShareData);
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        deleteShare(capdate){
            var obj={};
            obj.spvid=this.spvId,
            obj.capdatestr=capdate;
            axioss.deleteShare(obj).then(res=>{
                //console.log(res);
                this.reqShareList();
            })
        },
        addDelete(id){
            for(var i=0;i<this.addShareShow.length;i++){
                if(this.addShareShow[i].capid==id){
                    this.addShareShow.splice(i,1);
                } 
            }     
        },
        localAdd(){
            this.addShareData.lpaamtdate=this.addShareData.capdatestr
            this.addShareData.spvid=this.spvId;
            this.addShareData.capid=this.addShareData.capid+1;
            var datastr=JSON.stringify(this.addShareData);
            var dataobj=JSON.parse(datastr);
            this.addShareShow.push(dataobj);
            this.calculate(this.addShareShow);
        },
        calculate(data){
            var sum=0;
            for(var i=0;i<data.length;i++){
                sum+=parseFloat(data[i].lpaamt);
            }
            for(var i=0;i<data.length;i++){
                data[i].lpaamtprop=(parseFloat(data[i].lpaamt)/sum*100).toFixed(2);
                //console.log(parseFloat(data[i].lpaamt)/sum)
            }
        },
        changeShow(whichOper,id){
            var spvid=id||this.spvId;
            console.log(spvid)
            if(!this.canAdd&&whichOper=='add'){
                alert("请选择项目");
                return;
            }
            this.spvView=true;
            this.isShow=whichOper=='add'?true:false;
            this.spvId=spvid;
            if(whichOper=='update'){
                axioss.querySpv(spvid).then(res=>{
                    //console.log(res);
                    this.addSpvData=res.data.data;
                })
            }
        },
        isShareShow(){
            if(this.addShareData.capdatestr){
                this.shareView=true;
            }else{
                alert('请选则变更日期！！！')
            } 
        },
        deleteSpv(){   
            axioss.deleteSpv(this.spvId).then(res=>{
                this.reqSpvList();
                console.log(this.addSpvData.parentspvid)
                this.querySpv(this.parentid);
                axioss.reqShareList(this.parentid).then(res=>{
                    console.log(res)
                    this.shareListData=res.data.data;
                })
            })
        },
        reqShareList(id){
            var spvid=id||this.spvId;
            axioss.reqShareList(spvid).then(res=>{
                //console.log(res)
                this.shareListData=res.data.data;
            })
        },
        reqSharedrop(){
            axioss.reqdroplist({dictArray:"FUND,SPV,DDL_SpvInvestorType,DDL_SpvType"}).then(res=>{
                this.dropFundData=res.data.data[0].baseInfoList;
                this.dropSpvData=res.data.data[1].baseInfoList;
                this.dropType=res.data.data[2].baseInfoList;
                this.dropSpvType=res.data.data[3].baseInfoList;
                //console.log(res)
            })    
        },
        togetherChange(id){
            this.addShareData.stockholderid='';
            this.addShareData.stockholdername='';
            this.addShareData.stockholdercode='';
            if(id=='1'){
                this.fundspvData=this.dropFundData;
                this.isDisabled=true;
            }else if(id=='2'){
                this.fundspvData=this.dropSpvData;
                this.isDisabled=true;
            }else{
                this.isDisabled=false;
            }
            this.isDisabled2=!this.isDisabled;         
        },
        reqValue(event){
            //console.log(event.target.textContent)
            this.addShareData.stockholdername=event.target.textContent;
        },
        whichType(type){//在handleclick中调用。
            if(type=="2"||type=="3"){
                this.whichTypeShow=false;
                this.addShareData.type='stockholder';
            }else{
                this.whichTypeShow=true;
                this.addShareData.type='';
            }
        }
    },
    watch:{
        'addShareShow':{
            handler(newValue,oldValue){
                console.log(newValue);
                if(newValue!=[]){
                    this.calculate(this.addShareShow);
                }    
            },
            deep:true
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
    },
    components:{
        Header
    } 	
}
</script>
<style scoped lang="scss">
	.spv-member-list{
        width:100%;
        padding:15px 10px;
        box-sizing: border-box;
    }
    .spv-list-container {
        .form-container{
            width:27%;
            float:left;
            .el-form-item{
                margin-bottom: 0;
            }
        }
    }
    .spv-list-container{
        .spv-left{
            float:left;
            width:15%;
            height:600px;
        }
        .spv-right{
            float:left;
            width:80%;
            margin-left:2%;
            .spv-right-top{
                margin-bottom:30px;
                border-radius:4px;
                overflow:hidden;
            }
            .spv-right-bottom{
                border-radius:4px;
                overflow:hidden;
            }
        }
    }
    .spv-right .clearfix{
        background:#eee;
        padding:5px 15px;
        box-sizing:border-box;
        span{
            color:#606266;
        }
    }
    .spv-list-container::after{
        content:'';
        height: 0;
        clear: both;
        display:block;
        visibility: hidden;
    }
    .top,.bottom{
        margin:0 auto;
    }
    .el-pagination {
        text-align: center;
        padding: 10px 0;
        }
        .table tr th {
        vertical-align: top;
        width:120px;
        text-align:center;
        }
        .table tr th:last-child {
        width:130px;
        }
        .table tr td {
        width: 120px;
        white-space: wrap;
        overflow: hidden;
        color:#666;
        }
        .table tr td:last-child{
            text-align: center;
        }
        .table tr th,.table tr td{
            border-color:#ddd;
        }
        .pagination-container{
            width:100%;
            height:50px;
            
        }
        .position-container{
            width:95%;margin:0 auto;
            position:relative;
        }
</style>