package com.shtm.manage.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomAdminsMapper;
import com.shtm.manage.po.AdminsLoginLogReceiver;
import com.shtm.manage.po.AdminsReceiver;
import com.shtm.manage.po.AdminsReplier;
import com.shtm.manage.service.AdminsServiceI;
import com.shtm.mapper.AdminsLoginLogMapper;
import com.shtm.mapper.AdminsMapper;
import com.shtm.po.Admins;
import com.shtm.po.AdminsExample;
import com.shtm.po.AdminsExample.Criteria;
import com.shtm.service.impl.BaseService;
import com.shtm.util.Static.ADMINS_LOGIN_LOG_RESULT;
import com.shtm.util.Static.ADMINS_STATUS;

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
	

	@Autowired
	private AdminsLoginLogMapper adminsLoginLogMapper;
	
	@Override
	public Admins login(AdminsReceiver receiver,AdminsLoginLogReceiver adminsLoginLogReceiver) throws Exception {
		
		Admins dbAD = customAdminsMapper.selectAdminByUsername((String) receiver.getUsername());
		
		//设置参数
		adminsLoginLogReceiver.setTime(new Date());
		adminsLoginLogReceiver.setId(uuid());
		adminsLoginLogReceiver.setLoginAdmin(dbAD.getId());
		//验证用户是否存在
		eject(dbAD == null, "用户不存在",new EjectCallFun() {
			
			@Override
			public void ejectCallFun(Boolean r) {
				if(r){
					adminsLoginLogReceiver.setResult(ADMINS_LOGIN_LOG_RESULT.FAILURE);
					adminsLoginLogMapper.insert(adminsLoginLogReceiver);
				}
			}
		});
		
		//验证密码
		String salt = (String) dbAD.getSalt();
		
		String formPW = (String) receiver.getPassword();
		
		String md5PW = md5(formPW+salt);
		
		eject(!dbAD.getPassword().equals(md5PW), "密码错误",new EjectCallFun() {
			
			@Override
			public void ejectCallFun(Boolean r) {
				if(r){
					adminsLoginLogReceiver.setResult(ADMINS_LOGIN_LOG_RESULT.FAILURE);
					adminsLoginLogMapper.insert(adminsLoginLogReceiver);
				}
			}
		});
		
		//屏蔽关键信息
		dbAD.setPassword("");
		
		dbAD.setSalt("");
		
		//登录成功
		adminsLoginLogReceiver.setResult(ADMINS_LOGIN_LOG_RESULT.SUCCESS);
		adminsLoginLogMapper.insert(adminsLoginLogReceiver);
		
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

	@Override
	public AdminsReplier selectAdminsDatagrid(AdminsReceiver receiver)
			throws Exception {
		AdminsReplier replier = new AdminsReplier();

		// 查询
		replier.setRows(customAdminsMapper.selectAdminsDatagrid(receiver));

		// 设置记录总数
		Integer totalNum = customAdminsMapper.selectAdminsNum(receiver);

		replier.setTotal(totalNum);

		return replier;
	}

	@Override
	public void insertAdmin(String id,AdminsReceiver receiver) throws Exception {

		/**
		 * 小寫郵箱和用戶名
		 */
		receiver.setEmail(receiver.getEmail());
		receiver.setUsername(receiver.getUsername());
		
		/**
		 * 设置属性
		 */
		
		receiver.setCreatetime(timestamp());
		
		receiver.setCreator(id);
		
		receiver.setId(uuid());
		
		receiver.setSalt(receiver.getUsername());
		
		adminsMapper.insert(receiver);
	}

	@Override
	public void deleteAdmin(AdminsReceiver receiver) throws Exception {
		
		Admins ad = adminsMapper.selectByPrimaryKey(receiver.getId());
		
		
		if(ad == null || ad.getStatus() == ADMINS_STATUS.DELETE){
			//如果已被刪除
			return ;
		}
		
		//標志位刪除
		Admins newAd = new Admins();
		
		newAd.setId(receiver.getId());
		
		newAd.setUsername(uuid()+"_"+ad.getUsername());
		
		newAd.setStatus(ADMINS_STATUS.DELETE);
		
		adminsMapper.updateByPrimaryKeySelective(newAd);
		
	}

	@Override
	public void updateAdmin(AdminsReceiver receiver) throws Exception {
		/**
		 * 判断password是否需要更新
		 */
		String password = receiver.getPassword();
		if (password != null && !password.isEmpty()) {
			//如果有更新情况,加密密码
			receiver.setPassword(md5(receiver.getEmail()+receiver.getPassword()));
		}
		if(password.trim().equals("")){
			receiver.setPassword(null);
		}
		/**
		 * 禁止前台更新某些字段
		 */
		receiver.setCreator(null);
		
		receiver.setCreatetime(null);
		
		//更新
		adminsMapper.updateByPrimaryKeySelective(receiver);
		
	}


}
