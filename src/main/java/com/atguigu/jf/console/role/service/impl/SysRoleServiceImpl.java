package com.atguigu.jf.console.role.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.role.SysRoleMapper;
import com.atguigu.jf.console.baseapi.user.SysFuncMapper;
import com.atguigu.jf.console.role.bean.pojo.SysRole;
import com.atguigu.jf.console.role.bean.pojo.SysRoleFunc;
import com.atguigu.jf.console.role.service.SysRoleService;
import com.atguigu.jf.console.user.bean.bo.SysFuncBeanForRoleTree;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysFuncMapper sysFuncMapper;

	@Override
	public List<SysRole> geRoleList(Map<String, Object> map) {
		return sysRoleMapper.geRoleList(map);
	}

	@Override
	public List<SysFuncBeanForRoleTree> getRolFuncTree(Long roleId) {
		return sysFuncMapper.getRolFuncTree(roleId);
	}

	@Override
	public int batchUpdateRoleFunc(List<SysRoleFunc> list, Long roleId) {
		sysFuncMapper.removeFunc(roleId);
		
		int i = 0;
		// 若清除权限则无需进行批量更新
		if(list.size() > 0){
			i = sysFuncMapper.batchSaveRoleFunc(list);
		}
		
		return i;
	}

/*	@Override
	public void removeFunc(Long roleId) {
		sysFuncMapper.removeFunc(roleId);
	}*/
}
