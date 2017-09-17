package com.shtm.manage.po;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shtm.util.Static.RESULT;

/**
 * Title:Sender
 * <p>
 * Description:返回结果;可以用来被继承;
 * 做为controller的类参数;
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月17日 下午1:27:43
 * @version 1.0
 */
@Component
public class JsonResult {
	// 操作结果
	private Integer result = RESULT.FALSE;

	// 返回信息
	private String msg = "未知信息";

	//数据表格
	private List<?> datagrid;
	
	
	
	public List<?> getDatagrid() {
		return datagrid;
	}

	public void setDatagrid(List<?> datagrid) {
		this.datagrid = datagrid;
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
