package com.shtm.manage.service;

import java.util.List;

import com.shtm.manage.po.AdminsLoginLogReceiver;
import com.shtm.manage.po.AdminsLoginLogReplier;
import com.shtm.manage.po.AdminsReceiver;
import com.shtm.manage.po.AdminsReplier;
import com.shtm.po.Admins;
import com.shtm.po.Permissions;
import com.shtm.po.Roles;

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
	 * Title:
	 * <p>
	 * Description:通过username查询admins
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 下午3:35:06
	 * @version 1.0
	 * @param po
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public Admins selectAdminByUsername(String username) throws Exception;
	
	/**
	 * 
	 * Title:
	 * <p>
	 * Description:插入管理员登录记录
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 下午3:38:45
	 * @version 1.0
	 * @param adminId
	 * @param adminsLoginLogReceiver
	 * @throws Exception
	 */
	public AdminsLoginLogReplier insertAdminsLoginLog(String adminId,AdminsLoginLogReceiver adminsLoginLogReceiver) throws Exception;
	
	
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

	/**
	 * Title:
	 * <p>
	 * Description:通过管理员id获取其角色;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 下午6:34:32
	 * @version 1.0
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Roles> selectRoles(String id) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:通过管理员id获取其权限;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 下午6:52:40
	 * @version 1.0
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public List<Permissions> selectPermissions(String id) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:登录
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 下午8:24:37
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	AdminsReplier login(AdminsReceiver receiver) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:获取指定id的admin的roles(所有的roles也会被全部查询出来,但是admin拥有的roles有特殊标记);
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月1日 下午1:59:47
	 * @version 1.0
	 * @param receiver
	 * @return
	 */
	public AdminsReplier selectAdminRolesById(AdminsReceiver receiver) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:更新管理员的角色;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月1日 下午5:23:50
	 * @version 1.0
	 * @param id
	 * @param rolesIds
	 * @return
	 * @throws Exception
	 */
	public void updateAdminRoles(String id, String[] rolesIds) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:通过主键查询;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月6日 上午10:56:44
	 * @version 1.0
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Admins selectAdminByPK(String id) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:冻结自定id的admin;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月6日 上午11:16:10
	 * @version 1.0
	 * @param id
	 * @throws Exception
	 */
	public void frozen(String id) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:更新自身的信息;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月6日 下午3:52:54
	 * @version 1.0
	 * @param receiver
	 * @return 
	 * @throws Exception
	 */
	public AdminsReplier updateAdminSelf(AdminsReceiver receiver) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:踢出管理员;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月6日 下午8:27:10
	 * @version 1.0
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void kickOutAdmin(String id) throws Exception;


}
