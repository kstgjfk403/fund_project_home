<template>
<div>
    <Header/>
    <div class="search mysearch">
        <el-form :inline="true" :model="searchForm" ref="searchForm">
            <el-form-item label="Fund" style="margin-left:10px;">
                <el-select v-model="searchForm.fundId" placeholder=" ">
                <el-option :label="item.baseName" :value="item.baseId" :key="item.baseId" v-for="item in fundList"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Close Date" style="margin-left:15px;">
                <el-date-picker v-model="searchForm.closeDate" type="date" placeholder=" ">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-checkbox v-model="searchForm.isEquityOnly" true-label="Y" false-label="N" style="position:relative;">Equlity Investment Portfolios Only</el-checkbox>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size="mini" @click="searchSubmit('searchForm')">查询</el-button>
                <el-button type="primary" size="mini" v-loading="downloading" @click="myGetPdf()" title="导出pdf时:目标打印机选择另存为pdf,并点击保存">打印或导出PDF</el-button>
            </el-form-item>
        </el-form>
    </div>
    <div id="pdfDom">
    <table class="mytable"  v-for="(searchData,indexParent) in searchDataParent">
        <tr>
            <!-- :class="{pageSpace:m>1,firstPage:true}" -->
            <th colspan="2" :class="{pageSpace:indexParent>0,firstPage:true}"  style="color: rgb(185, 58, 58);font-size:18px;">IDG CHINA CAPITAL FUND III L.P.</th>
        </tr>
        <tr>
            <th colspan="2" style="color:#000;">PORTFOLIO SUMMARY</th>
        </tr>
        <tr>
            <th colspan="2" style="color:rgb(180, 184, 187);padding-bottom:30px;">December 31, 2017</th>
        </tr>
        <template v-for="(item,index) in searchData">
        <tr  class="borderTop">
            <td style="padding-top:15px;"><img style="height:45px;min-height:45px;" :src="baseurl+item.logo"></td>
            <td rowspan="8" style="vertical-align: middle;">{{item.oneLineDesc}}</td>
        </tr>
        <tr>
            <td style="font-weight:bold;">{{item.reportName}}</td>
        </tr>
        <tr>
            <td >http://www.cjia.com/</td>
        </tr>
        <tr>
            <td>Sector | Sub-Sector: TMT | Consumer & O2O</td>
        </tr>
        <tr>
            <td>IDG Initial Role: {{item.InitialRole +" | "+item.IDGRole}}</td>
        </tr>
        <tr>
            <td>Initial Stage: {{item.stage}}</td>
        </tr>
        <tr>
            <td>Board Seat: {{item.IDGOnBoard=="1"?"Yes":"NO"}}</td>
        </tr>
        <tr>
            <td style="padding-bottom:12px;">Status: Private</td>
        </tr>
        </template>
        <tr>
            <td colspan="2" style="text-align:center; color: rgb(185, 58, 58);border-top:1px solid #000; padding-top:15px; font-weight:800;">IDG Capital</td>
        </tr>
         <tr>
            <td colspan="2" style="text-align:center; color:rgb(180, 184, 187);padding-bottom:15px;">Confidential & Proprietary</td>
        </tr>

        
    </table>
    </div>
    
  
  </div>
   
