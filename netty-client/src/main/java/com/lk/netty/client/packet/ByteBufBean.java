package com.lk.netty.client.packet;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;

/**
 *  提供抽象接口及通用的消息读写方法
 * 2019年3月8日
 * likai
 */
public abstract class ByteBufBean {
	
	abstract public void writeBody(ByteBuf buf);

	abstract public void readBody(ByteBuf buf);

	protected  String readUTF8(ByteBuf buf){
		int strSize = buf.readInt();
		byte[] content = new byte[strSize];
		buf.readBytes(content);
		try {
			return new String(content,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}

	}

	protected  void writeUTF8(ByteBuf buf,String msg){
		byte[] content ;
		try {
			content = msg.getBytes("UTF-8");
			buf.writeInt(content.length);
			buf.writeBytes(content);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
