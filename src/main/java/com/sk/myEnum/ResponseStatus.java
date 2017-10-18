package com.sk.myEnum;

public enum ResponseStatus {
	
	SUCCESS(1,"成功"),
	FAILED(2,"失敗");
	
	
	
	
	
	
	
	
	
	private int code;
	private String statuName;
	private ResponseStatus(int code, String statuName) {
		this.code = code;
		this.statuName = statuName;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getStatuName() {
		return statuName;
	}
	public void setStatuName(String statuName) {
		this.statuName = statuName;
	}
	
	
}
