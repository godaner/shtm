package com.shtm.manage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.po.CustomPermissions;
import com.shtm.manage.po.JsonResult;
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
@Controller
public class PermissionsController extends BaseController<JsonResult,PermissionsServiceI> {
	
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
	public @ResponseBody JsonResult selectPermissionsByPid(String id){
		
		try {
			
			List<CustomPermissions> pers = service.selectPermissionsByPid(id);
			
			jsonResult.setDatagrid(pers);
			
			jsonResult.setMsg("获取权限成功");
			jsonResult.setResult(RESULT.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg(e.getMessage());
			jsonResult.setResult(RESULT.FALSE);
		}
		
		return jsonResult;
	}
}

