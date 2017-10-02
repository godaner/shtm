package com.shtm.manage.po;

import javax.validation.constraints.Size;

import com.shtm.manage.groups.RolesGroups.UpdateRolePermission;
import com.shtm.po.Roles;

/**
 * Title:RolesReceiver
 * <p>
 * Description:角色信息接受者;
 * <p>
 * @author Kor_Zhang
 * @date 2017年10月1日 下午2:08:29
 * @version 1.0
 */
public class RolesReceiver extends Roles {
	//權限的ids
	@Size(min = 0,message="{roles.permissionsIds.size.error}",groups={UpdateRolePermission.class})
	private String[] permissionsIds;

	public String[] getPermissionsIds() {
		return permissionsIds;
	}

	public void setPermissionsIds(String[] permissionsIds) {
		this.permissionsIds = permissionsIds;
	}
	
}
