package com.shtm.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomRegionMapper;
import com.shtm.manage.po.RegionReplier;
import com.shtm.manage.service.RegionServiceI;
import com.shtm.service.impl.BaseService;

/**
 * Title:RegionService
 * <p>
 * Description:region业务接口实现;
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月25日 下午1:48:20
 * @version 1.0
 */
@Service
public class RegionService extends BaseService implements RegionServiceI {

	@Autowired
	private CustomRegionMapper customRegionMapper;
	
	
	@Override
	public RegionReplier selectRegionByPid(Integer pid) throws Exception {
		
		RegionReplier replier = new RegionReplier();
		
		replier.setChilds(customRegionMapper.selectRegionByPid(pid));
		
		return replier;
	}

}
