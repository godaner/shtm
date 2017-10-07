<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${manageStaticSrcUrl}/css/clazzs_manage.css">
<!-- 工具栏 start -->
<div id="clazzs_dg_tb" style="display: none;">
	<form id="clazzsSearchForm">
		<input id="id" name="id" class="easyui-textbox input" data-options="prompt:'id'" style="width:100px">
		<input id="text" name="text" class="easyui-textbox input" data-options="prompt:'類型名'" style="width:100px">
		<a href="javascript:searchClazzs();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		<a href="javascript:clearGoodSearch();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">清空条件</a>
		|
		<a href="javascript:editClazzs();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑基本信息</a>
		|
		<a href="javascript:insertClazzs();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	</form>
</div>
<!-- 工具栏 end -->


<!-- easyui的datagrid start -->
<div id="clazzs_manage_content" class="easyui-panel" data-options="fit:true">

	<table id="clazzs_datagrid" class="easyui-datagrid" data-options="fit:true">  
		 
	</table>

</div>
<!-- easyui的datagrid end -->


<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/clazzs_manage.js"></script>
