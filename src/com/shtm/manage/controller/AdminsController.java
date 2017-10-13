package com.shtm.manage.controller;

import java.io.IOException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.groups.AdminsGroups.CheckOnlineAdminPWGroups;
import com.shtm.manage.groups.AdminsGroups.DeleteAdminGroups;
import com.shtm.manage.groups.AdminsGroups.InsertAdminGroups;
import com.shtm.manage.groups.AdminsGroups.KickOutAdminGroups;
import com.shtm.manage.groups.AdminsGroups.SelectAdminGroups;
import com.shtm.manage.groups.AdminsGroups.SelectAdminRolesById;
import com.shtm.manage.groups.AdminsGroups.UpdateAdminGroups;
import com.shtm.manage.groups.AdminsGroups.UpdateAdminRolesGroups;
import com.shtm.manage.groups.AdminsGroups.UpdateAdminSelfGroups;
import com.shtm.manage.po.AdminsLoginLogReceiver;
import com.shtm.manage.po.AdminsLoginLogReplier;
import com.shtm.manage.po.AdminsReceiver;
import com.shtm.manage.po.AdminsReplier;
import com.shtm.manage.service.AdminsServiceI;
import com.shtm.po.Admins;
import com.shtm.util.ProjectUtil;

/**
 * Title:AdminsController
 * <p>
 * Description:管理员controller
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月15日 上午10:37:56
 * @version 1.0
 */
@RequestMapping("/admins")
@Scope("prototype")
@Controller
public class AdminsController extends BaseController<AdminsServiceI> {

