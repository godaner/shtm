package com.shtm.manage.service;

import com.shtm.manage.po.CustomAdmins;
import com.shtm.manage.po.CustomUsers;

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
	 * Description:查询用户
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月18日 上午8:50:31
	 * @version 1.0
	 * @param po
	 * @return
	 */
	public CustomUsers selectUsersDatagrid(CustomUsers po) throws Exception ;
}
