<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/users_manage.css">
<!-- 工具栏 start -->
<div id="users_dg_tb" style="display: none;">
	<form id="usersSearchForm">
		<input name="id" class="easyui-textbox input" data-options="prompt:'id'" style="width:100px">
		<input name="username" class="easyui-textbox input" data-options="prompt:'用户名'" style="width:100px"> 
		<input name="email" class="easyui-textbox input" data-options="prompt:'邮箱'" style="width:100px"> 
		<select name="sex" class="easyui-combobox input" style="width:100px;" data-options="editable:false">   
		    <option value="">不选择</option>   
		    <option value="1">男</option> 
		    <option value="0">女</option>    
		    <option value="-1">未设置</option>  
		</select>
		<input name="description" class="easyui-textbox input" data-options="prompt:'介绍'" style="width:100px"> 
		<select name="status" class="easyui-combobox input" style="width:100px;" data-options="editable:false">   
		    <option value="">不选择</option>   
		    <option value="1">激活</option> 
		    <option value="0">冻结</option>    
		</select>       
		<a href="javascript:searchUsers();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		<a href="javascript:clearUserSearch();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">清空条件</a>
		|
		<a href="javascript:editUser();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
		|
		<a href="javascript:insertUser();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	</form>
</div>
<!-- 工具栏 end -->
<div id="users_manage_content" class="easyui-panel" data-options="fit:true">

	<table id="users_datagrid" class="easyui-datagrid" data-options="fit:true">  
		 
	</table>

</div>

<!-- 用户属性编辑弹窗 start -->
<%@include file="./users_manage_edit_dialog.jsp" %>
<!-- 用户属性编辑弹窗 end -->


<!-- 新增用户弹窗 start -->
<%@include file="./users_manage_insert_dialog.jsp" %>
<!-- 新增用户弹窗 end -->



<script type="text/javascript" src="${baseUrl}/js/users_manage.js"></script>