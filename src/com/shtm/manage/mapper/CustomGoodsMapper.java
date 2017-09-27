package com.shtm.manage.mapper;

import java.util.List;

import com.shtm.manage.po.GoodsReceiver;
import com.shtm.manage.po.GoodsReplier;

/**
 * Title:CustomGoodsMapper
 * <p>
 * Description:自定义goodsMapper
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月23日 下午2:31:37
 * @version 1.0
 */
public interface CustomGoodsMapper {

	/**
	 * Title:selectGoodsDatagrid
	 * <p>
	 * Description:通过组合条件获取easyui的datagrid所需的数据;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 下午3:10:28
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	List<GoodsReplier> selectGoodsDatagrid(GoodsReceiver receiver) throws Exception;
	/**
	 * Title:selectGoodsNum
	 * <p>
	 * Description:通过组合条件获取记录数;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 下午3:10:28
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	Integer selectGoodsNum(GoodsReceiver receiver) throws Exception;
	
}
