package com.shtm.manage.controller;

import java.io.IOException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.groups.AdminsGroups.DeleteAdminGroups;
import com.shtm.manage.groups.AdminsGroups.InsertAdminGroups;
import com.shtm.manage.groups.AdminsGroups.SelectAdminGroups;
import com.shtm.manage.groups.AdminsGroups.UpdateAdminGroups;
import com.shtm.manage.po.AdminsLoginLogReceiver;
import com.shtm.manage.po.AdminsReceiver;
import com.shtm.manage.po.AdminsReplier;
import com.shtm.manage.service.AdminsServiceI;
import com.shtm.po.Admins;

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

		setApplicationAttr(FILED_LOGIN_VERIFYCODE, vc.getCode());

		vc.write(res.getOutputStream());

	}

	/**
	 * Title:login
	 * <p>
	 * Description:登录
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月15日 下午2:46:34
	 * @version 1.0
	 * @param po
	 * @return
	 */
	/*
	 * @RequestMapping("login") public @ResponseBody AdminsReplier
	 * login(AdminsReceiver receiver,AdminsLoginLogReceiver
	 * adminsLoginLogReceiver) { AdminsReplier replier = new AdminsReplier();
	 * try{
	 *//**
	 * 验证验证码
	 */
	/*
	 * String msg = "验证码错误"; //正确验证码 Object vc =
	 * getApplicationAttr(FILED_LOGIN_VERIFYCODE); eject(vc == null,msg);
	 * 
	 * //表单验证码 String formVc = po.getVerifyCode(); eject(formVc == null,msg);
	 * 
	 * eject(!vc.toString().toLowerCase().equals(formVc.toLowerCase()),
	 * "验证码错误");
	 * 
	 * //---start,模拟用户信息,用于测试 receiver.setUsername("123");
	 * receiver.setPassword("123");
	 * 
	 * //---end
	 *//**
	 * 执行业务
	 */
	/*
	 * Admins admin = service.login(receiver,adminsLoginLogReceiver);
	 * 
	 * //设置到session setSessionAttr(FILED_ONLINE_ADMIN, admin);
	 * 
	 * replier = (AdminsReplier) admin; replier.setResult(RESULT.TRUE);
	 * replier.setMsg("登录成功"); }catch(Exception e){ e.printStackTrace();
	 * replier.setResult(RESULT.FALSE); replier.setMsg(e.getMessage()); } return
	 * replier; }
	 */

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
			
			replier = service.login(receiver);
			
			replier.setMsg("登录成功");

			replier.setResult(RESULT.TRUE);
			
			setSessionAttr(FILED_ONLINE_ADMIN,replier);
			
			adminsLoginLogReceiver.setResult(ADMINS_LOGIN_LOG_RESULT.SUCCESS);
			
		} catch (IncorrectCredentialsException e0) {
			e0.printStackTrace();
			replier.setMsg("密码错误");
		} catch (UnknownAccountException e1) {
			e1.printStackTrace();
			replier.setMsg("账户错误");
		} catch (ExcessiveAttemptsException e2) {
			e2.printStackTrace();
			replier.setMsg("ExcessiveAttemptsException");
		} catch (LockedAccountException e3) {
			e3.printStackTrace();
			replier.setMsg("该账户已被冻结");
		} catch (AuthenticationException e4) {
			e4.printStackTrace();
			replier.setMsg("验证失败");
		} catch (Exception e5) {
			e5.printStackTrace();
			replier.setMsg(e5.getMessage());
		}
		try {
			//尝试插入登录记录,如果没有详情信息,mapper将不会插入,并抛出错误
			service.insertAdminsLoginLog(replier.getId(), adminsLoginLogReceiver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return replier;


	}

	/*@RequestMapping("loginSuccess")
	public @ResponseBody AdminsReplier loginSuccess() {
		AdminsReplier replier = new AdminsReplier();
		replier.setResult(RESULT.TRUE);
		replier.setMsg("登录成功");
		return replier;
	}*/

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
			/*
			 * removeSessionAttr(FILED_ONLINE_ADMIN);
			 * 
			 * getSession().invalidate();
			 */

			// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
			SecurityUtils.getSubject().logout();

			replier.setResult(RESULT.TRUE);

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
	@RequestMapping("/updateTheme")
	public @ResponseBody AdminsReplier updateTheme(
			@RequestBody AdminsReceiver receiver) throws IOException {

		AdminsReplier replier = new AdminsReplier();

		try {
			// 获取id
			Admins onlineAdmin = getSessionAttr(FILED_ONLINE_ADMIN);

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
			Admins ad = getSessionAttr(FILED_ONLINE_ADMIN);

			// 假設在綫用戶為1,123,123
			ad.setId("1");

			/**
			 * 执行业务
			 */
			service.insertAdmin(ad.getId(), receiver);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("添加成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}

		return replier;
	}

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

}
