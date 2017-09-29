package com.shtm.manage.controller;

import java.io.File;

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
@Scope("prototype")
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
	@RequiresAuthentication
	@RequiresPermissions("users:select")
	@RequestMapping("/selectUsersDatagrid")
	public @ResponseBody UsersReplier selectUsersDatagrid(UsersReceiver receiver) throws Exception{
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
	
	
	/**
	 * Title:
	 * <p>
	 * Description:获取指定id的user信息
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月18日 下午8:56:31
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@RequiresAuthentication
	@RequiresPermissions("users:select")
	@RequestMapping("/selectUserByPK")
	public @ResponseBody UsersReplier selectUserByPK(String id) throws Exception{
		UsersReplier replier = new UsersReplier();
		try {
			replier = service.selectUserByPK(id);
			
			replier.setMsg("获取信息成功");
			
			replier.setResult(RESULT.TRUE);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			replier.setMsg(e.getMessage());
			
			replier.setResult(RESULT.FALSE);
		}
		return replier;
		
	}
	
	
	
	/**
	 * Title:updateUser
	 * <p>
	 * Description:根据id更新user;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月19日 下午4:38:54
	 * @version 1.0
	 * @return
	 */
	@RequiresAuthentication
	@RequiresPermissions("users:update")
	@RequestMapping("/updateUser")
	public @ResponseBody UsersReplier updateUser(UsersReceiver receiver) throws Exception{
		UsersReplier replier = new UsersReplier();
		try {
			
			service.updateUser(receiver);
			
			replier.setMsg("修改用户成功");
			
			replier.setResult(RESULT.TRUE);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			replier.setMsg(e.getMessage());
			
			replier.setResult(RESULT.FALSE);
		}
		return replier;
	}
	/**
	 * Title:getHeadImg
	 * <p>
	 * Description:根据headimg和图片尺寸获取图片;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月19日 下午4:38:54
	 * @version 1.0
	 * @return
	 */
	@RequestMapping("/getHeadImg")
	public void getHeadImg(String headimg,String size) throws Exception{

		
		try {
			
			eject(size == null || size.trim().isEmpty(),"用户头像的size没有指定");
			

			eject(headimg == null || size.trim().isEmpty(),"用户头像的headimgs没有指定");
			
			//找不到指定的图片
			String path = getValue(CONFIG.FILED_SRC_USERS_HEADIMGS)+size+"_"+headimg;
			
			//如果文件不存在
			eject(!new File(path).exists());
			
			//返回头像
			writeFileToOS(path, getResponse().getOutputStream());
			
		} catch (Exception e) {
			//返回默认图片
			String defaultImgName = getValue(CONFIG.FILED_USERS_HEADIMGS_DEFAULT).toString();
			String path = getValue(CONFIG.FILED_SRC_USERS_HEADIMGS) + defaultImgName;
			writeFileToOS(path, getResponse().getOutputStream());
		}
		
		
	}
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
	
	
	/**
	 * Title:deleteUser
	 * <p>
	 * Description:删除用户
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月20日 下午4:54:57
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteUser")
	public @ResponseBody UsersReplier deleteUser(String id) throws Exception{
		
		
		UsersReplier replier = new UsersReplier();
		try {
			
			service.deleteUser(id);
			
			replier.setMsg("删除用户成功");
			
			replier.setResult(RESULT.TRUE);
			
		} catch (Exception e) {
			e.printStackTrace();

			replier.setMsg("删除失败");
			
			replier.setResult(RESULT.FALSE);
		}
		return replier;
	}
}
