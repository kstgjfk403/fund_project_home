var dictTypeArray = ['induType', 'projLevel', 'busType', 'procType', 'meetType', 'procSts', 'traceType', 'stsType'];
var dictArray = getDictArray(dictTypeArray.toString());

var induType = getTypeArray('induType', dictArray); //查询基础数据类型为'induType'   所属行业
var projLevel = getTypeArray('projLevel', dictArray); //查询基础数据类型为'projLevel' 项目级别

var projMach = getMachArray('PROJ'); //查询状态机类型为PROJ的状态基础数据               项目阶段
var userCombox = getUserDup(getUserForCombox()); //所有有效用户下拉数据
var allUserCombox = getAllUserForCombox(); //所有用户下拉数据(包括失效用户)
var userManage = getUserDup(userCombox);  //项目经理
var busType = getTypeArray('busType', dictArray); // 查询基础数据类型为'busType'
var procType = getTypeArray('procType', dictArray);
var procSts = getTypeArray('procSts', dictArray);
var projComboxData = getProjInfoForCombox(); // 所有用户下拉数据
var meetTypeComboxData = getTypeArray('meetType', dictArray); // 查询基础数据类型为'meetType'
var traceType = getTypeArray('traceType', dictArray); // 查询基础数据类型为'meetType'
var stsType = getTypeArray('stsType', dictArray); //查询基础数据类型为'stsType'      项目状态
var paras, isAdd = false;
var progPermission = getProgPermission(getProgId());

var taskIndex = [], loaded = false, n = 0, usersExceptCurrentUser = [];

$.each(userCombox, function (i, u) {
    if (u.userId != loginUser.userId) {
        usersExceptCurrentUser.push(u);
    }
});

$(function () {//页面加载完成后，初始化JS
    initFocusFund('activeManage');
    initInvProj();
    $("#invProjPanel").css("display", "block");
    $('#invProjPanel').panel({
        closed: true,
        border: false,
        width: 230,
        height: 360
    });
});//初始化完成

