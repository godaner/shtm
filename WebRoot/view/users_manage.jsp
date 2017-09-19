<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./base.jsp"%>
<link rel="stylesheet" type="text/css" href="${baseUrl}/css/users_manage.css">
<!-- 工具栏 start -->
<div id="c_i_tb" style="display: none;">
	<form id="usersSearchForm">
		<input name="id" class="easyui-textbox" data-options="prompt:'id'" style="width:100px">
		<input name="username" class="easyui-textbox" data-options="prompt:'用户名'" style="width:100px"> 
		<input name="email" class="easyui-textbox" data-options="prompt:'邮箱'" style="width:100px"> 
		<select name="sex" class="easyui-combobox" style="width:100px;" data-options="editable:false">   
		    <option value="">不选择</option>   
		    <option value="1">男</option> 
		    <option value="0">女</option>    
		    <option value="-1">未设置</option>  
		</select>
		<input name="description" class="easyui-textbox" data-options="prompt:'介绍'" style="width:100px"> 
		<select name="status" class="easyui-combobox" style="width:100px;" data-options="editable:false">   
		    <option value="">不选择</option>   
		    <option value="1">激活</option> 
		    <option value="0">冻结</option>    
		</select>       
		<a href="javascript:searchUsers();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		|
		<a href="javascript:editUser();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	</form>
</div>
<!-- 工具栏 end -->
<div id="users_manage_content" class="easyui-panel" data-options="fit:true">

	<table id="users_datagrid" class="easyui-datagrid" data-options="fit:true">  
		 
	</table>

</div>

<!-- 属性表格 start -->
<div id="editUserDialog" style="display: none;width:450px;">
	<form id="editUserForm">
		<table id="editUserTable">
			<tr>
				<td>
					<label>主键</label> 
				</td>
				<td>
					<input id="id" name="id" class="easyui-textbox" data-options="editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>名称</label> 
				</td>
				<td>
					<input id="username" name="username" class="easyui-textbox" data-options="required:true" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>邮箱</label> 
				</td>
				<td>
					<input id="email" name="email" class="easyui-textbox" data-options="required:true,validType:'email'" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>密码</label> 
				</td>
				<td>
					<input id="password" name="password" class="easyui-textbox" data-options="prompt:'默认为原密码'" style="width:100%"> 
				</td>
			</tr>
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
					<label>性别</label> 
				</td>
				<td>
					<!-- <input id="sex" name="sex" class="easyui-combobox" style="width:100%"
    				data-options="valueField:'id',textField:'text',data:[
    					{    
						    'id':1,    
						    'text':'男'   
						},{    
						    'id':0,    
						    'text':'女'   
						},{    
						    'id':-1,    
						    'text':'未设置',    
						}]  " />   -->
					
					<select id="sex" name="sex" class="easyui-combobox" style="width:100%">   
					    <option value="-1">未设置</option>   
					    <option value="1">男</option>   
					    <option value="0">女</option>   
					</select>  
					
				</td>
			</tr>
			<tr>
				<td>
					<label>生日</label> 
				</td>
				<td>
					<input id="birthday" name="birthday" class="easyui-datebox" data-options="editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>介绍</label> 
				</td>
				<td>
					<input id="description" name="description" class="easyui-textbox" data-options="multiline:true" style="width:100%;height:80px;"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>积分</label> 
				</td>
				<td>
					<input id="score" name="score" class="easyui-numberbox" data-options="required:true,min:0,precision:0" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>注册时间</label> 
				</td>
				<td>
					<input id="registtime" name="registtime" class="easyui-textbox" data-options="required:true,editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>状态</label> 
				</td>
				<td>
					<select id="status" name="status" class="easyui-combobox" style="width:100%">   
					    <option value="1">激活</option>   
					    <option value="0">冻结</option>   
					</select>  
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<!-- 修改按钮 -->
					<a id="submitEditBtn" href="javascript:submitUserEdit();" class="easyui-linkbutton" data-options="">修改</a>
					<!-- 删除按钮 -->
					<a id="deleteBtn" style="color:red;" href="javascript:deleteUser();" class="easyui-linkbutton" data-options="">删除</a>
				</td>
			</tr>
		</table> 
	</form> 
</div>
<!-- 属性表格 end -->

<script type="text/javascript" src="${baseUrl}/js/users_manage.js"></script>