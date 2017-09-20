package com.shtm.manage.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shtm.manage.mapper.CustomUsersMapper;
import com.shtm.manage.po.UsersReceiver;
import com.shtm.manage.po.UsersReplier;
import com.shtm.manage.service.UsersServiceI;
import com.shtm.mapper.UsersMapper;
import com.shtm.po.Users;
import com.shtm.po.UsersExample;
import com.shtm.po.UsersExample.Criteria;
import com.shtm.service.impl.BaseService;
import com.shtm.util.Static.CONFIG;
import com.shtm.util.Static.REG;
import com.shtm.util.Static.USERS_STATUS;

/**
 * 
 * Title:UsersService
 * <p>
 * Description:用户业务接口实现
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月18日 上午8:51:36
 * @version 1.0
 */
@Service
public class UsersService extends BaseService implements UsersServiceI {
	
	@Autowired
	private CustomUsersMapper customUsersMapper;
	@Autowired
	private UsersMapper usersMapper;

	@Override
	public UsersReplier selectUsersDatagrid(UsersReceiver receiver) throws Exception {
		UsersReplier replier = new UsersReplier();
		
		//查询
		replier.setRows(customUsersMapper.selectUsersDatagrid(receiver));
		
		//设置记录总数
		Integer totalNum = customUsersMapper.selectUsersNum(receiver);
		
		replier.setTotal(totalNum);
		
		return replier;
	}

	@Override
	public UsersReplier selectUserByPK(String id)throws Exception {
		
		
		return (UsersReplier) usersMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateUser(UsersReceiver receiver) throws Exception {
		
		Users dbUser = usersMapper.selectByPrimaryKey(receiver.getId());;
		
		String userId = dbUser.getId();
		
		/**
		 * 头像
		 */
		MultipartFile sourceFile = receiver.getFile();
		String path = null;
		String fileName = null;
		if(sourceFile != null && !sourceFile.isEmpty()){
			
			String suffix = getFileNameExt(sourceFile.getOriginalFilename());
			
			//驗證圖片格式
			String accept = getValue(CONFIG.FILED_USERS_HEADINGS_TYPES).toString().toLowerCase();
			eject(accept.indexOf(suffix.toLowerCase())<0, 
					"圖片格式不正確,隻允許"+accept);
			
			//文件名a.jpg
			fileName = userId+"."+suffix;
			
			path = (String) getValue(CONFIG.FILED_SRC_USERS_HEADIMGS);
			
			//设置文件名
			receiver.setHeadimg(fileName);

		}
		
		/**
		 * 驗證密碼
		 */
		
		String fPW = receiver.getPassword();
		
		
		//判断,设置password
		if(fPW != null && fPW.trim().equals("")){
			receiver.setPassword(null);
		}
		
		if(receiver.getPassword() != null){
			
			//验证新密码格式
			eject(receiver.getPassword().matches(REG.PASSWORD), "密码格式错误");
			
			
			receiver.setPassword(md5(receiver.getPassword() + dbUser.getSalt()));
		
			
		}

		
		
		/**
		 * 验证username
		 */
		UsersExample ue = new UsersExample();
		Criteria c = null;
		List<Users> dbUsers = null;
		//如果修改了用户名
		if(!dbUser.getUsername().equals(receiver.getUsername())){
			c = ue.createCriteria();
			c.andUsernameEqualTo(receiver.getUsername());
			
			dbUsers = usersMapper.selectByExample(ue);
			
			eject(dbUsers.size() > 0, "该用户名已存在");
		}
		
		
		/**
		 * 验证email
		 */
		if(!dbUser.getEmail().equals(receiver.getEmail())){
			c = ue.createCriteria();
			
			c.andEmailEqualTo(receiver.getEmail());
			
			dbUsers = usersMapper.selectByExample(ue);
			
			eject(dbUsers.size() > 0, "该邮箱已存在");
		}
		
		
		try{
			//更新数据库
			usersMapper.updateByPrimaryKeySelective(receiver);
			
			//保存原图
			File targetFile = new File(path+fileName);
			sourceFile.transferTo(targetFile);
			//压缩原图到磁盘
			writeFileWithCompress(targetFile, 
					(String) getValue(CONFIG.FILED_USERS_HEADINGS_SIZES), 
					path, fileName);
			
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		
	}

	@Override
	public void deleteUser(String id) throws Exception {
		Users u = new Users();
		u.setId(id);
		u.setStatus(USERS_STATUS.DELETE);
		usersMapper.updateByPrimaryKeySelective(u);
	}
	
}
