									package com.shtm.manage.po.echarts;



/**
  * Title:
  * <p>
  * Description:百度echarts数据封装;
  * <p>
  * @author Kor_Zhang
  * @date 2017年10月9日 下午4:35:29
  * @version 1.0
 * @param <T>
  */
public class ECharts {
	private Series series;

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}
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
	
	
}
