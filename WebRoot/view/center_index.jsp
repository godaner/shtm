<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${baseUrl}/css/center_index.css">




<div fit="true" class="easyui-layout" width="100%;" height="100%;">

	<div region="west" width="50%" height="" split="true" border="false">
		<div fit="true" class="easyui-layout" width="100%;" height="100%;">
			<!-- 关于 -->
			<div region="north" width="100%" height="50%" split="true"
				border="false">
				<div id="about" class="easyui-panel" title="关于" fit="true"
					style="height: 100%; width: 100%" data-options="collapsible:true">
					<p>SHTM实现了对SHT为的同级项目,实现了对SHT的后端管理。</p>
					<p>SHTM实现了对用户,商品,管理员等信息的管理。</p>
					<p>"xiyanghong"作品。</p>
				</div>

			</div>
			<!-- 链接 -->
			<div region="south" width="100%" height="50%" split="true"
				border="false">
				<div id="link" class="easyui-panel" title="链接" fit="true"
					style="height: 100%; width: 100%" data-options="collapsible:true">
					<p><a style="color:red;" href="#">SHT前台链接</a></p>
				</div>
			</div>
		</div>

	</div>
	<div region="east" width="50%" height="" split="true" border="false">
		<div fit="true" class="easyui-layout" width="100%;" height="100%;">
			<!-- 说明 -->
			<div region="north" width="100%" height="50%" split="true"
				border="false">
				<div id="explain" class="easyui-panel" title="说明" fit="true"
					style="height: 100%; width: 100%" data-options="collapsible:true">
						<p>该系统拥有一个超级管理和不限量的普通管理员。</p>
						<p>超级管理对管理食物拥有所有权限，普通管理员可以被分配权限。</p>
						<p>请所有管理员遵守<a style="color:red;" href="javascript:void(0);">幸福假日管理准则</a>。</p>
				</div>
			</div>
			<!-- 公告 -->
			<div region="south" width="100%" height="50%" split="true"
				border="false">
				<div id="post" class="easyui-panel" title="公告" fit="true"
					style="height: 100%; width: 100%" data-options="collapsible:true">
					<table id="admins_index_center_noticeTable">


						<!-- <tr>
							<td>
								<p>幸福假日是一个提供综合旅游网站的网站。</p>
							</td>
						</tr>
						<tr>
							<td>
								<p>该后台为管理人员提供编辑管理网站的途径。</p>
							</td>
						</tr>
						<tr>
							<td>
								<p>。。。。。。</p>
							</td>
						</tr> -->
					</table>
				</div>
			</div>
		</div>


	</div>
</div>








