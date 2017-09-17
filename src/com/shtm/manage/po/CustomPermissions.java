package com.shtm.manage.po;

import org.springframework.stereotype.Component;

import com.shtm.po.Permissions;
import com.shtm.util.Static;

/**
 * Title:CustomPermissions
 * <p>
 * Description:拓展权限po
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月17日 下午1:04:11
 * @version 1.0
 */
@Component
public class CustomPermissions extends Permissions implements Static{
	
	private String state = PERS_STATE.CLOSED;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
