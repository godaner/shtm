<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/goods_manage.css">
<!-- 工具栏 start -->
<div id="goods_dg_tb" style="display: none;">
	<form id="goodsSearchForm">
		<input name="id" class="easyui-textbox input" data-options="prompt:'id'" style="width:100px">
		<input name="username" class="easyui-textbox input" data-options="prompt:'管理员名'" style="width:100px"> 
		<input name="email" class="easyui-textbox input" data-options="prompt:'邮箱'" style="width:100px"> 
		<select name="theme" class="easyui-combobox input" style="width:100px;" data-options="editable:false">   
		    <option value="">不选择</option>   
		    <option value="black">black</option> 
		    <option value="bootstrap">bootstrap</option> 
		    <option value="default">default</option> 
		    <option value="gray">gray</option> 
		    <option value="material">material</option> 
		    <option value="metro">metro</option> 
		</select>
		<select name="status" class="easyui-combobox input" style="width:100px;" data-options="editable:false">   
		    <option value="">不选择</option>   
		    <option value="1">激活</option> 
		    <option value="0">冻结</option>    
		</select>       
		<a href="javascript:searchGoods();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		<a href="javascript:clearGoodSearch();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">清空条件</a>
		|
		<a href="javascript:editGood();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
		|
		<a href="javascript:insertGood();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	</form>
</div>
<!-- 工具栏 end -->
<div id="goods_manage_content" class="easyui-panel" data-options="fit:true">

	<table id="goods_datagrid" class="easyui-datagrid" data-options="fit:true">  
		 
	</table>

</div>

<!-- 管理员属性编辑弹窗 start -->
<%-- <%@include file="./goods_manage_edit_dialog.jsp" %> --%>
<!-- 管理员属性编辑弹窗 end -->


<!-- 新增管理员弹窗 start -->
<%-- <%@include file="./goods_manage_insert_dialog.jsp" %> --%>
<!-- 新增管理员弹窗 end -->


<script type="text/javascript" src="${baseUrl}/js/goods_manage.js"></script>