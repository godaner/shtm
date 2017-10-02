package com.shtm.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomRolesMapper;
import com.shtm.manage.po.RolesReceiver;
import com.shtm.manage.service.RolesServiceI;
import com.shtm.mapper.RolesMapper;
import com.shtm.service.impl.BaseService;

/**
 * Title:RolesService
 * <p>
 * Description:roles業務接口實現;
 * <p>
 * @author Kor_Zhang
 * @date 2017年10月2日 上午11:35:49
 * @version 1.0
 */
@Service
public class RolesService extends BaseService implements RolesServiceI {

	@Autowired
	private RolesMapper rolesMapper;
	

	@Autowired
	private CustomRolesMapper customRolesMapper;
	
	@Override
	public void updateRolePermission(String id, String[] permissionsIds)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertRoleGroups(RolesReceiver receiver) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
