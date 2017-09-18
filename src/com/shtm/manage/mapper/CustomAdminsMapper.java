package com.shtm.manage.mapper;

import com.shtm.manage.po.AdminsReplier;


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
	AdminsReplier selectAdminByUsername(String username)throws Exception;
}
