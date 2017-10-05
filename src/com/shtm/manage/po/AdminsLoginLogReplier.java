package com.shtm.manage.po;

import java.util.Collection;

import com.shtm.po.AdminsLoginLog;

/**
 * Title:AdminsLoginLogReplier
 * <p>
 * Description:管理登录记录回复者;
 * <p>
 * @author Kor_Zhang
 * @date 2017年10月5日 下午2:32:34
 * @version 1.0
 * @param <T>
 */
public class AdminsLoginLogReplier<T> extends AdminsLoginLog {
	//对应admins类的username
	private String adminName;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	private Collection<T> rows;

	public Collection<T> getRows() {
		return rows;
	}

	public void setRows(Collection<T> rows) {
		this.rows = rows;
	}
	
	
}
