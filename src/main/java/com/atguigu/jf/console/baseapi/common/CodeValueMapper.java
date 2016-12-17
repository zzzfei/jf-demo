package com.atguigu.jf.console.baseapi.common;

import java.util.List;

import com.atguigu.jf.console.common.bean.pojo.CodeValue;

public interface CodeValueMapper {
	
	/**
	 * @methodName: getListByCodeType  
	 * @function: 获取下拉框内容  
	 * @param codeType
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月7日
	 */
	List<CodeValue> getListByCodeType(Integer codeType) throws Exception;

}
