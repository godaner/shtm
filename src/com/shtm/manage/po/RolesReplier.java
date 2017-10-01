package com.shtm.manage.po;

import java.sql.Timestamp;

import com.shtm.po.Roles;

/**
 * Title:RolesReplier
 * <p>
 * Description:角色信息返回者;
 * <p>
 * @author Kor_Zhang
 * @date 2017年10月1日 下午2:07:30
 * @version 1.0
 */
public class RolesReplier extends Roles {

	private Timestamp grantTime;
	
	public Timestamp getGrantTime() {
		return grantTime;
	}

	public void setGrantTime(Timestamp grantTime) {
		this.grantTime = grantTime;
	}

	
}
