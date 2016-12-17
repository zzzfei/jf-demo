package com.atguigu.jf.console.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.user.SysOpMapper;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SysOpMapper sysOpMapper;

	@Override
	public Integer getTotal(Map<String, Object> map) throws Exception {
		return sysOpMapper.getTotal(map);
	}

	@Override
	public List<SysOp> getSysOpList(Map<String, Object> map) throws Exception {
		return sysOpMapper.getSysOpList(map);
	}

	@Override
	public void saveSysOp(SysOp sysOp) throws Exception {
		sysOpMapper.saveSysOp(sysOp);
	}

	@Override
	public SysOp getSysOpById(Integer opId) throws Exception {
		return sysOpMapper.getSysOpById(opId);
	}

	@Override
	public void updateSysOp(SysOp sysOp) throws Exception {
		sysOpMapper.updateSysOp(sysOp);
	}

	@Override
	public List<SysOp> getSysOpListForPageInfo(Map<String, Object> map)
			throws Exception {
		return sysOpMapper.getSysOpListForPageInfo(map);
	}
	
	
}
