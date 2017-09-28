package com.shtm.manage.service;

import com.shtm.manage.po.ClazzsReceiver;
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
	ClazzsReplier selectAllClazzs() throws Exception;

	/**
	 * Title:selectClazzsDatagrid
	 * <p>
	 * Description:查詢easyui所需的clazzs列表;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月28日 下午3:03:43
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	ClazzsReplier selectClazzsDatagrid(ClazzsReceiver receiver) throws Exception;

	
}
