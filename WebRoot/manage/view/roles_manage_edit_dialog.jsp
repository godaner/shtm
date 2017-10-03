<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 用户属性编辑弹窗 start -->
<div id="editRoleDialog" style="display: none;width:450px;">
	<form id="editRoleForm" method="post">
		<table id="editRoleTable">
			<tr>
				<td>
					<label>主键</label> 
				</td>
				<td>
					<input id="id" name="id" class="easyui-textbox input" data-options="editable:false,required:true" style="width:100%"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>名称</label> 
				</td>
				<td>
					<input id="name" name="name" class="easyui-textbox input" data-options="prompt:'请输入名称',required:true" style="width:100%"> 
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
					<!-- 編輯按钮 -->
					<a id="submitEditBtn" style="color:green;" href="javascript:submitRoleEdit();" class="easyui-linkbutton" data-options="">同步</a>
					<!-- 删除按钮 -->
					<a id="deleteBtn" style="color:red;" href="javascript:deleteRole();" class="easyui-linkbutton" data-options="">删除</a>
					<!-- 重置按钮 -->
					<a style="color:red;" href="javascript:resetRole();" class="easyui-linkbutton" data-options="">重置</a>
				</td>
			</tr>
		</table> 
	</form> 
</div>
<!-- 用户属性编辑弹窗 end -->
