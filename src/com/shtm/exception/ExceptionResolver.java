package com.shtm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Title:ExceptionResolver
 * <p>
 * Description:springmvc的异常处理器
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月7日 上午9:03:08
 * @version 1.0
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("msg",ex.getMessage());
		
		mav.setViewName("exception");
		
		return mav;
	}

}
