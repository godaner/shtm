package com.shtm.manage.po;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.shtm.manage.groups.ClazzsGroups.SelectClazzsDatagridGroups;
import com.shtm.po.Clazzs;

/**
 * Title:ClazzsReplier
 * <p>
 * Description:clazzs信息接收者;
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月26日 上午9:42:43
 * @version 1.0
 */
public class ClazzsReceiver extends Clazzs {

	// 排序方式
	@NotNull(message = "{clazzs.order.notnull.error}", groups = { SelectClazzsDatagridGroups.class })
	private String order;

	// 排序字段
	@NotNull(message = "{clazzs.sort.notnull.error}", groups = { SelectClazzsDatagridGroups.class })
	private String sort;

	// 当前多少页数
	@NotNull(message = "{clazzs.page.notnull.error}", groups = { SelectClazzsDatagridGroups.class })
	@Range(message = "{clazzs.page.range.error}", groups = { SelectClazzsDatagridGroups.class })
	private Integer page;

	// 一页的行数
	@NotNull(message = "{clazzs.rows.notnull.error}", groups = { SelectClazzsDatagridGroups.class })
	@Range(message = "{clazzs.rows.range.error}", groups = { SelectClazzsDatagridGroups.class })
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
