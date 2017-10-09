package com.shtm.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomClazzsMapper;
import com.shtm.manage.po.ClazzsReceiver;
import com.shtm.manage.po.ClazzsReplier;
import com.shtm.manage.service.ClazzsServiceI;
import com.shtm.mapper.ClazzsMapper;
import com.shtm.po.Clazzs;
import com.shtm.po.ClazzsExample;
import com.shtm.po.ClazzsExample.Criteria;
import com.shtm.service.impl.BaseService;
import com.shtm.util.Static.CONFIG;

/**
 * Title:ClazzsService
 * <p>
 * Description:clazzs業務接口實現;
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月26日 上午11:04:24
 * @version 1.0
 */
@Service
public class ClazzsService extends BaseService implements ClazzsServiceI {
	
	@Autowired
	private ClazzsMapper clazzsMapper;
	@Autowired
	private CustomClazzsMapper customClazzsMapper;
	
	@Override
	public ClazzsReplier selectAllClazzs() throws Exception {
		
		ClazzsReplier replier = new ClazzsReplier();
		
		replier.setRows(customClazzsMapper.selectAllClazzs());
		
		//設置最大clazzs數量
		Integer max = Integer.valueOf(getValue(CONFIG.FILED_GOODS_CLAZZS_MAXNUMBER).toString());
		
		replier.setMaxClazzs(max);
		
		return replier;
	}

	@Override
	public ClazzsReplier selectClazzsDatagrid(ClazzsReceiver receiver) throws Exception {
		ClazzsReplier replier = new ClazzsReplier();

		// 查询
		replier.setRows(customClazzsMapper.selectClazzsDatagrid(receiver));

		// 设置记录总数
		Integer totalNum = customClazzsMapper.selectClazzsNum(receiver);

		replier.setTotal(totalNum);

		return replier;
	}

	@Override
	public void deleteClazzs(ClazzsReceiver receiver) throws Exception {
		
		clazzsMapper.deleteByPrimaryKey(receiver.getId());
		
	}

	@Override
	public void insertClazzs(ClazzsReceiver receiver) throws Exception {
		
		//查询是否存在同名clazz
		ClazzsExample example = new ClazzsExample();
		
		Criteria criteria = example.createCriteria();
		
		criteria.andTextEqualTo(receiver.getText());
		
		List<Clazzs> list = clazzsMapper.selectByExample(example);
		
		eject(list.size()>0, "该类名已存在");
		
		//执行插入
		receiver.setNum(0d);
		
		clazzsMapper.insert(receiver);
	}

	@Override
	public void updateClazzs(ClazzsReceiver receiver) throws Exception {
		//查询是否存在同名clazz
		ClazzsExample example = new ClazzsExample();

		Criteria criteria = example.createCriteria();

		criteria.andTextEqualTo(receiver.getText());

		List<Clazzs> list = clazzsMapper.selectByExample(example);

		eject(list.size() > 0, "该类名已存在");
		
		//禁止更新num字段
		receiver.setNum(null);
		
		
		clazzsMapper.updateByPrimaryKeySelective(receiver);
		
	}

}
