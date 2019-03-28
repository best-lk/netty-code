package com.lk.netty.server.constant;

/**
 * session�رյ�ԭ��ö��
 * 2019��3��7��
 * likai
 */
public enum SessionCloseReason {
	
	/**
	 * �����˳�
	 */
	NORMAL(0, "�����˳�"),
	
	/**
	 * ���ӳ�ʱ
	 */
	OVER_TIME(1, "���ӳ�ʱ");
	
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
