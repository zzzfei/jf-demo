package com.atguigu.jf.console.common.bean.bo;

/**
 * @package com.atguigu.jf.console.common.bean.bo
 * @Type ResultBean.java
 * @author 徐志超
 * @date 2016年11月8日
 * @describe 用于json操作返回数据
 * @version V 1.0
 */
public class ResultBean {
	
	public static final String SUCCESS_RESULT = "success";
	public static final String ERROR_RESULT = "error";

	private String result;
	private String msg;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
