const actions = {
	storedata({commit},id){
        commit('storedata',id);
        localStorage.portfolioid=id;		
    },
    updateData({commit}){
        var id=localStorage.portfolioid;
        commit('updateData',id);
    },
    saveCapTabel({commit},data){
        commit('saveCapTabel',data);
    },
    saveCapTabelList({commit},data){
        commit('saveCapTabelList',data);
    },
    isDetail({commit},data){
        commit('isDetail',data);
    }
}

export default actions