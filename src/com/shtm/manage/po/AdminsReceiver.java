package com.shtm.manage.po;

import org.hibernate.validator.constraints.NotBlank;

import com.shtm.manage.groups.AdminsGroups.LoginGroups;
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

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	// 排序方式
	private String order;

	// 排序字段
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
