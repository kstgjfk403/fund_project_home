
<template>
<div class="basic-detail-container">
  <Header></Header>
    <div class="primary-detail-container">
    <div class="detail-background">
      <ul class="droid">
        <li>
          <span>Project Name:</span><span>{{detaildata.abbname}}</span>
          <router-link to="listpage"><el-button type="primary" size="small" style="float:right;background:#31b0d5;">return</el-button></router-link>
          <el-button type="primary" size="small" icon="el-icon-edit" style="float:right;margin-right:10px;background:#31b0d5;color:white;" @click="linktoeditor(detaildata.portfolioid)"></el-button>
          <el-button type="primary" size="small" icon="el-icon-printer" style="float:right;margin-right:10px;background:#31b0d5;color:white;"></el-button>
        </li>
        <li><span>Project Manager:</span><span>{{detaildata.projectmanagerstr}}</span></li>
        <li><span>Company Legal Name:</span><span>{{detaildata.fullname}}</span></li>
      </ul>
      <div class="primary-img">
        <img src="../../static/images/head-img.png">
        <el-upload
          name="upload"
          class="avatar-uploader"
          action="http://localhost:8080/api/admin/logo/upload"
          :show-file-list="false"
          :data="upLoadData"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
        </el-upload>
      </div>
    </div>
    </div>
    <div class="base-info-container" ref="BasicInfo">
        <div class="base-info base-info-first" style="margin-top:0;border-top:none;">
          <div class="base-info-content" style="margin-top:15px;">
            <ul style='width:100%;'>
              <li><span style='width:25%'>Logo:</span><span style='width:75%'><img :src="detaildata.logo"/></span></li>
            </ul>
            <ul>
              <li><span>Project Name:</span><span>{{detaildata.abbname}}</span></li>
              <li><span>Project Name(Chinese):</span><span>{{detaildata.abbnamechi}}</span></li>
            </ul>
            <ul>
              <li><span>Company Legal Name:</span><span>{{detaildata.fullname}}</span></li>
              <li><span>Company Legal Name(Chinese):</span><span>{{detaildata.fullnamechi}}</span></li>
            </ul>
            <ul>
              <li><span>Report Name:</span><span>{{detaildata.reportname}}</span></li>
              <li><span>Project Manager:</span><span>{{detaildata.projectmanagerstr}}</span></li>
            </ul>
            <ul>
              <li><span>IDG Role:</span><span>{{detaildata.idgroleidstr}}</span></li>
              <li><span>Initial Role:</span><span>{{detaildata.initialroleidstr}}</span></li>
            </ul>
            <ul>
              <li><span>Industry Sector:</span><span>{{detaildata.sectoridstr}}</span></li>
              <li><span>Product:</span><span>{{detaildata.product}}</span></li>
            </ul>
            <ul>
              <li><span>Anticipated Exit:</span><span>{{detaildata.antidilutionidstr}}</span></li>
              <li><span>Project Buddy(2nd manager):</span><span>{{detaildata.projectbuddystr}}</span></li>
            </ul>
            <ul>
              <li><span>Initial Stage:</span><span>{{detaildata.stageidstr}}</span></li>
              <li><span>IDG First Investment:</span><span>{{detaildata.firstinvestidstr}}</span></li>
            </ul>
            <ul>
              <li><span>Sub-Sector:</span><span>{{detaildata.subsectoridstr}}</span></li>
              <li><span>Company Headcount:</span><span>{{detaildata.headcount}}</span></li>
            </ul>
            <ul>
              <li><span>CEO:</span><span>{{detaildata.ceo}}</span></li>
              <li><span>Found of Found:</span><span>{{detaildata.fundoffund?'yes':'no'}}</span></li>
            </ul>
            <ul>
              <li><span>Primary Business:</span><span>{{detaildata.primarybusiness}}</span></li>
            </ul>
            <ul style='width:100%;'>
              <li><span style='width:25%;'>One Line Description:</span><span style='width:75%;'>{{detaildata.onelinedesc}}</span></li>
            </ul>
          </div>
        </div>
        <div class="base-info">
            <h4><span class="droid">LEGAL DATA</span></h4>
            <div class="base-info-content">
                <ul>
                  <li><span>IDG Inhouse Counsel:</span><span>{{detaildata.idginhousecounselstr}}</span></li>
                  <li><span>IDG board representative:</span><span>{{detaildata.idgboardrepresentativestr}}</span></li>
                </ul>
                <ul>
                  <li><span>IDG Observer:</span><span>{{detaildata.observerstr}}</span></li>
                  <li><span>Liquidation preference:</span><span>{{detaildata.liquidationpreference}}</span></li>
                </ul>
                <ul>
                  <li><span>Year Of Incorporation:</span><span>{{splitDate(detaildata.foundeddate)}}</span></li>
                  <li><span>Incorporated In:</span><span>{{detaildata.locationidstr}}</span></li>
                </ul>
                <ul>
                  <li><span>Company Register No.:</span><span>{{detaildata.registerno}}</span></li>
                  <li><span>Investment Legal form:</span><span>{{detaildata.formid}}</span></li>
                </ul>
                <ul>
                  <li><span>Total Boardseat NO.:</span><span>{{detaildata.totalboardseatno}}</span></li>
                </ul>
            </div>
        </div>
        <div class="base-info">
            <h4><span class="droid">CONTACT INFORMATION</span></h4>
            <div class="base-info-content">
                <ul>
                <li><span>Business address(Eng):</span><span>{{detaildata.address}}</span></li>
                <li><span>Business address(chi):</span><span>{{detaildata.addresschi}}</span></li>
                </ul>
                <ul>
                <li><span>Telephone:</span><span>{{detaildata.telephone}}</span></li>
                <li><span>Fax:</span><span>{{detaildata.fax}}</span></li>
                </ul>
                <ul>
                <li><span>Audit Comformation Address:</span><span>{{detaildata.auditconfaddress}}</span></li>
                </ul>
            </div>
        </div>
        <div class="base-info" style="margin-bottom:40px;">
            <h4><span class="droid">IPO/LISTING RECORD(if applicable)</span></h4>
            <div class="base-info-content">
                <ul>
                <li><span>Portfolio Status:</span><span>{{detaildata.portfoliostatusstr}}</span></li>
                <li><span>Stock Exchange:</span><span>{{detaildata.stockexchangeidstr}}</span></li>
                </ul>
                <ul>
                <li><span>Ticker:</span><span>{{detaildata.stockcode}}</span></li>
                <li><span>Lockup period:</span><span>{{detaildata.lockupperiod}}</span></li>
                </ul>
                <ul>
                <li><span>Lockup expired on:</span><span>{{detaildata.lockupexpireddate}}</span></li>
                <li><span>ADR Ratio:</span><span>{{detaildata.adrratio}}</span></li>
                </ul>
                <ul>
                <li><span> Effective Date:</span><span>{{detaildata.portfoliostatusdate}}</span></li>
                <li><span>Financial YTD:</span><span>{{detaildata.ytd}}</span></li>
                </ul>
                <ul>
                <!-- <li><span> IPO Date:</span><span>{{detaildata.ipodate}}</span></li> -->
                <li><span>Stock Code:</span><span>{{detaildata.stockcode}}</span></li>
                </ul>
            </div>
        </div>
    </div>
    <Ivestment></Ivestment>
    <CapTable></CapTable>
    <FinancialInfo></FinancialInfo>
    <Director></Director>
    <MainBonus></MainBonus>
    <Business/>
    <Navlist v-on:toscorll="scrolltoview" :isactive="isActive"></Navlist>
