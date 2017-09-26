package com.shtm.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.shtm.manage.mapper.CustomClazzsMapper;
import com.shtm.manage.po.ClazzsReplier;
import com.shtm.manage.service.ClazzsServiceI;
import com.shtm.mapper.ClazzsMapper;
import com.shtm.service.impl.BaseService;

public class ClazzsService extends BaseService implements ClazzsServiceI {
	
	@Autowired
	private ClazzsMapper clazzsMapper;
	@Autowired
	private CustomClazzsMapper customClazzsMapper;
	
	@Override
	public ClazzsReplier selectClazzs() throws Exception {
		
		ClazzsReplier replier = new ClazzsReplier();
		replier.setRows(customClazzsMapper.selectClazzs());
		return replier;
	}

}
