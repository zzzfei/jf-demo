package com.atguigu.jf.console.mcht.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.mcht.ScMerchantMapper;
import com.atguigu.jf.console.mcht.bean.pojo.ScMerchant;
import com.atguigu.jf.console.mcht.service.ScMerchantService;

@Service
public class ScMerchantServiceImpl implements ScMerchantService {
	
	@Autowired
	private ScMerchantMapper scMerchantMapper;

	@Override
	public List<ScMerchant> getMchtForCombo() {
		return scMerchantMapper.getMchtForCombo();
	}
}
