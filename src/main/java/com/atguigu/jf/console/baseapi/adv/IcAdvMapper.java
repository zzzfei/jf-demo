package com.atguigu.jf.console.baseapi.adv;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.adv.bean.bo.IcAdvBean;
import com.atguigu.jf.console.adv.bean.pojo.IcAdv;

public interface IcAdvMapper {
	
    int deleteByPrimaryKey(Long advId);

    int insert(IcAdv record);

    int insertSelective(IcAdv record);

    IcAdv selectByPrimaryKey(Long advId);

    int updateByPrimaryKeySelective(IcAdv record);

    int updateByPrimaryKey(IcAdv record);
    
    /**
     * @methodName: getIcAdvList  
     * @function: 查询广告列表，使用PageHelper  
     * @param map
     * @return
     * @author 徐志超 
     * @date 2016年11月10日
     */
	List<IcAdvBean> getIcAdvList(Map<String, Object> map) throws Exception;
	
	/**
	 * @methodName: updateIcAdv  
	 * @function: 更新广告状态
	 * @param icAdvBean
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月11日
	 */
	int updateIcAdv(IcAdv icAdv) throws Exception;
	
	/**
	 * @methodName: save  
	 * @function: 保存广告位
	 * @param icAdv
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月11日
	 */
	int save(IcAdv icAdv) throws Exception;
	
	/**
	 * @methodName: getMaxOrder  
	 * @function: 获取当前最大的order 
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月11日
	 */
	Long getMaxOrder() throws Exception;
	
	/**
	 * @methodName: getIcAdvById  
	 * @function: 根据id获取对象
	 * @param advId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月11日
	 */
	IcAdv getIcAdvById(Long advId) throws Exception;
	
	/**
	 * @methodName: getIcAdvListForOrder  
	 * @function: 获取所有已上架的广告明细
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月11日
	 */
	List<IcAdv> getIcAdvListForOrder(Map<String, Object> map) throws Exception;
	
}
