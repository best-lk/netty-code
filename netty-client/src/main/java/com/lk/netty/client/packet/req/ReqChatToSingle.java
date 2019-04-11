package com.lk.netty.client.packet.req;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketType;

import io.netty.buffer.ByteBuf;

/**
 *  向一个人发送消息
 * @author likai
 * 2019年4月3日
 */
public class ReqChatToSingle extends AbstractPacket{

	private String fromUserId;
	
	private String toUserId;
	
	private String message;
	
	@Override
	public PacketType getPacketType() {
		return PacketType.ReqChatToSingle;
	}

	@Override
	public void execPacket() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void writeBody(ByteBuf buf) {
		writeUTF8(buf, toUserId);
		writeUTF8(buf, fromUserId);
		writeUTF8(buf, message);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.toUserId = readUTF8(buf);
		this.fromUserId = readUTF8(buf);
		this.message = readUTF8(buf);
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