	/**
	 * 
	 * Title:
	 * <p>
	 * Description:更新管理员的角色
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月1日 下午5:18:21
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequiresAuthentication
	@RequiresPermissions("admins:update")
	@RequestMapping("/updateAdminRoles")
	public @ResponseBody AdminsReplier updateAdminRoles(
			@Validated(value = { UpdateAdminRolesGroups.class }) @RequestBody AdminsReceiver receiver,
			BindingResult result) throws Exception {
		AdminsReplier replier = new AdminsReplier();
		try {
			getError(result);

			service.updateAdminRoles(receiver.getId(),receiver.getRolesIds());

			replier.setResult(RESULT.TRUE);
			replier.setMsg("更新角色成功");
		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}
		return replier;
	}
	
	/**
	 * 
	 * Title:
	 * <p>
	 * Description:获取指定id的admin的roles(所有的roles也会被全部查询出来,但是admin拥有的roles有特殊标记);
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月1日 下午5:15:09
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequiresAuthentication
	@RequiresPermissions("admins:select")
	@RequestMapping("/selectAdminRolesById")
	public @ResponseBody AdminsReplier selectAdminRolesById(
			@Validated(value = { SelectAdminRolesById.class }) AdminsReceiver receiver,
			BindingResult result) throws Exception {
		AdminsReplier replier = new AdminsReplier();
		try {
			getError(result);

			replier = service.selectAdminRolesById(receiver);

			replier.setResult(RESULT.TRUE);
			replier.setMsg("获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}
		return replier;
	}
	
	/**
	 * Title:selectAdmin
	 * <p>
	 * Description:获取指定id的admin
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月23日 上午10:57:37
	 * @version 1.0
	 * @param receiver
	 * @return
	 */
	@RequiresAuthentication
	@RequiresPermissions("admins:select")
	@RequestMapping("/selectAdmin")
	public @ResponseBody AdminsReplier selectAdmin(
			@Validated(value = { SelectAdminGroups.class }) AdminsReceiver receiver,
			BindingResult result) throws Exception {
		AdminsReplier replier = new AdminsReplier();
		try {
			getError(result);

			replier = service.selectAdmin(receiver.getId());

			replier.setResult(RESULT.TRUE);
			replier.setMsg("获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}
		return replier;
	}

	/**
	 * Title:verifyCode
	 * <p>
	 * Description:返回验证码
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月15日 下午2:38:37
	 * @version 1.0
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/verifyCode")
	public void verifyCode() throws Exception {

		vc.createCode();

		setSessionAttr(FILED_LOGIN_VERIFYCODE, vc.getCode());

		vc.write(res.getOutputStream());

	}

	

	/**
	 * Title:login
	 * <p>
	 * Description:当用户主动请求登录和验证shiro的authc请求时被调用;
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月29日 下午1:37:57
	 * @version 1.0
	 * @return
	 */
	@RequestMapping("/login")
	public @ResponseBody AdminsReplier login(AdminsReceiver receiver,
			AdminsLoginLogReceiver adminsLoginLogReceiver) {
		AdminsReplier replier = new AdminsReplier();
		//添加rows,防止datagrid出现错误
		replier.setRows(getList());
		//默认未登录
		replier.setResult(RESULT.UNONLINE);
		//默认登录记录为失败
		adminsLoginLogReceiver.setResult(ADMINS_LOGIN_LOG_RESULT.FAILURE);
		try {
			
			
			/*开发阶段的免登录模式*/
			if(receiver == null || 
					receiver.getUsername() == null||
							receiver.getUsername().equals("") || 
					receiver.getPassword() == null ||
							receiver.getPassword().equals("")){
				receiver.setUsername("a1");
				receiver.setPassword("123");
			}
			
			replier = service.login(receiver);
			
			replier.setMsg("登录成功");

			replier.setResult(RESULT.TRUE);
			
			//设置登录记录的登录结果
			adminsLoginLogReceiver.setResult(ADMINS_LOGIN_LOG_RESULT.SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			replier.setMsg(e.getMessage());
		}
		try {
			//尝试插入登录记录,如果没有详情信息,mapper将不会插入,并抛出错误
			AdminsLoginLogReplier adminsLoginLogReplier = service.insertAdminsLoginLog(replier.getId(), adminsLoginLogReceiver);
			//返回处理过后的登陆记录
			adminsLoginLogReplier.setAdminName(replier.getUsername());
			replier.setAdminsLoginLogReplier(adminsLoginLogReplier);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("insertAdminsLoginLog插入錯誤!!!!!!!!!!!!!!!!!!!!!!!");
		}
		//返回登陆相关信息
		return replier;


	}


	/**
	 * 
	 * Title:logout
	 * <p>
	 * Description:注销
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月16日 上午9:19:53
	 * @version 1.0
	 * @throws IOException
	 */
	@RequestMapping("/logout")
	public @ResponseBody AdminsReplier logout() throws IOException {

		AdminsReplier replier = new AdminsReplier();
		try {
			
			service.logout();
			
			
			replier.setResult(RESULT.UNONLINE);

			replier.setMsg("注销成功");
		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);

			replier.setMsg("注销失败");
		}

		return replier;
	}

	/**
	 * Title:
	 * <p>
	 * Description:更新主题
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月16日 下午4:28:49
	 * @version 1.0
	 * @return
	 * @throws IOException
	 */
	@RequiresAuthentication
	@RequiresPermissions("admins:update")
	@RequestMapping("/updateTheme")
	public @ResponseBody AdminsReplier updateTheme(
			@RequestBody AdminsReceiver receiver) throws IOException {

		AdminsReplier replier = new AdminsReplier();

		try {
			// 获取id
			/*Admins onlineAdmin = getSessionAttr(FILED_ONLINE_ADMIN);*/
			
			Admins onlineAdmin = getOnlineAdmin();

			receiver.setId(onlineAdmin.getId());

			// 更新
			service.updateTheme(receiver);

			// 更新session

			onlineAdmin.setTheme(receiver.getTheme());

			setSessionAttr(FILED_ONLINE_ADMIN, onlineAdmin);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("主题同步成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg("主题同步失败");
		}

		return replier;
	}

	/**
	 * Title:selectAdminsDatagrid
	 * <p>
	 * Description:
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午1:52:10
	 * @version 1.0
	 * @param receiver
	 * @return
	 */
	@RequiresAuthentication
	@RequiresPermissions("admins:select")
	@RequestMapping("/selectAdminsDatagrid")
	public @ResponseBody AdminsReplier selectAdminsDatagrid(
			AdminsReceiver receiver) throws Exception {

		AdminsReplier replier = new AdminsReplier();

		try {

			replier = service.selectAdminsDatagrid(receiver);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("获取列表成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg("获取列表失败");
		}

		return replier;

	}

	/**
	 * Title:insertAdmin
	 * <p>
	 * Description:添加一个admin
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午4:18:56
	 * @version 1.0
	 * @param receiver
	 * @return
	 */
	@RequiresAuthentication
	@RequiresPermissions("admins:insert")
	@RequestMapping("/insertAdmin")
	public @ResponseBody AdminsReplier insertAdmin(
			@Validated(value = { InsertAdminGroups.class }) AdminsReceiver receiver,
			BindingResult result) {
		AdminsReplier replier = new AdminsReplier();

		try {

			// 獲取验证信息
			getError(result);

			/**
			 * 获取在线的管理员
			 */
			
			Admins ad = getOnlineAdmin();
			/*Admins ad = getSessionAttr(FILED_ONLINE_ADMIN);*/

			// 假設在綫用戶為1,123,123
			/*ad.setId("1");*/

			/**
			 * 执行业务
			 */
			replier = service.insertAdmin(ad.getId(), receiver);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("添加成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}

		return replier;
	}

	@RequiresAuthentication
	@RequiresPermissions("admins:update")
	@RequestMapping("/updateAdmin")
	public @ResponseBody AdminsReplier updateAdmin(
			@Validated(value = { UpdateAdminGroups.class }) AdminsReceiver receiver,
			BindingResult result) {
		AdminsReplier replier = new AdminsReplier();

		try {

			// 獲取验证信息
			getError(result);

			/**
			 * 执行业务
			 */
			service.updateAdmin(receiver);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("修改成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}

		return replier;
	}

	/**
	 * Title:deleteAdmin
	 * <p>
	 * Description:刪除管理員
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月23日 上午8:22:14
	 * @version 1.0
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequiresAuthentication
	@RequiresPermissions("admins:delete")
	@RequestMapping("/deleteAdmin")
	public @ResponseBody AdminsReplier deleteAdmin(
			@Validated(value = { DeleteAdminGroups.class }) AdminsReceiver receiver,
			BindingResult result) throws Exception {
		AdminsReplier replier = new AdminsReplier();
		try {

			getError(result);

			service.deleteAdmin(receiver);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("刪除成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}

		return replier;
	}
	/**
	 * Title:getOnlineAdmin
	 * <p>
	 * Description:獲取在綫管理員
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月23日 上午8:22:14
	 * @version 1.0
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getOnlineAdmin")
	public @ResponseBody AdminsReplier getOnlineAd(){
		
		
				
		AdminsReplier replier = new AdminsReplier();
		try {

			eject(ProjectUtil.getOnlineAdmin() == null);

			replier = ProjectUtil.getOnlineAdmin();
			
			replier.setResult(RESULT.TRUE);

			replier.setMsg("");
			

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.UNONLINE);
			replier.setMsg(e.getMessage());
		}

		return replier;
	}
	
	
	
	/**
	 * 
	 * Title:
	 * <p>
	 * Description:检测在线用户的密码
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月6日 上午10:42:54
	 * @version 1.0
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping("/checkOnlineAdminPW")
	public @ResponseBody AdminsReplier checkOnlineAdminPW(@Validated(value={CheckOnlineAdminPWGroups.class}) AdminsReceiver receiver,
			BindingResult result){
				
		AdminsReplier replier = new AdminsReplier();
		try {
			
			getError(result);

			Admins adb = service.selectAdminByPK(ProjectUtil.getOnlineAdmin().getId());
			
			eject(adb == null, "账户不存在");
			
			String md5pw = md5(receiver.getPassword() + adb.getSalt());
			
			eject(!md5pw.equals(adb.getPassword()), "密码错误");
			
			replier.setResult(RESULT.TRUE);
			

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}
		return replier;
	}
	/**
	 * Title:
	 * <p>
	 * Description:冻结当前账户
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月6日 上午11:13:07
	 * @version 1.0
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping("/frozen")
	public @ResponseBody AdminsReplier frozen(){
				
		AdminsReplier replier = new AdminsReplier();
		try {
			
			service.frozen(getOnlineAdmin().getId());
			
			replier.setMsg("冻结成功");
			
			replier.setResult(RESULT.UNONLINE);
			
			//销毁session,登出

			SecurityUtils.getSubject().getSession().removeAttribute(FILED_ONLINE_ADMIN);
			
			// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
			SecurityUtils.getSubject().logout();
			

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}
		return replier;
	}
	
	/**
	 * 
	 * Title:
	 * <p>
	 * Description:更新当前用户的信息;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月6日 下午3:42:58
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping("/updateAdminSelf")
	public @ResponseBody AdminsReplier updateAdminSelf(@Validated(value={UpdateAdminSelfGroups.class}) AdminsReceiver receiver,
			BindingResult result){
				
		AdminsReplier replier = new AdminsReplier();
		try {
			
			AdminsReplier onlineAdmin = getOnlineAdmin();
			
			receiver.setId(onlineAdmin.getId());
			
			replier = service.updateAdminSelf(receiver);
			
			//更新登陸記錄中的username,前臺將發送該信息給websocket更新websocket的在綫管理員列表信息;
			onlineAdmin.getAdminsLoginLogReplier().setAdminName(replier.getUsername());
			replier.setAdminsLoginLogReplier(onlineAdmin.getAdminsLoginLogReplier());
			
			//更新session的在綫对象,用戶前臺的獲取
			setOnlineAdmin(replier);
			
			replier.setMsg("同步成功");
			
			replier.setResult(RESULT.TRUE);
			

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}
		return replier;
	}
	
	/**
	 * 
	 * Title:
	 * <p>
	 * Description:更新当前用户的信息;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月6日 下午3:42:58
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 */
	@RequiresAuthentication
	@RequiresPermissions("admins:kickOut")
	@RequestMapping("/kickOutAdmin")
	public @ResponseBody AdminsReplier kickOutAdmin(@Validated(value={KickOutAdminGroups.class}) AdminsReceiver receiver,
			BindingResult result){
				
		AdminsReplier replier = new AdminsReplier();
		try {
			
			
			service.kickOutAdmin(receiver.getId());
			replier.setMsg("踢出成功");
			replier.setResult(RESULT.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}
		return replier;
	}
	
	
}
