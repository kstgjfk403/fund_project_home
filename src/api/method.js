export function switchStr(obj){
        var datastr=JSON.stringify(obj);
        var dataobj=JSON.parse(datastr);
        var str;
        for(var i in dataobj){
            if(dataobj[i] instanceof Array){
                str=dataobj[i].join();
                dataobj[i]=str;
            }
        }
        return dataobj;
    }   
//新建人员信息对象格式转换。
export function translateFormat(arr){
        var newobj={};
        var len=arr.length;
        for(var i=0;i<len;i++){
            if(!newobj.hasOwnProperty(arr[i].baseType)){
                var proname=arr[i].baseType
                newobj[proname]=arr[i].baseInfoList
            }
        }
        return newobj;
    }
//删除对象属性
export function deleteattr(obj){
    var reg=/(str)$/;
    for(var i in obj){
        if(reg.test(i)){    
            delete obj[i]
        }
    }
    return obj;
 }
 //null转成''
 export function nulltostr(obj){
    for(var i in obj){
        if(obj[i]==null){
            obj[i]="";
        }
    }
    return obj;
}
//将毫秒数转成标准时间格式;
export function toLocalString(ms,symbol){
    var reg = /^-?[1-9][0-9]*$/;
    var marker,dateObj,timeString;
    if(reg.test(ms)){
        marker=symbol||"/";
        dateObj=new Date(ms);
        timeString=dateObj.getFullYear()+marker+(dateObj.getMonth()+1)+marker+dateObj.getDate();
    }
    return timeString
}
//将标准时间转成毫秒数格式;//要求日期格式2018/12/23
export function toMs(date){
    var toms;
    console.log(typeof date=='string');
    if(typeof data=='string'){
        toms=new Date(date).getTime();
    }
    return toms;
}

//时间对象格式化
export function formatTime(time,symbol){
    var marker=symbol||'/';
     var timeString=time.getFullYear()+marker+(time.getMonth()+1)+marker+time.getDate();
    return timeString;
}
//数字千分位格式化
export function toThousands(num) {  
    var result = '', counter = 0;  
    num = (num || 0).toString();  
    for (var i = num.length - 1; i >= 0; i--) {  
        counter++;  
        result = num.charAt(i) + result;  
        if (!(counter % 3) && i != 0) { result = ',' + result; }  
    }  
    return result;  
}
