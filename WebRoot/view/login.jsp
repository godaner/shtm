<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/global.css">
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/login.css">
<div id="login_content">

	<form id="loginForm" method="post">
		<table>
			<tr>
				<td>用户名:</td>
				<td><input name="username" invalidMessage="请输入账户"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input name="password" invalidMessage="请输入密码"
					type="password" class="easyui-validatebox"
					data-options="required:true" /></td>
			</tr>
			<tr>
				<td>验证码:</td>
				<td><img style="cursor: pointer;" alt="点击获取验证码"
					id="verifyCodeImg" src="#">
	
				</td>
			</tr>
			<tr>
				<td>输入验证码:</td>
				<td><input name="verifyCode" 
					class="easyui-validatebox" data-options="required:true,invalidMessage:'请输入验证码'" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="loginBtn"
					class="easyui-linkbutton" value="登录"
					data-options="width:60,height:35" /></td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript" src="${baseUrl}/js/login.js"></script>
