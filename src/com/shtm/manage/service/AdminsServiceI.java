package com.shtm.manage.service;

import com.shtm.manage.po.AdminsLoginLogReceiver;
import com.shtm.manage.po.AdminsReceiver;
import com.shtm.po.Admins;

/**
 * Title:UsersServiceI
 * <p>
 * Description:用户业务接口
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月15日 上午10:44:46
 * @version 1.0
 */
public interface AdminsServiceI {
	/**
	 * Title:login
	 * <p>
	 * Description:登录,并且记录
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月15日 上午10:44:14
	 * @version 1.0
	 * @param adminsLoginLogReceiver 
	 * @return 返回数据库的记录
	 * @throws Exception
	 */
	public Admins login(AdminsReceiver po, AdminsLoginLogReceiver adminsLoginLogReceiver) throws Exception;
	
	/**
	 * 
	 * Title:updateTheme
	 * <p>
	 * Description:更新主题
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月16日 下午4:34:15
	 * @version 1.0
	 * @param po
	 * @throws Exception
	 */
	public void updateTheme(AdminsReceiver po)throws Exception;

}
