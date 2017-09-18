package com.shtm.manage.service;

import com.shtm.manage.po.UsersReceiver;
import com.shtm.manage.po.UsersReplier;

/**
 * 
 * Title:UsersServiceI
 * <p>
 * Description:用户业务接口
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月18日 上午8:49:56
 * @version 1.0
 */
public interface UsersServiceI {


	/**
	 * Title:selectUsersDatagrid
	 * <p>
	 * Description:分页查询用户
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月18日 下午3:45:01
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception 
	 */
	public UsersReplier selectUsersDatagrid(UsersReceiver receiver) throws Exception;
}
