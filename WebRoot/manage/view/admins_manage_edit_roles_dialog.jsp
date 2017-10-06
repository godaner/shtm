<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 管理员角色辑弹窗 start -->
<div id="editAdminRoleDialog" class="easyui-dialog" data-options="closed:true" style="display: none;width:510px;">
	<div id="admins_roles_datagrid" style="margin-top:15px;width:100%;">
		
	</div>  
	<div style="text-align: center;margin: 15px 0px;width:100%;">
		<!-- 確認按钮 -->
		<a id="submitAdminRoleEditBtn" style="color:green;" href="javascript:submitAdminRolesEdit();" class="easyui-linkbutton" data-options="">同步</a>
		<!-- 恢复按钮 -->
		<a id="" style="color:green;" href="javascript:editAdminRole();" class="easyui-linkbutton" data-options="">恢复</a>
	</div>
</div>
<!-- 管理员角色编辑弹窗 end -->
