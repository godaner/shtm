package com.shtm.manage.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.groups.GoodsGroups.DeleteGoodsGroups;
import com.shtm.manage.groups.GoodsGroups.UpdateGoodGroups;
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
}
