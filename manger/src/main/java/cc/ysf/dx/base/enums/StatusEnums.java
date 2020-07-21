package cc.ysf.dx.base.enums;

/**
 * >>> 系统状态枚举类
 */
public enum  StatusEnums {
	STATUS_ENABLE(1,"启用"),
	STATUS_DISABLE(0,"禁用")
	;
	private Integer code;
	private String remark;

	private StatusEnums (Integer code,String remark){
		this.code=code;
		this.remark=remark;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
