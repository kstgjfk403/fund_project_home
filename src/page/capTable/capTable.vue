<template>
  <div class="capTable loan" ref="CapTable">
    <div class="title">CapTable History</div>
    <div class="loan-table-container">
      <h3 class="h3">CapTable History</h3>
      <div class="position-content">
        <div class="table-content">
          <el-table :data="firstContentListBase.baseName" style="width: 100%;" show-summary :summary-method="getSummaries1">
            <el-table-column label="Stockholder Information" width="160">
              <el-table-column prop="sharetype" label="Stockholder Type" width="150"></el-table-column>
              <el-table-column prop="securitytypeidstr" label="ShareType" width="130"></el-table-column>
              <el-table-column prop="shareowner" label="Name" width='130'></el-table-column>
            </el-table-column>
          </el-table>
          <el-table :data="firstContentListBase.baseName" style="width: 100%;position:absolute;top:0;left:0;z-index:10;" show-summary :summary-method="getSummaries1">
            <el-table-column label="Stockholder Information" width="160">
              <el-table-column prop="sharetype" label="Stockholder Type" width="150"></el-table-column>
              <el-table-column prop="securitytypeidstr" label="Share Type" width="130"></el-table-column>
              <el-table-column prop="shareowner" label="Name" width='130'></el-table-column>
            </el-table-column>
          </el-table>
          <el-table :data="item.baseName" style="width:592px;overflow:hidden;display: inline-block;vertical-align: top;" v-for="(item,index) in firstContentList" :key='index*(-1)' show-summary :summary-method="getSummaries">
            <el-table-column :label="item.baseId">
              <el-table-column align='right' prop="shareownedno" :label="item.investType=='Equity Interest'||item.investType=='Convert To Equity Interest'?'Registered Capital':'Shares Number'" width="150" :formatter="numberFormat"></el-table-column>
              <el-table-column align='right' prop="cost" label="Subscribed investment" width="180" :formatter="numberFormat"></el-table-column>
              <el-table-column align='right' prop="proper" label="Share ratio(%)" width="130" :formatter="properFormat"></el-table-column>
              <el-table-column align='right' prop="properwithoutesop" label="withoutESOP(%)" width="130" :formatter="properFormat"></el-table-column>
              <el-table-column prop="shareowner" label="Name" width='130'></el-table-column>
            </el-table-column>
          </el-table>
          <el-table :data="item.baseName" style="width:592px;overflow:hidden;display:inline-block;vertical-align: top;" v-for="(item,index) in otherContentList" :key='index+1' show-summary :summary-method="getSummaries">
            <el-table-column :label="item.baseId">
              <el-table-column align='right' prop="shareownedno" :label="item.investType=='Equity Interest'||item.investType=='Convert To Equity Interest'?'Registered Capital':'Shares Number'" width="150" :formatter="numberFormat"></el-table-column>
              <el-table-column align='right' prop="cost" label="Subscribed investment" width="180" :formatter="numberFormat"></el-table-column>
              <el-table-column align='right' prop="proper" label="Share ratio(%)" width="130" :formatter="properFormat"></el-table-column>
              <el-table-column align='right' prop="properwithoutesop" label="withoutESOP(%)" width="130" :formatter="properFormat"></el-table-column>
              <el-table-column prop="shareowner" label="Name" width='130'></el-table-column>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axioss from '@/api/axios';
import * as method from "@/api/method";
import bus from '@/api/eventbus'
import mix from "@/api/mixin"
export default {
    name:"CapTable",
    mixins:[mix],
    data(){
        return {
            heightObj:'',
            capVisible:false,
            capSelectList:[],
            capTableHeadList:[],
            capTableContentList:[],
            firstContentList:[],
            otherContentList:[],
            firstContentListBase:[]
        }
    },
    updated(){
    },
    mounted(){
        bus.$on('toScorll',(ace)=>{
           this.scrolltoview(ace,'CapTable');
        });
        this.reqTableContent();
        bus.$on('updateCaptable',()=>{
            this.reqTableContent();
        })
    },
    methods:{
        reqdroplist(){
            var obj={dictArray:"DDL_ShareType"}
            axioss.reqdroplist(obj).then(res=>{
                this.capSelectList=res.data.data[0].baseInfoList;
            })
        },
        handleAdd(){
            this.reqdroplist();//获取Create时的下拉列表数据。
            this.capVisible=true;
        },
        reqTableContent(){
            axioss.reqTableContent(this.portfolioid).then(res=>{
                
                this.capTableContentList=res.data.data;
                if(res.data.data&&res.data.data.length){
                    this.firstContentListBase=res.data.data.slice(0,1)[0];
                    this.firstContentList=res.data.data.slice(0,1);
                    this.otherContentList=res.data.data.slice(1);
                }
            })
        },
        toHeader(data){
            var len=data.length;
            for(var i=0;i<len;i++){
              for(var j in data[i]){
                if(/[0-9]/.test(j)){
                  data[i].header=(data[i])[j];
                }
              }
            }
            return data;
        },
        numberFormat: function (row, column) {
          var num = row[column.property];
          if (num == undefined) {
            return "";
          }
          return method.toThousands(num);
        },
        properFormat: function (row, column) {
          var num = row[column.property];
          if (num == undefined) {
            return "";
          }
          return (num*100).toFixed(2);
        },
        getSummaries(param) {
        const { columns, data } = param;
        const sums = [];
        columns.forEach((column, index) => {
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
            sums[index] += '';
          } else {
            sums[index] = 'N/A';
          }
        });
        sums[0]=method.toThousands((sums[0]*1).toFixed(2));
        sums[1]=method.toThousands((sums[1]*1).toFixed(2));
        sums[3]=sums[3]*100;
        sums[2]=sums[2]*100;
        return sums;
        },
        getSummaries1(param) {
        const { columns, data } = param;
        const sums = [];
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = 'Total';
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
            sums[index] += '';
          } else {
            sums[index] = '';
          }
        });

        return sums;
        }
    },
    computed:{
        capTabelList(){
            return this.$store.state.capTabelList;
        },
        portfolioid(){
            if(this.$store.state.portfolioid==''){
                this.$store.dispatch('updateData');
            }
            return this.$store.state.portfolioid;
        },
        thContent(){

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
    .loan-table-container .table-content{
        white-space: nowrap;
        overflow-x:scroll;
        font-size: 0;
    }
    .position-content{
        position:relative;
    }
</style>
