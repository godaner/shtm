package com.shtm.manage.controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.po.PermissionsReplier;
import com.shtm.manage.service.PermissionsServiceI;

/**
 * Title:PermissionsController
 * <p>
 * Description:权限控制器
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月17日 下午1:06:20
 * @version 1.0
 */
@RequestMapping("/permissions")
@Scope("prototype")
@Controller
public class PermissionsController extends BaseController<PermissionsServiceI> {
	
	/**
	 * Title:selectPermissionsByPid
	 * <p>
	 * Description:根据pid获取权限
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月17日 下午1:14:12
	 * @version 1.0
	 * @return
	 */
	@RequestMapping("/selectPermissionsByPid")
	public @ResponseBody PermissionsReplier selectPermissionsByPid(String id){
		
		PermissionsReplier per = new PermissionsReplier();
		
		try {
			
			List<PermissionsReplier> pers = service.selectPermissionsByPid(id);
			
			per.setDatagrid(pers);
			
			per.setMsg("获取权限成功");
			per.setResult(RESULT.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			per.setMsg(e.getMessage());
			per.setResult(RESULT.FALSE);
		}
		
		return per;
	}
}

