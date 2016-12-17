package com.atguigu.jf.console.user.bean.bo;

import java.util.List;

/**
 * @package com.atguigu.jf.console.user.bean.bo
 * @Type SysFuncBean.java
 * @author 徐志超
 * @date 2016年11月7日
 * @describe 用来展示菜单的简单逻辑类
 * @version V 1.0
 */
public class SysFuncBean {
	
	private List<SysFuncBean> children;
	
	private Long funcId;

    private Short funcType;

    private Long supFuncId;

    private Short funcLevel;

    private String funcCode;

    private String funcName;

    private Integer funcOrder;

    private String funcUrl;

    private String funcImg;
    
    private boolean checked;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public SysFuncBean() {
		
	}

	public SysFuncBean(List<SysFuncBean> children, Long funcId, Short funcType,
			Long supFuncId, Short funcLevel, String funcCode, String funcName,
			Integer funcOrder, String funcUrl, String funcImg) {
		super();
		this.children = children;
		this.funcId = funcId;
		this.funcType = funcType;
		this.supFuncId = supFuncId;
		this.funcLevel = funcLevel;
		this.funcCode = funcCode;
		this.funcName = funcName;
		this.funcOrder = funcOrder;
		this.funcUrl = funcUrl;
		this.funcImg = funcImg;
	}

	public List<SysFuncBean> getChildren() {
		return children;
	}

	public void setChildren(List<SysFuncBean> children) {
		this.children = children;
	}

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public Short getFuncType() {
		return funcType;
	}

	public void setFuncType(Short funcType) {
		this.funcType = funcType;
	}

	public Long getSupFuncId() {
		return supFuncId;
	}

	public void setSupFuncId(Long supFuncId) {
		this.supFuncId = supFuncId;
	}

	public Short getFuncLevel() {
		return funcLevel;
	}

	public void setFuncLevel(Short funcLevel) {
		this.funcLevel = funcLevel;
	}

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public Integer getFuncOrder() {
		return funcOrder;
	}

	public void setFuncOrder(Integer funcOrder) {
		this.funcOrder = funcOrder;
	}

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}

	public String getFuncImg() {
		return funcImg;
	}

	public void setFuncImg(String funcImg) {
		this.funcImg = funcImg;
	}
	
	
    
    
}
