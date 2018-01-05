<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/common/import_common.jsp"></jsp:include>
<%-- <script type="text/javascript" src="${basePath }library/json2/json2.js" ></script>  --%>
<link rel="stylesheet" href="${basePath }library/jquery-jcrop/cropper.min.css">
<link rel="stylesheet" href="${basePath }library/jquery-timeline/css/timeline.css">
<link rel="stylesheet" href="${basePath }common/accordion-menu.css">

<script type="text/javascript" src="${basePath }ims/workspace/workspaceMain.js"></script>
<script type="text/javascript" src="${basePath }library/jquery-easyui-datagrid-transposedview/datagrid-transposedview.js"></script>
<script type="text/javascript" src="${basePath }library/jquery-scrollSpy/jquery-scrollspy.js"></script>
<script type="text/javascript" src="${basePath }library/jquery.stickUp/stickUp.js"></script>
<style type="text/css">

#mainLayout span{
	font-size:12px;
}

.lvd-process-header > span{
	position: absolute;
    display: block;
}
#fundMainGridDiv tr{
	height: 32px;
}
</style>	
</head>
<body>
<jsp:include page="/common/top.jsp"></jsp:include>
	<div class="lvd_container">
		<div id="menu-leftDiv" class="menu-left" style="margin-right:75px;">
			<ul id="accordion" style="margin-left:0px;">
				<li style="list-style-type:none; border: solid 1px grey; border-radius:3px; border-bottom-width: 0px;background-color: #F9F9F9;margin-bottom:2px">
					<div class="link">
						<a href="#scroll_fundMain" id="activeManage"  onclick="initFocusFund('activeManage')" class="menuItem active">
							<span style="font-family:'华文细黑'">&nbsp;主动管理基金</span>
						</a></div>
				</li>
				<li style="list-style-type:none; border: solid 1px grey; border-radius:3px; border-bottom-width: 0px;background-color: #F9F9F9;margin-bottom:2px">
					<div class="link">
						<a href="#scroll_fundMain2" id="special" onclick="initFocusFund('special')" class="menuItem">
						    <span style="font-family:'华文细黑'">&nbsp;专项基金</span>
					    </a>
					</div>
				</li>
				<li style="list-style-type:none; border: solid 1px grey; border-radius:3px; border-bottom-width: 0px;background-color: #F9F9F9;margin-bottom:2px">
					<div class="link"><a href="#scroll_fundMain3" id="mother" onclick="initFocusFund('mother')" class="menuItem">
						<span style="font-family:'华文细黑'">&nbsp;母基金</span></a>
					</div>
				</li>
				<li style="list-style-type:none; border: solid 1px grey; border-radius:3px; border-bottom-width: 0px;background-color: #F9F9F9;margin-bottom:2px">
					<div class="link"><a href="#scroll_fundMain4" id="seedFof" onclick="initFocusFund('seedFof')" class="menuItem">
						<span style="font-family:'华文细黑'">&nbsp;Seed Fof</span></a></div>
				</li>
				<li style="list-style-type:none; border: solid 1px grey; border-radius:3px; border-bottom-width: 1px;background-color: #F9F9F9;margin-bottom:2px">
					<div class="link"><a href="#scroll_fundMain5" id="allFund" onclick="initFocusFund('allFund')" class="menuItem">
						<span style="font-family:'华文细黑'">&nbsp;所有基金</span></a></div>
				</li>
			</ul>
		</div>
		<div id = "fundMainGridDiv" style="margin:10px 10px 10px 20px;">
			<legend id="scroll_fundMain" class="scrollItem"><span><s:text name=""/></span></legend>
			<legend id="scroll_fundMain2" class="scrollItem"><span><s:text name=""/></span></legend>
			<legend id="scroll_fundMain3" class="scrollItem"><span><s:text name=""/></span></legend>
			<legend id="scroll_fundMain4" class="scrollItem"><span><s:text name=""/></span></legend>
			<legend id="scroll_fundMain5" class="scrollItem"><span><s:text name=""/></span></legend>
			<!-- <span>我关注的项目</span> -->
			<div  id="fundMainGrid"></div>
		</div>
		<div id="invProjPanel" class="easyui-panel" data-options="closed:true" style="display:none">
			<div style="margin-left: 130px;">
				<a style="float: left;margin-left: 10px"href="javascript:void(0)" id="invProjcloseBtn" onclick="$.tooltiper('hide')" class="easyui-linkbutton lvd-linkbutton"
				   data-options="plain:false,iconCls:'icon-cancel'"><s:text name="common.close"/></a>
			</div>
			<table id="invProjGrid"></table>
		</div>
	</div>
</body>
</html>