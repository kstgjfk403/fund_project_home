<template>
<div class="investManager" ref="Invest">
    <div class="title">Invest History</div>
    <Loan></Loan>
    <Invest></Invest>
    <Exit></Exit>
    <Warran></Warran>
</div>
</template>
<script>
import Loan from './subinvests/loan';
import Invest from "./subinvests/invest";
import Exit from "./subinvests/exit";
import Warran from "./subinvests/warran";
import bus from "@/api/eventbus";
export default {
    name:"investmanger",
    props:['id'],
    data(){  
        return {
            a:1,
            heightObj:''
        }   
    },
    created(){
    },
    mounted(){
        bus.$on('toScorll',(ace,arr)=>{
           this.scrolltoview(ace,arr);
       });
    },
    methods:{
        scrolltoview(eletoview,arr){
            var obj=this.$refs[eletoview];
            if(!this.heightObj){
                this.heightObj=this.$refs["Invest"].offsetHeight
            }
            if(!obj){
                this.$refs["Invest"].style.height=50+"px";
                this.$refs["Invest"].style.overflow='hidden';
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
    components:{
        Loan,
        Invest,
        Exit,
        Warran
    } 	
}
</script>
<style scoped lang="scss">
	.investManager{
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