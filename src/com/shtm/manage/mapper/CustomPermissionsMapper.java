package com.shtm.manage.mapper;

import java.util.List;

import com.shtm.manage.po.CustomPermissions;


/**
 * Title:CustomAdminsMapper
 * <p>
 * Description:自定义admins的mapper
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月15日 下午3:38:21
 * @version 1.0
 */
public interface CustomPermissionsMapper {
	/**
	 * Title:selectPermissionsByPid
	 * <p>
	 * Description:通過pid獲取權限;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月17日 下午2:00:23
	 * @version 1.0
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	List<CustomPermissions> selectPermissionsByPid(String pid)throws Exception;
}
