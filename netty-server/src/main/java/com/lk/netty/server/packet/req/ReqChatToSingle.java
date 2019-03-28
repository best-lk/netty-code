package com.lk.netty.server.packet.req;

import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.packet.AbstractPacket;
import com.lk.netty.server.packet.PacketType;

import io.netty.buffer.ByteBuf;

public class ReqChatToSingle extends AbstractPacket{

	@Override
	public PacketType getPacketType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execPacket(IoSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeBody(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readBody(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

}
