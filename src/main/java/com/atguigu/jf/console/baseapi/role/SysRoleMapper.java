package com.atguigu.jf.console.baseapi.role;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.role.bean.pojo.SysRole;

public interface SysRoleMapper {

    int deleteByPrimaryKey(Long roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    /**
     * @methodName: geRoleList  
     * @function: 获取角色集合，用PageHelper进行分页  
     * @param map
     * @return
     * @author 徐志超 
     * @date 2016年11月12日
     */
	List<SysRole> geRoleList(Map<String, Object> map);
}