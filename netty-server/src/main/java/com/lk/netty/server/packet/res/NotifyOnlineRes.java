package com.lk.netty.server.packet.res;

import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.packet.AbstractPacket;
import com.lk.netty.server.packet.PacketType;

import io.netty.buffer.ByteBuf;

/**
 * 上线通知
 * @author likai
 * 2019年4月11日
 */
public class NotifyOnlineRes extends AbstractPacket{

	@Override
	public PacketType getPacketType() {
		return PacketType.NotifyOnlineRes;
	}

	@Override
	public void execPacket(IoSession session) {
		
	}

	@Override
	public void writeBody(ByteBuf buf) {
		
	}

	@Override
	public void readBody(ByteBuf buf) {
		
	}

}
