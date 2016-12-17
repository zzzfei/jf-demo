package com.atguigu.jf.console.baseapi.mcht;

import java.util.List;

import com.atguigu.jf.console.mcht.bean.pojo.ScMerchant;

public interface ScMerchantMapper {

    int deleteByPrimaryKey(Long mchtId);

    int insert(ScMerchant record);

    int insertSelective(ScMerchant record);

    ScMerchant selectByPrimaryKey(Long mchtId);

    int updateByPrimaryKeySelective(ScMerchant record);

    int updateByPrimaryKey(ScMerchant record);
    
    /**
     * @methodName: getMchtForCombo  
     * @function: 获取商家集合，用于下拉框展示
     * @return
     * @author 徐志超 
     * @date 2016年11月12日
     */
	List<ScMerchant> getMchtForCombo();
}