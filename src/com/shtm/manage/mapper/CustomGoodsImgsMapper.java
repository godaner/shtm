package com.shtm.manage.mapper;

import java.util.List;

import com.shtm.manage.po.GoodsImgsReceiver;
import com.shtm.manage.po.GoodsImgsReplier;
import com.shtm.manage.po.GoodsReceiver;
import com.shtm.po.Goods;

/**
 * Title:CustomGoodsImgsMapper
 * <p>
 * Description:自定义GoodsImgsMapper
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月27日 上午11:43:52
 * @version 1.0
 */
public interface CustomGoodsImgsMapper {

	/**
	 * Title:
	 * <p>
	 * Description:通过goodsId查询其图片
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 上午11:46:14
	 * @version 1.0
	 * @return
	 * @throws Exception
	 */
	public List<GoodsImgsReplier> selectGoodsImgsDatagrid(GoodsImgsReceiver receiver) throws Exception;

	/**
	 * Title:
	 * <p>
	 * Description:通过goodsId查询其图片数量
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 下午1:53:44
	 * @version 1.0
	 * @param string
	 * @return
	 */
	public Integer selectGoodsImgsNum(GoodsImgsReceiver receiver) throws Exception;

}
