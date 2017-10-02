package com.shtm.manage.po;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.shtm.manage.groups.AdminsGroups.LoginGroups;
import com.shtm.manage.groups.AdminsGroups.SelectAdminRolesById;
import com.shtm.manage.groups.AdminsGroups.UpdateAdminRolesGroups;
import com.shtm.po.Admins;

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
public class AdminsReceiver extends Admins {

	// 验证码
	
	@NotBlank(message="{admins.verifyCode.notblank.error}",groups={LoginGroups.class})
	private String verifyCode;

	//角色id
	@Size(min = 0,message="{admins.rolesIds.size.error}",groups={UpdateAdminRolesGroups.class})
	private String[] rolesIds;
	
	public String[] getRolesIds() {
		return rolesIds;
	}

	public void setRolesIds(String[] rolesIds) {
		this.rolesIds = rolesIds;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	// 排序方式
	@NotBlank(message="{admins.order.size.error}",groups={SelectAdminRolesById.class})
	private String order;

	// 排序字段
	@NotBlank(message="{admins.sort.size.error}",groups={SelectAdminRolesById.class})
	private String sort;

	// 当前多少页数
	private Integer page;

	// 一页的行数
	private Integer rows;

	// 分页开始
	private Integer start;

	// 分页结束
	private Integer end;

	public Integer getStart() {
		return (page - 1) * rows + 1;
	}

	public Integer getEnd() {
		return getStart() + rows - 1;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
	
}
