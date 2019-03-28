package com.lk.netty.client.session;

import com.lk.netty.client.packet.AbstractPacket;

import io.netty.channel.Channel;

/**
 * 提供session的一些基础服务
 * 2019年3月8日
 * likai
 */
public enum SessionManager {
	
	INSTANCE;

	/** 通信会话 */
	private IoSession session;

	public void registerSession(Channel channel) {
		this.session = new IoSession(channel);
	}

	public void sendMessage(AbstractPacket request){
		this.session.sendPacket(request);
	}

	/**
	 * 是否已连上服务器
	 * @return
	 */
	public boolean isConnectedSever() {
		return this.session != null;
	}
}
