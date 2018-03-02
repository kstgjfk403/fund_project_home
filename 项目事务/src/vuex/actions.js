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
        localStorage.isDetail=data.isDetail;
    },
    updateIsDetail({commit}){
        var isDetail=localStorage.isDetail;
        commit('updateIsDetail',isDetail);
    },
    storetotalboardseatno({commit},data){
        commit('storetotalboardseatno',data)
    }
}

export default actions