function forwardNewPage(url) {
    $.tooltiper('hide');
    ld_forword(url, "", "", "_blank");
}
var ids;
var initFocusFund = function (fundSort) {

    document.getElementById("activeManage").style.color="#009DEA";
    document.getElementById("special").style.color="#009DEA";
    document.getElementById("mother").style.color="#009DEA";
    document.getElementById("seedFof").style.color="#009DEA";
    document.getElementById("allFund").style.color="#009DEA";

    document.getElementById(fundSort).style.color="#B63B4D";



    $('#fundMainGrid').datagrid({
        height: 635,
        showHeader: true,
        view: transposedview,
        headerWidth: 160,
        itemWidth: 128,
       // title: "基金信息",
        title:'<div id="allLpEntityMainGridTitleId" style="float:left;">基金信息(IRR等统计数据截至2017年3季度末)</div>',
        singleSelect: true,
        rownumbers: false,
        remoteSort: false,
        multiSort: true,
        scrollbarSize: 100,
        striped: true,
        idField: 'fundId',
        nowrap: false,
        autoRowHeight: true,
       // url: basePath + 'fund/fundInfoAction_queryAllFundInfo4HomePageQuicker.action?fundInfo.fundType=equity',
        url: basePath + 'fund/fundInfoAction_queryAllFundInfo4HomePageQuicker.action?fundInfo.fundSort=' + fundSort,
        columns: [
            [
            	
                {
                    field: 'fundName', title: '', align: 'center',
                    formatter: function (value, row, index) {
                        if (value && value != undefined && value != '') {
                            var html = "<div style=\"cursor: pointer;\"  id='fundId" + row.fundId + "' onclick=\"forwardFundInfo(\'" + row.fundId + "\','')\" ><span style='color:blue;' class='selected-to-white-font'>" +
                                ""+row.fundName+"</span></div>";
                            return html;
                        }
                        return;
                    }
                },
                
                {
                    field: 'foundDate', title: '成立日期', align: 'center', sortable: true,
                    formatter: function (value, row, index) {
                        if (value){
                        	return $.fn.datebox.defaults.formatter(new Date(value));
                        }else{
                        	return '-';
                        	
                        	
                        }
                    }
                },
                {
                    field: 'regAmt', title: '基金规模(亿元)', align: 'right', sortable: true,
                    formatter: function (value, row, index) {
                        if (value && value != undefined && value != '') {
                            return (parseFloat(value)/100000000).format1({precision: "1"});
                        }
                        return '-';
                    }
                
                },
                {
                    field: 'totalPaidAmtPropor', title: '实缴比例', align: 'right',
                    formatter: function (value, row, index) {
                        if (value && value != undefined && value != '') {
                            return parseFloat(value * 100).format1({precision: "2"}) + '%';
                        }
                        return '-';
                    }
                },
                {
                    field: 'sumInvCost', title: '已投资总额(亿元)', align: 'right', sortable: true,
                    formatter: function (value, row, index) {
                        if (value && value != undefined && value != '') {
                            return (parseFloat(value)/100000000).format1({precision: "1"});
                        }
                        return '-';
                    }
                },
                {
                    field: 'sumInvPipe', title: '已投+pipeline总额(亿元)', align: 'right',
                    formatter: function (value, row, index) {
                        if (row.sumInvCost && row.sumInvCost != undefined && row.sumInvCost != '' && row.pipelineAmtSum && row.pipelineAmtSum != undefined && row.pipelineAmtSum != '') {
                            // var invPipePropor = (row.sumInvCost+row.pipelineAmtSum)/row.regAmt;
                            // return parseFloat(invPipePropor*100).format1({precision:"2"}) + '%';
                            var invPipePropor = (row.sumInvCost + row.pipelineAmtSum);
                            return (parseFloat(invPipePropor)/100000000).format1({precision: "1"});
                        }
                        return '-';
                    }
                },
                {
                    field: 'totalActualAmt', title: '已分配总额(亿元)', align: 'right', sortable: true,
                    formatter: function (value, row, index) {
                        if (value && value != undefined && value != '') {
                            return (parseFloat(value)/100000000).format1({precision: "1"});
                        }
                        return '-';
                    }
                },
                // {field:'pipelineAmtSum',title:'pipeline',align:'right', sortable:true,
                //     formatter:function(value,row,index){
                //         if(value && value != undefined && value!=''){
                //             return parseFloat(value).format1({precision:"0"});
                //         }
                //         return ;
                //     },
                //     sorter:function(a,b){
                //         return numberSort(a,b)
                //     }},

                // {field:'sumInvPropor',title:'投资比例(%)',align:'right',
                //     formatter:function(value,row,index){
                //         if(value && value != undefined && value!=''){
                //             return parseFloat(value*100).format1({precision:"2"}) + '%';
                //         }
                //         return ;
                //     }
                // },
                {
                    field: 'netAssets', title: '基金净资产(亿元)', align: 'right',
                    formatter: function (value, row, index) {
                        if (value && value != undefined && value != '') {
                            return (parseFloat(value)/100000000).format1({precision: "1"});
                        }
                        return '-';
                    }
                },
                {
                    field: 'invProjCount', title: '已投项目数量', align: 'right',
                    formatter: function (value, row, index) {
                        if (value && value != undefined && value != '') {
                            var html = "<div style=\"cursor: pointer;\"  id='invProjCount" + index + "' onclick=\"openInvProjPanel($(this),\'" + row.fundId + "\','')\" ><span style='color:blue;' class='selected-to-white-font'>"
                                + parseFloat(value).format1({precision: "0"}) + "</span></div>";
                            return html;
                        }
                        return '-';
                    }
                },
                {
                    field: 'holdProjCount', title: '持有项目数量', align: 'right',
                    formatter: function (value, row, index) {
                        if (value && value != undefined && value != '') {
                            var html = "<div style=\"cursor: pointer;\"  id='holdProjCount" + index + "' onclick=\"openHoldProjPanel($(this),\'" + row.fundId + "\')\" ><span style='color:blue;' class='selected-to-white-font'>"
                                + parseFloat(value).format1({precision: "0"}) + "</span></div>";
                            return html;
                        }
                        return '-';
                    }
                },
                {
                    field: 'projStockCount', title: '已上市项目数量', align: 'right',
                    formatter: function (value, row, index) {
                        if (value && value != undefined && value != '') {
                            var html = "<div style=\"cursor: pointer;\"  id='projStockCount" + index + "' onclick=\"openStockProjPanel($(this),\'" + row.fundId + "\')\" ><span style='color:blue;' class='selected-to-white-font'>"
                                + parseFloat(value).format1({precision: "0"}) + "</span></div>";
                            return html;
                        }
                        return '-';
                    }
                },
                {
                    field: 'projStockN3Count', title: '新三板已上市项目数量', align: 'right',
                    formatter: function (value, row, index) {
                        if (value && value != undefined && value != '') {
                        	var html = "<div style=\"cursor: pointer;\"  id='projStockN3Count" + index + "' onclick=\"openStockProjN3Panel($(this),\'" + row.fundId + "\')\" ><span style='color:blue;' class='selected-to-white-font'>"
                            + parseFloat(value).format1({precision: "0"}) + "</span></div>";
                        return html;
                        }
                        return '-';
                    }
                },
                {
                    field: 'zz', title: ''
                },
                // {
                //     field: 'd', title: 'Gross IRR', align: 'right', sortable: true
                // },
                /*
                {
                    field: 'NetIRR', title: 'Net IRR', align: 'right',
                    formatter: function (value, row, index) {
                        if (value && value != undefined && value != '') {
                            return parseFloat(value).format1({precision: "2"}) + '%';
                        }
                        return;
                    }
                },
                {
                    field: 'f', title: '已分配 / 实缴', align: 'right',
                    formatter: function (value, row, index) {
                        if (row.totalActualAmt && row.totalActualAmt != undefined && row.totalActualAmt != '' && row.totalPaidAmt && row.totalPaidAmt != undefined && row.totalPaidAmt != '') {
                            // var invPipePropor = (row.sumInvCost+row.pipelineAmtSum)/row.regAmt;
                            // return parseFloat(invPipePropor*100).format1({precision:"2"}) + '%';
                            var f = row.totalActualAmt / row.totalPaidAmt;
                            return parseFloat(f).format1({precision: "2"}) + 'x';
                        }
                        return;
                    }
                },
                {
                    field: 'g', title: '（已分配+已上市）/ 实缴', align: 'right',
                    formatter: function (value, row, index) {
                        if (row.sumStockPrice && row.sumStockPrice != undefined && row.sumStockPrice != '' && row.totalActualAmt && row.totalActualAmt != undefined && row.totalActualAmt != '' &&
                            row.totalPaidAmt && row.totalPaidAmt != undefined && row.totalPaidAmt != '') {
                            var f = (row.sumStockPrice + row.totalActualAmt) / row.totalPaidAmt;
                            return parseFloat(f).format1({precision: "2"}) + 'x';
                        }
                        return;
                    }
                },
                {
                    field: 'h', title: '总估值 / 实缴', align: 'right',
                    formatter: function (value, row, index) {
                        if (row.sumFairAmt && row.sumFairAmt != undefined && row.sumFairAmt != '' && row.totalPaidAmt && row.totalPaidAmt != undefined && row.totalPaidAmt != '') {
                            var f = row.sumFairAmt / row.totalPaidAmt;
                            return parseFloat(f).format1({precision: "2"}) + 'x';
                        }
                        return;
                    }
                }
                */
                {
                    field: 'NetIRR', title: 'Net IRR', align: 'right',
                    formatter: function (value, row, index) {
                    	if(value == ''){
                    		return '-';
                    	}
                        if (value && value != undefined && value != '' && value.indexOf("%") != -1) {
                            return value;
                        }else if (value && value != undefined && value != '' && value.indexOf("%") == -1){
                        	 return parseFloat(value).format1({precision: "2"}) + '%';
                        }else{
                        	return;
                        }
                        
                    }
                },
                {
                    field: 'GrossIRR', title: 'Gross IRR', align: 'right',
                    formatter: function (value, row, index) {
                    	if(value == ''){
                    		return '-';
                    	}
                        if (value && value != undefined && value != '' && value.indexOf("%") != -1) {
                            return value;
                        }else if (value && value != undefined && value != '' && value.indexOf("%") == -1){
                        	 return parseFloat(value).format1({precision: "2"}) + '%';
                        }else{
                        	return;
                        }
                        
                    }
                },
                {
                    field: 'f', title: '已分配 / 实缴', align: 'right',
                    formatter: function (value, row, index) {
                    	if(value == ''){
                    		return '-';
                    	}
                        if (value && value != undefined && value != '' && value.indexOf("x") != -1) {
                            return value;
                        }else{
                        	if (row.totalActualAmt && row.totalActualAmt != undefined && row.totalActualAmt != '' && row.totalPaidAmt && row.totalPaidAmt != undefined && row.totalPaidAmt != '') {
                                // var invPipePropor = (row.sumInvCost+row.pipelineAmtSum)/row.regAmt;
                                // return parseFloat(invPipePropor*100).format1({precision:"2"}) + '%';
                                var f = row.totalActualAmt / row.totalPaidAmt;
                                return parseFloat(f).format1({precision: "2"}) + 'x';
                            }else{
                                return;
                            }
                        }
                    }
                },
                {
                    field: 'g', title: '（已分配+已上市）/ 实缴', align: 'right',
                    formatter: function (value, row, index) {
                    	if(value == ''){
                    		return '-';
                    	}
                        if (value && value != undefined && value != '' && value.indexOf("x") != -1) {
                            return value;
                        }else{
                        	if (row.sumStockPrice && row.sumStockPrice != undefined && row.sumStockPrice != '' && row.totalActualAmt && row.totalActualAmt != undefined && row.totalActualAmt != '' &&
                                    row.totalPaidAmt && row.totalPaidAmt != undefined && row.totalPaidAmt != '') {
                                    var f = (row.sumStockPrice + row.totalActualAmt) / row.totalPaidAmt;
                                    return parseFloat(f).format1({precision: "2"}) + 'x';
                                }
                                return;
                        }
                    }
                },
                {
                    field: 'h', title: '总估值 / 实缴', align: 'right',
                    formatter: function (value, row, index) {
                    	if(value == ''){
                    		return '-';
                    	}
                        if (value && value != undefined && value != '' && value.indexOf("x") != -1) {
                            return value;
                        }else{
                        	if (row.sumFairAmt && row.sumFairAmt != undefined && row.sumFairAmt != '' && row.totalPaidAmt && row.totalPaidAmt != undefined && row.totalPaidAmt != '') {
                                var f = row.sumFairAmt / row.totalPaidAmt;
                                return parseFloat(f).format1({precision: "2"}) + 'x';
                            }
                            return;
                        }
                    }
                }
            ]],
        // onDblClickRow: function (index, row) {
        //     $.tooltiper("hide");
        //     ld_forword(basePath + "fund/fundInfoAction_fundWholeDisplayPage.action?fundInfoKey.fundId=" + row.fundId, "", "", "_blank");
        // },
        onBeforeSortColumn: function (sort, order) {
            $.tooltiper('hide');
        },
        //使用transposedview的方法
        onSelect: function (rowIndex, rowData) {
            $(this).datagrid('clearSelectionsT', rowIndex);
        },
        onLoadSuccess: function (data) {
            // $(".datagrid-btable").html("")
            if (data.rows && data.rows.length > 0) {
                $.each(data.rows, function (i, r) {
                    ids += "," + r.fundId;
                });
                ids = ids.substr(1);
            }
            $(".datagrid-body").css("overflow-x","scroll");
            //scrollShow($('#fundMainGrid'));
        }
    });
}
function forwardFundInfo(fundId) {
    ld_forword(basePath + "fund/fundInfoAction_fundWholeDisplayPage.action?fundInfoKey.fundId=" + fundId, "", "", "_blank");
}
function initInvProj() {
    $('#invProjGrid').datagrid({
        width: 220,
        height:300,
        idField: 'projId',
        singleSelect: true,
        rownumbers: true,
        remoteSort: false,
        checkOnSelect: true,
        selectOnCheck: false,
        multiSort: true,
        scrollbarSize: 100,
        // striped: true,
        nowrap: false,
       // autoRowHeight: true,
        url: null,
        columns: [[
            {
                field: 'projName', title: '项目名称', width: 170, halign: 'center', align: 'center',
                formatter: function (value, row, index) {
                    if (value && value != undefined && value != '') {
                        var html = '<a href="#" onclick="forwardNewPage(\'' + basePath + 'ims/projInfoAction_projWholePage.action?key.projId=' + row.projId + '\')">' + value + '</a>';
                        return html;
                    }
                    return;
                }
            }
        ]],
        onLoadSuccess: function (data) {
        },
        onDblClickRow: function (index, row) {
        }
    });
}

