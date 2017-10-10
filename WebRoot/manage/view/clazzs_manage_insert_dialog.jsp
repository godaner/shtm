<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 新建用户弹窗 start -->
<div id="insertClazzDialog" class="easyui-dialog" data-options="closed:true" style="display: none;width:450px;">
	<form id="insertClazzForm" enctype="multipart/form-data" method="post">
		<table id="insertClazzTable">
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
					<!-- 新增按钮 -->
					<a id="submitInsertBtn" style="color:green;" href="javascript:submitNewClazz();" class="easyui-linkbutton" data-options="">新增</a>
					<!-- 清空按钮 -->
					<a style="color:red;" href="javascript:clearInsertClazzForm();" class="easyui-linkbutton" data-options="">清空</a>
				</td>
			</tr>
		</table> 
	</form> 
</div>
<!-- 新建用户弹窗 end -->
