package com.lk.netty.client.exception;

/**
 * �Զ����쳣
 * 2019��3��11��
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
