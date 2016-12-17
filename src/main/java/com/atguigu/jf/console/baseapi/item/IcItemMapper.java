package com.atguigu.jf.console.baseapi.item;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.bo.IcItemBean;
import com.atguigu.jf.console.item.bean.pojo.IcItem;

public interface IcItemMapper {

    int deleteByPrimaryKey(Long itemId);

    int insert(IcItem record);

    int insertSelective(IcItem record);

    IcItem selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(IcItem record);

    int updateByPrimaryKey(IcItem record);
    
    /**
     * @methodName: getIcItemForList  
     * @function: 获取数据展示用的集合，使用PageHelper进行分页  
     * @param map
     * @return
     * @throws Exception
     * @author 徐志超 
     * @date 2016年11月11日
     */
	List<IcItemBean> getIcItemForList(Map<String, Object> map) throws Exception;
	
	/**
	 * @methodName: saveIcItem  
	 * @function: 保存商品  
	 * @param icItem
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	int saveIcItem(IcItem icItem);
	
	/**
	 * @methodName: getIcItemById  
	 * @function: 根据id获取商品信息 
	 * @param icItem
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	IcItem getIcItemById(IcItem icItem);
	
	/**
	 * @methodName: updateIcItem  
	 * @function: 修改商品信息
	 * @param icItem
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	int updateIcItem(IcItem icItem);
}