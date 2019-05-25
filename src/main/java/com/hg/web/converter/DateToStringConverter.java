/**
 * 
 */
package com.hg.web.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 字符串到日期的转换器类
 * @author Y2B项目组
 * @date 2017年3月29日 17时05分11秒
 * @version 1.0
 */
public class DateToStringConverter implements Converter<Date, String> {
	private String pattern;
	public DateToStringConverter(String pattern) {
		this.pattern = pattern;
	}
	@Override
	public String convert(Date date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		return new SimpleDateFormat(pattern).format(date);
	}
}
