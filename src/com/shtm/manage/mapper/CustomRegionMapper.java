package com.shtm.manage.mapper;

import java.util.List;

import com.shtm.po.Region;

/**
 * Title:CustomRegionMapper
 * <p>
 * Description:自定义RegionMapper
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月25日 下午1:55:44
 * @version 1.0
 */
public interface CustomRegionMapper {
	/**
	 * Title:selectRegionByPid
	 * <p>
	 * Description:通过pid查找所有的region子节点;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月25日 下午1:56:47
	 * @version 1.0
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<Region> selectRegionByPid(Integer pid) throws Exception;
}
