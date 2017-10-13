package com.shtm.manage.po;

import org.springframework.web.multipart.MultipartFile;

import com.shtm.po.GoodsImgs;

/**
 * Title:GoodsImgsReceiver
 * <p>
 * Description:goodsimg信息接受者
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月28日 上午11:09:04
 * @version 1.0
 */
public class GoodsImgsReceiver extends GoodsImgs {
	
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
	
	//保存参数goodsId
	private String goodsId;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
