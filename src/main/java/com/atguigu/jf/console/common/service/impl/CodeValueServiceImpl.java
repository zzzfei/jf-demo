package com.atguigu.jf.console.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.common.CodeValueMapper;
import com.atguigu.jf.console.common.bean.pojo.CodeValue;
import com.atguigu.jf.console.common.service.CodeValueService;

@Service
public class CodeValueServiceImpl implements CodeValueService {
	
	@Autowired
	private CodeValueMapper codeValueMapper;

	@Override
	public List<CodeValue> getListByCodeType(Integer codeType) throws Exception{
		return codeValueMapper.getListByCodeType(codeType);
	}
}
