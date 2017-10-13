package com.shtm.manage.mapper;

import java.util.List;

import com.shtm.manage.po.echarts.NameValue;

/**
  * Title:
  * <p>
  * Description:获取图标数据的mapper;
  * <p>
  * @author Kor_Zhang
  * @date 2017年10月10日 上午8:47:14
  * @version 1.0
  */
public interface CustomUsersChartsMapper {
	/**
	 * 
	  * Title:
	  * <p>
	  * Description:获取性别圆饼图数据;
	  * <p>
	  * @author Kor_Zhang
	  * @date 2017年10月10日 上午12:19:10
	  * @version 1.0
	  * @return
	  * @throws Exception
	 */
	List<NameValue> getUsersSexCircleChartData() throws Exception;
	/**
	  * Title:
	  * <p>
	  * Description:获取echarts所需的用户月注册数折线图的数据;
	  * <p>
	  * @author Kor_Zhang
	  * @date 2017年10月10日 上午8:46:39
	  * @version 1.0
	  * @return
	  * @throws Exception
	  */
	List<NameValue> getUsersMonthRegistNumberChartData() throws Exception;
}
