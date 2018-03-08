<template>
  <div class="add-project">
    <Header></Header>
    <!-- <h2>添加项目信息</h2> -->


    <el-form :label-position="labelposition" :model="formData" :rules="rules" ref="userform">

      <!--<div style="margin-top:20px">
        <b style="font-size:18px">INVESTMENT OVERVIEW OF </b>
        <el-select v-model="searchData.fundid" placeholder="pls select fund name" style="width:400px" @change="changeFund()">
          <el-option :key="item.fundid"
                     :label="item.fundfullnameeng"
                     :value="item.fundid"
                     v-for="item in fundarrayList.data"></el-option>
        </el-select>
      </div>-->

      <div style="margin-top:20px">
        <b style="font-size:18px">INVESTMENT OVERVIEW OF </b>
        <!--   <el-form-item label="NET CASHFLOW FOR" >-->
        <el-select v-model="searchData.fundid" placeholder="pls select fund name" style="width:300px" @change="changeFund()">
          <el-option :key="item.fundid"
                     :label="item.fundfullnameeng"
                     :value="item.fundid"
                     v-for="item in fundarrayList.data"></el-option>
        </el-select>

        <b style="font-size:18px;margin-left:50px">  QUARTER </b>
        <el-select v-model="searchData.quarterid" placeholder="quarter" style="width:100px" @change="changeFund()">
          <el-option :key="item.quarterid"
                     :label="item.quarternameeng"
                     :value="item.quarterid"
                     v-for="item in quarterList"></el-option>
        </el-select>
        <el-select v-model="searchData.yearid" placeholder="year" style="width:90px" @change="changeFund()">
          <el-option :key="item.yearid"
                     :label="item.yearid"
                     :value="item.yearid"
                     v-for="item in yearList"></el-option>
        </el-select>
        <b style="font-size:13px"></b>
        <!-- 导出excel -->
        <el-button type="primary" size="mini" id="but" @click="exportTab" style="margin-left:20px;">导出Excel</el-button>
        <!-- </el-form-item>-->
      </div>


      <div style="margin-top:15px">
      </div>
      
      <!-- 需要导出的表格内容区域 -->
      
        <div class="position-container">
          <div class="table-responsive">
           
            <el-table :data="portfoliolistData" border style="width: 100%" maxHeight="850" id="excel">
              <el-table-column fixed prop="company" label="Company" width="150" class-name="firstrow">
              </el-table-column>
              <el-table-column  prop="businessDescription" label="Business Description" width="400">
              </el-table-column>
              <el-table-column  prop="sectorStr" label="Sector" width="100">
              </el-table-column>
              <el-table-column  prop="portfolioStatusStr" label="Status" width="100">
              </el-table-column>
              <el-table-column  prop="privateOrPublic" label="Private/Public" width="120">
              </el-table-column>
              <el-table-column  prop="currentOwnership" label="Current Ownership" width="150">
              </el-table-column>
              <el-table-column  prop="investmentDate" label="Investment Date" width="150">
              </el-table-column>
              <el-table-column  prop="exitDate" label="Exit Date" width="150">
              </el-table-column>
              <el-table-column  prop="upround" label="Upround (Yes/No)" width="100">
              </el-table-column>
              <el-table-column  prop="boardSeatsStr" label="Broad Seats(Yes/No)" width="100">
              </el-table-column>
              <el-table-column  prop="lead" label="Lead" width="135">
              </el-table-column>
            </el-table>


          </div>
        </div>
      


    </el-form>
  </div>
