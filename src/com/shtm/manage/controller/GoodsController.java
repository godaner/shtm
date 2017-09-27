package com.shtm.manage.controller;

import java.io.File;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.groups.GoodsGroups.DeleteGoodsGroups;
import com.shtm.manage.groups.GoodsGroups.UpdateGoodGroups;
import com.shtm.manage.groups.GoodsGroups.UploadGoodsImgsGroups;
import com.shtm.manage.po.GoodsReceiver;
import com.shtm.manage.po.GoodsReplier;
import com.shtm.manage.service.GoodsServiceI;

/**
 * Title:GoodsController
 * <p>
 * Description:goods的controller
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月22日 下午2:35:48
 * @version 1.0
 */
@RequestMapping("/goods")
@Scope("prototype")
@Controller
public class GoodsController extends BaseController<GoodsServiceI> {

	
	/**
	 * Title:selectGoodsByPK
	 * <p>
	 * Description:通过主键查询goods
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月26日 下午4:16:12
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectGoodsByPK")
	public @ResponseBody GoodsReplier selectGoodsByPK(String id){
		GoodsReplier replier = new GoodsReplier();

		try {

			replier = service.selectGoodsByPK(id);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("获取成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}

		return replier;
	}
	
	/**
	 * Title:selectGoodsDatagrid
	 * <p>
	 * Description:通过组合条件获取easyui的datagrid所需的数据;
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月23日 下午3:05:23
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectGoodsDatagrid")
	public @ResponseBody GoodsReplier selectGoodsDatagrid(GoodsReceiver receiver)
			throws Exception {

		GoodsReplier replier = new GoodsReplier();

		try {

			replier = service.selectGoodsDatagrid(receiver);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("获取列表成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}

		return replier;

	}
	
	/**
	 * Title:selectGoodsImgsDatagrid
	 * <p>
	 * Description:根据商品id查询商品图片
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 上午11:24:16
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectGoodsImgsDatagrid")
	public @ResponseBody GoodsReplier selectGoodsImgsDatagrid(GoodsReceiver receiver)
			throws Exception {

		GoodsReplier replier = new GoodsReplier();

		try {

			replier = service.selectGoodsImgsDatagrid(receiver);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("获取图片列表成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}

		return replier;

	}
	
	/**
	 * Title:
	 * <p>
	 * Description:更新商品基本信息
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月25日 下午6:54:30
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateGood")
	public @ResponseBody GoodsReplier updateGood(@Validated(value={UpdateGoodGroups.class}) GoodsReceiver receiver,
			BindingResult result)
			throws Exception {
		GoodsReplier replier = new GoodsReplier();

		try {
			
			getError(result);
			
			service.updateGood(receiver);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("更新成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}

		return replier;
	}
	/**
	 * 
	 * Title:deleteGood
	 * <p>
	 * Description:删除指定id的商品
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月25日 下午8:19:19
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteGood")
	public @ResponseBody GoodsReplier deleteGood(@Validated(value={DeleteGoodsGroups.class}) GoodsReceiver receiver,
			BindingResult result)
			throws Exception {
		GoodsReplier replier = new GoodsReplier();

		try {
			
			getError(result);
			
			service.deleteGood(receiver);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("删除成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}

		return replier;
	}
	
	
	/**
	 * Title:getGoodsImg
	 * <p>
	 * Description:通過size和imgName參數獲取商品圖片;<br/>
	 * 测试:http://localhost/sht/common/goods_getGoodsImg.action?size=200&imgName=2.jpg;<br/>
	 * 注意:保证目录下有指定size和imgName的文件,测试才能通过;<br/>
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月26日 上午11:32:24
	 * @version 1.0
	 * @throws Exception
	 */
	@RequestMapping("/getGoodsImg")
	public void getGoodsImg(GoodsReceiver receiver) throws Exception {
		String size = receiver.getSize();
		String imgName = receiver.getImgName();
		try {

			eject(size == null || size.trim().isEmpty(), "商品图片size没有指定");

			eject(imgName == null || size.trim().isEmpty(), "商品图片的imgName没有指定");

			// 指定的图片路徑
			String path = getValue(CONFIG.FILED_SRC_GOODS_IMGS) + size
					+ "_" + imgName;

			// 如果文件不存在
			eject(!new File(path).exists(),"不存在商品图片图片: "+path);

			// 返回头像
			writeFileToOS(path, getResponse().getOutputStream());

		} catch (Exception e) {
			// 找不大图片,抛出异常
			e.printStackTrace();
		}

	}
	
	/**
	 * Title:
	 * <p>
	 * Description:上傳圖片
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月27日 下午8:27:53
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 */
	@RequestMapping("/uploadGoodsImgs")
	public @ResponseBody GoodsReplier uploadGoodsImgs(@Validated(value={UploadGoodsImgsGroups.class}) GoodsReceiver receiver,
			BindingResult result){
		GoodsReplier replier = new GoodsReplier();

		try {
			
			getError(result);
			
			service.uploadGoodsImgs(receiver);

			replier.setResult(RESULT.TRUE);

			replier.setMsg("上傳成功");

		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}

		return replier;
	}
	
}
