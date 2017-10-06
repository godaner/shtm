<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 用户属性编辑弹窗 start -->
<div id="editAdminDialog" class="easyui-dialog" data-options="closed:true" style="display: none;width:450px;">
	<form id="editAdminForm" enctype="multipart/form-data" method="post">
		<table id="editAdminTable">
			<!-- <tr>
				<td>
					选择头像
				</td>
				<td>
					<input type="file" id="file" name="file" style="width:100%">
					<input id="file" name="file" class="easyui-filebox input" data-options="accept:'image/jpeg,image/gif,image/jpg',prompt:'请选择图片',buttonText: '选择图片', buttonAlign: 'right' "  style="width:100%"> 
				</td>
			</tr> -->
		
			<tr>
				<td>
					<label>主键</label> 
				</td>
				<td>
					<input id="id" name="id" class="easyui-textbox input" data-options="prompt:'请选择图片',editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>名称</label> 
				</td>
				<td>
					<input id="username" name="username" class="easyui-textbox input" data-options="editable:true,prompt:'请输入用户名',required:true" style="width:100%"> 
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
					<input id="password" name="password" class="easyui-textbox input" data-options="prompt:'默认为原密码'" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>简介</label> 
				</td>
				<td>
					<input id="description" name="description" class="easyui-textbox input" data-options="prompt:'请输入简介',required:true" style="width:100%"> 
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
				<td>
					<label>創建时间</label> 
				</td>
				<td>
					<input id="createtime" name="createtime" class="easyui-textbox input" data-options="required:true,editable:false" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<!-- 編輯按钮 -->
					<a id="submitEditBtn" style="color:green;" href="javascript:submitAdminEdit();" class="easyui-linkbutton" data-options="">同步</a>
					<!-- 删除按钮 -->
					<a id="deleteBtn" style="color:red;" href="javascript:deleteAdmin();" class="easyui-linkbutton" data-options="">删除</a>
					<!-- 重置按钮 -->
					<a style="color:red;" href="javascript:resetAdmin();" class="easyui-linkbutton" data-options="">重置</a>
				</td>
			</tr>
		</table> 
	</form> 
</div>
<!-- 用户属性编辑弹窗 end -->
