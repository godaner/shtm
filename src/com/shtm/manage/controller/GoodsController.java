package com.shtm.manage.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shtm.controller.BaseController;
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
	
	
}