</template>
<script>
import {toThousands} from "@/api/method"
import axioss from '@/api/axios';
import Header from "@/components/common/Header";
import html2Canvas from 'html2canvas'
import JsPDF from 'jspdf'
  export default {
    data() {
      return {
        fundList:[],
        baseurl:"",//http://localhost:8080
        downloading:false,
        searchForm:{
            fundId:'F0022',
            closeDate:'',
            isEquityOnly:"N",
        },
        htmlTitle:"main_business",
        searchDataParent:[[]],                
      }
    },
    components: {Header},
    mounted(){
        this.reportDropList();
    },
    methods:{
        /* 千分位 */
        mytoThousands(num,mysymbol){
            if(num=="-"){
                return "-"
            }else{
                if(num===""){
                    return ""
                }
                num = parseInt(num);
                if(mysymbol){
                    return "$"+toThousands(num);
                }else{
                     return toThousands(num);
                }
            }
        },
        /* 数据搜索 start */
        reqSearchData(obj){             
            axioss.reqMain_Business(obj).then(res=>{
                if(res.data && res.data.code=="SUCCESS"){
                    let data = res.data.data;
                    console.log("请求结果",data);
                    let splitArr = this.sliceArray(data, 4);
                    //console.log(splitArr)
                    this.searchDataParent = splitArr;
                }
            })
        },
        reportDropList(){/* 获取下拉列表 */
            var obj={dictArray:"FUND"};
            axioss.reqdroplist(obj).then(res=>{
                this.fundList=res.data.data[0].baseInfoList;
            })
        },
        searchSubmit(formName){/* 搜索事件函数 */
            console.log(this.searchForm)
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.reqSearchData(this.searchForm);                   
                }                
            });    
        },
        sliceArray(array, size) {
            let result = [];
            for (var x = 0; x < Math.ceil(array.length / size); x++) {
                let start = x * size;
                let end = start + size;
                result.push(array.slice(start, end));
            }
            return result;
        },
        myGetPdf(){
            window.print();return;
            let PDF = new JsPDF('', 'pt', 'a4');

            //先完成一个table的转换
            let dom = document.querySelectorAll(".mytable");
            console.log(dom)
            let pageArr = Array.prototype.slice.call(dom);
            let width = pageArr[0].offsetWidth;
            let height = pageArr[0].offsetHeight;
            let canvas = document.createElement("canvas");
            /* 确保pdf清晰度 */
            let scale = 4;
            canvas.width = width * scale; 
            canvas.height = height * scale;
            let context = canvas.getContext('2d'); 
            context.scale(scale, scale); 

            /* canvas转换选项 */
            let opts = {
                scale: scale, 
                canvas: canvas, 
                logging: true, 
                width: width, 
                height: height,
                useCORS:true
            }

            /* 将dom元素转换为canvas */
            
            let covert = async function(){
                for(let i=0; i<pageArr.length; i++){
                    
                    await html2Canvas(pageArr[i], opts).then(function (canvas) {
                        let contentWidth = canvas.width;  
                        let contentHeight = canvas.height;
                        let imgWidth = 595.28;  
                        let imgHeight = 592.28 / contentWidth * contentHeight;
                        let pageData = canvas.toDataURL('image/jpeg', 1.0);                
                        PDF.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight);
                        /* 最后一页不需要添加页面 */
                        if(i!=pageArr.length-1){
                            PDF.addPage();
                        }
                            
                            
                        
                        //console.log('addImage')
                        //PDF.save('text' + '.pdf');                    
                    })
                }
                
            }
            covert().then(function(){
                PDF.save('text' + '.pdf');
            })
        },         
        /* 数据搜索 end */
        getPdf () {
            this.downloading = true;
            let that = this;  
            var title = this.htmlTitle
            var shareContent = document.querySelector('#pdfDom'); 
            var width = shareContent.offsetWidth; 
            var height = shareContent.offsetHeight; 
            var canvas = document.createElement("canvas"); 
            var scale = 4; 

            canvas.width = width * scale; 
            canvas.height = height * scale;
            let context = canvas.getContext("2d"); 
            context.scale(scale, scale); 

            /* 关闭抗锯齿 */
            context.mozImageSmoothingEnabled = true;
            context.webkitImageSmoothingEnabled = true;
            context.msImageSmoothingEnabled = true;
            context.imageSmoothingEnabled = true;

            var opts = {
                scale: scale, 
                canvas: canvas, 
                logging: true, 
                width: width, 
                height: height,
                /* allowTaint: true, */
                useCORS:true 
            };  
            html2Canvas(document.querySelector('#pdfDom'), opts).then(function (canvas) {
                var context = canvas.getContext('2d');
                context.scale(1/scale, 1/scale);
                // 【重要】关闭抗锯齿
                context.mozImageSmoothingEnabled = true;
                context.webkitImageSmoothingEnabled = true;
                context.msImageSmoothingEnabled = true;
                context.imageSmoothingEnabled = true;  
                let contentWidth = canvas.width;  
                let contentHeight = canvas.height; 
                let pageHeight = contentWidth / 592.28 * 841.89; //contentWidth / 592.28 * 841.89; 
                let leftHeight = contentHeight;  
                let position = 0;  
                let imgWidth = 595.28;  
                let imgHeight = 592.28 / contentWidth * contentHeight;
                let pageData = canvas.toDataURL('image/jpeg', 1.0);  
                let PDF = new JsPDF('', 'pt', 'a4');  
                if (leftHeight < pageHeight) {  
                PDF.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight)  
                } else {  
                    while (leftHeight > 0) {  
                        PDF.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight);  
                        leftHeight -= pageHeight;  
                        position -= 841.89  
                        if (leftHeight > 0) {  
                            PDF.addPage();  
                        }  
                    }  
                }  
                PDF.save(title + '.pdf');
                that.downloading = false;        
            })
            }  
                
    }
    
  }
</script>
<style>
    .mysearch .el-checkbox__label{
        float:left;
        line-height:40px;
        padding-right:5px;
        color:#606266;
        font-weight: normal;
    }
</style>
<style scoped lang="scss">
    .search{
        margin-top:5px;        
    }
    #pdfDom{
       width:80%;
       margin:auto
    }
    .mytable{                        
        width:800px;
        margin:auto;
         
        td,th{
            box-sizing: border-box;
            color:#000;
            font-family: STSong;            
            /*border:1px solid #ebeef5;*/ 
        }
        td{
            font-size:14px;
            line-height:14px;
            padding-top:2px;
            padding-bottom:2px;
        }
        th{
            text-align:right;
            height:20px;
            line-height:20px;
            padding:5px;
        }
        td:nth-of-type(5){
            text-align:center;
        }
        th:nth-of-type(1),td:nth-of-type(1){
            width:50%;
        }
        
        .borderTop{
            border-top:1px solid #000;
        }
        .firstPage{
            padding-top:10px;//60
        }
        .pageSpace{
            padding-top:10px;//60
            border-top:1px dashed #000;
        }
    }
    @media print {
        .mysearch,#app>div>div:nth-of-type(1){
            display:none!important;
        }
        .mytable{
            page-break-after: always;
            /* margin-top:30px; */
            width:700px!important;
        }
        .mytable td, .mytable th{
            font-family: 'Times New Roman', Times, serif;
        }     
        #pdfDom{
            margin-left:10px;
            font-family: 'Times New Roman'!important;
        }
        .pageSpace{
            border-top:0px!important;
        }
        @page { 
            size: A4 portrait;             
        }
        @page { 
            margin-right:20px;             
        }
    
    }
    
    
 
    
</style>