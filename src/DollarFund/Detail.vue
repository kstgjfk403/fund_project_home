
<template>
<div class="basic-detail-container">
  <Header></Header>
  <div class="primary-detail-container">
  <div class="detail-background">
    <ul class="droid">
      <li>
          <span>Fund Type(Abbr):</span><span>{{detaildata.fundtypeid}}</span>
          <router-link to="fundlistpage"><el-button type="primary" size="small" style="float:right;background:#31b0d5;">return</el-button></router-link>
          <el-button type="primary" size="small" icon="el-icon-edit" style="float:right;margin-right:10px;background:#31b0d5;" @click="linktoeditor(detaildata.fundid)"></el-button>
          <el-button type="primary" size="small" icon="el-icon-printer" style="float:right;margin-right:10px;background:#31b0d5;"></el-button>
      </li>
      <li><span>Fund Abbr.name:</span><span>{{detaildata.fundname}}</span></li>
      <li><span>Fund Full Name:</span><span>{{detaildata.fundfullnameeng}}</span></li>  
    </ul>
    <div class="primary-img">
      <img src="../../static/images/head-img.png">
      <el-upload
        class="avatar-uploader"
        action="https://jsonplaceholder.typicode.com/posts/"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="imageUrl" :src="imageUrl" class="avatar">    
      </el-upload>
    </div>
  </div>
  </div>
  <div class="base-info base-info-first" style="margin-top:0;border-top:none;">
    <div class="base-info-content" ref="bacinfo" style="margin-top:15px;">
      <ul>
        <li><span>Fund Type(Abbr):</span><span>{{detaildata.fundtypeid}}</span></li>
        <li><span>Fund Abbr.name:</span><span>{{detaildata.fundname}}</span></li>
      </ul>
      <ul>
        <li><span>Fund Family Name:</span><span>{{detaildata.fundfamillyname}}</span></li>
        <li><span>基金全名:</span><span>{{detaildata.fundfullnamechi}}</span></li>
      </ul>
      <ul>
        <li><span>Fund Full Name:</span><span>{{detaildata.fundfullnameeng}}</span></li>
        <li><span>Registered Address:</span><span>{{detaildata.regaddress}}</span></li>
      </ul>
      <ul>
        <li><span>Industry Focus:</span><span>{{detaildata.mainindustry}}</span></li>
        <li><span>Financial Year End:</span><span>{{detaildata.yearend}}</span></li>
      </ul>
      <ul>
        <li><span>Incorporation In:</span><span>{{detaildata.incorplocation}}</span></li>
        <li><span>Formation Date One Line Description:</span><span>{{detaildata.formationdate}}</span></li>
      </ul>
      <ul>
        <li><span>Fund Close Date:</span><span>{{detaildata.closedate}}</span></li>
        <li><span>Total GP Commitment:</span><span>{{detaildata.totalgpcommitnum}}</span></li>
      </ul>
      <ul>
        <li><span>Total LP Commitment:</span><span>{{detaildata.totallpcommitnum}}</span></li>
        <li><span>LP+GP Commitment(Fund Size):</span><span>{{detaildata.totalcommitmentnum}}</span></li>
      </ul>
      <ul>
        <li><span>Fund Currency:</span><span>{{detaildata.totalcommitmentcur}}</span></li>
        <li><span>Bank Address:</span><span>{{detaildata.bankaddress}}</span></li>
      </ul> 
      <ul>
          <li><span>Swift Code:</span><span>{{detaildata.swiftcode}}</span></li>
          <li><span>GAAP:</span><span>{{detaildata.gaap}}</span></li>
      </ul>
      <ul>
          <li><span>Auditor:</span><span>{{detaildata.auditor}}</span></li>
          <li><span>Tax Representative:</span><span>{{detaildata.taxrepresentative}}</span></li>
      </ul>
    </div>
  </div>
  <div class="base-info" ref="legalinfo">
    <h4><span class="droid">LEGAL DATA</span></h4>
    <div class="base-info-content">
      <ul>
        <li><span>Bank Name:</span><span>{{detaildata.bankname}}</span></li>
        <li><span>Bank Account No:</span><span>{{detaildata.bankaccount}}</span></li>
      </ul>
      <ul>
        <li><span>ABA:</span><span>{{detaildata.aba}}</span></li>
        <li><span>Attn:</span><span>{{detaildata.attn}}</span></li>
      </ul>
      <ul>
        <li><span>Legal Advisor:</span><span>{{detaildata.legaladvisor}}</span></li>
        <li><span>FATCA:</span><span>{{detaildata.fatca}}</span></li>
      </ul>
      <ul>
        <li><span>FATCA Type:</span><span>{{detaildata.fatcatype}}</span></li>
        <li><span>GP Name:</span><span>{{detaildata.gpname}}</span></li>
      </ul>
      <ul>
        <li><span>FATCA officer:</span><span>{{detaildata.fatcaofficer}}</span></li>
        <li><span>Managerment Company:</span><span>{{detaildata.magtcompany}}</span></li>
      </ul>
      <ul>
        <li><span>MGMT Incorporate In:</span><span>{{detaildata.magtcomincorporated}}</span></li>
        <li><span>Tax ID:</span><span>{{detaildata.taxID}}</span></li>
      </ul>
      <ul>
        <li><span>GIIN No:</span><span>{{detaildata.GIIN_No}}</span></li>
        <li><span>Managerment Fee Structure:</span><span>{{detaildata.managfeedesc}}</span></li>
      </ul>
    </div>
  </div>
  <div class="base-info detail-margin" ref="contactinfo">
    <h4><span class="droid">CONTACT INFORMATION</span></h4>
    <div class="base-info-content">
      <ul>
          <li><span>Capital Allocations Desc:</span><span>{{detaildata.capitalallocationsdesc}}</span></li>
          <li><span>Fund Manager Email:</span><span>{{detaildata.fundmanageremail}}</span></li>
      </ul>
      <ul>
          <li><span>Fund Manager Name:</span><span>{{detaildata.fundmanager}}</span></li>
          <li><span>Finance Manager Name:</span><span>{{detaildata.financemanager}}</span></li>
      </ul>
      <ul>
          <li><span>Finance Manager Email:</span><span>{{detaildata.financemanageremail}}</span></li>
      </ul>
    </div>
  </div> 
  <ol class="navlist droid">
    <li class="title">Nav</li>
    <li @click="scrolltoview('bacinfo')" :class='{navactive:isActive.bacinfo}'>Basic</li>
    <li @click="scrolltoview('legalinfo')" :class='{navactive:isActive.legalinfo}'>LEGAL</li>
    <li @click="scrolltoview('contactinfo')" :class='{navactive:isActive.contactinfo}'>CONTACT</li>
  </ol>
