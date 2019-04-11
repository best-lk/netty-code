package com.lk.netty.server.packet.req;

import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.packet.AbstractPacket;
import com.lk.netty.server.packet.PacketType;
import com.lk.netty.server.service.ChatMessageService;

import io.netty.buffer.ByteBuf;

public class ReqChatToSingle extends AbstractPacket{
	
	private String fromUserId;
	
	private String toUserId;
	
	private String message;
	
	@Override
	public PacketType getPacketType() {
		return PacketType.ReqChatToSingle;
	}

	@Override
	public void execPacket(IoSession session) {
		ChatMessageService.sendMessage(session, fromUserId, toUserId, message);
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