</template>
<script>
  import Header from "../components/common/Header";
  import * as method from "../api/method";
  import axioss from '../api/axios';
  export default {
    name:"fundIrrStastic",
    data:function(){
      return {
        labelposition:"right",
        fundarrayList:'',
        quarterList:[
          { quarterid:'01',
            quarternameeng:'Mar. 31',
          },
          { quarterid:'02',
            quarternameeng:'Jun. 30',
          },
          { quarterid:'03',
            quarternameeng:'Sep. 30',
          },
          { quarterid:'04',
            quarternameeng:'Dec. 31',
          },
        ],
        yearList:[
          { yearid:'2001'},{ yearid:'2002'},{ yearid:'2003'},{ yearid:'2004'},{ yearid:'2005'},{ yearid:'2006'},{ yearid:'2007'},{ yearid:'2008'},{ yearid:'2009'},{ yearid:'2010'},
          { yearid:'2011'},{ yearid:'2012'},{ yearid:'2013'},{ yearid:'2014'},{ yearid:'2015'},{ yearid:'2016'},{ yearid:'2017'},{ yearid:'2018'},{ yearid:'2019'},{ yearid:'2020'},
          { yearid:'2021'},{ yearid:'2022'},{ yearid:'2023'},{ yearid:'2024'},{ yearid:'2025'},{ yearid:'2026'},{ yearid:'2027'},{ yearid:'2028'},{ yearid:'2029'},{ yearid:'2030'},
        ],
        portfolioList:'',
        // opreationType:'creatuserform',
        portfoliolistData:[],
        lpandgplistData:'',
        searchData: {
          fundid: 'F0001',
          quarterid: '',
          yearid:'',
        },
        resultData: {
          irrLp:'',
          irrLpAndGp: '',
          irrLpStr:'',
          irrLpAndGpStr: '',
          mocLp: '',
          mocLpAndGp:'',
          mocLpStr: '',
          mocLpAndGpStr:'',
          dpiLp:'',
          dpiLpAndGp:'',
          dpiLpStr:'',
          dpiLpAndGpStr:'',
          totalCapitalCallsLp:'',
          totalCapitalCallsLpAndGp:'',
          totalCapitalCallsLpStr:'',
          totalCapitalCallsLpAndGpStr:'',
          totalDistributionsLp:'',
          totalDistributionsLpAndGp:'',
          lpNetAssets:'',
          netAssets:'',
          totalGpCarry:'',
          dateStr:'September 30, 2017'
        },
        formData: {
          callid:'',
          fundid: '',
          calleddate: '',
          calledpecentage:'',
          totalcallamount:'',
          calledpurpose:'',
          duedate:'',
          callorder:'',
          createby:'',
          createdate:'',
          approved:'',
        },
        rules: {


        }
      }
    },
    created(){
      var way=this.$route.query.path;
      var callid=this.$route.query.callid;
      //this.classOperation(way,callid);
    },
    mounted:function(){
      //this.requserlist();//获取列表数据
      this.reqfundlist();

      this.submitpage(this.searchData);
    },
    methods:{
      exportTab(){
        var oHtml1 = document.getElementsByClassName('el-table__fixed-header-wrapper')[0].outerHTML;
        var oHtml2 = document.getElementsByClassName('el-table__fixed-body-wrapper')[0].outerHTML;
        var oHtml = oHtml1+oHtml2;        
        var excelHtml = `
          <html>
          <head>
          <meta charset='utf-8' />
          <style>
          table{
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
          }                  
          td{
            border:0.5px solid #ddd;
            padding:3em;            
          }           
          table tr th{
            border:0.5px solid #ddd;
            text-align:left;
            padding:3em;
            height:40px; 
            background:#5f87a0;
            color:#FFF;           
          }
           .el-table__fixed-header-wrapper .firstrow{
            text-align:left;            
          }
          
          </style>    
          </head>
          <body>
          ${oHtml}
          </body>
          </html>
          `;

        var excelBlob = new Blob([excelHtml], {type: 'application/vnd.ms-excel'})
        // Create一个a标签
        var oA = document.createElement('a');
        // 利用URL.createObjectURL()方法为a元素生成blob URL
        oA.href = URL.createObjectURL(excelBlob);
        // 给文件命名
        oA.download = `fundinvestoverviewpage${(new Date()).toLocaleString()}.xls`;
        // 模拟点击
        oA.click();
        // 移除
        oA.remove();
      },
      reqfundlist(){
        /*var enterobj={
         dictArray:'DDL_IncorpLocation,YTD,CURRENCY'
         }
         axioss.reqdroplist(enterobj).then(res=>{
         this.dictarraylist=method.translateFormat(res.data.data);
         console.log(this.dictarraylist)
         }).catch(res=>{
         console.log(res);
         })*/
        axioss.reqfundlistwithoutpage().then(res=>{
          this.fundarrayList=res.data;
        }).catch(res=>{
        })
      },

      submitpage: function(searchData) {

        //var that = this;
        axioss.reqfundinvestmentoverview(searchData)
          .then(res => {
            var trans=res.data;
            /*for(var i=0;i<trans.length;i++){
             var num=trans[i].totalcommitmentnum;
             trans[i].totalcommitmentnum=method.toThousands(num);
             }*/
            //console.log("临");
            //console.log(trans);
            this.portfoliolistData = trans.data;
            //this.lpandgplistData = trans.data.lpAndGpCashFlowList;
            //this.resultData = trans.data;

          })
          .catch(err => {
          });
      },
      changeFund:function() {
        // console.log("临");
        if(this.searchData.fundid!='' && this.searchData.quarterid!='' && this.searchData.yearid!='') {
          this.portfoliolistData = [];
          this.submitpage(this.searchData);
        }
      },


    },
    components:{
      Header
    }
  }
</script>
<style scoped>
  .add-project{
    width:88%;
    margin:0 auto;
  }
  .add-project .big-base-container{
    border:1px solid #ddd;
    box-shadow:0 0 2px #eee;
    border-radius:3px;
  }
  .add-project .el-form-item{
    font-family:'droid';
  }
  .add-project .base-info-container{
    display: flex;
    align-items:flex-start;
    justify-content:space-between;
  }
  .add-project h3{
    font-size:20px;
    margin:15px 0;
  }
  .add-project .baseinfo-left,.add-project .baseinfo-right{
    width:50%;
    padding:10px;
  }
  .add-project h3,.add-project h2{
    padding-left:10px;
  }
  .add-project .el-form-item label,.add-project .el-form-item .el-form-item__content{
    width:50%;
  }

</style>
