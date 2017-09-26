package com.shtm.manage.po;

import java.util.List;

import com.shtm.po.Users;

/**
 * Title:Replier
 * <p>
 * Description:users返回結果;
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月18日 下午3:25:26
 * @version 1.0
 */
public class UsersReplier extends Users {
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
	private List<UsersReplier> rows;

	// 记录总数
	private Integer total;

	public List<UsersReplier> getRows() {
		return rows;
	}

	public void setRows(List<UsersReplier> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	//用户发布,购买商品数量
	private Integer buyNumber;
	private Integer sellNumber;

	public Integer getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}

	public Integer getSellNumber() {
		return sellNumber;
	}

	public void setSellNumber(Integer sellNumber) {
		this.sellNumber = sellNumber;
	}
	
}
