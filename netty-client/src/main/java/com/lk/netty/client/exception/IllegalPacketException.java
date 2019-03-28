package com.lk.netty.client.exception;

/**
 * 自定义异常
 * 2019年3月11日
 * likai
 */
public class IllegalPacketException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public IllegalPacketException() {
		super();
	}

	public IllegalPacketException(String message) {
		super(message);
	}
}
