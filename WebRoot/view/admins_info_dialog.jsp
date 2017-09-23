<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/admins_info_dialog.css">

<!-- 这里不要指定其为 class="easyui-dialog",不然会有闪屏现象 -->
<div id="admins_info_dialog">

	<form id="admins_info_form" method="post">
		<table id="admins_info_table">
			
			<!-- <tr>
				<td>
					选择头像
				</td>
				<td>
					<input type="file" id="file" name="file" style="width:100%">
					<input id="file" name="file" class="easyui-filebox input" data-options="accept:'image/jpeg,image/gif,image/jpg',prompt:'请选择图片',buttonText: '选择图片', buttonAlign: 'right' "  style="width:100%"> 
				</td>
			</tr> -->
		
			<!-- <tr>
				<td>
					<label>主键</label> 
				</td>
				<td>
					<input id="id" name="id" class="easyui-textbox input" data-options="prompt:'请选择图片',editable:false" style="width:100%"> 
				</td>
			</tr> -->
			<tr>
				<td>
					<label>名称</label> 
				</td>
				<td>
					<input id="username" name="username" class="easyui-textbox input" data-options="editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>邮箱</label> 
				</td>
				<td>
					<input id="email" name="email" class="easyui-textbox input" data-options="editable:false" style="width:100%"> 
				</td>
			</tr>
			<!-- <tr>
				<td>
					<label>密码</label> 
				</td>
				<td>
					<input id="password" name="password" class="easyui-textbox input" data-options="prompt:'默认为原密码'" style="width:100%"> 
				</td>
			</tr> -->
			<!-- <tr>
				<td>
					<label>盐</label> 
				</td>
				<td>
					<input id="salt" name="salt" class="easyui-textbox" data-options="" style="width:100%"> 
				</td>
			</tr> -->
			<tr>
				<td>
					<label>主题</label> 
				</td>
				<td>
					
					<input id="theme" name="theme" class="easyui-textbox input" data-options="editable:false" style="width:100%">
					
				</td>
			</tr>
			<tr>
			
				<td>
					<label>状态</label> 
				</td>
				<td>
					<input id="status" name="status" class="easyui-textbox input" data-options="editable:false" style="width:100%">
				</td>
			</tr>
			<tr>
				<td>
					<label>创建者</label> 
				</td>
				<td>
					<input id="creatorName" name="creatorName" class="easyui-textbox input" data-options="editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>創建时间</label> 
				</td>
				<td>
					<input id="createtime" name="createtime" class="easyui-textbox input" data-options="editable:false" style="width:100%"> 
				</td>
			</tr>
			
		</table>
	</form>
</div>

