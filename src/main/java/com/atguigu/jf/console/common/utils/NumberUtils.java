package com.atguigu.jf.console.common.utils;

import java.math.BigDecimal;

/**
 * @package com.atguigu.jf.console.common.utils
 * @Type NumberUtils.java
 * @author 徐志超
 * @date 2016年11月13日
 * @describe  用于BigDecimal类型转换
 * @version V 1.0
 */
public class NumberUtils {

	public static int parseInt(long l) {
		return BigDecimal.valueOf(l).intValue();
	}

	public static long parseLong(String s) {
		return Long.parseLong(s.trim());
	}

	public static BigDecimal getBigDecimal(String s) {
		return BigDecimal.valueOf(Long.parseLong(s.trim()));
	}
}
