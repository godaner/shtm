<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="./base.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SHTM</title>




<!-- 导入css start -->
<%@include file="./css.jsp" %>
<!-- 导入css end -->


</head>
<body>

	<div class="easyui-layout" style="width: 100%; height: 100%;">

		<!-- 北部 start -->
		<div data-options="region:'north'" style="height: 80px;">
			<div id="north_panel" style="overflow: hidden;width: 100%; height: 100%;"></div>
		</div>
		<!-- 北部 end -->

		<!-- 南部 start -->
		<!-- <div data-options="region:'south',split:true" style="height: 10%;"></div> -->
		<!-- 南部 end -->

		<!-- 东部 start -->
		<div data-options="region:'east',split:true" title="East"
			style="width: 250px;">
			<div id="east_panel" style="width: 100%; height: 100%;"></div>	
		</div>
		<!-- 东部 start -->


		<!-- 西部start -->
		<div data-options="region:'west',split:true" title="菜单"
			style="width: 250px;">
			<div id="west_panel" style="width: 100%; height: 100%;"></div>
		</div>
		<!-- 西部 end -->

		<!-- 中部start -->
		<div
			data-options="region:'center',title:'Main Title',iconCls:'icon-ok'"
			style="height: 100%;">
			<div id="center_panel" style="width: 100%; height: 100%;"></div>	
		</div>
		<!-- 中部end -->
	</div>
	<!-- 登录弹框 start -->
	<div id="login_dialog" style="overflow: hidden;">
		
	</div>
	<!-- 登录弹框 end -->
	
	
	
</body>




<!-- 导入js start -->
<%@include file="./js.jsp" %>
<!-- 导入js start -->

<script type="text/javascript" src="${baseUrl}/js/index.js"></script>



</html>