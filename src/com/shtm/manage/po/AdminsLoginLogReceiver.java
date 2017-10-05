package com.shtm.manage.po;

import com.shtm.po.AdminsLoginLog;

/**
 * Title:AdminsLoginLogReceiver
 * <p>
 * Description:管理員登陸記錄封裝po
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月21日 上午8:11:08
 * @version 1.0
 */
public class AdminsLoginLogReceiver extends AdminsLoginLog {
	// 对应admins类的username
	private String adminName;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

}
