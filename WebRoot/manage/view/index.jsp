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
	href="${manageStaticSrcUrl}/plugin/jquery-easyui-1.5.3/themes/${defaultTheme}/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${manageStaticSrcUrl}/plugin/jquery-easyui-1.5.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${manageStaticSrcUrl}/css/index.css">
<link rel="stylesheet" type="text/css" href="${manageStaticSrcUrl}/css/global.css">
<!-- 导入css end -->



<!-- 导入插件 start -->
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/plugin/jquery-3.2.1.min.js"></script>
<%-- <script type="text/javascript"
	src="${manageStaticSrcUrl}/plugin/jquery-easyui-1.5.3/jquery.min.js"></script> --%>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/plugin/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/plugin/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/plugin/cropper/js/imagecropper.js"></script>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/util.js"></script>
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
		<div id="layout_west" data-options="region:'west',split:true" title="菜单"
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
	
	<!-- 登录弹框 start -->
	<%@include file="./confirm_admin_pw_dialog.jsp"%>
	<!-- 登录弹框 end -->
	
	<!-- 管理員編輯自身信息弹框 start -->
	<%@include file="./admins_edit_self_dialog.jsp"%>
	<!-- 管理員編輯自身信息弹框 end -->
	
</body>








<!-- 设置js使用的变量 start -->
<script type="text/javascript" charset="utf-8">
	/*在线admin的usernmae*/
	//var onlineUsername = '${sessionScope.onlineUser.username}';
	/*在线admin的id*/
	//var onlineAdminId = '${sessionScope.onlineUser.id}';
	/*在线用户的theme*/
	//var onlineUserTheme = '${sessionScope.onlineUser.theme}';
	/*默认theme*/
	var defaultTheme = '${defaultTheme}';

	
	
	/*统一管理tab跳转参数*/
	
	//商品的tab的title,url
	var goods_manage_tab_title = "商品管理";
	var goods_manage_tab_url = manageStaticSrcUrl+'/view/goods_manage.jsp';
	//用户的tab的title,url
	var users_manage_tab_title = "用户管理";
	var users_manage_tab_url = manageStaticSrcUrl+'/view/users_manage.jsp';
	//管理员的tab的title,url
	//var admins_manage_tab_title = "管理员管理";
	//var admins_manage_tab_url = manageStaticSrcUrl+'/view/admins_manage.jsp';

	
	//users跳转到goods的tab的上下文环境
	var users_to_goods_tab_context = new Context();
	//goods跳转到users的tab的上下文环境
	var goods_to_users_tab_context = new Context();
	//admins跳转到admins的tab的上下文环境
	//var admins_to_admins_tab_context = new Context();
	
	
</script>
<!-- 设置js使用的变量 end -->

<!-- 导入js开始 start -->
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/login.js"></script>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/center.js"></script>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/center_index.js"></script>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/east.js"></script>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/north.js"></script>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/west.js"></script>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/index.js"></script>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/admins_edit_self_dialog.js"></script>
<script type="text/javascript" charset="utf-8" src="${manageStaticSrcUrl}/js/online_admin_ws.js"></script>
<!-- 获取登录信息的js start -->
<%@include file="./login_recored.jsp" %>
<!-- 获取登录信息的js end -->
</html>