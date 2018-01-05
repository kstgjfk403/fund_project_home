import axios from "axios";
import * as method from './method'
export default {
    //获取项目列表
    reqmemberlist(obj){
        return axios.post('/api/admin/proj/list',obj)
    },
    //删除
    reqdelet(id){
        return axios.post('/api/admin/proj/delete/'+id,{portfolioid:id})
    },
    //获取详情页信息
    reqdetails(id){
        return axios.get('/api/admin/proj/entity/'+id)
    },
    //获取字典下拉列表
    reqdroplist(obj){
        return axios.post('/api/sys/dict', obj)
    },
    //添加项目信息
    submitinfo(obj){
        var newobj=method.switchStr(obj)
        console.log(newobj);
        return axios.post("/api/admin/proj/add",newobj);
    },
    //修改项目信息
    modifyinfo(obj){
        var objj=method.switchStr(obj)
        return axios.post('/api/admin/proj/update',objj);
    },
    //获取项目列表2
    reqmemberlist2(obj){
        return axios.post('/api/admin/fund/list',obj)
    },
    //删除2
    reqdelet2(id){
        return axios.post('/api/admin/fund/delete/'+id,{portfolioid:id})
    },
    //获取详情页信息2
    reqdetails2(id){
        return axios.get('/api/admin/fund/entity/'+id)
    },
    //获取字典下拉列表2
    
    //添加项目信息2
    submitinfo2(obj){
        //var newobj=method.switchStr(obj);
        //console.log(newobj);
        return axios.post("/api/admin/fund/add",obj);
    },
    //修改项目信息2
    modifyinfo2(obj){
        //var objj=method.switchStr(obj)
        return axios.post('/api/admin/fund/update',obj);
    }
}