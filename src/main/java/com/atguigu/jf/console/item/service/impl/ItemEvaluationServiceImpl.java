package com.atguigu.jf.console.item.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.item.ItemEvaluationMapper;
import com.atguigu.jf.console.item.bean.bo.ItemEvaluationBean;
import com.atguigu.jf.console.item.service.ItemEvaluationService;

@Service
public class ItemEvaluationServiceImpl implements ItemEvaluationService {
	
	@Autowired
	private ItemEvaluationMapper itemEvaluationMapper;
	
	@Override
	public List<ItemEvaluationBean> getItemEvaluation(Map<String, Object> map) throws Exception {
		return itemEvaluationMapper.getItemEvaluation(map);
	}

	@Override
	public int update(ItemEvaluationBean itemEvaluationBean) throws Exception {
		return itemEvaluationMapper.update(itemEvaluationBean);
	}

	@Override
	public ItemEvaluationBean getItemEvaluationById(Long evaluationId) throws Exception {
		return itemEvaluationMapper.getItemEvaluationById(evaluationId);
	}
	
}
