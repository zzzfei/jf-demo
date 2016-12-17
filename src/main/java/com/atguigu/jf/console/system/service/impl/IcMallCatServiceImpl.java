package com.atguigu.jf.console.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.system.IcMallCatMapper;
import com.atguigu.jf.console.system.bean.pojo.IcMallCat;
import com.atguigu.jf.console.system.service.IcMallCatService;

@Service
public class IcMallCatServiceImpl implements IcMallCatService {
	
	@Autowired
	private IcMallCatMapper icMallCatMapper;

	@Override
	public List<IcMallCat> getIcMallCatList() throws Exception {
		return icMallCatMapper.getIcMallCatList();
	}

	@Override
	public int saveIcMallCat(IcMallCat icMallCat) throws Exception {
		return icMallCatMapper.saveIcMallCat(icMallCat);
	}

	@Override
	public IcMallCat getIcMallCatById(Long mallCatId) throws Exception {
		return icMallCatMapper.getIcMallCatByUd(mallCatId);
	}

	@Override
	public int updateeIcMallCat(IcMallCat icMallCat) throws Exception {
		return icMallCatMapper.updateeIcMallCat(icMallCat);
	}
	
	
	
}
