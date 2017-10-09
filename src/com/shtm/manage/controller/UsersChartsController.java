package com.shtm.manage.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.po.UsersReceiver;
import com.shtm.manage.po.UsersReplier;
import com.shtm.manage.service.UsersServiceI;
import com.shtm.util.Static.RESULT;

/**
  * Title:
  * <p>
  * Description:用户图标controller;
  * <p>
  * @author Kor_Zhang
  * @date 2017年10月9日 下午5:25:21
  * @version 1.0
  */
@RequestMapping("/userscharts")
@Scope("prototype")
@Controller
public class UsersChartsController extends BaseController<UsersServiceI>{
	/**
	 * 
	 * Title:insertUser
	 * <p>
	 * Description:添加用户
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月21日 上午11:58:03
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	@RequiresAuthentication
	@RequiresPermissions("users:select")
	@RequestMapping("insertUser")
	public @ResponseBody UsersReplier insertUser(UsersReceiver receiver) throws Exception{
		
		
		UsersReplier replier = new UsersReplier();
		try {
			
			service.insertUser(receiver);
			
			replier.setMsg("添加用户成功");
			
			replier.setResult(RESULT.TRUE);
			
		} catch (Exception e) {
			e.printStackTrace();

			replier.setMsg("添加失败");
			
			replier.setResult(RESULT.FALSE);
		}
		return replier;
	}
}
