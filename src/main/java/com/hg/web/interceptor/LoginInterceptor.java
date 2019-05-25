/**
 * 
 */
package com.hg.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hg.web.util.Constants;

/**
 * 登录检查拦截器类
 * @author Y2B项目组
 * @date 2017年03月16日 17时03分29秒
 * @version 1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod)handler;
			
			//读取controll类上面的NoCheckLogin注解
			NoCheckLogin noCheckLogin = method.getBeanType().getAnnotation(NoCheckLogin.class);
			if (noCheckLogin != null) {
				return true;
			}
			
			//继续读取controll类的方法上面的NoCheckLogin注解
			noCheckLogin = method.getMethodAnnotation(NoCheckLogin.class);
			if (noCheckLogin != null) {
				return true;
			}
			
			//能执行到这里来，说明controll类的方法需要登录检查
			Object object = request.getSession().getAttribute(Constants.LOGIN_USER);
			if (object == null) {
				response.sendRedirect(request.getContextPath() + "/login");
				return false;
			}
		}
		return true;
	}
}