var openInvProjPanel = function (_tooltipOwner, fundId) {
    loadInvProj(fundId);
    _tooltipOwner.tooltiper({
        content: $("#invProjPanel")
    }).show();

}
var openHoldProjPanel = function (_tooltipOwner, fundId) {
    loadHoldProj(fundId);
    _tooltipOwner.tooltiper({
        content: $("#invProjPanel")
    }).show();
}
var openStockProjPanel = function (_tooltipOwner, fundId) {
    loadStockProj(fundId);
    _tooltipOwner.tooltiper({
        content: $("#invProjPanel")
    }).show();
}
var openStockProjN3Panel = function (_tooltipOwner, fundId) {
    loadStockProjN3(fundId);
    _tooltipOwner.tooltiper({
        content: $("#invProjPanel")
    }).show();
}


function loadInvProj(fundId) {
    var opt = $("#invProjGrid").datagrid("options");
    opt.url = basePath + 'ims/projInfoAction_queryForFundWorkSpace.action';
    opt.queryParams = {
        "fundInfoKey.fundId": fundId
    };
    $('#invProjGrid').datagrid("reload");
}
function loadHoldProj(fundId) {
    var opt = $("#invProjGrid").datagrid("options");
    opt.url = basePath + 'ims/projInfoAction_queryForFundHold.action';
    opt.queryParams = {
        "fundInfoKey.fundId": fundId
    };
    $('#invProjGrid').datagrid("reload");
}
function loadStockProj(fundId) {
    var opt = $("#invProjGrid").datagrid("options");
    opt.url = basePath + 'ims/projListStockInfoAction_queryProjListStockByFundWithoutN3.action';
    opt.queryParams = {
        "fundId": fundId
    };
    $('#invProjGrid').datagrid("reload");
}

function loadStockProjN3(fundId) {
    var opt = $("#invProjGrid").datagrid("options");
    opt.url = basePath + 'ims/projListStockInfoAction_queryProjListStockByFund.action';
    opt.queryParams = {
        "fundId": fundId,
        "projListStockInfo.exchange": "N3"
    };
    $('#invProjGrid').datagrid("reload");
}


function scrollShow(datagrid) {  
    datagrid.prev(".datagrid-view2").children(".datagrid-body").html("<div style='width:" + datagrid.prev(".datagrid-view2").find(".datagrid-header-row").width() + "px;border:solid 0px;height:1px;'></div>");  
}