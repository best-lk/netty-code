package com.lk.netty.server.constant;

/**
 * session关闭的原因枚举
 * 2019年3月7日
 * likai
 */
public enum SessionCloseReason {
	
	/**
	 * 正常退出
	 */
	NORMAL(0, "正常退出"),
	
	/**
	 * 连接超时
	 */
	OVER_TIME(1, "连接超时");
	
	private Integer value;
	
	private String desc;
	
	private SessionCloseReason() {
		
	}
	
	private SessionCloseReason(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
