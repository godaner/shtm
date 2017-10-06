<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 管理员角色辑弹窗 start -->
<div id="editRolePermissionDialog" class="easyui-dialog"  data-options="closed:true"  style="display: none;width:600px;">
	<div id="roles_permissions_datagrid" style="margin-top:15px;width:100%;">
		
	</div>  
	<div style="text-align: center;margin: 15px 0px;width:100%;">
		<!-- 確認按钮 -->
		<a id="submitRolePermissionEditBtn" style="color:green;" href="javascript:submitRolePermissionsEdit();" class="easyui-linkbutton" data-options="">同步</a>
		<!-- 恢复按钮 -->
		<a id="" style="color:green;" href="javascript:editRolePermission();" class="easyui-linkbutton" data-options="">恢复</a>
	</div>
</div>
<!-- 管理员角色编辑弹窗 end -->
