package com.shtm.manage.mapper;

import java.util.List;

import com.shtm.manage.po.AdminsReceiver;
import com.shtm.manage.po.AdminsReplier;
import com.shtm.manage.po.RolesReplier;
import com.shtm.po.Admins;
import com.shtm.po.Permissions;
import com.shtm.po.Roles;


/**
 * Title:CustomAdminsMapper
 * <p>
 * Description:自定义admins的mapper
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月15日 下午3:38:21
 * @version 1.0
 */
public interface CustomAdminsMapper {
	/**
	 * Title:selectAdminByUsername
	 * <p>
	 * Description:通过username获取admin
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午2:17:40
	 * @version 1.0
	 * @param username
	 * @return
	 * @throws Exception
	 */
	AdminsReplier selectAdminByUsername(String username) throws Exception;

	/**
	 * Title:selectAdminsDatagrid
	 * <p>
	 * Description:通过组合条件获取list
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午2:17:38
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	List<Admins> selectAdminsDatagrid(AdminsReceiver receiver) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:获取组合条件的记录数量;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午2:17:36
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	Integer selectAdminsNum(AdminsReceiver receiver) throws Exception;

	/**
	 * Title:selectAdmin
	 * <p>
	 * Description:获取指定id的相关信息;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 上午11:18:28
	 * @version 1.0
	 * @param id
	 * @return
	 * @throws Exception
	 */
	AdminsReplier selectAdmin(String id) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:通过adminid获取roles
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 下午6:38:53
	 * @version 1.0
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Roles> selectRoles(String id) throws Exception;
	
	/**
	 * Title:
	 * <p>
	 * Description:通过id查询权限
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 下午6:52:13
	 * @version 1.0
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Permissions> selectPermissions(String id) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:获取指定id的admin的roles(所有的roles也会被全部查询出来,但是admin拥有的roles有特殊标记);
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月1日 下午2:02:02
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	List<RolesReplier> selectAdminRolesById(AdminsReceiver receiver) throws Exception;
}
