package com.shtm.manage.po;

import java.util.List;


/**
  * Title:
  * <p>
  * Description:簡單的信息返回者;
  * <p>
  * @author Kor_Zhang
  * @date 2017年10月9日 下午6:36:29
  * @version 1.0
  * @param <T>
  */
public class SimpleReplier<T> {
	// 操作结果
	private Integer result;

	// 返回信息
	private String msg;

	// 返回列表
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
