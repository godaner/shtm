package com.shtm.manage.groups;

/**
 * Title:GoodsGroups
 * <p>
 * Description:商品相关的校验分组;
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月23日 下午2:16:14
 * @version 1.0
 */
public interface GoodsGroups {
	
	/**
	 * Title:UpdateGoodsGroups
	 * <p>
	 * Description:更新goods基本信息所使用的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 下午2:17:12
	 * @version 1.0
	 */
	public interface UpdateGoodGroups{};
	

	/**
	 * Title:DeleteGoodsGroups
	 * <p>
	 * Description:删除goods所使用的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 下午2:20:19
	 * @version 1.0
	 */
	public interface DeleteGoodsGroups{};
	


	/**
	 * Title:uploadGoodsImgsGroups
	 * <p>
	 * Description:上传goods图片所使用的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 下午8:04:07
	 * @version 1.0
	 */
	public interface UploadGoodsImgsGroups{};
	
	
	/**
	 * Title:GetGoodsImgGroups
	 * <p>
	 * Description:獲取goods某張圖片所使用的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 下午8:36:24
	 * @version 1.0
	 */
	public interface GetGoodsImgGroups{};
	
	
	/**
	 * Title:SelectGoodsImgsDatagrid
	 * <p>
	 * Description:獲取goods圖片列表所使用的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 下午8:39:13
	 * @version 1.0
	 */
	public interface SelectGoodsImgsDatagrid{};
	
	/**
	 * Title:SelectGoodsDatagrid
	 * <p>
	 * Description:獲取商品列表所使用的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 下午8:44:52
	 * @version 1.0
	 */
	public interface SelectGoodsDatagrid{};

	/**
	 * Title:SelectGoodsByPK
	 * <p>
	 * Description:通過主鍵獲取商品所使用的验证组;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 下午8:47:29
	 * @version 1.0
	 */
	public interface SelectGoodsByPK{};
}
