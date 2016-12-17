package com.atguigu.jf.console.adv.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.adv.bean.bo.IcAdvBean;
import com.atguigu.jf.console.adv.bean.pojo.IcAdv;
import com.atguigu.jf.console.adv.service.IcAdvService;
import com.atguigu.jf.console.baseapi.adv.IcAdvMapper;

@Service
public class IcAdvServiceImpl implements IcAdvService {
	
	@Autowired
	private IcAdvMapper icAdvMapper;

	@Override
	public List<IcAdvBean> getIcAdvList(Map<String, Object> map) throws Exception{
		return icAdvMapper.getIcAdvList(map);
	}

	@Override
	public int updateIcAdv(IcAdv icAdv) throws Exception {
		return icAdvMapper.updateIcAdv(icAdv);
	}

	@Override
	public int save(IcAdv icAdv) throws Exception {
		return icAdvMapper.save(icAdv);
	}

	@Override
	public Long getMaxOrder() throws Exception {
		return icAdvMapper.getMaxOrder();
	}

	@Override
	public IcAdv getIcAdvById(Long advId) throws Exception {
		return icAdvMapper.getIcAdvById(advId);
	}

	@Override
	public List<IcAdv> getIcAdvListForOrder(Map<String, Object> map) throws Exception {
		return icAdvMapper.getIcAdvListForOrder(map);
	}

	@Override
	public void updateOrder(IcAdv currentIcAdv, IcAdv preIcAdv) throws Exception {
		icAdvMapper.updateIcAdv(currentIcAdv);
		icAdvMapper.updateIcAdv(preIcAdv);
	}
}
