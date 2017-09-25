<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/goods_manage.css">
<!-- 工具栏 start -->
<div id="goods_dg_tb" style="display: none;">
	<form id="goodsSearchForm">
		<input name="id" class="easyui-textbox input" data-options="prompt:'id'" style="width:100px">
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
		<select name="status" class="easyui-combobox input" style="width:200px;" data-options="editable:false">
			<!-- 状态:
					 * 2为已发货，
					 * 1为购买了且待发货，,
					 * 0为创建且待购买,
					 * -1为买家收货后交易正常结束，
					 * -2为卖家取消了出售本商品，
					 * -3是用户取消购买本商品，
					 * -4管理员取消发布的商品,
					 * -5为管理员删除了商品 -->

			<option value="">状态</option>    
		    <option value="2">已发货</option>  
		    <option value="1">购买了且待发货</option>   
		    <option value="0">创建且待购买</option>   
		    <option value="-1">买家收货后交易正常结束</option>    
		    <option value="-2">卖家取消了出售本商品</option>    
		    <option value="-3">用户取消购买本商品</option>    
		    <option value="-4">管理员取消发布的商品</option>    
		    <!-- <option value="-5">管理员删除了商品</option> -->    
		</select>       
		<a href="javascript:searchGoods();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		<a href="javascript:clearGoodSearch();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">清空条件</a>
		|
		<a href="javascript:editGood();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
		<!-- |
		<a href="javascript:insertGood();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a> -->
	</form>
</div>
<!-- 工具栏 end -->
<div id="goods_manage_content" class="easyui-panel" data-options="fit:true">

	<table id="goods_datagrid" class="easyui-datagrid" data-options="fit:true">  
		 
	</table>

</div>

<!-- 管理员属性编辑弹窗 start -->
<%@include file="./goods_manage_edit_dialog.jsp" %>
<!-- 管理员属性编辑弹窗 end -->


<!-- 新增管理员弹窗 start -->
<%-- <%@include file="./goods_manage_insert_dialog.jsp" %> --%>
<!-- 新增管理员弹窗 end -->


<script type="text/javascript" src="${baseUrl}/js/goods_manage.js"></script>