
<template>
    <ol class="navlist droid">
        <li class="title">Nav</li>
        <li v-for="(value,key) in isactive" @click="scrollto(key)" :class="{navactive:value}">{{key}}</li>
    </ol>
</template>
<script>
import bus from "@/api/eventbus"
export default {
    name:"navlist",
    props:['isactive'],
    data:function(){
      return {

      }
    },
    mounted:function(){
        console.log(this.isactive)
    },
    methods:{
        confirmActive(obj){
            var isActive=this.isactive;
            for(var item in isActive){
                isActive[item]=false;
            }
            isActive[obj]=true;
        },
        scrollto(ele){
            this.confirmActive(ele);
            bus.$emit('toScorll',ele,this.isactive);
            this.$emit('toscorll',ele,this.isactive);  
        }
    }	
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
   
    .navlist{
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
    .navlist li{
        padding:10px 10px;
        cursor: pointer;
        text-overflow:ellipsis;
        overflow:hidden;
        white-space:nowrap;
        border-bottom:1px solid #ddd;
        text-align: center;
    }
    .navactive{
        background:#00a1e9;
        color:white;
    }
    .navlist .title{
        text-align:center;
        font-weight:600;
        background:white;
        color:#00a1e9;
    }     
</style>
