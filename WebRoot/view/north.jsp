<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/global.css">
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/north.css">


<div id="north_content" class="easyui-layout"
	style="background-color: rgb(66, 166, 206); width: 100%; height: 100%;"
	data-options="">

	<!-- 西部start -->
	<div id="n_w" data-options="region:'west',border:false"
		style=" padding-left:20px;width:calc(20% - 20px);background-color: rgb(66, 166, 206);height:100%;">
		<img style="height:100%;" alt="" src="${baseUrl}/img/manager.jpg?t=123">
		
	</div>
	<!-- 西部 end -->
	
	<!-- 中部start -->
	<div id="n_c" data-options="region:'center',border:false"
		style="color:white;background-color: rgb(66, 166, 206);width: 70%; height:100%;">
		<div style="height:100px;line-height:100px;font-size: 30px;">SHTM二手交易网站后台管理系统</div>
	</div>
	<!-- 中部 end -->
	
	
	<!-- 东部 start -->
	<div data-options="region:'east',border:false"
		style="background-color: rgb(66, 166, 206); width: 10%;">
		<%-- <div style="margin-top: 20px; width: 100%;">
			用户:${onlineUser.username}zhangke <a href="javascript:void(0);">个人信息</a>
			<a href="${baseUrl}/users/logout.action">注销</a>
		</div> --%>
		<div data-options="region:'east',border:false" style="width: 200px;">

			<div style="margin: 10px 10px;">
				当前管理员：<a href="javascript:void(0);"
					id="adminUsernameDiv">${sessionScope.onlineUser.username}</a>
			</div>
			<!-- 控制面板 -->
			<div>
				<a href="javascript:void(0)" id="admins_index_settingMenuBtn"
					class="easyui-menubutton"
					data-options="menu:'#admins_index_settingMenu',iconCls:'icon-help'">控制面板</a>


				<!-- 时间，设置 -->
				<div id="admins_index_settingMenu" style="width: 120px;">

					<div data-options="iconCls:'icon-remove'"
						id="admins_index_logoffBtn">
						<a id="logoutBtn" href="javascript:void(0);" style="text-decoration: none;">注销</a>
					</div>
					<div data-options="iconCls:'icon-ok'" id="">
						<span>其他</span>
						<div>
							<!-- <div data-options="iconCls:'icon-add'"
							onClick="showAdminInfo();">个人信息</div> -->

							<div data-options="iconCls:'icon-undo'" id="">
								<span>背景</span>

								<div>
									<div onClick="updateThemes('bootstrap');">bootstrap</div>
									<div onClick="updateThemes('default');">default</div>
									<div onClick="updateThemes('black');">black</div>
									<div onClick="updateThemes('gray');">gray</div>
									<div onClick="updateThemes('metro');">metro</div>

								</div>

							</div>
							<div data-options="iconCls:'icon-remove'"
								onClick="submitFrozen();">申请冻结</div>

						</div>

					</div>
				</div>

			</div>


		</div>
	</div>
	<!-- 东部 start -->
</div>



<script type="text/javascript" src="${baseUrl}/js/north.js"></script>



