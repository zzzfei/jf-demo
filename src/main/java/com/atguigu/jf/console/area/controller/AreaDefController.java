package com.atguigu.jf.console.area.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.atguigu.jf.console.area.bean.bo.AreaDef;
import com.atguigu.jf.console.area.service.AreaDefService;

@Controller
@RequestMapping("/areaDef")
public class AreaDefController {
	
	@Autowired
	private AreaDefService areaDefService;
	
	/**
	 * @methodName: getAreaDef  
	 * @function: 区域三级联动使用
	 * @param areaDef
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	@ResponseBody
	@RequestMapping("/getAreaDef")
	public String getAreaDef(AreaDef areaDef) throws Exception{
		if(areaDef != null){
			if("".equals(areaDef.getSupAreaId())){
				areaDef.setSupAreaId(null);
			}
		}
		
		List<AreaDef> list = areaDefService.getAreaDef(areaDef);
		
		return JSON.toJSONString(list);
	} 
	
	/**
	 * @methodName: getAreaDefForAdv  
	 * @function: 广告模块使用1、2级区域查询
	 * @param areaDef
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月11日
	 */
	@ResponseBody
	@RequestMapping("/getAreaDefForAdv")
	public String getAreaDefForAdv(AreaDef areaDef) throws Exception{
		if(areaDef != null){
			if("".equals(areaDef.getSupAreaId())){
				areaDef.setSupAreaId(null);
			}
		}
		
		List<AreaDef> list = areaDefService.getAreaDefForAdv(areaDef);
		
		return JSON.toJSONString(list);
	} 
}
