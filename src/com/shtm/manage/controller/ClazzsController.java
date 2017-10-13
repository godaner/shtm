package com.shtm.manage.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shtm.controller.BaseController;
import com.shtm.manage.groups.ClazzsGroups.DeleteClazzGroups;
import com.shtm.manage.groups.ClazzsGroups.InsertClazzGroups;
import com.shtm.manage.groups.ClazzsGroups.SelectClazzsDatagridGroups;
import com.shtm.manage.groups.ClazzsGroups.UpdateClazzGroups;
import com.shtm.manage.po.ClazzsReceiver;
import com.shtm.manage.po.ClazzsReplier;
import com.shtm.manage.service.ClazzsServiceI;

/**
 * Title:ClazzsController
 * <p>
 * Description:clazzs的controller
 * <p>
 * 
 * @author Kor_Zhang
 * @date 2017年9月26日 上午9:40:17
 * @version 1.0
 */
@RequestMapping("/clazzs")
@Scope("prototype")
@Controller
public class ClazzsController extends BaseController<ClazzsServiceI> {

	/**
	 * 描述:查詢所有的clazzs;
	 * @author xiaoyu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectAllClazzs")
	public @ResponseBody ClazzsReplier selectAllClazzs() throws Exception {
		ClazzsReplier replier = new ClazzsReplier();
		try {
			replier = service.selectAllClazzs();

			replier.setMsg("獲取成功");
			replier.setResult(RESULT.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			replier.setMsg(e.getMessage());
			replier.setResult(RESULT.FALSE);
		}

		return replier;
	}
	
	/**
	 * Title:selectClazzs
	 * <p>
	 * Description:查詢easyui所需的clazzs列表;
	 * <p>
	 * 
	 * @author Kor_Zhang
	 * @date 2017年9月26日 上午10:07:06
	 * @version 1.0
	 * @return
	 */
	@RequestMapping("/selectClazzsDatagrid")
	public @ResponseBody ClazzsReplier selectClazzsDatagrid(@Validated(value={SelectClazzsDatagridGroups.class}) ClazzsReceiver receiver,
			BindingResult result) throws Exception {
		
		ClazzsReplier replier = new ClazzsReplier();
		try {
			
			getError(result);
			
			replier = service.selectClazzsDatagrid(receiver);

			replier.setMsg("獲取成功");
			replier.setResult(RESULT.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			replier.setMsg(e.getMessage());
			replier.setResult(RESULT.FALSE);
		}

		return replier;
	}
	
	/**
	 * Title:
	 * <p>
	 * Description:删除clazzs
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 上午9:26:38
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteClazz")
	public @ResponseBody ClazzsReplier deleteClazz(@Validated(value={DeleteClazzGroups.class}) ClazzsReceiver receiver,
			BindingResult result) throws Exception {
		
		ClazzsReplier replier = new ClazzsReplier();
		try {
			
			getError(result);
			
			service.deleteClazz(receiver);

			replier.setMsg("删除成功");
			replier.setResult(RESULT.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			replier.setMsg(e.getMessage());
			replier.setResult(RESULT.FALSE);
		}

		return replier;
	}
	
	/**
	 * Title:
	 * <p>
	 * Description:新增
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 上午9:26:38
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertClazz")
	public @ResponseBody ClazzsReplier insertClazz(@Validated(value={InsertClazzGroups.class}) ClazzsReceiver receiver,
			BindingResult result) throws Exception {
		
		ClazzsReplier replier = new ClazzsReplier();
		try {
			
			getError(result);
			
			replier = service.insertClazz(receiver);

			replier.setMsg("插入數據成功");
			replier.setResult(RESULT.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			replier.setMsg(e.getMessage());
			replier.setResult(RESULT.FALSE);
		}

		return replier;
	}
	
	/**
	 * 
	 * Title:updateClazzs
	 * <p>
	 * Description:更新類型
	 * <p>
	 * @author Kor_Zhang
	 * @date 2017年9月29日 上午9:37:07
	 * @version 1.0
	 * @param receiver
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateClazz")
	public @ResponseBody ClazzsReplier updateClazz(@Validated(value={UpdateClazzGroups.class}) ClazzsReceiver receiver,
			BindingResult result) throws Exception {
		
		ClazzsReplier replier = new ClazzsReplier();
		try {
			
			getError(result);
			
			service.updateClazz(receiver);

			replier.setMsg("更新數據成功");
			replier.setResult(RESULT.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			replier.setMsg(e.getMessage());
			replier.setResult(RESULT.FALSE);
		}

		return replier;
	}
}
