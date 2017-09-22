package com.shtm.manage.mapper;

import java.util.List;

import com.shtm.manage.po.AdminsReceiver;
import com.shtm.manage.po.AdminsReplier;
import com.shtm.po.Admins;


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
	 * Description:
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午2:17:36
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	Integer selectAdminsNum(AdminsReceiver receiver) throws Exception;
}
