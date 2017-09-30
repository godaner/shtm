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
	 * Title:GOODS_IMGS_MAIN
	 * <p>
	 * Description:是否為主文件
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 下午8:17:06
	 * @version 1.0
	 */
	public interface GOODS_IMGS_IS_MAIN{
		public final static Double YES = 1d;
		public final static Double NO = 0d;
	}
	
	
	
	/**
	 * 
	 * Title:ADMINS_LOGIN_LOG_RESULT
	 * <p>
	 * Description:管理员登陆记录表的状态
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月21日 上午8:34:28
	 * @version 1.0
	 */
	public interface ADMINS_LOGIN_LOG_RESULT{
		static final Short FAILURE = 0;
		static final Short SUCCESS = 1;
	}
	
	
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
		/**
		 * 用户默认头像字段
		 */
		static final String FILED_USERS_HEADIMGS_DEFAULT = "config.users.headimgs.default";
		
		/**
		 * 用户头像圖片上传大小字段
		 */
		static final String FILED_USERS_HEADIMGS_MAXSIZE = "config.users.headimgs.maxsize";
		/**
		 * 商品图片上传大小字段
		 */
		static final String FILED_GOODS_IMGS_MAXSIZE = "config.goods.imgs.maxsize";
		
		/**
		 * 商品图片上传最大數量字段
		 */
		static final String FILED_GOODS_IMGS_MAXNUMBER = "config.goods.imgs.maxNumber";
		/**
		 * 商品類別最大數量字段
		 */
		static final String FILED_GOODS_CLAZZS_MAXNUMBER = "config.goods.clazzs.maxNumber";
		
	}
	
	/**
	 * Title:REDIS
	 * <p>
	 * Description:redis相關屬性
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 上午8:10:52
	 * @version 1.0
	 */
	public static interface REDIS{
		/**
		 * redis主機字段
		 */
		static final String FILED_REDIS_HOST = "redis.host";
	};
	
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

		/**
		 * 已离线
		 */
		Integer UNONLINE = -1;
		
		/**
		 * 没有权限
		 */
		Integer NOPERMISSIONS = -2;
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
		String PASSWORD = "^[a-zA-Z]\\w{4,12}$";
		
		//用户名
		String USERNAME = "^[A-Za-z][A-Za-z1-9_-]{4,20}$";
		
		//邮编
		String POST_CODE = "^[1-9]\\d{5}$";
	}
	public static void main(String[] args) {
		System.out.println("1138829222@qq.com".matches(REG.EMAIL));
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
	
	/**
	 * 
	 * Title:GOODS_STAUS
	 * <p>
	 * Description:商品状态
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午1:57:55
	 * @version 1.0
	 */
	public interface GOODS_STAUS{
		/*状态:,
			-6:待审核状态,(不可以被显示,不可以购买)
		      -7:审核未通过,(不可以被显示,不可以购买)
		      0:审核通过,(可以被显示,可以购买)
		      1:购买了且待发货,
		      2:已发货,
		      -1:买家收货后交易正常结束,
		      -2:卖家取消了出售本商品,
			  -3:买家取消购买本商品,
			  -5:管理员删除本商品,
		      -8:买家申请退款,
		      -9:退款成功（失败则保持-1状态）*/
		/**
		 * 待审核
		 */
		static final Short WAIT_TO_PASS = -6;
		
		/**
		 * 审核通过
		 */
		static final Short UNPASSED = -7;
		/**
		 * 审核未通过
		 */
		static final Short PASS_SUCCESS = 0;
		/**
		 * 购买了且待发货
		 */
		static final Short BUY_BUT_NOT_SEND = 1;
		/**
		 * 已发货
		 */
		static final Short SENDED = 2;
		/**
		 * 买家收货后正常结束
		 */
		static final Short BUYER_RECEIVED_AND_FINISHED = -1;
		/**
		 * 卖家取消了出售本商品
		 */
		static final Short SELLER_CANCEL = -2;
		/**
		 * 买家取消购买本商品
		 */
		static final Short BUYER_CANCEL = -3;
		/**
		 * 管理员删除本的商品
		 */
		static final Short ADMIN_DELETE = -5;
		/**
		 * 买家申请退款
		 */
		static final Short WAIT_RETURN_MONEY = -8;

		/**
		 * 退款成功（失败则保持-1状态）
		 */
		static final Short RETURN_MONEY_SUCCESS = -9;
		
	}
	
	
	/**
	 * Title:ADMINS_STATUS
	 * <p>
	 * Description:管理员状态<br/>
	 * 状态;1为激活,0为冻结,-1为删除
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月22日 下午3:06:57
	 * @version 1.0
	 */
	public interface ADMINS_STATUS{
		static final Short ACTIVITY = 1;
		static final Short FROZEN = 0;
		static final Short DELETE = -1;
	}
}