</div>
</template>
<script>
import axioss from '../api/axios'
import Header from "../components/common/Header"
import {toLocalString} from "../api/method"
export default {
    name:"FundDetail",
    data:function(){
      return {
        imageUrl: '',
        detaildata:'',
        isActive:{
            bacinfo:true,
            legalinfo:false,
            contactinfo:false,
            listinginfo:false
        }
      }
    },
    mounted:function(){    
        this.requestdetails(this.$route.query.fundid);
    },
    methods:{
        requestdetails(fundid){
          axioss.reqdetails2(fundid).then((res)=>{
              var tranRes=res.data.data;
              var closedate=res.data.data.closedate;
              var formationdate=res.data.data.formationdate;
              tranRes.closedate=toLocalString(closedate);
              tranRes.formationdate=toLocalString(formationdate);
              this.detaildata=tranRes;
          }).catch((res)=>{
              console.log(res);
          })
        },
        scrolltoview(eletoview){
            var obj=this.$refs[eletoview];
            var scrolly=window.scrollY;
            var _top=obj.getBoundingClientRect().top;//当前匹配元素距离浏览器窗口的距离。
            var top=_top+scrolly-100;
            document.documentElement.scrollTop=top;
            this.confirmActive(eletoview)
        },
        confirmActive(obj){
            var isActive=this.isActive;
            for(var item in isActive){
                isActive[item]=false;
            }
            isActive[obj]=true;
        },
        linktoeditor(id){
            this.$router.push({ path: "fundaddproject", query:{fundid:id,path:'editor'}})
        },
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
            const isJpeg = file.type === 'image/jpeg';
            const isJPG = file.type === 'image/jpg';
            const isGif = file.type === "image/gif";
            const isPng = file.type === "image/png";
            const isBmp = file.type === "image/bmp";
            const isLt2M = file.size / 1024 / 1024 < 2;
            if ((!isJPG)&&(!isJpeg)&&(!isGif)&&(!isPng)&&(!isBmp)) {
                this.$message.error('上传头像图片格式不正确!');
                return false;
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
                return false;
            }
        }
    },
    components:{
        Header
    } 	
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .basic-detail-container{
      width:72%;
      margin:0 auto;
    }
    .basic-detail-container .base-info-first ul li span:first-child{
        font-weight:600;
    }
    .basic-detail-container .navlist{
        position: fixed;
        top:75px;
        left:20px;
        background:white;
        border-radius:3px;
        box-shadow:0 0 3px #eee;
        width:130px;
        border:1px solid #ddd;
        border-bottom:none;
    }
    .basic-detail-container .navlist li{
        padding:10px 10px;
        cursor: pointer;
        text-overflow:ellipsis;
        overflow:hidden;
        white-space:wrap;
        border-bottom:1px solid #ddd;
        text-align: center;
    }
    .navactive{
        background:#00a1e9;
        color:white;
    }
    .basic-detail-container .navlist .title{
        text-align:center;
        font-weight:600;
        background:white;
        color:#00a1e9;
    } 
    .basic-detail-container h3{
      margin:10px 0;
    }
    .detail-background{
      height:140px;
      width:100%;
      /* background:url("https://static.licdn.com/sc/h/3chad3rzxalsf65nuo7hxciac") no-repeat; */
      background:#3c8dbc;
      margin-top:20px;
      display: flex;
      justify-content: space-between;
      align-items:center;
    }
    .detail-background ul li span{
      color:white;
      font-size:20px;
      /* font-weight:bold; */
    }
    .detail-background ul{
      margin-left:20px;
      width:80%;
    }
    .detail-background .primary-img{
      width:128px;
      height: 128px;
      border-radius:50%;
      border:1px solid #ccc;
      background:rgba(255,255,255,0.9);
      display: flex;
      justify-content: center;
      align-items:center;
      margin-right:20px;
      position:relative;
    }
    .avatar-uploader{
        position: absolute;
        width:100%;
        height:100%;
    }
    
    .primary-detail-container{
       box-shadow: 0 0 3px 2px #ddd;
    }
    .base-info{
      width:100%;  
      margin-top:15px;
      /* line-height:30px; */
      box-sizing:border-box;
      padding:15px;
      padding-top:5px;
      box-shadow: 0 0 3px 1px #ddd;
    }
    .base-info-content{
      display: flex;
      justify-content:flex-start;
      flex-wrap:wrap;
    }
    .base-info-content ul{
      width:50%;
    }
    .base-info h4{
      border-bottom:2px solid #eee;
      padding-bottom:5px;
      margin-bottom:10px;
    }
    .base-info h4 span{    
        color:#1d9ce5;
        font-size:22px; 
    }
    .basic-detail-container h3{
      padding:15px 0;
    }
    .base-info ul li span{
      display:inline-block;
      width:50%;
      /* text-overflow:ellipsis; */
      overflow:hidden;
      /* white-space:nowrap; */
      color:#666;
      /* font-weight: bold;  */
      vertical-align:top;
    }
    .detail-margin{
        margin-bottom: 30px;
    }
</style>

