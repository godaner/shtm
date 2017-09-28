<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>

<div id="goods_imgs_dialog" style="width:600px;height:400px;">
	<!-- 工具栏 start -->
	<div id="goods_imgs_dg_tb" style="display:none;">
		<a href="javascript:openAddGoodsImgDialog();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加图片</a>
	</div>
	<!-- 工具栏 end -->
	<table id="goods_imgs_datagrid">  
		 
	</table>
</div>