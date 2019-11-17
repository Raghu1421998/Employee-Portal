
package com.jamochatech.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.jamochatech.controller.ExcelRead;

@Component
public class SpringInterceptor extends HandlerInterceptorAdapter
{
	private static final Logger OUT = LoggerFactory.getLogger(ExcelRead.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if (request.getSession(false).getAttribute("name") == null || request.getSession(false) == null)
		{
			response.sendRedirect("/MyBatisCrud1");
			return false;
		}
		else
		{
			response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "0");
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
	{
		OUT.debug("Exception occurs");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	{
		OUT.debug("Exception occurs");
	}
}
