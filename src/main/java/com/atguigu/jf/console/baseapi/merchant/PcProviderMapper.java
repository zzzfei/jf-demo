package com.atguigu.jf.console.baseapi.merchant;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.merchant.bean.pojo.PcProvider;

public interface PcProviderMapper {

    PcProvider selectByPrimaryKey(Long providerId);
    
    /**
     * @methodName: updateByPrimaryKeySelective  
     * @function: 选择性更新对象
     * @param record
     * @return
     * @author 徐志超 
     * @date 2016年11月13日
     */
    int updateByPrimaryKeySelective(PcProvider record);
    
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
	
}