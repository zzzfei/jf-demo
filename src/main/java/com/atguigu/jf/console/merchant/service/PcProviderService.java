package com.atguigu.jf.console.merchant.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.merchant.bean.pojo.PcProvider;

public interface PcProviderService {
	
	/**
	 * @methodName: getPcProviderList  
	 * @function: 获取供应商集合，用PageHelper进行分页
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	List<PcProvider> getPcProviderList(Map<String, Object> map);
	
	/**
	 * @methodName: savePcProvider  
	 * @function: 保存积分供应商  
	 * @param pcProvider
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	int savePcProvider(PcProvider pcProvider);
	
	/**
	 * @methodName: selectByPrimaryKey  
	 * @function: 根据id查询对象
	 * @param providerId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	PcProvider selectByPrimaryKey(Long providerId);
	
	/**
	 * @methodName: updatePcProvider  
	 * @function: 动态更新 
	 * @param pcProvider
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月13日
	 */
	int updatePcProvider(PcProvider pcProvider);

}
