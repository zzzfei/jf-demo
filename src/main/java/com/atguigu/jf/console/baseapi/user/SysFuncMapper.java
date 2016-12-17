package com.atguigu.jf.console.baseapi.user;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.role.bean.pojo.SysRoleFunc;
import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.bo.SysFuncBeanForRoleTree;

public interface SysFuncMapper {
	
    /**
     * 获取指定用户的功能菜单
     * @methodName: getMenuByOpId  
     * @function:   
     * @param map
     * @return
     * @author 徐志超 
     * @date 2016年11月7日
     */
	List<SysFuncBean> getMenuByOpId(Map<String, Object> map) throws Exception;
	
	/**
	 * @methodName: getRolFuncTree  
	 * @function: 获取角色相应的权限树
	 * @param integer
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	List<SysFuncBeanForRoleTree> getRolFuncTree(Long integer);
	
	/**
	 * @methodName: removeFunc  
	 * @function: 从中间表删除角色功能 
	 * @param roleId
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	void removeFunc(Long roleId);
	
	/**
	 * @methodName: batchSaveRoleFunc  
	 * @function: 批量保存角色功能  
	 * @param list
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	int batchSaveRoleFunc(List<SysRoleFunc> list);
}
