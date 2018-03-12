<template>
<div class="mainbonus loan" ref="Bonus">
    <div class="title">Bonus</div>
    <Bonus/>
    <DividEnd/>
</div>
</template>
<script>
import Bonus from '@/page/bonus/subBonus/Bonus'
import DividEnd from '@/page/bonus/subBonus/DividEnd'
import bus from '@/api/eventbus'
import mix from "@/api/mixin"
export default {
    name:"mianbonus",
    mixins:[mix],
    data(){
        return {
            
        }
    },
    mounted(){
        bus.$on('toScorll',(ace)=>{
           this.scrolltoview(ace,'Bonus');
        });
    },
    methods:{
    },    
    computed:{
        portfolioid(){
            if(this.$store.state.portfolioid==''){
                this.$store.dispatch('updateData');
            }
            return this.$store.state.portfolioid;
        },
        capFormList(){
            return this.$store.state.capTabelData;
        },
        isDetail(){
            if(this.$store.state.isDetail==''){
                this.$store.dispatch('updateIsDetail');
            }
            return this.$store.state.isDetail;
        },
        isTableShow(){
            let investtype=this.investForm.investtype;
            let fundfamilly=this.investForm.fundfamillyname;
            if((investtype=='Equity Investment&Loan To Equity'||investtype=='Loan To Equity')&&fundfamilly){
                if(this.isReqLoanToEquity){
                    this.reqLoanToEquity(fundfamilly,this.portfolioid)
                }
                return 'LoanToEquity1'
            }else{
                return ''
            }
        }
    },
    components:{
        Bonus,
        DividEnd
    }
}
</script>
<style scoped lang="scss">
	.mainbonus{
        margin-top:20px;
        border:1px solid #ddd;
    }
    
</style>
