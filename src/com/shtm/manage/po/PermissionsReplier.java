package com.shtm.manage.po;

import java.util.List;

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
public class PermissionsReplier extends Permissions implements Static {
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

	// 數據集合
	private List<PermissionsReplier> datagrid;

	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<PermissionsReplier> getDatagrid() {
		return datagrid;
	}

	public void setDatagrid(List<PermissionsReplier> datagrid) {
		this.datagrid = datagrid;
	}

}
