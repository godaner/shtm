package com.shtm.manage.po;

import java.util.List;


import com.shtm.po.GoodsImgs;

/**
 * Title:GoodsImgsReplier
 * <p>
 * Description:自定义商品图片po
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月27日 上午11:32:37
 * @version 1.0
 * @param <T>
 */
public class GoodsImgsReplier<T> extends GoodsImgs {
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

	// 商品图片path(取自于files表),如a.png
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
