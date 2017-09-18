package com.shtm.manage.po;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shtm.po.Permissions;
import com.shtm.util.Static;

/**
 * Title:CustomPermissions
 * <p>
 * Description:拓展权限po
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月17日 下午1:04:11
 * @version 1.0
 */
@Component
public class CustomPermissions extends Permissions implements Static {

	private String state;

	// 操作结果
	private Integer result;

	// 返回信息
	private String msg;

	private List<CustomPermissions> datagrid;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public List<CustomPermissions> getDatagrid() {
		return datagrid;
	}

	public void setDatagrid(List<CustomPermissions> datagrid) {
		this.datagrid = datagrid;
	}

}
