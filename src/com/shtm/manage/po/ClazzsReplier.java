package com.shtm.manage.po;

import java.util.List;

import com.shtm.po.Clazzs;

/**
 * Title:ClazzsReplier
 * <p>
 * Description:Clazzs的信息回復者;
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月26日 上午9:42:43
 * @version 1.0
 */
public class ClazzsReplier extends Clazzs {
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
	private List<Clazzs> rows;

	// 记录总数
	private Integer total;

	public List<Clazzs> getRows() {
		return rows;
	}

	public void setRows(List<Clazzs> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	//最大上傳的clazzs數量
	private Integer maxClazzs;

	public Integer getMaxClazzs() {
		return maxClazzs;
	}

	public void setMaxClazzs(Integer maxClazzs) {
		this.maxClazzs = maxClazzs;
	}
	
}
