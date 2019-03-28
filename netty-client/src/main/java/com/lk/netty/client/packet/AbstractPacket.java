package com.lk.netty.client.packet;

import io.netty.buffer.ByteBuf;

/**
 *  �������ݰ�
 * 2019��3��8��
 * likai
 */
public abstract class AbstractPacket extends ByteBufBean{
	
	public void writeBody(ByteBuf buf) {
	}

	public void readBody(ByteBuf buf) {
	}

	abstract public PacketType getPacketType();

	abstract public void execPacket();


	/**
	 *  �Ƿ���gzipѹ��(Ĭ�Ϲر�)
	 *  ��Ϣ�����ݴ��ʱ��ſ������ǳ�С�İ�ѹ�������������󣬶��Һ�ʱ
	 */
	public boolean isUseCompression() {
		return false;
	}
}
