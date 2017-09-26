package com.shtm.manage.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.po.ClazzsReplier;
import com.shtm.manage.service.ClazzsServiceI;

/**
 * Title:ClazzsController
 * <p>
 * Description:clazzs的controller
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月26日 上午9:40:17
 * @version 1.0
 */
@RequestMapping("/clazzs")
@Scope("prototype")
@Controller
public class ClazzsController extends BaseController<ClazzsServiceI> {

	/**
	 * Title:selectClazzs
	 * <p>
	 * Description:查詢clazzs
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月26日 上午10:07:06
	 * @version 1.0
	 * @return
	 */
	@RequestMapping("/selectClazzs")
	public @ResponseBody ClazzsReplier selectClazzs() throws Exception{
		ClazzsReplier replier = new ClazzsReplier();
		try {
			replier = service.selectClazzs();
			
			replier.setMsg("獲取成功");
			replier.setResult(RESULT.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			replier.setMsg(e.getMessage());
			replier.setResult(RESULT.FALSE);
		}
		
		return replier;
	}
}
