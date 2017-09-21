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


<!-- 导入css start -->
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${baseUrl}/plugin/jquery-easyui-1.5.3/themes/${defaultTheme}/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${baseUrl}/plugin/jquery-easyui-1.5.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/global.css">
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/index.css">
<!-- 导入css end -->



<!-- 导入插件 start -->
<script type="text/javascript" charset="utf-8" src="${baseUrl}/plugin/jquery-3.2.1.min.js"></script>
<%-- <script type="text/javascript"
	src="${baseUrl}/plugin/jquery-easyui-1.5.3/jquery.min.js"></script> --%>
<script type="text/javascript" charset="utf-8" src="${baseUrl}/plugin/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${baseUrl}/plugin/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="${baseUrl}/plugin/cropper/js/imagecropper.js"></script>
<script type="text/javascript" charset="utf-8" src="${baseUrl}/js/util.js"></script>
<!-- 导入插件 end -->



</head>
<body>

	<div class="easyui-layout" style="width: 100%; height: 100%;">

		<!-- 北部 start -->
		<div data-options="region:'north'" style="height: 80px;">
			<div id="north_panel" class="easyui-panel" data-options="fit:true" style="overflow: hidden;">
				<%@include file="./north.jsp"%>
			</div>
		</div>
		<!-- 北部 end -->

		<!-- 南部 start -->
		<!-- <div data-options="region:'south',split:true" style="height: 10%;"></div> -->
		<!-- 南部 end -->

		<!-- 东部 start -->
		<div data-options="region:'east',split:true" title="其他"
			style="width: 250px;">
			<div id="east_panel" class="easyui-panel" data-options="fit:true">
				<%@include file="./east.jsp"%>
			</div>	
		</div>
		<!-- 东部 start -->


		<!-- 西部start -->
		<div data-options="region:'west',split:true" title="菜单"
			style="width: 250px;">
			<div id="west_panel" class="easyui-panel" data-options="fit:true">
				<%@include file="./west.jsp"%>
			</div>
		</div>
		<!-- 西部 end -->

		<!-- 中部start -->
		<div
			data-options="region:'center'"
			style="height: 100%;">
			<div id="center_panel" class="easyui-panel" data-options="fit:true">
				<%@include file="./center.jsp"%>
			</div>	
		</div>
		<!-- 中部end -->
	</div>
	<!-- 登录弹框 start -->
	<%@include file="./login.jsp"%>
	<!-- 登录弹框 end -->
	
	
	
</body>








<!-- 设置js使用的变量 start -->
<script type="text/javascript" >
	/*在线用户的usernmae*/
	var onlineUsername = '${sessionScope.onlineUser.username}';
	/*在线用户的theme*/
	var onlineUserTheme = '${sessionScope.onlineUser.theme}';
	/*默认theme*/
	var defaultTheme = '${defaultTheme}';
</script>
<!-- 设置js使用的变量 end -->

<!-- 导入js开始 start -->
<script type="text/javascript" charset="utf-8" src="${baseUrl}/js/center.js"></script>
<script type="text/javascript" charset="utf-8" src="${baseUrl}/js/center_index.js"></script>
<script type="text/javascript" charset="utf-8" src="${baseUrl}/js/east.js"></script>
<script type="text/javascript" charset="utf-8" src="${baseUrl}/js/login.js"></script>
<script type="text/javascript" charset="utf-8" src="${baseUrl}/js/north.js"></script>
<script type="text/javascript" charset="utf-8" src="${baseUrl}/js/west.js"></script>
<script type="text/javascript" charset="utf-8" src="${baseUrl}/js/index.js"></script>
<!-- 
	获取地理位置 
	var remote_ip_info = {"ret":1,"start":-1,"end":-1,"country":"\u4e2d\u56fd","province":"\u6c5f\u82cf","city":"\u5357\u901a","district":"","isp":"","type":"","desc":""};
-->
<script type="text/javascript" charset="utf-8" src='http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js'></script>
<!-- 
	获取ip 
	var returnCitySN = {"cip": "180.120.216.21", "cid": "320600", "cname": "江苏省南通市"};
-->
<script type="text/javascript" charset="utf-8" src="http://pv.sohu.com/cityjson?ie=utf-8"></script> 
<script type="text/javascript" charset="utf-8" src="${baseUrl}/js/visit.js"></script>
<!-- 导入js开始 end -->

</html>