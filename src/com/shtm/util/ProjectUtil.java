package com.shtm.util;

import org.apache.shiro.SecurityUtils;

import com.shtm.po.Admins;

/**
 * Title:ProjectUtil
 * <p>
 * Description:項目相關util
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月30日 上午8:10:21
 * @version 1.0
 */
public class ProjectUtil extends Util implements Static{
	/**
	 * Title:
	 * <p>
	 * Description:返回在綫的admin
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月30日 上午8:12:28
	 * @version 1.0
	 * @return
	 */
	public <T> T getOnlineAdmin(){
		return (T) SecurityUtils.getSubject().getSession().getAttribute(FILED_ONLINE_ADMIN);
	}
	
	/**
	 * Title:
	 * <p>
	 * Description:設置在綫admin
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月30日 上午8:13:18
	 * @version 1.0
	 * @param ad
	 */
	public void setOnlineAdmin(Admins ad){
		SecurityUtils.getSubject().getSession().setAttribute(FILED_ONLINE_ADMIN,ad);
	}
	
	
}
