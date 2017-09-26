package com.shtm.manage.service;

import com.shtm.manage.po.ClazzsReplier;

/**
 * Title:ClazzsServiceI
 * <p>
 * Description:clazzs业务接口;
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月26日 上午9:40:56
 * @version 1.0
 */
public interface ClazzsServiceI {

	/**
	 * Title:selectClazzs
	 * <p>
	 * Description:獲取clazzs;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月26日 上午10:14:06
	 * @version 1.0
	 * @return
	 * @throws Exception
	 */
	ClazzsReplier selectClazzs() throws Exception;
	
}
