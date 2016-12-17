package com.atguigu.jf.console.baseapi.area;

import java.util.List;

import com.atguigu.jf.console.area.bean.bo.AreaDef;

public interface AreaDefMapper {

    int deleteByPrimaryKey(String areaId);

    int insert(AreaDef record);

    int insertSelective(AreaDef record);

    AreaDef selectByPrimaryKey(String areaId);

    int updateByPrimaryKeySelective(AreaDef record);

    int updateByPrimaryKey(AreaDef record);
    
    /**
	 * @methodName: getAreaDef  
	 * @function: 根据传入的值不同，获取不同等级的区域菜单 
	 * @param areaDef
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	List<AreaDef> getAreaDef(AreaDef areaDef) throws Exception;
	
	/**
	 * @methodName: getAreaDefForAdv  
	 * @function: 广告模块使用的分级区域查询 
	 * @param areaDef
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月11日
	 */
	List<AreaDef> getAreaDefForAdv(AreaDef areaDef) throws Exception;
}