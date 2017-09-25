package com.shtm.manage.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.groups.RegionGroups.SelectRegionByPidGroups;
import com.shtm.manage.po.RegionReceiver;
import com.shtm.manage.po.RegionReplier;
import com.shtm.manage.service.RegionServiceI;



/**
 * Title:ReginController
 * <p>
 * Description:ReginController
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月25日 下午1:41:05
 * @version 1.0
 */
@RequestMapping("/region")
@Scope("prototype")
@Controller
public class ReginController extends BaseController<RegionServiceI>{
	
	
	/**
	 * Title:
	 * <p>
	 * Description:通过pid查找所有的region子节点;
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月25日 下午2:10:21
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 */
	@RequestMapping("selectRegionByPid")
	public @ResponseBody RegionReplier selectRegionByPid(@Validated(value={SelectRegionByPidGroups.class}) RegionReceiver receiver,
			BindingResult result){
		RegionReplier replier = new RegionReplier();
		try {
			getError(result);
			
			replier = service.selectRegionByPid(receiver.getPid());
			
			replier.setResult(RESULT.TRUE);
			replier.setMsg("获取列表成功");
		} catch (Exception e) {
			e.printStackTrace();
			replier.setResult(RESULT.FALSE);
			replier.setMsg(e.getMessage());
		}
		
		return replier;
		
	}
}
