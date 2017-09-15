<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/global.css">
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/north.css">
<div id="north_content" class="easyui-layout"
	style="background-color: rgb(66, 166, 206); width: 100%; height: 100%;"
	data-options="">

	<!-- 西部start -->
	<div data-options="region:'west',border:false"
		style="background-color: rgb(66, 166, 206); width: 70%;">
		<img style="height:80%;" alt=""
			src="${baseUrl}/img/manager.jpg">
		<label style="height:100%;line-height:100%;font-size: 28px;">SHTM二手交易网站后台管理系统</label>>
	</div>
	<!-- 西部 end -->
	<!-- 东部 start -->
	<div data-options="region:'east',border:false"
		style="background-color: rgb(66, 166, 206); width: 20%;">
			<div style="margin-top:20px;width:100%;">
				用户:${onlineUser.username}zhangke
				<a href="javascript:void(0);">个人信息</a>
				<a href="${baseUrl}/users/logout.action">注销</a>
			</div>
		</div>
	<!-- 东部 start -->
</div>



<script type="text/javascript" src="${baseUrl}/js/north.js"></script>



