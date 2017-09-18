package com.shtm.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomUsersMapper;
import com.shtm.manage.po.CustomUsers;
import com.shtm.manage.service.UsersServiceI;
import com.shtm.service.impl.BaseService;

/**
 * 
 * Title:UsersService
 * <p>
 * Description:用户业务接口实现
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月18日 上午8:51:36
 * @version 1.0
 */
@Service
public class UsersService extends BaseService implements UsersServiceI {
	
	@Autowired
	private CustomUsersMapper customUsersMapper;

	@Override
	public CustomUsers selectUsersDatagrid(CustomUsers po) throws Exception {
		
		po.setRows(customUsersMapper.selectUsersDatagrid(po));
		
		return po;
	}

}
