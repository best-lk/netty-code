package com.lk.netty.client.exception;

/**
 *  ҵ���쳣
 * 2019��3��27��
 * likai
 */
public class BusinessException extends RuntimeException{
	
	private static final long serialVersionUID = 5972043327038202753L;
	
	public BusinessException() {
		super();
	}
	
	public BusinessException(String msg) {
		super(msg);
	}
}
