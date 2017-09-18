<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/users_manage.css">
<!-- 工具栏 start -->
<div id="c_i_tb">
	<form id="usersSearchForm">
		<input name="id" class="easyui-textbox" data-options="prompt:'id'" style="width:100px">
		<input name="username" class="easyui-textbox" data-options="prompt:'用户名'" style="width:100px"> 
		<input name="email" class="easyui-textbox" data-options="prompt:'邮箱'" style="width:100px"> 
		<select name="sex" class="easyui-combobox" style="width:100px;" data-options="editable:false">   
		    <option value="">不选择</option>   
		    <option value="1">男</option> 
		    <option value="0">女</option>    
		    <option value="-1">未设置</option>  
		</select>
		<input name="description" class="easyui-textbox" data-options="prompt:'介绍'" style="width:100px"> 
		<select name="status" class="easyui-combobox" style="width:100px;" data-options="editable:false">   
		    <option value="">不选择</option>   
		    <option value="1">激活</option> 
		    <option value="0">冻结</option>    
		</select>       
		<a href="javascript:searchUsers();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		|
		<a href="javascript:editUser();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	</form>
</div>
<!-- 工具栏 end -->
<div id="users_manage_content" class="easyui-panel" data-options="fit:true">

	<table id="users_datagrid" class="easyui-datagrid" data-options="fit:true">  
		 
	</table>

</div>

<!-- 属性表格 start -->
<table id="editUserWindow" style="width:300px"></table>  
<!-- 属性表格 end -->

<script type="text/javascript" src="${baseUrl}/js/users_manage.js"></script>