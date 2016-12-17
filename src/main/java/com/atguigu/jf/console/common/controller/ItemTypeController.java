package com.atguigu.jf.console.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.jf.console.common.bean.pojo.ItemType;
import com.atguigu.jf.console.common.service.ItemTypeService;

@Controller
@RequestMapping("/itemType")
public class ItemTypeController {
	
	@Autowired
	private ItemTypeService itemTypeService;
	
	/**
	 * @methodName: getItemType  
	 * @function: 获取商品类别，用于下拉框展示
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	@ResponseBody
	@RequestMapping("/getItemType")
	public List<ItemType> getItemType(){
		
		List<ItemType> itemTypeList = itemTypeService.getItemType();
		
		return itemTypeList;
	}
}
