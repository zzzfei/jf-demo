package com.atguigu.jf.console.mcht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.jf.console.mcht.bean.pojo.ScMerchant;
import com.atguigu.jf.console.mcht.service.ScMerchantService;

@Controller
@RequestMapping("/ScMerchantController")
public class ScMerchantController {
	
	@Autowired
	private ScMerchantService scMerchantService;
	
	/**
	 * @methodName: getMcht  
	 * @function: 获取商家信息，用于下拉框展示
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	@ResponseBody
	@RequestMapping("/getMchtForCombo")
	public List<ScMerchant> getMchtForCombo(){
		
		List<ScMerchant> list = scMerchantService.getMchtForCombo();
		
		return list;
	}
	
}
