package com.shtm.manage.po;

import java.util.List;

import com.shtm.po.Admins;
import com.shtm.po.Users;

/**
 * Title:CustomAdmins
 * <p>
 * Description:自定义po
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月15日 上午11:11:14
 * @version 1.0
 */
public class AdminsReplier extends Admins{
	// 操作结果
	private Integer result;

	// 返回信息
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

	// 返回数据
	private List<Users> rows;

	// 记录总数
	private Integer total;

	public List<Users> getRows() {
		return rows;
	}

	public void setRows(List<Users> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
