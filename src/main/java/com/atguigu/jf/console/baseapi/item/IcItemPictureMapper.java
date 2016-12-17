package com.atguigu.jf.console.baseapi.item;

import com.atguigu.jf.console.item.bean.pojo.IcItemPicture;

public interface IcItemPictureMapper {
	
	/**
	 * @methodName: saveIcItemPicture  
	 * @function: 保存图片至数据库 
	 * @param icItemPicture
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	int saveIcItemPicture(IcItemPicture icItemPicture);
	
	/**
	 * @methodName: getIcItemPictureByItemId  
	 * @function: 获取对应商品的图片对象
	 * @param itemId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月12日
	 */
	IcItemPicture getIcItemPictureByItemId(Long itemId);
	
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
