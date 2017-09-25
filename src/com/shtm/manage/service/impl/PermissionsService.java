package com.shtm.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomPermissionsMapper;
import com.shtm.manage.po.PermissionsReplier;
import com.shtm.manage.service.PermissionsServiceI;
import com.shtm.service.impl.BaseService;

/**
 * Title:PermissionsService
 * <p>
 * Description:权限业务接口实现
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月17日 下午1:07:05
 * @version 1.0
 */
@Service
public class PermissionsService extends BaseService implements PermissionsServiceI{
	@Autowired
	private CustomPermissionsMapper customPermissionsMapper;
	
	@Override
	public List<PermissionsReplier> selectPermissionsByPid(String pid) throws Exception {
		
		if(null != pid && pid.trim().equals("")){
			pid = null;
		}
		
		return customPermissionsMapper.selectPermissionsByPid(pid);
	} 
}
