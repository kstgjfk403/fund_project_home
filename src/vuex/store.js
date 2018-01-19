
import Vue from "vue"
import Vuex from "vuex"
import actions from "./actions"
Vue.use(Vuex)
const state={
	portfolioid:''
}

const getters={
	
}
const mutations={
	storedata(state,id){
		state.portfolioid=id;
    },
    updateData(state,id){
        state.portfolioid=localStorage.portfolioid;
    }
}

const store=new Vuex.Store({
	state,
	getters,
	mutations,
	actions
})
export default store