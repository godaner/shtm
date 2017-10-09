package com.shtm.manage.mapper;

import java.util.List;

import com.shtm.manage.po.UsersReceiver;
import com.shtm.manage.po.UsersReplier;
import com.shtm.manage.po.echarts.NameValue;


/**
 * Title:CustomUsersMapper
 * <p>
 * Description:自定义用户mapper
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月18日 上午8:35:38
 * @version 1.0
 */
public interface CustomUsersMapper {
	/**
	 * Title:selectUsersDatagrid
	 * <p>
	 * Description:查询用户
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月18日 上午8:35:26
	 * @version 1.0
	 * @param po
	 * @return
	 * @throws Exception
	 */
	List<UsersReplier> selectUsersDatagrid(UsersReceiver po)throws Exception;
	/**
	 * 
	 * Title:selectUsersNum
	 * <p>
	 * Description:查询users记录数
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月18日 上午11:46:13
	 * @version 1.0
	 * @param po
	 * @return
	 * @throws Exception
	 */
	Integer selectUsersNum(UsersReceiver po)throws Exception;
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
}
