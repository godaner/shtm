<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 新建用户弹窗 start -->
<div id="insertAdminDialog" style="display: none;width:450px;">
	<form id="insertAdminForm" enctype="multipart/form-data" method="post">
		<table id="insertAdminTable">
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
					<input id="username" name="username" class="easyui-textbox input" data-options="prompt:'请输入用户名',required:true" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>邮箱</label> 
				</td>
				<td>
					<input id="email" name="email" class="easyui-textbox input" data-options="prompt:'请输入邮箱',required:true,validType:'email'" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>密码</label> 
				</td>
				<td>
					<input id="password" name="password" class="easyui-textbox input" data-options="required:true,prompt:'请输入密码'" style="width:100%"> 
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
					<label>主题</label> 
				</td>
				<td>
					
					<select id="theme" name="theme" class="easyui-combobox input" data-options="required:true,editable:false" style="width:100%">   
					    <option value="default">default</option> 
					    <option value="black">black</option> 
					    <option value="bootstrap">bootstrap</option> 
					    <option value="gray">gray</option> 
					    <option value="material">material</option> 
					    <option value="metro">metro</option> 
					</select>  
					
				</td>
			</tr>
			<tr>
				<td>
					<label>状态</label> 
				</td>
				<td>
					<select id="status" name="status" class="easyui-combobox input" data-options="required:true,editable:false" style="width:100%">   
					    <option value="1">激活</option>   
					    <option selected="selected" value="0">冻结</option>   
					</select>  
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<!-- 新增按钮 -->
					<a id="submitInsertBtn" style="color:green;" href="javascript:submitNewAdmin();" class="easyui-linkbutton" data-options="">新增</a>
					<!-- 清空按钮 -->
					<a style="color:red;" href="javascript:clearInsertAdminForm();" class="easyui-linkbutton" data-options="">清空</a>
				</td>
			</tr>
		</table> 
	</form> 
</div>
<!-- 新建用户弹窗 end -->
