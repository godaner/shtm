<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${manageStaticSrcUrl}/css/east.css">

<div class="easyui-layout" data-options="fit:true">
	<div class="" data-options="region:'north'" style="height:50%">
		<div id="cc" class="easyui-calendar" data-options="fit:true"></div>
	</div>
	<div class="" data-options="fit:true,region:'center'">
		<div id="cc" class="easyui-panel" data-options="fit:true">
			<table id="onlineAdmins_datagrid" style="height:50%">
			
			</table>
		</div>
	</div>
</div>
