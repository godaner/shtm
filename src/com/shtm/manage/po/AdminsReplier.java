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
public class AdminsReplier extends Admins implements Static{
	//操作结果
	private Integer result;

	//返回信息
	private String msg;
	

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
