package com.lk.netty.client.exception;

/**
 *  业务异常
 * 2019年3月27日
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
