<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




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




<script type="text/javascript" src="${baseUrl}/plugin/jquery-3.2.1.min.js"></script>
<%-- <script type="text/javascript"
	src="${baseUrl}/plugin/jquery-easyui-1.5.3/jquery.min.js"></script> --%>
<script type="text/javascript" src="${baseUrl}/plugin/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${baseUrl}/js/util.js"></script>






