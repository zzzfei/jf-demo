package com.atguigu.jf.console.baseapi.item;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.bo.ItemEvaluationBean;

public interface ItemEvaluationMapper {
	
	/**
	 * @methodName: getItemEvaluation  
	 * @function: 查询集合，用PageHelper进行分页处理  
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	List<ItemEvaluationBean> getItemEvaluation(Map<String, Object> map) throws Exception;
	
	/**
	 * @methodName: update  
	 * @function: 修改评论（审判、屏蔽） 
	 * @param itemEvaluationBean
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	int update(ItemEvaluationBean itemEvaluationBean) throws Exception;
	
	/**
	 * @methodName: getItemEvaluation  
	 * @function: 通过id获取评论的对象 
	 * @param evaluationId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	ItemEvaluationBean getItemEvaluationById(Long evaluationId) throws Exception;

}
