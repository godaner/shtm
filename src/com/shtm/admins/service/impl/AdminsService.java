package com.shtm.admins.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.admins.mapper.CustomAdminsMapper;
import com.shtm.admins.po.CustomAdmins;
import com.shtm.admins.service.AdminsServiceI;
import com.shtm.po.Admins;
import com.shtm.service.impl.BaseService;

/**
 * Title:UsersService
 * <p>
 * Description:用户业务接口实现
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月15日 上午10:44:41
 * @version 1.0
 */
@Service
public class AdminsService extends BaseService implements AdminsServiceI {
	
	@Autowired
	private CustomAdminsMapper customAdminsMapper;
	
	@Override
	public CustomAdmins login(CustomAdmins po) throws Exception {
		
		Admins dbAD = customAdminsMapper.selectAdminByUsername((String) po.getUsername());
		
		//验证用户是否存在
		eject(dbAD == null, "用户不存在");
		
		//验证密码
		String salt = (String) dbAD.getSalt();
		
		String formPW = (String) po.getPassword();
		
		String md5PW = md5(formPW+salt);
		
		eject(!dbAD.getPassword().equals(md5PW), "密码错误");
		
		
		return (CustomAdmins) dbAD;
		
	}


}
