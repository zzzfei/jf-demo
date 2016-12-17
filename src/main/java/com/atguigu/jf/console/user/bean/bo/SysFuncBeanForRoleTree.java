package com.atguigu.jf.console.user.bean.bo;


/**
 * @package com.atguigu.jf.console.user.bean.bo
 * @Type SysFuncBean.java
 * @author 徐志超
 * @date 2016年11月7日
 * @describe 用来展示菜单的简单逻辑类
 * @version V 1.0
 */
public class SysFuncBeanForRoleTree {

	private Long funcId;

	private Long supFuncId = new Long("0");

	private String name;

	private boolean checked;

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public Long getSupFuncId() {
		return supFuncId;
	}

	public void setSupFuncId(Long supFuncId) {
		this.supFuncId = supFuncId;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
