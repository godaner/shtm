package com.shtm.manage.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shtm.manage.mapper.CustomUsersMapper;
import com.shtm.manage.po.UsersReceiver;
import com.shtm.manage.po.UsersReplier;
import com.shtm.manage.service.UsersServiceI;
import com.shtm.mapper.UsersMapper;
import com.shtm.po.Users;
import com.shtm.service.impl.BaseService;
import com.shtm.util.Static.CONFIG;
import com.shtm.util.Static.REG;

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
		String fPW = receiver.getPassword();
		
		Users dbUser = usersMapper.selectByPrimaryKey(receiver.getId());;
		
		String userId = dbUser.getId();
		
		//判断,设置password
		if(fPW != null && fPW.trim().equals("")){
			receiver.setPassword(null);
		}
		
		if(receiver.getPassword() != null){
			
			//验证新密码格式
			eject(receiver.getPassword().matches(REG.PASSWORD), "密码格式错误");
			
			
			receiver.setPassword(md5(receiver.getPassword() + dbUser.getSalt()));
		
			
		}

		//头像
		MultipartFile sourceFile = receiver.getFile();
		String path = null;
		String fileName = null;
		if(sourceFile != null){
			
			String suffix = getFileNameExt(sourceFile.getOriginalFilename());
			
			if(suffix.equals("png")){}
			
			fileName = userId+"."+suffix;
			
			path = (String) getValue(CONFIG.FILED_SRC_USERS_HEADIMGS);
			
			//设置文件名
			receiver.setHeadimg(fileName);

		}
		
		try{
			//更新数据库
			usersMapper.updateByPrimaryKeySelective(receiver);
			
			
			//压缩图片到磁盘
			writeFileWithCompress(multipartFile2File(sourceFile), 
					(String) getValue(CONFIG.FILED_USERS_HEADINGS_SIZES), 
					path, fileName);
			
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		
	}
	
}
