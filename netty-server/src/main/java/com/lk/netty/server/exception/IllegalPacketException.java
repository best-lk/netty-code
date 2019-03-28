package com.lk.netty.server.exception;

/**
 *  自定义包异常
 * 2019年3月11日
 * likai
 */
public class IllegalPacketException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public IllegalPacketException(String message) {
		super(message);
	}
}
