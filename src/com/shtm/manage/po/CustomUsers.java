package com.shtm.manage.po;

import java.util.List;

import com.shtm.po.Users;

/**
 * Title:CustomUsers
 * <p>
 * Description:自定义用户
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月18日 上午8:36:33
 * @version 1.0
 */
public class CustomUsers extends Users {
	// 操作结果
	private Integer result;

	// 返回信息
	private String msg;

	private List<CustomUsers> rows;

	private Integer page;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	

	public List<CustomUsers> getRows() {
		return rows;
	}

	public void setRows(List<CustomUsers> rows) {
		this.rows = rows;
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
