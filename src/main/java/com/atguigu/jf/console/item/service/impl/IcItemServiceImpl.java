package com.atguigu.jf.console.item.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.item.IcItemMapper;
import com.atguigu.jf.console.baseapi.item.IcItemPictureMapper;
import com.atguigu.jf.console.item.bean.bo.IcItemBean;
import com.atguigu.jf.console.item.bean.pojo.IcItem;
import com.atguigu.jf.console.item.bean.pojo.IcItemPicture;
import com.atguigu.jf.console.item.service.IcItemService;

@Service
public class IcItemServiceImpl implements IcItemService {
	
	@Autowired
	private IcItemMapper icItemMapper;
	
	@Autowired
	private IcItemPictureMapper icItemPictureMapper;

	@Override
	public List<IcItemBean> getIcItemForList(Map<String, Object> map) throws Exception {
		return icItemMapper.getIcItemForList(map);
	}

	@Override
	public int saveIcItem(IcItem icItem) {
		return icItemMapper.saveIcItem(icItem);
	}

	@Override
	public int saveIcItemPicture(IcItemPicture icItemPicture) {
		return icItemPictureMapper.saveIcItemPicture(icItemPicture);
	}

	@Override
	public IcItem getIcItemById(IcItem icItem) {
		return icItemMapper.getIcItemById(icItem);
	}

	@Override
	public IcItemPicture getIcItemPictureByItemId(Long itemId) {
		return icItemPictureMapper.getIcItemPictureByItemId(itemId);
	}

	@Override
	public int updateIcItem(IcItem icItem) {
		return icItemMapper.updateIcItem(icItem);
	}

	@Override
	public int updateIcItemPicture(IcItemPicture icItemPicture) {
		return icItemPictureMapper.updateIcItemPicture(icItemPicture);
	}
}
