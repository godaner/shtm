package com.shtm.manage.po;

import java.util.List;

public class Replier<T> {
	// 操作结果
	private Integer result;

	// 返回信息
	private String msg;

	private List<T> rows;

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

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
