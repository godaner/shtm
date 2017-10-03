package com.shtm.manage.controller;

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
import com.shtm.manage.groups.RolesGroups.DeleteRoleGroups;
import com.shtm.manage.groups.RolesGroups.InsertRoleGroups;
import com.shtm.manage.groups.RolesGroups.SelectRolePermissionsById;
import com.shtm.manage.groups.RolesGroups.SelectRolesDatagridGroups;
import com.shtm.manage.groups.RolesGroups.UpdateRoleGroups;
import com.shtm.manage.groups.RolesGroups.UpdateRolePermission;
import com.shtm.manage.po.RolesReceiver;
import com.shtm.manage.po.RolesReplier;
import com.shtm.manage.service.RolesServiceI;


/**
 * Title:RolesController
 * <p>
 * Description:roles的controller
 * <p>
 * @author Kor_Zhang
 * @date 2017年10月2日 上午11:28:41
 * @version 1.0
 */
@RequestMapping("/roles")
@Scope("prototype")
@Controller
public class RolesController extends BaseController<RolesServiceI> {
	
	/**
	 * 
	 * Title:selectRolePermissionsById
	 * <p>
	 * Description:获取指定id的role的permisssions(所有的permisssions也会被全部查询出来,但是role拥有的permisssions有特殊标记);
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
	@RequiresPermissions("roles:select")
	@RequestMapping("/selectRolePermissionsById")
	public @ResponseBody RolesReplier selectRolePermissionsById(
			@Validated(value = { SelectRolePermissionsById.class }) RolesReceiver receiver,
			BindingResult result) throws Exception {
		RolesReplier replier = new RolesReplier();
		try {
			getError(result);

			replier = service.selectRolePermissionsById(receiver);

			replier.setResult(RESULT.TRUE);
			replier.setMsg("获取角色权限成功");
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
	 * Description:更新roles的權限;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月2日 上午11:29:31
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequiresAuthentication
	@RequiresPermissions("roles:update")
	@RequestMapping("/updateRolePermissions")
	public @ResponseBody RolesReplier updateRolePermissions(
			@Validated(value = { UpdateRolePermission.class }) @RequestBody RolesReceiver receiver,
			BindingResult result) throws Exception {
		RolesReplier replier = new RolesReplier();
		try {
			getError(result);

			service.updateRolePermissions(receiver.getId(),receiver.getPermissionsIds());

			replier.setResult(RESULT.TRUE);
			replier.setMsg("更新角色權限成功");
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
	 * Description:添加角色成功
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月2日 下午1:23:45
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequiresAuthentication
	@RequiresPermissions("roles:insert")
	@RequestMapping("/insertRole")
	public @ResponseBody RolesReplier insertRole(
			@Validated(value = { InsertRoleGroups.class }) RolesReceiver receiver,
			BindingResult result) throws Exception {
		RolesReplier replier = new RolesReplier();
		try {
			getError(result);

			service.insertRoleGroups(receiver);

			replier.setResult(RESULT.TRUE);
			replier.setMsg("添加角色成功");
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
	 * Description:添加角色成功
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月2日 下午1:23:45
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequiresAuthentication
	@RequiresPermissions("roles:delete")
	@RequestMapping("/deleteRole")
	public @ResponseBody RolesReplier deleteRole(
			@Validated(value = { DeleteRoleGroups.class }) RolesReceiver receiver,
			BindingResult result) throws Exception {
		RolesReplier replier = new RolesReplier();
		try {
			getError(result);

			service.deleteRole(receiver);

			replier.setResult(RESULT.TRUE);
			replier.setMsg("删除角色成功");
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
	 * Description:查询easyui所需的分页数据;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月2日 下午1:26:56
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequiresAuthentication
	@RequiresPermissions("roles:select")
	@RequestMapping("/selectRolesDatagrid")
	public @ResponseBody RolesReplier selectRolesDatagrid(
			@Validated(value = { SelectRolesDatagridGroups.class }) RolesReceiver receiver,
			BindingResult result) throws Exception {
		RolesReplier replier = new RolesReplier();
		try {
			getError(result);

			replier = service.selectRolesDatagrid(receiver);

			replier.setResult(RESULT.TRUE);
			replier.setMsg("查询角色成功");
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
	 * Description:更新角色基本信息;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年10月2日 下午1:27:28
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequiresAuthentication
	@RequiresPermissions("roles:update")
	@RequestMapping("/updateRole")
	public @ResponseBody RolesReplier updateRole(
			@Validated(value = { UpdateRoleGroups.class }) RolesReceiver receiver,
			BindingResult result) throws Exception {
		RolesReplier replier = new RolesReplier();
		try {
			getError(result);

			service.updateRole(receiver);

			replier.setResult(RESULT.TRUE);
			replier.setMsg("更新角色成功");
		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}
		return replier;
	}

}
