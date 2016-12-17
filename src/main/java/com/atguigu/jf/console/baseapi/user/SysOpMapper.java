package com.atguigu.jf.console.baseapi.user;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.pojo.SysOp;

public interface SysOpMapper {
    int deleteByPrimaryKey(Long opId);

    int insert(SysOp record);

    int insertSelective(SysOp record);

    SysOp selectByPrimaryKey(Long opId);

    int updateByPrimaryKeySelective(SysOp record);

    int updateByPrimaryKey(SysOp record);
    
    /**
     * @方法名: selectSysOpByUnameAndPwd  
     * @功能描述: 根据用户名密码查询
     * @param sysOp
     * @return
     * @throws Exception
     * @作者  
     * @日期 2016年11月5日
     */
    public SysOp selectSysOpByUnameAndPwd(SysOp sysOp) throws Exception;
    
    /**
     * 
     * @methodName: getTotal  
     * @function: 获取分页总记录
     * @param map
     * @return
     * @author 徐志超 
     * @date 2016年11月7日
     */
	Integer getTotal(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @methodName: getSysOpList  
	 * @function: 获取分页  
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月7日
	 */
	List<SysOp> getSysOpList(Map<String, Object> map) throws Exception;
	
	/**
	 * @methodName: saveSysOp  
	 * @function: 保存用户 
	 * @param sysOp
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	void saveSysOp(SysOp sysOp) throws Exception;
	
	/**
	 * @methodName: getSysOpById  
	 * @function: 通过ID查询SysOp对象
	 * @param opId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	SysOp getSysOpById(Integer opId) throws Exception;
	
	/**
	 * @methodName: updateSysOp  
	 * @function: 更新对象 
	 * @param sysOp
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	void updateSysOp(SysOp sysOp) throws Exception;
	
	/**
	 * @methodName: getSysOpListForPageInfo  
	 * @function: PageHelper 分页 
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月8日
	 */
	List<SysOp> getSysOpListForPageInfo(Map<String, Object> map) throws Exception;
    
    
}