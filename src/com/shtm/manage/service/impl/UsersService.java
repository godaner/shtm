package com.shtm.manage.service.impl;

import java.io.File;
import java.util.Date;
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
		;

		String userId = dbUser.getId();

		/**
		 * 验证username格式
		 */
		eject(!receiver.getUsername().matches(REG.USERNAME),
				"用户名格式错误,要求:开头为字母的[_-,数字,字母]的5-20组合");

		/**
		 * 验证email格式
		 */
		eject(!receiver.getEmail().matches(REG.EMAIL), "邮箱格式错误");

		/**
		 * 验证密码格式
		 */

		String fPW = receiver.getPassword();

		// 判断,设置password
		if (fPW != null && fPW.trim().equals("")) {
			receiver.setPassword(null);
		}

		if (receiver.getPassword() != null) {

			// 验证新密码格式
			eject(!receiver.getPassword().matches(REG.PASSWORD),
					"密码格式错误,要求:开头为字母的字母数字5-12组合");

			receiver.setPassword(md5(receiver.getPassword() + dbUser.getSalt()));

		}

		/**
		 * 验证username是否存在
		 */
		UsersExample ue = new UsersExample();
		Criteria c = null;
		List<Users> dbUsers = null;
		// 如果修改了用户名
		if (!dbUser.getUsername().equals(receiver.getUsername())) {
			c = ue.createCriteria();
			c.andUsernameEqualTo(receiver.getUsername());

			dbUsers = usersMapper.selectByExample(ue);

			eject(dbUsers.size() > 0, "该用户名已存在");
		}

		/**
		 * 验证email是否存在
		 */
		if (!dbUser.getEmail().equals(receiver.getEmail())) {
			c = ue.createCriteria();

			c.andEmailEqualTo(receiver.getEmail());

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
		//判斷是否已經被刪除
		if(u1.getStatus().equals(USERS_STATUS.DELETE)){
			return;
		}
		
		Users u0 = new Users();
		u0.setId(id);
		u0.setStatus(USERS_STATUS.DELETE);
		//防止管理員修改其他用戶信息成被刪除記錄的username或者email失敗(username和email有唯一約束)
		u0.setEmail("0_"+u1.getEmail());
		
		u0.setUsername("0_"+u1.getUsername());
		
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
		eject(!username.matches(REG.USERNAME),
				"用户名格式错误,要求:开头为字母的[_-,数字,字母]的5-20组合");
		

		/**
		 * 验证email
		 */
		String email = receiver.getEmail();
		//非空验证
		eject(email == null || email.trim().isEmpty(),"邮箱不能为空");
		//格式验证
		eject(!email.matches(REG.EMAIL), "邮箱格式错误");

		/**
		 * 验证密码
		 */

		String fPW = receiver.getPassword();
		//非空验证
		eject(fPW == null || fPW.trim().isEmpty(),"密码不能为空");

		//格式验证
		eject(!receiver.getPassword().matches(REG.PASSWORD),
				"密码格式错误,要求:开头为字母的字母数字5-12组合");

		//密码验证通过,进行加密
		receiver.setPassword(md5(receiver.getPassword() + receiver.getEmail()));

		

		List<Users> dbUsers = null;
		
		/**
		 * 验证username是否存在
		 */
		UsersExample ue = new UsersExample();
		Criteria c = null;
		c = ue.createCriteria();
		c.andUsernameEqualTo(receiver.getUsername()).andStatusNotEqualTo(USERS_STATUS.DELETE);

		dbUsers = usersMapper.selectByExample(ue);

		eject(dbUsers != null && dbUsers.size() > 0, "该用户名已存在");

		/**
		 * 验证email是否存在
		 */
		c = ue.createCriteria();

		c.andEmailEqualTo(receiver.getEmail()).andStatusNotEqualTo(USERS_STATUS.DELETE);

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
			
			receiver.setRegisttime(new Date());
			
			receiver.setId(uuid);
			
			receiver.setMoney(0d);
			
			Users u = ((Users)receiver);
			
			u = null2Empty(u);
			
			usersMapper.insert(u);

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
