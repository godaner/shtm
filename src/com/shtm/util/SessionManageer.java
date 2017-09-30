package com.shtm.util;

import java.util.Map;

/**
 * Title:SessionManageer
 * <p>
 * Description:会话管理,实现对用户所在session的管理;即对session的增加,删除,查询,修改;
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月30日 上午10:33:25
 * @version 1.0
 */
public class SessionManageer<V> {
	private  Map<String,V> sessionMap = Util.getMap();
	
	/**
	 * Title:
	 * <p>
	 * Description:添加会话;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月30日 上午10:36:27
	 * @version 1.0
	 */
	public void addSession(String key,V value){
		
	}
}
