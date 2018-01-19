const actions = {
	storedata({commit},id){
        commit('storedata',id);
        localStorage.portfolioid=id;		
    },
    updateData({commit}){
        var id=localStorage.portfolioid;
        console.log('action'+id)
        commit('updateData',id)
    }
}

export default actions