package com.shtm.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomAdminsMapper;
import com.shtm.manage.po.AdminsReceiver;
import com.shtm.manage.service.AdminsServiceI;
import com.shtm.mapper.AdminsMapper;
import com.shtm.po.Admins;
import com.shtm.po.AdminsExample;
import com.shtm.po.AdminsExample.Criteria;
import com.shtm.service.impl.BaseService;

/**
 * Title:AdminsService
 * <p>
 * Description:管理员业务接口实现
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月15日 上午10:44:41
 * @version 1.0
 */
@Service
public class AdminsService extends BaseService implements AdminsServiceI {
	
	@Autowired
	private CustomAdminsMapper customAdminsMapper;

	@Autowired
	private AdminsMapper adminsMapper;
	
	@Override
	public Admins login(AdminsReceiver receiver) throws Exception {
		
		Admins dbAD = customAdminsMapper.selectAdminByUsername((String) receiver.getUsername());
		
		//验证用户是否存在
		eject(dbAD == null, "用户不存在");
		
		//验证密码
		String salt = (String) dbAD.getSalt();
		
		String formPW = (String) receiver.getPassword();
		
		String md5PW = md5(formPW+salt);
		
		eject(!dbAD.getPassword().equals(md5PW), "密码错误");
		
		//屏蔽关键信息
		dbAD.setPassword("");
		
		dbAD.setSalt("");
		
		return dbAD;
		
	}

	@Override
	public void updateTheme(AdminsReceiver receiver) throws Exception {
		
		
		//设置Example
		AdminsExample ae = new AdminsExample();
		
		Criteria cc = ae.createCriteria();
		
		cc.andIdEqualTo(receiver.getId());
		
		//设置要更新的字段
		receiver.setId(null);
		
		//执行更新
		adminsMapper.updateByExampleSelective(receiver,ae );
		
	}

}
