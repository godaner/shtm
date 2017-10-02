package com.shtm.manage.service;

import com.shtm.manage.po.RolesReceiver;

/**
 * Title:RolesServiceI
 * <p>
 * Description:roles業務接口;
 * <p>
 * @author Kor_Zhang
 * @date 2017年10月2日 上午11:35:19
 * @version 1.0
 */
public interface RolesServiceI {

	/**
	 * Title:
	 * <p>
	 * Description:更新角色權限
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月2日 上午11:37:31
	 * @version 1.0
	 * @param id
	 * @param permissionsIds
	 * @throws Exception
	 */
	void updateRolePermission(String id, String[] permissionsIds) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:添加角色
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月2日 上午11:49:01
	 * @version 1.0
	 * @param receiver
	 * @throws Exception
	 */
	void insertRoleGroups(RolesReceiver receiver) throws Exception;

}
