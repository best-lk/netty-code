package com.lk.netty.server.exception;

/**
 *  �Զ�����쳣
 * 2019��3��11��
 * likai
 */
public class IllegalPacketException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public IllegalPacketException(String message) {
		super(message);
	}
}
