package com.shtm.manage.groups;

/**
 * Title:AdminsGroups
 * <p>
 * Description:管理员验证组
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月22日 下午7:09:51
 * @version 1.0
 */
/**
 * Title:AdminsGroups
 * <p>
 * Description:
 * <p>
 * @author Kor_Zhang
 * @date 2017年10月6日 下午3:47:39
 * @version 1.0
 */
public interface AdminsGroups {
	/**
	 * Title:InsertAdmins
	 * <p>
	 * Description:插入用户的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午7:10:41
	 * @version 1.0
	 */
	public interface InsertAdminGroups{};
	
	/**
	 * Title:UpdateAdminGroups
	 * <p>
	 * Description:更新管理员的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 上午8:42:11
	 * @version 1.0
	 */
	public interface UpdateAdminGroups{};
	
	/**
	 * Title:DeleteAdminGroups
	 * <p>
	 * Description:管理员删除的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 上午8:45:38
	 * @version 1.0
	 */
	public interface DeleteAdminGroups{};
	
	/**
	 * 
	 * Title:SelectAdminGroups
	 * <p>
	 * Description:按id查询单个用户的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 上午10:58:43
	 * @version 1.0
	 */
	public interface SelectAdminGroups{};
	
	/**
	 * Title:LoginGroups
	 * <p>
	 * Description:登录验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 下午3:41:42
	 * @version 1.0
	 */
	public interface LoginGroups{};
	
	/**
	 * 
	 * Title:SelectAdminRolesById
	 * <p>
	 * Description:获取指定id的admin的roles(所有的roles也会被全部查询出来,但是admin拥有的roles有特殊标记)的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月1日 下午1:44:50
	 * @version 1.0
	 */
	public interface SelectAdminRolesById{};
	
	
	
	/**
	 * Title:UpdateAdminRolesGroups
	 * <p>
	 * Description:更新管理员角色所用的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月1日 下午5:19:00
	 * @version 1.0
	 */
	public interface UpdateAdminRolesGroups{};
	
	
	/**
	 * Title:CheckOnlineAdminPW
	 * <p>
	 * Description:检测在线用户密码所使用的分组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月6日 上午10:43:56
	 * @version 1.0
	 */
	public interface CheckOnlineAdminPWGroups{};

	/**
	 * Title:UpdateAdminSelfGroups
	 * <p>
	 * Description:管理员更新自身所使用的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月6日 下午3:47:41
	 * @version 1.0
	 */
	public interface UpdateAdminSelfGroups{};
	
	
}
