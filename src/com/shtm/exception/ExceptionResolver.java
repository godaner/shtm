package com.shtm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.r;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.shtm.manage.po.Replier;
import com.shtm.util.Static.RESULT;
import com.shtm.util.Util;

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
		
		ex.printStackTrace();
		
		Replier r = new Replier();
		
		if(ex instanceof org.apache.shiro.authz.UnauthenticatedException){
			r.setMsg("ExceptionResolver:您没有登录");
			
			r.setResult(RESULT.UNONLINE);
			
			Util.writeJSON(r, response);
			
			return null;
		}
		
		
		
		
		if(ex instanceof org.apache.shiro.authz.UnauthorizedException){
			r.setMsg("ExceptionResolver:您没有权限");

			Util.writeJSON(r, response);
			

			r.setResult(RESULT.FALSE);
			
			return null;
		}
		
		return null;
	}

}
