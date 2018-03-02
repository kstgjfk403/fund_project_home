
import Vue from "vue"
import Vuex from "vuex"
import actions from "./actions"
Vue.use(Vuex)
const state={
    portfolioid:'',
    capTabelData:[],
    capTabelList:[],
    isDetail:'',
    storetotalboardseatno:'',
    isLogin:false
}

const getters={
	
}
const mutations={
	storedata(state,id){
		state.portfolioid=id;
    },
    updateData(state,id){
        state.portfolioid=id;
    },
    saveCapTabel(state,data){
        state.capTabelData=data;
    },
    saveCapTabelList(state,data){
        state.capTabelList=data;
    },
    isDetail(state,data){
        //console.log(data.isDetail)
        state.isDetail=data.isDetail;
    },
    updateIsDetail(state,data){
        state.isDetail=data;
    },
    storetotalboardseatno(state,data){
        state.storetotalboardseatno=data;
    }
}

const store=new Vuex.Store({
	state,
	getters,
	mutations,
	actions
})
export default store