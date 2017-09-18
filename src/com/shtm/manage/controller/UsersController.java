package com.shtm.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.po.CustomUsers;
import com.shtm.manage.service.UsersServiceI;

/**
 * Title:UsersController
 * <p>
 * Description:用户controller
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月15日 上午10:37:56
 * @version 1.0
 */
@RequestMapping("/users")
@Controller
public class UsersController extends BaseController<UsersServiceI>{
	/**
	 * Title:selectUsers
	 * <p>
	 * Description:查询用户
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月18日 上午8:53:58
	 * @version 1.0
	 * @param po
	 * @return
	 */
	@RequestMapping("/selectUsersDatagrid")
	public @ResponseBody CustomUsers selectUsersDatagrid(CustomUsers po){
		CustomUsers retJson = new CustomUsers();
		try {
			retJson = service.selectUsersDatagrid(po);
			
			retJson.setMsg("获取列表成功");
			
			retJson.setResult(RESULT.TRUE);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			retJson.setMsg(e.getMessage());
			
			retJson.setResult(RESULT.FALSE);
		}
		return retJson;
	}
}
