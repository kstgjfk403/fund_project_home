export function switchStr(obj) {
  var datastr = JSON.stringify(obj);
  var dataobj = JSON.parse(datastr);
  var str;
  for (var i in dataobj) {
    if (dataobj[i] instanceof Array) {
      str = dataobj[i].join();
      dataobj[i] = str;
    }
  }
  return dataobj;
}
//Create人员信息对象格式转换。
export function translateFormat(arr) {
  var newobj = {};
  var len = arr.length;
  if (arr) {
    for (var i = 0; i < len; i++) {
      if (!newobj.hasOwnProperty(arr[i].baseType)) {
        var proname = arr[i].baseType
        newobj[proname] = arr[i].baseInfoList
      }
    }
  }
  return newobj;
}
//删除对象属性
export function deleteattr(obj) {
  var reg = /(str)$/;
  for (var i in obj) {
    if (reg.test(i)) {
      delete obj[i]
    }
  }
  return obj;
}
//null转成''
export function nulltostr(obj) {
  for (var i in obj) {
    if (obj[i] == null) {
      obj[i] = "";
    }
  }
  return obj;
}
//将毫秒数转成标准时间格式;
export function toLocalString(ms, symbol) {
  var reg = /^-?[1-9][0-9]*$/;
  var marker, dateObj, timeString;
  if (reg.test(ms)) {
    marker = symbol || "/";
    dateObj = new Date(ms);
    timeString = dateObj.getFullYear() + marker + (dateObj.getMonth() + 1) + marker + dateObj.getDate();
  }
  return timeString
}
//将标准时间转成毫秒数格式;//要求日期格式2018/12/23
export function toMs(date) {
  var toms;
  toms = new Date(date)
  var ms = toms.getTime();
  return ms;
}
//时间对象格式化
export function formatTime(time, symbol) {
  var marker = symbol || '/';
  var timeString = time.getFullYear() + marker + (time.getMonth() + 1) + marker + time.getDate();
  return timeString;
}
//数字千分位格式化
export function toThousands(num) {

    if (num == null || num == undefined)
    return "";
  var flag=false;
  var indexDot = num.toString().indexOf('.');
  var dot;
  if (indexDot > 0) {
    dot = num.toString().substring(indexDot);
    num = num.toString().substring(0, indexDot);
  }
  var result = '', counter = 0;
  var reg = /^\-/;
  var reg1=/\(/g;
  
  
  num = (num || 0).toString();
  if(reg1.test(num)){
    num = num.replace(reg1,'');
    flag=true;
  }else{
    flag=false;
  }
  var num1 = num;
  num = num.replace(reg, '');
  for (var i = num.length - 1; i >= 0; i--) {
    counter++;
    result = num.charAt(i) + result;
    if (!(counter % 3) && i != 0) {
      result = ',' + result;
    }
  }
  if (num1 < 0) {
    result = "-" + result
  }
  if (indexDot > 0) {
    result = result + dot;
  }
  if(flag==true){
      result='('+result;
  }
  return result;
}
//合并单元格
export function mc(tableId, startRow, endRow, col) {
  var tb = document.getElementById(tableId);
  if(tb){
    if (col >= tb.rows[0].cells.length) {
      return;
    }
    if (col == 0) {
      endRow = tb.rows.length - 1;
    }
    for (var i = startRow; i < endRow; i++) {
      if (tb.rows[startRow].cells[col].textContent) {
        if (tb.rows[startRow].cells[col].textContent == tb.rows[i + 1].cells[0].textContent) {
          tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[0]);
          tb.rows[startRow].cells[col].rowSpan = (tb.rows[startRow].cells[col].rowSpan | 0) + 1;
          if (i == endRow - 1 && startRow != endRow) {
            mc(tableId, startRow, endRow, col + 1);
          }
        } else {
          mc(tableId, startRow, i + 0, col + 1);
          startRow = i + 1;
        }
      }
    }
  }
}

//拼接字符串
export function ConcatStr(data) {
  var len = data.length;
  for (var i = 0; i < len; i++) {
    var obj = data[i].baseName, str = '';
    for (var j in obj) {
      if (j == 'fundfamillyname' || j == 'securitytype' || j == 'round' || j == 'shareremain') {
        str += obj[j] + '_'
      }
    }

    data[i].baseName = str.slice(0, str.length - 1);
  }
  return data;
}

