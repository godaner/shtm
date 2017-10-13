package com.shtm.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomUsersChartsMapper;
import com.shtm.manage.po.echarts.ECharts;
import com.shtm.manage.po.echarts.NameValue;
import com.shtm.manage.po.echarts.Series;
import com.shtm.manage.service.UsersChartsServiceI;
import com.shtm.mapper.UsersMapper;

/**
  * Title:
  * <p>
  * Description:获取用户的图标数据的业务接口实现;
  * <p>
  * @author Kor_Zhang
  * @date 2017年10月10日 上午8:44:22
  * @version 1.0
  */
@Service
public class UsersChartsService implements UsersChartsServiceI {
	@Autowired
	private CustomUsersChartsMapper customUsersChartsMapper;
	@Autowired
	private UsersMapper usersMapper;
	
	
	@Override
	public ECharts getUsersSexCircleChartData() throws Exception {
		ECharts e = new ECharts();
		List<NameValue> seriesData = customUsersChartsMapper.getUsersSexCircleChartData();
		
		Series s = new Series();
		
		s.setData(seriesData);
		
		e.setSeries(s);
		
		return e;
	}

	@Override
	public ECharts getUsersMonthRegistNumberChartData() throws Exception {
		ECharts e = new ECharts();
		
		List<NameValue> seriesData = customUsersChartsMapper.getUsersMonthRegistNumberChartData();

		Series s = new Series();
		
		s.setData(seriesData);
		
		e.setSeries(s);
		
		return e;
	}  
}
