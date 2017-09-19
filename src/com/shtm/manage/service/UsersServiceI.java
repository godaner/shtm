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

	/**
	 * Title:
	 * <p>
	 * Description:根据逐渐查找user
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月18日 下午8:55:04
	 * @version 1.0
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UsersReplier selectUserByPK(String id) throws Exception;

	/**
	 * Title:updateUser
	 * <p>
	 * Description:更新用户;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月19日 下午4:40:45
	 * @version 1.0
	 * @return
	 * @throws Exception
	 */
	public void updateUser(UsersReceiver receiver) throws Exception;
}
