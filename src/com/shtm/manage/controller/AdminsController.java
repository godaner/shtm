package com.shtm.manage.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.po.CustomAdmins;
import com.shtm.manage.service.AdminsServiceI;

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
	public @ResponseBody CustomAdmins login(@RequestBody CustomAdmins po) {
		
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
			po.setUsername("123");
			po.setPassword("123");
			
			//---end
			
			/**
			 * 执行业务
			 */
			po = service.login(po);
			
			//设置到session
			setSessionAttr(FILED_ONLINE_ADMIN, po);
			
			po.setResult(RESULT.TRUE);
			po.setMsg("登录成功");
		}catch(Exception e){
			e.printStackTrace();
			po.setResult(RESULT.FALSE);
			po.setMsg(e.getMessage());
		}
		return po;
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
	public @ResponseBody CustomAdmins logout() throws IOException{
		
		CustomAdmins po = new CustomAdmins();
		try {
			removeSessionAttr(FILED_ONLINE_ADMIN);
			
			getSession().invalidate();

			po.setResult(RESULT.TRUE);
			
			po.setMsg("注销成功");
		} catch (Exception e) {
			e.printStackTrace();
			po.setResult(RESULT.FALSE);
			
			po.setMsg("注销失败");
		}
		
		
		return po;
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
	public @ResponseBody CustomAdmins updateTheme(@RequestBody CustomAdmins po) throws IOException{
		
		try {
			//获取id
			CustomAdmins onlineAdmin = getSessionAttr(FILED_ONLINE_ADMIN);
			
			po.setId(onlineAdmin.getId());
			
			//更新
			service.updateTheme(po);
			
			//更新session
			
			onlineAdmin.setTheme(po.getTheme());
			
			setSessionAttr(FILED_ONLINE_ADMIN, onlineAdmin);
			
			po.setResult(RESULT.TRUE);
			
			po.setMsg("主题同步成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			po.setResult(RESULT.FALSE);
			po.setMsg("主题同步失败");
		}
		
		
		return po;
	}
}
