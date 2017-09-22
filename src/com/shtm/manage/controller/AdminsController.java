package com.shtm.manage.controller;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
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
 * @author Kor_Zhang
 * @date 2017年9月15日 上午10:37:56
 * @version 1.0
 */
@RequestMapping("/admins")
@Scope("prototype")
@Controller
public class AdminsController extends BaseController<AdminsServiceI>{
	
	
	/**
	 * Title:verifyCode
	 * <p>
	 * Description:返回验证码
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月15日 下午2:38:37
	 * @version 1.0
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/verifyCode")
	public void verifyCode() throws Exception{
		
		vc.createCode();
		
		setApplicationAttr(FILED_LOGIN_VERIFYCODE, vc.getCode());
		
		vc.write(res.getOutputStream());
		
	}
	
	/**
	 * Title:login
	 * <p>
	 * Description:登录
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月15日 下午2:46:34
	 * @version 1.0
	 * @param po
	 * @return
	 */
	@RequestMapping("login")
	public @ResponseBody AdminsReplier login(AdminsReceiver receiver,AdminsLoginLogReceiver adminsLoginLogReceiver) {
		AdminsReplier replier = new AdminsReplier();
		try{
			
			/**
			 * 验证验证码
			 *//*
			String msg = "验证码错误";
			//正确验证码
			Object vc = getApplicationAttr(FILED_LOGIN_VERIFYCODE);
			eject(vc == null,msg);
			
			//表单验证码
			String formVc = po.getVerifyCode();
			eject(formVc == null,msg);
			
			eject(!vc.toString().toLowerCase().equals(formVc.toLowerCase()),
					"验证码错误");*/
			
			//---start,模拟用户信息,用于测试
			receiver.setUsername("123");
			receiver.setPassword("123");
			
			//---end
			
			/**
			 * 执行业务
			 */
			Admins admin = service.login(receiver,adminsLoginLogReceiver);
			
			//设置到session
			setSessionAttr(FILED_ONLINE_ADMIN, admin);
			
			replier = (AdminsReplier) admin;
			replier.setResult(RESULT.TRUE);
			replier.setMsg("登录成功");
		}catch(Exception e){
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}
		return replier;
	}
	
	
	/**
	 * 
	 * Title:logout
	 * <p>
	 * Description:注销
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月16日 上午9:19:53
	 * @version 1.0
	 * @throws IOException 
	 */
	@RequestMapping("/logout")
	public @ResponseBody AdminsReplier logout() throws IOException{
		
		AdminsReplier replier = new AdminsReplier();
		try {
			removeSessionAttr(FILED_ONLINE_ADMIN);
			
			getSession().invalidate();

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
	 * @author Kor_Zhang
	 * @date 2017年9月16日 下午4:28:49
	 * @version 1.0
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/updateTheme")
	public @ResponseBody AdminsReplier updateTheme(@RequestBody AdminsReceiver receiver) throws IOException{
		
		AdminsReplier replier = new AdminsReplier();
		
		try {
			//获取id
			Admins onlineAdmin = getSessionAttr(FILED_ONLINE_ADMIN);
			
			receiver.setId(onlineAdmin.getId());
			
			//更新
			service.updateTheme(receiver);
			
			//更新session
			
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
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午1:52:10
	 * @version 1.0
	 * @param receiver
	 * @return
	 */
	public @ResponseBody AdminsReplier selectAdminsDatagrid(AdminsReceiver receiver){
		
		AdminsReplier replier = new AdminsReplier();
		
		try {
			
			service.selectAdminsDatagrid(receiver);
			
			replier.setResult(RESULT.TRUE);
			
			replier.setMsg("获取列表成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg("获取列表失败");
		}
		
		
		return replier;
		
		
	}
	
	
}
