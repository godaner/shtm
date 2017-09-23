package com.shtm.manage.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.po.GoodsReceiver;
import com.shtm.manage.po.GoodsReplier;
import com.shtm.manage.service.GoodsServiceI;


/**
 * Title:GoodsController
 * <p>
 * Description:goods的controller
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月22日 下午2:35:48
 * @version 1.0
 */
@RequestMapping("/goods")
@Scope("prototype")
@Controller
public class GoodsController extends BaseController<GoodsServiceI>{
	
	/**
	 * Title:selectGoodsDatagrid
	 * <p>
	 * Description:通过组合条件获取easyui的datagrid所需的数据;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月23日 下午3:05:23
	 * @version 1.0
	 * @param receiver
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectGoodsDatagrid")
	public @ResponseBody GoodsReplier selectGoodsDatagrid(GoodsReceiver receiver) throws Exception{
		
		GoodsReplier replier = new GoodsReplier();
		
		try {
			
			replier = service.selectGoodsDatagrid(receiver);
			
			replier.setResult(RESULT.TRUE);
			
			replier.setMsg("获取列表成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg("获取列表失败");
		}
		
		
		return replier;
		
		
	}
}
