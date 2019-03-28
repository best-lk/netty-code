package com.lk.netty.client.session;

import com.lk.netty.client.packet.AbstractPacket;

import io.netty.channel.Channel;

/**
 * �ṩsession��һЩ��������
 * 2019��3��8��
 * likai
 */
public enum SessionManager {
	
	INSTANCE;

	/** ͨ�ŻỰ */
	private IoSession session;

	public void registerSession(Channel channel) {
		this.session = new IoSession(channel);
	}

	public void sendMessage(AbstractPacket request){
		this.session.sendPacket(request);
	}

	/**
	 * �Ƿ������Ϸ�����
	 * @return
	 */
	public boolean isConnectedSever() {
		return this.session != null;
	}
}
