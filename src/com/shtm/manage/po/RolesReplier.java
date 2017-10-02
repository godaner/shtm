package com.shtm.manage.po;

import java.sql.Timestamp;
import java.util.List;

import com.shtm.po.Roles;

/**
 * Title:RolesReplier
 * <p>
 * Description:角色信息返回者;
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年10月1日 下午2:07:30
 * @version 1.0
 * @param <T>
 */
public class RolesReplier<T> extends Roles {

	private Timestamp grantTime;

	public Timestamp getGrantTime() {
		return grantTime;
	}

	public void setGrantTime(Timestamp grantTime) {
		this.grantTime = grantTime;
	}

	
	
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
	private List<T> rows;

	// 记录总数
	private Integer total;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	private String creatorName;

	// 创建者的name
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

}
