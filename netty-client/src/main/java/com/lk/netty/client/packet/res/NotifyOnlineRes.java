package com.lk.netty.client.packet.res;
import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketType;

import io.netty.buffer.ByteBuf;

/**
 * ����֪ͨ
 * @author likai
 * 2019��4��11��
 */
public class NotifyOnlineRes extends AbstractPacket{

	@Override
	public PacketType getPacketType() {
		return PacketType.NotifyOnlineRes;
	}

	@Override
	public void execPacket() {
		//UserInfoService.getOnlineData(res);
	}

	@Override
	public void writeBody(ByteBuf buf) {
		
	}

	@Override
	public void readBody(ByteBuf buf) {
		
	}

}
