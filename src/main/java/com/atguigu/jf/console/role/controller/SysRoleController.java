package com.atguigu.jf.console.role.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.common.utils.GlobalName;
import com.atguigu.jf.console.role.bean.pojo.SysRole;
import com.atguigu.jf.console.role.bean.pojo.SysRoleFunc;
import com.atguigu.jf.console.role.service.SysRoleService;
import com.atguigu.jf.console.user.bean.bo.SysFuncBeanForRoleTree;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/role")
public class SysRoleController {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * @methodName: getRoleList  
	 * @function: 获取角色集合 
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	@ResponseBody
	@RequestMapping("/getRoleList")
	public String getRoleList(Integer page, Integer limit, SysRole sysRole){
		Map<String, Object> map = new HashMap<String, Object>();
		if(sysRole != null){
			map.put("roleName", sysRole.getRoleName());
		}
		
		//设置分页信息
		PageHelper.startPage(page, limit);
		
		// 获取集合
		List<SysRole> list = sysRoleService.geRoleList(map);
		// 用分页类包装集合
		PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);
		
		// 转化为JSON数据并返回
		return JSON.toJSONString(pageInfo);
	}
	
	/**
	 * @methodName: getRolFuncTree  
	 * @function: 获取角色菜单模块需要显示的功能树
	 * @param roleId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	@ResponseBody
	@RequestMapping("/getRolFuncTree")
	public String getRolFuncTree(Long roleId){
		
	    List<SysFuncBeanForRoleTree> list =	sysRoleService.getRolFuncTree(roleId);
		
		return JSON.toJSONString(list);
	}
	
	/**
	 * @methodName: updateRoleFunc  
	 * @function: 获取选中的funcId数组 
	 * @param funcIdArr
	 * @param roleId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	@ResponseBody
	@RequestMapping("/updateRoleFunc")
	public ResultBean updateRoleFunc(Long[] funcIdArr, Long roleId, HttpSession session){
		ResultBean resultBean = new ResultBean();
		// 获取操作人和操作时间信息
		SysOp sysOp = (SysOp) session.getAttribute(GlobalName.LOGIN_SYS_OP);
		if(sysOp == null){
			resultBean.setMsg("操作失败，请登录后操作!");
			return resultBean;
		}
		Date date = new Date();
		
		// 生成SysRoleFunc集合
		List<SysRoleFunc> list = new ArrayList<>();
		for (int i = 0; i < funcIdArr.length; i++) {
			SysRoleFunc roleFunc = new SysRoleFunc();
			roleFunc.setCreator(sysOp.getOpId());
			roleFunc.setCreateDate(date);
			roleFunc.setRoleId(roleId);
			roleFunc.setFuncId(funcIdArr[i]);
			roleFunc.setDataState(new Short("1"));
			
			list.add(roleFunc);
		}
		
		/**
		 * 批量执行更新，需要先清除后保存，必须在一个事务内操作
		 */
		int i = sysRoleService.batchUpdateRoleFunc(list, roleId);
		
		
		if(i > 0){
			resultBean.setMsg("操作成功");
		}else{
			resultBean.setMsg("清除成功");
		}
		
		return resultBean;
	}
}
