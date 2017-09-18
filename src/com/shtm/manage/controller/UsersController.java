package com.shtm.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.po.UsersReceiver;
import com.shtm.manage.po.UsersReplier;
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
	 * Title:
	 * <p>
	 * Description:分页查询用户
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月18日 上午11:28:19
	 * @version 1.0
	 * @param rows	一页的行数
	 * @param page	第几页
	 * @return
	 */
	@RequestMapping("/selectUsersDatagrid")
	public @ResponseBody UsersReplier selectUsersDatagrid(UsersReceiver receiver){
		UsersReplier replier = new UsersReplier();
		try {
			replier = service.selectUsersDatagrid(receiver);
			
			replier.setMsg("获取列表成功");
			
			replier.setResult(RESULT.TRUE);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			replier.setMsg(e.getMessage());
			
			replier.setResult(RESULT.FALSE);
		}
		return replier;
	}
}
