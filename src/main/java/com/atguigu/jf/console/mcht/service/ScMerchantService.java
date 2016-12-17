package com.atguigu.jf.console.mcht.service;

import java.util.List;

import com.atguigu.jf.console.mcht.bean.pojo.ScMerchant;

public interface ScMerchantService {
	
	/**
	 * @methodName: getMchtForCombo  
	 * @function: 获取商家集合，用于下拉框展示
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	List<ScMerchant> getMchtForCombo();

}
