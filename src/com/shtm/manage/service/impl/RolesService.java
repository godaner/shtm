package com.shtm.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomRolesMapper;
import com.shtm.manage.po.PermissionsReplier;
import com.shtm.manage.po.RolesReceiver;
import com.shtm.manage.po.RolesReplier;
import com.shtm.manage.service.RolesServiceI;
import com.shtm.mapper.RolesMapper;
import com.shtm.mapper.RolesPermissionsMapper;
import com.shtm.po.Admins;
import com.shtm.po.Roles;
import com.shtm.po.RolesExample;
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
	public void updateRolePermissions(String id, String[] permissionsIds)
			throws Exception {

		
		//检测角色状态
		Roles dbR = rolesMapper.selectByPrimaryKey(id);
		

		eject(dbR == null, "角色已不存在");
//		eject(dbR.getStatus() != ROLES_STATUS.ACTIVITY, "当前角色状态不允许更新");
		eject(isStaticRole(dbR),"不能更新内置角色的权限");
		
		
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
		
		/**
		 * 判断name是否存在
		 */
		RolesExample example = new RolesExample();
		com.shtm.po.RolesExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(receiver.getName());
		List<Roles> list = rolesMapper.selectByExample(example);
		
		eject(list.size() >= 1, "该角色名已存在");
		/**
		 * 设置字段
		 */
		receiver.setCreatetime(timestamp());
		receiver.setCreator(((Admins)getOnlineAdmin()).getId());
		receiver.setId(uuid());
		receiver.setStatus(ROLES_STATUS.ACTIVITY);
		receiver.setStaticc(ROLES_STATIC.NO);
		/**
		 * 插入roles
		 */
		
		rolesMapper.insert(receiver);
		
	}

	@Override
	public void deleteRole(RolesReceiver receiver) throws Exception {
		Roles dbR = rolesMapper.selectByPrimaryKey(receiver.getId());
		
		eject(dbR == null || dbR.getStatus() == ROLES_STATUS.DELETE, "该角色已不存在");

		eject(isStaticRole(dbR),"不能删除内置角色");
		
		Roles r = new Roles();
		//设置字段
		r.setId(receiver.getId());
		r.setStatus(ROLES_STATUS.DELETE);
		r.setName(uuid()+"_"+dbR.getName());
		
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
		Roles dbR = rolesMapper.selectByPrimaryKey(receiver.getId());
		//打算更新的roles是否存在
		eject(dbR == null || dbR.getStatus() == ROLES_STATUS.DELETE, "该劫色已不存在");
		
		eject(isStaticRole(dbR),"不能更新内置角色的信息");

		//更新name
		if(!dbR.getName().equals(receiver.getName())){
			/**
			 * 判断name是否存在
			 */
			RolesExample example = new RolesExample();
			com.shtm.po.RolesExample.Criteria criteria = example.createCriteria();
			criteria.andNameEqualTo(receiver.getName());
			List<Roles> list = rolesMapper.selectByExample(example);
			
			eject(list.size() >= 1, "该角色名已存在");
		}else{
			receiver.setName(null);
		}
		
		//禁止更新字段
		receiver.setCreatetime(null);
		receiver.setCreator(null);
		receiver.setStaticc(null);
		
		rolesMapper.updateByPrimaryKeySelective(receiver);
	}

	@Override
	public RolesReplier selectRolePermissionsById(RolesReceiver receiver)
			throws Exception {
		
		RolesReplier replier = new RolesReplier();
		
		List<PermissionsReplier> rows = customRolesMapper.selectRolePermissionsById(receiver);
		
		replier.setRows(rows);
		
		return replier;
	}
	/**
	 * 
	 * Title:
	 * <p>
	 * Description:判断数据库的某个角色为内置角色;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月3日 上午11:41:23
	 * @version 1.0
	 * @param dbRole
	 * @return
	 */
	private boolean isStaticRole(Roles dbRole){
		return dbRole.getStaticc() == ROLES_STATIC.YES;
	}
	
}
