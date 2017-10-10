package com.shtm.manage.service;

import com.shtm.manage.po.echarts.ECharts;

/**
  * Title:
  * <p>
  * Description:获取用户的图标数据的业务接口;
  * <p>
  * @author Kor_Zhang
  * @date 2017年10月10日 上午8:43:18
  * @version 1.0
  */
public interface UsersChartsServiceI {
	/**
	  * Title:
	  * <p>
	  * Description:获取echarts的饼状图的数据;
	  * <p>
	  * @author Kor_Zhang
	  * @date 2017年10月9日 下午6:33:15
	  * @version 1.0
	  * @return
	  * @throws Exception
	  */
	public ECharts getUsersSexCircleChartData() throws Exception;

	/**
	  * Title:
	  * <p>
	  * Description:获取echarts所需的用户月注册数折线图的数据;
	  * <p>
	  * @author Kor_Zhang
	  * @date 2017年10月10日 上午8:42:14
	  * @version 1.0
	  * @return
	  * @throws Exception
	  */
	public ECharts getUsersMonthRegistNumberChartData() throws Exception;
}
