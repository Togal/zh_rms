package com.bms.rms.model.vo;

/**
 * 
 * Title:MenuTypeEnum
 * Description:菜单枚举类型
 * @author    zwb
 * @date      2016年10月17日 下午3:30:18
 *
 */
public enum MenuTypeEnum {
	master("主（Master）菜单",(byte)0),slave("从（Slave）菜单",(byte)1);
	private String type;
	private Byte value;

	MenuTypeEnum(String type, Byte name) {
		this.type = type;
		this.value = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Byte getValue() {
		return value;
	}

	public void setValue(Byte value) {
		this.value = value;
	}
	
}
