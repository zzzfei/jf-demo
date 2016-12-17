package com.atguigu.jf.console.item.bean.bo;

import java.util.Date;

/**
 * @package com.atguigu.jf.console.item.bean.bo
 * @Type ItemEvaluationBean.java
 * @author 徐志超
 * @date 2016年11月10日
 * @describe 评价管理菜单使用的业务实体类
 * @version V 1.0
 */
public class ItemEvaluationBean {
	private Long evaluationId; // EVALUATION_ID
	private String userNickName; // USER_NICKNAME
	private String itemName; // ITEM_NAME
	private String itemPicUrl; // ITEM_PIC_URL
	private Short evaluationGrade; // EVALUATION_GRADE
	private String evaluationContent; // EVALUATION_CONTENT
	private String itemPicUrlEv; // ITEM_PIC_URL_EV 修改了数据库原字段
	private String mchtName; // MCHT_NAME
	private Date evaluationTime; // EVALUATION_TIME
	private Short evaluationStatus; // EVALUATION_STATUS
	private Short dataState; // DATA_STATE

	public Short getEvaluationStatus() {
		return evaluationStatus;
	}

	public void setEvaluationStatus(Short evaluationStatus) {
		this.evaluationStatus = evaluationStatus;
	}

	public Short getDataState() {
		return dataState;
	}

	public void setDataState(Short dataState) {
		this.dataState = dataState;
	}

	public Long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPicUrl() {
		return itemPicUrl;
	}

	public void setItemPicUrl(String itemPicUrl) {
		this.itemPicUrl = itemPicUrl;
	}

	public Short getEvaluationGrade() {
		return evaluationGrade;
	}

	public void setEvaluationGrade(Short evaluationGrade) {
		this.evaluationGrade = evaluationGrade;
	}

	public String getEvaluationContent() {
		return evaluationContent;
	}

	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	public String getItemPicUrlEv() {
		return itemPicUrlEv;
	}

	public void setItemPicUrlEv(String itemPicUrlEv) {
		this.itemPicUrlEv = itemPicUrlEv;
	}

	public String getMchtName() {
		return mchtName;
	}

	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}

	public Date getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(Date evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

}
