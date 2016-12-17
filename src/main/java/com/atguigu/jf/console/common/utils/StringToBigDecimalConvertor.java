package com.atguigu.jf.console.common.utils;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;

/**
 * @package com.atguigu.jf.console.common.utils
 * @Type StringToBigDecimalConvertor.java
 * @author 徐志超
 * @date 2016年11月13日
 * @describe 自定义类型转换器
 * @version V 1.0
 */
public class StringToBigDecimalConvertor implements Converter<String, BigDecimal> {

	@Override
	public BigDecimal convert(String source) {
		BigDecimal data = new BigDecimal(source);
		return data;
	}

}
