<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/login.css">
<!-- 这里不要指定其为 class="easyui-dialog",不然会有闪屏现象 -->
<div id="login_dialog">

	<form id="loginForm" method="post">
		<table id="logintable">
			<tr>
				<td>用户名:
				
				</td>
				<!-- required:true, 临时去除,便于测试 -->
				<td><input name="username" 
					autocomplete="off" disableautocomplete="true"
					class="easyui-textbox" 
					data-options="iconCls:'',
					prompt:'账户',
					
					missingMessage:'请输入账户'" /></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input name="password"
					autocomplete="off" disableautocomplete="true"
					class="easyui-passwordbox" 
					data-options="iconCls:'',
					prompt:'密码',
					
					missingMessage:'请输入密码'" /></td>
			</tr>
			<tr>
				<td>验证码:</td>
				<td><img style="cursor: pointer;" alt="点击获取验证码"
					id="verifyCodeImg" src="${baseUrl}/admins/verifyCode.action">
	
				</td>
			</tr>
			<tr>
				<td>输入验证码:</td>
				<td><input name="verifyCode"
					autocomplete="off" disableautocomplete="true"
					class="easyui-textbox" 
					data-options="iconCls:'',
					prompt:'验证码',
					
					missingMessage:'请输入验证码'" /></td>
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

