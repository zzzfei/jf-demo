package com.atguigu.jf.console.area.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.area.bean.bo.AreaDef;
import com.atguigu.jf.console.area.service.AreaDefService;
import com.atguigu.jf.console.baseapi.area.AreaDefMapper;

@Service
public class AreaDefServiceImpl implements AreaDefService {
	
	@Autowired
	private AreaDefMapper areaDefMapper;

	@Override
	public List<AreaDef> getAreaDef(AreaDef areaDef) throws Exception {
		return areaDefMapper.getAreaDef(areaDef);
	}

	@Override
	public List<AreaDef> getAreaDefForAdv(AreaDef areaDef) throws Exception {
		return areaDefMapper.getAreaDefForAdv(areaDef);
	}
	
	
}
