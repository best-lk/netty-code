package com.lk.netty.server.packet.res;

import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.packet.AbstractPacket;
import com.lk.netty.server.packet.PacketType;

import io.netty.buffer.ByteBuf;

public class ResUserLogin extends AbstractPacket{

	private Integer code;
	
	private String message;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.RespUserLogin;
	}

	@Override
	public void execPacket(IoSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeInt(code);
		writeUTF8(buf, message);	
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.code = buf.readInt();
		this.message = readUTF8(buf);
	}
}
