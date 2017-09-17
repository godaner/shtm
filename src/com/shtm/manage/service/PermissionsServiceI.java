package com.shtm.manage.service;

import java.util.List;

import com.shtm.manage.po.CustomPermissions;

/**
 * Title:PermissionServiceI
 * <p>
 * Description:权限业务接口
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月17日 下午1:02:53
 * @version 1.0
 */
public interface PermissionsServiceI {
	/**
	 * Title:selectPermissionsByPid
	 * <p>
	 * Description:通过父id获取子节点
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月17日 下午1:03:33
	 * @version 1.0
	 * @return
	 */
	List<CustomPermissions> selectPermissionsByPid(String pid) throws Exception;
}
