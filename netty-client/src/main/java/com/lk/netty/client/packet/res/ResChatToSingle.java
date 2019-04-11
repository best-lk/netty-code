package com.lk.netty.client.packet.res;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketType;
import com.lk.netty.client.service.ChatService;

import io.netty.buffer.ByteBuf;

public class ResChatToSingle extends AbstractPacket{

	private String toUserId;
	
	private String toUserName;
	
	private String fromUserId;
	
	private String fromUserName;
	
	private String message;
	
	@Override
	public PacketType getPacketType() {
		return PacketType.ResChatToSingle;
	}

	@Override
	public void execPacket() {
		ChatService.acceptMessageFromSingle(this);
	}

	@Override
	public void writeBody(ByteBuf buf) {
		writeUTF8(buf, message);
		writeUTF8(buf, toUserId);
		writeUTF8(buf, toUserName);
		writeUTF8(buf, fromUserId);
		writeUTF8(buf, fromUserName);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.message = readUTF8(buf);
		this.toUserId = readUTF8(buf);
		this.toUserName = readUTF8(buf);
		this.fromUserId = readUTF8(buf);
		this.fromUserName = readUTF8(buf);
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
