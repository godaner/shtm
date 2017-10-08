package com.shtm.manage.po;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import com.shtm.manage.groups.UsersGroups.GetUsersExcelGroups;
import com.shtm.po.Users;

/**
 * Title:CustomUsers
 * <p>
 * Description:自定义用户
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月18日 上午8:36:33
 * @version 1.0
 */
public class UsersReceiver extends Users {
	

	// 排序方式
	@NotNull(message = "{users.order.notnull.error}", groups = { GetUsersExcelGroups.class })
	private String order;

	// 排序字段
	@NotNull(message = "{users.sort.notnull.error}", groups = { GetUsersExcelGroups.class })
	private String sort;

	// 当前多少页数
	@NotNull(message = "{users.page.notnull.error}", groups = { GetUsersExcelGroups.class })
	@Range(message = "{users.page.range.error}", groups = { GetUsersExcelGroups.class })
	private Integer page;

	// 一页的行数
	@NotNull(message = "{users.rows.notnull.error}", groups = { GetUsersExcelGroups.class })
	@Range(message = "{users.rows.range.error}", groups = { GetUsersExcelGroups.class })
	private Integer rows;
	
	// 分页开始
	private Integer start;

	// 分页结束
	private Integer end;
	
	//上传的文件
	private MultipartFile file;
	
	
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Integer getStart() {
		return (page - 1) * rows + 1;
	}
	public Integer getEnd() {
		return getStart() + rows - 1;
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
	@Override
	public String toString() {
		return "UsersReceiver [order=" + order + ", sort=" + sort + ", page="
				+ page + ", rows=" + rows + ", start=" + start + ", end=" + end
				+ ", file=" + file + ", getFile()=" + getFile()
				+ ", getStart()=" + getStart() + ", getEnd()=" + getEnd()
				+ ", getPage()=" + getPage() + ", getRows()=" + getRows()
				+ ", getOrder()=" + getOrder() + ", getSort()=" + getSort()
				+ "]";
	}
	
	
}
