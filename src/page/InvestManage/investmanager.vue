<template>
<div class="investManager">
    <Loan></Loan>
</div>
</template>
<script>
import Loan from './subinvests/loan'
export default {
    name:"investmanger",
    data(){  
        return {
            a:1
        }   
    },
    components:{
        Loan
    } 	
}
</script>
<style scoped lang="scss">
	.investManager{
        margin-top:20px;
    }
</style>