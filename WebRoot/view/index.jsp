<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SHTM</title>
<!-- 默认的主题 -->
<c:set value="bootstrap" var="defaultTheme"></c:set>

<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${baseUrl}/plugin/jquery-easyui-1.5.3/themes/${defaultTheme}/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${baseUrl}/plugin/jquery-easyui-1.5.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/global.css">
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/index.css">




<script type="text/javascript"
	src="${baseUrl}/plugin/jquery-3.2.1.min.js"></script>
<%-- <script type="text/javascript"
	src="${baseUrl}/plugin/jquery-easyui-1.5.3/jquery.min.js"></script> --%>
<script type="text/javascript"
	src="${baseUrl}/plugin/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${baseUrl}/js/util.js"></script>

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
<script type="text/javascript" >
	/*在线用户的usernmae*/
	var onlineUsername = '${sessionScope.onlineUser.username}';
	/*在线用户的theme*/
	var onlineUserTheme = '${sessionScope.onlineUser.theme}';
	/*默认theme*/
	var defaultTheme = '${defaultTheme}';
</script>
<script type="text/javascript"
	src="${baseUrl}/js/index.js"></script>

</html>