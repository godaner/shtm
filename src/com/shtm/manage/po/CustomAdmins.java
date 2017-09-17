package com.shtm.manage.po;

import org.springframework.stereotype.Component;

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
@Component
public class CustomAdmins extends Admins implements Static{
	//操作结果
	private Integer result = RESULT.FALSE;

	//返回信息
	private String msg = "未知信息";
	
	//验证码
	private String verifyCode;
	
	
	
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}


	

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
