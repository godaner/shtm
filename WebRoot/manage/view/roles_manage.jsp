<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${manageStaticSrcUrl}/css/roles_manage.css">
<!-- 工具栏 start -->
<div id="roles_dg_tb" style="display: none;">
	<form id="rolesSearchForm">
		<input id="id" name="id" class="easyui-textbox input" data-options="prompt:'id'" style="width:100px">
		<input name="name" class="easyui-textbox input" data-options="prompt:'名称'" style="width:100px"> 
		<select name="status" class="easyui-combobox input" style="width:100px;" data-options="editable:false">   
		    <option value="">不选择</option>   
		    <option value="1">激活</option> 
		    <option value="-1">冻结</option>    
		</select>       
		<a href="javascript:searchRoles();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		<a href="javascript:clearRoleSearch();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">清空条件</a>
		|
		<a href="javascript:editRole();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑基本信息</a>
		|
		<a href="javascript:editRolePermission();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑权限</a>
		|
		<a href="javascript:insertRole();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	</form>
</div>
<!-- 工具栏 end -->
<div id="roles_manage_content" class="easyui-panel" data-options="fit:true">

	<table id="roles_datagrid" class="easyui-datagrid" data-options="fit:true">  
		 
	</table>

</div>

<!-- 管理员属性编辑弹窗 start -->
<%@include file="./roles_manage_edit_dialog.jsp" %>
<!-- 管理员属性编辑弹窗 end -->

<!-- 管理员角色属性编辑弹窗 start -->
<%@include file="./roles_manage_edit_permissions_dialog.jsp" %>
<!-- 管理员角色属性编辑弹窗 end -->


<!-- 新增管理员弹窗 start -->
<%@include file="./roles_manage_insert_dialog.jsp" %>
<!-- 新增管理员弹窗 end -->


<script type="text/javascript" src="${manageStaticSrcUrl}/js/roles_manage.js"></script>