package com.shtm.manage.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomAdminsMapper;
import com.shtm.manage.po.AdminsLoginLogReceiver;
import com.shtm.manage.po.AdminsLoginLogReplier;
import com.shtm.manage.po.AdminsReceiver;
import com.shtm.manage.po.AdminsReplier;
import com.shtm.manage.po.RolesReplier;
import com.shtm.manage.realm.JDBCRealm;
import com.shtm.manage.service.AdminsServiceI;
import com.shtm.manage.websocket.OnlineAdminsWS;
import com.shtm.mapper.AdminsLoginLogMapper;
import com.shtm.mapper.AdminsMapper;
import com.shtm.mapper.AdminsRolesMapper;
import com.shtm.mapper.RolesMapper;
import com.shtm.po.Admins;
import com.shtm.po.AdminsExample;
import com.shtm.po.AdminsExample.Criteria;
import com.shtm.po.AdminsRoles;
import com.shtm.po.AdminsRolesExample;
import com.shtm.po.Permissions;
import com.shtm.po.Roles;
import com.shtm.service.impl.BaseService;
import com.shtm.util.ProjectUtil;

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
	
	@Autowired
	private RolesMapper rolesMapper;

	@Autowired
	private AdminsRolesMapper adminsRolesMapper;
	
	@Autowired
	private JDBCRealm jdbcRealm;
	
	@Autowired
	private SessionDAO sessionDAO;
	/**
	 * Title:
	 * <p>
	 * Description:登录
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 下午8:23:36
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	@Override
	public AdminsReplier login(AdminsReceiver receiver) throws Exception {
		
		
		
		// 是否正确提交了用户信息
		eject(receiver.getUsername() == null
				|| receiver.getUsername().trim().equals(""), "账户不能为空");
		eject(receiver.getPassword() == null
				|| receiver.getPassword().toString().trim().equals(""), "密码不能为空");
		
		//查询admins
		Admins dbAd = customAdminsMapper.selectAdminByUsername(receiver.getUsername());
				
		// 用户不存在
		eject(dbAd == null, "账户不存在");

		// 用户被冻结
		eject(dbAd.getStatus() == ADMINS_STATUS.FROZEN, "账户被冻结");
		//验证密码
		String formPW = md5( receiver.getPassword() + dbAd.getSalt());
		eject(!dbAd.getPassword().equals(formPW), "密码错误");
		
		//传入realm的数据(原始數據)
		UsernamePasswordToken token = new UsernamePasswordToken(
				receiver.getUsername(), dbAd.getPassword());
		Subject subject = SecurityUtils.getSubject();
		// 调用shiro登录
		subject.login(token);
		
		AdminsReplier replier = new AdminsReplier();
		
		//不显示字段
		dbAd.setPassword(null);
		
		BeanUtils.copyProperties(dbAd, replier);

		//强制已登录的该用户下线
		destroyShiroSession(dbAd.getId());
		
		//设置在线用户
		setOnlineAdmin(replier);
		
		return replier;
		
	}

	@Override
	public void updateTheme(AdminsReceiver receiver) throws Exception {
		
		Admins newAdmin = new Admins();
		
		newAdmin.setId(receiver.getId());
		
		newAdmin.setTheme(receiver.getTheme());
		
		adminsMapper.updateByPrimaryKeySelective(newAdmin);
		
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
	public AdminsReplier insertAdmin(String id,AdminsReceiver receiver) throws Exception {

		/**
		 * 小寫郵箱和用戶名
		 */
		receiver.setEmail(receiver.getEmail());
		receiver.setUsername(receiver.getUsername());
		
		
		/**
		 * 验证名称是否存在
		 */
		
		Admins dbA = customAdminsMapper.selectAdminByUsername(receiver.getUsername());
		eject(dbA != null, "该用户名已存在");
		/**
		 * 设置属性
		 */
		
		receiver.setCreatetime(timestamp());
		
		receiver.setCreator(id);
		
		receiver.setId(uuid());
		
		receiver.setSalt(receiver.getUsername());
		
		receiver.setStaticc(ADMINS_STATIC.NO);
		
		//md5加密
		receiver.setPassword(md5(receiver.getPassword()+receiver.getSalt()));
		
		adminsMapper.insert(receiver);
		
		AdminsReplier replier = new AdminsReplier();
		
		BeanUtils.copyProperties(receiver, replier);
		
		return replier;
	}

	@Override
	public void deleteAdmin(AdminsReceiver receiver) throws Exception {
		
		Admins dbA = adminsMapper.selectByPrimaryKey(receiver.getId());
		
		
		if(dbA == null || dbA.getStatus() == ADMINS_STATUS.DELETE){
			//如果已被刪除
			return ;
		}
		
		eject(isStaticAdmin(dbA),"不能删除内置管理员");
		
		
		//標志位刪除
		Admins newAd = new Admins();
		
		newAd.setId(receiver.getId());
		
		newAd.setUsername(uuid()+"_"+dbA.getUsername());
		
		newAd.setStatus(ADMINS_STATUS.DELETE);
		
		adminsMapper.updateByPrimaryKeySelective(newAd);
		
	}

	@Override
	public void updateAdmin(AdminsReceiver receiver) throws Exception {
		//不能更新自己
		eject(receiver.getId().equals(getOnlineAdmin().getId()),"不能更新自己");
		
		Admins dbA = adminsMapper.selectByPrimaryKey(receiver.getId());
		
		
		//判断管理员是否存在
		eject(dbA == null ||
				dbA.getStatus() == ADMINS_STATUS.DELETE, "管理员不存在");
		
		eject(isStaticAdmin(dbA),"不能更新内置管理员的信息");
		
		
		//更新name
		if(!dbA.getUsername().equals(receiver.getUsername())){
			/**
			 * 判断name是否存在
			 */
			AdminsExample example = new AdminsExample();
			com.shtm.po.AdminsExample.Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(receiver.getUsername());
			List<Admins> list = adminsMapper.selectByExample(example);
			
			eject(list.size() >= 1, "该名称已存在");
		}else{
			receiver.setUsername(null);
		}
		/**
		 * 判断password是否需要更新
		 */
		String password = receiver.getPassword();
		if (password != null && !password.isEmpty()) {
			//如果有更新情况,加密密码
			receiver.setPassword(md5(password + dbA.getSalt()));
		}
		if(password.trim().equals("")){
			receiver.setPassword(null);
		}
		/**
		 * 禁止前台更新某些字段
		 */
		receiver.setCreator(null);
		
		receiver.setCreatetime(null);
		
		receiver.setStaticc(null);
		
//		receiver.setUsername(null);
		
		
		
		//更新
		adminsMapper.updateByPrimaryKeySelective(receiver);
		
		
		dbA = adminsMapper.selectByPrimaryKey(receiver.getId());
		
		AdminsReplier replier = new AdminsReplier();
		
		BeanUtils.copyProperties(dbA, replier);
		
		//通知shiro的session和websoket更新
//		notifyShiroSessionAndWSUpdate(replier.getId(),replier);
		
		//迫使其掉线
		notifyWSLogout(replier.getId());
		
		//判斷是否凍結
		if(receiver.getStatus() == ADMINS_STATUS.FROZEN){
			notifyWSLogout(receiver.getId());
		}
		
	}

	@Override
	public AdminsReplier selectAdmin(String id) throws Exception {
		
		AdminsReplier replier = customAdminsMapper.selectAdmin(id);
		
		//判读用户是否存在
		eject(replier == null, "该用户已不存在");
		
		//屏蔽属性
		replier.setPassword("");
		
		replier.setSalt("");
		
		return replier;
	}

	@Override
	public Admins selectAdminByUsername(String username) throws Exception {
		//查询admins
		return customAdminsMapper.selectAdminByUsername(username);
				
	}

	@Override
	public AdminsLoginLogReplier insertAdminsLoginLog(String adminId,
			AdminsLoginLogReceiver receiver){
		//插入登录记录
		//设置参数
		receiver.setTime(new Date());
		receiver.setId(uuid());
		receiver.setLoginAdmin(adminId);
		try{
			adminsLoginLogMapper.insert(receiver);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		AdminsLoginLogReplier replier = new AdminsLoginLogReplier();
		
		BeanUtils.copyProperties(receiver, replier);
		
		return replier;
		
	}

	@Override
	public List<Roles> selectRoles(String id) throws Exception {
		
		return customAdminsMapper.selectRoles(id);
	}

	@Override
	public List<Permissions> selectPermissions(String id) throws Exception {
		return customAdminsMapper.selectPermissions(id);
	}


	@Override
	public AdminsReplier selectAdminRolesById(AdminsReceiver receiver) throws Exception {
		
		AdminsReplier replier = new AdminsReplier();
		
		List<RolesReplier> rows = customAdminsMapper.selectAdminRolesById(receiver);
		
		replier.setRows(rows);
		
		return replier;
	}

	@Override
	public void updateAdminRoles(String id, String[] rolesIds)
			throws Exception {
		
		//判断管理员是否存在
		Admins dbA = adminsMapper.selectByPrimaryKey(id);
		eject(dbA == null ||
				dbA.getStatus() == ADMINS_STATUS.DELETE, "管理员不存在");
		//不能更新自己
//		Admins onlineAdmins = getOnlineAdmin();
//		eject(dbA.getUsername().equals(onlineAdmins.getUsername()), "不能更新自己的角色");

		eject(isStaticAdmin(dbA),"不能更新内置管理员的角色");
		
		//删除admin的旧角色
		
		AdminsRolesExample example = new AdminsRolesExample();
		
		com.shtm.po.AdminsRolesExample.Criteria criteria = example.createCriteria();
		
		criteria.andAdminEqualTo(id);
		
		adminsRolesMapper.deleteByExample(example);
		
		//为admin添加新角色
		
		for (int i = 0; i < rolesIds.length; i++) {
			String rolesId = rolesIds[i];
			
			//插入AdminsRoles
			AdminsRoles ar = new AdminsRoles();
			
			ar.setAdmin(id);
			
			ar.setGranttime(timestamp());
			
			ar.setId(uuid());
			
			ar.setRole(rolesId);
			
			adminsRolesMapper.insert(ar);
		}
		
		//刷新权限
//		jdbcRealm.clearCached();
//		reloadAuthorizing(new JDBCRealm(),dbA);
		//使指定的session离线
//		destroyShiroSession(dbA.getUsername());
		
	}
	/**
	 * Title:
	 * <p>
	 * Description:判断数据库的某个管理员为内置管理员;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月3日 上午11:51:29
	 * @version 1.0
	 * @param dbAdmin
	 * @return
	 */
	private boolean isStaticAdmin(Admins dbAdmin){
		return dbAdmin.getStaticc() == ADMINS_STATIC.YES;
	}
	
	/**
	 * Title:
	 * <p>
	 * Description:销毁指定username的shiro的session;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月3日 下午4:39:40
	 * @version 1.0
	 * @param adminId
	 */
	private void destroyShiroSession(String adminId){
		/**
		 * 销毁shiro的session
		 */
		Collection<Session> sessions = sessionDAO.getActiveSessions();

		for (Session session : sessions) {
			AdminsReplier shiroAdmins = (AdminsReplier) session.getAttribute(FILED_ONLINE_ADMIN);
			if(shiroAdmins != null ){
				if (adminId.equals(shiroAdmins.getId())) {
//					session.setTimeout(0);
					/**
					 * 利用websocket通知客户端某个用户已下线
					 */
					notifyWSLogout(shiroAdmins.getId());
					//移除session的在线用户信息
					session.removeAttribute(FILED_ONLINE_ADMIN);
//					session.stop(); 
				}
			}
			
		}
		
		
	}
	/**
	 * 
	 * Title:
	 * <p>
	 * Description:通知websocket某个id的用户已下线;
	 * <br/>
	 * websocket將向該ws發送離綫信息,並移除該ws相關信息;且通知其他ws該ws已離綫;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月6日 下午9:33:29
	 * @version 1.0
	 * @param adminId
	 */
	public static void notifyWSLogout(String adminId){
        
        OnlineAdminsWS stopWS  = OnlineAdminsWS.getWS(adminId);
        
        //通知离线的websoket它的session已离线,它可以做一些善后操作(发送一个空json数组),例如客戶端要調用ws.close()
        OnlineAdminsWS.sendSpecialMsgToWS(stopWS,ProjectUtil.getList(),RESULT.UNONLINE);
        
        //获取并且移除离线的websocket客户端和admin信息
        OnlineAdminsWS.removeWSAndAdmin(adminId, true);
        
    	//通知未离线的websocket最新的登陆记录
        OnlineAdminsWS.broadcastOnlineAdminWS();
        
        
	}
	/**
	 * Title:
	 * <p>
	 * Description:通知websoket和session某用户更新了信息;要求发送广播;<br/>
	 * 注意:只是更新了admins的基本信息,并没有更新其登录记录;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月7日 下午2:27:46
	 * @version 1.0
	 * @param adminId
	 * @param newAdmin
	 *//*
	public static void notifyShiroSessionAndWSUpdate(String adminId,AdminsReplier newAdmin){
       *//**
        * 保持登陸記錄
        *//*
        AdminsReplier wsAd = OnlineAdminsWS.getAdmin(adminId);
        
        if(wsAd == null){
        	return ;
        }
        //設置loginlog的參數
        
        AdminsLoginLogReplier adminsLoginLogReplier = wsAd.getAdminsLoginLogReplier();
        
        adminsLoginLogReplier.setAdminName(newAdmin.getUsername());
        
        newAdmin.setAdminsLoginLogReplier(adminsLoginLogReplier);
        
        
        
        *//**
         * 更新ws中的信息
         *//*
		
        OnlineAdminsWS.updateAdmin(adminId, newAdmin);
        
        //向容器中所有的ws广播更新
        OnlineAdminsWS.broadcastOnlineAdminWS();
	}
	*/
	
	@Override
	public Admins selectAdminByPK(String id) throws Exception {
		
		return adminsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void frozen(String id) throws Exception {
		
		Admins ad = new Admins();
		
		ad.setId(id);
		
//		eject(ad.getStaticc().equals(ADMINS_STATIC.YES), "内置管理员不能被冻结");
		
		ad.setStatus(ADMINS_STATUS.FROZEN);
		
		adminsMapper.updateByPrimaryKeySelective(ad);
		
		notifyWSLogout(id);
		
	}

	@Override
	public AdminsReplier updateAdminSelf(AdminsReceiver receiver) throws Exception {
		Admins dbA = adminsMapper.selectByPrimaryKey(receiver.getId());
		
		//判断管理员是否存在
		eject(dbA == null ||
				dbA.getStatus() == ADMINS_STATUS.DELETE, "管理员不存在");
		
		
		//更新name
		if(!dbA.getUsername().equals(receiver.getUsername())){
			/**
			 * 判断name是否存在
			 */
			AdminsExample example = new AdminsExample();
			com.shtm.po.AdminsExample.Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(receiver.getUsername());
			List<Admins> list = adminsMapper.selectByExample(example);
			
			eject(list.size() >= 1, "该名称已存在");
		}else{
			receiver.setUsername(null);
		}
		/**
		 * 判断password是否需要更新
		 */
		String password = receiver.getPassword();
		if (password != null && !password.isEmpty()) {
			//如果有更新情况,加密密码
			receiver.setPassword(md5(password + dbA.getSalt()));
		}
		if(password.trim().equals("")){
			receiver.setPassword(null);
		}
		
		
		/**
		 * 禁止更新的字段
		 */
		receiver.setCreatetime(null);
		receiver.setCreator(null);
		receiver.setSalt(null);
		receiver.setStatus(null);
		
		adminsMapper.updateByPrimaryKeySelective(receiver);
		
		//返回數據
		AdminsReplier replier = new AdminsReplier();
		
		dbA = adminsMapper.selectByPrimaryKey(receiver.getId());
		//屏蔽字段
		dbA.setPassword("");
		
		
		BeanUtils.copyProperties(dbA, replier);
		
		return replier;
	}

	@Override
	public void kickOutAdmin(String id) throws Exception {

		Admins dbAd = adminsMapper.selectByPrimaryKey(id);

		// 判断管理员是否存在
		eject(dbAd == null || dbAd.getStatus() == ADMINS_STATUS.DELETE,
				"管理员不存在");

		destroyShiroSession(dbAd.getId());
	}

	@Override
	public void logout() throws Exception {
		
		if(getOnlineAdmin() == null){
			return;
		}
		
		String adminId = getOnlineAdmin().getId();

		//通知ws某個用戶已離綫;
		notifyWSLogout(adminId);
		
		SecurityUtils.getSubject().getSession().removeAttribute(FILED_ONLINE_ADMIN);
		// 登出,將使session過期
		SecurityUtils.getSubject().logout();
		
		
	}
	
	
	
}
