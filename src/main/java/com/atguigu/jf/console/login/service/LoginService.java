package com.atguigu.jf.console.login.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;


public interface LoginService {
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
     * @methodName: getMenuByOpId  
     * @function: 获取指定用户功能菜单 
     * @param map
     * @return
     * @author 徐志超 
     * @date 2016年11月7日
     */
	public List<SysFuncBean> getMenuByOpId(Map<String, Object> map) throws Exception;
}
