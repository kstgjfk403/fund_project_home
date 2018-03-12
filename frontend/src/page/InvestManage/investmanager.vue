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
import Warran from "./subinvests/warrant";
import bus from "@/api/eventbus";
import mix from "@/api/mixin";
export default {
  name:"investmanger",
  mixins:[mix],
  data(){  
    return {
      a:1,
      heightObj:''
    }   
  },
  created(){
  },
  mounted(){
    bus.$on('toScorll',(ace)=>{
      this.scrolltoview(ace,'Invest');
    });
  },
  methods:{
      
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
</style>