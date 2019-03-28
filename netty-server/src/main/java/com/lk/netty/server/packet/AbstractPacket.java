package com.lk.netty.server.packet;

import com.lk.netty.server.io.util.IoSession;

/**
 * 抽象包定义
 * 2019年3月8日
 * likai
 */
public abstract class AbstractPacket extends ByteBufBean{
	
	abstract public PacketType getPacketType();

	/**
	 * 业务处理
	 */
	abstract public void execPacket(IoSession session);

	/**
	 *  是否开启gzip压缩(默认关闭)
	 *  消息体数据大的时候才开启，非常小的包压缩后体积反而变大，而且耗时
	 */
	public boolean isUseCompression() {
		return false;
	}
}
