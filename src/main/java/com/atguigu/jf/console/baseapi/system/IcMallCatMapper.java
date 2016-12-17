package com.atguigu.jf.console.baseapi.system;

import java.util.List;

import com.atguigu.jf.console.system.bean.pojo.IcMallCat;

public interface IcMallCatMapper {
	
	/**
	 * @methodName: getIcMallCatList  
	 * @function: 获取类目明细，使用PageHelper进行分页 
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月9日
	 */
	List<IcMallCat> getIcMallCatList() throws Exception;
	
	/**
	 * @methodName: saveIcMallCat  
	 * @function: 保存IcMallCat对象到数据库 
	 * @param icMallCat
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	int saveIcMallCat(IcMallCat icMallCat) throws Exception;
	
	/**
	 * @methodName: getIcMallCatById  
	 * @function: 根据id获取对象 
	 * @param mallCatId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	IcMallCat getIcMallCatByUd(Long mallCatId) throws Exception;
	
	/**
	 * @methodName: updateeIcMallCat  
	 * @function: 执行修改操作 
	 * @param icMallCat
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	int updateeIcMallCat(IcMallCat icMallCat) throws Exception;

}