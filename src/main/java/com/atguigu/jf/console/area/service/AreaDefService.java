package com.atguigu.jf.console.area.service;

import java.util.List;

import com.atguigu.jf.console.area.bean.bo.AreaDef;

public interface AreaDefService {
	
	/**
	 * @methodName: getAreaDef  
	 * @function: 根据传入的值不同，获取不同等级的区域菜单 
	 * @param areaDef
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	List<AreaDef> getAreaDef(AreaDef areaDef) throws Exception;
	
	/**
	 * @methodName: getAreaDefForAdv  
	 * @function: 广告模块使用的单级区域查询 
	 * @param areaDef
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月11日
	 */
	List<AreaDef> getAreaDefForAdv(AreaDef areaDef) throws Exception;

}
