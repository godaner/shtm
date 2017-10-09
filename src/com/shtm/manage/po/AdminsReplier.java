package com.shtm.manage.po;

import java.util.Collection;
import java.util.List;

import com.shtm.po.Admins;
import com.shtm.po.Roles;

/**
 * Title:CustomAdmins
 * <p>
 * Description:自定义po
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月15日 上午11:11:14
 * @version 1.0
 * @param <T>
 */
public class AdminsReplier<T> extends Admins{
	//记录admins的登陆记录
	private AdminsLoginLogReplier adminsLoginLogReplier;
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
	

	public AdminsLoginLogReplier getAdminsLoginLogReplier() {
		return adminsLoginLogReplier;
	}

	public void setAdminsLoginLogReplier(AdminsLoginLogReplier adminsLoginLogReplier) {
		this.adminsLoginLogReplier = adminsLoginLogReplier;
	}
	

	// 返回数据
	private Collection<T> rows;

	// 记录总数
	private Integer total;

	public Collection<T> getRows() {
		return rows;
	}

	public void setRows(Collection<T> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	private String creatorName;

	
	//创建者的name
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	//該admin的roles
	private List<Roles> roles;

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
	
}
