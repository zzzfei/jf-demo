package com.atguigu.jf.console.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.jf.console.common.bean.pojo.CodeValue;
import com.atguigu.jf.console.common.service.CodeValueService;

@Controller
@RequestMapping("/codeValue")
public class CodeValueController {
	
	@Autowired
	private CodeValueService codeValueService;
	
	/**
	 * @methodName: getCodeValue  
	 * @function: 获取下拉框内容
	 * @param codeType
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月7日
	 */
	@ResponseBody
	@RequestMapping("/getCodeValue")
	public List<CodeValue> getCodeValue(Integer codeType) throws Exception{
		
		// 获取下拉框内容
		List<CodeValue> codeValueList = codeValueService.getListByCodeType(codeType);
		
		return codeValueList;
	}
}
