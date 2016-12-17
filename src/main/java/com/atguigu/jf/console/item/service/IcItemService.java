package com.atguigu.jf.console.item.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.bo.IcItemBean;
import com.atguigu.jf.console.item.bean.pojo.IcItem;
import com.atguigu.jf.console.item.bean.pojo.IcItemPicture;

public interface IcItemService {
	
	/**
	 * @methodName: getIcItemForList  
	 * @function: 获取展示的数据集合，使用PageHelper进行分页
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月11日
	 */
	List<IcItemBean> getIcItemForList(Map<String, Object> map) throws Exception;
	
	/**
	 * @methodName: saveIcItem  
	 * @function: 将商品信息保存到数据库 
	 * @param icItem
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	int saveIcItem(IcItem icItem);
	
	/**
	 * @methodName: saveIcItemPicture  
	 * @function: 将商品图片保存到数据库
	 * @param icItemPicture
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	int saveIcItemPicture(IcItemPicture icItemPicture);
	
	/**
	 * @methodName: getIcItemById  
	 * @function: 获取商品对象，用于回显
	 * @param icItem
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	IcItem getIcItemById(IcItem icItem);
	
	/**
	 * @methodName: getIcItemPictureByItemId  
	 * @function: 根据商品id获取对应的商品图片类
	 * @param itemId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	IcItemPicture getIcItemPictureByItemId(Long itemId);
	
	/**
	 * @methodName: updateIcItem  
	 * @function: 修改商品状态
	 * @param icItem
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	int updateIcItem(IcItem icItem);
	
	/**
	 * @methodName: updateIcItemPicture  
	 * @function: 修改商品图片 
	 * @param icItemPicture
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	int updateIcItemPicture(IcItemPicture icItemPicture);

}
