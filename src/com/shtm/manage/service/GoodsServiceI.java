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
	 * Description:更新商品;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月25日 下午6:55:15
	 * @version 1.0
	 * @param receiver
	 * @throws Exception
	 */
	void updateGood(GoodsReceiver receiver) throws Exception;

}
