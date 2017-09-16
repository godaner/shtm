<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/global.css">
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/north.css">


<div id="north_content" class="easyui-layout"
	style="background-color: rgb(66, 166, 206); width: 100%; height: 80px;"
	data-options="">

	<!-- 西部start -->
	<div id="n_w" data-options="region:'west',border:false"
		style=" padding-left:20px;width:150px;background-color: rgb(66, 166, 206);height:100%;">
		<img style="height:80px;" alt="" src="${baseUrl}/img/manager.jpg?t=123">
		
	</div>
	<!-- 西部 end -->
	
	<!-- 中部start -->
	<div id="n_c" data-options="region:'center',border:false"
		style="color:white; height:80px;background-color: rgb(66, 166, 206);">
		<div style="width:100%;height:80px;line-height:80px;font-size: 24px;text-align: left;">SHTM二手交易网站后台管理系统</div>
	</div>
	<!-- 中部 end -->
	
	
	<!-- 东部 start -->
	<div 
		id="n_e"
		data-options="region:'east',border:false"
		style="line-height:80px;width: 400px;background-color: rgb(66, 166, 206);text-align: left;">
			<!-- 当前管理员 -->
			<a 
			class="easyui-linkbutton"
			data-options=""
			href="javascript:void(0);">
				管理员:
				<label id="usernameDisplayer" >
					${sessionScope.onlineUser.username}
				</label>
			</a>
			

			<!-- 主题 -->
			<a 
			class="easyui-menubutton"
			data-options="menu:'#bgMenu',iconCls:'icon-help'"
			href="javascript:void(0);">
				切换主题
			</a>
			<div data-options="iconCls:'icon-undo'" id="bgMenu">
				<div onClick="updateThemes('bootstrap');">bootstrap</div>
				<div onClick="updateThemes('default');">default</div>
				<div onClick="updateThemes('black');">black</div>
				<div onClick="updateThemes('gray');">gray</div>
				<div onClick="updateThemes('metro');">metro</div>
				<div onClick="updateThemes('material');">material</div>
			</div>
			<!-- 注销 -->
			<a 
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			id="logoutBtn" 
			href="javascript:void(0);">
				注销
			</a>
			<!-- 冻结 -->
			<a 
			class="easyui-linkbutton"
			data-options="iconCls:'icon-no',plain:true"
			id="forezeBtn" 
			href="javascript:void(0);">
				申请冻结
			</a>			

	</div>
	<!-- 东部 end -->

</div>



<script type="text/javascript" src="${baseUrl}/js/north.js"></script>
