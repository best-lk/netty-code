package com.lk.netty.server.packet;

import com.lk.netty.server.io.util.IoSession;

/**
 * ���������
 * 2019��3��8��
 * likai
 */
public abstract class AbstractPacket extends ByteBufBean{
	
	abstract public PacketType getPacketType();

	/**
	 * ҵ����
	 */
	abstract public void execPacket(IoSession session);

	/**
	 *  �Ƿ���gzipѹ��(Ĭ�Ϲر�)
	 *  ��Ϣ�����ݴ��ʱ��ſ������ǳ�С�İ�ѹ�������������󣬶��Һ�ʱ
	 */
	public boolean isUseCompression() {
		return false;
	}
}
