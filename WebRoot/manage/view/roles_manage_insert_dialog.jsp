<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 新建用户弹窗 start -->
<div id="insertRoleDialog" class="easyui-dialog"  data-options="closed:true"  style="display: none;width:450px;">
	<form id="insertRoleForm" method="post">
		<table id="insertRoleTable">
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
					<!-- 新增按钮 -->
					<a id="submitInsertBtn" style="color:green;" href="javascript:submitNewRole();" class="easyui-linkbutton" data-options="">新增</a>
					<!-- 清空按钮 -->
					<a style="color:red;" href="javascript:clearInsertRoleForm();" class="easyui-linkbutton" data-options="">清空</a>
				</td>
			</tr>
		</table> 
	</form> 
</div>
<!-- 新建用户弹窗 end -->
