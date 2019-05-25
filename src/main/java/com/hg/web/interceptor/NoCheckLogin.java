/**
 * 
 */
package com.hg.web.interceptor;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 登录检查注解
 * @author 1511班
 * @date 2017年03月16日 17时01分12秒
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoCheckLogin {
}