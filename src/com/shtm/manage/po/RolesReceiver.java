package com.shtm.manage.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.shtm.manage.groups.RolesGroups.SelectRolesDatagridGroups;
import com.shtm.manage.groups.RolesGroups.UpdateRolePermission;
import com.shtm.po.Roles;

/**
 * Title:RolesReceiver
 * <p>
 * Description:角色信息接受者;
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年10月1日 下午2:08:29
 * @version 1.0
 */
public class RolesReceiver extends Roles {
	// 權限的ids
	@Size(min = 0, message = "{roles.permissionsIds.size.error}", groups = { UpdateRolePermission.class })
	private String[] permissionsIds;
	// 排序方式
	@NotNull(message = "{roles.order.notnull.error}", groups = { SelectRolesDatagridGroups.class })
	private String order;

	// 排序字段
	@NotNull(message = "{roles.sort.notnull.error}", groups = { SelectRolesDatagridGroups.class })
	private String sort;

	// 当前多少页数
	@NotNull(message = "{roles.page.notnull.error}", groups = { SelectRolesDatagridGroups.class })
	@Range(message = "{roles.page.range.error}", groups = { SelectRolesDatagridGroups.class })
	private Integer page;

	// 一页的行数
	@NotNull(message = "{roles.rows.notnull.error}", groups = { SelectRolesDatagridGroups.class })
	@Range(message = "{roles.rows.range.error}", groups = { SelectRolesDatagridGroups.class })
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

	public String[] getPermissionsIds() {
		return permissionsIds;
	}

	public void setPermissionsIds(String[] permissionsIds) {
		this.permissionsIds = permissionsIds;
	}

}
