package com.shtm.manage.service;

import com.shtm.manage.po.GoodsReceiver;
import com.shtm.manage.po.GoodsReplier;

/**
 * Title:GoodsServiceI
 * <p>
 * Description:商品业务接口
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月22日 下午2:43:10
 * @version 1.0
 */
public interface GoodsServiceI {

	/**
	 * Title:selectGoodsDatagrid
	 * <p>
	 * Description:通过组合条件获取easyui的datagrid所需的数据;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 下午3:07:29
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	GoodsReplier selectGoodsDatagrid(GoodsReceiver receiver) throws Exception;

	/**
	 * Title:updateGood
	 * <p>
	 * Description:更新商品基本信息;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月25日 下午6:55:15
	 * @version 1.0
	 * @param receiver
	 * @throws Exception
	 */
	void updateGood(GoodsReceiver receiver) throws Exception;


	/**
	 * Title:
	 * <p>
	 * Description:删除指定id的商品
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月25日 下午8:20:09
	 * @version 1.0
	 * @param receiver
	 * @throws Exception
	 */
	void deleteGood(GoodsReceiver receiver) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:通过主键查询goods
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月26日 下午4:16:52
	 * @version 1.0
	 * @param id
	 * @return
	 * @throws Exception
	 */
	GoodsReplier selectGoodsByPK(String id) throws Exception;

	/**
	 * Title:selectGoodsImgsDatagrid
	 * <p>
	 * Description:通过商品id查询上商品图片
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 上午11:27:18
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	GoodsReplier selectGoodsImgsDatagrid(GoodsReceiver receiver) throws Exception;

}
