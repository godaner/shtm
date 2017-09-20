package com.shtm.manage.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	@RequestMapping("/updateUser")
	public @ResponseBody UsersReplier updateUser(MultipartFile file,UsersReceiver receiver) throws Exception{
		UsersReplier replier = new UsersReplier();
		try {
			
			receiver.setFile(file);
			
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
	 * Title:updateUser
	 * <p>
	 * Description:根据id更新user;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月19日 下午4:38:54
	 * @version 1.0
	 * @return
	 */
	@RequestMapping("/getHeadImg")
	public void getHeadImg(String headimg) throws Exception{
		if(headimg == null || headimg.trim().isEmpty()){//没有头像文件名,请求默认图片
			headimg = "default.png";
		}
		
		String filePath = (String) getValue(CONFIG.FILED_SRC_USERS_HEADIMGS)+headimg;
		
		File f = new File(filePath);
		
		//返回文件
		info(f.getAbsoluteFile());
		writeFileToOS(f, getResponse().getOutputStream());
	}
}
