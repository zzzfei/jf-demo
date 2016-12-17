package com.atguigu.jf.console.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.item.bean.bo.ItemEvaluationBean;
import com.atguigu.jf.console.item.service.ItemEvaluationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/item")
public class ItemEvaluationController {
	
	@Autowired
	private ItemEvaluationService itemEvaluationService;
	
	/**
	 * @methodName: getItemEvaluation  
	 * @function: 用PageHelper 查询分页数据
	 * @param page
	 * @param limit
	 * @param itemEvaluationBean
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	@ResponseBody
	@RequestMapping("/getItemEvaluation")
	public String getItemEvaluation(Integer page, Integer limit, 
												ItemEvaluationBean itemEvaluationBean) throws Exception{
		
		// 设置查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		if(itemEvaluationBean != null){
			Short evaluationStatus = itemEvaluationBean.getEvaluationStatus();
			String userNickName = itemEvaluationBean.getUserNickName();
			map.put("evaluationStatus", evaluationStatus);
			if(userNickName != ""){
				map.put("userNickName", userNickName);
			}
		}
		
		// 设置分页
		PageHelper.startPage(page, limit);
		
		// 查询集合
		List<ItemEvaluationBean> list = itemEvaluationService.getItemEvaluation(map);
		
		// 用PageInfo包装集合
		PageInfo<ItemEvaluationBean> pageInfo = new PageInfo<>(list);
		
		// 设定返回对象中的日期格式
		JSON.DEFFAULT_DATE_FORMAT = "yyyy:MM:dd hh:mm:ss";
		
		// 可以直接返回pageInfo对象或者使用FastJSON将对象转换为json字符串
		return JSON.toJSONString(pageInfo, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * @methodName: examineItamEvaluation  
	 * @function: 屏蔽操作
	 * @param itemEvaluationBean
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	@ResponseBody
	@RequestMapping("/examine")
	public ResultBean examineItamEvaluation(ItemEvaluationBean itemEvaluationBean) throws Exception{
		// 执行审判操作
		int i = itemEvaluationService.update(itemEvaluationBean);
		
		ResultBean resultBean = new ResultBean();
		
		if(i == 0 ){
			resultBean.setResult(ResultBean.ERROR_RESULT);
			resultBean.setMsg("操作失败，请联系管理员");
		}else{
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
			resultBean.setMsg("操作成功");
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: delete  
	 * @function: 屏蔽操作
	 * @param itemEvaluationBean
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public ResultBean delete(ItemEvaluationBean itemEvaluationBean) throws Exception{
		
		itemEvaluationBean.setDataState(new Short("2"));
		
		// 执行审判操作
		int i = itemEvaluationService.update(itemEvaluationBean);
		
		ResultBean resultBean = new ResultBean();
		
		if(i == 0 ){
			resultBean.setResult(ResultBean.ERROR_RESULT);
			resultBean.setMsg("操作失败，请联系管理员");
		}else{
			resultBean.setResult(ResultBean.SUCCESS_RESULT);
			resultBean.setMsg("操作成功");
		}
		
		return resultBean;
	}
	
	/**
	 * @methodName: detail  
	 * @function: 显示评论详情
	 * @param evaluationId 通过id获取对象
	 * @param map
	 * @return
	 * @author 徐志超 
	 * @date 2016年11月10日
	 */
	@RequestMapping("/detail")
	public String detail(Long evaluationId, Map<String, Object> map) throws Exception{
		// 根据id获取对象
		ItemEvaluationBean itemEvaluationBean = itemEvaluationService.getItemEvaluationById(evaluationId);
		
		map.put("itemEvaluationBean", itemEvaluationBean);
		
		return "item/itemEvaluationDetail";
	}
}
