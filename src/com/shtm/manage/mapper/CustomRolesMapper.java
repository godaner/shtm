package com.shtm.manage.mapper;

import java.util.List;

import com.shtm.manage.po.PermissionsReplier;
import com.shtm.manage.po.RolesReceiver;
import com.shtm.po.Roles;



/**
 * Title:CustomRolesMapper
 * <p>
 * Description:自定義rolesMapper
 * <p>
 * @author Kor_Zhang
 * @date 2017年10月2日 上午11:39:12
 * @version 1.0
 */
public interface CustomRolesMapper {

	
	/**
	 * Title:
	 * <p>
	 * Description:查询角色列表;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月2日 下午1:50:21
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	List<Roles> selectRolesDatagrid(RolesReceiver receiver) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:查询角色数量;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月2日 下午1:50:40
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	Integer selectRolesNum(RolesReceiver receiver) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:获取指定id的role的permisssions(所有的permisssions也会被全部查询出来,但是role拥有的permisssions有特殊标记);
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月2日 下午5:24:31
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	List<PermissionsReplier> selectRolePermissionsById(RolesReceiver receiver) throws Exception;
}
