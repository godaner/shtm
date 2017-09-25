package com.shtm.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomGoodsMapper;
import com.shtm.manage.po.GoodsReceiver;
import com.shtm.manage.po.GoodsReplier;
import com.shtm.manage.service.GoodsServiceI;
import com.shtm.mapper.GoodsMapper;
import com.shtm.service.impl.BaseService;

/**
 * Title:GoodsService
 * <p>
 * Description:商品业务接口实现
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月23日 下午2:34:33
 * @version 1.0
 */
@Service
public class GoodsService extends BaseService implements GoodsServiceI {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CustomGoodsMapper customGoodsMapper;
	
	@Override
	public GoodsReplier selectGoodsDatagrid(GoodsReceiver receiver)
			throws Exception {
		
		GoodsReplier replier = new GoodsReplier();

		// 查询
		replier.setRows(customGoodsMapper.selectGoodsDatagrid(receiver));

		// 设置记录总数
		Integer totalNum = customGoodsMapper.selectGoodsNum(receiver);

		replier.setTotal(totalNum);

		return replier;
		
	}

	@Override
	public void updateGood(GoodsReceiver receiver) throws Exception {
		
		//禁止更新字段
		receiver.setBrowsenumber(null);
		receiver.setBuyer(null);
		receiver.setBuytime(null);
		receiver.setCreatetime(null);
		receiver.setFinishtime(null);
		receiver.setOwner(null);
		receiver.setStatus(null);
		//設置字段
		receiver.setLastupdatetime(timestamp());
		
		goodsMapper.updateByPrimaryKeySelective(receiver);
		
	}

}
