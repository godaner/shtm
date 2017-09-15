package com.shtm.admins.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.admins.po.CustomAdmins;
import com.shtm.admins.service.AdminsServiceI;
import com.shtm.controller.BaseController;

/**
 * Title:UsersController
 * <p>
 * Description:用户controller
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
		
		setApplicationAttr("loginVerifyCode", vc.getCode());
		
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
	@RequestMapping("/login")
	public @ResponseBody CustomAdmins login(CustomAdmins po) {
		
		try{
			
			//验证验证码
			service.login(po);
			po.setLoginSuccess(true);
			po.setMsg("登录成功");
		}catch(Exception e){
			e.printStackTrace();
			po.setLoginSuccess(false);
			po.setMsg(e.getMessage());
		}
		return po;
	}
}
