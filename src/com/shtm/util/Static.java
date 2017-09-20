package com.shtm.util;

/**
 * Title:Static
 * <p>
 * Description:存放全局静态常量;
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月11日 下午3:12:24
 * @version 1.0
 */
public interface Static {
	/**
	 * request返回信息的字段名
	 */
	public static final String FIELD_REQUEST_RETURN_MSG = "msg";
	/**
	 * request中存放在线用户的信息的字段
	 */
	public static final String FILED_ONLINE_ADMIN = "onlineUser";
	
	/**
	 * 登录验证码字段
	 */
	public final static String FILED_LOGIN_VERIFYCODE = "loginVerifyCode";
	
	/**
	 * Title:CONFIG
	 * <p>
	 * Description:condig文件中的key常量字段;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月20日 上午7:57:57
	 * @version 1.0
	 */
	public interface CONFIG{
		/**
		 * 存放网站地址字段
		 */
		static final String FIELD_WEB_ADDR = "config.webaddr";
		/**
		 * 用户头像资源存放路径
		 * 
		 */
		static final String FILED_SRC_USERS_HEADIMGS = "config.src.users.heaimgs";
		/**
		 * 商品图片资源存放路径
		 * 
		 */
		static final String FILED_SRC_GOODS_IMGS = "config.src.goods.imgs";
		
		/**
		 * 用户头像尺寸版本字段
		 */
		static final String FILED_USERS_HEADINGS_SIZES = "config.users.headimgs.sizes";
		/**
		 * 商品图片尺寸版本字段
		 */
		static final String FILED_GOODS_IMGS_SIZES = "config.goods.imgs.sizes";
		/**
		 * 用户头像圖片類型字段
		 */
		static final String FILED_USERS_HEADINGS_TYPES = "config.users.headimgs.types";
		/**
		 * 商品图片類型字段
		 */
		static final String FILED_GOODS_IMGS_TYPES = "config.goods.imgs.types";
	}
	
	/**
	 * ajax返回的操作结果代号的字段
	 */
	public interface RESULT{
		/**
		 * 操作成功
		 */
		Integer TRUE = 1;
		/**
		 * 操作失败
		 */
		Integer FALSE = 0;
		
	}
	/**
	 * 权限节点状态
	 */
	public interface PERS_STATE{
		/**
		 * 关闭
		 */
		String CLOSED = "closed";
		/**
		 * 开启
		 */
		String OPEN = "open";
		
	}
	/**
	 * 
	 * Title:REG
	 * <p>
	 * Description:正则表达式
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月19日 下午7:27:32
	 * @version 1.0
	 */
	public interface REG{
		
		//邮箱
		String EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+$";
		
		//密码
		String PASSWORD = "^[a-zA-Z]\\w{5,12}$";
		
		//用户名
		String USERNAME = "^[A-Za-z][A-Za-z1-9_-]{5,20}$";
		
		//邮编
		String POST_CODE = "^[1-9]\\d{5}$";
	}
	
	/**
	 * Title:USERS_STATUS
	 * <p>
	 * Description:用户状态<br/>
	 * 状态:1为激活，,0为冻结,-1为删除 ,-2为注册未激活<br/>
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月20日 下午4:58:39
	 * @version 1.0
	 */
	public interface USERS_STATUS{
		static final Short ACTIVITY = 1;
		static final Short FROZEN = 0;
		static final Short UNACTIVITY = -2;
		static final Short DELETE = -1;
	}
	
	
}
