package com.shtm.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomGoodsMapper;
import com.shtm.manage.po.GoodsReceiver;
import com.shtm.manage.po.GoodsReplier;
import com.shtm.manage.service.GoodsServiceI;
import com.shtm.mapper.GoodsMapper;
import com.shtm.po.Goods;
import com.shtm.service.impl.BaseService;
import com.shtm.util.Static.GOODS_STAUS;

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
		//判斷商品狀態
		Goods dbGood = goodsMapper.selectByPrimaryKey(receiver.getId());
		//判断是否存在
		eject(dbGood == null || dbGood.getStatus() == GOODS_STAUS.ADMIN_DELETE, "该商品已不存在");
		
		Short oldStatus = dbGood.getStatus();
		Short newStatus = receiver.getStatus();
		
		if(oldStatus != newStatus){//如果更新了状态
			//旧状态只能为"待审核"和"审核通过"
			eject(oldStatus != GOODS_STAUS.WAIT_TO_PASS && oldStatus != GOODS_STAUS.PASS_SUCCESS ,"当前状态不允许更新");
			
		}
		
		
		//禁止更新字段
		receiver.setBrowsenumber(null);
		receiver.setBuyer(null);
		receiver.setBuytime(null);
		receiver.setCreatetime(null);
		receiver.setFinishtime(null);
		receiver.setOwner(null);
		//設置字段
		receiver.setLastupdatetime(timestamp());
		
		goodsMapper.updateByPrimaryKeySelective(receiver);
		
	}

	@Override
	public void cancelGood(GoodsReceiver receiver) throws Exception {
		Goods dbGood = goodsMapper.selectByPrimaryKey(receiver.getId());
		
		
		
		
		
	}

	@Override
	public void deleteGood(GoodsReceiver receiver) throws Exception {
		
		Goods dbGood = goodsMapper.selectByPrimaryKey(receiver.getId());
		
		eject(dbGood == null || dbGood.getStatus() == GOODS_STAUS.ADMIN_DELETE, "该用户已不存在");
		
		Goods g = new Goods();
		
		g.setId(receiver.getId());
		
		g.setStatus(GOODS_STAUS.ADMIN_DELETE);
		
		goodsMapper.updateByPrimaryKeySelective(g);
		
	}

}
