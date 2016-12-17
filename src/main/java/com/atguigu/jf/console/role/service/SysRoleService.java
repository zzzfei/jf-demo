package com.atguigu.jf.console.role.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.role.bean.pojo.SysRole;
import com.atguigu.jf.console.role.bean.pojo.SysRoleFunc;
import com.atguigu.jf.console.user.bean.bo.SysFuncBeanForRoleTree;

public interface SysRoleService {
	
	/**
	 * @methodName: geRoleList  
	 * @function: 获取角色集合 
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	List<SysRole> geRoleList(Map<String, Object> map);
	
	/**
	 * @methodName: getRolFuncTree  
	 * @function: 获取角色菜单模块需要显示的功能树
	 * @param roleId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	List<SysFuncBeanForRoleTree> getRolFuncTree(Long roleId);
	
	/**
	 * @methodName: batchUpdateRoleFunc  
	 * @function: 角色权限更新，需要先删除后保存，在一个事务方法内操作
	 * @param list
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	int batchUpdateRoleFunc(List<SysRoleFunc> list, Long roleId);

}
