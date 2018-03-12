
<template>
<div style="height:56px;width:100%;">
    <div class="header" style="position:fixed;top:0;left:0;width:100%;z-index:90">
      <div class="navbar navbar-expand-lg navbar-dark bg-primary" style="">
      <div class="container">
        <a href="#" class="navbar-brand">UFund</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation" style="">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" data-toggle="dropdown"  href="#">Dashboard<span class="caret"></span></a>

                </li>

                <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown"  href="javascript:;">Fund<span class="caret"></span></a>
                    <div class="dropdown-menu">
                        <router-link to='fundlistpage' class="invest-fund">Fund</router-link>
                        <router-link to='capitalcalllistpage' class="invest-fund">Capital Call</router-link>
                        <router-link to='fundFinListPage' class="invest-fund">Fund Financial Info</router-link>
                        <router-link to='funddistribution' class="invest-fund">Fund Distri</router-link>
                        <router-link to='lpinformation' class="invest-fund">LP Information</router-link>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown"  href="javascript:;">Portfolio<span class="caret"></span></a>
                    <div class="dropdown-menu">
                        <router-link class="invest-fund" to='listpage'>Portfolio</router-link>
                        <router-link class="invest-fund" to='valuation'>Valuation</router-link>
                    </div>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to='spvlist'>SPV</router-link>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" id="download">Report<span class="caret"></span></a>
                    <div class="dropdown-menu">
                        <a class="invest-fund" href="javascript:;">Fund
                            <div class="subNav">
                                <router-link to='fundirrstasticpage' class="invest-fund">Fund IRR</router-link>
                                <router-link to='fundinvestoverviewpage' class="invest-fund">Fund Investment Overview</router-link>
                                <router-link to='fundtrackrecordpage' class="invest-fund">Fund Track Record</router-link>
                                <router-link to='fundgrosscfspage' class="invest-fund">Fund Gross CFs</router-link>
                            </div>
                        </a>
                        <a class="invest-fund" href="javascript:;">Portofolio
                            <div class="subNav">
                                <router-link to='financial_Report' class="invest-fund">Financial Report</router-link>
                                <router-link to='main_Business' class="invest-fund">Business Summary</router-link>
                            </div>
                        </a>
                        <router-link class="invest-fund" to='RealizedGainsLosses'>Realized Gains Losses</router-link>
                        <router-link class="invest-fund" to='InvestDetail'>Investment Details</router-link>
                        <router-link class="invest-fund" to='appendix_2'>Appendix II</router-link>
                        <router-link class="invest-fund" to='main_Schedule'>Schedule Investment</router-link>
                    </div>
                </li>
            </ul>
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
                <!-- <li class="nav-item">
                    <a class="nav-link" href="#">Register</a>
                </li> -->
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
        linkto(route){
            window.open(window.location.origin+"/"+"#/"+route);
        }
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
        text-align:left;
        white-space: nowrap;
        background: none;
        border: 0;
        text-decoration: none;
        position:relative;
    }
    .invest-fund:hover{
        background:#2FA4E7;
        color:white;
    }
    .navbar .container .dropdown .dropdown-menu{
        min-width: 7rem;
    }
    .subNav{
        position:absolute;
        top:0;left:199px;
        display: none;
    }
    .invest-fund:hover .subNav{
        display:block;
        background:white;
    }
</style>
