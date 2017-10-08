package com.shtm.manage.service;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

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
	 * Description:通过组合条件查询分页数据
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

	/**
	 * Title:deleteUser
	 * <p>
	 * Description:删除用户
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月20日 下午4:56:46
	 * @version 1.0
	 * @param id
	 * @throws Exception
	 */
	public void deleteUser(String id)throws Exception;
	/**
	 * 
	 * Title:insertUser
	 * <p>
	 * Description:添加用户
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月21日 上午11:58:38
	 * @version 1.0
	 * @param receiver
	 */
	public void insertUser(UsersReceiver receiver)throws Exception;

	/**
	  * Title:
	  * <p>
	  * Description:获取excel
	  * <p>
	  * @author Kor_Zhang
	  * @date 2017年10月8日 下午10:06:39
	  * @version 1.0
	  * @param rceiver
	  * @return 
	  * @throws Exception
	  */
	public void getUsersExcel(UsersReceiver rceiver, HttpServletResponse response) throws Exception;
}