</div>
</template>
<script>
import axioss from '../api/axios';
import Header from "./common/Header";
import * as method from "@/api/method";
import Navlist from '@/components/common/Nav';
import Ivestment from '@/page/InvestManage/investmanager';
import CapTable from "@/page/capTable/capTable";
import FinancialInfo from "@/page/financial/FinancialInformation";
import Director from "@/page/directorandsuper/Director";
import MainBonus from "@/page/bonus/MainBonus";
import Business from "@/page/business/Business";
export default {
    name:"Detail",
    data:function(){
      return {
        heightObj:'',
        imageUrl: '',
        detaildata:'',
        incorpList:[],
        isActive:{
          BasicInfo:true,
          Invest:false,
          CapTable:false,
          Financial:false,
          Director:false,
          Bonus:false,
          Business:false
        },
        upLoadData: {
          portfolioId:this.$route.query.portfolioid
        }
      }
    },
    mounted:function(){
      this.requestdetails(this.$route.query.portfolioid);
    },
    methods:{
      requestdetails(portfolioid){
        axioss.reqdetails(portfolioid).then((res)=>{
          var obj=res.data.data;
          obj.portfoliostatusdate=method.toLocalString(obj.portfoliostatusdate)
          obj.lockupexpireddate=method.toLocalString(obj.lockupexpireddate)
          this.detaildata=obj;
        }).catch((res)=>{
        })
      },
      linktoeditor(id){
        this.$store.dispatch('isDetail',{isDetail:true})
        this.$router.push({ path: "addproject", query:{portfolioid:id,path:'editor'}})
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
        this.requestdetails(this.$route.query.portfolioid);
      },
      beforeAvatarUpload(file) {
        const isJpeg = file.type === 'image/jpeg';
        const isJPG = file.type === 'image/jpg';
        const isGif = file.type === "image/gif";
        const isPng = file.type === "image/png";
        const isBmp = file.type === "image/bmp";
        const isLt2M = file.size / 1024 / 1024 < 2;
        if ((!isJPG)&&(!isJpeg)&&(!isGif)&&(!isPng)&&(!isBmp)) {
          this.$message.error('file format is not correct !');
          return false;
        }
        if (!isLt2M) {
          this.$message.error('Less than 2MB!');
          return false;
        }
      },
      scrolltoview(eletoview,arr){
        var obj=this.$refs[eletoview];
        if(!this.heightObj){
          this.heightObj=this.$refs["BasicInfo"].offsetHeight;
        }
        if(!obj){
          this.$refs["BasicInfo"].style.height=42+"px";
          this.$refs["BasicInfo"].style.overflow='hidden';
        }
        if(obj){
          var scrolly=window.scrollY;
          var _top=obj.getBoundingClientRect().top;
          var top=_top+scrolly-130;
          document.documentElement.scrollTop=top;
          obj.style.height=this.heightObj+"px"
        }
      },
      splitDate(date){
        if(date){
          var a=date.split('-');
          return a[0];
        } 
      } 
    },
    components:{
        Header,
        Navlist,
        Ivestment,
        CapTable,
        FinancialInfo,
        Director,
        MainBonus,
        Business
    }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .basic-detail-container{
      width:82%;
      margin-left:12%;
    }
    .basic-detail-container .base-info-first ul li span:first-child{
        font-weight:600;
    }
    .avatar-uploader{
        position: absolute;
        width:100%;
        height:100%;
    }

    .basic-detail-container h3{
      margin:10px 0;
    }
    .detail-background{
      height:140px;
      width:100%;
      margin-top:20px;
      background:#3c8dbc;
      display: flex;
      justify-content: space-between;
      align-items:center;
    }
    .detail-background ul li span{
      color:white;
      font-size:20px;
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
    .primary-detail-container{
      box-shadow: 0 0 3px 2px #ddd;
    }
    .base-info{
      width:100%;
      margin-top:15px;
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
      overflow:hidden;
      color:#666;
      vertical-align:top;
    }
</style>
