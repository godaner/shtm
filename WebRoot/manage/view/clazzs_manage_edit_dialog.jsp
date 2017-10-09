<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 类型属性编辑弹窗 start -->
<div id="editClazzDialog" class="easyui-dialog" data-options="closed:true" style="display: none;width:450px;">
	<form id="editClazzForm" method="post">
		<table id="editClazzTable">
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
					<input id="text" name="text" class="easyui-textbox input" data-options="prompt:'请输入名称',required:true" style="width:100%"> 
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<!-- 編輯按钮 -->
					<a id="submitEditBtn" style="color:green;" href="javascript:submitClazzEdit();" class="easyui-linkbutton" data-options="">同步</a>
					<!-- 删除按钮 -->
					<a id="deleteBtn" style="color:red;" href="javascript:deleteClazz();" class="easyui-linkbutton" data-options="">删除</a>
					<!-- 重置按钮 -->
					<a style="color:red;" href="javascript:resetClazz();" class="easyui-linkbutton" data-options="">重置</a>
				</td>
			</tr>
		</table> 
	</form> 
</div>
<!-- 类型属性编辑弹窗 end -->
