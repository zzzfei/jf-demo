package com.atguigu.jf.console.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.common.ItemTypeMapper;
import com.atguigu.jf.console.common.bean.pojo.ItemType;
import com.atguigu.jf.console.common.service.ItemTypeService;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {
	
	@Autowired
	private ItemTypeMapper itemTypeMapper;
	
	@Override
	public List<ItemType> getItemType() {
		return itemTypeMapper.getItemType();
	}

}
