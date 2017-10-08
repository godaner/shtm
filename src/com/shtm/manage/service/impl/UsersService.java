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

/**
 * 
 * Title:UsersService
 * <p>
 * Description:用户业务接口实现
 * <p>
 * 
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
	public UsersReplier selectUsersDatagrid(UsersReceiver receiver)
			throws Exception {
		UsersReplier replier = new UsersReplier();

		// 查询
		replier.setRows(customUsersMapper.selectUsersDatagrid(receiver));

		// 设置记录总数
		Integer totalNum = customUsersMapper.selectUsersNum(receiver);

		replier.setTotal(totalNum);

		return replier;
	}

	@Override
	public UsersReplier selectUserByPK(String id) throws Exception {

		return (UsersReplier) usersMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateUser(UsersReceiver receiver) throws Exception {

		Users dbUser = usersMapper.selectByPrimaryKey(receiver.getId());

		if (dbUser == null) {//用户已被删除
			return;
		}
		if (dbUser.getStatus() == USERS_STATUS.DELETE) {//用户已被删除
			return;
		}
		
		String userId = dbUser.getId();

		/**
		 * 验证username
		 */
		String username = receiver.getUsername();
		//验证否空
		eject(username == null || username.trim().isEmpty(),"用户名不能为空");
		
		//验证格式
		username = username.toLowerCase();
		
		eject(!username.matches(REG.USERNAME),
				"用户名格式错误,要求:开头为字母的[_-,数字,字母]的5-20组合");

		//設置username小写
		receiver.setUsername(username);

		/**
		 * 验证email格式
		 */
		String email = receiver.getEmail();
		//验证否空
		eject(email == null || email.trim().isEmpty(),"邮箱不能为空");
		//验证格式
		email = email.toLowerCase();
		
		eject(!email.matches(REG.EMAIL), "邮箱格式错误");

		//設置email小写
		receiver.setEmail(email);
		
		/**
		 * 验证密码格式
		 */

		String password = receiver.getPassword();

		// 判断管理员是否更新了密码
		if (password != null && password.trim().equals("")) {
			receiver.setPassword(null);
		}else{
			// 验证新密码格式
			eject(!password.matches(REG.PASSWORD),
					"密码格式错误,要求:开头为字母的字母数字5-12组合");

			receiver.setPassword(md5(password + dbUser.getSalt()));
		}
		
		

		/**
		 * 验证username是否已存在
		 */
		UsersExample ue = null;
		Criteria c = null;
		List<Users> dbUsers = null;
		// 如果修改了用户名
		if (!dbUser.getUsername().toLowerCase().equals(username)) {
			ue = new UsersExample();
			c = ue.createCriteria();
			c.andUsernameEqualTo(username.toLowerCase()).andStatusNotEqualTo(USERS_STATUS.DELETE);

			dbUsers = usersMapper.selectByExample(ue);

			eject(dbUsers.size() > 0, "该用户名已存在");
		}

		/**
		 * 验证email是否已存在
		 */
		if (!dbUser.getEmail().toLowerCase().equals(email)) {
			ue = new UsersExample();
			c = ue.createCriteria();

			c.andEmailEqualTo(email).andStatusNotEqualTo(USERS_STATUS.DELETE);

			dbUsers = usersMapper.selectByExample(ue);

			eject(dbUsers.size() > 0, "该邮箱已存在");
		}

		/**
		 * 验证是否上传头像
		 */
		MultipartFile sourceFile = receiver.getFile();
		String path = null;
		String fileName = null;
		if (sourceFile != null && !sourceFile.isEmpty()) {

			String suffix = getFileNameExt(sourceFile.getOriginalFilename());

			// 驗證圖片格式
			String accept = getValue(CONFIG.FILED_USERS_HEADINGS_TYPES)
					.toString().toLowerCase();
			eject(accept.indexOf(suffix.toLowerCase()) < 0, "圖片格式不正確,隻允許"
					+ accept);

			// 文件名a.jpg
			fileName = userId + "." + suffix;

			path = (String) getValue(CONFIG.FILED_SRC_USERS_HEADIMGS);

			// 设置文件名
			receiver.setHeadimg(fileName);

		}

		try {
			/**
			 * 禁止更新字段
			 */
//			receiver.setUsername(null);
			
			/**
			 * 更新数据库
			 */
			usersMapper.updateByPrimaryKeySelective(receiver);

			/**
			 * 保存图片
			 */
			if (sourceFile != null && !sourceFile.isEmpty()) {
				// 保存原图
				File targetFile = new File(path + fileName);
				sourceFile.transferTo(targetFile);
				// 压缩原图到磁盘
				writeFileWithCompress(targetFile,
						(String) getValue(CONFIG.FILED_USERS_HEADINGS_SIZES),
						path, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void deleteUser(String id) throws Exception {
		Users u1 = usersMapper.selectByPrimaryKey(id);
		if (u1 == null) {//用户已被删除
			return;
		}
		if (u1.getStatus() == USERS_STATUS.DELETE) {//用户已被删除
			return;
		}
		
		Users u0 = new Users();
		u0.setId(id);
		u0.setStatus(USERS_STATUS.DELETE);
		//防止管理員修改其他用戶信息成被刪除記錄的username或者email失敗(username和email有唯一約束)
		u0.setEmail(uuid()+"_"+u1.getEmail().toLowerCase());
		
		u0.setUsername(uuid()+"_"+u1.getUsername().toLowerCase());
		
		usersMapper.updateByPrimaryKeySelective(u0);
	}

	@Override
	public void insertUser(UsersReceiver receiver) throws Exception {
		
		String uuid = uuid();

		/**
		 * 验证username
		 */
		String username = receiver.getUsername();
		//非空验证
		eject(username == null || username.trim().isEmpty(),"邮箱不能为空");
		//格式验证
		username = username.toLowerCase();
		
		eject(!username.matches(REG.USERNAME),
				"用户名格式错误,要求:开头为字母的[_-,数字,字母]的5-20组合");

		//設置username小写
		receiver.setUsername(username);
		

		/**
		 * 验证email
		 */
		String email = receiver.getEmail();
		//非空验证
		eject(email == null || email.trim().isEmpty(),"邮箱不能为空");
		//格式验证
		email = email.toLowerCase();
		
		eject(!email.matches(REG.EMAIL), "邮箱格式错误");
		
		//設置email小写
		receiver.setEmail(email);

		/**
		 * 验证密码
		 */

		String password = receiver.getPassword();
		//非空验证
		eject(password == null || password.trim().isEmpty(),"密码不能为空");

		//格式验证
		eject(!password.matches(REG.PASSWORD),
				"密码格式错误,要求:开头为字母的字母数字5-12组合");

		//密码验证通过,进行加密
		receiver.setPassword(md5(password + email));

		

		List<Users> dbUsers = null;
		
		/**
		 * 验证username是否存在
		 */
		UsersExample ue = new UsersExample();
		Criteria c = null;
		c = ue.createCriteria();
		c.andUsernameEqualTo(username).andStatusNotEqualTo(USERS_STATUS.DELETE);

		dbUsers = usersMapper.selectByExample(ue);

		eject(dbUsers != null && dbUsers.size() > 0, "该用户名已存在");

		/**
		 * 验证email是否存在
		 */
		ue = new UsersExample();
		
		c = ue.createCriteria();

		c.andEmailEqualTo(email).andStatusNotEqualTo(USERS_STATUS.DELETE);

		dbUsers = usersMapper.selectByExample(ue);

		eject(dbUsers != null && dbUsers.size() > 0, "该邮箱已存在");

		/**
		 * 验证是否上传头像
		 */
		MultipartFile sourceFile = receiver.getFile();
		String path = null;
		String fileName = null;
		if (sourceFile != null && !sourceFile.isEmpty()) {

			String suffix = getFileNameExt(sourceFile.getOriginalFilename());

			// 驗證圖片格式
			String accept = getValue(CONFIG.FILED_USERS_HEADINGS_TYPES)
					.toString().toLowerCase();
			eject(accept.indexOf(suffix.toLowerCase()) < 0, "圖片格式不正確,隻允許"
					+ accept);

			// 文件名a.jpg
			fileName = uuid + "." + suffix;

			path = (String) getValue(CONFIG.FILED_SRC_USERS_HEADIMGS);

			// 设置文件名
			receiver.setHeadimg(fileName);

		}

		try {
			
			
			/**
			 * 保存到数据库
			 */
			
			receiver.setRegisttime(timestamp());
			
			receiver.setId(uuid);
			
			receiver.setMoney(0d);
			
			usersMapper.insert(receiver);

			/**
			 * 保存图片
			 */
			if (sourceFile != null && !sourceFile.isEmpty()) {
				// 保存原图
				File targetFile = new File(path + fileName);
				sourceFile.transferTo(targetFile);
				// 压缩原图到磁盘
				writeFileWithCompress(targetFile,
						(String) getValue(CONFIG.FILED_USERS_HEADINGS_SIZES),
						path, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
