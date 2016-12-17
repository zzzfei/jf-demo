package com.atguigu.jf.console.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.pojo.SysOp;

public interface UserService {
	
	/**
	 * @methodName: getTotal  
	 * @function: 获取总数量 分页
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	Integer getTotal(Map<String, Object> map) throws Exception;
	
	/**
	 * @methodName: getSysOpList  
	 * @function: 获取分页集合 
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	List<SysOp> getSysOpList(Map<String, Object> map) throws Exception;
	
	/**
	 * @methodName: saveSysOp  
	 * @function: 保存用户
	 * @param sysOp
	 * @throws Exception
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	void saveSysOp(SysOp sysOp) throws Exception;
	
	/**
	 * @methodName: getSysOpById  
	 * @function: 通过ID获取SysOp对象 
	 * @param opId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	SysOp getSysOpById(Integer opId) throws Exception;
	
	/**
	 * @methodName: updateSysOp  
	 * @function: 更新用户
	 * @param sysOp
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	void updateSysOp(SysOp sysOp) throws Exception;
	
	/**
	 * @methodName: getSysOpListForPageInfo  
	 * @function: 使用PageHelper进行分页
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	List<SysOp> getSysOpListForPageInfo(Map<String, Object> map) throws Exception;

}
