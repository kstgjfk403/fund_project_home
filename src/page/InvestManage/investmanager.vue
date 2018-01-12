<template>
<div class="investManager">
    <Loan></Loan>
</div>
</template>
<script>
// import Header from "@/components/common/Header";	
import axioss from '@/api/axios';
import * as method from "@/api/method";
import Loan from './subinvests/loan'
export default {
    name:"investmanger",
    data(){  
        return {

        }   
    },
    updated(){  
        
    },
    mounted(){
       
    },
    methods:{ 
        
    },
    watch:{
        'addShareShow':{
            handler(newValue,oldValue){
                   
            },
            deep:true
        }
    },
    directives:{
        isedit:{
            inserted:function(el){
                
            }
        }
    },
    components:{
        Loan
    } 	
}
</script>
<style scoped lang="scss">
	
</style>