package com.shtm.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomRolesMapper;
import com.shtm.manage.po.RolesReceiver;
import com.shtm.manage.po.RolesReplier;
import com.shtm.manage.service.RolesServiceI;
import com.shtm.mapper.RolesMapper;
import com.shtm.mapper.RolesPermissionsMapper;
import com.shtm.po.Roles;
import com.shtm.po.RolesPermissions;
import com.shtm.po.RolesPermissionsExample;
import com.shtm.po.RolesPermissionsExample.Criteria;
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
	
	@Autowired
	private RolesPermissionsMapper rolesPermissionsMapper;
	
	@Override
	public void updateRolePermission(String id, String[] permissionsIds)
			throws Exception {
		
		//检测角色状态
		Roles dbR = rolesMapper.selectByPrimaryKey(id);
		eject(dbR == null || dbR.getStatus() == ROLES_STATUS.DELETE, "角色已不存在");
		
		
		//删除原有的权限
		RolesPermissionsExample example = new RolesPermissionsExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleEqualTo(id);
		rolesPermissionsMapper.deleteByExample(example);
		//添加新权限
		for(String perId : permissionsIds){
			RolesPermissions rp = new RolesPermissions();
			rp.setId(uuid());
			rp.setGranttime(timestamp());
			rp.setPermission(perId);
			rp.setRole(id);
			rolesPermissionsMapper.insert(rp);
		}
		
	}

	@Override
	public void insertRoleGroups(RolesReceiver receiver) throws Exception {
		receiver.setId(uuid());
		receiver.setStatus(ROLES_STATUS.ACTIVITY);
		rolesMapper.insert(receiver);
		
	}

	@Override
	public void deleteRole(RolesReceiver receiver) throws Exception {
		Roles r = new Roles();
		//设置字段
		r.setId(receiver.getId());
		r.setStatus(ROLES_STATUS.DELETE);
		
		rolesMapper.updateByPrimaryKeySelective(r);
	}

	@Override
	public RolesReplier selectRolesDatagrid(RolesReceiver receiver) throws Exception {
		
		RolesReplier replier = new RolesReplier();

		// 查询
		replier.setRows(customRolesMapper.selectRolesDatagrid(receiver));

		// 设置记录总数
		Integer totalNum = customRolesMapper.selectRolesNum(receiver);

		replier.setTotal(totalNum);

		return replier;
	}

	@Override
	public void updateRole(RolesReceiver receiver) throws Exception {
		rolesMapper.updateByPrimaryKeySelective(receiver);
	}

}
