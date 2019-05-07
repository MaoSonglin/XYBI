package com.xinyibi.pojo;

public class AccountPower extends AccountPowerKey {

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}
}