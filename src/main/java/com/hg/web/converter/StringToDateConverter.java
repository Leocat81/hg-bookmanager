/**
 * 
 */
package com.hg.web.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 字符串到日期的转换器类
 * @author Y2B项目组
 * @date 2017年3月29日 17时01分23秒
 * @version 1.0
 */
public class StringToDateConverter implements Converter<String, Date> {
	private final Logger log = LogManager.getLogger(StringToDateConverter.class);
	private DateFormat format;
	private String pattern;
	
	public StringToDateConverter(String pattern) {
		Validate.notNull(pattern, "格式字符串集不能为空！");
		this.pattern = pattern;
		this.format = new SimpleDateFormat(pattern);
	}
	
	@Override
	public Date convert(String text) {
		if (!StringUtils.hasText(text)) {
			return null;
		}
		try {
			return format.parse(text);
		} catch (ParseException e) {
			log.warn("尝试使用[" + pattern + "]格式转换" + text + "为java.util.Date类型失败！");
			return null;
		}
	}
}
