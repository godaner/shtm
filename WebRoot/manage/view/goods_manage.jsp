<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${manageStaticSrcUrl}/css/goods_manage.css">
<!-- 工具栏 start -->
<div id="goods_dg_tb" style="display: none;">
	<form id="goodsSearchForm">
		<input id="id" name="id" class="easyui-textbox input" data-options="prompt:'id'" style="width:100px">
		<input id="buyer" name="buyer" class="easyui-textbox input" data-options="prompt:'购买者id'" style="width:100px">
		<input id="owner" name="owner" class="easyui-textbox input" data-options="prompt:'销售者id'" style="width:100px">
		<input name="title" class="easyui-textbox input" data-options="prompt:'标题'" style="width:100px"> 
		<input name="description" class="easyui-textbox input" data-options="prompt:'描述'" style="width:100px"> 
		<select name="condition" class="easyui-combobox input" style="width:100px;" data-options="editable:false">   
		    <option value="">新旧度</option>   
		    <option value="1">1</option> 
		    <option value="2">2</option> 
		    <option value="3">3</option> 
		    <option value="4">4</option> 
		    <option value="5">5</option> 
		    <option value="6">6</option> 
		    <option value="7">7</option> 
		    <option value="8">8</option> 
		    <option value="9">9</option> 
		</select>
		<select name="status" class="easyui-combobox input" style="width:100px;" data-options="editable:false">
			<!-- 状态:,
			-6:待审核状态,(不可以被显示,不可以购买)
			0:审核通过,(可以被显示,可以购买)
			1:购买了且待发货,
			2:已发货,
			-1:买家收货后交易正常结束,
			-5:管理员删除本商品 -->

			<option value="">状态</option>    
		    <option value="-6">待审核</option>  
		    <option value="0">审核通过</option>   
		    <option value="1">待发货</option>    
		    <option value="2">已发货</option>    
		    <option value="-1">交易结束</option>    
		    <!-- <option value="-5">管理员删除本商品</option>  -->    
		</select>       
		<a href="javascript:searchGoods();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		<a href="javascript:clearGoodSearch();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">清空条件</a>
		|
		<a href="javascript:editGood();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑基本信息</a>
		<!-- |
		<a href="javascript:insertGood();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a> -->
	</form>
</div>
<!-- 工具栏 end -->
<div id="goods_manage_content" class="easyui-panel" data-options="fit:true">

	<table id="goods_datagrid" class="easyui-datagrid" data-options="fit:true">  
		 
	</table>

</div>

<!-- goods属性编辑弹窗 start -->
<%@include file="./goods_manage_edit_dialog.jsp" %>
<!-- goods属性编辑弹窗 end -->

<!-- goods圖片管理弹窗 start -->
<%@include file="./goods_manage_imgs_dialog.jsp" %>
<!-- goods属性编辑弹窗 end -->

<!-- goods圖片管理弹窗 start -->
<%@include file="./goods_manage_upload_imgs_dialog.jsp" %>
<!-- goods属性编辑弹窗 end -->

<!-- goods退款管理弹窗 start -->
<%@include file="./check_buyer_apply_dialog.jsp" %>
<!-- goods退款管理弹窗 end -->

<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/goods_manage.js"></script>
