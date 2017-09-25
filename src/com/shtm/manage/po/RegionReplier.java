package com.shtm.manage.po;

import java.util.List;

import com.shtm.po.Region;

/**
 * Title:RegionReplier
 * <p>
 * Description:region信息回复者;
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月25日 下午1:43:54
 * @version 1.0
 */
public class RegionReplier extends Region {

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

	// 当前节点的子节点
	private List<Region> childs;

	public List<Region> getChilds() {
		return childs;
	}

	public void setChilds(List<Region> childs) {
		this.childs = childs;
	}

}
