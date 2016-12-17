package com.atguigu.jf.console.merchant.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.merchant.PcProviderMapper;
import com.atguigu.jf.console.merchant.bean.pojo.PcProvider;
import com.atguigu.jf.console.merchant.service.PcProviderService;

@Service
public class PcProviderServiceImpl implements PcProviderService {
	
	@Autowired
	private PcProviderMapper pcProviderMapper;

	@Override
	public List<PcProvider> getPcProviderList(Map<String, Object> map) {
		return pcProviderMapper.getPcProviderList(map);
	}

	@Override
	public int savePcProvider(PcProvider pcProvider) {
		return pcProviderMapper.savePcProvider(pcProvider);
	}

	@Override
	public PcProvider selectByPrimaryKey(Long providerId) {
		return pcProviderMapper.selectByPrimaryKey(providerId);
	}

	@Override
	public int updatePcProvider(PcProvider pcProvider) {
		return pcProviderMapper.updateByPrimaryKeySelective(pcProvider);
	}
}
