<template>
    <div class="capTable loan" ref="CapTable">
        <div class="title">CapTable History</div>
        <div class="loan-table-container">
            <h3 class="h3">CapTable History</h3>
            <el-table :data="capTabelList" style="width: 100%">
                <el-table-column prop="date" label="股东信息" width="150">
                    <el-table-column prop="name" label="股东类型" width="130"></el-table-column>
                    <el-table-column prop="name" label="股东" width="130"></el-table-column>
                </el-table-column>
                <el-table-column label="第一轮 2014-05-06">
                    <el-table-column prop="name" label="认缴注册资本" width="130"></el-table-column>
                    <el-table-column prop="province" label="认缴投资额" width="130"></el-table-column>
                    <el-table-column prop="address" label="股比" width="130"></el-table-column>
                    <el-table-column prop="address" label="股比" width="130"></el-table-column>
                </el-table-column>
                <el-table-column label="第二轮 2014-05-06">
                    <el-table-column prop="name" label="认缴注册资本" width="130"></el-table-column>
                    <el-table-column prop="province" label="认缴投资额" width="130"></el-table-column>
                    <el-table-column prop="address" label="股比" width="130"></el-table-column>
                    <el-table-column prop="address" label="股比" width="130"></el-table-column>
                </el-table-column>
                <el-table-column label="合计">
                    <el-table-column prop="province" label="累计投资额" width="130"></el-table-column>
                    <el-table-column prop="address" label="股比" width="130"></el-table-column>
                </el-table-column>
            </el-table>
            <div class="table-foot">
            </div>
        </div>
    </div>
</template>
<script>	
import axioss from '@/api/axios';
import * as method from "@/api/method";
import bus from "@/api/eventbus";
export default {
    name:"CapTable",
    data(){  
        return {
            heightObj:'',
            capVisible:false,
            capSelectList:[], 
            // capTabelList: [

            // ]
        }
    },
    updated(){
    },
    mounted(){
        bus.$on('toScorll',(ace,arr)=>{
           this.scrolltoview(ace,arr);
       });
    },
    methods:{
        reqdroplist(){
            var obj={dictArray:"DDL_ShareType"}
            axioss.reqdroplist(obj).then(res=>{
                this.capSelectList=res.data.data[0].baseInfoList;
            })
        },
        handleAdd(){
            this.reqdroplist();//获取新建时的下拉列表数据。
            this.capVisible=true;
        },
        submitInputForm(fromName){
            this.$refs[capInputForm].validate((validate)=>{
                if(validate){
                    axioss.addShareHolder(obj).then(res=>{
                        console.log(res);
                        if(res.data.code=="SUCCESS"){
                            this.$message({
                                type:'success',
                                message: '创建成功'
                            })
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
        getSummaries(param){
            const { columns, data } = param;
            const sums = [];
            console.log(columns)
            columns.forEach((column, index) => {
            if (index === 0) {
                sums[index] = '总价';
                return;
            }
            const values = data.map(item => Number(item[column.property]));
            if (!values.every(value => isNaN(value))) {
                sums[index] = values.reduce((prev, curr) => {
                const value = Number(curr);
                if (!isNaN(value)) {
                    return prev + curr;
                } else {
                    return prev;
                }
                }, 0);
                sums[index] += '元';
            } else {
                sums[index] = 'N/A';
            }
            });
            return sums;
        },
        scrolltoview(eletoview){
            var obj=this.$refs[eletoview];
            if(!this.heightObj){
                this.heightObj=this.$refs["CapTable"].offsetHeight
            }
            if(!obj){
                this.$refs["CapTable"].style.height=50+"px";
                this.$refs["CapTable"].style.overflow='hidden';
            }
            if(obj){
                var scrolly=window.scrollY
                var _top=obj.getBoundingClientRect().top;
                var top=_top+scrolly;
                document.documentElement.scrollTop=-(top-_top);
                obj.style.height=this.heightObj+"px"
            }   
        }
    },
    computed:{
        capTabelList(){
            return this.$store.state.capTabelList;
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
	
        .loan-table-container h3{
            font-size:16px;
            padding:5px 0;
            padding-left:10px;
            background:#eee;
            margin: 0;
            border-bottom: 1px solid #ebeef5;
        }
    
    .loan-table-container .table-foot{
        background:#eee;
        padding:5px 0;
        padding-left:10px;
    }
    .select-container{
        width:100%;
    }
    .capTable{
        margin-top:20px;
        border:1px solid #ddd;
    }
    .title{
        padding:10px;
        box-sizing: border-box;
        font-size: 20px;
        color:white;
        font-weight:bold;
        background:#00a1e9;
    }
</style>