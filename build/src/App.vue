<template>
  <div id="app">
      <router-view></router-view>
  </div>
   
</template>

<script>
import Listpage from "./components/Listpage"
import Detail from "./components/Detail"	
export default {
  name: 'app',
  components:{
    Listpage,
    Detail
    }
}
</script>

<style lang='scss'>

  @import './styles/_reset.scss';
	
</style>
