package com.shtm.manage.po;

import com.shtm.po.Admins;
import com.shtm.util.Static;

/**
 * Title:CustomAdmins
 * <p>
 * Description:自定义po
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月15日 上午11:11:14
 * @version 1.0
 */
public class AdminsReceiver extends Admins implements Static{
	
	//验证码
	private String verifyCode;
	
	
	
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}


}
