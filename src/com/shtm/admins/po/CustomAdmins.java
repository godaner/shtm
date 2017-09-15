package com.shtm.admins.po;

import org.springframework.stereotype.Component;

import com.shtm.po.Admins;

/**
 * Title:CustomAdmins
 * <p>
 * Description:自定义po
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月15日 上午11:11:14
 * @version 1.0
 */
@Component
public class CustomAdmins extends Admins {
	private Boolean loginSuccess = false;

	private String msg = "未知信息";
	public Boolean getLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(Boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
