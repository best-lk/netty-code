package com.lk.netty.server.packet.res;

import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.packet.AbstractPacket;
import com.lk.netty.server.packet.PacketType;

import io.netty.buffer.ByteBuf;

public class ResChatToSingle extends AbstractPacket{

	private String fromUserId;
	
	private String fromUserName;
	
	private String toUserId;
	
	private String toUserName;
	
	private String message;
	
	@Override
	public PacketType getPacketType() {
		return PacketType.ResChatToSingle;
	}

	@Override
	public void execPacket(IoSession session) {
		
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
