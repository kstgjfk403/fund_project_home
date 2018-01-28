
<template>
<div style="height:56px;width:100%;">
    <div class="header" style="position:fixed;top:0;left:0;width:100%;z-index:90">
      <div class="navbar navbar-expand-lg navbar-dark bg-primary" style="">
      <div class="container">
        <a href="#" class="navbar-brand">InvestmentSystem</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation" style="">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown"  href="javascript:;">Fund<span class="caret"></span></a>
                    <div class="dropdown-menu">
                        <router-link to='fundlistpage' class="invest-fund">Fund</router-link>
                        <router-link to='fundlistpage' class="invest-fund">Fund</router-link>
                        <router-link to='fundlistpage' class="invest-fund">Fund</router-link>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown"  href="javascript:;">Portfolio<span class="caret"></span></a>
                    <div class="dropdown-menu">
                        <router-link class="invest-fund" to='listpage'>Portfolio</router-link>
                        <router-link class="invest-fund" to='valuation'>Valuation</router-link>
                        <router-link class="invest-fund" to='listpage'>Portfolio</router-link>
                    </div>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to='spvlist'>SPV</router-link>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" id="download">Cerulean <span class="caret"></span></a>
                    <div class="dropdown-menu" aria-labelledby="download">
                        <a class="dropdown-item" href="#">Open in JSFiddle</a>
                        <!-- <div class="dropdown-divider"></div> -->
                        <a class="dropdown-item" href="#">bootstrap.min.css</a>
                        <a class="dropdown-item" href="#">bootstrap.css</a>
                        <!-- <div class="dropdown-divider"></div> -->
                        <a class="dropdown-item" href="#">_variables.scss</a>
                        <a class="dropdown-item" href="#">_bootswatch.scss</a>
                    </div>
                </li>
            </ul>
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Register</a>
                </li>
            </ul>
        </div>
      </div>  
    </div>
    </div>
    </div>
</template>
<script>
export default {
    name:"Header",
    data:function(){
      return {
         
      }
    },
    mounted:function(){
       
    },
    methods:{
        
    } 	
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    
    .invest-fund{
        display: block;
        width: 100%;
        padding: 0.25rem 1.5rem;
        clear: both;
        font-weight: 400;
        color: #868e96;
        text-align: center;
        white-space: nowrap;
        background: none;
        border: 0;
        text-decoration: none;
    }
    .invest-fund:hover{
        background:#2FA4E7;
        color:white;
    }
    .navbar .container .dropdown .dropdown-menu{
        min-width: 7rem;
    }
</style>
