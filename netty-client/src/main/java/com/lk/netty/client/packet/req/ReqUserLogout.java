package com.lk.netty.client.packet.req;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketType;

import io.netty.buffer.ByteBuf;

/**
 *  �û��˳���¼������
 * @author likai
 * 2019��4��12��
 */
public class ReqUserLogout extends AbstractPacket{

	private String userId;
	
	@Override
	public PacketType getPacketType() {
		return PacketType.ReqUserLogout;
	}

	@Override
	public void execPacket() {
		
	}

	@Override
	public void writeBody(ByteBuf buf) {
		writeUTF8(buf, userId);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.userId = readUTF8(buf);
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
