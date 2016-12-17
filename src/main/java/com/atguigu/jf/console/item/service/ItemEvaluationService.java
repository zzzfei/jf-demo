package com.atguigu.jf.console.item.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.bo.ItemEvaluationBean;

public interface ItemEvaluationService {
	
	/**
	 * @methodName: getItemEvaluation  
	 * @function: 用PageHelper查询评论分页数据  
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
	 * @function: 通过id获取评论对象 
	 * @param evaluationId
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	ItemEvaluationBean getItemEvaluationById(Long evaluationId) throws Exception;

}
