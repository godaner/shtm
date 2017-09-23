package com.shtm.manage.service;

import com.shtm.manage.po.AdminsLoginLogReceiver;
import com.shtm.manage.po.AdminsReceiver;
import com.shtm.manage.po.AdminsReplier;
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

	/**
	 * Title:selectAdminsDatagrid
	 * <p>
	 * Description:通过组合条件查询分页数据
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午2:10:57
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	public AdminsReplier selectAdminsDatagrid(AdminsReceiver receiver) throws Exception;

	/**
	 * Title:insertAdmin
	 * <p>
	 * Description:添加一个admin
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午4:19:34
	 * @version 1.0
	 * @param receiver
	 * @throws Exception
	 */
	public void insertAdmin(String id,AdminsReceiver receiver) throws Exception;

	/**
	 * Title:deleteAdmin
	 * <p>
	 * Description:刪除管理員
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 上午8:23:20
	 * @version 1.0
	 * @param receiver
	 * @throws Exception
	 */
	public void deleteAdmin(AdminsReceiver receiver) throws Exception;

	/**
	 * Title:updateAdmin
	 * <p>
	 * Description:更新admin
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 上午8:52:46
	 * @version 1.0
	 * @param receiver
	 * @throws Exception
	 */
	public void updateAdmin(AdminsReceiver receiver) throws Exception;

	/**
	 * Title:selectAdmin
	 * <p>
	 * Description:通过id查询一个admin
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 上午11:02:09
	 * @version 1.0
	 * @param id
	 * @throws Exception
	 */
	public AdminsReplier selectAdmin(String id) throws Exception;


}
