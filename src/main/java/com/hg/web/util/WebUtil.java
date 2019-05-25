package com.hg.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.LazyDynaBean;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Web工具类
 */
@SuppressWarnings("unchecked")
public abstract class WebUtil {
	
	/**
	 * 按指定的键和值保存到request作用域
	 * @param name 键
	 * @param value 值
	 */
	public static void setRequest(String name, Object value) {
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		if (!ObjectUtils.isEmpty(ra)) {
			ra.setAttribute(name, value, RequestAttributes.SCOPE_REQUEST);
		}
	}
	
	/**
	 * 按指定的键在request作用域获取值
	 * @param name 键
	 * @return 值
	 */
	public static <T> T getRequest(String name) {
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		if (!ObjectUtils.isEmpty(ra)) {
			return (T)ra.getAttribute(name,  RequestAttributes.SCOPE_REQUEST);
		}
		return null;
	}
	
	/**
	 * 按指定的键和值保存到session作用域
	 * @param name 键
	 * @param value 值
	 */
	public static void setSession(String name,Object value) {
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		if (!ObjectUtils.isEmpty(ra)) {
			ra.setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
		}
	}
	
	/**
	 * 按指定的键在session作用域获取值
	 * @param name 键
	 * @return 值
	 */
	public static <T> T getSession(String name) {
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		if (!ObjectUtils.isEmpty(ra)) {
			return (T)ra.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
		}
		return null;
	}
	
	/**
	 * 按指定的键在session作用域移除对象
	 * @param name 键
	 */
	public static void removeSession(String name) {
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		if (!ObjectUtils.isEmpty(ra)) {
			ra.removeAttribute(name, RequestAttributes.SCOPE_SESSION);
		}
	}
	
	/**
	 * 获取HttpServletRequest对象
	 * @return HttpServletRequest对象
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
	}
	
	/**
	 * 获取应用的指定目录的路径
	 * @param dir 目录
	 * @return 指定目录的路径
	 */
	public static String getRealPath(String dir) {
		return getRequest().getServletContext().getRealPath(dir);
	}
	
	/**
	 * 获取应用基准路径
	 * @return 基准路径
	 */
	public static String getBasePath() {
		HttpServletRequest request = getRequest();
		String path = request.getContextPath();
		return request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
	}
	
	/**
	 * 获取客户端IP
	 * @return 客户端IP
	 */
	public static String getIP() {
		return getRequest().getRemoteAddr();
	}
	
	/**
	 * 直接采集表单元素的单值
	 * @param name 元素名称
	 * @return 元素值
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}
	
	/**
	 * 直接采集表单元素的多值
	 * @param name 元素名称
	 * @return 元素值
	 */
	public static String[] getParameterValues(String name) {
		return getRequest().getParameterValues(name);
	}
	
	/**
	 * 判断当前请求是否为AJAX请求
	 * @param request 请求对象
	 * @return 是否为AJAX请求，true-是，false-否
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
	
	/**
	 * 转换Spring MVC验证消息
	 * @param fieldErrors 验证消息
	 * @return 转换后的验证消息，如果fieldErrors为null或长度为0，则返回null
	 */
	public static List<Map<String, Object>> convertErrors(List<FieldError> fieldErrors) {
		if (ObjectUtils.isEmpty(fieldErrors)) {
			return null;
		}
		List<Map<String, Object>> errorList = new ArrayList<>();
		for (FieldError error : fieldErrors) {
			LazyDynaBean bean = new LazyDynaBean();
			bean.set("name", error.getField());
			bean.set("message", error.getDefaultMessage());
			errorList.add(bean.getMap());
		}
		return errorList;
	}
}