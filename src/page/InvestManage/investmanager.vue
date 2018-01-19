<template>
<div class="investManager" ref="Invest">
    <div class="title">Invest History</div>
    <Loan></Loan>
    <Invest></Invest>
</div>
</template>
<script>
import Loan from './subinvests/loan';
import Invest from "./subinvests/invest";
import mix from "@/api/mixin";
import bus from "@/api/eventbus";
export default {
    name:"investmanger",
    mixins:[mix],
    props:['id'],
    data(){  
        return {
            a:1 
        }   
    },
    created(){
    },
    mounted(){
        bus.$on('toscorll',(ace)=>{
           this.scrolltoview(ace);
       });
    },
    components:{
        Loan,
        Invest
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