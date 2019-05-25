/**
 * 
 */
package com.hg.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 导航状态拦截器
 * @author Y2B项目组
 * @date 2017年03月19日 12时11分39秒
 * @version 1.0
 */
public class MenuStatusInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String s = request.getParameter("__s");
		if (StringUtils.isNotBlank(s)) {
			session.setAttribute("__s", s);
		} else if (s != null && "".equals(s.trim())) {
			session.removeAttribute("__s");
		}
		return true;
	}
}
