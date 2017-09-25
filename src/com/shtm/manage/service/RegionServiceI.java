package com.shtm.manage.service;

import com.shtm.manage.po.RegionReplier;

/**
 * Title:RegionsServiceI
 * <p>
 * Description:region事务接口;
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月25日 下午1:41:22
 * @version 1.0
 */
public interface RegionServiceI {
	/**
	 * 
	 * Title:selectRegionByPid
	 * <p>
	 * Description:通过pid查找所有的region子节点;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月25日 下午1:43:14
	 * @version 1.0
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public RegionReplier selectRegionByPid(Integer pid) throws Exception;
}
